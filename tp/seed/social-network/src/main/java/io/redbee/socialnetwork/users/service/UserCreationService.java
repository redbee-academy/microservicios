package io.redbee.socialnetwork.users.service;

import io.redbee.socialnetwork.users.builder.UserBuilder;
import io.redbee.socialnetwork.users.dao.UserDao;
import io.redbee.socialnetwork.users.exception.AccountAlreadyExistsException;
import io.redbee.socialnetwork.users.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreationService {
    private final UserSearchService searchService;
    private final UserDao dao;
    private final PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreationService.class);

    public UserCreationService(UserSearchService searchService, UserDao dao, PasswordEncoder passwordEncoder) {
        this.searchService = searchService;
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String mail, String password) {
        User user = buildWith(mail, password);

        validateAccountAlreadyExists(user.getMail());

        int id = save(user);

        return user.copyId(id);
    }

    private User buildWith(String mail, String password) {
        return new UserBuilder()
                .mail(mail)
                .password(passwordEncoder.encode(password))
                .status("CREATED")
                .creationAuditFields()
                .build();
    }

    private void validateAccountAlreadyExists(String mail) {
        if (exists(mail)) {
            LOGGER.info("validateAccountAlreadyExists: account with mail {} already exists", mail);
            throw new AccountAlreadyExistsException(mail);
        }
        LOGGER.info("mail {} doesnt have an account yet", mail);
    }

    private boolean exists(String mail) {
        return searchService.getActiveBy(mail).isPresent();
    }

    private int save(User user) {
        int id = dao.save(user);
        LOGGER.info("save: user {} saved", id);
        return id;
    }

}
