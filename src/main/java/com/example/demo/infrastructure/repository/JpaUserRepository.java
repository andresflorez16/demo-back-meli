package com.example.demo.infrastructure.repository;

import com.example.demo.infrastructure.dto.ResponseDTO;
import com.example.demo.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

    UserEntity findById(int id);

    void deleteById(int id);
}
