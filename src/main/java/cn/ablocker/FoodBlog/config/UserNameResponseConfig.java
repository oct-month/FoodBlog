package cn.ablocker.FoodBlog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cn.ablocker.FoodBlog.response.UserNameResponse;

@Configuration
public class UserNameResponseConfig
{
    // 查询用户名的响应
    @Bean
    @Scope("prototype")
    public UserNameResponse getUserNameSuccessResponse(String userName)
    {
        UserNameResponse response = new UserNameResponse();
        response.setStatus(200);
        response.setSuccess(true);
        response.setUserName(userName);
        return response;
    }
}
