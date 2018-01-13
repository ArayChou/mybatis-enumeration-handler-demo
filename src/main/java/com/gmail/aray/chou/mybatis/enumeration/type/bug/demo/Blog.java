package com.gmail.aray.chou.mybatis.enumeration.type.bug.demo;

/**
 * Created by zhouhongyang@zbj.com on 1/10/2018.
 */
public class Blog {

    private Integer id;
    private String title;
    private BlogStatus status;
    private BlogType type;

    public Blog() {

    }

    public Blog(Integer id, String title, BlogStatus status,BlogType type) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.type=type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogStatus getStatus() {
        return status;
    }

    public void setStatus(BlogStatus status) {
        this.status = status;
    }

    public BlogType getType() {
        return type;
    }

    public void setType(BlogType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
