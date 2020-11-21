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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import cn.ablocker.FoodBlog.bussiness.LoginBussiness;
import cn.ablocker.FoodBlog.controller.MainController;
import cn.ablocker.FoodBlog.response.BaseResponse;

@Component
@Aspect
public class LoginAspect
{
	@Autowired
	private LoginBussiness loginBussiness;
	@Autowired
	private ApplicationContext context;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("@annotation(cn.ablocker.FoodBlog.annotation.LoginNeeded) && args(.., request, response)")
	public void loginNeeded(HttpServletRequest request, HttpServletResponse response) {}
	
	@Pointcut("@annotation(cn.ablocker.FoodBlog.annotation.UnLogin) && args(.., request, response)")
	public void unLogin(HttpServletRequest request, HttpServletResponse response) {}
	
	// 没有登录的用户返回未登录提示
	@Around("loginNeeded(request, response)")
	public Object checkLoginStatus(ProceedingJoinPoint jp, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String sessionId = request.getSession().getId();
		if (!loginBussiness.isLogin(sessionId))
		{
			// 如果是页面请求，发送重定向
			if (jp.getTarget().getClass().equals(MainController.class))
			{
				response.sendRedirect("/login");
				return null;
			}
			// 否则返回登录提示
			try {
				String typeName = jp.getSignature().toLongString().split(" +")[1];
				Class<?> clazz = Class.forName(typeName);
				BaseResponse resp = (BaseResponse) clazz.getDeclaredConstructor().newInstance();
				BaseResponse resp2 = context.getBean("loginNeededResponse", BaseResponse.class);
				resp.setStatus(resp2.getStatus());;
				resp.setSuccess(resp2.isSuccess());
				resp.setInfo(resp2.getInfo());
				return resp;
			}
			catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
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
