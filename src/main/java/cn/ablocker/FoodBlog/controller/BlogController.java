package cn.ablocker.FoodBlog.controller;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.ablocker.FoodBlog.annotation.LoginNeeded;
import cn.ablocker.FoodBlog.bussiness.BlogBussiness;
import cn.ablocker.FoodBlog.bussiness.LoginBussiness;
import cn.ablocker.FoodBlog.entity.WebBlog;
import cn.ablocker.FoodBlog.response.BlogsResponse;

@RestController
public class BlogController
{
    @Autowired
    private ApplicationContext context;
    @Autowired
    private BlogBussiness blogBussiness;
    @Autowired
    private LoginBussiness loginBussiness;
    
    @LoginNeeded
    @GetMapping("/api/blogs")
    public BlogsResponse getAllBlogs()
    {
        return (BlogsResponse) context.getBean("blogsResponse", new Object[] {blogBussiness.getBlogs()});
    }

    @LoginNeeded
    @GetMapping("/api/blogs/{userName}")
    public BlogsResponse getUserBlogs(@PathVariable("userName") String userName)   
    {
        return (BlogsResponse) context.getBean("blogsResponse", new Object[] {blogBussiness.getBlogs(userName)});
    }

    @LoginNeeded
    @PostMapping("/api/add/blog")
    public BlogsResponse addAnBlog(@RequestParam("content") String content, @RequestParam("img") Blob img, HttpServletRequest request, HttpServletResponse response)
    {
        String sessionId = request.getSession().getId();
        String userName = loginBussiness.getUserName(sessionId);
        WebBlog blog = blogBussiness.addBlog(userName, content, img);
        List<WebBlog> blogs = new ArrayList<>();
        blogs.add(blog);
        return (BlogsResponse) context.getBean("blogsResponse", new Object[] {blogs});
    }
}
