package com.baizhi.entity;

import java.util.Date;

public class Album {
    private String id;
    private String title;
    private String author;
    private Double score;
    private String cover_img;
    private String broadcast;
    private Integer count;
    private String content;
    private Date pub_date;

    public Album() {
    }

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", score=" + score +
                ", cover_img='" + cover_img + '\'' +
                ", broadcast='" + broadcast + '\'' +
                ", count=" + count +
                ", content='" + content + '\'' +
                ", pub_date=" + pub_date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getBroadcast() {
        return broadcast;
    }

    public void setBroadcast(String broadcast) {
        this.broadcast = broadcast;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public Album(String id, String title, String author, Double score, String cover_img, String broadcast, Integer count, String content, Date pub_date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.score = score;
        this.cover_img = cover_img;
        this.broadcast = broadcast;
        this.count = count;
        this.content = content;
        this.pub_date = pub_date;
    }
}
