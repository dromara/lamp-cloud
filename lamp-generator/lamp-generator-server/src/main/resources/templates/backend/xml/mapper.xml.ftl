<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${mapperName}">
<!--
    代码生成器 by ${datetime}
    自定义sql建议在base文件夹同级新建ext文件夹，并新建同名且同namespace的xml进行编写。方便修改字段时，重新生成此文件。
-->
<#if mapperConfig.cache>
    <!-- 开启二级缓存 -->
    <cache type="${mapperConfig.cacheClass.getName()}"/>
</#if>

<#if mapperConfig.baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${entityPackage}">
<#list allFields as field>
<#if field.isPk>
        <id column="${field.name}" property="${field.javaField}" />
<#else>
    <#if field.javaField!="${tenantIdColumn!''}">
        <result column="${field.name}" property="${field.javaField}" />
    </#if>
</#if>
</#list>
    </resultMap>

</#if>
<#if mapperConfig.baseColumnList>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#list allFields as field><#if field.javaField!="${tenantIdColumn!''}">${field.name}<#if field_index!=allFields?size - 1>, </#if>${((field_index + 1) % 6 ==0)?string('\r\n        ', '')}</#if></#list>
    </sql>

</#if>
</mapper>
