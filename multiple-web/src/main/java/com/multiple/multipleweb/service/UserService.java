package com.multiple.multipleweb.service;

import com.multiple.multiplemodels.dto.UserModel;
import com.multiple.multiplemodels.model.Users;
import com.multiple.multiplemodels.model.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Users> getAllUsers();

    Users registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, Users user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    Users findUserByEmail(String email);

    void createPasswordResetTokenForUser(Users user, String token);

    String validatePasswordResetToken(String token);

    Optional<Users> getUserByPasswordResetToken(String token);

    void changePassword(Users user, String newPassword);

    boolean checkIfValidOldPassword(Users user, String oldPassword);
}
