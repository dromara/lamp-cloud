<template>
<#if table.popupType == POPUP_TYPE_MODAL>
  <BasicModal
    v-bind="$attrs"
    @register="registerModel"
    :title="t(`common.title.${r'${type}'}`)"
    :maskClosable="false"
    width="70%"
    @ok="handleSubmit"
    :keyboard="true"
  >
  <#elseif table.popupType == POPUP_TYPE_DRAWER>
  <BasicDrawer
    v-bind="$attrs"
    @register="registerModel"
    showFooter
    width="70%"
    :maskClosable="false"
    :title="t(`common.title.${r'${type}'}`)"
    @ok="handleSubmit"
  >
</#if>
    <BasicForm @register="registerForm" />
    <h4 class="form-header">${sub.table.swaggerComment}</h4>
    <${sub.table.entityName}Modal ref="${sub.table.entityName?uncap_first}Ref" />
<#if table.popupType == POPUP_TYPE_MODAL>
  </BasicModal>
<#elseif table.popupType == POPUP_TYPE_DRAWER>
  </BasicDrawer>
</#if>
</template>
<script lang="ts">
  import { defineComponent, ref, unref, h } from 'vue';
  <#if table.popupType == POPUP_TYPE_MODAL>
  import { BasicModal, useModalInner } from '/@/components/Modal';
  <#elseif table.popupType == POPUP_TYPE_DRAWER>
  import { BasicDrawer, useDrawerInner } from '/@/components/Drawer';
  </#if>
  import { BasicForm, useForm } from '/@/components/Form/index';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { ActionEnum, VALIDATE_API } from '/@/enums/commonEnum';
  import { Api, save, update } from '/@/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';
  import { getValidateRules } from '/@/api/${projectPrefix}/common/formValidateService';
  import { customFormSchemaRules, editFormSchema } from './${table.entityName?uncap_first}.data';
  import ${sub.table.entityName}Modal from './${sub.table.entityName?uncap_first}/index.vue';

  export default defineComponent({
    name: '编辑${table.menuName}',
    components: { ${sub.table.entityName}Modal, <#if table.popupType == POPUP_TYPE_MODAL>BasicModal<#elseif table.popupType == POPUP_TYPE_DRAWER>BasicDrawer<#else></#if>, BasicForm },
    emits: ['success', 'register'],
    setup(_, { emit }) {
      const { t } = useI18n();
      const type = ref<ActionEnum>(ActionEnum.ADD);
      const { createMessage, notification } = useMessage();
      const ${sub.table.entityName?uncap_first}Ref = ref<any>(null);

      const [registerForm, { setFieldsValue, resetFields, updateSchema, validate, resetSchema }] =
        useForm({
          name: '${table.entityName}Edit',
          labelWidth: 120,
          schemas: editFormSchema(type),
          showActionButtonGroup: false,
          disabled: (_) => {
            return unref(type) === ActionEnum.VIEW;
          },
          baseColProps: { span: 24 },
          actionColOptions: {
            span: 23,
          },
        });

      function get${sub.table.entityName}Ref() {
        return unref(${sub.table.entityName?uncap_first}Ref);
      }

      <#if table.popupType == POPUP_TYPE_MODAL>
      const [registerModel, { setModalProps: setProps, closeModal: close }] = useModalInner(async (data) => {
      <#elseif table.popupType == POPUP_TYPE_DRAWER>
      const [registerModel, { setDrawerProps: setProps, closeDrawer: close }] = useDrawerInner(async (data) => {
      <#else></#if>
        setProps({ confirmLoading: false });
        await resetSchema(editFormSchema(type));
        await resetFields();
        await get${sub.table.entityName}Ref().reset();
        type.value = data?.type || ActionEnum.ADD;

        if (unref(type) !== ActionEnum.ADD) {
          // 赋值
          const record = { ...data?.record };
          await setFieldsValue(record);

          get${sub.table.entityName}Ref().load(data?.type, record.id);
        } else {
          get${sub.table.entityName}Ref().load(data?.type);
        }

        if (unref(type) !== ActionEnum.VIEW) {
          let validateApi = Api[VALIDATE_API[unref(type)]];
          await getValidateRules(validateApi, customFormSchemaRules(type)).then(async (rules) => {
            rules && rules.length > 0 && (await updateSchema(rules));
          });
        }
      });

      async function handleSubmit() {
        try {
          const params = await validate();
          const errMap = await get${sub.table.entityName}Ref().fullValidate();
          if (errMap) {
            let msgStr = '';
            Object.values(errMap).forEach((errList: any) => {
              errList.forEach((params: any) => {
                const { rowIndex, column, rules } = params;
                rules.forEach((rule: any) => {
                  msgStr += `${r'第 ${rowIndex + 1} 行 ${column.title} 校验错误：${rule.message} <br/>'}`;
                });
              });
            });
            notification.warn({
              message: '校验失败',
              description: () => {
                return h('div', { innerHTML: msgStr }, []);
              },
              duration: 5,
            });
            return;
          }

          const { insertRecords, removeRecords, updateRecords } = get${sub.table.entityName}Ref().getRecordset();
          params.insertList = insertRecords;
          params.updateList = updateRecords;
          params.deleteList = removeRecords.map((item) => item.id);

          setProps({ confirmLoading: true });
          if (unref(type) !== ActionEnum.VIEW) {
            if (unref(type) === ActionEnum.EDIT) {
              await update(params);
            } else {
              params.id = null;
              await save(params);
            }
            createMessage.success(t(`common.tips.${r'${'}type.value${r'}'}Success`));
          }
          close();
          emit('success');
        } finally {
          setProps({ confirmLoading: false });
        }
      }

      return { ${sub.table.entityName?uncap_first}Ref, type, t, registerModel, registerForm, handleSubmit };
    },
  });
</script>
