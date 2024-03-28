package com.finfirms.loginauthentication.dao;

import com.finfirms.loginauthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByName(String username);
    Optional<User> findByEmail(String email);

}
