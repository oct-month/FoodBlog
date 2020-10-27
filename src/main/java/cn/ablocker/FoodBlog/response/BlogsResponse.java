package cn.ablocker.FoodBlog.response;

import java.util.List;

import cn.ablocker.FoodBlog.entity.WebBlog;

public class BlogsResponse
{
    private int status;             // 请求状态
    private List<WebBlog> blogs;    // 博客

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public List<WebBlog> getBlogs()
    {
        return blogs;
    }

    public void setBlogs(List<WebBlog> blogs)
    {
        this.blogs = blogs;
    }
}
