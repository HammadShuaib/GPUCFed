package com.fluidops.fedx.trunk.parallel.engine.exec;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.script.Bindings;

//import org.apache.log4j.Level;
//import org.apache.log4j.LogManager;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.apache.jena.sparql.core.Var;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.joda.time.LocalTime;

import com.fluidops.fedx.trunk.config.Config;
import com.fluidops.fedx.trunk.description.RemoteService;
import com.fluidops.fedx.trunk.graph.Edge;
import com.fluidops.fedx.trunk.graph.Vertex;
import com.fluidops.fedx.trunk.parallel.engine.ParaEng;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.BindJoin;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.HashJoin;
import com.fluidops.fedx.trunk.parallel.engine.main.BGPEval;
import com.fluidops.fedx.trunk.parallel.engine.main.StageGen;
import com.fluidops.fedx.trunk.stream.engine.util.Logging;
import com.fluidops.fedx.trunk.stream.engine.util.QueryUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.fluidops.fedx.Summary;
import com.fluidops.fedx.optimizer.Optimizer;
import com.fluidops.fedx.structures.Endpoint;

import org.apache.commons.collections.MultiMap;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.ARQException;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingHashMap;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.sparql.engine.http.QueryExceptionHTTP;
import org.apache.jena.sparql.pfunction.library.alt;
import org.apache.jena.sparql.resultset.ResultSetException;
import org.apache.jena.sparql.resultset.ResultSetMem;
import org.apache.jena.sparql.syntax.ElementGroup;
import com.fluidops.fedx.structures.QueryInfo;

public class QueryTask {
//	private	static final org.apache.log4j.Logger logger = LogManager.getLogger(QueryTask.class.getName());
	Multimap<String, ResultSet> pro = ArrayListMultimap.create();;
	Multimap<String, Binding> proExt = ArrayListMultimap.create();;
	public static int wfilter=0;
	public static int bounded=0;
	public static int falsified=0;
	public static int unExecuted=0;
	public static int notInclined=0;
	//	boolean finished = false;
	AtomicBoolean finished = new AtomicBoolean(false);
	static List<EdgeOperator> ae = new Vector<>();
	static int isExecuted = 0;
	static volatile ConcurrentHashMap<Triple, Integer> singleVariable = new ConcurrentHashMap<>();
	static int singleVariableURL = 0;
//private final Object lock = new Object();
	public static HashMap<String, String> IsSingleStatement = new HashMap<>();
	protected static Triple triple;
	static private TripleExecution issuer;
	protected static Endpoint service;
	private int timeout = 0;
	public static int IsLiteral = 0;
	public static int IsURLProper = 0;
	static int m = 0;
	static int num = 0;
	static int ghi;
	static String ext;
	List<EdgeOperator> BushyTreeTriple = Collections.synchronizedList(new ArrayList<>());

	public static int ja = 0;
	// static HashMap<String,Integer> GroupEdgeOperatorGroupProcessed=new
	// HashMap<>();
	public static int isBound = 0;
	private int MAXTRIES = 3;
//public static Map<Triple,Integer> CompletionValue=Collections.synchronizedMap(new HashMap<Triple,Integer>()) ;;
//Map<Integer, String> Res = new HashMap<>();

	List count = new Vector<>();

//RepositoryConnection getSummaryConnection() {
	// return
	// ((Summary)(Optimizer.qInfo.getFedXConnection().getSummary())).getConnection();
//}	
	public QueryTask(TripleExecution te, Endpoint service,String string) {
		this.triple = te.getTriple();
		this.service = service;
		this.issuer = te;
		QueryTask.ext = string;
		// ////System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+this.triple+"--"+this.service+"--"+this.issuer);

	}

	public synchronized void setPro(String st, ResultSetMem rsm) {
		pro.put(st, rsm);
	}

	public synchronized Multimap<String, ResultSet> getPro() {
		return pro;
	}

	public QueryTask(Triple triple, Endpoint service, int timeout) {
		//// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		this.triple = triple;
		this.service = service;
		this.timeout = timeout;
	}

	public synchronized List<EdgeOperator> getBusyTreeTriple() {
		return BushyTreeTriple;
	}

	public void runTask(Integer integer) throws InterruptedException {
		
		/*
		 * org.apache.log4j.Logger.getRootLogger().setLevel(Level.OFF);
		 * org.apache.log4j.Logger.getLogger(
		 * "com.fluidops.fedx.trunk.parallel.engine.exec.QueryTask").setLevel(Level.OFF)
		 * ; org.apache.log4j.Logger.getLogger("httpclient.wire.level").setLevel(Level.
		 * FATAL);
		 * org.apache.log4j.Logger.getLogger("httpclient.wire").setLevel(Level.FATAL);
		 * 
		 * org.apache.log4j.Logger.getLogger("org.apache.http.wire").setLevel(Level.
		 * FATAL);
		 * org.apache.log4j.Logger.getLogger("org.apache.http.impl.client").setLevel(
		 * Level.FATAL);
		 * org.apache.log4j.Logger.getLogger("org.apache.http.impl.conn").setLevel(Level
		 * .FATAL);
		 * org.apache.log4j.Logger.getLogger("org.apache.http.client").setLevel(Level.
		 * FATAL);
		 */

		// System.out.println("This is in start of QueryTask");
		java.util.logging.Logger.getLogger("org.apache.http.wire").setLevel(java.util.logging.Level.SEVERE);
		java.util.logging.Logger.getLogger("org.apache.http.headers").setLevel(java.util.logging.Level.SEVERE);
		System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
		System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
		System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");
	
		// ////System.out.println("Normal QueryTask is being used:"+triple);
		// ForkJoinPool fjp = new ForkJoinPool(3);
		// fjp.invoke(fjp.submit(()->{

//	if(BGPEval.IsLeftQuery==false&&BGPEval.IsRightQuery==false)
		// else BushyTreeTriple=null;
		////// System.out.println("These are the created new Triples:"+BushyTreeTriple);

//ForkJoinPool fjp1 = new ForkJoinPool();	

		// fjp.shutdownNow();
//synchronized(lock) {
//	ForkJoinPool fjp1 = new ForkJoinPool();	

//	fjp1.submit(()->
//	{	
		if( BGPEval.urik.isEmpty() || BGPEval.urik.size()==0) {
		if(TripleExecution.size>1)
		if(TripleExecution.iteration==0)
			return ;
		}
		BushyTreeTriple = BushyTreeTripleCreation(triple);
	//	System.out.println("These are the triple final:"+BushyTreeTriple);
		Query q = buildQuery();
		
		
			List<Binding> bindings = new ArrayList<>();

			ResultSet rs = execQuery(q,integer,ext);

			bindings = postProcess(rs);
	//	for(Binding b:bindings)
		//	System.out.println("These are the bindings:"+bindings.size());
			synchronized (issuer) {
			issuer.addBindings(bindings);

			if (IsURLProper == 1)
				if (bindings.size() < 5)
					IsURLProper = 0;
			if (IsLiteral == 1)
				if (bindings.size() < 5)
					IsLiteral = 0;

		}

		

	}

	public boolean isFinished() {
		//// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask4!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return finished.get();
	}

	protected Query buildQuery() {
		////// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask5!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Query query = new Query();
		ElementGroup elg = new ElementGroup();
		// BGPEval.JoinGroupsList.stream().
		if (getBusyTreeTriple() != null) {
			// int ced=0;

			for (EdgeOperator btt : getBusyTreeTriple()) {
//if(ced<ghi) {
				//// System.out.println("This is the BushyTreeTrple in QueryTask:"+btt);
				elg.addTriplePattern(btt.getEdge().getTriple());
//		ced++;
//}		
			}
		} else
			elg.addTriplePattern(triple);
		query.setQueryResultStar(true);
		query.setQueryPattern(elg);
		query.setQuerySelectType();
//		////System.out.println("---------------QueryTask buildQUery---------------: "+query+"--"+elg.getElements()+"--"+query.getQueryPattern()+"--"+query.getBindingValues()+"--"+query.getResultURIs());
		isBound = 0;
		return query;
	}

	protected ResultSet execQuery(Query query, int integer,String ext) {
		int unionCount = 0;
		System.out.println("This is now creating extended query:" +ext);
		String inta=ext;
		try {
			HashSet<String> numOfNodes = new HashSet<>();
		/*	if (QueryTask.isBound == 0) {
			int count = 0;
			num = 0;
			String[] bbb = query.toString().split(" ");

			for (String bb : bbb)
				if (bb.startsWith("?"))
					numOfNodes.add(bb.replaceAll(" ", "").replaceAll("\n", ""));
			////// System.out.printlnln("This is numOfNodes:"+numOfNodes);
			num = numOfNodes.size();
			for (int i = 0; i < query.toString().length(); i++) {
				if (query.toString().charAt(i) == '?') {
					count++;
					//// System.out.printlnln("This is good one:"+query.toString().charAt(i));
				}
			}
			// If 2 URI present in 2 triple pattern in query then ignore 2nd URI
			singleVariable.put(triple, 0);
			if (count == 1)
				if (singleVariable.containsKey(triple))
					if (singleVariable.containsValue(0))
						if (query.toString().contains("\"")) {
							IsLiteral = 1;
							singleVariable.replace(triple, 1);
						}

			if (count == 1 && singleVariableURL == 0)
				if (query.toString().contains("<")) {
					IsURLProper = 1;
					singleVariableURL++;
				}
			}*/
			//// System.out.printlnln("This is occurenece of
			//// ?:"+count+"--"+IsSingleStatement);

			boolean error = false;
			String url = service.getEndpoint();
			String queryString;
			//// System.out.println("This is the filter out of OP:"+ParaEng.opq);
			//// System.out.println("This is the Query:"+query);
			/*
			 * for(int j=0;j<xyz2.length;j++) {
			 * //System.out.println("These are the character sequence:"+xyz1[i]+"--"+xyz2[j]
			 * ); if(xyz1[i].equals(xyz2[j])) { if(xyz1[i].startsWith("?"))
			 * //System.out.println("These are the character sequence in Clause:"+xyz1[i]);
			 * } }
			 */

			//System.out.println("ThiOP12:"+query);
			QueryEngineHTTP qexec = null;
			int a=0;
			// if(ParaEng.opq!=null) {
		

			// qString = qString.replace("}", "} LIMIT 100");
	ResultSetMem result = null ;	
	if(ParaEng.FiltereqLarge.size()==0 ||  ParaEng.FiltereqLarge.isEmpty()) {
	if(QueryTask.wfilter==1)
		{		
		 String q=TripleExecution.ab+" "+ query.toString().substring(query.toString().indexOf("*")+1);
			
		qexec = new QueryEngineHTTP(url, q);
		if (BoundQueryTask.isBound == 1)			
			
		System.out.println("This is now creating extended query:" + query+ "--" + url+"--"+ext);
		 result = new ResultSetMem(qexec.execSelect());// }).join();

		if (BoundQueryTask.isBound == 1)
			setPro(query.toString(), result);

		}	
			else
			{	
				
			//	 System.out.println("This is the string now in connection8989898989:"+query.toString()+"--"+ParaEng.InnerFilterSimple.isEmpty()+"--"+ParaEng.InnerFilterSimple.size());
					
				String qString="";
				 if(!ParaEng.InnerFilterSimple.isEmpty() ||  ParaEng.InnerFilterSimple.size()>0)
				 {		
				for(Entry<String, String> ifs:ParaEng.InnerFilterSimple.entrySet()) 
					if(triple.getSubject().getName().toString().contains(ifs.getKey().substring(1))
						|| triple.getObject().getName().toString().contains(ifs.getKey().substring(1)) ||
						triple.getSubject().getName().toString().contains(ifs.getValue().substring(1))
								|| triple.getObject().getName().toString().contains(ifs.getValue().substring(1))	)
					{
						 String q1=TripleExecution.ab+" "+ query.toString().substring(query.toString().indexOf("*")+1);

						qString=q1.toString();
					}
					else
			 qString = QueryExtension(query);
				 }
				 else
					 {
						
					 // System.out.println("This is the string now in connection:"+query.toString());
				
					 qString = QueryExtension(query);
						System.out.println("This is now creating extended query01:" + qString + "--" + url+"--"+ext);
						
					 }
			//	 qString=TripleExecution.ab+" "+ qString.substring(qString.indexOf("*")+1);
					
				qexec = new QueryEngineHTTP(url, qString);
			//	if (BoundQueryTask.isBound == 0)			
				System.out.println("This is now creating extended query:" + qString + "--" + url+"--"+ext);
				System.out.println("This is now creating extended query98889:" + qexec);
				
				result = new ResultSetMem(qexec.execSelect());// }).join();
			// while(result.hasNext())
			//		System.out.println("This is now result next next:" + result.next());
				
		//	if (BoundQueryTask.isBound == 1)
		//		setPro(qString, result);
	
			}
	}
	
	else
	{
		
		System.out.println("This is the value of bounded invincible:"+inta);		
		bounded=0;
		Set<String> availables=new HashSet<>();
		for(String asd: query.toString().split(" ")) {
			if(asd.startsWith("?"))
				availables.add(asd);
		
	}
	
	//	for(String av:availables)
	//	
		
		for(String av:availables)
		for(Entry<HashMap<String,String>,String> fe:ParaEng.FiltereqLarge.entrySet())
			for(Entry<String, String>  fextension1:fe.getKey().entrySet())
			{	
				
				
				
				if(ext.equals(inta))
				
				{
					System.out.println("This is the microscope:"+inta+"--"+av);
					
			if(inta.contains(av.replace("\n", "")))
				//	if(ext.equals(inta))
					{				
				System.out.println("This is the microscope123:"+ext+"--"+fextension1.getKey());
				
				bounded=1;break;}}
				}
//System.out.println("This is the perforamcen execution:"
//		+ query);
		
		 String qString ="";
		 
		 //	System.out.println("This is the value of bounded09022211:"+query+"--"+bounded);		

		 if(!ParaEng.InnerFilterSimple.isEmpty() || ParaEng.InnerFilterSimple.size()>0)
		{for(Entry<String, String> ifs:ParaEng.InnerFilterSimple.entrySet()) 
			if(triple.getSubject().getName().toString().contains(ifs.getKey().substring(1))
			|| triple.getObject().getName().toString().contains(ifs.getKey().substring(1)) ||
				triple.getSubject().getName().toString().contains(ifs.getValue().substring(1))
						|| triple.getObject().getName().toString().contains(ifs.getValue().substring(1))	)
				qString=query.toString();
			else
			{	if(bounded==1)
	 qString = QueryExtensionLarge(query,inta,bounded);
				else
					{
				
						//		System.out.println("This is not bounded3333333333333333:"+q.toString());
//								if (queryString == null)
								falsified=0;
								System.out.println("This is now the working style here");
									 String q1=TripleExecution.ab+" "+ query.toString().substring(query.toString().indexOf("*")+1);

								//	return q1.toString();
								
								qString=q1.toString();}
					}
		}
		 else 
			 {
			 System.out.println("This is now in bounded within querytask:"+bounded);
				if(bounded==1)
					 qString = QueryExtensionLarge(query,inta,bounded);
								else
									{
								
										//		System.out.println("This is not bounded3333333333333333:"+q.toString());
//												if (queryString == null)
												falsified=0;
												System.out.println("This is now the working style here");
												 String q1=TripleExecution.ab+" "+ query.toString().substring(query.toString().indexOf("*")+1);

												qString=q1.toString();}
									}
			 
			 //	System.out.println("This is the value of bounded:"+qString+"--"+ext+"--"+bounded+"--"+ParaEng.InnerFilterSimple);		
		//if(qString.contains("FILTER"))		{ 
		// qString=TripleExecution.ab+" "+ qString.substring(qString.indexOf("*")+1);
		qexec = new QueryEngineHTTP(url, qString);
	//	if (BoundQueryTask.isBound == 1)			
		System.out.println("This is now creating extended query:" + qString+ "--" + url+"--"+ext+"--"+bounded);
		 result = new ResultSetMem(qexec.execSelect());// }).join();

		if (BoundQueryTask.isBound == 1)
			setPro(query.toString(), result);
		//}
	
	//	while(result.hasNext())
//			System.out.println("This is the biggest hurdle now:"+result.next());
				 
	}
		// }
			// else {
//	//System.out.println("This is now creating extended query:"+qString+"--"+url);

//		qexec = new QueryEngineHTTP(url,query);
			// }
			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask62!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
			// ");

			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask
			// execQuery 2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+url+"--"+qexec);
//				//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask execQuery 2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: ");
			// qexec.toString().contains("UNION");
	//		if (Config.log)
	//			Logging.out();// record the number of outgoing requests
			// qexec.addParam("timeout", String.valueOf(timeout));
			int n = 0;
			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask
			// execQuery 3!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+qexec.toString() );
			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask6!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
			// "+qexec);

			// while (n < MAXTRIES) {

			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask61!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
			// "+MAXTRIES);
			m++;

//	System.out.println("THis is object:"+query);

//	ReadWriteLock lock = new ReentrantReadWriteLock();
		//	if (QueryTask.isBound == 0)

			//
//					ForkJoinPool fjp = new ForkJoinPool();
			// fjp.submit(()->{

			// lock.writeLock().lock();
			// try {
			// try {
			// Thread.sleep(1);
			// } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			// } finally {
			// lock.writeLock().unlock();
			// }
			// if(BoundQueryTask.isBound==1)

		//	 if(BoundQueryTask.isBound==1)
//			 while(result.hasNext())
	//		 System.out.println("This is now the bindings:"+result.nextBinding()+"--"+BoundQueryTask.task);

			// if(isBound==1)
			// for(int i1=0;i1<BoundQueryTask.bindings.size();i1++)
			// System.out.println("This is now the
			// bindings:"+BoundQueryTask.bindings.get(i1));
			// result1=new ResultSetMem(result);
			// pro.computeIfAbsent(qString,k->new ResultSetMem(qexec.execSelect()));
			// //put(qString,result);
//			if (BoundQueryTask.isBound == 1)
//				setPro(qString, result);
			// pro.put(qString, result); //put(qString,result);
		//	 for(Entry<String, ResultSet> pr:pro.entries())
	//		 {String ar=pr.getValue().nextBinding().toString();
	//		 System.out.println("This is the problem here44444:"+pr.getKey()+"--"+ar);
	//	 }

//					fjp.shutdownNow();
			// result1=result;
			// ResultSet result1 = result;

			// TupleQuery tupleQuery = conn.prepareTupleQuery(QueryLanguage.SPARQL,
			// query.toString());
			//// System.out.println(queryString);
			// TupleQueryResult result1 = tupleQuery.evaluate();

			// System.out.println(pro);

			/*
			 * try { while(result1.hasNext()) {
			 * //System.out.println("This is the second type query for result:"+result1.next
			 * ()); } } finally { result1.close(); }
			 */
			// if (Config.log)
			// Logging.in(((ResultSetMem) result).size());
			// while(result.hasNext()) {
			// a=result.next();
			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask4
			// execQuery 4!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
			// "+result.next().varNames()+"--"+result.next());
			// }
			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask4
			// execQuery 4!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
			// "+qexec.execDescribe()+"--"+qexec);
			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!QueryTask4 execQuery
			// 4!!!!!!!!!!!!!: "+m);
			// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask4
			// execQuery 4!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+qexec.getContext()
			// +"--"+qexec);

			// m=0;
		//	if (error) {
		//		result = new ResultSetMem();
		//		System.err.println("Error:\nremote service: " + url + "\nquery:\n" + query);
		//	}
			//// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask
			//// execQuery 5!!!!!!!!!!!!:"+result);

			// if(QueryTask.isBound==1) {
			// for(Entry<Map<Map<Node, Integer>, Node>, Node>
			// b1:BoundQueryTask.bin.entrySet())
			// for(Var q:query.getValuesVariables())
			// System.out.println("This is experiment1:"+b1);
			// System.out.println("This is experiment1:"+b1);

			// postProcess(result);
			// for(Binding q1:query.getValuesData())
			// System.out.println("This is experiment2:"+q1);
			// for(String q2:query.getResultVars())
			// System.out.println("This is experiment3:"+q2);
			// }
			qexec.close();

			// ArrayList<ResultSet> rs = new ArrayList<>();
			// rs.add(result);
			return result;

		} catch (NullPointerException e) {
			// System.out.println(
			// "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask execQuery
			// 4!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: ");
		} catch (ResultSetException e) {
		}
		return null;
	}

	public synchronized List<Binding> postProcess(ResultSet rs) {
		System.out.println("!!!!!!!!!!!!!!QueryTask7!!!!!!!!!!!!!!!!!!!!"+num);
		List<Binding> results = new ArrayList<Binding>();
	//	int i = 0;
		Binding a;
	//	ResultSet b;
//	if(BoundQueryTask.isBound==0)

		while (rs.hasNext()) {
			a = rs.nextBinding();
	//		Pattern regex = Pattern.compile("[?=\\)][?= ][?=\\(]");
	//		int numA = regex.split(a.toString()).length;
 //System.out.println("These are the equations:"+a);
	//		if (numA == num) {
//	System.out.println("This is working set:"+a);
				results.add(a);
		//	}
		}
		return results;
	}
	/*
	 * public static boolean isPrime(int n) { for(int i = 2;2*i<n;i++) { if(n%i==0)
	 * { return false; } } return true; }
	 */
	/*
	 * static int divsum(int n) { int sum = 0; for (int i = 1; i <= (Math.sqrt(n));
	 * i++) { if (n % i == 0) {
	 * 
	 * if (n / i == i) { sum = sum + i; } else { sum = sum + i; sum = sum + (n / i);
	 * } } } return sum; }
	 */

	public static String QueryExtension(Query q) {
		String FilterString = null;
		int countQmark = 0;
		// if(ParaEng.opq==null)
		// return q.toString();
		// FilterString=ParaEng.Filter.toString();
		String queryString = null;
		
		 if(ParaEng.Filtereq==null) 
		 {			FilterString = " ";
		if (FilterString == " ")
			return q.toString();
		 }
		 else {
		for(Entry<HashMap<String, String>, String> fa:ParaEng.Filtereq.entrySet()) {
			int breaking = 0;
			
			for(Entry<String, String> fa1:fa.getKey().entrySet()) {
		String extension = null;
		String extension1 = null;

//		if (fa != " ")
		int numberofLiterals=0;
		for(String fa2:fa1.getKey().split(" "))
		if(fa2.contains("?"))
			numberofLiterals++;
		if(numberofLiterals==2)
			return q.toString();
			extension = fa1.getKey().toString().substring(fa.toString().indexOf("FILTER")-1,
					fa1.getKey().length());
	//	if (fa != " ")
			if(fa.toString().contains("FILTER ("))
			extension1 = fa1.getKey().toString().substring(fa.toString().indexOf("FILTER")+7,
					fa1.getKey().length());
			else if(fa.toString().contains("FILTER("))
				extension1 = fa1.getKey().toString().substring(fa.toString().indexOf("FILTER")+8,
						fa1.getKey().length());
		System.out.println("These are the character sequence in Clause0:"+extension1);

		//String[] Extension = extension1.toString().split(" ");
//			////System.out.printlnln("These are the character sequence in Clause0:"+q);
	//	System.out.println("These are the character sequence in Clause0.2:"+extension);

		String[] query = q.toString().split(" ");

//			for(int i =0 ; i<query.length;i++)
		/*for (String ext : Extension)
			if (ext.contains("?"))
				countQmark++;

		if (countQmark == 2)
			for (String qa : query)
				for (String ex : Extension) {
					String qq = qa.replaceAll("[(),]", "");
					String e = ex.replaceAll("[(),]", "");
					if (qq.equals(e) && (!qq.isEmpty())) {
						isExecuted++;
						// //System.out.printlnln("These are the character sequence in
						// Clause0.1111:"+qa.replaceAll("[(),]","")+"--"+ex.replaceAll("[(),]","")+"--"+Extension);

					}
				}*/
//			//System.out.printlnln("These are the character sequence in Clause0.1:"+countQmark+"--"+isExecuted);

		 queryString = null;
	//	for (int i = 0; i < Extension.length; i++) {
			// ////System.out.printlnln("These are the character sequence in
			// Clause:"+Extension[i]);
		//	if (!Extension[i].equals("regex") &&( Extension[i].startsWith("(?") || Extension[i].startsWith("?") || Extension[i].startsWith("str")
		//			|| Extension[i].startsWith("regex") ||
			//		Extension[i].startsWith("(xsd") || Extension[i].startsWith("xsd"))) {
					String NewQuery = " " + extension + " }";
					
					for (int j = 0; j < query.length - 1; j++) {

						////// System.out.printlnln("These are the character sequence in
						////// Clause35:"+Extension[i]);
			//		System.out.println("THis is here in extension00000:"+extension1.toString());
					
					
						if (query[j].startsWith("?")) {
							if (extension1.replace("\n", "").contains(query[j].replace("\n", ""))) {
								
					////			|| query[j].startsWith("regex") ||
					////			query[j].startsWith("(xsd") || query[j].startsWith("xsd")) {
							////// System.out.printlnln("These are the character sequence in
							////// Clause36:"+query[j]);
							////// System.out.printlnln("These are the character sequence in
							////// Clause37:"+Extension[i]+"--"+Extension[i].substring(4,
							////// Extension[i].length()-1));

							// If both filter elements are Nodes
							/*if (countQmark == 2 && (!extension1.toString().contains("EXISTS") && !extension1.toString().contains("NOT EXISTS"))) {
								if ((extension1.toString().startsWith("str") || extension1.toString().startsWith("regex"))
										&& (isExecuted > 1)) {
									if (query[j].equals(extension1.toString().substring(4, extension1.toString().length() - 1))) {
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
									}
								}
								System.out.println("This is startwith problem:");
								if ((extension1.toString().startsWith("?") || extension1.toString().startsWith("(?"))
										&& (isExecuted > 1)) {
									if (query[j].equals(extension1.toString().replaceAll("[(),]", ""))) {
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
									}
								}
							}
							// If one filter element is node
							else {*/
/*								if (extension1.toString().startsWith("str")) {
									if (query[j].equals(extension1.toString().substring(4, extension1.toString().length() - 1))) {

										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
									}
								}
								
								if ((extension1.toString().startsWith("regex") || (extension1.toString().startsWith("regex(")))) {
									
									String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(",")).replace("[()]", "");
									String ji = query[j];
									System.out.println("THis is here in extension111111:"+e+"--"+ji);
									
									if (ji.equals(e)) {
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
													}
														
								}*/
						//		System.out.println("THis is here in extension:"+Extension[i]+"--"+query[j]);
						//		for(String e:extension1.toString().split(" "))
									
						//		
			//String[] v=		extension1.split(" ");
			//for(String ee:v) {	
				//System.out.println("This is the best of problems232.001:"+ee.replaceAll("\\s+",""));
				
		//	}
			
		//	for(String ee:v) {	
	//String e1=ee.replaceAll("\\s+","");
	System.out.println("This is the best of problems232:"+extension1+"--"+extension1);
	//if(extension1.length()==0 ||extension1.length()==1  || extension1.e("="))
	//	continue;
			if (  extension1.toString().contains("=")||  extension1.toString().contains("<")|| extension1.toString().contains(">") || extension1.toString().startsWith("(xsd") || 
					extension1.toString().contains("xsd") || extension1.toString().startsWith("xsd") || extension1.toString().startsWith("( xsd")
					|| 	extension1.toString().contains("LANG(") || extension1.toString().contains("DATATYPE(") 
					|| 	extension1.toString().contains("LANG (") || extension1.toString().contains("DATATYPE (")) {
				System.out.println("This is digging deep0.01:");
				String e ="";		
			if(extension1.contains("="))
				e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("=")).replaceAll("[^A-Za-z]+", "");
			else if(extension1.contains(">"))
				e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(">")).replaceAll("[^A-Za-z]+", "");
				else if(extension1.contains("<"))
					e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("<")).replaceAll("[^A-Za-z]+", "");
				
			String qe[]=q.toString().split(" ");
				List<String> b = new ArrayList<>();
				for(String qq:qe)
					if(qq.startsWith("?"))
						b.add(qq.substring(1).replace("\n", ""));
				for(String qe1:b)		
				{
						System.out.println("This is digging deep:-)"+e+"--"+qe1);
					if(qe1.equals(e))//.substring(query[j].indexOf("?"),query[j].indexOf("=")).replaceAll("[^A-Za-z]+", "");
					{	
							System.out.println("This is digging deep:"+e+"--"+qe1);
							
						//	if (ji.equals(e)) {
								queryString = q.toString().substring(0, q.toString().length() - 2)
										.concat(NewQuery);
								breaking=1;
								break;
					}
				}
							queryString=queryString.replace(",", "");
							if(queryString.contains("xsd"))
								queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
	//System.out.println("This is query null:"+queryString);
							if(breaking==1)
						break;
			}
					//	breaking=1;
					//	break;

												
			else if (extension1.toString().contains(" IN ") || extension1.toString().contains(" NOT IN ") ) {
				String e="";
				if(extension1.contains(" NOT "))
				 e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("NOT")).replace(")", "").replace("(","").replace(" ", "");
				else
					 e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("IN")).replace(")", "").replace("(","").replace(" ", "");
					
				String ji = query[j].replace("\n", "");
			//	System.out.println("THis is here in extension111111:"+e+"--"+ji);
				
				if (ji.equals(e)) {
			//		System.out.println("THis is here in extension222222:"+e+"--"+ji);
					
					queryString = q.toString().substring(0, q.toString().length() - 2)
							.concat(NewQuery);
					queryString=queryString.replace(",", "");
					if(queryString.contains("xsd"))
						queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
		breaking=1;
				break;
				}
				
			
				
			}



			else if (extension1.toString().contains("EXISTS") ||
					extension1.toString().contains("COALESCE")|| extension1.toString().contains("CONTAINS")|| extension1.toString().contains("NOT EXISTS") ) {
				Set<String> clause=new HashSet<>();
				for(String e:extension1.toString().split(" "))
				{ 
//					System.out.println("This is the best of problems232:"+e);
					
					if(e.startsWith("?"))
				{clause.add(e.replace(",", ""));
	//			System.out.println("This is the best of problems:"+e);
				}
				}
					for(String e:clause)
					if (query[j].replace("\n", "").equals(e.replace("\n", ""))) {
		//			System.out.println("THis is here in extension222222:"+e+"--"+query[j]);
					
					queryString = q.toString().substring(0, q.toString().length() - 2)
							.concat(NewQuery);
					queryString=queryString.replace(",", "");
					if(queryString.contains("xsd"))
						queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
		
					breaking=1;
					break;
				
					}
				
			
			}
			
			
			
								else if (extension1.toString().contains(",")  && (!extension1.toString().startsWith("?") 
										&&  !extension1.toString().contains("=")
										&&  !extension1.toString().contains("<") && !extension1.toString().contains(">") )
									||	extension1.toString().contains("STRDT(") ||	extension1.toString().contains("STRLANG(")
									||	extension1.toString().contains("STRSTARTS(") ||	extension1.toString().contains("STRENDS(")
									||	extension1.toString().contains("CONTAINS(")||	extension1.toString().contains("STRBEFORE(")
									||	extension1.toString().contains("STRAFTER(") ||extension1.toString().contains("LANGMATCHES(")
									||  extension1.toString().contains("REGEX(") ||extension1.toString().contains("REGEX (")						
									||	extension1.toString().contains("STRDT (") ||	extension1.toString().contains("STRLANG (")
									||	extension1.toString().contains("STRSTARTS (") ||	extension1.toString().contains("STRENDS (")
									||	extension1.toString().contains("STRBEFORE (")
									||	extension1.toString().contains("STRAFTER (") ||extension1.toString().contains("LANGMATCHES (")
									
									) {
								
									String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(",")).replace(")", "").
											replace("(","").replace(" ", "");
									String ji = query[j].replace("\n", "");
									System.out.println("THis is here in extension12121212:"+e+"--"+ji);
									
									if (ji.equals(e)) {
										System.out.println("THis is here in extension222222:"+e+"--"+ji);
										
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
										queryString=queryString.replace(",", "");
										if(queryString.contains("xsd"))
											queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
							breaking=1;
									break;
									}
									
									if (breaking == 1) {
										breaking=0;
																	break;
																}
								
								}
								
								else if ((!extension1.toString().startsWith("?") && 
										!extension1.toString().contains(",") 
										&&  !extension1.toString().contains("=")
										&&  !extension1.toString().contains("<") && !extension1.toString().contains(">"))||
										extension1.toString().startsWith("ABS(") || extension1.toString().contains("ROUND(") ||
												extension1.toString().contains("CEIL(") ||extension1.toString().contains("FLOOR(")||
												extension1.toString().contains("BOUND(") ||
												extension1.toString().contains("YEAR(") || extension1.toString().contains("MONTH(")||
												extension1.toString().contains("DAY(") || extension1.toString().contains("HOURS(") || 
												extension1.toString().contains("MINUTES(") || extension1.toString().contains("SECONDS(") 
										|| extension1.toString().contains("STRLEN(") ||  extension1.toString().contains("UCASE(") ||
										extension1.toString().contains("isIRI(") || extension1.toString().contains("isBlank(") ||
										extension1.toString().contains("isLiteral(") || extension1.toString().contains("isNumeric(") ||
										 extension1.toString().contains("str(") ||
										extension1.toString().contains("LCASE(") ||
										extension1.toString().contains("STRLEN (") ||  extension1.toString().contains("UCASE (") ||
										extension1.toString().contains("LCASE (") ||
										extension1.toString().contains("ABS (") || extension1.toString().contains("ROUND (") ||
										extension1.toString().contains("BOUND (") ||
										extension1.toString().contains("isIRI (") || extension1.toString().contains("isBlank (") ||
										extension1.toString().contains("isLiteral (") || extension1.toString().contains("isNumeric (") ||
										 extension1.toString().contains("str (") ||
										extension1.toString().contains("CEIL (") ||extension1.toString().contains("FLOOR (")||
										extension1.toString().contains("YEAR (") || extension1.toString().contains("MONTH (")||
										extension1.toString().contains("DAY (")|| extension1.toString().contains("HOURS (") || 
										extension1.toString().contains("MINUTES (") || extension1.toString().contains("SECONDS (") 
										) {
									
									String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(")")).replace(")", "").
											replace("(","").replace(" ", "");
									String ji = query[j].replace("\n", "");
									System.out.println("THis is here in extension12121212:"+e+"--"+ji);
									
									if (ji.equals(e)) {
										System.out.println("THis is here in extension222222:"+e+"--"+ji);
										
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
										queryString=queryString.replace(",", "");
										if(queryString.contains("xsd"))
											queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
							breaking=1;
									break;
									}
									
									if (breaking == 1) {
										breaking=0;
																	break;
																}
								}

								
								else {									
		//							String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(",")).replace("[()]", "");
			//				//		String ji = query[j];
									System.out.println("THis is here to skip:");
									
				//					if (ji.equals(e)) {
					//					System.out.println("THis is here in extension555555555555121212:"+ q+"--"+NewQuery);
										
					//					queryString = q.toString().substring(0, q.toString().length() - 2)
					//							.concat(NewQuery);
					//					if(queryString.contains("xsd"))
					//						queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
							
							//		}
								
								}
							
							//}
							if (breaking == 1) {
	breaking=0;
								break;
							}
					}
					if (breaking == 1) {
breaking=0;
						break;
					}
			}
//					}
		//			if (breaking == 1) {
		//				break;
		//			}
				//}
			//}
			// return queryString;
		
				if (breaking == 1) {
					break;
				}
		
			}	
					if (breaking == 1) {
						break;
					}
			
					if (breaking == 1) {
						break;
					}
			
			}
			if (breaking == 1) {
				break;
			}
	 
		}
		

		 }
//			////System.out.printlnln("This is the new query:"+queryString);
		if (queryString == null) {
			 String q1=TripleExecution.ab+" "+ q.toString().substring(q.toString().indexOf("*")+1);

			return q1.toString();
		}
		else
			return queryString;

	}

	
	public static String QueryExtensionLarge(Query q,String string, int bounded) {
		String FilterString = null;
		int countQmark = 0;
		// if(ParaEng.opq==null)
		// return q.toString();
		// FilterString=ParaEng.Filter.toString();
		String queryString = null;
		
	
		
		String extension = null;
		String extension1 = null;

//		if (fa != " ")
			extension = string;
	//	if (fa != " ")
			if(extension.toString().contains("FILTER ("))
			extension1 = extension.toString().substring(extension.toString().indexOf("FILTER")+7,
					extension.length());
			else if(extension.toString().contains("FILTER("))
				extension1 = extension.toString().substring(extension.toString().indexOf("FILTER")+8,
						extension.length());
		//System.out.println("These are the character sequence in Clause0:"+extension);

		String[] Extension = extension1.toString().split(" ");
//			////System.out.printlnln("These are the character sequence in Clause0:"+q);
	//	System.out.println("These are the character sequence in Clause0.2:"+extension);

		String[] query = q.toString().split(" ");

//			for(int i =0 ; i<query.length;i++)

//			//System.out.printlnln("These are the character sequence in Clause0.1:"+countQmark+"--"+isExecuted);

		 queryString = null;
		int breaking=0;
	//	for (int i = 0; i < Extension.length; i++) {
			// ////System.out.printlnln("These are the character sequence in
			// Clause:"+Extension[i]);
		//	if (!Extension[i].equals("regex") &&( Extension[i].startsWith("(?") || Extension[i].startsWith("?") || Extension[i].startsWith("str")
		//			|| Extension[i].startsWith("regex") ||
			//		Extension[i].startsWith("(xsd") || Extension[i].startsWith("xsd"))) {
					String NewQuery = " " + extension + " }";
					
					for (int j = 0; j < query.length - 1; j++) {

						////// System.out.printlnln("These are the character sequence in
						////// Clause35:"+Extension[i]);
			//		System.out.println("THis is here in extension00000:"+extension1.toString()+"--"+query[j]);
						
						if (query[j].startsWith("(?") || query[j].startsWith("?") || query[j].startsWith("str")
								|| query[j].startsWith("regex") ||
								query[j].startsWith("(xsd") || query[j].startsWith("xsd")) {								
					////			|| query[j].startsWith("regex") ||
					////			query[j].startsWith("(xsd") || query[j].startsWith("xsd")) {
							////// System.out.printlnln("These are the character sequence in
							////// Clause36:"+query[j]);
							////// System.out.printlnln("These are the character sequence in
							////// Clause37:"+Extension[i]+"--"+Extension[i].substring(4,
							////// Extension[i].length()-1));

							// If both filter elements are Nodes
							/*if (countQmark == 2 && (!extension1.toString().contains("EXISTS") && !extension1.toString().contains("NOT EXISTS"))) {
								if ((extension1.toString().startsWith("str") || extension1.toString().startsWith("regex"))
										&& (isExecuted > 1)) {
									if (query[j].equals(extension1.toString().substring(4, extension1.toString().length() - 1))) {
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
									}
								}
								System.out.println("This is startwith problem:");
								if ((extension1.toString().startsWith("?") || extension1.toString().startsWith("(?"))
										&& (isExecuted > 1)) {
									if (query[j].equals(extension1.toString().replaceAll("[(),]", ""))) {
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
									}
								}
							}
							// If one filter element is node
							else {*/
/*								if (extension1.toString().startsWith("str")) {
									if (query[j].equals(extension1.toString().substring(4, extension1.toString().length() - 1))) {

										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
									}
								}
								
								if ((extension1.toString().startsWith("regex") || (extension1.toString().startsWith("regex(")))) {
									
									String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(",")).replace("[()]", "");
									String ji = query[j];
									System.out.println("THis is here in extension111111:"+e+"--"+ji);
									
									if (ji.equals(e)) {
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
													}
														
								}*/
						//		System.out.println("THis is here in extension:"+Extension[i]+"--"+query[j]);
						//		for(String e:extension1.toString().split(" "))
									
						//		
			String[] v=		extension1.split(" ");
			//for(String ee:v) {	
				//System.out.println("This is the best of problems232.001:"+ee.replaceAll("\\s+",""));
				
		//	}
			
		//	for(String ee:v) {	
	//String e1=ee.replaceAll("\\s+","");
	System.out.println("This is the best of problems232:"+extension1+"--"+extension1);
	//if(extension1.length()==0 ||extension1.length()==1  || extension1.e("="))
	//	continue;
			if (extension1.toString().contains("=") || extension1.toString().startsWith("(xsd") || 
					extension1.toString().contains("xsd") || extension1.toString().startsWith("xsd") || extension1.toString().startsWith("( xsd")
					|| 	extension1.toString().contains("LANG(") || extension1.toString().contains("DATATYPE(") 
					|| 	extension1.toString().contains("LANG (") || extension1.toString().contains("DATATYPE (")) {
				System.out.println("This is digging deep0.01:");
				String e ="";		
			if(extension1.contains("="))
				e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("=")).replaceAll("[^A-Za-z]+", "");
			else if(extension1.contains(">"))
				e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(">")).replaceAll("[^A-Za-z]+", "");
				else if(extension1.contains("<"))
					e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("<")).replaceAll("[^A-Za-z]+", "");
				
			String qe[]=q.toString().split(" ");
				List<String> b = new ArrayList<>();
				for(String qq:qe)
					if(qq.startsWith("?"))
						b.add(qq.substring(1).replace("\n", ""));
				for(String qe1:b)		
				{
						System.out.println("This is digging deep:-)"+e+"--"+qe1);
					if(qe1.equals(e))//.substring(query[j].indexOf("?"),query[j].indexOf("=")).replaceAll("[^A-Za-z]+", "");
					{	
							System.out.println("This is digging deep:"+e+"--"+qe1);
							
						//	if (ji.equals(e)) {
								queryString = q.toString().substring(0, q.toString().length() - 2)
										.concat(NewQuery);
								breaking=1;
								break;
					}
				}
							queryString=queryString.replace(",", "");
							if(queryString.contains("xsd"))
								queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
	//System.out.println("This is query null:"+queryString);
							if(breaking==1)
						break;
			}
					//	breaking=1;
					//	break;

												
			else if (extension1.toString().contains(" IN ") || extension1.toString().contains(" NOT IN ") ) {
				String e="";
				if(extension1.contains(" NOT "))
				 e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("NOT")).replace(")", "").replace("(","").replace(" ", "");
				else
					 e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("IN")).replace(")", "").replace("(","").replace(" ", "");
					
				String ji = query[j].replace("\n", "");
			//	System.out.println("THis is here in extension111111:"+e+"--"+ji);
				
				if (ji.equals(e)) {
			//		System.out.println("THis is here in extension222222:"+e+"--"+ji);
					
					queryString = q.toString().substring(0, q.toString().length() - 2)
							.concat(NewQuery);
					queryString=queryString.replace(",", "");
					if(queryString.contains("xsd"))
						queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
		breaking=1;
				break;
				}
				
			
				
			}



			else if (extension1.toString().contains("EXISTS") ||
					extension1.toString().contains("COALESCE")|| extension1.toString().contains("CONTAINS")|| extension1.toString().contains("NOT EXISTS") ) {
				Set<String> clause=new HashSet<>();
				for(String e:extension1.toString().split(" "))
				{ 
//					System.out.println("This is the best of problems232:"+e);
					
					if(e.startsWith("?"))
				{clause.add(e.replace(",", ""));
	//			System.out.println("This is the best of problems:"+e);
				}
				}
					for(String e:clause)
					if (query[j].replace("\n", "").equals(e.replace("\n", ""))) {
		//			System.out.println("THis is here in extension222222:"+e+"--"+query[j]);
					
					queryString = q.toString().substring(0, q.toString().length() - 2)
							.concat(NewQuery);
					queryString=queryString.replace(",", "");
					if(queryString.contains("xsd"))
						queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
		
					breaking=1;
					break;
				
					}
				
			
			}
			
			
			
								else if (extension1.toString().contains(",")  &&(!extension1.toString().startsWith("?") 
										&&  !extension1.toString().contains("=")
										&&  !extension1.toString().contains("<") && !extension1.toString().contains(">") )
									||	extension1.toString().contains("STRDT(") ||	extension1.toString().contains("STRLANG(")
									||	extension1.toString().contains("STRSTARTS(") ||	extension1.toString().contains("STRENDS(")
									||	extension1.toString().contains("CONTAINS(")||	extension1.toString().contains("STRBEFORE(")
									||	extension1.toString().contains("STRAFTER(") ||extension1.toString().contains("LANGMATCHES(")
									||  extension1.toString().contains("REGEX(") ||extension1.toString().contains("REGEX (")						
									||	extension1.toString().contains("STRDT (") ||	extension1.toString().contains("STRLANG (")
									||	extension1.toString().contains("STRSTARTS (") ||	extension1.toString().contains("STRENDS (")
									||	extension1.toString().contains("STRBEFORE (")
									||	extension1.toString().contains("STRAFTER (") ||extension1.toString().contains("LANGMATCHES (")
									
									) {
								
									String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(",")).replace(")", "").
											replace("(","").replace(" ", "");
									String ji = query[j].replace("\n", "");
									System.out.println("THis is here in extension12121212:"+e+"--"+ji);
									
									if (ji.equals(e)) {
										System.out.println("THis is here in extension222222:"+e+"--"+ji);
										
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
										queryString=queryString.replace(",", "");
										if(queryString.contains("xsd"))
											queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
							breaking=1;
									break;
									}
									
									if (breaking == 1) {
										breaking=0;
																	break;
																}
								
								}
								
								else if (!extension1.toString().contains(",")  && (!extension1.toString().startsWith("?") && 
										  !extension1.toString().contains("=") &&
										 !extension1.toString().contains("<") && !extension1.toString().contains(">"))||
										extension1.toString().contains("ABS(") || extension1.toString().contains("ROUND(") ||
												extension1.toString().contains("CEIL(") ||extension1.toString().contains("FLOOR(")||
												extension1.toString().contains("BOUND(") ||
												extension1.toString().contains("YEAR(") || extension1.toString().contains("MONTH(")||
												extension1.toString().contains("DAY(") || extension1.toString().contains("HOURS(") || 
												extension1.toString().contains("MINUTES(") || extension1.toString().contains("SECONDS(") 
										|| extension1.toString().contains("STRLEN(") ||  extension1.toString().contains("UCASE(") ||
										extension1.toString().contains("isIRI(") || extension1.toString().contains("isBlank(") ||
										extension1.toString().contains("isLiteral(") || extension1.toString().contains("isNumeric(") ||
										 extension1.toString().contains("str(") ||
										extension1.toString().contains("LCASE(") ||
										extension1.toString().contains("STRLEN (") ||  extension1.toString().contains("UCASE (") ||
										extension1.toString().contains("LCASE (") ||
										extension1.toString().contains("ABS (") || extension1.toString().contains("ROUND (") ||
										extension1.toString().contains("BOUND (") ||
										extension1.toString().contains("isIRI (") || extension1.toString().contains("isBlank (") ||
										extension1.toString().contains("isLiteral (") || extension1.toString().contains("isNumeric (") ||
										 extension1.toString().contains("str (") ||
										extension1.toString().contains("CEIL (") ||extension1.toString().contains("FLOOR (")||
										extension1.toString().contains("YEAR (") || extension1.toString().contains("MONTH (")||
										extension1.toString().contains("DAY (")|| extension1.toString().contains("HOURS (") || 
										extension1.toString().contains("MINUTES (") || extension1.toString().contains("SECONDS (") 
										) {
									
									String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(")")).replace(")", "").
											replace("(","").replace(" ", "");
									String ji = query[j].replace("\n", "");
									System.out.println("THis is here in extension12121212:"+e+"--"+ji);
									
									if (ji.equals(e)) {
										System.out.println("THis is here in extension222222:"+e+"--"+ji);
										
										queryString = q.toString().substring(0, q.toString().length() - 2)
												.concat(NewQuery);
										queryString=queryString.replace(",", "");
										if(queryString.contains("xsd"))
											queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
							breaking=1;
									break;
									}
									
									if (breaking == 1) {
										breaking=0;
																	break;
																}
								}

								
								else {									
		//							String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(",")).replace("[()]", "");
			//				//		String ji = query[j];
									System.out.println("THis is here to skip:");
									
				//					if (ji.equals(e)) {
					//					System.out.println("THis is here in extension555555555555121212:"+ q+"--"+NewQuery);
										
					//					queryString = q.toString().substring(0, q.toString().length() - 2)
					//							.concat(NewQuery);
					//					if(queryString.contains("xsd"))
					//						queryString = "PREFIX xsd:     <http://www.w3.org/2001/XMLSchema#> ".concat(queryString);
							
							//		}
								
								}
							
							//}
							if (breaking == 1) {
	breaking=0;
								break;
							}
					}
					if (breaking == 1) {
breaking=0;
						break;
					}

					if (breaking == 1) {
						break;
					}
			}
//					}
		//			if (breaking == 1) {
		//				break;
		//			}
				//}
			//}
			// return queryString;
		
		
			
					
			
	
					
//					}
		//			if (breaking == 1) {
		//				break;
		//			}
				//}
			//}
			// return queryString;
		
				
	
		 
//			////System.out.printlnln("This is the new query:"+queryString);
				

//else
//	return queryString;
			
	
			//	System.out.println("This is bounded:"+q.toString());
				if (queryString == null)
				{
					System.out.println("This is now the working style here3939393939");
					unExecuted=1;
					return null;
				}
					else {
						falsified=1;
						System.out.println("This is now the working style here090909090");
						return queryString;		
				        	}
	
		//return queryString;

	}

	public synchronized static List<EdgeOperator> BushyTreeTripleCreation(Triple triples) {
		List<EdgeOperator> BushyTreeTriple2 = new Vector<EdgeOperator>();
		List<EdgeOperator> AlreadyProcessed = new Vector<EdgeOperator>();
		List<List<EdgeOperator>> JoinGroups = new Vector<>();
//if((ParaEng.Optional.contains("OPTIONAL") || ParaEng.Optional.contains("optional")) || ParaEng.Union.contains("UNION")) 
		JoinGroups.addAll(BGPEval.JoinGroupsListOptional);
		JoinGroups.addAll(BGPEval.JoinGroupsListMinus);
		
		JoinGroups.addAll(BGPEval.JoinGroupsListExclusive);
	//	System.out.println("This is the property:"+BGPEval.JoinGroupsListExclusive);
		
		if (BGPEval.JoinGroupsListRight.size() > 0)
			JoinGroups.add(BGPEval.JoinGroupsListRight);
		if (BGPEval.JoinGroupsListLeft.size() > 0)
			JoinGroups.add(BGPEval.JoinGroupsListLeft);
		if (BGPEval.JoinGroupsListRightOptional.size() > 0)
			JoinGroups.add(BGPEval.JoinGroupsListRightOptional);
		if (BGPEval.JoinGroupsListLeftOptional.size() > 0)
			JoinGroups.add(BGPEval.JoinGroupsListLeftOptional);
		if (BGPEval.JoinGroupsListRightMinus.size() > 0)
			JoinGroups.add(BGPEval.JoinGroupsListRightMinus);
		if (BGPEval.JoinGroupsListLeftMinus.size() > 0)
			JoinGroups.add(BGPEval.JoinGroupsListLeftMinus);

		
		int br = 0;
//if(BGPEval.IsLeftQuery==false&&BGPEval.IsRightQuery==false)
//{	
		for (int i = 0; i < JoinGroups.size(); i++)
			if (JoinGroups.get(i).size() > 0)
			{
				for (Entry<EdgeOperator, Map<EdgeOperator, Integer>> e2 : BGPEval.exchangeOfElements.entrySet()) {
				for (Entry<EdgeOperator, Integer> extension1 : e2.getValue().entrySet())
						if (e2.getKey().equals(JoinGroups.get(i).get(0)) && extension1.getValue() == 1)
							if (extension1.getKey().getEdge().getTriple().equals(triples)) {
								{
									if(!BushyTreeTriple2.equals(JoinGroups.get(i)))
									BushyTreeTriple2.addAll(JoinGroups.get(i));
									br = 1;
									break;
								}
							}
					if (br == 1) {
						return BushyTreeTriple2;
						}
				}	}

//	}
//}
//else {
		BushyTreeTriple2 = null;

		return BushyTreeTriple2;

	}

	/*public static Map<Integer, String> Non_Predicate(ResultSet rs) {

		BindingHashMap extend = new BindingHashMap();// TODO do not use a new binding map
		Map<Integer, String> FinalMap = new HashMap<>();// pInc.values().stream().collect(Collectors.groupingBy(pInc.entrySet()::getKey,
														// TreeMap::new, Collectors.toList()));
//	HashMap<Integer,ArrayList<String>> pInc = new HashMap<>();
		Multimap<Integer, String> pInc = ArrayListMultimap.create();
		int num = 0;
		String str;
		// Iterator<ResultSet> rs2 = rs.iterator();
		while (rs.hasNext()) {
			Binding binding = rs.nextBinding();

			if (ParaEng.pConstant.toString().contains("?p=")) {
//			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!BoundQueryTask33333333333333!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//		while(binding1.hasNext()) {
				// Binding binding = binding1.nextBinding();
				HashMap<Var, String> otherBinding = BoundQueryTask.ExtractBinding(binding);
				for (Entry<Var, String> ob : otherBinding.entrySet()) {
					if (!ob.getValue().equals("p") && binding.get(ob.getKey()).toString().startsWith("http:")
							&& binding.get(ob.getKey()).toString().contains("TCGA")) {
//	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!BoundQueryTask4444444444444!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

						num = Integer.parseInt(ob.getKey().getName().substring(ob.getKey().getName().indexOf("_") + 1));
						str = binding.get(ob.getKey()).toString();
//	pInc.put(num,new ArrayList<>());
						// pInc.get(num).add(str);
						pInc.put(num, str);

//	System.out.println("This is the number and its value:"+pInc.keySet()+"--"+pInc.values());
					}

					for (Entry<Integer, String> entry : pInc.entries()) {

						// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!BoundQueryTask555555555555!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
						FinalMap.put(entry.getKey(), pInc.get(entry.getKey()).stream().parallel()
								.max(Comparator.comparing(String::length)).get());
					}

//extend.add(Var.alloc(ob.getValue()), binding.get(ob.getKey()));
				}
			}
			// }
			else
				return null;
		}
//	for (Entry<Integer, String> entry : FinalMap.entrySet()) {
//System.out.println("This is good good godd whatever go to  hell:"+entry);

//	}
		return FinalMap;
	}*/
}