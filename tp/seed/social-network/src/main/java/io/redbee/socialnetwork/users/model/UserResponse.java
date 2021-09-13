package io.redbee.socialnetwork.users.model;

import java.io.Serializable;
import java.util.Objects;

public class UserResponse implements Serializable {

    private final Integer id;
    private final String mail;
    private final String status;

    public UserResponse(Integer id, String mail, String status) {
        this.id = id;
        this.mail = mail;
        this.status = status;
    }

    public Integer getId() {
        return id;
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
        UserResponse that = (UserResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(mail, that.mail) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, status);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
