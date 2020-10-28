package cn.ablocker.FoodBlog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.ablocker.FoodBlog.entity.WebBlog;

@Component
public class WebBlogDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public int addAnBlog(String userName, WebBlog blog)
    {
        String sql1 = "insert into WebBlog(publish_time, title, content, img) values(?, ?, ?, ?, ?)";
        String sql2 = "insert into BlogUser_WebBlog(user_name, blog_id) values(?, ?)";
        String sql3 = "select LAST_INSERT_ID()";
        try {
            int result = jdbcTemplate.update(sql1, blog.getPublishTime(), blog.getTitle(), blog.getContent(), blog.getImg());
            int blogId = jdbcTemplate.queryForObject(sql3, Integer.class);
            if (result == 1)
            {
                result = jdbcTemplate.update(sql2, userName, blogId);
            }
            return result;
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<WebBlog> findAllBlogs()
    {
        String sql = "select * from WebBlog";
        RowMapper<WebBlog> rowMapper = new BeanPropertyRowMapper<>(WebBlog.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<WebBlog> findUserBlogs(String userName)
    {
        String sql = "select * from WebBlog where user_name=?";
        RowMapper<WebBlog> rowMapper = new BeanPropertyRowMapper<>(WebBlog.class);
        return jdbcTemplate.query(sql, rowMapper, userName);
    }
}
