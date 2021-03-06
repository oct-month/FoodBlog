package cn.ablocker.FoodBlog.bussiness;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.ablocker.FoodBlog.dao.BlogSessionDAO;
import cn.ablocker.FoodBlog.dao.BlogUserDAO;
import cn.ablocker.FoodBlog.entity.BlogUser;

@Component
public class LoginBussiness
{
	@Autowired
	private BlogUserDAO blogUserDAO;
	@Autowired
	private BlogSessionDAO blogSessionDAO;

	// 登录
	public BlogUser login(String userName, String passWord, String sessionId)
	{
		BlogUser blogUser = blogUserDAO.findAnUser(userName, passWord);
		if (blogUser != null)
		{
			blogSessionDAO.set(sessionId, userName, 24, TimeUnit.HOURS);
		}
		return blogUser;
	}

	// 下线
	public void offLine(String sessionId)
	{
		blogSessionDAO.remove(sessionId);
	}

	// 注册
	public boolean register(BlogUser blogUser)
	{
		if (!StringUtils.isEmpty(blogUserDAO.addAnUser(blogUser)))
			return true;
		else
			return false;
	}

	// 获取用户名
	public String getUserName(String sessionId)
	{
		return blogSessionDAO.get(sessionId);
	}

	// 查询登录状态
	public boolean isLogin(String sessionId)
	{
		String userName = getUserName(sessionId);
		if (!StringUtils.isEmpty(userName))
			return true;
		else
			return false;
	}
}
