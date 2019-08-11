package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.HashMap;
import java.util.Map;

public interface AlbumService {

    //分页查询
    public HashMap<String, Object> queryPage( Integer page,  Integer rows);
    //增加专辑
    public String register(Album album);
    //删除专辑
    public void remove(String id);
    //修改专辑
    public String modify(Album album);
    //修改
    public Map<String,Object> modifyAlbum(Album album);
}
