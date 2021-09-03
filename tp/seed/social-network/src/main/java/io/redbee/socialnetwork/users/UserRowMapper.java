package io.redbee.socialnetwork.users;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

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
                rs.getTimestamp("modification_date").toLocalDateTime(),
                rs.getString("modification_user")
        );
    }
}
