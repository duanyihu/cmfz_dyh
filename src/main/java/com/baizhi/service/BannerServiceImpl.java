package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDAO bannerDAO;

    //添加一个轮播图
    @Override
    public String register(Banner banner) {
        String id = UUID.randomUUID().toString();
        banner.setId(id);
        banner.setStatus("1");
        banner.setUp_date(new Date());
        bannerDAO.insert(banner);
        return id;
    }

    @Override
    public void remove(String id) {

        bannerDAO.delete(id);
    }

    @Override
    public String modify(Banner banner) {
        String id = banner.getId();
        bannerDAO.update(banner);
        return id;
    }

    @Override
    public Map<String, Object> modifyStatus(Banner banner) {
        HashMap<String, Object> map = new HashMap<>();

        try {
            bannerDAO.update(banner);
            map.put("success","200" );
            map.put("message","修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400" );
            map.put("message","修改失败");
        }
        return map;
    }

    //分页
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public HashMap<String, Object> queryAllBann(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();
        Integer pages = (page-1)*rows;//起始条数
        Integer counts = bannerDAO.selectCount();//总条数
        List<Banner> banners = bannerDAO.selelctAllBann(pages, rows);
        Integer a=counts%rows==0?counts/rows:counts/rows+1;
        map.put("page", page);
        map.put("records", counts);
        map.put("total", a);
        map.put("rows", banners);
        return map;
    }

}
