package com.multiple.multipleweb.service.impl;

import com.multiple.multiplemodels.dto.UserModel;
import com.multiple.multiplemodels.model.VerificationToken;
import com.multiple.multiplemodels.model.Users;
import com.multiple.multiplemodels.repository.UserRepository;
import com.multiple.multipleweb.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Users registerUser(UserModel userModel) {
        return null;
    }

    @Override
    public void saveVerificationTokenForUser(String token, Users user) {

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
    public Users findUserByEmail(String email) {
        return null;
    }

    @Override
    public void createPasswordResetTokenForUser(Users user, String token) {

    }

    @Override
    public String validatePasswordResetToken(String token) {
        return null;
    }

    @Override
    public Optional<Users> getUserByPasswordResetToken(String token) {
        return Optional.empty();
    }

    @Override
    public void changePassword(Users user, String newPassword) {

    }

    @Override
    public boolean checkIfValidOldPassword(Users user, String oldPassword) {
        return false;
    }
}
