package com.maketest.repository;

import com.maketest.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */
@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer> {
    Page<Question> findAll (Pageable pegeable);
}
