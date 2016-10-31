package com.maketest.controller;

import com.maketest.dto.UserDTO;
import com.maketest.exceptions.UserNotFoundException;
import com.maketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user-registration", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO u) {
        UserDTO user = userService.register(u);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user-email", method = RequestMethod.POST)
    public String checkIfEmailExists(@RequestBody String email) {
        email = email.replace("\"", "");
        String retVal = userService.checkIfEmailExists(email);
        return retVal;
    }

    /*
    * Function checks if token exists in data base.
    * If exists, user profile is activated and user is redirected to login page.
    * */
    @RequestMapping(value = "/registration-confirmation", method = RequestMethod.GET)
    public ResponseEntity<String> registrationConfirm(@RequestParam String token) {
        boolean result = false;
        if (token != null) {
            userService.activateUser(token);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:8080/index.html");
        return new ResponseEntity<String>(headers, HttpStatus.SEE_OTHER);
    }

    @RequestMapping(value = "/login-session", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userToLogin) {
        UserDTO retVal = userService.login(userToLogin);
        if (retVal == null) {
            throw new UserNotFoundException(userToLogin.getEmail());
        }
        return new ResponseEntity<UserDTO>(retVal, HttpStatus.OK);
    }
}