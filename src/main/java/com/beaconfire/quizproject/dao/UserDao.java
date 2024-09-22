package com.beaconfire.quizproject.dao;


import com.beaconfire.quizproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Register a new user
    public int registerUser(User user) {
        String sql = "INSERT INTO USERS (email, firstname, lastname, " +
                "password) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getEmail(), user.getFirstname(),
                user.getLastname(), user.getPassword());
    }


        public User validateUser(String email, String password) {
            String sql = "SELECT * FROM USERS WHERE email = ? AND password = ?";

            try {
                return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User(
                                rs.getInt("user_id"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("firstname"),
                                rs.getString("lastname")
                        );
                    }
                });
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }





}
