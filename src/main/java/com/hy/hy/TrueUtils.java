package com.hy.hy;

import java.util.List;

/**
 * @Author 潘梦丽
 * @create 2020/8/3
 */
public class TrueUtils {
    private String title;
    private Integer id;
    private List<TrueUtils> children;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrueUtils> getChildren() {
        return children;
    }

    public void setChildren(List<TrueUtils> children) {
        this.children = children;
    }
}
