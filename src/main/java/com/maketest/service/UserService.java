package com.maketest.service;

import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface UserService {
    User save(User user);
    User login (UserDTO userToRegister);
    String checkIfEmailExists(String email);
    boolean activateUser(String token);
    User getUserProfile(String token);
}
