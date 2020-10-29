package cn.ablocker.FoodBlog.aspects;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ablocker.FoodBlog.bussiness.LoginBussiness;

@Component
@Aspect
public class LoginAspect
{
	@Autowired
	private LoginBussiness loginBussiness;
	
	@Pointcut("@annotation(cn.ablocker.FoodBlog.annotation.LoginNeeded) && args(request, response, ..)")
	public void loginNeeded(HttpServletRequest request, HttpServletResponse response) {}
	
	@Pointcut("@annotation(cn.ablocker.FoodBlog.annotation.UnLoginNeeded) && args(request, response, ..)")
	public void unLoginNeeded(HttpServletRequest request, HttpServletResponse response) {}
	
	// 没有登录的用户重定向到登录页面
	@Before("loginNeeded(request, response)")
	public void checkLoginStatus(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String sessionId = request.getSession().getId();
		if (!loginBussiness.isLogin(sessionId))
			response.sendRedirect("/login");
	}
	
	// 如果用户已登录，则下线
	@Before("unLoginNeeded(request, response)")
	public void checkUnLoginStatus(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = request.getSession().getId();
		if (loginBussiness.isLogin(sessionId))
			loginBussiness.offLine(sessionId);
	}
}
