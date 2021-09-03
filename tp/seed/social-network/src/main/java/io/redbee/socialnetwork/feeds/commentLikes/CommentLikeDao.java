package io.redbee.socialnetwork.feeds.commentLikes;

import io.redbee.socialnetwork.shared.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentLikeDao {
    private final NamedParameterJdbcTemplate template;

    public CommentLikeDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentLikeDao.class);

    private static final String getQuery = "SELECT " +
            "comment_id, " +
            "user_id, " +
            "FROM comment_likes";

    private static final String insertQuery = "" +
            "INSERT INTO comment_like (comment_id, user_id) " +
            "VALUES (:comment_id, :user_id,) ";

    private static final String deleteQuery = "" +
            "DELETE FROM comment_likes " +
            "WHERE post_id = :comment_id AND user_id = :user_id ";

    public void save(CommentLike commentLike) {
        try {
            template.update(insertQuery, commentLikeToParamSource(commentLike));
            LOGGER.info("save: like from user {} for comment {}", commentLike.getUserId(), commentLike.getCommentId());
        } catch (Exception e) {
            LOGGER.info("save: error {} saving like from user {} for comment {}", e.getMessage(), commentLike.getUserId(), commentLike.getCommentId());
        }
    }

    public void delete(CommentLike commentLike) {
        try {
            template.update(deleteQuery, commentLikeToParamSource(commentLike));
            LOGGER.info("delete: like from user {} for comment {}", commentLike.getUserId(), commentLike.getCommentId());
        } catch (Exception e) {
            LOGGER.info("delete: error {} deleting like from user {} for comment {}", e.getMessage(), commentLike.getUserId(), commentLike.getCommentId());
            throw new RepositoryException();
        }
    }

    public List<CommentLike> get() {
        try {
            List<CommentLike> result = template.query(getQuery, new CommentLikeRowMapper());
            LOGGER.info("get: likes found {}", result);
            return result;
        } catch (Exception e) {
            LOGGER.info("get: error {} searching likes", e.getMessage());
            throw new RepositoryException();
        }
    }

    private MapSqlParameterSource commentLikeToParamSource(CommentLike commentLike) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("comment_id", commentLike.getCommentId());
        params.addValue("user_id", commentLike.getUserId());

        return params;
    }
}
