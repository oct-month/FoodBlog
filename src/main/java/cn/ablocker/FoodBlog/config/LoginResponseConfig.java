package cn.ablocker.FoodBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.response.CommonResponse;

@Configuration
public class LoginResponseConfig
{
    // 登录成功的响应
    @Bean
    @Scope("prototype")
    public CommonResponse loginSuccessResponse()
    {
        CommonResponse response = new CommonResponse();
        response.setStatus(201);
        response.setSuccess(true);
        return response;
    }

    // 登陆失败的响应
    @Bean
    @Scope("prototype")
    public CommonResponse loginFailResponse()
    {
        CommonResponse response = new CommonResponse();
        response.setStatus(403);
        response.setSuccess(false);
        return response;
    }

    // 注销的响应
    @Bean
    @Scope("prototype")
    public CommonResponse unLoginResponse()
    {
        CommonResponse response = new CommonResponse();
        response.setStatus(200);
        response.setSuccess(true);
        return response;
    }
}
