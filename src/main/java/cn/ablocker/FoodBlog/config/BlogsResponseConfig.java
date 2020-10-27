package cn.ablocker.FoodBlog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.entity.WebBlog;
import cn.ablocker.FoodBlog.response.BlogsResponse;

@Configuration
public class BlogsResponseConfig
{
    // 查询博客的响应
    @Bean
    @Scope("prototype")
    BlogsResponse blogsResponse(List<WebBlog> blogs)
    {
        BlogsResponse response = new BlogsResponse();
        response.setStatus(200);
        response.setBlogs(blogs);
        return response;
    }
}
