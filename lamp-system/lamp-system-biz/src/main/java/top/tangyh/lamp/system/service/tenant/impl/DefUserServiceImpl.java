package top.tangyh.lamp.system.service.tenant.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tangyh.basic.base.request.PageParams;
import top.tangyh.basic.base.service.impl.SuperCacheServiceImpl;
import top.tangyh.basic.context.ContextUtil;
import top.tangyh.basic.database.mybatis.conditions.Wraps;
import top.tangyh.basic.database.mybatis.conditions.query.LbQueryWrap;
import top.tangyh.basic.exception.BizException;
import top.tangyh.basic.jackson.JsonUtil;
import top.tangyh.basic.utils.ArgumentAssert;
import top.tangyh.basic.utils.BeanPlusUtil;
import top.tangyh.lamp.common.cache.tenant.base.DefUserEmailCacheKeyBuilder;
import top.tangyh.lamp.common.cache.tenant.base.DefUserIdCardCacheKeyBuilder;
import top.tangyh.lamp.common.cache.tenant.base.DefUserMobileCacheKeyBuilder;

import top.tangyh.lamp.common.properties.SystemProperties;
import top.tangyh.lamp.file.service.AppendixService;
import top.tangyh.lamp.system.entity.tenant.DefUser;
import top.tangyh.lamp.system.manager.tenant.DefUserManager;
import top.tangyh.lamp.system.service.tenant.DefUserService;
import top.tangyh.lamp.system.vo.query.tenant.DefUserPageQuery;
import top.tangyh.lamp.system.vo.result.tenant.DefUserResultVO;
import top.tangyh.lamp.system.vo.save.tenant.DefUserSaveVO;
import top.tangyh.lamp.system.vo.update.tenant.DefUserAvatarUpdateVO;
import top.tangyh.lamp.system.vo.update.tenant.DefUserBaseInfoUpdateVO;
import top.tangyh.lamp.system.vo.update.tenant.DefUserEmailUpdateVO;
import top.tangyh.lamp.system.vo.update.tenant.DefUserMobileUpdateVO;
import top.tangyh.lamp.system.vo.update.tenant.DefUserPasswordResetVO;
import top.tangyh.lamp.system.vo.update.tenant.DefUserPasswordUpdateVO;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 用户
 * </p>
 *
 * @author zuihou
 * @date 2021-10-09
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class DefUserServiceImpl extends SuperCacheServiceImpl<DefUserManager, Long, DefUser>
        implements DefUserService {

    private final AppendixService appendixService;
    private final SystemProperties systemProperties;

    @Override
    public Map<Serializable, Object> findByIds(Set<Serializable> ids) {
        return superManager.findByIds(ids.stream().map(Convert::toLong).collect(Collectors.toSet()));
    }

    @Override
    public boolean checkUsername(String value, Long id) {
        return superManager.count(Wraps.<DefUser>lbQ().eq(DefUser::getUsername, value).ne(DefUser::getId, id)) > 0;
    }

    @Override
    public boolean checkEmail(String value, Long id) {
        return superManager.count(Wraps.<DefUser>lbQ().eq(DefUser::getEmail, value).ne(DefUser::getId, id)) > 0;
    }

    @Override
    public boolean checkMobile(String value, Long id) {
        return superManager.count(Wraps.<DefUser>lbQ().eq(DefUser::getMobile, value).ne(DefUser::getId, id)) > 0;
    }

    @Override
    public boolean checkIdCard(String value, Long id) {
        return superManager.count(Wraps.<DefUser>lbQ().eq(DefUser::getIdCard, value).ne(DefUser::getId, id)) > 0;
    }

    @Override
    public DefUser getUserByMobile(String mobile) {
        return superManager.getUserByMobile(mobile);
    }

    @Override
    public DefUser getUserByEmail(String email) {
        return superManager.getUserByEmail(email);
    }

    @Override
    public DefUser getUserByIdCard(String idCard) {
        return superManager.getUserByIdCard(idCard);
    }

    @Override
    public DefUser getUserByUsername(String username) {
        return superManager.getUserByUsername(username);
    }

    @Override
    protected <SaveVO> DefUser saveBefore(SaveVO vo) {
        DefUserSaveVO saveVO = (DefUserSaveVO) vo;
        ArgumentAssert.isFalse(checkUsername(saveVO.getUsername(), null), "用户名：{}已经存在", saveVO.getUsername());
        if (StrUtil.isNotEmpty(saveVO.getEmail())) {
            ArgumentAssert.isFalse(checkEmail(saveVO.getEmail(), null), "邮箱：{}已经存在", saveVO.getEmail());
        }
        if (StrUtil.isNotEmpty(saveVO.getMobile())) {
            ArgumentAssert.isFalse(checkMobile(saveVO.getMobile(), null), "手机号：{}已经存在", saveVO.getMobile());
        }
        if (StrUtil.isNotEmpty(saveVO.getIdCard())) {
            ArgumentAssert.isFalse(checkIdCard(saveVO.getIdCard(), null), "身份证号：{}已经存在", saveVO.getIdCard());
        }
        DefUser defUser = BeanUtil.toBean(saveVO, DefUser.class);
        defUser.setSalt(RandomUtil.randomString(20));
        if (StrUtil.isEmpty(defUser.getPassword())) {
            defUser.setPassword(systemProperties.getDefPwd());
        }
        defUser.setPassword(SecureUtil.sha256(defUser.getPassword() + defUser.getSalt()));
        defUser.setPasswordErrorNum(0);
        defUser.setReadonly(false);
        defUser.setState(true);
        return defUser;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String register(DefUser defUser) {
        ArgumentAssert.isFalse(checkMobile(defUser.getMobile(), null), "手机号：{}已经存在", defUser.getMobile());
        setDefUser(defUser);
        defUser.setNickName(defUser.getMobile());

        superManager.save(defUser);
        return defUser.getMobile();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String registerByEmail(DefUser defUser) {
        ArgumentAssert.isFalse(checkMobile(defUser.getEmail(), null), "邮箱：{}已经存在", defUser.getMobile());
        setDefUser(defUser);
        defUser.setNickName(defUser.getEmail());

        superManager.save(defUser);
        return defUser.getEmail();
    }

    private void setDefUser(DefUser defUser) {
        defUser.setSalt(RandomUtil.randomString(20));
        defUser.setPassword(SecureUtil.sha256(defUser.getPassword() + defUser.getSalt()));
        defUser.setPasswordErrorNum(0);
        defUser.setReadonly(false);
        defUser.setState(true);
        defUser.setUsername(UUID.fastUUID().toString(true));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean resetPassword(DefUserPasswordResetVO data) {
        if (data.getIsUseSystemPassword()) {
            data.setPassword(systemProperties.getDefPwd());
        } else {
            ArgumentAssert.notEmpty(data.getConfirmPassword(), "请输入确认密码");
            ArgumentAssert.notEmpty(data.getPassword(), "请输入密码");
            ArgumentAssert.equals(data.getConfirmPassword(), data.getPassword(), "密码和确认密码不一致");
        }
        DefUser user = superManager.getById(data.getId());
        ArgumentAssert.notNull(user, "您要重置密码的用户不存在");

        return updateUserPassword(user.getId(), data.getPassword(), user.getSalt());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateState(Long id, Boolean state) {
        // 演示环境专用标识，用于WriteInterceptor拦截器判断演示环境需要禁止用户执行sql，若您无需搭建演示环境，可以删除下面一行代码
        ContextUtil.setStop();
        return superManager.updateById(DefUser.builder().state(state).id(id).build());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateAvatar(DefUserAvatarUpdateVO data) {
        ArgumentAssert.isFalse(data.getAppendixAvatar() == null, "请上传或选择头像");
        boolean flag = appendixService.save(data.getId(), data.getAppendixAvatar());
        superManager.delCache(data.getId());
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updatePassword(DefUserPasswordUpdateVO data) {
        ArgumentAssert.notEmpty(data.getOldPassword(), "请输入旧密码");
        DefUser user = superManager.getById(data.getId());
        ArgumentAssert.notNull(user, "用户不存在");
        ArgumentAssert.equals(user.getId(), ContextUtil.getUserId(), "只能修改自己的密码");
        String oldPassword = SecureUtil.sha256(data.getOldPassword() + user.getSalt());
        ArgumentAssert.equals(user.getPassword(), oldPassword, "旧密码错误");

        return updateUserPassword(user.getId(), data.getPassword(), user.getSalt());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateMobile(DefUserMobileUpdateVO data) {
        Long id = ContextUtil.getUserId();
        DefUser user = superManager.getById(id);
        ArgumentAssert.notNull(user, "用户不存在");
        user.setMobile(data.getMobile());
        superManager.updateById(user);

        // 淘汰旧手机缓存
        cacheOps.del(DefUserMobileCacheKeyBuilder.builder(user.getMobile()));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateEmail(DefUserEmailUpdateVO data) {
        Long id = ContextUtil.getUserId();
        DefUser user = superManager.getById(id);
        ArgumentAssert.notNull(user, "用户不存在");
        user.setEmail(data.getEmail());
        superManager.updateById(user);
        cacheOps.del(DefUserEmailCacheKeyBuilder.builder(user.getEmail()));
        return true;
    }

    private boolean updateUserPassword(Long id, String password, String salt) {
        if (StrUtil.isEmpty(salt)) {
            salt = RandomUtil.randomString(20);
        }
        String defPassword = SecureUtil.sha256(password + salt);

        boolean flag = superManager.update(Wrappers.<DefUser>lambdaUpdate()
                .set(DefUser::getPassword, defPassword)
                .set(DefUser::getPasswordErrorNum, 0L)
                .set(DefUser::getPasswordErrorLastTime, null)
                .set(DefUser::getPasswordExpireTime, null)
                .eq(DefUser::getId, id)
        );
        superManager.delCache(id);
        return flag;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateBaseInfo(DefUserBaseInfoUpdateVO data) {
        DefUser old = getById(data.getId());
        DefUser defUser = BeanUtil.toBean(data, DefUser.class);

        boolean flag = superManager.updateById(defUser);
        if (StrUtil.isAllNotEmpty(data.getIdCard(), old.getIdCard()) && !StrUtil.equals(old.getIdCard(), data.getIdCard())) {
            cacheOps.del(DefUserIdCardCacheKeyBuilder.builder(old.getIdCard()));
        }
        return flag;
    }

    @Override
    public IPage<DefUserResultVO> pageUser(PageParams<DefUserPageQuery> params) {
        IPage<DefUser> page = params.buildPage(DefUser.class);
        DefUserPageQuery pageQuery = params.getModel();
        return superManager.pageUser(pageQuery, page);
    }

    @Override
    public List<Long> findUserIdList(DefUserPageQuery pageQuery) {
        if (pageQuery == null) {
            return superManager.listObjs(Wraps.<DefUser>lbQ().select(DefUser::getId), Convert::toLong);
        }
        return superManager.listObjs(Wraps.<DefUser>lbQ().select(DefUser::getId)
                        .like(DefUser::getMobile, pageQuery.getMobile())
                        .like(DefUser::getUsername, pageQuery.getUsername())
                        .like(DefUser::getIdCard, pageQuery.getIdCard())
                        .like(DefUser::getEmail, pageQuery.getEmail())
                        .eq(DefUser::getSex, pageQuery.getSex())
                , Convert::toLong);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resetPassErrorNum(Long id) {
        int count = superManager.resetPassErrorNum(id);
        superManager.delCache(id);
        return count;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void incrPasswordErrorNumById(Long id) {
        superManager.incrPasswordErrorNumById(id);
        superManager.delCache(id);
    }

    @Override
    public List<DefUserResultVO> queryUser(DefUserPageQuery params) {
        LbQueryWrap<DefUser> wrap = Wraps.lbQ();
        if (StrUtil.isAllEmpty(params.getEmail(), params.getUsername(), params.getIdCard(), params.getMobile())) {
            throw BizException.wrap("请至少传递一个参数");
        }
        wrap.eq(DefUser::getEmail, params.getEmail())
                .eq(DefUser::getUsername, params.getUsername())
                .eq(DefUser::getIdCard, params.getIdCard())
                .eq(DefUser::getMobile, params.getMobile());
        List<DefUser> list = superManager.list(wrap);
        return BeanPlusUtil.copyToList(list, DefUserResultVO.class);
    }
}
