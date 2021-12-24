package com.fluidops.fedx.trunk.parallel.engine.exec;

import java.util.Vector;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.locks.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.fluidops.fedx.structures.QueryInfo;

import org.apache.jena.ext.com.google.common.collect.ArrayListMultimap;
//import org.apache.log4j.Level;
//import org.apache.log4j.LogManager;
import org.eclipse.rdf4j.query.algebra.StatementPattern;

//import com.fluidops.fedx.trunk.config.Config;
import com.fluidops.fedx.trunk.description.RemoteService;
import com.fluidops.fedx.trunk.graph.Edge;
import com.fluidops.fedx.trunk.parallel.engine.ParaConfig;
import com.fluidops.fedx.trunk.parallel.engine.ParaEng;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.BindJoin;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator;
import com.fluidops.fedx.trunk.parallel.engine.main.BGPEval;
import com.fluidops.fedx.trunk.parallel.engine.main.StageGen;
import com.fluidops.fedx.trunk.parallel.engine.opt.ExhOptimiser;
import com.fluidops.fedx.trunk.stream.engine.util.QueryUtil;
//import com.fluidops.fedx.trunk.stream.engine.util.HypTriple;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.resultset.ResultSetMem;
import org.apache.jena.sparql.syntax.ElementGroup;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.Query;

import com.fluidops.fedx.algebra.StatementSource;
import com.fluidops.fedx.optimizer.Optimizer;
import com.fluidops.fedx.optimizer.SourceSelection;
import com.fluidops.fedx.structures.Endpoint;

public class TripleExecution {

	// private static final org.apache.log4j.Logger log =
	// LogManager.getLogger(TripleExecution.class.getName());
	static int i11 = 0;
	static int  iteration=0;
	QueryTask current1;
	static int size=0;
	public static String ab;
private Triple triple;
	static public List<Binding> results =new ArrayList<>();// Collections.synchronizedList(new HashSet<Binding>());
	public static Integer taskcounter = 0;
	public int blocksize = 40;
	ReentrantLock lock = new ReentrantLock();
static List<String> v = new ArrayList<>();
//	Stream<BoundQueryTask> x ;
	BoundQueryTask current3;
	static int IterationNumber = -1;
	static ConcurrentHashMap<List<Endpoint>, TripleExecution> processedEndPoint = new ConcurrentHashMap<>();

	public TripleExecution(Triple triple) {
		// log.info("This is now in TripleExecution1");

		this.triple = triple;
	}

	public synchronized void addBindings(Collection<Binding> bindings) {
		// synchronized (results) {
	//	for(Binding b:bindings)
	//	if(!BoundQueryTask.NonRepeat.contains(b))
		//				continue;
		results.addAll(bindings);
	//	if(BoundQueryTask.index==0)
		//	BoundQueryTask.NonRepeat.addAll(results);
	}

	public boolean isFinished() {
		// log.info("This is now in TripleExecution3:" + taskcounter);

		// synchronized (taskcounter) {
		if (taskcounter == 0) {
			// log.info("TE333333 Task Counter:" + true);

			return true;
		} else {
			// log.info("TE333333 Task Counter:" + false);
			return false;
		}
		// }

	}

	public int taskMinus() {
//	synchronized (taskcounter) {
		taskcounter--;
		// }

		return 0;
	}

	public Triple getTriple() {
//		log.info("This is now in TripleExecution5");

		return triple;
	}

	public List<Binding> exec(Set<Binding> intermediate, String string) {
		results  = new ArrayList<>();
		Optimizer opt = new Optimizer();
		if (intermediate == null) {
		System.out.println("This is value of intermediate2222:"+triple);

			List<Endpoint> relevantSources;
			relevantSources = opt.getEndpointE(triple);
 System.out.println("This is value of intermediate2222:"+relevantSources);

			for (Endpoint endpoints : relevantSources) {
				taskcounter++;
			}
			// IterationNumber++;
			// log.info("This is in Triple Execution service size:"
			// +relevantSources.size()+"--"+triple+"--"+taskcounter+"--"+IterationNumber);
			for (Endpoint endpoints : relevantSources) {

				int k = 0;

				if (intermediate == null) {
					// System.out.println("This is value of intermediate4444444:"+triple +"--"+
					// BGPEval.IsRightQuery+"--"+BGPEval.IsLeftQuery);
/*
					if (BGPEval.OptionalQuery == false && BGPEval.IsLeftQuery == false
							&& BGPEval.IsRightQuery == false) {
						// System.out.println("This is here in
						// intermediateeeeeeeeeeee33333333333333333333:"+triple +"--"+
						// BGPEval.IsRightQuery+"--"+BGPEval.IsLeftQuery);
						ForkJoinPool ex = new ForkJoinPool();
						ex.submit(() -> {
//								//System.out.println("This is in performance for:"+this+"--"+endpoints);

							current1 = new QueryTask(this, endpoints);
							try {

								current1.runTask();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}).join();
	//					ForkJoinTask.invokeAll();
						// ForkJoinTask.invokeAll();

						/*
						 * try { Thread.sleep(500); } catch (InterruptedException e) { // TODO
						 * Auto-generated catch block e.printStackTrace(); }
						 

						ex.shutdown();

					} else {*/
//					log.info("This is here in intermediateeeeeeeeeeee2222222222222222222222:"+triple);					

					BGPEval.bindEstimator = new HashSet<>();
					List<EdgeOperator> f=	QueryTask.BushyTreeTripleCreation(triple);
					
					Query query = new Query();
					ElementGroup elg = new ElementGroup();
					// BGPEval.JoinGroupsList.stream().
					
					if (f != null) {
						// int ced=0;

						for (EdgeOperator btt : f) {
				//if(ced<ghi) {
							//// System.out.println("This is the BushyTreeTrple in QueryTask:"+btt);
							elg.addTriplePattern(btt.getEdge().getTriple());
//					ced++;
				//}		
						}
					} else
						elg.addTriplePattern(triple);
					
					query.setQueryResultStar(true);
					query.setQueryPattern(elg);
					query.setQuerySelectType();
					List<String> list = new ArrayList<String>();
					
					Set<String> additives = new HashSet<>();
					//	System.out.println("This is the query in Triple Execution:"+f);
					//	Set<String> lem=new HashSet<>();
				
					Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(query.toString());
					while (m.find())
					    list.add(m.group(1)); // Add .repla
						
						for(String q:list)
							{
							
							if(q.startsWith("?"))
								additives.add(q.substring(q.indexOf("?")).replace("\n", ""));
									else if(q.contains("http")) {
										additives.add(q.substring(q.indexOf("<")).replace(">", "").replace("<", "").replace("\n", ""));
												
									}
									else if(q.contains("\"")) {
										additives.add(q.substring(q.indexOf("\"")).replace("\n", ""));
												
									}
							}
						
						
							
							BGPEval.bindEstimator.addAll(additives);
							System.out.println("This is bindestimator:"+BGPEval.bindEstimator);
							
							if(ParaEng.Distinct=="Yes")
								ab="SELECT DISTINCT ";
								else
							ab="SELECT ";
							for(String be:BGPEval.bindEstimator) {
								if(be.startsWith("?"))
									ab=ab+" "+be;
							}
							System.out.println("This is bindestimator123345567:"+ab);
								
							size=0;
						ForkJoinPool ex = new ForkJoinPool();
						try {
							ex.submit(() -> {
								System.out.println("Total time elapsed in starting:"+LocalTime.now());
								
							System.out.println("This is in performance for:"+this+"--"+endpoints+"--"+string);
								current1 = new QueryTask(this, endpoints,string);
								
								try {
								current1.runTask(0);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}).get();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ExecutionException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						System.out.println("This is in performance end for:"+this+"--"+endpoints+"--"+LocalTime.now());
					//	for(Binding r:results)
					//	System.out.println("Results.size() for hashJoin:" + results.size());
											
//						ForkJoinTask.invokeAll();

						ex.shutdown();	

					}
				}
	//		}
		} else {
			if (intermediate.isEmpty()) {

				return new ArrayList<Binding>();
			}
			BGPEval.bindEstimator = new HashSet<>();
	List<EdgeOperator> f=	QueryTask.BushyTreeTripleCreation(triple);
	
	Query query = new Query();
	ElementGroup elg = new ElementGroup();
	// BGPEval.JoinGroupsList.stream().
	
	if (f != null) {
		// int ced=0;

		for (EdgeOperator btt : f) {
//if(ced<ghi) {
			//// System.out.println("This is the BushyTreeTrple in QueryTask:"+btt);
			elg.addTriplePattern(btt.getEdge().getTriple());
//	ced++;
//}		
		}
	} else
		elg.addTriplePattern(triple);
	
	query.setQueryResultStar(true);
	query.setQueryPattern(elg);
	query.setQuerySelectType();
//	////System.out.println("---------------QueryTask buildQUery---------------: "+query+"--"+elg.getElements()+"--"+query.getQueryPattern()+"--"+query.getBindingValues()+"--"+query.getResultURIs());

		
		//	bindEstimator
			List<Endpoint> relevantSources1 = Optimizer.getEndpointE(triple);
			long start = System.currentTimeMillis();
			if( !BGPEval.urik.isEmpty() || BGPEval.urik.size()>0) {				
BGPEval.vat=new HashMap<>();
BGPEval.vat.put(8887, 0);
BGPEval.vat.put(8888, 0);
BGPEval.vat.put(8889, 0);

			}

			List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
			TripleExecution temp = this;
			ExecutorService exec = Executors.newWorkStealingPool();
			//ForkJoinPool exec = new ForkJoinPool();
//	List<Endpoint> al = new ArrayList<>();
			List<String> list = new ArrayList<String>();
			
			Set<String> additives = new HashSet<>();
			//	System.out.println("This is the query in Triple Execution:"+f);
			//	Set<String> lem=new HashSet<>();
		
			Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(query.toString());
			while (m.find())
			    list.add(m.group(1)); // Add .repla
				
				for(String q:list)
					{
					
					if(q.startsWith("?"))
						additives.add(q.substring(q.indexOf("?")).replace("\n", ""));
							else if(q.contains("http")) {
								additives.add(q.substring(q.indexOf("<")).replace(">", "").replace("<", "").replace("\n", ""));
										
							}
							else if(q.contains("\"")) {
								additives.add(q.substring(q.indexOf("\"")).replace("\n", ""));
										
							}
					}
				
				
					
					BGPEval.bindEstimator.addAll(additives);
					if(ParaEng.Distinct=="Yes")
						ab="SELECT DISTINCT ";
						else
					ab="SELECT ";
					for(String be:BGPEval.bindEstimator) {
						if(be.startsWith("?"))
							ab=ab+" "+be;
					}
				HashMap<String,Endpoint> rs= new HashMap<>();
			//	rs.addAll(Collections.reverse());	
				 size=	relevantSources1.size();
					//rs.addAll();
				for(Endpoint r:relevantSources1) {
					
					rs.put(r.getId(),r);
				}
				TreeMap<String, Endpoint> sorted = new TreeMap<>(rs); 
				Set<Entry<String, Endpoint>> mappings = sorted.entrySet();

				System.out.println("These are the order of exe:"+sorted.values());
				 iteration=0;
				for (Endpoint endpoints : sorted.values()) {
					System.out.println("These are the endpoints999999:"+iteration);
					
					//if(size>1 && i==0) {
					//i++;
					//	continue;}
//				if(BoundQueryTask.index>0)
//				if(al.contains(endpoints))
//					continue;
				System.out.println("These are the endpoints:"+endpoints+"--"+temp);
				System.out.println("This is here in part22222222222222222222222222222222:"+LocalTime.now());
				
				for (Binding binding : intermediate) {
				//	if(v.contains(endpoints+"--"+binding))
				//		continue;
				//	System.out.println("66666This is the BushyTreeTrple in QueryTask:"+binding);
					Callable<Integer> c = new Callable<Integer>() {
						@Override
						public Integer call() throws Exception {
					//		//	int currentValue = BGPEval.vat.get(Integer.valueOf(endpoints.getEndpoint().replaceAll("[^0-9]", "")));

							
						//	for(String be:BGPEval.bindEstimator)
						//		System.out.println("This is bindEstimator:"+be);
					//		System.out.println("This is here in part333333333:"+LocalTime.now());
											
							BoundQueryTask current3 = new BoundQueryTask(temp, endpoints, binding,string);
							try {
								//int currentValue = BGPEval.vat.get(Integer.valueOf(endpoints.getEndpoint().replaceAll("[^0-9]", "")));
								
					//	if(BGPEval.vat)
								if( !BGPEval.urik.isEmpty() || BGPEval.urik.size()>0) {
								//	System.out.println("THis is the execution problem 8989898989898989:"+temp+"--"+endpoints+"--"+binding);								
								current3.runTask(BGPEval.vat.put(Integer.valueOf(endpoints.getEndpoint().replaceAll("[^0-9]", "")), BGPEval.vat.get(Integer.valueOf(endpoints.getEndpoint().replaceAll("[^0-9]", "")))+1));
								}
								else
								{	
									current3.runTask(0);
								}
								return 1;

							} catch (InterruptedException e) {
								e.printStackTrace();
								return 0;
							}
						
						}
					};
					tasks.add(c);
//					v.add(endpoints+"--"+binding);
				}
			
			//	try {
					try {
						List<Future<Integer>> results = exec.invokeAll(tasks);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long elapsed = System.currentTimeMillis() - start;
					System.out.println(String.format("Total time elapsed:%d ms", elapsed));
					System.out.println("Total time elapsed:"+LocalTime.now());
			//	} finally {
			//	}
					//NonRepeat.addAll(results);
					//BoundQueryTask.index++;
					iteration++;
					
			}
			
			exec.shutdown();
			

		}

		System.out.println("Results.size()" + results.size());
		System.out.println("Total time elapsed final:"+LocalTime.now());
	//	for(Binding r:results)
	//		System.out.println("This is result:"+r);
		return results;
	}

	@Override
	public String toString() {
		return triple.toString();
	}

	
}
