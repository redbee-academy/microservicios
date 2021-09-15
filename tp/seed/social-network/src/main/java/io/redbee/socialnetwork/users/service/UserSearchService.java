package io.redbee.socialnetwork.users.service;

import io.redbee.socialnetwork.users.dao.UserDao;
import io.redbee.socialnetwork.users.exception.UserNotFoundException;
import io.redbee.socialnetwork.users.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSearchService {

    private final UserDao dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSearchService.class);

    public UserSearchService(UserDao dao) {
        this.dao = dao;
    }

    public Page<User> getPage(Pageable pageable) {
        List<User> usersFound = dao.getPage(pageable);
        Integer totalUsers = dao.getTotal();

        LOGGER.info("getPage: {} users found", usersFound.size());
        return new PageImpl<User>(usersFound, pageable, totalUsers);
    }

    public User getBy(Integer id) {
        User userFound = dao.getById(id)
                .orElseThrow(UserNotFoundException::new);

        LOGGER.info("getBy: user found {}", userFound);
        return userFound;
    }

    public Optional<User> getActiveBy(String mail) {
        return dao.getByMail(mail)
                .stream()
                .filter(user -> !user.getStatus().equals("DELETED"))
                .findFirst();
    }
}
