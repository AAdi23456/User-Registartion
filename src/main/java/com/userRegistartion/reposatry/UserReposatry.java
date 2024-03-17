package com.userRegistartion.reposatry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userRegistartion.entity.User;


@Repository
public interface UserReposatry extends JpaRepository<User, Integer> {
	Boolean existsByEmail(String email);
	//User  findByEmail(String email);
	User findByEmail(String email);

}
