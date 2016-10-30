package com.maketest.service;

import com.maketest.dto.UserDTO;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface UserService {
    UserDTO register (UserDTO newUser);
    UserDTO login (UserDTO userToRegister);
    String checkIfEmailExists(String email);
    boolean activateUser(String token);
}
