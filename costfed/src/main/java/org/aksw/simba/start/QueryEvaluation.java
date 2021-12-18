package org.aksw.simba.start;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;

//import org.apache.log4j.LogManager;
import org.apache.commons.io.FileUtils;
//import org.apache.log4j.Level;
//import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;

import com.fluidops.fedx.Config;
import com.fluidops.fedx.FedXFactory;
import com.fluidops.fedx.structures.QueryInfo;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class QueryEvaluation {
	//private	static final org.apache.log4j.Logger log = LogManager.getLogger(QueryEvaluation.class);
/*	static {
		try {
			ClassLoader.getSystemClassLoader().loadClass("org.slf4j.LoggerFactory"). getMethod("getLogger", ClassLoader.getSystemClassLoader().loadClass("java.lang.String")).
			 invoke(null,"ROOT");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	*/
	QueryProvider qp;

	public QueryEvaluation() throws Exception {
		qp = new QueryProvider("query/");
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception 
	{
		
//		org.apache.log4j.Logger.getRootLogger().setLevel(Level.OFF);
//		Logger.getRootLogger().setLevel(Level.OFF);
//org.apache.log4j.Logger.getLogger("org.aksw.simba.start.QueryEvaluation").setLevel(Level.OFF);

		String cfgName = "costfed.props";
String	repfile =args[0];
		
		String host = "localhost";
		//String host = "w324348.avicomp.com";
		//String host = "192.168.0.145";
	String queries = repfile; //C3 C4 C6 C7 C9 C9 C10"; //"C1 C3 C5 C6 C7 C8 C9 C10 L1 L2 L3 L4 L5 L6 L7 L8";
		//String queries = "S1 S2 S3 S4 S5 S6 S7 S8 S9 S10 S11 S12 S13 S14 C1 C2 C3 C6 C7 C8 C9 C10";
		//String queries = "S1 S2 S3 S4 S5 S6 S7 S8 S9 S10 S11 S12 S13 S14 C1 C2 C3 C4 C6 C7 C8 C9 C10";
		//String queries = "CH3"; // S3 C6 C2
	List<String[]> qu = new ArrayList<String[]>();
	String[] q=new String[1];
	q[0]=repfile;
	
	System.out.println("This is first input:"+repfile+"--"+q[0]+"--"+LocalTime.now());
	qu.add(q);
	    
	//    if(file1.delete())
	    //{
	  //      System.out.println("File deleted successfully1");
	   // }
	 File file1 = new File("/mnt/hdd/hammad/hammad/Query.csv");
	    if(file1.delete())
	    {
	        System.out.println("File deleted successfully");
	    }

	 try (CSVWriter writer = new CSVWriter(new FileWriter("/mnt/hdd/hammad/hammad/Query.csv",true))) {
        writer.writeAll(qu);
     //   writer.close();
	   } catch (IOException e4) {
		// TODO Auto-generated catch block
		e4.printStackTrace();
	}
 
		List<String> endpointsMin = Arrays.asList(
			 "http://" + host + ":8890/sparql", 
			 "http://" + host + ":8891/sparql",
			 "http://" + host + ":8892/sparql",
			 "http://" + host + ":8893/sparql",
			 "http://" + host + ":8894/sparql",
		 	 "http://" + host + ":8895/sparql",
		 	 "http://" + host + ":8896/sparql",
 			 "http://" + host + ":8897/sparql",
 			 "http://" + host + ":8898/sparql"
 			 , "http://" + host + ":8889/sparql"
			 , "http://" + host + ":8888/sparql"
			 , "http://" + host + ":8887/sparql"

				);
	/*	
		List<String> endpointsMax = Arrays.asList(
			 "http://" + host + ":8890/sparql",
			 "http://" + host + ":8891/sparql",
			 "http://" + host + ":8892/sparql",
			 "http://" + host + ":8893/sparql",
			 "http://" + host + ":8894/sparql",
			 "http://" + host + ":8895/sparql",
			 "http://" + host + ":8896/sparql",
			 "http://" + host + ":8897/sparql",
		 	 "http://" + host + ":8898/sparql",
			 "http://" + host + ":8887/sparql",
			 "http://" + host + ":8888/sparql",
		     "http://" + host + ":8889/sparql",
			  "http://" + host + ":8899/sparql"
		);
*/
	/*	List<String> endpointsMax = Arrays.asList(
			 "http://" + host + ":8891/sparql",
			 "http://" + host + ":8897/sparql"
		);*/
		
		List<String> endpointsMax = Arrays.asList(
				 "http://" + host + ":8850/A/sparql",
				 "http://" + host + ":8851/Aff/sparql",
				 "http://" + host + ":8852/Chebi/sparql",
				 "http://" + host + ":8853/Db/sparql",
				 "http://" + host + ":8854/Drug/sparql",
				 "http://" + host + ":8855/E/sparql",
				 "http://" + host + ":8856/Geoname/sparql",
				 "http://" + host + ":8857/Jam/sparql",
			 	 "http://" + host + ":8858/Kegg/sparql",
				 "http://" + host + ":8859/LMDB/sparql",
				 "http://" + host + ":8860/M/sparql",
			     "http://" + host + ":8861/NYT/sparql",
				  "http://" + host + ":8862/SWD/sparql"
			);
		
		List<String> endpointsTest = Arrays.asList(
		 "http://" + host + ":8895/sparql"
				 , "http://" + host + ":8889/sparql"
				 , "http://" + host + ":8888/sparql"
				 , "http://" + host + ":8887/sparql"

				 , "http://" + host + ":8894/sparql"
			);
		
	
		List<String> endpointsMin2 = Arrays.asList(
		/*	 "http://" + host + ":8890/sparql",
			 "http://" + host + ":3032/dbPedia/sparql",
			 "http://" + host + ":3030/drugbank/sparql",
			 "http://" + host + ":3036/geoname/sparql",
			 "http://" + host + ":8894/sparql",
			 "http://" + host + ":8895/sparql",
			 "http://" + host + ":8896/sparql",
			 "http://" + host + ":8897/sparql",
			 "http://" + host + ":8898/sparql",
			 "http://" + host + ":8899/sparql",
			 "http://" + host + ":8887/sparql",
			 "http://" + host + ":3034/tgca-e/sparql",
			 "http://" + host + ":3031/tcga-a/sparql"*/
				 "http://" + host + ":8890/sparql",
				 "http://" + host + ":8891/sparql",
				 "http://" + host + ":8892/sparql",
				 "http://" + host + ":8893/sparql",
				 "http://" + host + ":8894/sparql",
				 "http://" + host + ":8895/sparql",
				 "http://" + host + ":8896/sparql",
				 "http://" + host + ":8897/sparql",
			 	 "http://" + host + ":8898/sparql",
				 "http://" + host + ":8887/sparql",
				 "http://" + host + ":8888/sparql",
			     "http://" + host + ":8889/sparql",
				  "http://" + host + ":8899/sparql"
				
				);

		List<String> endpointsSake = Arrays.asList(
		        "http://144.76.166.111:8900/sparql",
		        "http://144.76.166.111:8901/sparql"
		);
		
		List<String> endpoints = endpointsMin;
		
		
		Map<String, List<List<Object>>> reports = multyEvaluate(queries, 1, cfgName, endpointsMin);
	
		for (Map.Entry<String, List<List<Object>>> e : reports.entrySet())
		{
			List<List<Object>> report = e.getValue();
			String r = printReport(report);
		//	log.info(r);
			if (null != repfile) {
				FileUtils.write(new File(repfile + "-" + e.getKey() + ".csv"), r);
			}
		}

		System.exit(0);
	}
	
	public Map<String, List<List<Object>>> evaluate(String queries, String cfgName, List<String> endpoints) throws Exception {
		List<List<Object>> report = new ArrayList<List<Object>>();
		List<List<Object>> sstreport = new ArrayList<List<Object>>();
		Map<String, List<List<Object>>> result = new HashMap<String, List<List<Object>>>();
		result.put("report", report);
		result.put("sstreport", sstreport);
	//	System.out.println("This is here in QueryEvalution 0:"+LocalTime.now());
		
		List<String> qnames = Arrays.asList(queries.split(" "));
		for (String curQueryName : qnames)
		{
			List<Object> reportRow = new ArrayList<Object>();
			report.add(reportRow);
			String curQuery = qp.getQuery(curQueryName);
			reportRow.add(curQueryName);
			
			List<Object> sstReportRow = new ArrayList<Object>();
			sstreport.add(sstReportRow);
			sstReportRow.add(curQueryName);
			
			Config config = new Config(cfgName);
			SailRepository repo = null;
			TupleQueryResult res = null;
			String c1=null;
			//String limit=null;
			try {
			//	Pattern pattern = Pattern.compile("(?m)^LIMIT.*");
			//	Matcher matcher = pattern.matcher(curQuery);
			//	if (matcher.find())
			//	{
				//   limit=matcher.group(1);
				//}
				String limit=null;
				curQuery=curQuery.replaceAll("(?i)ORDER BY", "ORDER BY").
				replaceAll("(?i)LIMIT","LIMIT").
				replaceAll("(?i)DISTINCT","DISTINCT").
				replaceAll("(?i)REDUCED","REDUCED").
				replaceAll("(?i)OFFSET","OFFSET").
				replaceAll("(?i)MINUS","MINUS").
				replaceAll("(?i)UNION","UNION").
				replaceAll("(?i)OPTIONAL","OPTIONAL");
				if(curQuery.contains("ORDER BY"))
					limit =curQuery.substring(curQuery.indexOf("ORDER BY")) ;
				else if(curQuery.contains("LIMIT"))
					 limit =curQuery.substring(curQuery.indexOf("LIMIT")) ;
				System.out.println("This is currentQuery55345435:"+limit);
				curQuery = curQuery.replaceAll("(?m)^FILTER.*", "").replaceAll("(?m)^LIMIT.*", "").replaceAll("(?m)^ORDER BY.*", "");//.substring(startIndex + 1, finalIndex);
					
			//	if(curQuery.contains("optional") ) {
					//			System.out.println("This is currentQuery:"+curQuery);
					//			System.out.println("This is currentQuery213213123:"+	curQuery.substring(curQuery.indexOf("OPTIONAL")).replaceAll("\\}", "").replaceAll("\\{", "").replaceAll("OPTIONAL", ""));;
			//			c1=	curQuery.substring(0,curQuery.indexOf("optional")-1).concat(curQuery.substring(curQuery.indexOf("optional")).replaceAll("\\}", "").replaceAll("\\{", "").replaceAll("optional", ""));	
			//			String c9=curQuery.substring(curQuery.indexOf("optional")).replaceAll("\\}", "").replaceAll("\\{", "").replaceAll("optional", "").replaceAll("(?m)^[ \t]*\r?\n", "");				
						
			//			ArrayList<String> c10 = new ArrayList<>();
			//			for(String c8:c9.split("\n"))
			//				 c1+="optional { \n"+c8+"\n}";
					//		System.out.println("This is the final issuic problem:"+c1);
			//			curQuery=c1.replaceAll("(?m)^[ \t]*\r?\n", "").concat("\n }");
			//	}
			//	else if(curQuery.contains("OPTIONAL") ) {
	//			System.out.println("This is currentQuery:"+curQuery);
		//			String curQuery1=curQuery.substring(0,curQuery.indexOf("OPTIONAL")-1).trim();
					
	//	System.out.println("This is currentQuery213213123:"+curQuery1.charAt(curQuery1.length()-1));
		///if(curQuery1.charAt(curQuery1.length()-1)=='.')
	//		c1=	curQuery.substring(0,curQuery.indexOf("OPTIONAL")-1).trim().concat(curQuery.substring(curQuery.indexOf("OPTIONAL")).replaceAll("\\}", "").replaceAll("\\{", "").replaceAll("OPTIONAL", ""));	
	//	else
	//				c1=	curQuery.substring(0,curQuery.indexOf("OPTIONAL")-1).trim().concat(".").concat(curQuery.substring(curQuery.indexOf("OPTIONAL")).replaceAll("\\}", "").replaceAll("\\{", "").replaceAll("OPTIONAL", ""));	
		
		//else
		//	c1=	curQuery.substring(0,curQuery.indexOf("OPTIONAL")-2).concat(".").concat(curQuery.substring(curQuery.indexOf("OPTIONAL")).replaceAll("\\}", "").replaceAll("\\{", "").replaceAll("OPTIONAL", ""));	
				
		//String c9=curQuery.substring(curQuery.indexOf("OPTIONAL")).replaceAll("\\}", "").replaceAll("\\{", "").replaceAll("OPTIONAL", "").replaceAll("(?m)^[ \t]*\r?\n", "");				
		
		
		
	//	ArrayList<String> c10 = new ArrayList<>();
	//	for(String c8:c9.split("\n"))
	//		 c1+="OPTIONAL { \n"+c8+"\n}";
		System.out.println("This is the final issuic problem000000000:"+c1);
		
	//	curQuery=c1.replaceAll("(?m)^[ \t]*\r?\n", "").concat("\n }");
		System.out.println("This is the final issuic problem:"+curQuery);
			
		//		}
		
				
				String firstEle="";
				String ccc="";
				
				
				String a1=curQuery.substring(0,curQuery.indexOf("{"));
				//String a2=curQuery.substring(curQuery.lastIndexOf("}"));
				String a3=curQuery.substring(curQuery.indexOf("{"),curQuery.lastIndexOf("}")).replace("}", "").replace("{", "").replace("OPTIONAL", "");
				System.out.println("This is currentQue7777777777777:"+a1);
				//System.out.println("This is currentQue8888888888888:"+a2);
				System.out.println("This is currentQue9999999999999:"+a3);
				curQuery=	a1+" {"+"\n"+a3+"\n }";
				System.out.println("This is currentQue10101010110:"+curQuery);
			
				//curQuery=curQuery.concat(limit);
					for(String c:curQuery.split("\n")) {
					String[]	cc=c.split("\\s+");
					System.out.println("This is currentQue123213123123:"+c+"--"+cc.length);
						
					if(cc.length==3  && cc[cc.length-1].contains(";") && !c.contains("}") && !c.contains("{"))
							firstEle=cc[0];
						if(cc.length==3 && (c.contains(";")|| c.contains(".")) ) {
							ccc+=c.replace(";", ".").replaceAll("\\s+", " ").replaceAll("\\s+$","");
							if(ccc.charAt(ccc.length()-1)=='.')
							ccc=ccc.substring(0,ccc.length()-1)+" . \n";
							else 
								ccc+="\n";
						}
						else if(cc.length==2 && (c.contains(";")|| c.contains("."))) {
							ccc+=firstEle+" "+c.replace(";", " .").replaceAll("\\s+", " ").replaceAll("\\s+$","");
						if(ccc.charAt(ccc.length()-1)=='.')
							ccc=ccc.substring(0,ccc.length()-1)+" . \n";
						else 
							ccc+="\n";
						}//else if(!c.contains("http"))
						//	ccc+=c.replaceAll("\\s+", " ").replace("."," .");
						
							else if(c.contains("?") || c.contains(".") ) {
							ccc+=c.replaceAll("\\s+$","");
							if(ccc.charAt(ccc.length()-1)=='.')
							ccc=ccc.substring(0,ccc.length()-1)+" . \n";
							else 
								ccc+="\n";
							}
							else
								ccc+=c+"\n";
						
					}
					//if(!ccc.contains("SELECT"))
					curQuery=ccc;
					//else
					//	curQuery=ccc;
						
					curQuery=curQuery.concat("\n"+limit).replaceAll("null", "");
			repo = FedXFactory.initializeSparqlFederation(config, endpoints);
			//int startIndex = curQuery.indexOf("FILTER");
			//int finalIndex = curQuery.lastIndexOf(")");
			
			System.out.println("This is currentQuery:"+curQuery);
			TupleQuery query = repo.getConnection().prepareTupleQuery(QueryLanguage.SPARQL, curQuery);
				
			   	long startTime = System.currentTimeMillis();
			   	System.out.println("This is the starting time:"+LocalTime.now());
				
			   	String[] q=new String[1];
				List<String[]> qu = new ArrayList<String[]>();
				
				 q[0]="This is initial time:"+LocalTime.now();
					qu.add(q);
			
			 try (CSVWriter writer = new CSVWriter(new FileWriter("/mnt/hdd/hammad/hammad/Query.csv",true))) {
			        writer.writeAll(qu);
			     //   writer.close();
				   } catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
			 
					
						res = query.evaluate();
			   	long count = 0;
			
			    while (res.hasNext()) {
			    	BindingSet row = res.next();
			    	System.out.println(count+": "+ row);
			    	count++;
			    }
			  
			    long runTime = System.currentTimeMillis() - startTime;
			    reportRow.add((Long)count); reportRow.add((Long)runTime);
			    sstReportRow.add((Long)count);
			    sstReportRow.add(QueryInfo.queryInfo.get().numSources.longValue());
			    sstReportRow.add(QueryInfo.queryInfo.get().totalSources.longValue());
			    System.out.println(curQueryName + ": Query exection time (msec): "+ runTime + ", Total Number of Records: " + count + ", Source count: " + QueryInfo.queryInfo.get().numSources.longValue());
			    //log.info(curQueryName + ": Query exection time (msec): "+ runTime + ", Total Number of Records: " + count + ", Source Selection Time: " + QueryInfo.queryInfo.get().getSourceSelection().time);
			} catch (Throwable e) {
				e.printStackTrace();
				//System.out.error("", e);
				File f = new File("results/" + curQueryName + ".error.txt");
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(os);
				e.printStackTrace(ps);
				ps.flush();
				FileUtils.write(f, os.toString("UTF8"));
				reportRow.add(null); reportRow.add(null);
			} finally {
				if (null != res) {
		    		res.close();
		    	}
				
		    	if (null != repo) {
		    	    repo.shutDown();
		    	}
	        }
		}
		return result;
	}
	
	static Map<String, List<List<Object>>> multyEvaluate(String queries, int num, String cfgName, List<String> endpoints) throws Exception {
		QueryEvaluation qeval = new QueryEvaluation();

		Map<String, List<List<Object>>> result = null;
		for (int i = 0; i < num; ++i) {
			Map<String, List<List<Object>>> subReports = qeval.evaluate(queries, cfgName, endpoints);
			if (i == 0) {
				result = subReports;
			} else {
				//assert(report.size() == subReport.size());
				for (Map.Entry<String, List<List<Object>>> e : subReports.entrySet())
				{
					List<List<Object>> subReport = e.getValue();
					for (int j = 0; j < subReport.size(); ++j) {
						List<Object> subRow = subReport.get(j);
						List<Object> row = result.get(e.getKey()).get(j);
						row.add(subRow.get(2));
					}
				}
			}
		}
		
		return result;
	}
	
	static String printReport(List<List<Object>> report) {
		if (report.isEmpty()) return "";
		
		StringBuilder sb = new StringBuilder();
		sb.append("Query,#Results");
		
		List<Object> firstRow = report.get(0);
		for (int i = 2; i < firstRow.size(); ++i) {
			sb.append(",Sample #").append(i - 2);
		}
		sb.append("\n");
		for (List<Object> row : report) {
			for (int c = 0; c < row.size(); ++c) {
				sb.append(row.get(c));
				if (c != row.size() - 1) {
					sb.append(",");
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}