//package com.tangyh.lamp.file.api.fallback;
//
//import com.tangyh.basic.base.R;
//import com.tangyh.lamp.file.api.AttachmentApi;
//import com.tangyh.lamp.file.dto.AttachmentDTO;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * 熔断
// *
// * @author zuihou
// * @date 2019/07/25
// */
//@Component
//public class AttachmentApiFallback implements AttachmentApi {
//    @Override
//    public R<AttachmentDTO> upload(MultipartFile file, Boolean isSingle, Long id, String bizId, String bizType) {
//        return R.timeout();
//    }
//}
