package com.like.weblog.weblog.controller;


import com.like.weblog.weblog.dto.ImgDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UploadController {
    @ResponseBody
    @PostMapping("/upload")
    public ImgDTO imgUpload(){
        ImgDTO imgDTO = new ImgDTO();
        imgDTO.setSuccess(1);
        imgDTO.setMessage("success");
        imgDTO.setUrl("/images/test.jpg");
        return  imgDTO;
    }
}
