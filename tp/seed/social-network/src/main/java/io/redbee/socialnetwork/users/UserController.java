package io.redbee.socialnetwork.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Joaco Campero
 * <p>
 * created at 6/9/21
 */
@RestController

@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserDao userDao;

    @GetMapping()
    public List<User> status() {
        return userDao.get();
    }

    @PostMapping()
    public void status(@RequestBody User user) {
        userDao.save(user);
    }

}
