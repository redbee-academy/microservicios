package io.redbee.socialnetwork.feeds.postLikes;

import io.redbee.socialnetwork.feeds.postLikes.exception.PostLikeAlreadyExistsException;
import io.redbee.socialnetwork.feeds.postLikes.exception.PostLikeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostLikeService {
    private final PostLikeDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostLikeService.class);

    public PostLikeService(PostLikeDao dao) {
        this.dao = dao;
    }

    public List<PostLike> getAll(Integer userId, Integer postId) {
        List<PostLike> likes = dao.getAllBy(postId);

        LOGGER.info("getAll: likes found: {}", likes);
        return likes;
    }

    public PostLike like(PostLike like) {

        if (exists(like)) {
            LOGGER.info("like: user {} already liked post {}", like.getUserId(), like.getPostId());
            throw new PostLikeAlreadyExistsException(like.getUserId(), like.getPostId());
        }

        dao.save(like);

        LOGGER.info("like: post {} liked by user {}", like.getPostId(), like.getUserId());
        return like;
    }

    public PostLike unlike(PostLike like) {
        PostLike likeFound = get(like).orElseThrow(PostLikeNotFoundException::new);

        dao.delete(likeFound);

        LOGGER.info("remove: user {} unliked post {}", likeFound.getUserId(), likeFound.getPostId());
        return likeFound;
    }

    private boolean exists(PostLike like) {
        return get(like).isPresent();
    }

    private Optional<PostLike> get(PostLike like) {
        return dao.getBy(like);
    }

}
