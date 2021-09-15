package io.redbee.socialnetwork.shared.controller;

import io.redbee.socialnetwork.configuration.hateoas.PaginatedResultsRetrievedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract class PaginatedController {

    private final ApplicationEventPublisher eventPublisher;

    public PaginatedController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public <T extends Serializable> void handlePaginationLinks(
            Class<T> resource,
            Pageable pageable,
            int totalPages,
            HttpServletResponse response,
            UriComponentsBuilder uriBuilder
    ) {
        eventPublisher.publishEvent(
                new PaginatedResultsRetrievedEvent<T>(
                        resource,
                        uriBuilder,
                        response,
                        pageable.getPageNumber(),
                        totalPages,
                        pageable.getPageSize()
                )
        );
    }
}
