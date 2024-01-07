package top.tangyh.lamp.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.tangyh.lamp.generator.enumeration.TplEnum;

/**
 * web pro 配置
 *
 * @author zuihou
 * @date 2022/3/23 22:31
 */
@Data
@NoArgsConstructor
public class WebProConfig {
    /**
     * 格式化菜单文件名称
     */
    private String formatMenuName = "{}维护";

    /**
     * 前端生成页面样式模板
     */
    private TplEnum tpl = TplEnum.SIMPLE;

}
