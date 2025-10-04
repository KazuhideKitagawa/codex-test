package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userMapper.findById(id));
    }

    @Transactional
    public User createUser(String name, String email) {
        User user = new User(null, name, email);
        userMapper.insert(user);
        return user;
    }

    @Transactional
    public Optional<User> updateUser(Long id, String name, String email) {
        User existing = userMapper.findById(id);
        if (existing == null) {
            return Optional.empty();
        }
        existing.setName(name);
        existing.setEmail(email);
        userMapper.update(existing);
        return Optional.of(existing);
    }

    @Transactional
    public boolean deleteById(Long id) {
        return userMapper.deleteById(id) > 0;
    }
}
