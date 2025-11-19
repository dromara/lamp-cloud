import type {
  CrudExpose,
  CreateCrudOptionsRet,
} from '@fast-crud/fast-crud';
import { ${table.entityName}Api } from '#/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';
import { YES_NO_CONSTANT_DICT, backendDict } from '#/plugins/fast-crud/common';
import { $t } from '#/locales';
import { ActionEnum } from '#/enums/commonEnum';
import type { FormRulesExt } from '#/api';


export function createCrudOptions(): CreateCrudOptionsRet {
  return {
    crudOptions: {
      request: {
        <#if table.addShow || table.copyShow>
        addRequest: ${table.entityName}Api.addRequest,
        </#if>
        <#if table.editShow>
        editRequest: ${table.entityName}Api.editRequest,
        </#if>
      },
      form: {
        // 单列布局
        col: { span: 24 }
      },
      addForm: { mode: 'add' },
      editForm: { mode: 'edit' },
      columns: {
        id: {
          title: 'id',
          form: { show: false },
          type: 'text'
        },
        <#if table.tplType == TPL_TREE>
        parentId: {
          title: 'parentId',
          form: { show: false },
          type: 'text'
        },
        parentName: {
          title: '上级节点',
          type: 'text',
          form: {
            component: { disabled: true },
            value: '根节点'
          }
        },
        </#if>
        <#list fields as field>
        <#if field.isEdit && !field.isLogicDeleteField>
        ${field.javaField}: {
          title: $t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.${field.javaField}'),
          type: '${field.soyComponent!}',
          <#if field.javaType =="Boolean">
          dict: YES_NO_CONSTANT_DICT,
          <#elseif field.enumStr?? && field.enumStr?trim != ''>
          dict: backendDict({ type: '${field.javaType}', isEnum: true }),
          <#elseif field.dictType?? && field.dictType?trim != ''>
          <#if field.dictType?contains('"')>
          dict: backendDict(${field.dictType}),
          <#else>
          <#assign dotIndex=field.dictType?last_index_of('.') + 1 />
          <#assign dt=field.dictType?substring(dotIndex?number) />
          // 建议将魔法数参数移动到 DictEnum 中，并添加为: ${field.dictType!?replace(".","_")} = '${dt!?upper_case}';
          // '${dt!?upper_case}' 需要与后端DictType类中的参数 以及 def_dict表中的key字段 保持一致，否则无法回显！
          // ...dictComponentProps(DictEnum.${field.dictType!?replace(".","_")}),
          dict: backendDict('${dt!?upper_case}'),
          </#if>
          <#else>
          </#if>
          addForm: {
            <#if field.editDefValue?? && field.editDefValue?trim != ''>
            <#if field.editDefValue?is_number || field.editDefValue?is_boolean || field.editDefValue == 'true' || field.editDefValue == 'false' || field.soyComponent == 'number'>
            value: ${field.editDefValue},
            <#else>
            value: '${field.editDefValue}',
            </#if>
            </#if>
          },
          form: {
            show: <#if field.isEdit>true<#else>false</#if>,
            <#if field.editHelpMessage?? && field.editHelpMessage?trim != ''>
            helper: '${field.editHelpMessage}',
            </#if>
            component: {
              <#if field.javaType =="LocalDateTime">
              defaultTime: '00:00:00',
              format: 'YYYY-MM-DD HH:mm:ss',
              valueFormat: 'YYYY-MM-DD HH:mm:ss',
              <#elseif field.javaType =="LocalDate">
              format: 'YYYY-MM-DD',
              valueFormat: 'YYYY-MM-DD',
              <#elseif field.javaType =="LocalTime">
              format: 'HH:mm:ss',
              valueFormat: 'HH:mm:ss',
              <#elseif field.soyComponent == "dict-radio">
              optionName: 'a-radio-button'
              <#else></#if>
            },
          },
          <#if field.javaType =="LocalDateTime" || field.javaType =="LocalDate" || field.javaType =="LocalTime">
          valueBuilder({ value, row, key }) {
            if (value !== null) {
              row[key] = value;
            }
          },
          </#if>
        },
        </#if>
        </#list>
        <#if table.tplType == TPL_TREE>
        sortValue: {
          title: $t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.sortValue'),
          type: 'number',
        },
        </#if>
      }
    }
  };
}

export const frontRules = (
  _crudExpose: CrudExpose,
  _mode: ActionEnum,
): FormRulesExt => {
  return {};
};