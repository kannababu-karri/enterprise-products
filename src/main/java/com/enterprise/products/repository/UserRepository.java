package com.enterprise.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.products.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserNameAndPassword(String userName, String password);
	
	Optional<User> findById(Long id);
	
	void deleteByUserId(Long id);
	
	// Find by username
    List<User> findByUserName(String userName);

    // Find by role
    List<User> findByRole(String role);

    // Find by username AND role
    List<User> findByUserNameAndRole(String userName, String role);

    // Optional: find by username OR role
    List<User> findByUserNameOrRole(String userName, String role);
}
