package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AdminDAO {

    //管理员的登录
    public Admin selectOne(@Param("name") String name, @Param("password") String password);
}
