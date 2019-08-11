package com.baizhi.controller;

import com.baizhi.service.AdminService;
import com.baizhi.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //生成验证码
    @RequestMapping("code")
    public String code(HttpSession session, HttpServletResponse response) throws Exception{
        String code = ImageCodeUtil.getSecurityCode();
        session.setAttribute("code",code );
        //生成图片
        BufferedImage image = ImageCodeUtil.createImage(code);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(image, "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将生成的验证码图片
        return null;
    }


    //管理员登录
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(String code, String name, String password, HttpSession session){
        Map<String, Object> map = adminService.login(code, name, password, session);
        return map;
    }
    //退出
    @RequestMapping("/exit")
    public  String exit(HttpSession session) throws  Exception{
        session.removeAttribute("login");
        return "redirect:/login/login.jsp";
    }

}
