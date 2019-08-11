package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("edit")
    @ResponseBody
    public String edit(Album album,String oper){
        String id =null;
        //添加
        if("add".equals(oper)){
            id=albumService.register(album);
        }
        //删除
        if("del".equals(oper)){
            albumService.remove(album.getId());
        }
        //修改
        if("edit".equals(oper)){
            if (album.getCover_img()=="") album.setCover_img(null);
            System.out.println(album);
            id=albumService.modify(album);
        }
        return id;
    }

    //文件上传
    @ResponseBody
    @RequestMapping("upload")
    public void upload(MultipartFile cover_img, String id, HttpServletRequest request){
        if(cover_img.getSize()!=0) {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/photo/");

            File file = new File(realPath);

            if (!file.exists()) {
                file.mkdirs();
            }
            String filename = cover_img.getOriginalFilename();
            String name = new Date().getTime()+filename;

            try {
                cover_img.transferTo(new File(realPath, name));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Album album = new Album();
            album.setId(id);
            album.setCover_img(name);
            albumService.modify(album);
        }
    }
    //展示专辑
    @RequestMapping("page")
    @ResponseBody
    public Map<String,Object> query(Integer page, Integer rows)throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        map=albumService.queryPage(page,rows );
        return map;
    }
}
