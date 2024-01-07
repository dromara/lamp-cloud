package top.tangyh.lamp.generator.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import top.tangyh.basic.base.controller.SuperCacheController;
import top.tangyh.basic.base.controller.SuperController;
import top.tangyh.basic.base.controller.SuperReadController;
import top.tangyh.basic.base.controller.SuperSimpleController;
import top.tangyh.basic.base.controller.SuperWriteController;
import top.tangyh.basic.base.manager.SuperCacheManager;
import top.tangyh.basic.base.manager.SuperManager;
import top.tangyh.basic.base.manager.impl.SuperCacheManagerImpl;
import top.tangyh.basic.base.manager.impl.SuperManagerImpl;
import top.tangyh.basic.base.mapper.SuperMapper;
import top.tangyh.basic.base.service.SuperCacheService;
import top.tangyh.basic.base.service.SuperService;
import top.tangyh.basic.base.service.impl.SuperCacheServiceImpl;
import top.tangyh.basic.base.service.impl.SuperServiceImpl;
import top.tangyh.basic.interfaces.BaseEnum;

/**
 * 父类
 *
 * @author tangyh
 * @date 2022/10/28 4:59 PM
 * @create [2022/10/28 4:59 PM ] [tangyh] [初始创建]
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SuperClassEnum implements BaseEnum {
    /**
     * 增删改查
     */
    SUPER_CLASS("01", SuperController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 增删改查 + 导入导出
     */
    SUPER_POI_CLASS("02", SuperController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 增删改查 + 缓存
     */
    SUPER_CACHE_CLASS("03", SuperCacheController.class.getName(),
            SuperCacheService.class.getName(), SuperCacheServiceImpl.class.getName(),
            SuperCacheManager.class.getName(), SuperCacheManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 没有增删改查
     */
    SUPER_SIMPLE_CLASS("04", SuperSimpleController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 仅查询方法
     */
    SUPER_READ_CLASS("05", SuperReadController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * 仅查询方法
     */
    SUPER_WRITE_CLASS("06", SuperWriteController.class.getName(),
            SuperService.class.getName(), SuperServiceImpl.class.getName(),
            SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * controller和service不继承父类， manager和mapper 继承
     */
    NONE_CS("07", "", "", "", SuperManager.class.getName(), SuperManagerImpl.class.getName(),
            SuperMapper.class.getName()),
    /**
     * controller、service、 manager和mapper不继承父类
     */
    NONE("08", "", "", "", "", "", "");

    private String value;
    private String controller;
    private String service;
    private String serviceImpl;
    private String manager;
    private String managerImpl;
    private String mapper;

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return this.name();
    }
}
