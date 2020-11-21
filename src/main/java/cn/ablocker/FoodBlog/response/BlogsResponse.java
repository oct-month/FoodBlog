package cn.ablocker.FoodBlog.response;

import java.util.List;

import cn.ablocker.FoodBlog.entity.WebBlog;

public class BlogsResponse extends BaseResponse
{
    private List<WebBlog> blogs;    // 博客

    public List<WebBlog> getBlogs()
    {
        return blogs;
    }

    public void setBlogs(List<WebBlog> blogs)
    {
        this.blogs = blogs;
    }
}
