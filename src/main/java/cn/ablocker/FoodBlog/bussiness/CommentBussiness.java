package cn.ablocker.FoodBlog.bussiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ablocker.FoodBlog.dao.CommentDAO;
import cn.ablocker.FoodBlog.entity.Comment;

@Component
public class CommentBussiness
{
    @Autowired
    private CommentDAO commentDAO;

    // 添加一条评论，并返回这条评论
    public Comment addComment(int blogId, String content)
    {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBlogId(blogId);
        int commentId = commentDAO.addAnComment(comment);
        if (commentId != 0)
            return commentDAO.findAnComment(commentId);
        else
            return null;
    }

    // 查找博客的所有评论
    public List<Comment> getComments(int blogId)
    {
        return commentDAO.findBlogComments(blogId);
    }
}
