package com.copperbrass.practice.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.copperbrass.practice.SiteUser;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	
	 Optional<SiteUser> findByusername(String username);
}
