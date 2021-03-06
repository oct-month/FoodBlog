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

import cn.ablocker.FoodBlog.entity.Comment;

@Component
public class CommentDAO
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<>(Comment.class);

    // 添加一条评论，返回评论的Id
    @Transactional
    public int addAnComment(Comment comment)
    {
        String sql1 = "insert into Comment(content) values(?)";
        String sql2 = "insert into WebBlog_Comment(blog_id, comment_id) values(?, ?)";
        jdbcTemplate.update(sql1, comment.getContent());
        int commentId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        jdbcTemplate.update(sql2, comment.getBlogId(), commentId);
        return commentId;
    }

    // 根据Id查找评论
    public Comment findAnComment(int commentId)
    {
        String sql = "select id, content, blog_id from Comment left join WebBlog_Comment on id=comment_id where id=?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, commentId);
        }
        catch (DataAccessException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    // 查询博客的所有评论
    public List<Comment> findBlogComments(int blogId)
    {
        String sql = "select id, content, ? as blog_id from Comment where id in (select comment_id from WebBlog_Comment where blog_id=?) order by id desc";
        return jdbcTemplate.query(sql, rowMapper, blogId, blogId);
    }
}
