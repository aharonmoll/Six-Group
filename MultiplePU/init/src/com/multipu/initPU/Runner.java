package com.multipu.initPU;

import com.gigaspaces.async.AsyncFuture;
import com.multipu.tasks.CountPartitionTask;
import com.multipu.tasks.CreateObjectTask;
import com.multipu.tasks.InitProxyTask;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

public class Runner {
    public static void main(String[] args) throws Exception {
        System.out.println("Start app");
        UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/spaceProcessor?locators=127.0.0.1");
        final GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).create();
        System.out.println("Got connection to processor proxy ... calling initialize task");
        gigaSpace.execute(new InitProxyTask());

        AsyncFuture<Long> execute = gigaSpace.execute(new CountPartitionTask());
        System.out.println("Partitions count is " + execute.get().longValue());

        AsyncFuture<Long> execute2 = gigaSpace.execute(new CreateObjectTask("Hello"));
        System.out.println("Object created in X partitions  " + execute2.get().longValue());
        System.out.println("Finish");
    }
}
