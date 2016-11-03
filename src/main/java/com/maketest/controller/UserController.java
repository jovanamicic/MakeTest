package com.maketest.controller;

import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.exceptions.UserEmailNotFoundException;
import com.maketest.exceptions.UserNotFoundException;
import com.maketest.exceptions.UserTokenNotFoundException;
import com.maketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
@RestController
@RequestMapping(value = "/api/user")
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
        if (retVal == null){
            throw new UserEmailNotFoundException(email);
        }
        return retVal;
    }

    /*
    * Function checks if token exists in data base.
    * If exists, user profile is activated and user is redirected to login page.
    * */
    @RequestMapping(value = "/registration-confirmation", method = RequestMethod.GET)
    public ResponseEntity<String> registrationConfirm(@RequestParam String token) {
        if (token == null) {
            throw new UserTokenNotFoundException(token);
        }
        userService.activateUser(token);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:8080/login.html");
        return new ResponseEntity<String>(headers, HttpStatus.SEE_OTHER);
    }

    @RequestMapping(value = "/login-session", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userToLogin, HttpSession session) {
        UserDTO retVal = userService.login(userToLogin);
        if (retVal == null) {
            throw new UserNotFoundException(userToLogin.getEmail());
        }
        session.setAttribute("user", retVal); //setting user on session
        return new ResponseEntity<UserDTO>(retVal, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<UserDTO>(HttpStatus.OK);
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public ResponseEntity<UserProfileDTO> userProfile(HttpSession session){
        UserDTO loggedUser = (UserDTO) session.getAttribute("user");
        UserProfileDTO retVal = userService.getUserProfile(loggedUser);
        if (retVal == null) {
            throw new UserNotFoundException(loggedUser.getEmail());
        }
        return new ResponseEntity<UserProfileDTO>(retVal, HttpStatus.OK);
    }

}