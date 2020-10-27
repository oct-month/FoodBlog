package cn.ablocker.FoodBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/api/register")
	public RegisterResponse register(@RequestParam("username") String userName, @RequestParam("password") String passWord, @RequestParam("email") String email, HttpServletRequest request, HttpServletResponse response)
	{
		BlogUser blogUser = new BlogUser(userName, passWord, email);
		if (loginBussiness.register(blogUser))
			return (RegisterResponse) context.getBean("registerSuccessResponse", new Object[] {userName});
		else
			return (RegisterResponse) context.getBean("registerFailResponse", new Object[] {userName});
	}
}
