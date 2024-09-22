package com.beaconfire.quizproject.dao;

import com.beaconfire.quizproject.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserResultDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public UserResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUserResult(int userId, int questionId, int choiceId) {
        String sql = "INSERT INTO user_result (user_id, question_id, choice_id, submit_time) VALUES (?, ?, ?, NOW())";

        int rowsAffected = jdbcTemplate.update(sql, userId, questionId, choiceId);

        if (rowsAffected > 0) {
            System.out.println("User result saved successfully.");
        } else {
            System.out.println("Failed to save user result.");
        }
    }

    public List<UserResult> findAllUserResults(){
        String sql = "select * from user_result";
        List<UserResult> userResultList = jdbcTemplate.queryForList(sql,
                UserResult.class);
        return  userResultList;
    }

    public UserResult findUserResultById(int userid){
        String sql = "select * from user_result where user_id = ? ";
        return  jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(UserResult.class), userid);
    }

    public void deleteUserResult(int resultId) {
        String sql = "DELETE FROM user_result WHERE result_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, resultId);

        if (rowsAffected > 0) {
            System.out.println("User result deleted successfully.");
        } else {
            System.out.println("No user result found with the given ID.");
        }
    }


}
