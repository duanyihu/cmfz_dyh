package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("chaper")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @RequestMapping("queryByPage")
    @ResponseBody
    //分页显示
    public Map<String,Object> query(String albumId,Integer page, Integer rows)throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        map=chapterService.queryAll(albumId,page,rows );
        return map;
    }
    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Chapter chapter, String oper,String albumId){
        System.out.println("c"+albumId);
        String id=null;
       if("add".equals(oper)){
           chapter.setAlbum_id(albumId);
            id = chapterService.register(chapter,albumId);
       }
       if("del".equals(oper)){
           chapterService.remove(chapter.getId());
       }
       if("edit".equals(oper)){
           id = chapterService.modify(chapter);
       }
       return id;
    }
    @RequestMapping("upload")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile url, String id, HttpServletRequest request){

        System.out.println(id);

        Map<String, Object> map = chapterService.upload(url, id, request);
        //调用业务
        return map;
    }
    @RequestMapping("down")
    @ResponseBody
    public void down(String fileName, HttpServletRequest request, HttpServletResponse response){
        //获取文件所在的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/music");

        //根据路径读取文件
        try {

            FileInputStream inputStream = new FileInputStream(new File(realPath, fileName));

            //设置响应格式头                               以附件形式 inline:在线打开
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"utf-8"));
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.copy(inputStream,outputStream );
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取文件所在路径
       /* String realPath = request.getSession().getServletContext().getRealPath("/upload/music");

        //根据路径读取文件
        try {
            FileInputStream inputStream = new FileInputStream(new File(realPath, fileName));

            //设置文件响应格式   响应头   attachment:以附件的形式下载，   inline:在线打开
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(fileName,"utf-8"));

            ServletOutputStream outputStream = response.getOutputStream();
            //文件下载
            IOUtils.copy(inputStream,outputStream);

            //关闭资源
            inputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
