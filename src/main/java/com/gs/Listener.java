package com.gs;

import org.openspaces.admin.pu.ProcessingUnitInstance;
import org.openspaces.admin.pu.ProcessingUnitPartition;
import org.openspaces.admin.pu.events.ProcessingUnitInstanceLifecycleEventListener;

/**
 * Created by aharon on 10/22/2014.
 */
public class Listener implements ProcessingUnitInstanceLifecycleEventListener {
    @Override
    public void processingUnitInstanceAdded(ProcessingUnitInstance processingUnitInstance) {
    }

    @Override
    public void processingUnitInstanceRemoved(ProcessingUnitInstance processingUnitInstance) {
        System.out.println("processingUnitInstance Was removed pu name = " +processingUnitInstance.getProcessingUnitInstanceName());
        ProcessingUnitPartition partition = processingUnitInstance.getPartition();
        System.out.println("number of partiotions are: "+partition.getInstances().length+" =======> "+processingUnitInstance.getProcessingUnitInstanceName());
        if( partition.getInstances().length==0) {
            System.out.println("Error for partition no partition available please shutdown system");
        }
    }
}
