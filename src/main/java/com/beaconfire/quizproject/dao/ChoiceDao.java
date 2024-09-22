package com.beaconfire.quizproject.dao;

import com.beaconfire.quizproject.model.Choice;
import com.beaconfire.quizproject.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChoiceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ChoiceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get a single question by its ID
    // Get all choices for a given question using BeanPropertyRowMapper
    public List<Choice> getChoicesByQuestionId(int questionId) {
        String sql = "SELECT * FROM Choice WHERE question_id = ?";
        return jdbcTemplate.query(sql, new Object[]{questionId}, new BeanPropertyRowMapper<>(Choice.class));
    }

}
