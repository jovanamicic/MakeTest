<<<<<<< HEAD
package com.maketest.repository;

import com.maketest.model.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Jovana Micic on 24-Oct-16.
 */
@Repository
public interface TestRepository extends PagingAndSortingRepository<Test, Integer> {
    Page<Test> findAll (Pageable pegeable);
}
=======
package com.maketest.repository;

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
public interface TestRepository extends PagingAndSortingRepository<Test, Integer> {
    Page<Test> findAll (Pageable pegeable);
    List<Test> findAll();
    List<Test> findByCategory(String category);
}
>>>>>>> 3e8b4f6b46df55a25ab9a29a2fa68ddb13b105a5
