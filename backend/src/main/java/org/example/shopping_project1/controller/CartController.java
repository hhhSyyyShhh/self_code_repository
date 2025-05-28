package org.example.shopping_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ShoppingCart")
public class CartController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> item) {
        int id = (int) item.get("id");
        String name = (String) item.get("name");
        Double price = Double.valueOf(item.get("price").toString());
        jdbcTemplate.update("INSERT INTO shopping_cart (id, name, price) VALUES (?, ?, ?)", id, name, price);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public List<Map<String, Object>> getCartItems() {
        return jdbcTemplate.queryForList("SELECT * FROM shopping_cart");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCartItem() {
//        int id = Integer.parseInt(item.get("id").toString());
        jdbcTemplate.update("TRUNCATE TABLE shopping_cart");
        return ResponseEntity.ok().build();
    }
}
