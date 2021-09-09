package io.redbee.socialnetwork.users;

import io.redbee.socialnetwork.users.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    
    public void delete(Integer id) {
        Optional<User> user = userDao.getById(id);
        user.ifPresent( u -> {
            u.setStatus(Status.DELETED.name());
            userDao.update(u);
        });
    }

    public List<User> get() {
        return  userDao.get();
    }

    public void save(User user) {
        userDao.save(user);
    }

    public User update(User user) {
        userDao.update(user);
        return userDao.getById(user.getId()).get();
    }

    public User getById(Integer id) {
        return userDao.getById(id).orElseThrow(() -> {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        });
    }
}
