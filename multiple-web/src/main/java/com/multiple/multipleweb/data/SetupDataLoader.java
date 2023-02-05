package com.multiple.multipleweb.data;


import com.multiple.multiplemodels.model.Privilege;
import com.multiple.multiplemodels.model.Role;
import com.multiple.multiplemodels.model.Users;
import com.multiple.multiplemodels.model.enums.RoleInfo;

import com.multiple.multipleweb.repository.PrivilegeRepository;
import com.multiple.multipleweb.repository.RoleRepository;
import com.multiple.multipleweb.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.Set;

import static com.multiple.multiplemodels.model.enums.RoleInfo.ADMIN;
import static com.multiple.multiplemodels.model.enums.RoleInfo.USER;


@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    public SetupDataLoader(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        Set<Privilege> adminPrivileges = Set.of(readPrivilege, writePrivilege);
        createRoleIfNotFound(ADMIN, adminPrivileges);
        createRoleIfNotFound(USER, Set.of(readPrivilege));

        Role adminRole = roleRepository.findByRoleInfo(ADMIN);
        Users user = new Users();
//      user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Set.of(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            RoleInfo name, Set<Privilege> privileges) {

        Role role = roleRepository.findByRoleInfo(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
