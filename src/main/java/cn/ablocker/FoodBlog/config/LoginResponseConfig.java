package cn.ablocker.FoodBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.response.LoginResponse;

@Configuration
public class LoginResponseConfig
{
    @Bean
    @Scope("prototype")
    public LoginResponse loginSuccessResponse()
    {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus(201);
        loginResponse.setSuccess(true);
        return loginResponse;
    }

    @Bean
    @Scope("prototype")
    public LoginResponse loginFailResponse()
    {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatus(403);
        loginResponse.setSuccess(false);
        return loginResponse;
    }
}
