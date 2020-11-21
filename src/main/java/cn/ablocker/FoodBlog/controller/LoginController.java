package cn.ablocker.FoodBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ablocker.FoodBlog.annotation.LoginNeeded;
import cn.ablocker.FoodBlog.annotation.UnLogin;
import cn.ablocker.FoodBlog.bussiness.LoginBussiness;
import cn.ablocker.FoodBlog.entity.BlogUser;
import cn.ablocker.FoodBlog.response.BaseResponse;
import cn.ablocker.FoodBlog.response.UserNameResponse;

@RestController()
@RequestMapping("/api/login")
public class LoginController
{
	@Autowired
	private ApplicationContext context;
	@Autowired
	private LoginBussiness loginBussiness;
	
	// 登录api
	@UnLogin
	@PostMapping(value = "/dologin", produces = "application/json")
	public BaseResponse login(@RequestBody BlogUser blogUser, HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = request.getSession().getId();
		blogUser = loginBussiness.login(blogUser.getUserName(), blogUser.getPassWord(), sessionId);
		if (blogUser != null)
			return context.getBean("loginSuccessResponse", BaseResponse.class);
		else
			return context.getBean("loginFailResponse", BaseResponse.class);
	}

	// 注册api
	@PostMapping(value = "/register", produces = "application/json")
	public UserNameResponse register(@RequestBody BlogUser blogUser)
	{
		if (loginBussiness.register(blogUser))
			return (UserNameResponse) context.getBean("registerSuccessResponse", new Object[] {blogUser.getUserName()});
		else
			return (UserNameResponse) context.getBean("registerFailResponse", new Object[] {blogUser.getUserName()});
	}

	// 下线api
	@LoginNeeded
	@DeleteMapping("/unlogin")
	public BaseResponse unLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = request.getSession().getId();
		loginBussiness.offLine(sessionId);
		return context.getBean("unLoginResponse", BaseResponse.class);
	}

	// 查询登录状态
	@GetMapping("/islogin")
	public BaseResponse isLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = request.getSession().getId();
		boolean flag = loginBussiness.isLogin(sessionId);
		if (flag)
			return context.getBean("loginSuccessResponse", BaseResponse.class);
		else
			return context.getBean("loginFailResponse", BaseResponse.class);
	}

	// 查询登录用户名
	@LoginNeeded
	@GetMapping("/username")
	public UserNameResponse getUserName(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = request.getSession().getId();
		String userName = loginBussiness.getUserName(sessionId);
		return (UserNameResponse) context.getBean("getUserNameSuccessResponse", new Object[] {userName});
	}
}
