//package top.tangyh.lamp.sms.dto;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.LinkedHashMap;
//import java.util.List;
//
///**
// * 短信发送任务
// *
// * @author zuihou
// * @date 2018/12/24
// */
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ApiModel(value = "SmsSendTask", description = "短信发送DTO")
//public class SmsSendTaskDTO {
//
//    @ApiModelProperty(value = "接收者手机")
//    @Size(min = 1, message = "请填写接收者手机")
//    private List<String> telNum;
//
//    /**
//     * 短信主题
//     */
//    @ApiModelProperty(value = "短信主题")
//    @Size(max = 255, message = "短信主题不能超过255")
//    private String topic;
//
//    /**
//     * 短信模板参数
//     * 需要封装为{"key":"value", ...}格式，且key必须有序
//     * <p>
//     * java代码中请使用 JSONObject.parseObject(xxx, Feature.OrderedField); 进行解析
//     * java代码中请使用 JSONObject obj = new JSONObject(true); 来设置有序的key
//     */
//    @ApiModelProperty(value = "短信模板参数")
//    @Size(max = 500, message = "短信模板参数不能超过500")
//    @NotNull(message = "短信模板参数不能为空")
//    private LinkedHashMap<String, String> templateParam;
//
//    /**
//     * 短信发送时间
//     */
//    @ApiModelProperty(value = "短信发送时间")
//    private LocalDateTime sendTime;
//
//    /**
//     * 发送内容
//     */
//    @ApiModelProperty(value = "发送内容")
//    @Size(max = 500, message = "发送内容不能超过450")
//    private String content;
//    /**
//     * 是否为草稿
//     * #BooleanStatus
//     */
//    @ApiModelProperty(value = "是否为草稿")
//    private Boolean draft;
//
//    /**
//     * 最少传递的参数
//     *
//     * @param templateParam 模版参数
//     * @return 短信任务
//     */
//    public SmsSendTaskDTO build(LinkedHashMap templateParam, String... telNum) {
//        return SmsSendTaskDTO.builder()
//                .telNum(Arrays.asList(telNum))
//                .templateParam(templateParam)
//                .build();
//    }
//
//}
