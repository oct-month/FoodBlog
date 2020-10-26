package cn.ablocker.FoodBlog.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ablocker.FoodBlog.annotation.UnLoginNeeded;
import cn.ablocker.FoodBlog.bussiness.BlogUserBussiness;
import cn.ablocker.FoodBlog.dao.BlogSessionDAO;
import cn.ablocker.FoodBlog.entity.BlogUser;

@RestController
public class LoginController
{
	@Autowired
	private BlogUserBussiness blogUserBussiness;
	
	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response)
	{
		return "请登录";
	}
	
	@UnLoginNeeded
	@PostMapping("/login")
	public String login(@RequestParam("username") String userName, @RequestParam("password") String passWord, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String sessionId = request.getSession().getId();
		BlogUser blogUser = blogUserBussiness.login(userName, passWord, sessionId);
		if (blogUser != null)
			response.sendRedirect("/");
		return "请登录";
	}
	
	@PostMapping("/register")
	public boolean register(@RequestParam("username") String userName, @RequestParam("password") String passWord, @RequestParam("email") String email)
	{
		BlogUser blogUser = new BlogUser(userName, passWord, email);
		return blogUserBussiness.register(blogUser);
	}
}
