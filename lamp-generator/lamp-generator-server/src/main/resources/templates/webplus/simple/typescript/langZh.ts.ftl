export default {
  table: { title: '${table.swaggerComment!}列表' },
<#list allFields as field>
  ${field.javaField}: '${field.swaggerComment!}',
</#list>
};
