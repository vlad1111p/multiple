package com.multiple.multiplemodels.model;


import com.multiple.multiplemodels.model.enums.RoleInfo;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //    CREATE TYPE role_info AS ENUM (
//        'USER', 'ADMIN'
//    )
    @Enumerated(EnumType.STRING)
    @Type( PostgreSQLEnumType.class)
    @Column(columnDefinition = "role_info")
    private RoleInfo roleInfo;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges;

    public Role(RoleInfo roleInfo){
        this.roleInfo = roleInfo;
    }
}
