package io.redbee.socialnetwork.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;

import java.util.Objects;

/**
 * @author Joaco Campero
 * <p>
 * created at 6/9/21
 */
@Configuration
@EnableWebSecurity
public class ResourceServerConfiguration extends WebSecurityConfigurerAdapter {

    private final String TOKEN = "83167767-8cb5-4852-88c0-4cddb36342ec";
    private final BearerTokenAuthenticationEntryPoint bearerTokenAuthenticationEntryPoint = new BearerTokenAuthenticationEntryPoint();
    private final BearerTokenAccessDeniedHandler bearerTokenAccessDeniedHandler = new BearerTokenAccessDeniedHandler();

    private final ObjectMapper objectMapper;

    public ResourceServerConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()

                .authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers("/**").authenticated())
                .oauth2ResourceServer(configurer -> {
                    configurer.authenticationManagerResolver(context -> authentication -> {
                                BearerTokenAuthenticationToken bearer = (BearerTokenAuthenticationToken) authentication;

                                if (!bearer.getToken().equals(TOKEN))
                                    throw new InvalidBearerTokenException("invalid token");

                                bearer.setAuthenticated(true);
                                return bearer;

                            })
                            .authenticationEntryPoint((request, response, authException) -> {
                                this.bearerTokenAuthenticationEntryPoint.commence(request, response, authException);

                                ResponseEntity<ErrorResponse> res = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                        .body(new ErrorResponse(authException.getMessage()));

                                response.setStatus(res.getStatusCode().value());
                                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                                response.getWriter().write(objectMapper.writeValueAsString(res.getBody()));

                            })
                            .accessDeniedHandler((request, response, accessDeniedException) -> {
                                this.bearerTokenAccessDeniedHandler.handle(request, response, accessDeniedException);

                                ResponseEntity<ErrorResponse> res = ResponseEntity.status(HttpStatus.FORBIDDEN)
                                        .body(new ErrorResponse(accessDeniedException.getMessage()));

                                response.setStatus(res.getStatusCode().value());
                                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                                response.getWriter().write(objectMapper.writeValueAsString(res.getBody()));
                            });
                });

    }

    static class ErrorResponse {
        private String error;

        public ErrorResponse() {
        }

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ErrorResponse that = (ErrorResponse) o;
            return Objects.equals(error, that.error);
        }

        @Override
        public int hashCode() {
            return Objects.hash(error);
        }

        @Override
        public String toString() {
            return "ErrorResponse{" +
                    "error='" + error + '\'' +
                    '}';
        }
    }
}
