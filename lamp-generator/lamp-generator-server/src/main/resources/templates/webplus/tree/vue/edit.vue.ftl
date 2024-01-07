<template>
  <div class="bg-white m-4 ml-2 overflow-hidden">
    <a-card :title="t(`common.title.${r'${'}type}`)" :bordered="false">
      <BasicForm @register="register" />
      <div class="flex justify-center">
        <a-button @click="resetFields">{{ t('common.resetText') }}</a-button>
        <a-button class="!ml-4" type="primary" @click="handleSubmit" :loading="confirmLoading">
          {{ t('common.okText') }}
        </a-button>
      </div>
    </a-card>
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref, unref } from 'vue';
  import { Card } from 'ant-design-vue';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { BasicForm, useForm } from '/@/components/Form';
  import { ActionEnum, VALIDATE_API } from '/@/enums/commonEnum';
  import { getValidateRules } from '/@/api/${projectPrefix}/common/formValidateService';
  import { Api, save, update } from '/@/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';
  import { customFormSchemaRules, editFormSchema } from './${table.entityName?uncap_first}.data';

  export default defineComponent({
    name: '${table.entityName}Edit',
    components: { BasicForm, [Card.name]: Card },
    emits: ['success'],
    setup(_, { emit }) {
      const { t } = useI18n();
      const { createMessage } = useMessage();
      const type = ref(ActionEnum.ADD);
      const confirmLoading = ref(false);

      const [register, { setFieldsValue, resetFields, updateSchema, validate }] = useForm({
        labelWidth: 100,
        showActionButtonGroup: false,
        schemas: editFormSchema(type),
        baseColProps: { span: 24 },
      });

      // 提交
      async function handleSubmit() {
        try {
          const params = await validate();
          confirmLoading.value = true;
          if (unref(type) === ActionEnum.EDIT) {
            await update(params);
          } else {
            params.id = undefined;
            await save(params);
          }
          createMessage.success(t(`common.tips.${r'${'}type.value}Success`));

          type.value = ActionEnum.ADD;
          await resetFields();
          emit('success');
        } finally {
          confirmLoading.value = false;
        }
      }

      // 设置回显数据
      async function setData(data: Recordable) {
        await resetFields();
        type.value = data?.type;

        const {record = {}, parent = {}} = data;
        record['parentName'] = parent?.${table.treeName};
        record['parentId'] = parent?.id;

        await setFieldsValue({...record});
        if (unref(type) !== ActionEnum.VIEW) {
          let validateApi = Api[VALIDATE_API[unref(type)]];
          getValidateRules(validateApi, customFormSchemaRules(type)).then(async (rules) => {
            rules && rules.length > 0 && (await updateSchema(rules));
          });
        }
      }

      return { register, resetFields, handleSubmit, setData, t, type, confirmLoading };
    },
  });
</script>
