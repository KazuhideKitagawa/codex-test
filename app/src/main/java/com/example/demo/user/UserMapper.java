package com.example.demo.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    List<User> findAll();

    User findById(Long id);

    int insert(User user);

    int update(User user);

    int deleteById(Long id);
}
