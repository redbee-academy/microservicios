package io.redbee.socialnetwork.feeds.posts.builder;

import io.redbee.socialnetwork.feeds.posts.model.Post;

import java.time.LocalDateTime;

public class PostBuilder {
    private Integer id;
    private Integer userId;
    private String content;
    private String status;
    private LocalDateTime creationDate;
    private String creationUser;
    private LocalDateTime modificationDate;
    private String modificationUser;

    public PostBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public PostBuilder userId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public PostBuilder content(String content) {
        this.content = content;
        return this;
    }

    public PostBuilder status(String status) {
        this.status = status;
        return this;
    }

    public PostBuilder creationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public PostBuilder creationUser(String creationUser) {
        this.creationUser = creationUser;
        return this;
    }

    public PostBuilder modificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public PostBuilder modificationUser(String modificationUser) {
        this.modificationUser = modificationUser;
        return this;
    }

    public PostBuilder updatedAuditFields() {
        this.modificationDate = LocalDateTime.now();
        this.modificationUser = "social-network";
        return this;
    }

    public PostBuilder creationAuditFields() {
        this.creationDate = LocalDateTime.now();
        this.creationUser = "social-network";

        return this;
    }

    public PostBuilder basedOn(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.content = post.getContent();
        this.status = post.getStatus();
        this.creationDate = post.getCreationDate();
        this.creationUser = post.getCreationUser();
        this.modificationDate = post.getModificationDate();
        this.modificationUser = post.getModificationUser();

        return this;
    }

    public Post build() {
        return new Post(
                id,
                userId,
                content,
                status,
                creationDate,
                creationUser,
                modificationDate,
                modificationUser
        );
    }
}
