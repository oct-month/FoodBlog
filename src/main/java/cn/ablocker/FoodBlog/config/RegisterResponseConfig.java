package cn.ablocker.FoodBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.response.RegisterResponse;

@Configuration
public class RegisterResponseConfig
{
    @Bean
    @Scope("session")
    public RegisterResponse registerSuccessResponse(String userName)
    {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setStatus(201);
        registerResponse.setSuccess(true);
        registerResponse.setUsername(userName);
        return registerResponse;
    }

    @Bean
    @Scope("session")
    public RegisterResponse registerFailResponse(String userName)
    {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setStatus(403);
        registerResponse.setSuccess(false);
        registerResponse.setUsername(userName);
        return registerResponse;
    }
}
