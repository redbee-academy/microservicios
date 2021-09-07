package io.redbee.socialnetwork.factory;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.http.HttpHeaders;

public interface HeaderFactory {

    String AUTORIZATION = "Authorization";
    String AUTORIZATION_VALUE = "Bearer 83167767-8cb5-4852-88c0-4cddb36342ec";

    HttpHeaders httpHeader = new HttpHeaders();

    @BeforeAll
    static void createHeaders() {
        if (httpHeader.isEmpty()) {
            httpHeader.add(AUTORIZATION, AUTORIZATION_VALUE);
        }
    }
}
