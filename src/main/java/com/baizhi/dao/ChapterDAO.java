package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDAO {
    //分页
    public List<Chapter> selelctAll(@Param("albumId")String albumId,@Param("start") Integer start, @Param("rows")Integer rows);
    //查询总条数
    public Integer selectCount(String albumId);

    //增加一个一个章节
    public void insert(Chapter chapter);
    //删除一个章节
    public void delete(String id);
    //修改一个章节
    public void update(Chapter chapter);
}
