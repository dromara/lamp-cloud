<template>
  <div class="bg-white m-4 mr-2 overflow-hidden">
    <div class="m-4">
      <#if table.addShow>
      <a-button
        @click="handleAdd()"
        preIcon="ant-design:plus-outlined"
        class="mr-2"
        <#if table.addAuth?? && table.addAuth != ''>
        v-hasAnyPermission="['${table.addAuth}']"
        </#if>
      >
        {{ t('common.title.addRoot') }}
      </a-button>
      </#if>
      <#if table.deleteShow>
      <a-button
        @click="handleBatchDelete()"
        preIcon="ant-design:delete-outlined"
        class="mr-2"
        <#if table.deleteAuth?? && table.deleteAuth != ''>
        v-hasAnyPermission="['${table.deleteAuth}']"
        </#if>
      >
        {{ t('common.title.delete') }}
      </a-button>
      </#if>
    </div>
    <BasicTree
      :title="t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.table.title')"
      toolbar
      checkable
      search
      checkStrictly
      :actionList="actionList"
      :beforeRightClick="getRightMenuList"
      :clickRowToExpand="false"
      :treeData="treeData"
      @select="handleSelect"
      ref="treeRef"
    />
  </div>
</template>
<script lang="ts">
  import { defineComponent, h, onMounted, ref, unref } from 'vue';
  import { useI18n } from '/@/hooks/web/useI18n';
  import { useMessage } from '/@/hooks/web/useMessage';
  import {
    BasicTree,
    TreeItem,
    ActionItem,
    TreeActionType,
    ContextMenuItem,
  } from '/@/components/Tree';
  import { findNodeByKey, eachTree } from '/@/utils/helper/treeHelper'
  import { tree, remove } from '/@/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';

  export default defineComponent({
    name: '${table.entityName}Tree',
    components: { BasicTree },

    emits: ['select', 'edit', 'add'],
    setup(_, {emit}) {
      const { t } = useI18n();
      const { createMessage, createConfirm } = useMessage();
      const treeRef = ref <Nullable<TreeActionType>> (null);
      const treeData = ref <TreeItem[]> ([]);

      function getTree() {
        const tree = unref(treeRef);
        if (!tree) {
          throw new Error('树结构加载失败,请刷新页面');
        }
        return tree;
      }

      onMounted(() => {
        fetch();
      });

      // 加载数据
      async function fetch() {
        treeData.value = ((await tree()) as unknown) as TreeItem[];
        eachTree(treeData.value, (item) => {
          item.key = item.id;
          item.title = item.${table.treeName};
          return item;
        });
        setTimeout(() => {
          getTree().filterByLevel(2);
        }, 0);
      }

      // 选择节点
      function handleSelect(keys: string[]) {
        if (keys[0]) {
          const node = findNodeByKey(keys[0], treeData.value);
          const parent = findNodeByKey(node?.parentId, treeData.value);
          emit('select', parent, node);
        }
      }

      // 悬停图标
      const actionList: ActionItem[] = [
        <#if table.addShow>
        {
          <#if table.addAuth?? && table.addAuth != ''>
          auth: '${table.addAuth}',
          </#if>
          render: (node) => {
            return h(
              'a',
              {
                class: 'ml-2',
                onClick: (e: Event) => {
                  e?.stopPropagation();
                  e?.preventDefault();
                  emit('add', findNodeByKey(node.id, treeData.value));
                },
              },
              t('common.title.add'),
            );
          },
        },
        </#if>
        <#if table.editShow>
        {
          <#if table.editAuth?? && table.editAuth != ''>
          auth: '${table.editAuth}',
          </#if>
          render: (node) => {
            return h(
              'a',
              {
                class: 'ml-2',
                onClick: (e: Event) => {
                  e?.stopPropagation();
                  e?.preventDefault();
                  const current = findNodeByKey(node?.id, treeData.value);
                  const parent = findNodeByKey(node?.parentId, treeData.value);
                  emit('edit', parent, current);
                },
              },
              t('common.title.edit'),
            );
          },
        },
        </#if>
        <#if table.deleteShow>
        {
          <#if table.deleteAuth?? && table.deleteAuth != ''>
          auth: '${table.deleteAuth}',
          </#if>
          render: (node) => {
            return h(
              'a',
              {
                class: 'ml-2',
                onClick: (e: Event) => {
                  e?.stopPropagation();
                  e?.preventDefault();
                  batchDelete([node.id]);
                },
              },
              t('common.title.delete'),
            );
          },
        },
        </#if>
      ];

      // 右键菜单
      function getRightMenuList(node: any): ContextMenuItem[] {
        return [
          <#if table.addShow>
          {
            label: t('common.title.addChildren'),
            <#if table.addAuth?? && table.addAuth != ''>
            auth: '${table.addAuth}',
            </#if>
            handler: () => {
              emit('add', findNodeByKey(unref(node).id, treeData.value));
            },
            icon: 'ant-design:plus-outlined',
          },
          </#if>
          <#if table.editShow>
          {
            label: t('common.title.edit'),
            <#if table.editAuth?? && table.editAuth != ''>
            auth: '${table.editAuth}',
            </#if>
            handler: () => {
              const current = findNodeByKey(unref(node).id, treeData.value);
              const parent = findNodeByKey(unref(node)?.parentId, treeData.value);
              emit('edit', parent, current);
            },
            icon: 'ant-design:edit-outlined',
          },
          </#if>
          <#if table.deleteShow>
          {
            <#if table.deleteAuth?? && table.deleteAuth != ''>
            auth: '${table.deleteAuth}',
            </#if>
            label: t('common.title.delete'),
            handler: () => {
              batchDelete([unref(node).id]);
            },
            icon: 'ant-design:delete-outlined',
          },
          </#if>
        ];
      }

      // 执行批量删除
      async function batchDelete(ids: string[]) {
        createConfirm({
          iconType: 'warning',
          content: '选中节点及其子结点将被永久删除, 是否确定删除？',
          onOk: async () => {
            try{
              await remove(ids);
              createMessage.success(t('common.tips.deleteSuccess'));
              fetch();
            } catch (e) {}
          },
        });
      }

      // 点击树外面的 新增
      function handleAdd() {
        emit('add', findNodeByKey('0', treeData.value));
      }

      // 点击树外面的 批量删除
      function handleBatchDelete() {
        const { checked } = getTree().getCheckedKeys() as {
          checked: string[];
          halfChecked: string[];
        };
        if (!checked || checked.length <= 0) {
          createMessage.warning(t('common.tips.pleaseSelectTheData'));
          return;
        }
        batchDelete(checked);
      }

      return {
        t,
        treeRef,
        treeData,
        fetch,
        handleAdd,
        handleBatchDelete,
        getRightMenuList,
        actionList,
        handleSelect,
      };
    },
  });
</script>
