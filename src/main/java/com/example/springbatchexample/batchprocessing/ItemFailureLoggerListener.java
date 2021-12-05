package com.example.springbatchexample.batchprocessing;

import org.springframework.batch.core.listener.ItemListenerSupport;

import java.util.List;

public class ItemFailureLoggerListener extends ItemListenerSupport {

//    private static Log logger = LogFactory.getLog("item.error");

    public void onReadError(Exception ex) {
        System.out.println("Encountered error on read");
//        logger.error("Encountered error on read", e);
    }

    public void onWriteError(Exception ex, List items) {
//        logger.error("Encountered error on write", ex);
        System.out.println("Encountered error on write");
    }
}