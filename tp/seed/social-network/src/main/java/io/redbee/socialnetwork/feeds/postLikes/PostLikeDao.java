package io.redbee.socialnetwork.feeds.postLikes;

import io.redbee.socialnetwork.shared.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PostLikeDao {
    private final NamedParameterJdbcTemplate template;

    public PostLikeDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(PostLikeDao.class);

    private static final String getQuery = "SELECT " +
            "post_id, " +
            "user_id " +
            "FROM post_likes";

    private static final String insertQuery = "" +
            "INSERT INTO post_likes (post_id, user_id) " +
            "VALUES (:post_id, :user_id) ";

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

    public List<PostLike> getAllBy(Integer postId) {
        try {
            List<PostLike> result =
                    template.query(
                            getQuery + " WHERE post_id = :post_id",
                            new MapSqlParameterSource(Map.of("post_id", postId)),
                            new PostLikeRowMapper()
                    );
            LOGGER.info("get: likes found {}", result);
            return result;
        } catch (Exception e) {
            LOGGER.info("get: error {} searching likes", e.getMessage());
            throw new RepositoryException();
        }
    }

    public Optional<PostLike> getBy(PostLike like) {
        try {
            Optional<PostLike> result = Optional.ofNullable(
                    template.queryForObject(
                            getQuery + " WHERE user_id = :user_id AND post_id = :post_id",
                            postLikeToParamSource(like),
                            new PostLikeRowMapper()
                    )
            );
            LOGGER.info("getBy: like found {}", result);
            return result;
        } catch (EmptyResultDataAccessException e) {
            LOGGER.info("getBy: like made by {} in post {} not found", like.getUserId(), like.getPostId());
            return Optional.empty();
        } catch (Exception e) {
            LOGGER.info("getBy: error {} searching like", e.getMessage());
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
