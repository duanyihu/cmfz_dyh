package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDAO articleDAO;

    //分页展示
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Article> articles = articleDAO.selelctAll((page - 1) * rows, rows);
        Integer counts = articleDAO.selectCount();
        Integer a = counts%rows==0?counts/rows:counts/rows+1;
        map.put("page",page);
        map.put("total",a );
        map.put("records",counts );
        map.put("rows",articles );
        return map;
    }
    //添加文章
    @Override
    public String register(Article article) {
        String id  = UUID.randomUUID().toString();
         article.setId(id);
         article.setGuruId("1");
         article.setUp_date(new Date());
         articleDAO.insert(article);
        return id;
    }


    //修改
    @Override
    public String modify(Article article) {
        String id  = article.getId();
        articleDAO.update(article);
        return id;
    }

    @Override
    public Map<String, Object> modifyArticle(Article article) {
        System.out.println(article);
        Map<String, Object> map = new HashMap<>();

        try {
            articleDAO.update(article);
            map.put("success","200" );
            map.put("message","修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400" );
            map.put("message","修改失败");
        }
        return map;
    }

    @Override
    public void remove(String id) {
        articleDAO.delete(id);
    }
}
