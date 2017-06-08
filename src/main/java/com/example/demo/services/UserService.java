package com.example.demo.services;

import com.example.demo.entities.User;

/**
 * This interface defines special methods for UserService
 *
 * @param <T> Child of User class
 */
public interface UserService<T extends User> {

    /**
     * This method defines some actions before saving User's object
     * and then call appropriate UserRepository method
     *
     * @param user User's object for saving
     */
    void save(T user);

    /**
     * This method returns User object by email
     *
     * @param email User email
     * @return Appropriate user
     */
    T findByEmail(String email);
}
