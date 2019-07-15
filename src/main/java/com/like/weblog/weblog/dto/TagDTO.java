package com.like.weblog.weblog.dto;

import java.util.List;

public class TagDTO {
    private String tagCategory;
    private List<String> tags;

    public String getTagCategory() {
        return tagCategory;
    }

    public void setTagCategory(String tagCategory) {
        this.tagCategory = tagCategory;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
