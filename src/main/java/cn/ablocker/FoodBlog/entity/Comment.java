package cn.ablocker.FoodBlog.entity;

public class Comment
{
    private int id;
    private String content;     // 评论内容
    private int blogId;         // 博客Id（关联属性）

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getBlogId()
    {
        return blogId;
    }

    public void setBlogId(int blogId)
    {
        this.blogId = blogId;
    }
}
