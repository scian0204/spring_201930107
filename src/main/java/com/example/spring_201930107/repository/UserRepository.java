package com.example.spring_201930107.repository;

import com.example.spring_201930107.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllBy();
    List<User> findAllByOrderByName();
    User getByUid(String uid);
}