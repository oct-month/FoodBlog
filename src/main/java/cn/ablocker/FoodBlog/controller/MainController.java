package cn.ablocker.FoodBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ablocker.FoodBlog.annotation.LoginNeeded;
import cn.ablocker.FoodBlog.bussiness.LoginBussiness;

@RestController
public class MainController
{
	@Autowired
	private LoginBussiness loginBussiness;

	// 登录页面
	@GetMapping("/login")
	public ModelAndView login()
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("login.html");
		return view;
	}

	// 注册页面
	@GetMapping("/register")
	public ModelAndView register()
	{
		ModelAndView view = new ModelAndView();
		view.setViewName("register.html");
		return view;
	}

	// 主页面
	@LoginNeeded
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView();
		String sessionId = request.getSession().getId();
		view.addObject("userName", loginBussiness.getUserName(sessionId));
		view.setViewName("index.html");
		return view;
	}

	// 用户blog页面
	@LoginNeeded
	@GetMapping("/blog/{username}")
	public ModelAndView userBlogs(@PathVariable("username") String userName, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView();
		String sessionId = request.getSession().getId();
		view.addObject("specialUser", userName);							// 对方的用户名
		view.addObject("userName", loginBussiness.getUserName(sessionId));	// 自己的用户名
		view.setViewName("userblog.html");
		return view;
	}

	// 发布blog页面
	@LoginNeeded
	@GetMapping("/add/blog")
	public ModelAndView addBlog(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView();
		String sessionId = request.getSession().getId();
		view.addObject("userName", loginBussiness.getUserName(sessionId));
		view.setViewName("addblog.html");
		return view;
	}
}
