<script setup lang="ts">
  import type { Key } from 'ant-design-vue/es/vc-tree/interface';

import type { Nullable } from '@vben/types';

import type {
  ContextMenuItem,
  TreeActionItem,
  TreeActionType,
  TreeItem,
} from '#/components/tree';

import { h, onMounted, ref, unref } from 'vue';

import { PermModeEnum } from '@vben/access';

import {
  DeleteOutlined,
  EditOutlined,
  PlusSquareOutlined,
} from '@ant-design/icons-vue';
import { Button } from 'ant-design-vue';

import { ${table.entityName}Api } from '#/api/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}';
import { BasicTree } from '#/components/tree';
import { useMessage } from '#/hooks/web/useMessage';
import { $t } from '#/locales';
import { findNodeByKey } from '#/utils/helper/treeHelper';

defineOptions({
  name: '${table.entityName}树结构',
  inheritAttrs: false
});

interface Emits {
  (e: 'select', parent: TreeItem, current: TreeItem): void;
  (e: 'add', parent: TreeItem): void;
  (e: 'edit', parent: TreeItem, current: TreeItem): void;
}

const emit = defineEmits<Emits>();

const { createMessage, createConfirm } = useMessage();
const treeRef = ref<Nullable<TreeActionType>>(null);
const treeData = ref<TreeItem[]>([]);
  const treeLoading = ref<boolean>(false);

function getTree() {
  const treeR = unref(treeRef);
  if (!treeR) {
    throw new Error('树结构加载失败,请刷新页面');
  }
  return treeR;
}

onMounted(() => {
  fetch();
});

// 加载数据
async function fetch() {
  try {
    treeLoading.value = true;
    treeData.value = (await ${table.entityName}Api.tree({})) as unknown as TreeItem[];
    setTimeout(() => {
      getTree().filterByLevel(2);
    }, 0);
  } finally {
    treeLoading.value = false;
  }
}

// 选择节点
function handleSelect(keys: Key[]) {
  if (keys[0]) {
    const node = findNodeByKey(keys[0], treeData.value);
    const parent = findNodeByKey(node?.parentId, treeData.value);

    emit('select', parent, node);
  }
}

// 悬停图标
const actionList: TreeActionItem[] = [
  <#if table.addShow>
  {
    <#if table.addAuth?? && table.addAuth != ''>
    auth: ['${table.addAuth}'],
    authMode: PermModeEnum.HasAny,
    </#if>
    render: (node) => {
      return h(PlusSquareOutlined, {
        class: 'ml-2',
        title: $t('common.title.add'),
        onClick: (e: Event) => {
          e?.stopPropagation();
          e?.preventDefault();
          emit('add', findNodeByKey(node.id as Key, treeData.value));
        },
      });
    },
  },
  </#if>
  <#if table.editShow>
  {
    <#if table.editAuth?? && table.editAuth != ''>
    auth: ['${table.editAuth}'],
    authMode: PermModeEnum.HasAny,
    </#if>
    render: (node) => {
      return h(EditOutlined, {
        class: 'ml-2',
        title: $t('common.title.edit'),
        onClick: (e: Event) => {
          e?.stopPropagation();
          e?.preventDefault();
          const current = findNodeByKey(node?.id as Key, treeData.value);
          const parent = findNodeByKey(node?.parentId as Key, treeData.value);
          emit('edit', parent, current);
        },
      });
    },
  },
  </#if>
  <#if table.deleteShow>
  {
    <#if table.deleteAuth?? && table.deleteAuth != ''>
    auth: ['${table.deleteAuth}'],
    authMode: PermModeEnum.HasAny,
    </#if>
    render: (node) => {
      return h(DeleteOutlined, {
        class: 'ml-2',
        title: $t('common.title.delete'),
        style: { color: '#ED6F6F' },
        onClick: (e: Event) => {
          e?.stopPropagation();
          e?.preventDefault();
          batchDelete([node.id as string]);
        },
      });
    },
  }
  </#if>
];

// 右键菜单
const getRightMenuList = (node: any): ContextMenuItem[] => {
  return [
    <#if table.addShow>
    {
      label: $t('common.title.addChildren'),
      <#if table.addAuth?? && table.addAuth != ''>
      auth: ['${table.addAuth}'],
      authMode: PermModeEnum.HasAny,
      </#if>
      handler: () => {
        emit('add', findNodeByKey(node.id as Key, treeData.value));
      },
      icon: 'ant-design:plus-outlined',
    },
    </#if>
    <#if table.editShow>
    {
      label: $t('common.title.edit'),
      <#if table.editAuth?? && table.editAuth != ''>
      auth: ['${table.editAuth}'],
      authMode: PermModeEnum.HasAny,
      </#if>
      handler: () => {
        const current = findNodeByKey(node?.id as Key, treeData.value);
        const parent = findNodeByKey(node?.parentId as Key, treeData.value);
        emit('edit', parent, current);
      },
      icon: 'ant-design:edit-outlined',
    },
    </#if>
    <#if table.deleteShow>
    {
      label: $t('common.title.delete'),
      <#if table.deleteAuth?? && table.deleteAuth != ''>
      auth: ['${table.deleteAuth}'],
      authMode: PermModeEnum.HasAny,
      </#if>
      handler: () => {
        batchDelete([node?.id as string]);
      },
      icon: 'ant-design:delete-outlined',
    }
    </#if>
  ];
};

<#if table.addShow || table.copyShow>
// 点击树外面的 新增
function handleAdd() {
  emit('add', findNodeByKey('0', treeData.value));
}
</#if>

<#if table.deleteShow>
// 执行批量删除
async function batchDelete(ids: string[]) {
  createConfirm({
    iconType: 'warning',
    content: '选中节点及其子结点将被永久删除, 是否确定删除？',
    onOk: async () => {
      try {
        await ${table.entityName}Api.remove(ids);
        await fetch();
        createMessage.success($t('common.tips.deleteSuccess'));
      } catch {}
    },
  });
}

// 点击树外面的 批量删除
function handleBatchDelete() {
  const checkedKeys = getTree().getCheckedKeys();
  const checked = getTree().getCheckStrictly()
          ? (checkedKeys as any).checked
          : checkedKeys;

  if (!checked || checked.length <= 0) {
    createMessage.warn($t('common.tips.pleaseSelectTheData'));
    return;
  }
  batchDelete(checked as string[]);
}
</#if>

defineExpose({ fetch });
</script>

<template>
  <div class="overflow-hidden">
    <div class="mb-2 mt-2">
      <#if table.addShow || table.copyShow>
      <Button
        <#if table.addAuth?? && table.addAuth != ''>
        v-hasAnyPermission="['${table.addAuth}']"
        </#if>
        class="mr-2"
        @click="handleAdd()"
      >
        {{ $t('common.title.addRoot') }}
      </Button>
      </#if>
      <#if table.deleteShow>
      <Button
        <#if table.deleteAuth?? && table.deleteAuth != ''>
        v-hasAnyPermission="['${table.deleteAuth}']"
        </#if>
        class="mr-2"
        @click="handleBatchDelete()"
      >
        {{ $t('common.title.delete') }}
      </Button>
      </#if>
      <Button class="mr-2" @click="fetch()">
        {{ $t('common.redo') }}
      </Button>
    </div>
    <BasicTree
      ref="treeRef"
      :field-names="{ key: 'id'<#if table.treeName?? && table.treeName != ''>, title: '${table.treeName}'</#if> }"
      :action-list="actionList"
      :before-right-click="getRightMenuList"
      :click-row-to-expand="false"
      :loading="treeLoading"
      checkable
      :title="$t('${table.plusApplicationName}.${table.plusModuleName}.${table.entityName?uncap_first}.table.title')"
      :tree-data="treeData"
      check-strictly
      highlight
      search
      toolbar
      @select="handleSelect"
    />
  </div>
</template>
<style lang="scss" scoped></style>
