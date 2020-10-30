package cn.ablocker.FoodBlog.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.ablocker.FoodBlog.entity.BlogUser;

@Component
public class BlogUserDAO
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private RowMapper<BlogUser> rowMapper = new BeanPropertyRowMapper<>(BlogUser.class);
	
	// 增加一个用户，返回用户名
	@Transactional
	public String addAnUser(BlogUser blogUser)
	{
		String sql = "insert into BlogUser(user_name, pass_word, email) values(?, ?, ?)";
		try {
			jdbcTemplate.update(sql, blogUser.getUserName(), blogUser.getPassWord(), blogUser.getEmail());
			return blogUser.getUserName();
		}
		catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public BlogUser findAnUser(String userName, String passWord)
	{
		String sql = "select * from BlogUser where user_name=? and pass_word=?";
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper, userName, passWord);
		}
		catch (DataAccessException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
