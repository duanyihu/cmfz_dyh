package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class AblumServiceImpl implements AlbumService {

    @Resource
    private AlbumDAO albumDAO;
    //分页
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public HashMap<String, Object> queryPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Integer pages = (page-1)*rows;//起始条数
        List<Album> albums = albumDAO.selectPage(pages, rows);//数据
        Integer counts = albumDAO.selectCount();//总条数
        Integer a=counts%rows==0?counts/rows:counts/rows+1;
        map.put("page", page);
        map.put("records", counts);
        map.put("total", a);
        map.put("rows", albums);
        return map;
    }
    //添加一个专辑
    @Override
    public String register(Album album) {
        String id = UUID.randomUUID().toString();
        album.setId(id);
        album.setPub_date(new Date());
        albumDAO.insert(album);
        return id;
    }

    @Override
    public void remove(String id) {
        albumDAO.delete(id);
    }

    @Override
    public String modify(Album album) {
        String id = album.getId();
        albumDAO.update(album);
        return id;
    }

    @Override
    public Map<String, Object> modifyAlbum(Album album) {

        HashMap<String, Object> map = new HashMap<>();
        try {
            albumDAO.update(album);
            map.put("success","200" );
            map.put("message","修改成功" );
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400" );
            map.put("message","修改失败" );
        }
        return map;
    }
}
