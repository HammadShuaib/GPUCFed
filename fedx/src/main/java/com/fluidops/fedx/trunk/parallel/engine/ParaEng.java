



package com.fluidops.fedx.trunk.parallel.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Pattern;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.io.FileUtils;

import com.fluidops.fedx.trunk.description.Statistics;
import com.fluidops.fedx.trunk.graph.Vertex;
import com.fluidops.fedx.trunk.parallel.engine.main.StageGen;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

//import org.apache.jena.graph.query.Element;
import org.apache.jena.query.Query;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.algebra.Op;
import org.apache.jena.sparql.algebra.OpAsQuery;
import org.apache.jena.sparql.algebra.OpLib;
import org.apache.jena.sparql.algebra.op.Op0;
import org.apache.jena.sparql.algebra.op.Op1;
import org.apache.jena.sparql.algebra.op.Op2;
import org.apache.jena.sparql.algebra.op.OpFilter;
import org.apache.jena.sparql.algebra.op.OpTopN;
import org.apache.jena.sparql.algebra.op.OpUnion;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.engine.ExecutionContext;
import org.apache.jena.sparql.engine.Plan;
import org.apache.jena.sparql.engine.QueryEngineFactory;
import org.apache.jena.sparql.engine.QueryEngineRegistry;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.iterator.QueryIterRoot;
import org.apache.jena.sparql.engine.main.QC;
import org.apache.jena.sparql.engine.main.QueryEngineMain;
import org.apache.jena.sparql.engine.main.StageBuilder;
import org.apache.jena.sparql.engine.main.StageGenerator;
import org.apache.jena.sparql.expr.Expr;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.util.Context;
import org.apache.jena.sparql.util.Symbol;
import org.eclipse.rdf4j.query.algebra.StatementPattern;

import com.opencsv.CSVReader;

public class ParaEng extends QueryEngineMain {
public static	String opq=null;
public static	String Optional= " ";
public static String Union=" ";
public static String Select = " ";
public static String Distinct = " ";

public static List<String> Projection = new ArrayList<>();
public static LinkedHashMap<String,Integer> OptionalOrder = new LinkedHashMap<>();
public static LinkedHashMap<String,Integer> UnionOrder = new LinkedHashMap<>();

public static String Filter=" ";
public static HashMap<HashMap<String,List<Binding>>,HashMap<String,List<Binding>>> InnerFilter=new HashMap<>();
public static HashMap<String,String> InnerFilterSimple=new HashMap<>();

public static String Minus=" ";
public static HashMap<String,String> pConstant= new HashMap<>();
public static HashSet<String> FilterArray=new HashSet<>();
public static Map<HashMap<String,String>,String> Filtereq= new HashMap<>();
public static Map<HashMap<String,String>,String> FiltereqLarge= new HashMap<>();
public static  String curQuery=null;

public ParaEng(Query query, DatasetGraph dataset, Binding input, Context context) {
		super(query, dataset, input, context);
	}

	static private QueryEngineFactory factory = new ParaEngineFactory();
	public static int IsUNION;
	
	static public void register() {
	//	System.out.println(
	//			"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44"
	//					+ "ParaEng%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5555555555"
	//					+ "77777777777777777777777777777777777777777777777777777777777");

		QueryEngineRegistry.addFactory(factory);
	}

	static public void unregister() {
		QueryEngineRegistry.removeFactory(factory);
	}

	static public QueryEngineFactory getFactory() {
		return factory;
	}

	@Override
	public QueryIterator eval(Op op, DatasetGraph dsg, Binding input, Context context) {
		//System.out.println(
		//		"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44"
		//				+ "ParaEng%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%5555555555"
		//				+ "55555555555555555555555555555555555555555555555555555555555555555");
		Vertex.resetVertexPool();
		Symbol property = Symbol.create("config");

		Statistics config = (Statistics) context.get(property);
		StageGenerator sg;
		
		System.out.println("This is context variable:"+config.getServiceRegistry()+"--"+config);
		sg = new StageGen(config);
		StageBuilder.setGenerator(context,  sg);
		ExecutionContext execCxt = new ExecutionContext(context, dsg.getDefaultGraph(), dsg, QC.getFactory(context));
		QueryIterator qIter1 = QueryIterRoot.create(input, execCxt);
		readFromFile();
		//System.out.println("This is now ParaEng correction:"+OpLib.isProject( op)+"--"+OpLib.isReduced( op)+"--"+OpLib.isSlice( op)+"--"+qIter1+"--"+execCxt);
	//if()
	//	OpAsQuery.asQuery(Op )
	//if(!Optional.toString().contains("OPTIONAL") ) {
	//	Op opu = OpUnion.create(op, op);
	//	Query query = OpAsQuery.asQuery(op);
		;
//		OpCondition;
	//	opq=OpAsQuery.asQuery(OpFilter.ensureFilter(op)).toString();
	//	opq=OpAsQuery.asQuery(op).toString();
		
//	}
//	else opq=" ";
		
		
	
	//OpFilter ops = OpFilter.filter(op);
	System.out.println("This is the sub op:"+opq);
		QueryIterator qIter = QC.execute(op, qIter1, execCxt);


return qIter;
	}

	public static void readFromFile() {
	       StringBuilder sb = new StringBuilder();
	        String strLine = "";
	       List<String> list = new ArrayList<String>();
	       List<String> record = new ArrayList<String>();

	       /*
	            try (CSVReader csvReader = new CSVReader(new FileReader("/mnt/hdd/hammad/hammad/Query.csv"))) {
	                String[] values = null;
	                try {
	    				while ((values = csvReader.readNext()) != null) {
	    					 // records.add(Arrays.asList(values));
	    					record = Arrays.asList(values);
	    					System.out.println("This is the list:"+list);
	    					break;
	    				}
	    			} catch (CsvValidationException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	            } catch (FileNotFoundException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		} catch (IOException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}*/
				System.out.println("This is the list22:"+record);

	            String fileName=record.toString().replace("[", "").replace("]","").replace("$", "?");
				System.out.println("This is the list33:"+fileName);

	            String query="/mnt/hdd/hammad/hammad/QueryFile.csv";
	            System.out.println("This is query in ParaEng:"+query);
//	             BufferedReader br = new BufferedReader(new FileReader(query));
	//             System.out.println("This is really good now:"+br); 


	            
	            int j=-1;
	            String ccc="";
            /*
	             try (Scanner s = new Scanner(new FileReader(query))) {
	            	    while (s.hasNext()) {
	            	        list.add(s.nextLine());
	            	    }
	            	} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
	//System.out.println("This is the file that is read:"+Arrays.toString(list.toArray()));     
//int i=-1;
// j=-1;
//ccc="";


//for(String cc:curQuery.split("\n"))
/*try (BufferedReader br = new BufferedReader(new FileReader(query))) {
	    String line;
	    while ((line = br.readLine()) != null) {
for(String cc:curQuery.split("\n"))
	{
		
	if(line.contains("OPTIONAL"))
	{
		j++;

//		System.out.println("This is size of currentQuerrrrrrrrrr:"+j);
}
if(j>=0) {
//	System.out.println("111111111This is size of currentQuerrrrrrrrrr:"+j);
	System.out.println("This is currentQuerrrrrrrrrr:"+cc);

	 if(!line.contains("{") && !line.contains("}") && !line.contains("OPTIONAL")   
			  )
		 if( line.contains(".")) {
		Optional = Optional+line.concat("\n").replace("$", "?");
		  OptionalOrder.put(line.concat("\n").replace("$", "?"),j);
	
		 }
			System.out.println("This is the complete array:"+cc);
	 
	  }

if(line.toString().contains("UNION"))
	Union = line.substring(line.indexOf("UNION")).replace("$", "?");
if(line.contains("UNION"))
i++;
if(i>=0)
if(!line.contains("{") && !line.contains("}") && !line.contains("UNION") )
{
	
	  UnionOrder.put(line.replace("$", "?"),i);

}


}
	    }
	} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}*/

try (Scanner s = new Scanner(new FileReader(query))) {
    while (s.hasNext()) {
        list.add(s.nextLine());
    }
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
//System.out.println("This is the file that is read:"+Arrays.toString(list.toArray()));     
int i=-1;
int j1=-1;
try (BufferedReader br = new BufferedReader(new FileReader(query))) {
 String line;
 while ((line = br.readLine()) != null) {
  
	 System.out.println("This is the file that is read:"+line);     
	 	
 	//if(Arrays.toString(list.toArray()).toString().contains("OPTIONAL"))
 	//	Optional = Arrays.toString(list.toArray()).substring(Arrays.toString(list.toArray()).indexOf("OPTIONAL")).replace("$", "?");
 	if(line.contains("OPTIONAL"))
 	   j1++;
  if(j1>=0)
 	  if(!line.contains("{") && !line.contains("}") && !line.contains("OPTIONAL") )
 	  {
 	 if( line.contains(".")) {
 		Optional = Optional+line.concat("\n").replace("$", "?");
 		  OptionalOrder.put(line.concat("\n").replace("$", "?"),j1);
 	 }
 	  }
  
 //if(line.contains("UNION"))
		
	if(line.contains("UNION"))
	   i++;
if(i>=0)
	  if(!line.contains("{") && !line.contains("}") && !line.contains("UNION") && line.contains("."))
	  {UnionOrder.put(line.replace("$", "?"),i);
	  Union =Union+line.substring(line.indexOf("UNION")).replace("$", "?");
	  }
 

 }
} catch (FileNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
	             
	             UnionOrder.remove(" ");
	             UnionOrder.remove("");
	             
	             
	             
	             
	             for(String l:list) {	           
	

	//	if(l.toString().contains("OPTIONAL"))
	//	{	Optional = l.substring(l.indexOf("OPTIONAL"));
	// 	System.out.println("This is the complete array:"+l.substring(l.indexOf("OPTIONAL")));

		//}
if(l.toString().contains("SELECT"))
	Select = l.substring(l.indexOf("SELECT"))+" ";
if(l.toString().contains("SELECT"))
	if(l.toString().contains("DISTINCT"))
	Distinct = "Yes";


if(l.toString().contains("FILTER"))
{
	
//	l=l.substring(l.replace("FILTER(", ""));
	//l=l.substring(l.replace("FILTER (", ""));
int start=l.indexOf("(");
	int last=l.lastIndexOf(")");
	//System.out.println("This is ml filter:"+l);
	
	l=l.substring(start+1, last);
//	System.out.println();
	System.out.println("This is ml filter:"+l);

	if(l.contains("&&") &&  !l.contains("||")) {
		Pattern regex = Pattern.compile("&&");
		String[] regexMatcher = regex.split(l);
		
		// synchronized(regexMatcher) {
		for (String ml : regexMatcher) {
		HashMap<String,String> a= new HashMap<>();
		
		System.out.println("This is ml:"+ml);
			ml="FILTER ("+ml+")";
			a.put(ml,null);
			Filtereq.put(a, l);
		}
		
	}

	else	if(l.contains("||") && !l.contains("&&")) {
		Pattern regex = Pattern.compile("[||]+");
		String[] regexMatcher = regex.split(l);
		
		// synchronized(regexMatcher) {
		for (String ml : regexMatcher) {
		HashMap<String,String> a= new HashMap<>();
		
		System.out.println("This is ml:"+ml);
			ml="FILTER ("+ml+")";
			a.put(ml,null);
			Filtereq.put(a, l);
		}
		
	}
	else	if(l.contains("||") && l.contains("&&")) {
		Pattern regex = Pattern.compile("[||]+");
		String[] regexMatcher = regex.split(l);
		for (String ml : regexMatcher) {
			System.out.println("This is the minor issue:"+ml);}
		
		// synchronized(regexMatcher) {
		for (String ml : regexMatcher) {
		HashMap<String,String> a= new HashMap<>();
		System.out.println("This is the major issue:"+ml);
		Pattern regex1 = Pattern.compile("&&");
		String[] regexMatcher1 = regex1.split(ml);
		
		for(String ml1:regexMatcher1) {
			System.out.println("This is ml:"+ml1);
			ml1="FILTER ("+ml1+")";
			a.put(ml1,null);
			HashMap<String, String> a1 = new HashMap<>();
			a1.put(ml, null);
		if(!FiltereqLarge.containsKey(a))
			FiltereqLarge.put(a, ml);
		}
		
		}
	
	}
	else {
	
		
			
			// synchronized(regexMatcher) {
			HashMap<String,String> a= new HashMap<>();
					
			String	ml="FILTER ("+l+")";
				a.put(ml,null);
				Filtereq.put(a, l);
			
				System.out.println("This is single filter:"+Filtereq);
	}
//	if(l.charAt( )

	if(l.contains("=")) {
	String l2=l.replace(" ", "");
	int lleft=l2.indexOf("=");

	String a=l2.substring(0,lleft).replace(" ","");
	String b=l2.substring(lleft+1,l2.length()).replace(" ","");
System.out.println("This is now Inner Join:"+a+"--"+b);
	
if(a.contains("?")==true && b.contains("?")==true && l.contains("\"")==false)
{
	HashMap<String,List<Binding>> c = new HashMap<>();
	HashMap<String,List<Binding>> d = new HashMap<>();
	
	c.put(a, null);
	d.put(b, null);
		InnerFilter.put(c,d);
		InnerFilterSimple.put(a, b);
}
	}
//	System.out.println("This is single filter Large:"+FiltereqLarge);
	HashMap<HashMap<String, String>, String>  freq=new HashMap<>();
for(Entry<HashMap<String, String>, String> fl:FiltereqLarge.entrySet())
	if(!freq.toString().contains(fl.toString()) )
	freq.put(fl.getKey(),fl.getValue());
FiltereqLarge.clear();
FiltereqLarge.putAll(freq);
	
	/*if(l.contains("&&") || l.contains("||"))
	{
		
		Pattern regex = Pattern.compile("&&|[||]+");
		String[] regexMatcher = regex.split(l);
		
		// synchronized(regexMatcher) {
		for (String ml : regexMatcher) {
			ml="FILTER ("+ml+")";
			FilterArray.add(ml);
		}
	}
		else
	Filter = l.substring(l.indexOf("FILTER"));
*/
		

}

System.out.println("This is a good thing:"+FilterArray);
if(l.toString().contains("MINUS"))
	Minus = l.substring(l.indexOf("MINUS"));

}
	             
	             System.out.println("This is single filter:"+Filtereq);

	             System.out.println("This is single filter large:"+FiltereqLarge);

	             System.out.println("This is inner filter:"+InnerFilter);

	             System.out.println("This is the filter requirement:"+Filtereq);
	             System.out.println("This is now the optional:"+Optional);
	             
	             System.out.println("This is now the optional order:"+OptionalOrder+"--"+i);
	             System.out.println("This is now the final line:"+Union);
	             
	             System.out.println("This is now the line with index:"+UnionOrder+"--"+j1);

	             
if(!Select.contains("*"))
	for(String str:Select.split(" "))
		Projection.add(str.replace("$", "?"));
//System.out.println("This is the filter value:"+Filter);

//	else
//		pConstant.put(null, null);
	

//if(l.toString().contains("?p"))
//	pConstant = l.substring(l.indexOf("?p ")-10,l.length());
//else pConstant=" ";

//System.out.println("This is the file that is read:"+Optional);     	
	
	       }
	
	public static int occurence(String[] str,String chr) {
		int count=0;
		  for(int i=0; i<str.length; i++)
		  {  
		      if(str[i].contains(chr))
		      {count++;
		      }
		  }
		 return count; 
	}
	
}

class ParaEngineFactory implements QueryEngineFactory {

	public boolean accept(Query query, DatasetGraph dataset, Context context) {
		return true;
	}

	public Plan create(Query query, DatasetGraph dataset, Binding initial, Context context) {
		ParaEng engine = new ParaEng(query, dataset, initial, context);
		return engine.getPlan();
	}

	public boolean accept(Op op, DatasetGraph dataset, Context context) { // Refuse to accept algebra expressions
																			// directly.
		return false;
	}

	public Plan create(Op op, DatasetGraph dataset, Binding inputBinding, Context context) { // Shodul notbe called
																								// because acceept/Op is
																								// false
		throw new ARQInternalErrorException("QueryEngine: factory called directly with an algebra expression");
	}
	
	
}
