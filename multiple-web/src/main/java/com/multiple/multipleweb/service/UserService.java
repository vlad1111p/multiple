package com.multiple.multipleweb.service;

import com.multiple.multiplemodels.dto.UserModel;
import com.multiple.multiplemodels.model.User;
import com.multiple.multiplemodels.model.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User findUserById(Long id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);


    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);
}
