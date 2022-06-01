package top.tangyh.lamp.model.constant;

/**
 * 存放系统中常用的类型
 *
 * @author zuihou
 * @date 2019/07/26
 */
public interface EchoDictType {
    // @lamp.generator auto insert EchoDictType
    /**
     * 职位状态
     */
    String POSITION_STATUS = "POSITION_STATUS";
    /**
     * 民族
     */
    String NATION = "NATION";
    /**
     * 学历
     */
    String EDUCATION = "EDUCATION";
    /**
     * 行政区级
     */
    String AREA_LEVEL = "AREA_LEVEL";
    /**
     * 资源类型
     */
    String RESOURCE_TYPE = "RESOURCE_TYPE";
    /**
     * 角色类别
     */
    String ROLE_CATEGORY = "ROLE_CATEGORY";
    /**
     * 机构类型
     */
    String ORG_TYPE = "ORG_TYPE";
    String[] ALL = new String[]{
            EDUCATION, NATION, POSITION_STATUS, AREA_LEVEL, ORG_TYPE
    };


}
