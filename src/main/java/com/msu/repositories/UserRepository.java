package com.msu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msu.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
