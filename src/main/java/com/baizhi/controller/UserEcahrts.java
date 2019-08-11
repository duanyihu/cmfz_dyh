package com.baizhi.controller;

import com.baizhi.entity.City;
import com.baizhi.entity.Proe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("us")
@Controller
public class UserEcahrts {

    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String,Object> queryAll(){
        Map<String, Object> map = new HashMap<>();
        map.put("mouth", Arrays.asList("1月","2月","3月","4月","5月","6月"));
        map.put("boys", Arrays.asList(5, 200, 36, 100, 10, 20));
        map.put("girls", Arrays.asList(5, 200, 400, 100, 100, 200));
        return map;
    }

    @RequestMapping("queryAllMap")
    @ResponseBody
    public ArrayList<Proe> queryAllMap(){

        ArrayList<City>  boys= new ArrayList<>();
        boys.add(new City("云南","200"));
        boys.add(new City("海南","300"));
        boys.add(new City("江苏","500"));
        boys.add(new City("江西","600"));
        boys.add(new City("广州","500"));
        boys.add(new City("广东","500"));

        ArrayList<City> girls = new ArrayList<>();
        girls.add(new City("上海","200"));
        girls.add(new City("河南","300"));
        girls.add(new City("河北","400"));
        girls.add(new City("天津","500"));
        girls.add(new City("黑龙江","400"));
        girls.add(new City("西藏","300"));

        Proe proe1 = new Proe("小哥哥",boys);
        Proe proe2 = new Proe("小姐姐",girls);

        ArrayList<Proe> proes = new ArrayList<>();
        proes.add(proe1);
        proes.add(proe2);
        return proes;
    }

}
