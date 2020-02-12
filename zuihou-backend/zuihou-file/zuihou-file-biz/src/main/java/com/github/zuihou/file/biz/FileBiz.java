package com.github.zuihou.file.biz;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.github.zuihou.file.domain.FileDO;
import com.github.zuihou.file.enumeration.DataType;
import com.github.zuihou.file.properties.FileServerProperties;
import com.github.zuihou.file.utils.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件和附件的一些公共方法
 *
 * @author zuihou
 * @date 2019/05/06
 */
@Component
@Slf4j
public class FileBiz {

    @Autowired
    private FileServerProperties fileProperties;

    private static String buildNewFileName(String filename, Integer order) {
        return StrUtil.strBuilder(filename).insert(filename.lastIndexOf("."), "(" + order + ")").toString();
    }

    public void down(List<FileDO> list, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取内网前缀地址
        String innerUriPrefix = fileProperties.getInnerUriPrefix();
        String remoteUriPrefix = fileProperties.getUriPrefix();
        log.info("内网前缀地址 innerUriPrefix={}", innerUriPrefix);
        long fileSize = list.stream().filter((file) -> file != null && !DataType.DIR.eq(file.getDataType()) && StringUtils.isNotEmpty(file.getUrl()))
                .mapToLong((file) -> Convert.toLong(file.getSize(), 0L)).sum();
        String extName = list.get(0).getSubmittedFileName();
        if (list.size() > 1) {
            extName = StringUtils.substring(extName, 0, StringUtils.lastIndexOf(extName, ".")) + "等.zip";
        }

        Map<String, String> map = new LinkedHashMap<>(list.size());
        Map<String, Integer> duplicateFile = new HashMap<>(list.size());
        list.stream()
                //过滤不符合要求的文件
                .filter((file) -> file != null && !DataType.DIR.eq(file.getDataType()) && StringUtils.isNotEmpty(file.getUrl()))
                //将外网地址转成内网地址
                .map((file) -> {
                    String url = file.getUrl();
                    if (StringUtils.isNotEmpty(innerUriPrefix)) {
                        //转为内网渠道下载
                        url = url.replaceAll(remoteUriPrefix, innerUriPrefix);
                        log.info("文件转内网 url地址 ={}", url);
                    }
                    file.setUrl(url);
                    return file;
                })
                //循环处理相同的文件名
                .forEach((file) -> {
                    String submittedFileName = file.getSubmittedFileName();
                    if (map.containsKey(submittedFileName)) {
                        if (duplicateFile.containsKey(submittedFileName)) {
                            duplicateFile.put(submittedFileName, duplicateFile.get(submittedFileName) + 1);
                        } else {
                            duplicateFile.put(submittedFileName, 1);
                        }
                        submittedFileName = buildNewFileName(submittedFileName, duplicateFile.get(submittedFileName));
                    }
                    map.put(submittedFileName, file.getUrl());
                });


        ZipUtils.zipFilesByInputStream(map, Long.valueOf(fileSize), extName, request, response);
    }


}
