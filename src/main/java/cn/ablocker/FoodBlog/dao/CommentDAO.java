package cn.ablocker.FoodBlog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.ablocker.FoodBlog.entity.Comment;

@Component
public class CommentDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 添加一条评论
    @Transactional
    public int addAnComment(int blogId, Comment comment)
    {
        String sql1 = "insert into Comment(content) values(?)";
        String sql2 = "insert into WebBlog_Comment(blog_id, comment_id) values(?, LAST_INSERT_ID())";
        int result = jdbcTemplate.update(sql1, comment.getContent());
        if (result == 1)
            result = jdbcTemplate.update(sql2, blogId);
        return result;
    }

    // 查询博客的所有评论
    public List<Comment> findBlogComments(int blogId)
    {
        String sql = "select * from Comment where id in (select comment_id from WebBlog_Comment where blog_id=?)";
        RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);
        return jdbcTemplate.query(sql, rowMapper, blogId);
    }
}
