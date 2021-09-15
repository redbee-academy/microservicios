package io.redbee.socialnetwork.feeds.posts.service;


import io.redbee.socialnetwork.feeds.posts.dao.PostDao;
import io.redbee.socialnetwork.feeds.posts.exception.PostNotFoundException;
import io.redbee.socialnetwork.feeds.posts.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSearchService {
    private final PostDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostSearchService.class);

    public PostSearchService(PostDao dao) {
        this.dao = dao;
    }

    public Page<Post> getPage(Pageable pageable) {
        List<Post> postsFound = dao.getPage(pageable);
        Integer totalPosts = dao.getTotal();

        LOGGER.info("getPage: {} posts found", totalPosts);
        return new PageImpl<Post>(postsFound, pageable, totalPosts);
    }

    public Post getBy(Integer userId, Integer id) {
        Post postFound = dao.getById(userId, id)
                .orElseThrow(PostNotFoundException::new);

        LOGGER.info("getBy: post found {}", postFound);
        return postFound;
    }
}
