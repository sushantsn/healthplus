package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByAuthority(String authority);
}
