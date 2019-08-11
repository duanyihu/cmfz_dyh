package com.baizhi.service;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface AdminService {

    //管理员登录
    public Map<String,Object> login(String code, String name, String password, HttpSession session);


}
