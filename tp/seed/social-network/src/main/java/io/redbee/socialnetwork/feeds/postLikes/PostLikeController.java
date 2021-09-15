package io.redbee.socialnetwork.feeds.postLikes;

import io.redbee.socialnetwork.users.service.UserSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/users/{userId}/posts/{postId}/likes")
public class PostLikeController {
    private final PostLikeService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(PostLikeController.class);


    public PostLikeController(PostLikeService service) {
        this.service = service;
    }

    @PutMapping()
    @ResponseStatus(NO_CONTENT)
    public void like(
            @PathVariable Integer userId, // post owner
            @PathVariable Integer postId, // post id to like,
            @RequestHeader Integer principalUserId // logged user

    ) {
        LOGGER.info("like: user {} wants to like post {} made by {}", principalUserId, postId, userId);
        service.like(new PostLike(postId, principalUserId), userId);
        LOGGER.info("like: user {} liked post {} made by {}", principalUserId, postId, userId);
    }

    @DeleteMapping()
    @ResponseStatus(NO_CONTENT)
    public void unLike(
            @PathVariable Integer userId,
            @PathVariable Integer postId,
            @RequestHeader Integer principalUserId // logged user

    ) {
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
