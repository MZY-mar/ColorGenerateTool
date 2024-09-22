package com.beaconfire.quizproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DatabaseConnectionTestt {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testConnection() {
        // 执行简单的 SQL 查询来测试连接
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        // 检查结果是否为1，表示成功执行查询
        assertThat(result).isEqualTo(1);
    }
}