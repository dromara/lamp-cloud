package com.tangyh.lamp.authority.controller.poi;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.tangyh.basic.database.mybatis.conditions.Wraps;
import com.tangyh.lamp.authority.entity.common.Dictionary;
import com.tangyh.lamp.authority.entity.core.Org;
import com.tangyh.lamp.authority.entity.core.Station;
import com.tangyh.lamp.authority.service.common.DictionaryService;
import com.tangyh.lamp.authority.service.core.OrgService;
import com.tangyh.lamp.authority.service.core.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 用户导出字典处理器
 *
 * @author tangyh
 * @version v1.0
 * @date 2021/5/23 6:58 下午
 * @create [2021/5/23 6:58 下午 ] [tangyh] [初始创建]
 */
@Component
@RequiredArgsConstructor
public class UserExcelDictHandlerImpl implements IExcelDictHandler {
    public static final String DICT_STATION = "station";
    public static final String DICT_ORG = "org";
    public static final String DICT_NATION = "NATION";
    public static final String DICT_EDUCATION = "EDUCATION";
    public static final String DICT_POSITION_STATUS = "POSITION_STATUS";
    private final OrgService orgService;
    private final StationService stationService;
    private final DictionaryService dictionaryService;

    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        if (value == null) {
            return null;
        }
        if (DICT_ORG.equals(dict)) {
            Org org = orgService.getByIdCache(Convert.toLong(value));
            return org != null ? org.getLabel() : value.toString();
        }
        if (DICT_STATION.equals(dict)) {
            Station station = stationService.getByIdCache(Convert.toLong(value));
            return station != null ? station.getName() : value.toString();
        }
        if (StrUtil.equalsAny(dict, DICT_NATION, DICT_EDUCATION, DICT_POSITION_STATUS)) {
            Dictionary dictionary = dictionaryService.getOne(Wraps.<Dictionary>lbQ()
                    .eq(Dictionary::getType, dict)
                    .eq(Dictionary::getCode, String.valueOf(value)), false);
            return dictionary != null ? dictionary.getName() : String.valueOf(value);
        }
        return String.valueOf(value);
    }

    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        if (value == null) {
            return null;
        }
        if (DICT_STATION.equals(dict)) {
            Station station = stationService.getOne(Wraps.<Station>lbQ().eq(Station::getName, String.valueOf(value)), false);
            return station != null ? String.valueOf(station.getId()) : "";
        }
        if (DICT_ORG.equals(dict)) {
            Org org = orgService.getOne(Wraps.<Org>lbQ().eq(Org::getLabel, String.valueOf(value)), false);
            return org != null ? String.valueOf(org.getId()) : "";
        }
        if (StrUtil.equalsAny(dict, DICT_NATION, DICT_EDUCATION, DICT_POSITION_STATUS)) {
            Dictionary dictionary = dictionaryService.getOne(Wraps.<Dictionary>lbQ()
                    .eq(Dictionary::getType, dict)
                    .eq(Dictionary::getName, String.valueOf(value)), false);
            return dictionary != null ? dictionary.getCode() : String.valueOf(value);
        }
        return value.toString();
    }
}
