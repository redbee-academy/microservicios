package io.redbee.socialnetwork.users.controller;

import io.redbee.socialnetwork.configuration.hateoas.PaginatedResultsRetrievedEvent;
import io.redbee.socialnetwork.users.mapper.UserToResponseMapper;
import io.redbee.socialnetwork.users.model.User;
import io.redbee.socialnetwork.users.model.UserCreationRequest;
import io.redbee.socialnetwork.users.model.UserResponse;
import io.redbee.socialnetwork.users.service.UserCreationService;
import io.redbee.socialnetwork.users.service.UserDeleteService;
import io.redbee.socialnetwork.users.service.UserSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserCreationService creationService;
    private final UserSearchService searchService;
    private final UserDeleteService deleteService;
    private final UserToResponseMapper responseMapper;
    private final ApplicationEventPublisher eventPublisher;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController(UserCreationService creationService, UserSearchService searchService, UserDeleteService deleteService, UserToResponseMapper responseMapper, ApplicationEventPublisher eventPublisher) {
        this.creationService = creationService;
        this.searchService = searchService;
        this.deleteService = deleteService;
        this.responseMapper = responseMapper;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping()
    public List<UserResponse> get(
            Pageable pageable,
            UriComponentsBuilder uriBuilder,
            HttpServletResponse response
    ) {
        LOGGER.info("get: searching users");
        Page<User> responsePage = searchService.get(pageable);

        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<User>(
                User.class, uriBuilder, response, pageable.getPageNumber(), responsePage.getTotalPages(), pageable.getPageSize()
        ));

        LOGGER.info("get: returning users");
        return responsePage.getContent()
                .stream()
                .map(this::mapResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Integer id) {
        LOGGER.info("getById: searching for user {}", id);
        UserResponse response = mapResponse(searchService.get(id));
        LOGGER.info("getById: user found {}", response);
        return response;
    }

    @PostMapping()
    public UserResponse create(@RequestBody @Valid UserCreationRequest request) {
        LOGGER.info("create: creating user {}", request.getMail());
        UserResponse response = mapResponse(creationService.create(request.getMail(), request.getPassword()));
        LOGGER.info("create: created user {}", response);
        return response;
    }

    @DeleteMapping("/{id}")
    public UserResponse delete(@PathVariable Integer id) {
        LOGGER.info("delete: deleting user {}", id);
        UserResponse response = mapResponse(deleteService.delete(id));
        LOGGER.info("delete: deleted user: {}", response);
        return response;
    }

    private UserResponse mapResponse(User user) {
        return responseMapper.map(user);
    }
}
