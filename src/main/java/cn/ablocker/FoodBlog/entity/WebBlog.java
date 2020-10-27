package cn.ablocker.FoodBlog.entity;

import java.sql.Blob;
import java.sql.Timestamp;

public class WebBlog
{
    private int id;
    private String userName;        // 关联到BlogUser的用户名
    private Timestamp publishTime;  // 发布博客的时间
    private String content;         // 文字内容
    private Blob img;               // 图片

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Timestamp getPublishTime()
    {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime)
    {
        this.publishTime = publishTime;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Blob getImg()
    {
        return img;
    }

    public void setImg(Blob img)
    {
        this.img = img;
    }
}
