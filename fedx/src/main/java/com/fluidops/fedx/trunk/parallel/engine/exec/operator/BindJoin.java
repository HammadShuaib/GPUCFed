package com.fluidops.fedx.trunk.parallel.engine.exec.operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import org.apache.jena.graph.Triple;
import org.apache.jena.query.Query;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingFactory;
import org.apache.jena.sparql.syntax.ElementGroup;
import org.joda.time.LocalTime;

import com.fluidops.fedx.trunk.graph.Edge;
import com.fluidops.fedx.trunk.graph.Vertex;
import com.fluidops.fedx.trunk.parallel.engine.ParaEng;
import com.fluidops.fedx.trunk.parallel.engine.exec.BoundQueryTask;
import com.fluidops.fedx.trunk.parallel.engine.exec.QueryTask;
import com.fluidops.fedx.trunk.parallel.engine.main.BGPEval;
import com.fluidops.fedx.trunk.parallel.engine.main.StageGen;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class BindJoin extends EdgeOperator {
	int remaining = 0;
	public static int inner=1;
	static List<EdgeOperator> AllEdges1 = new ArrayList<>();
	private int total;
	Var joinVars = null;
	 public static Multimap<Binding,Binding> MultipleBinding =  ArrayListMultimap.create();
	public static String ciff="";
	public static String biff="";
	// public Stream<Binding> results1;
	public static Set<Binding> intermediate = new HashSet<>();
	static List<Binding> results = new ArrayList<Binding>();

	public BindJoin(Vertex s, Edge e) {
		super(s, e);
	}

	@Override
	public void exec() {
		results = new ArrayList<>();
		intermediate = new HashSet<>();
		AllEdges1 =new ArrayList<>();
		System.out.println("These are intermediate0000:"+edge);

		Vertex end;
		if (start.equals(edge.getV1())) {
			end = edge.getV2();
		} else {
			end = edge.getV1();
		}
	//	for (Entry<Vertex, Set<Binding>> e : BGPEval.StartBinding123.entrySet()) {
	//	 System.out.println("These are intermediate11111:"+e.getKey()+"--"+e.getValue().size());
	//	}
		
		if(ParaEng.FiltereqLarge==null || ParaEng.FiltereqLarge.size()==0 ||  ParaEng.FiltereqLarge.isEmpty()) {		
		
		//	for(Entry<Vertex, Set<Binding>> e:BGPEval.StartBinding123.entrySet())
			String x = start.getNode().toString().replaceAll("[^A-Za-z]+", "");
////			for(List<EdgeOperator> jg1:BGPEval.JoinGroupsListExclusive)
		for(Entry<Vertex, List<Binding>> jg11:BGPEval.urik.entrySet())
//			if(jg11.getEdge().equals(edge))
					System.out.println("This is jg1 in jgl:"+jg11+"--"+edge.getV1()+"--"+edge.getV2());
		int i = 0;
		BindJoin.inner=0;
		
		//if(BGPEval.urik)
			for(Entry<Vertex, List<Binding>> uri:BGPEval.urik.entrySet()) {
				if(edge.getV1().toString().equals(uri.getKey().toString()) ||
						edge.getV2().toString().equals(uri.getKey().toString())	)
				intermediate.addAll(uri.getValue());
			}
			
			
			//for(Binding i1:intermediate)
		//		System.out.println("THis is the binding of intermediate:"+i1);
		/*	if(intermediate.size()<10 && intermediate.size()>0) {
				List<Binding> inter =new ArrayList<>();
				for(int i1=intermediate.size()-1;i1>=0;i1--)
					inter.add(intermediate.get(i1));
				intermediate.clear();
				intermediate.addAll(inter);
			System.out.println("THis is new intermediate order:"+intermediate);	
			}
			*/
		if(!intermediate.isEmpty() ||intermediate!=null)
		for (Entry<Vertex, LinkedHashSet<Binding>> e : BGPEval.StartBinding123.entrySet()) {
			//int y = x.compareTo(e.getKey().getNode().toString().replaceAll("[^A-Za-z]+", ""));
			System.out.println("This is comparison value:" + x+"--"+e.getKey().getNode().toString().replaceAll("[^A-Za-z]+", ""));
			//if (y == 0) {
if(x.equals(e.getKey().getNode().toString().replaceAll("[^A-Za-z]+", ""))) {
	intermediate.addAll(BGPEval.StartBinding123.get(e.getKey()))	;	
	
	//	for (Binding e1 : e.getValue()) {
			//		intermediate.add((org.apache.jena.sparql.engine.binding.Binding) e1);
//for(Binding i1:intermediate)
	//				System.out.println("This is intermediate:"+i1);
				//}
				// System.out.println("These are
				// intermediate:"+intermediate.parallelStream().limit(2).collect(Collectors.toList()));
				
}
			System.out.println("This is end 23 set Binding:" + x + "--"
					+ e.getKey().getNode().toString().replaceAll("[^A-Za-z]+", "") + "--"
					+ e.getValue().stream().limit(3).collect(Collectors.toList()) + "--" + edge + "--" + edge);
		}
		
//		if(intermediate.isEmpty()) {
	//	System.out.println("This is intermediate here null");
	//		intermediate=null;
	//	}
		// List<List<Binding>> lists = ListUtils.partition(intermediate,
		// Math.round(intermediate.size() / 2) + 1);
		// twoThreads(lists);
		/*
		 * ExecutorService executor = Executors.newWorkStealingPool(); Future<?> result
		 * = executor.submit(() ->twoThreads(lists)); // future.get() Waits for the task
		 * to complete, and then retrieves its result. try { result.get(); } catch
		 * (InterruptedException e4) { // TODO Auto-generated catch block
		 * e4.printStackTrace(); } catch (ExecutionException e4) { // TODO
		 * Auto-generated catch block e4.printStackTrace(); } executor.shutdown();
		 */
	//	for (ConcurrentHashMap<Vertex, Edge> i1 : BGPEval.StartBindingFinal.keySet())
	//		System.out.println("These are intermediate size:" + i1);
	//for (Binding i1 : intermediate)
		QueryTask.wfilter=0;
ciff="";
biff="";
		//	System.out.println("These are intermediate size:" + intermediate.size()+"--"+LocalTime.now());
		List<Binding> a=new ArrayList<>();
		   String b=null;
		 String c=null;
		 inner=0;
	/*	for(Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> iff:ParaEng.InnerFilter.entrySet()) {
			for(Entry<String, Set<Binding>> iff1:iff.getKey().entrySet()) {
				if(iff1.getValue()==null)
				b=iff1.getKey();
				else {
					a.addAll(iff1.getValue().parallelStream().collect(Collectors.toList()));
				c=iff1.getKey();
						
				}
				
					
			}
			for(Entry<String, Set<Binding>> iff1:iff.getValue().entrySet()) {
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
		if(edge.getV1().getNode().toString().equals(b) ||edge.getV2().getNode().toString().equals(b))
		{if(a.size()>0&& b!=null)
		{
			//	for(Binding a1:a)
			System.out.println("This is now gaining clarity:"+edge.getV1()+"--"+edge.getV2()+"--"+b);
		Set<Binding> intermediate= new HashSet<>();	
	for(Binding a1:a) {
		System.out.println("This is now gaining clarity:"+a1+"--"+b);
		
		intermediate.add(BindingFactory.binding(Var.alloc(b.substring(1)) , a1.get(Var.alloc(c.substring(1)))
					));
			}
			if(intermediate.isEmpty()) {
				System.out.println("This is intermediate here null");
					intermediate=null;
				}
	//	System.out.println("This is intermediate values in Inner Join:"+intermediate);
			results =te.exec(intermediate,null);//).parallel();}).join();
			ciff=c;
			biff=b;
			inner=1;
			
		}}
			else
	*/	//	{
int l=0;
				for(Binding ii:intermediate) {
					System.out.println("This is here in part1111111111111111111111111111111111111111111111111111:"+ii);
					if(l==10)
						break;
					l++;
				}
				
				results = te.exec(intermediate, null);
			//}
	//	for (Binding r : results)
			System.out.println("These are size of result in BindJoin:" + results.size());
			/*
			if ((results == null || results.size() == 0) && ParaEng.Filtereq!=null) {
			int br=0;
				for( Entry<HashMap<String, String>, String> feq:ParaEng.Filtereq.entrySet())
				for(Entry<String,String> feq1:feq.getKey().entrySet())
			{
				
					Boolean ne=BindJoin.NodeExtraction("?"+te.getTriple().getSubject().getName());
					Boolean ne1=BindJoin.NodeExtraction("?"+te.getTriple().getObject().getName());
					System.out.println("This is a good thing finally5656565656");
					if(results.size()>0)
						{break;
						
						}
					if(ne|| ne1)
			{
						System.out.println("This is the nodeextraction:"+ne+"--"+ne1+"--"+te.getTriple().getObject().getName()+"--"+feq1.getValue());
						
				if(feq1.getValue()==null) {		
				System.out.println("This is the biggest problem now:"+feq);
				System.out.println("This is the biggest problem now11111:"+feq1);
				
			//	HashMap<String,String> b = new HashMap<>();
				QueryTask.wfilter=1;
				feq.getKey().replace(feq1.getKey(), "No");
				results = te.exec(intermediate, null);
				System.out.println("This is a good thing finally2323232");

			//	br=1;
			//	break;
		//		b.put(feq1.getKey(), "No");
		//		ParaEng.Filtereq.put(b, feq.getValue());
			
			}
			}else break;
		//	if(br==1) {
		//		br=0;
		//		break;
		//	}
			}
				
			}
	*/
			System.out.println("This is a good thing finally6767676767 in 111:"+ParaEng.Filtereq+"--"+ParaEng.Filtereq.size());
			if (results.size() > 0 && ParaEng.Filtereq.size()>0) {
						
				 List<EdgeOperator> BushyTreeTriple = BoundQueryTask.BushyTreeTripleCreation(te.getTriple());
					Query query = BindJoin.buildQuery(te.getTriple(),BushyTreeTriple);
				//	 System.out.println("This is a good thing in BindJoin"+query);
						
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
					QueryTask.wfilter=1;
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
				//	 System.out.println("This is a good thing in BindJoin"+Vertex);
						
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
		

			for(Entry<HashMap<String, String>, String> fr:ParaEng.Filtereq.entrySet())
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
				 results =te.exec(intermediate,null);//).parallel();}).join();
			
			System.out.println("This is a good thing finally2323232");

		//	br=1;
		//	break;
	//		b.put(feq1.getKey(), "No");
	//		ParaEng.Filtereq.put(b, feq.getValue());
		
			}}
		}
	}

			
		for(Entry<HashMap<String, String>, String> as:ParaEng.Filtereq.entrySet())
		System.out.println("This is the replacement of filterreq:"+as);
		/*	results = te.exec(null, null);
			// for(Binding i1:results)
			// System.out.println("These are null result in bindjoin:"+i1);
		}*/
		/*
	//	if(BGPEval.Edgetype=="Norm") {
		for (List<EdgeOperator> e : BGPEval.JoinGroupsListExclusive) {
			for (EdgeOperator e1 : e)
				if (e1.getEdge().toString().equals(edge.toString())) {
					AllEdges1.addAll(e);
				}
		}

		for (EdgeOperator e : BGPEval.JoinGroupsListLeft) {
			//System.out.println("This is equality in JoinGroupsListLeft:"+e.getEdge().toString()+"--"+edge.toString());
			if (e.getEdge().toString().equals(edge.toString())) {
				AllEdges1.add(e);
			}
		}

		for (EdgeOperator e1 : BGPEval.JoinGroupsListRight) {

			if (e1.getEdge().toString().equals(edge.toString())) {
				AllEdges1.add(e1);
			}
		}//}
		//else {
		for(List<EdgeOperator> e:BGPEval.JoinGroupsListOptional) {
			for(EdgeOperator e1:e)
				if(e1.getEdge().equals(edge)) {
					AllEdges1.addAll(e);
				}
		}

		for(EdgeOperator e:BGPEval.JoinGroupsListLeftOptional) {
		
				if(e.getEdge().equals(edge)) {
					AllEdges1.add(e);
				}
		}
		
				for(EdgeOperator e1:BGPEval.JoinGroupsListRightOptional) {
					
					if(e1.getEdge().equals(edge)) {
						AllEdges1.add(e1);
					}
				}*/
	//	}
		
//		Iterator<Var> vff = BindJoin.results.iterator().next().vars();
//		Var vf = null;
//			while (vff.hasNext()) {
//				 vf = vff.next();

//			}
			
		
	/*	if ( ParaEng.InnerFilter!=null && (!BGPEval.StartBinding123.containsKey(edge.getV1())) && !BGPEval.StartBinding123.containsKey(edge.getV1())) {
			Iterator<Binding> rIterator;
			List<Binding> temp1 = new ArrayList<>();
			int br = 0;
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
//		System.out.println("This is rule no. 2 in BindJoin:" + joinVars);

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
													- 2).trim())));
					}

				}
			//	for(	Entry<Vertex, Set<Binding>> eh:BGPEval.StartBinding123.entrySet())
				//	System.out.println("This is timing within postprocess454545454545 before:"+"--"+eh);
			Iterator<Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>>> ifiterator = ParaEng.InnerFilter.entrySet().iterator();
			while(ifiterator.hasNext()) {
			Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>> ifnext = ifiterator.next();
			ifnext.getKey().replace(f, temp1);
	//		ifnext.getValue().replace(f, temp1);
			}	
			
	//		ParaEng.InnerFilter.put(f, temp1);
			//for(	Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> eh:ParaEng.InnerFilter.entrySet())
			//	System.out.println("This is timing within postprocess454545454545:"+"--"+eh);

			
			
			}
				
		}
*/
	System.out.println("THis is the vertex of this query:");	
				// Multimap<String, String> cars = ArrayListMultimap.create();
	//	if (!BGPEval.StartBinding123.containsKey(start)) {
		//	System.out.println("This key not present:"+BindJoin.AllEdges1);
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
	
			
	
	ForkJoinPool fjp = new ForkJoinPool();
			try {
				fjp.submit(() -> 
				HashJoin.IntermediateProcedure(BindJoin.results, HashJoin.AllEdges, edgee)).get();
			} catch (InterruptedException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			} catch (ExecutionException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
		//}
			fjp.shutdown();
		
		//	Iterator<Var> l = BindJoin.results.iterator().next().vars();
		//	Set<Var> v9=new HashSet<>();
		//		while (l.hasNext()) {
		//			Var v2 = l.next();
		//			v9.add(v2);
					
							
		//	}
			
				
				
			//	for(Var v1:v9)
			//		if(BGPEval.ProcessedVertexT.toString().contains(v1.toString()))
			//		{
				//		if(!Var.alloc(start.getNode()).toString().equals(v1.toString())) {
				//			Var va = Var.alloc(edge.getV1().getNode());
							
				//			Var va1 = Var.alloc(edge.getV2().getNode());
									
					//		for(Binding e1:results) {
								
					//		MultipleBinding.put(BindingFactory.binding(va1, e1.get(va1)),BindingFactory.binding(va, e1.get(va)));
							
						//	}
						
							

							System.out.println("----------------------------------------------------------------------------");
						//	for (Entry<Vertex, Set<Binding>> e : BGPEval.StartBinding123.entrySet()) {
						//		 System.out.println("These are intermediate3333332222222222222222:"+e.getKey()+"--"+e.getValue());
						//		}
						
						//	for(Entry<Binding, Binding> mb:MultipleBinding.entries())
						//		System.out.println("This is child1:"+mb);
//			}
						
//					}
				
	//			if(v9.contains(Var.alloc(start.getNode())))
		//			System.out.println("These are now being processed here in here");
			if (BGPEval.StartBinding123.containsKey(start)) {
		
				System.out.println("----------------------------------------------------------------------------");
				
				LinkedHashSet<Binding> temp1 = new LinkedHashSet<>();
			Set<Vertex> v = new HashSet<>();
			v.addAll(BGPEval.StartBinding123.keySet());
//}
			int br = 0;
			Iterator<Var> l1 = BindJoin.results.iterator().next().vars();
			Var r=null;
			for (Vertex v1 : v) {
				r = Var.alloc(v1.getNode());
			//	System.out.println("This is rule no. 1 in BindJoin:" + r);
				while (l1.hasNext()) {
					Var v2 = l1.next();

					if (r.equals(v2)) {
			//			System.out.println("This is rule no.3 in BindJoin:" + r + "--" + v);
						joinVars = v2;
						br = 1;
						break;
					}

				}
				if (br == 1) {
					br = 0;
					break;
				}
			}
			if (joinVars != null) {
				if( Var.alloc(start.getNode()).equals(r)) {
				temp1=new LinkedHashSet<>();
			//	System.out.println("This is rule no. 2 in BindJoin:" +  Var.alloc(start.getNode())+"--"+r);
			//	System.out.println("This is rule no. 2.1 in BindJoin:" + joinVars);

				for (Binding e1 : results) {
			if(e1.get(joinVars)!=null) {
		//	System.out.println("This is the final problem:"+e1.get(joinVars));
				temp1.add(BindingFactory.binding(joinVars, e1.get(joinVars)));
			}// for(int k=0;k<4;k++)

				}
//	for(Binding t:temp1)
//System.out.println("This the replacement:"+t+"--"+temp1.size()+"--"+BGPEval.StartBinding123.get(start).size());
//	BGPEval.StartBinding123.remove(joinVars);//.replace(start, BGPEval.StartBinding123.get(start), temp1);
				BGPEval.StartBinding123.put(start, temp1);
//	for(Entry<Vertex, Set<Binding>> t:BGPEval.StartBinding123.entrySet())
			//	System.out.println("This the replacement:" + "--" + temp1.size() + "--"
			//			+ BGPEval.StartBinding123.get(start).size());
			}}
		}
		
		//	for (Entry<Vertex, Set<Binding>> e : BGPEval.StartBinding123.entrySet()) {
		//		 System.out.println("These are intermediate33333344444444444:"+e.getKey()+"--"+e.getValue());
		//		}

			/// results = QueryUtil.join(results, start.getBindings());

		// for(Binding r:results)
		// System.out.println("These are value of bind results:"+r);
		int kl = 0;

		
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
		
	
		
		Edge CurrentEdgeOperator = new Edge(edge.getV1(), edge.getV2());// edge.getV1()+"--"+edge.getV2();
		Iterator<Entry<EdgeOperator, List<Binding>>> frIterator = BGPEval.finalResult.entrySet().iterator();
		while (frIterator.hasNext()) {
			total++;
			Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
			// System.out.println("This is
			// finalResult:"+ab.getKey()+"--"+ab.getValue().size());

			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				BGPEval.finalResult.replace(ab.getKey(), results);
				// CurrentEdgeOperator=ab.getKey();
			}

		}

		for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultRight.entrySet()) {
			total++;
			// System.out.println("This is
			// finalResultRight:"+ab.getKey()+"--"+ab.getValue().size());

			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				BGPEval.finalResultRight.replace(ab.getKey(), results);
			}

			// CurrentEdgeOperator=ab.getKey();

		}
		for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultLeft.entrySet()) {
			total++;

			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				// System.out.println("This is
				// finalResultLeft:"+ab.getKey()+"--"+results.size());

				BGPEval.finalResultLeft.replace(ab.getKey(), results);
				// CurrentEdgeOperator=ab.getKey();

			}
		}
		//System.out.println("This is out of TripleExectuion in BindJoin000000000000:");

		// synchronized(BGPEval.finalResultOptional) {
		for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultRightOptional.entrySet()) {
			total++;
			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				BGPEval.finalResultRightOptional.replace(ab.getKey(), results);
			}

			// CurrentEdgeOperator=ab.getKey();

		}
		for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultLeftOptional.entrySet()) {
			total++;
			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				BGPEval.finalResultLeftOptional.replace(ab.getKey(), results);
				// CurrentEdgeOperator=ab.getKey();

			}
		}
		for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultOptional.entrySet()) {
			total++;
			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				BGPEval.finalResultOptional.replace(ab.getKey(), results);
				// CurrentEdgeOperator=ab.getKey();

			}
		}

		for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultRightMinus.entrySet()) {
			total++;
			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				BGPEval.finalResultRightMinus.replace(ab.getKey(), results);
			}

			// CurrentEdgeOperator=ab.getKey();

		}
		for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultLeftMinus.entrySet()) {
			total++;
			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				BGPEval.finalResultLeftMinus.replace(ab.getKey(), results);
				// CurrentEdgeOperator=ab.getKey();

			}
		}
		for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultMinus.entrySet()) {
			total++;
			if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
					.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
				BGPEval.finalResultMinus.replace(ab.getKey(), results);
				// CurrentEdgeOperator=ab.getKey();

			}
		}
		// for(Binding i:start.getBindings())
		//System.out.println("This is out of TripleExectuion in BindJoin:");
		for(Entry<EdgeOperator, List<Binding>> fr:BGPEval.finalResult.entrySet())
			System.out.println("This is out of TripleExectuion in HashJoin Initil:"+fr.getKey()+"--"+edge);

		for(Entry<EdgeOperator, List<Binding>> fr:BGPEval.finalResultLeft.entrySet())
			System.out.println("This is out of TripleExectuion in HashJoin Initil Left:"+fr.getKey()+"--"+edge);
		
		for(Entry<EdgeOperator, List<Binding>> fr:BGPEval.finalResultRight.entrySet())
			System.out.println("This is out of TripleExectuion in HashJoin Initil Right:"+fr.getKey()+"--"+edge);
		
		for(List<EdgeOperator>  e2:JoinGroupsListLeftTemp) {
			
			for(EdgeOperator e3:e2)
				if(e3.getEdge().equals(CurrentEdgeOperator))
			{
				HashJoin.ProcessedEdgeOperators.add(e2);
			}
		}
		System.out.println("This is now the new tree right:"+HashJoin.ProcessedEdgeOperators+"--"+CurrentEdgeOperator+"--"+total);
		int count = 0;
		frIterator = BGPEval.finalResult.entrySet().iterator();
		while (frIterator.hasNext()) {
			Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
			for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
				for (EdgeOperator ee1 : ee) {
					if (ab.getValue() != null)
						if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final
											
							
							////// algo:"+ab.getKey()+"--"+ab.getValue().size());
							////// System.out.printlnln("THis is coming to final algo2:"+ee);
							HashJoin.ProcessedTriples.put(CompleteEdgeOperator(ab.getKey()), ab.getValue());

							count++;
						}
				}
		}
		//// System.out.printlnln("This is now the new tree right count:"+count);
		count = 0;
		frIterator = BGPEval.finalResultLeft.entrySet().iterator();
		while (frIterator.hasNext()) {
			Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
			for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
				for (EdgeOperator ee1 : ee) {
					if (ab.getValue() != null)
						if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final algo
														////// Rights:"+ab.getKey()+"--"+ab.getValue().size());
							////// System.out.printlnln("THis is coming to final algo2 Right:"+ee);
							// count++;
							List<EdgeOperator> l1 = new ArrayList<>();
							l1.add(ab.getKey());
							HashJoin.ProcessedTriples.put(l1, ab.getValue());

						}
				}
		}
		frIterator = BGPEval.finalResultRight.entrySet().iterator();
		while (frIterator.hasNext()) {
			Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
			for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
				for (EdgeOperator ee1 : ee) {
					if (ab.getValue() != null)
						if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final algo
														////// Rights:"+ab.getKey()+"--"+ab.getValue().size());
							////// System.out.printlnln("THis is coming to final algo2 Right:"+ee);
							// count++;
							List<EdgeOperator> l1 = new ArrayList<>();
							l1.add(ab.getKey());
							HashJoin.ProcessedTriples.put(l1, ab.getValue());

						}
				}
		}
		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) {
			frIterator = BGPEval.finalResultOptional.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final
															////// algo:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2:"+ee);
								HashJoin.ProcessedTriples.put(CompleteEdgeOperator(ab.getKey()), ab.getValue());

								count++;
							}
					}
			}
		}
		//// System.out.printlnln("This is now the new tree right count:"+count);
		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) {
			frIterator = BGPEval.finalResultLeftOptional.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { // ////System.out.printlnln("THis is coming to final algo
															// Left:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2 Left:"+ee);
								List<EdgeOperator> l1 = new ArrayList<>();
								l1.add(ab.getKey());
								HashJoin.ProcessedTriples.put(l1, ab.getValue());
								// count++;

							}
					}
			}
		}
		////// System.out.printlnln("This is now the new tree right count:"+count);

		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) {
			frIterator = BGPEval.finalResultRightOptional.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final algo
															////// Rights:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2 Right:"+ee);
								// count++;
								List<EdgeOperator> l1 = new ArrayList<>();
								l1.add(ab.getKey());
								HashJoin.ProcessedTriples.put(l1, ab.getValue());
							}
					}
			}
		}

		
		if (ParaEng.Minus.contains("MINUS")) {
			frIterator = BGPEval.finalResultMinus.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final
															////// algo:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2:"+ee);
								HashJoin.ProcessedTriples.put(CompleteEdgeOperator(ab.getKey()), ab.getValue());

								count++;
							}
					}
			}
		}
		//// System.out.printlnln("This is now the new tree right count:"+count);
		if (ParaEng.Minus.contains("MINUS")) {
			frIterator = BGPEval.finalResultLeftMinus.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { // ////System.out.printlnln("THis is coming to final algo
															// Left:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2 Left:"+ee);
								List<EdgeOperator> l1 = new ArrayList<>();
								l1.add(ab.getKey());
								HashJoin.ProcessedTriples.put(l1, ab.getValue());
								// count++;

							}
					}
			}
		}
		////// System.out.printlnln("This is now the new tree right count:"+count);

		if (ParaEng.Minus.contains("MINUS")) {
			frIterator = BGPEval.finalResultRightMinus.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final algo
															////// Rights:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2 Right:"+ee);
								// count++;
								List<EdgeOperator> l1 = new ArrayList<>();
								l1.add(ab.getKey());
								HashJoin.ProcessedTriples.put(l1, ab.getValue());
							}
					}
			}
		}

		
		int IsUnion = 0;
		if (!ParaEng.Union.isEmpty()) {

			frIterator = BGPEval.finalResultOptional.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final
															////// algo:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2:"+ee);
								HashJoin.ProcessedTriplesUnion.put(CompleteEdgeOperator(ab.getKey()), ab.getValue());
								IsUnion = 1;
								count++;
							}
					}
			}
		}
		//// System.out.printlnln("This is now the new tree right count:"+count);
		if (!ParaEng.Union.isEmpty()) {
			frIterator = BGPEval.finalResultLeftOptional.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { // ////System.out.printlnln("THis is coming to final algo
															// Left:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2 Left:"+ee);
								List<EdgeOperator> l1 = new ArrayList<>();
								l1.add(ab.getKey());
								HashJoin.ProcessedTriplesUnion.put(l1, ab.getValue());
								// count++;
								IsUnion = 1;
							}
					}
			}
		}
		////// System.out.printlnln("This is now the new tree right count:"+count);

		if (!ParaEng.Union.isEmpty()) {
			frIterator = BGPEval.finalResultRightOptional.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final algo
															////// Rights:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2 Right:"+ee);
								// count++;
								List<EdgeOperator> l1 = new ArrayList<>();
								l1.add(ab.getKey());

								HashJoin.ProcessedTriplesUnion.put(l1, ab.getValue());
								IsUnion = 1;
							}
					}
			}
		}

		////// System.out.printlnln("This is now the new tree right count:"+count);
		// count=0;

		for (List<EdgeOperator> et : HashJoin.EvaluatedTriples) {
			HashJoin.ProcessedTriples.remove(et);
		}
		for(Entry<List<EdgeOperator>, List<Binding>>  pt:HashJoin.ProcessedTriples.entrySet())
	 System.out.println("This is now the new tree right count:"+pt.getKey()+"--"+pt.getValue().size());

		for(List<EdgeOperator>  pt:HashJoin.EvaluatedTriples)
			 System.out.println("This is now the new tree right count12321:"+pt);

		
		 for(Entry<List<EdgeOperator>, List<Binding>> jt:HashJoin.NotJoinedTriples.entrySet())
				System.out.println("This is here in Union 0123-:"+jt.getKey()+"--"+jt.getValue().size());
		 for(Entry<List<EdgeOperator>, List<Binding>> jt:HashJoin.JoinedTriples.entrySet())
				System.out.println("This is here in Union 0123+:"+jt.getKey()+"--"+jt.getValue().size());
		// for(Entry<List<EdgeOperator>, List<Binding>>
		// s:HashJoin.ProcessedTriples.entrySet())
		// System.out.println("This is
		// linkingTreeDup:"+s.getKey()+"--"+s.getValue().size());
		// for(Entry<List<EdgeOperator>, List<Binding>>
		// s:HashJoin.JoinedTriples.entrySet())
		// System.out.println("This is linkingTreeDup
		// JoinedTriples:"+s.getKey()+"--"+s.getValue().size());
		// for(Entry<List<EdgeOperator>, List<Binding>>
		// s:HashJoin.NotJoinedTriples.entrySet())
		// System.out.println("This is linkingTreeDup
		// NotJoinedTriples:"+s.getKey()+"--"+s.getValue().size());

		// System.out.println("These are the processed
		// triples:"+HashJoin.ProcessedTriples);
		// if (!!ParaEng.Union.isEmpty()) {
		HashJoin.ProcessingTask(HashJoin.JoinedTriples, HashJoin.ProcessedTriples, 0,0);
		HashJoin.ProcessingTask(HashJoin.NotJoinedTriples, HashJoin.ProcessedTriples, 0,0);

		// } else {
		// if (IsUnion == 1) {

		// HashJoin.ProcessingTask(HashJoin.JoinedTriples, HashJoin.ProcessedTriples,0);
		// HashJoin.ProcessingTask(HashJoin.NotJoinedTriples,
		// HashJoin.ProcessedTriples,0);
		// remaining++;
		// System.out.println("This is second
		// Union"+HashJoin.JoinedTriples.size()+"--"+HashJoin.ProcessedTriples.size());

		// }
		// }
		System.out.println("THis is NotJoinedTriples JoinedTriples:"+HashJoin.NotJoinedTriples.keySet()+"--"+HashJoin.JoinedTriples.keySet()+"--"+HashJoin.EvaluatedTriples);

		
			for (List<EdgeOperator> et : HashJoin.EvaluatedTriples) {
			//	HashJoin.JoinedTriples.remove(et);
		
				
		
				HashJoin.NotJoinedTriples.remove(et);
				System.out.println("THis is comaprison NotJoinedTriples Evaluated Bind:"+HashJoin.NotJoinedTriples.keySet()+"--"+et);

			}
			
		//	ForkJoinPool fjp1 = new ForkJoinPool();
		//	fjp1.submit(()->{
			if(HashJoin.NotJoinedTriples.size()==1) {
				for(int j=0;j<3;j++) {
					HashJoin.ProcessingTask(HashJoin.NotJoinedTriples, HashJoin.JoinedTriples, 0,0);
					HashJoin.ProcessingTask(HashJoin.JoinedTriples,HashJoin.NotJoinedTriples, 0,0);
				}
			}
			else
		for (int i1 = 0; i1 < 5; i1++) {
	//		HashJoin.ProcessingTask(HashJoin.JoinedTriples, HashJoin.ProcessedTriples, 0,0);
	//		HashJoin.ProcessingTask(HashJoin.NotJoinedTriples, HashJoin.ProcessedTriples, 0,0);

		//		System.out.println("THis is NotJoinedTriples JoinedTriples bind:"+HashJoin.NotJoinedTriples.entrySet().parallelStream().limit(2).collect(Collectors.toList())+"--"+HashJoin.JoinedTriples.entrySet().parallelStream().limit(2).collect(Collectors.toList()));
			//
			//			ForkJoinPool fjp1 = new ForkJoinPool();
//			fjp1.submit(() -> {
				BGPEval.finalResultCalculation(HashJoin.NotJoinedTriples, HashJoin.EvaluatedTriples,
						HashJoin.JoinedTriples,0);

				
				//		}).join();
		//	fjp1.shutdown();
		//	ForkJoinPool fjp2 = new ForkJoinPool();

		//	fjp2.submit(() -> {
				BGPEval.finalResultCalculation(HashJoin.JoinedTriples, HashJoin.EvaluatedTriples,
						HashJoin.JoinedTriples,1);
		//	}).join();
//			fjp2.shutdown();

	//		ForkJoinPool fjp21 = new ForkJoinPool();

		//	fjp21.submit(() -> {
				BGPEval.finalResultCalculation(HashJoin.NotJoinedTriples, HashJoin.EvaluatedTriples,
						HashJoin.NotJoinedTriples,0);
		//	}).join();
		//	fjp21.shutdown();
		}
			//}).join();
			//fjp.shutdown();
				
				if(!ParaEng.Union.isEmpty())
		HashJoin.ProcessUnion();
		// for(Binding i:start.getBindings())
		//// System.out.println("This is out of TripleExectuion in BindJoin:");
		// }

		synchronized (end) {

			end.notifyAll();
		}
		// setInput(null);

		// start.removeEdge(edge);
		// end.removeEdge(edge);
		// }
		}else 
		{
			
			System.out.println("This is here in part222222222222222222222222222222222222222222222222222222222");

			int end1=0;
			List<String> Completed = new ArrayList<>();
						for(Entry<HashMap<String, String>,  String>  eq:ParaEng.FiltereqLarge.entrySet()) 
				for(Entry<String, String>  eq1:eq.getKey().entrySet())
			{
					
				//	System.out.println("This is the first of the firsts:"+eq.getKey()+"--"+eq.getValue());
					if(HashJoin.DisabledTriplesLarge.contains(eq.getValue()))
						continue;
					//for(String xy1:HashJoin.DisabledTriplesLarge)
					//{	
					//	System.out.println("This is DisabledTriplesLarge:"+eq.getValue()+"--"+xy1);
					//}
					
					
				//		if(eq.getValue().equals(xy1))
					//	{end1=1;
						//end1=0;
					//		break;}
					//		}

				//	if(end1==1)
				//	{end1=0;
				//		break;
				//	}
					
					List<EdgeOperator> BushyTreeTriple = QueryTask.BushyTreeTripleCreation(te.getTriple());
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
//					if(end1==1) {
//						end1=0;
//						
//						continue;
//					}
//					if(end1==1) {
//						end1=0;
//						continue;
//					}
					results=null;	
					
					String x1 = start.getNode().toString().replaceAll("[^A-Za-z]+", "");
					for(Entry<HashMap<Vertex, String>, Set<Binding>> sb21:BGPEval.StartBinding123Large.entrySet()) {
						System.out.println("This is comparison value:" + sb21.getKey()+"--"+sb21.getValue().size());
						
					}
					intermediate=new HashSet<>();
//					if(!Completed.contains(eq1.getKey())) {
					for (Entry<HashMap<Vertex, String>, Set<Binding>> e : BGPEval.StartBinding123Large.entrySet()) {
						for(Entry<Vertex, String> e2:e.getKey().entrySet()) {
						int y = x1.compareTo(e2.getKey().getNode().toString().replaceAll("[^A-Za-z]+", ""));
						int z = eq1.getKey().compareTo(e2.getValue());
						System.out.println("This is comparison value:" + x1+"--"+e2.getKey().getNode().toString().replaceAll("[^A-Za-z]+", "")+"--"+eq1.getKey()+"--"+e2.getValue()+"--"+y+"--"+z+"--"+ intermediate.size());
						//if (y == 0) {
			//if(x1.equals(e2.getKey().getNode().toString().replaceAll("[^A-Za-z]+", "")) && eq1.getKey().toString().equals(e2.getValue().toString())) {
				if(y==0 && z==0) {
					
						System.out.println("This is comparison value123:" + intermediate.size()+"--"+eq1.getKey());
						intermediate.addAll(BGPEval.StartBinding123Large.get(e.getKey()));
				//for (Binding e1 : e.getValue()) {
				//				intermediate.add((org.apache.jena.sparql.engine.binding.Binding) e1);

					//		}
							// System.out.println("These are
							// intermediate:"+intermediate.parallelStream().limit(2).collect(Collectors.toList()));
						}
		//	System.out.println("This is end 23 set Binding:" + x + "--"
	//				+ e2.getKey().getNode().toString().replaceAll("[^A-Za-z]+", "") + "--"
	//				+ e.getValue().stream().limit(3).collect(Collectors.toList()) + "--" + edge + "--" + edge);
		}
						}
					QueryTask.wfilter=0;
					//if(intermediate!=null) 
					for(Binding i1:intermediate)
					System.out.println("These are intermediate size:" + i1+"--"+LocalTime.now());
					if(intermediate.isEmpty()) {
						System.out.println("This is intermediate here null");
							intermediate=null;
						}
	
					System.out.println("This is weired now:"+eq1+"--"+eq.getValue());
					System.out.println("This is weired now1:"+te.getTriple()+"--"+eq.getValue());
					int k=0;
					/*if(ParaEng.InnerFilter!=null) {
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
					}*/
				/*	if(HashJoin.Completed890.size()>0 || !HashJoin.Completed890.isEmpty())
					{
					String skip=te.getTriple()+"--"+eq.getValue();
			for(String c:HashJoin.Completed890) {
				System.out.println("This is weired now1.5:"+c);	
			}
			if(!HashJoin.Completed890.contains(skip))
					results =te.exec(intermediate,eq1.getKey());
			
					
					else if (HashJoin.Completed890.contains(skip))
						continue;
					}
			else*/
				results =te.exec(intermediate,eq1.getKey());
					
					if(QueryTask.unExecuted==1 &&QueryTask.falsified==0 && ((results == null || results.size() == 0))) {
						QueryTask.unExecuted=0;
						continue;
					}
		//				}
		//	for (Binding r : results)

			if(results!=null || !results.isEmpty())
			System.out.println("These are size of result in BindJoin:" + results.size());

			//if(QueryTask.bounded==0)
				//HashJoin.Completed890.add(te.getTriple()+"--"+eq.getValue());
				
			QueryTask.notInclined=0;
			int br=0;
			if(QueryTask.falsified==1 && QueryTask.bounded==1)
			if ((results == null || results.size() == 0) && ParaEng.FiltereqLarge!=null  ) 	{
				int kcount=0;
				int kactual=0;
				
				for(Entry<HashMap<String, String>, String> fr:ParaEng.FiltereqLarge.entrySet())
				{
						kcount++;
						for(Entry<String, String> frkey:fr.getKey().entrySet())
							if(frkey.getValue()=="No")
								{
								kactual++;
								}
								
				}
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
				List< String> xy= new ArrayList<>();
				System.out.println("This is kcount vs kactual:"+kcount+"--"+kactual);
				
			for(Entry<HashMap<String, String>, String> fr:ParaEng.FiltereqLarge.entrySet())
			{
				if(fr.getValue().contains("||")) {
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
						
									QueryTask.wfilter=1;
									fr.getKey().replace(eq1.getKey(), "No");
									
						
								
					}
					}
				}
					else	if(fr.getValue().contains("&&")) {
						
								QueryTask.wfilter=1;
								fr.getKey().replace(eq1.getKey(), "No");
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
				for(String xy1: HashJoin.DisabledTriplesLarge)
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
									 System.out.println("This is a good thing finally5656565656 BindJoin:"+feq1.getKey());
										
							System.out.println("This is the nodeextraction:"+ne+"--"+feq1.getKey());
							
					if(feq1.getValue()==null && feq1.getKey().contains(ne) && feq1.getKey().equals(eq1.getKey())) {		
					System.out.println("This is the biggest problem now in BindJoin:"+feq);
					System.out.println("This is the biggest problem now11111 in BindJoin:"+feq1);
					
				//	HashMap<String,String> b = new HashMap<>();
					QueryTask.wfilter=1;
					feq.getKey().replace(feq1.getKey(), "Yes");
					System.out.println("This is a good thing finally2323232 BindJoin");

				//	br=1;
				//	break;
			//		b.put(feq1.getKey(), "No");
			//		ParaEng.Filtereq.put(b, feq.getValue());
				
					}}
				}
			}
			QueryTask.falsified=0;
			
				

				
		
				for(Entry<HashMap<Vertex, String>, Set<Binding>> as:BGPEval.StartBinding123Large.entrySet())
			System.out.println("This is the replacement of filterreq:"+as.getKey()+"--"+as.getValue().size());
		
			HashMap<Vertex,String>  a = new HashMap<>(); 
			a.put(start,eq.getValue()); 
			if(BindJoin.results.size()>0 || !BindJoin.results.isEmpty())
			if (BGPEval.StartBinding123Large.containsKey(a)) {
				
				System.out.println("----------------------------------------------------------------------------");
				
				Set<Binding> temp1 = new HashSet<>();
			HashMap<Vertex,String> v = new HashMap<>();
			for(Entry<HashMap<Vertex, String>, Set<Binding>> eq11:BGPEval.StartBinding123Large.entrySet())
			v.putAll(eq11.getKey());

			for(Entry<Vertex, String> as:v.entrySet())
				System.out.println("This is the replacement of filterreq2323232:"+as);
			
			
			for(Binding as:BindJoin.results)
				System.out.println("This is the replacement of result:"+as);
			//}
				Iterator<Var> l1=null;
			
			Var r=null;
			for (Entry<Vertex, String> v1 : v.entrySet()) {
				Iterator<Binding> l5=BindJoin.results.iterator();
				while(l5.hasNext()) {
				 
					l1 =l5.next().vars();
				}
				r = Var.alloc(v1.getKey().getNode());
				System.out.println("This is rule no. 1 in BindJoin:" + r);
				while (l1.hasNext()) {
					Var v2 = l1.next();
					System.out.println("This is l5.next():"+v2);
					if (r.equals(v2)) {
					System.out.println("This is rule no.3 in BindJoin:" + r + "--" + v);
						joinVars = v2;
						br = 1;
						break;
					}

				}
			
			
			
				if (br == 1) {
					br = 0;
					break;
				}
			}
			if (joinVars != null) {
				if( Var.alloc(start.getNode()).equals(r)) {
				temp1=new HashSet<>();
				System.out.println("This is rule no. 2 in BindJoin:" +  Var.alloc(start.getNode())+"--"+r);
				System.out.println("This is rule no. 2.1 in BindJoin:" + joinVars);

				for (Binding e1 : results) {
			if(e1.get(joinVars)!=null) {
		//	System.out.println("This is the final problem:"+e1.get(joinVars));
				temp1.add(BindingFactory.binding(joinVars, e1.get(joinVars)));
			}// for(int k=0;k<4;k++)

				
				}
			}	}
			
			
//	for(Binding t:temp1)
//System.out.println("This the replacement:"+t+"--"+temp1.size()+"--"+BGPEval.StartBinding123Large.get(a));
//	BGPEval.StartBinding123.remove(joinVars);//.replace(start, BGPEval.StartBinding123.get(start), temp1);
				BGPEval.StartBinding123Large.put(a, temp1);
//	for(Entry<Vertex, Set<Binding>> t:BGPEval.StartBinding123.entrySet())
			//	System.out.println("This the replacement:" + "--" + temp1.size() + "--"
			//			+ BGPEval.StartBinding123.get(start).size());
			
			}
			/*	results = te.exec(null, null);
				// for(Binding i1:results)
				// System.out.println("These are null result in bindjoin:"+i1);
			}*/
			/*
		//	if(BGPEval.Edgetype=="Norm") {
			for (List<EdgeOperator> e : BGPEval.JoinGroupsListExclusive) {
				for (EdgeOperator e1 : e)
					if (e1.getEdge().toString().equals(edge.toString())) {
						AllEdges1.addAll(e);
					}
			}

			for (EdgeOperator e : BGPEval.JoinGroupsListLeft) {
				//System.out.println("This is equality in JoinGroupsListLeft:"+e.getEdge().toString()+"--"+edge.toString());
				if (e.getEdge().toString().equals(edge.toString())) {
					AllEdges1.add(e);
				}
			}

			for (EdgeOperator e1 : BGPEval.JoinGroupsListRight) {

				if (e1.getEdge().toString().equals(edge.toString())) {
					AllEdges1.add(e1);
				}
			}//}
			//else {
			for(List<EdgeOperator> e:BGPEval.JoinGroupsListOptional) {
				for(EdgeOperator e1:e)
					if(e1.getEdge().equals(edge)) {
						AllEdges1.addAll(e);
					}
			}

			for(EdgeOperator e:BGPEval.JoinGroupsListLeftOptional) {
			
					if(e.getEdge().equals(edge)) {
						AllEdges1.add(e);
					}
			}
			
					for(EdgeOperator e1:BGPEval.JoinGroupsListRightOptional) {
						
						if(e1.getEdge().equals(edge)) {
							AllEdges1.add(e1);
						}
					}*/
		//	}
			
//			Iterator<Var> vff = BindJoin.results.iterator().next().vars();
//			Var vf = null;
//				while (vff.hasNext()) {
//					 vf = vff.next();

//				}
				
		System.out.println("THis is the vertex of this query:");	
					// Multimap<String, String> cars = ArrayListMultimap.create();
		//	if (!BGPEval.StartBinding123.containsKey(start)) {
			//	System.out.println("This key not present:"+BindJoin.AllEdges1);
				
			//}
				
			//	Iterator<Var> l = BindJoin.results.iterator().next().vars();
			//	Set<Var> v9=new HashSet<>();
			//		while (l.hasNext()) {
			//			Var v2 = l.next();
			//			v9.add(v2);
						
								
			//	}
				
					
					
				//	for(Var v1:v9)
				//		if(BGPEval.ProcessedVertexT.toString().contains(v1.toString()))
				//		{
					//		if(!Var.alloc(start.getNode()).toString().equals(v1.toString())) {
					//			Var va = Var.alloc(edge.getV1().getNode());
								
					//			Var va1 = Var.alloc(edge.getV2().getNode());
										
						//		for(Binding e1:results) {
									
						//		MultipleBinding.put(BindingFactory.binding(va1, e1.get(va1)),BindingFactory.binding(va, e1.get(va)));
								
							//	}
							
								

								System.out.println("----------------------------------------------------------------------------");
							//	for (Entry<Vertex, Set<Binding>> e : BGPEval.StartBinding123.entrySet()) {
							//		 System.out.println("These are intermediate3333332222222222222222:"+e.getKey()+"--"+e.getValue());
							//		}
							
							//	for(Entry<Binding, Binding> mb:MultipleBinding.entries())
							//		System.out.println("This is child1:"+mb);
//				}
							
//						}
					
		//			if(v9.contains(Var.alloc(start.getNode())))
			//			System.out.println("These are now being processed here in here");
		
							//if(results.size()==0)
						//		{
						//			System.out.println("This is the size of result if 0");
						//			break;
						//		}
						//		else {
								List<List<EdgeOperator>> JoinGroupsListLeftTemp= new ArrayList<>();
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListLeft);
			JoinGroupsListLeftTemp.add(BGPEval.JoinGroupsListRight);
			JoinGroupsListLeftTemp.addAll(BGPEval.JoinGroupsListExclusive);
		
			ForkJoinPool fjp = new ForkJoinPool();
			try {
				fjp.submit(() -> 
				HashJoin.IntermediateProcedureLarge(BindJoin.results, HashJoin.AllEdges, edge,eq1.getKey())).get();
			} catch (InterruptedException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			} catch (ExecutionException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
		//}
			fjp.shutdown();

			
			Edge CurrentEdgeOperator = new Edge(edge.getV1(), edge.getV2());// edge.getV1()+"--"+edge.getV2();
			Iterator<Entry<EdgeOperator, List<Binding>>> frIterator = BGPEval.finalResult.entrySet().iterator();
			while (frIterator.hasNext()) {
				total++;
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				// System.out.println("This is
				// finalResult:"+ab.getKey()+"--"+ab.getValue().size());

				if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
						.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
					BGPEval.finalResult.replace(ab.getKey(), results);
					// CurrentEdgeOperator=ab.getKey();
				}

			}

			for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultRight.entrySet()) {
				total++;
				// System.out.println("This is
				// finalResultRight:"+ab.getKey()+"--"+ab.getValue().size());

				if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
						.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
					BGPEval.finalResultRight.replace(ab.getKey(), results);
				}

				// CurrentEdgeOperator=ab.getKey();

			}
			for (Entry<EdgeOperator, List<Binding>> ab : BGPEval.finalResultLeft.entrySet()) {
				total++;

				if ((ab.getKey().getEdge().getV1() + "--" + ab.getKey().getEdge().getV2()).toString()
						.equals((edge.getV1() + "--" + edge.getV2()).toString())) {
					// System.out.println("This is
					// finalResultLeft:"+ab.getKey()+"--"+results.size());

					BGPEval.finalResultLeft.replace(ab.getKey(), results);
					// CurrentEdgeOperator=ab.getKey();

				}
			}
			//System.out.println("This is out of TripleExectuion in BindJoin000000000000:");

			// synchronized(BGPEval.finalResultOptional) {

			// for(Binding i:start.getBindings())
			//System.out.println("This is out of TripleExectuion in BindJoin:");
			for(List<EdgeOperator>  e2:JoinGroupsListLeftTemp) {
				
				for(EdgeOperator e3:e2)
					if(e3.getEdge().equals(CurrentEdgeOperator))
				{
					HashJoin.ProcessedEdgeOperatorsLarge.put(e2,eq.getValue());
				}
			}
			//// System.out.printlnln("This is now the new tree
			//// right:"+HashJoin.ProcessedEdgeOperators+"--"+CurrentEdgeOperator+"--"+total);
			int count = 0;
			frIterator = BGPEval.finalResult.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (Entry<List<EdgeOperator>, String> ee : HashJoin.ProcessedEdgeOperatorsLarge.entrySet())
					for (EdgeOperator ee1 : ee.getKey()) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final
												
								HashMap<List<EdgeOperator>, String> a1 = new HashMap<>();
								a1.put(CompleteEdgeOperator(ab.getKey()), eq.getValue());
								////// algo:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2:"+ee);
								HashJoin.ProcessedTriplesLarge.put(a1, ab.getValue());

								count++;
							}
					}
			}
			//// System.out.printlnln("This is now the new tree right count:"+count);
			count = 0;
			frIterator = BGPEval.finalResultLeft.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (Entry<List<EdgeOperator>, String> ee : HashJoin.ProcessedEdgeOperatorsLarge.entrySet())
					for (EdgeOperator ee1 : ee.getKey()) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final algo
															////// Rights:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2 Right:"+ee);
								// count++;
								List<EdgeOperator> l = new ArrayList<>();
								l.add(ab.getKey());
								HashMap<List<EdgeOperator>, String> a1 = new HashMap<>();
								a1.put(l, eq.getValue());
								HashJoin.ProcessedTriplesLarge.put(a1, ab.getValue());

							}
					}
			}
			////// System.out.printlnln("This is now the new tree right count:"+count);
			frIterator = BGPEval.finalResultRight.entrySet().iterator();
			while (frIterator.hasNext()) {
				Entry<EdgeOperator, List<Binding>> ab = frIterator.next();
				for (List<EdgeOperator> ee : HashJoin.ProcessedEdgeOperators)
					for (EdgeOperator ee1 : ee) {
						if (ab.getValue() != null)
							if (ee1.equals(ab.getKey())) { ////// System.out.printlnln("THis is coming to final algo
															////// Rights:"+ab.getKey()+"--"+ab.getValue().size());
								////// System.out.printlnln("THis is coming to final algo2 Right:"+ee);
								// count++;
								List<EdgeOperator> l = new ArrayList<>();
								l.add(ab.getKey());
								HashMap<List<EdgeOperator>, String> a1 = new HashMap<>();
								a1.put(l, eq.getValue());
								HashJoin.ProcessedTriplesLarge.put(a1, ab.getValue());

							}
					}
			}
					////// System.out.printlnln("This is now the new tree right count:"+count);
			// count=0;

			for (HashMap<List<EdgeOperator>, String> et : HashJoin.EvaluatedTriplesLarge) {
				HashJoin.ProcessedTriplesLarge.remove(et);
			}
			Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> ntjiterator = HashJoin.ProcessedTriplesLarge.entrySet().iterator();
			while(ntjiterator.hasNext()) {
				Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> ntj = ntjiterator.next();
			for(String dtl:HashJoin.DisabledTriplesLarge)
				if(ntj.getKey().toString().contains(dtl))
					ntjiterator.remove();
			}
			// for(Entry<List<EdgeOperator>, List<Binding>>
			// s:HashJoin.ProcessedTriples.entrySet())
			// System.out.println("This is
			// linkingTreeDup:"+s.getKey()+"--"+s.getValue().size());
			// for(Entry<List<EdgeOperator>, List<Binding>>
			// s:HashJoin.JoinedTriples.entrySet())
			// System.out.println("This is linkingTreeDup
			// JoinedTriples:"+s.getKey()+"--"+s.getValue().size());
			// for(Entry<List<EdgeOperator>, List<Binding>>
			// s:HashJoin.NotJoinedTriples.entrySet())
			// System.out.println("This is linkingTreeDup
			// NotJoinedTriples:"+s.getKey()+"--"+s.getValue().size());

			// System.out.println("These are the processed
			// triples:"+HashJoin.ProcessedTriples);
			// if (!!ParaEng.Union.isEmpty()) {
			
			HashJoin.ProcessingTaskLarge(HashJoin.JoinedTriplesLarge, HashJoin.ProcessedTriplesLarge, 0,0,eq.getValue());
			HashJoin.ProcessingTaskLarge(HashJoin.NotJoinedTriplesLarge, HashJoin.ProcessedTriplesLarge, 0,0,eq.getValue());
			for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> r:HashJoin.ProcessedTriplesLarge.entrySet())
				System.out.println("This is now the new processed triples:"+r.getKey()+"--"+r.getValue().size());

			// } else {
			// if (IsUnion == 1) {

			// HashJoin.ProcessingTask(HashJoin.JoinedTriples, HashJoin.ProcessedTriples,0);
			// HashJoin.ProcessingTask(HashJoin.NotJoinedTriples,
			// HashJoin.ProcessedTriples,0);
			// remaining++;
			// System.out.println("This is second
			// Union"+HashJoin.JoinedTriples.size()+"--"+HashJoin.ProcessedTriples.size());

			// }
			// }
			for(Entry<List<EdgeOperator>, List<Binding>>  jt:HashJoin.JoinedTriples.entrySet())
				System.out.println("This is the joinedtriples within the last123123213213:"+jt);
			
			for (HashMap<List<EdgeOperator>, String> et : HashJoin.EvaluatedTriplesLarge) {
				HashJoin.JoinedTriplesLarge.remove(et);
				HashJoin.NotJoinedTriplesLarge.remove(et);
			}
			Iterator<Entry<HashMap<List<EdgeOperator>, String>, List<Binding>>> ntjiterator1 = HashJoin.NotJoinedTriplesLarge.entrySet().iterator();
			while(ntjiterator1.hasNext()) {
				Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> ntj = ntjiterator1.next();
			for(String dtl:HashJoin.DisabledTriplesLarge)
				if(ntj.getKey().toString().contains(dtl))
					ntjiterator1.remove();
			}
			//DisabledTriplesLarge

		//	for (int i1 = 0; i1 < 6; i1++) {

				//	System.out.println("THis is NotJoinedTriples JoinedTriples bind:"+HashJoin.NotJoinedTriples.entrySet().parallelStream().limit(2).collect(Collectors.toList())+"--"+HashJoin.JoinedTriples.entrySet().parallelStream().limit(2).collect(Collectors.toList()));
				//
				//			ForkJoinPool fjp1 = new ForkJoinPool();
//				fjp1.submit(() -> {
			//		BGPEval.finalResultCalculation(HashJoin.NotJoinedTriples, HashJoin.EvaluatedTriples,
			//				HashJoin.JoinedTriples,0);
		//		}).join();
			//	fjp1.shutdown();
			//	ForkJoinPool fjp2 = new ForkJoinPool();

			//	fjp2.submit(() -> {
			//		BGPEval.finalResultCalculation(HashJoin.JoinedTriples, HashJoin.EvaluatedTriples,
			//				HashJoin.JoinedTriples,1);
			//	}).join();
//				fjp2.shutdown();

		//		ForkJoinPool fjp21 = new ForkJoinPool();

			//	fjp21.submit(() -> {
			//		BGPEval.finalResultCalculation(HashJoin.NotJoinedTriples, HashJoin.EvaluatedTriples,
			//				HashJoin.NotJoinedTriples,0);
			//	}).join();
			//	fjp21.shutdown();
			//}

					for(Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> jgl:HashJoin.JoinedTriplesLarge.entrySet())
					System.out.println("THis is NotJoinedTriples JoinedTriples:"+jgl);
					
					if(!ParaEng.Union.isEmpty())
			HashJoin.ProcessUnion();
			// for(Binding i:start.getBindings())
			//// System.out.println("This is out of TripleExectuion in BindJoin:");
			// }

			synchronized (end) {

				end.notifyAll();
			}
			// setInput(null);

			// start.removeEdge(edge);
			// end.removeEdge(edge);
			// }
			//if(end1==1)
		//	{end1=0;	
		//	break;}

			}
								}
			//			}
		}

	@Override
	public String toString() {
		return "Bind join: " + start + "--" + edge;
	}

	public List<EdgeOperator> CompleteEdgeOperator(EdgeOperator ct) {
		// Object jgl;
		for (List<EdgeOperator> jgl : BGPEval.JoinGroupsListExclusive) {
			if (jgl.contains(ct))
				return jgl;
		}
		for (List<EdgeOperator> jgl : BGPEval.JoinGroupsListOptional) {
			if (jgl.contains(ct))
				return jgl;
		}
		for (List<EdgeOperator> jgl : BGPEval.JoinGroupsListMinus) {
			if (jgl.contains(ct))
				return jgl;
		}

		
		return null;
	}

	public static String NodeExtraction1(String q,String fa) {
		String FilterString = null;
		int countQmark = 0;
		// if(ParaEng.opq==null)
		// return q.toString();
		// FilterString=ParaEng.Filter.toString();
		String queryString = null;
		
	
		
		String extension = null;
		String extension1 = null;

//		if (fa != " ")
			extension = fa;
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
		//	String[] v=		extension1.split(" ");
			//for(String ee:v) {	
				//System.out.println("This is the best of problems232.001:"+ee.replaceAll("\\s+",""));
				
		//	}
			
		//	for(String ee:v) {	
	//String e1=ee.replaceAll("\\s+","");
	System.out.println("This is the best of problems232:"+extension1+"--"+extension1);
	//if(extension1.length()==0 ||extension1.length()==1  || extension1.e("="))
	//	continue;
			if (extension1.toString().startsWith("(xsd") || 
					 extension1.toString().contains("=") ||	extension1.toString().contains("xsd") || extension1.toString().startsWith("xsd") || extension1.toString().startsWith("( xsd")
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
							return e;
					}
				}
					
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
			return e;
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
		return e;
				
					}
				
			
			}
			
			
			
								else if (extension1.toString().contains(",")  && (!extension1.toString().startsWith("?") 
										&&  !extension1.toString().contains("<")
										&&
										 !extension1.toString().contains("=") && !extension1.toString().contains(">") )
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
								return e;
									}
									
							
								
								}
								
								else if (!extension1.toString().contains(",")  && (!extension1.toString().startsWith("?") && 
										  !extension1.toString().contains("<") &&
										  !extension1.toString().contains("=") && !extension1.toString().contains(">"))||
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
										return e;
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
			
	
				
		return "not";
	}

	
	public static String NodeExtraction(List<String> string) {
		Map<HashMap<String, String>, String>  filter=new HashMap<>();
		
		if(ParaEng.Filtereq!=null)
		filter.putAll(ParaEng.Filtereq);	
		if(ParaEng.FiltereqLarge!=null)
			filter.putAll(ParaEng.FiltereqLarge);	
		for(Entry<HashMap<String, String>, String> fa:filter.entrySet()) {
			
			for(Entry<String, String> fa1:fa.getKey().entrySet()) {
		String extension1 = null;

//			extension1 = fa1.getKey().toString().substring(fa.toString().indexOf("FILTER")+8,
//					fa1.getKey().length());
		if(fa.toString().contains("FILTER ("))
			extension1 = fa1.getKey().toString().substring(fa.toString().indexOf("FILTER")+7,
					fa1.getKey().length());
			else if(fa.toString().contains("FILTER("))
				extension1 = fa1.getKey().toString().substring(fa.toString().indexOf("FILTER")+8,
						fa1.getKey().length());

		System.out.println("These are the character sequence in Clause0:"+extension1);

//			////System.out.printlnln("These are the character sequence in Clause0:"+q);
	//	System.out.println("These are the character sequence in Clause0.2:"+extension);

//		String[] query = string.toString().split(" ");
	//			////System.out.printlnln("This is the new query:"+queryString);

for(int j=0;j<string.size();j++)
			if (extension1.replace("\n", "").contains(string.get(j).replace("\n", ""))) {
				
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
if (extension1.toString().startsWith("(xsd") || 
	extension1.toString().contains("xsd") || extension1.toString().startsWith("xsd") || extension1.toString().startsWith("( xsd")
	|| 	extension1.toString().contains("LANG(") || extension1.toString().contains("DATATYPE(") 
	|| 	extension1.toString().contains("LANG (") || extension1.toString().contains("DATATYPE (")) {
String e ="";		
if(extension1.contains("="))
e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("=")).replaceAll("[^A-Za-z]+", "");
else if(extension1.contains(">"))
e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(">")).replaceAll("[^A-Za-z]+", "");
else if(extension1.contains("<"))
	e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("<")).replaceAll("[^A-Za-z]+", "");
System.out.println("This is digging deep0.01:"+extension1);

String qe[]=string.toString().split(" ");
List<String> b = new ArrayList<>();
for(String qq:qe)
	if(qq.startsWith("?"))
		b.add(qq.substring(1).replace("\n", "").replace(",", "").replace("]", ""));
for(String qe1:b)		
{
		System.out.println("This is digging deep:-)"+e+"--"+qe1);
	if(qe1.equals(e))//.substring(query[j].indexOf("?"),query[j].indexOf("=")).replaceAll("[^A-Za-z]+", "");
	{	return e.replace("\n","");
	}
}
	}
	//	breaking=1;
	//	break;

								
else if (extension1.toString().contains(" IN ") || extension1.toString().contains(" NOT IN ") ) {
String e="";
if(extension1.contains(" NOT "))
 e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("NOT")).replace(")", "").replace("(","").replace(" ", "");
else
	 e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf("IN")).replace(")", "").replace("(","").replace(" ", "");
	
String ji = string.get(j).replace("\n", "");
//	System.out.println("THis is here in extension111111:"+e+"--"+ji);

if (ji.equals(e)) {
//		System.out.println("THis is here in extension222222:"+e+"--"+ji);
	
	return e.replace("\n","");
}



}



else if (extension1.toString().contains("EXISTS") ||
	extension1.toString().contains("COALESCE")|| extension1.toString().contains("CONTAINS")|| extension1.toString().contains("NOT EXISTS") ) {
Set<String> clause=new HashSet<>();
for(String e:extension1.toString().split(" "))
{ 
//	System.out.println("This is the best of problems232:"+e);
	
	if(e.startsWith("?"))
{clause.add(e.replace(",", ""));
//			System.out.println("This is the best of problems:"+e);
}
}
	for(String e:clause)
	if (string.get(j).replace("\n", "").equals(e.replace("\n", ""))) {
//			System.out.println("THis is here in extension222222:"+e+"--"+query[j]);
	
	return e.replace("\n","");

	}


}

else if (!extension1.toString().contains(",") && (!extension1.toString().startsWith("?") && 
		
		  !extension1.toString().contains("<") && !extension1.toString().contains(">"))||
		extension1.toString().startsWith("ABS(") || extension1.toString().contains("ROUND(") ||
				extension1.toString().contains("CEIL(") ||extension1.toString().contains("FLOOR(")||
				extension1.toString().contains("BOUND(") ||
				extension1.toString().contains("YEAR(") || extension1.toString().contains("MONTH(")||
				extension1.toString().contains("DAY(") || extension1.toString().contains("HOURS(") || 
				extension1.toString().contains("MINUTES(") || extension1.toString().contains("SECONDS(") 
		|| extension1.toString().contains("STRLEN(") ||  extension1.toString().contains("UCASE(") ||
		extension1.toString().startsWith("isIRI(") || extension1.toString().startsWith("isBlank(") ||
		extension1.toString().startsWith("isLiteral(") || extension1.toString().startsWith("isNumeric(") ||
		 extension1.toString().startsWith("str(") ||
		extension1.toString().contains("LCASE(") ||
		extension1.toString().contains("STRLEN (") ||  extension1.toString().contains("UCASE (") ||
		extension1.toString().contains("LCASE (") ||
		extension1.toString().startsWith("ABS (") || extension1.toString().contains("ROUND (") ||
		extension1.toString().contains("BOUND (") ||
		extension1.toString().startsWith("isIRI (") || extension1.toString().startsWith("isBlank (") ||
		extension1.toString().startsWith("isLiteral (") || extension1.toString().startsWith("isNumeric (") ||
		 extension1.toString().startsWith("str (") ||
		extension1.toString().contains("CEIL (") ||extension1.toString().contains("FLOOR (")||
		extension1.toString().contains("YEAR (") || extension1.toString().contains("MONTH (")||
		extension1.toString().contains("DAY (")|| extension1.toString().contains("HOURS (") || 
		extension1.toString().contains("MINUTES (") || extension1.toString().contains("SECONDS (") 
		) {
	
	String e = extension1.toString().substring(extension1.toString().indexOf("?"),extension1.toString().indexOf(")")).replace(")", "").
			replace("(","").replace(" ", "");
	String ji = string.get(j).replace("\n", "").replaceAll(",", "");
	System.out.println("THis is here in extension12121212:"+e+"--"+ji);
	
	if (ji.equals(e)) {
	return e.replace("\n","");
	}
		}


				else if (extension1.toString().contains(",")  && (!extension1.toString().startsWith("?") 
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
					String ji = string.get(j).replace("\n", "");
					System.out.println("THis is here in extension12121212:"+e+"--"+ji);
					
					if (ji.equals(e)) {
						System.out.println("THis is here in extension222222:"+e+"--"+ji);
					return  e;
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
	
	}
}
//	}
//			if (breaking == 1) {
//				break;
//			}
//}
//}
// return queryString;


		}		
		System.out.println("This is a good thing finally");
		return "not";
	}
	
	protected static Query buildQuery(Triple triple, List<EdgeOperator> bushyTreeTriple) {
		////// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!QueryTask5!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		Query query = new Query();
		ElementGroup elg = new ElementGroup();
		// BGPEval.JoinGroupsList.stream().
		if (bushyTreeTriple != null) {
			// int ced=0;

			for (EdgeOperator btt : bushyTreeTriple) {
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
		return query;
	}

}
