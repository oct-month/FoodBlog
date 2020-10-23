package cn.ablocker.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ablocker.demo.bussiness.BlogUserBussiness;
import cn.ablocker.demo.entity.BlogUser;

@RestController
public class LoginController
{
	@Autowired
	private BlogUserBussiness blogUserBussiness;
	
	@PostMapping("/login")
	public BlogUser login(@RequestParam("username") String userName, @RequestParam("password") String passWord, HttpServletResponse response, HttpServletRequest request)
	{
		BlogUser blogUser = blogUserBussiness.login(userName, passWord);
		if (blogUser != null)
		{
			String sessionId = request.getSession().getId();
			// TODO 通过 redis 把 sessionId 和 userName 关联
		}
		return blogUser;
	}
	
	@PostMapping("/register")
	public boolean register(@RequestParam("username") String userName, @RequestParam("password") String passWord, @RequestParam("email") String email)
	{
		BlogUser blogUser = new BlogUser(userName, passWord, email);
		return blogUserBussiness.register(blogUser);
	}
	
}
