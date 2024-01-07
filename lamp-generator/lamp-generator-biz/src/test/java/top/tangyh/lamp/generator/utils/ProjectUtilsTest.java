package top.tangyh.lamp.generator.utils;

import top.tangyh.basic.database.properties.DatabaseProperties;
import top.tangyh.lamp.generator.enumeration.ProjectTypeEnum;
import top.tangyh.lamp.generator.vo.save.ProjectGeneratorVO;

/**
 * @author tangyh
 * @version v1.0
 * @date 2022/4/5 5:54 PM
 * @create [2022/4/5 5:54 PM ] [tangyh] [初始创建]
 */
public class ProjectUtilsTest {
    public static void main(String[] args) {
        ProjectGeneratorVO vo = new ProjectGeneratorVO();
        vo.setProjectPrefix("lamp");
        vo.setOutputDir("/Users/tangyh/gitlab/lamp-cloud-pro-datasource-column");
        vo.setType(ProjectTypeEnum.CLOUD);
        vo.setAuthor("阿汤哥");
        vo.setServiceName("test");
        vo.setModuleName("test");
        vo.setParent("top.tangyh.lamp");
        vo.setGroupId("top.tangyh.lamp");
        vo.setUtilParent("top.tangyh.basic");
        vo.setVersion("4.15.0-java17");
        vo.setDescription("测试服务");
        vo.setServerPort(8080);
        ProjectUtils.generator(vo, new DatabaseProperties());
    }
}
