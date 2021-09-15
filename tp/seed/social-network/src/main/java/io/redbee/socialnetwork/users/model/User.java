package io.redbee.socialnetwork.users.model;

import io.redbee.socialnetwork.users.builder.UserBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class User implements Serializable {
    private final Integer id;
    private final String mail;
    private final String encryptedPassword;
    private final String status;
    private final LocalDateTime creationDate;
    private final String creationUser;
    private final LocalDateTime modificationDate;
    private final String modificationUser;

    public User(Integer id, String mail, String encryptedPassword, String status, LocalDateTime creationDate, String creationUser, LocalDateTime modificationDate, String modificationUser) {
        this.id = id;
        this.mail = mail;
        this.encryptedPassword = encryptedPassword;
        this.status = status;
        this.creationDate = creationDate;
        this.creationUser = creationUser;
        this.modificationDate = modificationDate;
        this.modificationUser = modificationUser;
    }

    public Integer getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
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
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(mail, user.mail) && Objects.equals(encryptedPassword, user.encryptedPassword) && Objects.equals(status, user.status) && Objects.equals(creationDate, user.creationDate) && Objects.equals(creationUser, user.creationUser) && Objects.equals(modificationDate, user.modificationDate) && Objects.equals(modificationUser, user.modificationUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, encryptedPassword, status, creationDate, creationUser, modificationDate, modificationUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", encrypted_password='" + encryptedPassword + '\'' +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", creationUser='" + creationUser + '\'' +
                ", modificationDate=" + modificationDate +
                ", modificationUser='" + modificationUser + '\'' +
                '}';
    }

    public User copyStatus(String status) {
        return new UserBuilder()
                .basedOn(this)
                .status(status)
                .build();
    }

    public User copyMail(String mail) {
        return new UserBuilder()
                .basedOn(this)
                .mail(mail)
                .build();
    }

    public User copyId(Integer id) {
        return new UserBuilder()
                .basedOn(this)
                .id(id)
                .build();
    }

    public User copyModificationAudit() {
        return new UserBuilder()
                .basedOn(this)
                .updatedAuditFields()
                .build();
    }
}
