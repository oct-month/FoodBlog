package cn.ablocker.FoodBlog.response;

public class BaseResponse
{
    private int status;         // 请求状态
    private boolean success;    // 是否成功
    private String info;        // 提示信息

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

    public String getInfo()
    {
        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }
}
