package com.gs;

import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.machine.Machine;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitDeployment;
import org.openspaces.admin.pu.ProcessingUnits;
import org.openspaces.admin.pu.events.ProcessingUnitInstanceLifecycleEventListener;
import org.openspaces.admin.space.Space;
import org.openspaces.admin.space.Spaces;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DoubleFailure
{

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException
    {
        ProcessingUnitDeployment deployment=null;
        AdminFactory factory = new AdminFactory();
        factory.addGroup("aharon");
        factory.addLocator("127.0.0.1");
        Admin admin = factory.createAdmin();
        GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne(5, TimeUnit.MINUTES);
        Space space = null;
        Thread.sleep(1000);
        if (space == null)
        {
            try
            {
                System.out.println("Deploying PU wait 8 seconds");
                deployment = new ProcessingUnitDeployment("mySpace.zip");
                gsm.deploy(deployment);
                Thread.sleep(8000);
            }
            catch (Exception e)
            {
                System.out.println("PU deployed already");
            }
        }

        ProcessingUnits processingUnits = admin.getProcessingUnits();
        for (ProcessingUnit pu : processingUnits)
        {
            System.out.println("pu= "+pu.getName()+" has number of planned instances: "+pu.getPlannedNumberOfInstances());
            System.out.println("pu= "+pu.getName()+" has number of active instances: "+pu.getInstances().length);
        }
        getSpaces(admin);
        int numOfInstances=0;
        Spaces spaces = admin.getSpaces();
        for (Space singleSpace : spaces) {
            numOfInstances = singleSpace.getInstances().length;
            System.out.println("PU: "+singleSpace.getName()+" has Number of instances = "+ numOfInstances);
        }
        numberOfcomponents(admin);
        //getMachineStatistics(admin);


        //ProcessingUnitStatusChangedEventManager statusEventMgr = admin.getProcessingUnits().getProcessingUnitStatusChanged();
        ProcessingUnitInstanceLifecycleEventListener processingUnitInstanceLifecycleEventListener = new Listener();
        admin.getProcessingUnits().addLifecycleListener(processingUnitInstanceLifecycleEventListener);
        //statusEventMgr.add(listener);

    }

    private static void getSpaces(Admin admin)
    {
        Spaces spaces = admin.getSpaces();

        for (Space singleSpace : spaces)
        {
            System.out.println("space = "+ singleSpace.getName());
        }
    }

    private static void numberOfcomponents(Admin admin)
    {
        System.out.println("gscCount "+ admin.getGridServiceContainers().getSize());
        System.out.println("gsmCount "+ admin.getGridServiceManagers().getSize());
        System.out.println("lusCount "+ admin.getLookupServices().getSize());
    }

    private static void getMachineStatistics(Admin admin)
    {
        Machine[] machines = admin.getMachines().getMachines();
        for (Machine machine : machines)
        {
            System.out.println("machineName "+ machine.getHostName());
            System.out.println("actualFreePhysicalMemorySizeInMB "+ machine.getOperatingSystem().getStatistics().getActualFreePhysicalMemorySizeInMB());
            System.out.println("cpuPerc "+ machine.getOperatingSystem().getStatistics().getCpuPerc());
        }
    }
}

            
