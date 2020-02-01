package com.github.zuihou;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.zuihou.authority.api.StationApi;
import com.github.zuihou.authority.entity.core.Org;
import com.github.zuihou.injection.annonation.InjectionField;
import com.github.zuihou.model.RemoteData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

import static com.github.zuihou.common.constant.InjectionFieldConstants.STATION_ID_METHOD;

@Data
@ToString
/**
 * 测试DTO
 *
 * @author zuihou
 * @date 2019/07/25
 */
public class D {
    private LocalDateTime date;
    private Date d2;

    @ApiModelProperty(value = "组织ID")
    @TableField("org_id")
    @InjectionField(feign = StationApi.class, method = STATION_ID_METHOD)
    private RemoteData<Long, Org> stationId;


    @InjectionField(feign = Object.class, method = STATION_ID_METHOD)
    private RemoteData<Long, Org> asss;


    @InjectionField(api = "stationServiceImpl", method = STATION_ID_METHOD)
    private RemoteData<Long, Org> statId;

}
