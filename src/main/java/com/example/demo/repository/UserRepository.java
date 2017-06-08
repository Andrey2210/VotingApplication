package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface defines special methods for working with the entity User
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * This method returns User by email
     *
     * @param email User email
     * @return Appropriate user
     */
    User findByEmail(String email);
}
