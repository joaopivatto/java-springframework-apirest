package com.joao.pivatto.apirest.repository;

import com.joao.pivatto.apirest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {

    UserDetails findByLogin(String login);
}
