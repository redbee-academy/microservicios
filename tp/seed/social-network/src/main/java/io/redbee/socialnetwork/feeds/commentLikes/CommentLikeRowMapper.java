package io.redbee.socialnetwork.feeds.commentLikes;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentLikeRowMapper implements RowMapper<CommentLike> {
    @Override
    public CommentLike mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CommentLike(
                rs.getInt("comment_id"),
                rs.getInt("user_id")
        );
    }
}
