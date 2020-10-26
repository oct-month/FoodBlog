package cn.ablocker.FoodBlog.aspects;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ablocker.FoodBlog.bussiness.BlogUserBussiness;

@Component
@Aspect
public class LoginAspect
{
	@Autowired
	private BlogUserBussiness blogUserBussiness;
	
	@Pointcut("@annotation(cn.ablocker.FoodBlog.annotation.LoginNeeded) && args(request, response)")
	public void loginNeeded(HttpServletRequest request, HttpServletResponse response) {}
	
	@Pointcut("@annotation(cn.ablocker.FoodBlog.annotation.UnLoginNeeded) && args(request, response)")
	public void unLoginNeeded(HttpServletRequest request, HttpServletResponse response) {}
	
	
	@Before("loginNeeded(request, response)")
	public void checkLoginStatus(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String sessionId = request.getSession().getId();
		if (!blogUserBussiness.isLogin(sessionId))
			response.sendRedirect("/login");
	}
	
	@Before("unLoginNeeded(request, response)")
	public void checkUnLoginStatus(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = request.getSession().getId();
		if (blogUserBussiness.isLogin(sessionId))
			blogUserBussiness.offLine(sessionId);
	}
}
