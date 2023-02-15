package com.multiple.multiplemodels.model;


import com.multiple.multiplemodels.model.enums.RoleInfo;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@jakarta.persistence.Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    //run the following query in postgresql
    //CREATE TYPE role_info AS ENUM ('USER', 'ADMIN' );
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    @Column(columnDefinition = "role_info")
    private RoleInfo roleInfo;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges;

    public Role(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleInfo=" + roleInfo +
                ", description='" + description + '\'' +
                ", users=" + users +
                ", privileges=" + privileges +
                '}';
    }
}
