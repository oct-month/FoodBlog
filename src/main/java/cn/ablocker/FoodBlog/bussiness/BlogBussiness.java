package cn.ablocker.FoodBlog.bussiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ablocker.FoodBlog.dao.WebBlogDAO;
import cn.ablocker.FoodBlog.entity.WebBlog;
import cn.ablocker.FoodBlog.util.ImgHelper;

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

    // 增加一篇博客，并返回这篇博客
    public WebBlog addBlog(String userName, String title, String content, String imgHead, String img)
    {
        WebBlog blog = new WebBlog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setImgHead(imgHead);
        blog.setImg(ImgHelper.decodeFile(img));
        int blogId = webBlogDAO.addAnBlog(userName, blog);
        if ( blogId != 0)
            return webBlogDAO.findAnBlog(blogId);
        else
            return null;
    }

    public void addAnLikes(int blogId)
    {
        WebBlog blog = webBlogDAO.findAnBlog(blogId);
        webBlogDAO.updataLikes(blog.getLikes() + 1, blogId);
    }
}
