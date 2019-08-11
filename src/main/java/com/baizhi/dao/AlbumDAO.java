package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO {

    //分页查询
    public List<Album> selectPage(@Param("start") Integer start,@Param("rows") Integer rows);
    //查询总条数
    public Integer selectCount();
    //增加专辑
    public void insert(Album album);
    //删除专辑
    public void delete(String id);
    //修改专辑
    public void update(Album album);
    //通过id查询专辑
    public Album  selectOneAlbum(String id);

}
