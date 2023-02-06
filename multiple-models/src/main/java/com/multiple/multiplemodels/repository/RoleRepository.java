package com.multiple.multiplemodels.repository;

import com.multiple.multiplemodels.model.Role;
import com.multiple.multiplemodels.model.enums.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleInfo(RoleInfo roleInfo);
}

