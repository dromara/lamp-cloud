import { Ref } from 'vue';
<#list dataTsImport as pkg>
import ${pkg};
</#list>
import { <#if table.tplType != TPL_TREE>BasicColumn, </#if>FormSchema } from '/@/components/Table';
import { useI18n } from '/@/hooks/web/useI18n';
import { ActionEnum } from '/@/enums/commonEnum';
import { FormSchemaExt } from '/@/api/${projectPrefix}/common/formValidateService';

const { t } = useI18n();
<#if table.tplType != TPL_TREE>
// 列表页字段
export const columns = (): BasicColumn[] => {
  return [
<#list fields as field>
<#if field.isList && !field.isLogicDeleteField>
    {
      title: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.${field.javaField}'),
<#if field.echoStr?? && field.echoStr?trim != ''>
      dataIndex: ['echoMap', '${field.javaField}'],
      key: '${field.javaField}',
<#else >
      dataIndex: '${field.javaField}',
</#if>
<#if field.width?? && field.width?trim != ''>
      width: ${field.width},
</#if>
<#if field.indexHelpMessage?? && field.indexHelpMessage?trim != ''>
    helpMessage: '${field.indexHelpMessage}',
</#if>
    },
</#if>
</#list>
<#if table.tplType == TPL_TREE>
    {
      title: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.sortValue'),
      dataIndex: 'sortValue',
      width: 40,
    },
</#if>
    {
      title: t('${projectPrefix}.common.createdTime'),
      dataIndex: 'createdTime',
      sorter: true,
      width: 180,
    },
  ];
};

export const searchFormSchema = (): FormSchema[] => {
  return [
<#list fields as field>
<#if field.isQuery && !field.isLogicDeleteField>
    {
      label: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.${field.javaField}'),
      field: '${field.javaField}',
      component: '${field.component}',
<#if field.javaType =="LocalDateTime">
      componentProps: {
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'YYYY-MM-DD HH:mm:ss',
        showTime: { defaultValue: dateUtil('00:00:00', 'HH:mm:ss') },
      },
<#elseif field.javaType =="LocalDate">
      componentProps: {
        format: 'YYYY-MM-DD',
        valueFormat: 'YYYY-MM-DD',
        showTime: { defaultValue: dateUtil('00:00:00', 'HH:mm:ss') },
      },
<#elseif field.javaType =="LocalTime">
      componentProps: {
        format: 'HH:mm:ss',
        valueFormat: 'HH:mm:ss',
        showTime: { defaultValue: dateUtil('00:00:00', 'HH:mm:ss') },
      },
<#elseif field.javaType =="Boolean">
      componentProps: {
        ...yesNoComponentProps(),
      },
<#elseif field.enumStr?? && field.enumStr?trim != ''>
      componentProps: {
        // 生成的 EnumEnum 常量不存在时，请自行在 EnumEnum 中添加: ${field.javaType} = '${field.javaType}',
        // 请确保后端方法：OauthGeneralController#findEnumListByType 能扫描到后端的枚举类： ${field.javaType}，否则无法回显！
        // ...enumComponentProps(EnumEnum.${field.javaType}),
        ...enumComponentProps('${field.javaType}'),
      },
<#elseif field.dictType?? && field.dictType?trim != ''>
      componentProps: {
    <#if field.dictType?contains('"')>
        ...dictComponentProps(${field.dictType}),
    <#else>
        <#assign dotIndex=field.dictType?last_index_of('.') + 1 />
        <#assign dt=field.dictType?substring(dotIndex?number) />
        // 建议将魔法数参数移动到 DictEnum 中，并添加为: ${field.dictType!?replace(".","_")} = '${dt!?upper_case}';
        // '${dt!?upper_case}' 需要与 后端DictType类中的参数 以及 def_dict表中的key字段 保持一致，否则无法回显！
        // ...dictComponentProps(DictEnum.${field.dictType!?replace(".","_")}),
        ...dictComponentProps('${dt!?upper_case}'),
    </#if>
      },
<#else></#if>
      colProps: { span: 6 },
    },
</#if>
</#list>
    {
      field: 'createTimeRange',
      label: t('${projectPrefix}.common.createdTime'),
      component: 'RangePicker',
      colProps: { span: 6 },
    },
  ];
};
</#if>

// 编辑页字段
export const editFormSchema = (_type: Ref<ActionEnum>): FormSchema[] => {
  return [
    {
      field: 'id',
      label: 'ID',
      component: 'Input',
      show: false,
    },
<#if table.tplType == TPL_TREE>
    {
      field: 'parentId',
      label: 'parentId',
      component: 'Input',
      show: false,
    },
    {
      field: 'parentName',
      label: '上级节点',
      defaultValue: '根节点',
      component: 'Input',
      dynamicDisabled: true,
    },
</#if>
<#list fields as field>
<#if field.isEdit && !field.isLogicDeleteField>
    {
      label: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.${field.javaField}'),
      field: '${field.javaField}',
      component: '${field.component}',
<#if field.editDefValue?? && field.editDefValue?trim != ''>
    <#if field.editDefValue?is_number || field.editDefValue?is_boolean || field.editDefValue == 'true' || field.editDefValue == 'false'>
      defaultValue: ${field.editDefValue},
    <#else>
      defaultValue: '${field.editDefValue}',
    </#if>
</#if>
<#if field.editHelpMessage?? && field.editHelpMessage?trim != ''>
      helpMessage: '${field.editHelpMessage}',
</#if>
<#if field.javaType =="LocalDateTime">
      componentProps: {
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'YYYY-MM-DD HH:mm:ss',
        showTime: { defaultValue: dateUtil('00:00:00', 'HH:mm:ss') },
      },
<#elseif field.javaType =="LocalDate">
      componentProps: {
        format: 'YYYY-MM-DD',
        valueFormat: 'YYYY-MM-DD',
        showTime: { defaultValue: dateUtil('00:00:00', 'HH:mm:ss') },
      },
<#elseif field.javaType =="LocalTime">
      componentProps: {
        format: 'HH:mm:ss',
        valueFormat: 'HH:mm:ss',
        showTime: { defaultValue: dateUtil('00:00:00', 'HH:mm:ss') },
      },
<#elseif field.javaType =="Boolean">
      componentProps: {
        ...yesNoComponentProps(),
      },
<#elseif field.enumStr?? && field.enumStr?trim != ''>
      componentProps: {
        // 生成的 EnumEnum 常量不存在时，请自行在 EnumEnum 中添加: ${field.javaType} = '${field.javaType}',
        // 请确保后端方法：OauthGeneralController#findEnumListByType 能扫描到后端的枚举类： ${field.javaType}，否则无法回显！
        // ...enumComponentProps(EnumEnum.${field.javaType}),
        ...enumComponentProps('${field.javaType}'),
      },
<#elseif field.dictType?? && field.dictType?trim != ''>
      componentProps: {
    <#if field.dictType?contains('"')>
        ...dictComponentProps(${field.dictType}),
    <#else>
        <#assign dotIndex=field.dictType?last_index_of('.') + 1 />
        <#assign dt=field.dictType?substring(dotIndex?number) />
        // 建议将魔法数参数移动到 DictEnum 中，并添加为: ${field.dictType!?replace(".","_")} = '${dt!?upper_case}';
        // '${dt!?upper_case}' 需要与 后端DictType类中的参数 以及 def_dict表中的key字段 保持一致，否则无法回显！
        // ...dictComponentProps(DictEnum.${field.dictType!?replace(".","_")}),
        ...dictComponentProps('${dt!?upper_case}'),
    </#if>
      },
<#else></#if>
    },
</#if>
</#list>
<#if table.tplType == TPL_TREE>
    {
      label: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.sortValue'),
      field: 'sortValue',
      component: 'InputNumber',
    },
</#if>
  ];
};

// 前端自定义表单验证规则
export const customFormSchemaRules = (_): Partial<FormSchemaExt>[] => {
  return [];
};
