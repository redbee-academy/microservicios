package io.redbee.socialnetwork.feeds.postLikes;

import io.redbee.socialnetwork.shared.controller.SecuredController;
import io.redbee.socialnetwork.users.service.UserSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/users/{userId}/posts/{postId}/likes")
@PreAuthorize("isAuthenticated()")
public class PostLikeController extends SecuredController {
    private final PostLikeService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostLikeController.class);


    public PostLikeController(UserSearchService searchService, PostLikeService service) {
        super(searchService);
        this.service = service;
    }

    @PutMapping()
    @ResponseStatus(NO_CONTENT)
    public void like(
            @PathVariable Integer userId,
            @PathVariable Integer postId
    ) {
        Integer principalUserId = getPrincipalUserId();
        LOGGER.info("like: user {} wants to like post {} made by {}", principalUserId, postId, userId);
        service.like(new PostLike(postId, principalUserId), userId);
        LOGGER.info("like: user {} liked post {} made by {}", principalUserId, postId, userId);
    }

    @DeleteMapping()
    @ResponseStatus(NO_CONTENT)
    public void unLike(
            @PathVariable Integer userId,
            @PathVariable Integer postId
    ) {
        Integer principalUserId = getPrincipalUserId();
        LOGGER.info("unLike: user {} wants to unlike post {} made by {}", principalUserId, postId, userId);
        service.unlike(new PostLike(postId, principalUserId));
        LOGGER.info("unLike: user {} unliked post {} made by {}", principalUserId, postId, userId);
    }

    @GetMapping()
    public List<PostLike> getLikes(
            @PathVariable Integer userId,
            @PathVariable Integer postId
    ) {
        LOGGER.info("getLikes: searching for likes for post {} made by {}", postId, userId);
        List<PostLike> response = service.getAll(userId, postId);

        LOGGER.info("getLikes: likes found {}", response);
        return response;
    }
}
