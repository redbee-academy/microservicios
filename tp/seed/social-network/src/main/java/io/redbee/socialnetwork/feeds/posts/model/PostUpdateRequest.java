package io.redbee.socialnetwork.feeds.posts.model;

import java.util.Objects;

public class PostUpdateRequest {
    private final String content;
    private final String status;

    public PostUpdateRequest(String content, String status) {
        this.content = content;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostUpdateRequest that = (PostUpdateRequest) o;
        return Objects.equals(content, that.content) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, status);
    }

    @Override
    public String toString() {
        return "PostUpdateRequest{" +
                "content='" + content + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
