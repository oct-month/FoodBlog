package cn.ablocker.FoodBlog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cn.ablocker.FoodBlog.entity.WebBlog;

@Component
public class WebBlogDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addAnBlog(WebBlog blog)
    {
        String sql = "insert into WebBlog(user_name, publish_time, content, img) values(?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql, blog.getUserName(), blog.getPublishTime(), blog.getContent(), blog.getImg());
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<WebBlog> findAllBlogs()
    {
        String sql = "select * from WebBlog";
        RowMapper<WebBlog> rowMapper = new BeanPropertyRowMapper<>();
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<WebBlog> findUserBlogs(String userName)
    {
        String sql = "select * from WebBlog where user_name=?";
        RowMapper<WebBlog> rowMapper = new BeanPropertyRowMapper<>();
        return jdbcTemplate.query(sql, rowMapper, userName);
    }
}
