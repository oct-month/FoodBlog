package cn.ablocker.FoodBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.response.UserNameResponse;

@Configuration
public class RegisterResponseConfig
{
    // 注册成功的响应
    @Bean
    @Scope("prototype")
    public UserNameResponse registerSuccessResponse(String userName)
    {
        UserNameResponse response = new UserNameResponse();
        response.setStatus(201);
        response.setSuccess(true);
        response.setUserName(userName);
        return response;
    }

    // 注册失败的响应
    @Bean
    @Scope("prototype")
    public UserNameResponse registerFailResponse(String userName)
    {
        UserNameResponse response = new UserNameResponse();
        response.setStatus(403);
        response.setSuccess(false);
        response.setUserName(userName);
        return response;
    }
}
