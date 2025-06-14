package com.example.swp.repository;

import com.example.swp.entity.Role;
import com.example.swp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    User findByUserId(int userId);
    List<User> findByRole(Role role);
    List<User> findByRole_RoleId(Integer roleId);
    List<User> findByRole_RoleIdNot(Integer roleId);
    List<User> findByEmailContainingAndRole_RoleId(String email, Integer roleId);
    List<User> findByEmailContainingAndRole_RoleIdNot(String email, Integer roleId);

}
