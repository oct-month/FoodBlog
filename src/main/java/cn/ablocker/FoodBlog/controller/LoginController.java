package cn.ablocker.FoodBlog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ablocker.FoodBlog.annotation.UnLoginNeeded;
import cn.ablocker.FoodBlog.bussiness.BlogUserBussiness;
import cn.ablocker.FoodBlog.entity.BlogUser;
import cn.ablocker.FoodBlog.response.LoginResponse;

@RestController
public class LoginController
{
	@Autowired
	private ApplicationContext context;
	@Autowired
	private BlogUserBussiness blogUserBussiness;
	
	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("login.html");
	}
	
	@UnLoginNeeded
	@PostMapping("/login")
	public LoginResponse login(@RequestParam("username") String userName, @RequestParam("password") String passWord, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String sessionId = request.getSession().getId();
		BlogUser blogUser = blogUserBussiness.login(userName, passWord, sessionId);
		if (blogUser != null)
			return context.getBean("loginSuccessResponse", LoginResponse.class);
		else
			return context.getBean("loginFailResponse", LoginResponse.class);
	}
}
