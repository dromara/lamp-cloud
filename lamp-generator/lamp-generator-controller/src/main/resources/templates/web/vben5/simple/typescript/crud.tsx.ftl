import type {
  CreateCrudOptionsProps,
  CreateCrudOptionsRet,
  CrudExpose,
} from '@fast-crud/fast-crud';

import type { FormRulesExt } from '#/api';
import type { ${table.entityName}Model } from '#/api/${table.plusApplicationName}/${table.plusModuleName}/model/${table.entityName?uncap_first}Model';

import { ref, unref } from 'vue';
import { useRouter } from 'vue-router';

import { useAccess } from '@vben/access';
import { $t } from '@vben/locales';

import { ${table.entityName}Api } from '#/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';
import { ActionEnum } from '#/enums/commonEnum';
import { DictEnum } from '#/enums/commonEnum';
import {
  YES_NO_CONSTANT_DICT,
  backendDict,
  createdTimeColumn,
  deleteButton,
  indexColumn,
} from '#/plugins/fast-crud/common';

const { hasPermission } = useAccess();

type ContextRef = object;

export function createCrudOptions(
  props: CreateCrudOptionsProps<${table.entityName}Model.${resultVoName}, ContextRef>,
): CreateCrudOptionsRet<${table.entityName}Model.${resultVoName}> {
  const selectedRowKeys = ref<string[]>([]);
  <#if table.popupType == POPUP_TYPE_JUMP>
  const { push } = useRouter();
  // 注意，menuName 要跟(/anyone/visible/resource)实际返回的路由name一致才行。
  const menuName = '${table.menuName}编辑';
  </#if>
  return {
    crudOptions: {
      request: {
        pageRequest: ${table.entityName}Api.pageRequest,
<#if table.addShow || table.copyShow>
        addRequest: ${table.entityName}Api.addRequest,
</#if>
<#if table.editShow>
        editRequest: ${table.entityName}Api.editRequest,
</#if>
        delRequest: ${table.entityName}Api.delRequest,
        infoRequest: ${table.entityName}Api.infoRequest,
      },
      actionbar: {
        buttons: {
          add: {
          <#if !table.addShow>
            show: false,
          <#elseif table.addAuth?? && table.addAuth != '' >
            show: hasPermission('${table.addAuth}'),
          </#if>
          <#if table.popupType == POPUP_TYPE_JUMP>
            click() {
              push({ name: menuName, params: { type: ActionEnum.ADD, id: '0' } });
            },
          </#if>
          },
<#if table.deleteShow>
          ...deleteButton({
            crudExpose: props.crudExpose,
            selectedRowKeys,
            removeFn: ${table.entityName}Api.remove,
            <#if table.deleteAuth??>
            role: '${table.deleteAuth!}'
            </#if>
          }),
</#if>
        },
      },
      form: {
        wrapper: {
          <#if table.popupType == POPUP_TYPE_MODAL>
          is: 'a-modal',
          </#if>
        },
      },
      table: {
        striped: true,
        rowKey: 'id',
        rowSelection: {
          type: 'checkbox',
          selectedRowKeys,
          onChange: (changed: string[]) => {
            selectedRowKeys.value = changed;
          },
        },
      },
      rowHandle: {
        width: '200px',
        buttons: {
          edit: {
            <#if !table.editShow>
            show: false,
            <#elseif table.editAuth?? && table.editAuth != '' >
            show: hasPermission('${table.editAuth!}'),
            </#if>
            <#if table.popupType == POPUP_TYPE_JUMP>
            click({ row }) {
              push({ name: menuName, params: { type: ActionEnum.EDIT, id: row.id } });
            }
            </#if>
          },
          view: {
            show: true,
            <#if table.popupType == POPUP_TYPE_JUMP>
            click({ row }) {
              push({ name: menuName, params: { type: ActionEnum.VIEW, id: row.id } });
            }
            </#if>
          },
          remove: {
            <#if !table.deleteShow>
            show: false
            <#elseif table.deleteAuth?? && table.deleteAuth != '' >
            show: hasPermission('${table.deleteAuth!}')
            </#if>
          },
          copy: {
            <#if !table.copyShow>
            show: false
            <#elseif table.copyAuth?? && table.copyAuth != '' >
            show: hasPermission('${table.copyAuth!}')
            </#if>
          },
        },
      },
      columns: {
        ...indexColumn(props.crudExpose),
        <#if table.tplType == TPL_TREE>
        parentId: {
          title: 'parentId',
          type: 'text',
          form: { show: false },
          search: { show: false },
          column: { show: false },
        },
        parentName: {
          title: '上级节点',
          type: 'text',
          form: {
            component: {
              disabled: true
            },
            value: '根节点'
          }
          search: { show: false },
          column: { show: false },
        },
        </#if>
        <#list fields as field>
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
          // dict: backendDict(DictEnum.${field.dictType!?replace(".","_")}),
          dict: backendDict('${dt!?upper_case}'),
          </#if>
          <#else>
          </#if>
          search: { show: <#if field.isQuery && !field.isLogicDeleteField>true<#else>false</#if> },
          addForm: {
            show: <#if field.isEdit>true<#else>false</#if>,
            <#if field.editDefValue?? && field.editDefValue?trim != ''>
            <#if field.editDefValue?is_number || field.editDefValue?is_boolean || field.editDefValue == 'true' || field.editDefValue == 'false' || field.soyComponent == 'number'>
            value: ${field.editDefValue},
            <#else>
            value: '${field.editDefValue}',
            </#if>
            </#if>
          },
          editForm: { show: <#if field.isEdit>true<#else>false</#if> },
          form: {
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
            if (value !== null && row) {
              row[key] = value;
            }
          },
          </#if>
          column: {
            show: <#if field.isList && !field.isLogicDeleteField>true<#else>false</#if>,
            <#if field.width?? && field.width?trim != ''>
            minWidth: ${field.width},
            width: ${field.width}
            </#if>
            <#if field.echoStr?? && field.echoStr?trim != ''>
            formatter: scope => {
              return scope.row?.echoMap?.${field.javaField};
            }
            </#if>
          }
        },
        </#list>
        <#if table.tplType == TPL_TREE>
        sortValue: {
          title: $t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.sortValue'),
          type: 'number',
          search: { show: false },
          column: {
            minWidth: 40,
            width: 40
          }
        },
        </#if>
        ...createdTimeColumn({})
      },
    },
  };
}

export const frontRules = (
  _crudExpose: CrudExpose,
  _mode: ActionEnum,
): FormRulesExt => {
  return {};
};
