package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.HashMap;
import java.util.Map;

public interface BannerService {

    //插入轮播图
    public String register(Banner banner);

    //删除轮播图
    public void remove(String id);

    //修改轮播图
    public String modify(Banner banner);

    //修改状态
    public Map<String,Object> modifyStatus(Banner banner);

    //分页
    public HashMap<String, Object> queryAllBann(Integer page, Integer rows);

}
