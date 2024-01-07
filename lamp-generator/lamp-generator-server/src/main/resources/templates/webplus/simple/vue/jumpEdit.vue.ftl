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

  export default defineComponent({
    name: '编辑${table.menuName}',
    components: { BasicForm, PageWrapper },
    setup(_) {
      const { t } = useI18n();
      const type = ref<ActionEnum>(ActionEnum.ADD);
      const loading = ref<boolean>(false);
      const { createMessage } = useMessage();
      const { replace, currentRoute } = useRouter();
      const { closeCurrent } = useTabs();

      const [registerForm, { setFieldsValue, resetFields, updateSchema, validate }] =
        useForm({
          name: '${table.entityName}Edit',
          labelWidth: 100,
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

      onMounted(async () => {
        const { params, query } = currentRoute.value;
        const id = params.id;
        await load({ type: query?.type, id });
      });

      const load = async (data: Recordable) => {
        await resetFields();
        type.value = data?.type || ActionEnum.ADD;

        if (![ActionEnum.ADD].includes(unref(type))) {
          const record = await detail(data?.id);
          await setFieldsValue({ ...record });
        }

        if ([ActionEnum.ADD, ActionEnum.EDIT, ActionEnum.COPY].includes(unref(type))) {
          let validateApi = Api[VALIDATE_API[unref(type)]];
          const rules = await getValidateRules(validateApi, customFormSchemaRules(type));
          rules && rules.length > 0 && (await updateSchema(rules));
        }
      };

      async function handleSubmit() {
        try {
          const params = await validate();
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

      return { type, t, loading, registerForm, handleSubmit, ActionEnum };
    },
  });
</script>
