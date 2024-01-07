<template>
  <vxe-grid ref="xGrid" v-bind="gridOptions" v-on="gridEvents">
    <template #operate="{ row }">
      <PopConfirmButton
        circle
        circleIcon="ant-design:delete-outlined"
        title="确认删除吗？"
        @confirm="removeRowEvent(row)"
      />
    </template>
  </vxe-grid>
</template>
<script lang="ts">
  import { defineComponent, reactive, ref } from 'vue';
  import { VxeGridInstance, VxeGridListeners, VxeGridProps, VxeTablePropTypes } from 'vxe-table';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { PopConfirmButton } from '/@/components/Button';
  import { ActionEnum, VALIDATE_API } from '/@/enums/commonEnum';
  import { getValidateRuleObj } from '/@/api/${projectPrefix}/common/formValidateService';
  import { Api, page as pageRequest } from '/@/api/${sub.table.plusApplicationName}/${sub.table.plusModuleName}/${sub.table.entityName?uncap_first}';
  import { columns, customFormSchemaRules, formItems } from './${sub.table.entityName?uncap_first}.data';

  export default defineComponent({
    name: '${sub.table.menuName}',
    components: { PopConfirmButton },
    setup: function () {
      const { t } = useI18n();
      const { createMessage } = useMessage();
      const ${table.subJavaFieldName} = ref<string>('');

      const typeRef = ref<ActionEnum>(ActionEnum.ADD);
      const xGrid = ref<VxeGridInstance>({});
      const validRules = ref<VxeTablePropTypes.EditRules>({});

      const gridOptions = reactive<VxeGridProps>({
        id: '${sub.table.entityName}',
        height: 600,
        rowConfig: {
          // 自定义行数据唯一主键的字段名（默认自动生成）
          keyField: 'id',
          // 当鼠标移到行时，是否要高亮当前行
          isHover: true,
        },
        // 自定义列配置项
        customConfig: {
          // 是否启用 localStorage 本地保存，会将列操作状态保留在本地（需要有 id）
          storage: true,
        },
        formConfig: {
          items: formItems(),
        },
        toolbarConfig: {
          perfect: true,
          buttons: [
            { code: 'myInsert', name: '新增' },
            {
              code: 'delete',
              name: '删除',
              status: 'danger',
            },
          ],
        },
        // 分页配置项
        pagerConfig: {
          // pageSize: 10,
        },
        // 数据代理配置项（基于 Promise API）
        proxyConfig: {
          // 只接收Promise，具体实现自由发挥
          ajax: {
            // 当点击工具栏查询按钮或者手动提交指令 query或reload 时会被触发
            query: ({ page, sorts, filters, form }) => {
              const queryParams: any = { model: { ...form } };

              if (!${table.subJavaFieldName}.value) {
                createMessage.warn('请先保存数据');
                return Promise.reject('请先保存数据');
              }
              // 处理排序条件
              const firstSort = sorts[0];
              if (firstSort) {
                queryParams.sort = firstSort.property;
                queryParams.order = firstSort.order;
              }
              queryParams.size = page.pageSize;
              queryParams.current = page.currentPage;
              queryParams.model.${table.subJavaFieldName} = ${table.subJavaFieldName}.value;
              // 处理筛选条件
              filters.forEach(({ property, values }) => {
                queryParams[property] = values.join(',');
              });
              return pageRequest(queryParams).then((r) => {
                r.total = Number(r.total);
                return r;
              });
            },
          },
        },
        columns: columns(),
        editRules: validRules,
        editConfig: {
          trigger: 'click',
          mode: 'row',
          showStatus: true,
          autoClear: true,
        },
      });

      const gridEvents: VxeGridListeners = {
        toolbarButtonClick({ code }) {
          const $grid = xGrid.value;
          switch (code) {
            case 'myInsert': {
              $grid.insert({
                ${table.subJavaFieldName}: ${table.subJavaFieldName}.value,
              });
              break;
            }
          }
        },
      };

      const getRecordset = () => {
        const $grid = xGrid.value;
        const recordset = $grid.getRecordset();
        if (typeRef.value === ActionEnum.COPY) {
          return { insertRecords: $grid.getData() };
        }
        return recordset;
      };

      const fullValidate = () => {
        const $grid = xGrid.value;
        return $grid.fullValidate();
      };

      async function load(type: ActionEnum, tId?: string) {
        ${table.subJavaFieldName}.value = '';
        typeRef.value = type;
        if (type !== ActionEnum.ADD) {
          ${table.subJavaFieldName}.value = tId || '';
          reload();
        }

        if (type !== ActionEnum.VIEW) {
          let validateApi = Api[VALIDATE_API[type]];
          const rules = await getValidateRuleObj(validateApi, customFormSchemaRules());
          const $grid = xGrid.value;
          $grid && rules && (validRules.value = rules);
        }
      }

      const insertEvent = async (row: any) => {
        const $grid = xGrid.value;
        const record = {
          ${table.subJavaFieldName}: ${table.subJavaFieldName}.value,
        };
        const { row: newRow } = await $grid.insertAt(record, row);
        await $grid.setActiveCell(newRow, 'key');
      };

      const removeRowEvent = async (row: any) => {
        const $grid = xGrid.value;
        if ($grid) {
          $grid.remove(row);
          createMessage.success(t('common.tips.deleteSuccess'));
        }
      };

      function reload() {
        const $grid = xGrid.value;
        $grid.commitProxy('query');
      }

      async function reset() {
        const $grid = xGrid.value;
        await $grid.revertData();
        await $grid.clearAll();
        await $grid.clearData();
        await $grid.remove();
        $grid.getRefMaps().refForm.value.reset();
      }

      return {
        t,
        xGrid,
        gridOptions,
        gridEvents,
        load,
        reset,
        insertEvent,
        removeRowEvent,
        getRecordset,
        fullValidate,
      };
    },
  });
</script>
