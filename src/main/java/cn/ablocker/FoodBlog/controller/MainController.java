package cn.ablocker.FoodBlog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.ablocker.FoodBlog.annotation.LoginNeeded;

@RestController
public class MainController
{	
	@LoginNeeded
	@GetMapping("/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		return new ModelAndView("index.html");
	}
}
