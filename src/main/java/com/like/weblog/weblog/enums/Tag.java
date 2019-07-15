package com.like.weblog.weblog.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum  Tag {
    PROGRAMMING("开发语言", getPrograming()),
    FRAME("开发框架",getFrame());

    private String tagCategory;
    private List<String> tags;

    Tag(String tagCategory,List<String> tags){
        this.tagCategory=tagCategory;
        this.tags=tags;
    }
    static List getPrograming(){
        ArrayList<String> programLanguage= new ArrayList<>();
        programLanguage.addAll(Arrays.asList("java","python","js","php"));
        return programLanguage;
    }
    static List getFrame(){
        ArrayList<String> Frame= new ArrayList<>();
        Frame.addAll(Arrays.asList("spring","springBoot","struts","mybatis","jsp","springMVC"));
        return Frame;
    }
    public List<String> gettags(){
        return tags;
    }
}

