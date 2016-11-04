package com.maketest.service_implementations;

import com.maketest.model.Session;
import com.maketest.repository.SessionRepository;
import com.maketest.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jovana Micic on 04-Nov-16.
 */
@Service
public class JPASessionService implements SessionService {

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public Session getToken(String token) {
        Session s = sessionRepository.findBySessionToken(token);
        if (s!= null && isExpired(s.getSessionExpire())==false) {
            return s;
        }
        else {
            return null;
        }
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
