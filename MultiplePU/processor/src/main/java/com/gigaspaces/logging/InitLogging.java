package com.gigaspaces.logging;

import org.slf4j.bridge.SLF4JBridgeHandler;

/**
 * Created by ronz on 8/26/2014.
 */
public class InitLogging {
    public void init() {
        System.err.println("IIIIIIIIII>>>>> Initializing JUL bridge. <<<<< IIIIII");
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}
