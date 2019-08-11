package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterDAO chapterDAO;
    @Resource
    private AlbumDAO albumDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public HashMap<String, Object> queryAll(String albumId,Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();
        Integer pages = (page - 1) * rows;//起始条数
        Integer counts = chapterDAO.selectCount(albumId);//总条数
        List<Chapter> chapters = chapterDAO.selelctAll(albumId,pages, rows);
        Integer a = counts % rows == 0 ? counts / rows : counts / rows + 1;
        map.put("page", page);
        map.put("records", counts);
        map.put("total", a);
        map.put("rows", chapters);
        return map;
    }

    //增加一个章节
    @Override
    public String register(Chapter chapter,String albumId) {
        Album album = albumDAO.selectOneAlbum(albumId);
        System.out.println(album);
        album.setCount(album.getCount()+1);
        String id = UUID.randomUUID().toString();
        chapter.setId(id);
        chapter.setUp_date(new Date());
        chapterDAO.insert(chapter);
        return id;
    }
    //文件上传
    @Override
    public Map<String, Object> upload(MultipartFile url, String id, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();

        String realPath = request.getSession().getServletContext().getRealPath("/upload/music");

        File file = new File(realPath);

        if(!file.exists()){
            file.mkdirs();
        }
        //获取上传的文件名子
        String filename = url.getOriginalFilename();
        //给文件加上时间戳前缀
        String name=new Date().getTime()+"-"+filename;

        //文件上传
        try {
            url.transferTo(new File(realPath,name));

            //获取文件大小-1
            long size = url.getSize();
            String sizes =size/1024/1024+"MB";
            //获取文件大小-2
            DecimalFormat format = new DecimalFormat("0.00");
            String str = String.valueOf(size);
            Double dd = Double.valueOf(str)/1024/1024;
            String newsizess = format.format(dd)+"MB";

            //获取文件时长
            AudioFileIO fileIO = new AudioFileIO();
            AudioFile audio = fileIO.readFile(new File(realPath, name));
            AudioHeader audioHeader = audio.getAudioHeader();
            //获取时长    分钟
            int length = audioHeader.getTrackLength();
            System.out.println("=时长==length "+length);
            String duration=length/60+"分"+length%60+"秒";

            Chapter chapter = new Chapter();
            chapter.setId(id);
            chapter.setUrl(name);
            chapter.setSize(newsizess);
            chapter.setDuration(duration);

            System.out.println("===service=修改音频信息=chapter"+chapter);
            chapterDAO.update(chapter);

            map.put("success","200");
            map.put("message","上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400");
            map.put("message","上传失败");
        }


        return map;
    }

    //删除一个章节
    @Override
    public void remove(String id) {
        chapterDAO.delete(id);
    }

    //增加时
    @Override
    public Map<String, Object> modifyChapter(Chapter chapter) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            chapterDAO.update(chapter);
            map.put("success","200" );
            map.put("message","修改成功" );
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success","400" );
            map.put("message","修改失败" );
        }
        return map;
    }

    //修改一个章节
    @Override
    public String modify(Chapter chapter) {
        String id = chapter.getId();
        chapterDAO.update(chapter);
        return id;
    }
}
