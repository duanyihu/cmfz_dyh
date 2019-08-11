package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public interface ChapterService {

    //分页
    public HashMap<String, Object> queryAll(String albumId,Integer page, Integer rows);

    //增加一个一个章节
    public String  register(Chapter chapter,String albumId);

    //上传文件
    public Map<String,Object> upload(MultipartFile url, String id, HttpServletRequest request);




    //删除一个章节
    public void remove(String id);
    // 增加时修改一个章节
    public Map<String,Object> modifyChapter(Chapter chapter);

    //修改
    public String modify(Chapter chapter);
}
