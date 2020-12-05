package cn.ablocker.FoodBlog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebBlog
{
    private int id;
    private Date publishTime;       // 发布博客的时间
    private String title;           // 标题
    private String content;         // 文字内容
    private String img;             // 图片
    private int likes;              // 点赞数
    private String userName;        // 用户名（关联属性）
    private List<Comment> comments; // 评论（关联属性）

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }

    public void setPublishTime(Date publishTime)
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

    public List<Comment> getComments()
    {
        if (this.comments == null)
            this.comments = new ArrayList<Comment>();
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }
}
