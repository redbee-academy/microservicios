package io.redbee.socialnetwork.feeds.posts.controller;


import io.redbee.socialnetwork.feeds.posts.mapper.PostToResponseMapper;
import io.redbee.socialnetwork.feeds.posts.model.Post;
import io.redbee.socialnetwork.feeds.posts.model.PostCreationRequest;
import io.redbee.socialnetwork.feeds.posts.model.PostResponse;
import io.redbee.socialnetwork.feeds.posts.service.PostCreationService;
import io.redbee.socialnetwork.feeds.posts.service.PostDeleteService;
import io.redbee.socialnetwork.feeds.posts.service.PostSearchService;
import io.redbee.socialnetwork.shared.controller.SecuredController;
import io.redbee.socialnetwork.users.service.UserSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostSearchService searchService;
    private final PostCreationService creationService;
    private final PostDeleteService deleteService;
    private final PostToResponseMapper responseMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    public PostController(
            PostSearchService searchService,
            PostCreationService creationService,
            PostDeleteService deleteService,
            PostToResponseMapper responseMapper) {
        this.searchService = searchService;
        this.creationService = creationService;
        this.deleteService = deleteService;
        this.responseMapper = responseMapper;
    }

    @GetMapping("/posts")
    public List<PostResponse> get(Pageable pageable) {
        LOGGER.info("get: searching posts");

        Page<Post> page = searchService.getPage(pageable);

        LOGGER.info("get: returning posts");
        return page
                .map(this::mapResponse)
                .getContent();
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public PostResponse getById(
            @PathVariable Integer userId,
            @PathVariable Integer postId,
            @RequestHeader Integer principalUserId
    ) {
        LOGGER.info("getById: searching post for user: {}, with id {} and made by {}", principalUserId, postId, userId);

        PostResponse response = mapResponse(searchService.getBy(userId, postId));

        LOGGER.info("getById: post found: {}", response);
        return response;
    }

    @PostMapping("/users/{userId}/posts")
    public PostResponse create(
            @RequestBody PostCreationRequest post,
            @PathVariable Integer userId
    ) {
        LOGGER.info("create: creating post for {}", userId);

        PostResponse response = mapResponse(creationService.create(userId, post));

        LOGGER.info("create: post created {}", response);
        return response;
    }

    @DeleteMapping("/users/{userId}/posts/{postId}")
    public PostResponse delete(
            @PathVariable Integer userId,
            @PathVariable Integer postId
    ) {
        LOGGER.info("delete: deleting post {}", postId);

        PostResponse response = mapResponse(deleteService.delete(userId, postId));

        LOGGER.info("delete: post deleted: {}", response);
        return response;
    }


    private PostResponse mapResponse(Post post) {
        return responseMapper.map(post);
    }
}
