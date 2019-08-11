package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Map<String, Object> login(String code, String name, String password, HttpSession session) {
        HashMap<String, Object> map = new HashMap<>();
        String code1 =(String) session.getAttribute("code");
        Admin admin =null;
        if(code.equals(code1)){
            admin = adminDAO.selectOne(name, password);
            if(name.equals(admin.getName())){
                if(password.equals(admin.getPassword())){
                    session.setAttribute("admin",admin );
                    map.put("success","200");
                    map.put("message","登录成功" );
                }else{
                    map.put("success","400" );
                    map.put("message","密码错误" );
                }
            }else{
                map.put("success","400" );
                map.put("message","请输入正确的用户名" );
            }

        }else{
            map.put("success","400" );
            map.put("message","请输入正确的验证码" );
        }
        return map;
    }



}
