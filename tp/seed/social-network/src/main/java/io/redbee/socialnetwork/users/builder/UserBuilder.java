package io.redbee.socialnetwork.users.builder;

import io.redbee.socialnetwork.users.model.User;

import java.time.LocalDateTime;

public class UserBuilder {
    private Integer id;
    private String mail;
    private String encryptedPassword;
    private String status;
    private LocalDateTime creationDate;
    private String creationUser;
    private LocalDateTime modificationDate;
    private String modificationUser;

    public UserBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder mail(String mail) {
        this.mail = mail;
        return this;
    }

    public UserBuilder status(String status) {
        this.status = status;
        return this;
    }

    public UserBuilder password(String password) {
        this.encryptedPassword = password;
        return this;
    }

    public UserBuilder updatedAuditFields() {
        this.modificationDate = LocalDateTime.now();
        this.modificationUser = "social-network";
        return this;
    }

    public UserBuilder creationAuditFields() {
        this.creationDate = LocalDateTime.now();
        this.creationUser = "social-network";

        return this;
    }

    public UserBuilder modificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public UserBuilder modificationUser(String modificationUser) {
        this.modificationUser = modificationUser;
        return this;
    }

    public UserBuilder basedOn(User user) {
        this.id = user.getId();
        this.mail = user.getMail();
        this.encryptedPassword = user.getEncryptedPassword();
        this.status = user.getStatus();
        this.creationDate = user.getCreationDate();
        this.creationUser = user.getCreationUser();
        this.modificationDate = user.getModificationDate();
        this.modificationUser = user.getModificationUser();

        return this;
    }

    public User build() {
        return new User(
                id,
                mail,
                encryptedPassword,
                status,
                creationDate,
                creationUser,
                modificationDate,
                modificationUser
        );
    }
}
