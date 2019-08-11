package com.baizhi.service;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.util.Md5Utils;
import io.goeasy.GoEasy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    //分页查询
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> queryAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        List<User> users = userDAO.selectAll((page - 1) * rows, rows);
        Integer counts = userDAO.selectCount();
        Integer a = counts%rows==0?counts/rows:counts/rows+1;
        map.put("page",page );
        map.put("records",counts );
        map.put("total", a);
        map.put("rows",users );

        return map;
    }

    //查询所有的用户信息
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<User> queryUsers() {
        List<User> users = userDAO.selectUsers();
        return users;
    }

    //用户统计
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryUserByDateAndSex(String sex) {
        Map<String, Object> map = new HashMap<>();
        List<Date> dates = userDAO.selectDate();//查询时间
        ArrayList<String> list = new ArrayList<>();
        for (Date date : dates) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String s = format.format(date);
            String[] split = s.split("-");
            list.add(split[1]);
        }
        List<Date> dates1 = userDAO.selectDateBySex(sex);//通过性别查时间
        for (String s : list) {
            Integer count =0;
            for (Date date : dates1) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String s1 = format.format(date);
                String[] split = s1.split("-");
                System.out.println(split.length);
                if(s.equals(split[1])){
                    count++;
                }
            }
            map.put(s,count);
        }
        return map;
    }

    //用户分布
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> queryUserInChina(String sex) {
        Map<String, Object> map = new HashMap<>();
        List<String> cities = userDAO.selectCity();//查询所有城市
        List<String> cities1 = userDAO.selectCities(sex);
        for (String c : cities) {
            Integer count = 0;
            for (String city1 : cities1) {
                if(c.equals(city1)){
                    count++;
                }
            }
            map.put(c, count);
        }
        return map;
    }


    //修改用户的状态
    @Override
    public HashMap<String, Object> modifyStatus(User user) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            userDAO.update(user);
            map.put("success","200" );
            map.put("message","修改成功" );
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400" );
            map.put("message","修改失败" );
        }
        return map;
    }

    //注册用户
    @Override
    public String register(User user) {
        String id = UUID.randomUUID().toString();
        String salt = Md5Utils.getSalt(9);
        String md5Code = Md5Utils.getMd5Code(user.getPassword() + salt);
        user.setSalt(salt);
        user.setPassword(md5Code);
        user.setId(id);
        user.setStatus("1");
        user.setPhone("1");
        user.setReg_date(new Date());
        user.setGuruId("1");
        userDAO.insert(user);
        return id;
    }

    //注册修改
    @Override
    public String modify(User user) {
        String id = user.getId();
        userDAO.update(user);
        return id;
    }

    //删除用户信息
    @Override
    public void remove(String id) {
        userDAO.delete(id);
    }


}
