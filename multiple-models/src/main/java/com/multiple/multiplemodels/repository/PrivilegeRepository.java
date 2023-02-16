package com.multiple.multiplemodels.repository;

import com.multiple.multiplemodels.model.Privilege;
import com.multiple.multiplemodels.model.enums.PrivilegeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByPrivilegeInfo(PrivilegeInfo privilegeInfo);
}
