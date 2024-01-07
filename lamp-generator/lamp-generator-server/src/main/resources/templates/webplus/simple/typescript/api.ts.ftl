import { ${saveVoName}, ${updateVoName}, ${resultVoName}, ${pageQueryName} } from './model/${table.entityName?uncap_first}Model';
import { PageParams, PageResult } from '/@/api/model/baseModel';
import { defHttp } from '/@/utils/http/axios';
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
import { UploadFileParams } from '/#/axios';
</#if>
import { RequestEnum } from '/@/enums/httpEnum';
import { ServicePrefixEnum } from '/@/enums/commonEnum';
import type { AxiosRequestConfig } from 'axios';

const MODULAR = '${table.entityName?uncap_first}';
// tips: 建议在ServicePrefixEnum中新增：${table.serviceName?upper_case} = '/${table.serviceName}'，并将下方代码改为： const ServicePrefix = ServicePrefixEnum.${table.serviceName?upper_case};
// tips: /${table.serviceName} 需要与 ${projectPrefix}-gateway-server.yml中配置的Path一致，否则无法正常调用接口！！！
// const ServicePrefix = ServicePrefixEnum.${table.serviceName?upper_case};
const ServicePrefix = '/${table.serviceName}';

export const Api = {
  Page: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/page`,
    method: RequestEnum.POST,
  } as AxiosRequestConfig,
  Detail: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/detail`,
    method: RequestEnum.GET,
  } as AxiosRequestConfig,
  Copy: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/copy`,
    method: RequestEnum.POST,
  } as AxiosRequestConfig,
<#if table.tplType == TPL_TREE>
  Tree: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/tree`,
    method: RequestEnum.POST,
  } as AxiosRequestConfig,
</#if>
<#if table.addShow || table.copyShow>
  Save: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}`,
    method: RequestEnum.POST,
  } as AxiosRequestConfig,
</#if>
<#if table.editShow>
  Update: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}`,
    method: RequestEnum.PUT,
  },
</#if>
<#if table.deleteShow>
  Delete: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}`,
    method: RequestEnum.DELETE,
  } as AxiosRequestConfig,
</#if>
  Query: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/query`,
    method: RequestEnum.POST,
  } as AxiosRequestConfig,
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>
  Preview: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/preview`,
    method: RequestEnum.POST,
  } as AxiosRequestConfig,
  Export: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/export`,
    method: RequestEnum.POST,
    responseType: 'blob',
  } as AxiosRequestConfig,
  Import: {
    url: `${r"${"}ServicePrefix}/${r"${MODULAR}"}/import`,
    method: RequestEnum.POST,
  } as AxiosRequestConfig,
</#if>
};

<#if table.tplType == TPL_TREE>
export const tree = (params?: ${pageQueryName}) => defHttp.request<${resultVoName}>({ ...Api.Tree, params });

</#if>
export const copy = (id: string) => defHttp.request<${resultVoName}>({ ...Api.Copy, params: { id } });

export const page = (params: PageParams<${pageQueryName}>) =>
  defHttp.request<PageResult<${resultVoName}>>({ ...Api.Page, params });

export const detail = (id: string) =>
  defHttp.request<${resultVoName}>({ ...Api.Detail, params: { id } });

export const query = (params: ${pageQueryName}) => defHttp.request<${resultVoName}[]>({ ...Api.Query, params });

<#if table.addShow || table.copyShow>
export const save = (params: ${saveVoName}) => defHttp.request<${resultVoName}>({ ...Api.Save, params });

</#if>
<#if table.editShow>
export const update = (params: ${updateVoName}) =>
  defHttp.request<${resultVoName}>({ ...Api.Update, params });

</#if>
<#if table.deleteShow>
export const remove = (params: string[]) => defHttp.request<boolean>({ ...Api.Delete, params });

</#if>
<#if table.superClass == SUPER_CLASS_SUPER_POI_CLASS>

export const exportPreview = (params: PageParams<${pageQueryName}>) =>
  defHttp.request<string>({ ...Api.Preview, params });

export const exportFile = (params: PageParams<${pageQueryName}>) =>
  defHttp.request<any>({ ...Api.Export, params }, { isReturnNativeResponse: true });

export const importFile = (params: UploadFileParams) =>
  defHttp.uploadFile<boolean>({ ...Api.Import }, params);
</#if>
