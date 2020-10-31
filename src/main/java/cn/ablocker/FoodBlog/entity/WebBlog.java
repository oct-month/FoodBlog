package cn.ablocker.FoodBlog.entity;

import java.sql.Timestamp;

public class WebBlog
{
    private int id;
    private Timestamp publishTime;  // 发布博客的时间
    private String title;           // 标题
    private String content;         // 文字内容
    private String img;             // 图片
    private int likes;              // 点赞数
    private String userName;        // 用户名（关联属性）

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Timestamp getPublishTime()
    {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime)
    {
        this.publishTime = publishTime;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public int getLikes()
    {
        return likes;
    }

    public void setLikes(int likes)
    {
        this.likes = likes;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
