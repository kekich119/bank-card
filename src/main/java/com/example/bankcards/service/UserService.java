package com.example.bankcards.service;


import com.example.bankcards.dto.UserViewDto;
import com.example.bankcards.entity.User;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.security.UserDetailsImpl;
import com.example.bankcards.util.RoleType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return UserDetailsImpl.build(user);
    }

    public List<UserViewDto> findAllUserWithoutPassword() {
        return userRepository.findAllUsersWithoutPassword();
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }



    public RoleType getRoleByEmail(String email) {

        if (userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            int roleId = user.getRoleId();

            return RoleType.fromCode(roleId);
        }else {
            return null;
        }



    }


}
