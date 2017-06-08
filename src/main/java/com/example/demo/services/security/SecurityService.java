package com.example.demo.services.security;

/**
 * This interface define methods for SecurityService
 *
 */
public interface SecurityService {
    /**
     * This method serves to provide auto login user after registering an account.
     * @param email - User's Email address
     * @param password - User's password
     */
    void autoLogin(String email, String password);
}
