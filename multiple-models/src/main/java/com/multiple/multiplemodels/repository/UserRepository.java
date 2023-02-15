package com.multiple.multiplemodels.repository;

import com.multiple.multiplemodels.model.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @QueryHints({
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    Optional<User> findByEmail(String email);

    @QueryHints({
            @QueryHint(name = "org.hibernate.cacheable", value = "true")
    })
    Optional<User> findByUsername(String username);
}

