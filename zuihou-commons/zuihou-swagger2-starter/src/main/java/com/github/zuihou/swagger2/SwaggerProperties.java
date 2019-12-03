package com.github.zuihou.swagger2;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * swagger2 属性配置
 * 必须配置 prefix ，才能有提示
 *
 * @author zuihou
 * @date 2018/11/18 9:17
 */
@Data
//@RefreshScope
@ConfigurationProperties(prefix = "zuihou.swagger")
public class SwaggerProperties {
    /**
     * 是否开启swagger
     **/
    private Boolean enabled = true;

    /**
     * 是否生产环境
     */
    private Boolean production = false;
    /**
     * 离线文档路径
     */
    private Markdown markdown = new Markdown();


    /**
     * 访问账号密码
     */
    private Basic basic = new Basic();

    /**
     * 标题
     **/
    private String title = "在线文档";
    private String group = "";
    /**
     * 描述
     **/
    private String description = "zuihou-admin-cloud 在线文档";
    /**
     * 版本
     **/
    private String version = "1.0";
    /**
     * 许可证
     **/
    private String license = "";
    /**
     * 许可证URL
     **/
    private String licenseUrl = "";
    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl = "";

    private Contact contact = new Contact();

    /**
     * swagger会解析的包路径
     **/
    private String basePackage = "com.github.zuihou";

    /**
     * swagger会解析的url规则
     **/
    private List<String> basePath = new ArrayList<>();
    /**
     * 在basePath基础上需要排除的url规则
     **/
    private List<String> excludePath = new ArrayList<>();

    /**
     * 分组文档
     **/
    private Map<String, DocketInfo> docket = new LinkedHashMap<>();

    /**
     * host信息
     **/
    private String host = "";

    /**
     * 排序
     */
    private Integer order = 1;

    /**
     * 全局参数配置
     **/
    private List<GlobalOperationParameter> globalOperationParameters;

    @Setter
    @Getter
    public static class GlobalOperationParameter {
        /**
         * 参数名
         **/
        private String name;

        /**
         * 描述信息
         **/
        private String description = "全局参数";

        /**
         * 指定参数类型
         **/
        private String modelRef = "String";

        /**
         * 参数放在哪个地方:header,query,path,body.form
         **/
        private String parameterType = "header";

        /**
         * 参数是否必须传
         **/
        private Boolean required = false;
        /**
         * 默认值
         */
        private String defaultValue = "";
        /**
         * 允许为空
         */
        private Boolean allowEmptyValue = true;
        /**
         * 排序
         */
        private int order = 1;
    }

    @Data
    public static class DocketInfo {
        /**
         * 标题
         **/
        private String title = "在线文档";
        /**
         * 自定义组名
         */
        private String group = "";
        /**
         * 描述
         **/
        private String description = "zuihou-admin-cloud 在线文档";
        /**
         * 版本
         **/
        private String version = "1.0";
        /**
         * 许可证
         **/
        private String license = "";
        /**
         * 许可证URL
         **/
        private String licenseUrl = "";
        /**
         * 服务条款URL
         **/
        private String termsOfServiceUrl = "";

        private Contact contact = new Contact();

        /**
         * swagger会解析的包路径
         **/
        private String basePackage = "";

        /**
         * swagger会解析的url规则
         **/
        private List<String> basePath = new ArrayList<>();
        /**
         * 在basePath基础上需要排除的url规则
         **/
        private List<String> excludePath = new ArrayList<>();

        private List<GlobalOperationParameter> globalOperationParameters;

        /**
         * 排序
         */
        private Integer order = 1;

        public String getGroup() {
            if (group == null || "".equals(group)) {
                return title;
            }
            return group;
        }
    }

    public String getGroup() {
        if (group == null || "".equals(group)) {
            return title;
        }
        return group;
    }

    @Data
    public static class Contact {
        /**
         * 联系人
         **/
        private String name = "zuihou";
        /**
         * 联系人url
         **/
        private String url = "https://github.com/zuihou/zuihou-admin-cloud";
        /**
         * 联系人email
         **/
        private String email = "244387066@qq.com";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Basic {
        private Boolean enable = false;
        private String username = "zuihou";
        private String password = "zuihou";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Markdown {
        private Boolean enable = false;
        private String basePath = "classpath:markdown/*";
    }
}
