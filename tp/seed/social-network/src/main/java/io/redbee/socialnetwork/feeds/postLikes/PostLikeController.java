package io.redbee.socialnetwork.feeds.postLikes;

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
            @PathVariable Integer userId,
            @PathVariable Integer postId,
            @RequestHeader Integer requestUserId
    ) {
        LOGGER.info("like: user {} wants to like post {} made by {}", requestUserId, postId, userId);
        service.like(new PostLike(postId, requestUserId));
        LOGGER.info("like: user {} liked post {} made by {}", requestUserId, postId, userId);
    }

    @DeleteMapping()
    @ResponseStatus(NO_CONTENT)
    public void unLike(
            @PathVariable Integer userId,
            @PathVariable Integer postId,
            @RequestHeader Integer requestUserId
    ) {
        LOGGER.info("unLike: user {} wants to unlike post {} made by {}", requestUserId, postId, userId);
        service.unlike(new PostLike(postId, requestUserId));
        LOGGER.info("unLike: user {} unliked post {} made by {}", requestUserId, postId, userId);
    }

    @GetMapping()
    public List<PostLike> getLikes(
            @PathVariable Integer userId,
            @PathVariable Integer postId,
            @RequestHeader Integer requestUserId
    ) {
        LOGGER.info("getLikes: searching for likes for post {} made by {}", postId, userId);
        List<PostLike> response = service.getAll(userId, postId);

        LOGGER.info("getLikes: likes found {}", response);
        return response;
    }
}
