package org.example.shopping_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Map<String, Object>> getTopProducts() {
        return jdbcTemplate.queryForList(
                "SELECT name, COUNT(*) AS count " +
                        "FROM shopping_cart " +
                        "GROUP BY name " +
                        "ORDER BY count DESC " +
                        "LIMIT 5"
        );
    }
}