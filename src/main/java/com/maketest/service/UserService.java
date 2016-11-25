<<<<<<< HEAD
package com.maketest.service;

import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface UserService {
    User findOne(String email);
    User save(User user);
    User update(User user);
    User login (UserDTO userToRegister);
    String checkIfEmailExists(String email);
    boolean activateUser(String token);
    User getUserProfile(String token);
}
=======
package com.maketest.service;

import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.model.User;

import java.util.List;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface UserService {
    User findOne(String email);
    User findOne(int id);
    User save(User user);
    User update(User user);
    User login (UserDTO userToRegister);
    boolean emailExists(String email);
    boolean activateUser(String token);
    User getUserProfile(String token);
    List<User> findAll();
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
