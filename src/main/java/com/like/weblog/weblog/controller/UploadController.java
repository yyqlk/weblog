package com.like.weblog.weblog.controller;


import com.fasterxml.jackson.databind.util.ClassUtil;
import com.like.weblog.weblog.dto.ImgDTO;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.Buffer;
import java.util.UUID;

@Controller
public class UploadController {
    @ResponseBody
    @PostMapping("/upload")
    public ImgDTO imgUpload(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multiparRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multiparRequest.getFile("editormd-image-file");
        String originalFilename = UUID.randomUUID()+file.getOriginalFilename();
        InputStream inputStream = file.getInputStream();
        String contextPath = ResourceUtils.getURL("classpath:").getPath();
        File saveFile = new File(contextPath+"\\static\\images\\upload");
        OutputStream outputStream = new FileOutputStream(saveFile+File.separator+originalFilename);
//        FileWriter fileWriter = new FileWriter(saveFile+"\\"+originalFilename);
        byte[] buffer = new byte[1024];
        int lengh= 0;
        while ((lengh = inputStream.read(buffer))>0){
            outputStream.write(buffer,0,lengh);
        }
        inputStream.close();
        outputStream.close();
        File uploadFile = new File(saveFile + File.separator + originalFilename);
        int succcss =0;
        String message = "false";
        if(uploadFile.exists()){
            succcss=1;
            message="success";
        }
        ImgDTO imgDTO = new ImgDTO();
        imgDTO.setSuccess(succcss);
        imgDTO.setMessage(message);
        imgDTO.setUrl("/images/upload/"+originalFilename);
        return  imgDTO;
    }
}
