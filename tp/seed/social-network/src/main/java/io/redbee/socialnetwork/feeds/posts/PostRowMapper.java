package io.redbee.socialnetwork.feeds.posts;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Post(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("content"),
                rs.getString("status"),
                rs.getTimestamp("creation_date").toLocalDateTime(),
                rs.getString("creation_user"),
                rs.getTimestamp("modification_date").toLocalDateTime(),
                rs.getString("modification_user")
        );
    }
}
