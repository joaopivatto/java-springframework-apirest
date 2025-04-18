package com.joao.pivatto.apirest.repository;

import com.joao.pivatto.apirest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findAllByAuthorityIn(List<String> authorities);

}
