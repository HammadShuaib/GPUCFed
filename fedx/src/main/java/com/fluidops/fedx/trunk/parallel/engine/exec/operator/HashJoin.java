package com.fluidops.fedx.trunk.parallel.engine.exec.operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingFactory;
import org.apache.jena.sparql.engine.binding.BindingHashMap;
import org.eclipse.rdf4j.query.BindingSet;

import com.fluidops.fedx.trunk.config.Config;
import com.fluidops.fedx.trunk.graph.Edge;
import com.fluidops.fedx.trunk.graph.Vertex;
import com.fluidops.fedx.trunk.parallel.engine.ParaEng;
import com.fluidops.fedx.trunk.parallel.engine.error.TripleCard;
import com.fluidops.fedx.trunk.parallel.engine.exec.BoundQueryTask;
import com.fluidops.fedx.trunk.parallel.engine.exec.QueryTask;
import com.fluidops.fedx.trunk.parallel.engine.main.BGPEval;
import com.fluidops.fedx.trunk.parallel.engine.main.StageGen;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public class HashJoin extends EdgeOperator {

	//private	static final com.hp.hpl.log4j.Logger log = LogManager.getLogger(BindJoin.class.getName());

	public HashJoin(Edge e) {
		super(null, e);
	}
	static String sv1="",sv2="";
 static int total=0;
static int abnormal=0;
 static int remaning=0;
	static Var joinVars = null;
	static List<String> Completed890 = new ArrayList<>();
	
	int skp=0;
String finalization="Y";
public static List<EdgeOperator> AllEdges = new ArrayList<>();
public static List<Edge> ProcessedEdge = new ArrayList<>();
public static Multimap<Edge,String> ProcessedEdgeLarge =ArrayListMultimap.create();;

public static List<Edge> ProcessedVertex = new ArrayList<>();
List<BindingSet> BindJoinWhenEnd;
//	EdgeOperator CurrentEdgeOperator;
static ArrayList<Binding>	sBindingSet;
public static String isComplete="N";
static List<List<EdgeOperator>> ProcessedEdgeOperators=new ArrayList<>();
static HashMap<List<EdgeOperator>,String> ProcessedEdgeOperatorsLarge=new HashMap<>();

 static LinkedHashMap<List<EdgeOperator>,List<Binding>> ProcessedTriples = new LinkedHashMap<>();
 static LinkedHashMap<HashMap<List<EdgeOperator>,String>,List<Binding>> ProcessedTriplesLarge = new LinkedHashMap<>();
 static Set<String> DisabledTriplesLarge = new HashSet<>();
 
 static LinkedHashMap<List<EdgeOperator>,List<Binding>> ProcessedTriplesUnion = new LinkedHashMap<>();
 static LinkedHashMap<List<EdgeOperator>,List<Binding>> ProcessedTriplesMinus = new LinkedHashMap<>();

 public static HashSet<List<EdgeOperator>> EvaluatedTriples=new HashSet<>();
 public static HashSet<HashMap<List<EdgeOperator>,String>> EvaluatedTriplesLarge=new HashSet<>();
 public static HashSet<String> EvaluatedTriplesLargeSting=new HashSet<>();

 static	HashSet<List<EdgeOperator>> Removal= new HashSet<>();
	
public static LinkedHashMap<List<EdgeOperator>,List<Binding>> JoinedTriples = new LinkedHashMap<>();
public static LinkedHashMap<HashMap<List<EdgeOperator>,String>,List<Binding>> JoinedTriplesLarge = new LinkedHashMap<>();


public static LinkedHashMap<List<EdgeOperator>,List<Binding>> NotJoinedTriples = new LinkedHashMap<>();
public static LinkedHashMap<HashMap<List<EdgeOperator>,String>,List<Binding>> NotJoinedTriplesLarge = new LinkedHashMap<>();

public static LinkedHashMap<List<EdgeOperator>,List<Binding>> NotJoinedTriplesUnion = new LinkedHashMap<>();
public static LinkedHashMap<List<EdgeOperator>,List<Binding>> NotJoinedTriplesMinus = new LinkedHashMap<>();

static List<Binding> results = new ArrayList<>();

public static LinkedHashMap<List<EdgeOperator>,List<Binding>> JoinedTriplesUnion = new LinkedHashMap<>();
public static LinkedHashMap<List<EdgeOperator>,List<Binding>> JoinedTriplesMinus = new LinkedHashMap<>();

@Override
	public  void exec() {
	results = new ArrayList<>();
	//	com.hp.hpl.log4j.Logger.getRootLogger().setLevel(Level.OFF);
//		Logger.getRootLogger().setLevel(Level.OFF);
//com.hp.hpl.log4j.Logger.getLogger("com.fluidops.fedx.trunk.parallel.engine.exec.operator.HashJoin").setLevel(Level.OFF);
//////System.out.printlnln("44444444444444444444444444444444Subsubsubsubsubs:");

	//	synchronized(BindJoin.StartBindingSet123) {
		
		// it's possible that both vertices are not visited, but
		// it should not occur. To simplify the code we assume
		// at least one vertex is bound.
		//log.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in HashJoin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
		//		+ edge.getV1() + "--" + edge.getV2());
		
;
 
//	results = null;

		Vertex end;
		
//ForkJoinPool fjp = new ForkJoinPool();
	// fjp.submit(()->
	//{Stream.of(
		System.out.println("This is out of TripleExectuion in HashJoin2:"+ParaEng.FiltereqLarge);
		QueryTask.wfilter=0;
		
		if(ParaEng.FiltereqLarge==null || ParaEng.FiltereqLarge.size()==0 ||  ParaEng.FiltereqLarge.isEmpty()) {
			BindJoin.inner=0;
	
			List<Binding> a=new ArrayList<>();
			   String b=null;
			 String c=null;
			/*for(Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> iff:ParaEng.InnerFilter.entrySet()) {
							
				for(Entry<String, Set<Binding>> iff1:iff.getKey().entrySet()) {
					//if(iff1.getValue().size()>0)
						
				//	for(Binding ifs:iff1.getValue())
				//	System.out.println("This is successful now 3434343434123213123:"+ifs);

					if(iff1.getValue()==null)
					b=iff1.getKey();
					else {
					a.addAll(iff1.getValue().parallelStream().collect(Collectors.toList()));
					c=iff1.getKey();		
					}	
				}
				for(Entry<String, Set<Binding>> iff1:iff.getValue().entrySet()) {
				//if(iff1.getValue().size()>0)
				//	for(Binding ifs:iff1.getValue())
				//		System.out.println("123This is successful now 3434343434123213123:"+ifs);

				if(a==null )
					if(iff1.getValue()==null)
						break;
					if(iff1.getValue()!=null)
					{
				    a.addAll(iff1.getValue().parallelStream().collect(Collectors.toList()));
					c=iff1.getKey();
					}
					else
						b=iff1.getKey();
	
				}
				System.out.println("This is successful now 3434343434:"+a);
		
			}
			*/
			 Set<Binding> intermediate = new HashSet<>();
				
			 	int k=0;
			/*if(BGPEval.urim!=null) {
				for(Entry<Vertex, LinkedHashSet<Binding>>  ur:BGPEval.urim.entrySet())
				{
								int y = ur.getKey().getNode().toString().compareTo(te.getTriple().getSubject().toString());
							int z= ur.getKey().getNode().toString().compareTo(te.getTriple().getObject().toString());
							System.out.println("This is the execution here77777777777:"+y+"--"+z);
											
							if(y==0 || z==0)
							{
								if(ur.getValue()==null)
									continue;
								
							System.out.println("This is the execution here555555555555:"+ur.getValue().size());
							intermediate.addAll(ur.getValue());
							if(intermediate.size()<10)
				System.out.println("This is the execution here666666666666666666:"+intermediate);
							for(Entry<Vertex, LinkedHashSet<Binding>> ur1:BGPEval.urim.entrySet())
								System.out.println("This is the warhead:"+ur1.getKey()+"--"+ur1.getValue().size());
					
							results =te.exec(intermediate,null);
				k=1;
			
			}
							System.out.println("This is the execution here9191919191:"+k);

						}
			}
			
			System.out.println("This is the execution here9191919191:"+k);

			if(k==0) {
			*///System.out.println("This is now gaining clarity:"+a.size()+"--"+b);
		/*
			if(a.size()>0&& b!=null)
		{
				for(Binding a1:a)
					System.out.println("This is now gaining clarity:"+a1+"--"+b);
			for(Binding a1:a) {
			intermediate.add(BindingFactory.binding(Var.alloc(b.substring(1)) , a1.get(Var.alloc(c.substring(1)))
					));
			
			}
			if(intermediate.isEmpty()) {
				System.out.println("This is intermediate here null");
					intermediate=null;
				}
	//	System.out.println("This is intermediate values in Inner Join:"+intermediate);
			results =te.exec(intermediate,null);//).parallel();}).join();
			BindJoin.inner=1;
			BindJoin.ciff=c;
			BindJoin.biff=b;
		}
				else
			*/	
			 	

			 	results =te.exec(null,null);		
			//	for(Binding jvl:results)
			//		System.out.println("This is rule no. 2 in BindJoin123123123123:" + jvl+"--"+joinVars);
				
			 	
			 	/*
				if ( ParaEng.InnerFilter!=null) {
					LinkedHashSet<Binding> temp1 = new LinkedHashSet<>();
					int br = 0;
					Var joinVars = null;
					List<Var> joinVarsList = new ArrayList<>();
					Iterator<Var> l11=null;
					Iterator<Binding> rIterator = results.iterator();
					//Set<Vertex> v1 = new HashSet<>();
				//	Multimap<Vertex,String> v1 = ArrayListMultimap.create();
					while(rIterator.hasNext()) 
						l11 = rIterator.next().vars();
					String f = null;
			//		for(Binding r:results)
					int keyOrvalue=0;	
					
					if(l11!=null)
						while (l11.hasNext()) {
							
							Var v3 = l11.next();
							
							System.out.println("This is rule no.3 in StartBinding123Large:" + v3);
										
									for(Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> iff:ParaEng.InnerFilter.entrySet())
				{	for(Entry<String, Set<Binding>> iff1:iff.getKey().entrySet())
					{		Var r = Var.alloc(iff1.getKey().substring(1));
						//	System.out.println("This is rule no.3 in StartBinding123Large:" + r + "--" + v3);
							joinVarsList.add(v3);
							if (r.equals(v3)) {
							//	joinVarsList.add(v3);
								keyOrvalue=1;
								f=iff1.getKey();
								joinVars = v3;
								br = 1;
						//		break;
							}

						}
				if(joinVars==null) {
					for(Entry<String, Set<Binding>> iff1:iff.getValue().entrySet())
					{		Var r = Var.alloc(iff1.getKey().substring(1));
							//System.out.println("This is rule no.3 in StartBinding123Large:" + r + "--" + v3);
							joinVarsList.add(v3);
							if (r.equals(v3)) {
								//joinVarsList.add(v3);
								keyOrvalue=2;
								f=iff1.getKey();
								joinVars = v3;
								br = 1;
						//		break;
							}

						}
				}
				}
						//if (br == 1) {
						//	br = 0;
						//	break;
						//}
						}
				//	System.out.println("This is timing within postprocess3:"+LocalTime.now());

					if (joinVars != null) {
						for(Var jvl:joinVarsList)
				System.out.println("This is rule no. 2 in BindJoin123123123123:" + jvl+"--"+joinVars);
						
						for (Binding e1 : results) {
							BindingHashMap extend = new BindingHashMap();// TODO do not use a new binding map
							
						//	System.out.println("These are the problems:"+e1.toString().replace("<", "").replace(">", "").replace("\"", ""));
							//List<Binding> t_temp=new ArrayList<>();
							for(Var jvl:joinVarsList)
								
							if (e1.get(jvl) != null) {
								if (e1.get(jvl).toString().contains("http"))
									extend.addAll(BindingFactory.binding(jvl, StageGen.StringConversion(e1.get(jvl)
											.toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", ""))));
								else
									extend.addAll(BindingFactory.binding(jvl, StageGen.StringConversion(
											e1.get(jvl).toString().replace("<", "").replace(">", "").substring(0
															))));
								
							}
							temp1.add(extend);
							
						}
					
					Iterator<Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>>> ifiterator = ParaEng.InnerFilter.entrySet().iterator();
					while(ifiterator.hasNext()) {
					Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> ifnext = ifiterator.next();
					if(keyOrvalue==1)
					ifnext.getKey().replace(f, temp1);
					if(keyOrvalue==2)
						
					ifnext.getValue().replace(f, temp1);
					}	
					
					for(	Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> eh: ParaEng.InnerFilter.entrySet())
						for(Entry<String,Set<Binding>> ee:eh.getKey().entrySet()) {
						int k1=0;
							
						for(Binding e1:ee.getValue()) {
						System.out.println("This is timing within postprocess454545454545 HashJoin:"+ee.getKey()+"--"+e1);
							k1++;
							if(k1==50)
								break;
							}
						}
					
			//		ParaEng.InnerFilter.put(f, temp1);
				//	for(	Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> eh:ParaEng.InnerFilter.entrySet())
				//		System.out.println("This is timing within postprocess454545454545:"+string+"--"+eh);

					
					
					}
						
				}
				*/
			 	//}
			//).parallel();}).join();
			
			int lp=0;
	//	for(Binding rr:results)
	//		System.out.println("This is the largest economy:"+r);
			if(BGPEval.urik!=null) {
				lp=1;
				for(Entry<Vertex, List<Binding>>  ur:BGPEval.urik.entrySet())
					
						{
							System.out.println("This is the execution here22222222:"+ur.getKey().getNode()+"234234");
							System.out.println("This is the execution here33333333:"+te.getTriple().getSubject()+"314324"+"--"+
									te.getTriple().getObject()+"2134324");
							int y = ur.getKey().getNode().toString().compareTo(te.getTriple().getSubject().toString());
						int z= ur.getKey().getNode().toString().compareTo(te.getTriple().getObject().toString());
						System.out.println("This is the execution here4444444444:"+y+"--"+z);
						if(y==0|| z==0)
							{
							System.out.println("This is the execution here11111111:");
								
								if(ur.getValue()==null)
							{
									List<Binding> rs = new ArrayList<>();
									rs.addAll(results);
				//					System.out.println("This is the execution here11111111:"+ur.getValue().size());
									ur.setValue(rs);
				       
							}		
			
								
									}
			}}
			/*	for(Entry<EdgeOperator, HashMap<Vertex, List<Binding>>> ur:BGPEval.urim.entrySet()) {
					if(ur.getKey().getEdge().getTriple().equals(te.getTriple()))
						for(Entry<Vertex,List<Binding>> ur1:ur.getValue().entrySet())
							if(ur1.getKey().getNode().equals(te.getTriple().getSubject())||
									ur1.getKey().getNode().equals(te.getTriple().getObject())	)
					ur1.setValue(results);
					 System.out.println("This is a good thing in BindJoin"+results);
						
				}
			*/
			System.out.println("This is a good thing finally6767676767 in 111:"+ParaEng.Filtereq+"--"+ParaEng.Filtereq.size());
			if (results.size() > 0 && ParaEng.Filtereq.size()>0) {
						
				 List<EdgeOperator> BushyTreeTriple = BoundQueryTask.BushyTreeTripleCreation(te.getTriple());
					Query query = BindJoin.buildQuery(te.getTriple(),BushyTreeTriple);
				//	 System.out.println("This is a good thing in BindJoin for good:"+BushyTreeTriple);
						
					List<String> Vertex=new ArrayList<>();
					 for(String btt:query.toString().split(" "))
						 
					 { if(btt.startsWith("?"))
						 Vertex.add(btt);
					 }
						
						String ne=BindJoin.NodeExtraction(Vertex);
							
						
						System.out.println("This is a good thing finally5656565656 in 111");
					//	if(results.size()>0)
					//	if(results.size()>0)
					//		{break;
					//		
					//		}
						if(ne!=null)
				{
							for( Entry<HashMap<String, String>, String> feq:ParaEng.Filtereq.entrySet())
								for(Entry<String,String> feq1:feq.getKey().entrySet())
							{
									 System.out.println("This is a good thing finally5656565656 in 2222:"+feq1.getKey());
										
							System.out.println("This is the nodeextraction:"+ne+"--"+feq1.getKey());
							
					if(feq1.getValue()==null && feq1.getKey().contains(ne)) {		
					System.out.println("This is the biggest problem now in HashJoin:"+feq);
					System.out.println("This is the biggest problem now11111 in HashJoin:"+feq1);
					
				//	HashMap<String,String> b = new HashMap<>();
				//	QueryTask.wfilter=1;
					feq.getKey().replace(feq1.getKey(), "Yes");
					System.out.println("This is a good thing finally2323232");

				//	br=1;
				//	break;
			//		b.put(feq1.getKey(), "No");
			//		ParaEng.Filtereq.put(b, feq.getValue());
				
						}
					}
				}
			}
			if ((results == null || results.size() == 0) && ParaEng.Filtereq.size()>0) 	{
				
				 List<EdgeOperator> BushyTreeTriple = BoundQueryTask.BushyTreeTripleCreation(te.getTriple());
					Query query = BindJoin.buildQuery(te.getTriple(),BushyTreeTriple);
						
					List<String> Vertex=new ArrayList<>();
					 for(String btt:query.toString().split(" "))
						 
					 { if(btt.startsWith("?"))
						 Vertex.add(btt);
					 }
					 System.out.println("This is a good thing in BindJoin"+Vertex);
						
						String ne=BindJoin.NodeExtraction(Vertex);
			
					
				
				System.out.println("This is a good thing finally5656565656 in 3333");
			//	if(results.size()>0)
			//	if(results.size()>0)
			//		{break;
			//		
			//		}
				
				 System.out.println("This is a good thing finally5656565656 in 111:"+ne);
								
				if(ne!=null)
		{
					for( Entry<HashMap<String, String>, String> feq:ParaEng.Filtereq.entrySet())
						for(Entry<String,String> feq1:feq.getKey().entrySet())
					{
							 System.out.println("This is a good thing finally5656565656 in 444:"+feq1.getKey());
								
					System.out.println("This is the nodeextraction:"+ne+"--"+feq1.getKey());
					
			if(feq1.getValue()==null && feq1.getKey().contains(ne)) {		
			System.out.println("This is the biggest problem now in HashJoin:"+feq);
			System.out.println("This is the biggest problem now11111 in HashJoin:"+feq1);
			
		//	HashMap<String,String> b = new HashMap<>();
			int kcount=0;
			int kactual=0;

			for(Entry<HashMap<String, String>, String> fr:ParaEng.Filtereq.entrySet())
			{
					kcount++;
					for(Entry<String, String> frkey:fr.getKey().entrySet())
						if(frkey.getValue()=="No")
							{
							kactual++;
							}
							
			}
			
			for(Entry<HashMap<String, String>, String> fr:ParaEng.Filtereq.entrySet())
			{
				if(fr.getValue().contains("||")) {
				if(kcount==(kactual+1))
					if(fr.getValue().contains("||")) {
						for(Entry<String, String> frkey:fr.getKey().entrySet())
							if(frkey.getValue()=="No")
								{
								//QueryTask.wfilter=1;
								fr.getKey().replace(feq1.getKey(), "No");
								System.exit(0);
								}
					
					}
					else {
						if(fr.getValue().contains("||")) {
						
									QueryTask.wfilter=1;
									fr.getKey().replace(feq1.getKey(), "No");
									
						
								
					}
					}
				}
					else	if(fr.getValue().contains("&&")) {
						
								QueryTask.wfilter=1;
								fr.getKey().replace(feq1.getKey(), "No");
								System.exit(0);
								
					}
					
					else {
						QueryTask.wfilter=1;
						fr.getKey().replace(feq1.getKey(), "No");
										}
				
			//	HashMap<String,String> b = new HashMap<>();
				
			
				
				/*for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>  es:HashJoin.NotJoinedTriplesLarge.entrySet())
				{System.out.println("This is now coupled problem000000000:"+eq.getValue()+"--"+eq1.getKey());

				

					for(Entry<List<EdgeOperator>, String> es2:es.getKey().entrySet())
						{
						System.out.println("This is now coupled problem:"+eq.getValue()+"--"+es2.getValue());
						if(es2.getValue().contains(eq.getValue()))
						{ 
							HashJoin.DisabledTriplesLarge.add(es2.getValue());
							System.out.println("This is now coupled problem111111111111:"+eq.getValue()+"--"+es2.getValue());
							
						}
						}
						}
				

				HashMap<List<EdgeOperator>, String> lmkey=new HashMap<>();*/
				/*for(String xy1: HashJoin.DisabledTriplesLarge)
				{
					if(!HashJoin.NotJoinedTriplesLarge.isEmpty() ||HashJoin.NotJoinedTriplesLarge!=null)
					{
						Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> njt = HashJoin.NotJoinedTriplesLarge.entrySet().iterator();
						for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>  ntl11:HashJoin.NotJoinedTriplesLarge.entrySet())
				{
					if(ntl11.getKey().toString().contains(xy1))
					{
						lmkey.putAll(ntl11.getKey());
						lm=1;
					}
					
				}
					}
					if(!HashJoin.JoinedTriplesLarge.isEmpty() ||HashJoin.JoinedTriplesLarge!=null)
					{		
					for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>  ntl11:HashJoin.JoinedTriplesLarge.entrySet())
				{
					if(ntl11.getKey().toString().contains(xy1)) {
						lmkey.putAll(ntl11.getKey());
						lm=1;
					}
						
				}
				}
				}
				
				
				if(lm==1) {
					HashJoin.NotJoinedTriplesLarge.remove(lmkey);
					HashJoin.JoinedTriplesLarge.remove(lmkey);
					
				lmkey.clear();
				}
				
				if(HashJoin.NotJoinedTriplesLarge.isEmpty() ||HashJoin.NotJoinedTriplesLarge==null
						|| HashJoin.JoinedTriplesLarge.isEmpty() ||HashJoin.JoinedTriplesLarge==null)
				{
			
					HashJoin.DisabledTriplesLarge.add(eq.getValue());
					}
				*/
				//if(lm==1)
				//	break;
				}
			
			/*for(Entry<HashMap<String, String>, String> fr:ParaEng.Filtereq.entrySet())
			{
				
				
				if(fr.getValue().contains("||")) {
					for(Entry<String, String> frkey:fr.getKey().entrySet())
						if(frkey.getValue()=="No")
							{
							QueryTask.wfilter=1;
							feq.getKey().replace(feq1.getKey(), "No");
							System.exit(0);}
				}
				

				if(fr.getValue().contains("&&")) {
					
						
							QueryTask.wfilter=1;
							feq.getKey().replace(feq1.getKey(), "No");
							System.exit(0);}
				
			}
			QueryTask.wfilter=1;
			feq.getKey().replace(feq1.getKey(), "No");
			a=new ArrayList<>();
			 b=null;
			 int f=0;
			 for(EdgeOperator e:BGPEval.OptionalAll)
				 if(te.getTriple().equals(e.getEdge().getTriple()))
				 { f=1;
					 break;
			 
				 }
			 if(f==1) {
				 f=0;
				 break;
			 }
				 results =te.exec(null,null);//).parallel();}).join();
			
			*/System.out.println("This is a good thing finally2323232");

		//	br=1;
		//	break;
	//		b.put(feq1.getKey(), "No");
	//		ParaEng.Filtereq.put(b, feq.getValue());
		
			}
			}
		}
	}
	
		for(Entry<HashMap<String, String>, String> as:ParaEng.Filtereq.entrySet())
		System.out.println("This is the replacement of filterreq in HashJoin:"+as);

	//		for(Binding r:results)
	//			System.out.println("This si the problem here:"+r);
		//	if(BGPEval.Edgetype=="Norm") {
/*			for(List<EdgeOperator> e:BGPEval.JoinGroupsListExclusive) {
				for(EdgeOperator e1:e)
					if(e1.getEdge().equals(edge)) {
						AllEdges.addAll(e);
					}
			}

			for(EdgeOperator e:BGPEval.JoinGroupsListLeft) {
			
					if(e.getEdge().equals(edge)) {
					AllEdges.add(e);
					}
			}
			
					for(EdgeOperator e1:BGPEval.JoinGroupsListRight) {
						
						if(e1.getEdge().equals(edge)) {
							AllEdges.add(e1);
						}
					}

//			}else {
					for(List<EdgeOperator> e:BGPEval.JoinGroupsListOptional) {
						for(EdgeOperator e1:e)
							if(e1.getEdge().equals(edge)) {
								AllEdges.addAll(e);
							}
					}

					for(EdgeOperator e:BGPEval.JoinGroupsListLeftOptional) {
					
							if(e.getEdge().equals(edge)) {
								AllEdges.add(e);
							}
					}
					
							for(EdgeOperator e1:BGPEval.JoinGroupsListRightOptional) {
								
								if(e1.getEdge().equals(edge)) {
									AllEdges.add(e1);
								}
							}
	//		}
			*/
			for(EdgeOperator ae:AllEdges)
				System.out.println("This is the final final final final:"+ae);
					//System.out.println("this is after hashjoin:"+te.getTriple()+"--"+results.size());
			if (edge.getV1().isBound() && edge.getV2().isBound()) {
				//System.out.println("This is out of TripleExectuion in HashJoin2:");

				start = edge.getV1();
				end = edge.getV2();
			//	results = QueryUtil.join(results, start.getBindings());
			//	results = QueryUtil.join(results, end.getBindings());
				//results = QueryUtil.join(results, input);
				// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!This is here in
				// HashJoin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
				// "+start.getBindings()+"--"+end.getBindings());

				if (Config.debug) {
					
					//System.out.println(edge + ": " + results.size());

				}
				//if (Config.relative_error) {
					//System.out.println("This is out of TripleExectuion in HashJoin4:");

//					RelativeError.real_resultSize.put(end, (double) results.size());
//					RelativeError.real_resultSize.put(start, (double) results.size());
					// RelativeError.addJoinCard(end,(double)results.size(),edge.getTriple(),tripleCard);
					// RelativeError.addJoinCard(start,(double)results.size(),edge.getTriple(),tripleCard);

//				}
				if (results.size() == 0) {
					//System.out.println("This is out of TripleExectuion in HashJoin5:");

					finished = true;
					// //System.out.println("SE shutdown");
	//				BGPEval.getCurrentExeService().shutdownNow();
				}
				if (start.getNode().isConcrete()) {
					//System.out.println("This is out of TripleExectuion in HashJoin6:");

			//		end.setBindings(results);
					synchronized (end) {
						end.notifyAll();
					}
				} else {
					//System.out.println("This is out of TripleExectuion in HashJoin7:");

		//			start.setBindings(results);
					synchronized (start) {
						start.notifyAll();
					}
				}
				start.removeEdge(edge);
				end.removeEdge(edge);
				return;
			}
			
		/*	String fq;
			if(edge.getV1().toString().contains("http:"))
			fq=edge.getV1().getNode().getURI().toString();
			else
				fq=edge.getV1().getNode().getName().toString();
			
				if(results.toString().contains(fq))
				{	//System.out.println("Theser are the vars of result in hashJoin edge.getV1:"+edge.getV1().getNode().getName().toString());
				start = edge.getV1();
				end = edge.getV2();
		
				}
				else
				{	//System.out.println("Theser are the vars of result in hashJoin edge.getV2:"+edge.getV1());
				start = edge.getV2();
				end = edge.getV1();
		
				}
			*/	
	//	System.out.println("This is the edge:"+edge);		
		if(results.size()==0)
		{
			System.out.println("This is the size of result if 0");
			return;
		}else {
			
			
		
			
			if ( ParaEng.InnerFilter!=null && (!BGPEval.StartBinding123.containsKey(edge.getV1())) && !BGPEval.StartBinding123.containsKey(edge.getV1())) {
				Iterator<Binding> rIterator;
				List<Binding> temp1 = new ArrayList<>();
				int br = 0;
				int keyOrvalue=0;
				Var joinVars = null;
				Iterator<Var> l11=null;
				
			rIterator = results.iterator();
				while(rIterator.hasNext()) 
				l11 = rIterator.next().vars();
				//Set<Vertex> v1 = new HashSet<>();
				Multimap<Vertex,String> v1 = ArrayListMultimap.create();

				String f = null;
				
				if(l11!=null)
					while (l11.hasNext()) {
						Var v3 = l11.next();
								for(Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>> iff:ParaEng.InnerFilter.entrySet())
			{	for(Entry<String, List<Binding>> iff1:iff.getKey().entrySet())
				{		Var r = Var.alloc(iff1.getKey().substring(1));
						System.out.println("This is rule no.3 in StartBinding123Large:" + r + "--" + v3);

						if (r.equals(v3)) {
							f=iff1.getKey();
							joinVars = v3;
							br = 1;
							keyOrvalue=1;
					//		break;
						}

					}
			if(joinVars==null) {
				for(Entry<String, List<Binding>> iff1:iff.getValue().entrySet())
				{		Var r = Var.alloc(iff1.getKey().substring(1));
						System.out.println("This is rule no.3 in StartBinding123Large:" + r + "--" + v3);

						if (r.equals(v3)) {
							f=iff1.getKey();
							joinVars = v3;
							br = 1;
							keyOrvalue=2;
					//		break;
						}

					}
			}
			}
					//if (br == 1) {
					//	br = 0;
					//	break;
					//}
					}
			//	System.out.println("This is timing within postprocess3:"+LocalTime.now());

				if (joinVars != null) {
//			System.out.println("This is rule no. 2 in BindJoin:" + joinVars);

					for (Binding e1 : results) {
						if (e1.get(joinVars) != null) {
					//System.out.println("These are the problems:"+e1.get(joinVars).toString().replace("<", "").replace(">", "").replace("\"", ""));
							if (e1.get(joinVars).toString().contains("http"))
								temp1.add(BindingFactory.binding(joinVars, StageGen.StringConversion(e1.get(joinVars)
										.toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", ""))));
							else
								temp1.add(BindingFactory.binding(joinVars, StageGen.StringConversion(
										e1.get(joinVars).toString().replace("<", "").replace(">", "").substring(0,
												e1.get(joinVars).toString().replace("<", "").replace(">", "").replace(" ", "").length()
														- 2))));
						}

					}
			//		for(	Entry<Vertex, Set<Binding>> eh:BGPEval.StartBinding123.entrySet())
			//			System.out.println("This is timing within postprocess454545454545 before:"+"--"+eh);
				Iterator<Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>>> ifiterator = ParaEng.InnerFilter.entrySet().iterator();
				while(ifiterator.hasNext()) {
				Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>> ifnext = ifiterator.next();
				if(keyOrvalue==1)
				ifnext.getKey().replace(f, temp1);
				if(keyOrvalue==2)
				ifnext.getValue().replace(f, temp1);
				}	
				
		//		ParaEng.InnerFilter.put(f, temp1);
		//		for(	Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> eh:ParaEng.InnerFilter.entrySet())
		//			System.out.println("This is timing within postprocess454545454545:"+"--"+eh);

				
				
				}
					
			}
		
		
			
			
			List<List<EdgeOperator>> JoinGroupsListLeftTemp= new ArrayList<>();
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeft);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRight);
			JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListExclusive);
			JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListOptional);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeftOptional);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRightOptional);
			JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListMinus);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeftMinus);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRightMinus);
		
			List<Edge> edgee= new ArrayList<>();
			for(EdgeOperator jgl:BGPEval.JoinGroupsListLeft)
				if(jgl.getEdge().equals(edge))
					edgee.add(jgl.getEdge());
			for(EdgeOperator jgl:BGPEval.JoinGroupsListRight)
				if(jgl.getEdge().equals(edge))
					edgee.add(jgl.getEdge());
			for(List<EdgeOperator> jgl:BGPEval.JoinGroupsListExclusive)
				if(jgl.get(0).getEdge().equals(edge))
					for(EdgeOperator jgll:jgl)
					edgee.add(jgll.getEdge());
			
			for(EdgeOperator jgl:BGPEval.JoinGroupsListLeftOptional)
				if(jgl.getEdge().equals(edge))
					edgee.add(jgl.getEdge());
			for(EdgeOperator jgl:BGPEval.JoinGroupsListRightOptional)
				if(jgl.getEdge().equals(edge))
					edgee.add(jgl.getEdge());
			for(List<EdgeOperator> jgl:BGPEval.JoinGroupsListOptional)
				if(jgl.get(0).getEdge().equals(edge))
					for(EdgeOperator jgll:jgl)
					edgee.add(jgll.getEdge());
			
			
		
			
			//	synchronized(BindJoin.StartBinding123) {
			//	ForkJoinPool fjp = new ForkJoinPool();
			//for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> e:BGPEval.StartBindingSetBJ.entrySet())
								//BGPEval.HashJoinCompletion++;

		//BindJoin.StartBinding123.notifyAll();
			//		}
			
			int xz=0;
	//	for(Entry<Triple, Integer> ab:QueryTask.CompletionValue.entrySet())
		//	if()
			
		//	while(QueryTask.CompletionValue.containsKey(te.getTriple())&& (QueryTask.CompletionValue.values().contains(0)))
		//	{	
		//		if(results.size()!=0)
		//		{	
		//			break;
		//		}
				//else
		//	}
		
		//	else break;		
		//	QueryTask.CompletionValue
	//	if(	BGPEval.ExclusivelyExclusive==1)
			/*if(QueryTask.CompletionValue.containsKey(te.getTriple())&& (QueryTask.CompletionValue.values().contains(0)))
			{	if(results.size()!=0)
				{	
			//		break;
				}
				//else
				//	////System.out.printlnln("");
			}*/
				//	log.debug("111111111111111This is out of TripleExectuion");
		//	log.debug("");
		System.out.println("1232131231231231232112321312312312321312323213123123213");
		//if(lp==0) {
		ForkJoinPool fjp = new ForkJoinPool();
			try {
		//		for(EdgeOperator ae:HashJoin.AllEdges)
		//		System.out.println("These are alledges:"+ae);
				
				
				fjp.submit(()->IntermediateProcedure(results,AllEdges,edgee)).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		fjp.shutdown();
		//}

				//synchronized(BGPEval.finalResult) {
	//Edge	CurrentEdgeOperator=  new Edge(edge.getV1(),edge.getV2());//edge.getV1()+"--"+edge.getV2();
		
	 Iterator<Entry<EdgeOperator, List<Binding>>> frIterator = BGPEval.finalResult.entrySet().iterator();
		while(frIterator.hasNext()) {
			total++;
			Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
		
			if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
			{
	//			System.out.println("This is finalResult:"+ab.getKey()+"--"+edge+"--"+results.size());
				BGPEval.finalResult.replace(ab.getKey(), results); 
	//			CurrentEdgeOperator=ab.getKey();
			}
				//	}
	///		for(Entry<EdgeOperator, List<Binding>> r:BGPEval.finalResult.entrySet())
	//			for(Binding r1:r.getValue())
	//			System.out.println("These are the results:"+r1);

	 //for(Entry<EdgeOperator, ArrayList<Binding>> ab:BGPEval.finalResult.entrySet())
	
}
//synchronized(BGPEval.finalResultOptional) {
		for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultRightOptional.entrySet())
		{	total++;	
			if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
			{	BGPEval.finalResultRightOptional.replace(ab.getKey(), results);
			}
		
			//	CurrentEdgeOperator=ab.getKey();
			
			}
		for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultLeftOptional.entrySet())
		{total++;
			if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
			{	BGPEval.finalResultLeftOptional.replace(ab.getKey(), results);
		//	CurrentEdgeOperator=ab.getKey();
			
			}
		}
for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultOptional.entrySet())
{	total++;
	if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
			{	BGPEval.finalResultOptional.replace(ab.getKey(), results);
		//	CurrentEdgeOperator=ab.getKey();
			
			}
}

for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultRightMinus.entrySet())
{	total++;	
	if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
	{	BGPEval.finalResultRightMinus.replace(ab.getKey(), results);
	}

	//	CurrentEdgeOperator=ab.getKey();
	
	}
for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultLeftMinus.entrySet())
{total++;
	if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
	{	BGPEval.finalResultLeftMinus.replace(ab.getKey(), results);
//	CurrentEdgeOperator=ab.getKey();
	
	}
}
for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultMinus.entrySet())
{	total++;
if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
	{	BGPEval.finalResultMinus.replace(ab.getKey(), results);
//	CurrentEdgeOperator=ab.getKey();
	
	}
}
//}}

//synchronized(BGPEval.finalResultRight) {

//for(Entry<EdgeOperator, List<Binding>> lkd:BGPEval.finalResult.entrySet()) {
//	System.out.println("This is finalResult:"+lkd);
//}

//for(Entry<HashSet<List<EdgeOperator>>, Integer> lkd:BGPEval.linkingTreeDup.entrySet()) {
//	System.out.println("This is linkingTreeDup:"+lkd);
//}

//	System.out.println("This is CurrentEdgeOperator:"+CurrentEdgeOperator);




for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultRight.entrySet())
{total++;

if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
				{
//	System.out.println("This is finalResultRight:"+ab.getKey()+"--"+edge+"--"+results.size());
//	for(Entry<EdgeOperator, List<Binding>> fr:BGPEval.finalResult.entrySet())
//		System.out.println("This is finalResult:"+fr.getKey()+"--"+edge+"--"+fr.getValue().size());

	BGPEval.finalResultRight.replace(ab.getKey(), results);
		//		CurrentEdgeOperator=ab.getKey();
				
				}
}
for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultLeft.entrySet())
{
	total++;

	if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
		{BGPEval.finalResultLeft.replace(ab.getKey(), results);
//		CurrentEdgeOperator=ab.getKey();
		
		}
}
//}	

//synchronized(BGPEval.finalResultLeft) {
//for(Entry<EdgeOperator, List<Binding>> fr:BGPEval.finalResult.entrySet())
//System.out.println("This is fr:"+fr.getKey()+"--"+fr.getValue().size());

//if(BGPEval.finalResultRight.size()>0 ||BGPEval.finalResultRight!=null)
//for(Entry<EdgeOperator, List<Binding>> frr:BGPEval.finalResultRight.entrySet())
//System.out.println("This is frr:"+frr.getKey()+"--"+frr.getValue().size());


//for (Entry<Vertex, Set<Binding>> e : BGPEval.StartBinding123.entrySet()) {
//	 System.out.println("These are intermediate333333:"+e.getKey()+"--"+e.getValue());
//	}

for(List<EdgeOperator>  e2:JoinGroupsListLeftTemp) {
			
				for(EdgeOperator e3:e2)
					if(e3.getEdge().equals(edge))
				{
					ProcessedEdgeOperators.add(e2);
				}
			}
//System.out.println("This is now the new tree right:"+ProcessedEdgeOperators+"--"+CurrentEdgeOperator);
			int count=0;
			frIterator = BGPEval.finalResult.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2:"+ee);
							ProcessedTriples.put(CompleteEdgeOperator(ab.getKey()),ab.getValue());
							
					count++;
					}
								}
					}
			////System.out.printlnln("This is now the new tree right count:"+count);
			count=0;
			frIterator = BGPEval.finalResultLeft.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo Rights:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Right:"+ee);
				//count++;
							List<EdgeOperator> l = new ArrayList<>();
				l.add(ab.getKey());
							ProcessedTriples.put(l,ab.getValue());
				
					}
								}
					}
			//////System.out.printlnln("This is now the new tree right count:"+count);
			frIterator = BGPEval.finalResultRight.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo Rights:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Right:"+ee);
				//count++;
							List<EdgeOperator> l = new ArrayList<>();
				l.add(ab.getKey());
							ProcessedTriples.put(l,ab.getValue());
				
					}
								}
					}
			if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ) 
			{
			frIterator = BGPEval.finalResultOptional.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2:"+ee);
							ProcessedTriples.put(CompleteEdgeOperator(ab.getKey()),ab.getValue());
							
					count++;
					}
					}
					}
					}
			////System.out.printlnln("This is now the new tree right count:"+count);
		//	System.out.println("This is now the new finalResultLeftOptional:"+BGPEval.finalResultLeftOptional);
			if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) 
			{
			frIterator = BGPEval.finalResultLeftOptional.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{	//	////System.out.printlnln("THis is coming to final algo Left:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Left:"+ee);
							List<EdgeOperator> l = new ArrayList<>();
							l.add(ab.getKey());
										ProcessedTriples.put(l,ab.getValue());
							//count++;
					
					}
								}
					}
		}


			
			//////System.out.printlnln("This is now the new tree right count:"+count);
	//		System.out.println("This is now the new finalResultRightOptional:"+BGPEval.finalResultRightOptional);
			
			if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ) 
			{		
			frIterator = BGPEval.finalResultRightOptional.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo Rights:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Right:"+ee);
				//count++;
							List<EdgeOperator> l = new ArrayList<>();
				l.add(ab.getKey());
							ProcessedTriples.put(l,ab.getValue());
					}	
					}
								}
					}

			
			if(ParaEng.Optional.contains("MINUS") ) 
			{
			frIterator = BGPEval.finalResultMinus.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2:"+ee);
							ProcessedTriples.put(CompleteEdgeOperator(ab.getKey()),ab.getValue());
							
					count++;
					}
					}
					}
					}
			////System.out.printlnln("This is now the new tree right count:"+count);
		//	System.out.println("This is now the new finalResultLeftOptional:"+BGPEval.finalResultLeftOptional);
			if(ParaEng.Optional.contains("MINUS")) 
			{
			frIterator = BGPEval.finalResultLeftMinus.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{	//	////System.out.printlnln("THis is coming to final algo Left:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Left:"+ee);
							List<EdgeOperator> l = new ArrayList<>();
							l.add(ab.getKey());
										ProcessedTriples.put(l,ab.getValue());
							//count++;
					
					}
								}
					}
		}


			
			//////System.out.printlnln("This is now the new tree right count:"+count);
	//		System.out.println("This is now the new finalResultRightOptional:"+BGPEval.finalResultRightOptional);
			
			if(ParaEng.Minus.contains("MINUS") ) 
			{		
			frIterator = BGPEval.finalResultRightMinus.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo Rights:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Right:"+ee);
				//count++;
							List<EdgeOperator> l = new ArrayList<>();
				l.add(ab.getKey());
							ProcessedTriples.put(l,ab.getValue());
					}	
					}
								}
					}

			int IsUnion=0;
			
		
			if(!ParaEng.Union.isEmpty() ) 
			{
			
			frIterator = BGPEval.finalResultOptional.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				//System.out.println("This is finalReusltOptional:"+ab);
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
				{//	System.out.println("This is finalReusltOptional1111:"+ee);
				
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2:"+ee);
							ProcessedTriplesUnion.put(CompleteEdgeOperator(ab.getKey()),ab.getValue());
							IsUnion=1;
					count++;
					}
					}
					}
					}
					}
			////System.out.printlnln("This is now the new tree right count:"+count);
			if( !ParaEng.Union.isEmpty()) 
			{
			frIterator = BGPEval.finalResultLeftOptional.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{	//	////System.out.printlnln("THis is coming to final algo Left:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Left:"+ee);
							List<EdgeOperator> l = new ArrayList<>();
							l.add(ab.getKey());
										ProcessedTriplesUnion.put(l,ab.getValue());
							//count++;
					IsUnion=1;
					}
								}
					}
		}
			//////System.out.printlnln("This is now the new tree right count:"+count);
		
			if(!ParaEng.Union.isEmpty() ) 
			{		
			frIterator = BGPEval.finalResultRightOptional.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(List<EdgeOperator> ee:ProcessedEdgeOperators)
					for(EdgeOperator ee1:ee) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo Rights:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Right:"+ee);
				//count++;
							List<EdgeOperator> l = new ArrayList<>();
				l.add(ab.getKey());
					
				ProcessedTriplesUnion.put(l,ab.getValue());
				IsUnion=1;
					}	
					}
								}
					}

			//count=0;

		for(List<EdgeOperator> et:EvaluatedTriples)
		{ProcessedTriples.remove(et);
		}
		//for(Entry<List<EdgeOperator>, List<Binding>> r:ProcessedTriples.entrySet())
	//	System.out.println("This is now the new processed triples:"+r.getKey()+"--"+r.getValue().size());
		
				ProcessingTask(JoinedTriples,ProcessedTriples,0,0);
			ProcessingTask(NotJoinedTriples,ProcessedTriples,0,0);

	//	}else
	//	{
		//  if(IsUnion==1)		{
				
		//  }
		
		}
		
		
		System.out.println("THis is NotJoinedTriples JoinedTriples:"+NotJoinedTriples.keySet()+"--"+JoinedTriples.keySet()+"--"+EvaluatedTriples);
		
		for(List<EdgeOperator> et:EvaluatedTriples)
		{
			System.out.println("THis is comaprison NotJoinedTriples Evaluated Hash:"+HashJoin.NotJoinedTriples.keySet()+"--"+et);

			
			NotJoinedTriples.remove(et);
		}

		if(NotJoinedTriples.size()==1) {
			for(int j=0;j<5;j++) {
			ProcessingTask(NotJoinedTriples, HashJoin.JoinedTriples, 0,0);
			ProcessingTask(JoinedTriples,HashJoin.NotJoinedTriples, 0,0);
			}
		}
			else	
		
for(int i=0;i<5;i++){	
	

//	ForkJoinPool fjp1 = new ForkJoinPool();
//	fjp1.submit(()->{
		BGPEval.finalResultCalculation(NotJoinedTriples,EvaluatedTriples,JoinedTriples,0);//}).join();
//	fjp1.shutdown();
//	ForkJoinPool fjp2 = new ForkJoinPool();
	
//fjp2.submit(()->{
	BGPEval.finalResultCalculation(JoinedTriples,EvaluatedTriples,JoinedTriples,1);//}).join();
//	fjp2.shutdown();

//	ForkJoinPool fjp21 = new ForkJoinPool();
	
//fjp21.submit(()->{
	
	BGPEval.finalResultCalculation(NotJoinedTriples,EvaluatedTriples,NotJoinedTriples,0);//}).join();
//	fjp21.shutdown();
}

if(!ParaEng.Union.isEmpty())		 
ProcessUnion();
//	}
	//	for(Entry<EdgeOperator, ArrayList<Binding>> fr: BGPEval.finalResult.entrySet())
	//		if(fr.getValue().size()>0)
//		log.info("This is out of TripleExectuion in HashJoin:"+results.size());
		TripleCard tripleCard = new TripleCard();
	/*	if (Config.relative_error) {
			log.info("This is out of TripleExectuion in HashJoin1:");
			
			tripleCard.real_Card = results.size();
			tripleCard.estimated_card = edge.estimatedCard();
		}*/

		// if both vertices are bound
		if (edge.getV1().isBound() && edge.getV2().isBound()) {
	//		log.info("This is out of TripleExectuion in HashJoin2:");

			start = edge.getV1();
			end = edge.getV2();
			//results = QueryUtil.join(results, start.getBindingSets(),results, start.getBindingSets());
			//results = QueryUtil.join(results, end.getBindingSets(),results, end.getBindingSets());
			//results = QueryUtil.join(results, input,results, input);
			// log.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
			// HashJoin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
			// "+start.getBindingSets()+"--"+end.getBindingSets());

		/*	if (Config.debug) {
				
				log.info(edge + ": " + results.size());

			}
			if (Config.relative_error) {
				log.info("This is out of TripleExectuion in HashJoin4:");

				RelativeError.real_resultSize.put(end, (double) results.size());
				RelativeError.real_resultSize.put(start, (double) results.size());
				// RelativeError.addJoinCard(end,(double)results.size(),edge.getTriple(),tripleCard);
				// RelativeError.addJoinCard(start,(double)results.size(),edge.getTriple(),tripleCard);

			}*/
			if (results.size() == 0) {
		//		log.info("This is out of TripleExectuion in HashJoin5:");

				finished = true;
				// log.info("SE shutdown");
			//	BGPEval.getCurrentExeService().shutdownNow();
			}
			if (start.getNode().isConcrete()) {
			//	log.info("This is out of TripleExectuion in HashJoin6:");

			//	end.setBindings(results);
				synchronized (end) {
					end.notifyAll();
				}
			} else {
				//log.info("This is out of TripleExectuion in HashJoin7:");

			//	start.setBindings(results);
	//			synchronized (start) {
	//				start.notifyAll();
	//			}
			}
		//	start.removeEdge(edge);
		//	end.removeEdge(edge);
			return;
		}

	
		/*	if(results.toString().contains(edge.getV1().getNode().toString()))
			{//	log.info("Theser are the vars of result in hashJoin edge.getV1:"+edge.getV1());
			start = edge.getV1();
			end = edge.getV2();
	
			}
			else
			{	//log.info("Theser are the vars of result in hashJoin edge.getV2:"+edge.getV1());
			start = edge.getV2();
			end = edge.getV1();
	
			}*/
		// only one vertex is bound
	/*	if (edge.getV1().isBound()) {
			log.info("This is out of TripleExectuion in HashJoin8:");

			start = edge.getV1();
			end = edge.getV2();
		} else {
			log.info("This is out of TripleExectuion in HashJoin9:");

			start = edge.getV1();
			end = edge.getV2();
		}*/
/*			synchronized(BindJoin.StartBinding123) {
//	for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> e:BGPEval.StartBindingSetBJ.entrySet())
//		log.info("This is end 23 set BindingSet in HashJoin:"+e.getKey()+"--"+edge+"--"+edge);
	Vertex start1 = new Vertex();
	start1=start;
//	////System.out.printlnln("This is here now before error"+results.size()+"--"+start.getBindingSets()+"--"+results1.size()+"--"+start1.getBindingSets());
//		results = QueryUtil.join(results, start.getBindingSets(),results1, start1.getBindingSets());
		for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> e:BGPEval.StartBindingSetBJ.entrySet())
		{	for(Entry<Set<Vertex>, Set<Edge>> f:e.getKey().entrySet())
			{
//			log.info("This is en  d 24 set BindingSet in HashJoin11111:"+BGPEval.StartBindingSetBJ);
			for(Vertex fCondition:f.getKey()) {
				if((fCondition.toString().equals(start.toString()))) {
//					if((fCondition.toString().equals(end)) ||(fCondition.toString().equals(edge.getV2().toString()))) {

					for(Edge f1:f.getValue())
				if(f1.toString().equals(edge.toString()))
			{
	//				log.info("This is end 24 set BindingSet in HashJoin1.1111.1.1111:"+f1+"--"+f );
					
			ArrayList<Binding> x = new ArrayList<Binding>();		
				x.addAll(results);
				ConcurrentHashMap<Set<Vertex>,Set<Edge>> b = new ConcurrentHashMap<>();
			b.put(f.getKey(),f.getValue());
		//	BGPEval.StartBindingSet.put(b,new ArrayList<Binding>(results));
				
			BindJoin.StartBinding123.put(b,x);
		//		log.info("This is end 24 set BindingSet in HashJoin0000:"+results.size());
skp=1;
				break;
		//				BGPEval.StartBindingSet.remove(e);
			//	BGPEval.StartBindingSet.put(e.getKey(),a);
			}
				if(skp==1)
					break;
				}
				if(skp==1)
				break;
			}
			if(skp==1)
				break;
			}
		if(skp==1) {
			skp=0;
			break;
		}
		}
 
		
	*/		
		//////System.out.printlnln("This is now the new tree:"+BGPEval.finalResult);
		//////System.out.printlnln("This is now the new tree left:"+BGPEval.finalResultLeft);
		//////System.out.printlnln("This is now the new tree right:"+BGPEval.finalResultRight);

				
	//		finalResultCalculation()
			
		//	log.info("This is end 23 set BindingSet in HashJoin:"+results );

//		BGPEval.StartBindingSet.put(BGPEval.b,BGPEval.a);
	//	log.info("This is end 24 set BindingSet in HashJoin0000:"+BGPEval.StartBindingSet);
		//log.info("This is end 25 set BindingSet in HashJoin:"+results );
		// log.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
		// HashJoin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+start.getBindingSets());

		if(!ParaEng.Union.isEmpty()==false) {
		//	ArrayList<Binding> input1 = new ArrayList<Binding>();
		//	if(input!=null)
		//		input1.addAll(input);
			//Vertex start1 = new Vertex();
			//start1=start;
//////System.out.printlnln("This is great solution:");
//	ArrayList<Binding> results1 = new ArrayList<Binding>();
//if(results !=null)
//	for(BindingSet r:results1)
//results1.add(r);
		//		if(results!=null || input!=null)
			//results = QueryUtil.join(results, input);
		}
		
		

				if (results.size() == 0) {
			finished = true;
			// log.info("SE shutdown");
		//	BGPEval.getCurrentExeService().shutdownNow();
		}
		
		//log.info("This is end 24 set BindingSet in HashJoin0000000000111:"+ BGPEval.StartBindingSet);
	//	if(!ParaEng.Union.isEmpty()==false) 
	//	end.setBindings(results);
	//	sBindingSet = start.getBindingSets();
		//for(BindingSet r:results)
	//	log.info("This is end 25 set BindingSet in HashJoin:"+results.size());
		//	for(BindingSet r:results)
	//	synchronized (end) {
	//		end.notifyAll();
	//	}		
			//	log.info("End statement in HashJoin notified1:"+start.getBindingSets());
		//	log.info("End statement in HashJoin notified2:"+results);
		//	log.info("End statement in HashJoin notified3:"+end.getBindingSets());
			
		//	{	BindJoinWhenEnd=new ArrayList<BindingSet>(end.getBindingSets());
//
//			te.exec(BindJoinWhenEnd);
//			}
		//}
	//	log.info("This is end 26 set BindingSet in HashJoin:"+start.getBindingSets() );
	//	log.info("This is end 27 set BindingSet in HashJoin:"+end.getBindingSets() );
//	setInput(null);
	//	if(start.getBindingSets()==null)
	//	if(end.getBindingSets() != null) {
	//	BindJoinWhenEnd=new ArrayList<BindingSet>(end.getBindingSets());
	//	te.exec(BindJoinWhenEnd);
	//}
	//	start.removeEdge(edge);
	//	end.removeEdge(edge);
	
//		BGPEval.HashJoinCompletion++;
	//	if(BGPEval.HashJoinCompletion>3)
		//	BindJoin.StartBindingSet123.notifyAll();
}
		else {
			int end1=0;
			
			for(Entry<HashMap<String, String>,  String>  eq:ParaEng.FiltereqLarge.entrySet()) 
				for(Entry<String, String>  eq1:eq.getKey().entrySet())
			{
					if(HashJoin.DisabledTriplesLarge.contains(eq.getValue()))
						continue;
				/*	for(String xy1:HashJoin.DisabledTriplesLarge)
					{	System.out.println("This is DisabledTriplesLarge:"+eq.getValue()+"--"+xy1);
					
						if(eq.getValue().equals(xy1))
						{end1=1;
						end1=0;
							break;}
					}
					if(end1==1)
					{end1=0;
					continue;
					}
				*/	List<EdgeOperator> BushyTreeTriple = QueryTask.BushyTreeTripleCreation(te.getTriple());
					Query query = BindJoin.buildQuery(te.getTriple(),BushyTreeTriple);
					// System.out.println("This is a good thing in HashJoin for cal:"+query);
						
					List<String> Vertex=new ArrayList<>();
					 for(String btt:query.toString().split(" "))
						 
					 { if(btt.startsWith("?"))
						 Vertex.add(btt);
					 }
					 int bounded=0;
					 for(String av:Vertex)
						
									
								if(eq.getValue().contains(av.replace("\n", "")))		
									bounded=1;
				if(bounded==1) {
					 String x=BindJoin.NodeExtraction1(query.toString(),eq1.getKey());
				System.out.println("THis is the problem problem: is problem:"+x+"--"+Vertex);
				if(x.equals("not"))
					continue;
				}
					results=null;
					

					//if(
					//		!Completed.contains(te.getTriple()+"--"+eq.getValue()) 
					//		) {
					System.out.println("This is weired now:"+eq1.getKey());
					System.out.println("This is weired now1:"+te.getTriple()+"--"+eq.getValue());
					int k=0;
					if(ParaEng.InnerFilter!=null) {
					List<Binding> a=new ArrayList<>();
					   String b=null;
					 String c=null;
					for(Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>> iff:ParaEng.InnerFilter.entrySet()) {
						for(Entry<String, List<Binding>> iff1:iff.getKey().entrySet()) {
							if(iff1.getValue()==null)
							b=iff1.getKey();
							else {
								a.addAll(iff1.getValue().parallelStream().collect(Collectors.toList()));
							c=iff1.getKey();
									
							}
							
								
						}
						for(Entry<String, List<Binding>> iff1:iff.getValue().entrySet()) {
						if(a==null )
							if(iff1.getValue()==null)
								break;
							if(iff1.getValue()!=null)
							{a.addAll(iff1.getValue().parallelStream().collect(Collectors.toList()));
							c=iff1.getKey();
							}
							else
								b=iff1.getKey();
			
						}
					}
					System.out.println("This is now gaining clarity:"+a.size()+"--"+b);
					
					Set<Binding> intermediate = new HashSet<>();
						if(a.size()>0&& b!=null)
				{
					for(Binding a1:a) {
					intermediate.add(BindingFactory.binding(Var.alloc(b.substring(1)) , a1.get(Var.alloc(c.substring(1)))
							));
					}
					results =te.exec(intermediate,null);//).parallel();}).join();
					k=1;
				}
						
					}
					
					if(k==1) {
						k=0;
					}
					else	if(Completed890.size()>0 || !Completed890.isEmpty())
					{
					String skip=te.getTriple()+"--"+eq.getValue();
			for(String c1:Completed890) {
				System.out.println("This is weired now1.5:"+c1);	
			}
			if(!Completed890.contains(skip))
					results =te.exec(null,eq1.getKey());
			
					
					else if (Completed890.contains(skip))
						continue;
					}
			else
				results =te.exec(null,eq1.getKey());
					
					System.out.println("This is now exercising here");
					
					//}//).parallel();}).join();
	
					
					//	if(QueryTask.bounded==1 && (results == null || results.size() == 0))
		//		{
		//		System.out.println("----------This hashjoin will be continued------------");
		//		continue;}
					
					
					if(QueryTask.bounded==0)
					Completed890.add(te.getTriple()+"--"+eq.getValue());
					
							List<List<EdgeOperator>> JoinGroupsListLeftTemp= new ArrayList<>();
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeft);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRight);
			JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListExclusive);
			JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListOptional);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeftOptional);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRightOptional);
			JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListMinus);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeftMinus);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRightMinus);
			
			

			QueryTask.notInclined=0;
			int br=0;
			if(QueryTask.falsified==1 && QueryTask.bounded==1)
			if ((results == null || results.size() == 0) && ParaEng.FiltereqLarge!=null  ) 	{
				
				
				/* List<EdgeOperator> BushyTreeTriple = BoundQueryTask.BushyTreeTripleCreation(te.getTriple());
				Query query = BindJoin.buildQuery(te.getTriple(),BushyTreeTriple);
				 System.out.println("This is a good thing in BindJoin"+query);
					
				List<String> Vertex=new ArrayList<>();
				 for(String btt:query.toString().split(" "))
					 
				 { if(btt.startsWith("?"))
					 Vertex.add(btt);
				 }
					System.out.println("This is a good thing in BindJoin"+te.getTriple()+"--"+Vertex);
							
				String ne=BindJoin.NodeExtraction(Vertex);
					
				
				System.out.println("This is a good thing finally5656565656");
			//	if(results.size()>0)
				
				
				if(ne!=null)
		{
					
					for( Entry<HashMap<String, String>, String> feq:ParaEng.FiltereqLarge.entrySet())
						for(Entry<String,String> feq1:feq.getKey().entrySet())
					{
							 System.out.println("This is a good thing finally5656565656:"+feq1.getKey());
								
					System.out.println("This is the nodeextraction:"+ne+"--"+feq1.getKey());
					
			if(feq1.getValue()==null && feq1.getKey().contains(ne)) {		
			System.out.println("This is the biggest problem now in HashJoin:"+feq);
			System.out.println("This is the biggest problem now11111 in HashJoin:"+feq1);
			
	*/
				int lm=0;
				
			for(Entry<HashMap<String, String>, String> fr:ParaEng.FiltereqLarge.entrySet())
			{
				List< String> xy= new ArrayList<>();
				
				if(fr.getValue().contains("||")) {
				
					int kcount=0;
					int kactual=0;
					
					for(Entry<HashMap<String, String>, String> fr1:ParaEng.FiltereqLarge.entrySet())
					{
							kcount++;
							for(Entry<String, String> frkey:fr1.getKey().entrySet())
								if(frkey.getValue()=="No")
									{
									kactual++;
									}
									
					}
					
					
					if(kcount==(kactual+1))
						if(fr.getValue().contains("||")) {
							for(Entry<String, String> frkey:fr.getKey().entrySet())
								if(frkey.getValue()=="No")
									{
									QueryTask.wfilter=1;
									fr.getKey().replace(eq1.getKey(), "No");
									System.exit(0);
									}
						
						}
				
					else {
						if(fr.getValue().contains("||")) {
							
							for(Entry<String, String> frkey:fr.getKey().entrySet())
								if(frkey.getValue()=="No")
									{
									QueryTask.wfilter=1;
									fr.getKey().replace(eq1.getKey(), "No");
									}
										
							
									
						}
						}
					}
					
					
						else if(fr.getValue().contains("&&")) {
						
								QueryTask.wfilter=1;
								fr.getKey().replace(eq1.getKey(), "No");
								for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>  es:HashJoin.NotJoinedTriplesLarge.entrySet())
								{System.out.println("This is now coupled problem000000000:"+eq.getValue()+"--"+eq1.getKey());

								

									for(Entry<List<EdgeOperator>, String> es2:es.getKey().entrySet())
										{
										System.out.println("This is now coupled problem:"+eq.getValue()+"--"+es2.getValue());
										if(es2.getValue().contains(eq.getValue()))
										{ 
											HashJoin.DisabledTriplesLarge.add(es2.getValue());
											System.out.println("This is now coupled problem111111111111:"+eq.getValue()+"--"+es2.getValue());
											
										}
										}
										}
					
						
						
						}
					else {
						QueryTask.wfilter=1;
						fr.getKey().replace(eq1.getKey(), "No");
					}
				
			//	HashMap<String,String> b = new HashMap<>();

				for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>  es:HashJoin.NotJoinedTriplesLarge.entrySet())
				{System.out.println("This is now coupled problem000000000:"+eq.getValue()+"--"+eq1.getKey());

				

					for(Entry<List<EdgeOperator>, String> es2:es.getKey().entrySet())
						{
						System.out.println("This is now coupled problem:"+eq.getValue()+"--"+es2.getValue());
						if(es2.getValue().contains(eq.getValue()))
						{ 
							HashJoin.DisabledTriplesLarge.add(es2.getValue());
							System.out.println("This is now coupled problem111111111111:"+eq.getValue()+"--"+es2.getValue());
							
						}
						}
						}
				

				HashMap<List<EdgeOperator>, String> lmkey=new HashMap<>();
				if(!HashJoin.NotJoinedTriplesLarge.isEmpty() ||HashJoin.NotJoinedTriplesLarge.size()>0)	{
				for(String xy1: HashJoin.DisabledTriplesLarge)
				{
					if(!HashJoin.NotJoinedTriplesLarge.isEmpty() ||HashJoin.NotJoinedTriplesLarge!=null)
					{
				//		Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> njt = HashJoin.NotJoinedTriplesLarge.entrySet().iterator();
						for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>  ntl11:HashJoin.NotJoinedTriplesLarge.entrySet())
				{
					if(ntl11.getKey().toString().contains(xy1))
					{
						lmkey.putAll(ntl11.getKey());
						lm=1;
					}
					
				}
					}
					if(!HashJoin.JoinedTriplesLarge.isEmpty() ||HashJoin.JoinedTriplesLarge!=null)
					{		
					for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>  ntl11:HashJoin.JoinedTriplesLarge.entrySet())
				{
					if(ntl11.getKey().toString().contains(xy1)) {
						lmkey.putAll(ntl11.getKey());
						lm=1;
					}
						
				}
				}
				}
				
				
				if(lm==1) {
					HashJoin.NotJoinedTriplesLarge.remove(lmkey);
					HashJoin.JoinedTriplesLarge.remove(lmkey);
					
				lmkey.clear();
				}
			}
			else
				{
			
					HashJoin.DisabledTriplesLarge.add(eq.getValue());
					}
			
				if(lm==1)
					break;
				}
			if(lm==1) {
				lm=0;
					continue;
				}
					System.out.println("This is a good thing finally2323232");
					
					System.out.println("This is a good thing finally2323232");
				}	
			
			if(QueryTask.falsified==1 && QueryTask.bounded==1)
			if (results.size() > 0 && ParaEng.FiltereqLarge!=null) {
				
				 List<EdgeOperator> BushyTreeTriple1 = BoundQueryTask.BushyTreeTripleCreation(te.getTriple());
					Query query1 = BindJoin.buildQuery(te.getTriple(),BushyTreeTriple1);
				//	 System.out.println("This is a good thing in BindJoin"+query1);
						
					List<String> Vertex1=new ArrayList<>();
					 for(String btt:query1.toString().split(" "))
						 
					 { if(btt.startsWith("?"))
						 Vertex1.add(btt.replace("\n", ""));
					 }
					//	System.out.println("This is a good thing finally000000000:"+Vertex1);
						String ne=BindJoin.NodeExtraction(Vertex1);
							
						
						System.out.println("This is a good thing finally5656565656 in 5555:"+ne);
					//	if(results.size()>0)
					//	if(results.size()>0)
					//		{break;
					//		
					//		}
						if(ne!=null)
				{
							for( Entry<HashMap<String, String>, String> feq:ParaEng.FiltereqLarge.entrySet())
								for(Entry<String,String> feq1:feq.getKey().entrySet())
							{
									
									 System.out.println("This is a good thing finally5656565656:"+feq1.getKey());
										
							System.out.println("This is the nodeextraction:"+ne+"--"+feq1.getKey());
							
					if(feq1.getValue()==null && feq1.getKey().contains(ne) && feq1.getKey().equals(eq1.getKey())) {		
					System.out.println("This is the biggest problem now in HashJoin:"+feq);
					System.out.println("This is the biggest problem now11111 in HashJoin:"+feq1);
					
				//	HashMap<String,String> b = new HashMap<>();
					QueryTask.wfilter=1;
					feq.getKey().replace(feq1.getKey(), "Yes");
					System.out.println("This is a good thing finally2323232");

				//	br=1;
				//	break;
			//		b.put(feq1.getKey(), "No");
			//		ParaEng.Filtereq.put(b, feq.getValue());
				
					}}
				}
			}
			QueryTask.falsified=0;
		
			System.out.println("This is successful now:");
			for(Entry<List<EdgeOperator>, List<Binding>>  jt:JoinedTriples.entrySet())
				System.out.println("This is the joinedtriples within the last:"+jt);
				for(Entry<HashMap<String, String>, String> as:ParaEng.FiltereqLarge.entrySet())
		System.out.println("This is the replacement of filterreq in HashJoin:"+as);

	//		for(Binding r:results)
	//			System.out.println("This si the problem here:"+r);
		//	if(BGPEval.Edgetype=="Norm") {
/*			for(List<EdgeOperator> e:BGPEval.JoinGroupsListExclusive) {
				for(EdgeOperator e1:e)
					if(e1.getEdge().equals(edge)) {
						AllEdges.addAll(e);
					}
			}

			for(EdgeOperator e:BGPEval.JoinGroupsListLeft) {
			
					if(e.getEdge().equals(edge)) {
					AllEdges.add(e);
					}
			}
			
					for(EdgeOperator e1:BGPEval.JoinGroupsListRight) {
						
						if(e1.getEdge().equals(edge)) {
							AllEdges.add(e1);
						}
					}

//			}else {
					for(List<EdgeOperator> e:BGPEval.JoinGroupsListOptional) {
						for(EdgeOperator e1:e)
							if(e1.getEdge().equals(edge)) {
								AllEdges.addAll(e);
							}
					}

					for(EdgeOperator e:BGPEval.JoinGroupsListLeftOptional) {
					
							if(e.getEdge().equals(edge)) {
								AllEdges.add(e);
							}
					}
					
							for(EdgeOperator e1:BGPEval.JoinGroupsListRightOptional) {
								
								if(e1.getEdge().equals(edge)) {
									AllEdges.add(e1);
								}
							}
	//		}
			*/
			

		//log.info("This is out of TripleExectuion in HashJoin Initil:"+results.size());
			
//synchronized(BGPEval.finalResult) {

				for(EdgeOperator ae:AllEdges)
					System.out.println("This is the final final final final:"+ae);
						//System.out.println("this is after hashjoin:"+te.getTriple()+"--"+results.size());
				if (edge.getV1().isBound() && edge.getV2().isBound()) {
					//System.out.println("This is out of TripleExectuion in HashJoin2:");

					start = edge.getV1();
					end = edge.getV2();
				//	results = QueryUtil.join(results, start.getBindings());
				//	results = QueryUtil.join(results, end.getBindings());
					//results = QueryUtil.join(results, input);
					// //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!This is here in
					// HashJoin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
					// "+start.getBindings()+"--"+end.getBindings());

					if (Config.debug) {
						
						//System.out.println(edge + ": " + results.size());

					}
					//if (Config.relative_error) {
						//System.out.println("This is out of TripleExectuion in HashJoin4:");

//						RelativeError.real_resultSize.put(end, (double) results.size());
//						RelativeError.real_resultSize.put(start, (double) results.size());
						// RelativeError.addJoinCard(end,(double)results.size(),edge.getTriple(),tripleCard);
						// RelativeError.addJoinCard(start,(double)results.size(),edge.getTriple(),tripleCard);

//					}
					if (results.size() == 0) {
						//System.out.println("This is out of TripleExectuion in HashJoin5:");

						finished = true;
						// //System.out.println("SE shutdown");
		//				BGPEval.getCurrentExeService().shutdownNow();
					}
					if (start.getNode().isConcrete()) {
						//System.out.println("This is out of TripleExectuion in HashJoin6:");

				//		end.setBindings(results);
						synchronized (end) {
							end.notifyAll();
						}
					} else {
						//System.out.println("This is out of TripleExectuion in HashJoin7:");

			//			start.setBindings(results);
						synchronized (start) {
							start.notifyAll();
						}
					}
					start.removeEdge(edge);
					end.removeEdge(edge);
					return;
				}
			
				
				String fq;
				if(edge.getV1().toString().contains("http:"))
				fq=edge.getV1().getNode().getURI().toString();
				else
					fq=edge.getV1().getNode().getName().toString();
				
					if(results.toString().contains(fq))
					{	//System.out.println("Theser are the vars of result in hashJoin edge.getV1:"+edge.getV1().getNode().getName().toString());
					start = edge.getV1();
					end = edge.getV2();
			
					}
					else
					{	//System.out.println("Theser are the vars of result in hashJoin edge.getV2:"+edge.getV1());
					start = edge.getV2();
					end = edge.getV1();
			
					}
					
		//	System.out.println("This is the edge:"+edge);		
			//if(results.size()==0)
			//{
			//	System.out.println("This is the size of result if 0");
			//	break;
			//}
			//else {
				JoinGroupsListLeftTemp= new ArrayList<>();
				JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeft);
				JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRight);
				JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListExclusive);
				JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListOptional);
				JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeftOptional);
				JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRightOptional);
				JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListMinus);
				JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeftMinus);
				JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRightMinus);

//					synchronized(BindJoin.StartBinding123) {
//					ForkJoinPool fjp = new ForkJoinPool();
				//for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> e:BGPEval.StartBindingSetBJ.entrySet())
									//BGPEval.HashJoinCompletion++;

				//BindJoin.StartBinding123.notifyAll();
//						}

				int xz=0;
//					for(Entry<Triple, Integer> ab:QueryTask.CompletionValue.entrySet())
//					if()

//					while(QueryTask.CompletionValue.containsKey(te.getTriple())&& (QueryTask.CompletionValue.values().contains(0)))
//					{	
//						if(results.size()!=0)
//						{	
//							break;
//						}
					//else
//					}

//					else break;		
//					QueryTask.CompletionValue
//					if(	BGPEval.ExclusivelyExclusive==1)
				/*if(QueryTask.CompletionValue.containsKey(te.getTriple())&& (QueryTask.CompletionValue.values().contains(0)))
				{	if(results.size()!=0)
					{	
//						break;
					}
					//else
					//	////System.out.printlnln("");
				}*/
					//	log.debug("111111111111111This is out of TripleExectuion");
//					log.debug("");
//					log.debug("1232131231231231232112321312312312321312323213123123213");
				ForkJoinPool fjp = new ForkJoinPool();
				try {
//						for(EdgeOperator ae:HashJoin.AllEdges)
					System.out.println("These are alledges:"+results.size());
					fjp.submit(()->IntermediateProcedureLarge(results,AllEdges,edge,eq1.getKey())).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fjp.shutdown();

				System.out.println("This is after IntermediateProcedureLarge");

			
				Edge	CurrentEdgeOperator=  new Edge(edge.getV1(),edge.getV2());//edge.getV1()+"--"+edge.getV2();
		
	 Iterator<Entry<EdgeOperator, List<Binding>>> frIterator = BGPEval.finalResult.entrySet().iterator();
	if(results.size()>0) {	
	 while(frIterator.hasNext()) {
			total++;
			Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
		
			if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
			{
	//			System.out.println("This is finalResult:"+ab.getKey()+"--"+edge+"--"+results.size());
				BGPEval.finalResult.replace(ab.getKey(), results); 
	//			CurrentEdgeOperator=ab.getKey();
			}
				//	}
	 
	 //for(Entry<EdgeOperator, ArrayList<Binding>> ab:BGPEval.finalResult.entrySet())
	
}
//synchronized(BGPEval.finalResultOptional) {
		for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultRightOptional.entrySet())
		{	total++;	
			if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
			{	BGPEval.finalResultRightOptional.replace(ab.getKey(), results);
			}
		
			//	CurrentEdgeOperator=ab.getKey();
			
			}
		for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultLeftOptional.entrySet())
		{total++;
			if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
			{	BGPEval.finalResultLeftOptional.replace(ab.getKey(), results);
		//	CurrentEdgeOperator=ab.getKey();
			
			}
		}
for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultOptional.entrySet())
{	total++;
	if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
			{	BGPEval.finalResultOptional.replace(ab.getKey(), results);
		//	CurrentEdgeOperator=ab.getKey();
			
			}
}

for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultRightMinus.entrySet())
{	total++;	
	if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
	{	BGPEval.finalResultRightMinus.replace(ab.getKey(), results);
	}

	//	CurrentEdgeOperator=ab.getKey();
	
	}
for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultLeftMinus.entrySet())
{total++;
	if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
	{	BGPEval.finalResultLeftMinus.replace(ab.getKey(), results);
//	CurrentEdgeOperator=ab.getKey();
	
	}
}
for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultMinus.entrySet())
{	total++;
if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
	{	BGPEval.finalResultMinus.replace(ab.getKey(), results);
//	CurrentEdgeOperator=ab.getKey();
	
	}
}
//}}

//synchronized(BGPEval.finalResultRight) {

//for(Entry<EdgeOperator, List<Binding>> lkd:BGPEval.finalResult.entrySet()) {
//	System.out.println("This is finalResult:"+lkd);
//}

//for(Entry<HashSet<List<EdgeOperator>>, Integer> lkd:BGPEval.linkingTreeDup.entrySet()) {
//	System.out.println("This is linkingTreeDup:"+lkd);
//}

//	System.out.println("This is CurrentEdgeOperator:"+CurrentEdgeOperator);




for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultRight.entrySet())
{total++;

if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
				{
//	System.out.println("This is finalResultRight:"+ab.getKey()+"--"+edge+"--"+results.size());
//	for(Entry<EdgeOperator, List<Binding>> fr:BGPEval.finalResult.entrySet())
//		System.out.println("This is finalResult:"+fr.getKey()+"--"+edge+"--"+fr.getValue().size());

	BGPEval.finalResultRight.replace(ab.getKey(), results);
		//		CurrentEdgeOperator=ab.getKey();
				
				}
}
for(Entry<EdgeOperator, List<Binding>> ab:BGPEval.finalResultLeft.entrySet())
{
	total++;

	if((ab.getKey().getEdge().getV1()+"--"+ab.getKey().getEdge().getV2()).toString().equals((edge.getV1()+"--"+edge.getV2()).toString()))
		{BGPEval.finalResultLeft.replace(ab.getKey(), results);
//		CurrentEdgeOperator=ab.getKey();
		
		}
}

	 

	 //}	

//synchronized(BGPEval.finalResultLeft) {
//for(Entry<EdgeOperator, List<Binding>> fr:BGPEval.finalResult.entrySet())
//System.out.println("This is fr:"+fr.getKey()+"--"+fr.getValue().size());

//if(BGPEval.finalResultRight.size()>0 ||BGPEval.finalResultRight!=null)
//for(Entry<EdgeOperator, List<Binding>> frr:BGPEval.finalResultRight.entrySet())
//System.out.println("This is frr:"+frr.getKey()+"--"+frr.getValue().size());


//for (Entry<Vertex, Set<Binding>> e : BGPEval.StartBinding123.entrySet()) {
//	 System.out.println("These are intermediate333333:"+e.getKey()+"--"+e.getValue());
//	}

for(List<EdgeOperator>  e2:JoinGroupsListLeftTemp) {
			
				for(EdgeOperator e3:e2)
					if(e3.getEdge().equals(CurrentEdgeOperator))
				{
					ProcessedEdgeOperatorsLarge.put(e2,eq.getValue());
				}
			}

for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt:NotJoinedTriplesLarge.entrySet())
		System.out.println("This is joinedTriple at beginning before final algo:"+jt.getKey()+"--"+jt.getValue().size());

System.out.println("These are the results respectively:"+results.size()+"--"+eq.getValue());
for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt:ProcessedTriplesLarge.entrySet())
	System.out.println("This is joinedTriple at beginning before final algo797979797979:"+jt.getKey()+"--"+jt.getValue().size());

System.out.println("This is now the new tree right:"+eq.getValue()+"--"+ProcessedEdgeOperatorsLarge+"--"+CurrentEdgeOperator);
			int count=0;
			frIterator = BGPEval.finalResult.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(Entry<List<EdgeOperator>, String> ee:ProcessedEdgeOperatorsLarge.entrySet()) {
					System.out.println("Problematic version:"+ee.getValue()+"--"+eq.getValue()+"--"+QueryTask.bounded);
					for(EdgeOperator ee1:ee.getKey()) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) && eq.getValue().equals(ee.getValue()))
					{		System.out.println("THis is coming to final algo:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2:"+ee);
							HashMap<List<EdgeOperator>, String> a = new HashMap<>();
							a.put(CompleteEdgeOperator(ab.getKey()), eq.getValue());
							System.out.println("This is now going inside it:"+a+"--"+ProcessedTriplesLarge.keySet());
							//if(!ProcessedTriplesLarge.containsKey(a))
							ProcessedTriplesLarge.put(a,ab.getValue());
							
					count++;
					}
						}
								}
					}
			for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt:NotJoinedTriplesLarge.entrySet())
				System.out.println("This is joinedTriple at beginning after final algo:"+jt.getKey()+"--"+jt.getValue().size());

			for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> etl:ProcessedTriplesLarge.entrySet())
			System.out.println("This is now the new tree right23223232:"+etl.getKey()+"--"+etl.getValue().size());

			////System.out.printlnln("This is now the new tree right count:"+count);
			count=0;
			frIterator = BGPEval.finalResultLeft.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(Entry<List<EdgeOperator>, String> ee:ProcessedEdgeOperatorsLarge.entrySet())
					for(EdgeOperator ee1:ee.getKey()) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo Rights:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Right:"+ee);
				//count++;
							List<EdgeOperator> l = new ArrayList<>();
				l.add(ab.getKey());
				HashMap<List<EdgeOperator>, String> a = new HashMap<>();
				a.put(l, eq.getValue());
							ProcessedTriplesLarge.put(a,ab.getValue());
				
					}
								}
					}
			//////System.out.printlnln("This is now the new tree right count:"+count);
			frIterator = BGPEval.finalResultRight.entrySet().iterator();
			while(frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for(Entry<List<EdgeOperator>, String> ee:ProcessedEdgeOperatorsLarge.entrySet())
					for(EdgeOperator ee1:ee.getKey()) {
						if(ab.getValue()!=null)
						if(ee1.equals(ab.getKey()) )
					{		//////System.out.printlnln("THis is coming to final algo Rights:"+ab.getKey()+"--"+ab.getValue().size());
				//////System.out.printlnln("THis is coming to final algo2 Right:"+ee);
				//count++;
							List<EdgeOperator> l = new ArrayList<>();
				l.add(ab.getKey());
				HashMap<List<EdgeOperator>, String> a = new HashMap<>();
				a.put(l, eq.getValue());
							ProcessedTriplesLarge.put(a,ab.getValue());
				
					}
								}
					}

			//count=0;

			for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> r:ProcessedTriplesLarge.entrySet())
				System.out.println("This is now the new processed triples before:"+r.getKey()+"--"+r.getValue().size());
	for(HashMap<List<EdgeOperator>, String> et:EvaluatedTriplesLarge)
		{ProcessedTriplesLarge.remove(et);
		}
	
	Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> ntjiterator1 = HashJoin.ProcessedTriplesLarge.entrySet().iterator();
	while(ntjiterator1.hasNext()) {
		Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> ntj = ntjiterator1.next();
	for(String dtl:HashJoin.DisabledTriplesLarge)
		if(ntj.getKey().toString().contains(dtl))
			ntjiterator1.remove();
	}
	
	for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> r:ProcessedTriplesLarge.entrySet())
		System.out.println("This is now the new processed triples:"+r.getKey()+"--"+r.getValue().size());
		
	Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> ntjiter;
		
		ntjiter=NotJoinedTriplesLarge.entrySet().iterator();
	while(ntjiter.hasNext())
		if(ntjiter.next().getValue().size()==0)
			ntjiter.remove();
	
	
	Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> ntjiter1;
	
	ntjiter1=JoinedTriplesLarge.entrySet().iterator();
while(ntjiter1.hasNext())
	if(ntjiter1.next().getValue().size()==0)
		ntjiter1.remove();

				ProcessingTaskLarge(JoinedTriplesLarge,ProcessedTriplesLarge,0,0,eq.getValue());
			ProcessingTaskLarge(NotJoinedTriplesLarge,ProcessedTriplesLarge,0,0,eq.getValue());
			

	//	}else
	//	{
		//  if(IsUnion==1)		{
				
		//  }
		
		
		
		
		
		for(HashMap<List<EdgeOperator>, String> et:EvaluatedTriplesLarge)
		{
			NotJoinedTriplesLarge.remove(et);
		}

		Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> ntjiterator11 = HashJoin.NotJoinedTriplesLarge.entrySet().iterator();
		while(ntjiterator11.hasNext()) {
			Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> ntj = ntjiterator11.next();
		for(String dtl:HashJoin.DisabledTriplesLarge)
			if(ntj.getKey().toString().contains(dtl))
				ntjiterator11.remove();
		}
		
		
		
//for(int i=0;i<6;i++){	
	

//	ForkJoinPool fjp1 = new ForkJoinPool();
//	fjp1.submit(()->{
		BGPEval.finalResultCalculationLarge(NotJoinedTriplesLarge,EvaluatedTriplesLarge,JoinedTriplesLarge,0,eq.getValue());//}).join();
//	fjp1.shutdown();
//	ForkJoinPool fjp2 = new ForkJoinPool();
	
//fjp2.submit(()->{
	BGPEval.finalResultCalculationLarge(JoinedTriplesLarge,EvaluatedTriplesLarge,JoinedTriplesLarge,1,eq.getValue());//}).join();
//	fjp2.shutdown();

//	ForkJoinPool fjp21 = new ForkJoinPool();
	
//fjp21.submit(()->{
	
	BGPEval.finalResultCalculationLarge(NotJoinedTriplesLarge,EvaluatedTriplesLarge,NotJoinedTriplesLarge,0,eq.getValue());//}).join();
//	fjp21.shutdown();
//}*/
	System.out.println("THis is NotJoinedTriples JoinedTriples:"+NotJoinedTriplesLarge.keySet()+"--"+JoinedTriplesLarge.keySet()+"--"+EvaluatedTriples);
if(!ParaEng.Union.isEmpty())		 
ProcessUnion();
//	}
	//	for(Entry<EdgeOperator, ArrayList<Binding>> fr: BGPEval.finalResult.entrySet())
	//		if(fr.getValue().size()>0)
//		log.info("This is out of TripleExectuion in HashJoin:"+results.size());
		TripleCard tripleCard = new TripleCard();
	/*	if (Config.relative_error) {
			log.info("This is out of TripleExectuion in HashJoin1:");
			
			tripleCard.real_Card = results.size();
			tripleCard.estimated_card = edge.estimatedCard();
		}*/

		// if both vertices are bound
			//results = QueryUtil.join(results, start.getBindingSets(),results, start.getBindingSets());
			//results = QueryUtil.join(results, end.getBindingSets(),results, end.getBindingSets());
			//results = QueryUtil.join(results, input,results, input);
			// log.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
			// HashJoin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:
			// "+start.getBindingSets()+"--"+end.getBindingSets());

		/*	if (Config.debug) {
				
				log.info(edge + ": " + results.size());

			}
			if (Config.relative_error) {
				log.info("This is out of TripleExectuion in HashJoin4:");

				RelativeError.real_resultSize.put(end, (double) results.size());
				RelativeError.real_resultSize.put(start, (double) results.size());
				// RelativeError.addJoinCard(end,(double)results.size(),edge.getTriple(),tripleCard);
				// RelativeError.addJoinCard(start,(double)results.size(),edge.getTriple(),tripleCard);

			}*/


	
			if(results.toString().contains(edge.getV1().getNode().toString()))
			{//	log.info("Theser are the vars of result in hashJoin edge.getV1:"+edge.getV1());
			start = edge.getV1();
			end = edge.getV2();
	
			}
			else
			{	//log.info("Theser are the vars of result in hashJoin edge.getV2:"+edge.getV1());
			start = edge.getV2();
			end = edge.getV1();
	
			}
		// only one vertex is bound
	/*	if (edge.getV1().isBound()) {
			log.info("This is out of TripleExectuion in HashJoin8:");

			start = edge.getV1();
			end = edge.getV2();
		} else {
			log.info("This is out of TripleExectuion in HashJoin9:");

			start = edge.getV1();
			end = edge.getV2();
		}*/
/*			synchronized(BindJoin.StartBinding123) {
//	for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> e:BGPEval.StartBindingSetBJ.entrySet())
//		log.info("This is end 23 set BindingSet in HashJoin:"+e.getKey()+"--"+edge+"--"+edge);
	Vertex start1 = new Vertex();
	start1=start;
//	////System.out.printlnln("This is here now before error"+results.size()+"--"+start.getBindingSets()+"--"+results1.size()+"--"+start1.getBindingSets());
//		results = QueryUtil.join(results, start.getBindingSets(),results1, start1.getBindingSets());
		for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> e:BGPEval.StartBindingSetBJ.entrySet())
		{	for(Entry<Set<Vertex>, Set<Edge>> f:e.getKey().entrySet())
			{
//			log.info("This is en  d 24 set BindingSet in HashJoin11111:"+BGPEval.StartBindingSetBJ);
			for(Vertex fCondition:f.getKey()) {
				if((fCondition.toString().equals(start.toString()))) {
//					if((fCondition.toString().equals(end)) ||(fCondition.toString().equals(edge.getV2().toString()))) {

					for(Edge f1:f.getValue())
				if(f1.toString().equals(edge.toString()))
			{
	//				log.info("This is end 24 set BindingSet in HashJoin1.1111.1.1111:"+f1+"--"+f );
					
			ArrayList<Binding> x = new ArrayList<Binding>();		
				x.addAll(results);
				ConcurrentHashMap<Set<Vertex>,Set<Edge>> b = new ConcurrentHashMap<>();
			b.put(f.getKey(),f.getValue());
		//	BGPEval.StartBindingSet.put(b,new ArrayList<Binding>(results));
				
			BindJoin.StartBinding123.put(b,x);
		//		log.info("This is end 24 set BindingSet in HashJoin0000:"+results.size());
skp=1;
				break;
		//				BGPEval.StartBindingSet.remove(e);
			//	BGPEval.StartBindingSet.put(e.getKey(),a);
			}
				if(skp==1)
					break;
				}
				if(skp==1)
				break;
			}
			if(skp==1)
				break;
			}
		if(skp==1) {
			skp=0;
			break;
		}
		}
 
		
	*/		
		//////System.out.printlnln("This is now the new tree:"+BGPEval.finalResult);
		//////System.out.printlnln("This is now the new tree left:"+BGPEval.finalResultLeft);
		//////System.out.printlnln("This is now the new tree right:"+BGPEval.finalResultRight);

				
	//		finalResultCalculation()
			
		//	log.info("This is end 23 set BindingSet in HashJoin:"+results );

//		BGPEval.StartBindingSet.put(BGPEval.b,BGPEval.a);
	//	log.info("This is end 24 set BindingSet in HashJoin0000:"+BGPEval.StartBindingSet);
		//log.info("This is end 25 set BindingSet in HashJoin:"+results );
		// log.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
		// HashJoin!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+start.getBindingSets());

		if(!ParaEng.Union.isEmpty()==false) {
		//	ArrayList<Binding> input1 = new ArrayList<Binding>();
		//	if(input!=null)
		//		input1.addAll(input);
			//Vertex start1 = new Vertex();
			//start1=start;
//////System.out.printlnln("This is great solution:");
//	ArrayList<Binding> results1 = new ArrayList<Binding>();
//if(results !=null)
//	for(BindingSet r:results1)
//results1.add(r);
		//		if(results!=null || input!=null)
			//results = QueryUtil.join(results, input);
		}
		
		

	//			if (results.size() == 0) {
	//		finished = true;
			// log.info("SE shutdown");
		//	BGPEval.getCurrentExeService().shutdownNow();
	//	}
		
		//log.info("This is end 24 set BindingSet in HashJoin0000000000111:"+ BGPEval.StartBindingSet);
	//	if(!ParaEng.Union.isEmpty()==false) 
	//	end.setBindings(results);
	//	sBindingSet = start.getBindingSets();
		//for(BindingSet r:results)
	//	log.info("This is end 25 set BindingSet in HashJoin:"+results.size());
		//	for(BindingSet r:results)
		synchronized (end) {
			end.notifyAll();
		}		
			//	log.info("End statement in HashJoin notified1:"+start.getBindingSets());
		//	log.info("End statement in HashJoin notified2:"+results);
		//	log.info("End statement in HashJoin notified3:"+end.getBindingSets());
			
		//	{	BindJoinWhenEnd=new ArrayList<BindingSet>(end.getBindingSets());
//
//			te.exec(BindJoinWhenEnd);
//			}
		//}
	//	log.info("This is end 26 set BindingSet in HashJoin:"+start.getBindingSets() );
	//	log.info("This is end 27 set BindingSet in HashJoin:"+end.getBindingSets() );
//	setInput(null);
	//	if(start.getBindingSets()==null)
	//	if(end.getBindingSets() != null) {
	//	BindJoinWhenEnd=new ArrayList<BindingSet>(end.getBindingSets());
	//	te.exec(BindJoinWhenEnd);
	//}
		start.removeEdge(edge);
		end.removeEdge(edge);
	
//		BGPEval.HashJoinCompletion++;
	//	if(BGPEval.HashJoinCompletion>3)
		//	BindJoin.StartBindingSet123.notifyAll();
		if(end1==1)
		{end1=0;	
		break;}

		//	}
		}
			}}
		}
		//BGPEval.HashJoinCompletion++;

		//BindJoin.StartBindingSet123.notifyAll();
		//}
//	}
   public List<EdgeOperator> CompleteEdgeOperator(EdgeOperator ct) {
	 //  Object jgl;
	   for(List<EdgeOperator>  jgl: BGPEval.JoinGroupsListExclusive) {
			if(jgl.contains(ct))
				return jgl;
	   }
	   for(List<EdgeOperator>  jgl: BGPEval.JoinGroupsListOptional) {
				if(jgl.contains(ct))
					return jgl;
		   }
	   for(List<EdgeOperator>  jgl: BGPEval.JoinGroupsListMinus) {
				if(jgl.contains(ct))
					return jgl;
		   }

	   
	   return null;
   }
	@Override
	public String toString() {
		return "Hash join: " + edge;
	}

public static void ProcessingTask(LinkedHashMap<List<EdgeOperator>, List<Binding>>  joinedTriples2,LinkedHashMap<List<EdgeOperator>, List<Binding>> processedTriplesUnion2,int IsUnionClause,int bothJoined) {
	
	for(Entry<List<EdgeOperator>, List<Binding>> jt:joinedTriples2.entrySet())
		System.out.println("This is joinedTriple at beginning:"+jt.getKey()+"--"+jt.getValue().size());
	for(Entry<List<EdgeOperator>, List<Binding>> jt:processedTriplesUnion2.entrySet())
		System.out.println("This is joinedTriple at beginning1111:"+jt.getKey()+"--"+jt.getValue().size());
	int index=0;
	ArrayList<Binding> First= new ArrayList<Binding>();
	ArrayList<Binding> Second= new ArrayList<Binding>();

	ArrayList<Binding> Union = new ArrayList<>();
	//JoinedTriples	
	List<EdgeOperator> ListEdges = new ArrayList<>();
	List<EdgeOperator> ListEdges2 = new ArrayList<>();
  int IsLiteral=0;
//  Set<String> Equity1 = new HashSet<>();
//	Set<String> Equity2 = new HashSet<>();
for(Entry<List<EdgeOperator>, List<Binding>> ew1: processedTriplesUnion2.entrySet()) {
	
if(processedTriplesUnion2.size()==2 && (joinedTriples2.isEmpty()||joinedTriples2.size()==0)) {

	
	System.out.println("This is here in part 1");
//	try {
//		Thread.sleep(500);
//	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	if(index==0) {
	//System.out.println("This is here in index value first:"+ew1.getValue());
	//	ForkJoinPool fjp = new ForkJoinPool();

		//fjp.submit(	()->{	
		First.addAll(ew1.getValue());
		ListEdges.addAll( ew1.getKey());
		ListEdges2.addAll( ew1.getKey());//}).join();
//		fjp.shutdown();
	//	////System.out.printlnln("This is here in index0:"+ew1.getKey());
		//(ew1.getKey());
	
	
	}
	if(index==1) {
	/*	
		
			  for(EdgeOperator le1:ListEdges)
			  {
				  Equity1.add(le1.te.getTriple().getSubject().toString());//////System.out.printlnln("This is ListEdges to work"+le1.te.getTriple().getSubject()+"--"+le1.te.getTriple().getObject());
				  Equity1.add(le1.te.getTriple().getObject().toString());
			  }
					for(EdgeOperator le1:ew1.getKey())
					{
						  Equity2.add(le1.te.getTriple().getSubject().toString());//////System.out.printlnln("This is ListEdges to work"+le1.te.getTriple().getSubject()+"--"+le1.te.getTriple().getObject());
						  Equity2.add(le1.te.getTriple().getObject().toString());

					}
					
//			////System.out.printlnln("This is ListEdges to work:::::::"+Equity1);
//			////System.out.printlnln("This is ListEdges to work1::::::"+Equity2);

			int isAvailable=0;
				
				for(String eq2:Equity2)
					if(Equity1.contains(eq2))
						isAvailable++;
				
	//			////System.out.printlnln("There are common nodes::::::::::::::::::::::::::::::::::::::"+isAvailable);
					//for(Node eq1:Equity1)
						if(isAvailable==0 && Equity2.size()>1 && Equity1.size()>1)
							return;
*/
		
		int isExecuted=0;
		for(EdgeOperator ew2:ew1.getKey()) {
			System.out.println("This is here in index1.3:"+ew2);
			
			//	if(ListEdges2.contains(ew2))
		//		break; 
			//	try {
			//		Thread.sleep(500);
				//} catch (InterruptedException e) {
			//		// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	}
				//////System.out.printlnln("This is here in index1:"+ew2);
	//		System.out.println("This is here in index1.2:"+ListEdges);

		
				
				if(ListEdges.toString().contains(ew2.getEdge().getV1().toString()) || ListEdges.toString().contains(ew2.getEdge().getV2().toString()))
		{
					
				//	////System.out.printlnln("This is here in index1.1:"+ew2);
					if(bothJoined==0)
				if(EvaluatedTriples.contains(ListEdges2) || EvaluatedTriples.contains(ew1.getKey()))
					break;
				if(ListEdges2.equals(ew1.getKey()))
					break;

			

		isExecuted=1;
//				try {
	//		Thread.sleep(500);
//		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
				
				////System.out.printlnln("This is pseudo-iteration First Main:"+First.size()+"--"+First.parallelStream().limit(3).collect(Collectors.toSet()));
				////System.out.printlnln("This is pseudo-iteration First Main:"+ListEdges);
			
				////System.out.printlnln("This is pseudo-iteration Secod Main:"+ew1.getValue().size()+"--" +ew1.getValue().parallelStream().limit(3).collect(Collectors.toSet()));
		////System.out.printlnln("This is pseudo-iteration Second Main:"+ew1.getKey());
   		
   		int Which=0;
   	//	ForkJoinPool fjp = new ForkJoinPool();
//fjp.submit(()->{ 
	Second.addAll(ew1.getValue());
		ListEdges.addAll(ew1.getKey());
//}).join();
//fjp.shutdown();
    
	 	if(ParaEng.Minus.contains("MINUS") )
     	{
     	for(List<EdgeOperator> jg:BGPEval.JoinGroupsListMinus)
			for(EdgeOperator as:ListEdges2)
				if(jg.contains(as))
			    Which=1;
   	for(EdgeOperator jg:BGPEval.JoinGroupsListRightMinus)
			for(EdgeOperator as:ListEdges2)
				if(jg.equals(as))
			    Which=1;
   	for(EdgeOperator jg:BGPEval.JoinGroupsListLeftMinus)
			for(EdgeOperator as:ListEdges2)
				if(jg.equals(as))
			    Which=1;

   	for(List<EdgeOperator> jg:BGPEval.JoinGroupsListMinus)
			for(EdgeOperator as:ListEdges)
				if(jg.contains(as))
			    Which=2;
   	for(EdgeOperator jg:BGPEval.JoinGroupsListRightMinus)
			for(EdgeOperator as:ListEdges)
				if(jg.equals(as))
			    Which=2;
   	for(EdgeOperator jg:BGPEval.JoinGroupsListLeftMinus)
			for(EdgeOperator as:ListEdges)
				if(jg.equals(as))
			    Which=2;

     
     	if(Which==2)	
{Union=	BGPEval.GPUJoin(First,Second,First,Second,1,null);
isExecuted=1;
}	else if (Which==1)
{  Union=	BGPEval.GPUJoin(Second,First,Second,First,1,null);
isExecuted=1;
}
else
{		 Union=	BGPEval.GPUJoin(Second,First,Second,First,0,null);
isExecuted=1;
}     	}
	 	
		if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) )
	     	{
	     	for(List<EdgeOperator> jg:BGPEval.JoinGroupsListOptional)
				for(EdgeOperator as:ListEdges2)
					if(jg.contains(as))
				    Which=1;
       	for(EdgeOperator jg:BGPEval.JoinGroupsListRightOptional)
				for(EdgeOperator as:ListEdges2)
					if(jg.equals(as))
				    Which=1;
       	for(EdgeOperator jg:BGPEval.JoinGroupsListLeftOptional)
				for(EdgeOperator as:ListEdges2)
					if(jg.equals(as))
				    Which=1;
  
       	for(List<EdgeOperator> jg:BGPEval.JoinGroupsListOptional)
				for(EdgeOperator as:ListEdges)
					if(jg.contains(as))
				    Which=2;
       	for(EdgeOperator jg:BGPEval.JoinGroupsListRightOptional)
				for(EdgeOperator as:ListEdges)
					if(jg.equals(as))
				    Which=2;
       	for(EdgeOperator jg:BGPEval.JoinGroupsListLeftOptional)
				for(EdgeOperator as:ListEdges)
					if(jg.equals(as))
				    Which=2;
  
	     
	     	if(Which==2)	
   {Union=	BGPEval.GPUJoin(First,Second,First,Second,1,null);
   isExecuted=1;
   }	else if (Which==1)
   {  Union=	BGPEval.GPUJoin(Second,First,Second,First,1,null);
   isExecuted=1;
   }
   else
   {		 Union=	BGPEval.GPUJoin(Second,First,Second,First,0,null);
   isExecuted=1;
   }     	}
	     	else {
	  //   		try {
		//			Thread.sleep(500);
		//		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	}
	     			 Union=	BGPEval.GPUJoin(First,Second,First,Second,0,null);
	     			
	     			 isExecuted=1;
	  				EvaluatedTriples.add(ListEdges2);
	  			   EvaluatedTriples.add(ew1.getKey());
	  			//   for(List<EdgeOperator> et:EvaluatedTriples)
	  		//	 InterJoinedTriples.put(et, Union);
	     	}

//		Union=	BGPEval.GPUJoin(First,Second);
//		   JoinedTriples.remove( jt.getKey());
    	if(Union==null)
     		return;
		   joinedTriples2.put(ListEdges,Union);
		   for(Entry<List<EdgeOperator>, List<Binding>> jt:JoinedTriples.entrySet())
			System.out.println("This is here in Union 0:"+jt.getKey()+"--"+jt.getValue().size());
	     	
			EvaluatedTriples.add(ListEdges2);
			   EvaluatedTriples.add(ew1.getKey());
		
		   isExecuted=1;
		   //	////System.out.printlnln("This is joinedTriples:"+JoinedTriples.values().parallelStream().limit(3).collect(Collectors.toSet()));
		//	////System.out.printlnln("This is joinedTriples evaluatedTriples:"+Union.parallelStream().limit(3).collect(Collectors.toSet()));
		  	
	}
		
	}
		if(isExecuted==0) {
			if(IsUnionClause==1)
				
				NotJoinedTriplesUnion.put(ListEdges,First);
			else
					NotJoinedTriples.put(ListEdges,First);
		}
		}
	index++;
		
}
else if(!joinedTriples2.isEmpty()||joinedTriples2.size()>0) {
//	System.out.println("This is here in part 2:"+bothJoined);
	System.out.println("THis is the problem in 2 jt");
	int out=0;
	int isProcessed=0;
	int e=0;
	//if(bothJoined==0) {
	for(List<EdgeOperator> et:EvaluatedTriples)
	if(ew1.getKey().equals(et))
	{e=1; break;}
	if(e==1)
		break;
	//}
	//System.out.println("This is size1:"+processedTriplesUnion2.size());
	for(Entry<List<EdgeOperator>, List<Binding>>  jt:joinedTriples2.entrySet())
	{
	//	if(bothJoined==0)
		//{	
			for(List<EdgeOperator> et:EvaluatedTriples)
			if(jt.getKey().equals(et))
			{e=1; break;}
			if(e==1)
				break;
		//}
			for(EdgeOperator ew1K:ew1.getKey()) {
  			//	for(List<EdgeOperator> et:EvaluatedTriples)
  		//		if(et.equals(ew1))
	
				
				
				if(jt.getKey().toString().contains(ew1K.getEdge().getV1().toString()) || jt.getKey().toString().contains(ew1K.getEdge().getV2().toString()))
			{
			isProcessed=1;
	//	System.out.println("THis is the problem in 2 jt"+jt.getKey().toString());
	//	System.out.println("THis is the problem in 2 ew1K"+ew1K.getEdge().getV1()+"--"+ew1K.getEdge().getV2());
		
		/*	  for(EdgeOperator le:ew1.getKey())
				{	
			  
				  Equity1.add(le.te.getTriple().getSubject().toString());//////System.out.printlnln("This is ListEdges to work"+le1.te.getTriple().getSubject()+"--"+le1.te.getTriple().getObject());
				  Equity1.add(le.te.getTriple().getObject().toString());
			 
				}
				for(EdgeOperator le:jt.getKey())
				{	
						  Equity2.add(le.te.getTriple().getSubject().toString());//////System.out.printlnln("This is ListEdges to work"+le1.te.getTriple().getSubject()+"--"+le1.te.getTriple().getObject());
						  Equity2.add(le.te.getTriple().getObject().toString());

				}
				int isAvailable=0;
				
				for(String eq2:Equity2)
					if(Equity1.contains(eq2))
						isAvailable++;
		//		////System.out.printlnln("There are common nodes 0000:"+Equity1);
		//		////System.out.printlnln("There are common nodes 1111:"+Equity2);
						
		//		////System.out.printlnln("There are common nodes Single::::::::::::::::::::::::::::::::::::::"+isAvailable);
					//for(Node eq1:Equity1)
						if(isAvailable==0 && Equity1.size()>1 && Equity2.size()>1)
							return;
*/
			//if(bothJoined==0)

			System.out.println("THis is the problem in 3 jt");
			
			if(ew1.getKey().toString().equals(jt.getKey().toString())) 
				break;
			System.out.println("THis is the problem in 4 jt");

			////System.out.printlnln("This is 1 pseudo-iteration:"+ew1.getKey()+"--"+ew1.getValue().parallelStream().limit(3).collect(Collectors.toSet()));
			First.addAll(ew1.getValue());
			////System.out.printlnln("This is 1 pseudo-iteration Second Main:"+jt.getKey()+"--"+jt.getValue().parallelStream().limit(3).collect(Collectors.toSet()));
			//for( com.hp.hpl.jena.sparql.engine.BindingSet.BindingSet jt2:jt1)
				//////System.out.printlnln("This is pseudo-iteration Second Sub:"+jt1.parallelStream().limit(3).collect(Collectors.toSet()));
   			int Which=0;
			
//ForkJoinPool fjp = new ForkJoinPool();

//fjp.submit(	()->{		
	Second.addAll(jt.getValue());
				ListEdges.addAll(ew1.getKey());
				ListEdges.addAll(jt.getKey());
//}).join();
//fjp.shutdown();

				
						if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()))
		     	{
		     	for(List<EdgeOperator> jg:BGPEval.JoinGroupsListOptional)
   				for(EdgeOperator as:ew1.getKey())
   					if(jg.contains(as))
   				    Which=1;
		     	for(EdgeOperator jg:BGPEval.JoinGroupsListRightOptional)
	   				for(EdgeOperator as:ew1.getKey())
	   					if(jg.equals(as))
	   				    Which=1;

		     	for(EdgeOperator jg:BGPEval.JoinGroupsListLeftOptional)
	   				for(EdgeOperator as:ew1.getKey())
	   					if(jg.equals(as))
	   				    Which=1;

		     	//ew1.getKey().size();
	System.out.println("This is in procedure for join:"+Which+"--"+Second.size()+"--"+First.size());	     		
		     	if(Which==1)	
	   {Union=	BGPEval.GPUJoin(Second,First,Second,First,1,null);
	   isProcessed=1;
	   }     	
		     		 
			else
			{			  Union=	BGPEval.GPUJoin(First,Second,First,Second,0,null);
			isProcessed=1;
			}
		     	}
		     	else if(ParaEng.Minus.contains("MINUS"))
		     	{
		     	for(List<EdgeOperator> jg:BGPEval.JoinGroupsListMinus)
   				for(EdgeOperator as:ew1.getKey())
   					if(jg.contains(as))
   				    Which=1;
		     	for(EdgeOperator jg:BGPEval.JoinGroupsListRightMinus)
	   				for(EdgeOperator as:ew1.getKey())
	   					if(jg.equals(as))
	   				    Which=1;

		     	for(EdgeOperator jg:BGPEval.JoinGroupsListLeftMinus)
	   				for(EdgeOperator as:ew1.getKey())
	   					if(jg.equals(as))
	   				    Which=1;

		     	//ew1.getKey().size();
		     		
		     	if(Which==1)	
	   {Union=	BGPEval.GPUJoin(Second,First,Second,First,1,null);
	   isProcessed=1;
	   }     	
		     		 
			else
			{			  
			Union=	BGPEval.GPUJoin(First,Second,First,Second,0,null);
			isProcessed=1;
			}
		     	}
		     	else {
		     		int c=0;
		     	for(EdgeOperator ew2: jt.getKey())
		     		if(ew2.getEdge().toString().contains("http"))
		     	c++;
		     	
		     	if(c==jt.getKey().size())
		     	{		     		 Union=	BGPEval.GPUJoin(Second,First,Second,First,4,null);
		     	////System.out.printlnln("THis is number 4:"+Union.size()+"--"+Union.parallelStream().limit(4).collect(Collectors.toSet()));
		     	isProcessed=1;
		     	}
		     	else
		     	{
		     //		System.out.println("This is first value:"+First.parallelStream().limit(2).collect(Collectors.toList()));
		    //		System.out.println("This is second value:"+Second.parallelStream().limit(2).collect(Collectors.toList()));
		  //  	Iterator<Entry<List<EdgeOperator>, List<Binding>>> x = InterJoinedTriples.entrySet().iterator();
		    //	while(x.hasNext())
			    	 System.out.println("This is the final set of problem here1111:");
		     		Union=	BGPEval.GPUJoin(First,Second,First,Second,0,null);
		     	isProcessed=1;
		     	}}
		     	if(Union==null)
		     		return;
		     	
		     	joinedTriples2.remove( ew1.getKey());
		 	joinedTriples2.remove( jt.getKey());
/*
			if(BindJoin.inner==1)
			{
			for(EdgeOperator le:ListEdges) {
				
			   	 System.out.println("This is the final set1:"+le.getEdge().getV2().getNode().toString());
					
				if(le.getEdge().getV1().getNode().toString().equals(BindJoin.biff))
					le.getEdge().getV1().setNode(StageGen.StringConversion(BindJoin.ciff.substring(1)));
				
else if(le.getEdge().getV2().getNode().toString().equals(BindJoin.biff))
   le.getEdge().getV2().setNode(StageGen.StringConversion(BindJoin.ciff.substring(1)));
else if(le.getEdge().getV1().getNode().toString().equals(BindJoin.ciff))
le.getEdge().getV1().setNode(StageGen.StringConversion(BindJoin.biff.substring(1)));

else if(le.getEdge().getV2().getNode().toString().equals(BindJoin.ciff))
le.getEdge().getV2().setNode(StageGen.StringConversion(BindJoin.biff.substring(1)));

			
			}
			}
*/
		 	joinedTriples2.put(ListEdges,Union);
		for(Entry<List<EdgeOperator>,List<Binding>> jt21:joinedTriples2.entrySet())
		    	 System.out.println("This is the final set of problem here1111:"+jt21.getKey()+"--"+jt21.getValue().parallelStream().limit(2).collect(Collectors.toList()));
			
		   isProcessed=1;
		   if(bothJoined==1) {
				JoinedTriples.remove( ew1.getKey());
				JoinedTriples.remove( jt.getKey());
/*
				if(BindJoin.inner==1)
				{
				for(EdgeOperator le:ListEdges) {
					if(le.getEdge().getV1().getNode().toString().equals(BindJoin.biff))
						le.getEdge().getV1().setNode(StageGen.StringConversion(BindJoin.ciff.substring(1)));
					
	else if(le.getEdge().getV2().getNode().toString().equals(BindJoin.biff))
	   le.getEdge().getV2().setNode(StageGen.StringConversion(BindJoin.ciff.substring(1)));
	else if(le.getEdge().getV1().getNode().toString().equals(BindJoin.ciff))
	le.getEdge().getV1().setNode(StageGen.StringConversion(BindJoin.biff.substring(1)));

	else if(le.getEdge().getV2().getNode().toString().equals(BindJoin.ciff))
	le.getEdge().getV2().setNode(StageGen.StringConversion(BindJoin.biff.substring(1)));

				
				}
				}*/

				JoinedTriples.put(ListEdges,Union);
		   }
		   EvaluatedTriples.add(ew1.getKey());
			EvaluatedTriples.add(jt.getKey());
	//		 			 InterJoinedTriples.put(ListEdges, Union);
		//   ////System.out.printlnln("This is the result of joinTriple:"+Union.parallelStream().limit(3).collect(Collectors.toSet()));

out=1;		
		   break;
			}		
if(out==1) 
	break;

  			}
  			if(out==1) {
  				out=0;
  				break;
  			}
		
	}
//	System.out.println("This is size1=====1:"+processedTriplesUnion2.size());

	if(isProcessed==0)
		if(IsUnionClause==1)
			NotJoinedTriplesUnion.putAll(processedTriplesUnion2);
		else
		NotJoinedTriples.putAll(processedTriplesUnion2);
}
else {
	if(IsUnionClause==1)
	NotJoinedTriplesUnion.putAll(processedTriplesUnion2);
	else
	NotJoinedTriples.putAll(processedTriplesUnion2);}
}


}
public static void ProcessingTaskLarge(LinkedHashMap<HashMap<List<EdgeOperator>, String>, List<Binding>>  joinedTriplesLarge2,LinkedHashMap<HashMap<List<EdgeOperator>, String>, List<Binding>> processedTriplesLarge2,int IsUnionClause,int bothJoined,String string) {
	
	//for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt:joinedTriplesLarge2.entrySet())
	//	System.out.println("This is joinedTriple at beginning444444444:"+jt.getKey()+"--"+jt.getValue().size());
	LinkedHashMap<HashMap<List<EdgeOperator>, String>, List<Binding>> processor = new LinkedHashMap<>();
	LinkedHashMap<HashMap<List<EdgeOperator>, String>, List<Binding>> abc = new LinkedHashMap<>();
	
	for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt:processedTriplesLarge2.entrySet())
		{
		for(Entry<List<EdgeOperator>, String> jt1:jt.getKey().entrySet()) {
			if(processor.isEmpty() || processor.size()==0 || processor==null)
			processor.put(jt.getKey(),jt.getValue());
			else
				for(Entry<HashMap<List<EdgeOperator>,String>,List<Binding>> pr:processor.entrySet())
					for(Entry<List<EdgeOperator>,String>  prk:pr.getKey().entrySet())
							if(prk.getValue().equals(jt1.getValue()))
								abc.put(jt.getKey(),jt.getValue());
							
		}
		
	
		}
//	for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt:abc.entrySet())
//		System.out.println("This is joinedTriple at beginning4444444442222222:"+jt.getKey()+"--"+jt.getValue().size());
	
//	for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt:processedTriplesLarge2.entrySet())
//		System.out.println("This is joinedTriple at beginning444444444111111:"+jt.getKey()+"--"+jt.getValue().size());
	
	HashMap<List<EdgeOperator>, String> xyz=new HashMap<>();
	for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> prs:processor.entrySet())
		if(prs.getValue().size()==0)
		{
			xyz.putAll(prs.getKey());
		}
			//System.out.println("This is joinedTriple at beginning 0101010101:"+prs.getKey()+"--"+prs.getValue().size());
	
	
	int index=0;
	ArrayList<Binding> First= new ArrayList<Binding>();
	ArrayList<Binding> Second= new ArrayList<Binding>();

	ArrayList<Binding> Union = new ArrayList<>();
	//JoinedTriples	
	HashMap<List<EdgeOperator>,String> ListEdges = new HashMap<>();
	HashMap<List<EdgeOperator>,String> ListEdges2 = new HashMap<>();
 //  Set<String> Equity1 = new HashSet<>();
//	Set<String> Equity2 = new HashSet<>();
for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> ew1: processor.entrySet()) {
	
	if(processedTriplesLarge2.size()==2 && (joinedTriplesLarge2.isEmpty()||joinedTriplesLarge2.size()==0)) {
System.out.println("This is here in part 1");
//	try {
//		Thread.sleep(500);
//	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	if(index==0) {
	//System.out.println("This is here in index value first:"+ew1.getValue());
	//	ForkJoinPool fjp = new ForkJoinPool();

		//fjp.submit(	()->{	
		First.addAll(ew1.getValue());
		ListEdges.putAll( ew1.getKey());
		ListEdges2.putAll( ew1.getKey());//}).join();
//		fjp.shutdown();
	//	////System.out.printlnln("This is here in index0:"+ew1.getKey());
		//(ew1.getKey());
	
	}
	if(index==1) {
	/*	
		
			  for(EdgeOperator le1:ListEdges)
			  {
				  Equity1.add(le1.te.getTriple().getSubject().toString());//////System.out.printlnln("This is ListEdges to work"+le1.te.getTriple().getSubject()+"--"+le1.te.getTriple().getObject());
				  Equity1.add(le1.te.getTriple().getObject().toString());
			  }
					for(EdgeOperator le1:ew1.getKey())
					{
						  Equity2.add(le1.te.getTriple().getSubject().toString());//////System.out.printlnln("This is ListEdges to work"+le1.te.getTriple().getSubject()+"--"+le1.te.getTriple().getObject());
						  Equity2.add(le1.te.getTriple().getObject().toString());

					}
					
//			////System.out.printlnln("This is ListEdges to work:::::::"+Equity1);
//			////System.out.printlnln("This is ListEdges to work1::::::"+Equity2);

			int isAvailable=0;
				
				for(String eq2:Equity2)
					if(Equity1.contains(eq2))
						isAvailable++;
				
	//			////System.out.printlnln("There are common nodes::::::::::::::::::::::::::::::::::::::"+isAvailable);
					//for(Node eq1:Equity1)
						if(isAvailable==0 && Equity2.size()>1 && Equity1.size()>1)
							return;
*/
		for(Entry<List<EdgeOperator>, String> ew:ListEdges.entrySet())
			System.out.println("This is here in problem:"+ew);
		for(Entry<List<EdgeOperator>, String> ew2:ew1.getKey().entrySet()) {
			System.out.println("This is here in problem333333333333333:"+ew2);
					
		}
		int isExecuted=0;
		for(Entry<List<EdgeOperator>, String> ew2:ew1.getKey().entrySet()) {
			for(EdgeOperator ew3:ew2.getKey()) {
		//	if(ListEdges2.contains(ew2))
		//		break;
			//	try {
			//		Thread.sleep(500);
				//} catch (InterruptedException e) {
			//		// TODO Auto-generated catch block
				//	e.printStackTrace();
			//	}
				//////System.out.printlnln("This is here in index1:"+ew2);
				
				if((ListEdges.toString().contains(ew3.getEdge().getV1().toString()) || ListEdges.toString().contains(ew3.getEdge().getV1().toString())) && ListEdges.containsValue(ew2.getValue()))
		
				{
			System.out.println("This is here in index1.1:"+ListEdges+"--"+ew1.getValue().size());
					if(bothJoined==0)
				if(EvaluatedTriplesLarge.contains(ListEdges2) || EvaluatedTriplesLarge.contains(ew1.getKey()))
					break;
				if(ListEdges2.equals(ew1.getKey()))
					break;
			//	////System.out.printlnln("This is here in index1.2:"+ew2);
				
				
			//////System.out.printlnln("This is here in index1.3:"+ew2);
			

		isExecuted=1;
//				try {
	//		Thread.sleep(500);
//		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
				
				////System.out.printlnln("This is pseudo-iteration First Main:"+First.size()+"--"+First.parallelStream().limit(3).collect(Collectors.toSet()));
				////System.out.printlnln("This is pseudo-iteration First Main:"+ListEdges);
			
				////System.out.printlnln("This is pseudo-iteration Secod Main:"+ew1.getValue().size()+"--" +ew1.getValue().parallelStream().limit(3).collect(Collectors.toSet()));
		////System.out.printlnln("This is pseudo-iteration Second Main:"+ew1.getKey());
   		
   		
   	//	ForkJoinPool fjp = new ForkJoinPool();
//fjp.submit(()->{ 
	Second.addAll(ew1.getValue());
		ListEdges.putAll(ew1.getKey());
//}).join();
//fjp.shutdown();

	  //   		try {
		//			Thread.sleep(500);
		//		} catch (InterruptedException e) {
					// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	}
	     			 Union=	BGPEval.GPUJoin(First,Second,First,Second,0,string);
	     			System.out.println("This is here in Union 0:"+Union.parallelStream().limit(3).collect(Collectors.toSet()));
		     		
	     			 isExecuted=1;
	  				EvaluatedTriplesLarge.add(ListEdges2);
	  			   EvaluatedTriplesLarge.add(ew1.getKey());
	  			//   for(List<EdgeOperator> et:EvaluatedTriples)
	  		//	 InterJoinedTriples.put(et, Union);
	     	//}

//		Union=	BGPEval.GPUJoin(First,Second);
//		   JoinedTriples.remove( jt.getKey());
    	
	  			 JoinedTriplesLarge.put(ListEdges,Union);
			EvaluatedTriplesLarge.add(ListEdges2);
			   EvaluatedTriplesLarge.add(ew1.getKey());
		
		   isExecuted=1;
		   for(Entry<List<EdgeOperator>, List<Binding>> jt:JoinedTriples.entrySet())
		  System.out.println("This is joinedTriples:"+jt.getKey()+"--"+jt.getValue().size());
		//	////System.out.printlnln("This is joinedTriples evaluatedTriples:"+Union.parallelStream().limit(3).collect(Collectors.toSet()));
		  	
	}
		
		}}
		if(isExecuted==0) {
			
					NotJoinedTriplesLarge.put(ListEdges,First);
		}
		}
	index++;
		
}
else if(!joinedTriplesLarge2.isEmpty()||joinedTriplesLarge2.size()>0) {
//	System.out.println("This is here in part 2 in Large:"+bothJoined);

	int out=0;
	int isProcessed=0;
	int e=0;
	//if(bothJoined==0) {
	for(HashMap<List<EdgeOperator>, String> et:EvaluatedTriplesLarge)
	if(ew1.getKey().equals(et))
	{e=1; break;}
	if(e==1)
		break;
	//}
	//System.out.println("This is size1:"+processedTriplesUnion2.size());
	Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> jtIterator = joinedTriplesLarge2.entrySet().iterator();

		while(jtIterator.hasNext())
	{
		Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt = jtIterator.next();
		for(Entry<List<EdgeOperator>, String> jt1:jt.getKey().entrySet()) {
	//	if(bothJoined==0)
		//{	
		//	System.out.println("This is size1:"+jt1);

			
			
			for(HashMap<List<EdgeOperator>, String> et:EvaluatedTriplesLarge)
			{	
		//		System.out.println("This is size2:"+jt1);

				if(jt.getKey().equals(et))
			{e=1; break;}
			if(e==1)
				break;
			}
		//}

			

			for(Entry<List<EdgeOperator>, String> ew1K:ew1.getKey().entrySet()) {
				for(EdgeOperator ew2K:ew1K.getKey()) {
  			//	for(List<EdgeOperator> et:EvaluatedTriples)
  		//		if(et.equals(ew1))
		if(ew1K.getValue().equals(jt1.getValue()) &&(jt1.getKey().toString().contains(ew2K.getEdge().getV1().toString()) ||jt1.getKey().toString().contains(ew2K.getEdge().getV2().toString())))
			{
			isProcessed=1;
	//	System.out.println("THis is the problem in 2 jt"+jt.getKey().toString());
	//	System.out.println("THis is the problem in 2 ew1K"+ew1K.getEdge().getV1()+"--"+ew1K.getEdge().getV2());
		
		/*	  for(EdgeOperator le:ew1.getKey())
				{	
			  
				  Equity1.add(le.te.getTriple().getSubject().toString());//////System.out.printlnln("This is ListEdges to work"+le1.te.getTriple().getSubject()+"--"+le1.te.getTriple().getObject());
				  Equity1.add(le.te.getTriple().getObject().toString());
			 
				}
				for(EdgeOperator le:jt.getKey())
				{	
						  Equity2.add(le.te.getTriple().getSubject().toString());//////System.out.printlnln("This is ListEdges to work"+le1.te.getTriple().getSubject()+"--"+le1.te.getTriple().getObject());
						  Equity2.add(le.te.getTriple().getObject().toString());

				}
				int isAvailable=0;
				
				for(String eq2:Equity2)
					if(Equity1.contains(eq2))
						isAvailable++;
		//		////System.out.printlnln("There are common nodes 0000:"+Equity1);
		//		////System.out.printlnln("There are common nodes 1111:"+Equity2);
						
		//		////System.out.printlnln("There are common nodes Single::::::::::::::::::::::::::::::::::::::"+isAvailable);
					//for(Node eq1:Equity1)
						if(isAvailable==0 && Equity1.size()>1 && Equity2.size()>1)
							return;
*/
			//if(bothJoined==0)
			if(ew1.getKey().equals(jt.getKey())) 
				break;
						
			System.out.println("This is 1 pseudo-iteration:"+ew1.getKey()+"--"+ew1.getValue().parallelStream().limit(3).collect(Collectors.toSet()));
			First.addAll(ew1.getValue());
		System.out.println("This is 1 pseudo-iteration Second Main:"+jt.getKey()+"--"+jt.getValue().parallelStream().limit(3).collect(Collectors.toSet()));
			//for( com.hp.hpl.jena.sparql.engine.BindingSet.BindingSet jt2:jt1)
				//////System.out.printlnln("This is pseudo-iteration Second Sub:"+jt1.parallelStream().limit(3).collect(Collectors.toSet()));
   		
//ForkJoinPool fjp = new ForkJoinPool();

//fjp.submit(	()->{		
	Second.addAll(jt.getValue());
				ListEdges.putAll(ew1.getKey());
				ListEdges.putAll(jt.getKey());
//}).join();
//fjp.shutdown();

		     		int c=0;
		     	for(Entry<List<EdgeOperator>, String> ew2: jt.getKey().entrySet())
		     		if(ew2.getKey().toString().contains("http"))
		     	c++;
		     	
		     	if(c==jt.getKey().size())
		     	{		     		 Union=	BGPEval.GPUJoin(Second,First,Second,First,4,string);
		     	////System.out.printlnln("THis is number 4:"+Union.size()+"--"+Union.parallelStream().limit(4).collect(Collectors.toSet()));
		     	isProcessed=1;
		     	}
		     	else
		     	{
		     //		System.out.println("This is first value:"+First.parallelStream().limit(2).collect(Collectors.toList()));
		    //		System.out.println("This is second value:"+Second.parallelStream().limit(2).collect(Collectors.toList()));
		  //  	Iterator<Entry<List<EdgeOperator>, List<Binding>>> x = InterJoinedTriples.entrySet().iterator();
		    //	while(x.hasNext())
			    	 System.out.println("This is the final set of problem here1111:");
		     		Union=	BGPEval.GPUJoin(First,Second,First,Second,0,string);
		     	isProcessed=1;
		     	}

		     	joinedTriplesLarge2.remove( ew1.getKey());
			     	joinedTriplesLarge2.remove( jt.getKey());
		 	joinedTriplesLarge2.put(ListEdges,Union);
	//	for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jt21:joinedTriplesLarge2.entrySet())
	//	    	 System.out.println("This is the final set of problem here1111 Large:"+jt21.getKey()+"--"+jt21.getValue().parallelStream().limit(2).collect(Collectors.toList()));
			   for(Entry<List<EdgeOperator>, List<Binding>> jt11:JoinedTriples.entrySet())
					  System.out.println("This is joinedTriples in 1:"+jt11.getKey()+"--"+jt11.getValue().size());
			
		   isProcessed=1;
		   if(bothJoined==1) {
			   JoinedTriplesLarge.remove( ew1.getKey());
			   JoinedTriplesLarge.remove( jt.getKey());
			   JoinedTriplesLarge.put(ListEdges,Union);
		   }
		   EvaluatedTriplesLarge.add(ew1.getKey());
			EvaluatedTriplesLarge.add(jt.getKey());
	//		 			 InterJoinedTriples.put(ListEdges, Union);
		//   ////System.out.printlnln("This is the result of joinTriple:"+Union.parallelStream().limit(3).collect(Collectors.toSet()));

out=1;		
		   break;
			}		
if(out==1) 
	break;
			}
				if(out==1) 
					break;

			}
  			if(out==1) {
  				
  				break;
  			}
	}	
		if(out==1) {
				out=0;
				break;
			}
	}
//	System.out.println("This is size1=====1:"+processedTriplesUnion2.size());

	if(isProcessed==0)
		
		NotJoinedTriplesLarge.putAll(processedTriplesLarge2);
}
else {
	NotJoinedTriplesLarge.putAll(processedTriplesLarge2);}
}

}

public static void ProcessUnion() {
	
	System.out.println("------------------------This is processing Union---------------------------");
	for(List<EdgeOperator> et:EvaluatedTriples)
	{
		ProcessedTriplesUnion.remove(et);
	}
	
	for(List<EdgeOperator> e:EvaluatedTriples)
	System.out.println("These are evaulatedTriple:"+e);
	for(Entry<List<EdgeOperator>, List<Binding>> e:JoinedTriplesUnion.entrySet())
		System.out.println("These are JoinTripleUnion:"+e.getKey());
	for(Entry<List<EdgeOperator>, List<Binding>> e:NotJoinedTriplesUnion.entrySet())
		System.out.println("These are NotJoinTripleUnion:"+e.getKey());
	for(Entry<List<EdgeOperator>, List<Binding>> e:ProcessedTriplesUnion.entrySet())
		System.out.println("These are ProcessedTriplesUnion:"+e.getKey());
	
	ProcessingTask(JoinedTriplesUnion,ProcessedTriplesUnion,1,0);
		ProcessingTask(NotJoinedTriplesUnion,ProcessedTriplesUnion,1,0);

//	}else
//	{
	//  if(IsUnion==1)		{
			
	//  }
	
	
	
	
	
	for(List<EdgeOperator> et:EvaluatedTriples)
	{
		NotJoinedTriplesUnion.remove(et);
	}


//for(int i=0;i<7;i++){	
//ForkJoinPool fjp1 = new ForkJoinPool();
//fjp1.submit(()->{
	BGPEval.finalResultCalculation(NotJoinedTriplesUnion,EvaluatedTriples,JoinedTriplesUnion,0);//}).join();
//fjp1.shutdown();
//ForkJoinPool fjp2 = new ForkJoinPool();

//fjp2.submit(()->{
	BGPEval.finalResultCalculation(JoinedTriplesUnion,EvaluatedTriples,JoinedTriplesUnion,1);//}).join();
//fjp2.shutdown();

//ForkJoinPool fjp21 = new ForkJoinPool();

//fjp21.submit(()->{
	BGPEval.finalResultCalculation(NotJoinedTriplesUnion,EvaluatedTriples,NotJoinedTriplesUnion,0);//}).join();
//fjp21.shutdown();
//}


}


static void IntermediateProcedureLarge(List<Binding> results,List<EdgeOperator> AllEdges, Edge edge,String string) {
	Set<Binding>	temp1 = new HashSet<>();
	Set<Node> StartBinding = new HashSet<>();	
	 HashMap<HashMap<Vertex, String>, Set<Binding>>  b = new HashMap<>();

		for(Entry<HashMap<Vertex, String>, Set<Binding>> sj:BGPEval.StartBinding123Large.entrySet())
			System.out.println("This is bindJoin at beginning:"+sj.getKey()+"--"+sj.getValue());

	if(results.size()==0)
		return;
	String str=string;
	System.out.println("This is the the the the the string:"+str);
//	&& ev.getValue().equals(belong)
	//for(EdgeOperator e:AllEdges)
	//{	
		//System.out.println("This is end 23 set Binding in AllEdge:"+e);
	//}	
	
	
///	for(Entry<HashMap<HashMap<Edge, Vertex>, String>, ArrayList<Binding>> e:BGPEval.StartBindingFinalLarge.entrySet())
//		{	
//				System.out.println("This is end 23 set Binding in HashJoin:"+e.getKey());
//		}	
	for(Entry<HashMap<HashMap<Edge, Vertex>, String>, ArrayList<Binding>> e:BGPEval.StartBindingFinalLarge.entrySet())
	{	
	//System.out.println("This is end 23 set Binding in HashJoin0101:"+e.getKey());
	temp1=new HashSet<>();
	///	results = QueryUtil.join(results, start.getBindings());
		int k1=0;
	/*for(Entry<Edge, String> dsb:BGPEval.DoubleStartBindingLarge.entries())
	{
		if(BGPEval.DoubleStartBindingLarge.containsKey(dsb.getKey().getV1()) &&
				BGPEval.DoubleStartBindingLarge.containsKey(dsb.getKey().getV2()))

		if(edge.getV1().toString().equals(dsb.getKey().getV1().toString()) && dsb.getValue().equals(string))
		{System.out.println("This is problem within HashJoin:"+edge+"="+dsb);
		
			if(!dsb.getKey().equals(edge)) 
			{
				k1=1;
				break;
			}
		if(k1==1)
			break;
		}
			
	}*/	
	System.out.println("This is end 24.4 set Alledges equal in HashJoin:"+k1);
	
	if(k1==1) {
		k1=0;
		break;
	}
			//System.out.println("This is end 24 set Binding in HashJoin11111:"+BGPEval.StartBindingSetBJ);
			for(EdgeOperator ae:AllEdges) {
				//System.out.println("This is end 23 set Alledges in HashJoin:"+ae);
					for(Entry<HashMap<Edge, Vertex>, String> ev:e.getKey().entrySet())
					{joinVars=null;
					int counter=0;
				
				//	System.out.println("---------------------------------------------------------------------");
					if(ev.getValue().equals(string))
						for(Entry<Edge,Vertex> ev1:ev.getKey().entrySet())
					{for(Node sbk:StartBinding)
						if(sbk.toString().equals(ev1.getValue().getNode().toString()))
							{
								counter=1;
								break;
							}
				if(counter==1)
					break;
					}
					if(counter==1) {
						counter=0;
						continue;
					}
					Vertex ver=null;
					
					for(Entry<Edge,Vertex>  ev1:ev.getKey().entrySet())
					{
					if(ev1.getKey().equals(ae.getEdge())) {
	//					for(Edge e1:ProcessedEdge)
	//						if(e1.equals(ev.getKey()))
	//							continue;
					
						System.out.println("This is euqality of AllEdges Vertex:"+ae.getEdge()+"--"+ev.getKey()+"--"+edge);
						
						//	for(Node sbk:StartBinding)
					//	System.out.println("This is StartBinding:"+sbk+"--"+ev.getValue().getNode());
						
						
						
					//				if(e1.getKey().toString().equals(ev.getKey().toString()))
					//					if(e1.getValue()!=null)
					//					return;
					//	for(Binding r:results)
					
						
						//DoubleStartBinding
					System.out.println("This is end 25 set Alledges equal in HashJoin:"+
						results.parallelStream().limit(10).collect(Collectors.toList()));
						temp1=new HashSet<>();
						ProcessedEdge.add(ae.getEdge());
						Var r=null;	
							Iterator<Var> l = results.iterator().next().vars();
						//	while(l.hasNext())
							 r = Var.alloc(ev1.getValue().getNode());
							ver=ev1.getValue();
							System.out.println("This is rule no. 1:"+r+"--"+ev1.getValue().getNode());
							
							while (l.hasNext()) {
								Var v = l.next();
								
										
								if (r.toString().equals(v.toString())) {
				//					System.out.println("This is rule no.3:"+r+"--"+v);
									joinVars=v;
								}
							}
						//	 System.out.println("This is joinVars0:"+joinVars);
							System.out.println("This is rule no. 1.1:"+r+"--"+joinVars);
					}
							if(joinVars==null)
								continue;
 //System.out.println("This is joinVars:"+joinVars);
				 StartBinding.add(joinVars);	
						
				 for(Binding e1:results){
					//	//	BindingMap join = BindingFactory.create();
					//	//	join.add(joinVars, e1.get(joinVars));
							if(e1.get(joinVars)!=null)
							{
							//	temp1.add(BindingFactory.binding(joinVars, e1.get(joinVars)));
								if(e1.get(joinVars).toString().contains("http"))
								temp1.add(BindingFactory.binding(joinVars,StageGen.StringConversion(e1.get(joinVars).toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", "")) ));
								else
									temp1.add(BindingFactory.binding(joinVars,StageGen.StringConversion(e1.get(joinVars).toString().replace("<", "").replace(">", "").substring(0, e1.get(joinVars).toString().replace("<", "").replace(">", "").length()-1)) ));
								}
								
							}
							//for(int k=0;k<4;k++)
							//	System.out.println("This is join:"+temp1);
								
								
						//}
				 HashMap<Vertex,String> a = new HashMap<>();
				 a.put(ver, string);
				 b = new HashMap<>();
					System.out.println("This is temp1:"+ver+"--"+string);
					
					for(Entry<HashMap<Vertex, String>, Set<Binding>> sj:BGPEval.StartBinding123Large.entrySet())
						System.out.println("This is bindJoin with value25252525:"+sj.getKey()+"--"+sj.getValue());

					
					
					if(BGPEval.StartBinding123Large.isEmpty()) {
						BGPEval.StartBinding123Large.put(a, temp1);
					}
					else {
						if(BGPEval.StartBinding123Large.containsKey(a)) {
							if(BGPEval.StartBinding123Large.get(a).size()>temp1.size())
							{
								System.out.println("This iteration contains it:"+a);
								b.put(a, temp1);}
						}
						else {
							System.out.println("This iteration does not contains it:"+a);
							b.put(a, temp1);
							BGPEval.StartBinding123Large.putAll(b);
	
						}
					
						/*for(Entry<HashMap<Vertex, String>, Set<Binding>> e1:BGPEval.StartBinding123Large.entrySet())
						{
							if(e1.ge) 
								{System.out.println("This iteration contains it:"+e1);
								if(e1.getValue().size()>temp1.size())
									b.put(a, temp1);
								//else
								//	b.put(a, temp1);
						
								}
								else
									{System.out.println("This iteration does not contains it:"+e1);
									b.put(a, temp1);
									}
						}
						*/
					/*	for(Entry<HashMap<Vertex, String>, Set<Binding>> e1:BGPEval.StartBinding123Large.entrySet())
							for(Entry<Vertex,String> e2:e1.getKey().entrySet())
							{
								//System.out.println("This is the problem in progress:"+ver+"--"+string+":--:"+e2);
								if(!e2.getKey().equals(ver) && !e2.getValue().equals(string)) {
									System.out.println("This is the protocol1:"+e1.getValue().size()+"--"+temp1.size());
									
									b.put(a, temp1);

								}
							else if(e2.getKey().equals(ver) && !e2.getValue().equals(string)  )
							{		System.out.println("This is the protocol2:"+e1.getValue().size()+"--"+temp1.size());
							
								
								b.put(a, temp1);
							} 
							else if(ver.equals(e2.getKey()) && string.equals(e2.getValue()))
									{	
									System.out.println("This is the protocol3:"+e1.getValue().size()+"--"+temp1.size());
									if(e1.getValue().size()>temp1.size())
										b.put(a, temp1);
									//else
									//	b.put(a, temp1);
							
									}
								
								
					
							}
					*/}
					
					/*if(!BGPEval.StartBinding123Large.isEmpty()) {
						for(Entry<HashMap<Vertex, String>, Set<Binding>> e1:BGPEval.StartBinding123Large.entrySet())
							for(Entry<Vertex,String> e2:e1.getKey().entrySet())
							
						if(!e2.getKey().equals(ver)) {
							BGPEval.StartBinding123Large.put(a, temp1);

						}
						else
			for(Entry<HashMap<Vertex, String>, Set<Binding>> sb1:BGPEval.StartBinding123Large.entrySet())	{
				for(Entry<Vertex, String> sb2:sb1.getKey().entrySet())
				{
					System.out.println("This is temp111111111:"+sb2.getKey()+"--"+sb2.getValue());
					
					if(ver.equals(sb2.getKey()) && string.equals(sb2.getValue()))
					{	
					System.out.println("This is the protocol:"+sb1.getValue().size()+"--"+temp1.size());
					if(sb1.getValue().size()>temp1.size())
					BGPEval.StartBinding123Large.put(a, temp1);
					}
					else {
						BGPEval.StartBinding123Large.put(a, temp1);
							
					}
				}	
				}
					}
					
			
	*/
//					System.out.println("This is temp1:"+ev.getValue()+"--"+temp1.parallelStream().limit(1).collect(Collectors.toList()));
							}
					
						
					}
					
					}
			
		}
//	for(Entry<Vertex, Set<Binding>> sj:BGPEval.StartBinding123.entrySet())
//		System.out.println("This is bindJoin with value:"+sj.getKey()+"--"+sj.getValue().parallelStream().limit(4).collect(Collectors.toList()));

}

 static void IntermediateProcedure(List<Binding> results,List<EdgeOperator> AllEdges, List<Edge> edgee) {
	LinkedHashSet<Binding>	temp1 = new LinkedHashSet<>();
	Set<Node> StartBinding = new HashSet<>();	
	 HashMap<Vertex, LinkedHashSet<Binding>>  b = new HashMap<>();
	
//	&& ev.getValue().equals(belong)
//	for(EdgeOperator e:AllEdges)
//	{	
//		System.out.println("This is end 23 set Binding in AllEdge:"+e);
//	}	
	
	
//	for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> e:BGPEval.StartBindingFinal.entrySet())
//		{	
	//			System.out.println("This is end 23 set Binding in HashJoin:"+e.getKey());
	//	}	
	for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> e:BGPEval.StartBindingFinal.entrySet())
	{	
	//System.out.println("This is end 23 set Binding in HashJoin0101:"+e.getKey());
	temp1=new LinkedHashSet<>();
	///	results = QueryUtil.join(results, start.getBindings());
		int k1=0;
	//	for(Edge dsb:BGPEval.DoubleStartBinding)
	//	System.out.println("This is end 23 set Binding in HashJoin0202:"+dsb);

	/*		for(Edge dsb:BGPEval.DoubleStartBinding)
	{
				if(BGPEval.StartBinding123.containsKey(dsb.getV1()) &&
						BGPEval.StartBinding123.containsKey(dsb.getV2()))
		if(edge.getV1().toString().equals(dsb.getV1().toString()))
		{	//System.out.println("This is problem within HashJoin:"+edge+"="+dsb.getV1());
		
			if(!dsb.equals(edge)) 
			{
				k1=1;
				break;
			}
		}	
	}*/	
	//System.out.println("This is end 24.4 set Alledges equal in HashJoin:"+k1);
	
	///if(k1==1) {
	//	k1=0;
	//	continue;
	//}
			//System.out.println("This is end 24 set Binding in HashJoin11111:"+BGPEval.StartBindingSetBJ);
			//for(EdgeOperator ae:AllEdges) {
				//System.out.println("This is end 23 set Alledges in HashJoin:"+ae);
					for(Entry<Edge, Vertex> ev:e.getKey().entries())
					{
						
						joinVars=null;
					int counter=0;
				
				//	System.out.println("---------------------------------------------------------------------");
					
					for(Node sbk:StartBinding)
					{//System.out.println("This is end 23 set Alledges in HashJoin:"+sbk+"--"+ev.getValue().getNode());
					
						if(sbk.toString().equals(ev.getValue().getNode().toString()))
							{
								counter=1;
								break;
							}
					if(counter==1) {
						counter=0;
						continue;
					}
					}
						Vertex ver=null;
		for(Edge ee:edgee) {
			System.out.println("This is euqality of AllEdges Vertex:"+edgee+"--"+ev.getKey()+"--"+ee);
			
					if(ev.getKey().equals(ee)) {
						
					//	System.out.println("This is end 23 set Alledges in HashJoin:"+ev);
						
	//					for(Edge e1:ProcessedEdge)
	//						if(e1.equals(ev.getKey()))
	//							continue;
					
						
						//	for(Node sbk:StartBinding)
					//	System.out.println("This is StartBinding:"+sbk+"--"+ev.getValue().getNode());
						
						
						
					//				if(e1.getKey().toString().equals(ev.getKey().toString()))
					//					if(e1.getValue()!=null)
					//					return;
					//	for(Binding r:results)
					
						
						//DoubleStartBinding
				//	System.out.println("This is end 25 set Alledges equal in HashJoin:"+
					//	results.parallelStream().limit(10).collect(Collectors.toList()));
						temp1=new LinkedHashSet<>();
						ProcessedEdge.add(ev.getKey());
						for(Entry<Vertex, LinkedHashSet<Binding>> sj:BGPEval.StartBinding123.entrySet())
							if(sj.getValue().size()<10)
							System.out.println("This is bindJoin with value111111 before:"+sj.getKey()+"--"+sj.getValue());
										
							Iterator<Var> l = results.iterator().next().vars();
						//	while(l.hasNext())
							Var r=null;
							if(!ev.getValue().getNode().toString().contains("http"))
							{r = Var.alloc(ev.getValue().getNode());
								ver=ev.getValue();
							System.out.println("This is rule no. 1:"+r+"--"+ev.getValue().getNode());
							
							while (l.hasNext()) {
								Var v = l.next();
								
										
								if (r.toString().equals(v.toString())) {
				//					System.out.println("This is rule no.3:"+r+"--"+v);
									joinVars=v;
								}
							}
						//	 System.out.println("This is joinVars0:"+joinVars);
							System.out.println("This is rule no. 1.1:"+r+"--"+joinVars);
							
							if(joinVars==null)
								continue;
 //System.out.println("This is joinVars:"+joinVars);
				 StartBinding.add(joinVars);	
							}
							else
							{
								String t = ev.getValue().getNode().toString();
								ver=ev.getValue();
									
								System.out.println("This is rule no. 1:"+t+"--"+ev.getValue().getNode());
								
								while (l.hasNext()) {
									Var v = l.next();
									
											
									if (t.toString().equals(v.toString())) {
					//					System.out.println("This is rule no.3:"+r+"--"+v);
										joinVars=v;
									}
								}
							//	 System.out.println("This is joinVars0:"+joinVars);
								System.out.println("This is rule no. 1.1:"+t+"--"+joinVars);
								
								if(joinVars==null)
									continue;
	 //System.out.println("This is joinVars:"+joinVars);
					 StartBinding.add(joinVars);
							}
				 for(Binding e1:results){
					//	//	BindingMap join = BindingFactory.create();
					//	//	join.add(joinVars, e1.get(joinVars));
							if(e1.get(joinVars)!=null)
							{
							//	temp1.add(BindingFactory.binding(joinVars, e1.get(joinVars)));
								if(e1.get(joinVars).toString().contains("http"))
								temp1.add(BindingFactory.binding(joinVars,StageGen.StringConversion(e1.get(joinVars).toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", "")) ));
								else
									temp1.add(BindingFactory.binding(joinVars,StageGen.StringConversion(e1.get(joinVars).toString().replace("<", "").replace(">", "").substring(0, e1.get(joinVars).toString().replace("<", "").replace(">", "").length()-1)) ));
								}
								
							}
							//for(int k=0;k<4;k++)
							//	System.out.println("This is join:"+temp1);
								
								
						//}
//					System.out.println("This is temp1:"+ev.getValue()+"--"+temp1.parallelStream().limit(1).collect(Collectors.toList()));
						//BGPEval.StartBinding123.put(ev.getValue(), temp1);
							
				// HashMap<Vertex,String> a = new HashMap<>();
				// a.put(ver, string);
				// b = new HashMap<>();
					//System.out.println("This is temp1:"+ver+"--"+string);
					
				//	for(Entry<Vertex, Set<Binding>> sj:BGPEval.StartBinding123.entrySet())
			//			System.out.println("This is bindJoin with value25252525 normal:"+sj.getKey()+"--"+sj.getValue());

					 b = new HashMap<>();
						
					
					if(BGPEval.StartBinding123.isEmpty()) {
						BGPEval.StartBinding123.put(ver, temp1);
					}
					else {
						if(BGPEval.StartBinding123.containsKey(ver)) {
							if(BGPEval.StartBinding123.get(ver).size()>temp1.size())
							{
								System.out.println("This iteration contains it:"+ver);
								b.put(ver, temp1);}
						}
						else {
							System.out.println("This iteration does not contains it:"+ver);
							b.put(ver, temp1);
							BGPEval.StartBinding123.putAll(b);
	
						}

					
					}
					
						
					}
					}
					}
			
		//}
		for(Entry<Vertex, LinkedHashSet<Binding>> sj:BGPEval.StartBinding123.entrySet())
			System.out.println("This is bindJoin with value111111:"+sj.getKey()+"--"+sj.getValue().size());
//	for(Entry<Vertex, Set<Binding>> sj:BGPEval.StartBinding123.entrySet())
//		System.out.println("This is bindJoin with value:"+sj.getKey()+"--"+sj.getValue().parallelStream().limit(4).collect(Collectors.toList()));

 }
 }
}
