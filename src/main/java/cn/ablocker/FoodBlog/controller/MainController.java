package cn.ablocker.FoodBlog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ablocker.FoodBlog.annotation.LoginNeeded;
import cn.ablocker.FoodBlog.bussiness.BlogUserBussiness;

@RestController
public class MainController
{	
	@LoginNeeded
	@GetMapping("/")
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		return "登陆成功";
	}
}
