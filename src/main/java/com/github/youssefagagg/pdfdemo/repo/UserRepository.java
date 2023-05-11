package com.github.youssefagagg.pdfdemo.repo;

import com.github.youssefagagg.pdfdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}