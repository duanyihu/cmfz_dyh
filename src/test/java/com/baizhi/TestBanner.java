package com.baizhi;

import com.baizhi.dao.BannerDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = CmfzDyhApplication.class)
@RunWith(SpringRunner.class)
public class TestBanner {


    @Autowired
    private BannerDAO bannerDAO;
    @Test
    public void test1(){

        //Banner banner = new Banner("6","挺好的", "../bootstrap/img/4.png", "嗯嗯不错！", "冻结", new Date());
        //bannerDAO.insert(banner);
        //List<Banner> banners =bannerDAO.selelctAllBann(1, 2);
        // bannerDAO.update(banner);
        //List<Banner> banners = bannerDAO.selectAll();
        /*for (Banner banner : banners) {
            System.out.println(banner);

        }*/
        /*Integer integer = bannerDAO.selectCount();
        System.out.println(integer);*/
       bannerDAO.delete("6");
    }
}
