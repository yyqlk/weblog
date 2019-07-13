package com.like.weblog.weblog.dto;

import com.like.weblog.weblog.expection.CustomizeErrorCode;

import java.util.List;

public class ResultDTO<T> {
    private Integer code;
    private String message;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static ResultDTO okOf(Integer code, String message, List data){
        ResultDTO resultDTO =new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return  resultDTO;
    }

    public static ResultDTO okOf(Integer code, String message){
        ResultDTO resultDTO =new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return  resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode customizeErrorCode) {
        ResultDTO resultDTO =new ResultDTO();
        resultDTO.setCode(customizeErrorCode.getCode());
        resultDTO.setMessage(customizeErrorCode.getMessage());
        return  resultDTO;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
