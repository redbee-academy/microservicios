package io.redbee.socialnetwork.feeds.comments;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Comment(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("post_id"),
                rs.getString("content"),
                rs.getString("status"),
                rs.getTimestamp("creation_date").toLocalDateTime(),
                rs.getString("creation_user"),
                rs.getTimestamp("modification_date").toLocalDateTime(),
                rs.getString("modification_user")
        );
    }
}
