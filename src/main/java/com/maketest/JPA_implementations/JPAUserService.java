package com.maketest.JPA_implementations;

import com.maketest.model.User;
import com.maketest.repository.UserRepository;
import com.maketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class JPAUserService implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findOne(int id) {
        User user = userRepository.findOne(id);
        User retVal = null;

        if(user!=null){
            retVal = user;
        }
        else
            throw new IllegalArgumentException("There is no data with id: " + id);
        return retVal;
    }
}
