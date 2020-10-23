package cn.ablocker.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cn.ablocker.demo.entity.BlogUser;

@Component
public class BlogUserDAO
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int addAnUser(BlogUser blogUser)
	{
		String sql = "insert into BlogUser(user_name, pass_word, email) values(?, ?, ?)";
		return jdbcTemplate.update(sql, blogUser.getUserName(), blogUser.getPassWord(), blogUser.getEmail());
	}
	
	public BlogUser findAnUser(String userName, String passWord)
	{
		String sql = "select * from BlogUser where user_name=? and pass_word=?";
		RowMapper<BlogUser> rowMapper = new BeanPropertyRowMapper<BlogUser>(BlogUser.class);
		try {
			return jdbcTemplate.queryForObject(sql, rowMapper, userName, passWord);
		}
		catch (DataAccessException e) {
			return null;
		}
	}
}
