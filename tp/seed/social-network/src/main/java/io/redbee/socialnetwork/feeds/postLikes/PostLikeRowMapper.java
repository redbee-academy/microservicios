package io.redbee.socialnetwork.feeds.postLikes;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostLikeRowMapper implements RowMapper<PostLike> {
    @Override
    public PostLike mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PostLike(
                rs.getInt("post_id"),
                rs.getInt("user_id")
        );
    }
}
