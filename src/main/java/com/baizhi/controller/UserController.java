package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.City;
import com.baizhi.entity.Proe;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    //分页展示
    @RequestMapping("page")
    @ResponseBody
    public HashMap<String,Object> page(Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<>();
        map = userService.queryAll(page, rows);

        return map;
    }

    //用户月份统计
    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(){

        Map<String, Object> map1 = userService.queryUserByDateAndSex("男");
        Map<String, Object> map2 = userService.queryUserByDateAndSex("女");
        Set<Map.Entry<String, Object>> entrySet = map1.entrySet();
        ArrayList<String> list1 = new ArrayList<>();//月份
        ArrayList<Integer> list2 = new ArrayList<>();//男生月份
        ArrayList<Integer> list3 = new ArrayList<>();//女生月份

        for (Map.Entry<String, Object> entry : entrySet) {//男生
            String key = entry.getKey();
            list1.add(key);
            Integer value =(Integer) entry.getValue();
            list2.add(value);
        }
        Set<Map.Entry<String, Object>> entrySet1 = map2.entrySet();
        for (Map.Entry<String, Object> entry : entrySet1) {
            String key = entry.getKey();
            Integer value = (Integer)entry.getValue();
            list3.add(value);

        }
        Map<String, Object> map = new HashMap<>();
        map.put("month",list1 );
        map.put("boys",list2 );
        map.put("girls",list3 );
        return map;
    }
    //用户分布
    @RequestMapping("queryAllMap")
    @ResponseBody
    public ArrayList<Proe> queryAllMap(){

        Map<String, Object> map1 = userService.queryUserInChina("男");
        Map<String, Object> map2 = userService.queryUserInChina("女");
        //男
        ArrayList<City>  boys= new ArrayList<>();
        Set<Map.Entry<String, Object>> entrySet1 = map1.entrySet();
        for (Map.Entry<String, Object> entry : entrySet1) {
            City city = new City();
            String key = entry.getKey();
            Integer value = (Integer)entry.getValue();
            city.setName(key);
            city.setValue(value*1000+"");
            boys.add(city);
        }
        //女
        ArrayList<City> girls = new ArrayList<>();
        Set<Map.Entry<String, Object>> entrySet2 = map2.entrySet();
        for (Map.Entry<String, Object> entry : entrySet2) {
            City city = new City();
            String key = entry.getKey();
            Integer value = (Integer)entry.getValue();
            city.setName(key);
            city.setValue(value*1000+"");
            girls.add(city);
        }
        Proe proe1 = new Proe("小哥哥",boys);
        Proe proe2 = new Proe("小姐姐",girls);

        ArrayList<Proe> proes = new ArrayList<>();
        proes.add(proe1);
        proes.add(proe2);
        return proes;
    }

    //goeasy和eacharts集成
    @ResponseBody
    @RequestMapping("queryAllMapAndGoEasy")
    public void queryAllMapAndGoEasy(){
        Map<String, Object> map1 = userService.queryUserByDateAndSex("男");
        Map<String, Object> map2 = userService.queryUserByDateAndSex("女");
        Set<Map.Entry<String, Object>> entrySet = map1.entrySet();
        ArrayList<String> list1 = new ArrayList<>();//月份
        ArrayList<Integer> list2 = new ArrayList<>();//男生月份
        ArrayList<Integer> list3 = new ArrayList<>();//女生月份

        for (Map.Entry<String, Object> entry : entrySet) {//男生
            String key = entry.getKey();
            list1.add(key);
            Integer value =(Integer) entry.getValue();
            list2.add(value);
        }
        Set<Map.Entry<String, Object>> entrySet1 = map2.entrySet();
        for (Map.Entry<String, Object> entry : entrySet1) {
            String key = entry.getKey();
            Integer value = (Integer)entry.getValue();
            list3.add(value);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("month",list1 );
        jsonObject.put("boys", list2);
        jsonObject.put("girls",list3 );
        String content = jsonObject.toJSONString();
        //goeasy
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io","BC-f88e12d31cb24218bce834e437cb6228");
        goEasy.publish("myChannel",content);
    }

    //导出用户表
    @RequestMapping("exportExcel")
    @ResponseBody
    public String exportExcel(){
        List<User> users = userService.queryUsers();
        String message=null;
        //src/main/webapp/upload/photo/7.png
        for (User user : users) {
            user.setPic_img("src/main/webapp/upload/photo/"+user.getPic_img());
        }
        Workbook workbook= ExcelExportUtil.exportExcel(new ExportParams("用户表", "用户详细信息"), User.class, users);
        try {
            workbook.write(new FileOutputStream(new File("F:用户信息表.xls")));
            workbook.close();
            message="导出成功";
            return message;
        } catch (IOException e) {
            e.printStackTrace();
            message="导出失败";
            return message;
        }
    }

    //修改用户状态
    @ResponseBody
    @RequestMapping("updateStatus")
    public  HashMap<String,Object> modify(String id,String status){
        HashMap<String, Object> map = new HashMap<>();
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        map = userService.modifyStatus(user);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(User user,String oper){
        String id =null;
        if("add".equals(oper)){
            id = userService.register(user);

        }
        if("del".equals(oper)){
            userService.remove(user.getId());
        }
        if("edit".equals(oper)){
            if(user.getPic_img()=="") user.setPic_img(null);
            id = userService.modify(user);
        }
        return id;
    }
    //文件上传
    @ResponseBody
    @RequestMapping("upload")
    public void upload(MultipartFile pic_img, String id, HttpServletRequest request){
        if(pic_img.getSize()!=0){
            //获取文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/upload/photo");

            File file = new File(realPath);

            if(!file.exists()){
                file.mkdirs();
            }
            String filename = pic_img.getOriginalFilename();

            try {
                pic_img.transferTo(new File(realPath,filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
            User user = new User();
            user.setPic_img(filename);
            System.out.println(filename);
            user.setId(id);
            userService.modify(user);
        }
    }
}
