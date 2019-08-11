package com.baizhi;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.dao.UserDAO;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@SpringBootTest(classes = CmfzDyhApplication.class)
@RunWith(SpringRunner.class)
public class TestUser {
    @Resource
    private UserDAO userDAO;
    @Resource
    private UserService userService;
    @Test
    public  void test1(){
        /*Map<Date, Integer> map = userDAO.selectUser("男");
        System.out.println(map);
        Set<Date>  set = map.keySet();
        for (Date key : set) {
            System.out.println("key"+key+"value"+map.get(key));
        }*/
        for(int i=0; i<10;i++){
            Map<String, Object> map1 = userService.queryUserByDateAndSex("男");
            Map<String, Object> map2 = userService.queryUserByDateAndSex("女");
            Set<Map.Entry<String, Object>> entrySet = map1.entrySet();
            ArrayList<String> list1 = new ArrayList<>();//月份
            ArrayList<Integer> list2 = new ArrayList<>();//男生月份
            ArrayList<Integer> list3 = new ArrayList<>();//女生月份

            for (Map.Entry<String, Object> entry : entrySet) {//男生
                String key = entry.getKey();
                list1.add(key);
                Integer value = (Integer) entry.getValue();
                list2.add(value);
            }
            Set<Map.Entry<String, Object>> entrySet1 = map2.entrySet();
            for (Map.Entry<String, Object> entry : entrySet1) {
                String key = entry.getKey();
                Integer value = (Integer) entry.getValue();
                list3.add(value);
            }
            Random random = new Random();
            random.nextInt(10);
            int[] randoms ={random.nextInt(500),random.nextInt(100),random.nextInt(900),random.nextInt(400),random.nextInt(300),random.nextInt(800)};
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("month", list1);
            jsonObject.put("boys", randoms);
            jsonObject.put("girls", randoms);
            String content = jsonObject.toJSONString();
            //goeasy
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-f88e12d31cb24218bce834e437cb6228");
            goEasy.publish("myChannel", content);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
