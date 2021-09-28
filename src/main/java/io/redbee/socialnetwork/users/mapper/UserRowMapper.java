package io.redbee.socialnetwork.users.mapper;

import io.redbee.socialnetwork.users.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static io.redbee.socialnetwork.shared.util.LocalDateTimeUtils.formatDate;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("mail"),
                rs.getString("encrypted_password"),
                rs.getString("status"),
                formatDate(rs.getTimestamp("creation_date")),
                rs.getString("creation_user"),
                formatDate(rs.getTimestamp("modification_date")),
                rs.getString("modification_user")
        );
    }

}
