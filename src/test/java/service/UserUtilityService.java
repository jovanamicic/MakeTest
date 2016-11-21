package service;

import builder.UserBuilder;
import com.maketest.dto.UserDTO;
import com.maketest.model.User;
import com.maketest.repository.UserRepository;
import com.maketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Jovana Micic on 18-Nov-16.
 */
@Service
public class UserUtilityService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    public UserDTO saveUser(UserDTO userToLogin){
        //dodaje u bazu novu osobu
        User retVal = userService.login(userToLogin);
        //dobija iz baze tu osobu
        User user = userRepository.findByEmail(retVal.getEmail());
        return new UserDTO(user);
    }
}
