package io.redbee.socialnetwork.feeds.posts.model;

import java.util.Objects;

public class PostResponse {
    private final Integer id;
    private final String content;
    private final String status;
    private final Integer userId;

    public PostResponse(Integer id, String content, String status, Integer userId) {
        this.id = id;
        this.content = content;
        this.status = status;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostResponse that = (PostResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(status, that.status) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, status, userId);
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
