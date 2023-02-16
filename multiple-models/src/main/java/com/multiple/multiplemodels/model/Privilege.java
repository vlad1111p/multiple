package com.multiple.multiplemodels.model;

import com.multiple.multiplemodels.model.enums.PrivilegeInfo;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
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
@Entity
@jakarta.persistence.Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //run the following query in postgresql
    //CREATE TYPE privilege_info AS ENUM ('READ_PRIVILEGE', 'WRITE_PRIVILEGE');
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    @Column(columnDefinition = "privilege_info")
    private PrivilegeInfo privilegeInfo;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;

    public Privilege(PrivilegeInfo privilegeInfo) {
        this.privilegeInfo = privilegeInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Privilege privilege = (Privilege) o;
        return id != null && Objects.equals(id, privilege.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", privilegeInfo=" + privilegeInfo +
                ", roles=" + roles +
                '}';
    }
}
