package io.redbee.socialnetwork.feeds.postLikes;

import io.redbee.socialnetwork.shared.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostLikeDao {
    private final NamedParameterJdbcTemplate template;

    public PostLikeDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PostLikeDao.class);

    private static final String getQuery = "SELECT " +
            "post_id, " +
            "user_id, " +
            "FROM post_likes";

    private static final String insertQuery = "" +
            "INSERT INTO post_likes (post_id, user_id) " +
            "VALUES (:post_id, :user_id,) ";

    private static final String deleteQuery = "" +
            "DELETE FROM post_likes " +
            "WHERE post_id = :post_id AND user_id = :user_id ";

    public void save(PostLike postLike) {
        try {
            template.update(insertQuery, postLikeToParamSource(postLike));
            LOGGER.info("save: like from user {} for post {}", postLike.getUserId(), postLike.getPostId());
        } catch (Exception e) {
            LOGGER.info("save: error {} saving like from user {} for post {}", e.getMessage(), postLike.getUserId(), postLike.getPostId());
            throw new RepositoryException();
        }
    }

    public void delete(PostLike postLike) {
        try {
            template.update(deleteQuery, postLikeToParamSource(postLike));
            LOGGER.info("delete: like from user {} for post {}", postLike.getUserId(), postLike.getPostId());
        } catch (Exception e) {
            LOGGER.info("delete: error {} deleting like from user {} for post {}", e.getMessage(), postLike.getUserId(), postLike.getPostId());
            throw new RepositoryException();
        }
    }

    public List<PostLike> get() {
        try {
            List<PostLike> result = template.query(getQuery, new PostLikeRowMapper());
            LOGGER.info("get: likes found {}", result);
            return result;
        } catch (Exception e) {
            LOGGER.info("get: error {} searching likes", e.getMessage());
            throw new RepositoryException();
        }
    }

    private MapSqlParameterSource postLikeToParamSource(PostLike postLike) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("post_id", postLike.getPostId());
        params.addValue("user_id", postLike.getUserId());

        return params;
    }
}
