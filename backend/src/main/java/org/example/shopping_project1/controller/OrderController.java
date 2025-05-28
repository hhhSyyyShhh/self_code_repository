package org.example.shopping_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping
    public void submitOrder(@RequestBody List<Map<String, Object>> cart) {
        for (Map<String, Object> item : cart) {
            jdbcTemplate.update("INSERT INTO orders(order_id, product_id, quantity, total_price, status) " +
                            "VALUES (?, ?, ?, ?, ?)",
                    UUID.randomUUID().toString(),
                    item.get("id"),
                    1,
                    item.get("price"),
                    "待处理");
        }
    }

    @GetMapping("/orders")
    public List<Map<String, Object>> getOrders() {
        return jdbcTemplate.queryForList("SELECT order_id, product_id, total_price, status FROM orders");
    }
}