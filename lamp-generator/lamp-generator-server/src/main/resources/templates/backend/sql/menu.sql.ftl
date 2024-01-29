-- SQL脚本仅需执行一次，重复执行会生成多条数据，请谨慎手动执行！
<#setting number_format="#">
<#assign menuCode = "${table.plusApplicationName}:${table.plusModuleName}:${table.entityName?uncap_first}"/>
-- 创建菜单
INSERT INTO def_resource(id, application_id, code, name, resource_type, parent_id, open_with, describe_, path, component, redirect, icon, is_hidden, is_general, state, sort_value, sub_group, field_is_secret, field_is_edit, data_scope, custom_class, is_def, tree_path, tree_grade, meta_json, created_by, created_time, updated_by, updated_time)
VALUES (${menuId}, ${table.menuApplicationId}, '${table.plusApplicationName}:${table.plusModuleName}:${table.entityName?uncap_first}', '${table.menuName}', '20', <#if parentMenuId??>${parentMenuId}<#else>0</#if>, '01', '', '/${table.plusModuleName}/${table.entityName?uncap_first}', '/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}/index', '', '${table.menuIcon!''}', 0, 0, 1, 10, '', 0, 1, NULL, NULL, NULL, '<#if parentMenuId??>/${parentMenuId}</#if>/', <#if parentMenuId??>1<#else>0</#if>, '{}', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
<#if table.popupType == POPUP_TYPE_JUMP>
INSERT INTO def_resource(id, application_id, code, name, resource_type, parent_id, open_with, describe_, path, component, redirect, icon, is_hidden, is_general, state, sort_value, sub_group, field_is_secret, field_is_edit, data_scope, custom_class, is_def, tree_path, tree_grade, meta_json, created_by, created_time, updated_by, updated_time)
VALUES (${buttonEditId}, ${table.menuApplicationId}, '<#if table.editAuth?? && table.editAuth?trim != ''>${table.editAuth}<#else>${menuCode}:edit</#if>', '编辑${table.menuName}', '20', ${menuId}, '01', '', '/${table.plusModuleName}/${table.entityName?uncap_first}/edit/:id', '/${table.plusApplicationName}/${table.plusModuleName}/${table.entityName?uncap_first}/Edit', '', '${table.menuIcon!''}', 1, 0, 1, 20, '', 0, 1, NULL, NULL, NULL, '<#if parentMenuId??>/${parentMenuId}</#if>/${menuId}/', 1, '{"currentActiveMenu":"/${table.plusModuleName}/${table.entityName?uncap_first}"}', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>

-- 创建按钮
<#if table.addShow>
INSERT INTO def_resource(id, application_id, code, name, resource_type, parent_id, open_with, describe_, path, component, redirect, icon, is_general, state, sort_value, sub_group, field_is_secret, field_is_edit, data_scope, custom_class, is_def, tree_path, tree_grade, meta_json, created_by, created_time, updated_by, updated_time)
VALUES (${buttonAddId}, ${table.menuApplicationId}, '<#if table.addAuth?? && table.addAuth?trim != ''>${table.addAuth}<#else>${menuCode}:add</#if>', '新增', '40', ${menuId}, '01', '', '', '', '', '', 0, 1, 1, '', 0, 1, NULL, NULL, NULL, '<#if parentMenuId??>/${parentMenuId}</#if>/${menuId}/', 2, '{}', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.editShow && table.popupType != POPUP_TYPE_JUMP>
INSERT INTO def_resource(id, application_id, code, name, resource_type, parent_id, open_with, describe_, path, component, redirect, icon, is_general, state, sort_value, sub_group, field_is_secret, field_is_edit, data_scope, custom_class, is_def, tree_path, tree_grade, meta_json, created_by, created_time, updated_by, updated_time)
VALUES (${buttonEditId}, ${table.menuApplicationId}, '<#if table.editAuth?? && table.editAuth?trim != ''>${table.editAuth}<#else>${menuCode}:edit</#if>', '编辑', '40', ${menuId}, '01', '', '', '', '', '', 0, 1, 2, '', 0, 1, NULL, NULL, NULL, '<#if parentMenuId??>/${parentMenuId}</#if>/${menuId}/', 2, '{}', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.copyShow>
INSERT INTO def_resource(id, application_id, code, name, resource_type, parent_id, open_with, describe_, path, component, redirect, icon, is_general, state, sort_value, sub_group, field_is_secret, field_is_edit, data_scope, custom_class, is_def, tree_path, tree_grade, meta_json, created_by, created_time, updated_by, updated_time)
VALUES (${buttonCopyId}, ${table.menuApplicationId}, '<#if table.copyAuth?? && table.copyAuth?trim != ''>${table.copyAuth}<#else>${menuCode}:copy</#if>', '复制', '40', ${menuId}, '01', '', '', '', '', '', 0, 1, 2, '', 0, 1, NULL, NULL, NULL, '<#if parentMenuId??>/${parentMenuId}</#if>/${menuId}/', 2, '{}', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.deleteShow>
INSERT INTO def_resource(id, application_id, code, name, resource_type, parent_id, open_with, describe_, path, component, redirect, icon, is_general, state, sort_value, sub_group, field_is_secret, field_is_edit, data_scope, custom_class, is_def, tree_path, tree_grade, meta_json, created_by, created_time, updated_by, updated_time)
VALUES (${buttonDeleteId}, ${table.menuApplicationId}, '<#if table.deleteAuth?? && table.deleteAuth?trim != ''>${table.deleteAuth}<#else>${menuCode}:delete</#if>', '删除', '40', ${menuId}, '01', '', '', '', '', '', 0, 1, 3, '', 0, 1, NULL, NULL, NULL, '<#if parentMenuId??>/${parentMenuId}</#if>/${menuId}/', 2, '{}', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>
<#if table.viewShow>
INSERT INTO def_resource(id, application_id, code, name, resource_type, parent_id, open_with, describe_, path, component, redirect, icon, is_general, state, sort_value, sub_group, field_is_secret, field_is_edit, data_scope, custom_class, is_def, tree_path, tree_grade, meta_json, created_by, created_time, updated_by, updated_time)
VALUES (${buttonViewId}, ${table.menuApplicationId}, '<#if table.viewAuth?? && table.viewAuth?trim != ''>${table.viewAuth}<#else>${menuCode}:view</#if>', '查看', '40', ${menuId}, '01', '', '', '', '', '', 0, 1, 4, '', 0, 1, NULL, NULL, NULL, '<#if parentMenuId??>/${parentMenuId}</#if>/${menuId}/', 2, '{}', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#if>

-- 创建接口
INSERT INTO def_resource_api(id, resource_id, controller, spring_application_name, request_method, name, uri, is_input, created_by, created_time, updated_by, updated_time)
VALUES (${apiPageId}, ${menuId}, '${controllerName}', '${projectPrefix}-${table.serviceName}-server', 'POST', '${table.swaggerComment}-分页列表查询', '/${table.serviceName}/<#if controllerConfig.hyphenStyle>${mappingHyphen}<#else>${table.entityName?uncap_first}</#if>/page', 0, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
INSERT INTO def_resource_api(id, resource_id, controller, spring_application_name, request_method, name, uri, is_input, created_by, created_time, updated_by, updated_time)
VALUES (${apiDetailId}, ${menuId}, '${controllerName}', '${projectPrefix}-${table.serviceName}-server', 'GET', '${table.swaggerComment}-查询单体详情', '/${table.serviceName}/<#if controllerConfig.hyphenStyle>${mappingHyphen}<#else>${table.entityName?uncap_first}</#if>/detail', 0, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
INSERT INTO def_resource_api(id, resource_id, controller, spring_application_name, request_method, name, uri, is_input, created_by, created_time, updated_by, updated_time)
VALUES (${apiAddId}, ${buttonAddId}, '${controllerName}', '${projectPrefix}-${table.serviceName}-server', 'POST', '${table.swaggerComment}-新增', '/${table.serviceName}/<#if controllerConfig.hyphenStyle>${mappingHyphen}<#else>${table.entityName?uncap_first}</#if>', 0, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
INSERT INTO def_resource_api(id, resource_id, controller, spring_application_name, request_method, name, uri, is_input, created_by, created_time, updated_by, updated_time)
VALUES (${apiEditId}, ${buttonEditId}, '${controllerName}', '${projectPrefix}-${table.serviceName}-server', 'PUT', '${table.swaggerComment}-修改', '/${table.serviceName}/<#if controllerConfig.hyphenStyle>${mappingHyphen}<#else>${table.entityName?uncap_first}</#if>', 0, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
INSERT INTO def_resource_api(id, resource_id, controller, spring_application_name, request_method, name, uri, is_input, created_by, created_time, updated_by, updated_time)
VALUES (${apiDeleteId}, ${buttonDeleteId}, '${controllerName}', '${projectPrefix}-${table.serviceName}-server', 'DELETE', '${table.swaggerComment}-删除', '/${table.serviceName}/<#if controllerConfig.hyphenStyle>${mappingHyphen}<#else>${table.entityName?uncap_first}</#if>', 0, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
INSERT INTO def_resource_api(id, resource_id, controller, spring_application_name, request_method, name, uri, is_input, created_by, created_time, updated_by, updated_time)
VALUES (${apiCopyId}, ${buttonCopyId}, '${controllerName}', '${projectPrefix}-${table.serviceName}-server', 'POST', '${table.swaggerComment}-复制', '/${table.serviceName}/<#if controllerConfig.hyphenStyle>${mappingHyphen}<#else>${table.entityName?uncap_first}</#if>/copy', 0, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);

-- 字典
<#list dictList as dict>
<#assign dictId=uidGenerator.getUid()/>
INSERT INTO def_dict(id, parent_id, parent_key, classify, key_, name, state, remark, sort_value, icon, css_style, css_class, created_by, created_time, updated_by, updated_time)
VALUES (${dictId}, 0, '', '20', '${dict.key}', '${dict.value}', 1, '${dict.str!}', 1, '', '', '', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);

<#list dict.itemList as item>
INSERT INTO def_dict(id, parent_id, parent_key, classify, key_, name, state, remark, sort_value, icon, css_style, css_class, created_by, created_time, updated_by, updated_time)
VALUES (${uidGenerator.getUid()}, ${dictId}, '${dict.key}', '20', '${item.key}', '${item.value}', 1, '', 1, '', '', '', ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>, ${createdBy}, <#if dbType == oracle>to_date('${datetime}', 'yyyy-MM-dd HH24:mi:ss')<#else>'${datetime}'</#if>);
</#list>

-- 删除数据，用于测试
-- delete from def_dict where id in (${dictId}) or parent_id in (${dictId});
</#list>

-- 删除数据，用于测试
/*
delete from def_resource where id in (${menuId}, ${buttonAddId}, ${buttonEditId}, ${buttonCopyId}, ${buttonDeleteId}, ${buttonViewId});
delete from def_resource_api where id in (${apiPageId}, ${apiDetailId}, ${apiAddId}, ${apiEditId}, ${apiDeleteId}, ${apiCopyId});
*/
