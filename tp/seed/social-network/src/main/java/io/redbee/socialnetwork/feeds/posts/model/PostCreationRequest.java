package io.redbee.socialnetwork.feeds.posts.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class PostCreationRequest {

    @Size(min = 1, max = 280, message = "post content must have at least one character, and less than 280")
    @NotNull(message = "post content can not be null")
    private String content;

    public PostCreationRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostCreationRequest that = (PostCreationRequest) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "PostCreationRequest{" +
                "content='" + content + '\'' +
                '}';
    }
}
