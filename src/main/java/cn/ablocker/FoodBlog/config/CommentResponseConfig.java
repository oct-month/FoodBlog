package cn.ablocker.FoodBlog.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.entity.Comment;
import cn.ablocker.FoodBlog.response.CommentResponse;

@Configuration
public class CommentResponseConfig
{
    // 查询评论的响应
    @Bean
    @Scope("prototype")
    public CommentResponse commentsResponse(List<Comment> comments)
    {
        CommentResponse response = new CommentResponse();
        response.setStatus(200);
        response.setSuccess(true);
        response.setComments(comments);
        return response;
    }
}
