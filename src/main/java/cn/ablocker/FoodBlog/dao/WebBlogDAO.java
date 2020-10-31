package cn.ablocker.FoodBlog.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RowMapper<WebBlog> rowMapper = new BeanPropertyRowMapper<>(WebBlog.class);

    // 增加一篇博客，返回博客的Id
    @Transactional
    public int addAnBlog(WebBlog blog)
    {
        String sql1 = "insert into WebBlog(publish_time, title, content, img) values(current_timestamp(), ?, ?, ?)";
        String sql2 = "insert into BlogUser_WebBlog(user_name, blog_id) values(?, ?)";
        jdbcTemplate.update(sql1, blog.getTitle(), blog.getContent(), blog.getImg());
        int blogId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        jdbcTemplate.update(sql2, blog.getUserName(), blogId);
        return blogId;
    }

    // 根据博客Id查找博客
    public WebBlog findAnBlog(int blogId)
    {
        String sql = "select id, Publish_time, title, content, img, likes, user_name from WebBlog left join BlogUser_WebBlog on id=blog_id where id=?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, blogId);
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    // 更新博客的 likes
    @Transactional
    public void updataLikes(int likes, int blogId)
    {
        String sql = "update WebBlog set likes=? where id=?";
        jdbcTemplate.update(sql, likes, blogId);
    }

    // 查找所有博客
    public List<WebBlog> findAllBlogs()
    {
        String sql = "select id, Publish_time, title, content, img, likes, user_name from WebBlog left join BlogUser_WebBlog on id=blog_id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // 查找用户的所有博客
    public List<WebBlog> findUserBlogs(String userName)
    {
        String sql = "select id, Publish_time, title, content, img, likes, ? as user_name from WebBlog where id in (select blog_id from BlogUser_WebBlog where user_name=?);";
        return jdbcTemplate.query(sql, rowMapper, userName, userName);
    }
}
