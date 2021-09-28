package io.redbee.socialnetwork.feeds.posts.mapper;

import io.redbee.socialnetwork.feeds.posts.model.Post;
import io.redbee.socialnetwork.feeds.posts.model.PostResponse;
import org.springframework.stereotype.Component;

@Component
public class PostToResponseMapper {
    public PostResponse map(Post post) {
        return new PostResponse(
                post.getId(),
                post.getContent(),
                post.getStatus(),
                post.getUserId()
        );
    }
}
