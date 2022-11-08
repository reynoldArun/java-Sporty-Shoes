package com.sportyshoes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository <UserEntity, Integer> {

	List<UserEntity> findByUsername(String username);

	

}
