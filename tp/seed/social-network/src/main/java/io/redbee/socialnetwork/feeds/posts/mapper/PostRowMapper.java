package io.redbee.socialnetwork.feeds.posts.mapper;

import io.redbee.socialnetwork.feeds.posts.model.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static io.redbee.socialnetwork.shared.util.LocalDateTimeUtils.formatDate;

public class PostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Post(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getString("content"),
                rs.getString("status"),
                formatDate(rs.getTimestamp("creation_date")),
                rs.getString("creation_user"),
                formatDate(rs.getTimestamp("modification_date")),
                rs.getString("modification_user")
        );
    }
}
