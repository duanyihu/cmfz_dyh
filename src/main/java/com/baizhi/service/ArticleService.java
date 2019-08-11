package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {

    //分页
    public Map<String, Object> queryAll(Integer page, Integer rows);
    //增加文章
    public String register(Article article);
    //修改文章
    public String modify(Article article);
    //增加修改
    public Map<String, Object> modifyArticle(Article article);
    //删除文章
    public void remove(String id);

}
