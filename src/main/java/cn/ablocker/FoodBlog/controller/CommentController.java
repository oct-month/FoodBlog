package cn.ablocker.FoodBlog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ablocker.FoodBlog.annotation.LoginNeeded;
import cn.ablocker.FoodBlog.bussiness.CommentBussiness;
import cn.ablocker.FoodBlog.entity.Comment;
import cn.ablocker.FoodBlog.response.CommentResponse;

@CrossOrigin
@RestController
@RequestMapping("/api/comment")
public class CommentController
{
    @Autowired
    private ApplicationContext context;
    @Autowired
    private CommentBussiness commentBussiness;

    private static final String COMMENTS_RESPONSE_BEAN = "commentsResponse";

    // 返回blog的所有评论
    @LoginNeeded
    @GetMapping("/comments/{blogId}")
    public CommentResponse getBlogComments(@PathVariable("blogId") int blogId, HttpServletRequest request, HttpServletResponse response)
    {
        return (CommentResponse) context.getBean(COMMENTS_RESPONSE_BEAN, new Object[] {commentBussiness.getComments(blogId)});
    }

    // 增加blog的评论
    @LoginNeeded
    @PostMapping(value = "/add", produces = "application/json")
    public CommentResponse addBlogComment(@RequestBody Comment comment, HttpServletRequest request, HttpServletResponse response)
    {
        comment = commentBussiness.addComment(comment.getBlogId(), comment.getContent());
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        return (CommentResponse) context.getBean(COMMENTS_RESPONSE_BEAN, new Object[] {comments});
    }
}
