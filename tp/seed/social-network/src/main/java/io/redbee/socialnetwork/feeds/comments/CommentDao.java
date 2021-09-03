package io.redbee.socialnetwork.feeds.comments;

import io.redbee.socialnetwork.shared.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CommentDao {
    private final NamedParameterJdbcTemplate template;

    public CommentDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentDao.class);

    private static final String getQuery = "SELECT " +
            "id, " +
            "user_id, " +
            "post_id, " +
            "content, " +
            "status, " +
            "creation_date, " +
            "creation_user, " +
            "modification_date, " +
            "modification_user " +
            "FROM comments";

    private static final String insertQuery = "" +
            "INSERT INTO comments (user_id, post_id, content, status, creation_date, creation_user, modification_date, modification_user) " +
            "VALUES (:user_id, :post_id, :content, :status, :creation_date, :creation_user, :modification_date, :modification_user) ";

    private static final String updateQuery = "" +
            "UPDATE comments " +
            "SET content = :content, " +
            "    status             = :status, " +
            "    modification_date  = :modification_date, " +
            "    modification_user  = :modification_user " +
            "WHERE id = :id";

    public void save(Comment comment) {
        try {
            template.update(insertQuery, commentToParamSource(comment));
            LOGGER.info("save: comment from user {} saved", comment.getUserId());
        } catch (Exception e) {
            LOGGER.info("save: error {} saving comment from user {}", e.getMessage(), comment.getUserId());
        }
    }

    public void update(Comment comment) {
        try {
            template.update(updateQuery, commentToParamSource(comment));
            LOGGER.info("update: comment {} updated", comment.getId());
        } catch (Exception e) {
            LOGGER.info("update: error {} updating comment {}", e.getMessage(), comment.getId());
        }
    }

    public List<Comment> get() {
        try {
            List<Comment> result = template.query(getQuery, new CommentRowMapper());
            LOGGER.info("get: comment found: {}", result);
            return result;
        } catch (DataAccessException e) {
            LOGGER.info("get: comment {} searching posts", e.getMessage());
            throw new RepositoryException();
        }
    }

    public Optional<Comment> getById(Integer id) {
        try {
            Optional<Comment> result = Optional.ofNullable(
                    template.queryForObject(
                            getQuery + " WHERE id = :id",
                            Map.of("id", id),
                            new CommentRowMapper()
                    )
            );
            LOGGER.info("getById: comment found: {}", result);
            return result;
        } catch (ResourceAccessException e) {
            LOGGER.info("getById: comment with id {} not found", id);
            return Optional.empty();
        } catch (DataAccessException e) {
            LOGGER.info("getById: error {} searching comment with id: {}", e.getMessage(), id);
            throw new RepositoryException();
        }
    }


    private MapSqlParameterSource commentToParamSource(Comment comment) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", comment.getId());
        params.addValue("user_id", comment.getUserId());
        params.addValue("post_id", comment.getUserId());
        params.addValue("content", comment.getContent());
        params.addValue("status", comment.getStatus());
        params.addValue("creation_date", comment.getCreationDate());
        params.addValue("creation_user", comment.getCreationUser());
        params.addValue("modification_date", comment.getModificationDate());
        params.addValue("modification_user", comment.getModificationUser());

        return params;
    }
}
