package com.esprit.pidev2022.security.repository;


import com.esprit.pidev2022.security.model.ERole;
import com.esprit.pidev2022.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
