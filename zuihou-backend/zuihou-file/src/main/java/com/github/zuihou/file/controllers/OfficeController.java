package com.github.zuihou.file.controllers;


import com.github.zuihou.file.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

/**
 * @author zuihou
 */
@Controller
@RequestMapping("/office")
public class OfficeController {


    @Value("${fileServer.domain}")
    String domain;

    @Value("${files.docservice.url}")
    String doc_api;


    @RequestMapping("/upload")
    public String test(){

        return "file";
    }

    @RequestMapping
    public String office(ModelMap map, String url, String filename) throws UnknownHostException {
        String userAddress = InetAddress.getLocalHost().getHostAddress();
        map.put("key", GenerateRevisionId(userAddress + "/" + filename));

        map.put("url", domain + url);

        map.put("filename", filename);

        map.put("fileType", FileUtils.getExtension(filename).replace(".", ""));

        map.put("doc_api", doc_api);

        map.put("documentType", FileUtils.getFileType(filename).toString().toLowerCase());

        return "office";
    }

    private static String GenerateRevisionId(String expectedKey) {
        if (expectedKey.length() > 20)
            expectedKey = Integer.toString(expectedKey.hashCode());

        String key = expectedKey.replace("[^0-9-.a-zA-Z_=]", "_");

        return key.substring(0, Math.min(key.length(), 20));
    }

    public static void main(String[] args) {
        //Path path = Paths.get("/Users/xxxx/Books/Java并发编程的艺术.pdf");
        Path path = Paths.get("c:\\");
        FileSystem fileSystem = path.getFileSystem();
        Set<String> supportedViews = fileSystem.supportedFileAttributeViews();

        for (String view : supportedViews) {
            System.out.println(view);
        }
    }

}
