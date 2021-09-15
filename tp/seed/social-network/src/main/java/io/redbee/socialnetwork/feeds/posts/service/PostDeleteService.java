package io.redbee.socialnetwork.feeds.posts.service;

import io.redbee.socialnetwork.feeds.posts.model.Post;
import io.redbee.socialnetwork.feeds.posts.model.PostUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostDeleteService {
    private final PostUpdateService updateService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostDeleteService.class);
    private static final PostUpdateRequest deleteUpdateRequest = new PostUpdateRequest(null, "DELETED");

    public PostDeleteService(PostUpdateService updateService) {
        this.updateService = updateService;
    }

    public Post delete(Integer userId, Integer postId) {
        Post deleted = updateService.update(userId, postId, deleteUpdateRequest);

        LOGGER.info("delete: post {} deleted", postId);
        return deleted;
    }
}
