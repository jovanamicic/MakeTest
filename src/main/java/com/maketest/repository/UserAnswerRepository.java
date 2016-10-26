package com.maketest.repository;

import com.maketest.model.Result;
import com.maketest.model.UserAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */
@Repository
public interface UserAnswerRepository extends PagingAndSortingRepository<UserAnswer,Integer> {
    Page<UserAnswer> findAll (Pageable pegeable);
}
