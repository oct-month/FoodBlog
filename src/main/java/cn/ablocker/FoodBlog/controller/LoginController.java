package cn.ablocker.FoodBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ablocker.FoodBlog.annotation.UnLoginNeeded;
import cn.ablocker.FoodBlog.bussiness.LoginBussiness;
import cn.ablocker.FoodBlog.entity.BlogUser;
import cn.ablocker.FoodBlog.response.CommonResponse;

@RestController
public class LoginController
{
	@Autowired
	private ApplicationContext context;
	@Autowired
	private LoginBussiness loginBussiness;
	
	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
	{
		return new ModelAndView("login.html");
	}

	@UnLoginNeeded
	@PostMapping(value = "/api/login", produces = "application/json")
	public CommonResponse login(@RequestBody BlogUser blogUser, HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = request.getSession().getId();
		blogUser = loginBussiness.login(blogUser.getUserName(), blogUser.getPassWord(), sessionId);
		if (blogUser != null)
			return context.getBean("loginSuccessResponse", CommonResponse.class);
		else
			return context.getBean("loginFailResponse", CommonResponse.class);
	}
}
