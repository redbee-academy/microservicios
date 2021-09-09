package io.redbee.socialnetwork.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    UserService userService;

    @GetMapping()
    public List<User> listUsers() {
        return userService.get();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable final Integer id) {
       return userService.getById(id);
    }

    @PostMapping()
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping()
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final Integer id) {
        userService.delete(id);
    }

}
