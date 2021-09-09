package io.redbee.socialnetwork.users;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private final Integer id;
    private final String mail;
    private final String encryptedPassword;
    private  String status;
    private final LocalDateTime creationDate;
    private final String creationUser;
    private final LocalDateTime modificationDate;
    private final String modificationUser;

    public User(Integer id, String mail, String encryptedPassword, String status, LocalDateTime creationDate,
                String creationUser, LocalDateTime modificationDate, String modificationUser) {
        this.id = id;
        this.mail = mail;
        this.encryptedPassword = encryptedPassword;
        this.status = status;
        this.creationDate = creationDate;
        this.creationUser = creationUser;
        this.modificationDate = modificationDate;
        this.modificationUser = modificationUser;
    }
}
