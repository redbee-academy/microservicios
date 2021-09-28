package io.redbee.socialnetwork.feeds.posts.service;

import io.redbee.socialnetwork.feeds.posts.dao.PostDao;
import io.redbee.socialnetwork.feeds.posts.model.Post;
import io.redbee.socialnetwork.feeds.posts.model.PostUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostUpdateService {
    private final PostSearchService searchService;
    private final PostDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostUpdateService.class);

    public PostUpdateService(PostSearchService searchService, PostDao dao) {
        this.searchService = searchService;
        this.dao = dao;
    }

    public Post update(Integer userId, Integer postId, PostUpdateRequest updateRequest) {
        Post updated = updateFields(
                searchService.getBy(userId, postId),
                updateRequest
        );

        dao.update(updated);

        LOGGER.info("update: updated post {}", updated);
        return updated;
    }

    private Post updateFields(Post post, PostUpdateRequest updateRequest) {
        Post postWithUpdatedFields = post;

        if(updateRequest.getStatus() != null) {
            postWithUpdatedFields = postWithUpdatedFields.copyStatus(updateRequest.getStatus());
        }

        if (updateRequest.getContent() != null) {
            postWithUpdatedFields = postWithUpdatedFields.copyContent(updateRequest.getContent());
        }

        if (postWithUpdatedFields != post) {
            postWithUpdatedFields = postWithUpdatedFields.copyModificationAudit();
        }

        return postWithUpdatedFields;
    }
}
