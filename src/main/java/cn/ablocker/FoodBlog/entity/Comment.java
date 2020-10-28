package cn.ablocker.FoodBlog.entity;

public class Comment
{
    private int id;
    private String content;     // 评论内容

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
}
