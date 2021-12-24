package com.fluidops.fedx.trunk.parallel.engine.exec;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.fluidops.fedx.trunk.parallel.engine.main.StageGen;
import com.fluidops.fedx.trunk.parallel.engine.opt.ExhOptimiser;
import com.fluidops.fedx.trunk.description.RemoteService;
import com.fluidops.fedx.trunk.graph.Vertex;
import com.fluidops.fedx.trunk.parallel.engine.ParaEng;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.BindJoin;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator;
import com.fluidops.fedx.trunk.parallel.engine.main.BGPEval;
import com.fluidops.fedx.trunk.stream.engine.util.QueryUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.fluidops.fedx.structures.Endpoint;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.sparql.syntax.Element;
import org.apache.jena.query.Query;
import org.apache.jena.query.ResultSet;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingFactory;
import org.apache.jena.sparql.engine.binding.BindingHashMap;
import org.apache.jena.sparql.engine.binding.BindingMap;
import org.apache.jena.sparql.engine.http.QueryEngineHTTP;
import org.apache.jena.sparql.resultset.ResultSetException;
import org.apache.jena.sparql.resultset.ResultSetMem;
import org.apache.jena.sparql.syntax.ElementGroup;
import org.apache.jena.sparql.syntax.ElementTriplesBlock;
import org.apache.jena.sparql.syntax.ElementUnion;
import java.util.*;  
import java.io.*;  
public class BoundQueryTask extends QueryTask {


//static int i11 = 0;
//static  int i=0;
//ArrayList<Binding>  = new ArrayList<Binding>();
	static List<Binding>	NonRepeat = new ArrayList<>();
	static int index=0;

	
	Binding bindings ;
  Endpoint serv ;

  Integer vat;
  static String task;
  static String ext;
//HashMap<Var, String> otherBinding =new HashMap<>() ;
//static Map<Map<Map<Node,Integer>,Node>,Node> bin = Collections.synchronizedMap(new HashMap<>()) ;
static	String value="";
	//int index;
	public BoundQueryTask(TripleExecution te, Endpoint service, Binding bindings,String string) {
		super(te, service,string);
		this.bindings = bindings;
this.serv=service;
this.ext=string;

		//for(Binding b:bindings)
	//	System.out.println("This is secure here:"+b);
		// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!BoundQueryTask!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

	}

	
public  Binding getBindings(){
	return bindings;
}	


@Override
public  synchronized  Query buildQuery() {
//System.out.println("BoundQueryTask is being used");
//bin.clear();
//	TripleExecution.i++;
	//synchronized(bindings) {
	//List<Binding> bs= new ArrayList<Binding>();
	 Node subject,object,predicate;
//	 if(BindJoin.MultipleBinding!=null)
//for(Entry<Binding, Binding> i:BindJoin.MultipleBinding.entries())
//if(i.getValue().equals(this.bindings))
//	bs.addAll(BindJoin.MultipleBinding.get(i.getKey()));
// System.out.println("This is doubly query being processed::"+bs+"--"+this.bindings+"--"+bs.size());
	 
	Query query = new Query();
	ElementGroup elg = new ElementGroup();
			Binding binding = this.bindings;
			// BGPEval.JoinGroupsList.stream().
	//if(bs.isEmpty()|| bs==null) {
			if (getBusyTreeTriple() != null ) {
		// int ced=0;

		for (EdgeOperator btt : getBusyTreeTriple()) {
//if(ced<ghi) {
	// System.out.println("This is the BushyTreeTrple in QueryTask:"+binding);
	
			subject= QueryUtil.replacewithBinding(btt.getEdge().getTriple().getSubject(), binding);
			predicate = QueryUtil.replacewithBinding(btt.getEdge().getTriple().getPredicate(), binding);
			object = QueryUtil.replacewithBinding(btt.getEdge().getTriple().getObject(), binding);
		//	 System.out.println("This is the BushyTreeTrple in QueryTask11111:"+subject+"--"+predicate+"--"+object);
//		System.out.println("This is original triple subject213:"+btt.getEdge().getTriple().getSubject()+"--"+subject);
//			System.out.println("This is original triple object213:"+btt.getEdge().getTriple().getObject()+"--"+object);
				if(!subject.equals(btt.getEdge().getTriple().getSubject()))
			value=btt.getEdge().getTriple().getSubject().toString();
				else if(!object.equals(btt.getEdge().getTriple().getObject()))
					value=btt.getEdge().getTriple().getObject().toString();
			///		Stream.of(ReplaceLabelInQueries(subject,object,predicate,binding));
			//Node;
			//Node 
				//System.out.println("This is original triple subject213:"+value);
				//		
				
				
			if (subject.isVariable()) {
				subject = Var.alloc(subject.getName() );
			}

			if (predicate.isVariable()) {
				predicate = Var.alloc(predicate.getName()  );
			}

			if (object.isVariable()) {
				object = Var.alloc(object.getName() );
			}

			Triple newtriple = new Triple(subject, predicate, object);
	//				ElementGroup elg = new ElementGroup();

			elg.addTriplePattern(newtriple);
		//	System.out.println("This is processing:"+elg+"--"+newtriple);
//	ced++;
//}		
		}
	}
			else {
				
				subject= QueryUtil.replacewithBinding(triple.getSubject(), binding);
			//	System.out.println("This is original triple subject:"+triple.getSubject()+"--"+subject);
				predicate =triple.getPredicate();// QueryUtil.replacewithBinding(triple.getPredicate(), binding);
				object = QueryUtil.replacewithBinding(triple.getObject(), binding);
			//	System.out.println("This is original triple object:"+triple.getObject()+"--"+object);
				if(!subject.equals(triple.getSubject()))
					value=triple.getSubject().toString();
				else	if(!object.equals(triple.getObject()))
							value=triple.getObject().toString();
				//if(value=="")
				//	if(subject.toString().contains("http"))
				//		value=subject.toString();
				//	else if(object.toString().contains("http"))
				//		value=object.toString();
				///		Stream.of(ReplaceLabelInQueries(subject,object,predicate,binding));
				//Node;
				//Node 
		//	 System.out.println("This is the BushyTreeTrple in QueryTask222222:"+value);
			/* for(Entry<Vertex, List<Binding>> ee:BGPEval.urik.entrySet())
			 { System.out.println("This is the BushyTreeTrple in QueryTask333333:"+ee.getKey().getNode());
			 if(ee.getKey().getNode().toString().equals(value))
				 System.out.println("Node is equal to value");
			 }*/
				if (subject.isVariable()) {
					subject = Var.alloc(subject.getName() );
				}

				if (predicate.isVariable()) {
					predicate = Var.alloc(predicate.getName()  );
				}

				if (object.isVariable()) {
					object = Var.alloc(object.getName() );
				}

				Triple newtriple = new Triple(subject, predicate, object);
		//		ElementGroup elg = new ElementGroup();
				
				elg.addTriplePattern(newtriple);
			
			//	System.out.println("This is processing222222222:"+elg+"--"+newtriple);
			}
	
//	}
/*		else if(bs!=null) {
	ElementUnion eu = new ElementUnion();
		for(int i=0;i<bs.size();i++) {
			Binding binding1 = bs.get(i);
			Node subject1 = QueryUtil.replacewithBinding(triple.getSubject(), binding1);
			Node predicate1 = QueryUtil.replacewithBinding(triple.getPredicate(), binding1);
			Node object1 = QueryUtil.replacewithBinding(triple.getObject(), binding1);
			if(subject1.isVariable()) {
				subject1 = Var.alloc(subject1.getName());
			}
			
			if(predicate1.isVariable()) {
				predicate1 = Var.alloc(predicate1.getName());
			}
			
			if(object1.isVariable()) {
				object1 = Var.alloc(object1.getName());
			}
			
			Triple newtriple = new Triple(subject1,predicate1,object1);
			ElementTriplesBlock tb = new ElementTriplesBlock();
			tb.addTriple(newtriple);
			eu.addElement(tb);
		}
		
		List<Element> elements = eu.getElements();
		
		if(elements.size() > 1) {
			elg.addElement(eu);
		}
		else {
			elg.addTriplePattern(((ElementTriplesBlock)elements.get(0)).getPattern().get(0));
		}
		
	System.out.println("This is the query:"+elg );
	}*/
			query.setQueryResultStar(true);
	query.setQueryPattern(elg);
	query.setQuerySelectType();
	
	//		////System.out.println("---------------QueryTask buildQUery---------------: "+query+"--"+elg.getElements()+"--"+query.getQueryPattern()+"--"+query.getBindingValues()+"--"+query.getResultURIs());
//	isBound = 0;
//	return query;
QueryTask.isBound=1;
		
//System.out.println("This is the query:"+query);
	return query;
	//}
}

	@Override
	protected ResultSet execQuery(Query query,int integer,String ext) {
		int unionCount = 0;
 String inta=ext;
		try {
			HashSet<String> numOfNodes = new HashSet<>();
//// System.out.printlnln("This is occurenece of
			//// ?:"+count+"--"+IsSingleStatement);

			boolean error = false;
			String url = serv.getEndpoint();
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

			// //System.out.println("ThiOP12:"+QueryExtension(query));
			QueryEngineHTTP qexec = null;
			int a=0;
			// if(ParaEng.opq!=null) {
		

			// qString = qString.replace("}", "} LIMIT 100");
	ResultSetMem result = null ;	
	if( ParaEng.FiltereqLarge.size()==0 ||  ParaEng.FiltereqLarge.isEmpty()) {
//		System.out.println("This is now creating extended query131313:" + query);
	if(QueryTask.wfilter==1)
			{	
		
		 String q=TripleExecution.ab+" "+ query.toString().substring(query.toString().indexOf("*")+1);
			
			qexec = new QueryEngineHTTP(url, q);

		
			//System.out.println("This is now creating extended query9999:" + query+ "--" + url+"--"+ext);
			 result = new ResultSetMem(qexec.execSelect());// }).join();
				setPro(query.toString(), result);
				
			}	
		else
			{
			String qString=null;
			 /*if(!ParaEng.InnerFilterSimple.isEmpty() ||  ParaEng.InnerFilterSimple.size()>0)
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
		 */   qString =query.toString();// QueryExtension(query);
			// qString=TripleExecution.ab+" "+ qString.substring(qString.indexOf("*")+1);
			qexec = new QueryEngineHTTP(url, qString);
			
		//	System.out.println("This is now creating extended query66661:" + qString + "--" + url+"--"+ext+"--"+integer);
			
	//		if(url.contains("8885"))
	//		BGPEval.vat.merge(BGPEval.vat.get(8885), 1, Integer::sum);
	//		else if(url.contains("8880"))
	//			BGPEval.vat.merge(BGPEval.vat.get(8880), 1, Integer::sum);
			
			///	int currentValue1 = BGPEval.vat.get(Attribute.ONE.name());
		//	BGPEval.vat.put(Attribute.ONE.name(), currentValue1+1);
		//	if(
		//			BGPEval.vat.get(Integer.valueOf(url.replaceAll("[^0-9]", "")))<=1) {
			//	if(!(BGPEval.vat.containsKey(8885) && BGPEval.vat.get(8885).intValue()>1))
			//	{
		//		qexec.close();
		//		return null;
		//		}
		//	if(!(BGPEval.vat.containsKey(8880) && BGPEval.vat.get(8880).intValue()>2))
		//			{qexec.close();return null;}
		//	if(vat<=1) {
			String node = null;
			if( !BGPEval.urik.isEmpty() || BGPEval.urik.size()>0) {
			for(Entry<Vertex, List<Binding>> urr:BGPEval.urik.entrySet())
				node=urr.getKey().getNode().toString()+">";
					//		System.out.println("THis is the fear of hell:");
				if(url.contains("8888") && integer<=0 &&  qString.contains(node)) {
	//		System.out.println("This is now creating extended:" + BGPEval.vat);

	//	System.out.println("This is now creating extended query12321:" + qString + "--" + url+"--"+ext+"--"+integer);

				result = new ResultSetMem(qexec.execSelect());// }).join();

				setPro(query.toString(), result);
				}
				else if ((url.contains("8889")|| url.contains("8887")) && integer<=1  &&  qString.contains(node)) {
		//			System.out.println("This is now creating extended:" + BGPEval.vat);

			//		System.out.println("This is now creating extended query12321:" + qString + "--" + url+"--"+ext+"--"+integer);

							result = new ResultSetMem(qexec.execSelect());// }).join();

							setPro(qString, result);
				}
				else if(!qString.contains(node))  {
//					System.out.println("This is now creating extended:" + BGPEval.vat);

	//				System.out.println("This is now creating extended query12321:" + qString + "--" + url+"--"+ext+"--"+integer);

							result = new ResultSetMem(qexec.execSelect());// }).join();

							setPro(qString, result);
					
				}
			}
			else {
			//	System.out.println("This is now creating extended query99999:" + qString);

				result = new ResultSetMem(qexec.execSelect());// }).join();

				setPro(qString, result);
			}
			}
			//}
			
			//}
			
	}
	else
	{
		
		System.out.println("This is the value of bounded invincible:"+ext+"--"+inta);		
		bounded=0;
		Set<String> availables=new HashSet<>();
		for(String asd: query.toString().split(" ")) {
			if(asd.startsWith("?"))
				availables.add(asd);
		
	}
	
		for(String av:availables)
	//	
		
	//	for(String av:BGPEval.bindEstimator)
		for(Entry<HashMap<String,String>,String> fe:ParaEng.FiltereqLarge.entrySet())
			for(Entry<String, String>  fe1:fe.getKey().entrySet())
			{	
				
				
				
			//	if(ext.equals(fe1.getKey()))
				if(inta.contains(av.replace("\n", "")))
					
				{
			if(fe.getValue().contains(av.replace("\n", "")))
				
				
				bounded=1;}}
//System.out.println("This is the perforamcen execution:"
//		+ query);
		
		 String qString ="";
		 if(!ParaEng.InnerFilterSimple.isEmpty()  || ParaEng.InnerFilterSimple.size()>0)
		 {
		 for(Entry<String, String> ifs:ParaEng.InnerFilterSimple.entrySet()) 
			if(triple.getSubject().getName().toString().contains(ifs.getKey().substring(1))
				|| triple.getObject().getName().toString().contains(ifs.getKey().substring(1)) ||
				triple.getSubject().getName().toString().contains(ifs.getValue().substring(1))
						|| triple.getObject().getName().toString().contains(ifs.getValue().substring(1))	)
			{
				 String q1=TripleExecution.ab+" "+ query.toString().substring(query.toString().indexOf("*")+1);

				qString=q1.toString();}
			else
				{if(bounded==1)
					 qString = QueryExtensionLarge(query,inta,bounded);
								else
									{
								
										//		System.out.println("This is not bounded3333333333333333:"+q.toString());
//												if (queryString == null)
												falsified=0;
		//										System.out.println("This is now the working style here");
												 String q1=TripleExecution.ab+" "+ query.toString().substring(query.toString().indexOf("*")+1);

												qString=q1.toString();}
		 
		 }
		 }
		 else  
			 
			 {if(bounded==1)
			 qString = QueryExtensionLarge(query,inta,bounded);
			else
				{
			
							//System.out.println("This is not bounded3333333333333333:"+q.toString());
//							if (queryString == null)
							falsified=0;
	//						System.out.println("This is now the working style here");
							 String q1=TripleExecution.ab+" "+ query.toString().substring(query.toString().indexOf("*")+1);

							qString=q1.toString();
							}
			 }
				 //	System.out.println("This is the value of bounded:"+qString);		
		//if(qString.contains("FILTER"))		{ 
		// qString=TripleExecution.ab+" "+ qString.substring(qString.indexOf("*")+1);
		qexec = new QueryEngineHTTP(url, qString);
	//	if (BoundQueryTask.isBound == 1)			
		System.out.println("This is now creating extended query:" + qString+ "--" + url+"--"+ext+"--"+bounded);
		 result = new ResultSetMem(qexec.execSelect());// }).join();

//		if (BoundQueryTask.isBound == 1)
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
	//		// "+MAXTRIES);
	//		m++;

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

	@Override
	public 	 List<Binding> postProcess(ResultSet rs) {
		List<Binding> resultsBq =
				 new ArrayList<Binding>();
	
	//	while(rs.hasNext())
	//		System.out.println("This is result:"+rs.nextBinding());
		
		//	System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!BoundQueryTask222222222222222222222!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		//	int i=0;
			
		//	Set<Binding> a = new HashSet<>();		
	/*		for(Binding b:this.bindings)
			{	while(b.vars().hasNext()) {
					Var x = b.vars().next();
					
					System.out.println("These are the bindings:"+Var.alloc(x+"_"+i)+"--"+ b.get(x));
					a.add(BindingFactory.binding(Var.alloc(x.toString().substring(i)+"_"+i), b.get(x)));
				//	System.out.println("This is resultset:"+x+""+i+"--"+b.get(x));
				}
			i++;
			//System.out.println("This");
			}*/	
//for(Binding a1:a)
//System.out.println("This is a new error:"+a1);
//		ForkJoinPool fjp1 = new ForkJoinPool();
	//				fjp1.submit(
				//()->
	//	if(ParaEng.pConstant.toString().contains("?p=")) {		
	//	
	//			Res=Non_Predicate(result1);
	//	}
			//	).join();
			//	fjp1.shutdown();
				
			//	if(Res!=null);
			//	for(Entry<Integer, String> r:Res.entrySet())
			//		System.out.println("This is result values:"+r);
		
		
	//	Multimap<Integer ,String> pInc = ArrayListMultimap.create();
		//HashMap<String,Integer>  = new HashMap<>();
		//Map<Object, List<Integer>> FinalMap =new HashMap<>();
//		ForkJoinPool fjp = new ForkJoinPool();
/*		if(ParaEng.pConstant.toString().contains("?p="))
		while (rs.hasNext()) {
			Binding binding = rs.nextBinding();
			HashMap<Var, String> otherBinding = ExtractBinding(binding);
			for(Entry<Var,String> ob:otherBinding.entrySet())
			{
						
				
int	num=Integer.parseInt(ob.getKey().getName().substring(ob.getKey().getName().indexOf("_")+1));

for(Entry<Integer, String> fm:Res.entrySet())
if(num==fm.getKey())
	extend.add(Var.alloc((String)ParaEng.pConstant.values().toArray()[0]), StageGen.StringConversion(fm.getValue()));

								
extend.add(Var.alloc(ob.getValue()), binding.get(ob.getKey()));


			}
//System.out.println("This is now extend:"+extend);
results.add(extend);
//fjp.shutdownNow();
//results.notifyAll();
//}

//i++;
		}
		else
	*/		//String b1=
	//	String b2="";
		
		// System.out.println("THis is extend000:"+value+"--"+bindings);
			
		String key=(String) pro.keySet().toArray()[0];
		//System.out.println("This is the respective keySet"+key);
		while(rs.hasNext())
		{Binding rr=rs.nextBinding();
			proExt.put(key,rr);
		//	if(index==0)
		//	NonRepeat.add(rr);
		
		}
		
		//for(Entry<String, ResultSet> p:pro.entries())
		//	while(p.getValue().hasNext())
		//		proExt.put(key, p.getValue().nextBinding());
//	for(Entry<String, Binding> pe:proExt.entries())
//	System.out.println("This is pe:"+pe);
	
	//	synchronized(proExt) {
	//	if(!NonRepeat.isEmpty() ||NonRepeat!=null)
	//		System.out.println("These are the number of bidings:"+NonRepeat.size());

			for(Entry<String,Binding> entry:proExt.entries()){
		//		if(TripleExecution.index==0)
		///		TripleExecution.NonRepeat.add(entry.getValue());
				
					
				//if(!NonRepeat.isEmpty() ||NonRepeat!=null)
			//	if(index>0)
		 	//		if(NonRepeat.contains(entry.getValue()))
			//			continue;
	//	ab++;
//System.out.println("These are the outputs:"+entry);

	//BindingHashMap extend = new BindingHashMap();// TODO do not use a new binding map
	
	
	
  // String p="";
	//	while(entry.getValue().hasNext())
		//{
		//	Set<String> finall =new HashSet<>() ;
			String s1 = null;
			BindingHashMap extend = new BindingHashMap();// TODO do not use a new binding map
			BindingHashMap extendTemp1 = new BindingHashMap();// TODO do not use a new binding map
			BindingHashMap extendTemp2 = new BindingHashMap();
			
			
			//String keyy=entry.getKey();
			String ar=entry.getValue().toString();
				//System.out.println("This is extend1:"+ar+"--"+keyy);	
			
				Pattern regex = Pattern.compile("[?=\\)][?= ][?=\\(]");
				 String[] regexMatcher = regex.split(ar);
				//synchronized(regexMatcher) {
				
				// Stream<String> stream1 = Arrays.stream(regexMatcher);
				// stringStream.toArray(String[]::new);
				// System.out.println("This is the original one BQT:"+ar);
					
				 String p1="";
				//List<String> resultname=new ArrayList<>();		
			for(String st:regexMatcher){
					String str = null;
						// System.out.println("This is ml BQT:"+st);
							
						String[] mlp=st.split(" ");	 
						for(int iii=3;iii<=mlp.length-1;iii++)
						{ str+=mlp[iii].replace(")", "").replace("(", "").replace("\"", "")+" ";
							
						}String mll=st;
						//  System.out.println("THis is extend111:"+st);
							
						  p1=mll.substring(mll.toString().indexOf("?"),mll.toString().indexOf("=")-1);
							
			//			  resultname.add(p1);
							extendTemp1.add(Var.alloc(p1.substring(1)),StageGen.StringConversion("\""+str.substring(0,str.length()-1).replace("null", "").replace(")", "")+"\""));  
						//  else
						//		extend.add(Var.alloc(p1.substring(1)),StageGen.StringConversion(str.replace("null", "").replace("<", "").replace(">", "").replace(" ", "")));  
				// 
				 }//;);
			
			
	/*		Set<String> lem=new HashSet<>();
			String str =entry.getKey();

		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
		while (m.find())
		    list.add(m.group(1)); // Add .replace("\"", "") to remove surrounding quotes.  

	//	System.out.println("This is lem before before:"+list);

		for(String e:list)
			{
				if(e.startsWith("?"))
			lem.add(e.substring(e.indexOf("?")).replace("\n", ""));
				else if(e.contains("http")) {
					lem.add(e.substring(e.indexOf("<")).replace(">", "").replace("<", "").replace("\n", ""));
							
				}
				else if(e.contains("\"")) {
					
				
					lem.add(e.substring(e.indexOf("\"")).replace("\n", ""));
							
				}
			
			
			}
	

		//System.out.println("This is lem before:"+lem);
		//System.out.println("This is bev:"+BGPEval.bindEstimator);
		for(String be:BGPEval.bindEstimator)
	//		{		
	//					System.out.println("This is bev:"+be.replaceAll("\\s+",""));
						lem.remove(be);		
	//		}
		*/
		//	System.out.println("This is lem:"+"--"+value);
		//	System.out.println("This is lem1:"+"--"+Var.alloc(value.substring(1)));
	//		System.out.println("This is lem2:"+"--"+bindings.get(Var.alloc(value.substring(1))));
//
	//		Var.alloc(value.substring(1));
		//	 for(String l:lem)
		//	if( BGPEval.urik.isEmpty() || BGPEval.urik.size()==0) {
		//		if(!BGPEval.urik.containsKey(value.toString()))
			if(BGPEval.urik.isEmpty()|| BGPEval.urik.size()==0)
			extendTemp2.add(Var.alloc(value.substring(1)), bindings.get(Var.alloc(value.substring(1))));
			else {
				Set<String> lem=new HashSet<>();
				String str =entry.getKey();

			List<String> list = new ArrayList<String>();
			Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
			while (m.find())
			    list.add(m.group(1)); // Add .replace("\"", "") to remove surrounding quotes.  

		//	System.out.println("This is lem before before:"+list);

			for(String e:list)
				{
					if(e.startsWith("?"))
				lem.add(e.substring(e.indexOf("?")).replace("\n", ""));
					else if(e.contains("http")) {
						lem.add(e.substring(e.indexOf("<")).replace(">", "").replace("<", "").replace("\n", ""));
								
					}
					else if(e.contains("\"")) {
						
					
						lem.add(e.substring(e.indexOf("\"")).replace("\n", ""));
								
					}
				
				
				}
		

		//		System.out.println("This is lem before:"+lem);
				
			for(String be:BGPEval.bindEstimator)
		//		{		
		//					System.out.println("This is bev:"+be.replaceAll("\\s+",""));
							lem.remove(be);		
		//		}
			
		//	System.out.println("This is lem:"+lem+"--"+value);
				 for(String l:lem)
					extendTemp2.add(Var.alloc(value.substring(1)), StageGen.StringConversion(l));
			}
		//	}
			/*
		//	  System.out.println("THis is extend111:"+triple.getObject());
			int IsUrik=0;
			for(Entry<Vertex, List<Binding>> ur:BGPEval.urik.entrySet())
				if(triple.getSubject().equals(ur.getKey().getNode()) || 
						triple.getObject().equals(ur.getKey().getNode()) )
			IsUrik=1;
			String ab="";
		 	 String rr=entry.getKey().toString().replace(".",";");
				
			/// System.out.println("THis is extend222:"+BGPEval.urim);
				if(IsUrik==1) {
				 if(triple.getSubject().toString().contains("http"))
				    	 ab =triple.getSubject().toString();
				 else
			    	 ab =triple.getObject().toString();
				}
					 //else  if(bindings.get(Var.alloc(triple.getObject()))!=null)
				//    	 ab = bindings.get(Var.alloc(triple.getObject())).toString();
				else {	 if(bindings.get(Var.alloc(triple.getSubject()))!=null)
			    	 ab = bindings.get(Var.alloc(triple.getSubject())).toString();
			     else  if(bindings.get(Var.alloc(triple.getObject()))!=null)
			    	 ab = bindings.get(Var.alloc(triple.getObject())).toString();
				}
		//		 String rr2=entry.getKey().toString();
					System.out.println("This is extend5.6 space:"+resultname);		
			//		String xyz="";
					
				// System.out.println("------------------------------------------------------------------");
				//	System.out.println("This is query:"+rr);
					
		//	System.out.println("This is index value within:"+rr+"--"+extend);
				// if(extend==null || extend.isEmpty())
				//	 continue;
			// /*	 for(String ml:regexMatcher)
				{	String str = null;
				 String p1="";
					 String[] mlp= ml.split(" ");
					for(int iii=3;iii<=mlp.length-1;iii++)
					{ str+=mlp[iii].replace(")", "").replace("(", "").replace("\"", "")+" ";
						
					}String mll=ml;
					  p1=mll.substring(mll.toString().indexOf("?"),mll.toString().indexOf("=")-1);
					  if(!str.contains("http"))
						extend.add(Var.alloc(p1.substring(1,p1.indexOf("_"))),StageGen.StringConversion("\""+str.substring(0,str.length()-1).replace("null", "").replace(")", "")+"\""));  
					  else
							extend.add(Var.alloc(p1.substring(1,p1.indexOf("_"))),StageGen.StringConversion(str.replace("null", "").replace("<", "").replace(">", "").replace(" ", "")));  
			//	}*/			
				// System.out.println("This is now extend:"+extend);
				//	 System.out.println("This is now value:"+rr);
						
				 	//String[] rrr=rr.split("UNION");
//				 	int inc=0;
				 	//for(String r:rrr)
		//		 	System.out.println("This is problem here:"+rrr[index]);
				 	//for(String r3:rrr) {
						//if(inc==index)
						//{
				// String[]	rr1=rrr[index].split(" ");
					
					//	System.out.println("This is extend2:"+finall);	
														
							//}
	//						if(!finall.isEmpty())
						//	System.out.println("This is the problem here010101:"+finall);
					//inc++;
				//	}
					
				//	System.out.println("This is the problem here010101:"+finall);
				//	 System.out.println("------------------------------------------------------------------");
						
					//					System.out.println("This is the extend here:"+extend);
								//System.out.println("This is the problem here:"+rr);
				//		 System.out.println("This is extend:"+extend);
													
				
				//	 System.out.println("This is extend3:"+e);
/*int a=0;
//for(String rrr1:rr.split(" ")) {
System.out.println("This is extend5.8:"+a+"--"+rr);
//}

//if(!triple.getObject().toString().contains("http://"))
if(!rr.contains(";"))
				 for(String rrr1:rr.split(" ")) {
							
				 //    System.out.println("This is extend3.0001:"+rrr1.replace("<", "").replace(">", "").replace("\n", "")+"--"+triple.getSubject()+"--"+triple.getObject());		
						
				    
					     // if((rrr1.endsWith("\n")) && rrr1.startsWith("<"))
		//		     System.out.println("This is extend3.0002:"+rrr1.replace("<", "").replace(">", "").replace("\n", "")+"--"+triple.getSubject()+"--"+triple.getObject());		
				     if( rrr1.startsWith("<") && 
				    		 rrr1.replace("<", "").replace(">", "").replace("\n", "").equals(ab)
				    		 )     {		s1=rrr1.replace("\n", "");

						//	if(!triple.getObject().toString().contains("http://") && !triple.getSubject().toString().contains("http://") )
					 	 if(!triple.getSubject().toString().equals(s1.replace("<", "").replace(">", "").replace(";", "")))
								
					      if(resultname.contains(triple.getObject().toString()))		
					      extendTemp2.add(Var.alloc(triple.getSubject().toString().substring(1)), StageGen.StringConversion(s1.replace("<", "").replace(">", "").replace(";", "")));
					else
					      extendTemp2.add(Var.alloc(triple.getObject().toString().substring(1)), StageGen.StringConversion(s1.replace("<", "").replace(">", "").replace(";", "")));
					
								     System.out.println("This is extend3.1:"+resultname+"--"+s1+"--"+triple.getSubject()+"--"+extendTemp2);		
					 break;   
				     }
					    
								 
				 }
				
				//if(rr.split(" ")(";"))
				//	 System.out.println("This is extend3.2 space:"+rrr1);		
				 //     }
else if(rr.contains(";"))
				 {	
//	System.out.println("This is extend3.489 space:");
	
				 int bracket = 0,colon=0;
				 String[] rt3 =rr.split(" ");
	//				System.out.println("This is extend3.499 space:");

				 for(int j=0;j<rt3.length;j++) {
		//				System.out.println("This is extend3.2 space:"+rt3[j]);
						//for(int j=0;j<rt3.length;j++)
					 for(int j1=0;j1<rt3[j].length();j1++)
					 {  int asciiValue =(int)rt3[j].charAt(j1);
					 
					 if(asciiValue==62)
						 bracket=j;
						  if(asciiValue==59)
						  {	  colon=j;
						  break;}
					 }
				 if(colon!=0)
					 break;
				 }
				 if(rt3[colon-1].contains("http")) {
					 
				     System.out.println("This is extend3.0010:"+rt3[colon-1]);		

					//	if(!triple.getObject().toString().contains("http://") && !triple.getSubject().toString().contains("http://") )
					 if(!triple.getSubject().toString().equals(rt3[colon-1].replace("<", "").replace(">", "").replace(";", "")))
							
					 if(resultname.contains(triple.getObject().toString()))		
							
				   	 extendTemp2.add(Var.alloc(triple.getSubject().toString().substring(1)), StageGen.StringConversion(rt3[colon-1].replace("<", "").replace(">", "").replace(";", "")));
						else
							extendTemp2.add(Var.alloc(triple.getObject().toString().substring(1)), StageGen.StringConversion(rt3[colon-1].replace("<", "").replace(">", "").replace(";", "")));
						
									 System.out.println("This is now the substring3.31:"+extend);
						
				 }
				 else {
					 String abc="";
			
					 for(int j=bracket+2;j<colon;j++) {
					 abc+=rt3[j]+" ";
				// System.out.println("This is the ascii value:"+rt3[j]);
			   	 }			
					//if(!triple.getObject().toString().contains("http://") && !triple.getSubject().toString().contains("http://") )
				// if(!triple.getSubject().toString().equals(s1.replace("<", "").replace(">", "")))
				// if(p1.equals(triple.getObject().toString()))		
						
				// extendTemp2.add(Var.alloc(triple.getSubject().toString().substring(1)), StageGen.StringConversion(abc.replace("<", "").replace(">","").substring(0, abc.replace("<", "").replace(">","").length()-1)));
				//	else
//				     System.out.println("This is extend3.0009:"+abc);		

				 if(resultname.contains(triple.getObject().toString()))								
					 extendTemp2.add(Var.alloc(triple.getSubject().toString().substring(1)), StageGen.StringConversion(abc.replace("<", "").replace(">","").substring(0, abc.replace("<", "").replace(">","").length()-1)));
						else
							 extendTemp2.add(Var.alloc(triple.getObject().toString().substring(1)), StageGen.StringConversion(abc.replace("<", "").replace(">","").substring(0, abc.replace("<", "").replace(">","").length()-1)));
						
			
					
						 System.out.println("This is now the substring3.3:"+extendTemp2);
				 }			
				  
					 
				 
					//	if(rt3[j].equals(";"))
					//	{		System.out.println("This is now the substring:"+rt3[j]);
					//	
					//						}		
				 }
					//	xyz=rt2;
	
					
				//	if(rr.)
			//		 String[] rt4 =rr.split(" ");
			//			for(int k=0;k<rt4.length;k++) {
			//				System.out.println("This is extend3.2 space:"+ StageGen.StringConversion(rt4[k-1].replace("<", "").replace(">", "")));
			//				if(rt4[k].contains(";"))
			//				{	System.out.println("This is extend3.2 space:"+ StageGen.StringConversion(rt4[k-1].replace("<", "").replace(">", "")));
			//				 extend.add(Var.alloc(triple.getObject().toString().substring(1)), StageGen.StringConversion(rt4[k-1].replace("<", "").replace(">", "")));
			//									}		
							
		//					xyz=rt2;
		
			//			}
					    
								 
else
				 {	 for(String rrr1:rr.split(" ")) {
					  if(rrr1.startsWith("<") && rrr1.endsWith(">"))
						 {	
						  System.out.println("This is extend3:"+rrr1);
					    	
						if(a==9)
						{	s1=rrr1;
						//if(!triple.getObject().toString().contains("http://") && !triple.getSubject().toString().contains("http://") )
						if(!triple.getSubject().toString().equals(s1.replace("<", "").replace(">", "")))
							 if(resultname.contains(triple.getObject().toString()))		
											extendTemp2.add(Var.alloc(triple.getSubject().toString().substring(1)), StageGen.StringConversion(s1.replace("<", "").replace(">", "")));
						else
							extendTemp2.add(Var.alloc(triple.getObject().toString().substring(1)), StageGen.StringConversion(s1.replace("<", "").replace(">", "")));
						
						 System.out.println("This is extend3.2:"+triple.getObject()+"--"+extend);
						   	
						} 
		//				System.out.println("This is extend3.3:"+a+"--"+rrr1);
						 }
			    		a++;
				 }}
				 
					 //		finall.add(s1);
				// System.out.println("THis is the current:"+triple);		
					
				
				//	System.out.println("This is extend:"+rr+"--"+extend+"--"+s1);	
					//			if(s1t==1)
					//			else
						//			extend.add(Var.alloc(triple.getObject().toString().substring(1)), StageGen.StringConversion(s1.replace("<", "").replace(">", "")));
								
					//				System.out.println("This is extend1:"+extend+"--"+triple.getObject());	

						 // if(!resultsBq.contains(extend))
			
				//	System.out.println("This is the next iterator12:"+extendTemp1);
				//	System.out.println("This is the next iterator34:"+extendTemp2+"--"+	triple.getSubject().toString()+"--"+s1.replaceAll("<", "").replaceAll(">", ""));
	*/			
				 extend.addAll(extendTemp1);		
				extend.addAll(extendTemp2);		
				resultsBq.add(extend);  
				//		 resultsBq.add(extendTemp2);
			//		for(Binding rb:resultsBq)
			//		 System.out.println("This is extend:"+rb);
							
										 
	/*				 for(Entry<String, String> f:finall.entrySet())	
					if(e.equals(f.getValue())) {
		
extend.add(Var.alloc(triple.getSubject().toString().substring(1)), StageGen.StringConversion(f.getKey().replace("<", "").replace(">", "")));
	
resultsBq.add(extend); */
				//	}
				
	/*			 for(String ar1:ar.split(" "))
					//	if(ar1.startsWith("?"))
					//		{	
									for(Entry<String, String> f:finall.entrySet())	
							if(ar1.equals(f.getValue())) {
				
	extend.add(Var.alloc(triple.getSubject().toString().substring(1)), StageGen.StringConversion(f.getKey().replace("<", "").replace(">", "")));
			
	resultsBq.add(extend);//});
	*/
				//			}
//}
					//}
//		}
}
//);
//for(Entry<String, ResultSet> r:pro.entrySet())
//{
//synchronized(r) {	
		//								if(resultsBq.toString().contains("TCGA-21"))
		//							System.out.println("This is the problem here44444:"+resultsBq);

	//						}
		//				}		//
							//}	
	//	}
	//	l=r2;
	//System.out.println("This is the problem here111:"+l);	
//}}
	

	
//}
//}
//for(Entry<String, ResultSet> r:pro.entrySet())
	
//{
/*	System.out.println("This is need:"+r);
	String s0=""; String s1="";
	HashMap<String,String> s=new HashMap<>();
	
	s.put(s0, s1);
	while(r.getValue().hasNext())
	{System.out.println("This is the problem here:"+s+"--"+r.getValue().nextBinding().toString());

	}
	for(String st:r.getKey().split(" "))
	{
		if(st.contains("http"))
			s1=st.replace("}", "").replace("{", "");

		//System.out.println("These are strings:"+st);
		if(st.startsWith("?"))
			s0=st;
	//}
	 * 
	 * 
}*/
/*synchronized(rs) {
while (rs.hasNext()) {
	
	Binding binding = rs.nextBinding();
	System.out.println("This is binding:"+binding);
	int lock=0;

	//	extend=new BindingHashMap();
	ArrayList<Binding> resultsSub = new ArrayList<Binding>();
		
String ch=	binding.toString().substring(binding.toString().indexOf("?")+1,binding.toString().indexOf("=")-1);
String ch1=	binding.toString().substring(binding.toString().indexOf("_")+1,binding.toString().indexOf("=")-1);
int x=0;
for(Entry<Map<Map<Node, Integer>, Node>, Node> b:BoundQueryTask.bin.entrySet())
				{
	if(lock==1)
		continue;
			for(Entry<Map<Node, Integer>, Node> b1:b.getKey().entrySet())
								for(Entry<Node,Integer> b2:b1.getKey().entrySet())
				{	
									
									String b2K = b2.getKey().getName();
									String chK=ch.toString();
									String b2V=b2.getValue().toString();
									String ch1K=ch1;
//									System.out.println("This is binding123:"+b2K+"--"+chK);
//									System.out.println("This is binding1234:"+b2V+"--"+ch1K);
//									System.out.println("This is b:"+b+"--"+ch1);
				
			//		System.out.println("This is bin:"+b2.getKey().getName()+"--"+ch.toString());
					//	
					//	System.out.println("This is bin:"+b1.getValue()+""+ch1+"--"+b.getValue());
					//	System.out.println("This is bin2:"+BindingFactory.binding(Var.alloc(b1.getValue().toString().substring(1)+""+ch1), b.getValue()));
				//	HashMap<Node, Node> b3=new HashMap<>();
				//	b3.put(b1.getKey(),b1.getValue());;
					//for(Entry<Node, Node> bb:b3.entrySet())
					//b2=bb.ke.getName().substring(b2.indexOf("_"),b2.indexOf("=")-1);
											
						//otherBinding =ExtractBinding(BindingFactory.binding(Var.alloc(b1.getValue().toString().substring(1)+""+ch1), b.getValue()));
			//otherBinding.entrySet().parallelStream().forEach(e->{extend.add(Var.alloc(e.getValue()), binding.get(e.getKey()));results.add(extend);});
//			String index=b1.getKey().toString().substring(binding.toString().indexOf("_"),binding.toString().indexOf("=")-1);
						//System.out.println("This is now extend00:"+b1);
						
					//	System.out.println("This is now extend:"+binding+"--"+ch1+"--"+b2.getValue());
						
						if(b2K.equals(chK)) {
							
			if(b2V.equals(ch1K))
			{	lock=1;
			BindingHashMap extend = new BindingHashMap();// TODO do not use a new binding map
			BindingHashMap extend1 = new BindingHashMap();// TODO do not use a new binding map
			

			
			 	
		//	 System.out.println("This is now extend1:"+extend1);
		
	//			System.out.println("This is now extend999:"+extend+"--"+b1+"--"+BindingFactory.binding(Var.alloc(b1.getValue().toString().substring(1)+""+ch1), b.getValue()));
				
				//for(Entry<Node, Node> bb:b3.entrySet())
			//b1.put(bb.getKey(),bb.getValue());;
				//resultsBq.add(extend1);
				

				otherBinding	= ExtractBinding(binding);

				//otherBinding.entrySet().parallelStream().forEach(e->{
					
					//Object e;
					for(Entry<Var,String> e:otherBinding.entrySet())
					extend1.add(Var.alloc(e.getValue()), binding.get(e.getKey()));  
					extend1.addAll(BindingFactory.binding(Var.alloc(b1.getValue().toString().substring(1)), b.getValue()));
					//			System.out.println("This is now extend1:"+extend1);/	
					resultsBq.add(extend1);//});

		//				System.out.println("This is now extend000:"+resultsBq);
				//break;
				////System.out.println("This is now result:"+results);
			//	extend=null;
			}
					}
				}	}}
*/
//System.out.println("-----------------------------------------END oF BIND JOIN-------------------------------------");
		
//		System.out.println("This is now result11111:"+results);

	
//		for(Entry<Var,String> ob:otherBinding.entrySet())
	//		{				
//extend.add(Var.alloc(ob.getValue()), binding.get(ob.getKey()));

//}

//results.notifyAll();
//}

//i++;
	//	}
	//	fjp.shutdownNow();

		//	for (Entry<Integer, String> entry : pInc.entrySet()) {
	//		  multiMap.put( entry.getKey(),entry.getValue());
	//		}			
	//	FinalMap=pInc.keySet().stream().collect(Collectors.groupingBy(k -> pInc.get(k)));
	//	for(Entry<Integer, String> fm:FinalMap.entrySet())
	//	System.out.println("This is final value:"+fm);
	
//}
//	ja=i;

//System.out.println("-------------------------------------------------------------------------");
//for(Binding r:resultsBq)
//System.out.println("These are resultsBq:"+r);
//System.out.println("These are the extends:"+resultsBq.size());
return resultsBq;
	}
	
	public int ReplaceLabelInQueries(Node subject, Node Object, Node Predicate,Binding binding) {
		subject= QueryUtil.replacewithBinding(triple.getSubject(), binding);
		Predicate = QueryUtil.replacewithBinding(triple.getPredicate(), binding);
		Object = QueryUtil.replacewithBinding(triple.getObject(), binding);
	return 0;
	}
/*public static HashMap<Var,String> ExtractBinding(Binding binding) {
	HashMap<Var,String> Combination =new HashMap<>();
	Arrays.stream(binding.toString().split(" ")).parallel().forEach(b->{
		if(b.contains("?") && !b.startsWith("\""))
		{
		//	Var var = );
			//String vName = null ;
			if(b.contains("_"))
			//vName =;
			Combination.put(Var.alloc(b.substring(1)),b.substring(1,b.indexOf("_")));
//			//System.out.println("This is correction of bindJoin results1:"+binding+"--"+b1+"--"+var1+"--"+var2+"--"+binding.get(var1));
		}		
	});
	
//	String[] b = binding.toString().split(" ");
//	for(String b1:b) {


	//	}
	return Combination;

}*/


/*
protected Object clone() throws CloneNotSupportedException{
    BGPEval student = (BGPEval) super.clone();
    student.results = results ;
    return student;
 }*/

/*private static Object deepCopy(Object object) {
	   try {
	     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	     ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
	     outputStrm.writeObject(object);
	     ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	     ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
	     return objInputStream.readObject();
	   }
	   catch (Exception e) {
	     e.printStackTrace();
	     return null;
	   }
	 }
*/}
