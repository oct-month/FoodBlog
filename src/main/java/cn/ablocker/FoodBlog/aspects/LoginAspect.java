package cn.ablocker.FoodBlog.aspects;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ablocker.FoodBlog.bussiness.LoginBussiness;

@Component
@Aspect
public class LoginAspect
{
	@Autowired
	private LoginBussiness loginBussiness;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("@annotation(cn.ablocker.FoodBlog.annotation.LoginNeeded) && args(.., request, response)")
	public void loginNeeded(HttpServletRequest request, HttpServletResponse response) {}
	
	@Pointcut("@annotation(cn.ablocker.FoodBlog.annotation.UnLogin) && args(.., request, response)")
	public void unLogin(HttpServletRequest request, HttpServletResponse response) {}
	
	// 没有登录的用户重定向到登录页面
	@Around("loginNeeded(request, response)")
	public Object checkLoginStatus(ProceedingJoinPoint jp, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String sessionId = request.getSession().getId();
		if (!loginBussiness.isLogin(sessionId))
			response.sendRedirect("/login");
		else
		{
			try {
				return jp.proceed();
			}
			catch(Throwable e) {
				logger.error(e.getMessage(), e);
			}
		}
		return null;
	}

	// 如果用户已登录，则下线
	@Before("unLogin(request, response)")
	public void unLoginPre(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId = request.getSession().getId();
		if (loginBussiness.isLogin(sessionId))
			loginBussiness.offLine(sessionId);
	}
}
