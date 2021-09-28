package io.redbee.socialnetwork.users.model;

import java.util.Objects;

public class UserUpdateRequest {
    private final String mail;
    private final String status;

    public UserUpdateRequest(String mail, String status) {
        this.mail = mail;
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdateRequest that = (UserUpdateRequest) o;
        return Objects.equals(mail, that.mail) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, status);
    }

    @Override
    public String toString() {
        return "UserPutRequest{" +
                "mail='" + mail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
