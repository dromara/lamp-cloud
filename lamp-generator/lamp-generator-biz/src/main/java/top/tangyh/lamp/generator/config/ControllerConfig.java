package top.tangyh.lamp.generator.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import top.tangyh.lamp.generator.enumeration.SuperClassEnum;

/**
 * @author zuihou
 * @date 2022/3/9 21:40
 */
@Data
@NoArgsConstructor
public class ControllerConfig {


    /**
     * 生成 <code>@RestController</code> 控制器（默认 false）
     * <pre>
     *      <code>@Controller</code> -> <code>@RestController</code>
     * </pre>
     */
    private Boolean restStyle = true;
    /**
     * 格式化文件名称
     * 默认： entityName + ConstVal.CONTROLLER
     */
    private String formatFileName;

    /**
     * 驼峰转连字符（默认 false）
     * <pre>
     *      <code>@RequestMapping("/managerUserActionHistory")</code> -> <code>@RequestMapping("/manager-user-action-history")</code>
     * </pre>
     */
    private Boolean hyphenStyle = false;

    /**
     * 自定义继承的Controller类全称，带包名
     */
    private String superClass = SuperClassEnum.SUPER_CLASS.getController();


}
