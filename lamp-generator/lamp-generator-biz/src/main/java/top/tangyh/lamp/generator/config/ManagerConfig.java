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
public class ManagerConfig {

    /**
     * 自定义继承的Manager类全称，带包名
     */
    private String superManagerClass = SuperClassEnum.SUPER_CLASS.getManager();

    /**
     * 自定义继承的ManagerImpl类全称，带包名
     */
    private String superManagerImplClass = SuperClassEnum.SUPER_CLASS.getManagerImpl();
    /**
     * 格式化Manager接口文件名称
     */
    private String formatManagerFileName;
    /**
     * 格式化Manager实现类文件名称
     */
    private String formatManagerImplFileName;


}
