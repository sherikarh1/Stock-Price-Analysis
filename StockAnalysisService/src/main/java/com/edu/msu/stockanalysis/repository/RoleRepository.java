package com.edu.msu.stockanalysis.repository;

import com.edu.msu.stockanalysis.model.ERole;
import com.edu.msu.stockanalysis.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
