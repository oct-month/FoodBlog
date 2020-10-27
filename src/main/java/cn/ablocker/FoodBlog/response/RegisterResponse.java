package cn.ablocker.FoodBlog.response;

public class RegisterResponse
{
    private int status;         // 请求状态
    private boolean success;    // 注册是否成功
    private String username;    // 注册使用的用户名

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
