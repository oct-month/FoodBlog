package cn.ablocker.FoodBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.response.BaseResponse;

@Configuration
public class LoginResponseConfig
{
    // 登录成功的响应
    @Bean
    @Scope("prototype")
    public BaseResponse loginSuccessResponse()
    {
        BaseResponse response = new BaseResponse();
        response.setStatus(201);
        response.setSuccess(true);
        return response;
    }

    // 登陆失败的响应
    @Bean
    @Scope("prototype")
    public BaseResponse loginFailResponse()
    {
        BaseResponse response = new BaseResponse();
        response.setStatus(403);
        response.setSuccess(false);
        return response;
    }

    // 注销的响应
    @Bean
    @Scope("prototype")
    public BaseResponse unLoginResponse()
    {
        BaseResponse response = new BaseResponse();
        response.setStatus(200);
        response.setSuccess(true);
        return response;
    }

    @Bean
    @Scope("prototype")
    public BaseResponse loginNeededResponse()
    {
        BaseResponse response = new BaseResponse();
        response.setStatus(401);
        response.setSuccess(false);
        response.setInfo("You should login first.");
        return response;
    }
}
