package io.redbee.socialnetwork.users.controller;

import io.redbee.socialnetwork.shared.controller.SecuredController;
import io.redbee.socialnetwork.users.mapper.UserToResponseMapper;
import io.redbee.socialnetwork.users.model.User;
import io.redbee.socialnetwork.users.model.UserCreationRequest;
import io.redbee.socialnetwork.users.model.UserResponse;
import io.redbee.socialnetwork.users.service.UserCreationService;
import io.redbee.socialnetwork.users.service.UserDeleteService;
import io.redbee.socialnetwork.users.service.UserSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UserController extends SecuredController {

    private final UserCreationService creationService;
    private final UserSearchService searchService;
    private final UserDeleteService deleteService;
    private final UserToResponseMapper responseMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(
            UserSearchService searchService,
            UserCreationService creationService,
            UserDeleteService deleteService,
            UserToResponseMapper responseMapper
    ) {
        super(searchService);
        this.creationService = creationService;
        this.searchService = searchService;
        this.deleteService = deleteService;
        this.responseMapper = responseMapper;
    }

    @GetMapping()
    public List<UserResponse> get(
            Pageable pageable,
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response
    ) {
        LOGGER.info("get: searching users");

        Page<User> responsePage = searchService.getPage(pageable);

        LOGGER.info("get: users found");
        return responsePage
                .map(this::mapResponse)
                .getContent();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Integer id) {
        LOGGER.info("getById: searching for user {}", id);
        UserResponse response = mapResponse(searchService.getBy(id));
        LOGGER.info("getById: user found {}", response);
        return response;
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    @PreAuthorize("permitAll()")
    public UserResponse create(@RequestBody @Valid UserCreationRequest user) {
        LOGGER.info("create: creating user {}", user.getMail());
        UserResponse response = mapResponse(
                creationService.create(user.getMail(), user.getPassword())
        );
        LOGGER.info("create: user created {}", response);
        return response;
    }

    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable Integer id) {
        validateUserIsOwner(id);
        LOGGER.info("delete: deleting user {}", id);
        SecurityContextHolder.getContext().getAuthentication().getDetails();
        UserResponse response = mapResponse(deleteService.delete(id));
        LOGGER.info("delete: user deleted: {}", response);
        return response;
    }

    private UserResponse mapResponse(User user) {
        return responseMapper.map(user);
    }

}
