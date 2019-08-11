package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserService {
    //添加一个用户
    public String register(User user);
    //删除用户
    public void remove(String id);
    //修改用户信息
    public String modify(User user);
    //修改状态
    public HashMap<String, Object> modifyStatus(User user);
    //分页
    public HashMap<String, Object> queryAll( Integer page, Integer rows);
    //查询所有的用户信息
    public List<User> queryUsers();
    //用户统计

    public Map<String,Object> queryUserByDateAndSex(String sex);
    //用户分布
    public Map<String,Object> queryUserInChina(String sex);




}
