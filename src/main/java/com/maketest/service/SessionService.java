<<<<<<< HEAD
package com.maketest.service;

import com.maketest.model.Session;

/**
 * Created by Jovana Micic on 04-Nov-16.
 */
public interface SessionService {
    Session getToken(String token);
    Session findBySessionToken(String token);
    void remove(int id);
}
=======
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
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
