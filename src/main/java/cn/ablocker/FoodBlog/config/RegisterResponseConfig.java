package cn.ablocker.FoodBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.response.RegisterResponse;

@Configuration
public class RegisterResponseConfig
{
    // 注册成功的响应
    @Bean
    @Scope("prototype")
    public RegisterResponse registerSuccessResponse(String userName)
    {
        RegisterResponse response = new RegisterResponse();
        response.setStatus(201);
        response.setSuccess(true);
        response.setUsername(userName);
        return response;
    }

    // 注册失败的响应
    @Bean
    @Scope("prototype")
    public RegisterResponse registerFailResponse(String userName)
    {
        RegisterResponse response = new RegisterResponse();
        response.setStatus(403);
        response.setSuccess(false);
        response.setUsername(userName);
        return response;
    }
}
