package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDAO {

    //分页查询
    public List<Article> selelctAll(@Param("start") Integer start, @Param("rows")Integer rows);
    //查询总条数
    public Integer selectCount();
    //修改用户状态
    public void update(Article article);
    //添加文章
    public void insert(Article article);
    //删除文章
    public void delete(String  id);

}
