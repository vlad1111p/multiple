package com.multiple.multipleweb.service.impl;

import com.multiple.multiplemodels.dto.UserModel;
import com.multiple.multiplemodels.model.User;
import com.multiple.multiplemodels.model.VerificationToken;
import com.multiple.multiplemodels.repository.UserRepository;
import com.multiple.multipleweb.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User registerUser(UserModel userModel) {
        return null;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {

    }

    @Override
    public String validateVerificationToken(String token) {
        return null;
    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        return null;
    }


    @Override
    public void createPasswordResetTokenForUser(User user, String token) {

    }

    @Override
    public String validatePasswordResetToken(String token) {
        return null;
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.empty();
    }

    @Override
    public void changePassword(User user, String newPassword) {

    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return false;
    }
}
