package io.redbee.socialnetwork.feeds.posts;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private final Integer id;
    private final Integer userId;
    private final String content;
    private final String status;
    private final LocalDateTime creationDate;
    private final String creationUser;
    private final LocalDateTime modificationDate;
    private final String modificationUser;

    public Post(Integer id, Integer userId, String content, String status, LocalDateTime creationDate, String creationUser, LocalDateTime modificationDate, String modificationUser) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.status = status;
        this.creationDate = creationDate;
        this.creationUser = creationUser;
        this.modificationDate = modificationDate;
        this.modificationUser = modificationUser;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public String getModificationUser() {
        return modificationUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(userId, post.userId) && Objects.equals(content, post.content) && Objects.equals(status, post.status) && Objects.equals(creationDate, post.creationDate) && Objects.equals(creationUser, post.creationUser) && Objects.equals(modificationDate, post.modificationDate) && Objects.equals(modificationUser, post.modificationUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, content, status, creationDate, creationUser, modificationDate, modificationUser);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", creationUser='" + creationUser + '\'' +
                ", modificationDate=" + modificationDate +
                ", modificationUser='" + modificationUser + '\'' +
                '}';
    }
}
