package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Banner {
    private String id;
    private String title;
    private String imgpath;
    private String description;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date up_date;

    public Banner() {
    }

    public Banner(String id, String title, String imgpath, String description, String status, Date up_date) {
        this.id = id;
        this.title = title;
        this.imgpath = imgpath;
        this.description = description;
        this.status = status;
        this.up_date = up_date;
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

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imgpath='" + imgpath + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", up_date=" + up_date +
                '}';
    }
}
