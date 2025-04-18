package com.joao.pivatto.apirest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joao.pivatto.apirest.dto.CreateUserDTO;
import com.joao.pivatto.apirest.dto.UpdateUserDTO;
import com.joao.pivatto.apirest.dto.UserDTO;
import com.joao.pivatto.apirest.infrastructure.TokenService;
import com.joao.pivatto.apirest.infrastructure.Token;
import com.joao.pivatto.apirest.model.Role;
import com.joao.pivatto.apirest.model.User;
import com.joao.pivatto.apirest.repository.RoleRepository;
import com.joao.pivatto.apirest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RoleRepository roleRepository;

    public UserDTO find(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find user with id: " + id + "."));
        return new UserDTO(user);
    }


    public UserDTO find(Token token){
        String login = tokenService.validateToken(token.getValue());
        if (login != null && !login.isEmpty()){
            return new UserDTO((User) userRepository.findByLogin(login));
        }
        return null;
    }

    public List<UserDTO> search(){
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }


    public String createUser(CreateUserDTO createUserDTO) throws JsonProcessingException {

        List<Role> roles = roleRepository.findAllByAuthorityIn(createUserDTO.getRoles());

        if (roles.size() != createUserDTO.getRoles().size()) {
            throw new RuntimeException("Invalid roles: " + createUserDTO.getRoles() + ". Available roles: [" +
                    roles.stream()
                    .map(Role::getAuthority)
                    .collect(Collectors.joining(", "))
                    + "].");
        }

        createUserDTO.setPassword(new BCryptPasswordEncoder().encode(createUserDTO.getPassword()));
        userRepository.save(new User(createUserDTO));
        return "User successfully registered!";
    }

    public String updateUser(int id, UpdateUserDTO updateUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find user with id: " + id + "."));

        if (updateUserDTO.getName() != null && !updateUserDTO.getName().isBlank()) {
            user.setName(updateUserDTO.getName());
        }

        if (updateUserDTO.getPassword() != null && !updateUserDTO.getPassword().isBlank()) {
            user.setPassword(new BCryptPasswordEncoder().encode(updateUserDTO.getPassword()));
        }

        if (updateUserDTO.getRoles() != null) {
            if (updateUserDTO.getRoles().isEmpty()) {
                user.setAuthorities(List.of());
            } else {
                List<Role> roles = roleRepository.findAllByAuthorityIn(updateUserDTO.getRoles());

                if (roles.size() != updateUserDTO.getRoles().size()) {
                    throw new RuntimeException("Invalid roles: " + updateUserDTO.getRoles() + ". Available roles: [" +
                            roles.stream()
                                    .map(Role::getAuthority)
                                    .collect(Collectors.joining(", "))
                            + "].");
                }

                user.setAuthorities(roles);
            }
        }

        userRepository.save(user);
        return "User successfully updated!";
    }

}
