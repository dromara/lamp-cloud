import { useI18n } from '/@/hooks/web/useI18n';
import { VxeGridPropTypes } from 'vxe-table';
import { FormSchemaExt } from '/@/api/${projectPrefix}/common/formValidateService';

const { t } = useI18n();

export const formItems = () => {
  return [
<#list sub.fields as field>
  <#if field.isQuery && !field.isLogicDeleteField>
    {
      field: '${field.javaField}',
      title: t('${sub.table.plusApplicationName}.${sub.table.plusModuleName}.${sub.table.entityName?uncap_first}.${field.javaField}'),
      span: 6,
      folding: <#if field_index gt 3>true<#else>false</#if>,
      itemRender: { name: '${field.vxeComponent!'$input'}', props: { placeholder: '请输入' } },
    },
  </#if>
</#list>
    {
      span: 24,
      align: 'center',
      collapseNode: true,
      itemRender: {
        name: '$buttons',
        children: [
          {
            props: {
              type: 'submit',
              content: t('common.queryText'),
              status: 'primary',
            },
          },
          { props: { type: 'reset', content: t('common.resetText') } },
        ],
      },
    },
  ];
};

export const columns = (): VxeGridPropTypes.Columns => {
  return [
    { type: 'checkbox', width: 50, fixed: 'left' },
    { type: 'seq', width: 40, fixed: 'left' },
<#list sub.fields as field>
  <#if field.isList && !field.isLogicDeleteField>
    {
      field: '${field.javaField}',
      title: t('${sub.table.plusApplicationName}.${sub.table.plusModuleName}.${sub.table.entityName?uncap_first}.${field.javaField}'),
      editRender: { name: '${field.vxeComponent!'$input'}', attrs: { placeholder: '请输入' } },
    },
  </#if>
</#list>
    {
      field: 'createdTime',
      title: t('${projectPrefix}.common.createdTime'),
      sortable: true,
      width: 180,
    },
    {
      title: t('common.column.action'),
      width: 160,
      fixed: 'right',
      slots: { default: 'operate' },
    },
  ];
};

export const customFormSchemaRules = (): Partial<FormSchemaExt>[] => {
  return [];
};
