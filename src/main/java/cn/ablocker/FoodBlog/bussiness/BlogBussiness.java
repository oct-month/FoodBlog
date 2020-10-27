package cn.ablocker.FoodBlog.bussiness;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ablocker.FoodBlog.dao.WebBlogDAO;
import cn.ablocker.FoodBlog.entity.WebBlog;

@Component
public class BlogBussiness
{
    @Autowired
    private WebBlogDAO webBlogDAO;

    // 返回所有博客
    public List<WebBlog> getBlogs()
    {
        return webBlogDAO.findAllBlogs();
    }

    // 返回指定用户的所有博客
    public List<WebBlog> getBlogs(String userName)
    {
        return webBlogDAO.findUserBlogs(userName);
    }

    // 增加一篇博客
    public WebBlog addBlog(String userName, String title, String content, Blob img)
    {
        WebBlog blog = new WebBlog();
        blog.setUserName(userName);
        blog.setPublishTime(new Timestamp(System.currentTimeMillis()));
        blog.setTitle(title);
        blog.setContent(content);
        blog.setImg(img);
        if (webBlogDAO.addAnBlog(blog) == 1)
            return blog;
        else
            return null;
    }
}
