package ${package.ServiceImpl};

<#list serviceImplImport as pkg>
import ${pkg};
</#list>
import ${servicePackage};
<#if superServiceImplClass??>
import ${superServiceImplClassPackage};
import ${managerPackage};
import ${entityPackage};
import ${saveVoPackage};
import ${updateVoPackage};
import ${resultVoPackage};
import ${pageQueryPackage};
</#if>

/**
 * <p>
 * 业务实现类
 * ${table.comment!?replace("\n","\n * ")}
 * </p>
 *
 * @author ${author}
 * @date ${datetime}
 * @create [${datetime}] [${author}] [代码生成器生成]
 */
<#if table.isDs>

</#if>
<#if table.isLombok>
@Slf4j
@RequiredArgsConstructor
</#if>
@Service
@Transactional(readOnly = true)
<#if superServiceImplClass??>
public class ${serviceImplName} extends ${superServiceImplClass}<${managerName}, ${pkField.javaType}, ${table.entityName}> implements ${serviceName} {
<#else>
public class ${serviceImplName} implements ${serviceName} {
</#if>
<#if table.tplType == TPL_MAIN_SUB>
    private final ${sub.managerName} subManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIds(Collection<${pkField.javaType}> ids) {
        if (CollUtil.isEmpty(ids)) {
            return false;
        }

        // 删除 从表
        subManager.remove(Wraps.<${sub.table.entityName}>lbQ().in(${sub.table.entityName}::get${table.subJavaFieldName?cap_first}, ids));
        return super.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.entityName} save(${saveVoName} saveVO) {
        ${table.entityName} entity = BeanPlusUtil.toBean(saveVO,  ${table.entityName}.class);
        superManager.save(entity);

        saveItem(saveVO.getInsertList(), entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.entityName} updateById(${updateVoName} updateVO) {
        ${table.entityName} old = getById(updateVO.getId());

        ${table.entityName} entity = BeanPlusUtil.toBean(updateVO, ${table.entityName}.class);
        superManager.updateById(entity);

        saveItem(updateVO.getInsertList(), entity);
        updateItem(updateVO.getUpdateList(), entity, old);
        removeSubByIds(updateVO.getDeleteList());

        return entity;
    }

    private void saveItem(List<${sub.saveVoName}> insertList, ${table.entityName} entity) {
        if (CollUtil.isEmpty(insertList)) {
            return;
        }

        List<${sub.table.entityName}> itemList = new ArrayList<>();
        insertList.forEach(insert -> {
            ${sub.table.entityName} item = new ${sub.table.entityName}();
            BeanPlusUtil.copyProperties(insert, item);
            item.set${table.subJavaFieldName?cap_first}(entity.getId());
            itemList.add(item);
        });
        subManager.saveBatch(itemList);
    }

    private void updateItem(List<${sub.updateVoName}> updateInsert, ${table.entityName} entity, ${table.entityName} old) {
        if (CollUtil.isEmpty(updateInsert)) {
            return;
        }
        List<${sub.table.entityName}> itemList = new ArrayList<>();
        updateInsert.forEach(insert -> {
            ${sub.table.entityName} item = new ${sub.table.entityName}();
            BeanPlusUtil.copyProperties(insert, item);
            item.set${table.subJavaFieldName?cap_first}(entity.getId());
            itemList.add(item);
        });
        subManager.updateBatchById(itemList);
    }

    private boolean removeSubByIds(Collection<${pkField.javaType}> idList) {
        if (CollUtil.isEmpty(idList)) {
            return false;
        }
        return subManager.removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.entityName} copy(${pkField.javaType} id) {
        ${table.entityName} old = getById(id);
        ArgumentAssert.notNull(old, "数据不存在或已被删除，请刷新重试");

        ${table.entityName} entity = BeanPlusUtil.toBean(old, ${table.entityName}.class);
        entity.setId(null);
        superManager.save(entity);

        LbQueryWrap<${sub.table.entityName}> wrap = Wraps.<${sub.table.entityName}>lbQ().eq(${sub.table.entityName}::get${table.subJavaFieldName?cap_first}, old.getId());
        List<${sub.table.entityName}> itemList = subManager.list(wrap);
        itemList.forEach(item -> {
            item.setId(null);
            item.set${table.subJavaFieldName?cap_first}(entity.getId());
        });
        subManager.saveBatch(itemList);
        return entity;
    }
</#if>

<#if isTreeEntity>
    @Override
    public List<${table.entityName}> findTree(${pageQueryName} query) {
    List<${table.entityName}> list = superManager.list(Wraps.<${table.entityName}>lbQ().orderByAsc(${table.entityName}::getSortValue));
        return TreeUtil.buildTree(list);
    }
</#if>

}


