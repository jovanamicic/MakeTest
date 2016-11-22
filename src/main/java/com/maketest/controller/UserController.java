package com.maketest.controller;

import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.exceptions.ExpiredTokenException;
import com.maketest.exceptions.UserEmailNotFoundException;
import com.maketest.exceptions.UserNotFoundException;
import com.maketest.exceptions.UserTokenNotFoundException;
import com.maketest.model.Session;
import com.maketest.model.User;
import com.maketest.service.SessionService;
import com.maketest.service.UserService;
import com.maketest.utility.MD5Hash;
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

    /* Creating new user
       Params: UserDTO : email, password, first name, last name.
       Return: UserDTO : id, email, password, first name, last name and status Created.
       Error : Returns bad request.
    */
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO u) {
        if (userService.emailExists(u.getEmail())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(u.getEmail());
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        String hashedPassword = "";
        if(u.getPassword() != null) {
            hashedPassword = MD5Hash.getMD5(u.getPassword());
        }
        user.setPassword(hashedPassword);
        user = userService.save(user);
        if(user != null) {
            return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * Function checks if token exists in data base.
    * If exists, user profile is activated and user is redirected to login page.
    * */
    @RequestMapping(value = "/activation", method = RequestMethod.GET)
    public ResponseEntity<String> registrationConfirm(@RequestParam String token) {
        userService.activateUser(token);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http://localhost:8080/login");
        return new ResponseEntity<String>(headers, HttpStatus.SEE_OTHER);
    }

    /* Login and creating new session for logged user.
       Params: UserDTO : email and password.
       Return: Status Created.
       Error : Bad request.
    */
    @RequestMapping(value = "/sessions", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserDTO userToLogin, UriComponentsBuilder uriBuilder) {
        User retVal = userService.login(userToLogin);
        if (retVal != null){
            String sessionToken = retVal.getUserSession().getSessionToken();
            URI location = uriBuilder.path("api/users/sessions/{token}").buildAndExpand(sessionToken).toUri();
            return ResponseEntity.created(location).build(); //creates resurs
        }
        else {
            //exception when email and password are not matching
            throw new UserNotFoundException(userToLogin.getEmail());
        }
    }

    /*
        Function for checking if token is valid.
        Params: String token.
        Return: User profile and status OK.
        Error: Status Not Found.
     */
    @RequestMapping(value = "/sessions/{token}", method = RequestMethod.GET)
    public ResponseEntity<UserProfileDTO> getToken(@PathVariable String token) {
        Session validToken = sessionService.getSession(token);
        if (validToken != null){
            User user = userService.getUserProfile(token);
            return new ResponseEntity<>(new UserProfileDTO(user),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*  Deleting session token after logging out.
        Params: String token.
        Return: Status OK.
        Error: Status Not Found.
    */
    @RequestMapping(value = "/sessions/{token}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> logout(@PathVariable String token)
    {
        Session session = sessionService.findBySessionToken(token);
        if (session != null){
            sessionService.remove(session.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /* Getting user profile.
       Params: int id of user.
       Return: user profile and status OK.
       Error: Status Unathorized or Not Found.
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserProfileDTO> userProfile(@RequestHeader("mtt") String sessionToken, @PathVariable int id) {
            User user = userService.findOne(id);
            if (user == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  //ne postoji user sa ovim id-om
            }
            else if(user.getUserSession().getSessionToken().equals(sessionToken)) {
                Session s = sessionService.getSession(sessionToken);
                if (s == null) {
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //znaci da sesija nije validna, prosao joj rok
                }
                return new ResponseEntity<>(new UserProfileDTO(user), HttpStatus.OK); //sve je ok vracam user-a
            }
            else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); //znaci da nije isti token usera i onaj koji je poslat
            }
    }

    /* Method updates user data.
       Params: UserDTO: firstName, lastName, password.
       Return: user profile and status OK.
       Error: status Bad request.
    */
    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO u){  //TODO ispraviti da user session je sessionDTO
        User user = userService.findOne(u.getEmail());
        if (user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (u.getPassword() != null) {
            String hashedPassword = MD5Hash.getMD5(u.getPassword());
            user.setPassword(hashedPassword);
        }
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());

        userService.update(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

}