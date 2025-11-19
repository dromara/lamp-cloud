<script lang="ts" setup>
import type { VbenFormProps } from '@vben/common-ui';

import type {
  VxeGridListeners,
  VxeGridProps,
  VxeGridPropTypes,
  VxeTablePropTypes,
} from '#/adapter/vxe-table';
import type { ${sub.table.entityName}Model } from '#/api/${sub.table.plusApplicationName}/${sub.table.plusModuleName}/model/${sub.table.entityName?uncap_first}Model';

import { ref } from 'vue';

import { Page } from '@vben/common-ui';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { ${sub.table.entityName}Api } from '#/api/${sub.table.plusApplicationName}/${sub.table.plusModuleName}/${sub.table.entityName?uncap_first}';
import { TableAction } from '#/components/table-action';
import { ActionEnum } from '#/enums/commonEnum';
import { useMessage } from '#/hooks/web/useMessage';
import { $t } from '#/locales';

import { gridSchemas, searchFormSchemas } from '../data/slave';

const ${table.subJavaFieldName}Ref = ref<string>('');
const editRules = ref<VxeTablePropTypes.EditRules>({
});
const { createMessage } = useMessage();

const formOptions: VbenFormProps = {
  ...searchFormSchemas,
  collapsed: true,
};

const gridOptions: VxeGridProps<${sub.table.entityName}Model.${sub.table.entityName}ResultVO> = {
  ...gridSchemas,
  height: 'auto',
  keepSource: true,
  proxyConfig: {
    autoLoad: false,
    ajax: {
      query: async (
        params: VxeGridPropTypes.ProxyAjaxQueryParams,
        formValues,
      ) => {
        const { page, sorts, filters } = params;
        if (!${table.subJavaFieldName}Ref.value) {
          createMessage.warn($t('common.pleaseSave'));
          throw new Error($t('common.pleaseSave'));
        }

        const queryParams: any = { model: { ...formValues } };
        // 处理排序条件
        const firstSort = sorts[0];
        if (firstSort) {
          queryParams.sort = firstSort.field;
          queryParams.order = firstSort.order;
        }
        queryParams.size = page.pageSize;
        queryParams.current = page.currentPage;
        // 处理筛选条件
        filters.forEach(({ field, values }) => {
          queryParams[field] = values.join(',');
        });
        queryParams.model.${table.subJavaFieldName} = ${table.subJavaFieldName}Ref.value;
        return ${sub.table.entityName}Api.page(queryParams).then((r) => {
          r.total = Number(r.total);
          return r;
        });
      },
    },
  },
};

const handleBatchDelete = () => {
  const selectRecords = gridApi.grid.getCheckboxRecords();
  if (selectRecords.length > 0) {
    gridApi.grid.removeCheckboxRow();
  } else {
    createMessage.warning($t('common.tips.pleaseSelectTheData'));
  }
};

const gridEvents: VxeGridListeners<any> = {
  toolbarButtonClick({ code }) {
    switch (code) {
      case 'batchDelete': {
        handleBatchDelete();
        break;
      }
      case 'myInsert': {
        gridApi.grid.insert({
          ${table.subJavaFieldName}: ${table.subJavaFieldName}Ref.value,
          state: true,
        });
        break;
      }
    }
  },
};

const [Grid, gridApi] = useVbenVxeGrid({
  gridEvents,
  formOptions,
  gridOptions,
});

// 删除
const handleDelete = (row: any) => {
  gridApi.grid.remove(row);
};

const getRecordset = () => {
  const $grid = gridApi.grid;
  const recordset = $grid.getRecordset();
  return recordset;
};

const fullValidate = () => {
  return gridApi.grid.fullValidate();
};

async function load(type: ActionEnum, subId?: string) {
  ${table.subJavaFieldName}Ref.value = '';
  if (type !== ActionEnum.ADD) {
    ${table.subJavaFieldName}Ref.value = subId || '';
    gridApi.grid.commitProxy('_init');
  }
  // const validateApi = Api[VALIDATE_API[type]];
  // const rules = await getValidateRuleObj(validateApi, customFormSchemaRules());
  // gridApi && rules && (editRules.value = rules);
}

defineExpose({ load, fullValidate, getRecordset });
</script>

<template>
  <Page auto-content-height>
    <Grid :edit-rules="editRules" :separator="false">
      <template #ACTION="{ row }">
        <TableAction
          :actions="[
            {
              label: $t('common.delete'),
              <#if sub.table.deleteAuth?? && sub.table.deleteAuth != ''>
              auth: ['${sub.table.deleteAuth}'],
              </#if>
              popConfirm: {
                title: $t('common.tips.confirmDelete'),
                confirm: handleDelete.bind(null, row),
              },
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
