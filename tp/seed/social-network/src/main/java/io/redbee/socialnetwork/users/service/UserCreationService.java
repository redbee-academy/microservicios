package io.redbee.socialnetwork.users.service;

import io.redbee.socialnetwork.shared.exception.RepositoryException;
import io.redbee.socialnetwork.users.dao.UserDao;
import io.redbee.socialnetwork.users.exception.AccountAlreadyExistsException;
import io.redbee.socialnetwork.users.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCreationService {
    private final UserSearchService searchService;
    private final UserDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreationService.class);

    public UserCreationService(UserSearchService searchService, UserDao dao) {
        this.searchService = searchService;
        this.dao = dao;
    }

    public User create(String mail, String password) {
        validateAccountAlreadyExists(mail);

        dao.save(new User(mail, password));

        return getActive(mail)
                .orElseThrow(RepositoryException::new);
    }

    private void validateAccountAlreadyExists(String mail) {
        if (exists(mail)) {
            LOGGER.info("validateAccountAlreadyExists: account with mail {} already exists", mail);
            throw new AccountAlreadyExistsException(mail);
        }
    }

    private boolean exists(String mail) {
        Optional<User> user = getActive(mail);
        return user.isPresent();
    }

    private Optional<User> getActive(String mail) {
        return searchService.getActiveBy(mail);
    }
}
