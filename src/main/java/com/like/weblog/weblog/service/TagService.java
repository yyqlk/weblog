package com.like.weblog.weblog.service;

import com.like.weblog.weblog.enums.Tag;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    public static boolean isContain(String tag) {
        String[] tags = tag.split(",");
        for(String t:tags){
            if(Tag.FRAME.gettags().contains(t)||Tag.PROGRAMMING.gettags().contains(t)){
            }else {
                return false;
            }
        }
        return true;
    }
}
