package com.maketest.service_implementations;

import com.maketest.model.Session;
import com.maketest.model.User;
import com.maketest.repository.SessionRepository;
import com.maketest.repository.UserRepository;
import com.maketest.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jovana Micic on 04-Nov-16.
 */
@Service
public class SessionServiceImplementation implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Session getSession(String token) {
        Session s = sessionRepository.findBySessionToken(token);
        if (s!= null && isExpired(s.getSessionExpire())==false) {
            return s;
        }
        else {
            return null;
        }
    }

    @Override
    public Session findBySessionToken(String token) {
        return sessionRepository.findBySessionToken(token);
    }

    @Override
    public void remove(int id) {
        Session s = sessionRepository.findOne(id);
        User u = userRepository.findByUserSession(s);
        u.setUserSession(null);
        userRepository.save(u);
        sessionRepository.delete(id);
    }

    /* Check if session token is still valid*/
    private boolean isExpired(Date tokenExpire){
        boolean retVal = false;
        if (tokenExpire.before(new Date())) {
            retVal = true;
        }
        return  retVal;
    }
}
