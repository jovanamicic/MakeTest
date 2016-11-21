package com.maketest.service;

import com.maketest.model.Session;

/**
 * Created by Jovana Micic on 04-Nov-16.
 */
public interface SessionService {
    Session getSession(String token);
    Session findBySessionToken(String token);
    void remove(int id);

}
