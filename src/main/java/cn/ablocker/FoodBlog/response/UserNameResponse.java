package cn.ablocker.FoodBlog.response;

public class UserNameResponse extends BaseResponse
{
    private String userName;    // 用户名

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
