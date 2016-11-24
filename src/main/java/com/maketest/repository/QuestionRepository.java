<<<<<<< HEAD
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
=======
package com.maketest.repository;

import com.maketest.model.Question;
import com.maketest.model.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */
@Repository
public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer> {
    Page<Question> findAll (Pageable pegeable);
    List<Question> findByTestQuestions(Test test);
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
