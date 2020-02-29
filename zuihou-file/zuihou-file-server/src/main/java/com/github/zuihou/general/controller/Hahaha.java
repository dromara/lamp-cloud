package com.github.zuihou.general.controller;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.zuihou.authority.api.OrgApi;
import com.github.zuihou.authority.entity.auth.User;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.authority.entity.core.Station;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;
import static com.github.zuihou.common.constant.InjectionFieldConstants.*;

@Data
public class Hahaha {
    @InjectionField(api = ORG_ID_FEIGN_CLASS, method = ORG_ID_METHOD, beanClass = Org.class)
    private RemoteData<Long, Org> org;

    @InjectionField(api = ORG_ID_FEIGN_CLASS, method = ORG_ID_NAME_METHOD)
    private RemoteData<Long, String> org2;

    @InjectionField(feign = OrgApi.class, method = ORG_ID_METHOD)
    private RemoteData<Long, Org> org3;
    @InjectionField(feign = OrgApi.class, method = ORG_ID_NAME_METHOD, beanClass = String.class)
    private RemoteData<Long, String> org4;


    @InjectionField(api = "stationApi", method = STATION_ID_METHOD, beanClass = Station.class)
    private RemoteData<Long, Station> station;
    @InjectionField(api = "userApi", method = USER_ID_METHOD, beanClass = User.class)
    private RemoteData<Long, User> user;

    /**
     * 民族
     *
     * @InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD) RemoteData<String, String>
     */
    @ApiModelProperty(value = "民族")
    @Length(max = 20, message = "民族长度不能超过20")
    @TableField(value = "nation", condition = LIKE)
    @InjectionField(api = DICTIONARY_ITEM_FEIGN_CLASS, method = DICTIONARY_ITEM_METHOD)
    private RemoteData<String, String> nation;
}
