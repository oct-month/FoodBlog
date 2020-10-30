package cn.ablocker.FoodBlog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ablocker.FoodBlog.annotation.LoginNeeded;
import cn.ablocker.FoodBlog.bussiness.BlogBussiness;
import cn.ablocker.FoodBlog.bussiness.LoginBussiness;
import cn.ablocker.FoodBlog.entity.WebBlog;
import cn.ablocker.FoodBlog.response.BlogsResponse;
import cn.ablocker.FoodBlog.response.CommonResponse;

@RestController
public class BlogController
{
    @Autowired
    private ApplicationContext context;
    @Autowired
    private BlogBussiness blogBussiness;
    @Autowired
    private LoginBussiness loginBussiness;

    private static final String BLOGS_RESPONSE_BEAN = "blogsResponse";
    
    @LoginNeeded
    @GetMapping("/api/blogs")
    public BlogsResponse getAllBlogs(HttpServletRequest request, HttpServletResponse response)
    {
        return (BlogsResponse) context.getBean(BLOGS_RESPONSE_BEAN, new Object[] {blogBussiness.getBlogs()});
    }

    @LoginNeeded
    @GetMapping("/api/blogs/{userName}")
    public BlogsResponse getUserBlogs(@PathVariable("userName") String userName, HttpServletRequest request, HttpServletResponse response)   
    {
        return (BlogsResponse) context.getBean(BLOGS_RESPONSE_BEAN, new Object[] {blogBussiness.getBlogs(userName)});
    }

    @LoginNeeded
    @PostMapping(value = "/api/add/blog", produces = "application/json")
    public BlogsResponse addAnBlog(@RequestBody Map<String, String> blogValue, HttpServletRequest request, HttpServletResponse response)
    {
        String sessionId = request.getSession().getId();
        String userName = loginBussiness.getUserName(sessionId);
        String title = blogValue.get("title");
        String content = blogValue.get("content");
        String imgHead = blogValue.get("imgHead");
        String img = blogValue.get("img");
        WebBlog blog = blogBussiness.addBlog(userName, title, content, imgHead, img);
        List<WebBlog> blogs = new ArrayList<>();
        blogs.add(blog);
        return (BlogsResponse) context.getBean(BLOGS_RESPONSE_BEAN, new Object[] {blogs});
    }

    @LoginNeeded
    @PutMapping(value = "api/add/likes/{blogId}", produces = "application/json")
    public CommonResponse addAnLikes(@PathVariable("blogId") int blogId, HttpServletRequest request, HttpServletResponse response)
    {
        blogBussiness.addAnLikes(blogId);
        return context.getBean("likesResponse", CommonResponse.class);
    }
}
