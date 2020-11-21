package cn.ablocker.FoodBlog.response;

import java.util.List;

import cn.ablocker.FoodBlog.entity.Comment;

public class CommentResponse extends BaseResponse
{
    private List<Comment> comments; // 评论

    public List<Comment> getComments()
    {
        return comments;
    }

    public void setComments(List<Comment> comments)
    {
        this.comments = comments;
    }
}
