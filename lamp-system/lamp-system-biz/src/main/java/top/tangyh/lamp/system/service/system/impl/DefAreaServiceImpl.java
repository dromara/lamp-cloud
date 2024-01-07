package top.tangyh.lamp.system.service.system.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.CollHelper;
import top.tangyh.basic.utils.StrPool;
import top.tangyh.basic.utils.TreeUtil;
import top.tangyh.lamp.common.constant.DefValConstants;

import top.tangyh.lamp.system.entity.system.DefArea;
import top.tangyh.lamp.system.manager.system.DefAreaManager;
import top.tangyh.lamp.system.service.system.DefAreaService;
import top.tangyh.lamp.system.vo.query.system.DefAreaPageQuery;
import top.tangyh.lamp.system.vo.save.system.DefAreaSaveVO;
import top.tangyh.lamp.system.vo.update.system.DefAreaUpdateVO;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 业务实现类
 * 地区表
 * </p>
 *
 * @author zuihou
 * @date 2021-10-13
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefAreaServiceImpl extends SuperServiceImpl<DefAreaManager, Long, DefArea> implements DefAreaService {
    private static String toJson(List<DefArea> list) {
        List<DefArea> treeList = TreeUtil.buildTree(list);
        List<Map<String, Object>> jsonList = new ArrayList<>();
        mapTree(jsonList, treeList);
        return JSONObject.toJSONString(jsonList);
    }

    private static void mapTree(List<Map<String, Object>> jsonList, List<DefArea> treeList) {
        Map<String, Object> map;
        for (DefArea defArea : treeList) {
            map = new HashMap<>();
            map.put("value", String.valueOf(defArea.getId()));
            map.put("label", defArea.getName());

            if (CollUtil.isNotEmpty(defArea.getChildren())) {
                List<Map<String, Object>> childList = new ArrayList<>();
                mapTree(childList, defArea.getChildren());
                map.put("children", childList);
            }
            jsonList.add(map);
        }
    }

    @Override
    protected <SaveVO> DefArea saveBefore(SaveVO saveVO) {
        DefAreaSaveVO defAreaSaveVO = (DefAreaSaveVO) saveVO;
        check(defAreaSaveVO.getCode(), null);
        DefArea defArea = super.saveBefore(defAreaSaveVO);
        fillArea(defArea);
        return defArea;
    }

    private void fillArea(DefArea defArea) {
        if (defArea.getParentId() == null || defArea.getParentId() <= 0) {
            defArea.setParentId(DefValConstants.PARENT_ID);
            defArea.setTreePath(DefValConstants.TREE_PATH_SPLIT);
            defArea.setTreeGrade(DefValConstants.TREE_GRADE);
        } else {
            DefArea parent = superManager.getById(defArea.getParentId());
            ArgumentAssert.notNull(parent, "请正确填写父级地区");
            defArea.setTreePath(StrPool.EMPTY);
            defArea.setTreeGrade(parent.getTreeGrade() + 1);
            defArea.setTreePath(TreeUtil.getTreePath(parent.getTreePath(), parent.getId()));
        }
    }

    @Override
    protected <UpdateVO> DefArea updateBefore(UpdateVO updateVO) {
        DefAreaUpdateVO defAreaUpdateVO = (DefAreaUpdateVO) updateVO;
        DefArea defArea = super.updateBefore(defAreaUpdateVO);
        fillArea(defArea);
        return defArea;
    }

    @Override
    public <UpdateVO> DefArea updateAllBefore(UpdateVO updateVO) {
        DefAreaUpdateVO defAreaUpdateVO = (DefAreaUpdateVO) updateVO;
        DefArea defArea = super.updateAllBefore(defAreaUpdateVO);
        fillArea(defArea);
        return defArea;
    }

    @Override
    public List<DefArea> findTree(DefAreaPageQuery pageQuery) {
        List<DefArea> list;
        if (pageQuery != null && StrUtil.isNotEmpty(pageQuery.getName())) {
            List<DefArea> searchList = superManager.list(Wraps.<DefArea>lbQ().like(DefArea::getName, pageQuery.getName()));
            if (searchList.isEmpty()) {
                return searchList;
            }
            List<String> paths = CollHelper.split(searchList, DefArea::getTreePath, DefValConstants.TREE_PATH_SPLIT);
            if (paths.isEmpty()) {
                return searchList;
            }
            Collection<Long> parentIds = CollUtil.trans(paths, Convert::toLong);
            List<DefArea> parentAreas = superManager.list(Wraps.<DefArea>lbQ().in(DefArea::getId, parentIds));
            searchList.addAll(parentAreas);
            list = searchList.stream().distinct().toList();
        } else {
            list = superManager.list();
        }
        return TreeUtil.buildTree(list);
    }

    @Override
    public Boolean check(String code, Long id) {
        ArgumentAssert.notEmpty(code, "请填写地区编码");
        long count = superManager.count(Wraps.<DefArea>lbQ().eq(DefArea::getCode, code).ne(DefArea::getId, id));
        return count > 0;
    }

    @Override
    public List<DefArea> findLazyList(Long parentId) {
        return superManager.list(Wraps.<DefArea>lbQ().eq(DefArea::getParentId, parentId));
    }

    @Override
    public void downloadJson(Integer treeGrade, HttpServletRequest request, HttpServletResponse response) {
        long start = System.currentTimeMillis();
        List<DefArea> list = superManager.list(
                Wraps.<DefArea>lbQ().le(DefArea::getTreeGrade, treeGrade).orderByAsc(DefArea::getSortValue)
        );
        long end = System.currentTimeMillis();
        log.info("查询时间: {}", end - start);
        down(toJson(list), response);
    }

    @SneakyThrows
    private void down(String text, HttpServletResponse response) {
        response.setContentType("application/octet-stream; charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=cities.json");
        ServletOutputStream out = response.getOutputStream();

        IoUtil.write(out, StandardCharsets.UTF_8, true, text);
    }

}
