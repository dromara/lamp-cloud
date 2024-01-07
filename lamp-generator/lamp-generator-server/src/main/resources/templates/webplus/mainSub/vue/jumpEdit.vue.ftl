<template>
  <PageWrapper class="high-form" title="编辑${table.menuName}" contentBackground contentClass="p-4">
    <template #extra>
      <a-button
        :loading="loading"
        v-if="type !== ActionEnum.VIEW"
        type="primary" @click="handleSubmit" class="ml-4">
        保存
      </a-button>
    </template>
    <BasicForm @register="registerForm" />
    <h4 class="form-header">${sub.table.swaggerComment}</h4>
    <${sub.table.entityName}Modal ref="${sub.table.entityName?uncap_first}Ref" />
  </PageWrapper>
</template>
<script lang="ts">
  import { defineComponent, ref, unref, onMounted } from 'vue';
  import { BasicForm, useForm } from '/@/components/Form/index';
  import { PageWrapper } from '/@/components/Page';
  import { useRouter } from 'vue-router';
  import { useTabs } from '/@/hooks/web/useTabs';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { ActionEnum, VALIDATE_API } from '/@/enums/commonEnum';
  import { Api, save, update, detail } from '/@/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';
  import { getValidateRules } from '/@/api/${projectPrefix}/common/formValidateService';
  import { customFormSchemaRules, editFormSchema } from './${table.entityName?uncap_first}.data';
  import ${sub.table.entityName}Modal from './${sub.table.entityName?uncap_first}/index';

  export default defineComponent({
    name: '编辑${table.menuName}',
    components: { BasicForm, PageWrapper, ${sub.table.entityName}Modal },
    setup(_) {
      const { t } = useI18n();
      const type = ref<ActionEnum>(ActionEnum.ADD);
      const loading = ref<boolean>(false);
      const { createMessage, notification } = useMessage();
      const { replace, currentRoute } = useRouter();
      const { closeCurrent } = useTabs();
      const ${sub.table.entityName?uncap_first}Ref = ref<any>(null);

      const [registerForm, { setFieldsValue, resetFields, updateSchema, validate }] =
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

      onMounted(async () => {
        const { params, query } = currentRoute.value;
        const id = params.id;
        await load({ type: query?.type, id });
      });

      const load = async (data: Recordable) => {
        await resetFields();
        await get${sub.table.entityName}Ref().reset();

        type.value = data?.type || ActionEnum.ADD;

        if (![ActionEnum.ADD].includes(unref(type))) {
          const record = await detail(data?.id);
          await setFieldsValue({ ...record });
          get${sub.table.entityName}Ref().load(data?.type, record.id);
        } else {
          get${sub.table.entityName}Ref().load(data?.type);
        }

        if (unref(type) !== ActionEnum.VIEW) {
          let validateApi = Api[VALIDATE_API[unref(type)]];
          const rules = await getValidateRules(validateApi, customFormSchemaRules(type));
          rules && rules.length > 0 && (await updateSchema(rules));
        }
      };

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

          loading.value = true;
          if (unref(type) !== ActionEnum.VIEW) {
            if (unref(type) === ActionEnum.EDIT) {
              await update(params);
            } else {
              params.id = null;
              await save(params);
            }
            createMessage.success(t(`common.tips.${r'${'}type.value${r'}'}Success`));
          }

          await closeCurrent();
          replace({ name: '${table.menuName}' });
        } finally {
          loading.value = false;
        }
      }

      return { ${sub.table.entityName?uncap_first}Ref, type, t, loading, registerForm, handleSubmit, ActionEnum };
    },
  });
</script>
