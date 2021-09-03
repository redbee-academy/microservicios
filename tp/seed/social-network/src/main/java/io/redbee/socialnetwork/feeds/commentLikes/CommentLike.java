package io.redbee.socialnetwork.feeds.commentLikes;

import java.util.Objects;

public class CommentLike {
    private final Integer commentId;
    private final Integer userId;

    public CommentLike(Integer commentId, Integer userId) {
        this.commentId = commentId;
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentLike that = (CommentLike) o;
        return Objects.equals(commentId, that.commentId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, userId);
    }

    @Override
    public String toString() {
        return "CommentLike{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                '}';
    }
}
