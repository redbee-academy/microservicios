package io.redbee.socialnetwork.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Joaco Campero
 * <p>
 * created at 6/9/21
 */
@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> status() {
        return Collections.singletonList(new User(
                1, "mail@pepito.com", "adsfadslfkasdjfalskdfjalskdfjlk", "CREATED", LocalDateTime.now(), "system", LocalDateTime.now(), "system"
        ));
    }

}
