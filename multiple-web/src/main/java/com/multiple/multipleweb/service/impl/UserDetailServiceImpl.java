package com.multiple.multipleweb.service.impl;


import com.multiple.multiplemodels.model.Privilege;
import com.multiple.multiplemodels.model.Role;
import com.multiple.multiplemodels.model.Users;
import com.multiple.multiplemodels.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email);
        if (users == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                users.getEmail(), users.getPassword(), users.isEnabled(), true, true,
                true, getAuthorities(users.getRoles()));
    }

    private Set<? extends GrantedAuthority> getAuthorities(
            Set<Role> roles) {

        return getGrantedAuthorities(getPrivileges(roles));
    }

    private Set<String> getPrivileges(Collection<Role> roles) {

        Set<String> privileges = new HashSet<>();
        Set<Privilege> collection = new HashSet<>();
        roles.forEach(role -> {
            privileges.add(role.getRoleInfo().toString());
            collection.addAll(role.getPrivileges());
        });
        collection.forEach(privilege -> privileges.add(privilege.getName()));
        return privileges;
    }

    private Set<GrantedAuthority> getGrantedAuthorities(Set<String> privileges) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        privileges.forEach(privilege -> authorities.add(new SimpleGrantedAuthority(privilege)));
        return authorities;
    }
}
