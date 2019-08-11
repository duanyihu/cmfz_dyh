package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Excel(name = "ID")
    private String id;
    @Excel(name = "用户名字")
    private String phone;
    @Excel(name = "用户密码")
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "用户头像",type = 2,width = 50)
    private String pic_img;//头像
    @Excel(name = "用户法名")
    private String ahama;//法名
    @Excel(name = "用户名字")
    private String name;
    @Excel(name = "用户性别")
    private String sex;
    @Excel(name = "用户籍贯")
    private String city;
    @Excel(name = "用户签名")
    private String sign;//签名
    @Excel(name = "用户的状态")
    private String status;
    @Excel(name="注册时间",format="yyyy-MM-dd",width=20)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reg_date;//注册时间
    @Excel(name = "关注的上师")
    private String guruId;//上师id
}
