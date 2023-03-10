package com.multiple.multipleweb.controller;

import com.multiple.multiplemodels.dto.UserModel;
import com.multiple.multiplemodels.model.User;
import com.multiple.multipleweb.service.UserService;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/email")
    public User getUserByEmail(@RequestAttribute("email") String email) {
        return userService.findUserByEmail(email);
    }

    @RequestMapping("/register")
    public User register(@RequestBody UserModel userModel) {
        return userService.registerUser(userModel);
    }

    @RequestMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }


}
