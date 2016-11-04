package com.maketest.controller;

import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.exceptions.ExpiredTokenException;
import com.maketest.exceptions.UserEmailNotFoundException;
import com.maketest.exceptions.UserNotFoundException;
import com.maketest.exceptions.UserTokenNotFoundException;
import com.maketest.model.Session;
import com.maketest.service.SessionService;
import com.maketest.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URI;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SessionService sessionService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO u) {
        UserDTO user = userService.register(u);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user-email", method = RequestMethod.POST)
    public String checkIfEmailExists(@RequestBody String email) {
        email = email.replace("\"", "");
        String retVal = userService.checkIfEmailExists(email);
        if (retVal == null) {
            throw new UserEmailNotFoundException(email);
        }
        return retVal;
    }

    /*
    * Function checks if token exists in data base.
    * If exists, user profile is activated and user is redirected to login page.
    * */
    @RequestMapping(value = "/activation", method = RequestMethod.GET)
    public ResponseEntity<String> registrationConfirm(@RequestParam String token) {
        if (token == null) {
            throw new UserTokenNotFoundException(token);
        }
        userService.activateUser(token);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:8080/login");
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserDTO userToLogin, UriComponentsBuilder uriBuilder) {
        UserDTO retVal = userService.login(userToLogin);
        if (retVal == null) {  //TODO exceptions move to service
            throw new UserNotFoundException(userToLogin.getEmail());
        }
        String sessionToken = retVal.getUserSession().getSessionToken();
        URI location = uriBuilder.path("api/users/sessions/{token}").buildAndExpand(sessionToken).toUri();
        return ResponseEntity.created(location).build(); //creates resurs
    }

    @RequestMapping(value = "/sessions/{token}", method = RequestMethod.GET)
    public ResponseEntity<?> getToken(@PathVariable String token) {
        Session validToken = sessionService.getToken(token);
        if (validToken != null){
            return new ResponseEntity<Session>(validToken, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

        //Logout means DELETEing /sessions/{token}
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<UserDTO>(HttpStatus.OK);
    }

    @RequestMapping(value = "/userProfile", method = RequestMethod.GET)
    public ResponseEntity<UserProfileDTO> userProfile(@RequestHeader("mtt") String sessionToken) {
            UserProfileDTO retVal = userService.getUserProfile(sessionToken);
            if (retVal == null) {
                throw new UserNotFoundException("not found");
            }
            return new ResponseEntity<UserProfileDTO>(retVal, HttpStatus.OK);
    }

    //TODO implement POST /userProfile sending person

}