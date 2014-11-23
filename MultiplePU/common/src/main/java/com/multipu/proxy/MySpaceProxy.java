package com.multipu.proxy;

import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.slf4j.LoggerFactory;

public class MySpaceProxy {
    private static Logger log = Logger.getLogger(MySpaceProxy.class.getName());
    private static org.slf4j.Logger slf4jLogger = LoggerFactory.getLogger(MySpaceProxy.class.getName());
    private GigaSpace space = null;

    public MySpaceProxy() {
        log.info("********************* Starting MySpaceProxy...");
//        initProxy();
    }

    public GigaSpace getSpace() {
        return space;
    }

    public void initProxy() {
        if (space == null) {
            log.info("********************* Init MySpaceProxy with remote spaceFeeder");
            slf4jLogger.info("********************* Init MySpaceProxy with remote spaceFeeder");
            UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/spaceFeeder?locators=127.0.0.1");
            space = new GigaSpaceConfigurer(configurer).create();
            log.info("********************* Proxy initialized");
            slf4jLogger.info("********************* Proxy initialized");
        } else {
            log.info("********************* Proxy has already initialized before");
            slf4jLogger.info("********************* Proxy has already initialized before");
        }
    }
}
