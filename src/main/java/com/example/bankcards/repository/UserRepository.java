package com.example.bankcards.repository;


import com.example.bankcards.dto.UserViewDto;
import com.example.bankcards.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query("SELECT new com.example.bankcards.dto.UserViewDto(u.id, u.email, u.name, u.roleId) FROM User u")
    List<UserViewDto> findAllUsersWithoutPassword();

}
