package cn.ablocker.FoodBlog.response;

public class CommonResponse
{
    private int status;         // 请求状态
    private boolean success;    // 是否登陆成功

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
}
