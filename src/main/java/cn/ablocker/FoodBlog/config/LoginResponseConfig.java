package cn.ablocker.FoodBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.response.LoginResponse;

@Configuration
public class LoginResponseConfig
{
    // 登录成功的响应
    @Bean
    @Scope("prototype")
    public LoginResponse loginSuccessResponse()
    {
        LoginResponse response = new LoginResponse();
        response.setStatus(201);
        response.setSuccess(true);
        return response;
    }

    // 登陆失败的响应
    @Bean
    @Scope("prototype")
    public LoginResponse loginFailResponse()
    {
        LoginResponse response = new LoginResponse();
        response.setStatus(403);
        response.setSuccess(false);
        return response;
    }
}
