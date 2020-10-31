package cn.ablocker.FoodBlog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.entity.WebBlog;
import cn.ablocker.FoodBlog.response.BlogsResponse;
import cn.ablocker.FoodBlog.response.CommonResponse;

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

    // 点赞成功的响应
    @Bean
    @Scope("prototype")
    CommonResponse likesResponse()
    {
        CommonResponse response = new CommonResponse();
        response.setStatus(201);
        response.setSuccess(true);
        return response;
    }

    @Bean
    @Scope("prototype")
    CommonResponse addBlogSuccessResponse()
    {
        CommonResponse response = new CommonResponse();
        response.setStatus(201);
        response.setSuccess(true);
        return response;
    }

    @Bean
    @Scope("prototype")
    CommonResponse addBlogFailResponse()
    {
        CommonResponse response = new CommonResponse();
        response.setStatus(400);
        response.setSuccess(false);
        return response;
    }
}
