package cn.ablocker.FoodBlog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ablocker.FoodBlog.annotation.LoginNeeded;
import cn.ablocker.FoodBlog.bussiness.LoginBussiness;

@RestController
public class MainController
{
	@Autowired
	private LoginBussiness loginBussiness;

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

	@LoginNeeded
	@GetMapping("/blog/{username}")
	public ModelAndView userBlogs(@PathVariable("username") String userName, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView view = new ModelAndView();
		String sessionId = request.getSession().getId();
		view.addObject("specialUser", userName);
		view.addObject("userName", loginBussiness.getUserName(sessionId));
		view.setViewName("userblog.html");
		return view;
	}

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
