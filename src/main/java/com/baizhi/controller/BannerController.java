package com.baizhi.controller;


import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;


    @RequestMapping("updateStatus")
    @ResponseBody
    public Map<String,Object> updataStatus(String id,String status){

        Banner banner = new Banner();
        banner.setId(id);
        banner.setStatus(status);
        Map<String, Object> map = bannerService.modifyStatus(banner);
        return map;

    }
    @RequestMapping("edit")
    @ResponseBody
    public String edit(Banner banner,String oper){
        //System.out.println("qqqq");
            String id =null;
        //增加
        if("add".equals(oper)){
             id = bannerService.register(banner);
        }

        // 删除
        if("del".equals(oper)){
            bannerService.remove(banner.getId());
        }

        //修改
        if("edit".equals(oper)){
            if (banner.getImgpath()=="") banner.setImgpath(null);
            id= bannerService.modify(banner);
        }
        return id;
    }
    //文件上传
    @RequestMapping("upload")
    @ResponseBody
    public void upload(MultipartFile imgpath, String id, HttpServletRequest request){
        if(imgpath.getSize()!=0) {
            String realPath = request.getSession().getServletContext().getRealPath("/upload/photo/");

            File file = new File(realPath);

            if (!file.exists()) {
                file.mkdirs();
            }
            String filename = imgpath.getOriginalFilename();

            try {
                imgpath.transferTo(new File(realPath, filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Banner banner = new Banner();
            banner.setId(id);
            banner.setImgpath(filename);
            bannerService.modify(banner);
        }
    }
    //展示轮播图
    @RequestMapping("page")
    @ResponseBody
    public Map<String,Object> query(Integer page,Integer rows)throws Exception{
        HashMap<String, Object> map = new HashMap<>();
        map=bannerService.queryAllBann(page,rows );
        return map;
    }
}
