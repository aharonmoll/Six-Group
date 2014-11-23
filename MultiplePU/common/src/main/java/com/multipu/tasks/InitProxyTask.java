package com.multipu.tasks;

import com.gigaspaces.async.AsyncResult;
import com.multipu.proxy.MySpaceProxy;
import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.DistributedTask;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InitProxyTask implements DistributedTask<Integer, Boolean> {
    private static Logger log = Logger.getLogger(InitProxyTask.class.getName());
    private static org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(InitProxyTask.class.getName());


  public Integer execute() throws Exception {
      MySpaceProxy ourProxy = new MySpaceProxy();
      ourProxy.initProxy();
      GigaSpace space = ourProxy.getSpace();
      log.info("****** Our proxy is " + space);
      return 1;
  }

  public Boolean reduce(List<AsyncResult<Integer>> results) throws Exception {
    return true;
  }
}