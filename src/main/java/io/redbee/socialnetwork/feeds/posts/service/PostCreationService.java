package io.redbee.socialnetwork.feeds.posts.service;

import io.redbee.socialnetwork.feeds.posts.builder.PostBuilder;
import io.redbee.socialnetwork.feeds.posts.dao.PostDao;
import io.redbee.socialnetwork.feeds.posts.model.Post;
import io.redbee.socialnetwork.feeds.posts.model.PostCreationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostCreationService {
    private final PostDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostCreationService.class);

    public PostCreationService(PostDao dao) {
        this.dao = dao;
    }

    public Post create(Integer userId, PostCreationRequest desiredPost) {
        Post post = buildWith(userId, desiredPost.getContent());

        int id = save(post);

        LOGGER.info("create: post {} created", id);
        return post.copyId(id);
    }

    private Post buildWith(Integer userId, String content) {
        return new PostBuilder()
                .userId(userId)
                .content(content)
                .status("POSTED")
                .creationAuditFields()
                .build();
    }

    private int save(Post post) {
        int id = dao.save(post);

        LOGGER.info("save: post {} saved", id);
        return id;
    }
}
