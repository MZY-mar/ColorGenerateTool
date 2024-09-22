package com.beaconfire.quizproject.dao;

import com.beaconfire.quizproject.model.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.beaconfire.quizproject.model.Question;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class QuestionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public QuestionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get a single question by its ID
    public Question getQuestionById(int id) {
        String sql = "SELECT * FROM Question WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Question.class), id); }

     public List<Question> getAllQuestions(){
        String sql = "Select * from question";
         return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Question.class));
     }

}
