package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //分页
    @RequestMapping("page")
    @ResponseBody
    public Map<String, Object> queryByPage(Integer page,Integer rows){
        Map<String, Object> map = new HashMap<>();
        map = articleService.queryAll(page,rows );
        return map;
    }


    //修改
    @RequestMapping("update")
    @ResponseBody
    public Map<String,Object> update(Article article,String articleId){
        System.out.println("++++"+articleId);
        System.out.println(article);
        article.setId(articleId);
        Map<String, Object> map = articleService.modifyArticle(article);
        return map;

    }

    @RequestMapping("add")
    @ResponseBody
    public String  updataStatus(Article article){

         String id = articleService.register(article);
        return id;
    }
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Article article,String oper){
        //System.out.println("qqqq");
        String id =null;
        //增加
        if("add".equals(oper)){

        }
        //删除
        if("del".equals(oper)){
            articleService.remove(article.getId());
        }
        //修改
        if("edit".equals(oper)){
            if (article.getInsert_img()=="")article.setInsert_img(null);
            id= articleService.modify(article);
        }
        return id;
    }
}
