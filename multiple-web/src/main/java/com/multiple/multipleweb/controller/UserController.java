package com.multiple.multipleweb.controller;

import com.multiple.multiplemodels.dto.UserModel;
import com.multiple.multiplemodels.model.Users;
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
    public Users getUserByEmail(@RequestAttribute("email") String email) {
        return userService.findUserByEmail(email);
    }

    @RequestMapping("/register")
    public Users register(@RequestBody UserModel userModel) {
        return userService.registerUser(userModel);
    }

    @RequestMapping("/all")
    public Iterable<Users> getAllUsers() {
        return userService.getAllUsers();
    }


}
