export interface ${pageQueryName} {
<#list fields as field>
  ${field.javaField}?: ${field.tsType}; // ${field.swaggerComment!}
</#list>
<#if isTreeEntity>
  parentId?: ${pkField.tsType};
  sortValue?: number;
</#if>
}

export interface ${saveVoName} {
<#list fields as field>
  ${field.javaField}?: ${field.tsType}; // ${field.swaggerComment!}
</#list>
<#if isTreeEntity>
  parentId?: ${pkField.tsType};
  sortValue?: number;
</#if>
}

export interface ${updateVoName} {
  id: ${pkField.tsType};
<#list fields as field>
  ${field.javaField}?: ${field.tsType}; // ${field.swaggerComment!}
</#list>
<#if isTreeEntity>
  parentId?: ${pkField.tsType};
  sortValue?: number;
</#if>
}

export interface ${resultVoName} {
  echoMap?: any;
<#list commonFields as field>
  ${field.javaField}?: ${field.tsType}; // ${field.swaggerComment!}
</#list>
<#list fields as field>
  ${field.javaField}?: ${field.tsType}; // ${field.swaggerComment!}
</#list>
}
