package cn.ablocker.FoodBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ablocker.FoodBlog.bussiness.LoginBussiness;
import cn.ablocker.FoodBlog.entity.BlogUser;
import cn.ablocker.FoodBlog.response.RegisterResponse;

@RestController
public class RegisterController
{
	@Autowired
	private ApplicationContext context;
    @Autowired
    private LoginBussiness loginBussiness;
    
	@GetMapping("/register")
	public ModelAndView register()
	{
		return new ModelAndView("register.html");
	}
	
	@PostMapping(value = "/api/register", produces = "application/json")
	public RegisterResponse register(@RequestBody BlogUser blogUser)
	{
		if (loginBussiness.register(blogUser))
			return (RegisterResponse) context.getBean("registerSuccessResponse", new Object[] {blogUser.getUserName()});
		else
			return (RegisterResponse) context.getBean("registerFailResponse", new Object[] {blogUser.getUserName()});
	}
}
