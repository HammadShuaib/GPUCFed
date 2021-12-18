package org.aksw.simba.quetsal.configuration;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import org.aksw.simba.quetsal.configuration.EndpointListFromDirectoryProvider.WatcherProc;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.joda.time.LocalTime;
import org.apache.jena.sparql.engine.binding.Binding;

import com.fluidops.fedx.Config;
import com.fluidops.fedx.FedX;
import com.fluidops.fedx.SummaryProvider;
import com.fluidops.fedx.structures.Endpoint;
import com.fluidops.fedx.trunk.parallel.engine.exec.BoundQueryTask;
import com.fluidops.fedx.trunk.parallel.engine.exec.TripleExecution;

public class CostFedSummaryProvider implements SummaryProvider {
//    static Logger log = LoggerFactory.getLogger(CostFedSummaryProvider.class);
    
    String path;
    CostFedSummary summary = null;
    
    public CostFedSummaryProvider() {
    	 System.out.println("This is here in CostFedSummaryProvider:"+LocalTime.now());
    	    /*
    	List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		ExecutorService exec = Executors.newWorkStealingPool();
		//ForkJoinPool exec = new ForkJoinPool();

			//System.out.println("These are the endpoints:"+endpoints+"--"+temp);
			
				Callable<Integer> c = new Callable<Integer>() {
					@Override
					public Integer call() throws Exception {
				//		 System.out.println("This is the binding in intermediate within call:"+temp+"--"+endpoints);

						
						return 1;
					}
				};
				tasks.add(c);
				try {
					exec.invokeAll(tasks);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				exec.shutdown();
			*/	rebuildSummary();
				
			 System.out.println("This is here in CostFedSummaryProvider end:"+LocalTime.now());
			    
    }

    
    @Override
    public Summary getSummary(FedX federation) {
    System.out.println("This is here in getSummary");
    	Config config = federation.getConfig();
        String path = config.getProperty("quetzal.fedSummaries");
        if (path == null) {
        	  System.out.println("This is here in getSummary1");
        	  
        	this.path = null;
            resetSummary();
            return summary;
        }
        if (!path.equals(this.path)) {
        	  System.out.println("This is here in getSummary2");
        	  
            this.path = path;
          //  registerWatcher();
            resetSummary();
            
            rebuildSummary();
        }
        return doGetSummary(); // to enable synchronized access
    }

    synchronized void resetSummary() {
        if (summary != null) {
            summary.close();
            summary = null;
        }
    }
    
    
    synchronized Summary doGetSummary() {
        if (summary != null) {
            summary.addref();
        }
        return summary;
    }
    
    synchronized void rebuildSummary() {
        try {
            doRebuildSummary();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    void doRebuildSummary() throws Exception {
        try {
            
            List<String> files = new ArrayList<String>();
            File file = new File(path);
            if (file.isDirectory()) {
                
                for (final File fileEntry : file.listFiles()) {
                    if (fileEntry.isDirectory()) continue;
                    if (!fileEntry.getName().endsWith(".ttl") && !fileEntry.getName().endsWith(".n3")) continue;
                    files.add(fileEntry.getPath());
                }
            } else {
               // log.info(file.getAbsoluteFile().getPath());
                files.add(path);
            }
            
          //  log.info("build summary for: " + files);
     //       ForkJoinPool fjp =new ForkJoinPoo
           
            CostFedSummary tempSummary = new CostFedSummary(files);
            if (summary != null) {
                summary.close();
            }
            summary = tempSummary;
        } catch (Throwable t) {
           System.err.println("can't load summary"+ t);
        }
    }
    
   
   
/*
    @Override
    public synchronized void close() {
        try {
            if (watchKey != null) {
                watchKey.cancel();
            }
            if (watcherThread != null) {
                watcherThread.interrupt();
                watcherThread.join();
                watcherThread = null;
            }
        } catch (Exception ex) {
       }
    }*/
}
