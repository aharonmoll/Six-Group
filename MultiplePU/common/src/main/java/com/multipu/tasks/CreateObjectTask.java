package com.multipu.tasks;

import com.gigaspaces.async.AsyncResult;
import org.openspaces.core.executor.DistributedTask;

import java.util.List;
import java.util.logging.Logger;

public class CreateObjectTask implements DistributedTask<Integer, Long> {
    private static Logger log = Logger.getLogger(String.valueOf(CreateObjectTask.class));
    private String name;

    public CreateObjectTask(String name) {
        this.name = name;
    }

    public Integer execute() throws Exception {
        log.info("Creating my new object with name = " + name);
        // set the routing key
        return 1;
    }


    public Long reduce(List<AsyncResult<Integer>> results) throws Exception {
        long sum = 0;
        for (AsyncResult<Integer> result : results) {
            if (result.getException() != null) {
                throw result.getException();
            }
            sum += result.getResult();
        }
        return sum;
    }
}