package cn.ablocker.FoodBlog.bussiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ablocker.FoodBlog.dao.CommentDAO;
import cn.ablocker.FoodBlog.entity.Comment;

public class CommentBussiness
{
    @Autowired
    private CommentDAO commentDAO;

    // 添加一条评论
    public Comment addComment(int blogId, String content)
    {
        Comment comment = new Comment();
        comment.setContent(content);
        if (commentDAO.addAnComment(blogId, comment) == 1)
            return comment;
        else
            return null;
    }

    // 查找博客的所有评论
    public List<Comment> getComments(int blogId)
    {
        return commentDAO.findBlogComments(blogId);
    }
}
