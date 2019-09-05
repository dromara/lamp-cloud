//package com.github.zuihou.zuul.fallback;
//
//import org.springframework.stereotype.Component;
//
///**
// * 消息服务 网关层面服务熔断 处理类
// *
// * @author zuihou
// * @date 2018/07/05
// */
//@Component
//public class MsgsServiceFallbackProvider extends AbstractServiceFallbackProvider {
//    @Override
//    public String getRoute() {
//        //Zuul 目前只支持服务级别的熔断，不支持具体到某个URL进行熔断 ， 所以，在响应时只需给出大概提示即可
//        return "zuihou-msgs-server";
//    }
//}
