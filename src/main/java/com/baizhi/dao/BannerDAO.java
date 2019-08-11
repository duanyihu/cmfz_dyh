package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BannerDAO {

    //插入轮播图
    public void  insert(Banner banner);

    //查询所有的轮播图
    public List<Banner> selectAll();

    //删除轮播图
    public void delete(String id);

    //修改轮播图
    public void update(Banner banner);

    //查询轮播图总条数
    public Integer selectCount();

    //分页展示轮播图                         //当前页      一页显示多少条
    public List<Banner> selelctAllBann(@Param("start") Integer start, @Param("rows")Integer rows);

}
