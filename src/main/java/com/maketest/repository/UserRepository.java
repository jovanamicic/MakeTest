<<<<<<< HEAD
package com.maketest.repository;

import com.maketest.model.Session;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.maketest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    Page<User> findAll (Pageable pegeable);
    User findByEmail (String email);
    User findByToken (String token);
    User findByEmailAndPassword (String email, String password);
    User findByUserSession(Session session);
}
=======
package com.maketest.repository;

import com.maketest.model.Session;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.maketest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    Page<User> findAll (Pageable pegeable);
    User findByEmail (String email);
    User findByToken (String token);
    User findByEmailAndPassword (String email, String password);
    User findByUserSession(Session session);
    List<User> findAll();
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
