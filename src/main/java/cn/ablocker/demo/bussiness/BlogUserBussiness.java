package cn.ablocker.demo.bussiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ablocker.demo.dao.BlogUserDAO;
import cn.ablocker.demo.entity.BlogUser;

@Component
public class BlogUserBussiness
{
	@Autowired
	private BlogUserDAO blogUserDAO;
	
	public BlogUser login(String userName, String passWord)
	{
		BlogUser blogUser = blogUserDAO.findAnUser(userName, passWord);
		return blogUser;
	}
	
	public boolean register(BlogUser blogUser)
	{
		if (blogUserDAO.addAnUser(blogUser) == 1)
			return true;
		else
			return false;
	}
}
