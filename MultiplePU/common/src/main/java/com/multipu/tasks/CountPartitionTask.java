package com.multipu.tasks;

import com.gigaspaces.async.AsyncResult;
import org.openspaces.core.executor.DistributedTask;

import java.util.List;

/**
 * Created by ronz on 7/2/2014.
 */
public class CountPartitionTask implements DistributedTask<Integer, Long> {

  public Integer execute() throws Exception {
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