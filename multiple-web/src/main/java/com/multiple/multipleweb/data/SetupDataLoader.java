package com.multiple.multipleweb.data;


import com.multiple.multiplemodels.model.Privilege;
import com.multiple.multiplemodels.model.Role;
import com.multiple.multiplemodels.model.User;
import com.multiple.multiplemodels.model.enums.PrivilegeInfo;
import com.multiple.multiplemodels.model.enums.RoleInfo;
import com.multiple.multiplemodels.repository.PrivilegeRepository;
import com.multiple.multiplemodels.repository.RoleRepository;
import com.multiple.multiplemodels.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    boolean alreadySetup = false;

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
        User user = new User();
//      user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Set.of(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByPrivilegeInfo(PrivilegeInfo.valueOf(name));
        if (privilege == null) {
            privilege = new Privilege(PrivilegeInfo.valueOf(name));
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    void createRoleIfNotFound(
            RoleInfo name, Set<Privilege> privileges) {

        Role role = roleRepository.findByRoleInfo(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
    }
}
