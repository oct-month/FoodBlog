package cn.ablocker.FoodBlog.bussiness;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import cn.ablocker.FoodBlog.dao.BlogSessionDAO;
import cn.ablocker.FoodBlog.dao.BlogUserDAO;
import cn.ablocker.FoodBlog.entity.BlogUser;

@Component
public class BlogUserBussiness
{
	@Autowired
	private BlogUserDAO blogUserDAO;
	@Autowired
	private BlogSessionDAO blogSessionDAO;
	
	public BlogUser login(String userName, String passWord, String sessionId)
	{
		BlogUser blogUser = blogUserDAO.findAnUser(userName, passWord);
		if (blogUser != null)
		{
			blogSessionDAO.set(sessionId, userName, 24, TimeUnit.HOURS);
		}
		return blogUser;
	}
	
	public void offLine(String sessionId)
	{
		blogSessionDAO.remove(sessionId);
	}
	
	public boolean register(BlogUser blogUser)
	{
		if (blogUserDAO.addAnUser(blogUser) == 1)
			return true;
		else
			return false;
	}
	
	public boolean isLogin(String sessionId)
	{
		String userName = blogSessionDAO.get(sessionId);
		if (!StringUtils.isEmpty(userName))
			return true;
		else
			return false;
	}
}
