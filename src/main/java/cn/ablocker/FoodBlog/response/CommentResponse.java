package cn.ablocker.FoodBlog.response;

import java.util.List;

import cn.ablocker.FoodBlog.entity.Comment;

public class CommentResponse
{
    private int status;             // 请求状态
    private List<Comment> comments; // 评论

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }
}
