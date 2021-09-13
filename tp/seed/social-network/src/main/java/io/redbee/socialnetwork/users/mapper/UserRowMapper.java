package io.redbee.socialnetwork.users.mapper;

import io.redbee.socialnetwork.users.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("mail"),
                rs.getString("encrypted_password"),
                rs.getString("status"),
                rs.getTimestamp("creation_date").toLocalDateTime(),
                rs.getString("creation_user"),
                formatDate(rs.getTimestamp("modification_date")),
                rs.getString("modification_user")
        );
    }

    private LocalDateTime formatDate(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

}
