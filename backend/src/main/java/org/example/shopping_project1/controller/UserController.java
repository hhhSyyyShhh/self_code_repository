package org.example.shopping_project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

        // 判断是否已存在
        List<Map<String, Object>> existing = jdbcTemplate.queryForList(
                "SELECT * FROM users WHERE username = ?", username);
        if (!existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("用户名已存在");
        }

        jdbcTemplate.update("INSERT INTO users (username, password) VALUES (?, ?)", username, password);
        return ResponseEntity.ok("注册成功");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

//        List<Map<String, Object>> result = jdbcTemplate.queryForList(
////                "SELECT * FROM user WHERE username = ? AND password = ?", username, password);
//        ;if (result.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
//        }

        return ResponseEntity.ok("登录成功");
    }
}
