package io.redbee.socialnetwork.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean("feedsDataSource")
    public DataSource feedsDataSource(
            @Value("${datasource.feeds.driver}") String driverClassName,
            @Value("${datasource.feeds.password}") String password,
            @Value("${datasource.feeds.username}") String username,
            @Value("${datasource.feeds.url}") String url
    ) {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName(driverClassName);
        builder.password(password);
        builder.username(username);
        builder.url(url);

        return builder.build();
    }

    @Primary
    @Bean("feedsTemplate")
    public NamedParameterJdbcTemplate feedsTemplate(@Qualifier("feedsDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean("usersDataSource")
    public DataSource usersDataSource(
            @Value("${datasource.users.driver}") String driverClassName,
            @Value("${datasource.users.password}") String password,
            @Value("${datasource.users.username}") String username,
            @Value("${datasource.users.url}") String url
    ) {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.driverClassName(driverClassName);
        builder.password(password);
        builder.username(username);
        builder.url(url);

        return builder.build();
    }

    @Bean("usersTemplate")
    public NamedParameterJdbcTemplate usersTemplate(@Qualifier("usersDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
