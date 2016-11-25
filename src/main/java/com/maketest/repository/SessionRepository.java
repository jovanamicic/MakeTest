package com.maketest.repository;

import com.maketest.model.Session;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Jovana Micic on 04-Nov-16.
 */
public interface SessionRepository extends PagingAndSortingRepository<Session, Integer> {

    Session findBySessionToken(String sessionToken);
}
