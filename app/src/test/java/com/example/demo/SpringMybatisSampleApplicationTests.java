package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.user.User;
import com.example.demo.user.UserService;

@SpringBootTest
class SpringMybatisSampleApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void applicationStartsAndLoadsSampleData() {
        List<User> users = userService.findAll();
        assertThat(users)
                .as("初期データが読み込まれていること")
                .isNotEmpty();
    }
}
