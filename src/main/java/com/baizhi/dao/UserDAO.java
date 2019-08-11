package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserDAO {
    //添加用户
    public void insert(User user);
    //删除用户
    public void delete(String id );
    //修改用户状态
    public void update(User user);
    //分页查询
    public List<User> selectAll(@Param("start") Integer start, @Param("rows")Integer rows);
    //查询总条数
    public Integer selectCount();
    //查询所有用户的信息
    public List<User> selectUsers();
    //用户统计
    public List<Date> selectDate();
    public List<Date> selectDateBySex(String sex);
    //城市分布
    public List<String> selectCity();
    public List<String> selectCities(String sex);

}
