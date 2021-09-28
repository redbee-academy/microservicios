package io.redbee.socialnetwork.feeds.comments;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private final Integer id;
    private final Integer userId;
    private final Integer postId;
    private final String content;
    private final String status;
    private final LocalDateTime creationDate;
    private final String creationUser;
    private final LocalDateTime modificationDate;
    private final String modificationUser;

    public Comment(Integer id, Integer userId, Integer postId, String content, String status, LocalDateTime creationDate, String creationUser, LocalDateTime modificationDate, String modificationUser) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
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

    public Integer getPostId() {
        return postId;
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
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(userId, comment.userId) && Objects.equals(postId, comment.postId) && Objects.equals(content, comment.content) && Objects.equals(status, comment.status) && Objects.equals(creationDate, comment.creationDate) && Objects.equals(creationUser, comment.creationUser) && Objects.equals(modificationDate, comment.modificationDate) && Objects.equals(modificationUser, comment.modificationUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, postId, content, status, creationDate, creationUser, modificationDate, modificationUser);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", postId=" + postId +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", creationUser='" + creationUser + '\'' +
                ", modificationDate=" + modificationDate +
                ", modificationUser='" + modificationUser + '\'' +
                '}';
    }
}
