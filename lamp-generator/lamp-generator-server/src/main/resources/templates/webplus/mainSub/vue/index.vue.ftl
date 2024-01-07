<template>
  <PageWrapper dense contentFullHeight>
    <BasicTable @register="registerTable">
      <template #toolbar>
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
        <a-button type="primary" @click="handleImport">{{ t('common.title.import') }}</a-button>
        <a-button type="primary" @click="handleExport">{{ t('common.title.export') }}</a-button>
</#if>
        <#if table.deleteShow>
        <a-button
          type="primary"
          color="error"
          preIcon="ant-design:delete-outlined"
          @click="handleBatchDelete"
          <#if table.deleteAuth?? && table.deleteAuth != ''>
          v-hasAnyPermission="['${table.deleteAuth}']"
          </#if>
        >
          {{ t('common.title.delete') }}
        </a-button>
        </#if>
        <#if table.addShow>
        <a-button
          type="primary"
          preIcon="ant-design:plus-outlined"
          @click="handleAdd"
        <#if table.addAuth?? && table.addAuth != ''>
          v-hasAnyPermission="['${table.addAuth}']"
        </#if>
        >
          {{ t('common.title.add') }}
        </a-button>
        </#if>
      </template>
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'action'">
          <TableAction
            :actions="[
              <#if table.viewShow>
              {
                tooltip: t('common.title.view'),
                icon: 'ant-design:search-outlined',
                onClick: handleView.bind(null, record),
              },
              </#if>
              <#if table.editShow>
              {
                tooltip: t('common.title.edit'),
                icon: 'ant-design:edit-outlined',
                onClick: handleEdit.bind(null, record),
                <#if table.editAuth?? && table.editAuth != ''>
                auth: '${table.editAuth}',
                </#if>
              },
              </#if>
              <#if table.copyShow>
              {
                tooltip: t('common.title.copy'),
                icon: 'ant-design:copy-outlined',
                <#if table.copyAuth?? && table.copyAuth != ''>
                auth: '${table.copyAuth}',
                </#if>
                popConfirm: {
                  title: t('common.tips.confirmCopy'),
                  confirm: handleCopy.bind(null, record),
                },
              },
              </#if>
              <#if table.deleteShow>
              {
                tooltip: t('common.title.delete'),
                icon: 'ant-design:delete-outlined',
                color: 'error',
                <#if table.deleteAuth?? && table.deleteAuth != ''>
                auth: '${table.deleteAuth}',
                </#if>
                popConfirm: {
                  title: t('common.tips.confirmDelete'),
                  confirm: handleDelete.bind(null, record),
                },
              },
              </#if>
            ]"
            :stopButtonPropagation="true"
          />
        </template>
      </template>
    </BasicTable>
    <#if table.popupType == POPUP_TYPE_MODAL>
    <EditModal @register="registerModal" @success="handleSuccess" />
    <#elseif table.popupType == POPUP_TYPE_DRAWER>
    <EditModal @register="registerDrawer" @success="handleSuccess" />
    </#if>
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
    <PreviewExcelModel
      width="70%"
      @register="exportRegister"
      @success="handleExportSuccess"
      :exportApi="exportFile"
      :previewApi="exportPreview"
    />
    <ImpExcelModel
      @register="importRegister"
      @success="handleImportSuccess"
      :api="importFile"
      templateHref=""
    />
</#if>
  </PageWrapper>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { PageWrapper } from '/@/components/Page';
  <#if table.popupType == POPUP_TYPE_MODAL || table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
  import { useModal } from '/@/components/Modal';
  </#if>
  <#if table.popupType == POPUP_TYPE_DRAWER>
  import { useDrawer } from '/@/components/Drawer';
  <#elseif table.popupType == POPUP_TYPE_JUMP>
  import { useRouter } from 'vue-router';
  </#if>
  <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
  import { ImpExcelModel, PreviewExcelModel } from '/@/components/Poi';
  </#if>
  import { <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>handleSearchInfoByCreateTime, </#if>handleFetchParams } from '/@/utils/${projectPrefix}/common';
  import { ActionEnum } from '/@/enums/commonEnum';
  <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
  import { copy, page, remove, importFile, exportFile, exportPreview } from '/@/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';
  <#else>
  import { copy, page, remove } from '/@/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';
  </#if>
  import { columns, searchFormSchema } from './${table.entityName?uncap_first}.data';
  <#if table.popupType == POPUP_TYPE_MODAL || table.popupType == POPUP_TYPE_DRAWER >
  import EditModal from './Edit.vue';
  </#if>

  export default defineComponent({
    // 若需要开启页面缓存，请将此参数跟菜单名保持一致
    name: '${table.menuName}',
    components: {
      BasicTable,
      PageWrapper,
      TableAction,
    <#if table.popupType == POPUP_TYPE_MODAL || table.popupType == POPUP_TYPE_DRAWER >
      EditModal,
    </#if>
    <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
      ImpExcelModel,
      PreviewExcelModel,
    </#if>
    },
    setup() {
      const { t } = useI18n();
      const { createMessage, createConfirm } = useMessage();
      <#if table.popupType == POPUP_TYPE_MODAL>
      const [registerModal, { openModal }] = useModal();
      <#elseif table.popupType == POPUP_TYPE_DRAWER>
      const [registerDrawer, { openDrawer }] = useDrawer();
      <#elseif table.popupType == POPUP_TYPE_JUMP>
      const { replace } = useRouter();
      const menuName = '编辑${table.menuName}';
      </#if>

      // 表格
      const [registerTable, { reload, getSelectRowKeys<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>, getForm</#if> }] = useTable({
        title: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.table.title'),
        api: page,
        columns: columns(),
        formConfig: {
          name: '${table.entityName}Search',
          labelWidth: 120,
          schemas: searchFormSchema(),
          autoSubmitOnEnter: true,
          resetButtonOptions: {
            preIcon: 'ant-design:rest-outlined',
          },
          submitButtonOptions: {
            preIcon: 'ant-design:search-outlined',
          },
        },
        beforeFetch: handleFetchParams,
        useSearchForm: true,
        showTableSetting: true,
        bordered: true,
        rowKey: 'id',
        rowSelection: {
          type: 'checkbox',
          columnWidth: 40,
        },
        actionColumn: {
          width: 200,
          title: t('common.column.action'),
          dataIndex: 'action',
        },
      });

      <#if table.copyShow>
      // 直接复制
      async function handleCopy(record: Recordable, e: Event) {
        e?.stopPropagation();
        await copy(record.id);
        createMessage.success(t('common.tips.copySuccess'));
        handleSuccess();
      }
      </#if>
      <#if table.addShow>
      // 弹出新增页面
      function handleAdd() {
        <#if table.popupType == POPUP_TYPE_MODAL>
        openModal(true, {
          type: ActionEnum.ADD,
        });
        <#elseif table.popupType == POPUP_TYPE_DRAWER>
        openDrawer(true, {
          type: ActionEnum.ADD,
        });
        <#else>
        replace({
          // name 一定要唯一，且跟存储在def_resource表中的name一致
          name: menuName,
          params: { id: '0' },
          query: { type: ActionEnum.ADD },
        });
        </#if>
      }
      </#if>

      <#if table.viewShow>
      // 弹出查看页面
      function handleView(record: Recordable, e: Event) {
        e?.stopPropagation();
        <#if table.popupType == POPUP_TYPE_MODAL>
        openModal(true, {
          record,
          type: ActionEnum.VIEW,
        });
        <#elseif table.popupType == POPUP_TYPE_DRAWER>
        openDrawer(true, {
          record,
          type: ActionEnum.VIEW,
        });
        <#else>
        replace({
          // name 一定要唯一，且跟存储在def_resource表中的name一致
          name: menuName,
          params: { id: record.id },
          query: { type: ActionEnum.VIEW },
        });
        </#if>
      }
      </#if>

      <#if table.editShow>
      // 弹出编辑页面
      function handleEdit(record: Recordable, e: Event) {
        e?.stopPropagation();
        <#if table.popupType == POPUP_TYPE_MODAL>
        openModal(true, {
          record,
          type: ActionEnum.EDIT,
        });
        <#elseif table.popupType == POPUP_TYPE_DRAWER>
        openDrawer(true, {
          record,
          type: ActionEnum.EDIT,
        });
        <#else>
        replace({
          // name 一定要唯一，且跟存储在def_resource表中的name一致
          name: menuName,
          params: { id: record.id },
          query: { type: ActionEnum.EDIT },
        });
        </#if>
      }
      </#if>

      // 新增或编辑成功回调
      function handleSuccess() {
        reload();
      }

      <#if table.deleteShow>
      async function batchDelete(ids: string[]) {
        await remove(ids);
        createMessage.success(t('common.tips.deleteSuccess'));
        handleSuccess();
      }

      // 点击单行删除
      function handleDelete(record: Recordable, e: Event) {
        e?.stopPropagation();
        if (record?.id) {
          batchDelete([record.id]);
        }
      }

      // 点击批量删除
      function handleBatchDelete() {
        const ids = getSelectRowKeys();
        if (!ids || ids.length <= 0) {
          createMessage.warning(t('common.tips.pleaseSelectTheData'));
          return;
        }
        createConfirm({
          iconType: 'warning',
          content: t('common.tips.confirmDelete'),
          onOk: async () => {
            try {
              await batchDelete(ids);
            } catch (e) {}
          },
        });
      }
      </#if>

      <#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
      // 导入弹窗
      const [importRegister, importModal] = useModal();
      // 导出弹窗
      const [exportRegister, exportModel] = useModal();
      // 导入成功
      function handleImportSuccess(_data) {
        reload();
      }

      // 导出成功
      function handleExportSuccess() {
        reload();
      }

      // 点击导出按钮
      function handleExport() {
        const form = getForm();
        let params = { ...form.getFieldsValue() };
        params = handleSearchInfoByCreateTime(params);
        params.extra = {
          ...{
            fileName: t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.table.title'),
          },
          ...params?.extra,
        };
        params.size = 20000;

        exportModel.openModal(true, {
          params,
        });
      }

      return {
        t,
        registerTable,
        <#if table.popupType == POPUP_TYPE_MODAL>
        registerModal,
        <#elseif table.popupType == POPUP_TYPE_DRAWER>
        registerDrawer,
        </#if>
        <#if table.viewShow>
        handleView,
        </#if>
        <#if table.addShow>
        handleAdd,
        </#if>
        <#if table.copyShow>
        handleCopy,
        </#if>
        <#if table.editShow>
        handleEdit,
        </#if>
        <#if table.deleteShow>
        handleDelete,
        handleBatchDelete,
        </#if>
        handleSuccess,
        importRegister,
        handleImport: importModal.openModal,
        handleImportSuccess,
        importFile,
        exportRegister,
        handleExport,
        handleExportSuccess,
        exportFile,
        exportPreview,
      };
      <#else>
      return {
        t,
        registerTable,
        <#if table.popupType == POPUP_TYPE_MODAL>
        registerModal,
        <#elseif table.popupType == POPUP_TYPE_DRAWER>
        registerDrawer,
        </#if>
        <#if table.viewShow>
        handleView,
        </#if>
        <#if table.addShow>
        handleAdd,
        </#if>
        <#if table.copyShow>
        handleCopy,
        </#if>
        <#if table.editShow>
        handleEdit,
        </#if>
        <#if table.deleteShow>
        handleDelete,
        handleBatchDelete,
        </#if>
        handleSuccess,
      };
      </#if>
    },
  });
</script>
