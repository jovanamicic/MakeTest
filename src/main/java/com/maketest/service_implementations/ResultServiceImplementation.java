package com.maketest.service_implementations;

import com.maketest.model.Result;
import com.maketest.repository.ResultRepository;
import com.maketest.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class ResultServiceImplementation implements ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Override
    public Result findOne(int id) {
        Result result = resultRepository.findOne(id);
        Result retVal = null;

        if (result!= null)
            retVal = result;
        else
            throw new IllegalArgumentException("There is no data with id: " + id);
        return retVal;
    }
}
