package com.fluidops.fedx.trunk.parallel.engine.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import org.apache.jena.sparql.engine.binding.BindingComparator;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.jena.atlas.io.IndentedWriter;
import org.apache.jena.graph.Node;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.sparql.core.Var;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.engine.binding.BindingFactory;
import org.apache.jena.sparql.engine.binding.BindingHashMap;
import org.apache.jena.sparql.engine.iterator.QueryIteratorBase;
import org.apache.jena.sparql.serializer.SerializationContext;
//import org.apache.log4j.Level;
//import org.apache.log4j.LogManager;

import com.fluidops.fedx.optimizer.Optimizer;
import com.fluidops.fedx.structures.Endpoint;
import com.fluidops.fedx.trunk.config.Config;
import com.fluidops.fedx.trunk.graph.Edge;
import com.fluidops.fedx.trunk.graph.SimpleGraph;
import com.fluidops.fedx.trunk.graph.Vertex;
import com.fluidops.fedx.trunk.parallel.engine.ParaEng;
import com.fluidops.fedx.trunk.parallel.engine.exec.QueryTask;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.BindJoin;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator;
import com.fluidops.fedx.trunk.parallel.engine.exec.operator.HashJoin;
import com.fluidops.fedx.trunk.parallel.engine.opt.ExhOptimiser;
//import com.fluidops.fedx.trunk.parallel.engine.opt.ExhOptimiser;
import com.fluidops.fedx.trunk.parallel.engine.opt.Optimiser;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import py4j.Gateway;
import py4j.GatewayServer;
import py4j.Protocol;
import py4j.examples.IHello;
import py4j.reflection.ReflectionUtil;
import py4j.reflection.RootClassLoadingStrategy;

public class BGPEval extends QueryIteratorBase {
//	private	static final com.hp.hpl.log4j.Logger logger = LogManager.getLogger(TripleExecution.class.getName());
	static ResultSet v = null;
	static int ij1 = 0;
	static int type1;
	static List<File> fList = new ArrayList<>();
	static int ltsize = 0;
	public static  Set<String> bindEstimator = new HashSet<>();
	static List<EdgeOperator> Done = new ArrayList<>();
	public static HashMap<EdgeOperator,EdgeOperator> urim = new HashMap<>();
public	static HashMap<Vertex,List<Binding>> urik = new HashMap<>();
public	static Set<EdgeOperator> urik_element = new HashSet<>();
//static LinkedHashMap<EdgeOperator, List<Binding>>  delayedExecution = new LinkedHashMap<>();
static ArrayList<String> temp999 = new ArrayList<>();
	static List<List<String>> lt = new ArrayList<>();
	static List<ResultSet> resultSetList = new ArrayList<>();
	static List<List<String>> rowsLeft = new ArrayList<>();
	public static Set<Var> ProcessedVertexT = new HashSet<>();
	public static List<Edge> DoubleStartBinding=new ArrayList<>();
	public static Multimap<Edge, String> DoubleStartBindingLarge = ArrayListMultimap.create();
	String baaa=null;
	
public static	List<EdgeOperator> OptionalAll = new ArrayList<>();
static EdgeOperator eo1;
static EdgeOperator eo2;
	static String[][] finalTable = null;
	public static String Edgetype;
	static InputStream targetStream = null;
	static ArrayList<Binding> resultoutput = new ArrayList<>();
	public static int ExclusivelyExclusive = 0;
	public static ConcurrentHashMap<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> StartBinding = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> StartBindingBJ = new ConcurrentHashMap<>();
	public static HashMap<Multimap<Edge, Vertex>, ArrayList<Binding>> StartBindingFinal =   new HashMap<>();//ArrayListMultimap.create();
	public static HashMap<HashMap<HashMap<Edge, Vertex>,String>, ArrayList<Binding>> StartBindingFinalLarge =  new HashMap<>();

	static	LinkedListMultimap<Edge, Vertex>	StartBindingFinalKey = LinkedListMultimap.create();
	public static Map<EdgeOperator, Integer> joinGroupUnion = new HashMap<>();
	public static Map<EdgeOperator, Integer> joinGroupMinus = new HashMap<>();

	public static Map<String, String> HeaderReplacement = new HashMap<>();
	int o = 0;
	static int e = 0;
	static List<List<String>> rightTable = null;
	static List<List<String>> leftTable = null;
	static List<ArrayList<String>> print;
	 public static HashMap<Integer,Integer> vat = new HashMap<>();

	public static HashSet<String> headersAll = new HashSet<>();
	public static List<List<String>> OptionalHeadersRight = new ArrayList<>();
	public static List<List<String>> OptionalHeadersLeft = new ArrayList<>();
	public static List<List<String>> OptionalHeaders = new ArrayList<>();
	public static List<String> MinusHeadersRight = new ArrayList<>();
	public static List<String> MinusHeadersLeft = new ArrayList<>();
	public static List<String> MinusHeaders = new ArrayList<>();

	
	public static Map<EdgeOperator, Integer> TreeType = new HashMap<>();
	public static Multimap<List<EdgeOperator>, Integer> joinGroups2 = ArrayListMultimap.create();;
	public static LinkedListMultimap<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator, Integer> joinGroupsLeft = LinkedListMultimap
			.create();
	public static LinkedListMultimap<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator, Integer> joinGroupsRight = LinkedListMultimap
			.create();
	public static Multimap<List<EdgeOperator>, Integer> joinGroupsOptional1 = ArrayListMultimap.create();;
	public static LinkedListMultimap<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator, Integer> joinGroupsLeftOptional = LinkedListMultimap
			.create();
	public static LinkedListMultimap<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator, Integer> joinGroupsRightOptional = LinkedListMultimap
			.create();
	public static Multimap<List<EdgeOperator>, Integer> joinGroupsMinus1 = ArrayListMultimap.create();;
	public static LinkedListMultimap<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator, Integer> joinGroupsLeftMinus = LinkedListMultimap
			.create();
	public static LinkedListMultimap<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator, Integer> joinGroupsRightMinus = LinkedListMultimap
			.create();

	public static LinkedListMultimap<EdgeOperator, String> TreeOrder = LinkedListMultimap.create();
	public static ResultSet v1;

	public static List<List<EdgeOperator>> JoinGroupsList = new Vector<>();
	public static List<List<EdgeOperator>> JoinGroupsListExclusive = new Vector<>();
	public static List<EdgeOperator> JoinGroupsListLeft = new Vector<>();
	public static List<EdgeOperator> JoinGroupsListRight = new Vector<>();
	public static List<EdgeOperator> JoinGroupsListLeftOptional = new Vector<>();
	public static List<EdgeOperator> JoinGroupsListRightOptional = new Vector<>();
	public static List<EdgeOperator> JoinGroupsListLeftMinus = new Vector<>();
	public static List<EdgeOperator> JoinGroupsListRightMinus = new Vector<>();
	
	public static ConcurrentHashMap<EdgeOperator, HashSet<Integer>> newFormation = new ConcurrentHashMap<>();
	public static HashSet<List<EdgeOperator>> JoinGroupsListAll = new HashSet<>();

	static int completionE = 0;
	public static int finalResultSize = 0;
	static int mo = 0;
	public static List<List<EdgeOperator>> JoinGroupsListOptional = new Vector<>();
	public static List<List<EdgeOperator>> JoinGroupsListMinus = new Vector<>();

	public static List<List<Binding>> UnProcessedSets = new Vector<>();
	public int completion = 0;
	public static ConcurrentHashMap<EdgeOperator, Map<EdgeOperator, Integer>> exchangeOfElements = new ConcurrentHashMap<>();
	public static LinkedHashMap<EdgeOperator, List<EdgeOperator>> orderingElements = new LinkedHashMap<>();
	public static LinkedHashMap<Integer, HashSet<List<EdgeOperator>>> linkingTree = new LinkedHashMap<>();
	public static LinkedHashMap<HashSet<List<EdgeOperator>>, Integer> linkingTreeDup = new LinkedHashMap<>();
	public static int possibleOuter;
	public static ArrayList<ArrayList<Integer>> Treelowestlevel = new ArrayList<ArrayList<Integer>>();
	public static ArrayList<Integer> TreelowestlevelProcessed = new ArrayList<Integer>();
	static int UnprocessedSetRepNo = 0;
	public static List<EdgeOperator> operators_BushyTreeOrder = new Vector<>();// =optBT.nextStage();
	public static List<EdgeOperator> operators_BushyTreeOrder1 = new Vector<>();// =optBT.nextStage();

	public static HashMap<Vertex, LinkedHashSet<Binding>> StartBinding123 = new HashMap<>();
	public static HashMap<HashMap<Vertex,String>, Set<Binding>> StartBinding123Large = new HashMap<>();

	public static List<Set<Binding>> ProcessedSets = new ArrayList<Set<Binding>>();
	public static Set<Vertex> PublicHashVertex = new HashSet<>();

	static List<EdgeOperator> oiSubList = new Vector<>();
	static List<EdgeOperator> ProcessedTriples = new Vector<>();
//	static List<Vertex> ProcessedIndependentSubjects = new Vector<>();

	public static ConcurrentHashMap<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> StartBindingSet = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> StartBindingSetBJ = new ConcurrentHashMap<>();
	public int BushyTreeSize;
	private SimpleGraph g;
	//public static HashMap<Vertex,LinkedHashSet<Binding>> urim = new HashMap<>();
	
	// public static Map<EdgeOperator,Set<Binding>>
	// finalResult=Collections.synchronizedMap(new LinkedHashMap<>());
	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResult = new LinkedHashMap<>();
	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultFinal = new LinkedHashMap<>();

	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultRight = new LinkedHashMap<>();
	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultLeft = new LinkedHashMap<>();

	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultRightOptional = new LinkedHashMap<>();
	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultLeftOptional = new LinkedHashMap<>();

	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultOptional = new LinkedHashMap<>();
	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultOptionalFinal = new LinkedHashMap<>();

	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultRightMinus = new LinkedHashMap<>();
	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultLeftMinus = new LinkedHashMap<>();

	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultMinus = new LinkedHashMap<>();
	public static LinkedHashMap<EdgeOperator, List<Binding>> finalResultMinusFinal = new LinkedHashMap<>();

	public List<EdgeOperator> operatorsTemp;
	private Set<Binding> input = null;
	public static Iterator<Binding> results;
	private static ExecutorService es;
	// static int i=0;
	static int i = 0;
	static int ij = 0;
	private Optimiser optimiser;
	Set<Binding> BindingSets = null;
	public static int HashJoinCompletion = 0;
	public static int HashJoinCount;
	public static int BushyTreeCounter = 0;
	static ArrayList<String> abv = new ArrayList<>();

	static int l = 0;
	public static Vector<Binding> a = new Vector<>();
	public static Vertex b = new Vertex();
	public static boolean OptionalQuery;
	public static boolean IsRightQuery;
	public static boolean IsLeftQuery;

	static ArrayList<ArrayList<Integer>> UnProcessedSetsRep = new ArrayList<>();
	static ArrayList<Set<Binding>> Remaining = new ArrayList<>();

	public static HashMap<Vertex, Set<Edge>> c = new HashMap<>();
	public static Set<Edge> d = new HashSet<>();
	public static List<Binding> r3 = new ArrayList<>();
	private static int iteration = 0;

	public BGPEval(SimpleGraph g, QueryIterator input) {
		this.g = g;
		this.input = null;
		/*
		 * //logger.info("This is in BGPEval Constructor:"); if (!(input instanceof
		 * QueryIterRoot)) { if (Config.debug) { //logger.info("Not QueryIterRoot"); }
		 * this.input = new HashSet<Binding>(); while (input.hasNext()) {
		 * this.input.add(input.next()); } }
		 */
	}

	protected Object clone() throws CloneNotSupportedException {
		BGPEval student = (BGPEval) super.clone();
		student.optimiser = (ExhOptimiser) optimiser.clone();
		return student;
	}

//	public void output(IndentedWriter out, SerializationContext sCxt) {
//		//logger.info("These is the list of operators in ExecuteJoins22244441");
//	}

	static public ExecutorService getCurrentExeService() {
		return es;
	}

	/**
	 * @return 
	 * @throws CloneNotSupportedException
	 */

	public void execBGP() throws CloneNotSupportedException {
		System.out.println("This is the starting time of execBGP:" + LocalTime.now());
		completionE++;
		double distinctSubject = 0;
		double distinctObject = 0;
		Optimizer opt0 = new Optimizer();
		Optimiser opt = null;
		// System.out.println("This is value of ExhOpt:" + opt0.ExhOpt);
		// if(opt0.ExhOpt==1)
		// opt= new ExhOptimiser_NoSubObjCount(g);
		// i/f(opt0.ExhOpt==0)
		opt = new ExhOptimiser(g);
		// if(opt0.ExhOpt==2)
		// opt=new ExhOptimiser_EqualSubObjCount(g);
		// Optimiser opt = new ExhOptimiser_NoSubObjCount(g);
		// Optimiser opt = new GrdyOptimiser(g);
		Optimiser opt1 = null;
		HashJoinCompletion = 0;
		// Optimiser opt2=null;
		try {
			opt1 = (Optimiser) opt.clone();
		} catch (CloneNotSupportedException e1) {
			// [ TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final Optimiser opt2 = (Optimiser) opt.clone();
		final Optimiser optBT = (Optimiser) opt.clone();
		// for (int op = 0; op < Optimizer.triples.length; op++)
		// System.out.println("This is the new Bind to HashJoin in BGPEval111112222222:"
		// + Optimizer.triples[op][0]
		// + "--" + Optimizer.triples[op][1] + "--" + Optimizer.triples[op][7]);
		System.out.println("THis is problematic time:" + LocalTime.now());

		List<EdgeOperator> operators = opt.nextStage();
		List<EdgeOperator> operators_dependent = opt2.nextStage();
		List<EdgeOperator> operators_independent = opt1.nextStage();
		List<EdgeOperator> operators_BushyTree = new Vector<>();// =optBT.nextStage();
		List<EdgeOperator> operators_optional = new Vector<>();// =optBT.nextStage();
		List<EdgeOperator> operators_minus = new Vector<>();// =optBT.nextStage();
		
		List<EdgeOperator> operators_BushyTreeRight = new Vector<>();// =optBT.nextStage();
		List<EdgeOperator> operators_BushyTreeLeft = new Vector<>();// =optBT.nextStage();

		ListIterator<EdgeOperator> operatorsIterator = operators_independent.listIterator();
		ListIterator<EdgeOperator> operatorsIteratorDep = operators_dependent.listIterator();
		// ListIterator<EdgeOperator> operatorsIteratorBT ;

		 for(EdgeOperator o:operators)
		System.out.println("This is the new Bind to HashJoin in BGPEval00:"+o);

		String a;
		// System.out.println("These are the value of size in
		// BGPEval:"+ExhOptimiser.LabeledSize);
		StageGen.kl = 0;
		EdgeOperator x;
		while (operatorsIterator.hasNext()) {
			// a=String.valueOf(operatorsIterator.next());
			x = operatorsIterator.next();
			for (int i = 0; i < Optimizer.triples.length; i++) {
				// System.out.println("Getting individual operators 4 BushyTree element
				// before:"+"--"+Optimizer.triples[i][0]+"--"+x.getEdge().getTriple().getSubject().getName()+"--"+Optimizer.triples[i][1]+"--"+x.getEdge().getTriple().getObject().getName());
				String Subject; // =
								// x.getEdge().getTriple().getSubject().isURI()?x.getEdge().getTriple().getSubject().toString():x.getEdge().getTriple().getSubject().getName().toString();

				if (x.getEdge().getTriple().getSubject().isURI())
					Subject = x.getEdge().getTriple().getSubject().toString();
				else if (x.getEdge().getTriple().getSubject().isLiteral())
					Subject = x.getEdge().getTriple().getSubject().getLiteral().toString();
				else
					Subject = x.getEdge().getTriple().getSubject().getName().toString();

				String Object;// =x.getEdge().getTriple().getObject().isURI()?x.getEdge().getTriple().getObject().toString():x.getEdge().getTriple().getObject().getName().toString();

				if (x.getEdge().getTriple().getObject().isURI())
					Object = x.getEdge().getTriple().getObject().toString();
				else if (x.getEdge().getTriple().getObject().isLiteral())
					Object = x.getEdge().getTriple().getObject().getLiteral().toString();
				else
					Object = x.getEdge().getTriple().getObject().getName().toString();

				String SizeSO = Subject + "--" + Object;
				int m = 0;
				if (x.getEdge().getTriple().getSubject().isLiteral() || x.getEdge().getTriple().getObject().isLiteral())
					m = 1;
				if (x.getEdge().getTriple().getSubject().isURI() || x.getEdge().getTriple().getObject().isURI())
					m = 2;

				// System.out.println("This is the new generated
				// error:"+Optimizer.triples[i][0].toString()+"--"+x.getEdge().getTriple().getSubject().getName().toString());
//	System.out.println("This is the new generated error:"+Optimizer.triples[i][1].toString()+"--"+x.getEdge().getTriple().getObject().is);	
				if (Optimizer.triples[i][0] != null || Optimizer.triples[i][1] != null)
					if (Optimizer.triples[i][0].toString().replace("\"", "").equals(Subject)
							&& Optimizer.triples[i][1].toString().replace("\"", "").equals(Object)) {

						int skp = 0;
						Vertex BindDep = new Vertex();

						for (Entry<String, LinkedHashMap<Double, Double>> ls : ExhOptimiser.LabeledSize.entrySet()) {

							/*
							 * for(EdgeOperator gh:operators_BushyTree)
							 * if((gh.getEdge().getV1().getNode().getName()+"--"+gh.getEdge().getV1().
							 * getNode().getName()).equals(ls.getKey().toString())) {skp=1; break; }
							 * if(skp==1) { skp=0; continue; }
							 */
							// for(Entry<Double, Double> ll:ls.getValue().entrySet())
							// System.out.println("This is the value of BindJoin
							// BGPEval>>>>>>>>>>>>>>>>>>>>>>>>>"+SizeSO+"--"+ls.getKey()+"--"+ll+"--"+x.getEdge().getV2());
							if (m == 1)
								System.out.println("This is the literal:" + SizeSO + "--" + m);
							if (m == 2)
								System.out.println("This is the URI:" + SizeSO + "--" + m);

							if (m != 2 && m != 1) {
								if (SizeSO.equals(ls.getKey())) {
									for (Entry<Double, Double> size : ls.getValue().entrySet()) {

										// System.out.printf("This is BindDep size::" + x + "--" + size.getKey() + "--"
										// + size.getValue());

//						if()

										if (Optimizer.triples[i][7] == "HashJoin" || Optimizer.triples[i][7] == null) {
											// System.out.println("This is the new Bind to HashJoin in
											// BGPEval1:"+"--"+Optimizer.triples[i][7]+"--"+x);
											if(!operators_BushyTree.toString().contains(x.toString())){
											operators_BushyTree.add(new HashJoin(x.getEdge()));
											operators_BushyTreeRight.add(new HashJoin(x.getEdge()));
											operators_BushyTreeLeft.add(new HashJoin(x.getEdge()));
											operators_BushyTreeOrder.add(new HashJoin(x.getEdge()));
											operators_BushyTreeOrder1.add(new HashJoin(x.getEdge()));
											}
											// System.out.println("This is the new HashJoin in
											// BGPEval:"+operators_BushyTree);

										}
										if (Optimizer.triples[i][7] == "BindJoin"
												&& (!x.getEdge().getV1().getNode().isConcrete()
														&& !x.getEdge().getV2().getNode().isConcrete())) {
											if (size.getKey() <= size.getValue()
													&& !(size.getKey() == 0.02 && size.getValue() == 0.02)) {

												if (size.getKey() == 0.71)
													BindDep = x.getEdge().getV1();// }
												else
													BindDep = x.getEdge().getV2();// }
												// else BindDep = x.getEdge().getV1();
												// System.out.println("This is the value of BindJoin
												// BGPEval000:"+BindDep.getEdges());
												// ConcurrentHashMap<Set<Vertex>, Set<Edge>> cd = new
												// ConcurrentHashMap<>();
												// HashSet<Vertex> ab = new HashSet<>();
												// ab.add(BindDep);

												/// cd.put(ab, x.getEdge().getV2().getEdges());
												// StartBinding.put(cd, new ArrayList<>());

											} else if (size.getKey() > size.getValue()
													&& !(size.getKey() == 0.02 && size.getValue() == 0.02)) {
												// for(EdgeOperator io:operators_independent)
												// if(!io.toString().equals(x.toString()))
												// if(io.toString().contains(x.getEdge().getV2().toString()))
												// { System.out.println("This is now checking every operator:"+io);
												if (size.getValue() == 0.71)
													BindDep = x.getEdge().getV2();// }

												else
													BindDep = x.getEdge().getV1();// }
												// else BindDep = x.getEdge().getV2();
												// BindDep= x.getEdge().getV1();
												// System.out.println("This is the value of BindJoin
												// BGPEval000:"+BindDep.getEdges());
												// ConcurrentHashMap<Set<Vertex>, Set<Edge>> cd = new
												// ConcurrentHashMap<>();
												// HashSet<Vertex> ab = new HashSet<>();
												// ab.add(BindDep);
												// cd.put(ab, x.getEdge().getV1().getEdges());

//												StartBinding.put(cd, new ArrayList<>());

											}

											else if (size.getKey() == 0.02 && size.getValue() == 0.02
													&& Optimizer.triples[i][7] == "BindJoin") {

												// System.out.println("This is here in 1st try:" + x.getEdge());
												if(!operators_BushyTree.toString().contains(x.toString())){
													
												operators_BushyTree.add(new BindJoin(x.getEdge().getV1(), x.getEdge()));
												operators_BushyTreeRight
														.add(new BindJoin(x.getEdge().getV1(), x.getEdge()));
												operators_BushyTreeLeft
														.add(new BindJoin(x.getEdge().getV1(), x.getEdge()));
												operators_BushyTreeOrder
														.add(new BindJoin(x.getEdge().getV1(), x.getEdge()));
												operators_BushyTreeOrder1
														.add(new BindJoin(x.getEdge().getV1(), x.getEdge()));

											}}
										}
									}
								}
							}

							else {
								// System.out.println("This is here in 2st try:" + x.getEdge());
								if(!operators_BushyTree.toString().contains(x.toString())){
									
								operators_BushyTree.add(new HashJoin(x.getEdge()));
								operators_BushyTreeRight.add(new HashJoin(x.getEdge()));
								operators_BushyTreeLeft.add(new HashJoin(x.getEdge()));
								operators_BushyTreeOrder.add(new HashJoin(x.getEdge()));
								operators_BushyTreeOrder1.add(new HashJoin(x.getEdge()));
								}
							}

						}
						// System.out.println("This is the new Bind to HashJoin in
						// BGPEval11111:"+BindDep);
//						System.out.println("This is the new Bind to HashJoin in BGPEval111112222222:" + "--"
//								+ Optimizer.triples[i][7] + "--" + x);

						if (Optimizer.triples[i][7] == "BindJoin") {
							// System.out.println("This is the new Bind to HashJoin in BGPEval2.5:" + "--"
							// + Optimizer.triples[i][7] + "--" + x);

							if (BindDep.equals(x.getEdge().getV1())) {
								// System.out.println("This is the new Bind to HashJoin in BGPEval2:" + "--"
								// + Optimizer.triples[i][7] + "--" + x);
								if(!operators_BushyTree.toString().contains(x.toString())){
									
								operators_BushyTree.add(new BindJoin(x.getEdge().getV1(), x.getEdge()));
								operators_BushyTreeRight.add(new BindJoin(x.getEdge().getV1(), x.getEdge()));
								operators_BushyTreeLeft.add((new BindJoin(x.getEdge().getV1(), x.getEdge())));
								operators_BushyTreeOrder.add((new BindJoin(x.getEdge().getV1(), x.getEdge())));
								operators_BushyTreeOrder1.add((new BindJoin(x.getEdge().getV1(), x.getEdge())));
								}
							}
							if (BindDep.equals(x.getEdge().getV2())) {
								// System.out.println("This is the new Bind to HashJoin in BGPEval3:" + "--"
								// + Optimizer.triples[i][7] + "--" + x);
								if(!operators_BushyTree.toString().contains(x.toString())){
									
								operators_BushyTree.add(new BindJoin(x.getEdge().getV2(), x.getEdge()));
								operators_BushyTreeRight.add(new BindJoin(x.getEdge().getV2(), x.getEdge()));
								operators_BushyTreeLeft.add(new BindJoin(x.getEdge().getV2(), x.getEdge()));
								operators_BushyTreeOrder.add(new BindJoin(x.getEdge().getV2(), x.getEdge()));
								operators_BushyTreeOrder1.add(new BindJoin(x.getEdge().getV2(), x.getEdge()));
								}
							}
							// }
							// System.out.println("This is the new BindJoin in
							// BGPEval:"+operators_BushyTree);
						}
					}
			}
		}
		// Collections.sort(operators_BushyTree,Comparator.comparing(item->operators.indexOf(item)));
		// Collections.sort(operators_BushyTreeRight,Comparator.comparing(item->operators.indexOf(item)));
		// Collections.sort(operators_BushyTreeLeft,Comparator.comparing(item->operators.indexOf(item)));
		// Collections.sort(operators_BushyTreeOrder,Comparator.comparing(item->operators.indexOf(item)));

		// System.out.println("Getting individual operators 4 BushyTree
		// element:"+"--"+operators_BushyTree);
		// Collection<Edge> list = null;
		// System.out.println("This is the new HashJoin:"+JoinOrderOptimizer2.Join);
		// for (Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> e1
		// : StartBinding.entrySet())
		// for (Map.Entry<Set<Vertex>, Set<Edge>> e : e1.getKey().entrySet()) {
		// list = e.getValue();
		// for (Iterator<Edge> itr = list.iterator(); itr.hasNext();) {
		// if (Collections.frequency(list, itr.next()) > 1) {
		// itr.remove();
		// }
		// }
		// }

		// for (EdgeOperator bt : operators_BushyTree)
		// System.out.println("Getting individual operators 4 dep. element:" + "--" +
		// bt);
		// System.out.println("Getting individual operators 4
		// element:"+"--"+operators_BushyTree);
//		HashJoinCount = operators_dependent.size();

		for(EdgeOperator obt:operators_BushyTreeOrder)
		System.out.println("This is the obsecure:"+obt);
		String length = "";
		String[] vv = null;
		Iterator<EdgeOperator> l3 = operators_BushyTreeOrder.iterator();

		while (l3.hasNext()) {
			// BindingSet xz=l1.next();
			// while(l1.hasNext()) {
			length = l3.next().toString();
			vv = length.split(" ");
			for (String v : vv)
				if (v.startsWith("(")) {
					if (!v.contains("http") && v.contains(":") && (v.contains("$") || v.contains("?")))
						headersAll.add(v.substring(1, v.indexOf(":")));

					// break;
				} else
					continue;
			// break;
		}

//	System.out.println("These are all the headers:"+headersAll);

		/*
		 * Collections.sort(operators_BushyTree,Comparator.comparing(item->operators.
		 * indexOf(item)));
		 * Collections.sort(operators_BushyTreeRight,Comparator.comparing(item->
		 * operators.indexOf(item)));
		 * Collections.sort(operators_BushyTreeLeft,Comparator.comparing(item->operators
		 * .indexOf(item)));
		 * Collections.sort(operators_BushyTreeOrder,Comparator.comparing(item->
		 * operators.indexOf(item)));
		 */
		// System.out.println("Getting individual operators 4 BushyTree
		// element:"+"--"+operators_BushyTree);
		// Collection<Edge> list = null;
		// //logger.info("This is the new HashJoin:"+JoinOrderOptimizer2.Join);
		/*
		 * for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, Set<Binding>>
		 * e1:StartBindingSet.entrySet()) for(Map.Entry<Set<Vertex>,Set<Edge>>
		 * e:e1.getKey().entrySet()) { list = e.getValue(); for(Iterator<Edge> itr =
		 * list.iterator(); itr.hasNext();) { if(Collections.frequency(list,
		 * itr.next())>1) { itr.remove(); } } }
		 */

		// //logger.info("Getting individual operators 4 dep.
		// element:"+"--"+operators_dependent);
		// logger.info("Getting individual operators 4
		// element:"+"--"+operators_BushyTree);
		// HashJoinCount=operators_dependent.size();
		// for (EdgeOperator ob : operators_BushyTree)
		// System.out.println("This is the new BindJoin in BGPEval:" + ob);
		for (EdgeOperator o : operators_BushyTree)
			if (!joinGroupUnion.containsKey(o))
				joinGroupUnion.put(o, 0);

		for (EdgeOperator o : operators_BushyTree)
			if (!joinGroupMinus.containsKey(o))
				joinGroupMinus.put(o, 0);
		Map<Integer, List<EdgeOperator>> joinGroupsExcl = new HashMap<>();

		Map<Integer, List<EdgeOperator>> joinGroupsOptional = new HashMap<>();
		Map<Integer, List<EdgeOperator>> joinGroupsMinus = new HashMap<>();

		Iterator<EdgeOperator> Operators_BushyIterator = operators_BushyTree.iterator();

//Created Data structure for optional or union clauses after extracting them from file in ParaEng
		System.out.println("This is the problem currently:"+ParaEng.Optional);
		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty()) {
			String[] ijkl = null;
			ijkl = ParaEng.Optional.split(" ");
			;
			if (ParaEng.Optional.isEmpty())
				ijkl = ParaEng.Union.replaceAll("[{},\\]\\[]", "").replaceAll("[$]", "?")
				.replaceAll("[<>]", "").split(" ");// .replaceAll("[{},\\]\\[]", "").replaceAll("UNION",
												// "").replaceAll("null","").split(" ");;
			
		//	System.out.println("This is bushyTree edge:"+ijkl);
			List<Integer> namePosition = new ArrayList<>();
			String name = new String();

			String obj1 = new String();
			String sub1 = new String();//
			//// logger.info("This is now the StageGenOptional"+ijkl.length+"--"+jk);
			// while(Operators_BushyIterator.hasNext())
//		//logger.info("This is bushyTree Subject"+Operators_BushyIterator.next());
			
			for (int i = 0; i < ijkl.length; i++) {
				if (ijkl[i].contains("\'") || ijkl[i].contains("\""))
					namePosition.add(i);
			}
			if (namePosition.isEmpty() == false)
				for (int i = namePosition.get(0); i <= namePosition.get(1); i++)
					name = name + " " + ijkl[i];
			name = name.replaceAll("['\"]", "");

			// logger.info("These are the positions of name of
			// object:"+namePosition+"--"+name);
			
			while (Operators_BushyIterator.hasNext()) {
				EdgeOperator xy = Operators_BushyIterator.next();
				//.out.println("This is bushyTree edge:"+xy.getEdge().getTriple());
				
				if (xy.getEdge().getTriple().getSubject().isURI())
					sub1 = xy.getEdge().getTriple().getSubject().toString().replace("[<>]", "");
				else if (xy.getEdge().getTriple().getSubject().isLiteral())
					sub1 = xy.getEdge().getV1().getNode().getName().toString();
				else
					sub1 = xy.getEdge().getV1().getNode().toString();

				if (xy.getEdge().getTriple().getObject().isURI())
					obj1 = xy.getEdge().getTriple().getObject().toString().replace("[<>]", "");
				else if (xy.getEdge().getTriple().getObject().isLiteral())
					obj1 = xy.getEdge().getV2().getNode().getName().toString();
				else
					obj1 = xy.getEdge().getV2().getNode().toString();
				//System.out.println("This is bushyTree Subject:"+obj1+"--"+sub1);
				
				for (int i = 0; i < ijkl.length; i++) {
					if (!ijkl[i].equals("")) {

						if (namePosition.isEmpty() == false && (i + 2) == namePosition.get(0)) {
							if (sub1.toString().equals(ijkl[i].toString()) && ((i + 2) == namePosition.get(0))
									&& (" " + obj1).toString().equals(name.toString())) {
								// logger.info("This is bushyTree Subject"+i+"--"+sub1+"--"+obj1);
								operators_optional.add(xy);
								Operators_BushyIterator.remove();
							}
						} else {
					//		System.out.println("THis is the issue here:"+sub1+"--"+obj1);
						//	if(i + 2<ijkl.length)
						//		System.out.println("THis is the issue1 here:"+ijkl[i]+"--"+ijkl[i + 2]);
							
								if (sub1.toString().equals(ijkl[i].toString().replace("\t", ""))
									&& obj1.toString().equals(ijkl[i + 2].toString())) {
								//System.out.println("This is bushyTree Subject"+i+"--"+sub1+"--"+obj1+"--"+xy);
								for (Entry<EdgeOperator, Integer> xz : joinGroupUnion.entrySet())
									if (xz.getKey().equals(xy))
										joinGroupUnion.replace(xy, 1);
								operators_optional.add(xy);
								Operators_BushyIterator.remove();
							}
						}
					}
				}
			}
		}
		if (ParaEng.Minus.contains("MINUS")) {
			String[] ijkl = null;
			ijkl = ParaEng.Minus.replaceAll("[{},\\]\\[]", "").replaceAll("MINUS", "").replaceAll("null", "")
					.replaceAll("[$]", "?").split(" ");
			;
			List<Integer> namePosition = new ArrayList<>();
			String name = new String();

			String obj1 = new String();
			String sub1 = new String();//
			//// logger.info("This is now the StageGenOptional"+ijkl.length+"--"+jk);
			// while(Operators_BushyIterator.hasNext())
//		//logger.info("This is bushyTree Subject"+Operators_BushyIterator.next());

			for (int i = 0; i < ijkl.length; i++) {
				if (ijkl[i].contains("\'") || ijkl[i].contains("\""))
					namePosition.add(i);
			}
			if (namePosition.isEmpty() == false)
				for (int i = namePosition.get(0); i <= namePosition.get(1); i++)
					name = name + " " + ijkl[i];
			name = name.replaceAll("['\"]", "");

			// logger.info("These are the positions of name of
			// object:"+namePosition+"--"+name);

			while (Operators_BushyIterator.hasNext()) {
				EdgeOperator xy = Operators_BushyIterator.next();
				if (xy.getEdge().getTriple().getSubject().isURI())
					sub1 = xy.getEdge().getTriple().getSubject().toString().replace("[<>]", "");
				else if (xy.getEdge().getTriple().getSubject().isLiteral())
					sub1 = xy.getEdge().getV1().getNode().getName().toString();
				else
					sub1 = xy.getEdge().getV1().getNode().toString();

				if (xy.getEdge().getTriple().getObject().isURI())
					obj1 = xy.getEdge().getTriple().getObject().toString().replace("[<>]", "");
				else if (xy.getEdge().getTriple().getObject().isLiteral())
					obj1 = xy.getEdge().getV2().getNode().getName().toString();
				else
					obj1 = xy.getEdge().getV2().getNode().toString();

				for (int i = 0; i < ijkl.length; i++) {
					if (!ijkl[i].equals("")) {

						if (namePosition.isEmpty() == false && (i + 2) == namePosition.get(0)) {
							if (sub1.toString().equals(ijkl[i].toString()) && ((i + 2) == namePosition.get(0))
									&& (" " + obj1).toString().equals(name.toString())) {
								// logger.info("This is bushyTree Subject"+i+"--"+sub1+"--"+obj1);
								operators_minus.add(xy);
								Operators_BushyIterator.remove();
							}
						} else {
//	//logger.info("This is bushyTree Subject:"+i+"--"+sub1.toString()+"--"+ijkl[i].toString());
//	//logger.info("This is bushyTree Object:"+i+"--"+obj1.toString()+"--"+ijkl[i+2].toString());

							if (sub1.toString().equals(ijkl[i].toString().replace("\t", ""))
									&& obj1.toString().equals(ijkl[i + 2].toString())) {
								// logger.info("This is bushyTree Subject"+i+"--"+sub1+"--"+obj1);
								for (Entry<EdgeOperator, Integer> xz : joinGroupMinus.entrySet())
									if (xz.getKey().equals(xy))
										joinGroupMinus.replace(xy, 1);
								operators_minus.add(xy);
								Operators_BushyIterator.remove();
							}
						}
					}
				}
			}
		}

		// for( Entry<EdgeOperator,Integer> xz:joinGroupUnion.entrySet()) {
		// System.out.println("These are the union status:"+xz);
		// }
//		System.out.println("1Getting individual operators 4 element:"+"--"+operators_optional);
//		System.out.println("2Getting individual operators 4 element:"+"--"+operators_BushyTree);

		
		for(Entry<String, Integer> po:ParaEng.OptionalOrder.entrySet())
		//	 System.out.println("This is now the line with index:"+po.getKey()+"--"+po.getValue());		
			for(EdgeOperator oo:operators_optional)
			{//	 System.out.println("This is now the line with index 123456:"+oo);		
			if(operators_BushyTree.contains(oo)) {
				operators_BushyTree.remove(oo);
				operators_BushyTreeRight.remove(oo);
				operators_BushyTreeLeft.remove(oo);
			
			}
				
			}
		
			for(Entry<String, Integer> po:ParaEng.UnionOrder.entrySet())
				 System.out.println("This is now the line with index:"+po.getKey()+"--"+po.getValue());		
				for(EdgeOperator oo:operators_optional)
				{	 System.out.println("This is now the line with index 123456:"+oo);		
				if(operators_BushyTree.contains(oo)) {
					operators_BushyTree.remove(oo);
					operators_BushyTreeRight.remove(oo);
					operators_BushyTreeLeft.remove(oo);
				
				}
					
				}
				
			for(EdgeOperator obt:operators_BushyTree)
	System.out.println("THis is good enough:"+obt);
			
				Set<EdgeOperator> URIbased = new HashSet<>();
		Set<EdgeOperator> uris = new HashSet<>();
		
		for (EdgeOperator nf : operators_BushyTree)
		//	for (EdgeOperator nf : nf1)
				if (nf.getEdge().getV1().getNode().isURI() || nf.getEdge().getV2().getNode().isURI()) {
					URIbased.add(nf);

				}
		int equality = 0;
		if (!URIbased.isEmpty() || URIbased.size() > 0 || URIbased != null)
			for (EdgeOperator uri : URIbased)
				for (EdgeOperator uri1 : URIbased) {
					// System.out.println("This is here in uris condition:" + uri + "--" + uri1);

					if ((uri.getEdge().getV1().equals(uri1.getEdge().getV2())
							|| uri.getEdge().getV2().equals(uri1.getEdge().getV1()))
							&& (uri.getEdge().getV1().getNode().isURI() || uri.getEdge().getV2().getNode().isURI())
							&& (uri1.getEdge().getV1().getNode().isURI() || uri1.getEdge().getV2().getNode().isURI())) {
						// System.out.println("This is the focus
						// here:"+Optimizer.getEndpointE(uri.getEdge().getTriple()).toString()+"--"+Optimizer.getEndpointE(uri1.getEdge().getTriple()).toString()+"--"+equality);
						equality = 0;

						for (Endpoint op1 : Optimizer.getEndpointE(uri.getEdge().getTriple())) {
							for (Endpoint op2 : Optimizer.getEndpointE(uri1.getEdge().getTriple()))
								if (op1.equals(op2)) {
									equality++;
									// System.out.println("This is the focus here:"+op1+"--"+op2+"--"+equality);

								}

							if (equality == 2) {

								if(!uris.contains(uri) ||!uris.contains(uri))
								{uris.add(uri);
								uris.add(uri1);}
								HashMap<Vertex,List<Binding>> a1 = new HashMap<>();
								
								if(uri.getEdge().getV1().getNode().isURI()) {
								a1.put(uri.getEdge().getV1(), null);
								if(!urik_element.toString().contains(uri.toString()))
										urik_element.add(uri);
								}
								else
									{a1.put(uri.getEdge().getV2(), null);
									if(!urik_element.toString().contains(uri.toString()))
												urik_element.add(uri);
									}
								urik.putAll(a1);
									
									
									HashMap<Vertex,List<Binding>> b = new HashMap<>();
									if(uri1.getEdge().getV1().getNode().isURI()) {
									{b.put(uri1.getEdge().getV1(), null);
									if(!urik_element.toString().contains(uri.toString()))
											urik_element.add(uri);
									}
									}
									else
										{
										b.put(uri1.getEdge().getV2(), null);
										if(!urik_element.toString().contains(uri.toString()))
										urik_element.add(uri);
										}
								urik.putAll(b);
				
								// System.out.println("This is the focus here2:"+uris+"--"+equality);

								break;
							}
							if (equality == 2)
								break;
						}
					}
				}
		for(EdgeOperator uri:uris)
		{		if(uri.getEdge().getV1().getNode().isURI())
				urim.put(uri, new BindJoin(uri.getEdge().getV1(),uri.getEdge()));
		System.out.println("THis is the exchange:"+urim);
		
		}
		Set<EdgeOperator> temp = new HashSet<>();
		for(EdgeOperator obt:operators_BushyTreeLeft)
		if(!temp.toString().contains(obt.toString()))
			temp.add(obt);	
		operators_BushyTreeLeft.clear();
		operators_BushyTreeLeft.addAll(temp);
		
		for(EdgeOperator obt:operators_BushyTreeRight)
			if(!temp.toString().contains(obt.toString()))
				temp.add(obt);	
		operators_BushyTreeRight.clear();
		operators_BushyTreeRight.addAll(temp);
		
		
		for(EdgeOperator obt:operators_BushyTree)
			if(!temp.toString().contains(obt.toString()))
				temp.add(obt);	
		operators_BushyTree.clear();
		operators_BushyTree.addAll(temp);
		
		for(EdgeOperator obt:operators_BushyTreeOrder)
			if(!temp.toString().contains(obt.toString()))
				temp.add(obt);	
		operators_BushyTreeOrder.clear();
		operators_BushyTreeOrder.addAll(temp);
		
		//for(EdgeOperator ol:operators_BushyTreeLeft)
		//System.out.println("THis is the exchange:"+ol+"--"+operators_BushyTreeLeft.indexOf(ol));

	
		
		
		
		for(Entry<EdgeOperator, EdgeOperator> urii:urim.entrySet())
		{
			System.out.println("THis is the exchange12321 left before :"+operators_BushyTreeLeft);
		Iterator<EdgeOperator> obl = operators_BushyTreeLeft.iterator();
			while(obl.hasNext()) 
			if(obl.next().getEdge().equals(urii.getKey().getEdge()))
			obl.remove();	
			operators_BushyTreeLeft.add(urii.getValue());
			System.out.println("THis is the exchange12321 left after:"+operators_BushyTreeLeft);
		}
		for(Entry<EdgeOperator, EdgeOperator> urii:urim.entrySet())
		{	System.out.println("THis is the exchange12321 right before :"+operators_BushyTreeLeft);
			Iterator<EdgeOperator> obl1 = operators_BushyTreeRight.iterator();
				while(obl1.hasNext()) 
					if(obl1.next().getEdge().equals(urii.getKey().getEdge()))
								obl1.remove();	
				operators_BushyTreeRight.add(urii.getValue());
				System.out.println("THis is the exchange12321 right after:"+operators_BushyTreeLeft);
		}
		for(Entry<EdgeOperator, EdgeOperator> urii:urim.entrySet())
		{
				System.out.println("THis is the exchange12321 operators_BushyTreeOrder before:"+operators_BushyTreeLeft);
				Iterator<EdgeOperator> obl11 = operators_BushyTreeOrder.iterator();
				while(obl11.hasNext()) 
					if(obl11.next().getEdge().equals(urii.getKey().getEdge()))
						obl11.remove();	
				operators_BushyTreeOrder.add(urii.getValue());
			}
		for(Entry<EdgeOperator, EdgeOperator> urii:urim.entrySet())
		{
				System.out.println("THis is the exchange12321 operators_BushyTree before:"+operators_BushyTree);
				Iterator<EdgeOperator> obl11 = operators_BushyTree.iterator();
				while(obl11.hasNext()) 
					if(obl11.next().getEdge().equals(urii.getKey().getEdge()))
						obl11.remove();	
				operators_BushyTree.add(urii.getValue());
			}
			for(EdgeOperator urii:operators_optional)
			{
				System.out.println("THis is the exchange12321 operators_BushyTree before:"+operators_BushyTree);
				Iterator<EdgeOperator> obl11 = operators_BushyTree.iterator();
				while(obl11.hasNext()) 
					if(obl11.next().getEdge().equals(urii.getEdge()))
						obl11.remove();	
				System.out.println("THis is the exchange12321 operators_BushyTree after:"+operators_BushyTree);
				
			}
			
		//	System.out.println("THis is the exchange12321 right:"+urii);

//			operators_BushyTreeRight.remove(urii.getKey());
			
	//		operators_BushyTreeRight.add(urii.getValue());
	//		System.out.println("THis is the exchange12321 order:"+urii);

	//		operators_BushyTreeOrder.remove(urii.getKey());
			
	//		operators_BushyTreeOrder.add(urii.getValue());
		
		//else
		temp.clear();
		for(EdgeOperator obo1:operators_BushyTreeOrder1)
		for(EdgeOperator obo:operators_BushyTreeOrder)
			if(obo1.getEdge().getTriple().equals(obo.getEdge().getTriple()))
				temp.add(obo);
		operators_BushyTreeOrder.clear();
		operators_BushyTreeOrder.addAll(temp);
		System.out.println("THis is the exchange12321 operators_BushyTreeOrder after:"+operators_BushyTreeLeft);
	
	//	for(EdgeOperator ol:operators_BushyTreeLeft)
	//		System.out.println("THis is the exchange:"+ol);
			
	//	for(EdgeOperator ol:operators_BushyTreeRight)
	//		System.out.println("THis is the exchange12321:"+ol);
		
		 //for(EdgeOperator uri:urim)
//		 System.out.println("This is uris:"+urim);
		HashMap<EdgeOperator, Set<Endpoint>> abc = new HashMap<>();
		//if (!uris.isEmpty() || uris.size() > 0 || uris != null)
		//	for (EdgeOperator uri : uris)
		//		abc.put(uri, Optimizer.getEndpointE(uri.getEdge().getTriple()));

//for(EdgeOperator uri:uris)
		// System.out.println("This is here in uris
		// endpoint:"+Optimizer.getEndpointE(uri.getEdge().getTriple()));

//		System.out.println("This is here in uris:" + uris);
		
		
		ConcurrentHashMap<EdgeOperator, HashSet<Integer>> newFormationM = new ConcurrentHashMap<>();

		// ForkJoinPool fjp = new ForkJoinPool(6);
		// fjp.submit(()->
		joinGroups2 = CreateBushyTreesExclusive(operators_BushyTree, joinGroupsExcl, operators,
				operators_BushyTreeOrder);// );
//try {
//	fjp.awaitTermination(100, TimeUnit.MILLISECONDS);
//} catch (InterruptedException e2) {
		// TODO Auto-generated catch block
//	e2.printStackTrace();
//}

//fjp.shutdownNow();
		// for (Entry<List<EdgeOperator>, Integer> nf1 : joinGroups2.entries())
		// System.out.println("THis is problematic list:" + nf1);

		int ll = 0;
/////////////////////////////Seperating SourceStatements from Exclusive Group and forming Left/Right Bushy Tree ////////////////////////
		for (Entry<List<EdgeOperator>, Integer> e4 : joinGroups2.entries()) {
			for (EdgeOperator e6 : e4.getKey()) {

				if (newFormation.size() == 0 || !newFormation.containsKey(e6)) {
					HashSet<Integer> bb = new HashSet<>();
					bb.add(e4.getValue());
					newFormation.put(e6, bb);

				} else {
					for (Entry<EdgeOperator, HashSet<Integer>> nf : newFormation.entrySet()) {
						if (nf.getKey().equals(e6)) {
							HashSet<Integer> bb = new HashSet<>();
							bb.addAll(nf.getValue());
							bb.add(e4.getValue());
							if (bb.size() > 1)
								newFormation.put(e6, bb);
						}

					}
				}
				// //logger.info("This is the new new new:"+e4.getKey()+"--"+e6);
			}

			JoinGroupsListExclusive.add(e4.getKey());
		}

		System.out.println("This is uris12321:"+uris);
			
		for (Entry<EdgeOperator, HashSet<Integer>> nf : newFormation.entrySet()) {

			if (nf.getValue().size() > 1) {
				newFormationM.put(nf.getKey(), nf.getValue());

			}
		}
		
		System.out.println("This is newFormation:"+newFormationM);
		
		
		// for (Entry<EdgeOperator, HashSet<Integer>> uri : newFormation.entrySet())
		// System.out.println("This is here in uris condition0000:" + uri);

		// for (Entry<EdgeOperator, HashSet<Integer>> uri : newFormationM.entrySet())
		// System.out.println("This is here in uris condition0000111111:" + uri);

//System.out.println("This is Old joinGroup2:"+newFormationM);
//System.out.println("This is Old joinGroup333:"+JoinGroupsListExclusive);

//LinkedHashMap<Integer, List<EdgeOperator>> uuu;
//HashSet<EdgeOperator> ExistingEdges = new HashSet<>(); 

		operators_BushyTreeLeft.clear();

		operators_BushyTreeRight.clear();

		/*
		 * int isDoubled=0; int isTripled=0; int CompletlyNewFormation=0;
		 * List<EdgeOperator> leSafe= new ArrayList<>(); List<EdgeOperator>
		 * leSafeNonConcrete= new ArrayList<>();
		 * 
		 * Iterator<List<EdgeOperator>> jgle = JoinGroupsListExclusive.iterator() ;
		 * while(jgle.hasNext()) {
		 * List<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator>
		 * jglen = jgle.next(); //CompletlyNewFormation=0; for(Entry<EdgeOperator,
		 * HashSet<Integer>> nf:newFormationM.entrySet()) {
		 * //Iterator<List<EdgeOperator>> le = JoinGroupsListExclusive.iterator() ;
		 * 
		 * // while(le.hasNext()) // { // List<EdgeOperator> le2 = le.next();
		 * Iterator<EdgeOperator> le3 = jglen.iterator(); EdgeOperator le1;
		 * while(le3.hasNext()) { le1= le3.next();
		 * if(le1.getEdge().getTriple().getSubject().isURI()
		 * ||le1.getEdge().getTriple().getSubject().isLiteral()) { isTripled++; //
		 * if(!ConcreteEdge.contains(le1.getEdge().getTriple().getObject()))
		 * 
		 * //ConcreteEdge.add(le1.getEdge().getTriple().getObject()); continue; }
		 * 
		 * if( le1.getEdge().getTriple().getObject().isURI()
		 * ||le1.getEdge().getTriple().getObject().isLiteral()) { isTripled++;
		 * //if(!ConcreteEdge.contains(le1.getEdge().getTriple().getSubject()))
		 * //C/oncreteEdge.add(le1.getEdge().getTriple().getSubject()); continue; }
		 * 
		 * 
		 * /* if(!nf.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nf.getKey().getEdge().getTriple().getSubject().isConcrete()) { for(Node ce:
		 * ConcreteEdge) { if(nf.getKey().getEdge().getTriple().getObject().equals(ce) )
		 * CompletlyNewFormation=1; // //logger.info(""); if(
		 * nf.getKey().getEdge().getTriple().getSubject().equals(ce))
		 * CompletlyNewFormation=1; ////logger.info(""); } }
		 * 
		 * 
		 * if(CompletlyNewFormation==0) { isDoubled++;
		 * 
		 * // //log.info("This is total size of triple group:"+"--"+jglen.size());
		 * if(!leSafeNonConcrete.contains(nf.getKey()))
		 * leSafeNonConcrete.add(nf.getKey()); } CompletlyNewFormation=1;
		 * 
		 * if((nf.getKey().getEdge().getTriple().getSubject().toString().equals(le1.
		 * getEdge().getTriple().getObject().toString()) ||
		 * nf.getKey().getEdge().getTriple().getObject().toString().equals(le1.getEdge()
		 * .getTriple().getSubject().toString())) &&
		 * !nf.getKey().getEdge().toString().equals(le1.getEdge().toString())) { if
		 * ((le1.getEdge().getTriple().getSubject().isConcrete() &&
		 * !le1.getEdge().getTriple().getObject().isConcrete())) //isDoubled++;
		 * isTripled++; if ((le1.getEdge().getTriple().getObject().isConcrete() &&
		 * !le1.getEdge().getTriple().getSubject().isConcrete())) //isDoubled++;
		 * isTripled++; if ((nf.getKey().getEdge().getTriple().getSubject().isConcrete()
		 * && !nf.getKey().getEdge().getTriple().getObject().isConcrete()))
		 * //isDoubled++; isTripled++; if
		 * ((nf.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nf.getKey().getEdge().getTriple().getSubject().isConcrete())) //isDoubled++;
		 * isTripled++; } leSafe.add(le1);
		 * 
		 * } if(isTripled>1) { //isTripled=0; continue; }
		 * 
		 * 
		 * 
		 * if(isTripled==0 ) { for(Entry<EdgeOperator, HashSet<Integer>>
		 * nf1:newFormationM.entrySet()) {
		 * 
		 * if(jglen.contains(nf1.getKey())) jglen.remove(nf1.getKey());
		 * if(!operators_BushyTreeLeft.contains(nf1.getKey()) &&
		 * ((!nf1.getKey().getEdge().getTriple().getSubject().isURI()
		 * ||!nf1.getKey().getEdge().getTriple().getSubject().isLiteral()) ||
		 * (!nf1.getKey().getEdge().getTriple().getObject().isURI() ||
		 * !nf1.getKey().getEdge().getTriple().getObject().isLiteral())) )
		 * {operators_BushyTreeLeft.add(nf1.getKey());
		 * operators_BushyTreeRight.add(nf1.getKey()); }
		 * 
		 * if(jglen.size()==1) {
		 * if(!operators_BushyTreeLeft.toString().contains(jglen.toString())) {
		 * operators_BushyTreeLeft.addAll(jglen);
		 * operators_BushyTreeRight.addAll(jglen);
		 * 
		 * }
		 * 
		 * // jgle.remove(); } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * // isDoubled=0;
		 * 
		 * } } // }
		 * 
		 * 
		 * 
		 * if(isDoubled>0) { //for(Entry<EdgeOperator, HashSet<Integer>>
		 * nf1:newFormationM.entrySet()) //{ for(EdgeOperator le11:leSafeNonConcrete) {
		 * EdgeOperator a11= le11; for(int i=0;i<JoinGroupsListExclusive.size();i++)
		 * if(!JoinGroupsListExclusive.get(i).toString().contains("http")) {
		 * JoinGroupsListExclusive.get(i).remove(a11);
		 * if(!operators_BushyTreeLeft.contains(le11) ) {
		 * operators_BushyTreeLeft.add(le11); operators_BushyTreeRight.add(le11); } } //
		 * }
		 * 
		 * // } // } //} }
		 * 
		 * 
		 * }
		 * 
		 */

		/*
		 * for(int i=0 ;i<JoinGroupsListExclusive.size();i++) for(int
		 * j=0;j<JoinGroupsListExclusive.get(i).size();j++) for(Node ce:ConcreteEdge)
		 * if(!JoinGroupsListExclusive.get(i).get(j).getEdge().getTriple().getObject().
		 * equals(ce) &&
		 * !JoinGroupsListExclusive.get(i).get(j).getEdge().getTriple().getSubject().
		 * equals(ce)) {
		 * operators_BushyTreeLeft.add(JoinGroupsListExclusive.get(i).get(j));
		 * operators_BushyTreeRight.add(JoinGroupsListExclusive.get(i).get(j));
		 * JoinGroupsListExclusive.get(i).remove(j); }
		 */

		// for (List<EdgeOperator> e : JoinGroupsListExclusive)
		// System.out.println("This is list of edgeOperator121212121212:" + e);
		for(List<EdgeOperator> jgl:JoinGroupsListExclusive)
			 System.out.println("This is joinGroupList loop111111111111:"+jgl);

		
		for(EdgeOperator jgl:operators_BushyTreeOrder)
			 System.out.println("This is joinGroupList loop999999999999:"+jgl);

		List<EdgeOperator> inclusion = new ArrayList<>();
		Iterator<List<EdgeOperator>> jgIterator = JoinGroupsListExclusive.iterator();// joinGroups2.keySet().iterator();
		int ij1 = 0;
		while (jgIterator.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator.next();
			for (Entry<EdgeOperator, HashSet<Integer>> nfm : newFormationM.entrySet()) {

				if (aa.contains(nfm.getKey())) {
					inclusion.add(nfm.getKey());
//	jgIterator.remove();;
					if (!uris.contains(nfm.getKey())) {
						operators_BushyTreeLeft.add(nfm.getKey());
						operators_BushyTreeRight.add(nfm.getKey());
					}
					// System.out.println("this is problem11111:" + aa);

//}
//}
//}
				}
			}
		}
		
		System.out.println("this is problem11111:" + operators_BushyTreeLeft);
		jgIterator = JoinGroupsListExclusive.iterator();// joinGroups2.keySet().iterator();
		if (!inclusion.isEmpty() || inclusion.size() > 0 || inclusion != null)
			while (jgIterator.hasNext()) {
				List<EdgeOperator> aa = new ArrayList<>();
				aa = jgIterator.next();
				for (EdgeOperator e : inclusion)
					aa.remove(e);
			}

		// for (EdgeOperator e : uris)
		// System.out.println("This is list of edgeOperator 0:" + e);

//		for (EdgeOperator e : inclusion)
		// System.out.println("This is list of edgeOperator 1:" + e);

		// for (EdgeOperator e : operators_BushyTreeLeft)
//			System.out.println("This is list of edgeOperator 2:" + e);

//		for (List<EdgeOperator> e : JoinGroupsListExclusive)
//			System.out.println("This is list of edgeOperator 3:" + e);
		for(List<EdgeOperator> jgl:JoinGroupsListExclusive)
			 System.out.println("This is joinGroupList loop2222222222222:"+jgl);

		
//		if (!uris.isEmpty() || uris.size() > 0 || uris != null) {
//			List<EdgeOperator> namesList1 = uris.parallelStream().collect(Collectors.toList());
//namesList1.addAll(uris);
//			JoinGroupsListExclusive.add(namesList1);
//		}
		// System.out.println("These are uris:"+uris);
		// for(List<EdgeOperator> jgl:JoinGroupsListExclusive)
		// System.out.println("This is list of edgeOperator after that:" + jgl);

//JoinGroupsListExclusive.remove(inclusion);
		/*
		 * Iterator<List<EdgeOperator>> jgIterator1 =
		 * JoinGroupsListExclusive.iterator(); int ij=0;
		 * 
		 * while(jgIterator1.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
		 * aa=jgIterator1.next(); if(aa.size()==1 ) { for(EdgeOperator aaa:aa) { //
		 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
		 * ()); for(List<EdgeOperator> jge:JoinGroupsListExclusive)
		 * if((aaa.getEdge().getTriple().getObject().isConcrete() ||
		 * aaa.getEdge().getTriple().getSubject().isConcrete()) && jge.size()>1)
		 * for(EdgeOperator jgle:jge) {
		 * if(jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getSubject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getSubject())) { jge.add(aaa); aa.remove(aaa); } ij=1; break; } if(ij==1)
		 * break;
		 * 
		 * if(ij==1) {ij=0; break;}
		 * 
		 * }
		 * 
		 * } }
		 * 
		 * Iterator<List<EdgeOperator>> jgIterator11 = joinGroups2.values().iterator();
		 * int ij11=0;
		 * 
		 * while(jgIterator11.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
		 * aa=jgIterator11.next(); if(aa.size()==1 ) { for(EdgeOperator aaa:aa) { //
		 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
		 * ()); for(Entry<Integer, List<EdgeOperator>> jgel:joinGroups2.entrySet())
		 * if((aaa.getEdge().getTriple().getObject().isConcrete() ||
		 * aaa.getEdge().getTriple().getSubject().isConcrete()) &&
		 * jgel.getValue().size()>1) for(EdgeOperator jgle:jgel.getValue()) {
		 * if(jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getSubject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getSubject())) { jgel.getValue().add(aaa); aa.remove(aaa); } ij11=1; break; }
		 * if(ij11==1) break;
		 * 
		 * if(ij11==1) {ij11=0; break;}
		 * 
		 * }
		 * 
		 * } }
		 * 
		 *//*
			 * Iterator<List<EdgeOperator>> jgIterator111 =
			 * JoinGroupsListExclusive.iterator(); int ij111=0;
			 * 
			 * while(jgIterator111.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
			 * aa=jgIterator111.next(); if(aa.size()<=newFormationM.size() && aa.size()>1 )
			 * { for(EdgeOperator aaa:aa) { //
			 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
			 * ()); for(Entry<EdgeOperator, HashSet<Integer>> nfm:newFormationM.entrySet())
			 * if(aaa.equals(nfm.getKey()) &&
			 * !aaa.getEdge().getTriple().getSubject().isConcrete()&&
			 * !aaa.getEdge().getTriple().getObject().isConcrete()) ij111++;
			 * if(ij111==aa.size()) if(aa.size()>1 || aa.isEmpty()==false) {
			 * 
			 * // System.out.println("1this is problem:"+aa);
			 * //if(!operators_BushyTreeRight.equals(aa)) {
			 * 
			 * // operators_BushyTreeRight.addAll(aa); //
			 * operators_BushyTreeLeft.addAll(aa); // } // jgIterator111.remove();
			 * //System.out.println("1this is problem11:"+aa);
			 * 
			 * } } } }
			 */

		/*
		 * Iterator<List<EdgeOperator>> obtIterator99; Iterator<List<EdgeOperator>>
		 * jg2Iterator99; List<EdgeOperator> obt99; boolean isString=false;
		 * obtIterator99 = JoinGroupsListExclusive.iterator();
		 * while(obtIterator99.hasNext()) { obt99 = obtIterator99.next(); for(int i=0;
		 * i<obt99.size();i++) {
		 * if(obt99.get(i).getEdge().getTriple().getObject().isConcrete() ||
		 * obt99.get(i).getEdge().getTriple().getSubject().isConcrete()) {isString=true;
		 * break; } } if(isString==true) { isString=false; jg2Iterator99 =
		 * JoinGroupsListExclusive.iterator(); List<EdgeOperator> jg99;
		 * while(jg2Iterator99.hasNext()) { jg99=jg2Iterator99.next();
		 * if(!obt99.equals(jg99))
		 * 
		 * for(EdgeOperator jg991:jg99) { if(obt99.contains(jg991)) jg99.remove(jg991);
		 * } } }
		 * 
		 * }
		 */
		/*
		 * Iterator<List<EdgeOperator>> jgIterator3 = joinGroups2.keySet().iterator();
		 * 
		 * while (jgIterator3.hasNext()) { List<EdgeOperator> aa = new ArrayList<>(); aa
		 * = jgIterator3.next(); if (aa.size() == 1) {
		 * 
		 * if (!operators_BushyTreeRight.equals(aa)) {
		 * 
		 * operators_BushyTreeRight.addAll(aa); operators_BushyTreeLeft.addAll(aa); }
		 * jgIterator3.remove(); } } if(!uris.isEmpty() || uris.size()>0 || rusi!=null)
		 * for (EdgeOperator uri : uris) { operators_BushyTreeRight.remove(uri);
		 * operators_BushyTreeLeft.remove(uri);
		 * 
		 * }
		 */
////logger.info("This is the task of eternity:"+joinGroups2);
		Iterator<List<EdgeOperator>> jgIterator1111 = JoinGroupsListExclusive.iterator();

		while (jgIterator1111.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator1111.next();
			if (aa.size() == 1) {
				if (!operators_BushyTreeRight.equals(aa)) {
					operators_BushyTreeRight.addAll(aa);
					operators_BushyTreeLeft.addAll(aa);
				}

				jgIterator1111.remove();
			}
		}

		
		// for(List<EdgeOperator> e:JoinGroupsListExclusive)
		// System.out.println("This is 11111111111222222222222:"+e);

		/*
		 * List<List<EdgeOperator>> Temp = new ArrayList<>(); Set<EdgeOperator> Temp1 =
		 * new HashSet<>();
		 * 
		 * List<List<EdgeOperator>> Temp2 = new ArrayList<>(); for(List<EdgeOperator>
		 * e:JoinGroupsListExclusive) for(EdgeOperator e1:e) {
		 * if(e1.getEdge().getTriple().getSubject().isLiteral()||e1.getEdge().getTriple(
		 * ).getSubject().isURI() ||
		 * e1.getEdge().getTriple().getObject().isLiteral()||e1.getEdge().getTriple().
		 * getObject().isURI()) { if(!Temp.contains(e)) Temp.add(e);
		 * 
		 * } }
		 * 
		 * // for(List<EdgeOperator> t:Temp) for(List<EdgeOperator> t:Temp) { //
		 * System.out.println("This is middle JoinGroupsListExclusive:"+t);
		 * 
		 * Temp1.clear(); for(EdgeOperator t1:t) { Temp1.add(new
		 * HashJoin(t1.getEdge())); operators_BushyTreeOrder.add(new
		 * HashJoin(t1.getEdge()));
		 * //System.out.println("This is operators_BushyTreeOrder in loop:"
		 * +operators_BushyTreeOrder); }
		 * JoinGroupsListExclusive.add(Temp1.parallelStream().collect(Collectors.toList(
		 * ))); //
		 * System.out.println("This is middle JoinGroupsListExclusivett:"+Temp2);
		 * 
		 * }
		 * 
		 * // for(List<EdgeOperator> t:Temp2) //
		 * System.out.println("This is middle 11 JoinGroupsListExclusive:"+t);
		 * 
		 * for(List<EdgeOperator> t:Temp) JoinGroupsListExclusive.remove(t);
		 * 
		 * // for(List<EdgeOperator> t1:Temp2) // JoinGroupsListExclusive.add(t1);
		 * for(List<EdgeOperator> t1:Temp) for(EdgeOperator t11:t1)
		 * operators_BushyTreeOrder.remove(t11);
		 */

		// for(EdgeOperator t2:operators_BushyTreeOrder)
		// System.out.println("This is the problem here in 11111111111111111:"+t2);

		// for(List<EdgeOperator> t2:JoinGroupsListExclusive)
		// System.out.println("This is the problem here in 1111111111111133333111:"+t2);
//					for(List<EdgeOperator> t:Temp2)
//				for(EdgeOperator t1:t)
//			operators_BushyTreeOrder.add(t1);

/////////////////////////////Seperating SourceStatements from Exclusive Group and forming Left/Right Bushy Tree ////////////////////////

/////////////////////////////Ordering JoinGroupsListExclusive ////////////////////////

////////////////////////////////////////////////////////////////////////////
		/*
		 * Iterator<List<EdgeOperator>> jgIterator6 =
		 * JoinGroupsListExclusive.iterator();
		 * 
		 * List<EdgeOperator> jg = new ArrayList<EdgeOperator>();
		 * while(jgIterator6.hasNext()) {int ij6=0;
		 * 
		 * List<EdgeOperator> aa = new ArrayList<>(); aa=jgIterator6.next();
		 * if(aa.size()>1 ) { for(Entry<EdgeOperator, HashSet<Integer>>
		 * nfm:newFormationM.entrySet()) { int isConcrete=0; for(List<EdgeOperator>
		 * joinG:JoinGroupsListExclusive) { for(EdgeOperator joinG1:joinG) {
		 * if(joinG1.getEdge().getTriple().getObject().equals(nfm.getKey().getEdge().
		 * getTriple().getSubject()) &&
		 * joinG1.getEdge().getTriple().getSubject().isConcrete()) isConcrete=1; } }
		 * 
		 * if(aa.contains(nfm.getKey()) &&
		 * !nfm.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nfm.getKey().getEdge().getTriple().getSubject().isConcrete() &&
		 * isConcrete==0) {ij6++; jg.add(nfm.getKey()); } } if(aa.size()>=ij6 &&ij6>0)
		 * //for(EdgeOperator aaa:jg) {
		 * ////log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.
		 * size()); for(EdgeOperator j:jg) { if(!operators_BushyTreeRight.contains(j)) {
		 * operators_BushyTreeRight.addAll(jg); operators_BushyTreeLeft.addAll(jg); }
		 * aa.remove(j);
		 * 
		 * } //}
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * Iterator<List<EdgeOperator>> jgIterator7 = joinGroups2.values().iterator();
		 * List<EdgeOperator> jg1 = new ArrayList<EdgeOperator>();
		 * 
		 * while(jgIterator7.hasNext()) {int ij7=0; List<EdgeOperator> aa = new
		 * ArrayList<>(); aa=jgIterator7.next(); if(aa.size()>1 ) {
		 * for(Entry<EdgeOperator, HashSet<Integer>> nfm:newFormationM.entrySet()) { int
		 * isConcrete=0; for(List<EdgeOperator> joinG:JoinGroupsListExclusive) {
		 * for(EdgeOperator joinG1:joinG) {
		 * if(joinG1.getEdge().getTriple().getObject().equals(nfm.getKey().getEdge().
		 * getTriple().getSubject()) &&
		 * joinG1.getEdge().getTriple().getSubject().isConcrete()) isConcrete=1; } }
		 * 
		 * if(aa.contains(nfm.getKey()) &&
		 * !nfm.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nfm.getKey().getEdge().getTriple().getSubject().isConcrete() &&
		 * isConcrete==0) {ij7++; jg.add(nfm.getKey());
		 * 
		 * } } if(aa.size()>=ij7 &&ij7>0) //for(EdgeOperator aaa:jg) {
		 * ////log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.
		 * size()); for(EdgeOperator j:jg1) { if(!operators_BushyTreeRight.contains(j))
		 * { operators_BushyTreeRight.addAll(jg1); operators_BushyTreeLeft.addAll(jg1);
		 * } aa.remove(j); } //}
		 * 
		 * } }
		 */

		// ForkJoinPool fjp98 = new ForkJoinPool();
		// fjp98.submit(() -> futileTriple());
		// fjp98.shutdown();

		// ForkJoinPool fjp97 = new ForkJoinPool();
		// fjp97.submit(() -> refineTriple(newFormationM, operators_BushyTreeLeft,
		// operators_BushyTreeRight));
		// fjp97.shutdown();
		/*
		 * for (int i = 0; i < JoinGroupsListExclusive.size(); i++) { if
		 * (JoinGroupsListExclusive.get(i).size() == 1) {
		 * operators_BushyTreeRight.addAll(JoinGroupsListExclusive.get(i));
		 * operators_BushyTreeLeft.addAll(JoinGroupsListExclusive.get(i));
		 * 
		 * JoinGroupsListExclusive.remove(i); } }
		 */
//		for(List<EdgeOperator> e:JoinGroupsListExclusive)
//System.out.println("This is JoinGroupsListExclusive lastly:"+e);
		/*
		 * obtIterator=null; jg2Iterator=null; ax=null; obt=null; obtIterator =
		 * joinGroups2.values().iterator(); while(obtIterator.hasNext()) { int
		 * comparedSize=0; obt = obtIterator.next(); jg2Iterator =
		 * joinGroups2.values().iterator(); while(jg2Iterator.hasNext()) {
		 * List<EdgeOperator> jg2=jg2Iterator.next(); size=jg2.size();
		 * if(obt.size()<size) {
		 * 
		 * for(EdgeOperator jg22:jg2) { if(obt.contains(jg22)) comparedSize++; }
		 * ax.add(obt);
		 * //log.info("This is the size for whole1:"+obt.size()+"--"+size);;
		 * 
		 * obtIterator.remove();
		 * 
		 * }
		 * 
		 * //log.info("THis is exclussss1:"+JoinGroupsListExclusive);
		 * 
		 * 
		 * } }
		 */////////////////////////////////////////////////////////////////////////////

////logger.info("This is the task of eternity222222222:"+JoinGroupsListExclusive);

////logger.info("This is now operators_BushyTreeRight:"+operators_BushyTreeRight);

////////////////////////////////////////////////////////////////////////////

//logger.info("This is the task of eternity222222222:"+JoinGroupsListExclusive);

		List<EdgeOperator> JoinGroupsListExclusiveTemp1 = new Vector<>();
		for (EdgeOperator obt1 : operators_BushyTreeOrder)
			for (EdgeOperator jg2 : operators_BushyTreeRight)
				if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp1.contains(jg2))
							JoinGroupsListExclusiveTemp1.add(jg2);

		operators_BushyTreeRight.clear();
		operators_BushyTreeLeft.clear();
		operators_BushyTreeRight.addAll(JoinGroupsListExclusiveTemp1);
		operators_BushyTreeLeft.addAll(JoinGroupsListExclusiveTemp1);
//operators_BushyTreeRight.parallelStream();

/////////////////////////////Ordering JoinGroupsListExclusive ////////////////////////

//for(List<EdgeOperator> jgle1:JoinGroupsListExclusive)
////logger.info("This is now JoinGroupsListExclusive:"+jgle1);

//for(Entry<Integer, List<EdgeOperator>> jgle1:joinGroups2.entrySet())
////logger.info("This is now JoinGroups2:"+jgle1);

////logger.info("This is now the original order:"+operators_BushyTreeOrder);

////logger.info("This is JoinGroupsListExclusive:"+JoinGroupsListExclusive);
System.out.println("This is operators_BushyTreeLeft:"+operators_BushyTreeLeft);

		if (operators_BushyTreeLeft.size() > 0) {
			joinGroupsLeft = CreateBushyTreesLeft(operators_BushyTreeLeft, operators, operators_BushyTreeOrder);

			for (Entry<EdgeOperator, Integer> e : joinGroupsLeft.entries()) {
				// logger.info("This is the new group of queries123123
				// left:"+e.getKey()+"--"+e.getValue());
				if (!JoinGroupsListLeft.contains(e.getKey()))
					JoinGroupsListLeft.add(e.getKey());
			}

			Iterator<EdgeOperator> jgll = JoinGroupsListLeft.iterator();
			while (jgll.hasNext()) {
				com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator jglenl = jgll.next();
//if(jglenl.size()==1) {
				if (operators_BushyTreeRight.toString().contains(jglenl.toString())) {
					operators_BushyTreeRight.remove(jglenl);

				}
//}
			}
					for(EdgeOperator jgl:JoinGroupsListLeft)
			System.out.println("This i the  problem here99999999999:"+jgl);

			Iterator<List<EdgeOperator>> jgrrr = JoinGroupsListExclusive.iterator();
			while (jgrrr.hasNext()) {
				List<EdgeOperator> jglenr = jgrrr.next();
//if(jglenl.size()==1) {

				Iterator<EdgeOperator> jgr111 = operators_BushyTreeRight.iterator();
				while (jgr111.hasNext()) {
					if (jglenr.contains(jgr111.next())) {
						jgr111.remove();
						;

					}
				}
			}
			Iterator<EdgeOperator> jgrrl = JoinGroupsListLeft.iterator();
			while (jgrrl.hasNext()) {
				EdgeOperator jglenrl = jgrrl.next();
//if(jglenl.size()==1) {

				Iterator<EdgeOperator> jgr1112 = operators_BushyTreeRight.iterator();
				while (jgr1112.hasNext()) {
					if (jglenrl.equals(jgr1112.next())) {
						jgr1112.remove();
						;

					}
				}
			}
			if (operators_BushyTreeRight != null)
				joinGroupsRight = CreateBushyTreesRight(operators_BushyTreeRight, operators, operators_BushyTreeOrder);

			if (joinGroupsRight != null)
				for (Entry<EdgeOperator, Integer> e : joinGroupsRight.entries()) {
					// logger.info("This is the new group of
					// queries123123:"+e.getKey()+"--"+e.getValue());
					if (!JoinGroupsListRight.contains(e.getKey()))
						JoinGroupsListRight.add(e.getKey());
				}
		}
		///// logger.info("This is now right bushy tree:"+joinGroups1);

		
	//	System.out.println("This is minus material:" + operators_minus);
	//	if (ParaEng.Minus.contains("MINUS"))
	//		joinGroupsMinus1 = CreateBushyTreesExclusive(operators_minus, joinGroupsMinus, operators,
	//				operators_BushyTreeOrder);

		List<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator> itr;

		// for(int i=0;i<JoinGroupsList.size();i++)
		/*
		 * for(List<EdgeOperator> jglr:JoinGroupsListExclusive) for(EdgeOperator
		 * jglr1:jglr) if(JoinGroupsList.toString().contains(jglr1.toString())) {
		 * for(List<EdgeOperator> jgl:JoinGroupsList)
		 * 
		 * 
		 * }
		 */
		System.out.println("This is exclusive group tree:"+JoinGroupsListExclusive);

		System.out.println("This is left group tree:"+JoinGroupsListLeft);

		System.out.println("This is right group tree:"+JoinGroupsListRight);
		Iterator<List<EdgeOperator>> xyz = JoinGroupsListExclusive.iterator();
		while (xyz.hasNext()) {
			List<EdgeOperator> xyz1 = xyz.next();
			if (xyz1.size() == 0)
				xyz.remove();
		}

		//// logger.info("This is the new group list of
		//// JoinGroupsListRight:"+operators_BushyTreeRight);

//		//logger.info("This is the new group list of JoinGroupsListLeft:"+operators_BushyTreeLeft);

		// ExecOrder
		// logger.info("This is now
		// operators_BushyTreeRight:"+operators_BushyTreeRight);
		Map<List<EdgeOperator>, Integer> joinGroups2Temp1 = new HashMap<>();
		List<EdgeOperator> JoinGroupsListExclusiveTemp = new Vector<>();
		List<List<EdgeOperator>> JoinGroupsListExclusiveTempT = new Vector<>();
		/*
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) { for(List<EdgeOperator>
		 * jg2:JoinGroupsListExclusive) {
		 * 
		 * for(EdgeOperator jg22:jg2) {
		 * if(jg22.getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp.
		 * contains(jg22) ) JoinGroupsListExclusiveTemp.add(jg22); }
		 * 
		 * } if(!JoinGroupsListExclusiveTempT.contains(JoinGroupsListExclusiveTemp))
		 * JoinGroupsListExclusiveTempT.add(JoinGroupsListExclusiveTemp);
		 * JoinGroupsListExclusiveTemp =new Vector<>();
		 * 
		 * }
		 * 
		 * 
		 * // Map<List<EdgeOperator>, Integer> JoinGroupsListExclusiveTempTV =new
		 * ConcurrentHashMap<>();
		 * 
		 * // for(Entry<List<EdgeOperator>, Integer> jg2:joinGroups2.entrySet()) //
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) //
		 * if(jg2.getKey().get(0).getEdge().equals(obt1.getEdge())&&!
		 * JoinGroupsListExclusiveTempTV.containsKey(jg2.getKey()) ) //
		 * JoinGroupsListExclusiveTempTV.put(jg2.getKey(),jg2.getValue());
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //joinGroups2.clear();
		 * 
		 * // joinGroups2.putAll(JoinGroupsListExclusiveTempTV);
		 * 
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) for(Entry<List<EdgeOperator>,
		 * Integer> jg2:joinGroups2.entrySet()) if(jg2.getKey().size()>0)
		 * if(obt1.toString().equals(jg2.getKey().get(0).toString()))
		 * joinGroups2Temp1.put(jg2.getKey(),jg2.getValue());
		 * 
		 * joinGroups2.clear(); joinGroups2.putAll(joinGroups2Temp1);
		 */if (JoinGroupsListRight.size() > 0 || !JoinGroupsListRight.isEmpty()) {
			JoinGroupsListExclusiveTemp = new Vector<>();
			for (EdgeOperator jg2 : JoinGroupsListRight) {
				for (EdgeOperator obt1 : operators_BushyTreeOrder) {
					if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp.contains(jg2))
						JoinGroupsListExclusiveTemp.add(jg2);

				}

			}

			JoinGroupsListRight.clear();

			JoinGroupsListRight.addAll(JoinGroupsListExclusiveTemp);

		}
		if (JoinGroupsListLeft.size() > 0 || !JoinGroupsListLeft.isEmpty()) {

			JoinGroupsListExclusiveTemp = new Vector<>();
			for (EdgeOperator obt1 : operators_BushyTreeOrder) {
				for (EdgeOperator jg2 : JoinGroupsListLeft) {
					if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp.contains(jg2))
						JoinGroupsListExclusiveTemp.add(jg2);

				}

			}

			JoinGroupsListLeft.clear();

			JoinGroupsListLeft.addAll(JoinGroupsListExclusiveTemp);
		}
////logger.info("There is only one hashJoin");


			
		if (ParaEng.Minus.contains("MINUS") )
			for (Entry<List<EdgeOperator>, Integer> e : joinGroupsMinus1.entries()) {
				// logger.info("This is the new group of
				// queries:"+e.getKey()+"--"+e.getValue());
				if (e.getKey().size() > 0)
					JoinGroupsListMinus.add(e.getKey());

			}
		System.out.println("This is minus group tree:" + joinGroupsMinus1);
	//	LinkedHashSet<EdgeOperator> lhs = new LinkedHashSet<EdgeOperator>();
		if (!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())
		{
			/*		HashMap<String,Integer> Temp=new HashMap<>();
			
			Temp.putAll(ParaEng.OptionalOrder);
			
			
			Map<String, Integer> sortedMap = 
					Temp.entrySet().stream()
				    .sorted(Entry.comparingByValue())
				    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
				                              (e1, e2) -> e1, LinkedHashMap::new));
		//	int l=0;

			ParaEng.OptionalOrder.clear();
			ParaEng.OptionalOrder.putAll(sortedMap);
		*/
			if(!ParaEng.OptionalOrder.isEmpty()) {
		
//					for(int i9=0;i9<3;i9++) {

			       List<String> list = new ArrayList<String>();
			       List<String> record = new ArrayList<String>();

			       
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
			    		}
						System.out.println("This is the list22:"+record);

			            String fileName=record.toString().replace("[", "").replace("]","").replace("$", "?");
						System.out.println("This is the list33:"+fileName);

		        Set<List<EdgeOperator>> grouping = new HashSet<>();
		        List<EdgeOperator> grouping_sub = new ArrayList<>();	

	            String query="/mnt/hdd/hammad/hammad/query/"+fileName;;

	            String curQuery=null;
	            File file = new File(query);
	         try {
				 curQuery=  FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

	            
	//System.out.println("This is the file that is read:"+Arrays.toString(list.toArray()));     
int i=-1;
if(curQuery.contains("OPTIONAL")) {
String s9=curQuery.substring(curQuery.indexOf("OPTIONAL")).replaceAll("\\}", "").replaceAll("\\{", "").replaceAll("$","?").replaceAll(".(?m)^*\r?\n", "").replaceAll("\t","");				
s9=s9.replaceAll("\\$", "?");
s9=s9.replaceAll("$", "?");
s9=s9.replaceAll("OPTIONAL", "");
//System.out.println("This is the biggest biggest biggest123123123:"+operators_optional);

for(String s10:s9.split("\n")) {
	
		if(s10.contains("OPTIONAL"))
		{
			grouping_sub = new ArrayList<>();
		}
			else {
			//	System.out.println("This is the biggest biggest biggest:"+s10);
			
				String[] s11=s10.split(" ");
			//	for(String ss11:s11)
			//	System.out.println("This is the main problem right now:"+ss11);

				for(EdgeOperator it1:operators_optional) 
				{				for(i=0;i<s11.length;i+=1)
						
				//	System.out.println("This is the main problem right now0000000000:"+s11[i]);
				if((s11[i].startsWith("?") || s11[i].startsWith("\"")) && i+2<s11.length && !s11[i].contains(":")) {
							System.out.println("This is the main problem right now:"+s11[i]+"--"+s11[i+2]+"--"+it1.getEdge());
					if(it1.getEdge().getV1().equals(Vertex.create(StageGen.StringConversion(s11[i].substring(1)))) &&
							it1.getEdge().getV2().equals(Vertex.create(StageGen.StringConversion(s11[i+2].substring(1)))) )

				grouping_sub.add(it1);
				
				}
				}
			grouping.add(grouping_sub);
		}
	}
	System.out.println("This is greater good1111111:"+grouping);
}
				
for(List<EdgeOperator> g:grouping)					{
	if(g.size()>1)		
	joinGroupsOptional1 = CreateBushyTreesExclusive(g, joinGroupsOptional, operators,
					operators_BushyTreeOrder);
	else
	{
		joinGroupsLeftOptional = CreateBushyTreesLeft(g,  operators,
				operators_BushyTreeOrder);
		for (Entry<EdgeOperator, Integer> e : joinGroupsLeftOptional.entries()) {
			// logger.info("This is the new group of queries123123
			// left:"+e.getKey()+"--"+e.getValue());
			if (!JoinGroupsListLeftOptional.contains(e.getKey()))
				JoinGroupsListLeftOptional.add(e.getKey());
		}
	}
		
//		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
			for (Entry<List<EdgeOperator>, Integer> e : joinGroupsOptional1.entries()) {
				// logger.info("This is the new group of
				// queries:"+e.getKey()+"--"+e.getValue());
				if (e.getKey().size() > 0)
					JoinGroupsListOptional.add(e.getKey());

			}
	System.out.println("THis is optional JoinList:"+JoinGroupsListOptional);
	//	if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
		//	if (JoinGroupsListOptional.size() > 1) {
		//		CompleteOptional(newFormationM, operators, g);
		//	}
			//}
				//for(Entry<String, Integer> oo1:ParaEng.OptionalOrder.entrySet())
				//	for(String oo2:oo1.getKey().split(" ")) {
				//		if(oo2.startsWith("?"))
						//	for(EdgeOperator oo:operators_optional)
								//if(oo.getEdge().getV1().getNode())
				//	}
				}	
			}
		}
			else
			{
				System.out.println("This is the proposal:"+operators_optional);
				joinGroupsOptional1 = CreateBushyTreesExclusive(operators_optional, joinGroupsOptional, operators,
						operators_BushyTreeOrder);

			
//			if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
				for (Entry<List<EdgeOperator>, Integer> e : joinGroupsOptional1.entries()) {
					// logger.info("This is the new group of
					// queries:"+e.getKey()+"--"+e.getValue());
					if (e.getKey().size() > 0)
						JoinGroupsListOptional.add(e.getKey());

				}
			/*	List<EdgeOperator> temp1=new ArrayList<>();
				for(List<EdgeOperator> fj:JoinGroupsListExclusive)
					for(EdgeOperator fj1:fj)
					temp1.add(fj1);
				for(EdgeOperator fj:JoinGroupsListLeft)
					temp1.add(fj);
				for(EdgeOperator fj:JoinGroupsListRight)
					temp1.add(fj);
				for(EdgeOperator t:temp1)
				operators_optional.remove(t);
				*/
			System.out.println("This is optional group tree:" + joinGroupsOptional1);

		//	if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
				if (JoinGroupsListOptional.size() > 1) {
					CompleteOptional(newFormationM, operators, operators_optional);
				}
				
			}
		
		//}
		
		
		
		
		if (ParaEng.Minus.contains("MINUS"))
			if (JoinGroupsListMinus.size() > 1) {
				CompleteMinus(newFormationM, operators, operators_minus);
			}

		System.out.println("This is optional group tree:111" + joinGroupsOptional1);
		System.out.println("This is minus group tree:111" + joinGroupsMinus1);

//		//logger.info("There is only one hashJoin1");

		// Case1: If there is only one record with first element as BindJoin
		// Case2: If most upper record has BindJoin at start then compare it with
		// below one if the total cost of next record is less then let program swap
		// that record
		// Case3: Incase in a record first element is bindJoin and its cost with next
		// HashJoin
		// is less than 500 then swap both
//if(JoinGroupsListLeft!=null)
//	BindJoinCorrection(JoinGroupsListLeft);
//if(JoinGroupsListRight!=null)
//		BindJoinCorrection(JoinGroupsListRight);

		

		HashSet<List<EdgeOperator>> JoinGroupsListLeftTemp = new HashSet<>();
		// List<List<EdgeOperator>> JoinGroupsListRightTemp = new Vector<>();
		/*
		 * for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, Set<Binding>>
		 * el:StartBinding.entrySet()) StartBindingBJ.put(el.getKey(),el.getValue());
		 * //List<List<EdgeOperator>> JoinGroupsListRightTemp = new Vector<>();
		 * //List<List<EdgeOperator>> JoinGroupsListLeftTemp = new Vector<>();
		 * JoinGroupsListLeftTemp.add(JoinGroupsListLeft);
		 * JoinGroupsListLeftTemp.add(JoinGroupsListRight);
		 * JoinGroupsListLeftTemp.addAll(JoinGroupsListExclusive);
		 * if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())||!ParaEng.Union.isEmpty())
		 * JoinGroupsListLeftTemp.addAll(JoinGroupsListOptional);
		 * enableBindJoins(JoinGroupsListLeftTemp);//for(List<EdgeOperator> e:
		 * JoinGroupsList)
		 */ // ForkJoinPool fjp1 =new ForkJoinPool(6);
//fjp1.submit(()->{	
		/*
		 * if(JoinGroupsListRight.size()>0 || !JoinGroupsListRight.isEmpty())
		 * BindJoinCorrectionRight(JoinGroupsListRight,0);
		 * if(JoinGroupsListLeft.size()>0 || !JoinGroupsListLeft.isEmpty())
		 * BindJoinCorrectionLeft(JoinGroupsListLeft,0);
		 * 
		 * JoinGroupsListRightTemp.clear(); JoinGroupsListLeftTemp.clear();
		 * 
		 * if(JoinGroupsListLeftOptional.size()>0 ||
		 * !JoinGroupsListLeftOptional.isEmpty())
		 * BindJoinCorrectionLeft(JoinGroupsListLeftOptional,1);
		 * if(JoinGroupsListRightOptional.size()>0 ||
		 * !JoinGroupsListRightOptional.isEmpty())
		 * BindJoinCorrectionRight(JoinGroupsListRightOptional,1);
		 */// });
//	fjp1.shutdown();
		// Incase of 1 element reverse elements in list
		/*
		 * int NumberOfHashJoin=0; int HashJoinIndex=0; for(Entry<List<EdgeOperator>,
		 * Integer> e: joinGroups2.entrySet()) { for( EdgeOperator e1:e.getKey())
		 * if(e1.toString().contains("Hash")) {
		 * 
		 * NumberOfHashJoin++; HashJoinIndex = e.getValue(); } }
		 * 
		 * 
		 * // //logger.info("There is only one hashJoin2");
		 * 
		 * if(NumberOfHashJoin==1) { //logger.info("There is only one hashJoin");
		 * 
		 * 
		 * for(Entry<Integer, List<EdgeOperator>> e: joinGroups.entrySet()) {
		 * if(HashJoinIndex==e.getKey()) Collections.reverse( e.getValue()); } }
		 */
		// logger.info("This is the new group of queries just checking
		// left:"+JoinGroupsListLeft);

		// logger.info("This is the new group of queries just checking
		// right:"+JoinGroupsListRight);

		// logger.info("This is the new group of queries just checking
		// excl:"+JoinGroupsListExclusive);
		// if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
		// logger.info("This is the new group of optional queries just
		// checking:"+JoinGroupsListOptional);
		// if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
		// logger.info("This is the new group of optional queries just
		// checking:"+JoinGroupsListLeftOptional);
//	if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty()) 
		// logger.info("This is the new group of optional queries just
		// checking:"+JoinGroupsListRightOptional);

		List<List<EdgeOperator>> JoinGroupsListExclusiveTempH = new Vector<>();
//		List<List<EdgeOperator>> JoinGroupsListExclusiveTempB = new Vector<>();

		/*
		 * for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, Set<Binding>>
		 * el:StartBindingSet.entrySet()) { Set<Edge> v = new HashSet<>(); for(
		 * Entry<Set<Vertex>, Set<Edge>> el1:el.getKey().entrySet()) for(Edge
		 * e11:el1.getValue()) { for(List<EdgeOperator> jge:JoinGroupsListExclusive) {
		 * for(EdgeOperator jge1:jge) { if(e11.equals(jge1.getEdge())) {
		 * for(EdgeOperator jgee:jge) v.add(jgee.getEdge());
		 * 
		 * } } } Set<Edge> vDup=new HashSet<>(); for(Edge e111:el1.getValue())
		 * v.add(e111);
		 * 
		 * for(Edge v2:v) for(EdgeOperator boo:operators_BushyTreeOrder)
		 * if(boo.getEdge().equals(v2)) vDup.add(v2); el1.setValue(vDup); } }
		 */
		// if(xyz.isEmpty())
		//// logger.info("This is the new group list of queries0:"+StartBindingSet);
		// for (Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>> el
		// : StartBindingSet.entrySet())
		// StartBindingSetBJ.put(el.getKey(), el.getValue());
//for(Entry<ConcurrentHashMap<Set<Vertex>, Set<Edge>>, ArrayList<Binding>>  b:StartBindingSetBJ.entrySet())
		System.out.println("This is the new group time before:" + LocalTime.now());
		Set<EdgeOperator> Temp = new HashSet<>();
	
		List<List<EdgeOperator>> Temp00 = new ArrayList<>();
		
		for (List<EdgeOperator> jge : JoinGroupsListExclusive)
			{
			Temp00.add(jge.parallelStream().distinct().collect(Collectors.toList()));
			System.out.println("This is jgeeeeeeeeeeewwwww before:" + jge);
			}
		
		JoinGroupsListExclusive.clear();
		JoinGroupsListExclusive.addAll(Temp00);
		
		for(EdgeOperator obo:operators_BushyTreeOrder1)
		for(EdgeOperator jgll:JoinGroupsListLeft)
		if(obo.getEdge().equals(jgll.getEdge()))
			Temp.add(jgll);
		JoinGroupsListLeft.clear();
		JoinGroupsListLeft.addAll(Temp);
		
	//	for(EdgeOperator jgll:JoinGroupsListLeft)
		//	System.out.println("This is the new group after:" + jgll);
	
		
		Temp = new HashSet<>();
		
		for(EdgeOperator obo:operators_BushyTreeOrder1)
			for(EdgeOperator jgll:JoinGroupsListRight)
				if(obo.getEdge().equals(jgll.getEdge()))
								Temp.add(jgll);
		JoinGroupsListRight.clear();
		JoinGroupsListRight.addAll(Temp);
		
	Temp = new HashSet<>();
		
		for(EdgeOperator obo:operators_BushyTreeOrder1)
			for(EdgeOperator jgll:JoinGroupsListRightOptional)
				if(obo.getEdge().equals(jgll.getEdge()))
								Temp.add(jgll);
		JoinGroupsListRightOptional.clear();
		JoinGroupsListRightOptional.addAll(Temp);
		
Temp = new HashSet<>();
		
		for(EdgeOperator obo:operators_BushyTreeOrder1)
			for(EdgeOperator jgll:JoinGroupsListLeftOptional)
				if(obo.getEdge().equals(jgll.getEdge()))
									Temp.add(jgll);

	//	finalResultLeft.clear();
		JoinGroupsListLeftOptional.clear();
		JoinGroupsListLeftOptional.addAll(Temp);

		
		
		if(!ParaEng.Union.isEmpty()){
			
			for (EdgeOperator jge : operators_BushyTreeOrder)
						System.out.println("This is jgeeeeeeeeeeewwwww before:" + jge);
			
			for (EdgeOperator jge : JoinGroupsListLeftOptional)
				System.out.println("This is jgeeeeeeeeeeewwwww213213123 before:" + jge);
	
			
			JoinGroupsListLeftTemp.clear();
			JoinGroupsListLeftTemp.add(JoinGroupsListLeft);
			JoinGroupsListLeftTemp.add(JoinGroupsListRight);
			JoinGroupsListLeftTemp.addAll(JoinGroupsListExclusive);

			ForkJoinPool fjp111 = new ForkJoinPool();
			fjp111.submit(() -> enableBindJoins(JoinGroupsListLeftTemp, JoinGroupsListExclusive,
					JoinGroupsListLeft, JoinGroupsListRight)).join();
			fjp111.shutdown();

			JoinGroupsListLeftTemp.clear();
			
			
			JoinGroupsListLeftTemp.addAll(JoinGroupsListOptional);
			JoinGroupsListLeftTemp.add(JoinGroupsListLeftOptional);
			JoinGroupsListLeftTemp.add(JoinGroupsListRightOptional);


			ForkJoinPool fjp1111 = new ForkJoinPool();
			fjp1111.submit(() ->enableBindJoinsOptional(JoinGroupsListLeftTemp, JoinGroupsListExclusive,
					JoinGroupsListLeft, JoinGroupsListRight,JoinGroupsListOptional,
					JoinGroupsListLeftOptional, JoinGroupsListRightOptional)).join();
			fjp1111.shutdown();
			for (EdgeOperator jge : operators_BushyTreeOrder)
				System.out.println("This is jgeeeeeeeeeeewwwww after:" + jge);
	

			
			for (EdgeOperator jge : JoinGroupsListLeftOptional)
				System.out.println("This is jgeeeeeeeeeeewwwww213213123 after:" + jge);
		
		}
		else if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ) {
			JoinGroupsListLeftTemp.clear();
			JoinGroupsListLeftTemp.add(JoinGroupsListLeft);
			JoinGroupsListLeftTemp.add(JoinGroupsListRight);
			JoinGroupsListLeftTemp.addAll(JoinGroupsListExclusive);

			JoinGroupsListLeftTemp.addAll(JoinGroupsListOptional);
			JoinGroupsListLeftTemp.add(JoinGroupsListLeftOptional);
			JoinGroupsListLeftTemp.add(JoinGroupsListRightOptional);

			ForkJoinPool fjp111 = new ForkJoinPool();
			fjp111.submit(() -> enableBindJoinsOptional(JoinGroupsListLeftTemp, JoinGroupsListExclusive,
					JoinGroupsListLeft, JoinGroupsListRight,JoinGroupsListOptional,
					JoinGroupsListLeftOptional, JoinGroupsListRightOptional)).join();
			fjp111.shutdown();

		//	ForkJoinPool fjp1111 = new ForkJoinPool();
		//	fjp1111.submit(() -> enableBindJoins(JoinGroupsListLeftTemp, JoinGroupsListOptional,
		//			JoinGroupsListLeftOptional, JoinGroupsListRightOptional)).join();
		//	fjp1111.shutdown();
			for (List<EdgeOperator> jge : JoinGroupsListExclusive)
				System.out.println("This is jgeeeeeeeeeeewwwww:" + jge);

		}

		else		if (ParaEng.Minus.contains("MINUS")) {
			JoinGroupsListLeftTemp.clear();
			JoinGroupsListLeftTemp.add(JoinGroupsListLeft);
			JoinGroupsListLeftTemp.add(JoinGroupsListRight);
			JoinGroupsListLeftTemp.addAll(JoinGroupsListExclusive);

			JoinGroupsListLeftTemp.addAll(JoinGroupsListMinus);
			JoinGroupsListLeftTemp.add(JoinGroupsListLeftMinus);
			JoinGroupsListLeftTemp.add(JoinGroupsListRightMinus);

			ForkJoinPool fjp111 = new ForkJoinPool();
			fjp111.submit(() -> enableBindJoins(JoinGroupsListLeftTemp, JoinGroupsListMinus,
					JoinGroupsListLeftMinus, JoinGroupsListRightMinus)).join();
			fjp111.shutdown();
			for (List<EdgeOperator> jge : JoinGroupsListExclusive)
				System.out.println("This is jgeeeeeeeeeeewwwww:" + jge);

		}
		else {
			JoinGroupsListLeftTemp.clear();

		
			JoinGroupsListLeftTemp.add(JoinGroupsListLeft);
			JoinGroupsListLeftTemp.add(JoinGroupsListRight);
			JoinGroupsListLeftTemp.addAll(JoinGroupsListExclusive);

			System.out.println("THis is oooooo:" + JoinGroupsListLeftTemp);
			System.out.println("This is going to work00000");
			ForkJoinPool fjp11 = new ForkJoinPool();
			fjp11.submit(() -> enableBindJoins(JoinGroupsListLeftTemp, JoinGroupsListExclusive, JoinGroupsListLeft,
					JoinGroupsListRight)).join();
			fjp11.shutdown();

			
		}
		
	List<List<EdgeOperator>> JoinGroupsListLeftTemp1= new ArrayList<>();
	List<EdgeOperator> JoinGroup1= new ArrayList<>();

	JoinGroupsListLeftTemp1.addAll(JoinGroupsListExclusive);	
		JoinGroupsListLeftTemp1.add(JoinGroupsListLeft);
		JoinGroupsListLeftTemp1.add(JoinGroupsListRight);
		JoinGroupsListLeftTemp1.addAll(JoinGroupsListOptional);
		JoinGroupsListLeftTemp1.add(JoinGroupsListLeftOptional);
		JoinGroupsListLeftTemp1.add(JoinGroupsListRightOptional);
		for(EdgeOperator bo:operators_BushyTreeOrder1) {

		for(List<EdgeOperator >jgll:JoinGroupsListLeftTemp1)
			if(jgll.size()>0)
				for(EdgeOperator jgl:jgll)
			if(jgl.getEdge().equals(bo.getEdge()))
				JoinGroup1.add(jgl);
		}
		ForkJoinPool fjp11 = new ForkJoinPool();
		fjp11.submit(() -> enableBindJoinsOptionals(JoinGroup1)).join();
		fjp11.shutdown();
		
		for (List<EdgeOperator> jge : JoinGroupsListExclusive)
			System.out.println("This is jgeeeeeeeeeeewwwww:" + jge);

		for (List<EdgeOperator> jge : JoinGroupsListOptional)
			System.out.println("This is jgeeeeeeeeeeewwwwwOptional:" + jge);

		for (List<EdgeOperator> jge : JoinGroupsListMinus)
			System.out.println("This is jgeeeeeeeeeeewwwwwOptional:" + jge);

//ForkJoinPool fjp1 = new ForkJoinPool();
//fjp1.submit(()->
//BindJoinCorrection(JoinGroupsListLeftTemp));
//fjp1.shutdown();

//System.out.println("This is StartBindingSetBJ:"+StartBindingSetBJ);
//fjp11.shutdown();
		// enableBindJoins(JoinGroupsListRightTemp);//for(List<EdgeOperator> e:
		// JoinGroupsList) {
		// enableBindJoins(JoinGroupsListLeftTemp);//for(List<EdgeOperator> e:
		// JoinGroupsList) {
		// enableBindJoins(JoinGroupsListExclusive);//for(List<EdgeOperator> e:
		// JoinGroupsList) {

//	//logger.info("There is only one hashJoin3");

		// //logger.info("..........:"+JoinGroups0);
		// JoinGroupsListLeftTemp.clear();
		// JoinGroupsListLeftTemp.add(JoinGroupsListLeft);
		// JoinGroupsListLeftTemp.add(JoinGroupsListRight);
		// JoinGroupsListLeftTemp.addAll(JoinGroupsListExclusive);
		// JoinGroupsListLeftTemp.addAll(JoinGroupsListOptional);
		// JoinGroupsListLeftTemp.add(JoinGroupsListLeftOptional);
		// JoinGroupsListLeftTemp.add(JoinGroupsListRightOptional);

		// JoinGroupsListExclusiveTempH = new Vector<>();
		// JoinGroupsListExclusiveTempB = new Vector<>();
		// HashMap<HashSet<List<EdgeOperator>>, Integer> ExecOrder =
		// ExecutionOrder(JoinGroupsListLeftTemp);

		// JoinGroupsListExclusiveTempT.clear();
		// for (Entry<HashSet<List<EdgeOperator>>, Integer> obt1 : ExecOrder.entrySet())
		// for (List<EdgeOperator> jg2 : JoinGroupsListExclusive) {
		// for (List<EdgeOperator> obt2 : obt1.getKey())
		// for (EdgeOperator obt3 : obt2) {
		// if (!jg2.isEmpty())
		// if (jg2.get(0).getEdge().equals(obt3.getEdge())) {
		// JoinGroupsListExclusiveTempH.add(jg2);
		// System.out.println("This is where is the
		// problem:"+JoinGroupsListExclusiveTempH);

		// }
		// }
		// if(jg2.get(0).getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp.contains(jg2)
		// &&jg2.get(0).getStartVertex()!=null)
		// {
		// JoinGroupsListExclusiveTempB.add(jg2);
		// }
		// }

//		JoinGroupsListExclusive.clear();

//		JoinGroupsListExclusive.addAll(JoinGroupsListExclusiveTempH);

		/*
		 * for(List<EdgeOperator> jge:JoinGroupsListExclusive)
		 * System.out.println("This is the required output before:"+jge); for(Vertex
		 * ht:PublicHashVertex) {
		 * 
		 * List<EdgeOperator> temp1=new ArrayList<>(); List<EdgeOperator> temp2=new
		 * ArrayList<>(); for(List<EdgeOperator> jgl:JoinGroupsListExclusive) {
		 * for(EdgeOperator jgll:jgl) if(jgll.getStartVertex()!=null)
		 * if(jgll.getStartVertex().equals(ht)) { temp1.addAll(jgl);
		 * //temp2.addAll(jgl); temp2.add(jgll); if(temp2.size()>0 || temp2 !=null)
		 * for(EdgeOperator jgll1:jgl) if(!jgll1.equals(jgll)) temp2.add(jgll1); }
		 * 
		 * } JoinGroupsListExclusive.remove(temp1); JoinGroupsListExclusive.add(temp2);
		 * 
		 * }
		 */

		// Set<EdgeOperator> JoinGroupsListExclusiveTempA = new HashSet<>();
		/*
		 * JoinGroupsListExclusiveTempA.clear(); for(HashSet<List<EdgeOperator>>
		 * obt1:ExecOrder.keySet()) for(List<EdgeOperator> obt2:obt1) for(EdgeOperator
		 * obt3:obt2) for(EdgeOperator jg2:JoinGroupsListLeft) {
		 * if(jg2.getEdge().equals(obt3.getEdge())&&!JoinGroupsListExclusiveTempA.
		 * contains(jg2)) JoinGroupsListExclusiveTempA.add(jg2);
		 * 
		 * } JoinGroupsListLeft.clear();
		 * JoinGroupsListLeft.addAll(JoinGroupsListExclusiveTempA);
		 *//*
			 * 
			 * JoinGroupsListExclusiveTempA.clear(); //for(HashSet<List<EdgeOperator>>
			 * obt1:ExecOrder.keySet()) for(EdgeOperator obt1: operators_BushyTreeOrder)
			 * for(EdgeOperator jg2:JoinGroupsListRight) {
			 * if(jg2.equals(obt1)&&!JoinGroupsListExclusiveTempA.contains(jg2))
			 * JoinGroupsListExclusiveTempA.add(jg2);
			 * 
			 * }
			 */// JoinGroupsListRight.clear();

//	JoinGroupsListRight.addAll(JoinGroupsListExclusiveTempA);

		// JoinGroupsListExclusive.addAll(JoinGroupsListExclusiveTempB);

//	System.out.println("1111..........777:");

//	for(List<EdgeOperator> jgyy:JoinGroupsListExclusive)
//System.out.println("1111..........:"+jgyy);

		// Set<List<EdgeOperator>> tempp = new HashSet<>();
		// for (List<EdgeOperator> jgl : JoinGroupsListExclusive)
		// tempp.add(jgl);
		// JoinGroupsListExclusive.clear();
		// JoinGroupsListExclusive.addAll(tempp);

		// for(List<EdgeOperator> jge:JoinGroupsListExclusive)
		// System.out.println("This is jge jge jge jge jge jge:"+jge);
		Iterator<List<EdgeOperator>> jglll = JoinGroupsListExclusive.iterator();
		while (jglll.hasNext()) {
			List<EdgeOperator> jglenl = jglll.next();
//if(jglenl.size()==1) {

			Iterator<EdgeOperator> jgl111 = JoinGroupsListLeft.iterator();
			while (jgl111.hasNext()) {
				EdgeOperator z = jgl111.next();

				if (jglenl.toString().contains(z.toString())) {
					jgl111.remove();
				}
			}

//}
		}

		Iterator<List<EdgeOperator>> jglll1 = JoinGroupsListExclusive.iterator();
		while (jglll1.hasNext()) {
			List<EdgeOperator> jglenl = jglll1.next();
//if(jglenl.size()==1) {

			Iterator<EdgeOperator> jgl111 = JoinGroupsListRight.iterator();
			while (jgl111.hasNext()) {
				EdgeOperator z = jgl111.next();

				if (jglenl.toString().contains(z.toString())) {
					jgl111.remove();
				}
			}

//}
		}
	Set<List<EdgeOperator>>JoinGroupsListExclusiveTemp11 = new HashSet<>();
	Set<EdgeOperator> JoinGroupsListExclusiveTemp112 = new HashSet<>();
	
	for(List<EdgeOperator>jgl:JoinGroupsListExclusive) {
		if(!JoinGroupsListExclusiveTemp11.contains(jgl))
		JoinGroupsListExclusiveTemp11.add(jgl);
	}
	JoinGroupsListExclusive.clear();
	
	JoinGroupsListExclusive.addAll(JoinGroupsListExclusiveTemp11);
	
	for(EdgeOperator jgl:JoinGroupsListRight) {
		if(!JoinGroupsListExclusiveTemp112.toString().contains(jgl.toString()))
		JoinGroupsListExclusiveTemp112.add(jgl);
	}
	for(EdgeOperator jgr:JoinGroupsListExclusiveTemp112)
		{ 
		System.out.println("These are the edgeoperator right:"+jgr);
	//	for(int i=0; i<jgr.toString().length(); i++)
	//	{
	//	  int asciiValue = jgr.toString().charAt(i);
	//	  System.out.print(" "+jgr.toString().charAt(i) + "=" + asciiValue);
	//	}
		}  
		
	JoinGroupsListRight.clear();
	for(EdgeOperator jgr:JoinGroupsListExclusiveTemp112)
	if(!JoinGroupsListRight.toString().contains(jgr.toString()))
	JoinGroupsListRight.addAll(JoinGroupsListExclusiveTemp112);

	JoinGroupsListExclusiveTemp112.clear();
	for(EdgeOperator jgl:JoinGroupsListLeft) {
		JoinGroupsListExclusiveTemp112.add(jgl);
	}
	JoinGroupsListLeft.clear();
	
	for(EdgeOperator jgr:JoinGroupsListExclusiveTemp112)
		if(!JoinGroupsListLeft.toString().contains(jgr.toString()))
		JoinGroupsListLeft.addAll(JoinGroupsListExclusiveTemp112);
	
//	for(EdgeOperator jgr:JoinGroupsListRight)
//	System.out.println("These are the edgeoperator right:"+jgr);
	JoinGroupsListExclusiveTemp112.clear();
	//	System.out.println("This is the new group time:" + LocalTime.now());
		
		

		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty()) {
			List<String> ab= new ArrayList<>();
			length = "";
			vv = null;
			
			Iterator<List<EdgeOperator>> l5 = JoinGroupsListOptional.iterator();
			while (l5.hasNext()) {
				List<EdgeOperator> l4 = l5.next();
// BindingSet xz=l1.next();
				length = l4.toString();
				vv = length.split(" ");
				{
					for (String v : vv)
					if (v.startsWith("(")) {
				//	System.out.println("This is very great doing:"+v);
						if (!v.contains("http") && v.contains(":") && (v.contains("$") || v.contains("?")))
							if(!ab.contains(v.substring(2, v.indexOf(":"))))
							ab.add(v.substring(2, v.indexOf(":")));
								
							else
										continue;
//break;
					}
				//} else
				//		continue;
			}
				if(!OptionalHeaders.contains(ab))
				{Collections.sort(ab); 
				OptionalHeaders.add(ab);
				}
				ab=new ArrayList<>();
				
			}
			
//break;

		//	System.out.println("This is very great doing21321312321:"+OptionalHeaders);
			
		//	Set<Set<String>> a1 = new HashSet<>();
		//	a1.addAll(OptionalHeaders);
		//	OptionalHeaders.clear();
		//	OptionalHeaders.addAll(a1);

			ab= new ArrayList<>();
			length = "";
			vv = null;
			
						
			Iterator<EdgeOperator> l51 = JoinGroupsListLeftOptional.iterator();
			while (l51.hasNext()) {
				EdgeOperator l4 = l51.next();
// BindingSet xz=l1.next();
				length = l4.toString();
				vv = length.split(" ");
				{
					for (String v : vv)
					if (v.startsWith("(")) {
			//		System.out.println("This is very great doing:"+v);
						if (!v.contains("http") && v.contains(":") && (v.contains("$") || v.contains("?")))
							if(!ab.contains(v.substring(2, v.indexOf(":"))))
							ab.add(v.substring(2, v.indexOf(":")));
								
							else
										continue;
//break;
					}
				//} else
				//		continue;
			}
				if(!OptionalHeadersLeft.contains(ab))
				{Collections.sort(ab); 
				OptionalHeadersLeft.add(ab);
				}
				ab=new ArrayList<>();
				
			}
			

			/*		length = "";
			vv = null;
			l3 = null;
			l3 = JoinGroupsListLeftOptional.iterator();
			while (l3.hasNext()) {
// BindingSet xz=l1.next();
//  while(l1.hasNext()) {
				length = l3.next().toString();
				vv = length.split(" ");
				for (String v : vv)
					if (v.startsWith("(")) {
						if (!v.contains("http") && v.contains(":") && (v.contains("$") || v.contains("?")))
							OptionalHeadersLeft.add(v.substring(2, v.indexOf(":")));

//break;
					} else
						continue;
//break;
			}
*/
			
			ab= new ArrayList<>();
			length = "";
			vv = null;
			
			Iterator<EdgeOperator> l511 = JoinGroupsListRightOptional.iterator();
			while (l511.hasNext()) {
				EdgeOperator l4 = l511.next();
// BindingSet xz=l1.next();
				length = l4.toString();
				vv = length.split(" ");
				{
					for (String v : vv)
					if (v.startsWith("(")) {
			//.out.println("This is very great doing:"+v);
						if (!v.contains("http") && v.contains(":") && (v.contains("$") || v.contains("?")))
							if(!ab.contains(v.substring(2, v.indexOf(":"))))
							ab.add(v.substring(2, v.indexOf(":")));
								
							else
										continue;
//break;
					}
				//} else
				//		continue;
			}
				if(!OptionalHeadersRight.contains(ab))
				{Collections.sort(ab); 
				OptionalHeadersRight.add(ab);
				}
				ab=new ArrayList<>();
				
			}
		//	Collections.sort(OptionalHeaders); 
		}

		
		if (ParaEng.Minus.contains("MINUS")) {

			length = "";
			vv = null;
			Iterator<List<EdgeOperator>> l5 = JoinGroupsListMinus.iterator();
			while (l5.hasNext()) {
				List<EdgeOperator> l4 = l5.next();
// BindingSet xz=l1.next();

				length = l4.toString();
				vv = length.split(" ");
				for (String v : vv)
					if (v.startsWith("(")) {
						if (!v.contains("http") && v.contains(":") && (v.contains("$") || v.contains("?")))
							MinusHeaders.add(v.substring(1, v.indexOf(":")));

//break;
					} else
						continue;
			}
//break;

			
			Set<String> a1 = new HashSet<>();
			a1.addAll(MinusHeaders);
			MinusHeaders.clear();
			MinusHeaders.addAll(a1);
			length = "";
			vv = null;
			l3 = null;
			l3 = JoinGroupsListLeftMinus.iterator();
			while (l3.hasNext()) {
// BindingSet xz=l1.next();
//  while(l1.hasNext()) {
				length = l3.next().toString();
				vv = length.split(" ");
				for (String v : vv)
					if (v.startsWith("(")) {
						if (!v.contains("http") && v.contains(":") && (v.contains("$") || v.contains("?")))
							MinusHeadersLeft.add(v.substring(1, v.indexOf(":")));

//break;
					} else
						continue;
//break;
			}

			length = "";
			vv = null;

			l3 = JoinGroupsListRightMinus.iterator();
			while (l3.hasNext()) {
// BindingSet xz=l1.next();
//  while(l1.hasNext()) {
				length = l3.next().toString();
				vv = length.split(" ");
				for (String v : vv)
					if (v.startsWith("(")) {
						if (!v.contains("http") && v.contains(":") && (v.contains("$") || v.contains("?")))
							MinusHeadersRight.add(v.substring(1, v.indexOf(":")));

//break;
					} else
						continue;
//break;
			}
//break;
		}

		
		 
	  
		/*
		 * LinkedListMultimap<EdgeOperator, String> TreeOrderOpt =
		 * LinkedListMultimap.create(); for (Entry<HashSet<List<EdgeOperator>>, Integer>
		 * cde : linkingTreeDup.entrySet()) for (List<EdgeOperator> cde1 : cde.getKey())
		 * { for (List<EdgeOperator> jge : JoinGroupsListExclusive) if
		 * (jge.equals(cde1)) { // ForkJoinPool fjp111= new ForkJoinPool(); //
		 * fjp111.submit(()->{ TreeOrder.put(cde1.get(0), "E");// }).join(); //
		 * fjp111.shutdown(); } for (List<EdgeOperator> jge : JoinGroupsListOptional) if
		 * (jge.equals(cde1)) {// ForkJoinPool fjp111= new ForkJoinPool(); //
		 * fjp111.submit(()->{ TreeOrder.put(cde1.get(0), "O");// }).join(); //
		 * fjp111.shutdown(); }
		 * 
		 * for (EdgeOperator cde2 : cde1) { if (JoinGroupsListRight.contains(cde2)) { //
		 * ForkJoinPool fjp111= new ForkJoinPool(); // fjp111.submit(()->{
		 * TreeOrder.put(cde2, "R");// }).join(); // } // fjp111.shutdown(); }
		 * 
		 * if (JoinGroupsListLeft.contains(cde2)) { // ForkJoinPool fjp111= new
		 * ForkJoinPool(); // try { // fjp111.submit(()->{ TreeOrder.put(cde2, "L");//
		 * }).join(); // } catch (InterruptedException e1) { // TODO Auto-generated
		 * catch block // e1.printStackTrace(); // } catch (ExecutionException e1) { //
		 * TODO Auto-generated catch block // e1.printStackTrace(); // } //
		 * fjp111.shutdown(); } if (JoinGroupsListRightOptional.contains(cde2)) { { //
		 * ForkJoinPool fjp111= new ForkJoinPool(); // try { // fjp111.submit(()->{
		 * TreeOrder.put(cde2, "OR");// }).join(); // } catch (InterruptedException e1)
		 * { // TODO Auto-generated catch block // e1.printStackTrace(); // } catch
		 * (ExecutionException e1) { // TODO Auto-generated catch block //
		 * e1.printStackTrace(); // } // fjp111.shutdown(); } } if
		 * (JoinGroupsListLeftOptional.contains(cde2)) { { // ForkJoinPool fjp111= new
		 * ForkJoinPool(); // try { // fjp111.submit(()->{ TreeOrder.put(cde2, "OL");//
		 * }).join(); // } catch (InterruptedException e1) { // TODO Auto-generated
		 * catch block // e1.printStackTrace(); // } catch (ExecutionException e1) { //
		 * TODO Auto-generated catch block // e1.printStackTrace(); // } //
		 * fjp111.shutdown(); } } } }
		 * 
		 * LinkedListMultimap<EdgeOperator, String> TreeOrderTemp =
		 * LinkedListMultimap.create(); LinkedListMultimap<EdgeOperator, String>
		 * TreeOrderOptTemp = LinkedListMultimap.create(); for (EdgeOperator obt1 :
		 * operators_BushyTreeOrder) { for (Entry<EdgeOperator, String> to :
		 * TreeOrder.entries()) if (to.getKey().toString().equals(obt1.toString()) &&
		 * !TreeOrderTemp.toString().contains(obt1.toString()))
		 * TreeOrderTemp.put(to.getKey(), to.getValue()); }
		 * 
		 * TreeOrder.clear(); TreeOrder.putAll(TreeOrderTemp);
		 * 
		 * for (EdgeOperator obt1 : operators_BushyTreeOrder) { for (Entry<EdgeOperator,
		 * String> to : TreeOrderOpt.entries()) if
		 * (to.getKey().toString().equals(obt1.toString()) &&
		 * !TreeOrderOptTemp.toString().contains(obt1.toString()))
		 * TreeOrderOptTemp.put(to.getKey(), to.getValue()); }
		 * 
		 * TreeOrderOpt.clear(); TreeOrderOpt.putAll(TreeOrderOptTemp);
		 * 
		 * TreeOrder.putAll(TreeOrderOpt); for (Entry<EdgeOperator, String> to :
		 * TreeOrder.entries()) System.out.println("This is the required order:" + to);
		 */
//new	StatementGroupOptimizer2(null).meetOther(query);
//System.out.println("This is the new group list of StartBindingSetBJ:"+StartBindingSetBJ);

		// System.out.println("This is the new group list of
		// StartBindingSet:"+StartBindingSet);

		// }

//		while(operators_independent!=null)
//			ExecuteJoinsDep(opt,operators);
//		ExecuteJoinsDep(opt1,operators_independent);

	
		List<EdgeOperator> JoinGroupsListExclusiveTemp111 = new ArrayList<>(); 
		  for(EdgeOperator obt1:operators_BushyTreeOrder1) 
			  for(EdgeOperator jg2:JoinGroupsListRight)
		  if(jg2.getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp111.contains(jg2) ) 
			  JoinGroupsListExclusiveTemp111.add(jg2);
		  
		  //operators_BushyTreeRight.clear(); 
		  //operators_BushyTreeLeft.clear(); //
		  JoinGroupsListRight.clear();
		  JoinGroupsListRight.addAll(JoinGroupsListExclusiveTemp111);
		  
		  
		  JoinGroupsListExclusiveTemp111 =new ArrayList<>(); 
		  for(EdgeOperator obt1:operators_BushyTreeOrder1) 
			  for(EdgeOperator jg2:JoinGroupsListLeft)
		  if(jg2.getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp111.contains(jg2) ) 
			  JoinGroupsListExclusiveTemp111.add(jg2);
		  JoinGroupsListLeft.clear();
		  JoinGroupsListLeft.addAll(JoinGroupsListExclusiveTemp111);
		 
		  
		  LinkedHashMap <Edge,Integer> Order= new LinkedHashMap <>();
		 int p=0;
		  for(EdgeOperator obt:operators_BushyTreeOrder1) {
			  Order.put(obt.getEdge(), p);
			  p++;
		  }
		List<List<EdgeOperator>>  JoinGroupsListExclusiveTemp2 =new ArrayList<>();
		 JoinGroupsListExclusiveTemp111 =new ArrayList<>();
		 	  for(List<EdgeOperator> jg2:JoinGroupsListExclusive)
			  {	
		 		  int k=0;
		 		  for(Entry<Vertex, LinkedHashSet<Binding>>  sb:StartBinding123.entrySet())
		 			 for(EdgeOperator jg21:jg2)
		 			  if(jg21.getEdge().getV1().equals(sb.getKey()) ||
		 					 jg21.getEdge().getV2().equals(sb.getKey()))
		 		  {
		 			  System.out.println("This is the issue of the issues");
		 			  if(!sb.getValue().isEmpty() || sb.getValue().size()>0)
		 			  {k=1; break;}
		 		  if(k==1) {
		 			 JoinGroupsListExclusiveTemp111.addAll(jg2);
		 			 break;
		 		  }
		 		  }
		 		 if(k==1) {
		 			k=0;
		 			 continue;
		 		  }
		 		 for(Entry<Edge, Integer> obt1:Order.entrySet()) 
		 		
				  	  for(EdgeOperator jg21:jg2)
		  if(jg21.getEdge().equals(obt1.getKey())&&!JoinGroupsListExclusiveTemp111.contains(jg21) ) 
			  if(obt1.getValue()==0)
			  JoinGroupsListExclusiveTemp111.add(new HashJoin(jg21.getEdge()));
			  else
				  JoinGroupsListExclusiveTemp111.add(jg21);
				  	
				  JoinGroupsListExclusiveTemp2.add(JoinGroupsListExclusiveTemp111);
				  JoinGroupsListExclusiveTemp111= new ArrayList<>();
			  }
			  
		 	 JoinGroupsListExclusive.clear();
		 	JoinGroupsListExclusive.addAll(JoinGroupsListExclusiveTemp2);
		
			  JoinGroupsListExclusiveTemp2 = new ArrayList<>();
			  for(EdgeOperator obt1:operators_BushyTreeOrder1) 
				  for(List<EdgeOperator> jg2:JoinGroupsListExclusive)
					  if(jg2.size()>0)
			  if(jg2.get(0).getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp2.contains(jg2) ) 
				  JoinGroupsListExclusiveTemp2.add(jg2);
			  
			  
			  
			  JoinGroupsListExclusive.clear();
			  JoinGroupsListExclusive.addAll(JoinGroupsListExclusiveTemp2);

		// BushyTreeSize = operators_BushyTree.size();
		
ExecuteJoins(opt1, operators_BushyTree);
	}

	@Override
	protected Binding moveToNextBinding() {
		// //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
		// execBGP2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: ");
		//// logger.info("These is the list of operators in ExecuteJoins44444");

		return (Binding) results.next();
	}

	@Override
	protected void closeIterator() {
		// //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
		// execBGP2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: ");
//		//logger.info("These is the list of operators in ExecuteJoins12312321");

		g.getEdges().clear();
	//	results = null;
	}

	public void ExecuteJoins(Optimiser opt, List<EdgeOperator> operators) {
		// for(EdgeOperator o:operators)
		// System.out.println("These are operators>>>>>>>>>>>>>>>>>>>>:"+o);
		// for(EdgeOperator jgl:JoinGroupsListRight)
		// System.out.println("These are list right>>>>>>>>>>>>>>>>>>>>:"+jgl);
		// for(EdgeOperator jgr:JoinGroupsListLeft)
		// System.out.println("These are list left>>>>>>>>>>>>>>>>>>>>:"+jgl);

		// while (operators != null) {
		Set<Vertex> containers =new HashSet<>();
		if(!ParaEng.Projection.isEmpty() || ParaEng.Projection.size() >0) {
//for(List<EdgeOperator>jgl:JoinGroupsListExclusive)	
//		for(String pj:ParaEng.Projection)
//				if(jgl.toString().contains(pj))
	//				for(EdgeOperator jgll:jgl)
	//				{	
	//					containers.add(jgll.getEdge().getV1());
	//				containers.add(jgll.getEdge().getV2());
	//				}
			for(List<EdgeOperator>jgl:JoinGroupsListExclusive)	
				for(EdgeOperator jgll:jgl)
						{	
					if(ParaEng.Projection.contains(jgll.getEdge().getV1().getNode().toString()) || ParaEng.Projection.toString().contains(jgll.getEdge().getV2().getNode().toString()))
							{	
							containers.add(jgll.getEdge().getV1());
						containers.add(jgll.getEdge().getV2());
						}
						}
			for(List<EdgeOperator>jgl:JoinGroupsListOptional)	
				for(EdgeOperator jgll:jgl)
						{	
					if(ParaEng.Projection.contains(jgll.getEdge().getV1().getNode().toString()) || ParaEng.Projection.toString().contains(jgll.getEdge().getV2().getNode().toString()))
							{	
							containers.add(jgll.getEdge().getV1());
						containers.add(jgll.getEdge().getV2());
						}
						}
//for(EdgeOperator jgl:JoinGroupsListLeft)	
//	System.out.println("THis is the final instrumental010101:"+jgl.getEdge().getV1().getNode()+"--"+jgl.getEdge().getV2().getNode());		

	for(EdgeOperator jgl:JoinGroupsListLeft)	

			if(ParaEng.Projection.contains(jgl.getEdge().getV1().getNode().toString()) || ParaEng.Projection.toString().contains(jgl.getEdge().getV2().getNode().toString()))
			{
					containers.add(jgl.getEdge().getV1());
				containers.add(jgl.getEdge().getV2());
			//	System.out.println("THis is the final instrumental:"+jgl);		
			}	

	for(EdgeOperator jgl:JoinGroupsListRight)	

		if(ParaEng.Projection.contains(jgl.getEdge().getV1().getNode().toString()) || ParaEng.Projection.toString().contains(jgl.getEdge().getV2().getNode().toString()))
		{
				containers.add(jgl.getEdge().getV1());
			containers.add(jgl.getEdge().getV2());
	//		System.out.println("THis is the final instrumental:"+jgl);		
		}	

	for(EdgeOperator jgl:JoinGroupsListLeftOptional)	

		if(ParaEng.Projection.contains(jgl.getEdge().getV1().getNode().toString()) || ParaEng.Projection.toString().contains(jgl.getEdge().getV2().getNode().toString()))
		{
				containers.add(jgl.getEdge().getV1());
			containers.add(jgl.getEdge().getV2());
		//	System.out.println("THis is the final instrumental:"+jgl);		
		}	

for(EdgeOperator jgl:JoinGroupsListRightOptional)	

	if(ParaEng.Projection.contains(jgl.getEdge().getV1().getNode().toString()) || ParaEng.Projection.toString().contains(jgl.getEdge().getV2().getNode().toString()))
	{
			containers.add(jgl.getEdge().getV1());
		containers.add(jgl.getEdge().getV2());
//		System.out.println("THis is the final instrumental:"+jgl);		
	}	

		}

		 System.out.println("These are list list list>>>>>>>>>>>>>>>>>>>>:"+ParaEng.Projection);

		 System.out.println("These are list left>>>>>>>>>>>>>>>>>>>>:"+containers);

		/*
		if(!ParaEng.Projection.isEmpty() || ParaEng.Projection.size() >0) {
			Set<List<EdgeOperator>> JoinGroupExclusiveTemp = new HashSet<>();
			Set<EdgeOperator> JoinGroupLeftTemp = new HashSet<>();
			Set<EdgeOperator> JoinGroupRightTemp = new HashSet<>();
					
		Iterator<List<EdgeOperator>> jgli = JoinGroupsListExclusive.iterator();
		while(jgli.hasNext()) {
		List<EdgeOperator> jgl	=jgli.next();
	    ;
		for(Vertex c:containers) {
		if(jgl.toString().contains(c.toString()))
			JoinGroupExclusiveTemp.add(jgl);
			}
				}
		JoinGroupsListExclusive.clear();
		JoinGroupsListExclusive.addAll(JoinGroupExclusiveTemp);
		
		Iterator<EdgeOperator> jgll = JoinGroupsListLeft.iterator();
		while(jgll.hasNext()) {
		EdgeOperator jgl	=jgll.next();
		for(Vertex c:containers) {
		if(jgl.toString().contains(c.toString()))
			{		
		JoinGroupLeftTemp.add(jgl);
			}
								 }
										}
		JoinGroupsListLeft.clear();
		JoinGroupsListLeft.addAll(JoinGroupLeftTemp);
				

		
		Iterator<EdgeOperator> jglr = JoinGroupsListRight.iterator();
		while(jglr.hasNext()) {
		EdgeOperator jgl	=jglr.next();
	    ;
		for(Vertex c:containers) {
		if(jgl.toString().contains(c.toString()))
			JoinGroupRightTemp.add(jgl);
			}
				}
		JoinGroupsListRight.clear();
		JoinGroupsListRight.addAll(JoinGroupRightTemp);
		}		*/
		//// logger.info("Tis is the latest EdgeOperator
		//// List:"+prepareBushyTree(operators));
		// Iterator<EdgeOperator> x = operators.iterator();
		LinkedHashSet<EdgeOperator> opr = new LinkedHashSet<>();
		LinkedHashSet<EdgeOperator> oprOpt = new LinkedHashSet<>();
		LinkedHashSet<EdgeOperator> oprOptLeft = new LinkedHashSet<>();
		LinkedHashSet<EdgeOperator> oprOptRight = new LinkedHashSet<>();
		LinkedHashSet<EdgeOperator> oprMin = new LinkedHashSet<>();
		LinkedHashSet<EdgeOperator> oprMinLeft = new LinkedHashSet<>();
		LinkedHashSet<EdgeOperator> oprMinRight = new LinkedHashSet<>();
		
		
		LinkedHashSet<EdgeOperator> oprRight = new LinkedHashSet<>();
		LinkedHashSet<EdgeOperator> oprLeft = new LinkedHashSet<>();

		// Because now a Literal/URI is placed at beginning of list if its cost is less
		// than 500
		// for hashJoin perform
		Iterator<List<EdgeOperator>> it = JoinGroupsListExclusive.iterator();
		while (it.hasNext()) {
			List<EdgeOperator> itn = it.next();
			if (itn.isEmpty() || itn.size() == 0)
				it.remove();
		}
		
		
		for (List<EdgeOperator> e : JoinGroupsListExclusive) {
			Map<EdgeOperator, Integer> t = new HashMap<>();
		
			 System.out.println("This is the final result before>>>>>>>>>>>>>>>>>>>>>>>"+e+"--"+e.get(0));
			 System.out.println("This is the final result after>>>>>>>>>>>>>>>>>>>>>>>"+ExhOptimiser.lowestElement.getEdge().toString()+"--"+e.get(0).getEdge().toString());
			if(ExhOptimiser.lowestElement.getEdge().toString().equals(e.get(0).getEdge().toString()))
				{
				finalResult.put(e.get(0), null);
				t.put(e.get(0), 1);
				exchangeOfElements.put(e.get(0), t);
				}
			else 
			    {
				t.put(e.get(0), 1);
				finalResult.put(e.get(0), null);
				}
			exchangeOfElements.put(e.get(0), t);
			// TreeType.put(e.get(0), 1);
			orderingElements.put(findFirstNonLiteral(e), e);
			// System.out.println("This is the final result
			// before>>>>>>>>>>>>>>>>>>>>>>>"+e);

			// System.out.println("This is the final
			// result>>>>>>>>>>>>>>>>>>>>>>>"+finalResult);

		}

		for (EdgeOperator e : JoinGroupsListRight) 
		{
			Map<EdgeOperator, Integer> t = new HashMap<>();
			t.put(e, 0);
			exchangeOfElements.put(e, t);
			finalResultRight.put(e, null);
		}

		for (EdgeOperator e : JoinGroupsListLeft) 
		{
			Map<EdgeOperator, Integer> t = new HashMap<>();
			t.put(e, 0);
			exchangeOfElements.put(e, t);
			finalResultLeft.put(e, null);
			// System.out.println("This is finalResultLeft opp:" + finalResultLeft);
		}
		
		for(Entry<EdgeOperator, Map<EdgeOperator, Integer>> eoe:exchangeOfElements.entrySet())
		System.out.println("This is here now:"+eoe);


		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty()) {
			Iterator<List<EdgeOperator>> it1 = JoinGroupsListOptional.iterator();
			while (it1.hasNext()) {
				List<EdgeOperator> itn = it1.next();
				if (itn.isEmpty() || itn.size() == 0)
					it1.remove();
			}
			for (List<EdgeOperator> e : JoinGroupsListOptional) {
				Map<EdgeOperator, Integer> t = new HashMap<>();
				if (e.size() > 0) {
					t.put(findFirstNonLiteral(e), 1);
					exchangeOfElements.put(e.get(0), t);
					finalResultOptional.put(findFirstNonLiteral(e), null);
					// System.out.println("this is JoinGroupsListOptional:"+e);
				}
				// System.out.println("this is JoinGroupsListOptional111:"+finalResultOptional);

			}
		}
		
		
		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
			for (EdgeOperator e : JoinGroupsListLeftOptional) {
				Map<EdgeOperator, Integer> t = new HashMap<>();
				t.put(e, 0);
				exchangeOfElements.put(e, t);
				finalResultLeftOptional.put(e, null);
			}

		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
			for (EdgeOperator e : JoinGroupsListRightOptional) {
				Map<EdgeOperator, Integer> t = new HashMap<>();
				t.put(e, 0);
				exchangeOfElements.put(e, t);
				finalResultRightOptional.put(e, null);
			}

		if (ParaEng.Minus.contains("MINUS")) {
			Iterator<List<EdgeOperator>> it1 = JoinGroupsListMinus.iterator();
			while (it1.hasNext()) {
				List<EdgeOperator> itn = it1.next();
				if (itn.isEmpty() || itn.size() == 0)
					it1.remove();
			}
			for (List<EdgeOperator> e : JoinGroupsListMinus) {
				Map<EdgeOperator, Integer> t = new HashMap<>();
				if (e.size() > 0) {
					t.put(findFirstNonLiteral(e), 1);
					exchangeOfElements.put(e.get(0), t);
					finalResultMinus.put(findFirstNonLiteral(e), null);
					// System.out.println("this is JoinGroupsListOptional:"+e);
				}
				// System.out.println("this is JoinGroupsListOptional111:"+finalResultOptional);

			}
		}
		if (ParaEng.Minus.contains("MINUS")) 
			for (EdgeOperator e : JoinGroupsListLeftMinus) {
				Map<EdgeOperator, Integer> t = new HashMap<>();
				t.put(e, 0);
				exchangeOfElements.put(e, t);
				finalResultLeftMinus.put(e, null);
			}

		if (ParaEng.Minus.contains("MINUS")) 
			for (EdgeOperator e : JoinGroupsListRightMinus) {
				Map<EdgeOperator, Integer> t = new HashMap<>();
				t.put(e, 0);
				exchangeOfElements.put(e, t);
				finalResultRightMinus.put(e, null);
			}


		System.out.println("This is here now111:");

		for (List<EdgeOperator> e : JoinGroupsListExclusive) {
			opr.add(e.get(0));
		}
		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())

			for (List<EdgeOperator> e : JoinGroupsListOptional) {
				if (e.size() > 0)
					oprOpt.add(e.get(0));

			}

		if (ParaEng.Minus.contains("MINUS"))

			for (List<EdgeOperator> e : JoinGroupsListMinus) {
				if (e.size() > 0)
					oprMin.add(e.get(0));

			}
		for (EdgeOperator e : JoinGroupsListRight) {
			oprRight.add(e);
		}


		for (EdgeOperator e : JoinGroupsListLeft) {
			oprLeft.add(e);
		}
		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())

			for (EdgeOperator e : JoinGroupsListRightOptional) {
				oprOptRight.add(e);
			}
		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())

			for (EdgeOperator e : JoinGroupsListLeftOptional) {
				oprOptLeft.add(e);
			}

		if (ParaEng.Minus.contains("MINUS"))

			for (EdgeOperator e : JoinGroupsListRightMinus) {
				oprMinRight.add(e);
			}
		if (ParaEng.Minus.contains("MINUS"))

			for (EdgeOperator e : JoinGroupsListLeftMinus) {
				oprMinLeft.add(e);
			}

		System.out.println("This is here now333333:");

		// es =
		// Executors.newCachedThreadPool();//newCachedThreadPool(Runtime.getRuntime().availableProcessors());

		// opr.add(findFirstNonLiteral(e));
//synchronized(finalResult) {		

//			//logger.info("11111111111111111111This this this this this this this this this this:"+LocalTime.now());

		// //logger.info("000000000000000000000000This this this this this this this
		// this this this:"+LocalTime.now());
		// CompletableFuture<String> cfa = CompletableFuture.supplyAsync(() -> );
		// CompletableFuture<String> cfb = CompletableFuture.supplyAsync(() -> );
		// CompletableFuture<String> cfc = CompletableFuture.supplyAsync(() ->);

		// ExecutorService executor = Executors.newWorkStealingPool();

//			    Set<Callable<String>> callables = new HashSet<Callable<String>>();
		

		for (Entry<EdgeOperator, List<Binding>> t : finalResult.entrySet())
			System.out.println("This is the ttttttttttt1111111111111111:" + t);

		System.out.println("This is current problem:" + JoinGroupsListExclusive);
		/*		for (Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf : StartBindingFinal.entrySet()) {
			t1 = new ArrayList<>();
			t2 = new ArrayList<>();
			t3 = new ArrayList<>();
			int l = 0;
			
			 * for (Entry<Edge, Vertex> sbf1 : sbf.getKey().entrySet()) for
			 * (List<EdgeOperator> jgg : JoinGroupsListExclusive) { for (EdgeOperator jgg1 :
			 * jgg) {
			 * 
			 * if (jgg1.toString().contains("Bind") &&
			 * jgg1.getStartVertex().equals(sbf1.getValue()) &&
			 * !jgg1.getEdge().equals(sbf1.getKey())) { t1.addAll(jgg); t2.addAll(jgg);
			 * t2.remove(jgg1); t3.add(jgg1); t3.addAll(t2); l = 1; break; } if (l == 1)
			 * break; } if (l == 1) break; }
			 
			
			/*			if (!t1.isEmpty() || t1.size() > 0) {
				// System.out.println("This is the final jglelelelelelele232322lel:"+t1);
				// System.out.println("This is the final
				// jglelelelelelele232322lesdfdsfsdfl:"+t3);

				JoinGroupsListExclusive.remove(t1);
				if (!JoinGroupsListExclusive.contains(t3)) {
					JoinGroupsListExclusive.add(t3);
					for (EdgeOperator rr : t1)
						exchangeOfElements.remove(rr);
					Map<EdgeOperator, Integer> t = new HashMap<>();
					t.put(findFirstNonLiteral(t3), 1);
					exchangeOfElements.put(t3.get(0), t);
				}
				for (List<EdgeOperator> tt1 : JoinGroupsListExclusive)
					for (EdgeOperator tt : tt1) {
						Iterator<EdgeOperator> xz = operators_BushyTreeOrder.iterator();
						while (xz.hasNext())
							if (tt.getEdge().equals(xz.next().getEdge()))
								xz.remove();
					}

				for (List<EdgeOperator> tt : JoinGroupsListExclusive)
					for (EdgeOperator tt1 : tt)
						operators_BushyTreeOrder.add(tt1);
				finalResult.remove(t1.get(0));
				if (!JoinGroupsListExclusive.contains(t3)) {
					for (List<EdgeOperator> jge : JoinGroupsListExclusive)
						finalResult.put(findFirstNonLiteral(jge), null);
				}
			}
		}
	*/

		for (EdgeOperator eo1 : operators_BushyTreeOrder1)
			System.out.println("This is the ttttttttttt22222222222222:" + eo1);
		
		
//for(EdgeOperator ue:urik_element) {
	//operators_BushyTreeOrder1.remove(ue);
	//operators_BushyTreeOrder.remove(ue);
	//operators_BushyTreeOrder1.removeAll(Collections.singletonList(ue));
	//operators_BushyTreeOrder.removeAll(Collections.singletonList(ue));

//}

//Iterator<EdgeOperator> obt = operators_BushyTreeOrder1.iterator();
//while(obt.hasNext())
//	for(EdgeOperator ue1:urik_element) {
	///	if(obt.next().contains(ue1))
	//	if(obt.next().equals(ue1))
//			obt.remove();
//	}

//Iterator<EdgeOperator> obt1 = operators_BushyTreeOrder.iterator();
//while(obt1.hasNext())
//	for(EdgeOperator ue1:urik_element) {
	//	if(obt.next().contains(ue1))
//		if(obt1.next().equals(ue1))
//			obt1.remove();
	//}
//operators_BushyTreeOrder1.remove(ue);
	//operators_BushyTreeOrder.remove(ue);
//	operators_BushyTreeOrder1.removeAll(Collections.singletonList(ue));
//	operators_BushyTreeOrder.removeAll(Collections.singletonList(ue));
	//}
/*
for (EdgeOperator eo1 : urik_element)
	System.out.println("This is the ttttttttttt11111111111111:" + eo1);

for (EdgeOperator eo1 : operators_BushyTreeOrder1)
	System.out.println("This is the ttttttttttt22222222222222:" + eo1);

		List<EdgeOperator> TempEO = new ArrayList<>();
TempEO.addAll(urik_element);
TempEO.addAll(operators_BushyTreeOrder1);
operators_BushyTreeOrder1.clear();
operators_BushyTreeOrder1.addAll(TempEO);
TempEO.clear();	
TempEO.addAll(urik_element);
TempEO.addAll(operators_BushyTreeOrder);
operators_BushyTreeOrder.clear();
operators_BushyTreeOrder.addAll(TempEO);
TempEO.clear();	

*/
		for(EdgeOperator jgll:JoinGroupsListLeft)
			System.out.println("This is the new group before:" + jgll);
		

		List<EdgeOperator> TempEO = new ArrayList<>();
	
		for (EdgeOperator eo1 : operators_BushyTreeOrder1)
			for (EdgeOperator eo : operators_BushyTreeOrder)
				if (eo1.getEdge().equals(eo.getEdge())) {				
					TempEO.add(eo);
				}

		operators_BushyTreeOrder.clear();
		operators_BushyTreeOrder.addAll(TempEO);

		
		
//		for (Entry<EdgeOperator, List<Binding>> t : finalResult.entrySet())
//			System.out.println("This is the ttttttttttt22222222222222:" + t);

//		for (List<EdgeOperator> jgle : JoinGroupsListExclusive)
//			System.out.println("This is the final jglelelelelelelelel:" + jgle);
		// int n = 0;

		// ForkJoinPool fjp = new ForkJoinPool();
		// fjp.submit(()->{

			HashJoin.AllEdges.addAll(JoinGroupsListLeft);
			HashJoin.AllEdges.addAll(JoinGroupsListRight);
		for (List<EdgeOperator> jge : JoinGroupsListExclusive)
			HashJoin.AllEdges.addAll(jge);
		for (List<EdgeOperator> jge : JoinGroupsListOptional)
			HashJoin.AllEdges.addAll(jge);

		
			HashJoin.AllEdges.addAll(JoinGroupsListLeftOptional);
			HashJoin.AllEdges.addAll(JoinGroupsListRightOptional);

		for (List<EdgeOperator> jge : JoinGroupsListMinus)
			HashJoin.AllEdges.addAll(jge);
    		HashJoin.AllEdges.addAll(JoinGroupsListLeftMinus);
	    	HashJoin.AllEdges.addAll(JoinGroupsListRightMinus);

		List<EdgeOperator>	Temp = new ArrayList<>();
		
		System.out.println("This is the new group time before:" + LocalTime.now());
		for(EdgeOperator obo:operators_BushyTreeOrder1)
		for(EdgeOperator jgll:JoinGroupsListLeft)
		if(obo.getEdge().equals(jgll.getEdge()))
			if(!Temp.contains(jgll))
			    Temp.add(jgll);
		
		
		JoinGroupsListLeft.clear();
		JoinGroupsListLeft.addAll(Temp);
		
		for(EdgeOperator jgll:JoinGroupsListLeft)
			System.out.println("This is the new group after:" + jgll);
	
		
		Temp = new ArrayList<>();
		
		for(EdgeOperator obo:operators_BushyTreeOrder1)
			for(EdgeOperator jgll:JoinGroupsListRight)
				if(obo.getEdge().equals(jgll.getEdge()))
					if(!Temp.contains(jgll))						
					Temp.add(jgll);
		
		
		JoinGroupsListRight.clear();
		JoinGroupsListRight.addAll(Temp);
		
	Temp = new ArrayList<>();
		
		for(EdgeOperator obo:operators_BushyTreeOrder1)
			for(EdgeOperator jgll:JoinGroupsListRightOptional)
				if(obo.getEdge().equals(jgll.getEdge()))
					if(!Temp.contains(jgll))
					Temp.add(jgll);
		
		
		JoinGroupsListRightOptional.clear();
		JoinGroupsListRightOptional.addAll(Temp);
		
Temp = new ArrayList<>();
		
		for(EdgeOperator obo:operators_BushyTreeOrder1)
			for(EdgeOperator jgll:JoinGroupsListLeftOptional)
				if(obo.getEdge().equals(jgll.getEdge()))
					if(!Temp.contains(jgll))				
					Temp.add(jgll);

		
	//	finalResultLeft.clear();
		JoinGroupsListLeftOptional.clear();
		JoinGroupsListLeftOptional.addAll(Temp);

	//	for(EdgeOperator obo:operators_BushyTreeOrder1)
		//	System.out.println("This is the obo of obo:"+obo);
			
        Temp.clear();		
		for(EdgeOperator jgl:JoinGroupsListLeft)
			for(EdgeOperator ue1:urik_element)
				if(jgl.getEdge().equals(ue1.getEdge()))
					if(!Temp.contains(jgl))
					Temp.add(jgl);
		
		
		if(!Temp.isEmpty()) {
		Iterator<EdgeOperator> obt1 = JoinGroupsListLeft.iterator();
        while(obt1.hasNext())
        {		
        	EdgeOperator i11 = obt1.next();
for(EdgeOperator ue1:Temp) {	
	System.out.println("THis is the removal process:"+i11.getEdge().toString()+"--"+(ue1.getEdge().toString()));
if(i11.getEdge().toString().equals(ue1.getEdge().toString()))
					obt1.remove();
			}

        }
        TempEO.clear();	
		TempEO.addAll(Temp);
		TempEO.addAll(JoinGroupsListLeft);
		JoinGroupsListLeft.clear();
		JoinGroupsListLeft.addAll(TempEO);		
		for(EdgeOperator jgl:JoinGroupsListLeft)
			finalResultLeft.put(jgl, null);
		
		}

		
//		JoinGroupsListLeftOptional.clear();
//		JoinGroupsListLeftOptional.addAll(Temp);
	
		for(List<EdgeOperator> ed:JoinGroupsListOptional)
		for(EdgeOperator ed1:ed)
		OptionalAll.add(ed1);
		for(List<EdgeOperator> ed:JoinGroupsListMinus)
		for(EdgeOperator ed1:ed)
		OptionalAll.add(ed1);
		for(EdgeOperator ed:JoinGroupsListLeftOptional)
		OptionalAll.add(ed);
		for(EdgeOperator ed:JoinGroupsListRightOptional)
		OptionalAll.add(ed);
		for(EdgeOperator ed:JoinGroupsListLeftMinus)
		OptionalAll.add(ed);
		for(EdgeOperator ed:JoinGroupsListRightMinus)
		OptionalAll.add(ed);
		
		LinkedHashMap<EdgeOperator, List<Binding>> Final = new LinkedHashMap<>();
		LinkedHashMap<EdgeOperator, List<Binding>> Temp1 = new LinkedHashMap<>();
		LinkedHashMap<EdgeOperator, List<Binding>> Temp2 = new LinkedHashMap<>();
		LinkedHashMap<EdgeOperator, List<Binding>> TempBind = new LinkedHashMap<>();
		LinkedHashMap<EdgeOperator, List<Binding>> TempBind1 = new LinkedHashMap<>();
		List<EdgeOperator> TempDone = new ArrayList<>();
		List<EdgeOperator> TempDoneHash = new ArrayList<>();
				
		LinkedHashMap<EdgeOperator, List<Binding>> TempHash = new LinkedHashMap<>();

		Temp1.putAll(finalResult);
		Temp1.putAll(finalResultLeft);
		Temp1.putAll(finalResultRight);

		for(Entry<EdgeOperator, List<Binding>> t9:Temp1.entrySet())
			System.out.println("This is running now normal:"+t9);

		System.out.println("This is the new group list of JoinGroupsListRight:" + JoinGroupsListRight);
		System.out.println("This is the new group list of JoinGroupsListLeft:" + JoinGroupsListLeft);
		System.out.println("This is the new group list of JoinGroupsListExclusive:" + JoinGroupsListExclusive);
		System.out.println("This is the new group list of JoinGroupsListRightOptional:" + JoinGroupsListRightOptional);
		System.out.println("This is the new group list of JoinGroupsListLeftOptional:" + JoinGroupsListLeftOptional);
		System.out.println("This is the new group list of JoinGroupsListOptional:" + JoinGroupsListOptional);
		System.out.println("This is the new group list of JoinGroupsListRightMinus:" + JoinGroupsListRightMinus);
		System.out.println("This is the new group list of JoinGroupsListLeftMinus:" + JoinGroupsListLeftMinus);
		System.out.println("This is the new group list of JoinGroupsListMinus:" + JoinGroupsListMinus);
		
		for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet())
			System.out.println("This is the ttttttttttt:" + t);
	/*	for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet())
			for (Entry<EdgeOperator, List<Binding>> t11 : Temp1.entrySet())
				if ( t.getKey().toString().contains("Bind") && t11.getKey().toString().contains("Bind")
						&& !t11.getKey().equals(t.getKey()) ) {
					if (t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV1())
							|| t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV2())) {
						if (!Temp2.containsKey(t11.getKey()) && !Temp2.containsKey(t.getKey()))
							Temp2.put(t.getKey(), t.getValue());
						Temp2.put(t11.getKey(), t11.getValue());

					}
				}
		for (Entry<EdgeOperator, List<Binding>> t : Temp2.entrySet())
			Temp1.remove(t.getKey());
		Temp1.putAll(Temp2);*/
		System.out.println("This is current problem finalResult000000000000:" + TempBind);
		System.out.println("This is current problem finalResult222222222222:" + TempBind);

		//		for (EdgeOperator bo : operators_BushyTreeOrder)
//			System.out.println("This is final problem finalResult:" + bo);

		for (EdgeOperator bo : operators_BushyTreeOrder)
			for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet()) {
				// System.out.println("This is processing:"+t.getKey()+"--"+bo);
				if (bo.toString().equals(t.getKey().toString()) && t.getKey().toString().contains("Hash"))
					TempHash.put(t.getKey(), t.getValue());
			}

		PartitionedExecutions(TempHash,TempHash, "Norm");

		for (EdgeOperator bo : operators_BushyTreeOrder)
			for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet())
				if (bo.toString().equals(t.getKey().toString()) && t.getKey().toString().contains("Bind"))
					TempBind1.put(t.getKey(), t.getValue());

		for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet())
			for (Entry<EdgeOperator, List<Binding>> t11 : Temp1.entrySet())

				if (t.getKey().toString().contains("Bind") && t11.getKey().toString().contains("Bind")
						&& !t11.getKey().equals(t.getKey())) {
					if (t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV1())
							|| t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV2())) {
						for (List<EdgeOperator> jggl : JoinGroupsListExclusive) {
							int k = 0;
							for (EdgeOperator jggl1 : jggl) {
								if (jggl1.getEdge().getV1().equals(t.getKey().getStartVertex())
										|| jggl1.getEdge().getV2().equals(t.getKey().getStartVertex())) {
									for (Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> gst : StartBindingFinal
											.entrySet())
										for (Entry<Edge, Vertex> gst1 : gst.getKey().entries())
											if (jggl1.getEdge().equals(gst1.getKey())) {
												TempBind.put(findFirstNonLiteral(jggl), null);
												TempBind1.remove(findFirstNonLiteral(jggl));
												System.out.println("This is the new group list of JoinGroupsListLeft000000:" + JoinGroupsListLeft);
												
												PartitionedExecutions(TempBind,TempBind, "Norm");
												k = 1;
												break;
											}
									if (k == 1)
										break;
								}
								if (k == 1)
									break;

							}
							if (k == 1)
								break;

						}

												for (EdgeOperator td : TempDone)
							if (!TempBind.containsKey(td)) {
								PartitionedExecutions(TempBind,TempBind, "Norm");
								TempBind.put(t11.getKey(), t11.getValue());
								TempBind.put(t.getKey(), t.getValue());
								Iterator<Entry<EdgeOperator, List<Binding>>> tb1 = TempBind1.entrySet().iterator();
								while (tb1.hasNext()) {
									EdgeOperator tt = tb1.next().getKey();
									if (tt.equals(t.getKey()) || tt.equals(t11.getKey()))
										tb1.remove();
								}
							}
						// TempBind1.remove(t.getKey());
						// TempBind1.remove(t11.getKey());

						for (Entry<EdgeOperator, List<Binding>> et : TempBind.entrySet())
							TempDone.add(et.getKey());
					}
				}
		for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet())
			for (Entry<EdgeOperator, List<Binding>> t11 : Temp1.entrySet())
		if (t.getKey().toString().contains("Bind") && t11.getKey().toString().contains("Bind")
				&& !t11.getKey().equals(t.getKey())) {
			adjustEdgeOperator( TempDone,TempBind,TempBind1,t,t11,JoinGroupsListLeft);	
		}
		
		System.out.println("This is current problem finalResult88888888:" + TempBind);

		for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet())
			for (Entry<EdgeOperator, List<Binding>> t11 : Temp1.entrySet())
		if (t.getKey().toString().contains("Bind") && t11.getKey().toString().contains("Bind")
				&& !t11.getKey().equals(t.getKey())) {
			if (t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV1())
					|| t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV2())) {
					adjustEdgeOperator( TempDone,TempBind,TempBind1,t,t11,JoinGroupsListRight);
			}
		}
		
		for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet())
			for (Entry<EdgeOperator, List<Binding>> t11 : Temp1.entrySet())
		if (t.getKey().toString().contains("Bind") && t11.getKey().toString().contains("Bind")
				&& !t11.getKey().equals(t.getKey())) {
			if (t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV1())
					|| t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV2())) {
					adjustEdgeOperator( TempDone,TempBind,TempBind1,t,t11,JoinGroupsListLeftOptional);
			}
		}

		for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet())
			for (Entry<EdgeOperator, List<Binding>> t11 : Temp1.entrySet())
		if (t.getKey().toString().contains("Bind") && t11.getKey().toString().contains("Bind")
				&& !t11.getKey().equals(t.getKey())) {
			if (t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV1())
					|| t.getKey().getStartVertex().equals(t11.getKey().getEdge().getV2())) {
					adjustEdgeOperator( TempDone,TempBind,TempBind1,t,t11,JoinGroupsListRightOptional);
			}
		}
		// for(EdgeOperator td:TempDone)
		// if(!TempBind1.containsKey(td))
		System.out.println("This is the new group list of JoinGroupsListLeft333333333:" + TempBind1);
		
		PartitionedExecutions(TempBind1,TempBind1, "Norm");

		// for(EdgeOperator bo:operators_BushyTreeOrder)
		// System.out.println("This is ordered:"+bo);

//		for (Entry<EdgeOperator, List<Binding>> bo : TempHash.entrySet())
//			System.out.println("This is Hash:" + bo);

//		for (Entry<EdgeOperator, List<Binding>> bo : TempBind.entrySet())
//			System.out.println("This is Bind:" + bo);
		// System.out.println("This is TempHash:" + Temp);
	//	for(EdgeOperator ob:operators_BushyTreeOrder)
		//	if(ob.getEdge().getV1().getNode().isURI())	
	//		urim.replace(ob.getEdge().getV1(),null);
	//		else if((ob.getEdge().getV2().getNode().isURI())	)
	//			urim.replace(ob.getEdge().getV2(),null);
		if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
		{
		Temp1 = new LinkedHashMap<>();
		TempBind = new LinkedHashMap<>();
		TempHash = new LinkedHashMap<>();

		Temp1.putAll(finalResultOptional);
		Temp1.putAll(finalResultLeftOptional);

		Temp1.putAll(finalResultRightOptional);
for(Entry<EdgeOperator, List<Binding>> t9:Temp1.entrySet())
	System.out.println("This is running now:"+t9);


//if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
//{	
	//System.out.println("This is optional material:" + oo.getEdge().getV1().getNode()+"--"+ oo.getEdge().getV2().getNode());
List<EdgeOperator> TempOrder=new ArrayList<>();
List<EdgeOperator> TempOrder1=new ArrayList<>();
List<EdgeOperator> TempOrder3=new ArrayList<>();
if(!ParaEng.InnerFilter.isEmpty())
{	TempOrder3.addAll(operators_BushyTreeOrder);
 
for(Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>> er:ParaEng.InnerFilter.entrySet())	
{	for(Entry<String, List<Binding>> er1:er.getValue().entrySet())
for(EdgeOperator to:operators_BushyTreeOrder)
	if(to.toString().contains(er1.getKey()))
		TempOrder.add(to);

for(Entry<String, List<Binding>> er1:er.getKey().entrySet())
for(EdgeOperator to:operators_BushyTreeOrder)
	if(to.toString().contains(er1.getKey()))
		TempOrder1.add(to);

}
for(EdgeOperator to:TempOrder)
TempOrder3.remove(to);	

//for(:er.getValue())
for(EdgeOperator to:TempOrder1)
TempOrder3.remove(to);	

TempOrder3.addAll(TempOrder);
TempOrder3.addAll(TempOrder1);
operators_BushyTreeOrder.clear();

operators_BushyTreeOrder.addAll(TempOrder3);

}

int l=0;
	if(!ParaEng.OptionalOrder.isEmpty()) {
//	for(int i9=0;i9<3;i9++) {

		Temp1.clear();
		Temp1.putAll(finalResultOptional);
		Temp1.putAll(finalResultLeftOptional);

		Temp1.putAll(finalResultRightOptional);

HashMap<EdgeOperator,List<Binding>> lhs = new HashMap<EdgeOperator,List<Binding>>();
LinkedHashMap<EdgeOperator,Integer> b=new LinkedHashMap<>(); 
for(Entry<String, Integer> oo:ParaEng.OptionalOrder.entrySet()) {
	String[] oo1=oo.getKey().split(" ");
	for(int i=0;i<oo1.length;i+=1)
		System.out.println("This is optional Order:"+oo1[i]);

	for(int i=0;i<oo1.length;i+=1)
	{if((oo1[i].replaceAll("\t", "").startsWith("?") ||oo1[i].replaceAll("\t", "").contains("http:") ||oo1[i].replaceAll("\t", "").startsWith("\"") ) && i+2<oo1.length)
		{for(Entry<EdgeOperator, List<Binding>> it1:Temp1.entrySet())
			{			System.out.println("This is creation of new node in para:"+it1+"--"+oo1[i]+"--"+oo1[i+2]);
			
			if(it1.getKey().getEdge().getV1().equals(Vertex.create(StageGen.StringConversion(oo1[i].replaceAll("\t", "").substring(1).replaceAll(">", "")))) &&
					it1.getKey().getEdge().getV2().equals(Vertex.create(StageGen.StringConversion(oo1[i+2].replaceAll("\t", "").substring(1).replaceAll(">", "")))) )
		
		b.put(it1.getKey(),oo.getValue());
			}}
//			else
//		b.add(new BindJoin(new ,new Edge(Vertex.create(StageGen.StringConversion(oo1[i].substring(1))),Vertex.create(StageGen.StringConversion(oo1[i+2].substring(1))) )));
				
	}
}
//for(Entry<EdgeOperator, Integer> ob:b.entrySet())
//	if(ob.getKey().getEdge().getV1().getNode().isURI())	
//	urim.replace(ob.getKey().getEdge().getV1(),null);
//	else if((ob.getKey().getEdge().getV2().getNode().isURI())	)
//		urim.replace(ob.getKey().getEdge().getV2(),null);

LinkedHashMap<EdgeOperator,Integer> Temp81=new LinkedHashMap<>();

Temp81.putAll(b);


Map<EdgeOperator, Integer> sortedMap = 
		Temp81.entrySet().stream()
	    .sorted(Entry.comparingByValue())
	    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
	                              (e1, e2) -> e1, LinkedHashMap::new));


b.clear();
b.putAll(sortedMap);

for(Entry<EdgeOperator, Integer> b3:b.entrySet())
	System.out.println("This is creation of new node in para:"+b3);
		int l1=0;
/*	for(EdgeOperator oo:b)
	{	
	
		for(Entry<EdgeOperator, List<Binding>> oo3:Temp.entrySet())
		{
						if(oo3.getKey().getEdge().getV1().equals(oo.getEdge().getV1())
							&& oo3.getKey().getEdge().getV2().equals(oo.getEdge().getV2())) {
		//				if(l1==oo.getValue()) {
		{
			//System.out.println("This is optional OptionalOrder:" + oo1[i]+"--"+oo1[i+2]+"--"+oo.getValue());
		lhs.put(oo3.getKey(),oo3.getValue());
		//}
						}
						}
	//		l1++;
			
	//		}
				
	//	System.out.println("This is the value of l111:"+l);
			
	//System.out.println("------------------------------------");
		}
*/
	
		
//	l++;

		//}
	TempHash.clear();
	TempBind.clear();
	
//operators_BushyTreeOrder
//	for (EdgeOperator bo : operators_BushyTreeOrder)
		for (Entry<EdgeOperator, Integer> t : b.entrySet()) {
	//		 System.out.println("This is processing:"+t.getKey()+"--"+bo);
	//		if (bo.toString().equals(t.getKey().toString()) && t.getKey().toString().contains("Hash"))
				TempHash.put(t.getKey(), null);
		}
	

//	for (EdgeOperator bo : operators_BushyTreeOrder)
		for (Entry<EdgeOperator, Integer> t : b.entrySet()) {
	//		 System.out.println("This is processing:"+t.getKey()+"--"+bo);
	//		if (bo.toString().equals(t.getKey().toString()) && t.getKey().toString().contains("Bind"))
				TempBind.put(t.getKey(), null);
		}

	// if (bo.toString().equals(t.getKey().toString()) &&
	// t.getKey().toString().contains("Bind"))
	// TempBind.put(t.getKey(), t.getValue());

	PartitionedExecutions(TempHash,TempHash, "Optional");

	PartitionedExecutions(TempBind,TempBind, "Optional");

	System.out.println("------------------------------" );
	
	}

	else if(!ParaEng.UnionOrder.isEmpty()) {
		Temp1.clear();
		Temp1.putAll(finalResultOptional);
		Temp1.putAll(finalResultLeftOptional);

		Temp1.putAll(finalResultRightOptional);

		
		System.out.println("These are the executions2222222222222222222222222:"+Temp1);

//			for(int i9=0;i9<3;i9++) {

				System.out.println("This is optional Order:"+ParaEng.UnionOrder);

		HashMap<EdgeOperator,List<Binding>> lhs = new HashMap<EdgeOperator,List<Binding>>();
		LinkedHashMap<EdgeOperator,Integer> b=new LinkedHashMap<>(); 
		for(Entry<String, Integer> oo:ParaEng.UnionOrder.entrySet()) {
			String[] oo1=oo.getKey().split(" ");
			for(int i=0;i<oo1.length;i+=1)
			{if((oo1[i].startsWith("?")||oo1[i].contains("http:") ) && i+2<oo1.length)
			
		
				
				for(Entry<EdgeOperator, List<Binding>> it1:Temp1.entrySet())
				{
					//System.out.println("This is optional Order000000000000:"+it1.getKey().getEdge().getV1()+"--"+it1.getKey().getEdge().getV2());
		//			System.out.println("This is optional Order111111111111:"+oo1[]);
					
					if(it1.getKey().getEdge().getV1().equals(Vertex.create(StageGen.StringConversion(oo1[i].substring(1).replaceAll(">", "")))) &&
							it1.getKey().getEdge().getV2().equals(Vertex.create(StageGen.StringConversion(oo1[i+2].substring(1).replaceAll(">", "")))) )
					
				b.put(it1.getKey(),oo.getValue());
//					else
//				b.add(new BindJoin(new ,new Edge(Vertex.create(StageGen.StringConversion(oo1[i].substring(1))),Vertex.create(StageGen.StringConversion(oo1[i+2].substring(1))) )));
			}			
			}
		}

		LinkedHashMap<EdgeOperator,Integer> Temp81=new LinkedHashMap<>();

		Temp81.putAll(b);


		Map<EdgeOperator, Integer> sortedMap = 
				Temp81.entrySet().stream()
			    .sorted(Entry.comparingByValue())
			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
			                              (e1, e2) -> e1, LinkedHashMap::new));


		b.clear();
		b.putAll(sortedMap);
		
		Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
		for (Integer c : b.values()) {
		    int value = counts.get(c) == null ? 0 : counts.get(c);
		    counts.put(c, value + 1);
		}
		
		System.out.println("This is the count of values:"+counts);
		for(Entry<EdgeOperator, Integer> b3:b.entrySet())
			System.out.println("This is creation of new node in paraUnion:"+b3);
				int l1=0;
		/*	for(EdgeOperator oo:b)
			{	
			
				for(Entry<EdgeOperator, List<Binding>> oo3:Temp.entrySet())
				{
								if(oo3.getKey().getEdge().getV1().equals(oo.getEdge().getV1())
									&& oo3.getKey().getEdge().getV2().equals(oo.getEdge().getV2())) {
				//				if(l1==oo.getValue()) {
				{
					//System.out.println("This is optional OptionalOrder:" + oo1[i]+"--"+oo1[i+2]+"--"+oo.getValue());
				lhs.put(oo3.getKey(),oo3.getValue());
				//}
								}
								}
			//		l1++;
					
			//		}
						
			//	System.out.println("This is the value of l111:"+l);
					
			//System.out.println("------------------------------------");
				}
		*/
			
				
//			l++;

				//}
		
			
		//operators_BushyTreeOrder
//			for (EdgeOperator bo : operators_BushyTreeOrder)
			for(Entry<Integer, Integer>  cc:counts.entrySet()) {
				TempHash.clear();
				TempBind.clear();
				for (Entry<EdgeOperator, Integer> t : b.entrySet()) {
			//		 System.out.println("This is processing:"+t.getKey()+"--"+bo);
					if (t.getKey().toString().contains("Hash") && cc.getKey()==t.getValue())
						TempHash.put(t.getKey(), null);
					if ( t.getKey().toString().contains("Bind") && cc.getKey()==t.getValue())
						TempBind.put(t.getKey(), null);
			
				}
				PartitionedExecutions(TempHash,TempHash, "Optional");

				PartitionedExecutions(TempBind,TempBind, "Optional");
				System.out.println("------------------------------" );
	//			for(EdgeOperator ob:operators_BushyTreeOrder)
	//				if(ob.getEdge().getV1().getNode().isURI())	
	//				urim.replace(ob.getEdge().getV1(),null);
	//				else if((ob.getEdge().getV2().getNode().isURI())	)
	//					urim.replace(ob.getEdge().getV2(),null);
		
				if(ParaEng.Union.contains("UNION") && (!HashJoin.JoinedTriplesUnion.isEmpty() || HashJoin.JoinedTriplesUnion!=null || HashJoin.JoinedTriplesUnion.size()>0))
				{	ArrayList<Binding> First= new ArrayList<Binding>();
					ArrayList<Binding> Second= new ArrayList<Binding>();
					List<EdgeOperator> ListEdges = new ArrayList<>();
					
					for(Entry<List<EdgeOperator>, List<Binding>> jgu:HashJoin.JoinedTriplesUnion.entrySet())
					{First.addAll(jgu.getValue());
					ListEdges.addAll(jgu.getKey());
					}
					for(Entry<List<EdgeOperator>, List<Binding>> jg:HashJoin.JoinedTriples.entrySet())
					{
						Second.addAll(jg.getValue());
					ListEdges.addAll(jg.getKey());
					}
					//System.out.println("These are the two sets010101010:"+First.size()+"--"+Second.size());
					
					System.out.println("These are the two sets:"+First.size()+"--"+Second.size());
					if(First.size()>0 && Second.size()>0) {
					
				ForkJoinPool fjp = new ForkJoinPool();
				fjp.submit(()->
				{ArrayList<Binding> Union = new ArrayList<>();
					Union.addAll(First);
					Union.addAll(Second);
					System.out.println("These are the middle two sets:"+Union.size());
					
					HashJoin.JoinedTriples.clear();
					HashJoin.JoinedTriples.put(ListEdges,Union);
					for(Entry<List<EdgeOperator>, List<Binding>>  ee:HashJoin.JoinedTriples.entrySet())
					System.out.println("These are the last two sets:"+ee.getKey()+"--"+ee.getKey().size());
						
						//	BGPEval.GPUJoin(First,Second,First,Second,2,null);
				//	HashJoin.JoinedTriples.put(ListEdges,Union);
				}	).join();
					fjp.shutdown();
					}
				}
			}
	

			// if (bo.toString().equals(t.getKey().toString()) &&
			// t.getKey().toString().contains("Bind"))
			// TempBind.put(t.getKey(), t.getValue());

			
//					}
			
			
	}
	else {
System.out.println("These are the executions111111111111111111111111111111");


System.out.println("This is the new group list of JoinGroupsListLeft88888888:" + JoinGroupsListLeft);

		for (EdgeOperator bo : operators_BushyTreeOrder)
			for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet()) {
				// System.out.println("This is processing:"+t.getKey()+"--"+bo);
				if (bo.toString().equals(t.getKey().toString()) && t.getKey().toString().contains("Hash"))
					TempHash.put(t.getKey(), t.getValue());
			}
		

		for (EdgeOperator bo : operators_BushyTreeOrder)
			for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet()) {
				// System.out.println("This is processing:"+t.getKey()+"--"+bo);
				if (bo.toString().equals(t.getKey().toString()) && t.getKey().toString().contains("Bind"))
					TempBind.put(t.getKey(), t.getValue());
			}

		PartitionedExecutions(TempHash,TempHash, "Optional");

	PartitionedExecutions(TempBind,TempBind, "Optional");
	}

		}
		if (ParaEng.Minus.contains("MINUS"))
		{
		Temp1 = new LinkedHashMap<>();
		TempBind = new LinkedHashMap<>();
		TempHash = new LinkedHashMap<>();

		Temp1.putAll(finalResultMinus);
		Temp1.putAll(finalResultLeftMinus);

		Temp1.putAll(finalResultRightMinus);

		for (EdgeOperator bo : operators_BushyTreeOrder)
			for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet()) {
				// System.out.println("This is processing:"+t.getKey()+"--"+bo);
				if (bo.toString().equals(t.getKey().toString()) && t.getKey().toString().contains("Hash"))
					TempHash.put(t.getKey(), t.getValue());
			}
		

		for (EdgeOperator bo : operators_BushyTreeOrder)
			for (Entry<EdgeOperator, List<Binding>> t : Temp1.entrySet()) {
				// System.out.println("This is processing:"+t.getKey()+"--"+bo);
				if (bo.toString().equals(t.getKey().toString()) && t.getKey().toString().contains("Bind"))
					TempBind.put(t.getKey(), t.getValue());
			}

		// if (bo.toString().equals(t.getKey().toString()) &&
		// t.getKey().toString().contains("Bind"))
		// TempBind.put(t.getKey(), t.getValue());

		PartitionedExecutions(TempHash,TempHash, "Minus");

		PartitionedExecutions(TempBind,TempBind, "Minus");
		}
		// }

		// }).join();

		// fjp.shutdownNow();

		finalResult.putAll(finalResultRight);

		//// logger.info("aaaaaaaaaa5555:"+UnProcessedSets.parallelStream().limit(1).collect(Collectors.toSet()));

//	for(Entry<EdgeOperator, Set<Binding>> frl:finalResultLeft.entrySet())
		// if(frl.getValue()!=null)
		// //logger.info("This is finalResultExclusive
		// Left:"+frl.getValue().size()+"--"+frl.getValue().parallelStream().limit(1).collect(Collectors.toSet()));
		finalResult.putAll(finalResultLeft);

		//// logger.info("This is the final final final final result:"+finalResult);
		/*
		 * for(Map.Entry<EdgeOperator,Set<Binding>> a:finalResult.entrySet())
		 * for(Map.Entry<EdgeOperator,Set<Binding>> a1:finalResult.entrySet())
		 * if(a.getKey().getEdge().getTriple().getObject().equals(a1.getKey().getEdge().
		 * getTriple().getSubject())) {finalResultFinal.put(a1.getKey(),a1.getValue());
		 * finalResultFinal.put(a.getKey(),a.getValue());
		 * 
		 * }
		 */
//	//logger.info("aaaaaaaaaa4444:"+UnProcessedSets.parallelStream().limit(1).collect(Collectors.toSet()));

		// for(Map.Entry<EdgeOperator,Set<Binding>> a:finalResultFinal.entrySet())
//	for(Map.Entry<EdgeOperator,Set<Binding>> a1:finalResult.entrySet())
//		if(!a.getKey().equals(a1.getKey()))
//			finalResultFinal.put(a1.getKey(), a1.getValue());
		// //logger.info("Tis is the final finalResult:"+a.getKey());

//	for(Entry<EdgeOperator, Set<Binding>> frl:finalResult.entrySet())
//		//logger.info("This is finalResultExclusive Final:"+frl.getKey());
////logger.info("888888888888888This this this this this this this this this this:"+LocalTime.now());

		// }

		// finalResult(finalResult);
//	for(BindingSet rr:r1)
//	//logger.info("77777777777777777This this this this this this this this this this:"+rr);

		// for(Entry<EdgeOperator, Set<Binding>> r:finalResult.entrySet())
		// //logger.info("This is now the final result:"+r.getValue().size());
		/*
		 * //logger.info("This is now the final result:"+r1.size());
		 * 
		 * if(ParaEng.Union.contains("UNION")==true ) {
		 * if(finalResultRightOptional!=null ||finalResultRightOptional.size()>0)
		 * finalResultOptional.putAll(finalResultRightOptional);
		 * if(finalResultLeftOptional!=null ||finalResultLeftOptional.size()>0)
		 * finalResultOptional.putAll(finalResultLeftOptional);
		 * 
		 * //for(Map.Entry<EdgeOperator,Set<Binding>> a:finalResultOptional.entrySet())
		 * // for(Map.Entry<EdgeOperator,Set<Binding>>
		 * a1:finalResultOptional.entrySet()) ///
		 * if(a.getKey().getEdge().getTriple().getObject().equals(a1.getKey().getEdge().
		 * getTriple().getSubject())) //
		 * {finalResultOptionalFinal.put(a1.getKey(),a1.getValue()); //
		 * finalResultOptionalFinal.put(a.getKey(),a.getValue());
		 * 
		 * // } // for(Map.Entry<EdgeOperator,Set<Binding>>
		 * a:finalResultOptionalFinal.entrySet()) //
		 * for(Map.Entry<EdgeOperator,Set<Binding>> a1:finalResultOptional.entrySet())
		 * // if(!a.getKey().equals(a1.getKey())) //
		 * finalResultOptionalFinal.put(a1.getKey(), a1.getValue());
		 * 
		 * Set<Binding> r2 = finalResult(finalResultOptional);
		 * //logger.info("This is now the final result:"+r2.size()+"--"+r1.size());
		 * r3.addAll(r1); r3.addAll(r2) ;
		 * //logger.info("This is now the final result12123:"+r2.size()+"--"+r1.size());
		 * 
		 * } if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())==true) {
		 * finalResultOptional.putAll(finalResultRightOptional);
		 * finalResultOptional.putAll(finalResultLeftOptional);
		 * 
		 * 
		 * 
		 * System.out.println("This this this this this this this this this:"+r1.size())
		 * ;
		 * //System.out.println("222222222This this this this this this this this this:"
		 * +); Set<Binding> iuo ; Iterator<Set<Binding>> aa =
		 * finalResultOptional.values().iterator(); aa =
		 * finalResultOptional.values().iterator();
		 * 
		 * while(aa.hasNext()) { Set<Binding> aaaa = aa.next();
		 * //iuo=QueryUtil.join(aaaa,r1);
		 * System.out.println("89898989898989This is the finalResultOptional:"+
		 * aaaa.size()+"--"+r1.size()); //r1.removeAll(iuo);
		 * System.out.println("89898989898989This is the finalResultOptional:"+
		 * aaaa.size()+"--"+r1.size());
		 * 
		 * //if(iuo.size()>0) {
		 * //System.out.println("89898989898989This is the finalResultOptional:"+
		 * iuo.size());
		 * 
		 * // r3.addAll(iuo); // r3.addAll(r1); // if(aa.next().size()>0) //
		 * r3.addAll(aa.next()); if(r3.size()==0) r3.addAll(r1);
		 * System.out.println("This is the finalResultOptional:"+r3.size()+"--"+r3.
		 * parallelStream().limit(1).collect(Collectors.toSet()));
		 * 
		 * break; } } // for(BindingSet rr:r3)
		 * ////logger.info("This is now the final result rrr:"+rr); //
		 * r3.addAll(r1);r3.addAll(r2) ; //}
		 * 
		 * 
		 * 
		 * if(((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())==true ||
		 *!ParaEng.Union.isEmpty()==true)) { results = r3.iterator();
		 * finalResultSize =r3.size();
		 * 
		 * System.out.println("This is the result of first Joining:"+r3.size()+"--"+r3.
		 * parallelStream().limit(1).collect(Collectors.toSet())+"--"+LocalTime.now());
		 * 
		 * return;
		 * 
		 * }
		 * 
		 * 
		 * // if(!((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())==true ||
		 *!ParaEng.Union.isEmpty()==true)) { // r3=r1; //} //}
		 */ // logger.info("This is the result of first
			// Joining11:"+r3.size()+"--"+LocalTime.now());
		List<Binding> r1 = new ArrayList<>();
//		for (Entry<List<EdgeOperator>, List<Binding>> b : HashJoin.JoinedTriples.entrySet())
//		for(Binding bb:b.getValue())
//			System.out.println("This is before finalization JoinedTriples :" +bb);
		
//		for (Entry<List<EdgeOperator>, List<Binding>> b : HashJoin.NotJoinedTriples.entrySet())
//			for(Binding bb:b.getValue())
//				System.out.println("This is before finalization NotJoinedTriples :" +bb);

//for(List<Binding> e1:HashJoin.NotJoinedTriples.values().iterator())
//	for(Binding e2:e1)
//System.out.println("This is the binding in NotJoinedTriples:"+e2);		


	//for(Binding e2:e1.iterator())
//System.out.println("This is the binding in JoinedTriples:"+e2);		

if ( ParaEng.InnerFilter!=null) {
	
	for(List<Binding> e1:HashJoin.NotJoinedTriples.values()) {
		List<Var> joinVarsList1 = new ArrayList<>();
		Var joinVars1 = null;
	Iterator<Binding> rIterator1 = e1.iterator();
	List<Binding> temp11 = new ArrayList<>();
	int br1 = 0;


	Iterator<Var> l111=null;
	//Set<Vertex> v1 = new HashSet<>();
//	Multimap<Vertex,String> v1 = ArrayListMultimap.create();
	while(rIterator1.hasNext()) 
		l111 = rIterator1.next().vars();
	String f = null;
//		for(Binding r:results)
	int keyOrvalue=0;	
	
	if(l111!=null)
		while (l111.hasNext()) {
			
			Var v3 = l111.next();
			
		//	System.out.println("This is rule no.3 in StartBinding123Large: BGPEval" + v3);
						
					for(Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>> iff:ParaEng.InnerFilter.entrySet())
{	for(Entry<String, List<Binding>> iff1:iff.getKey().entrySet())
	{		Var r = Var.alloc(iff1.getKey().substring(1));
		//	System.out.println("This is rule no.3 in StartBinding123Larsge:" + r + "--" + v3);
			joinVarsList1.add(v3);
			if (r.equals(v3)) {
			//	joinVarsList.add(v3);
				keyOrvalue=1;
				f=iff1.getKey();
				joinVars1 = v3;
				br1 = 1;
		//		break;
			}

		}
if(joinVars1==null) {
	for(Entry<String, List<Binding>> iff1:iff.getValue().entrySet())
	{		Var r = Var.alloc(iff1.getKey().substring(1));
			//System.out.println("This is rule no.3 in StartBinding123Large:" + r + "--" + v3);
			joinVarsList1.add(v3);
			if (r.equals(v3)) {
				//joinVarsList.add(v3);
				keyOrvalue=2;
				f=iff1.getKey();
				joinVars1 = v3;
				br1 = 1;
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

	if (joinVars1 != null) {
//			for(Var jvl:joinVarsList1)
		
		for (Binding e11 : e1) {
			BindingHashMap extend = new BindingHashMap();// TODO do not use a new binding map
			
		//	System.out.println("These are the problems:"+e1.toString().replace("<", "").replace(">", "").replace("\"", ""));
			//List<Binding> t_temp=new ArrayList<>();
			for(Var jvl:joinVarsList1)
			{	
			//	System.out.println("This is rule no. 2 in BindJoin123123123123:" + jvl+"--"+e11.get(jvl));
											
			if (e11.get(jvl) != null) {
				if (e11.get(jvl).toString().contains("http"))
					extend.addAll(BindingFactory.binding(jvl, StageGen.StringConversion(e11.get(jvl)
							.toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", ""))));
				else
					extend.addAll(BindingFactory.binding(jvl, StageGen.StringConversion(
							e11.get(jvl).toString().replace("<", "").replace(">", "").trim())));
			//	System.out.println("This is rule no. 2 in BindJoin00000111111:" + extend);	
			}
			
			}
			temp11.add(extend);
			
		}
	
	Iterator<Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>>> ifiterator = ParaEng.InnerFilter.entrySet().iterator();
	while(ifiterator.hasNext()) {
	Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>> ifnext = ifiterator.next();
	if(keyOrvalue==1)
	ifnext.getKey().replace(f, temp11);
	if(keyOrvalue==2)
		
	ifnext.getValue().replace(f, temp11);
	}	
	}
	
	
	
	}

}

Set<String> ab= new HashSet<>(); 
List<Binding> resultsBq =
new ArrayList<Binding>();

	for(	Entry<HashMap<String, List<Binding>>, HashMap<String, List<Binding>>> eh: ParaEng.InnerFilter.entrySet())
	{
	for(Entry<String, List<Binding>> ee:eh.getValue().entrySet()) {
	
	Var r = Var.alloc(ee.getKey().substring(1));

	
	ee.getValue().stream().forEach(e11->{
		if(e11.get(r).toString().contains("@"))
			 baaa=e11.get(r).toString().substring(0,e11.get(r).toString().indexOf("@")).replaceAll("\"", "");
			else
				baaa=e11.get(r).toString().replaceAll("\"", "");
		
		for(Entry<String, List<Binding>> ee1:eh.getKey().entrySet()) {
			
			BindingHashMap extend = new BindingHashMap();// TODO do not use a new binding map
			
		
			//int k11=0;
		//	Var r12 = Var.alloc(ee1.getKey().substring(1));
			
			ee1.getValue().parallelStream().forEach(e111->{	if(e111.toString().contains(baaa))
			{
				extend.addAll(e111);
				extend.addAll(e11);
			//	System.out.println("This is equality of condition:"+extend);
				if(!resultsBq.contains(extend))
				resultsBq.add(extend);
			}
				//System.out.println("This is timing within postprocess454545454545 BGPEval value:"+baaa+"--"+e111);
});

		
		}}); 
	//	String a=null;
		
			
							// a=e111.get(r12).toString().substring(0,e11.get(r).toString().indexOf("@")).replaceAll("\"", "");
				//else
				//	a=e111.get(r12).toString().replaceAll("\"", "");
				
				
				//System.out.println("This is timing within postprocess454545454545 BGPEval value:"+a+"--"+b);

				//k11++;
			//	if(a.equals(b))
					/*{
					ab.add(e111.get(r12).toString());
					extend.addAll(e111);
					extend.addAll(e11);
				//	System.out.println("This is equality of condition:"+extend);
					if(!resultsBq.contains(extend))
					resultsBq.add(extend);
					//System.out.println("This is equality of condition:"+e111.get(r12)+"--"+e11.get(r));
					}*/
					//	extend1.add(BindingFactory.binding(r12,e111.get(r12)));
				
				//} 
		///	if(extend.size()>0)
		//	break;
			
		//}
			
		
		
		

		
	//	}

	}
	//	k1++;
	//	extend.add(BindingFactory.binding(r,e11.get(r)));
		
		
		}
	//});
//	}
	//}
//	}
for (Binding b : resultsBq)
	System.out.println("This is equailty of condition:" + b);

for (Binding b : resultsBq)
	System.out.println("This is equailty of condition totality:" + b);
		for (Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> b : HashJoin.JoinedTriplesLarge.entrySet())
			System.out.println("This is before finalization:" + b.getKey() + "--"
					+ b.getValue());
		for (Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> b : HashJoin.NotJoinedTriplesLarge.entrySet())
			for(Binding d:b.getValue())
			System.out.println("This is before finalization not:" + b.getKey() + "--"
					+ d);
		if(ParaEng.FiltereqLarge.size()==0 ||  ParaEng.FiltereqLarge.isEmpty()) {	
	if(!HeaderReplacement.isEmpty() || HeaderReplacement.size()>0) {
			for (List<Binding> b : HashJoin.JoinedTriples.values())
		{//	for(Binding b1:b) {
				
				for(Binding b1:b)
				{
					BindingHashMap extendTemp2 = new BindingHashMap();
						
					Iterator<Var> l = b.iterator().next().vars();
							
					while(l.hasNext())
						
				{
				Var k=	l.next();
		//		b1.get(l);
			//	BindingFactory.binding(b1., null); 
				//extendTemp2.add(Var.alloc(value.substring(1)), StageGen.StringConversion(l));
		//		System.out.println("This is filter values lsat5454353:" +k+"--"+b1 );
				
				if(k.toString().endsWith("?a"))
				{	//if(b1.get(k).toString().contains("http"))
					//extendTemp2.addAll(BindingFactory.binding(Var.alloc(k.toString().substring(1,k.toString().indexOf("?a"))),StageGen.StringConversion(b1.get(k).toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", "")) ));
					//else
						extendTemp2.addAll(BindingFactory.binding(Var.alloc(k.toString().substring(1,k.toString().indexOf("?a"))),b1.get(k)));
				}
				else
				{
					//if(b1.get(k).toString().contains("http"))
					//	extendTemp2.addAll(BindingFactory.binding(k,StageGen.StringConversion(b1.get(k).toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", "")) ));
						//else
							extendTemp2.addAll(BindingFactory.binding(k,b1.get(k)));
				}
	//	System.out.println("This is final result444444555555:"+k+"--"+b1.get(k));
		}	
			//	r1.addAll(b);
			//}
					r1.add(extendTemp2);
					
				}
	
				}
		}
	else {
		for (List<Binding> b :  HashJoin.JoinedTriples.values())
			r1.addAll(b);
		
	}
		}
		else {
			if(!HeaderReplacement.isEmpty() || HeaderReplacement.size()>0) {
				for (List<Binding> b : HashJoin.NotJoinedTriplesLarge.values())
			{//	for(Binding b1:b) {
					
					for(Binding b1:b)
					{
						BindingHashMap extendTemp2 = new BindingHashMap();
							
						Iterator<Var> l = b.iterator().next().vars();
								
						while(l.hasNext())
							
					{
					Var k=	l.next();
			//		b1.get(l);
				//	BindingFactory.binding(b1., null); 
					//extendTemp2.add(Var.alloc(value.substring(1)), StageGen.StringConversion(l));
			//		System.out.println("This is filter values lsat5454353:" +k+"--"+b1 );
					
					if(k.toString().endsWith("?a"))
					{	//if(b1.get(k).toString().contains("http"))
						//extendTemp2.addAll(BindingFactory.binding(Var.alloc(k.toString().substring(1,k.toString().indexOf("?a"))),StageGen.StringConversion(b1.get(k).toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", "")) ));
						//else
							extendTemp2.addAll(BindingFactory.binding(Var.alloc(k.toString().substring(1,k.toString().indexOf("?a"))),b1.get(k)));
					}
					else
					{
						//if(b1.get(k).toString().contains("http"))
						//	extendTemp2.addAll(BindingFactory.binding(k,StageGen.StringConversion(b1.get(k).toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", "")) ));
							//else
								extendTemp2.addAll(BindingFactory.binding(k,b1.get(k)));
					}
		//	System.out.println("This is final result444444555555:"+k+"--"+b1.get(k));
			}	
				//	r1.addAll(b);
				//}
						r1.add(extendTemp2);
						
					}
		
					}
			}
		else		
			{	for (List<Binding> b :  HashJoin.NotJoinedTriplesLarge.values())
				r1.addAll(b);
			}
		}
		
//	for(Binding rr:r1)	
//		System.out.println("This is filter values lsat:" + rr );
List<Binding> results1=new ArrayList<>();

if(r1.size()==0 || r1.isEmpty())
	for(Entry<EdgeOperator, List<Binding>>  fr:BGPEval.finalResult.entrySet())
		{
		r1.addAll(fr.getValue());
		break;
		}

if(r1.size()==0 || r1.isEmpty())
	for(Entry<EdgeOperator, List<Binding>>  fr:BGPEval.finalResultLeft.entrySet())
	{
	r1.addAll(fr.getValue());
	break;
	
	}

if(r1.size()==0 || r1.isEmpty())
	for(Entry<EdgeOperator, List<Binding>>  fr:BGPEval.finalResultRight.entrySet())
	{
	r1.addAll(fr.getValue());
	break;
	}

results1.addAll(r1);
r3 = r1;
		results = r3.iterator();
		/*
		for(	Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> eh:ParaEng.InnerFilter.entrySet())
		{
			for(Entry<String, Set<Binding>>  ee:eh.getKey().entrySet())
				for(Binding ee1:ee.getValue())
			System.out.println("This is timing within postprocess454545454545 key:"+ee.getKey()+"--"+ee1);
			for(Entry<String, Set<Binding>>  ee:eh.getValue().entrySet())
				for(Binding ee1:ee.getValue())
			System.out.println("This is timing within postprocess454545454545 value:"+ee.getKey()+"--"+ee1);
		}
		*/
	//	Iterable<Binding> newIterable = () -> results;
	//	long count = StreamSupport.stream(newIterable.spliterator(), false).count();
		
	//	System.out.println("This is filter values lsat:" + count);

		System.out.println(
				"This is finalization here--------------------------------------------------------------------------------------------------------------------");
				String[] q = new String[1];
		List<String[]> qu = new ArrayList<String[]>();

		q[0] = "This is time where algorithm's execution is complete:" + LocalTime.now();
		qu.add(q);

		try (CSVWriter writer = new CSVWriter(new FileWriter("/mnt/hdd/hammad/hammad/Query.csv", true))) {
			writer.writeAll(qu);
			// writer.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}

		System.out.println("This is final result:" + "--" + LocalTime.now() + "--" + r3.size() + "--"
				+ r3.parallelStream().limit(1).collect(Collectors.toSet()));

		String length = "";
		String[] vv = null;
		List<String> headersAllRight = new ArrayList<>();
		List<String> input = new ArrayList<>();
		Set<String> input1 = new HashSet<>();

		int i = 0;
		int size = 0;
		for(Binding rs:r3.parallelStream().limit(1).collect(Collectors.toSet())) {
			length = rs.toString();
			vv = length.split(" ");
		
			for (String v : vv) {
			//	System.out.println("This is length1:"+v);
				if (v.startsWith("?")) {
			//		 System.out.println("This is length123232323232:"+v.substring(1));

					headersAllRight.add(v.substring(1));
					// headersAll.add(v.substring(1));
					// break;
				}
			}
		}
	/*	for(Binding results:results1 ) {
			// if(results.next().size()>size)
			// size=results.next().size();
			length = results.toString();
			// if(results.next().size()<size)
			// continue;
//		System.out.println("This is length:"+length);
			String a = String.valueOf(length.replaceAll("\"", ""));// l1.next().toString();
			if(ParaEng.Distinct.equals("Yes"))
				
			input1.add(a);
			else
				input.add(a);
		
			
			i++;
		}

	System.out.println("This is headersAllRight for output file:"+headersAllRight);
//	for(String i:input)
//System.out.println("This is isisisisis:"+i);
	List<List<String>> leftTable = new ArrayList<>();
	if(ParaEng.Distinct.equals("Yes"))
		
		leftTable = BGPEval.transformResult(input1, null,
				headersAllRight);
	else
		leftTable = BGPEval.transformResult(input, null,
				headersAllRight);
		System.out.println("This is the type");
		ArrayList<String[]> leftCsv = new ArrayList<String[]>();
		for (int i1 = 0; i1 < leftTable.size(); i1++) {
			String[] temp = new String[leftTable.get(0).size()];
			for (int n = 0; n < temp.length; n++) {
				temp[n] = leftTable.get(i1).get(n).replaceAll("\"", "");
			}

			leftCsv.add(temp);
		}

		File file1 = new File("/mnt/hdd/hammad/hammad/Output.csv");

		if (file1.delete()) {
			System.out.println("File deleted successfully1");
		}
		// System.out.print("These are left var4");
		try (CSVWriter writer = new CSVWriter(new FileWriter("/mnt/hdd/hammad/hammad/Output.csv", true))) {
			writer.writeAll(leftCsv);
			// writer.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
*/
		q = new String[1];
		qu = new ArrayList<String[]>();

		q[0] = "This is time where Output file is written:" + LocalTime.now();
		qu.add(q);

		try (CSVWriter writer = new CSVWriter(new FileWriter("/mnt/hdd/hammad/hammad/Query.csv", true))) {
			writer.writeAll(qu);
			// writer.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}

	//	while(results.hasNext()) {
	//		System.out.println("This is the final final final final final final final result:"+results.next());
	//	}
		// System.exit(0);


	return ;
	}

//	}

	public void ExecuteJoinsDep(Optimiser opt, List<EdgeOperator> operators) {

		while (operators != null) {
			es = Executors.newWorkStealingPool(1);
			for (EdgeOperator eo : operators) {
				// consume the input BindingSets
				// logger.info("This is here in ExecuteJoinDep counting number of
				// HashJoin:"+eo);

				Stream.of(eo.setInput(input)).sequential().forEachOrdered(e1 -> {
					try {
						es.submit(eo);
						Thread.sleep(1000);
					} catch (RejectedExecutionException | InterruptedException e) {
						// if (Config.debug)
						// //logger.info("Query execution is terminated.");
					}
				});

				// logger.info("This is sequence of edgeOperators in actual"+eo.toString());
				// BGPEval!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+g+"--"+
				// opt.nextStage()+"--"+es+"--"+"--"+eo.getEdge()+"--"+eo.getStartVertex());
				input = null;
				// //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
				// BGPEval!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+g+"--"+
				// opt.nextStage()+"--"+es+"--"+"--"+eo.getEdge()+"--"+eo.getStartVertex());

			}
			// //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in BGPEval After
			// es.submit!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+getCurrentExeService());

			es.shutdownNow();
			while (!es.isTerminated()) {
				try {
					es.awaitTermination(100, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					// logger.info("Query execution interrupted.");
				} catch (IllegalMonitorStateException e) {
					// logger.info("IllegalMonitorStateException");
				}
			}

			// if(Config.debug)
			// //logger.info("********************");
			// logger.info(
			// "***********************************************************************************************************************************************************************************************************");

			/*
			 * RelativeError.addEstimatedCardBGP(RelativeError.est_resultSize);
			 * RelativeError.print(); RelativeError.add_plan_joincards();
			 * RelativeError.est_resultSize.clear(); RelativeError.real_resultSize.clear();
			 */
			// logger.info("Now it is here1");
			for (EdgeOperator eo : operators) {
				if (eo.isFinished()) {
//					if (Config.debug) {
					// logger.info("Query is finished by " + eo.getEdge());
					// }
					// //logger.info("These are the values of result in BGPEval:"+ eo.getEdge());
					results = new Vector<Binding>().iterator();
					// //logger.info("This is in BGPEval results:" + results);
					return;
				}
				// logger.info("Now it is here2:"+results);

			}

			operators = opt.nextStage();
		}
		i++;

		// find out vertices with BindingSets that have not been used (joined)

		// Set<Binding> BindingSets = null;
		for (Vertex v : opt.getRemainVertices()) {
			if (Config.debug) {
				// logger.info("Remaining vertex: " + v);
			}
			// //logger.info("This is in BGPEval results:" + BindingSets + "--" +
			// v.getBindingSets());
			// ForkJoinPool fjp = new
			// ForkJoinPool(Runtime.getRuntime().availableProcessors());
//		fjp.submit(()->Stream.of(BindingSets = QueryUtil.join(BindingSets, v.getBindingSets())).parallel().forEachOrdered(
			// e->results = bindinfgs.iterator())) ;
			// try {
			// Thread.sleep(1000);
			// logger.info("THis is currently the entering part B6");
			// Stream.of(BindingSets = QueryUtil.join(BindingSets,
			// v.getBindingSets())).parallel().forEachOrdered(e->{
			// //logger.info("THis is after exiting QueryUtil B6");
			// results = BindingSets.iterator();
			// });
			/*
			 * Iterator<Binding> x = v.getBindingSets().iterator(); Set<Binding> set =
			 * setFromIterator(x); //Set<Binding> foo =null; Iterable<List<Binding>>
			 * set_partition = Iterables.partition(set, 10); for(int i=0;
			 * i<v.getBindingSets().size();i++) { // for (i=0;i<set.toArray().length/2;i++)
			 * int k = 0; for(List<Binding> sp:set_partition) { Set<Binding> foo =
			 * sp.stream().collect(Collectors.toSet()); //new
			 * HashSet<Binding>(set_partition); BindingSets = QueryUtil.join(BindingSets,
			 * foo);
			 * //logger.info("THis is currently the entering part B6 segment1:"+BindingSets)
			 * ; k++; }
			 */// k=0;

			// }
			// for(int i=v.getBindingSets().size()/2; i<v.getBindingSets().size();i++) {
			Set<Binding> results1 = new HashSet<Binding>(BindingSets);
			// Vertex start1 = new Vertex();
			// start1=start;

			// BindingSets = QueryUtil.join(BindingSets, v.getBindingSets());
			// logger.info("THis is currently the entering part B6 segment2");
			// logger.info("THis is currently the entering part B6
			// segment1"+BindingSets.size()+"--"+v.getBindingSets().size());

		}
		// logger.info("THis is after exiting QueryUtil B6");
		results = BindingSets.iterator();

		// logger.info("THis is after BindingSets iterator QueryUtil B6");
		// logger.info("This is in BGPEval results1:" + BindingSets.size());
	}
//		//logger.info("This is the new value in BGPEval:" + BindingSets.size()+"--"+Iterators.size(results));

	// ForkJoinPool fjp =new
	// ForkJoinPool(Runtime.getRuntime().availableProcessors());

//fjp.submit(()->
////logger.info("This is the new value in BGPEval:" + BindingSets.size()+"--"+Iterators.size(results));//);
//fjp.shutdownNow();
	// //logger.info("This is the new value in BGPEval:" + BindingSets + "--" +
	// results);

	/*
	 * public void ExecuteJoins(Optimiser opt,List<EdgeOperator> operators) { while
	 * (operators != null) { es = Executors.newCachedThreadPool(); for (EdgeOperator
	 * eo : operators) { // consume the input BindingSets eo.setInput(input);
	 * 
	 * // //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in //
	 * BGPEval!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+g+"--"+ //
	 * opt.nextStage()+"--"+es+"--"+"--"+eo.getEdge()+"--"+eo.getStartVertex());
	 * input = null; // //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in //
	 * BGPEval!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+g+"--"+ //
	 * opt.nextStage()+"--"+es+"--"+"--"+eo.getEdge()+"--"+eo.getStartVertex());
	 * 
	 * try { es.submit(eo); } catch (RejectedExecutionException e) { if
	 * (Config.debug) //logger.info("Query execution is terminated."); } } //
	 * //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in BGPEval After //
	 * es.submit!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+getCurrentExeService());
	 * 
	 * es.shutdown(); while (!es.isTerminated()) { try { es.awaitTermination(3600,
	 * TimeUnit.SECONDS); } catch (InterruptedException e) {
	 * //logger.info("Query execution interrupted."); } catch
	 * (IllegalMonitorStateException e) {
	 * //logger.info("IllegalMonitorStateException"); } }
	 * 
	 * // if(Config.debug) // //logger.info("********************"); //logger.info(
	 * "***********************************************************************************************************************************************************************************************************"
	 * );
	 * 
	 * /* RelativeError.addEstimatedCardBGP(RelativeError.est_resultSize);
	 * RelativeError.print(); RelativeError.add_plan_joincards();
	 * RelativeError.est_resultSize.clear(); RelativeError.real_resultSize.clear();
	 * 
	 * //logger.info("Now it is here1"); for (EdgeOperator eo : operators) { if
	 * (eo.isFinished()) { if (Config.debug) { //logger.info("Query is finished by "
	 * + eo.getEdge()); } results = new Vector<Binding>().iterator();
	 * //logger.info("This is in BGPEval results:" + results); return; } }
	 * //logger.info("Now it is here2");
	 * 
	 * operators = opt.nextStage(); } i++;
	 * 
	 * // find out vertices with BindingSets that have not been used (joined)
	 * 
	 * Set<Binding> BindingSets = null; for (Vertex v : opt.getRemainVertices()) {
	 * if (Config.debug) { //logger.info("Remaining vertex: " + v); }
	 * //logger.info("This is in BGPEval results:" + BindingSets + "--" +
	 * v.getBindingSets()); BindingSets = QueryUtil.join(BindingSets,
	 * v.getBindingSets()); //logger.info("This is in BGPEval results1:" +
	 * BindingSets + "--" + v.getBindingSets()); }
	 * //logger.info("This is the new value in BGPEval:" + BindingSets);
	 * 
	 * results = BindingSets.iterator();
	 * //logger.info("This is the new value in BGPEval:" + BindingSets + "--" +
	 * results);
	 * 
	 * }
	 */
	/*
	 * public static int Transform(Edge edge) { HashJoin hj ;//= new HashJoin(edge);
	 * // hj.exec(); Stream.of( hj = new
	 * HashJoin(edge)).parallel().forEachOrdered(e->hj.exec()); hj.exec();
	 * //logger.info("This is every thread related to:"+edge+"--"+Thread.
	 * currentThread()); //
	 * //logger.info("Maximum Threads:"+ForkJoinPool.commonPool()+"--"+Runtime.
	 * getRuntime().availableProcessors()); return 0; }
	 */

	public static Set<EdgeOperator> setFromIterator(Iterator<EdgeOperator> it) {
		final Set<EdgeOperator> s = new LinkedHashSet<EdgeOperator>();
		while (it.hasNext())
			s.add(it.next());
		return s;
	}

	public static ArrayList<Binding> setFromIteratorB(Iterator<Binding> it) {
		final ArrayList<Binding> s = new ArrayList<Binding>();
		while (it.hasNext())
			s.add(it.next());
		return s;
	}

	public static LinkedHashSet<EdgeOperator> prepareBushyTree(
			Set<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator> set) {
		LinkedHashSet<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator> FinalOrdering = new LinkedHashSet<>();

		for (EdgeOperator s : set)
			FinalOrdering.add(s);
		/*
		 * int IsDr =0; String anm3 ; String bnm3[]; String bnm4[]; String anm ; String
		 * bnm[]; String bnm2[];
		 * 
		 * int iteration=0; int l=0; int m=0; EdgeOperator finalV = null;
		 * for(EdgeOperator o:set) { FinalOrdering.add(o);
		 * 
		 * 
		 * if(iteration>0) { for(EdgeOperator fo:FinalOrdering)
		 * if(fo.toString().equals(o.toString())) { l=1; break; } if(l==1) { l=0;
		 * continue; } } FinalOrdering.add(o);
		 * 
		 * for(EdgeOperator o2:set) { FinalOrderingTemp.add(o2);
		 * 
		 * if(GetVertexName(o.getEdge().getV1()).toString().equals(GetVertexName(o2.
		 * getEdge().getV1()).toString())) { // //logger.info();
		 * FinalOrderingTemp.add(o2); continue; }
		 * if(GetVertexName(o.getEdge().getV1()).toString().equals(GetVertexName(o2.
		 * getEdge().getV2()).toString())) { // //logger.info();
		 * FinalOrderingTemp.add(o2); continue; }
		 * if(GetVertexName(o.getEdge().getV2()).toString().equals(GetVertexName(o2.
		 * getEdge().getV1()).toString())) {// //logger.info();
		 * FinalOrderingTemp.add(o2); continue; }
		 * if(GetVertexName(o.getEdge().getV2()).toString().equals(GetVertexName(o2.
		 * getEdge().getV2()).toString())) {// //logger.info();
		 * FinalOrderingTemp.add(o2); continue; }
		 * if(GetVertexName(o.getEdge().getV1()).toString().length()>2
		 * &&GetVertexName(o2.getEdge().getV1()).toString().length()>2) for(int
		 * i=3;i>=2;i--)
		 * if(GetVertexName(o.getEdge().getV1()).toString().substring(0,i).equals(
		 * GetVertexName(o2.getEdge().getV1()).toString().substring(0,i))) { //
		 * //logger.info(); anm3=
		 * o.getEdge().getTriple().getPredicate().toString().substring(o.getEdge().
		 * getTriple().getPredicate().toString().indexOf(":")) ; bnm3=
		 * o2.getEdge().getTriple().getPredicate().toString().split("[/]"); bnm4=
		 * bnm3[bnm3.length-1].split("_"); for(String bnm10:bnm4) {
		 * if(anm3.contains(bnm10))
		 * {//logger.info("1This is now the problem to be solved here1232131231"+anm3+
		 * "--"+bnm10); IsDr++; } }
		 * 
		 * FinalOrderingTemp.add(o2); continue; }
		 * 
		 * if(GetVertexName(o.getEdge().getV1()).toString().length()>2
		 * &&GetVertexName(o2.getEdge().getV2()).toString().length()>2) for(int
		 * i=3;i>=2;i--)
		 * if(GetVertexName(o.getEdge().getV1()).toString().substring(0,i).equals(
		 * GetVertexName(o2.getEdge().getV2()).toString().substring(0,i))) { //
		 * //logger.info();
		 * 
		 * 
		 * anm= o.getEdge().getTriple().getPredicate().toString() ; bnm=
		 * o2.getEdge().getTriple().getPredicate().toString().split("[/]"); bnm2=
		 * bnm[bnm.length-1].split("_");
		 * 
		 * for(String bnm9:bnm2) { String xkl = bnm9;
		 * if(xkl.toString().contains(anm.toString()))
		 * {//logger.info("2This is now the problem to be solved here1232131231"+anm+
		 * "--"+bnm9); IsDr++; } } FinalOrderingTemp.add(o2); continue; }
		 * if(GetVertexName(o.getEdge().getV2()).toString().length()>2
		 * &&GetVertexName(o2.getEdge().getV1()).toString().length()>2) for(int
		 * i=3;i>=2;i--)
		 * if(GetVertexName(o.getEdge().getV2()).toString().substring(0,i).equals(
		 * GetVertexName(o2.getEdge().getV1()).toString().substring(0,i))) {
		 * ////logger.info(); FinalOrderingTemp.add(o2); continue; }
		 * if(GetVertexName(o.getEdge().getV2()).toString().length()>2
		 * &&GetVertexName(o2.getEdge().getV2()).toString().length()>2) for(int
		 * i=3;i>=2;i--)
		 * if(GetVertexName(o.getEdge().getV2()).toString().substring(0,i).equals(
		 * GetVertexName(o2.getEdge().getV2()).toString().substring(0,i))) {
		 * ////logger.info(); FinalOrderingTemp.add(o2); continue; }
		 * 
		 * } } /* if(IsDr>0) { FinalOrdering.clear(); for(EdgeOperator
		 * o:FinalOrderingTemp) for(Entry<EdgeOperator, List<EdgeOperator>>
		 * ord:orderingElements.entrySet()) { {
		 * 
		 * if(o.toString().equals(ord.getKey().toString()))
		 * orderingElementsTemp.put(ord.getKey(), ord.getValue()); } }
		 * 
		 * FinalOrdering= ProcessOrdering(FinalOrderingTemp,orderingElementsTemp); }else
		 * for(EdgeOperator fok:FinalOrderingTemp) FinalOrdering.add(fok); //
		 * FinalOrdering= ProcessOrdering(FinalOrdering,orderingElements);
		 * 
		 * //logger.info("This is the latest latest latest problem:"+IsDr);
		 */

		return FinalOrdering;
	}

	/*
	 * public static LinkedHashSet<EdgeOperator>
	 * orderBushyTree(LinkedHashSet<EdgeOperator> linkedHashSet){ Vector<String>
	 * order = new Vector<>(); order.add("Hash"); order.add("Bind");
	 * LinkedHashSet<EdgeOperator> operatorNew = new LinkedHashSet<>(); for(String
	 * o:order) for(EdgeOperator e:linkedHashSet) if(e.toString().contains(o)) { //
	 * for(EdgeOperator e2:linkedHashSet) { //
	 * if(e2.toString().equals(e.toString()))
	 * //logger.info("This is the new group of queriestwew:"+e); operatorNew.add(e);
	 * //break; }
	 * 
	 * 
	 * return operatorNew; }
	 */

	public static void PartitionedExecutions(LinkedHashMap<EdgeOperator, List<Binding>> finalResult2,LinkedHashMap<EdgeOperator, List<Binding>> finalResult3, String string) {
		Edgetype = string;
//finalResult;
		
	for (Entry<EdgeOperator, List<Binding>> fr : finalResult2.entrySet()) {
	System.out.println("Current process during finalization:"+fr);
		}
	//	for (EdgeOperator fr : Done) {
	//		System.out.println("Already processed edgeoperator:"+fr);
	//		}
	
		//System.out.println("Current process during fin:"+Edgetype);
		
		
		 List<EdgeOperator> ee = new ArrayList<>();
		 List<EdgeOperator> ee1 = new ArrayList<>();
		 System.out.println("This is the mask:");
			
		 for (Entry<EdgeOperator, List<Binding>> eo : finalResult2.entrySet()) {
			 
			 eo1=null;
				 eo2=null;
				 if(Done.contains(eo.getKey()))
					 continue;
			
			
						 
					if(eo.getKey().toString().contains("Bind"))	
						//	if(!Done.contains(eo.getKey()))
						 {
				 			for(Entry<Vertex, LinkedHashSet<Binding>>  sb123:StartBinding123.entrySet()) {
								
							if(!sb123.getKey().equals(eo.getKey().getStartVertex())) {
							
									System.out.println("It has no bind vertex yet:"+sb123.getValue().size()+"--"+eo.getKey());
						 				
								
							}
						}
				 }
			//	 System.out.println("Current process during finalization454545454545:"+eo);
				//	if(string.equals("Optional"))
					for(Entry<String, String> iff:ParaEng.InnerFilterSimple.entrySet()) {
						
								
						if(iff.getValue().equals(eo.getKey().getEdge().getV1().getNode().toString()) || iff.getValue().equals(eo.getKey().getEdge().getV2().getNode().toString()) ) {
							 if(Done.contains(eo.getKey()))
								 continue;
							eo2=eo.getKey();
							System.out.println("This is finalization134:" + eo2 + "--" + LocalTime.now());
							Stream.of(eo2.setInput(null)).parallel().forEach(e1 -> {
								ForkJoinPool fjp_bind7 = new ForkJoinPool();

								try {
									fjp_bind7.submit(eo2).get();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ExecutionException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								// Thread.sleep(300000);
								// Thread.sleep(2000);

								fjp_bind7.shutdown();
							}

							);
							Done.add(eo2);


					
						}

						
						if(iff.getKey().equals(eo.getKey().getEdge().getV1().getNode().toString()) || iff.getKey().equals(eo.getKey().getEdge().getV2().getNode().toString()) ) {
							if(Done.contains(eo.getKey()))
								 continue;	
							eo1=eo.getKey();
								System.out.println("This is finalization32:" + eo1 + "--" + LocalTime.now());
								Stream.of(eo1.setInput(null)).parallel().forEach(e1 -> {
									ForkJoinPool fjp_bind7 = new ForkJoinPool();

									try {
										fjp_bind7.submit(eo1).get();
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} catch (ExecutionException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									// Thread.sleep(300000);
									// Thread.sleep(2000);

									fjp_bind7.shutdown();
								}

								);
								Done.add(eo1);


						
							}
													
								
							
	}	
					 if(Done.contains(eo.getKey()))
						 continue;
			
	//	for (Entry<EdgeOperator, List<Binding>> eo11 : finalResult3.entrySet()) {
		//	if(eo11.getKey().equals(eo.getKey()))
			ForkJoinPool fjp = new ForkJoinPool();
			if(!StartBinding123Large.isEmpty() || StartBinding123Large.size()>0)
			fjp.submit(()->{
					for(List<EdgeOperator> jgle:JoinGroupsListExclusive)
				if(jgle.contains(eo.getKey()))
					for(EdgeOperator jgle1:jgle)
						if(jgle1.toString().contains("Bind"))
					for(Entry<HashMap<Vertex, String>, Set<Binding>> sb:StartBinding123Large.entrySet())
						for(Entry<Vertex, String> sb1:sb.getKey().entrySet())
						if(jgle1.getEdge().getV1().equals(sb1.getKey()) ||
								jgle1.getEdge().getV2().equals(sb1.getKey()) 
						&& sb.getValue().size()>0)
					{System.out.println("This is the problem of problems in problem:"+jgle1+"--"+jgle);		
					if(!jgle.equals(ee))
					ee.addAll(jgle);
					eo1=jgle1;
					}
						
			int isExecutable=0;
			if( eo1==null)
			for(Entry<Vertex, LinkedHashSet<Binding>> sb:StartBinding123.entrySet())
				if(eo.getKey().getEdge().getV1().equals(sb.getKey()) ||
						eo.getKey().getEdge().getV2().equals(sb.getKey()) 
				&& sb.getValue().size()>0) {
					isExecutable=1;
				}
			
		

			}).join();
			
			else if(!StartBinding123.isEmpty() || StartBinding123.size()>0)
			fjp.submit(()->{
					for(List<EdgeOperator> jgle:JoinGroupsListExclusive)
				if(jgle.contains(eo.getKey()))
					for(EdgeOperator jgle1:jgle)
						if(jgle1.toString().contains("Bind"))
					for(Entry<Vertex, LinkedHashSet<Binding>> sb:StartBinding123.entrySet())
						if(jgle1.getEdge().getV1().equals(sb.getKey()) ||
								jgle1.getEdge().getV2().equals(sb.getKey()) 
						&& sb.getValue().size()>0)
					{System.out.println("This is the problem of problems in problem:"+jgle1+"--"+jgle);		
					if(!jgle.equals(ee))
					ee.addAll(jgle);
					eo1=jgle1;
					}
						
			int isExecutable=0;
			if( eo1==null)
			for(Entry<Vertex, LinkedHashSet<Binding>> sb:StartBinding123.entrySet())
				if(eo.getKey().getEdge().getV1().equals(sb.getKey()) ||
						eo.getKey().getEdge().getV2().equals(sb.getKey()) 
				&& sb.getValue().size()>0) {
					isExecutable=1;
				}
			if(isExecutable==0)
			for(EdgeOperator jgll:JoinGroupsListLeft)
				if(eo.getKey().getStartVertex()!=null)
			if( (eo.getKey().getStartVertex().equals(jgll.getEdge().getV1()) ||
					eo.getKey().getStartVertex().equals(jgll.getEdge().getV2())) && !Done.contains(jgll) )
					for(Entry<Vertex, LinkedHashSet<Binding>> sb:StartBinding123.entrySet())		
					if (sb.getKey().equals(jgll.getEdge().getV1()) || sb.getKey().equals(jgll.getEdge().getV2()) &&
							sb.getValue().size()>0) {
						eo2=jgll;
					int a=	JoinGroupsListLeft.indexOf(eo.getKey());	
					int b=	JoinGroupsListLeft.indexOf(eo2);	
					Collections.swap(JoinGroupsListLeft, a, b);
				
			}
if(eo2==null && isExecutable==0) {
	for(EdgeOperator jgll:JoinGroupsListRight)
		if(eo.getKey().getStartVertex()!=null)
		if((eo.getKey().getStartVertex().equals(jgll.getEdge().getV1()) ||
				eo.getKey().getStartVertex().equals(jgll.getEdge().getV2())) && !Done.contains(jgll) )
				for(Entry<Vertex, LinkedHashSet<Binding>> sb:StartBinding123.entrySet())		
				if (sb.getKey().equals(jgll.getEdge().getV1()) || sb.getKey().equals(jgll.getEdge().getV2()) &&
						sb.getValue().size()>0) {
					eo2=jgll;
					int a=	JoinGroupsListRight.indexOf(eo.getKey());	
					int b=	JoinGroupsListRight.indexOf(eo2);	
					Collections.swap(JoinGroupsListRight, a, b);
				
		}
}
			}).join();
			
			
			fjp.shutdown();
			//}
			
			// fjp_bind.submit(()->{
			if(eo2!=null) {
				System.out.println("This is finalization5:" + eo2 + "--" + LocalTime.now());
				Stream.of(eo2.setInput(null)).parallel().forEach(e1 -> {
					ForkJoinPool fjp_bind7 = new ForkJoinPool();

					try {
						fjp_bind7.submit(eo2).get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Thread.sleep(300000);
					// Thread.sleep(2000);

					fjp_bind7.shutdown();
				}

				);
				Done.add(eo2);

				System.out.println("This is finalization443:" + eo.getKey() + "--" + LocalTime.now());
				Stream.of(eo.getKey().setInput(null)).parallel().forEach(e1 -> {
					ForkJoinPool fjp_bind7 = new ForkJoinPool();

					try {
						fjp_bind7.submit(eo.getKey()).get();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Thread.sleep(300000);
					// Thread.sleep(2000);

					fjp_bind7.shutdown();
				}

				);
				Done.add(eo.getKey());

			}
			else	if(eo1!=null) {
				JoinGroupsListExclusive.remove(ee);
				System.out.println("This is systemexclusive:"+BGPEval.JoinGroupsListExclusive);
				
				ee.remove(eo1);
				ee1.add(eo1);
				ee1.addAll(ee);
				System.out.println("This is systemexclusive456:"+ee+"--"+eo1+"--"+ee1);
				
				System.out.println("This is systemexclusive123:"+ee1);
				
				System.out.println("This is ee1:"+ee1);
				JoinGroupsListExclusive.add(ee1);
				finalResult.remove(eo.getKey());
				finalResult.put(eo1, null);
				//int l=0;
			//	for(Entry<EdgeOperator, Map<EdgeOperator, Integer>> fr:exchangeOfElements.entrySet())
			//		for(Entry<EdgeOperator,Integer> fr1:fr.getValue().entrySet())
			//		if(fr1.getKey().equals(eo1))
			//			l=fr1.getValue();
				
				exchangeOfElements.remove(eo.getKey());
				HashMap<EdgeOperator, Integer> ab = new HashMap<>();
				ab.put(eo1, 1);
				exchangeOfElements.put(eo1, ab);
//				for(Entry<EdgeOperator, Map<EdgeOperator, Integer>> eoe:exchangeOfElements.entrySet())
	//			System.out.println("This is during last:" +eoe);
				System.out.println("This is finalization344:" + Done);
				
	System.out.println("This is finalization311:" + eo1 + "--" + LocalTime.now());
	Stream.of(eo1.setInput(null)).parallel().forEach(e1 -> {
		ForkJoinPool fjp_bind7 = new ForkJoinPool();

		try {
			fjp_bind7.submit(eo1).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Thread.sleep(300000);
		// Thread.sleep(2000);

		fjp_bind7.shutdown();
	}

	);
	Done.add(eo1);
	
			}		else {
				
					if (Done.contains(eo.getKey()))
						continue;
			
	System.out.println("This is finalization998:" + eo + "--" + LocalTime.now());
			Stream.of(eo.getKey().setInput(null)).parallel().forEach(e1 -> {
				ForkJoinPool fjp_bind7 = new ForkJoinPool();

				try {
					fjp_bind7.submit(eo.getKey()).get();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Thread.sleep(300000);
				// Thread.sleep(2000);

				fjp_bind7.shutdown();
			}

			);
}
			// }).invoke();
			Done.add(eo.getKey());
				}
	}

	/*
	 * //logger.info("This is out of the looop:"); int aaa=0; for(Entry<Triple,
	 * Integer> tc:TripleExecution.TrackCompletion.entrySet()) if(tc.getKey() ==
	 * eo.getEdge().getTriple()) // { while(tc.getValue()==0) {System.out.print("");
	 * if(tc.getValue()==1) { aaa=1; break; // } } if(aaa==1) { aaa=0; break; } }
	 */

////logger.info("This is sequence of edgeOperators in actual"+eo.toString());
	// BGPEval!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+g+"--"+
	// opt.nextStage()+"--"+es+"--"+"--"+eo.getEdge()+"--"+eo.getStartVertex());
	// input = null;
	// //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
	// BGPEval!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+g+"--"+
	// opt.nextStage()+"--"+es+"--"+"--"+eo.getEdge()+"--"+eo.getStartVertex());

	/*
	 * public static void PartitionedExecutionsSingle(LinkedHashMap<EdgeOperator,
	 * Set<Binding>> finalResult2, ConcurrentHashMap<EdgeOperator,EdgeOperator>
	 * exchangeOfElements2,List<EdgeOperator> operators) {
	 * 
	 * LinkedList<EdgeOperator> newOperators = new LinkedList<>();
	 * LinkedList<EdgeOperator> newOperatorsSorted = new LinkedList<>();
	 * 
	 * for(Entry<EdgeOperator, Map<EdgeOperator, Integer>>
	 * e2:BGPEval.exchangeOfElements.entrySet()) { for(Entry<EdgeOperator, Integer>
	 * e1:e2.getValue().entrySet()) for(EdgeOperator o:operators) {
	 * if(e1.getValue()!=null)
	 * if(e1.getKey().getEdge().toString().equals(o.getEdge().toString()) &&
	 * !(e1.getKey().getEdge().toString().equals(e1.getKey().getEdge().toString())))
	 * newOperators.add(new HashJoin(e1.getKey().getEdge()));
	 * if(e1.getKey().getEdge().toString().equals(o.getEdge().toString()) &&
	 * (e1.getKey().getEdge().toString().equals(e1.getKey().getEdge().toString())))
	 * newOperators.add(e1.getKey());
	 * 
	 * // else
	 * //if(e1.getKey().getEdge().toString().equals(e1.getValue().getEdge().toString
	 * ())) //newOperators.add(o); } }
	 * 
	 * 
	 * 
	 * //for(HashSet<List<EdgeOperator>> er:execOrder.keySet()) //
	 * for(List<EdgeOperator> er1:er) // for(EdgeOperator er2:er1) //
	 * for(EdgeOperator no:newOperators) //
	 * if(er2.getEdge().toString().equals(no.getEdge().toString())) //
	 * newOperatorsSorted.add(no);
	 * 
	 * System.out.println("These are the replaced first non literal/URI characters"
	 * +newOperatorsSorted);
	 * 
	 * 
	 * if(newOperatorsSorted.size()==1) { fjp_bind= new ForkJoinPool(6) ;
	 * 
	 * } if(newOperatorsSorted.size()==2) { fjp_bind = new ForkJoinPool(3) ;
	 * fjp_bind1 = new ForkJoinPool(3) ; } if(newOperatorsSorted.size()==3) {
	 * fjp_bind = new ForkJoinPool(2) ;
	 * 
	 * fjp_bind1 = new ForkJoinPool(2) ;
	 * 
	 * fjp_bind2 = new ForkJoinPool(2) ; } if(newOperatorsSorted.size()==4) {
	 * fjp_bind = new ForkJoinPool(2) ;
	 * 
	 * fjp_bind1 = new ForkJoinPool(2) ;
	 * 
	 * fjp_bind2 = new ForkJoinPool(1) ;
	 * 
	 * fjp_bind3 = new ForkJoinPool(1) ; } if(newOperatorsSorted.size()==5) {
	 * fjp_bind = new ForkJoinPool(2) ;
	 * 
	 * fjp_bind1 = new ForkJoinPool(1) ;
	 * 
	 * fjp_bind2 = new ForkJoinPool(1) ;
	 * 
	 * fjp_bind3 = new ForkJoinPool(1) ;
	 * 
	 * fjp_bind4 = new ForkJoinPool(1) ; } if(newOperatorsSorted.size()==6) {
	 * fjp_bind = new ForkJoinPool(1) ; fjp_bind1 = new ForkJoinPool(1) ; fjp_bind2
	 * = new ForkJoinPool(1) ; fjp_bind3 = new ForkJoinPool(1) ; fjp_bind4 = new
	 * ForkJoinPool(1) ; fjp_bind5 = new ForkJoinPool(1) ; } for (EdgeOperator eo :
	 * (newOperatorsSorted.isEmpty())?operators:newOperatorsSorted) { /*
	 * if(eo.toString().contains("Bind")) {
	 * 
	 * //fjp_bind.submit(()->{ Stream.of(eo.setInput(null)).parallel().forEach(
	 * e1->{ ForkJoinPool fjp_bind7 = new
	 * ForkJoinPool(Runtime.getRuntime().availableProcessors());
	 * 
	 * fjp_bind7.submit(eo).invoke(); // Thread.sleep(300000); //
	 * Thread.sleep(2000);
	 * 
	 * fjp_bind7.shutdown();
	 * 
	 * }
	 * 
	 * ); //}).invoke();
	 * 
	 * 
	 * } else {
	 * 
	 * if(i000==0) Stream.of(eo.setInput(null)).parallel().forEach( e1->{
	 * 
	 * try { fjp_bind.submit(eo).get(); } catch (InterruptedException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch (ExecutionException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } //
	 * if(i000%2==1)
	 * 
	 * 
	 * } );
	 * 
	 * 
	 * if(i000==1) Stream.of(eo.setInput(null)).parallel().forEach( e1->{
	 * 
	 * // if(i000%2==0) try { fjp_bind1.submit(eo).get(); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (ExecutionException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } // if(i000%2==1)
	 * 
	 * 
	 * } );
	 * 
	 * 
	 * if(i000==2) Stream.of(eo.setInput(null)).parallel().forEach( e1->{
	 * 
	 * // if(i000%2==0) try { fjp_bind2.submit(eo).get(); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (ExecutionException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } // if(i000%2==1)
	 * 
	 * 
	 * } );
	 * 
	 * 
	 * if(i000==3) Stream.of(eo.setInput(null)).parallel().forEach( e1->{
	 * 
	 * // if(i000%2==0) try { fjp_bind3.submit(eo).get(); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (ExecutionException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } // if(i000%2==1)
	 * 
	 * 
	 * } );
	 * 
	 * 
	 * if(i000==4) Stream.of(eo.setInput(null)).parallel().forEach( e1->{
	 * 
	 * // if(i000%2==0) try { fjp_bind4.submit(eo).get(); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (ExecutionException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } // if(i000%2==1)
	 * 
	 * } );
	 * 
	 * 
	 * if(i000==5) Stream.of(eo.setInput(null)).parallel().forEach( e1->{
	 * 
	 * // if(i000%2==0) try { fjp_bind5.submit(eo).get(); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (ExecutionException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } // if(i000%2==1)
	 * 
	 * 
	 * } ); i000++; //}
	 * 
	 * }
	 * 
	 * if(newOperatorsSorted.size()==1) { try { fjp_bind.awaitTermination(270,
	 * TimeUnit.SECONDS); fjp_bind.shutdown(); } catch (InterruptedException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * fjp_bind.shutdown();
	 * 
	 * }
	 * 
	 * if(newOperatorsSorted.size()==2) {
	 * 
	 * try { fjp_bind.awaitTermination(180, TimeUnit.SECONDS);
	 * 
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { fjp_bind1.awaitTermination(180, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * fjp_bind.shutdown(); fjp_bind1.shutdown();
	 * 
	 * } if(newOperatorsSorted.size()==3) { try { fjp_bind.awaitTermination(270,
	 * TimeUnit.SECONDS);
	 * 
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { fjp_bind1.awaitTermination(270, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * try { fjp_bind2.awaitTermination(270, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } fjp_bind.shutdown(); fjp_bind1.shutdown();
	 * fjp_bind2.shutdown();
	 * 
	 * } if(newOperatorsSorted.size()==4) { try { fjp_bind.awaitTermination(90,
	 * TimeUnit.SECONDS);
	 * 
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { fjp_bind1.awaitTermination(90, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * try { fjp_bind2.awaitTermination(90, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { fjp_bind3.awaitTermination(90, TimeUnit.SECONDS);
	 * 
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } fjp_bind.shutdown(); fjp_bind1.shutdown();
	 * fjp_bind2.shutdown(); fjp_bind3.shutdown();
	 * 
	 * }
	 * 
	 * if(newOperatorsSorted.size()==5) { try { fjp_bind.awaitTermination(90,
	 * TimeUnit.SECONDS);
	 * 
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { fjp_bind1.awaitTermination(90, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * try { fjp_bind2.awaitTermination(90, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { fjp_bind3.awaitTermination(90, TimeUnit.SECONDS);
	 * 
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * try { fjp_bind4.awaitTermination(90, TimeUnit.SECONDS);
	 * 
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } fjp_bind.shutdown(); fjp_bind1.shutdown();
	 * fjp_bind2.shutdown(); fjp_bind3.shutdown(); fjp_bind4.shutdown(); }
	 * 
	 * 
	 * if(newOperatorsSorted.size()==6) {
	 * 
	 * try { fjp_bind.awaitTermination(270, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { fjp_bind1.awaitTermination(270, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * try { fjp_bind2.awaitTermination(270, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { fjp_bind3.awaitTermination(270, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * try { fjp_bind4.awaitTermination(270, TimeUnit.SECONDS); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * try { fjp_bind5.awaitTermination(270, TimeUnit.SECONDS);
	 * 
	 * } catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * fjp_bind.shutdown(); fjp_bind1.shutdown(); fjp_bind2.shutdown();
	 * fjp_bind3.shutdown(); fjp_bind4.shutdown(); fjp_bind5.shutdown();
	 * 
	 * //}
	 * 
	 * 
	 * }
	 * 
	 * i000=0;
	 * 
	 * }
	 * 
	 * 
	 */
////logger.info("This is sequence of edgeOperators in actual"+eo.toString());
	// BGPEval!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+g+"--"+
	// opt.nextStage()+"--"+es+"--"+"--"+eo.getEdge()+"--"+eo.getStartVertex());
	// input = null;
	// //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
	// BGPEval!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!:"+g+"--"+
	// opt.nextStage()+"--"+es+"--"+"--"+eo.getEdge()+"--"+eo.getStartVertex());

	/*
	 * public static int TransformDep(Vertex start, Edge edge) {
	 * ////logger.info("This is beginning of TransformDep");
	 * 
	 * //logger.info("This is every thread related to:"+start+"--"+edge+"--"+Thread.
	 * currentThread()); BindJoin bj;//=new BindJoin(start,edge); HashJoin hj; //
	 * //logger.info("These are the Start Dep. of Stream:"+start+"--"+edge+"--"+
	 * start.getBindingSets() ); if(start==null) Stream.of( hj = new
	 * HashJoin(edge)).parallel().forEachOrdered(e->hj.exec());
	 * 
	 * else Stream.of( bj = new
	 * BindJoin(start,edge)).parallel().forEachOrdered(e->bj.exec()); //Stream.of(
	 * bj = new BindJoin(start,edge)).parallel().forEach(e->bj.exec()); //
	 * //logger.info("These are the End Dep. of Stream" );
	 * 
	 * // //logger.info("Maximum Threads:"+ForkJoinPool.commonPool()+"--"+Runtime.
	 * getRuntime().availableProcessors()); return 0; }
	 */
	public static HashMap<Integer, EdgeOperator> findFirstHash(List<EdgeOperator> EdgeO) {
		int i = 0;
		HashMap<Integer, EdgeOperator> NonLiteral = new HashMap<>();
		for (EdgeOperator e : EdgeO) {
			// if( (e.getEdge().getTriple().getSubject().isLiteral()==false
			// )&&(e.getEdge().getTriple().getObject().isLiteral()==false)
			// && (e.getEdge().getTriple().getSubject().isURI()==false
			// )&&(e.getEdge().getTriple().getObject().isURI()==false))
			if (e.toString().contains("Hash")) {
				NonLiteral.put(i, e);
				break;

			}
			i++;

		}

		return NonLiteral;
	}



	public static String GetVertexName(Vertex input) {
		if (input.getNode().isURI())
			return input.getNode().toString();
		else if (input.getNode().isLiteral())
			return input.getNode().getLiteral().toString();
		else
			return input.getNode().getName().toString();

	}

	public static EdgeOperator findFirstNonLiteral(List<EdgeOperator> EdgeO) {
		// int i=0;
		EdgeOperator NonLiteral = null;
		for (EdgeOperator e : EdgeO) {
			if ((e.getEdge().getTriple().getSubject().isLiteral() == false)
					&& (e.getEdge().getTriple().getObject().isLiteral() == false)
					&& (e.getEdge().getTriple().getSubject().isURI() == false)
					&& (e.getEdge().getTriple().getObject().isURI() == false))
			// if(e.toString().contains("Hash"))
			{
				NonLiteral = e;
				// i++;
				break;
			}
		}
		if (NonLiteral == null)
			return EdgeO.get(0);
		return NonLiteral;
	}

	public static LinkedListMultimap<EdgeOperator, Integer> CreateBushyTreesLeft(List<EdgeOperator> operators_BushyTree,
			List<EdgeOperator> operators, List<EdgeOperator> operators_BushyTreeOrdered) {

		// for(Entry<Integer, List<EdgeOperator>> es:eSet.entrySet())
		// //logger.info("This is now the new list444444:"+es);

		// logger.info("This is the source table in left :"+operators_BushyTree);

		List<EdgeOperator> joinGroups2 = new Vector<>();
		LinkedListMultimap<EdgeOperator, Integer> joinGroupsOrdered = LinkedListMultimap.create();
		LinkedListMultimap<EdgeOperator, Integer> joinGroups3 = LinkedListMultimap.create();
		int i5 = 0;
		for (EdgeOperator obt : operators_BushyTree) {
			for (EdgeOperator obt1 : operators_BushyTree) {
				if ((obt.getEdge().getV1().toString().equals(obt1.getEdge().getV1().toString())
						|| obt.getEdge().getV1().toString().equals(obt1.getEdge().getV2().toString()))
						&& !joinGroups2.contains(obt1)) {

					if (i5 == 0)
						joinGroups2.add(obt1);
					if (i5 > 0)
						if ((!obt.equals(obt1)))
							joinGroups2.add(obt1);

					i5++;
				}

			}

		}

		String[] sic1;
//	Map<EdgeOperator,Integer> joinGroups3 = new ConcurrentHashMap<>();
		for (int i = 0; i < Optimizer.triples.length; i++)
			if (Optimizer.triples[i][8] != null) {
				sic1 = Optimizer.triples[i][8].toString().replaceAll("[^0-9,]", "").split(",");
				for (String si : sic1)
					if (!si.equals(""))
						for (int i1 = 0; i1 < joinGroups2.size(); i1++) {
							// logger.info("This is the last
							// resort:"+GetVertexName(joinGroups2.get(i1).getEdge().getV2())+"--"+(Optimizer.triples[i][1].toString().replaceAll("\"",
							// "")));
							if (GetVertexName(joinGroups2.get(i1).getEdge().getV1())
									.equals(Optimizer.triples[i][0].toString().replaceAll("\"", ""))
									&& GetVertexName(joinGroups2.get(i1).getEdge().getV2())
											.equals(Optimizer.triples[i][1].toString().replaceAll("\"", "")))
								joinGroups3.put(joinGroups2.get(i1), Integer.parseInt(si));
						}
			}
		for (EdgeOperator obt : operators_BushyTreeOrdered)
			for (Entry<EdgeOperator, Integer> jg2 : joinGroups3.entries())
				if (obt.getEdge().toString().equals(jg2.getKey().getEdge().toString()))
					joinGroupsOrdered.put(jg2.getKey(), jg2.getValue());

//for(Entry<EdgeOperator, Integer> jg3:joinGroupsOrdered.entries())
		// logger.info("This is the created table in left
		// :"+jg3.getValue()+"--"+jg3.getKey());

		return joinGroupsOrdered;
	}

	public static LinkedListMultimap<EdgeOperator, Integer> CreateBushyTreesRight(
			List<EdgeOperator> operators_BushyTree, List<EdgeOperator> operators,
			List<EdgeOperator> operators_BushyTreeOrdered) {
		if (operators_BushyTree == null)
			return null;
//for(Entry<Integer, List<EdgeOperator>> es:eSet.entrySet())
//	//logger.info("This is now the new list444444:"+es);

//logger.info("This is the source table in left :"+operators_BushyTree);

		List<EdgeOperator> joinGroups2 = new Vector<>();

		for (EdgeOperator obt : operators_BushyTree) {
			for (EdgeOperator obt1 : operators_BushyTree) {
				if ((obt.getEdge().getV2().toString().equals(obt1.getEdge().getV2().toString())
						|| obt.getEdge().getV2().toString().equals(obt1.getEdge().getV1().toString()))
						&& !joinGroups2.contains(obt1)) {
					joinGroups2.add(obt1);
				}

			}

		}

//logger.info("This is the created table in left :"+joinGroups2);

		String[] sic1;
		LinkedListMultimap<EdgeOperator, Integer> joinGroupsOrdered = LinkedListMultimap.create();
		LinkedListMultimap<EdgeOperator, Integer> joinGroups3 = LinkedListMultimap.create();

		for (int i = 0; i < Optimizer.triples.length; i++)
			if (Optimizer.triples[i][8] != null) {
				sic1 = Optimizer.triples[i][8].toString().replaceAll("[^0-9,]", "").split(",");
				for (String si : sic1)
					if (!si.equals(""))
						for (int i1 = 0; i1 < joinGroups2.size(); i1++)
							if (GetVertexName(joinGroups2.get(i1).getEdge().getV1())
									.equals(Optimizer.triples[i][0].toString().replaceAll("\"", ""))
									&& GetVertexName(joinGroups2.get(i1).getEdge().getV2())
											.equals(Optimizer.triples[i][1].toString().replaceAll("\"", "")))
								joinGroups3.put(joinGroups2.get(i1), Integer.parseInt(si));
			}
		for (EdgeOperator obt : operators_BushyTreeOrdered)
			for (Entry<EdgeOperator, Integer> jg2 : joinGroups3.entries())
				if (obt.getEdge().toString().equals(jg2.getKey().getEdge().toString()))
					joinGroupsOrdered.put(jg2.getKey(), jg2.getValue());

//for(Entry<EdgeOperator, Integer> jg3:joinGroupsOrdered.entries())
		// logger.info("This is the created table in left
		// :"+jg3.getValue()+"--"+jg3.getKey());

		return joinGroupsOrdered;
	}

	public static Multimap<List<EdgeOperator>, Integer> CreateBushyTreesExclusive(
			List<EdgeOperator> operators_BushyTree,
			Map<Integer, List<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator>> joinGroups,
			List<EdgeOperator> operators, List<EdgeOperator> operatorsOrder) {

		LinkedHashMap<Integer, List<EdgeOperator>> eSet = new LinkedHashMap<>();
		String[] sic;
		for (int i = 0; i < Optimizer.triples.length; i++)
			if (Optimizer.triples[i][8] != null) {
				sic = Optimizer.triples[i][8].toString().replaceAll("[^0-9,]", "").split(",");
				for (String si : sic)
					if (!si.equals(""))
						eSet.put(Integer.parseInt(si.toString().replaceAll(",", "")), new ArrayList<>());
			}
//for(Entry<Integer, List<EdgeOperator>> es:eSet.entrySet())
		// System.out.println("This is now the new list444444:"+es);

		for (EdgeOperator oiSub : operators_BushyTree) {
			// if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
			// {

			// //logger.info("#######################Sub Query to be
			// seperated###################:"+oiSub);

			String CurrentURL = null;

			for (int i = 0; i < Optimizer.triples.length; i++)
				if (Optimizer.triples[i][8] != null) {
//logger.info("This is tarzan:"+Optimizer.triples[i][1]+"--"+GetVertexName(oiSub.getEdge().getV2()));
					if (GetVertexName(oiSub.getEdge().getV1()).toString().equals(Optimizer.triples[i][0].toString())
							&& GetVertexName(oiSub.getEdge().getV2()).toString()
									.equals(Optimizer.triples[i][1].toString().replaceAll("\"", ""))) {
//	for(Entry<Integer, HashSet<EdgeOperator>> es1:eSet.entrySet())
//		if(es1.getKey())
						//// logger.info("1This is now checking the exclusive
						//// group00000:"+Optimizer.triples[i][8]);

						sic = Optimizer.triples[i][8].toString().replaceAll("[^0-9,]", "").split(",");
						int kk = 0;
						for (String si : sic) {
							if (si.length() > 1)
								if (!si.equals("")) {
									List<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator> nn = new ArrayList<>();

									for (Entry<Integer, List<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator>> es1 : eSet
											.entrySet()) {
										if (es1.getKey() == Integer.parseInt(si.toString().replaceAll(",", ""))) {// logger.info("1This
																													// is
																													// now
																													// checking
																													// the
																													// exclusive
																													// group:"+es1.getKey()+"--"+es1.getValue());
																													// logger.info("1This
																													// is
																													// now
																													// checking
																													// the
																													// exclusive
																													// group111:"+oiSub);
											if (!es1.getValue().toString().contains(oiSub.toString()))
												nn.add(oiSub);
											nn.addAll(es1.getValue());
											eSet.replace(Integer.parseInt(si.toString().replaceAll(",", "")), nn);
											kk++;
										}
									}
								}
						}
					}
				}
		}

		// Map<List<EdgeOperator>, Integer> joinGroups2 = new HashMap<>();
		Multimap<List<EdgeOperator>, Integer> joinGroups2 = ArrayListMultimap.create();

		for (Entry<Integer, List<EdgeOperator>> es1 : eSet.entrySet())

		{

			List<EdgeOperator> mm = new ArrayList<>();

			for (EdgeOperator obt : operators_BushyTree)

			{
				for (EdgeOperator es2 : es1.getValue()) {
					if (obt.toString().equals(es2.toString())) {
						if (!mm.toString().contains(obt.toString()))
							mm.add(obt);
						System.out.println("This is the triple555:" + es1.getKey() + "--" + es2);
					}

				}

			}
			List<EdgeOperator> mm2 = new ArrayList<>(mm);
			System.out.println("THis is triple666666:" + mm2 + "--" + es1.getKey());
			joinGroups2.put(mm2, es1.getKey());
			// logger.info("-----------------------------------------------------------------");
			mm.clear();

		}

		for (Entry<List<EdgeOperator>, Integer> es : joinGroups2.entries())
			System.out.println("This is the triple444445555:" + es.getKey() + "--" + es.getValue());

		return joinGroups2;
	}

	/*
	 * public static LinkedHashSet<EdgeOperator>
	 * ProcessOrdering(LinkedHashSet<EdgeOperator>
	 * FinalOrderingTemp,LinkedHashMap<EdgeOperator, List<EdgeOperator>>
	 * orderingElementsTemp){ LinkedHashSet<EdgeOperator> FinalOrdering = new
	 * LinkedHashSet<>(); EdgeOperator finalV = null; for(EdgeOperator
	 * o:FinalOrderingTemp) { FinalOrdering.add(o); break; }
	 * 
	 * for(Entry<EdgeOperator, List<EdgeOperator>>
	 * ord:orderingElementsTemp.entrySet()) {
	 * 
	 * 
	 * for(EdgeOperator o2:ord.getValue()) { for(EdgeOperator o:FinalOrderingTemp) {
	 * 
	 * if(GetVertexName(o.getEdge().getV1()).toString().equals(GetVertexName(o2.
	 * getEdge().getV2()).toString())) { // //logger.info(); finalV = o; //
	 * FinalOrdering.add(o2); //continue; }
	 * if(GetVertexName(o.getEdge().getV1()).toString().equals(GetVertexName(o2.
	 * getEdge().getV2()).toString())) { // //logger.info(); finalV = o; //
	 * FinalOrdering.add(o2); //continue; } /*
	 * if(GetVertexName(o.getEdge().getV1()).toString().equals(GetVertexName(o2.
	 * getEdge().getV2()).toString())) { finalV = o; // FinalOrdering.add(o2); //
	 * continue; }
	 * if(GetVertexName(o.getEdge().getV2()).toString().equals(GetVertexName(o2.
	 * getEdge().getV2()).toString())) { finalV = o; // FinalOrdering.add(o2);
	 * //continue; }
	 * /*if(GetVertexName(o.getEdge().getV2()).toString().equals(GetVertexName(o2.
	 * getEdge().getV2()).toString())) { finalV = o; // FinalOrdering.add(o2); } }
	 * 
	 * } FinalOrdering.add(finalV); } return FinalOrdering; }
	 */
	/*
	 * static private Set<com.hp.hpl.jena.sparql.core.Var>
	 * getJoinVars(Collection<Binding> left, Collection<Binding> right) { if (left
	 * == null || right == null) return null; if (left.isEmpty() == true ||
	 * right.isEmpty() == true) return null; if (left.size() == 0 || right.size() ==
	 * 0) return null;
	 * 
	 * //logger.info("This is in Join QueryUtil5:"+right.size()+"--"+left.size());
	 * 
	 * Set<com.hp.hpl.jena.sparql.core.Var> joinVars = new
	 * HashSet<com.hp.hpl.jena.sparql.core.Var>();
	 * Iterator<com.hp.hpl.jena.sparql.core.Var> l = left.iterator().next().vars();
	 * BindingSet r = right.iterator().next();
	 * ////logger.info("This is rule no. 1:"+r); while (l.hasNext()) {
	 * com.hp.hpl.jena.sparql.core.Var v = l.next(); //
	 * //logger.info("This is rule no. 2:"+v);
	 * 
	 * if (r.contains(v)) { joinVars.add(v); } } //
	 * //logger.info("This is in Join QueryUtil6:"+joinVars);
	 * 
	 * return joinVars; }
	 * 
	 * 
	 */

	/*
	 * public static Set<Binding> ResultsSize8(ArrayList<Set<Binding>>
	 * finalResultQueryOrder){ Set<Binding> r5= new HashSet<>();
	 * 
	 * int a = 0,b=0; rTreeLowestLevel.clear(); for(ArrayList<Integer>
	 * e:Treelowestlevel) { // if(TreelowestlevelProcessed.contains(e)) // continue;
	 * ForkJoinPool fjp = new ForkJoinPool(4); fjp.submit(()-> rTreeLowestLevel=
	 * QueryUtil.join(finalResultQueryOrder.get(e.get(0)),finalResultQueryOrder.get(
	 * e.get(1)))); //try { // ProcessedSets.wait(); //} catch (InterruptedException
	 * e1) { // TODO Auto-generated catch block // e1.printStackTrace(); // }
	 * if((rTreeLowestLevel).size()>0 && !ProcessedSets.contains(rTreeLowestLevel))
	 * { synchronized(ProcessedSets) {
	 * 
	 * //logger.info("This is happening yeay1:"+Treelowestlevel);
	 * 
	 * //r5=
	 * QueryUtil.join(finalResultQueryOrder.get(e.get(0)),finalResultQueryOrder.get(
	 * e.get(1))); a=e.get(0); b=e.get(1); ProcessedSets.add(rTreeLowestLevel);
	 * //logger.info("This is happening yeay:"+ProcessedSets.size()+"--"+e.get(0)+
	 * "--"+e.get(1));
	 * //logger.info("This is happening yeay:"+ProcessedSets.get(0));
	 * 
	 * LowestLevelProcess(a,b); break;
	 * 
	 * 
	 * } } fjp.shutdownNow();
	 * 
	 * }
	 * 
	 * //logger.info("This is happening yeay1:"+Treelowestlevel);
	 * 
	 * 
	 * 
	 * return r5;
	 * 
	 * }
	 */
//public static void ResultsSize14(HashMap<List<EdgeOperator>, Set<Binding>> processedSets2, HashSet<List<EdgeOperator>> evaluatedTriples, LinkedHashMap<List<EdgeOperator>, Set<Binding>> joinedTriples)
//{

	// if(evaluatedTriples.size()==0)
	// {
//	for(ArrayList<Integer> e:Treelowestlevel)
//	{
	// System.out.println("This is the current size of evaluated
	// triples:"+HashJoin.EvaluatedTriples.size());
//		if(HashJoin.EvaluatedTriples.size()>0)
//			break;
//

	// }
//	}
//	else {
	/*
	 * for(Integer e:TreelowestlevelProcessed) { LinkedHashMap<List<EdgeOperator>,
	 * Set<Binding>> firstSet = new LinkedHashMap<>(); int equity=0; for(
	 * Entry<List<EdgeOperator>, Set<Binding>> ps2:processedSets2.entrySet())
	 * {firstSet.clear(); firstSet.put(ps2.getKey(),ps2.getValue()); //
	 * System.out.println("This is the numbering:"+e+"--"+equity); if(equity==e)
	 * break; equity++; } int s=0; for(List<EdgeOperator> et:evaluatedTriples) {
	 * //if(firstSet.toString().contains("id")) {
	 * //System.out.println("This is firstSet:"+firstSet);
	 * //System.out.println("This is SecondSet:"+et); //}
	 * if(firstSet.containsKey(et)) {s=0 ;break;} if(s==1) {continue;} } //
	 * for(List<EdgeOperator> et:evaluatedTriples) //
	 * System.out.println("Second problem:"+et);
	 * 
	 * // System.out.println("First problem:"+firstSet.keySet()); //
	 * System.out.println("Second problem:"+joinedTriples.keySet());
	 * 
	 * 
	 * 
	 * HashJoin.ProcessingTask(joinedTriples,firstSet);
	 * 
	 * 
	 * }
	 */

	// }

//}
	public void enableBindJoins(HashSet<List<EdgeOperator>> JoinGroupLists, List<List<EdgeOperator>> joinGroupsList2,
			List<EdgeOperator> JoinGroupsListLeft, List<EdgeOperator> JoinGroupsListRight) {
		
		
		 List<EdgeOperator> JoinGroupsListExclusiveTemp1 = new ArrayList<>(); 
		  for(EdgeOperator obt1:operators_BushyTreeOrder1) 
			  for(EdgeOperator jg2:JoinGroupsListRight)
		  if(jg2.getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp1.contains(jg2) ) 
			  JoinGroupsListExclusiveTemp1.add(jg2);
		  
		  //operators_BushyTreeRight.clear(); 
		  //operators_BushyTreeLeft.clear(); //
		  JoinGroupsListRight.clear();
		  JoinGroupsListRight.addAll(JoinGroupsListExclusiveTemp1);
		  
		  
		  JoinGroupsListExclusiveTemp1 =new ArrayList<>(); 
		  for(EdgeOperator obt1:operators_BushyTreeOrder1) 
			  for(EdgeOperator jg2:JoinGroupsListLeft)
		  if(jg2.getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp1.contains(jg2) ) 
			  JoinGroupsListExclusiveTemp1.add(jg2);
		  JoinGroupsListLeft.clear();
		  JoinGroupsListLeft.addAll(JoinGroupsListExclusiveTemp1);
		 
		  
		  LinkedHashMap <Edge,Integer> Order= new LinkedHashMap <>();
		 int p=0;
		  for(EdgeOperator obt:operators_BushyTreeOrder1) {
			  Order.put(obt.getEdge(), p);
			  p++;
		  }
		List<List<EdgeOperator>>  JoinGroupsListExclusiveTemp2 =new ArrayList<>();
		 JoinGroupsListExclusiveTemp1 =new ArrayList<>();
		 	  for(List<EdgeOperator> jg2:joinGroupsList2)
			  {		
		 		 for(Entry<Edge, Integer> obt1:Order.entrySet()) 
		 			
				  	  for(EdgeOperator jg21:jg2)
		  if(jg21.getEdge().equals(obt1.getKey())&&!JoinGroupsListExclusiveTemp1.contains(jg21) ) 
			  if(obt1.getValue()==0) {
				  operators_BushyTreeOrder.remove(jg21);
				  operators_BushyTreeOrder.add(new HashJoin(jg21.getEdge()));
			
				  JoinGroupsListExclusiveTemp1.add(new HashJoin(jg21.getEdge()));
			  }  else
				  JoinGroupsListExclusiveTemp1.add(jg21);
				  	
				  JoinGroupsListExclusiveTemp2.add(JoinGroupsListExclusiveTemp1);
				  JoinGroupsListExclusiveTemp1= new ArrayList<>();
			  }
			  
		 joinGroupsList2.clear();
		 joinGroupsList2.addAll(JoinGroupsListExclusiveTemp2);
		
			  JoinGroupsListExclusiveTemp2 = new ArrayList<>();
			  for(EdgeOperator obt1:operators_BushyTreeOrder1) 
				  for(List<EdgeOperator> jg2:joinGroupsList2)
					  if(jg2.size()>0)
			  if(jg2.get(0).getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp2.contains(jg2) ) 
				  JoinGroupsListExclusiveTemp2.add(jg2);
			  
			  
			  
			  joinGroupsList2.clear();
			  joinGroupsList2.addAll(JoinGroupsListExclusiveTemp2);
			  for(EdgeOperator obt1:operators_BushyTreeOrder1) 
		  System.out.println("This is the new group list of StartBindingSetBJ:"+obt1);
		// for(List<EdgeOperator> jgl:JoinGroupLists)
		// System.out.println("These are JoinGroupLists jgl:"+jgl);
	/*	if(JoinGroupsListExclusive.size()==1)
		{
			System.out.println("THis is Tempf2:"+JoinGroupsListExclusive.size());			

			List<EdgeOperator>	Tempf = new ArrayList<>(); 

			for(List<EdgeOperator> dsb:JoinGroupsListExclusive)
		//	if(dsb.size()==1)
				for(EdgeOperator dsb1:dsb)
				Tempf.add(new HashJoin(dsb1.getEdge()));
System.out.println("THis is Tempf:"+Tempf);			
if(!Tempf.isEmpty())
{
	for(List<EdgeOperator> jge:JoinGroupsListExclusive) {
		JoinGroupLists.remove(jge);
		for(EdgeOperator jge1:jge)
			operators_BushyTreeOrder.remove(jge1);
		}
	
	joinGroupsList2.clear();
	joinGroupsList2.add(Tempf);
//	for(EdgeOperator tf:Tempf)
		JoinGroupLists.add(Tempf);
for(EdgeOperator t:Tempf)
	operators_BushyTreeOrder.add(t);

	JoinGroupsListExclusive.clear();
JoinGroupsListExclusive.add(Tempf);
//Tempf.clear();
System.out.println("THis is Tempf2:"+JoinGroupsListExclusive);			

}
		}*/
		 for(List<EdgeOperator> jgl:joinGroupsList2)
		 System.out.println("This is joinGroupList loop:"+jgl);

		// Set<Set<EdgeOperator>> iii = new HashSet<>();
		// for (List<EdgeOperator> jg2 : joinGroupsList2) {
		// iii.add(jg2.parallelStream().collect(Collectors.toSet()));
		// }

//		joinGroupsList2.clear();

		// for (Set<EdgeOperator> ii : iii)
		// joinGroupsList2.add(ii.parallelStream().collect(Collectors.toList()));

		Set<EdgeOperator> BindEdges = new HashSet<>();
		HashMap<Vertex, List<EdgeOperator>> BindVertex = new HashMap<>();
		HashMap<Vertex, Integer> bindVertexTime = new HashMap<>();
		Set<EdgeOperator> HashEdges = new HashSet<>();
		Set<Vertex> HashVertex = new HashSet<>();
		HashMap<EdgeOperator, EdgeOperator> BindReplacement = new HashMap<>();
		Set<Vertex> ProcessedVertex = new HashSet<>();
///Get bind vertex
		for (List<EdgeOperator> e : JoinGroupLists) {
			for (EdgeOperator e1 : e) {
				if (e1.toString().contains("Bind"))
					BindVertex.put(e1.getStartVertex(), e);

			}
		}

		for (List<EdgeOperator> jgl : JoinGroupLists) {
			System.out.println("This is jgl213213123:" + jgl);
		}

		List<EdgeOperator> t1 = new ArrayList<>();
		List<EdgeOperator> t2 = new ArrayList<>();
		List<EdgeOperator> t3 = new ArrayList<>();

//				for(Entry<EdgeOperator, List<Binding>> t:finalResult.entrySet())
//					System.out.println("This is the ttttttttttt00000:"+t);

		/*
		 * int l=0; for (List<EdgeOperator> e : joinGroupsList2) { for (EdgeOperator e1
		 * : e) {
		 * 
		 * for(Entry<Vertex, Integer> bvt:bindVertexTime.entrySet()) {
		 * if(bvt.getValue()==0) { if (e1.toString().contains("Bind") &&
		 * (e1.getEdge().getV1().equals(bvt.getKey()) ||
		 * e1.getEdge().getV2().equals(bvt.getKey()))) { t1.addAll(e); t2.addAll(e);
		 * t2.add(new HashJoin(e1.getEdge())); System.out.println("THis is e1:"+e1);
		 * System.out.println("THis is t2 before:"+t2); t2.remove(e1); // t2.remove(e1);
		 * System.out.println("THis is t2 after:"+t2);
		 * 
		 * // l=1; // break; // } // if(l==1) // { // l=0; // break; // }
		 * 
		 * } } } } }
		 */
		/*
		 * if (t2.size() > 0 || !t2.isEmpty()) {
		 * 
		 * joinGroupsList2.remove(t1); // for (EdgeOperator tt : t1) //
		 * exchangeOfElements.remove(tt); if (!joinGroupsList2.contains(t2)) {
		 * joinGroupsList2.add(t2.parallelStream().collect(Collectors.toList()));
		 * 
		 * // for (EdgeOperator tt : t1) // finalResult.remove(tt);
		 * System.out.println("This is findFirstNonLiteral:" + t2);
		 * //finalResult.put(findFirstNonLiteral(t2.parallelStream().collect(Collectors.
		 * toList())), null); //Map<EdgeOperator, Integer> t = new HashMap<>();
		 * //t.put(findFirstNonLiteral(t2.parallelStream().collect(Collectors.toList()))
		 * , 1);
		 * //exchangeOfElements.put(t2.parallelStream().collect(Collectors.toList()).get
		 * (0), t); } for (EdgeOperator tt : t1) { Iterator<EdgeOperator> xz =
		 * operators_BushyTreeOrder.iterator(); while (xz.hasNext()) if
		 * (tt.getEdge().equals(xz.next().getEdge())) xz.remove(); }
		 * 
		 * for (EdgeOperator tt1 : t2) if (!operators_BushyTreeOrder.contains(tt1))
		 * operators_BushyTreeOrder.add(tt1); }
		 */
		// for(Entry<EdgeOperator, List<Binding>> t:finalResult.entrySet())
		// System.out.println("This is the ttttttttttt11111:"+t);

		// for (List<EdgeOperator> jgl : joinGroupsList2)
		// System.out.println("This is is is jgl:" + jgl);

		for (List<EdgeOperator> jgl2 : joinGroupsList2)
			System.out.println("THis is middle of jgl2:" + jgl2);

//		for (List<EdgeOperator> jgl2 : joinGroupsList2)
//			System.out.println("These.,.,.,.,., before:" + jgl2);

		/*
		 * if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) {
		 * JoinGroupLists.addAll(joinGroupsList2Optional); List<List<EdgeOperator>>
		 * c=new ArrayList<>();
		 * 
		 * c.add(JoinGroupsListLeftOptional);
		 * 
		 * JoinGroupLists.addAll(c); List<List<EdgeOperator>> d=new ArrayList<>();
		 * d.add(JoinGroupsListRightOptional);
		 * 
		 * JoinGroupLists.addAll(d);}
		 */
		
		Vertex x = null;

//		HashMap<Vertex, Integer> HashVertexTimes = new HashMap<>();
		// System.out.println("This is the decision");
		/*
		 * for (Vertex pv : ProcessedVertex) { int j = 0; for (List<EdgeOperator> jgl :
		 * JoinGroupLists) { if (jgl.toString().contains(pv.toString())) { j++;
		 * HashVertexTimes.put(pv, j); }
		 * 
		 * } }
		 */
		t1 = new ArrayList<>();
		t2 = new ArrayList<>();
		for (List<EdgeOperator> jgl2 : joinGroupsList2)
			System.out.println("THis is middle1 of jgl2:" + jgl2);

		/// Get all the vertex which are not start vertex and the respective
		/// edgeoperator
		HashMap<Vertex, List<EdgeOperator>> OnlyHashVertexTemp = new HashMap<>();
		List<Vertex> OnlyHashVertex = new ArrayList<>();

		for (List<EdgeOperator> jgl : JoinGroupLists) {
			for (EdgeOperator jgll : jgl)
				if (jgll.toString().contains("Bind")) {
					Vertex sv = jgll.getStartVertex();
					Vertex gv  = jgll.getEdge().getV1();
					Vertex gv1 = jgll.getEdge().getV2();
				//	System.out.println("This is the prob prob prob:" + sv + "--" + gv);
				//	System.out.println("This is the prob prob prob1111:" + sv + "--" + gv1);

					if (gv.equals(sv))
						OnlyHashVertexTemp.put(jgll.getEdge().getV2(), jgl);
					if (gv1.equals(sv))
						OnlyHashVertexTemp.put(jgll.getEdge().getV1(), jgl);

				}
		}
		/// Get the vertex on other edgeoperator if on only hashvertextemp

		for (Entry<Vertex, List<EdgeOperator>> ohv : OnlyHashVertexTemp.entrySet())

			for (List<EdgeOperator> jgl : JoinGroupLists) {
				if (!ohv.getValue().equals(jgl)) {
					for (EdgeOperator jgll : jgl) {
						if (ohv.getKey().equals(jgll.getEdge().getV1()))
							OnlyHashVertex.add(jgll.getEdge().getV1());
						if (ohv.getKey().equals(jgll.getEdge().getV2()))
							OnlyHashVertex.add(jgll.getEdge().getV2());
					}
				}
			}
		List<Vertex> HashVertexTimes = new ArrayList<>();
		List<Vertex> SingleHashVertexTimes = new ArrayList<>();
		 Multimap<Vertex, Vertex> AllVertex = ArrayListMultimap.create();;

		//All the vertex that are not part of a specific edgeoperator not having respective bind vertex
		 //because these startvertex exists in a single group
		List<Vertex> Never = new ArrayList<>();
		 for (Entry<Vertex, List<EdgeOperator>> bv : BindVertex.entrySet()) {
			for (List<EdgeOperator> jgl : JoinGroupLists)
				if (!bv.getValue().equals(jgl)) {
//Hash Join
							for(EdgeOperator jgll:jgl)
								{
								if(bv.getKey().equals(jgll.getEdge().getV1())|| bv.getKey().equals(jgll.getEdge().getV2()))
								{
									
									AllVertex.removeAll(bv.getKey());
									Never.add(bv.getKey());
								}
								else
								{
									
								if(!Never.contains(bv.getKey())) {
									AllVertex.put(bv.getKey(),jgll.getEdge().getV1());
								
								AllVertex.put(bv.getKey(),jgll.getEdge().getV2());
								}
								}	
						}
				}
		 }
		 
			//If start vertex exists in single group and its respective edge exist in other group
				 
		 for(Entry<Vertex, Vertex> av:AllVertex.entries())
			 	if(!HashVertexTimes.contains(av.getKey()))
			 HashVertexTimes.add(av.getKey());
		//If start vertex exists in single group and its respective edge exist in one instance of that group only
		 for(Vertex hvt:HashVertexTimes)
		 for(List<EdgeOperator> jgl:JoinGroupLists)
			 for(EdgeOperator jgll:jgl)
			 if(jgll.toString().contains("Bind") && jgll.getStartVertex().equals(hvt))
			 {
				 if(!OnlyHashVertex.contains(jgll.getEdge().getV1()))
				 SingleHashVertexTimes.add(jgll.getEdge().getV1());
				 if(!OnlyHashVertex.contains(jgll.getEdge().getV2()))
			 SingleHashVertexTimes.add(jgll.getEdge().getV2());
			 }
		 for (Vertex ohv : OnlyHashVertex)
			System.out.println("This is OnlyHashVertex:" + ohv);

		 for (Vertex ohv : SingleHashVertexTimes)
				System.out.println("This is OnlyHashVertex1111:" + ohv);
		
			 for (Vertex ohv : HashVertexTimes)
					System.out.println("This is OnlyHashVertex222222222:" + ohv);

		 
//		for (Entry<Vertex, Vertex> ohv : AllVertex.entries())
//			System.out.println("This is AllVertex:" + ohv);
		
//		for (Vertex ohv : HashVertexTimes)
//			System.out.println("This is HashVertexTimes:" + ohv);
		
//		for (Vertex ohv : SingleHashVertexTimes)
//			System.out.println("This is SingleHashVertexTimes:" + ohv);
		
		
		/*
		 * for (List<EdgeOperator> jgl : joinGroupsList2) { int i=0; for (EdgeOperator
		 * jgll : jgl) { if(i==0) { t1.addAll(jgl); t2.addAll(jgl); } if
		 * (jgll.toString().contains("Bind") &&
		 * !ProcessedVertex.contains(jgll.getStartVertex())) {
		 * 
		 * 
		 * if (jgll.getStartVertex().equals(jgll.getEdge().getV1()) &&
		 * (OnlyHashVertex.contains(jgll.getEdge().getV2())) ) { t2.add(new
		 * BindJoin(jgll.getEdge().getV2(), jgll.getEdge())); Iterator<EdgeOperator> t5
		 * = t2.iterator(); if (t5.next().equals(jgll)) t2.remove(jgll);
		 * System.out.println("This is t21:"+t2); }
		 * 
		 * if (jgll.getStartVertex().equals(jgll.getEdge().getV2()) &&
		 * (OnlyHashVertex.contains(jgll.getEdge().getV1()))) { t2.add(new
		 * BindJoin(jgll.getEdge().getV1(), jgll.getEdge())); Iterator<EdgeOperator> t5
		 * = t2.iterator(); if (t5.next().equals(jgll)) t2.remove(jgll);
		 * System.out.println("This is t22:"+t2);
		 * 
		 * }
		 * 
		 * } i++;} }
		 * 
		 * System.out.println("This is t21:"+t2);
		 * 
		 * Iterator<List<EdgeOperator>> t5 = joinGroupsList2.iterator(); if
		 * (t5.next().equals(t1)) t5.remove(); // joinGroupsList2.remove(t1);
		 * joinGroupsList2.add(t2);
		 * 
		 * t1 = new ArrayList<>(); t2 = new ArrayList<>(); Iterator<EdgeOperator> jgll =
		 * JoinGroupsListLeft.iterator(); while (jgll.hasNext()) { // for(EdgeOperator
		 * jgll:jgl) EdgeOperator jj = jgll.next(); if (jj.toString().contains("Bind")
		 * && !ProcessedVertex.contains(jj.getStartVertex())) { t1.add(jj); t2.add(jj);
		 * if (jj.getStartVertex().toString().equals(jj.getEdge().getV1().toString())) {
		 * t1.add(jj); t2.add(new BindJoin(jj.getEdge().getV2(), jj.getEdge())); //
		 * t2.remove(jj); Iterator<EdgeOperator> t9 = t2.iterator(); if
		 * (t9.next().equals(jj)) t9.remove(); } if
		 * (jj.getStartVertex().toString().equals(jj.getEdge().getV2().toString())) {
		 * t1.add(jj); t2.add(new BindJoin(jj.getEdge().getV1(), jj.getEdge())); //
		 * t2.remove(jj); Iterator<EdgeOperator> t9 = t2.iterator(); if
		 * (t9.next().equals(jj)) t9.remove(); } }
		 * 
		 * } for(List<EdgeOperator> jgl2:joinGroupsList2)
		 * System.out.println("THis is middle1 of jgl3:"+jgl2);
		 * 
		 * if (t2.size() > 0 || !t2.isEmpty()) { Iterator<EdgeOperator> t9 =
		 * JoinGroupsListLeft.iterator(); for(EdgeOperator tt:t1) if
		 * (t9.next().equals(tt)) t9.remove();
		 * 
		 */
	//	for (List<EdgeOperator> jgl2 : joinGroupsList2)
	//		System.out.println("THis is middle1 of jgl4:" + jgl2);

		/*
		 * for (EdgeOperator r4 : t1) { // JoinGroupsListLeft.remove(r4);
		 * 
		 * operators_BushyTreeOrder.remove(r4); exchangeOfElements.remove(r4);
		 * finalResult.remove(r4);
		 * 
		 * }
		 */
		if (!JoinGroupsListLeft.equals(t2.parallelStream().collect(Collectors.toList())))
			JoinGroupsListLeft.addAll(t2.parallelStream().collect(Collectors.toList()));
		// for (EdgeOperator r32 : t2) {
		// operators_BushyTreeOrder.add(r32);
		// finalResult.put(r32, null);
		// }
		// Map<EdgeOperator, Integer> t = new HashMap<>();
		// t.put(findFirstNonLiteral(t2.parallelStream().collect(Collectors.toList())),
		// 1);
		// if
		// (!JoinGroupsListLeft.equals(t2.parallelStream().collect(Collectors.toList())))/

		// exchangeOfElements.put(t2.parallelStream().collect(Collectors.toList()).get(0)//,
		// t);
		// }

//		for (EdgeOperator jgl : JoinGroupsListLeft)
//			System.out.println("These are the purposes1:" + jgl);

		/*Iterator<EdgeOperator> jglr = JoinGroupsListRight.iterator();
		while (jglr.hasNext()) {
			// for(EdgeOperator jgll:jgl)
			EdgeOperator jj = jglr.next();
			if (jj.toString().contains("Bind") && !ProcessedVertex.contains(jj.getStartVertex())) {
				t1.add(jj);
				t2.add(jj);
				if (jj.getStartVertex().equals(jj.getEdge().getV1())) {
					t2.add(new BindJoin(jj.getEdge().getV2(), jj.getEdge()));
					jglr.remove();
				}
				if (jj.getStartVertex().equals(jj.getEdge().getV2())) {
					t2.add(new BindJoin(jj.getEdge().getV1(), jj.getEdge()));
					jglr.remove();
				}
			}

		}
		JoinGroupsListRight.addAll(t2);
*/
	//	for (List<EdgeOperator> jgl2 : joinGroupsList2)
	//		System.out.println("THis is beginning2 of jgl2:" + jgl2);

		List<EdgeOperator> temp1 = new ArrayList<>();
		List<EdgeOperator> temp2 = new ArrayList<>();
		List<EdgeOperator> temp3 = new ArrayList<>();

		List<List<EdgeOperator>> jglset11 = new ArrayList<>();
		jglset11.addAll(joinGroupsList2.parallelStream().collect(Collectors.toSet()));
		if(JoinGroupsListLeft.size()>0)
			
		jglset11.add(JoinGroupsListLeft);
		if(JoinGroupsListRight.size()>0)
		jglset11.add(JoinGroupsListRight);
		
		for (List<EdgeOperator> jgl : jglset11)
			System.out.println("These are the purposes1:" + jgl);
	//	Set<List<EdgeOperator>> jglset = new HashSet<>();
	//	jglset.addAll(joinGroupsList2);

					for (List<EdgeOperator> jgl : jglset11) {
						temp1 = new ArrayList<>();
					temp2 = new ArrayList<>();
						temp3 = new ArrayList<>();
					
					for (EdgeOperator jgll1 : jgl)
						if (jgll1.getStartVertex() != null)
						{
									System.out.println("This is OnlyHashVertex88888:" + HashVertexTimes+"--"+OnlyHashVertex+"--"+jgll1);

							if (HashVertexTimes.contains(jgll1.getStartVertex())
									&& (OnlyHashVertex.contains(jgll1.getEdge().getV1()))) {
						//	if(!temp1.equals(jgl))	
						//	{	
							
							
								temp1.addAll(jgl);
						
						    temp2.add(new BindJoin(jgll1.getEdge().getV1(),jgll1.getEdge()));			
							temp2.addAll(jgl);
							System.out.println("This is OnlyHashVertex99999:" + HashVertexTimes+"--"+OnlyHashVertex+"--"+jgll1);

							Iterator<EdgeOperator> at = temp2.iterator();
									while(at.hasNext())
										if(at.next().equals(jgll1))
											at.remove();
								//	t2.remove(jgll);
									temp3.add(jgll1);
									
								//	t2.remove(jgll);
									for(EdgeOperator ttt:temp3)
										temp2.remove(ttt);

		//							System.out.println("This is temp1.2:"+temp1);
		//							System.out.println("This is temp2.2:"+temp2);
								

							}
					if (HashVertexTimes.contains(jgll1.getStartVertex())
							&& (OnlyHashVertex.contains(jgll1.getEdge().getV2()))) {
						if(!temp1.equals(jgl))	
							temp1.addAll(jgl);
					
						temp2.add(new BindJoin(jgll1.getEdge().getV2(),jgll1.getEdge()));		
						temp2.addAll(jgl);
								Iterator<EdgeOperator> at1 = temp2.iterator();
								while(at1.hasNext())
									if(at1.next().equals(jgll1))
										at1.remove();
							//	t2.remove(jgll);
								temp3.add(jgll1);
								
							//	t2.remove(jgll);
								for(EdgeOperator ttt:temp3)
									temp2.remove(ttt);
				
					
				//				System.out.println("This is temp1.1:"+temp1);
			//					System.out.println("This is temp2.1:"+temp2);
							
					}
					

						}
					System.out.println("This is temp1:"+temp1);
					System.out.println("This is temp2:"+temp2);
			
					temp1=temp1.parallelStream().distinct().collect(Collectors.toList());
					temp2=temp2.parallelStream().distinct().collect(Collectors.toList());
			
					int typee=0;
					Iterator<List<EdgeOperator>> at = joinGroupsList2.iterator();
					while(at.hasNext())
					{
						List<EdgeOperator> at1 = at.next().parallelStream().distinct().collect(Collectors.toList());
						if(at1.equals(temp1)) {
						at.remove();
						typee=1;
						}
					}
					if(JoinGroupsListLeft.size()>0)
					{Iterator<EdgeOperator> at1 = JoinGroupsListLeft.iterator();
					while(at1.hasNext())
					{ EdgeOperator aaa = at1.next();
						for(EdgeOperator t:temp1)
						if(aaa.equals(t)) {
						at1.remove();
						typee=2;
						}
						
					}}
					if(JoinGroupsListRight.size()>0)
					{
					Iterator<EdgeOperator> at11 = JoinGroupsListRight.iterator();
					while(at11.hasNext())
					{
						EdgeOperator aaa = at11.next();
						for(EdgeOperator t:temp1)
						if(aaa.equals(t)) {
						at11.remove();
						typee=3;
						}
					}
					}	
					
					//		if (!joinGroupsList2.contains(t2))
					if(typee==1)
					joinGroupsList2.add(temp2);
					if(typee==2)
						JoinGroupsListLeft.addAll(temp2);
					if(typee==3)
						JoinGroupsListRight.addAll(temp2);
						for (EdgeOperator tt : temp1) {
							Iterator<EdgeOperator> xz = operators_BushyTreeOrder.iterator();
							while (xz.hasNext())
								if (tt.getEdge().equals(xz.next().getEdge()))
									xz.remove();
						}

						for (EdgeOperator tt1 : temp2)
							if (!operators_BushyTreeOrder.contains(tt1))
								operators_BushyTreeOrder.add(tt1);

					}
					// joinGroupsList2.remove(temp1);
			
	//	for (List<EdgeOperator> jgl2 : joinGroupsList2)
	//		System.out.println("THis is beginning 1 of jgl2:" + jgl2);

		
		for (Entry<Vertex, List<EdgeOperator>> pv : BindVertex.entrySet()) {
			int j1 = 0;
			for (List<EdgeOperator> jgl1 : JoinGroupLists) {
				for (EdgeOperator jgll1 : jgl1)
					if (!jgl1.equals(pv.getValue()))
						if (

						(jgll1.getEdge().getV1().equals(pv.getKey()) || jgll1.getEdge().getV2().equals(pv.getKey()))) {
							bindVertexTime.put(pv.getKey(), j1++);
						}

			}
		}

		t1 = new ArrayList<>();
		t2 = new ArrayList<>();
		t3 = new ArrayList<>();

		List<List<EdgeOperator>> jglset111 = new ArrayList<>();
		jglset111.addAll(joinGroupsList2.parallelStream().collect(Collectors.toSet()));
		if(JoinGroupsListLeft.size()>0)
			
			jglset111.add(JoinGroupsListLeft);
			if(JoinGroupsListRight.size()>0)
			jglset111.add(JoinGroupsListRight);
			
		for (List<EdgeOperator> jgl : joinGroupsList2)
			System.out.println("These are the purposes1:" + jgl.parallelStream().distinct().collect(Collectors.toList()));
	//	Set<List<EdgeOperator>> jglset = new HashSet<>();
	//	jglset.addAll(joinGroupsList2);

					for (List<EdgeOperator> jgl : jglset111) {
						temp1 = new ArrayList<>();
					temp2 = new ArrayList<>();
						temp3 = new ArrayList<>();
					
					for (EdgeOperator jgll1 : jgl.parallelStream().distinct().collect(Collectors.toList()))
						if (jgll1.getStartVertex() != null)
						{	if (HashVertexTimes.contains(jgll1.getStartVertex())
									&& (SingleHashVertexTimes.contains(jgll1.getEdge().getV1())
								||	SingleHashVertexTimes.contains(jgll1.getEdge().getV2())		)) {
						//	if(!temp1.equals(jgl))	
						//	{	
								for(EdgeOperator j:jgl)
								{
									if(!temp1.contains(j)) {
									temp1.add(j);
								temp2.add(j);
									}
								}
						    temp2.add(new HashJoin(jgll1.getEdge()));			
									Iterator<EdgeOperator> at = temp2.iterator();
									while(at.hasNext())
										if(at.next().equals(jgll1))
											at.remove();
								//	t2.remove(jgll);
									temp3.add(jgll1);
									
								//	t2.remove(jgll);
									for(EdgeOperator ttt:temp3)
										temp2.remove(ttt);

		//							System.out.println("This is temp1.2:"+temp1);
		//							System.out.println("This is temp2.2:"+temp2);
								

							}


						}
					
					temp1=temp1.parallelStream().distinct().collect(Collectors.toList());
					temp2=temp2.parallelStream().distinct().collect(Collectors.toList());
						
					System.out.println("This is temp3:"+temp1);
					System.out.println("This is temp4:"+temp2);

					
					for (List<EdgeOperator> jg1l : joinGroupsList2)
						System.out.println("These are the purposes1.5:" + jg1l);

					for (EdgeOperator jg1l : JoinGroupsListLeft)
						System.out.println("These are the purposes2.5:" + jg1l);

					
					int typee=0;
					Iterator<List<EdgeOperator>> at = joinGroupsList2.iterator();
					while(at.hasNext())
					{
						List<EdgeOperator> at1 = at.next().parallelStream().distinct().collect(Collectors.toList());
						if(at1.equals(temp1)) {
						at.remove();
						typee=1;
						}
					}
					System.out.println("These are the purposes2.6:" );

					if(JoinGroupsListLeft.size()>0) {
					Iterator<EdgeOperator> at1 = JoinGroupsListLeft.iterator();
					while(at1.hasNext())
					{
						
						EdgeOperator aaa = at1.next();
						
						for(EdgeOperator t:temp1)
						if(aaa.equals(t)) {
						at1.remove();
						typee=2;
						}
						
					}
					}

					System.out.println("These are the purposes2.7:" );

					if(JoinGroupsListRight.size()>0) {
						
					Iterator<EdgeOperator> at11 = JoinGroupsListRight.iterator();
					while(at11.hasNext())
					{
						EdgeOperator aaa = at11.next();
						
						for(EdgeOperator t:temp1)
						if(aaa.equals(t)) {
						at11.remove();
						typee=3;
						}
					}
					}
					
					//		if (!joinGroupsList2.contains(t2))
					if(typee==1)
					joinGroupsList2.add(temp2);
					if(typee==2)
						JoinGroupsListLeft.addAll(temp2);
					if(typee==3)
						JoinGroupsListRight.addAll(temp2);
						
					System.out.println("These are the purposes2.8:" );

					//		if (!joinGroupsList2.contains(t2))
						joinGroupsList2.add(temp2);
						for (EdgeOperator tt : temp1) {
							Iterator<EdgeOperator> xz = operators_BushyTreeOrder.iterator();
							while (xz.hasNext())
								if (tt.getEdge().equals(xz.next().getEdge()))
									xz.remove();
						}

						
						System.out.println("These are the purposes2.9:" );

						
						for (EdgeOperator tt1 : temp2)
							if (!operators_BushyTreeOrder.contains(tt1))
								operators_BushyTreeOrder.add(tt1);

					}
		
					
					for (List<EdgeOperator> jgl : joinGroupsList2)
						System.out.println("These are the purposes2:" + jgl);
				
					
		//		for (List<EdgeOperator> jgl2 : joinGroupsList2)
	//		System.out.println("THis is beginning of jgl2:" + jgl2);

				BindEdges.clear();
				HashEdges.clear();
				
				for (List<EdgeOperator> e : JoinGroupLists) {
					for (EdgeOperator e1 : e) {
						if (e1.toString().contains("Hash")) {
							if (!e1.getEdge().getTriple().getSubject().isLiteral()
									&& !e1.getEdge().getTriple().getSubject().isURI())
								HashVertex.add(e1.getEdge().getV1());
							if (!e1.getEdge().getTriple().getObject().isLiteral()
									&& !e1.getEdge().getTriple().getObject().isURI())
								HashVertex.add(e1.getEdge().getV2());

						}
					}
				}
				int bindno = 0;
				// System.out.println("THis is HashVertex:" + HashVertex);
				for (List<EdgeOperator> e : JoinGroupLists) {
					for (EdgeOperator e1 : e) {
						if (e1.toString().contains("Bind")) {
							BindEdges.add(e1);
						}
						if (e1.toString().contains("Hash")) {
							HashEdges.add(e1);

						}
					}
				}
			//	for(EdgeOperator he:HashEdges)
		//			System.out.println("This is hashJoin11:"+he);
			//		for(EdgeOperator he:BindEdges)
			//			System.out.println("This is BindJoin11:"+he);
				for (EdgeOperator be : BindEdges) {
					int isApp = 0;
					for (Vertex hv : HashVertex) {
						if (be.getStartVertex().toString().equals(hv.toString())) {
							isApp = 1;
						}

						// (?geneResults: 2147483647 false), (?expValue: 2147483647 false)
						// [Hash join: (http://tcga.deri.ie/TCGA-37-3789: 1 true) - (?geneResults:
						// 2147483647 false),
						// Hash join: (?uri: 2147483647 false) - (http://tcga.deri.ie/TCGA-37-3789: 1
						// true)]
						// Bind join: (?geneResults: 2147483647 false)--(?geneResults: 2147483647 false)
						// - (?expValue: 2147483647 false)

						// System.out.println("This is here in be:"+isApp+"--"+be+"--"+hv);
						if (isApp == 0) {
							// int count=0;
							// for(List<EdgeOperator> jgl:JoinGroupLists)
							// if(jgl.toString().contains(hv.toString()))
							// count++;
							// System.out.println("THis is isApp:" + be + "--" + hv+"--"+count);
							if (be.getEdge().getV1().toString().equals(hv.toString())) {

								BindReplacement.put(be, new BindJoin(be.getEdge().getV1(), be.getEdge()));
								break;
							}
							if (be.getEdge().getV2().toString().equals(hv.toString())) {
								BindReplacement.put(be, new BindJoin(be.getEdge().getV2(), be.getEdge()));
								break;
							}
						}
					}
				}
				if (BindReplacement.size() > 0) {
//				for (Entry<EdgeOperator, EdgeOperator> br : BindReplacement.entrySet())
//					System.out.println("These are BindReplacement:"+br);
//				System.out.println("These are BindReplacement processing:"+BindReplacement.size());

					for (Entry<EdgeOperator, EdgeOperator> br : BindReplacement.entrySet()) {
						operators_BushyTreeOrder.remove(br.getKey());
						operators_BushyTreeOrder.add(br.getValue());

						if (JoinGroupsListLeft.contains(br.getKey())) {
							// System.out.println("This is within JoinGroupsListLeft:" + br.getKey());
							JoinGroupsListLeft.remove(br.getKey());
							JoinGroupsListLeft.add(br.getValue());

							// for (Entry<EdgeOperator, List<Binding>> fr : finalResultLeft.entrySet())
							// System.out.println("THis is finalResultLeft replacement:" + fr);

						}

						if (JoinGroupsListRight.toString().contains(br.getKey().toString())) {
							JoinGroupsListRight.remove(br.getKey());
							JoinGroupsListRight.add(br.getValue());
						}
						// if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()))
						// if (JoinGroupsListRightOptional.toString().contains(br.getKey().toString()))
						// {
						// JoinGroupsListRightOptional.remove(br.getKey());
						// JoinGroupsListRightOptional.add(br.getValue());
						// }
						List<List<EdgeOperator>> Temp1 = new ArrayList<>();
						List<List<EdgeOperator>> Temp2 = new ArrayList<>();
						List<EdgeOperator> Temp3 = new ArrayList<>();
						// System.out.println("This is joinGroupList with br.getKey():"+br.getKey());
						// for(List<EdgeOperator> jgl:joinGroupsList2)
						// System.out.println("This is joinGroupList loop:"+jgl);

						for (List<EdgeOperator> jgl : joinGroupsList2)
							if (jgl.toString().contains(br.getKey().toString())) {
								// System.out.println("This is joinGroupList with br.getKey():"+br.getKey());
								Temp3.addAll(jgl);
								Temp3.remove(br.getKey());
								Temp3.add(br.getValue());

								Temp1.add(Temp3);
								Temp2.add(jgl);
							}
						// System.out.println("THis is Temp2 joinGroupList:"+Temp2);
						// System.out.println("THis is Temp1 joinGroupList:"+Temp1);
						for (List<EdgeOperator> t21 : Temp2)
							if (joinGroupsList2.contains(t21))
								joinGroupsList2.remove(t21);
						joinGroupsList2.addAll(Temp1);

						// for (List<EdgeOperator> jgl : joinGroupsList2)
						// System.out.println("This is is is jgl 111111111111111111111111111:" + jgl);
						/*
						 * if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) { for(List<EdgeOperator>
						 * jgl:joinGroupsList2Optional) if
						 * (jgl.toString().contains(br.getKey().toString())) {
						 * System.out.println("This is joinGroupList with br.getKey():"+br.getKey());
						 * Temp6.addAll(jgl); Temp6.remove(br.getKey()); Temp6.add(br.getValue());
						 * 
						 * Temp4.add(Temp6); Temp5.add(jgl); } //
						 * System.out.println("THis is Temp2 joinGroupList:"+Temp2); //
						 * System.out.println("THis is Temp1 joinGroupList:"+Temp1);
						 * for(List<EdgeOperator> t2:Temp5) if(joinGroupsList2Optional.contains(t2))
						 * joinGroupsList2Optional.remove(t2); joinGroupsList2Optional.addAll(Temp4); }
						 */

						// JoinGroupLists.clear();
						// JoinGroupLists.addAll(joinGroupsList2);
						// JoinGroupLists.add(JoinGroupsListLeft);
						// JoinGroupLists.add(JoinGroupsListRight);

					}
				}
				
				
			//	for(EdgeOperator he:HashEdges)
			//	System.out.println("This is hashJoin00:"+he);
			//	for(EdgeOperator he:BindEdges)
			//		System.out.println("This is BindJoin00:"+he);
				
				List<EdgeOperator> a = new ArrayList<>();
				JoinGroupLists.clear();
				JoinGroupLists.addAll(joinGroupsList2);
				for (EdgeOperator jgl : JoinGroupsListLeft) {
					a.add(jgl);
					JoinGroupLists.add(a);
				}
				// System.out.println("These.,.,.,.,.,:"+JoinGroupsListLeft);
				List<List<EdgeOperator>> b = new ArrayList<>();
				for (EdgeOperator jgl : JoinGroupsListRight) {
					a.add(jgl);
					JoinGroupLists.add(a);
				}

				JoinGroupLists.addAll(b);

				BindEdges.clear();
				HashEdges.clear();

				// for(List<EdgeOperator> jgl:JoinGroupLists)
				// System.out.println("These are JoinGroupLists jgl111:"+jgl);
				for (List<EdgeOperator> e : JoinGroupLists) {
					for (EdgeOperator e1 : e) {
						if (e1.toString().contains("Bind"))
							BindEdges.add(e1);
						if (e1.toString().contains("Hash")) {
							HashEdges.add(e1);

						}
					}
				}
				
				
			//	for(EdgeOperator he:HashEdges)
			//	System.out.println("This is hashJoin:"+he);
			//	for(EdgeOperator he:BindEdges)
			//		System.out.println("This is BindJoin:"+he);
		
				LinkedListMultimap<Edge, Vertex> temp = LinkedListMultimap.create();


				for (EdgeOperator be : BindEdges)
					System.out.println("This is bindEdges be:"+be);
					for (EdgeOperator he : HashEdges)
						System.out.println("This is hashEdges he:"+he);

						for (EdgeOperator be : BindEdges)
					for (EdgeOperator he : HashEdges)
					{
						System.out.println("These are inside StartBindingFinal20000000000.1:" + be.getStartVertex().getNode().toString());
						System.out.println("These are inside StartBindingFinal20000000000.2:" + he.getEdge().getV1().getNode().toString()+"--"+ he.getEdge().getV2().getNode().toString());
				
						
						if (be.getStartVertex().getNode().toString().equals(he.getEdge().getV1().getNode().toString())
								|| be.getStartVertex().getNode().toString().equals(he.getEdge().getV2().getNode().toString())) {
							// if(!he.getEdge().getV1().toString().contains("http") &&
							// !he.getEdge().getV2().toString().contains("http"))
							{
								temp.put(he.getEdge(), be.getStartVertex());
								StartBindingFinal.put(temp, new ArrayList<>());
								// Processed.add(he);
								ProcessedVertex.add(be.getStartVertex());
						//		ProcessedVertex.add(be.getEdge().getV1());
						//		ProcessedVertex.add(be.getEdge().getV2());
								StartBindingFinalKey.putAll(temp);
	//							System.out.println("These are inside StartBindingFinal00000000000.1:" + StartBindingFinal);
				
								//for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
								//	System.out.println("These are the StartBindingFinal values0099111:" +sbf.getKey());
					
							}
					}		
						}
//						for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
					//		System.out.println("These are StartBindingFinal00000000000.1:" + sbf);

				for (EdgeOperator be : BindEdges)
					for (EdgeOperator he : BindEdges)
					{	
					//	System.out.println("These are inside StartBindingFinal10000000000.1:" + be.getStartVertex().getNode().toString());
					//	System.out.println("These are inside StartBindingFinal10000000000.2:" + he.getEdge().getV1().getNode().toString()+"--"+ he.getEdge().getV2().getNode().toString());
							
						if (be.getStartVertex().getNode().toString().equals(he.getEdge().getV1().getNode().toString())
								|| be.getStartVertex().getNode().toString().equals(he.getEdge().getV2().getNode().toString())) {
						//	if(!StartBindingFinalKey.containsKey(he.getEdge()))
						//	{
								temp.put(he.getEdge(), be.getStartVertex());
						//		if(StartBindingFinal.con)
								StartBindingFinal.put(temp, new ArrayList<>());
								// Processed.add(he);
								ProcessedVertex.add(be.getStartVertex());
						//		ProcessedVertex.add(be.getEdge().getV1());
						//		ProcessedVertex.add(be.getEdge().getV2());
								StartBindingFinalKey.putAll(temp);
							//	for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
								//System.out.println("These are the StartBindingFinal values:" +sbf.getKey());
								
						}
					}		
						//}
				
				
				//for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
				//	System.out.println("These are StartBindingFinal00000000000:" + sbf);
			
				for(Vertex pv:ProcessedVertex)
					System.out.println("This is the first processed vertex:"+pv);
				for (EdgeOperator be : BindEdges)
					{if(ProcessedVertex.toString().contains(be.getEdge().getV1().getNode().toString()))
						temp.put(be.getEdge(), be.getEdge().getV1());
					else if(ProcessedVertex.toString().contains(be.getEdge().getV2().getNode().toString()))
						temp.put(be.getEdge(), be.getEdge().getV2());
					StartBindingFinalKey.putAll(temp);
					
					StartBindingFinal.put(temp, new ArrayList<>());
					}
				/*			for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
				System.out.println("These are StartBindingFinal11111:" + sbf);
				for (EdgeOperator be : BindEdges)
					for (EdgeOperator he : BindEdges)
						
						if (be.getStartVertex().equals(he.getEdge().getV1()) 
								&& (!be.equals(he))) {
							if (be.getEdge().getV1().equals(he.getEdge().getV1())
									|| be.getEdge().getV1().equals(he.getEdge().getV2()))
								if(ProcessedVertex.contains(be.getEdge().getV1()))
									if(!StartBindingFinalKey.containsKey(he.getEdge()))
								temp.put(he.getEdge(), be.getEdge().getV1());
							else
								if(!StartBindingFinalKey.containsKey(he.getEdge()))
								temp.put(he.getEdge(), be.getStartVertex());
							StartBindingFinalKey.putAll(temp);
							
							StartBindingFinal.put(temp, new ArrayList<>());
							ProcessedVertex.add(be.getStartVertex());
							
						}
*/	
//				for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
//	System.out.println("These are StartBindingFinal11111111111:" + sbf);

				
				for (EdgeOperator be : BindEdges)

					for (EdgeOperator he : BindEdges) {// System.out.println("These are the determined
														// vertex:"+be.getStartVertex()+"--"+he.getEdge().getV2()+"--"+ProcessedVertex);
						if (be.getStartVertex().equals(he.getEdge().getV1())
								&& !be.equals(he)) {
							
							if (be.getEdge().getV1().getNode().toString().equals(he.getEdge().getV1().getNode().toString())
									|| be.getEdge().getV1().getNode().toString().equals(he.getEdge().getV2().getNode().toString())) {
								if((ProcessedVertex.size()>0 || !ProcessedVertex.isEmpty()))
								{	
									if(ProcessedVertex.contains(be.getEdge().getV1()))
									if(!StartBindingFinalKey.containsKey(he.getEdge()))
								temp.put(he.getEdge(), be.getEdge().getV1());
								
								else
									if(!StartBindingFinalKey.containsKey(he.getEdge()))
									temp.put(he.getEdge(), be.getStartVertex()); }
								else	if(!StartBindingFinalKey.containsKey(he.getEdge()))
									temp.put(he.getEdge(), be.getEdge().getV1());
								}
							else
								if(!StartBindingFinalKey.containsKey(he.getEdge()))
								temp.put(he.getEdge(), be.getStartVertex());

							// temp.put(be.getEdge().getV2(), he.getEdge());
							// System.out.println("These are the determined vertex1111:" + temp);
							StartBindingFinalKey.putAll(temp);
							StartBindingFinal.put(temp, new ArrayList<>());
							ProcessedVertex.add(be.getStartVertex());

						}
					}
			//	for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
			//		System.out.println("These are StartBindingFinal2222222222:" + sbf);
				
				for (EdgeOperator be : BindEdges)

					for (EdgeOperator he : BindEdges) {// System.out.println("These are the determined
														// vertex:"+be.getStartVertex()+"--"+he.getEdge().getV2()+"--"+ProcessedVertex);
						if (be.getStartVertex().getNode().toString().equals(he.getEdge().getV2().getNode().toString())
								&& !be.equals(he)) {
							
							if (be.getEdge().getV2().getNode().toString().equals(he.getEdge().getV1().getNode().toString())
									|| be.getEdge().getV2().getNode().toString().equals(he.getEdge().getV2().getNode().toString())) {
								if((ProcessedVertex.size()>0 || !ProcessedVertex.isEmpty()))
								{	if(ProcessedVertex.contains(be.getEdge().getV2()))
									if(!StartBindingFinalKey.containsKey(he.getEdge()))
									temp.put(he.getEdge(), be.getEdge().getV2());
								else 
									if(!StartBindingFinalKey.containsKey(he.getEdge()))
									temp.put(he.getEdge(), be.getStartVertex()); }
								else
									if(!StartBindingFinalKey.containsKey(he.getEdge()))
									temp.put(he.getEdge(), be.getEdge().getV2());
								}
							else
								if(!StartBindingFinalKey.containsKey(he.getEdge()))
								temp.put(he.getEdge(), be.getStartVertex());

							// temp.put(be.getEdge().getV2(), he.getEdge());
							// System.out.println("These are the determined vertex1111:" + temp);
							StartBindingFinalKey.putAll(temp);
							StartBindingFinal.put(temp, new ArrayList<>());
							ProcessedVertex.add(be.getStartVertex());

						}
					}
				
			/*	for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entries()) {
					for(Entry<Edge, Vertex> sbf1:sbf.getKey().entries()) {
						if(sbf1.getValue().getNode().isURI()) {
							if(sbf1.getKey().getV1().getNode().isURI())
								sbf.getKey() .replaceValues(sbf1.getKey(), sbf1.getKey().getV2());
							else	if(sbf1.getKey().getV2().getNode().isURI())
								sbf.getKey().replaceValues(sbf1.getKey(), sbf1.getKey().getV1());
						}
					}
				}*/
			
				//for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
			//	for(Entry<Edge,Vertex> sbf1:sbf.getKey().entrySet())
			//		System.out.println("These are StartBindingFinal333333:" + sbf1);
				
/*				for (EdgeOperator he : BindEdges) {// System.out.println("These are the determined
					// vertex:"+be.getStartVertex()+"--"+he.getEdge().getV2()+"--"+ProcessedVertex);
						if (ProcessedVertex.contains(he.getEdge().getV1())) 
							temp.put(he.getEdge(), he.getEdge().getV1());
						if (ProcessedVertex.contains(he.getEdge().getV2())) 
							for(Entry<Edge,Vertex> sbfk:StartBindingFinalKey.entrySet())
									if(sbfk.getValue().equals(he.getEdge().getV2()))
StartBindingFinalKey.putAll(temp);												
StartBindingFinal.put(temp, new ArrayList<>());

}

				temp = new HashMap<>();
				for (Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> be : StartBindingFinal.entrySet())
					for (Entry<Edge, Vertex> be1 : be.getKey().entrySet())
						for (EdgeOperator he : BindEdges)
							if (he.getStartVertex().toString().equals(be1.getValue().toString())) {

								temp.put(he.getEdge(), he.getStartVertex());

							}
				StartBindingFinal.put(temp, new ArrayList<>());
				StartBindingFinalKey.putAll(temp);
*/				
				HashMap<Edge, Vertex> Temp3 = new HashMap<>();
				for (Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> be : StartBindingFinal.entrySet())
					for (Entry<Edge, Vertex> be1 : be.getKey().entries())
						Temp3.put(be1.getKey(), be1.getValue());

				StartBindingFinal.clear();
				for (Entry<Edge, Vertex> t31 : Temp3.entrySet()) {
					LinkedListMultimap<Edge, Vertex> Temp2 = LinkedListMultimap.create();
					Temp2.put(t31.getKey(), t31.getValue());
					StartBindingFinal.put(Temp2, null);
				}
				for (Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbt : StartBindingFinal.entrySet())
					System.out.println("This is obt value StartBindingFinal:" + sbt);

//				for (Entry<ConcurrentHashMap<Vertex, Edge>, ArrayList<Binding>> obt : StartBindingFinal.entrySet())
//					System.out.println("This is StartBindingFinal value:" + obt);

//				for (List<EdgeOperator> obt : joinGroupsList2)
//					System.out.println("This is StartBindingFinal value>>>>>>>>>>>>>>>>>>>>>>>>.." + obt);

				// if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()))
				// for (List<EdgeOperator> obt : joinGroupsList2Optional)
				// System.out.println("This is StartBindingFinal Optional
				// value>>>>>>>>>>>>>>>>>>>>>>>>.." + obt);

			//	HashMap<Edge, Vertex> removal = new HashMap<>();

	//			for (Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbt : StartBindingFinal.entrySet())
	//				for (Entry<Edge, Vertex> sba : sbt.getKey().entrySet()) {
	//					if (sba.getKey().toString().contains("http")) {
	//						removal.putAll(sbt.getKey());
	//					}
	//				}
		
		//		StartBindingFinal.remove(removal);

			for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
					System.out.println("These are StartBindingFinal:" + sbf);
				
				PublicHashVertex.addAll(ProcessedVertex);
				System.out.println("These are pvertex:" + PublicHashVertex);
			//	for(Entry<Edge, Vertex> sbf:StartBindingFinalKey.entrySet()) {
					
			//	}
				for(Entry<Edge, Vertex> sbf:StartBindingFinalKey.entries()) {
					Pattern pattern = Pattern.compile("\\?\\w+");
					HashMap<String,String> abc1 =new HashMap<>();
					
					for(Entry<HashMap<String, String>, String> pe:ParaEng.FiltereqLarge.entrySet())
						for(Entry<String,String> pe1:pe.getKey().entrySet())
						{	
					Matcher matcher = pattern.matcher(pe1.getKey());
					while (matcher.find())
					{
					    abc1.put(matcher.group(),pe1.getKey());
					//	System.out.println("This is now billion times:"+matcher.group());
						
					}
						}
					if(ProcessedVertex.contains(sbf.getKey().getV1()) && ProcessedVertex.contains(sbf.getKey().getV2()))
						if(!BGPEval.urik.containsKey(sbf.getKey().getV1()) &&!BGPEval.urik.containsKey(sbf.getKey().getV2()))
						DoubleStartBinding.add(sbf.getKey());
					for(Entry<HashMap<String, String>,  String>  eq:ParaEng.FiltereqLarge.entrySet()) 
						for(Entry<String,String> eq1:eq.getKey().entrySet()) {
					
					DoubleStartBindingLarge.put(sbf.getKey(),eq1.getKey());
						}
				}
				Set<String> abc =new HashSet<>();
						for(Entry<HashMap<String, String>,  String>  eq:ParaEng.FiltereqLarge.entrySet()) 
					for(Entry<String,String> eq1:eq.getKey().entrySet()) {
						System.out.println("This is now billion times0101010:"+eq1.getKey());
						
						Pattern pattern = Pattern.compile("\\?\\w+");

						
						Matcher matcher = pattern.matcher(eq1.getKey());
						while (matcher.find())
						{
						    abc.add(matcher.group());
							System.out.println("This is now billion times:"+matcher.group());
							
						}
					//	abc.add("?"+eq1.getKey().substring(eq1.getKey().indexOf("?")).replaceAll("[^A-Za-z]+", ""));
					
				for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet()) {
					for(Entry<Edge, Vertex> sbf1:sbf.getKey().entries())
						for(String abc1:abc)
						if(sbf1.getKey().toString().contains(abc1)) {
							HashMap<HashMap<Edge, Vertex>, String> abc11  = new HashMap<>();
							HashMap<Edge, Vertex> xyz11= new HashMap<>();
							 xyz11.put(sbf1.getKey(), sbf1.getValue());
							 System.out.println("This is the largest thing:"+xyz11);
							 abc11.put(xyz11, eq.getValue());
							 System.out.println("This is the 2nd largest thing:"+abc11);
								
							 StartBindingFinalLarge.put(abc11, null);
						
						}
						}}
				
				for(Entry<HashMap<HashMap<Edge, Vertex>, String>, ArrayList<Binding>> dsb:StartBindingFinalLarge.entrySet())
					System.out.println("This was CH1 problem:"+dsb);

				
	//for(EdgeOperator ob:operators_BushyTreeOrder1)
	//			for(List<EdgeOperator> dsb:JoinGroupsListExclusive)
	//				System.out.println("This is final redo:"+dsb);

	
	
	}
	
	
	public void enableBindJoinsOptional(HashSet<List<EdgeOperator>> JoinGroupLists, List<List<EdgeOperator>> joinGroupsList2,
			List<EdgeOperator> JoinGroupsListLeft, List<EdgeOperator> JoinGroupsListRight,List<List<EdgeOperator>> joinGroupsList2Optional,
			List<EdgeOperator> JoinGroupsListLeftOptional, List<EdgeOperator> JoinGroupsListRightOptional) {
		// for(List<EdgeOperator> jgl:JoinGroupLists)
		// System.out.println("These are JoinGroupLists jgl:"+jgl);
		if(JoinGroupsListExclusive.size()==1)
		{
			System.out.println("THis is Tempf2:"+JoinGroupsListExclusive.size());			

			List<EdgeOperator>	Tempf = new ArrayList<>(); 

			for(List<EdgeOperator> dsb:JoinGroupsListExclusive)
		//	if(dsb.size()==1)
				for(EdgeOperator dsb1:dsb)
				Tempf.add(new HashJoin(dsb1.getEdge()));
System.out.println("THis is Tempf:"+Tempf);			
if(!Tempf.isEmpty())
{
	for(List<EdgeOperator> jge:JoinGroupsListExclusive) {
		JoinGroupLists.remove(jge);
		for(EdgeOperator jge1:jge)
			operators_BushyTreeOrder.remove(jge1);
		}
	
	joinGroupsList2.clear();
	joinGroupsList2.add(Tempf);
//	for(EdgeOperator tf:Tempf)
		JoinGroupLists.add(Tempf);
for(EdgeOperator t:Tempf)
	operators_BushyTreeOrder.add(t);

	JoinGroupsListExclusive.clear();
JoinGroupsListExclusive.add(Tempf);
//Tempf.clear();
System.out.println("THis is Tempf2:"+JoinGroupsListExclusive);			

}
		}		 for(List<EdgeOperator> jgl:joinGroupsList2)
		 System.out.println("This is joinGroupList loop:"+jgl);

		// Set<Set<EdgeOperator>> iii = new HashSet<>();
		// for (List<EdgeOperator> jg2 : joinGroupsList2) {
		// iii.add(jg2.parallelStream().collect(Collectors.toSet()));
		// }

//		joinGroupsList2.clear();

		// for (Set<EdgeOperator> ii : iii)
		// joinGroupsList2.add(ii.parallelStream().collect(Collectors.toList()));

		Set<EdgeOperator> BindEdges = new HashSet<>();
		HashMap<Vertex, List<EdgeOperator>> BindVertex = new HashMap<>();
		HashMap<Vertex, Integer> bindVertexTime = new HashMap<>();
		Set<EdgeOperator> HashEdges = new HashSet<>();
		Set<Vertex> HashVertex = new HashSet<>();
		HashMap<EdgeOperator, EdgeOperator> BindReplacement = new HashMap<>();
		Set<Vertex> ProcessedVertex = new HashSet<>();
///Get bind vertex
		for (List<EdgeOperator> e : JoinGroupLists) {
			for (EdgeOperator e1 : e) {
				if (e1.toString().contains("Bind"))
					BindVertex.put(e1.getStartVertex(), e);

			}
		}

		for (List<EdgeOperator> jgl : JoinGroupLists) {
			System.out.println("This is jgl213213123:" + jgl);
		}

		List<EdgeOperator> t1 = new ArrayList<>();
		List<EdgeOperator> t2 = new ArrayList<>();
		List<EdgeOperator> t3 = new ArrayList<>();

//				for(Entry<EdgeOperator, List<Binding>> t:finalResult.entrySet())
//					System.out.println("This is the ttttttttttt00000:"+t);

		/*
		 * int l=0; for (List<EdgeOperator> e : joinGroupsList2) { for (EdgeOperator e1
		 * : e) {
		 * 
		 * for(Entry<Vertex, Integer> bvt:bindVertexTime.entrySet()) {
		 * if(bvt.getValue()==0) { if (e1.toString().contains("Bind") &&
		 * (e1.getEdge().getV1().equals(bvt.getKey()) ||
		 * e1.getEdge().getV2().equals(bvt.getKey()))) { t1.addAll(e); t2.addAll(e);
		 * t2.add(new HashJoin(e1.getEdge())); System.out.println("THis is e1:"+e1);
		 * System.out.println("THis is t2 before:"+t2); t2.remove(e1); // t2.remove(e1);
		 * System.out.println("THis is t2 after:"+t2);
		 * 
		 * // l=1; // break; // } // if(l==1) // { // l=0; // break; // }
		 * 
		 * } } } } }
		 */
		/*
		 * if (t2.size() > 0 || !t2.isEmpty()) {
		 * 
		 * joinGroupsList2.remove(t1); // for (EdgeOperator tt : t1) //
		 * exchangeOfElements.remove(tt); if (!joinGroupsList2.contains(t2)) {
		 * joinGroupsList2.add(t2.parallelStream().collect(Collectors.toList()));
		 * 
		 * // for (EdgeOperator tt : t1) // finalResult.remove(tt);
		 * System.out.println("This is findFirstNonLiteral:" + t2);
		 * //finalResult.put(findFirstNonLiteral(t2.parallelStream().collect(Collectors.
		 * toList())), null); //Map<EdgeOperator, Integer> t = new HashMap<>();
		 * //t.put(findFirstNonLiteral(t2.parallelStream().collect(Collectors.toList()))
		 * , 1);
		 * //exchangeOfElements.put(t2.parallelStream().collect(Collectors.toList()).get
		 * (0), t); } for (EdgeOperator tt : t1) { Iterator<EdgeOperator> xz =
		 * operators_BushyTreeOrder.iterator(); while (xz.hasNext()) if
		 * (tt.getEdge().equals(xz.next().getEdge())) xz.remove(); }
		 * 
		 * for (EdgeOperator tt1 : t2) if (!operators_BushyTreeOrder.contains(tt1))
		 * operators_BushyTreeOrder.add(tt1); }
		 */
		// for(Entry<EdgeOperator, List<Binding>> t:finalResult.entrySet())
		// System.out.println("This is the ttttttttttt11111:"+t);

		// for (List<EdgeOperator> jgl : joinGroupsList2)
		// System.out.println("This is is is jgl:" + jgl);

		for (List<EdgeOperator> jgl2 : joinGroupsList2)
			System.out.println("THis is middle of jgl2:" + jgl2);

//		for (List<EdgeOperator> jgl2 : joinGroupsList2)
//			System.out.println("These.,.,.,.,., before:" + jgl2);

		/*
		 * if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) {
		 * JoinGroupLists.addAll(joinGroupsList2Optional); List<List<EdgeOperator>>
		 * c=new ArrayList<>();
		 * 
		 * c.add(JoinGroupsListLeftOptional);
		 * 
		 * JoinGroupLists.addAll(c); List<List<EdgeOperator>> d=new ArrayList<>();
		 * d.add(JoinGroupsListRightOptional);
		 * 
		 * JoinGroupLists.addAll(d);}
		 */
		
		Vertex x = null;

//		HashMap<Vertex, Integer> HashVertexTimes = new HashMap<>();
		// System.out.println("This is the decision");
		/*
		 * for (Vertex pv : ProcessedVertex) { int j = 0; for (List<EdgeOperator> jgl :
		 * JoinGroupLists) { if (jgl.toString().contains(pv.toString())) { j++;
		 * HashVertexTimes.put(pv, j); }
		 * 
		 * } }
		 */
		t1 = new ArrayList<>();
		t2 = new ArrayList<>();
		for (List<EdgeOperator> jgl2 : joinGroupsList2)
			System.out.println("THis is middle1 of jgl2:" + jgl2);

		/// Get all the vertex which are not start vertex and the respective
		/// edgeoperator
		HashMap<Vertex, List<EdgeOperator>> OnlyHashVertexTemp = new HashMap<>();
		List<Vertex> OnlyHashVertex = new ArrayList<>();

		for (List<EdgeOperator> jgl : JoinGroupLists) {
			for (EdgeOperator jgll : jgl)
				if (jgll.toString().contains("Bind")) {
					Vertex sv = jgll.getStartVertex();
					Vertex gv = jgll.getEdge().getV1();
					Vertex gv1 = jgll.getEdge().getV2();
				//	System.out.println("This is the prob prob prob:" + sv + "--" + gv);
				//	System.out.println("This is the prob prob prob1111:" + sv + "--" + gv1);

					if (gv.equals(sv))
						OnlyHashVertexTemp.put(jgll.getEdge().getV2(), jgl);
					if (gv1.equals(sv))
						OnlyHashVertexTemp.put(jgll.getEdge().getV1(), jgl);

				}
		}
		/// Get the vertex on other edgeoperator if on only hashvertextemp

		for (Entry<Vertex, List<EdgeOperator>> ohv : OnlyHashVertexTemp.entrySet())

			for (List<EdgeOperator> jgl : JoinGroupLists) {
				if (!ohv.getValue().equals(jgl)) {
					for (EdgeOperator jgll : jgl) {
						if (ohv.getKey().equals(jgll.getEdge().getV1()))
							OnlyHashVertex.add(jgll.getEdge().getV1());
						if (ohv.getKey().equals(jgll.getEdge().getV2()))
							OnlyHashVertex.add(jgll.getEdge().getV2());
					}
				}
			}
		List<Vertex> HashVertexTimes = new ArrayList<>();
		List<Vertex> SingleHashVertexTimes = new ArrayList<>();
		 Multimap<Vertex, Vertex> AllVertex = ArrayListMultimap.create();;

		//All the vertex that are not part of a specific edgeoperator not having respective bind vertex
		 //because these startvertex exists in a single group
		List<Vertex> Never = new ArrayList<>();
		 for (Entry<Vertex, List<EdgeOperator>> bv : BindVertex.entrySet()) {
			for (List<EdgeOperator> jgl : JoinGroupLists)
				if (!bv.getValue().equals(jgl)) {

							for(EdgeOperator jgll:jgl)
								{
								if(bv.getKey().equals(jgll.getEdge().getV1())|| bv.getKey().equals(jgll.getEdge().getV2()))
								{
									
									AllVertex.removeAll(bv.getKey());
									Never.add(bv.getKey());
								}
								else
								{
									
								if(!Never.contains(bv.getKey())) {
									AllVertex.put(bv.getKey(),jgll.getEdge().getV1());
								
								AllVertex.put(bv.getKey(),jgll.getEdge().getV2());
								}
								}	
						}
				}
		 }
		 
			//If start vertex exists in single group and its respective edge exist in other group
				 
		 for(Entry<Vertex, Vertex> av:AllVertex.entries())
			 	if(!HashVertexTimes.contains(av.getKey()))
			 HashVertexTimes.add(av.getKey());
		//If start vertex exists in single group and its respective edge exist in one instance of that group only
		 for(Vertex hvt:HashVertexTimes)
		 for(List<EdgeOperator> jgl:JoinGroupLists)
			 for(EdgeOperator jgll:jgl)
			 if(jgll.toString().contains("Bind") && jgll.getStartVertex().equals(hvt))
			 {
				 if(!OnlyHashVertex.contains(jgll.getEdge().getV1()))
				 SingleHashVertexTimes.add(jgll.getEdge().getV1());
				 if(!OnlyHashVertex.contains(jgll.getEdge().getV2()))
			 SingleHashVertexTimes.add(jgll.getEdge().getV2());
			 }
	//	 for (Vertex ohv : OnlyHashVertex)
//			System.out.println("This is OnlyHashVertex:" + ohv);

//		for (Entry<Vertex, Vertex> ohv : AllVertex.entries())
//			System.out.println("This is AllVertex:" + ohv);
		
//		for (Vertex ohv : HashVertexTimes)
//			System.out.println("This is HashVertexTimes:" + ohv);
		
//		for (Vertex ohv : SingleHashVertexTimes)
//			System.out.println("This is SingleHashVertexTimes:" + ohv);
		
		
		/*
		 * for (List<EdgeOperator> jgl : joinGroupsList2) { int i=0; for (EdgeOperator
		 * jgll : jgl) { if(i==0) { t1.addAll(jgl); t2.addAll(jgl); } if
		 * (jgll.toString().contains("Bind") &&
		 * !ProcessedVertex.contains(jgll.getStartVertex())) {
		 * 
		 * 
		 * if (jgll.getStartVertex().equals(jgll.getEdge().getV1()) &&
		 * (OnlyHashVertex.contains(jgll.getEdge().getV2())) ) { t2.add(new
		 * BindJoin(jgll.getEdge().getV2(), jgll.getEdge())); Iterator<EdgeOperator> t5
		 * = t2.iterator(); if (t5.next().equals(jgll)) t2.remove(jgll);
		 * System.out.println("This is t21:"+t2); }
		 * 
		 * if (jgll.getStartVertex().equals(jgll.getEdge().getV2()) &&
		 * (OnlyHashVertex.contains(jgll.getEdge().getV1()))) { t2.add(new
		 * BindJoin(jgll.getEdge().getV1(), jgll.getEdge())); Iterator<EdgeOperator> t5
		 * = t2.iterator(); if (t5.next().equals(jgll)) t2.remove(jgll);
		 * System.out.println("This is t22:"+t2);
		 * 
		 * }
		 * 
		 * } i++;} }
		 * 
		 * System.out.println("This is t21:"+t2);
		 * 
		 * Iterator<List<EdgeOperator>> t5 = joinGroupsList2.iterator(); if
		 * (t5.next().equals(t1)) t5.remove(); // joinGroupsList2.remove(t1);
		 * joinGroupsList2.add(t2);
		 * 
		 * t1 = new ArrayList<>(); t2 = new ArrayList<>(); Iterator<EdgeOperator> jgll =
		 * JoinGroupsListLeft.iterator(); while (jgll.hasNext()) { // for(EdgeOperator
		 * jgll:jgl) EdgeOperator jj = jgll.next(); if (jj.toString().contains("Bind")
		 * && !ProcessedVertex.contains(jj.getStartVertex())) { t1.add(jj); t2.add(jj);
		 * if (jj.getStartVertex().toString().equals(jj.getEdge().getV1().toString())) {
		 * t1.add(jj); t2.add(new BindJoin(jj.getEdge().getV2(), jj.getEdge())); //
		 * t2.remove(jj); Iterator<EdgeOperator> t9 = t2.iterator(); if
		 * (t9.next().equals(jj)) t9.remove(); } if
		 * (jj.getStartVertex().toString().equals(jj.getEdge().getV2().toString())) {
		 * t1.add(jj); t2.add(new BindJoin(jj.getEdge().getV1(), jj.getEdge())); //
		 * t2.remove(jj); Iterator<EdgeOperator> t9 = t2.iterator(); if
		 * (t9.next().equals(jj)) t9.remove(); } }
		 * 
		 * } for(List<EdgeOperator> jgl2:joinGroupsList2)
		 * System.out.println("THis is middle1 of jgl3:"+jgl2);
		 * 
		 * if (t2.size() > 0 || !t2.isEmpty()) { Iterator<EdgeOperator> t9 =
		 * JoinGroupsListLeft.iterator(); for(EdgeOperator tt:t1) if
		 * (t9.next().equals(tt)) t9.remove();
		 * 
		 */
	//	for (List<EdgeOperator> jgl2 : joinGroupsList2)
	//		System.out.println("THis is middle1 of jgl4:" + jgl2);

		/*
		 * for (EdgeOperator r4 : t1) { // JoinGroupsListLeft.remove(r4);
		 * 
		 * operators_BushyTreeOrder.remove(r4); exchangeOfElements.remove(r4);
		 * finalResult.remove(r4);
		 * 
		 * }
		 */
	//	if (!JoinGroupsListLeft.equals(t2.parallelStream().collect(Collectors.toList())))
		//	JoinGroupsListLeft.addAll(t2.parallelStream().collect(Collectors.toList()));
		// for (EdgeOperator r32 : t2) {
		// operators_BushyTreeOrder.add(r32);
		// finalResult.put(r32, null);
		// }
		// Map<EdgeOperator, Integer> t = new HashMap<>();
		// t.put(findFirstNonLiteral(t2.parallelStream().collect(Collectors.toList())),
		// 1);
		// if
		// (!JoinGroupsListLeft.equals(t2.parallelStream().collect(Collectors.toList())))/

		// exchangeOfElements.put(t2.parallelStream().collect(Collectors.toList()).get(0)//,
		// t);
		// }

//		for (EdgeOperator jgl : JoinGroupsListLeft)
//			System.out.println("These are the purposes1:" + jgl);

		/*Iterator<EdgeOperator> jglr = JoinGroupsListRight.iterator();
		while (jglr.hasNext()) {
			// for(EdgeOperator jgll:jgl)
			EdgeOperator jj = jglr.next();
			if (jj.toString().contains("Bind") && !ProcessedVertex.contains(jj.getStartVertex())) {
				t1.add(jj);
				t2.add(jj);
				if (jj.getStartVertex().equals(jj.getEdge().getV1())) {
					t2.add(new BindJoin(jj.getEdge().getV2(), jj.getEdge()));
					jglr.remove();
				}
				if (jj.getStartVertex().equals(jj.getEdge().getV2())) {
					t2.add(new BindJoin(jj.getEdge().getV1(), jj.getEdge()));
					jglr.remove();
				}
			}

		}
		JoinGroupsListRight.addAll(t2);
*/
	//	for (List<EdgeOperator> jgl2 : joinGroupsList2)
	//		System.out.println("THis is beginning2 of jgl2:" + jgl2);

		List<EdgeOperator> temp1 = new ArrayList<>();
		List<EdgeOperator> temp2 = new ArrayList<>();
		List<EdgeOperator> temp3 = new ArrayList<>();

		Set<List<EdgeOperator>> jglset11 = new HashSet<>();
		jglset11.addAll(joinGroupsList2.parallelStream().collect(Collectors.toSet()));
	if(JoinGroupsListLeft.size()>0)
			
			jglset11.add(JoinGroupsListLeft);
			if(JoinGroupsListRight.size()>0)
			jglset11.add(JoinGroupsListRight);
			
		Set<List<EdgeOperator>> jglset11Op = new HashSet<>();
		jglset11.addAll(joinGroupsList2Optional.parallelStream().collect(Collectors.toSet()));
if(JoinGroupsListLeftOptional.size()>0)
			
			jglset11.add(JoinGroupsListLeftOptional);
			if(JoinGroupsListRightOptional.size()>0)
			jglset11.add(JoinGroupsListRightOptional);
		
	//	for (List<EdgeOperator> jgl : JoinGroupLists)
	//		System.out.println("These are the purposes1:" + jgl);
	//	Set<List<EdgeOperator>> jglset = new HashSet<>();
	//	jglset.addAll(joinGroupsList2);

					for (List<EdgeOperator> jgl : jglset11) {
						temp1 = new ArrayList<>();
					temp2 = new ArrayList<>();
						temp3 = new ArrayList<>();
					
					for (EdgeOperator jgll1 : jgl)
						if (jgll1.getStartVertex() != null)
						{	if (HashVertexTimes.contains(jgll1.getStartVertex())
									&& (OnlyHashVertex.contains(jgll1.getEdge().getV1()))) {
						//	if(!temp1.equals(jgl))	
						//	{	
								temp1.addAll(jgl);
						
						    temp2.add(new BindJoin(jgll1.getEdge().getV1(),jgll1.getEdge()));			
							temp2.addAll(jgl);
									Iterator<EdgeOperator> at = temp2.iterator();
									while(at.hasNext())
										if(at.next().equals(jgll1))
											at.remove();
								//	t2.remove(jgll);
									temp3.add(jgll1);
									
								//	t2.remove(jgll);
									for(EdgeOperator ttt:temp3)
										temp2.remove(ttt);

		//							System.out.println("This is temp1.2:"+temp1);
		//							System.out.println("This is temp2.2:"+temp2);
								

							}
					if (HashVertexTimes.contains(jgll1.getStartVertex())
							&& (OnlyHashVertex.contains(jgll1.getEdge().getV2()))) {
						if(!temp1.equals(jgl))	
							temp1.addAll(jgl);
					
						temp2.add(new BindJoin(jgll1.getEdge().getV2(),jgll1.getEdge()));		
						temp2.addAll(jgl);
								Iterator<EdgeOperator> at1 = temp2.iterator();
								while(at1.hasNext())
									if(at1.next().equals(jgll1))
										at1.remove();
							//	t2.remove(jgll);
								temp3.add(jgll1);
								
							//	t2.remove(jgll);
								for(EdgeOperator ttt:temp3)
									temp2.remove(ttt);
				
					
			//					System.out.println("This is temp1.1:"+temp1);
			//					System.out.println("This is temp2.1:"+temp2);
							
					}
					

						}
		//			System.out.println("This is temp1:"+temp1);
		//			System.out.println("This is temp2:"+temp2);
					int typee=0;
					Iterator<List<EdgeOperator>> at = joinGroupsList2.iterator();
					while(at.hasNext())
					{
						List<EdgeOperator> at1 = at.next().parallelStream().distinct().collect(Collectors.toList());
						if(at1.equals(temp1)) {
						at.remove();
						typee=1;
						}
					}
					if(JoinGroupsListLeft.size()>0)
					{Iterator<EdgeOperator> at1 = JoinGroupsListLeft.iterator();
					while(at1.hasNext())
					{ EdgeOperator aaa = at1.next();
						for(EdgeOperator t:temp1)
						if(aaa.equals(t)) {
						at1.remove();
						typee=2;
						}
						
					}}
					if(JoinGroupsListRight.size()>0)
					{
					Iterator<EdgeOperator> at11 = JoinGroupsListRight.iterator();
					while(at11.hasNext())
					{
						EdgeOperator aaa = at11.next();
						for(EdgeOperator t:temp1)
						if(aaa.equals(t)) {
						at11.remove();
						typee=3;
						}
					}
					}	
					
					//		if (!joinGroupsList2.contains(t2))
					if(typee==1)
					joinGroupsList2.add(temp2);
					if(typee==2)
						JoinGroupsListLeft.addAll(temp2);
					if(typee==3)
						JoinGroupsListRight.addAll(temp2);
						for (EdgeOperator tt : temp1) {
							Iterator<EdgeOperator> xz = operators_BushyTreeOrder.iterator();
							while (xz.hasNext())
								if (tt.getEdge().equals(xz.next().getEdge()))
									xz.remove();
						}

						for (EdgeOperator tt1 : temp2)
							if (!operators_BushyTreeOrder.contains(tt1))
								operators_BushyTreeOrder.add(tt1);

					}
					
					
					for (List<EdgeOperator> jgl : jglset11Op) {
						temp1 = new ArrayList<>();
					temp2 = new ArrayList<>();
						temp3 = new ArrayList<>();
					
					for (EdgeOperator jgll1 : jgl)
						if (jgll1.getStartVertex() != null)
						{	if (HashVertexTimes.contains(jgll1.getStartVertex())
									&& (OnlyHashVertex.contains(jgll1.getEdge().getV1()))) {
						//	if(!temp1.equals(jgl))	
						//	{	
								temp1.addAll(jgl);
						
						    temp2.add(new BindJoin(jgll1.getEdge().getV1(),jgll1.getEdge()));			
							temp2.addAll(jgl);
									Iterator<EdgeOperator> at = temp2.iterator();
									while(at.hasNext())
										if(at.next().equals(jgll1))
											at.remove();
								//	t2.remove(jgll);
									temp3.add(jgll1);
									
								//	t2.remove(jgll);
									for(EdgeOperator ttt:temp3)
										temp2.remove(ttt);

		//							System.out.println("This is temp1.2:"+temp1);
		//							System.out.println("This is temp2.2:"+temp2);
								

							}
					if (HashVertexTimes.contains(jgll1.getStartVertex())
							&& (OnlyHashVertex.contains(jgll1.getEdge().getV2()))) {
						if(!temp1.equals(jgl))	
							temp1.addAll(jgl);
					
						temp2.add(new BindJoin(jgll1.getEdge().getV2(),jgll1.getEdge()));		
						temp2.addAll(jgl);
								Iterator<EdgeOperator> at1 = temp2.iterator();
								while(at1.hasNext())
									if(at1.next().equals(jgll1))
										at1.remove();
							//	t2.remove(jgll);
								temp3.add(jgll1);
								
							//	t2.remove(jgll);
								for(EdgeOperator ttt:temp3)
									temp2.remove(ttt);
				
					
			//					System.out.println("This is temp1.1:"+temp1);
			//					System.out.println("This is temp2.1:"+temp2);
							
					}
					

						}
		//			System.out.println("This is temp1:"+temp1);
		//			System.out.println("This is temp2:"+temp2);
					int typee=0;
					Iterator<List<EdgeOperator>> at = joinGroupsList2Optional.iterator();
					while(at.hasNext())
					{
						List<EdgeOperator> at1 = at.next().parallelStream().distinct().collect(Collectors.toList());
						if(at1.equals(temp1)) {
						at.remove();
						typee=1;
						}
					}
					if(JoinGroupsListLeftOptional.size()>0)
					{Iterator<EdgeOperator> at1 = JoinGroupsListLeftOptional.iterator();
					while(at1.hasNext())
					{ EdgeOperator aaa = at1.next();
						for(EdgeOperator t:temp1)
						if(aaa.equals(t)) {
						at1.remove();
						typee=2;
						}
						
					}}
					if(JoinGroupsListRightOptional.size()>0)
					{
					Iterator<EdgeOperator> at11 = JoinGroupsListRightOptional.iterator();
					while(at11.hasNext())
					{
						EdgeOperator aaa = at11.next();
						for(EdgeOperator t:temp1)
						if(aaa.equals(t)) {
						at11.remove();
						typee=3;
						}
					}
					}	
					
					//		if (!joinGroupsList2.contains(t2))
					if(typee==1)
					joinGroupsList2Optional.add(temp2);
					if(typee==2)
						JoinGroupsListLeftOptional.addAll(temp2);
					if(typee==3)
						JoinGroupsListRightOptional.addAll(temp2);
					
			
						for (EdgeOperator tt : temp1) {
							Iterator<EdgeOperator> xz = operators_BushyTreeOrder.iterator();
							while (xz.hasNext())
								if (tt.getEdge().equals(xz.next().getEdge()))
									xz.remove();
						}

						for (EdgeOperator tt1 : temp2)
							if (!operators_BushyTreeOrder.contains(tt1))
								operators_BushyTreeOrder.add(tt1);

					}
					// joinGroupsList2.remove(temp1);
			
	//	for (List<EdgeOperator> jgl2 : joinGroupsList2)
	//		System.out.println("THis is beginning 1 of jgl2:" + jgl2);

		
		for (Entry<Vertex, List<EdgeOperator>> pv : BindVertex.entrySet()) {
			int j1 = 0;
			for (List<EdgeOperator> jgl1 : JoinGroupLists) {
				for (EdgeOperator jgll1 : jgl1)
					if (!jgl1.equals(pv.getValue()))
						if (

						(jgll1.getEdge().getV1().equals(pv.getKey()) || jgll1.getEdge().getV2().equals(pv.getKey()))) {
							bindVertexTime.put(pv.getKey(), j1++);
						}

			}
		}

		
		
		t1 = new ArrayList<>();
		t2 = new ArrayList<>();
		t3 = new ArrayList<>();

		Set<List<EdgeOperator>> jglset111 = new HashSet<>();
	
		
		jglset111.addAll(joinGroupsList2.parallelStream().collect(Collectors.toSet()));
	if(JoinGroupsListLeft.size()>0)
			
			jglset111.add(JoinGroupsListLeft);
			if(JoinGroupsListRight.size()>0)
			jglset111.add(JoinGroupsListRight);
			
		Set<List<EdgeOperator>> jglset111Op = new HashSet<>();
		jglset111.addAll(joinGroupsList2Optional.parallelStream().collect(Collectors.toSet()));
if(JoinGroupsListLeftOptional.size()>0)
			
			jglset111.add(JoinGroupsListLeftOptional);
			if(JoinGroupsListRightOptional.size()>0)
			jglset111.add(JoinGroupsListRightOptional);
	//	for (List<EdgeOperator> jgl : JoinGroupLists)
	//		System.out.println("These are the purposes1:" + jgl);
	//	Set<List<EdgeOperator>> jglset = new HashSet<>();
	//	jglset.addAll(joinGroupsList2);

					for (List<EdgeOperator> jgl : jglset111) {
						temp1 = new ArrayList<>();
					temp2 = new ArrayList<>();
						temp3 = new ArrayList<>();
					
					for (EdgeOperator jgll1 : jgl)
						if (jgll1.getStartVertex() != null)
						{	if (HashVertexTimes.contains(jgll1.getStartVertex())
									&& (SingleHashVertexTimes.contains(jgll1.getEdge().getV1())
								||	SingleHashVertexTimes.contains(jgll1.getEdge().getV2())		)) {
						//	if(!temp1.equals(jgl))	
						//	{	
								temp1.addAll(jgl);
								temp2.addAll(jgl);
								
						    temp2.add(new HashJoin(jgll1.getEdge()));			
									Iterator<EdgeOperator> at = temp2.iterator();
									while(at.hasNext())
										if(at.next().equals(jgll1))
											at.remove();
								//	t2.remove(jgll);
									temp3.add(jgll1);
									
								//	t2.remove(jgll);
									for(EdgeOperator ttt:temp3)
										temp2.remove(ttt);

		//							System.out.println("This is temp1.2:"+temp1);
		//							System.out.println("This is temp2.2:"+temp2);
								

							}


						}
//					System.out.println("This is temp1:"+temp1);
//					System.out.println("This is temp2:"+temp2);
					
					Iterator<List<EdgeOperator>> at = joinGroupsList2.iterator();
					while(at.hasNext())
						if(at.next().equals(temp1))
							at.remove();
					
						
					//		if (!joinGroupsList2.contains(t2))
						joinGroupsList2.add(temp2);
						for (EdgeOperator tt : temp1) {
							Iterator<EdgeOperator> xz = operators_BushyTreeOrder.iterator();
							while (xz.hasNext())
								if (tt.getEdge().equals(xz.next().getEdge()))
									xz.remove();
						}

						for (EdgeOperator tt1 : temp2)
							if (!operators_BushyTreeOrder.contains(tt1))
								operators_BushyTreeOrder.add(tt1);

					}
		
					for (List<EdgeOperator> jgl : jglset111Op) {
						temp1 = new ArrayList<>();
					temp2 = new ArrayList<>();
						temp3 = new ArrayList<>();
					
					for (EdgeOperator jgll1 : jgl)
						if (jgll1.getStartVertex() != null)
						{	if (HashVertexTimes.contains(jgll1.getStartVertex())
									&& (SingleHashVertexTimes.contains(jgll1.getEdge().getV1())
								||	SingleHashVertexTimes.contains(jgll1.getEdge().getV2())		)) {
						//	if(!temp1.equals(jgl))	
						//	{	
								temp1.addAll(jgl);
								temp2.addAll(jgl);
								
						    temp2.add(new HashJoin(jgll1.getEdge()));			
									Iterator<EdgeOperator> at = temp2.iterator();
									while(at.hasNext())
										if(at.next().equals(jgll1))
											at.remove();
								//	t2.remove(jgll);
									temp3.add(jgll1);
									
								//	t2.remove(jgll);
									for(EdgeOperator ttt:temp3)
										temp2.remove(ttt);

		//							System.out.println("This is temp1.2:"+temp1);
		//							System.out.println("This is temp2.2:"+temp2);
								

							}


						}
//					System.out.println("This is temp1:"+temp1);
//					System.out.println("This is temp2:"+temp2);
					
					Iterator<List<EdgeOperator>> at = joinGroupsList2Optional.iterator();
					while(at.hasNext())
						if(at.next().equals(temp1))
							at.remove();
					
						
					//		if (!joinGroupsList2.contains(t2))
						joinGroupsList2Optional.add(temp2);
						for (EdgeOperator tt : temp1) {
							Iterator<EdgeOperator> xz = operators_BushyTreeOrder.iterator();
							while (xz.hasNext())
								if (tt.getEdge().equals(xz.next().getEdge()))
									xz.remove();
						}

						for (EdgeOperator tt1 : temp2)
							if (!operators_BushyTreeOrder.contains(tt1))
								operators_BushyTreeOrder.add(tt1);

					}
					
				
					
		//		for (List<EdgeOperator> jgl2 : joinGroupsList2)
	//		System.out.println("THis is beginning of jgl2:" + jgl2);

				BindEdges.clear();
				HashEdges.clear();
				
				for (List<EdgeOperator> e : JoinGroupLists) {
					for (EdgeOperator e1 : e) {
						if (e1.toString().contains("Hash")) {
							if (!e1.getEdge().getTriple().getSubject().isLiteral()
									&& !e1.getEdge().getTriple().getSubject().isURI())
								HashVertex.add(e1.getEdge().getV1());
							if (!e1.getEdge().getTriple().getObject().isLiteral()
									&& !e1.getEdge().getTriple().getObject().isURI())
								HashVertex.add(e1.getEdge().getV2());

						}
					}
				}
				int bindno = 0;
				// System.out.println("THis is HashVertex:" + HashVertex);
				for (List<EdgeOperator> e : JoinGroupLists) {
					for (EdgeOperator e1 : e) {
						if (e1.toString().contains("Bind")) {
							BindEdges.add(e1);
						}
						if (e1.toString().contains("Hash")) {
							HashEdges.add(e1);

						}
					}
				}
			//	for(EdgeOperator he:HashEdges)
		//			System.out.println("This is hashJoin11:"+he);
			//		for(EdgeOperator he:BindEdges)
			//			System.out.println("This is BindJoin11:"+he);
				for (EdgeOperator be : BindEdges) {
					int isApp = 0;
					for (Vertex hv : HashVertex) {
						if (be.getStartVertex().toString().equals(hv.toString())) {
							isApp = 1;
						}

						// (?geneResults: 2147483647 false), (?expValue: 2147483647 false)
						// [Hash join: (http://tcga.deri.ie/TCGA-37-3789: 1 true) - (?geneResults:
						// 2147483647 false),
						// Hash join: (?uri: 2147483647 false) - (http://tcga.deri.ie/TCGA-37-3789: 1
						// true)]
						// Bind join: (?geneResults: 2147483647 false)--(?geneResults: 2147483647 false)
						// - (?expValue: 2147483647 false)

						// System.out.println("This is here in be:"+isApp+"--"+be+"--"+hv);
						if (isApp == 0) {
							// int count=0;
							// for(List<EdgeOperator> jgl:JoinGroupLists)
							// if(jgl.toString().contains(hv.toString()))
							// count++;
							// System.out.println("THis is isApp:" + be + "--" + hv+"--"+count);
							if (be.getEdge().getV1().toString().equals(hv.toString())) {

								BindReplacement.put(be, new BindJoin(be.getEdge().getV1(), be.getEdge()));
								break;
							}
							if (be.getEdge().getV2().toString().equals(hv.toString())) {
								BindReplacement.put(be, new BindJoin(be.getEdge().getV2(), be.getEdge()));
								break;
							}
						}
					}
				}
				if (BindReplacement.size() > 0) {
//				for (Entry<EdgeOperator, EdgeOperator> br : BindReplacement.entrySet())
//					System.out.println("These are BindReplacement:"+br);
//				System.out.println("These are BindReplacement processing:"+BindReplacement.size());

					for (Entry<EdgeOperator, EdgeOperator> br : BindReplacement.entrySet()) {
						operators_BushyTreeOrder.remove(br.getKey());
						operators_BushyTreeOrder.add(br.getValue());

						if (JoinGroupsListLeft.contains(br.getKey())) {
							// System.out.println("This is within JoinGroupsListLeft:" + br.getKey());
							JoinGroupsListLeft.remove(br.getKey());
							JoinGroupsListLeft.add(br.getValue());

							// for (Entry<EdgeOperator, List<Binding>> fr : finalResultLeft.entrySet())
							// System.out.println("THis is finalResultLeft replacement:" + fr);

						}

						if (JoinGroupsListRight.toString().contains(br.getKey().toString())) {
							JoinGroupsListRight.remove(br.getKey());
							JoinGroupsListRight.add(br.getValue());
						}

						
						if (JoinGroupsListLeftOptional.contains(br.getKey())) {
							// System.out.println("This is within JoinGroupsListLeft:" + br.getKey());
							JoinGroupsListLeftOptional.remove(br.getKey());
							JoinGroupsListLeftOptional.add(br.getValue());

							// for (Entry<EdgeOperator, List<Binding>> fr : finalResultLeft.entrySet())
							// System.out.println("THis is finalResultLeft replacement:" + fr);

						}

						if (JoinGroupsListRightOptional.toString().contains(br.getKey().toString())) {
							JoinGroupsListRightOptional.remove(br.getKey());
							JoinGroupsListRightOptional.add(br.getValue());
						}
						// if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()))
						// if (JoinGroupsListRightOptional.toString().contains(br.getKey().toString()))
						// {
						// JoinGroupsListRightOptional.remove(br.getKey());
						// JoinGroupsListRightOptional.add(br.getValue());
						// }
						List<List<EdgeOperator>> Temp1 = new ArrayList<>();
						List<List<EdgeOperator>> Temp2 = new ArrayList<>();
						List<EdgeOperator> Temp3 = new ArrayList<>();
						// System.out.println("This is joinGroupList with br.getKey():"+br.getKey());
						// for(List<EdgeOperator> jgl:joinGroupsList2)
						// System.out.println("This is joinGroupList loop:"+jgl);

						for (List<EdgeOperator> jgl : joinGroupsList2)
							if (jgl.toString().contains(br.getKey().toString())) {
								// System.out.println("This is joinGroupList with br.getKey():"+br.getKey());
								Temp3.addAll(jgl);
								Temp3.remove(br.getKey());
								Temp3.add(br.getValue());

								Temp1.add(Temp3);
								Temp2.add(jgl);
							}

						
						
						// System.out.println("THis is Temp2 joinGroupList:"+Temp2);
						// System.out.println("THis is Temp1 joinGroupList:"+Temp1);
						for (List<EdgeOperator> t21 : Temp2)
							if (joinGroupsList2.contains(t21))
								joinGroupsList2.remove(t21);
						joinGroupsList2.addAll(Temp1);

						
						
						for (List<EdgeOperator> jgl : joinGroupsList2Optional)
							if (jgl.toString().contains(br.getKey().toString())) {
								// System.out.println("This is joinGroupList with br.getKey():"+br.getKey());
								Temp3.addAll(jgl);
								Temp3.remove(br.getKey());
								Temp3.add(br.getValue());

								Temp1.add(Temp3);
								Temp2.add(jgl);
							}

						
						
						// System.out.println("THis is Temp2 joinGroupList:"+Temp2);
						// System.out.println("THis is Temp1 joinGroupList:"+Temp1);
						for (List<EdgeOperator> t21 : Temp2)
							if (joinGroupsList2Optional.contains(t21))
								joinGroupsList2Optional.remove(t21);
						joinGroupsList2Optional.addAll(Temp1);

						// for (List<EdgeOperator> jgl : joinGroupsList2)
						// System.out.println("This is is is jgl 111111111111111111111111111:" + jgl);
						/*
						 * if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) { for(List<EdgeOperator>
						 * jgl:joinGroupsList2Optional) if
						 * (jgl.toString().contains(br.getKey().toString())) {
						 * System.out.println("This is joinGroupList with br.getKey():"+br.getKey());
						 * Temp6.addAll(jgl); Temp6.remove(br.getKey()); Temp6.add(br.getValue());
						 * 
						 * Temp4.add(Temp6); Temp5.add(jgl); } //
						 * System.out.println("THis is Temp2 joinGroupList:"+Temp2); //
						 * System.out.println("THis is Temp1 joinGroupList:"+Temp1);
						 * for(List<EdgeOperator> t2:Temp5) if(joinGroupsList2Optional.contains(t2))
						 * joinGroupsList2Optional.remove(t2); joinGroupsList2Optional.addAll(Temp4); }
						 */

						// JoinGroupLists.clear();
						// JoinGroupLists.addAll(joinGroupsList2);
						// JoinGroupLists.add(JoinGroupsListLeft);
						// JoinGroupLists.add(JoinGroupsListRight);

					}
				}
				
				
			//	for(EdgeOperator he:HashEdges)
			//	System.out.println("This is hashJoin00:"+he);
			//	for(EdgeOperator he:BindEdges)
			//		System.out.println("This is BindJoin00:"+he);
				
				List<EdgeOperator> a = new ArrayList<>();
				JoinGroupLists.clear();
				JoinGroupLists.addAll(joinGroupsList2);
				for (EdgeOperator jgl : JoinGroupsListLeft) {
					a.add(jgl);
					JoinGroupLists.add(a);
				}
				// System.out.println("These.,.,.,.,.,:"+JoinGroupsListLeft);
				List<List<EdgeOperator>> b = new ArrayList<>();
				for (EdgeOperator jgl : JoinGroupsListRight) {
					a.add(jgl);
					JoinGroupLists.add(a);
				}

				//JoinGroupLists.addAll(b);

				
				 a = new ArrayList<>();
				JoinGroupLists.addAll(joinGroupsList2Optional);
				for (EdgeOperator jgl : JoinGroupsListLeftOptional) {
					a.add(jgl);
					JoinGroupLists.add(a);
				}
				// System.out.println("These.,.,.,.,.,:"+JoinGroupsListLeft);
				 b = new ArrayList<>();
				for (EdgeOperator jgl : JoinGroupsListRightOptional) {
					a.add(jgl);
					JoinGroupLists.add(a);
				}

//				JoinGroupLists.addAll(b);

				
				BindEdges.clear();
				HashEdges.clear();

				// for(List<EdgeOperator> jgl:JoinGroupLists)
				// System.out.println("These are JoinGroupLists jgl111:"+jgl);
						for (Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbt : StartBindingFinal.entrySet())
					System.out.println("This is obt value StartBindingFinal:" + sbt);

//				for (Entry<ConcurrentHashMap<Vertex, Edge>, ArrayList<Binding>> obt : StartBindingFinal.entrySet())
//					System.out.println("This is StartBindingFinal value:" + obt);

//				for (List<EdgeOperator> obt : joinGroupsList2)
//					System.out.println("This is StartBindingFinal value>>>>>>>>>>>>>>>>>>>>>>>>.." + obt);

				// if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()))
				// for (List<EdgeOperator> obt : joinGroupsList2Optional)
				// System.out.println("This is StartBindingFinal Optional
				// value>>>>>>>>>>>>>>>>>>>>>>>>.." + obt);

			//	HashMap<Edge, Vertex> removal = new HashMap<>();

	//			for (Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbt : StartBindingFinal.entrySet())
	//				for (Entry<Edge, Vertex> sba : sbt.getKey().entrySet()) {
	//					if (sba.getKey().toString().contains("http")) {
	//						removal.putAll(sbt.getKey());
	//					}
	//				}
		
		//		StartBindingFinal.remove(removal);

			for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
					System.out.println("These are StartBindingFinal:" + sbf);
				

//				for (Entry<ConcurrentHashMap<Vertex, Edge>, ArrayList<Binding>> obt : StartBindingFinal.entrySet())
//					System.out.println("This is StartBindingFinal value:" + obt);

//				for (List<EdgeOperator> obt : joinGroupsList2)
//					System.out.println("This is StartBindingFinal value>>>>>>>>>>>>>>>>>>>>>>>>.." + obt);

				// if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()))
				// for (List<EdgeOperator> obt : joinGroupsList2Optional)
				// System.out.println("This is StartBindingFinal Optional
				// value>>>>>>>>>>>>>>>>>>>>>>>>.." + obt);

			//	HashMap<Edge, Vertex> removal = new HashMap<>();

	//			for (Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbt : StartBindingFinal.entrySet())
	//				for (Entry<Edge, Vertex> sba : sbt.getKey().entrySet()) {
	//					if (sba.getKey().toString().contains("http")) {
	//						removal.putAll(sbt.getKey());
	//					}
	//				}
		
		//		StartBindingFinal.remove(removal);

	//			for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
	//				System.out.println("These are StartBindingFinal:" + sbf);
				
				PublicHashVertex.addAll(ProcessedVertex);
				System.out.println("These are pvertex:" + PublicHashVertex);
				for(Entry<Edge, Vertex> sbf:StartBindingFinalKey.entries()) {
					if(ProcessedVertex.contains(sbf.getKey().getV1()) && ProcessedVertex.contains(sbf.getKey().getV2()))
						DoubleStartBinding.add(sbf.getKey());
				}
				Set<String> abc =new HashSet<>();
				for(Entry<HashMap<String, String>,  String>  eq:ParaEng.FiltereqLarge.entrySet()) 
					for(Entry<String,String> eq1:eq.getKey().entrySet()) {
					System.out.println("This is now billion times:"+eq1.getKey().substring(eq1.getKey().indexOf("?"), eq1.getKey().indexOf(")")));
					abc.add(eq1.getKey().substring(eq1.getKey().indexOf("?"), eq1.getKey().indexOf(")")));
					
				for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet()) {
					for(Entry<Edge, Vertex> sbf1:sbf.getKey().entries())
						for(String abc1:abc)
						if(sbf1.getKey().toString().contains(abc1)) {
							HashMap<HashMap<Edge, Vertex>, String> abc11  =new HashMap<>();
							HashMap<Edge, Vertex> xyz11= new HashMap<>();
							 xyz11.put(sbf1.getKey(), sbf1.getValue());
							 System.out.println("This is the largest thing:"+xyz11);
							 abc11.put(xyz11, eq.getValue());
							 System.out.println("This is the 2nd largest thing:"+abc11);
								
							 StartBindingFinalLarge.put(abc11, null);
						
						}
						}}
				
			//	for(Entry<HashMap<HashMap<Edge, Vertex>, String>, ArrayList<Binding>> dsb:StartBindingFinalLarge.entrySet())
			//		System.out.println("This was CH1 problem:"+dsb);

	
			//	for(List<EdgeOperator> dsb:JoinGroupsListExclusive)
			//		System.out.println("This is final redo:"+dsb);

	
		}
	
	public void enableBindJoinsOptionals(List<EdgeOperator> JoinGroupLists) {
	
	
		LinkedHashSet<EdgeOperator> BindEdges = new LinkedHashSet<>();
		LinkedHashSet<EdgeOperator> HashEdges = new LinkedHashSet<>();
		LinkedHashSet<Vertex> ProcessedVertex = new LinkedHashSet<>();		
	//	for (EdgeOperator be : JoinGroupLists)
	//		System.out.println("This is bindEdge bbbbbbbbbbbb:"+be);
	
		
		for (EdgeOperator e : JoinGroupLists) {
				if (e.toString().contains("Bind"))
					BindEdges.add(e);
				if (e.toString().contains("Hash")) {
					HashEdges.add(e);

				}
			}
		
	
		
	//	for(EdgeOperator he:HashEdges)
	//	System.out.println("This is hashJoin:"+he);
	//	for(EdgeOperator he:BindEdges)
	//		System.out.println("This is BindJoin:"+he);

		LinkedListMultimap<Edge, Vertex> temp = LinkedListMultimap.create();
/*List<EdgeOperator> tt=new ArrayList<>();
//for(EdgeOperator bo:operators_BushyTreeOrder1) {
for (EdgeOperator be : BindEdges)
//if(bo.getEdge().equals(be.getEdge()))
	tt.add(be);
//}
BindEdges.clear();
BindEdges.addAll(tt);
tt.clear();
//for(EdgeOperator bo:operators_BushyTreeOrder1) {
for (EdgeOperator be : HashEdges)
//if(bo.getEdge().equals(be.getEdge()))
	tt.add(be);
//}

HashEdges.clear();
HashEdges.addAll(tt);

	*/	
		StartBindingFinal.clear();
		StartBindingFinalKey.clear();
		for (EdgeOperator be : BindEdges)
			System.out.println("This is bindEdges be:"+be);
			for (EdgeOperator he : HashEdges)
				System.out.println("This is hashEdges he:"+he);

				for (EdgeOperator be : BindEdges)
			for (EdgeOperator he : HashEdges)
			{
			//	System.out.println("These are inside StartBindingFinal20000000000.1:" + be.getStartVertex().getNode().toString());
			//	System.out.println("These are inside StartBindingFinal20000000000.2:" + he.getEdge().getV1().getNode().toString()+"--"+ he.getEdge().getV2().getNode().toString());
		
				
				if (be.getStartVertex().getNode().toString().equals(he.getEdge().getV1().getNode().toString())
						|| be.getStartVertex().getNode().toString().equals(he.getEdge().getV2().getNode().toString())) {
					// if(!he.getEdge().getV1().toString().contains("http") &&
					// !he.getEdge().getV2().toString().contains("http"))
	//				if(!StartBindingFinalKey.containsKey(he.getEdge()))
						
					{
						 temp =LinkedListMultimap.create();
						temp.put(he.getEdge(), be.getStartVertex());
						StartBindingFinal.put(temp, new ArrayList<>());
						// Processed.add(he);
						ProcessedVertex.add(be.getStartVertex());
				//		ProcessedVertex.add(be.getEdge().getV1());
				//		ProcessedVertex.add(be.getEdge().getV2());
						StartBindingFinalKey.putAll(temp);
			//			System.out.println("These are inside StartBindingFinal00000000000.1:" + StartBindingFinal);
					}
			}		
				}
			//	for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
			//		System.out.println("These are StartBindingFinal00000000000.1:" + sbf);

				for (EdgeOperator he : BindEdges)
					
		for (EdgeOperator be : BindEdges)
			{	
			//	System.out.println("These are inside StartBindingFinal10000000000.1:" + be.getStartVertex().getNode().toString());
			//	System.out.println("These are inside StartBindingFinal10000000000.2:" + he.getEdge().getV1().getNode().toString()+"--"+ he.getEdge().getV2().getNode().toString());
					
				if (be.getStartVertex().getNode().toString().equals(he.getEdge().getV1().getNode().toString())
						|| be.getStartVertex().getNode().toString().equals(he.getEdge().getV2().getNode().toString())) {
		//			if(!StartBindingFinalKey.containsKey(he.getEdge())) {
									
					//	if(!StartBindingFinalKey.containsKey(he.getEdge()))
				//	{
					
					{		
						temp.put(he.getEdge(), be.getStartVertex());
						StartBindingFinal.put(temp, new ArrayList<>());
						// Processed.add(he);
						ProcessedVertex.add(be.getStartVertex());
				//		ProcessedVertex.add(be.getEdge().getV1());
				//		ProcessedVertex.add(be.getEdge().getV2());
						StartBindingFinalKey.putAll(temp);
		//				System.out.println("These are inside StartBindingFinal993000000000.1:" + StartBindingFinal);
										
				}}
			}		
				//}
		
		
	//	for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
	//		System.out.println("These are StartBindingFinal00000000000:" + sbf);
	
	/*	for(Vertex pv:ProcessedVertex)
			System.out.println("This is the first processed vertex:"+pv);
	for (EdgeOperator be : BindEdges)
		if(!StartBindingFinalKey.containsKey(be.getEdge()))	
			{
			if(ProcessedVertex.toString().contains(be.getEdge().getV1().getNode().toString()))
				temp.put(be.getEdge(), be.getEdge().getV1());
			else if(ProcessedVertex.toString().contains(be.getEdge().getV2().getNode().toString()))
				temp.put(be.getEdge(), be.getEdge().getV2());
			StartBindingFinalKey.putAll(temp);
			
			StartBindingFinal.put(temp, new ArrayList<>());
			}
		/*			for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
		System.out.println("These are StartBindingFinal11111:" + sbf);
		for (EdgeOperator be : BindEdges)
			for (EdgeOperator he : BindEdges)
				
				if (be.getStartVertex().equals(he.getEdge().getV1()) 
						&& (!be.equals(he))) {
					if (be.getEdge().getV1().equals(he.getEdge().getV1())
							|| be.getEdge().getV1().equals(he.getEdge().getV2()))
						if(ProcessedVertex.contains(be.getEdge().getV1()))
							if(!StartBindingFinalKey.containsKey(he.getEdge()))
						temp.put(he.getEdge(), be.getEdge().getV1());
					else
						if(!StartBindingFinalKey.containsKey(he.getEdge()))
						temp.put(he.getEdge(), be.getStartVertex());
					StartBindingFinalKey.putAll(temp);
					
					StartBindingFinal.put(temp, new ArrayList<>());
					ProcessedVertex.add(be.getStartVertex());
					
				}
*
//		for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
//System.out.println("These are StartBindingFinal11111111111:" + sbf);

		
		for (EdgeOperator be : BindEdges)

			for (EdgeOperator he : BindEdges) {// System.out.println("These are the determined
												// vertex:"+be.getStartVertex()+"--"+he.getEdge().getV2()+"--"+ProcessedVertex);
				if (be.getStartVertex().equals(he.getEdge().getV1())
						&& !be.equals(he) && !ProcessedVertex.contains(be.getStartVertex())) {
					//if(!ProcessedVertex.contains(be.getStartVertex()) || !ProcessedVertex.contains(he.getStartVertex()))
						
					if (be.getEdge().getV1().getNode().toString().equals(he.getEdge().getV1().getNode().toString())
							|| be.getEdge().getV1().getNode().toString().equals(he.getEdge().getV2().getNode().toString())) {
							
						if((ProcessedVertex.size()>0 || !ProcessedVertex.isEmpty()))
						{	if(ProcessedVertex.contains(be.getEdge().getV1()))
							if(!StartBindingFinalKey.containsKey(he.getEdge()))
						temp.put(he.getEdge(), be.getEdge().getV1());
						
						else
							if(!StartBindingFinalKey.containsKey(he.getEdge()))
							temp.put(he.getEdge(), be.getStartVertex()); }
						else	if(!StartBindingFinalKey.containsKey(he.getEdge()))
							temp.put(he.getEdge(), be.getEdge().getV1());
						}
					else
						if(!StartBindingFinalKey.containsKey(he.getEdge()))
						temp.put(he.getEdge(), be.getStartVertex());

					// temp.put(be.getEdge().getV2(), he.getEdge());
					// System.out.println("These are the determined vertex1111:" + temp);
					StartBindingFinalKey.putAll(temp);
					StartBindingFinal.put(temp, new ArrayList<>());
					ProcessedVertex.add(be.getStartVertex());

				}
			}
		//for(Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
		//	System.out.println("These are StartBindingFinal2222222222:" + sbf);
		
		for (EdgeOperator be : BindEdges)

			for (EdgeOperator he : BindEdges) {// System.out.println("These are the determined
												// vertex:"+be.getStartVertex()+"--"+he.getEdge().getV2()+"--"+ProcessedVertex);
				if(!ProcessedVertex.contains(be.getStartVertex()) || !ProcessedVertex.contains(he.getStartVertex()))
					
				if (be.getStartVertex().getNode().toString().equals(he.getEdge().getV2().getNode().toString())
						&& !be.equals(he)) {
					
					if (be.getEdge().getV2().getNode().toString().equals(he.getEdge().getV1().getNode().toString())
							|| be.getEdge().getV2().getNode().toString().equals(he.getEdge().getV2().getNode().toString())) {
						if((ProcessedVertex.size()>0 || !ProcessedVertex.isEmpty()))
						{	if(ProcessedVertex.contains(be.getEdge().getV2()))
							if(!StartBindingFinalKey.containsKey(he.getEdge()))
							temp.put(he.getEdge(), be.getEdge().getV2());
						else 
							if(!StartBindingFinalKey.containsKey(he.getEdge()))
							temp.put(he.getEdge(), be.getStartVertex()); }
						else
							if(!StartBindingFinalKey.containsKey(he.getEdge()))
							temp.put(he.getEdge(), be.getEdge().getV2());
						}
					else
						if(!StartBindingFinalKey.containsKey(he.getEdge()))
						temp.put(he.getEdge(), be.getStartVertex());

					// temp.put(be.getEdge().getV2(), he.getEdge());
					// System.out.println("These are the determined vertex1111:" + temp);
					StartBindingFinalKey.putAll(temp);
					StartBindingFinal.put(temp, new ArrayList<>());
					ProcessedVertex.add(be.getStartVertex());

				}
			}
		
	/*	for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entries()) {
			for(Entry<Edge, Vertex> sbf1:sbf.getKey().entries()) {
				if(sbf1.getValue().getNode().isURI()) {
					if(sbf1.getKey().getV1().getNode().isURI())
						sbf.getKey().replaceValues(sbf1.getKey(), sbf1.getKey().getV2());
					else	if(sbf1.getKey().getV2().getNode().isURI())
						sbf.getKey().replaceValues(sbf1.getKey(), sbf1.getKey().getV1());
				}
			}
		}*/
		for(Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> sbf:StartBindingFinal.entrySet())
			System.out.println("These are StartBindingFinal333333:" + sbf);
		
/*				for (EdgeOperator he : BindEdges) {// System.out.println("These are the determined
			// vertex:"+be.getStartVertex()+"--"+he.getEdge().getV2()+"--"+ProcessedVertex);
				if (ProcessedVertex.contains(he.getEdge().getV1())) 
					temp.put(he.getEdge(), he.getEdge().getV1());
				if (ProcessedVertex.contains(he.getEdge().getV2())) 
					for(Entry<Edge,Vertex> sbfk:StartBindingFinalKey.entrySet())
							if(sbfk.getValue().equals(he.getEdge().getV2()))
StartBindingFinalKey.putAll(temp);												
StartBindingFinal.put(temp, new ArrayList<>());

}

		temp = new HashMap<>();
		for (Entry<HashMap<Edge, Vertex>, ArrayList<Binding>> be : StartBindingFinal.entrySet())
			for (Entry<Edge, Vertex> be1 : be.getKey().entrySet())
				for (EdgeOperator he : BindEdges)
					if (he.getStartVertex().toString().equals(be1.getValue().toString())) {

						temp.put(he.getEdge(), he.getStartVertex());

					}
		StartBindingFinal.put(temp, new ArrayList<>());
		StartBindingFinalKey.putAll(temp);
*/				
//		HashMap<Edge, Vertex> Temp3 = new HashMap<>();
//		for (Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> be : StartBindingFinal.entries())
//			for (Entry<Edge, Vertex> be1 : be.getKey().entries())
//				Temp3.put(be1.getKey(), be1.getValue());

//		StartBindingFinal.clear();
//		for (Entry<Edge, Vertex> t31 : Temp3.entrySet()) {
//			LinkedListMultimap<Edge, Vertex> Temp2 =LinkedListMultimap.create();
//			Temp2.put(t31.getKey(), t31.getValue());
//			StartBindingFinal.put(Temp2, null);
//		}

	}
	public HashMap<HashSet<List<EdgeOperator>>, Integer> MatchOperators(HashSet<List<EdgeOperator>> Temp,
			HashSet<List<EdgeOperator>> Temp1) {
		HashSet<List<EdgeOperator>> xyz_current = new HashSet<>();
		HashMap<HashSet<List<EdgeOperator>>, Integer> cde = new HashMap<>();

		HashSet<List<EdgeOperator>> xyz_processed = new HashSet<>();
		int i = 0;
		int fee = 0;

//	for(EdgeOperator bo:operators_BushyTreeOrder)
//	System.out.println("This is bushytreeorder:"+bo);

		for (List<EdgeOperator> e : Temp) {

			for (List<EdgeOperator> eS : Temp1) {
				// try {
				// Thread.sleep(500);
				// } catch (InterruptedException e3) {
				// TODO Auto-generated catch block
				// e3.printStackTrace();
				// }

				for (EdgeOperator e1 : e) {
					/*
					 * try { Thread.sleep(500); } catch (InterruptedException e3) { // TODO
					 * Auto-generated catch block e3.printStackTrace(); }
					 */
					for (EdgeOperator e2 : eS)
						if (!(xyz_processed.toString().contains(e1.toString())
								|| xyz_processed.toString().contains(e2.toString()))) {
							{
								xyz_current = new HashSet<>();

								// try {
								// Thread.sleep(500);
								// } catch (InterruptedException e3) {
								// TODO Auto-generated catch block
								// e3.printStackTrace();
								// }
//				if(!e.equals(eS)) {
								if (!e1.equals(e2)) {

									{
										// try {
										// Thread.sleep(500);
										// }/ catch (InterruptedException e3) {
										// TODO Auto-generated catch block
										// e3.printStackTrace();
										// }

										if (e1.getEdge().getV1().equals(e2.getEdge().getV2())) {
											// xyz_current=new HashSet<>();

											xyz_current.add(eS);
											xyz_current.add(e);
											xyz_processed.add(e);
											xyz_processed.add(eS);

											if (!cde.containsKey(xyz_current)) {
												HashSet<List<EdgeOperator>> JoinGroupsListExclusiveTemp1 = new HashSet<>();
												for (EdgeOperator obt1 : operators_BushyTreeOrder)
													for (List<EdgeOperator> jg2 : xyz_current)
														for (EdgeOperator jg4 : jg2)
															if (jg4.getEdge().equals(obt1.getEdge()))
																JoinGroupsListExclusiveTemp1.add(jg2);
												cde.put(JoinGroupsListExclusiveTemp1, i);
											}

											fee = 1;
										}

										if (e1.getEdge().getV1().equals(e2.getEdge().getV1())) { // System.out.println("This
																									// is now current
																									// work2:"+e1.getEdge().getV1()+"--"+e2.getEdge().getV1()+"--"+e+"--"+eS);
											xyz_current.add(eS);
											xyz_current.add(e);
											if (!cde.containsKey(xyz_current)) {
												// try {
												// Thread.sleep(500);
												// } catch (InterruptedException e3) {
												// TODO Auto-generated catch block
												// e3.printStackTrace();
												// }
												HashSet<List<EdgeOperator>> JoinGroupsListExclusiveTemp1 = new HashSet<>();
												for (EdgeOperator obt1 : operators_BushyTreeOrder)
													for (List<EdgeOperator> jg2 : xyz_current)
														for (EdgeOperator jg4 : jg2)
															if (jg4.getEdge().equals(obt1.getEdge()))
																JoinGroupsListExclusiveTemp1.add(jg2);
												cde.put(JoinGroupsListExclusiveTemp1, i);

											}
											xyz_processed.add(e);
											xyz_processed.add(eS);

											// xyz_current=new HashSet<>();

											fee = 1;
										}
										if (e1.getEdge().getV2().equals(e2.getEdge().getV1())) {
											// System.out.println("This is now current
											// work3:"+e1.getEdge().getV2()+"--"+e2.getEdge().getV1()+"--"+e+"--"+eS);
											xyz_current.add(eS);
											xyz_current.add(e);
											if (!cde.containsKey(xyz_current)) {
												HashSet<List<EdgeOperator>> JoinGroupsListExclusiveTemp1 = new HashSet<>();
												for (EdgeOperator obt1 : operators_BushyTreeOrder)
													for (List<EdgeOperator> jg2 : xyz_current)
														for (EdgeOperator jg4 : jg2)
															if (jg4.getEdge().equals(obt1.getEdge()))
																JoinGroupsListExclusiveTemp1.add(jg2);
												cde.put(JoinGroupsListExclusiveTemp1, i);

											}
											xyz_processed.add(e);
											xyz_processed.add(eS);
											// xyz_current=new HashSet<>();

											fee = 1;
										}

										if (e1.getEdge().getV2().equals(e2.getEdge().getV2())) { // try {
											// Thread.sleep(500);
											// } catch (InterruptedException e3) {
											// TODO Auto-generated catch block
											// e3.printStackTrace();
											// }
//				System.out.println("This is now current work4:"+e1.getEdge().getV2()+"--"+e2.getEdge().getV2()+"--"+e+"--"+eS);
											xyz_current.add(eS);
											xyz_current.add(e);
											if (!cde.containsKey(xyz_current)) {
												HashSet<List<EdgeOperator>> JoinGroupsListExclusiveTemp1 = new HashSet<>();
												for (EdgeOperator obt1 : operators_BushyTreeOrder)
													for (List<EdgeOperator> jg2 : xyz_current)
														for (EdgeOperator jg4 : jg2)
															if (jg4.getEdge().equals(obt1.getEdge()))
																JoinGroupsListExclusiveTemp1.add(jg2);
												cde.put(JoinGroupsListExclusiveTemp1, i);

											}
											xyz_processed.add(e);
											xyz_processed.add(eS);

											// xyz_current=new HashSet<>();
											fee = 1;
										}

									}

								}
								//
								// 3 }
							} // System.out.println("Remaning Numbers:"+cde_current.size());
							/*
							 * try { Thread.sleep(500); } catch (InterruptedException e3) { // TODO
							 * Auto-generated catch block e3.printStackTrace(); }
							 */
						} // System.out.println("Remaning Numbers:"+cde_current.size());

				}

//		System.out.println("Remaning Numbers:"+cde_current);
				if (fee == 1) {
					fee = 0;
//				break;
				}
			}
			i++;
		}
		i = 0;

		return cde;
	}

	/*
	 * public HashMap<HashSet<List<EdgeOperator>>, Integer>
	 * ExecutionOrder(HashSet<List<EdgeOperator>> JoinGroupLists) { // LinkingTree
	 * HashMap<HashSet<List<EdgeOperator>>, Integer> cde = new HashMap<>();
	 * HashMap<HashSet<List<EdgeOperator>>, Integer> cdeDup = new HashMap<>();
	 * HashSet<List<EdgeOperator>> Temp = new HashSet<>();
	 * HashSet<List<EdgeOperator>> Temp1 = new HashSet<>();
	 * 
	 * // for(List<EdgeOperator> j:JoinGroupLists) //
	 * System.out.println("This is next:"+j); // for(EdgeOperator
	 * obt1:operators_BushyTreeOrder) //
	 * System.out.println("This is next222:"+obt1);
	 * 
	 * for (EdgeOperator obt1 : operators_BushyTreeOrder) for (List<EdgeOperator> ee
	 * : JoinGroupLists) if (!ee.isEmpty()) for (EdgeOperator ee1 : ee) if
	 * (ee1.toString().equals(obt1.toString())) Temp.add(ee); for (EdgeOperator obt1
	 * : operators_BushyTreeOrder) for (List<EdgeOperator> ee : JoinGroupLists) if
	 * (!ee.isEmpty()) for (EdgeOperator ee1 : ee) if
	 * (ee1.toString().equals(obt1.toString())) Temp1.add(ee);
	 * 
	 * // for(List<EdgeOperator> t:Temp) //
	 * System.out.println("This is JoinGroupLists:"+t);
	 * 
	 * cde = MatchOperators(Temp, Temp1);
	 * 
	 * HashSet<List<EdgeOperator>> notPresent = new HashSet<>();
	 * 
	 * for (List<EdgeOperator> e1 : Temp) { //
	 * System.out.println("This is the old old new list:"+e1);
	 * 
	 * notPresent.add(e1); } //
	 * System.out.println("This is the old old old list:"+notPresent); //
	 * System.out.println("This is the old old old cde list:"+cde);
	 * 
	 * for (HashSet<List<EdgeOperator>> e1 : cde.keySet()) for (List<EdgeOperator>
	 * e2 : e1) // for(EdgeOperator e3:e2) notPresent.remove(e2);
	 * 
	 * // for(HashSet<List<EdgeOperator>> cc1:cde.values())
	 * 
	 * // System.out.println("This is in old list:"+notPresent); //
	 * System.out.println("This is in old cde list:"+cde);
	 * 
	 * if (o == 1) { HashMap<HashSet<List<EdgeOperator>>, Integer> cde2 =
	 * ExecutionOrder(notPresent); int count = Collections.max(cde.values());
	 * HashSet<List<EdgeOperator>> du = new HashSet<>(); for
	 * (HashSet<List<EdgeOperator>> c : cde2.keySet()) { du.addAll(c); count++;
	 * cde.put(du, count); }
	 * 
	 * o++; } // for(Entry<HashSet<List<EdgeOperator>>, Integer>
	 * cde1:cde.entrySet()) { //
	 * System.out.println("Here is the main problem:"+cde1); // } cde.remove(null);
	 * HashSet<List<EdgeOperator>> Done = new HashSet<>(); int KeyNo = 0;
	 * cdeDup.putAll(cde); Iterator<Entry<HashSet<List<EdgeOperator>>, Integer>>
	 * Outer = cde.entrySet().iterator(); while (Outer.hasNext()) {
	 * Entry<HashSet<List<EdgeOperator>>, Integer> Outer4 = Outer.next();
	 * HashSet<List<EdgeOperator>> Outer1Value = Outer4.getKey();//
	 * Outer.next().getKey(); //s int Outer1Key= Outer.next().getValue();
	 * Iterator<List<EdgeOperator>> Outer1 = Outer1Value.iterator(); while
	 * (Outer1.hasNext()) { List<EdgeOperator> Outer2Value = Outer1.next();
	 * Iterator<List<EdgeOperator>> InnerIterator = notPresent.iterator(); while
	 * (InnerIterator.hasNext()) { List<EdgeOperator> Inner = InnerIterator.next();
	 * Iterator<EdgeOperator> Inner1Iterator = Inner.iterator(); //
	 * if(!Done.equals(Outer1Value)) // try { // Thread.sleep(500); // } catch
	 * (InterruptedException e3) { // TODO Auto-generated catch block //
	 * e3.printStackTrace(); // } while (Inner1Iterator.hasNext()) { EdgeOperator
	 * Inner1 = Inner1Iterator.next();
	 *
	 * try { Thread.sleep(500); } catch (InterruptedException e3) { // TODO
	 * Auto-generated catch block e3.printStackTrace(); }
	 *
	 * if (Outer2Value.toString().contains(Inner1.getEdge().getV1().toString()) ||
	 * Outer2Value.toString().contains(Inner1.getEdge().getV2().toString())) {
	 *
	 * try { Thread.sleep(500); } catch (InterruptedException e3) { // TODO
	 * Auto-generated catch block e3.printStackTrace(); }
	 * 
	 * HashSet<List<EdgeOperator>> Involvment = new HashSet<>();
	 * Involvment.add(Inner); // Inner.remove(Inner1); if (!Done.equals(Involvment))
	 * cdeDup.put(Involvment, Outer4.getValue()); Done.addAll(Involvment); //
	 * System.out.println("This is in final cde list:"+Outer2Value); //
	 * System.out.println("This is in final list list:"+Inner1);
	 * 
	 * // KeyNo=Outer1Key; } } } } }*
	 *
	 * Iterator<Entry<HashSet<List<EdgeOperator>>, Integer>> cde1 =
	 * cde.entrySet().iterator(); while(cde1.hasNext()) {
	 * HashSet<List<EdgeOperator>> cde1Value = cde1.next().getKey();
	 * Iterator<Entry<HashSet<List<EdgeOperator>>, Integer>> cde21=
	 * cde.entrySet().iterator(); while(cde21.hasNext()) {
	 * HashSet<List<EdgeOperator>> cde2Value = cde21.next().getKey();
	 * if(cde1Value.equals(cde2Value)) cde.remove(cde1Value);
	 * 
	 * } }
	 * 
	 * 
	 * HashSet<HashMap<HashSet<List<EdgeOperator>>, Integer>> cdehash = new
	 * HashSet<>();
	 * 
	 * LinkedHashMap<HashSet<List<EdgeOperator>>, Integer> CdeUnOrder = new
	 * LinkedHashMap<>();
	 * 
	 * for (HashSet<List<EdgeOperator>> cde9 : cdeDup.keySet()) //
	 * for(List<EdgeOperator> cde10:cde9) //
	 * System.out.println("This is really bad:"+cde10); for
	 * (HashSet<List<EdgeOperator>> cde1 : cdeDup.keySet()) { if (!cde1.isEmpty() ||
	 * cde1 != null || !(cde1.size() == 0)) CdeUnOrder.put(cde1, cde1.size()); //
	 * System.out.println("This is HashSet Size:"+cde1.size()+"--"+cde1); }
	 * 
	 * LinkedHashMap<HashSet<List<EdgeOperator>>, Integer> CdeOrder = new
	 * LinkedHashMap<>(); // System.out.println("This is hash hash //
	 * hash:"+CdeUnOrder.values().toArray()[0]); int size = 0; if
	 * (CdeUnOrder.values().toArray()[0] != null) size = (int)
	 * CdeUnOrder.values().toArray()[0]; else size = (int)
	 * CdeUnOrder.values().toArray()[1]; if (size == 1) CdeOrder =
	 * CdeUnOrder.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.
	 * comparingByValue())) .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
	 * (e1, e2) -> e2, LinkedHashMap::new)); else CdeOrder.putAll(CdeUnOrder);
	 * 
	 * // for(Entry<HashSet<List<EdgeOperator>>, Integer> cde1:CdeOrder.entrySet())
	 * { // CdeUnOrder.put(cde1.size(), cde1);
	 * 
	 * // System.out.println("This is ordered HashSet Size:"+cde1); // }
	 * cdehash.add(CdeOrder);
	 * 
	 * LinkedHashMap<HashSet<List<EdgeOperator>>, Integer> cdeSemiFinal = new
	 * LinkedHashMap<>(); for (HashMap<HashSet<List<EdgeOperator>>, Integer> cdeh :
	 * cdehash) cdeSemiFinal.putAll(cdeh);
	 * 
	 * LinkedHashMap<HashSet<List<EdgeOperator>>, Integer> cdeFinal = new
	 * LinkedHashMap<>();
	 * 
	 * for (HashSet<List<EdgeOperator>> co : CdeOrder.keySet()) { for
	 * (Entry<HashSet<List<EdgeOperator>>, Integer> cdeh : cdeSemiFinal.entrySet())
	 * { if (co.equals(cdeh.getKey())) {
	 * 
	 * cdeFinal.put(cdeh.getKey(), cdeh.getValue()); } } }
	 * 
	 * // System.out.println("This is in final cdeFinal:"+cdeFinal);
	 * linkingTreeDup.putAll(cdeFinal); return cdeFinal; }
	 */
	/*
	 * public static Set<Binding> finalResult(LinkedHashMap<EdgeOperator,
	 * Set<Binding>> finalRes) { ArrayList<Set<Binding>> finalResultQueryOrder = new
	 * ArrayList<>();
	 * 
	 * ////logger.info("aaaaaaaaaa111:"+UnProcessedSets.parallelStream().limit(1).
	 * collect(Collectors.toSet()));
	 * 
	 * Set<EdgeOperator> fresults= finalRes.keySet();
	 * //for(com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator
	 * fr:fresults)
	 * ////logger.info("These are now the ordered by similarity of words:"+fr);
	 * 
	 * 
	 * 
	 * //for (Entry<EdgeOperator, Set<Binding>> eq:finalRes.entrySet())
	 * //if(eq.getValue()!=null) // if(eq.getKey().toString().
	 * equals("Bind join: (?place: 0 true)--(?place: 0 true) - (?longitude: 0 true)"
	 * )) //for(BindingSet eq11:eq.getValue()) // {//logger.
	 * info("THis is currently the entering part B6 segment123123123123123:"+eq.
	 * getKey()+"--"+eq.getValue().size());
	 * 
	 * 
	 * //} for(EdgeOperator fr:fresults) for (Entry<EdgeOperator, Set<Binding>>
	 * eq2:finalRes.entrySet()) if(eq2.getValue()!=null) if(fr!=null )
	 * if(fr.toString().equals(eq2.getKey().toString()) && eq2.getValue().size()>0)
	 * { //
	 * //logger.info("THis is one last thing left:"+fr+"--"+eq2.getValue().size());
	 * finalResultQueryOrder.add(eq2.getValue()); }
	 * System.out.println("This is the size:"+fresults.size()+"--"+finalRes.size()+
	 * "--"+LocalTime.now()); UnProcessedSets.addAll(finalResultQueryOrder);
	 * //for(Set<Binding> ek:finalResultQueryOrder) // //logger.
	 * info("THis is currently the entering part B6 segment123123123123123:"+"--"+ek
	 * .size()); // finalResultQueryOrder.notifyAll();
	 * 
	 * return finalResultCalculation(finalResultQueryOrder); }
	 */

	public static ArrayList<Binding> GPUJoin(Collection<Binding> left, Collection<Binding> right,
			Collection<Binding> left2, Collection<Binding> right2, int type,String string) {
		iteration++;
		type1 = type;
		int a = 0;

		ArrayList<String> headersAllLeft = new ArrayList<>();
		ArrayList<String> headersAllRight = new ArrayList<>();
		List<String> headersAllLeft1 = new ArrayList<>();
		List<String> headersAllRight1 = new ArrayList<>();
//	ArrayList<String> headersAll= new ArrayList<>();
		int IsLeft = 0;
// if(ParaEng.pConstant.contains("?p"))
		System.out.println(
				"This is::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
						+ ParaEng.pConstant);
// for(BindingSet l:left)
//	 System.out.println("This is left side:"+l);

		// for(BindingSet l:right)
//	 System.out.println("This is right side:"+l);
		rightTable = null;
		leftTable = null;
		String length = "";

		String[] vv = null;
//	final String[][]	rTable;

		System.out.println("These are all the headers:" + headersAll);
		for(Entry<HashMap<Vertex, String>, Set<Binding>> sj:BGPEval.StartBinding123Large.entrySet())
			System.out.println("This is bindJoin with value after headers:"+sj.getKey()+"--"+sj.getValue().parallelStream().limit(4).collect(Collectors.toList()));

		System.out.println("This is the size of left:" + left.size()+"--"+left.parallelStream().limit(1).collect(Collectors.toList()));

		System.out.println("This is the size of right:" + right.size()+"--"+right.parallelStream().limit(1).collect(Collectors.toList()));

		int incL = 0;

// System.out.println("These are the optional Hedaers:"+OptionalHeaders);
// System.out.println("These are the optional Hedaers2:"+headersAllRight);
		// System.out.println("These are the optional Hedaers3:"+headersAllLeft);
		// System.out.println("These are the optional Hedaers4:"+OptionalHeadersRight);
		// System.out.println("These are the optional Hedaers5:"+OptionalHeadersLeft);

		// String[]
		vv = null;
//String 

		length = "";
		Iterator<Binding> l2 = left.iterator();
		while (l2.hasNext()) {
			length = l2.next().toString();
			vv = length.split(" ");
			for (String v : vv)
				if (v.startsWith("?")) {
					headersAllLeft.add(v.substring(1));
					headersAllLeft1.add(v.substring(1));
//headersAll.add(v.substring(1));
//break;
				}
			break;
		}
		// Iterator<Binding> l2 =left.iterator();

		// l2 = left.iterator();
		/*
		 * while(l2.hasNext()) {
		 * 
		 * 
		 * Pattern regex =
		 * Pattern.compile(";(?=[a-zA-Z0-9])(?![$-:-?{-~!\"^_\\-`\\[\\]])"); String lll
		 * = l2.next().toString().replace("?", "");
		 * System.out.println("This is left pattern:"+lll); String[] regexMatcher =
		 * regex.split(lll); for(String ml:regexMatcher) {
		 * headersAllLeft.add("?"+ml.substring(0,ml.indexOf('=')).replace("]","").
		 * replace("[","")); }
		 * 
		 * 
		 * break; }
		 */

		for (String hla : headersAllLeft)
			System.out.println("This is headersAllLeft:" + hla);
		for (String hla : headersAllRight)
			System.out.println("This is headersAllRight:" + hla);

		incL = 0;
		for (String hla : headersAllLeft)
			if (headersAll.contains(hla))
				incL++;
		if (incL == 0) {
			for (String hla : headersAllLeft)
				if (headersAll.contains(hla))
					incL++;
		}

		// String[]
		vv = null;
		// String

		length = "";
		Iterator<Binding> l21 = right.iterator();
		while (l21.hasNext()) {
			length = l21.next().toString();
			vv = length.split(" ");
			for (String v : vv)
				if (v.startsWith("?")) {
					headersAllRight.add(v.substring(1));
					headersAllRight1.add(v.substring(1));
					// headersAll.add(v.substring(1));
					// break;
				}
			break;
		}
		// ExecutorService fjp = Executors.newCachedThreadPool();
//	System.out.println("This is time1:"+LocalTime.now());      
		/*
		 * for(Entry<String, String> a:ParaEng.pConstant.entrySet())
		 * if(headersAllRight.contains(a.getValue().substring(1)) &&
		 * headersAllLeft.contains(a.getKey().substring(1))) { //
		 * headersAllLeft.remove(a.getKey().substring(1)); //
		 * headersAllLeft.add(a.getValue().substring(1)); int index =
		 * headersAllLeft.indexOf(a.getKey().substring(1)); headersAllLeft.set(index,
		 * a.getValue().substring(1)); }
		 * 
		 * for(Entry<String, String> a:ParaEng.pConstant.entrySet())
		 * if(headersAllLeft.contains(a.getValue().substring(1)) &&
		 * headersAllRight.contains(a.getKey().substring(1))) { //
		 * headersAllRight.remove(a.getKey().substring(1)); int index =
		 * headersAllRight.indexOf(a.getKey().substring(1)); headersAllRight.set(index,
		 * a.getValue().substring(1)); }
		 */
		Collections.sort(headersAllRight1); 
		Collections.sort(headersAllLeft1); 
		System.out.println("These are headersAllLeft:" + headersAllLeft1);
		System.out.println("These are headersAllRight:" + headersAllRight1);
		System.out.println("These are headersAll Optional:" + OptionalHeaders);
		System.out.println("These are headersAllLeft Optional:" + OptionalHeadersLeft);
		System.out.println("These are headersAllRight Optional:" + OptionalHeadersRight);
		int hlsize = 0;
	int hrsize = 0;
	int hesize = 0;

	if (headersAllRight.size() > headersAllLeft.size()) {
		for (String hl : headersAllLeft)
			if (headersAllRight.contains(hl))
				hlsize++;
	}
	if (headersAllRight.size() < headersAllLeft.size()) {
		for (String hr : headersAllRight)
			if (headersAllLeft.contains(hr))
				hrsize++;
	}
	if (headersAllRight.size() == headersAllLeft.size()) {
		for (String hr : headersAllRight)
			if (headersAllLeft.contains(hr))
				hesize++;
	}
	if (hesize == 1)
		hesize = 0;
	if (hrsize == 1)
		hrsize = 0;
	if (hlsize == 1)
		hlsize = 0;
if(type!=2) {
	if (hesize == headersAllRight.size() && headersAllRight.size() == headersAllLeft.size())
		return null;

	if (hrsize == headersAllRight.size())
		return null;
	if (hlsize == headersAllLeft.size())
		return null;
}
	int incR = 0;
	for (String hla : headersAllRight)
		if (headersAll.contains(hla))
			incR++;

		
		/*

		int skip = 0;
		
		*/
	System.out.println("This is now the personal space");
	if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty())) {
			/*incL = 0;

			for (String hla : headersAllLeft)
				if (OptionalHeaders.contains(hla))
					incL++;

			if (skip == 0)
				if (OptionalHeaders != null && OptionalHeaders.size() > 0)
					if (OptionalHeaders.size() == incL) {
						System.out.println("These are right1:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));

						skip = 1;
						System.out.println("These are right:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));
						// leftTable=TransformForGPU(right,right2);
						// ForkJoinPool fjp1 = new ForkJoinPool();
						// fjp1.submit(()->{ Stream.of(
						leftTable = TransformForGPU(right, null, headersAllRight);// ).parallel();}).join();
						// fjp1.shutdown();

						System.out.println("These are right result:"
								+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));

						System.out.println("These are left:" + left.size() + "--"
								+ left.parallelStream().limit(2).collect(Collectors.toSet()));
						// rightTable=TransformForGPU(left,left2);
						// ForkJoinPool fjp = new ForkJoinPool();
						// fjp.submit(()->{ Stream.of(
						rightTable = TransformForGPU(left, null, headersAllLeft);// ).parallel();}).join();
//	fjp.shutdownNow();

						System.out.println("These are left result:"
								+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));

					}

			incL = 0;
			for (String hla : headersAllRight)
				if (OptionalHeaders.contains(hla))
					incL++;

			if (skip == 0)
				if (OptionalHeaders != null && OptionalHeaders.size() > 0)
					if (OptionalHeaders.size() == incL) {
						System.out.println("These are right2:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));

						System.out.println("These are left:" + left.size() + "--"
								+ left.parallelStream().limit(2).collect(Collectors.toSet()));
						// leftTable=TransformForGPU(left,left2);
						// ForkJoinPool fjp = new ForkJoinPool();
//	fjp.submit(()->{ Stream.of(
						leftTable = TransformForGPU(left, null, headersAllLeft);// ).parallel();}).join();
						// fjp.shutdownNow();

						System.out.println("These are left result:"
								+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));
						skip = 1;
						System.out.println("These are right:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));
						// rightTable=TransformForGPU(right,right2);
//		ForkJoinPool fjp1 = new ForkJoinPool();
						// fjp1.submit(()->{ Stream.of(rightTable=
						TransformForGPU(right, null, headersAllRight);// ).parallel();}).join();
						// fjp1.shutdown();

						System.out.println("These are right result:"
								+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));

					}

			incL = 0;
			for (String hla : headersAllLeft)
				if (OptionalHeadersRight.contains(hla))
					incL++;

			if (skip == 0)
				if (OptionalHeadersRight != null && OptionalHeadersRight.size() > 0)
					if (OptionalHeadersRight.size() == incL) {
						System.out.println("These are right3:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));

						System.out.println("These are right:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));
						// leftTable=TransformForGPU(right,right2);
						ForkJoinPool fjp1 = new ForkJoinPool();
						// fjp1.submit(()->{ Stream.of(
						leftTable = TransformForGPU(right, null, headersAllRight);// ).parallel();}).join();
						// fjp1.shutdown();

						System.out.println("These are right result:"
								+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));
						skip = 1;
						System.out.println("These are left:" + left.size() + "--"
								+ left.parallelStream().limit(2).collect(Collectors.toSet()));
//	ForkJoinPool fjp = new ForkJoinPool();
						// fjp.submit(()->{ Stream.of(
						rightTable = TransformForGPU(left, null, headersAllLeft);// ).parallel();}).join();
						// fjp.shutdownNow();
						System.out.println("These are left result:"
								+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));

					}

			incL = 0;
			for (String hla : headersAllRight)
				if (OptionalHeadersRight.contains(hla))
					incL++;

			if (skip == 0)

				if (OptionalHeadersRight != null && OptionalHeadersRight.size() > 0)
					if (OptionalHeadersRight.size() == incL) {
						System.out.println("These are right4:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));

						System.out.println("These are left:" + left.size() + "--"
								+ left.parallelStream().limit(2).collect(Collectors.toSet()));
						// ForkJoinPool fjp1 = new ForkJoinPool();
						// fjp1.submit(()->{ Stream.of(
						leftTable = TransformForGPU(left, null, headersAllLeft);// ).parallel();}).join();
						// fjp1.shutdown();
						System.out.println("These are left result:"
								+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));
						skip = 1;
						System.out.println("These are right:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));
						// ForkJoinPool fjp = new ForkJoinPool();
						// fjp.submit(()->{ Stream.of(
						rightTable = TransformForGPU(right, null, headersAllRight);// ).parallel();}).join();
						// fjp.shutdownNow();

						System.out.println("These are right result:"
								+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));

					}

			incL = 0;
			for (String hla : headersAllLeft)
				if (OptionalHeadersLeft.contains(hla))
					incL++;

			if (skip == 0)

				if (OptionalHeadersLeft != null && OptionalHeadersLeft.size() > 0)
					if (OptionalHeadersLeft.size() == incL) {
						System.out.println("These are right5:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));

						System.out.println("These are right:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));
//		ForkJoinPool fjp1 = new ForkJoinPool();
						// fjp1.submit(()->{ Stream.of(
						leftTable = TransformForGPU(right, null, headersAllRight);// ).parallel();}).join();
						// fjp1.shutdown();
						System.out.println("These are right result:"
								+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));

						skip = 1;
						System.out.println("These are left:" + left.size() + "--"
								+ left.parallelStream().limit(2).collect(Collectors.toSet()));
						// ForkJoinPool fjp = new ForkJoinPool();
						// fjp.submit(()->{ Stream.of(
						rightTable = TransformForGPU(left, null, headersAllLeft);// );//.parallel();}).join();
						// fjp.shutdownNow();
						System.out.println("These are left result:"
								+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));

					}

			incL = 0;
			for (String hla : headersAllRight)
				if (OptionalHeadersLeft.contains(hla))
					incL++;

			if (skip == 0)
				if (OptionalHeadersLeft != null && OptionalHeadersLeft.size() > 0)
					if (OptionalHeadersLeft.size() == incL) {
						System.out.println("These are right6:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));
						// leftTable=TransformForGPU(left,left2);
//	ForkJoinPool fjp1 = new ForkJoinPool();
//	fjp1.submit(()->{ Stream.of(
						leftTable = TransformForGPU(left, null, headersAllLeft);// ).parallel();}).join();
//	fjp1.shutdown();

						System.out.println("These are left result:"
								+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));
						skip = 1;
						System.out.println("These are right:" + right.size() + "--"
								+ right.parallelStream().limit(2).collect(Collectors.toSet()));
						// rightTable=TransformForGPU(right,right2);
//	ForkJoinPool fjp = new ForkJoinPool();
//	fjp.submit(()->{ Stream.of(
						rightTable = TransformForGPU(right, null, headersAllRight);// ).parallel();}).join();
//	fjp.shutdownNow();

						System.out.println("These are right result:"
								+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));

					}
		*/
			int isOptionalLeft=0;
			int isOptionalRight=0;
			//for(Set<String> oh:OptionalHeaders)
				if(OptionalHeaders.contains(headersAllLeft1))
			//	if(headersAllLeft1.equals(oh.parallelStream().collect(Collectors.toList())))
					isOptionalLeft=1;
			if(!OptionalHeadersLeft.isEmpty())
			if(OptionalHeadersLeft.contains(headersAllLeft1))
				isOptionalLeft=1;
			if(!OptionalHeadersRight.isEmpty())
				if(OptionalHeadersRight.contains(headersAllLeft1))
					isOptionalLeft=1;
		
			if(OptionalHeaders.contains(headersAllRight1))
					isOptionalRight=1;
			if(!OptionalHeadersLeft.isEmpty())
				if(OptionalHeadersLeft.contains(headersAllRight1))
					isOptionalRight=1;
				if(!OptionalHeadersRight.isEmpty())
					if(OptionalHeadersRight.contains(headersAllRight1))
						isOptionalRight=1;
				
				
		//	for(Set<String> oh:OptionalHeaders)
			if( isOptionalLeft==1 )
			{
				System.out.println("These are right60:" + right.size() + "--"
						+ right.parallelStream().limit(2).collect(Collectors.toSet()));
				// leftTable=TransformForGPU(left,left2);
//ForkJoinPool fjp1 = new ForkJoinPool();
//fjp1.submit(()->{ Stream.of(
				rightTable = TransformForGPU(left, null, headersAllLeft,type);// ).parallel();}).join();
//fjp1.shutdown();

				System.out.println("These are left result:"
						+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));
				System.out.println("These are right:" + right.size() + "--"
						+ right.parallelStream().limit(2).collect(Collectors.toSet()));
				// rightTable=TransformForGPU(right,right2);
//ForkJoinPool fjp = new ForkJoinPool();
//fjp.submit(()->{ Stream.of(
				leftTable = TransformForGPU(right, null, headersAllRight,type);// ).parallel();}).join();
//fjp.shutdownNow();

				System.out.println("These are right result:"
						+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));
			}	

			else if( isOptionalRight==1)
			{
				System.out.println("These are right61:" + right.size() + "--"
						+ right.parallelStream().limit(2).collect(Collectors.toSet()));
				// leftTable=TransformForGPU(left,left2);
//ForkJoinPool fjp1 = new ForkJoinPool();
//fjp1.submit(()->{ Stream.of(
				leftTable = TransformForGPU(left, null, headersAllLeft,type);// ).parallel();}).join();
//fjp1.shutdown();

				System.out.println("These are left result:"
						+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));
				System.out.println("These are right:" + right.size() + "--"
						+ right.parallelStream().limit(2).collect(Collectors.toSet()));
				// rightTable=TransformForGPU(right,right2);
//ForkJoinPool fjp = new ForkJoinPool();
//fjp.submit(()->{ Stream.of(
				rightTable = TransformForGPU(right, null, headersAllRight,type);// ).parallel();}).join();
//fjp.shutdownNow();

				System.out.println("These are right result:"
						+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));
			}
			
			else {

				System.out.println("These are right61:" + right.size() + "--"
						+ right.parallelStream().limit(2).collect(Collectors.toSet()));
				// leftTable=TransformForGPU(left,left2);
//ForkJoinPool fjp1 = new ForkJoinPool();
//fjp1.submit(()->{ Stream.of(
				leftTable = TransformForGPU(left, null, headersAllLeft,type);// ).parallel();}).join();
//fjp1.shutdown();

				System.out.println("These are left result:"
						+ leftTable.parallelStream().limit(2).collect(Collectors.toSet()));
				System.out.println("These are right:" + right.size() + "--"
						+ right.parallelStream().limit(2).collect(Collectors.toSet()));
				// rightTable=TransformForGPU(right,right2);
//ForkJoinPool fjp = new ForkJoinPool();
//fjp.submit(()->{ Stream.of(
				rightTable = TransformForGPU(right, null, headersAllRight,type);// ).parallel();}).join();
//fjp.shutdownNow();

				System.out.println("These are right result:"
						+ rightTable.parallelStream().limit(2).collect(Collectors.toSet()));
				
				
			}
		}
		else {
		System.out.println("THis is yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
		
				System.out.println("These are right:" + right.size() + "--"
						+ right.parallelStream().limit(2).collect(Collectors.toSet()));
				// ForkJoinPool fjp = new ForkJoinPool();
				// fjp.submit(()->{
				// ArrayList<String> input2 =new ArrayList<>();
				// boolean correct = str.matches("[-+]?[0-9]*\\.?[0-9]+");
				// if(QueryTask.isBound==1)
				/*
				 * while(r.hasNext()) { String a1;
				 * //if(r.next().toString().matches("[-+]?[0-9]*\\.?[0-9]+"))
				 * a1=String.valueOf(r.next());// Float.toString(r.next()); // else //
				 * a1=r.next().toString();
				 * 
				 * System.out.println("This is in GPU Join:"+a1); input2.add(a1); //a1++; }
				 */
				System.out.println("This is going into function");
				ForkJoinPool fjp = new ForkJoinPool();
				fjp.submit(()->rightTable = TransformForGPU(right, null, headersAllRight,0)).join();
				 fjp.shutdownNow();
				System.out.println(
						"These are right result:" + rightTable.parallelStream().limit(2).collect(Collectors.toSet()));

				// for(BindingSet l:left)
				// System.out.println("These are
				// left:"+l);//left.size()+"--"+left.parallelStream().limit(2).collect(Collectors.toSet()));
				/*
				 * List<String >input1 = new ArrayList<>(); if(QueryTask.isBound==1)
				 * while(l.hasNext()) { String a1=l.next().toString(); //
				 * System.out.println("This is in GPU Join:"+a1); input1.add(a1); //a1++; }
				 */

				System.out.println("These are left:" + left.size() + "--"
						+ left.parallelStream().limit(2).collect(Collectors.toSet()));
				// ForkJoinPool fjp1 = new ForkJoinPool();
				// fjp1.submit(()->{
				ForkJoinPool fjp1 = new ForkJoinPool();
				fjp1.submit(()->leftTable = TransformForGPU(left, null, headersAllLeft,0)).join();
				 fjp1.shutdownNow();
				;// }).join();
				// fjp1.shutdown();
				System.out.println(
						"These are left result:" + leftTable.parallelStream().limit(2).collect(Collectors.toSet()));

			}
  
		int isAddition = 0;
		for (String s : HeaderReplacement.keySet())
			if (headersAll.contains(s))
				isAddition = 1;
		if (headersAll.size() == incL && isAddition == 0)
			return null;

		if (headersAll.size() == incR && isAddition == 0)
			return null;

		List<String[]> leftCsv = new ArrayList<String[]>();
		/*
		 * for (int i = 0; i < leftTable.length; i++) { String[] temp = new
		 * String[leftTable.get(0).size()]; for (int n = 0; n < temp.length; n++) {
		 * System.out.print(" "+leftTable[i][n]); } System.out.println(); //
		 * leftCsv.add(temp); }
		 */
		if ((leftTable.get(0).size() == 1 && rightTable.get(0).size() > 1)
				|| (rightTable.get(0).size() == 1 && leftTable.get(0).size() > 1)) {
			if (leftTable.get(0).size() == 1 && leftTable.size() > 5)
				type = 4;
			if (rightTable.get(0).size() == 1 && rightTable.size() > 5)
				type = 4;
		}
		if(type!=1) {
		if ((leftTable.get(0).size() == 2 && rightTable.get(0).size() > 2)
				|| (rightTable.get(0).size() == 2 && leftTable.get(0).size() > 2)) {
			if (leftTable.get(0).size() == 2 && leftTable.size() > 5)
				if (leftTable.get(0).get(1) == null)
					type = 4;
			if (rightTable.get(0).size() == 2 && rightTable.size() > 5)
				if (rightTable.get(0).get(1) == null)
					type = 4;
		}
		}
		resultSetList = new ArrayList<>();
	//	for(	Entry<Vertex, Set<Binding>> eh:BGPEval.StartBinding123.entrySet())
			System.out.println("This is timing before python process:"+leftTable.size()+"--"+rightTable.size());

		//if (leftTable.size() <= 100000 && rightTable.size() <= 100000) {
			HashMap<String, ResultSet> resultSet = new HashMap<>();
			resultSet = pythonProcess(leftTable, rightTable, type, 0);
			System.out.println("This is true logic before:"+LocalTime.now());
			
			for (Entry<String, ResultSet> rs : resultSet.entrySet()) {
			
				resultoutput = postProcess(rs, 0,string);
				System.out.println("This is true logic:"+LocalTime.now());
				break;
			}
	//	}
		
	//	for(	Entry<Vertex, Set<Binding>> eh:BGPEval.StartBinding123.entrySet())
	//		System.out.println("This is timing after python process:"+eh);

		System.out.println("This is true logic11111111111111111111:"+LocalTime.now());
		
		// ForkJoinPool fjp = new ForkJoinPool();
		// fjp.submit(() -> {

		// }//).join();

//		if(leftTable.size()>150000 && rightTable.size()<=150000)
//		{

//	int	ltsize=	leftTable.size();
//int partition=ltsize/40;
//			for(int ij1=1;ij1<leftTable.size();ij1+=partition)

		// {
		// lt = new ArrayList<>();
		// List<List<String>> lt = new ArrayList<>();

		// if(ltsize>50000)
		// lt=leftTable.subList(ij1, Math.min(ij1 + partition, leftTable.size()));
		// else
		// lt=leftTable.subList(ij1, leftTable.size());

		// ltsize=ltsize-lt.size();
		// System.out.println("This is the binding in intermediate within
		// call:"+lt.size()+"--"+ij1+"--"+(ltsize)+"--"+leftTable.size());

		// Callable<Integer> c = new Callable<Integer>() {
		// @Override
		//////// public Integer call() throws Exception {
	/*	if (leftTable.size() > 100000 && rightTable.size() <= 100000) {
			resultoutput = new ArrayList<>();
			HashMap<String, ResultSet> resultSet = new HashMap<>();
			List<ResultSet> resultSet1 = new ArrayList<>();
			List<List<String>> leftTable1 = new ArrayList<>();
			leftTable1.addAll(leftTable);
			// leftTable1.addAll(lt);
			ForkJoinPool fjp = new ForkJoinPool();
			HashMap<String, ResultSet> rs = new HashMap<>();
			try {
				rs = fjp.submit(() -> pythonProcess(leftTable1, rightTable, type1, 0)).get();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("This is going in3:" + resultSet.size());
			for (Entry<String, ResultSet> rr : rs.entrySet())
				resultSet.put(rr.getKey(), rr.getValue());
			System.out.println("This is going in4:" + resultSet.size());

			fjp.shutdown();
			// System.out.println("This is going in:"+resultSet.size());

			// if(iteration==2) {
			// resultSet1.addAll(rs1);
			// for(ResultSet rs2:resultSet1)
			// while(rs2.hasNext())
			// System.out.println("This is going in:"+rs2.nextBinding());
//						}
			// for(ResultSet rr:resultSet)
			// System.out.println("This is going in resultset:"+rr.);

			HashMap<String, ResultSet> rSet1 = resultSet;
			// List<ResultSet> rSet2 = resultSet.subList(200, 400);
			// List<ResultSet> rSet3 = resultSet.subList(400, 600);
			// List<ResultSet> rSet4 = resultSet.subList(600, 800);
			// List<ResultSet> rSet5 = resultSet.subList(800, 1000);
			// List<ResultSet> rSet6 = resultSet.subList(1000, 1200);
			// List<ResultSet> rSet7 = resultSet.subList(1200, 1400);
			// List<ResultSet> rSet8 = resultSet.subList(1400, 1600);
			// List<ResultSet> rSet9 = resultSet.subList(1600, 1800);
			// List<ResultSet> rSet10 = resultSet.subList(1800, 2000);

			// List<ResultSet> rSet2 =
			// resultSet.subList(resultSet.size()/2,resultSet.size());
			resultSetThread(rSet1,string);
			for (Entry<String, ResultSet> r : resultSet.entrySet())
				resultoutput.addAll(postProcess(r, 1,string));
			System.out.println("This is rSet1:" + resultoutput.size());
			*
			 * resultSetThread(rSet2);
			 * System.out.println("This is rSet2:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet3);
			 * System.out.println("This is rSet3:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet4);
			 * System.out.println("This is rSet4:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet5);
			 * System.out.println("This is rSet5:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet6);
			 * System.out.println("This is rSet6:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet7);
			 * System.out.println("This is rSet7:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet8);
			 * System.out.println("This is rSet8:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet9);
			 * System.out.println("This is rSet9:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet10);
			 * System.out.println("This is rSet10:"+resultoutput.size());
			 *
			*
			 * List<Callable<ArrayList<Binding>>> tasks1 = new
			 * ArrayList<Callable<ArrayList<Binding>>>(); ScheduledExecutorService exec1 =
			 * Executors.newSingleThreadScheduledExecutor(); for (ResultSet r : rSet2) {
			 * 
			 * Callable<ArrayList<Binding>> c1 = new Callable<ArrayList<Binding>>() {
			 * 
			 * @Override public ArrayList<Binding> call() throws Exception { //
			 * System.out.println("This is going in:"+resultoutput.size()+"--"+r);
			 * ArrayList<Binding> bb = postProcess(r, 0); ;
			 * System.out.println("This is going out1:"+bb.size());
			 * 
			 * // System.out.println("This is going out:"+bb);
			 * 
			 * // return resultoutput.addAll(postProcess(r,1)); return bb;
			 * 
			 * // return 1; } }; tasks1.add(c1); }
			 * 
			 * // }
			 * 
			 * try { List<Future<ArrayList<Binding>>> results = exec.invokeAll(tasks); for
			 * (Future<ArrayList<Binding>> r : results) try { int j = 0;
			 * 
			 * resultoutput.addAll(r.get());
			 * 
			 * // for(Binding fr:resultoutput) //
			 * {System.out.println("These are the bindings in fr:"+fr); // if(j==10) //
			 * break; // j++;} } catch (ExecutionException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }// finally {
			 * exec.shutdown(); //} // }
			 *
			System.out.println("This is is resultSet left:" + resultoutput.size());

		}

		if (rightTable.size() > 100000 && leftTable.size() <= 100000) {
			resultoutput = new ArrayList<>();
			HashMap<String, ResultSet> resultSet = new HashMap<>();
			List<ResultSet> resultSet1 = new ArrayList<>();
			List<List<String>> leftTable1 = new ArrayList<>();
			leftTable1.addAll(rightTable);
			// leftTable1.addAll(lt);
			ForkJoinPool fjp = new ForkJoinPool();
			HashMap<String, ResultSet> rs = new HashMap<>();
			try {
				rs = fjp.submit(() -> pythonProcess(leftTable1, leftTable, type1, 1)).get();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("This is going in3:" + resultSet.size());
			for (Entry<String, ResultSet> rr : rs.entrySet())
				resultSet.put(rr.getKey(), rr.getValue());
			System.out.println("This is going in4:" + resultSet.size());

			fjp.shutdown();
			// System.out.println("This is going in:"+resultSet.size());

			// if(iteration==2) {
			// resultSet1.addAll(rs1);
			// for(ResultSet rs2:resultSet1)
			// while(rs2.hasNext())
			// System.out.println("This is going in:"+rs2.nextBinding());
//						}
			// for(ResultSet rr:resultSet)
			// System.out.println("This is going in resultset:"+rr.);

			HashMap<String, ResultSet> rSet1 = resultSet;
			// List<ResultSet> rSet2 = resultSet.subList(200, 400);
			// List<ResultSet> rSet3 = resultSet.subList(400, 600);
			// List<ResultSet> rSet4 = resultSet.subList(600, 800);
			// List<ResultSet> rSet5 = resultSet.subList(800, 1000);
			// List<ResultSet> rSet6 = resultSet.subList(1000, 1200);
			// List<ResultSet> rSet7 = resultSet.subList(1200, 1400);
			// List<ResultSet> rSet8 = resultSet.subList(1400, 1600);
			// List<ResultSet> rSet9 = resultSet.subList(1600, 1800);
			// List<ResultSet> rSet10 = resultSet.subList(1800, 2000);

			// List<ResultSet> rSet2 =
			// resultSet.subList(resultSet.size()/2,resultSet.size());
			resultSetThread(rSet1,string);
			for (Entry<String, ResultSet> r : resultSet.entrySet())
				resultoutput.addAll(postProcess(r, 1,string));
			System.out.println("This is rSet1:" + resultoutput.size());
			*
			 * resultSetThread(rSet2);
			 * System.out.println("This is rSet2:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet3);
			 * System.out.println("This is rSet3:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet4);
			 * System.out.println("This is rSet4:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet5);
			 * System.out.println("This is rSet5:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet6);
			 * System.out.println("This is rSet6:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet7);
			 * System.out.println("This is rSet7:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet8);
			 * System.out.println("This is rSet8:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet9);
			 * System.out.println("This is rSet9:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet10);
			 * System.out.println("This is rSet10:"+resultoutput.size());
			 *
			*
			 * List<Callable<ArrayList<Binding>>> tasks1 = new
			 * ArrayList<Callable<ArrayList<Binding>>>(); ScheduledExecutorService exec1 =
			 * Executors.newSingleThreadScheduledExecutor(); for (ResultSet r : rSet2) {
			 * 
			 * Callable<ArrayList<Binding>> c1 = new Callable<ArrayList<Binding>>() {
			 * 
			 * @Override public ArrayList<Binding> call() throws Exception { //
			 * System.out.println("This is going in:"+resultoutput.size()+"--"+r);
			 * ArrayList<Binding> bb = postProcess(r, 0); ;
			 * System.out.println("This is going out1:"+bb.size());
			 * 
			 * // System.out.println("This is going out:"+bb);
			 * 
			 * // return resultoutput.addAll(postProcess(r,1)); return bb;
			 * 
			 * // return 1; } }; tasks1.add(c1); }
			 * 
			 * // }
			 * 
			 * try { List<Future<ArrayList<Binding>>> results = exec.invokeAll(tasks); for
			 * (Future<ArrayList<Binding>> r : results) try { int j = 0;
			 * 
			 * resultoutput.addAll(r.get());
			 * 
			 * // for(Binding fr:resultoutput) //
			 * {System.out.println("These are the bindings in fr:"+fr); // if(j==10) //
			 * break; // j++;} } catch (ExecutionException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }// finally {
			 * exec.shutdown(); //} // }
			 *
			System.out.println("This is is resultSet right:" + resultoutput.size());

		}

		if (rightTable.size() > 100000 && leftTable.size() > 100000) {
			resultoutput = new ArrayList<>();
			HashMap<String, ResultSet> resultSet = new HashMap<>();
			List<ResultSet> resultSet1 = new ArrayList<>();
			List<List<String>> leftTable1 = new ArrayList<>();
			HashMap<String, ResultSet> rs = new HashMap<>();
			ForkJoinPool fjp = new ForkJoinPool();
				
			if(rightTable.size()>leftTable.size()) {
			leftTable1.addAll(rightTable);
			// leftTable1.addAll(lt);
			try {
				rs = fjp.submit(() -> pythonProcess(leftTable1, leftTable, type1, 1)).get();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExecutionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
			else
			{
				leftTable1.addAll(leftTable);
				// leftTable1.addAll(lt);
				try {
					rs = fjp.submit(() -> pythonProcess(leftTable1, rightTable, type1, 1)).get();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			System.out.println("This is going in3:" + resultSet.size());
			for (Entry<String, ResultSet> rr : rs.entrySet())
				resultSet.put(rr.getKey(), rr.getValue());
			System.out.println("This is going in4:" + resultSet.size());

			fjp.shutdown();
			// System.out.println("This is going in:"+resultSet.size());

			// if(iteration==2) {
			// resultSet1.addAll(rs1);
			// for(ResultSet rs2:resultSet1)
			// while(rs2.hasNext())
			// System.out.println("This is going in:"+rs2.nextBinding());
//						}
			// for(ResultSet rr:resultSet)
			// System.out.println("This is going in resultset:"+rr.);

			HashMap<String, ResultSet> rSet1 = resultSet;
			// List<ResultSet> rSet2 = resultSet.subList(200, 400);
			// List<ResultSet> rSet3 = resultSet.subList(400, 600);
			// List<ResultSet> rSet4 = resultSet.subList(600, 800);
			// List<ResultSet> rSet5 = resultSet.subList(800, 1000);
			// List<ResultSet> rSet6 = resultSet.subList(1000, 1200);
			// List<ResultSet> rSet7 = resultSet.subList(1200, 1400);
			// List<ResultSet> rSet8 = resultSet.subList(1400, 1600);
			// List<ResultSet> rSet9 = resultSet.subList(1600, 1800);
			// List<ResultSet> rSet10 = resultSet.subList(1800, 2000);

			// List<ResultSet> rSet2 =
			// resultSet.subList(resultSet.size()/2,resultSet.size());
			resultSetThread(rSet1,string);
			for (Entry<String, ResultSet> r : resultSet.entrySet())
				resultoutput.addAll(postProcess(r, 1,string));
			System.out.println("This is rSet1:" + resultoutput.size());
			/*
			 * resultSetThread(rSet2);
			 * System.out.println("This is rSet2:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet3);
			 * System.out.println("This is rSet3:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet4);
			 * System.out.println("This is rSet4:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet5);
			 * System.out.println("This is rSet5:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet6);
			 * System.out.println("This is rSet6:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet7);
			 * System.out.println("This is rSet7:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet8);
			 * System.out.println("This is rSet8:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet9);
			 * System.out.println("This is rSet9:"+resultoutput.size());
			 * 
			 * resultSetThread(rSet10);
			 * System.out.println("This is rSet10:"+resultoutput.size());
			 *
			*
			 * List<Callable<ArrayList<Binding>>> tasks1 = new
			 * ArrayList<Callable<ArrayList<Binding>>>(); ScheduledExecutorService exec1 =
			 * Executors.newSingleThreadScheduledExecutor(); for (ResultSet r : rSet2) {
			 * 
			 * Callable<ArrayList<Binding>> c1 = new Callable<ArrayList<Binding>>() {
			 * 
			 * @Override public ArrayList<Binding> call() throws Exception { //
			 * System.out.println("This is going in:"+resultoutput.size()+"--"+r);
			 * ArrayList<Binding> bb = postProcess(r, 0); ;
			 * System.out.println("This is going out1:"+bb.size());
			 * 
			 * // System.out.println("This is going out:"+bb);
			 * 
			 * // return resultoutput.addAll(postProcess(r,1)); return bb;
			 * 
			 * // return 1; } }; tasks1.add(c1); }
			 * 
			 * // }
			 * 
			 * try { List<Future<ArrayList<Binding>>> results = exec.invokeAll(tasks); for
			 * (Future<ArrayList<Binding>> r : results) try { int j = 0;
			 * 
			 * resultoutput.addAll(r.get());
			 * 
			 * // for(Binding fr:resultoutput) //
			 * {System.out.println("These are the bindings in fr:"+fr); // if(j==10) //
			 * break; // j++;} } catch (ExecutionException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } } catch (InterruptedException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); }// finally {
			 * exec.shutdown(); //} // }
			 *
			System.out.println("This is is resultSet large:" + resultoutput.size());

		}

		/*
		 * if (rightTable.size() > 100000 && leftTable.size() <= 100000) { resultoutput
		 * = new ArrayList<>(); HashMap<String,ResultSet> resultSet = new HashMap<>();
		 * List<ResultSet> resultSet1 = new ArrayList<>(); List<List<String>> leftTable1
		 * = new ArrayList<>(); leftTable1.addAll(rightTable); // leftTable1.addAll(lt);
		 * ForkJoinPool fjp = new ForkJoinPool(); HashMap<String, ResultSet> rs = new
		 * HashMap<>(); HashMap<String,ResultSet> rs1 = new HashMap<>(); try { rs =
		 * fjp.submit(() -> pythonProcess(leftTable1, leftTable, type1, 1)).get(); }
		 * catch (InterruptedException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } catch (ExecutionException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } for(Entry<String,
		 * ResultSet> rr:rs.entrySet()) resultSet.put(rr.getKey(),rr.getValue());
		 * 
		 * fjp.shutdown(); // System.out.println("This is going in:"+resultSet.size());
		 * 
		 * // if(iteration==2) { // resultSet1.addAll(rs1); // for(ResultSet
		 * rs2:resultSet1) // while(rs2.hasNext()) //
		 * System.out.println("This is going in:"+rs2.nextBinding()); // } //
		 * for(ResultSet rr:resultSet) //
		 * System.out.println("This is going in resultset:"+rr.);
		 * 
		 * List<ResultSet> rSet1 = resultSet.subList(0, resultSet.size()/2);
		 * List<ResultSet> rSet2 =
		 * resultSet.subList(resultSet.size()/2,resultSet.size());
		 * List<Callable<ArrayList<Binding>>> tasks = new
		 * ArrayList<Callable<ArrayList<Binding>>>(); ExecutorService exec =
		 * Executors.newWorkStealingPool(); for (ResultSet r : rSet1) {
		 * 
		 * Callable<ArrayList<Binding>> c = new Callable<ArrayList<Binding>>() {
		 * 
		 * @Override public ArrayList<Binding> call() throws Exception { //
		 * System.out.println("This is going in:"+resultoutput.size()+"--"+r);
		 * ArrayList<Binding> bb = postProcess(r, 0); ;
		 * System.out.println("This is going out:"+bb.size());
		 * 
		 * // System.out.println("This is going out:"+bb);
		 * 
		 * // return resultoutput.addAll(postProcess(r,1)); return bb;
		 * 
		 * // return 1; } }; tasks.add(c); }
		 * 
		 * try { List<Future<ArrayList<Binding>>> results = exec.invokeAll(tasks); for
		 * (Future<ArrayList<Binding>> r : results) try { int j = 0;
		 * 
		 * resultoutput.addAll(r.get());
		 * 
		 * // for(Binding fr:resultoutput) //
		 * {System.out.println("These are the bindings in fr:"+fr); // if(j==10) //
		 * break; // j++;} } catch (ExecutionException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } finally { exec.shutdown();
		 * } // }
		 * 
		 * 
		 * List<Callable<ArrayList<Binding>>> tasks1 = new
		 * ArrayList<Callable<ArrayList<Binding>>>(); ExecutorService exec1 =
		 * Executors.newWorkStealingPool(); for (ResultSet r : rSet2) {
		 * 
		 * Callable<ArrayList<Binding>> c = new Callable<ArrayList<Binding>>() {
		 * 
		 * @Override public ArrayList<Binding> call() throws Exception { //
		 * System.out.println("This is going in:"+resultoutput.size()+"--"+r);
		 * ArrayList<Binding> bb = postProcess(r, 0); ;
		 * System.out.println("This is going out1:"+bb.size());
		 * 
		 * // System.out.println("This is going out:"+bb);
		 * 
		 * // return resultoutput.addAll(postProcess(r,1)); return bb;
		 * 
		 * // return 1; } }; tasks1.add(c); }
		 * 
		 * try { List<Future<ArrayList<Binding>>> results1 = exec1.invokeAll(tasks); for
		 * (Future<ArrayList<Binding>> r : results1) try { int j = 0;
		 * 
		 * resultoutput.addAll(r.get()); // for(Binding fr:resultoutput) //
		 * {System.out.println("These are the bindings in fr:"+fr); // if(j==10) //
		 * break; // j++;} } catch (ExecutionException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } finally { exec1.shutdown();
		 * }
		 * 
		 * System.out.println("This is is resultSet right:" + resultoutput.size());
		 * 
		 * }
		 */
		/*
		 * if(rightTable.size()>200000 && leftTable.size()<=200000) {
		 * List<Callable<Integer>> tasks1 = new ArrayList<Callable<Integer>>();
		 * ExecutorService exec1 = Executors.newWorkStealingPool();
		 * 
		 * for(int ij1=1;ij1<rightTable.size()-200000;ij1+=200000) { List<List<String>>
		 * rt=rightTable.subList(ij1, ij1+200000);
		 * 
		 * Callable<Integer> c = new Callable<Integer>() {
		 * 
		 * @Override public Integer call() throws Exception { //
		 * System.out.println("This is the binding in intermediate within call:"+temp+
		 * "--"+endpoints); List<ResultSet> resultSet; List<List<String>> rightTable1
		 * =new ArrayList<>(); rightTable1.add(rightTable.get(0));
		 * rightTable1.addAll(rt);
		 * resultSet=pythonProcess(leftTable,rightTable1,type1,2);
		 * //resultSetList.add(resultSet); return 1; } }; tasks1.add(c);
		 * 
		 * }
		 * 
		 * try { List<Future<Integer>> results = exec1.invokeAll(tasks1);
		 * for(Future<Integer> r:results) try { r.get(); } catch (ExecutionException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * finally { exec1.shutdown();
		 * 
		 * }
		 * 
		 * }
		 * 
		 */
		// if(rightTable.size()>200000 ||leftTable.size()>200000 )
		// {System.out.println("This is resultset size:"+resultSetList.size());
		// for(ResultSet rsll:resultSetList) {
		// System.out.println("This is the size of rsll:");
		// );
		// }
		// }
		System.out.println("This is resultsetoutput size:" + resultoutput.size() + "--"
				+ resultoutput.parallelStream().limit(2).collect(Collectors.toList()));
		System.out.println("This is resultsetoutput Time:" +LocalTime.now());

		for (File f : fList)
			if (f.delete()) {
				// System.out.println("File deleted successfully1");
			}

		return resultoutput;

	}

	public static ArrayList<Binding> postProcess(Entry<String, ResultSet> r2, int type,String string) {

		ArrayList<Binding> results = new ArrayList<Binding>();
		ArrayList<Binding> results1 = new ArrayList<Binding>();
		
		// ArrayList<Binding> results1 = new ArrayList<Binding>();

		int l = 0;

		// ForkJoinPool fjp =new ForkJoinPool();
		// try {
		// fjp.submit( ()->{
	//System.out.println("This is timing within postprocess:"+LocalTime.now());
		
		Binding a;
		System.out.println("This is the last of stories:"+BindJoin.inner);		
		//Vertex ew1Kv1=ew1K.getEdge().getV1();
		//Vertex ew1Kv2=ew1K.getEdge().getV2();
		//Vertex ew1Kv3=ew1K.getEdge().getV1();
		//Vertex ew1Kv4=ew1K.getEdge().getV2();
		
	/*	if(BindJoin.inner==1)
		{
			System.out.println("This is the last of stories567:"+BindJoin.ciff+"--"+  StageGen.StringConversion(BindJoin.biff.substring(1)));
			
			while (r2.getValue().hasNext()) {
				//ResultSet lr = r2.getValue();
				a =  r2.getValue().nextBinding();
				
			
						
					Iterator<String> l1 =  r2.getValue().next().varNames();
					BindingHashMap extendTemp2 = new BindingHashMap();
											
					while(l1.hasNext())
						
				{
				String k=	l1.next();
		//		b1.get(l);
			//	BindingFactory.binding(b1., null); 
				//extendTemp2.add(Var.alloc(value.substring(1)), StageGen.StringConversion(l));
				if(k.equals(BindJoin.biff.substring(1)))
				extendTemp2.addAll(BindingFactory.binding(Var.alloc(BindJoin.ciff.substring(1)),a.get(Var.alloc(k))));
				else	if(k.equals(BindJoin.ciff.substring(1)))
					extendTemp2.addAll(BindingFactory.binding(Var.alloc(BindJoin.biff.substring(1)),a.get(Var.alloc(k))));
				else
					extendTemp2.addAll(BindingFactory.binding(Var.alloc(k),a.get(Var.alloc(k))));
				}
				//System.out.println("This is filter values lsat5454353:" +extendTemp2+"--"+Var.alloc(BindJoin.ciff.substring(1))+"--"+Var.alloc(BindJoin.biff.substring(1)) );
			//	for(Binding r:results)
			//	System.out.println("This is filter lsat5454353:" +r );
									
				/*
				if(k.toString().contains("?a"))
				{	//if(b1.get(k).toString().contains("http"))
					//extendTemp2.addAll(BindingFactory.binding(Var.alloc(k.toString().substring(1,k.toString().indexOf("?a"))),StageGen.StringConversion(b1.get(k).toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", "")) ));
					//else
						extendTemp2.addAll(BindingFactory.binding(Var.alloc(k.toString().substring(1,k.toString().indexOf("?a"))),b1.get(k)));
				}
				else
				{
					//if(b1.get(k).toString().contains("http"))
					//	extendTemp2.addAll(BindingFactory.binding(k,StageGen.StringConversion(b1.get(k).toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", "")) ));
						//else
							extendTemp2.addAll(BindingFactory.binding(k,b1.get(k)));
				}
	//	System.out.println("This is final result444444555555:"+k+"--"+b1.get(k));
		}	
			//	r1.addAll(b);
			//}
					r1.add(extendTemp2);
					
				*
	
				
		

				//System.out.println("This is timing within postprocess1:"+a);

				results.add(extendTemp2);
			}
		//	for(Binding r:results)
		//	System.out.println("This is the last of stories777:"+r);
			
		}
//	System.out.println("This is the last of stories777:"+ew1Kv3+"--"+ew1Kv4);
		else
		*/while (r2.getValue().hasNext()) {
			a = r2.getValue().nextBinding();
			//System.out.println("This is timing within postprocess1:"+a);

			results.add(a);
			results1.add(a);
		}
		//if(results.isEmpty())
		//{System.out.println("There are no matching rows");
		//	System.exit(0);
		//}
		
		/*
		 * Iterator<Binding> l1 = results1.iterator();
		 * 
		 * String length = ""; String[] vv = null; Set<String> headersAllRight = new
		 * HashSet<>();
		 * 
		 * List<String> input = new ArrayList<>(); int i=0; while (l1.hasNext()) {
		 * length=l1.next().toString(); // System.out.println("This is length:"+length);
		 * String a1 = String.valueOf(length);// l1.next().toString(); input.add(a1);
		 * if(i==1) { vv = length.split(" "); for (String v1 : vv) { if
		 * (v1.startsWith("?")) { //
		 * System.out.println("This is length1:"+v1.substring(1));
		 * 
		 * headersAllRight.add(v1.substring(1)); // headersAll.add(v.substring(1)); //
		 * break; } } } i++; }
		 * 
		 * 
		 * // System.out.println("This is headersAllRight:"+headersAllRight); //
		 * for(String i:input) //System.out.println("This is isisisisis:"+i);
		 * List<List<String>> leftTable =
		 * BGPEval.transformResult(input.parallelStream().collect(Collectors.toSet()),
		 * null,headersAllRight.parallelStream().collect(Collectors.toList()));
		 * 
		 * 
		 * System.out.println("This is the type"); ArrayList<String[]> leftCsv = new
		 * ArrayList<String[]>(); for (int i1 = 0; i1 < leftTable.size(); i1++) {
		 * String[] temp = new String[leftTable.get(0).size()]; for (int n = 0; n <
		 * temp.length; n++) { temp[n] = leftTable.get(i1).get(n); }
		 * 
		 * leftCsv.add(temp); }
		 */
		// File file1 = new File("/mnt/hdd/hammad/hammad/OutputBinding.csv");

//			if (file1.delete()) {
		// System.out.println("File deleted successfully1");
		// }
		// System.out.print("These are left var4");
		// try (CSVWriter writer = new CSVWriter(new
		// FileWriter("/mnt/hdd/hammad/hammad/OutputBinding.csv", true))) {
		// writer.writeAll(leftCsv);
		// writer.close();
		// } catch (IOException e4) {
		// TODO Auto-generated catch block
		//// e4.printStackTrace();
		// }
//	}).fork().get();
		// } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ExecutionException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// fjp.shutdown();
		Iterator<Binding> rIterator;
		LinkedHashSet<Binding> temp1 = new LinkedHashSet<>();
		int br = 0;
		Var joinVars = null;
		List<Var> joinVarsList = new ArrayList<>();
		Iterator<Var> l11=null;
		
		if (type == 0) {
	rIterator = results.iterator();
		while(rIterator.hasNext()) 
		l11 = rIterator.next().vars();
		System.out.println("This is timing within postprocess1:"+l11);		
		
	
		}
		if (type == 0 && string ==null) {
			Set<Vertex> v1 = new HashSet<>();
			Vertex f = null;
			v1.addAll(BGPEval.StartBinding123.keySet());
			
			for (Vertex v2 : v1) {
				
				// System.out.println("This is rule no. 1 in BindJoin:" + r);
				while (l11.hasNext()) {
					Var v3 = l11.next();
					joinVarsList.add(v3);
					Var r = Var.alloc(v2.getNode());
					System.out.println("This is rule no.3 in BindJoin:" + v3);

					if (r.equals(v3)) {
//					System.out.println("This is rule no.3 in BindJoin:" + r + "--" + v);
						f = v2;
						joinVars = v3;
						br = 1;
						break;
					}

				}
				if (br == 1) {
					br = 0;
					break;
				}
			}
		//	System.out.println("This is timing within postprocess3:"+LocalTime.now());

			if (joinVars != null) {
//		System.out.println("This is rule no. 2 in BindJoin:" + joinVars);

				for (Binding e1 : results) {
					if (e1.get(joinVars) != null) {
//				System.out.println("These are the problems:"+e1.get(joinVars).toString().replace("<", "").replace(">", "").replace("\"", ""));
						if (e1.get(joinVars).toString().contains("http"))
							temp1.add(BindingFactory.binding(joinVars, StageGen.StringConversion(e1.get(joinVars)
									.toString().replace("<", "").replace(">", "").replace("\"", "").replace(" ", ""))));
						else
							temp1.add(BindingFactory.binding(joinVars, StageGen.StringConversion(
									e1.get(joinVars).toString().replace("<", "").replace(">", "").substring(0,
											e1.get(joinVars).toString().replace("<", "").replace(">", "").length()
													- 2))));
					}

				}
				BGPEval.StartBinding123.put(f, temp1);
		//	for(	Entry<Vertex, Set<Binding>> eh:BGPEval.StartBinding123.entrySet())
				//System.out.println("This is timing within postprocess4:"+eh);

			}
			for(	Entry<Vertex, LinkedHashSet<Binding>> eh:BGPEval.StartBinding123.entrySet())
			System.out.println("This is timing within postprocess4:"+eh.getKey()+"--"+eh.getValue().size());

		}

		
		if (type == 0 && string !=null) {
			//Set<Vertex> v1 = new HashSet<>();
			Multimap<Vertex,String> v1 = ArrayListMultimap.create();
			HashMap<Vertex,String> f = new HashMap<>();

			
			for(Entry<HashMap<Vertex, String>, Set<Binding>> es:BGPEval.StartBinding123Large.entrySet())
				{System.out.println("This is timing within postprocess99:"+es.getKey()+"--"+es.getValue().size());
				for(Entry<Vertex, String> es1:es.getKey().entrySet())
				v1.put(es1.getKey(),es1.getValue());
				
				}
			for(Entry<Vertex,String> v9:v1.entries())
			System.out.println("This is the final fight:"+v9);
			for (Entry<Vertex, String> v2 : v1.entries()) {
				if( v2.getValue().equals(string)) {
				// System.out.println("This is rule no. 1 in BindJoin:" + r);
			if(l11!=null)
				while (l11.hasNext()) {
					Var v3 = l11.next();
					Var r = Var.alloc(v2.getKey().getNode());
					System.out.println("This is rule no.3 in StartBinding123Large:" + v2.getValue() + "--" + string);

					if (r.equals(v3)) {
						f.put(v2.getKey(),string);
						joinVars = v3;
						br = 1;
						break;
					}

				}
				if (br == 1) {
					br = 0;
					break;
				}
			}
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
											e1.get(joinVars).toString().replace("<", "").replace(">", "").length()
													- 2))));
					}

				}
			//	for(	Entry<HashMap<Vertex, String>, Set<Binding>> eh:BGPEval.StartBinding123Large.entrySet())
			//		System.out.println("This is timing within postprocess454545454545 before:"+string+"--"+eh.getKey()+"--"+eh.getValue().size());

				BGPEval.StartBinding123Large.put(f, temp1);
		//	for(	Entry<HashMap<Vertex, String>, Set<Binding>> eh:BGPEval.StartBinding123Large.entrySet())
		//		System.out.println("This is timing within postprocess454545454545:"+string+"--"+eh.getKey()+"--"+eh.getValue().size());

			
			
			}
			}

/*	for(	Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> eh: ParaEng.InnerFilter.entrySet())
				for(Entry<String,Set<Binding>> ee:eh.getValue().entrySet()) {
				int k1=0;
					
				for(Binding e1:ee.getValue()) {
				System.out.println("This is timing within postprocess454545454545BGPEval:"+ee.getKey()+"--"+e1);
					k1++;
					if(k1==50)
						break;
					}
				}	*/
	//		ParaEng.InnerFilter.put(f, temp1);
		//	for(	Entry<HashMap<String, Set<Binding>>, HashMap<String, Set<Binding>>> eh:ParaEng.InnerFilter.entrySet())
		//		System.out.println("This is timing within postprocess454545454545:"+string+"--"+eh);

		
			
		//		System.out.println("This is timing within postprocess5:"+LocalTime.now());

//		for(Binding r4:results)

		// for(Entry<Vertex, Set<Binding>> r4:BGPEval.StartBinding123.entrySet())
		// System.out.println("These are the bindings within BGPEval:"+r4);

//	System.out.println("These are the bindings within BGPEval:"+results.size());
//		for(	Entry<Vertex, Set<Binding>> eh:BGPEval.StartBinding123.entrySet())
	//		System.out.println("This is timing within postprocess49:"+eh.getKey()+"--"+eh.getValue().size());

		

		return results;
	}

	public static List<List<String>> TransformForGPU(Collection<Binding> left, List<String> in,
			ArrayList<String> headersLeft,int type) {
		// List<String> headersLeft = new ArrayList<String>();

		Iterator<Binding> l1 = left.iterator();

		// Object String in1;
		// if(QueryTask.isBound==1)
		// for(String in1:in) {
		// String a1=l1.next().toString();
		// System.out.println("This is in GPU Join:"+a1);
		// input1.add(a1);
		// a1++;
		// }
		/*
		 * if(headersLeft==null) { Iterator<Binding> l2 = left.iterator();
		 * while(l2.hasNext()) { length=l2.next().toString(); vv=length.split(" ");
		 * for(String v:vv) if(v.startsWith("?")) { headersLeft.add(v.substring(1));
		 * headersAll.add(v.substring(1)); //break; } break; } }
		 */
		for (String hla : headersLeft)
			System.out.println("This is headersAllLeft in GPU:" + hla);

//ExecutorService fjp = Executors.newCachedThreadPool();
		System.out.println("This is time1:" + LocalTime.now()+"--"+type);
//}
//fjp.shutdownNow();
//executor.shutdown();
//ForkJoinPool fjp = new ForkJoinPool();
//fjp.submit(()-> {
//
		List<String> input=new ArrayList<>();
		Set<String> input1=new HashSet<>();

//		if(ParaEng.Distinct.equals("Yes"))
	//	List<String> input = new ArrayList<>();
		//else
		int j = 0;
//if(QueryTask.isBound==0)
		while (l1.hasNext()) {

			String a = String.valueOf(l1.next());// l1.next().toString();
	//		if(type==2)
	//		System.out.println("This is value:"+a);
//	if(QueryTask.isBound==0)
			if(ParaEng.Distinct.equals("Yes"))
				input1.add(a);
			else
				input.add(a);
//	if(QueryTask.isBound==1)
//for(String ii:input)
//			
			j++;
		}
		if(ParaEng.Distinct.equals("Yes"))
		rowsLeft = transformResult(input1, in, headersLeft);
		else
			rowsLeft = transformResult(input, in, headersLeft,type);
		
			//	}).join();
//ForkJoinTask.join();
//fjp.shutdownNow();
		;
		System.out.println("This is it::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("This is time2:" + LocalTime.now());

//       for(String r:rowsLeft)
		// System.out.println("This is correct:"+r);
//Stream<String> stream = listToStream(rowsLeft);

//System.out.println("This is arraylist:"+Arrays.toString(stream.toArray()));
//stream.ma
//for(int l=0;l<=headersLeft.size();l++){
//	System.out.print(" "+table[0][l]);
//}
		// ForkJoinPool fjp1 = new ForkJoinPool(39);
//fjp1.submit(()-> {finalTable = tableCreation(headersLeft,rowsLeft);}).join();
//fjp1.shutdownNow();
//}
//else 
//	for(int j=0;j<rowsLeft.size();j++)
//	{
		// System.out.println("This is the error:"+k+"--"+(rowsLeft.size()-2));

//	table[j][0]=rowsLeft.get(j);

//}

		/*
		 * for(int l=0;l<=rowsLeft.size()/headersLeft.size();l++){ {for(int
		 * m=0;m<headersLeft.size();m++) System.out.print(" "+table[l][m]);}
		 * System.out.println(); }
		 */
		return rowsLeft;
	}

	
	public static List<List<String>> transformResult(Set<String> input2, List<String> in, List<String> headersLeft) {

		List<List<String>> rowsLeft = new ArrayList<>();

		System.out.println("This is time1.1:" + LocalTime.now());

		temp999 = new ArrayList<>();
		rowsLeft.add(headersLeft);
		int size = headersLeft.size();
		// ArrayList<String> arr = new ArrayList<>();
//int j=0;
		// while(l1.hasNext()) {
//		if(QueryTask.isBound==1)
//		{
//		  try {
		// l1.wait(500);
		// } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// Binding xz
		// =l1.next();
//if(QueryTask.isBound==0) {
		for (String l : input2) {
			// System.out.println("This is the original one:"+l);
			int i = 0;
			Pattern regex = Pattern.compile("[?=\\)][?= ][?=\\(]");
			String[] regexMatcher = regex.split(l);
			// synchronized(regexMatcher) {
			for (String ml : regexMatcher) {
				// System.out.println("This is the original one:"+ml);

				String[] mlp = ml.split(" ");
				// arr.clear();
				// arr.addAll(Arrays.asList(mlp));
				String str = "";

				for (int iii = 3; iii <= mlp.length - 1; iii++)
					// if(!arr.toString().contains("http"))
				{	//System.out.println("This is subsubsplit:"+mlp[iii]);
					str += mlp[iii].replace(")", "").replace("(", "").replace("->", "").replace("[Root]", "")
							.replace("\"", "") + " ";
				}// else
				// str+=arr.get(iii).replace(")", "").replace("(", "").replace("->",
				// "").replace("[Root]", "").replace("\"", "");
				// System.out.println("This is subsubsplit:"+str);
				temp999.add(String.valueOf(str));

				// if(QueryTask.isBound==1)
				// {
				// for(String m:mlp)
				/// System.out.println("This is old record:"+m);
				// for(String t:temp)
//					System.out.println("This is new record:"+str);
				// }
				
				
				if (i == size - 1) {
					rowsLeft.add(temp999);
					temp999 = new ArrayList<>();
				}

				i++;
				
		
			}

			// j++;

			}

	

		/*
		 * } else { for(String l:in) {// String zy=
		 * l1.toString();//.replace("\\", "").replace("',","").replace("'-
		 * ","").replace(",","").replace("\'", "").replace("\\\"","").replace(
		 * "^^xsd:int", "^^xsd:int\"").replace("^^xsd:date",
		 * "^^xsd:date\"").replace("@en", "@en\"").replace("@de", "@de\"").replace("_:",
		 * "\"_:").replace("^^xsd:float", "^^xsd:float\"").replace("^^xsd:double",
		 * "^^xsd:double\"").replace("NAN", "0");
		 * //System.out.println("This is the original one:"+l1.size()+"--"+zy); int i=0;
		 * Pattern regex = Pattern.compile("[?=\\)][?= ][?=\\(]"); String[] regexMatcher
		 * = regex.split(l); //synchronized(regexMatcher) { for(String ml:regexMatcher)
		 * { String[] mlp= ml.split(" "); // arr.clear(); //
		 * arr.addAll(Arrays.asList(mlp)); String str="";
		 * 
		 * for(int iii=3;iii<=mlp.length-1;iii++) //if(!arr.toString().contains("http"))
		 * str+=mlp[iii].replace(")", "").replace("(", "").replace("->",
		 * "").replace("[Root]", "").replace("\"", "")+" "; //else //
		 * str+=arr.get(iii).replace(")", "").replace("(", "").replace("->",
		 * "").replace("[Root]", "").replace("\"", ""); //
		 * System.out.println("This is subsubsplit:"+str); temp.add(str);
		 * 
		 * // if(QueryTask.isBound==1) // { // for(String m:mlp) ///
		 * System.out.println("This is old record:"+m); // for(String t:temp) //
		 * System.out.println("This is new record:"+str); // }
		 * 
		 * if(i==size-1) { rowsLeft.add(temp); temp = new ArrayList<>(); }
		 * 
		 * 
		 * i++; }
		 * 
		 * 
		 * j++;
		 * 
		 * } }
		 */
		System.out.println("This is time1.2:" + LocalTime.now());

		if (headersLeft.size() > 1) {
			if (HeaderReplacement != null || HeaderReplacement.size() > 0)
				for (int z = 0; z < headersLeft.size(); z++) {
					if (HeaderReplacement.containsKey(headersLeft.get(z).toString()))
						rowsLeft.get(0).set(z, HeaderReplacement.get(headersLeft.get(z).toString()));// ).replace(rowsLeft.get(0).get(z),
																										// HeaderReplacement.get(headersLeft.get(z).toString()));
					else
						rowsLeft.get(0).set(z, headersLeft.get(z));// ).replace(rowsLeft.get(0).get(z),
																	// HeaderReplacement.get(headersLeft.get(z).toString()));

					// rowsLeft.get(0).get(z).replace(rowsLeft.get(0).get(z), headersLeft.get(z));

					// table[0][z]=headersLeft.get(z);

				}
			else
				for (int z = 0; z < headersLeft.size(); z++) {// table[0][z]=headersLeft.get(z);
																// rowsLeft.get(0).get(z).replace(rowsLeft.get(0).get(z),
																// headersLeft.get(z));
					rowsLeft.get(0).set(z, headersLeft.get(z));// ).replace(rowsLeft.get(0).get(z),
																// HeaderReplacement.get(headersLeft.get(z).toString()));

				}
		}
		// if(ParaEng.Union.contains("UNION"))
		if (headersLeft.size() == 1) {
			HeaderReplacement.put(headersLeft.get(0).toString(), "?a");
			// rowsLeft.get(0).get(0).replace(rowsLeft.get(0).get(0), "?a");
			rowsLeft.get(0).set(0, "?a");// ).replace(rowsLeft.get(0).get(z),
											// HeaderReplacement.get(headersLeft.get(z).toString()));

//		table[0][0]="?a";

		}
		for (int z = 0; z < headersLeft.size(); z++) {// table[z][0]=headersLeft.get(z);
//			System.out.println("This is arraylist9.5:" + z + "--" + rowsLeft.get(0).get(z));

		}

//	for(List<String> r:rowsLeft) {
//for(String l:r) {
//		System.out.print(" "+l);
//	}
//	System.out.println();
//}	
		return rowsLeft;
	}
	
	public static List<List<String>> transformResult(List<String> input2, List<String> in, List<String> headersLeft, int type) {

		List<List<String>> rowsLeft = new ArrayList<>();

		System.out.println("This is time1.1:" + LocalTime.now());

		temp999 = new ArrayList<>();
		rowsLeft.add(headersLeft);
		int size = headersLeft.size();
		// ArrayList<String> arr = new ArrayList<>();
//int j=0;
		// while(l1.hasNext()) {
//		if(QueryTask.isBound==1)
//		{
//		  try {
		// l1.wait(500);
		// } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// Binding xz
		// =l1.next();
//if(QueryTask.isBound==0) {
		for (String l : input2) {
			// System.out.println("This is the original one:"+l);
			int i = 0;
			Pattern regex = Pattern.compile("[?=\\)][?= ][?=\\(]");
		//	System.out.println("This is su:"+l);
			
			String[] regexMatcher = regex.split(l);
			// synchronized(regexMatcher) {
			for (String ml : regexMatcher) {
				// System.out.println("This is the original one:"+ml);

				String[] mlp = ml.split(" ");
				// arr.clear();
				// arr.addAll(Arrays.asList(mlp));
				String str = "";
			//	System.out.println("This is subsubsplit9919191919:"+ml);
				for (int iii = 3; iii <= mlp.length - 1; iii++)
					// if(!arr.toString().contains("http"))
				{
			//		System.out.println("This is subsubsplit:"+mlp[iii]);
					
					str += mlp[iii].replace(")", "").replace("(", "").replace("->", "").replace("[Root]", "")
							.replace("\"", "") + " ";
				}	// else
				// str+=arr.get(iii).replace(")", "").replace("(", "").replace("->",
				// "").replace("[Root]", "").replace("\"", "");
			// System.out.println("6666655555555This is subsubsplit:"+str);
		//	if(type==2)	
			//	System.out.println("This is subsubsplit:"+temp999+"--"+size+"--"+i+"--"+l);
				temp999.add(String.valueOf(str));

				// if(QueryTask.isBound==1)
				// {
				// for(String m:mlp)
				/// System.out.println("This is old record:"+m);
				// for(String t:temp)
//					System.out.println("This is new record:"+str);
				// }

				if (i == size - 1) {
					rowsLeft.add(temp999);
					temp999 = new ArrayList<>();
				}

				i++;
			}

			// j++;

		}
		/*
		 * } else { for(String l:in) {// String zy=
		 * l1.toString();//.replace("\\", "").replace("',","").replace("'-
		 * ","").replace(",","").replace("\'", "").replace("\\\"","").replace(
		 * "^^xsd:int", "^^xsd:int\"").replace("^^xsd:date",
		 * "^^xsd:date\"").replace("@en", "@en\"").replace("@de", "@de\"").replace("_:",
		 * "\"_:").replace("^^xsd:float", "^^xsd:float\"").replace("^^xsd:double",
		 * "^^xsd:double\"").replace("NAN", "0");
		 * //System.out.println("This is the original one:"+l1.size()+"--"+zy); int i=0;
		 * Pattern regex = Pattern.compile("[?=\\)][?= ][?=\\(]"); String[] regexMatcher
		 * = regex.split(l); //synchronized(regexMatcher) { for(String ml:regexMatcher)
		 * { String[] mlp= ml.split(" "); // arr.clear(); //
		 * arr.addAll(Arrays.asList(mlp)); String str="";
		 * 
		 * for(int iii=3;iii<=mlp.length-1;iii++) //if(!arr.toString().contains("http"))
		 * str+=mlp[iii].replace(")", "").replace("(", "").replace("->",
		 * "").replace("[Root]", "").replace("\"", "")+" "; //else //
		 * str+=arr.get(iii).replace(")", "").replace("(", "").replace("->",
		 * "").replace("[Root]", "").replace("\"", ""); //
		 * System.out.println("This is subsubsplit:"+str); temp.add(str);
		 * 
		 * // if(QueryTask.isBound==1) // { // for(String m:mlp) ///
		 * System.out.println("This is old record:"+m); // for(String t:temp) //
		 * System.out.println("This is new record:"+str); // }
		 * 
		 * if(i==size-1) { rowsLeft.add(temp); temp = new ArrayList<>(); }
		 * 
		 * 
		 * i++; }
		 * 
		 * 
		 * j++;
		 * 
		 * } }
		 */
		System.out.println("This is time1.2:" + LocalTime.now());
		if(type!=0)
		{
		if (headersLeft.size() > 1) {
			if (HeaderReplacement != null || HeaderReplacement.size() > 0)
				for (int z = 0; z < headersLeft.size(); z++) {
					if (HeaderReplacement.containsKey(headersLeft.get(z).toString()))
						rowsLeft.get(0).set(z, HeaderReplacement.get(headersLeft.get(z).toString()));// ).replace(rowsLeft.get(0).get(z),
																										// HeaderReplacement.get(headersLeft.get(z).toString()));
					else
						rowsLeft.get(0).set(z, headersLeft.get(z));// ).replace(rowsLeft.get(0).get(z),
																	// HeaderReplacement.get(headersLeft.get(z).toString()));

					// rowsLeft.get(0).get(z).replace(rowsLeft.get(0).get(z), headersLeft.get(z));

					// table[0][z]=headersLeft.get(z);

				}
			else
				for (int z = 0; z < headersLeft.size(); z++) {// table[0][z]=headersLeft.get(z);
																// rowsLeft.get(0).get(z).replace(rowsLeft.get(0).get(z),
																// headersLeft.get(z));
					rowsLeft.get(0).set(z, headersLeft.get(z));// ).replace(rowsLeft.get(0).get(z),
																// HeaderReplacement.get(headersLeft.get(z).toString()));

				}
		}
		// if(ParaEng.Union.contains("UNION"))
		if (headersLeft.size() == 1) {
			HeaderReplacement.put(headersLeft.get(0).toString(), headersLeft.get(0).toString()+"?a");
			// rowsLeft.get(0).get(0).replace(rowsLeft.get(0).get(0), "?a");
			rowsLeft.get(0).set(0, headersLeft.get(0).toString()+"?a");// ).replace(rowsLeft.get(0).get(z),
											// HeaderReplacement.get(headersLeft.get(z).toString()));

//		table[0][0]="?a";

		}
		
		for(Entry<String, String> hr:HeaderReplacement.entrySet())
			System.out.println("This is arraylist9.7:" + hr.getKey() + "--" + hr.getValue());

			for (int z = 0; z < headersLeft.size(); z++) {// table[z][0]=headersLeft.get(z);
			System.out.println("This is arraylist9.7:" + z + "--" + rowsLeft.get(0).get(z));

		}
	}
//	for(List<String> r:rowsLeft) {
//	for(String l:r) {
//		System.out.print(" "+l);
//	}
//	System.out.println();
//}	
		long count=0;
		
		//if(rowsLeft.size()==1)
	//	{
	//		rowsLeft.clear();
	//		rowsLeft.add(as.parallelStream().collect(Collectors.toList()));
	//	}
		return rowsLeft;
	}

	
	public static List<List<String>> transformResultList(List<String> input2, List<String> in, List<String> headersLeft) {

		List<List<String>> rowsLeft = new ArrayList<>();

		System.out.println("This is time1.1:" + LocalTime.now());

		temp999 = new ArrayList<>();
		rowsLeft.add(headersLeft);
		int size = headersLeft.size();
		// ArrayList<String> arr = new ArrayList<>();
//int j=0;
		// while(l1.hasNext()) {
//		if(QueryTask.isBound==1)
//		{
//		  try {
		// l1.wait(500);
		// } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// Binding xz
		// =l1.next();
//if(QueryTask.isBound==0) {
		for (String l : input2) {
			// System.out.println("This is the original one:"+l);
			int i = 0;
			Pattern regex = Pattern.compile("[?=\\)][?= ][?=\\(]");
			String[] regexMatcher = regex.split(l);
			// synchronized(regexMatcher) {
			for (String ml : regexMatcher) {
				// System.out.println("This is the original one:"+ml);

				String[] mlp = ml.split(" ");
				// arr.clear();
				// arr.addAll(Arrays.asList(mlp));
				String str = "";

				for (int iii = 3; iii <= mlp.length - 1; iii++)
					// if(!arr.toString().contains("http"))
					str += mlp[iii].replace(")", "").replace("(", "").replace("->", "").replace("[Root]", "")
							.replace("\"", "") + " ";
				// else
				// str+=arr.get(iii).replace(")", "").replace("(", "").replace("->",
				// "").replace("[Root]", "").replace("\"", "");
				// System.out.println("This is subsubsplit:"+str);
				temp999.add(String.valueOf(str));

				// if(QueryTask.isBound==1)
				// {
				// for(String m:mlp)
				/// System.out.println("This is old record:"+m);
				// for(String t:temp)
//					System.out.println("This is new record:"+str);
				// }

				if (i == size - 1) {
					rowsLeft.add(temp999);
					temp999 = new ArrayList<>();
				}

				i++;
			}

			// j++;

		}
		/*
		 * } else { for(String l:in) {// String zy=
		 * l1.toString();//.replace("\\", "").replace("',","").replace("'-
		 * ","").replace(",","").replace("\'", "").replace("\\\"","").replace(
		 * "^^xsd:int", "^^xsd:int\"").replace("^^xsd:date",
		 * "^^xsd:date\"").replace("@en", "@en\"").replace("@de", "@de\"").replace("_:",
		 * "\"_:").replace("^^xsd:float", "^^xsd:float\"").replace("^^xsd:double",
		 * "^^xsd:double\"").replace("NAN", "0");
		 * //System.out.println("This is the original one:"+l1.size()+"--"+zy); int i=0;
		 * Pattern regex = Pattern.compile("[?=\\)][?= ][?=\\(]"); String[] regexMatcher
		 * = regex.split(l); //synchronized(regexMatcher) { for(String ml:regexMatcher)
		 * { String[] mlp= ml.split(" "); // arr.clear(); //
		 * arr.addAll(Arrays.asList(mlp)); String str="";
		 * 
		 * for(int iii=3;iii<=mlp.length-1;iii++) //if(!arr.toString().contains("http"))
		 * str+=mlp[iii].replace(")", "").replace("(", "").replace("->",
		 * "").replace("[Root]", "").replace("\"", "")+" "; //else //
		 * str+=arr.get(iii).replace(")", "").replace("(", "").replace("->",
		 * "").replace("[Root]", "").replace("\"", ""); //
		 * System.out.println("This is subsubsplit:"+str); temp.add(str);
		 * 
		 * // if(QueryTask.isBound==1) // { // for(String m:mlp) ///
		 * System.out.println("This is old record:"+m); // for(String t:temp) //
		 * System.out.println("This is new record:"+str); // }
		 * 
		 * if(i==size-1) { rowsLeft.add(temp); temp = new ArrayList<>(); }
		 * 
		 * 
		 * i++; }
		 * 
		 * 
		 * j++;
		 * 
		 * } }
		 */
		System.out.println("This is time1.2:" + LocalTime.now());

		if (headersLeft.size() > 1) {
			if (HeaderReplacement != null || HeaderReplacement.size() > 0)
				for (int z = 0; z < headersLeft.size(); z++) {
					if (HeaderReplacement.containsKey(headersLeft.get(z).toString()))
						rowsLeft.get(0).set(z, HeaderReplacement.get(headersLeft.get(z).toString()));// ).replace(rowsLeft.get(0).get(z),
																										// HeaderReplacement.get(headersLeft.get(z).toString()));
					else
						rowsLeft.get(0).set(z, headersLeft.get(z));// ).replace(rowsLeft.get(0).get(z),
																	// HeaderReplacement.get(headersLeft.get(z).toString()));

					// rowsLeft.get(0).get(z).replace(rowsLeft.get(0).get(z), headersLeft.get(z));

					// table[0][z]=headersLeft.get(z);

				}
			else
				for (int z = 0; z < headersLeft.size(); z++) {// table[0][z]=headersLeft.get(z);
																// rowsLeft.get(0).get(z).replace(rowsLeft.get(0).get(z),
																// headersLeft.get(z));
					rowsLeft.get(0).set(z, headersLeft.get(z));// ).replace(rowsLeft.get(0).get(z),
																// HeaderReplacement.get(headersLeft.get(z).toString()));

				}
		}
		// if(ParaEng.Union.contains("UNION"))
		if (headersLeft.size() == 1) {
			HeaderReplacement.put(headersLeft.get(0).toString(), "?a");
			// rowsLeft.get(0).get(0).replace(rowsLeft.get(0).get(0), "?a");
			rowsLeft.get(0).set(0, "?a");// ).replace(rowsLeft.get(0).get(z),
											// HeaderReplacement.get(headersLeft.get(z).toString()));

//		table[0][0]="?a";

		}
		for (int z = 0; z < headersLeft.size(); z++) {// table[z][0]=headersLeft.get(z);
			System.out.println("This is arraylist:" + z + "--" + rowsLeft.get(0).get(z));

		}

//	for(List<String> r:rowsLeft) {
//	for(String l:r) {
//		System.out.print(" "+l);
//	}
//	System.out.println();
//}	
		return rowsLeft;
	}
//public static String replaceCharUsingCharArray(String str, char ch, int index) {
	// char[] chars = str.toCharArray();
	// chars[index] = ch;
	// return String.valueOf(chars);
//}

	public static void finalResultCalculation(LinkedHashMap<List<EdgeOperator>, List<Binding>> joinedTriples,
			HashSet<List<EdgeOperator>> evaluatedTriples,
			LinkedHashMap<List<EdgeOperator>, List<Binding>> joinedTriples2, int bothJoined) {

		int k = 0;
		Treelowestlevel.clear();
		// logger.info("THis is currently the entering part B6
		// size:"+"--"+ProcessedSets2.size());

		for (int i = 0; i < joinedTriples.size() - 1; i++) {
			TreelowestlevelProcessed.add(i);
			for (int j = i + 1; j < joinedTriples.size(); j++) {
				Treelowestlevel.add(new ArrayList<>());
				Treelowestlevel.get(k).add(i);// [[1,2]];
				Treelowestlevel.get(k).add(j);// [[1,2]];

				// xyz.get(k).add(j);//[[1,2]];

				k++;
			}
		}
		if(Treelowestlevel.size()==0 || Treelowestlevel.isEmpty())
		{	ArrayList<Integer> b= new ArrayList<>();
		b.add(0);
			Treelowestlevel.add(b);
			}
//	Set<Binding> x = new HashSet<>();
		// System.out.println("13131313131313131313This this this this this this this this this this:"+Treelowestlevel);

		ForkJoinPool fjp = new ForkJoinPool();

		fjp.submit(() -> {
			ResultsSize14(joinedTriples, evaluatedTriples, joinedTriples2, bothJoined);
		}).join();

		fjp.shutdown();

		return;// ProcessedSets.get(ProcessedSets.size()-1);
		// }
	}

	public static void ResultsSize14Large(LinkedHashMap<HashMap<List<EdgeOperator>, String>, List<Binding>> notJoinedTriplesLarge,
			HashSet<HashMap<List<EdgeOperator>, String>> evaluatedTriplesLarge,
			LinkedHashMap<HashMap<List<EdgeOperator>, String>, List<Binding>> joinedTriplesLarge, int bothJoined, String string) {

		if (evaluatedTriplesLarge.size() == 0) {
			for (ArrayList<Integer> e : Treelowestlevel) {
				// System.out.println("This is the current size of evaluated
				// triples:"+HashJoin.EvaluatedTriples.size());
				if (HashJoin.EvaluatedTriples.size() > 0)
					break;
//		
				LinkedHashMap<HashMap<List<EdgeOperator>,String>, List<Binding>> firstSet = new LinkedHashMap<>();
				LinkedHashMap<HashMap<List<EdgeOperator>,String>, List<Binding>> secondSet = new LinkedHashMap<>();

				Object firstKey = notJoinedTriplesLarge.keySet().toArray()[e.get(0)];

				Object secondKey = notJoinedTriplesLarge.keySet().toArray()[e.get(1)];

				for (Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> ps2 : notJoinedTriplesLarge.entrySet()) {
					// System.out.println("This is a firstKey:"+firstKey+"--"+ps2.getKey());

					if (ps2.getKey().toString().equals(firstKey.toString())) {
						firstSet.put(ps2.getKey(), ps2.getValue());
					}
				}
				for (Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> ps2 : notJoinedTriplesLarge.entrySet()) {
					// System.out.println("This is a secondKey:"+secondKey+"--"+ps2.getKey());
					if (ps2.getKey().toString().contains(secondKey.toString())) {

						secondSet.put(ps2.getKey(), ps2.getValue());
					}
				}
				if (!firstSet.equals(secondSet))
					firstSet.putAll(secondSet);
				HashJoin.ProcessingTaskLarge(joinedTriplesLarge, firstSet, 0, bothJoined,string);
			}
		} else {
			for (Integer e : TreelowestlevelProcessed) {
				LinkedHashMap<HashMap<List<EdgeOperator>,String>, List<Binding>> firstSet = new LinkedHashMap<>();
				int equity = 0;
				for (Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> ps2 : notJoinedTriplesLarge.entrySet()) {
					firstSet.clear();
					firstSet.put(ps2.getKey(), ps2.getValue());
					// System.out.println("This is the numbering:"+e+"--"+equity);
					if (equity == e)
						break;
					equity++;
				}

				for (Entry<HashMap<List<EdgeOperator>, String>, List<Binding>> e2 : joinedTriplesLarge.entrySet()) {
					LinkedHashMap<HashMap<List<EdgeOperator>,String>, List<Binding>> secondSet = new LinkedHashMap<>();
					secondSet.put(e2.getKey(), e2.getValue());

					if (!firstSet.keySet().equals(secondSet.keySet())) {
						for (HashMap<List<EdgeOperator>, String> ks : firstSet.keySet())
							for (Entry<List<EdgeOperator>, String> ks1 : ks.entrySet())
									for(EdgeOperator ks2:ks1.getKey())
								if ((secondSet.toString().contains(ks2.getEdge().getV2().toString() )
										|| secondSet.toString().contains(ks2.getEdge().getV1().toString()))) {
									// for(List<Binding> fv:firstSet.values())
									// System.out.println("First
									// problem:"+firstSet.keySet()+"--"+fv.parallelStream().limit(1).collect(Collectors.toSet()));
									// for(List<Binding> fv:secondSet.values())

									// System.out.println("Second
									// problem:"+secondSet.keySet()+"--"+"--"+fv.parallelStream().limit(1).collect(Collectors.toSet()));

									HashJoin.ProcessingTaskLarge(secondSet, firstSet, 0, bothJoined,string);
								}

					}
				}

			}

		}

	}

	
	public static void finalResultCalculationLarge(LinkedHashMap<HashMap<List<EdgeOperator>, String>, List<Binding>> notJoinedTriplesLarge,
			HashSet<HashMap<List<EdgeOperator>, String>> evaluatedTriplesLarge,
			LinkedHashMap<HashMap<List<EdgeOperator>, String>, List<Binding>> joinedTriplesLarge, int bothJoined,String string) {

		int k = 0;
		Treelowestlevel.clear();
		// logger.info("THis is currently the entering part B6
		// size:"+"--"+ProcessedSets2.size());

		for (int i = 0; i < notJoinedTriplesLarge.size() - 1; i++) {
			TreelowestlevelProcessed.add(i);
			for (int j = i + 1; j < notJoinedTriplesLarge.size(); j++) {
				Treelowestlevel.add(new ArrayList<>());
				Treelowestlevel.get(k).add(i);// [[1,2]];
				Treelowestlevel.get(k).add(j);// [[1,2]];

				// xyz.get(k).add(j);//[[1,2]];

				k++;
			}
		}
//	Set<Binding> x = new HashSet<>();

		ForkJoinPool fjp = new ForkJoinPool();

		fjp.submit(() -> {
			ResultsSize14Large(notJoinedTriplesLarge, evaluatedTriplesLarge, joinedTriplesLarge, bothJoined,string);
		}).join();

		fjp.shutdown();
		//// logger.info("13131313131313131313This this this this this this this this
		//// this this:"+LocalTime.now());

		return;// ProcessedSets.get(ProcessedSets.size()-1);
		// }
	}

	public static void ResultsSize14(LinkedHashMap<List<EdgeOperator>, List<Binding>> joinedTriples,
			HashSet<List<EdgeOperator>> evaluatedTriples,
			LinkedHashMap<List<EdgeOperator>, List<Binding>> notJoinedTriples2, int bothJoined) {
		// System.out.println("This is the current size of evaluated triples:"+HashJoin.EvaluatedTriples.size());
			
		if (evaluatedTriples.size() == 0) {
			for (ArrayList<Integer> e : Treelowestlevel) {
				if (HashJoin.EvaluatedTriples.size() > 0)
					break;
//		
				LinkedHashMap<List<EdgeOperator>, List<Binding>> firstSet = new LinkedHashMap<>();
				LinkedHashMap<List<EdgeOperator>, List<Binding>> secondSet = new LinkedHashMap<>();

				Object firstKey = joinedTriples.keySet().toArray()[e.get(0)];

				Object secondKey = joinedTriples.keySet().toArray()[e.get(1)];

				for (Entry<List<EdgeOperator>, List<Binding>> ps2 : joinedTriples.entrySet()) {
					// System.out.println("This is a firstKey:"+firstKey+"--"+ps2.getKey());

					if (ps2.getKey().toString().equals(firstKey.toString())) {
						firstSet.put(ps2.getKey(), ps2.getValue());
					}
				}
				for (Entry<List<EdgeOperator>, List<Binding>> ps2 : joinedTriples.entrySet()) {
					// System.out.println("This is a secondKey:"+secondKey+"--"+ps2.getKey());
					if (ps2.getKey().toString().contains(secondKey.toString())) {

						secondSet.put(ps2.getKey(), ps2.getValue());
					}
				}
				if (!firstSet.equals(secondSet))
					firstSet.putAll(secondSet);
				HashJoin.ProcessingTask(notJoinedTriples2, firstSet, 0, bothJoined);
			}
		} else {
			for (Integer e : TreelowestlevelProcessed) {
				LinkedHashMap<List<EdgeOperator>, List<Binding>> firstSet = new LinkedHashMap<>();
				int equity = 0;


//				 for(Entry<List<EdgeOperator>, List<Binding>> fv:joinedTriples.entrySet())
//System.out.println("First problem:"+fv.getKey()+"--"+fv.getValue().parallelStream().limit(1).collect(Collectors.toSet()));
//						 for(Entry<List<EdgeOperator>, List<Binding>> fv:notJoinedTriples2.entrySet())

	//					 System.out.println("Second problem:"+fv.getKey()+"--"+"--"+fv.getValue().parallelStream().limit(1).collect(Collectors.toSet()));

				for (Entry<List<EdgeOperator>, List<Binding>> ps2 : joinedTriples.entrySet()) {
					firstSet.clear();
					firstSet.put(ps2.getKey(), ps2.getValue());
					// System.out.println("This is the numbering:"+e+"--"+equity);
					if (equity == e)
						break;
					equity++;
				}

				for (Entry<List<EdgeOperator>, List<Binding>> e2 : notJoinedTriples2.entrySet()) {
					LinkedHashMap<List<EdgeOperator>, List<Binding>> secondSet = new LinkedHashMap<>();
					secondSet.put(e2.getKey(), e2.getValue());

					if (!firstSet.keySet().equals(secondSet.keySet())) {
						for (List<EdgeOperator> ks : firstSet.keySet())
							for (EdgeOperator ks1 : ks)

								if (secondSet.toString().contains(ks1.getEdge().getV2().toString())
										|| secondSet.toString().contains(ks1.getEdge().getV1().toString())) {
		
									HashJoin.ProcessingTask(secondSet, firstSet, 0, bothJoined);
								}

					}
				}

			}

		}

	}

//	if(m==1) {
	// for(List<EdgeOperator> bl:bLM.keySet())
	// HashJoin.JoinedTriples.remove(bLM.keySet());
	// for(List<EdgeOperator> bl:aLM.keySet())
	// HashJoin.JoinedTriples.remove(bl);
	// }
	/*
	 * if(i==0) break;
	 * 
	 * LinkedHashMap<List<EdgeOperator>, Set<Binding>> firstSet = new
	 * LinkedHashMap<>(); LinkedHashMap<List<EdgeOperator>, Set<Binding>> secondSet
	 * = new LinkedHashMap<>();
	 * 
	 * 
	 * 
	 * //if(firstKey.toString().contains("id")) //
	 * System.out.println("This is firstSet:"+firstSet);
	 * //if(secondKey.toString().contains("id")) //
	 * System.out.println("This is secondSet:"+secondSet);
	 * 
	 * for(Entry<List<EdgeOperator>, Set<Binding>> ps2:ProcessedSets2.entrySet()) {
	 * //System.out.println("This is a firstKey:"+firstKey+"--"+ps2.getKey());
	 * 
	 * if(ps2.getKey().toString().equals(firstKey.toString())) {
	 * firstSet.put(ps2.getKey(),ps2.getValue()); } } for(Entry<List<EdgeOperator>,
	 * Set<Binding>> ps2:ProcessedSets2.entrySet()) {
	 * //System.out.println("This is a secondKey:"+secondKey+"--"+ps2.getKey());
	 * if(ps2.getKey().toString().contains(secondKey.toString())) {
	 * 
	 * secondSet.put(ps2.getKey(),ps2.getValue()); } } HashSet<String> aa = new
	 * HashSet<>(); if(!firstSet.equals(secondSet)) {firstSet.putAll(secondSet); }
	 * for(Entry<List<EdgeOperator>, Set<Binding>> f:firstSet.entrySet()) {
	 * for(EdgeOperator f1:f.getKey()) {
	 * aa.add(f1.getEdge().getTriple().getSubject().toString());
	 * aa.add(f1.getEdge().getTriple().getObject().toString());
	 * 
	 * } } int l=0; for(Entry<List<EdgeOperator>, Set<Binding>>
	 * jt:joinedTriples.entrySet()) for(String aaa:aa)
	 * if(jt.getKey().toString().contains(aaa)) l++;
	 * System.out.println("THis is comparison for size:"+l+"--"+aa.size());
	 * if(l==aa.size()) break;
	 * 
	 */
	// HashJoin.ProcessingTask(joinedTriples,firstSet);

	/*
	 * public static void RemainingTriples(List<Set<Binding>> ProcessedSets2) { int
	 * bnc_ =0; int k=0; //Add remaining vertics and calucations from previous
	 * iterations //
	 * //logger.info("This is happening yeay2, Now being static:"+ProcessedSets.size
	 * ()); Set<Integer> remainings = new HashSet<>(); ArrayList<Set<Binding>>
	 * ProcessedSetsRemaining = new ArrayList<>(); for(ArrayList<Integer>
	 * e:Treelowestlevel) for(Integer e3:e) remainings.add(e3);
	 * 
	 * for(Integer e4:remainings) ProcessedSetsRemaining.addAll(UnProcessedSets);
	 * ProcessedSetsRemaining.addAll(ProcessedSets);
	 * 
	 * // //logger.info("These are all the triples:"+ProcessedSets.size()+"--"+
	 * UnProcessedSets.size());
	 * 
	 * 
	 * // for(Set<Binding> e1:ProcessedSetsRemaining) //
	 * //logger.info("These are all the triples:"+e1.parallelStream().limit(1).
	 * collect(Collectors.toSet()));
	 * 
	 * // for(Set<Binding> e:UnProcessedSets) //
	 * //logger.info("These are remaining ones:"+e.size()+"--"+e.parallelStream().
	 * limit(1).collect(Collectors.toSet()));
	 * 
	 * 
	 * 
	 * k=0; Treelowestlevel.clear(); //ProcessedSets.clear();
	 * 
	 * for(int i=0;i<UnProcessedSets.size()-1;i++) for(int
	 * j=i+1;j<UnProcessedSets.size();j++) { Treelowestlevel.add(new ArrayList<>());
	 * Treelowestlevel.get(k).add(i);//[[1,2]];
	 * Treelowestlevel.get(k).add(j);//[[1,2]];
	 * 
	 * //xyz.get(k).add(j);//[[1,2]];
	 * 
	 * k++; } while(Treelowestlevel.size()>0) { bnc_ = ProcessedSets.size();
	 * Set<Binding> x = ResultsSize14(UnProcessedSets);
	 * 
	 * for(Set<Binding> p:ProcessedSets)
	 * //logger.info("This is processedSet1:"+p.parallelStream().limit(1).collect(
	 * Collectors.toSet()));
	 * 
	 * for(Set<Binding> up:UnProcessedSets)
	 * //logger.info("This is UnprocessedSet1:"+up.parallelStream().limit(1).collect
	 * (Collectors.toSet()));
	 * 
	 * // RemainingTriples(ProcessedSetsRemaining,k,bnc_);
	 * 
	 * ////logger.info("This is happening yeay98, Now being static0:"
	 * +UnProcessedSets.size()+"--"+ProcessedSets.get(0));
	 * 
	 * if(bnc_==ProcessedSets.size()) { RemainingTriples(UnProcessedSets); //
	 * //logger.info("This is happening yeay99, Now being static:"+UnProcessedSets+
	 * "--"+ProcessedSets.get(0)); break; } }
	 * 
	 * }
	 * 
	 * 
	 * 
	 */

	/*
	 * public static void BindJoinCorrection(List<List<EdgeOperator>>
	 * joinGroupsListLeftTemp) { { HashMap<Vertex, Integer> bvb = new HashMap<>();
	 * 
	 * for (List<EdgeOperator> jgl1 : JoinGroupsListExclusive) for (EdgeOperator
	 * jgl2 : jgl1) if (jgl2.getStartVertex() != null) {
	 * bvb.put(jgl2.getStartVertex(), 0); } Integer kl = 0, sum = 0;
	 * 
	 * for (List<EdgeOperator> jgl1 : joinGroupsListLeftTemp) for (EdgeOperator jgl2
	 * : jgl1) if (jgl2.getStartVertex() != null) {
	 * 
	 * for (List<EdgeOperator> jgl1s : joinGroupsListLeftTemp) if
	 * (!jgl1.toString().equals(jgl1s.toString())) { if
	 * (jgl1s.toString().contains(jgl2.getStartVertex().toString())) { for
	 * (Entry<Vertex, Integer> bvb1 : bvb.entrySet()) if
	 * (bvb1.getKey().toString().equals(jgl2.getStartVertex().toString())) {
	 * bvb.replace(bvb1.getKey(), bvb1.getValue(), kl); sum = sum + kl; } } kl++; }
	 * }
	 * 
	 * // logger.info("This is the new sum now:"+sum);
	 * 
	 * for (Map.Entry<Vertex, Integer> bvb11 : bvb.entrySet()) { //
	 * logger.info("This is nwe waysssss:"+bvb11); }
	 * 
	 * if (sum > 0) {
	 * 
	 * for (int i = 0; i < joinGroupsListLeftTemp.size(); i++) for (int j = 0; j <
	 * joinGroupsListLeftTemp.get(i).size(); j++) for (Map.Entry<Vertex, Integer>
	 * bvb11 : bvb.entrySet()) { if
	 * (joinGroupsListLeftTemp.get(i).get(j).getStartVertex() != null) { if
	 * (joinGroupsListLeftTemp.get(i).get(j).getStartVertex().toString()
	 * .contains(bvb11.getKey().toString()) && bvb11.getValue() == 0) { //
	 * logger.info("Make this vertex //
	 * opposite:"+JGroupsList.get(i).get(j).getStartVertex()+"--"+bvb11.getValue());
	 * if (joinGroupsListLeftTemp.get(i).get(j).getEdge().getV1().toString()
	 * .contains(bvb11.getKey().toString())) joinGroupsListLeftTemp.get(i).set(j,
	 * new BindJoin(joinGroupsListLeftTemp.get(i).get(j).getEdge().getV2(),
	 * joinGroupsListLeftTemp.get(i).get(j).getEdge())); else
	 * joinGroupsListLeftTemp.get(i).set(j, new
	 * BindJoin(joinGroupsListLeftTemp.get(i).get(j).getEdge().getV1(),
	 * joinGroupsListLeftTemp.get(i).get(j).getEdge()));
	 * 
	 * } } }
	 * 
	 * // for(List<EdgeOperator> jgl1:JGroupsList) //
	 * logger.info("This is the JoinGroupListAfter:"+jgl1);
	 * 
	 * // for(Map.Entry<Vertex,Integer> bvb11:bvb.entrySet()) //
	 * logger.info("This is nwe waysssss:"+bvb11);
	 * 
	 * for (List<EdgeOperator> jgl1 : JoinGroupsListExclusive) for (EdgeOperator
	 * jgl2 : jgl1) if (jgl2.getStartVertex() != null) {
	 * bvb.put(jgl2.getStartVertex(), 0); } kl = 0;
	 * 
	 * for (List<EdgeOperator> jgl1 : joinGroupsListLeftTemp) for (EdgeOperator jgl2
	 * : jgl1) if (jgl2.getStartVertex() != null) {
	 * 
	 * for (List<EdgeOperator> jgl1s : joinGroupsListLeftTemp) if
	 * (!jgl1.toString().equals(jgl1s.toString())) { if
	 * (jgl1s.toString().contains(jgl2.getStartVertex().toString())) { for
	 * (Entry<Vertex, Integer> bvb11 : bvb.entrySet()) if
	 * (bvb11.getKey().toString().equals(jgl2.getStartVertex().toString())) {
	 * bvb.replace(bvb11.getKey(), bvb11.getValue(), kl); } } kl++; } }
	 * 
	 * // for(Map.Entry<Vertex,Integer> bvb11:bvb.entrySet()) //
	 * logger.info("This is nwe waysssss:"+bvb11);
	 * 
	 * for (int i = 0; i < joinGroupsListLeftTemp.size(); i++) for (int j = 0; j <
	 * joinGroupsListLeftTemp.get(i).size(); j++) for (Map.Entry<Vertex, Integer>
	 * bvb11 : bvb.entrySet()) { if
	 * (joinGroupsListLeftTemp.get(i).get(j).getStartVertex() != null) { if
	 * (joinGroupsListLeftTemp.get(i).get(j).getStartVertex().toString()
	 * .contains(bvb11.getKey().toString()) && bvb11.getValue() == 0) { //
	 * logger.info("Make this vertex //
	 * opposite:"+JGroupsList.get(i).get(j).getStartVertex()+"--"+bvb11.getValue());
	 * joinGroupsListLeftTemp.get(i).set(j, new
	 * HashJoin(joinGroupsListLeftTemp.get(i).get(j).getEdge()));
	 * 
	 * } } }
	 * 
	 * // for(List<EdgeOperator> jgl1:JGroupsList) //
	 * logger.info("This is the JoinGroupListAfter:"+jgl1); }
	 * 
	 * int rep = 0;
	 * 
	 * for (List<EdgeOperator> jg2 : JoinGroupsListExclusive) { for (EdgeOperator
	 * jg22 : jg2) if (jg22.getStartVertex() != null) { for (EdgeOperator obt1 :
	 * operators_BushyTreeOrder) if (!obt1.getEdge().equals(jg22.getEdge())) { //
	 * //logger.info("This is si sis is //
	 * is:"+jg22.getStartVertex()+"--"+obt1.getEdge().getV1() ); if
	 * (jg22.getStartVertex().equals(obt1.getEdge().getV1()) ||
	 * jg22.getStartVertex().equals(obt1.getEdge().getV2())) { rep++; } } if (rep ==
	 * 0) { if
	 * (jg22.getStartVertex().getNode().equals(jg22.getEdge().getTriple().getSubject
	 * ())) jg2.add(new BindJoin(jg22.getEdge().getV2(), jg22.getEdge())); else
	 * jg2.add(new BindJoin(jg22.getEdge().getV1(), jg22.getEdge()));
	 * jg2.remove(jg22);
	 * 
	 * } }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public static void BindJoinCorrectionLeft(List<EdgeOperator> JGroupsList, int
	 * type) {
	 * 
	 * String FilterString = null; int countQmark = 0; String extension; String[]
	 * Extension = null; for (EdgeOperator jgll : JGroupsList) { if (ParaEng.opq ==
	 * " ") FilterString = ParaEng.Filter.toString(); else if
	 * (ParaEng.opq.toString().contains("FILTER")) FilterString =
	 * ParaEng.opq.toString(); else FilterString = " ";
	 * 
	 * if (FilterString != " ") { extension =
	 * FilterString.toString().substring(FilterString.toString().indexOf("FILTER"),
	 * FilterString.toString().indexOf(")") + 1);
	 * 
	 * Extension = extension.toString().split(" ");
	 * 
	 * } String querySubject = jgll.getEdge().getTriple().getSubject().toString();
	 * String queryObject = jgll.getEdge().getTriple().getObject().toString();
	 * 
	 * if (FilterString != " ") if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||
	 *!ParaEng.Union.isEmpty()) { if (jgll.toString().contains("Bind")) for
	 * (String ex : Extension) { if (queryObject.equals(ex.replaceAll("[()]", ""))
	 * || querySubject.equals(ex.replaceAll("[()]", ""))) { // logger.info("This is
	 * the subject and //
	 * ex11:"+queryObject+"--"+querySubject+"--"+ex.replaceAll("[()]","")); for
	 * (Entry<EdgeOperator, Integer> jgr : joinGroupsLeftOptional.entries()) if
	 * (jgr.getValue().equals(jgll)) { if (type == 1) {
	 * joinGroupsLeftOptional.remove(jgr.getKey(), jgr.getValue());
	 * JoinGroupsListLeftOptional.remove(jgr.getValue()); int a = jgr.getValue();
	 * EdgeOperator b = jgr.getKey(); joinGroupsLeftOptional.put(new
	 * HashJoin(b.getEdge()), a); JoinGroupsListLeftOptional.add(new
	 * HashJoin(b.getEdge())); break; } } } }
	 * 
	 * } } int rep = 0;
	 * 
	 * for (EdgeOperator jg22 : JoinGroupsListLeft) { // for(EdgeOperator jg22:jg2)
	 * if (jg22.getStartVertex() != null) { for (EdgeOperator obt1 :
	 * operators_BushyTreeOrder) if (!obt1.getEdge().equals(jg22.getEdge())) { //
	 * //logger.info("This is si sis is //
	 * is:"+jg22.getStartVertex()+"--"+obt1.getEdge().getV1() ); if
	 * (jg22.getStartVertex().equals(obt1.getEdge().getV1()) ||
	 * jg22.getStartVertex().equals(obt1.getEdge().getV2())) { rep++; } } if (type
	 * == 0) {
	 *
	 * 
	 * if(rep==0) {
	 * //if(jg22.getStartVertex().getNode().equals(jg22.getEdge().getTriple().
	 * getSubject())) // JoinGroupsListLeft.add(new
	 * BindJoin(jg22.getEdge().getV2(),jg22.getEdge())); // else //
	 * JoinGroupsListLeft.add(new BindJoin(jg22.getEdge().getV1(),jg22.getEdge()));
	 * JoinGroupsListLeft.remove(jg22);
	 * 
	 * } rep = 0; } } }
	 * 
	 * }
	 */
	/*
	 * public static void BindJoinCorrectionRight(List<EdgeOperator> JGroupsList,int
	 * type) {
	 * 
	 * 
	 * 
	 * 
	 * String FilterString=null; int countQmark=0; String extension; String[]
	 * Extension = null;
	 * 
	 * for(EdgeOperator jgll:JGroupsList) {if(ParaEng.opq==" ")
	 * FilterString=ParaEng.Filter.toString(); else
	 * if(ParaEng.opq.toString().contains("FILTER")) FilterString =
	 * ParaEng.opq.toString(); else FilterString = " ";
	 * 
	 * 
	 * if(FilterString!=" ") { extension =
	 * FilterString.toString().substring(FilterString.toString().indexOf("FILTER"),
	 * FilterString.toString().indexOf(")")+1);
	 * 
	 * Extension = extension.toString().split(" ");
	 * 
	 * }
	 * 
	 * String querySubject = jgll.getEdge().getTriple().getSubject().toString();
	 * String queryObject = jgll.getEdge().getTriple().getObject().toString();
	 * 
	 * 
	 * if(FilterString!=" ") if((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||
	 *!ParaEng.Union.isEmpty()) {
	 * 
	 * if(jgll.toString().contains("Bind")) for(String ex:Extension ) {
	 * if(queryObject.equals(ex.replaceAll("[()]",""))
	 * ||querySubject.equals(ex.replaceAll("[()]","")) ) {
	 * //logger.info("This is the subject and ex11:"+queryObject+"--"+querySubject+
	 * "--"+ex.replaceAll("[()]","")); for (Entry<EdgeOperator, Integer>
	 * jgr:joinGroupsRightOptional.entries()) if(jgr.getValue().equals(jgll)) {
	 * if(type==1) {
	 * 
	 * joinGroupsRightOptional.remove(jgr.getKey(),jgr.getValue());
	 * JoinGroupsListRightOptional.remove(jgr.getValue()); int a=jgr.getValue();
	 * EdgeOperator b= jgr.getKey(); joinGroupsRightOptional.put(new
	 * HashJoin(b.getEdge()), a); JoinGroupsListRightOptional.add(new
	 * HashJoin(b.getEdge())); break; } } }}
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * int rep=0;
	 * 
	 * for(EdgeOperator jg22:JoinGroupsListRight) { // for(EdgeOperator jg22:jg2)
	 * if(jg22.getStartVertex()!=null ) { for(EdgeOperator
	 * obt1:operators_BushyTreeOrder) if(!obt1.getEdge().equals(jg22.getEdge())) {
	 * //logger.info("This is si sis is is:"+jg22.getStartVertex()+"--"+obt1.getEdge
	 * ().getV1() ); if(jg22.getStartVertex().equals(obt1.getEdge().getV1() ) ||
	 * jg22.getStartVertex().equals(obt1.getEdge().getV2())) {rep++; } } if(rep==0)
	 * { if(type==0) {
	 * 
	 * if(jg22.getStartVertex().getNode().equals(jg22.getEdge().getTriple().
	 * getSubject())) JoinGroupsListRight.add(new
	 * BindJoin(jg22.getEdge().getV2(),jg22.getEdge())); else
	 * JoinGroupsListRight.add(new BindJoin(jg22.getEdge().getV1(),jg22.getEdge()));
	 * JoinGroupsListRight.remove(jg22);
	 * 
	 * } rep=0; } } }
	 * 
	 * 
	 * }
	 */
//JoinGroupsListOptional
//joinGroupsOptional1
	public static void CompleteOptional(ConcurrentHashMap<EdgeOperator, HashSet<Integer>> newFormationM,
			List<EdgeOperator> operators, List<EdgeOperator> operators_BushyTreeOrder) {
		int ll = 0;
		ConcurrentHashMap<EdgeOperator, HashSet<Integer>> newFormation = new ConcurrentHashMap<>();
		JoinGroupsListOptional.clear();
		List<EdgeOperator> operators_BushyTreeLeftOptional = new ArrayList<>();
		List<EdgeOperator> operators_BushyTreeRightOptional = new ArrayList<>();

		urik.clear();

		Set<EdgeOperator> URIbased = new HashSet<>();
Set<EdgeOperator> uris = new HashSet<>();

for (EdgeOperator nf : operators_BushyTreeOrder)
//	for (EdgeOperator nf : nf1)
		if (nf.getEdge().getV1().getNode().isURI() || nf.getEdge().getV2().getNode().isURI()) {
			URIbased.add(nf);

		}
int equality = 0;
if (!URIbased.isEmpty() || URIbased.size() > 0 || URIbased != null)
	for (EdgeOperator uri : URIbased)
		for (EdgeOperator uri1 : URIbased) {
			// System.out.println("This is here in uris condition:" + uri + "--" + uri1);

			if ((uri.getEdge().getV1().equals(uri1.getEdge().getV2())
					|| uri.getEdge().getV2().equals(uri1.getEdge().getV1()))
					&& (uri.getEdge().getV1().getNode().isURI() || uri.getEdge().getV2().getNode().isURI())
					&& (uri1.getEdge().getV1().getNode().isURI() || uri1.getEdge().getV2().getNode().isURI())) {
				// System.out.println("This is the focus
				// here:"+Optimizer.getEndpointE(uri.getEdge().getTriple()).toString()+"--"+Optimizer.getEndpointE(uri1.getEdge().getTriple()).toString()+"--"+equality);
				equality = 0;

				for (Endpoint op1 : Optimizer.getEndpointE(uri.getEdge().getTriple())) {
					for (Endpoint op2 : Optimizer.getEndpointE(uri1.getEdge().getTriple()))
						if (op1.equals(op2)) {
							equality++;
							// System.out.println("This is the focus here:"+op1+"--"+op2+"--"+equality);

						}

					if (equality == 2) {

						if(!uris.contains(uri) ||!uris.contains(uri))
						{uris.add(uri);
						uris.add(uri1);}
						HashMap<Vertex,List<Binding>> a1 = new HashMap<>();
						
						if(uri.getEdge().getV1().getNode().isURI()) {
						a1.put(uri.getEdge().getV1(), null);
						if(!urik_element.toString().contains(uri.toString()))
								urik_element.add(uri);
						}
						else
							{a1.put(uri.getEdge().getV2(), null);
							if(!urik_element.toString().contains(uri.toString()))
										urik_element.add(uri);
							}
						urik.putAll(a1);
							
							
							HashMap<Vertex,List<Binding>> b = new HashMap<>();
							if(uri1.getEdge().getV1().getNode().isURI()) {
							{b.put(uri1.getEdge().getV1(), null);
							if(!urik_element.toString().contains(uri.toString()))
									urik_element.add(uri);
							}
							}
							else
								{
								b.put(uri1.getEdge().getV2(), null);
								if(!urik_element.toString().contains(uri.toString()))
								urik_element.add(uri);
								}
						urik.putAll(b);
		
						// System.out.println("This is the focus here2:"+uris+"--"+equality);

						break;
					}
					if (equality == 2)
						break;
				}
			}
		}

if(!urik.isEmpty()|| urik!=null)
{
urim.clear();
for(EdgeOperator uri:uris)
{		if(uri.getEdge().getV1().getNode().isURI())
		urim.put(uri, new BindJoin(uri.getEdge().getV1(),uri.getEdge()));
System.out.println("THis is the exchange:"+urim);
}
Set<EdgeOperator> temp = new HashSet<>();
for(EdgeOperator obt:operators_BushyTreeOrder)
if(!temp.toString().contains(obt.toString()))
	temp.add(obt);	
operators_BushyTreeOrder.clear();
operators_BushyTreeOrder.addAll(temp);


//for(EdgeOperator ol:operators_BushyTreeLeft)
//System.out.println("THis is the exchange:"+ol+"--"+operators_BushyTreeLeft.indexOf(ol));






for(Entry<EdgeOperator, EdgeOperator> urii:urim.entrySet())
{
		System.out.println("THis is the exchange12321 operators_BushyTreeOrder before:"+operators_BushyTreeOrder);
		Iterator<EdgeOperator> obl11 = operators_BushyTreeOrder.iterator();
		while(obl11.hasNext()) 
			if(obl11.next().getEdge().equals(urii.getKey().getEdge()))
				obl11.remove();	
		operators_BushyTreeOrder.add(urii.getValue());
	}
	
temp.clear();

for(EdgeOperator obo1:operators_BushyTreeOrder1)
	for(EdgeOperator obo:operators_BushyTreeOrder)
		if(obo1.getEdge().getTriple().equals(obo.getEdge().getTriple()))
			temp.add(obo);
	operators_BushyTreeOrder.clear();
	operators_BushyTreeOrder.addAll(temp);

//	System.out.println("THis is the exchange12321 right:"+urii);

//	operators_BushyTreeRight.remove(urii.getKey());
	
//		operators_BushyTreeRight.add(urii.getValue());
//		System.out.println("THis is the exchange12321 order:"+urii);

//		operators_BushyTreeOrder.remove(urii.getKey());
	
//		operators_BushyTreeOrder.add(urii.getValue());

//else
temp.clear();

System.out.println("THis is the exchange12321 operators_BushyTreeOrder after:"+operators_BushyTreeOrder);
//joinGroupsOptional1.clear();
System.out.println("This is the proposal:"+operators_BushyTreeOrder);
System.out.println("This is the proposal123213:"+operators);
System.out.println("This is the proposal456456:"+BGPEval.operators_BushyTreeOrder);

joinGroupsOptional1 = CreateBushyTreesExclusive(operators_BushyTreeOrder, null, operators,
		BGPEval.operators_BushyTreeOrder);

System.out.println("This is optional group tree:" + joinGroupsOptional1);

//if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty())
for (Entry<List<EdgeOperator>, Integer> e : joinGroupsOptional1.entries()) {
	// logger.info("This is the new group of
	// queries:"+e.getKey()+"--"+e.getValue());
	if (e.getKey().size() > 0)
		JoinGroupsListOptional.add(e.getKey());

}
	}




		newFormationM.clear();

		for (Entry<List<EdgeOperator>, Integer> e4 : joinGroupsOptional1.entries()) {
			for (EdgeOperator e6 : e4.getKey()) {

				if (newFormation.size() == 0 || !newFormation.containsKey(e6)) {
					HashSet<Integer> bb = new HashSet<>();
					bb.add(e4.getValue());
					newFormation.put(e6, bb);

				} else {
					for (Entry<EdgeOperator, HashSet<Integer>> nf : newFormation.entrySet()) {
						if (nf.getKey().equals(e6)) {
							HashSet<Integer> bb = new HashSet<>();
							bb.addAll(nf.getValue());
							bb.add(e4.getValue());
							if (bb.size() > 1)
								newFormation.put(e6, bb);
						}

					}
				}
				// //logger.info("This is the new new new:"+e4.getKey()+"--"+e6);
			}

			JoinGroupsListOptional.add(e4.getKey());
		}

//for(Entry<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator, HashSet<Integer>> nf:newFormation.entrySet())



		for (Entry<EdgeOperator, HashSet<Integer>> nf : newFormation.entrySet()) {
			if (nf.getValue().size() > 1) {
				newFormationM.put(nf.getKey(), nf.getValue());
			}
		}

		System.out.println("This is the new new new11:"+newFormationM);
		
System.out.println("This is Old joinGroup2:"+uris);
//System.out.println("This is Old joinGroup333:"+JoinGroupsListExclusive);

//LinkedHashMap<Integer, List<EdgeOperator>> uuu;
//HashSet<EdgeOperator> ExistingEdges = new HashSet<>(); 



		operators_BushyTreeLeftOptional.clear();

		operators_BushyTreeRightOptional.clear();

		/*
		 * int isDoubled=0; int isTripled=0; int CompletlyNewFormation=0;
		 * List<EdgeOperator> leSafe= new ArrayList<>(); List<EdgeOperator>
		 * leSafeNonConcrete= new ArrayList<>();
		 * 
		 * Iterator<List<EdgeOperator>> jgle = JoinGroupsListExclusive.iterator() ;
		 * while(jgle.hasNext()) {
		 * List<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator>
		 * jglen = jgle.next(); //CompletlyNewFormation=0; for(Entry<EdgeOperator,
		 * HashSet<Integer>> nf:newFormationM.entrySet()) {
		 * //Iterator<List<EdgeOperator>> le = JoinGroupsListExclusive.iterator() ;
		 * 
		 * // while(le.hasNext()) // { // List<EdgeOperator> le2 = le.next();
		 * Iterator<EdgeOperator> le3 = jglen.iterator(); EdgeOperator le1;
		 * while(le3.hasNext()) { le1= le3.next();
		 * if(le1.getEdge().getTriple().getSubject().isURI()
		 * ||le1.getEdge().getTriple().getSubject().isLiteral()) { isTripled++; //
		 * if(!ConcreteEdge.contains(le1.getEdge().getTriple().getObject()))
		 * 
		 * //ConcreteEdge.add(le1.getEdge().getTriple().getObject()); continue; }
		 * 
		 * if( le1.getEdge().getTriple().getObject().isURI()
		 * ||le1.getEdge().getTriple().getObject().isLiteral()) { isTripled++;
		 * //if(!ConcreteEdge.contains(le1.getEdge().getTriple().getSubject()))
		 * //C/oncreteEdge.add(le1.getEdge().getTriple().getSubject()); continue; }
		 * 
		 * 
		 * /* if(!nf.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nf.getKey().getEdge().getTriple().getSubject().isConcrete()) { for(Node ce:
		 * ConcreteEdge) { if(nf.getKey().getEdge().getTriple().getObject().equals(ce) )
		 * CompletlyNewFormation=1; // //logger.info(""); if(
		 * nf.getKey().getEdge().getTriple().getSubject().equals(ce))
		 * CompletlyNewFormation=1; ////logger.info(""); } }
		 * 
		 * 
		 * if(CompletlyNewFormation==0) { isDoubled++;
		 * 
		 * // //log.info("This is total size of triple group:"+"--"+jglen.size());
		 * if(!leSafeNonConcrete.contains(nf.getKey()))
		 * leSafeNonConcrete.add(nf.getKey()); } CompletlyNewFormation=1;
		 * 
		 * if((nf.getKey().getEdge().getTriple().getSubject().toString().equals(le1.
		 * getEdge().getTriple().getObject().toString()) ||
		 * nf.getKey().getEdge().getTriple().getObject().toString().equals(le1.getEdge()
		 * .getTriple().getSubject().toString())) &&
		 * !nf.getKey().getEdge().toString().equals(le1.getEdge().toString())) { if
		 * ((le1.getEdge().getTriple().getSubject().isConcrete() &&
		 * !le1.getEdge().getTriple().getObject().isConcrete())) //isDoubled++;
		 * isTripled++; if ((le1.getEdge().getTriple().getObject().isConcrete() &&
		 * !le1.getEdge().getTriple().getSubject().isConcrete())) //isDoubled++;
		 * isTripled++; if ((nf.getKey().getEdge().getTriple().getSubject().isConcrete()
		 * && !nf.getKey().getEdge().getTriple().getObject().isConcrete()))
		 * //isDoubled++; isTripled++; if
		 * ((nf.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nf.getKey().getEdge().getTriple().getSubject().isConcrete())) //isDoubled++;
		 * isTripled++; } leSafe.add(le1);
		 * 
		 * } if(isTripled>1) { //isTripled=0; continue; }
		 * 
		 * 
		 * 
		 * if(isTripled==0 ) { for(Entry<EdgeOperator, HashSet<Integer>>
		 * nf1:newFormationM.entrySet()) {
		 * 
		 * if(jglen.contains(nf1.getKey())) jglen.remove(nf1.getKey());
		 * if(!operators_BushyTreeLeft.contains(nf1.getKey()) &&
		 * ((!nf1.getKey().getEdge().getTriple().getSubject().isURI()
		 * ||!nf1.getKey().getEdge().getTriple().getSubject().isLiteral()) ||
		 * (!nf1.getKey().getEdge().getTriple().getObject().isURI() ||
		 * !nf1.getKey().getEdge().getTriple().getObject().isLiteral())) )
		 * {operators_BushyTreeLeft.add(nf1.getKey());
		 * operators_BushyTreeRight.add(nf1.getKey()); }
		 * 
		 * if(jglen.size()==1) {
		 * if(!operators_BushyTreeLeft.toString().contains(jglen.toString())) {
		 * operators_BushyTreeLeft.addAll(jglen);
		 * operators_BushyTreeRight.addAll(jglen);
		 * 
		 * }
		 * 
		 * // jgle.remove(); } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * // isDoubled=0;
		 * 
		 * } } // }
		 * 
		 * 
		 * 
		 * if(isDoubled>0) { //for(Entry<EdgeOperator, HashSet<Integer>>
		 * nf1:newFormationM.entrySet()) //{ for(EdgeOperator le11:leSafeNonConcrete) {
		 * EdgeOperator a11= le11; for(int i=0;i<JoinGroupsListExclusive.size();i++)
		 * if(!JoinGroupsListExclusive.get(i).toString().contains("http")) {
		 * JoinGroupsListExclusive.get(i).remove(a11);
		 * if(!operators_BushyTreeLeft.contains(le11) ) {
		 * operators_BushyTreeLeft.add(le11); operators_BushyTreeRight.add(le11); } } //
		 * }
		 * 
		 * // } // } //} }
		 * 
		 * 
		 * }
		 * 
		 */

		/*
		 * for(int i=0 ;i<JoinGroupsListExclusive.size();i++) for(int
		 * j=0;j<JoinGroupsListExclusive.get(i).size();j++) for(Node ce:ConcreteEdge)
		 * if(!JoinGroupsListExclusive.get(i).get(j).getEdge().getTriple().getObject().
		 * equals(ce) &&
		 * !JoinGroupsListExclusive.get(i).get(j).getEdge().getTriple().getSubject().
		 * equals(ce)) {
		 * operators_BushyTreeLeft.add(JoinGroupsListExclusive.get(i).get(j));
		 * operators_BushyTreeRight.add(JoinGroupsListExclusive.get(i).get(j));
		 * JoinGroupsListExclusive.get(i).remove(j); }
		 */
		
		HashSet<List<EdgeOperator>> t= new HashSet<>();
		t.addAll(JoinGroupsListOptional);
		JoinGroupsListOptional.clear();
		JoinGroupsListOptional.addAll(t);
		
		System.out.println("this is JoinGroupsListOptional in jgIterator:" + operators_BushyTreeLeftOptional);
		System.out.println("this is JoinGroupsListOptional in jgIterator12:" + JoinGroupsListOptional);
 
		List<EdgeOperator> inclusion = new ArrayList<>();
		Iterator<List<EdgeOperator>> jgIterator = JoinGroupsListOptional.iterator();// joinGroups2.keySet().iterator();
		int ij1 = 0;
		while (jgIterator.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator.next();
			for (Entry<EdgeOperator, HashSet<Integer>> nfm : newFormationM.entrySet()) {

				if (aa.contains(nfm.getKey())) {
					inclusion.add(nfm.getKey());
//	jgIterator.remove();;
					
					System.out.println("This is the problemvbvbvvbvv:"+uris);
					System.out.println("This is the problemvbvbvvbvv121212121212:"+nfm.getKey());
					
					//if (!uris.contains(nfm.getKey())) {
						operators_BushyTreeLeftOptional.add(nfm.getKey());
						operators_BushyTreeRightOptional.add(nfm.getKey());
				//	}
					// System.out.println("this is problem11111:" + aa);

//}
//}
//}
				}
			}
		}
	
			
		System.out.println("this is problem11111 Optional:" + operators_BushyTreeLeftOptional);
		System.out.println("this is problem11111 inclusion before:" + JoinGroupsListOptional);


		jgIterator = JoinGroupsListOptional.iterator();// joinGroups2.keySet().iterator();
		if (!inclusion.isEmpty() || inclusion.size() > 0 || inclusion != null)
		while (jgIterator.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator.next();
			for (EdgeOperator e : inclusion)
				aa.remove(e);
		}

		
		System.out.println("this is problem11111 inclusion:" + JoinGroupsListOptional);

		//List<EdgeOperator> namesList1 = uris.parallelStream().collect(Collectors.toList());

//		JoinGroupsListOptional.add(namesList1);
//JoinGroupsListExclusive.remove(inclusion);
		/*
		 * Iterator<List<EdgeOperator>> jgIterator1 =
		 * JoinGroupsListExclusive.iterator(); int ij=0;
		 * 
		 * while(jgIterator1.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
		 * aa=jgIterator1.next(); if(aa.size()==1 ) { for(EdgeOperator aaa:aa) { //
		 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
		 * ()); for(List<EdgeOperator> jge:JoinGroupsListExclusive)
		 * if((aaa.getEdge().getTriple().getObject().isConcrete() ||
		 * aaa.getEdge().getTriple().getSubject().isConcrete()) && jge.size()>1)
		 * for(EdgeOperator jgle:jge) {
		 * if(jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getSubject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getSubject())) { jge.add(aaa); aa.remove(aaa); } ij=1; break; } if(ij==1)
		 * break;
		 * 
		 * if(ij==1) {ij=0; break;}
		 * 
		 * }
		 * 
		 * } }
		 * 
		 * Iterator<List<EdgeOperator>> jgIterator11 = joinGroups2.values().iterator();
		 * int ij11=0;
		 * 
		 * while(jgIterator11.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
		 * aa=jgIterator11.next(); if(aa.size()==1 ) { for(EdgeOperator aaa:aa) { //
		 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
		 * ()); for(Entry<Integer, List<EdgeOperator>> jgel:joinGroups2.entrySet())
		 * if((aaa.getEdge().getTriple().getObject().isConcrete() ||
		 * aaa.getEdge().getTriple().getSubject().isConcrete()) &&
		 * jgel.getValue().size()>1) for(EdgeOperator jgle:jgel.getValue()) {
		 * if(jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getSubject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getSubject())) { jgel.getValue().add(aaa); aa.remove(aaa); } ij11=1; break; }
		 * if(ij11==1) break;
		 * 
		 * if(ij11==1) {ij11=0; break;}
		 * 
		 * }
		 * 
		 * } }
		 * 
		 *//*
			 * Iterator<List<EdgeOperator>> jgIterator111 =
			 * JoinGroupsListExclusive.iterator(); int ij111=0;
			 * 
			 * while(jgIterator111.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
			 * aa=jgIterator111.next(); if(aa.size()<=newFormationM.size() && aa.size()>1 )
			 * { for(EdgeOperator aaa:aa) { //
			 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
			 * ()); for(Entry<EdgeOperator, HashSet<Integer>> nfm:newFormationM.entrySet())
			 * if(aaa.equals(nfm.getKey()) &&
			 * !aaa.getEdge().getTriple().getSubject().isConcrete()&&
			 * !aaa.getEdge().getTriple().getObject().isConcrete()) ij111++;
			 * if(ij111==aa.size()) if(aa.size()>1 || aa.isEmpty()==false) {
			 * 
			 * // System.out.println("1this is problem:"+aa);
			 * //if(!operators_BushyTreeRight.equals(aa)) {
			 * 
			 * // operators_BushyTreeRight.addAll(aa); //
			 * operators_BushyTreeLeft.addAll(aa); // } // jgIterator111.remove();
			 * //System.out.println("1this is problem11:"+aa);
			 * 
			 * } } } }
			 */

		/*
		 * Iterator<List<EdgeOperator>> obtIterator99; Iterator<List<EdgeOperator>>
		 * jg2Iterator99; List<EdgeOperator> obt99; boolean isString=false;
		 * obtIterator99 = JoinGroupsListExclusive.iterator();
		 * while(obtIterator99.hasNext()) { obt99 = obtIterator99.next(); for(int i=0;
		 * i<obt99.size();i++) {
		 * if(obt99.get(i).getEdge().getTriple().getObject().isConcrete() ||
		 * obt99.get(i).getEdge().getTriple().getSubject().isConcrete()) {isString=true;
		 * break; } } if(isString==true) { isString=false; jg2Iterator99 =
		 * JoinGroupsListExclusive.iterator(); List<EdgeOperator> jg99;
		 * while(jg2Iterator99.hasNext()) { jg99=jg2Iterator99.next();
		 * if(!obt99.equals(jg99))
		 * 
		 * for(EdgeOperator jg991:jg99) { if(obt99.contains(jg991)) jg99.remove(jg991);
		 * } } }
		 * 
		 * }
		 */

		/*for(List<EdgeOperator> jgl:JoinGroupsListOptional)
			System.out.println("This i the optional problem here:"+jgl);
		Iterator<List<EdgeOperator>> jgIterator3 = joinGroupsOptional1.keySet().iterator();

		while (jgIterator3.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator3.next();
			if (aa.size() == 1) {

				if (!operators_BushyTreeRightOptional.equals(aa)) {

					operators_BushyTreeRightOptional.addAll(aa);
					operators_BushyTreeLeftOptional.addAll(aa);
				}
				jgIterator3.remove();
			}
		}*/
//		for (EdgeOperator uri : uris) {
	//		operators_BushyTreeRightOptional.remove(uri);
	//		operators_BushyTreeLeftOptional.remove(uri);
	//	}
System.out.println("This is the task of eternity:"+JoinGroupsListOptional);
		Iterator<List<EdgeOperator>> jgIterator1111 = JoinGroupsListOptional.iterator();

		while (jgIterator1111.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator1111.next();
			if (aa.size() == 1) {
				if (!operators_BushyTreeRightOptional.equals(aa)) {
					operators_BushyTreeRightOptional.addAll(aa);
					operators_BushyTreeLeftOptional.addAll(aa);
				}

				jgIterator1111.remove();
			}
		}
		for(EdgeOperator jgl:operators_BushyTreeLeftOptional)
			System.out.println("This the  problem here111111111111:"+jgl);

/////////////////////////////Seperating SourceStatements from Exclusive Group and forming Left/Right Bushy Tree ////////////////////////

/////////////////////////////Ordering JoinGroupsListExclusive ////////////////////////

////////////////////////////////////////////////////////////////////////////
		/*
		 * Iterator<List<EdgeOperator>> jgIterator6 =
		 * JoinGroupsListExclusive.iterator();
		 * 
		 * List<EdgeOperator> jg = new ArrayList<EdgeOperator>();
		 * while(jgIterator6.hasNext()) {int ij6=0;
		 * 
		 * List<EdgeOperator> aa = new ArrayList<>(); aa=jgIterator6.next();
		 * if(aa.size()>1 ) { for(Entry<EdgeOperator, HashSet<Integer>>
		 * nfm:newFormationM.entrySet()) { int isConcrete=0; for(List<EdgeOperator>
		 * joinG:JoinGroupsListExclusive) { for(EdgeOperator joinG1:joinG) {
		 * if(joinG1.getEdge().getTriple().getObject().equals(nfm.getKey().getEdge().
		 * getTriple().getSubject()) &&
		 * joinG1.getEdge().getTriple().getSubject().isConcrete()) isConcrete=1; } }
		 * 
		 * if(aa.contains(nfm.getKey()) &&
		 * !nfm.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nfm.getKey().getEdge().getTriple().getSubject().isConcrete() &&
		 * isConcrete==0) {ij6++; jg.add(nfm.getKey()); } } if(aa.size()>=ij6 &&ij6>0)
		 * //for(EdgeOperator aaa:jg) {
		 * ////log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.
		 * size()); for(EdgeOperator j:jg) { if(!operators_BushyTreeRight.contains(j)) {
		 * operators_BushyTreeRight.addAll(jg); operators_BushyTreeLeft.addAll(jg); }
		 * aa.remove(j);
		 * 
		 * } //}
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * Iterator<List<EdgeOperator>> jgIterator7 = joinGroups2.values().iterator();
		 * List<EdgeOperator> jg1 = new ArrayList<EdgeOperator>();
		 * 
		 * while(jgIterator7.hasNext()) {int ij7=0; List<EdgeOperator> aa = new
		 * ArrayList<>(); aa=jgIterator7.next(); if(aa.size()>1 ) {
		 * for(Entry<EdgeOperator, HashSet<Integer>> nfm:newFormationM.entrySet()) { int
		 * isConcrete=0; for(List<EdgeOperator> joinG:JoinGroupsListExclusive) {
		 * for(EdgeOperator joinG1:joinG) {
		 * if(joinG1.getEdge().getTriple().getObject().equals(nfm.getKey().getEdge().
		 * getTriple().getSubject()) &&
		 * joinG1.getEdge().getTriple().getSubject().isConcrete()) isConcrete=1; } }
		 * 
		 * if(aa.contains(nfm.getKey()) &&
		 * !nfm.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nfm.getKey().getEdge().getTriple().getSubject().isConcrete() &&
		 * isConcrete==0) {ij7++; jg.add(nfm.getKey());
		 * 
		 * } } if(aa.size()>=ij7 &&ij7>0) //for(EdgeOperator aaa:jg) {
		 * ////log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.
		 * size()); for(EdgeOperator j:jg1) { if(!operators_BushyTreeRight.contains(j))
		 * { operators_BushyTreeRight.addAll(jg1); operators_BushyTreeLeft.addAll(jg1);
		 * } aa.remove(j); } //}
		 * 
		 * } }
		 */

		// ForkJoinPool fjp98 = new ForkJoinPool();
		// fjp98.submit(() -> futileTripleOptional());
		// fjp98.shutdown();

		// ForkJoinPool fjp97 = new ForkJoinPool();
		// fjp97.submit(() -> refineTripleOptional(newFormationM,
		// operators_BushyTreeLeftOptional,
		// operators_BushyTreeRightOptional));
		// fjp97.shutdown();

	//	for (int i = 0; i < JoinGroupsListOptional.size(); i++) {
	//		if (JoinGroupsListOptional.get(i).size() == 1) {
		//		operators_BushyTreeRightOptional.addAll(JoinGroupsListOptional.get(i));
		//		operators_BushyTreeLeftOptional.addAll(JoinGroupsListOptional.get(i));

			//	JoinGroupsListOptional.remove(i);
		//	}
		//}
		/*
		 * obtIterator=null; jg2Iterator=null; ax=null; obt=null; obtIterator =
		 * joinGroups2.values().iterator(); while(obtIterator.hasNext()) { int
		 * comparedSize=0; obt = obtIterator.next(); jg2Iterator =
		 * joinGroups2.values().iterator(); while(jg2Iterator.hasNext()) {
		 * List<EdgeOperator> jg2=jg2Iterator.next(); size=jg2.size();
		 * if(obt.size()<size) {
		 * 
		 * for(EdgeOperator jg22:jg2) { if(obt.contains(jg22)) comparedSize++; }
		 * ax.add(obt);
		 * //log.info("This is the size for whole1:"+obt.size()+"--"+size);;
		 * 
		 * obtIterator.remove();
		 * 
		 * }
		 * 
		 * //log.info("THis is exclussss1:"+JoinGroupsListExclusive);
		 * 
		 * 
		 * } }
		 */
		////////////////////////////////////////////////////////////////////////////

////logger.info("This is the task of eternity222222222:"+JoinGroupsListExclusive);

////logger.info("This is now operators_BushyTreeRight:"+operators_BushyTreeRight);

////////////////////////////////////////////////////////////////////////////

//logger.info("This is the task of eternity222222222:"+JoinGroupsListExclusive);
	//	for(List<EdgeOperator> jgl:JoinGroupsListOptional)
	//		System.out.println("This i the optional problem here2222222222:"+jgl);
	//	for(EdgeOperator jgl:JoinGroupsListLeftOptional)
	//		System.out.println("This i the optional problem here33333333333:"+jgl);
	//	for(EdgeOperator jgl:JoinGroupsListRightOptional)
	//		System.out.println("This i the optional problem here44444444444:"+jgl);

		
		List<EdgeOperator> JoinGroupsListExclusiveTemp1 = new Vector<>();
		for (EdgeOperator obt1 : BGPEval.operators_BushyTreeOrder)
			for (EdgeOperator jg2 : operators_BushyTreeRightOptional)
				if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp1.contains(jg2))
					JoinGroupsListExclusiveTemp1.add(jg2);

		operators_BushyTreeRightOptional.clear();
		operators_BushyTreeLeftOptional.clear();
	//	Collections.reverse(JoinGroupsListExclusiveTemp1);
		operators_BushyTreeRightOptional.addAll(JoinGroupsListExclusiveTemp1);
		operators_BushyTreeLeftOptional.addAll(JoinGroupsListExclusiveTemp1);
//operators_BushyTreeRight.parallelStream();

/////////////////////////////Ordering JoinGroupsListExclusive ////////////////////////

for(EdgeOperator jgle1:operators_BushyTreeLeftOptional)
System.out.println("This is now JoinGroupsListExclusive jgle1:"+jgle1);

//for(Entry<Integer, List<EdgeOperator>> jgle1:joinGroups2.entrySet())
////logger.info("This is now JoinGroups2:"+jgle1);

////logger.info("This is now the original order:"+operators_BushyTreeOrder);

////logger.info("This is JoinGroupsListExclusive:"+JoinGroupsListExclusive);
////logger.info("This is operators_BushyTreeLeft:"+operators_BushyTreeLeft);
		for(List<EdgeOperator> jgl:JoinGroupsListOptional)
			System.out.println("This i the optional problem here33333333:"+jgl);
for(EdgeOperator jgl:operators_BushyTreeLeftOptional)
	System.out.println("This i the optional problem here333333333333:"+jgl);

		if (operators_BushyTreeLeftOptional.size() > 0) {
			joinGroupsLeftOptional = CreateBushyTreesLeft(operators_BushyTreeLeftOptional, operators,
					BGPEval.operators_BushyTreeOrder);

			for (Entry<EdgeOperator, Integer> e : joinGroupsLeftOptional.entries()) {
				// logger.info("This is the new group of queries123123
				// left:"+e.getKey()+"--"+e.getValue());
				if (!JoinGroupsListLeftOptional.contains(e.getKey()))
					JoinGroupsListLeftOptional.add(e.getKey());
			}

		
			
			Iterator<EdgeOperator> jgll = JoinGroupsListLeftOptional.iterator();
			while (jgll.hasNext()) {
				com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator jglenl = jgll.next();
//if(jglenl.size()==1) {
				if (operators_BushyTreeRightOptional.toString().contains(jglenl.toString())) {
					operators_BushyTreeRightOptional.remove(jglenl);

				}
//}
			}

			
			for(EdgeOperator jgl:JoinGroupsListLeftOptional)
				System.out.println("This i the optional problem here99999999999:"+jgl);
		
			Iterator<List<EdgeOperator>> jgrrr = JoinGroupsListOptional.iterator();
			while (jgrrr.hasNext()) {
				List<EdgeOperator> jglenr = jgrrr.next();
//if(jglenl.size()==1) {

				Iterator<EdgeOperator> jgr111 = operators_BushyTreeRightOptional.iterator();
				while (jgr111.hasNext()) {
					if (jglenr.contains(jgr111.next())) {
						jgr111.remove();
						;

					}
				}
			}
			Iterator<EdgeOperator> jgrrl = JoinGroupsListLeftOptional.iterator();
			while (jgrrl.hasNext()) {
				EdgeOperator jglenrl = jgrrl.next();
//if(jglenl.size()==1) {

				Iterator<EdgeOperator> jgr1112 = operators_BushyTreeRightOptional.iterator();
				while (jgr1112.hasNext()) {
					if (jglenrl.equals(jgr1112.next())) {
						jgr1112.remove();
						;

					}
				}
			}
			
			for(List<EdgeOperator> jgl:JoinGroupsListOptional)
				System.out.println("This i the optional problem here333333333333:"+jgl);
			for(EdgeOperator jgl:JoinGroupsListLeftOptional)
				System.out.println("1This i the optional problem here33333333333:"+jgl);
			for(EdgeOperator jgl:JoinGroupsListRightOptional)
				System.out.println("1This i the optional problem here44444444444:"+jgl);

			if (operators_BushyTreeRightOptional != null)
				joinGroupsRightOptional = CreateBushyTreesRight(operators_BushyTreeRightOptional, operators,
						BGPEval.operators_BushyTreeOrder);

			if (joinGroupsRightOptional != null)
				for (Entry<EdgeOperator, Integer> e : joinGroupsRightOptional.entries()) {
					// logger.info("This is the new group of
					// queries123123:"+e.getKey()+"--"+e.getValue());
					if (!JoinGroupsListRightOptional.contains(e.getKey()))
						JoinGroupsListRightOptional.add(e.getKey());
				}
		}
		///// logger.info("This is now right bushy tree:"+joinGroups1);

		// for(int i=0;i<JoinGroupsList.size();i++)
		/*
		 * for(List<EdgeOperator> jglr:JoinGroupsListExclusive) for(EdgeOperator
		 * jglr1:jglr) if(JoinGroupsList.toString().contains(jglr1.toString())) {
		 * for(List<EdgeOperator> jgl:JoinGroupsList)
		 * 
		 * 
		 * }
		 */
		// logger.info("This is left group tree:"+JoinGroupsListLeft);

		// logger.info("This is right group tree:"+JoinGroupsListRight);
		Iterator<List<EdgeOperator>> xyz = JoinGroupsListOptional.iterator();
		while (xyz.hasNext()) {
			List<EdgeOperator> xyz1 = xyz.next();
			if (xyz1.size() == 0)
				xyz.remove();
		}

		//// logger.info("This is the new group list of
		//// JoinGroupsListRight:"+operators_BushyTreeRight);

//		//logger.info("This is the new group list of JoinGroupsListLeft:"+operators_BushyTreeLeft);

		// ExecOrder
		// logger.info("This is now
		// operators_BushyTreeRight:"+operators_BushyTreeRight);
		List<EdgeOperator> JoinGroupsListExclusiveTemp = new Vector<>();
		/*
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) { for(List<EdgeOperator>
		 * jg2:JoinGroupsListExclusive) {
		 * 
		 * for(EdgeOperator jg22:jg2) {
		 * if(jg22.getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp.
		 * contains(jg22) ) JoinGroupsListExclusiveTemp.add(jg22); }
		 * 
		 * } if(!JoinGroupsListExclusiveTempT.contains(JoinGroupsListExclusiveTemp))
		 * JoinGroupsListExclusiveTempT.add(JoinGroupsListExclusiveTemp);
		 * JoinGroupsListExclusiveTemp =new Vector<>();
		 * 
		 * }
		 * 
		 * 
		 * // Map<List<EdgeOperator>, Integer> JoinGroupsListExclusiveTempTV =new
		 * ConcurrentHashMap<>();
		 * 
		 * // for(Entry<List<EdgeOperator>, Integer> jg2:joinGroups2.entrySet()) //
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) //
		 * if(jg2.getKey().get(0).getEdge().equals(obt1.getEdge())&&!
		 * JoinGroupsListExclusiveTempTV.containsKey(jg2.getKey()) ) //
		 * JoinGroupsListExclusiveTempTV.put(jg2.getKey(),jg2.getValue());
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //joinGroups2.clear();
		 * 
		 * // joinGroups2.putAll(JoinGroupsListExclusiveTempTV);
		 * 
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) for(Entry<List<EdgeOperator>,
		 * Integer> jg2:joinGroups2.entrySet()) if(jg2.getKey().size()>0)
		 * if(obt1.toString().equals(jg2.getKey().get(0).toString()))
		 * joinGroups2Temp1.put(jg2.getKey(),jg2.getValue());
		 * 
		 * joinGroups2.clear(); joinGroups2.putAll(joinGroups2Temp1);
		 * 
		 */
		
		
		for(List<EdgeOperator> jgl:JoinGroupsListOptional)
			System.out.println("This i the optional problem here44444444:"+jgl);

		if (JoinGroupsListRightOptional.size() > 0 || !JoinGroupsListRightOptional.isEmpty()) {
			JoinGroupsListExclusiveTemp = new Vector<>();
			for (EdgeOperator jg2 : JoinGroupsListRightOptional) {
				for (EdgeOperator obt1 : operators_BushyTreeOrder) {
					if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp.contains(jg2))
						JoinGroupsListExclusiveTemp.add(jg2);

				}

			}

			JoinGroupsListRightOptional.clear();

			JoinGroupsListRightOptional.addAll(JoinGroupsListExclusiveTemp);

		}
		
	//	for(EdgeOperator jgl:JoinGroupsListLeftOptional)
	//		System.out.println("2This i the optional problem here33333333333:"+jgl);
	//	for(EdgeOperator jgl:JoinGroupsListRightOptional)
	//		System.out.println("2This i the optional problem here44444444444:"+jgl);

		
		if (JoinGroupsListLeftOptional.size() > 0 || !JoinGroupsListLeftOptional.isEmpty()) {

			JoinGroupsListExclusiveTemp = new Vector<>();
			for (EdgeOperator obt1 : BGPEval.operators_BushyTreeOrder) {
				for (EdgeOperator jg2 : JoinGroupsListLeftOptional) {
					if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp.contains(jg2))
						JoinGroupsListExclusiveTemp.add(jg2);

				}

			}

			JoinGroupsListLeftOptional.clear();

			JoinGroupsListLeftOptional.addAll(JoinGroupsListExclusiveTemp);
		}
		for(EdgeOperator jgl:JoinGroupsListLeftOptional)
			System.out.println("6This i the optional problem here33333333333:"+jgl);
		for(EdgeOperator jgl:JoinGroupsListRightOptional)
			System.out.println("6This i the optional problem here44444444444:"+jgl);

		/*
		for(List<EdgeOperator> jgl:JoinGroupsListOptional)
			System.out.println("This i the optional problem here6666666662:"+jgl);
		for(EdgeOperator jgl:JoinGroupsListLeftOptional)
			System.out.println("This i the optional left problem here6666666662:"+jgl);
		for(EdgeOperator jgl:JoinGroupsListRightOptional)
			System.out.println("This i the optional right problem here6666666662:"+jgl);
	List<EdgeOperator> v=new ArrayList<>();
	
	for(List<EdgeOperator> jglo:JoinGroupsListOptional)
	for(EdgeOperator jglo1:jglo)
		v.add(jglo1);
	for(EdgeOperator jglo1:JoinGroupsListRightOptional)
		v.add(jglo1);
	for(EdgeOperator jglo1:JoinGroupsListLeftOptional)
		v.add(jglo1);
	for(EdgeOperator v1:v)
		{if(v1.getEdge().getV1().getNode().isURI())
		BGPEval.urim.replace(v1.getEdge().getV1(),null);
		if(v1.getEdge().getV2().getNode().isURI())
			BGPEval.urim.replace(v1.getEdge().getV2(),null);
		}*/
	
		Iterator<List<EdgeOperator>> jglll = JoinGroupsListOptional.iterator();
		while (jglll.hasNext()) {
			List<EdgeOperator> jglenl = jglll.next();
//if(jglenl.size()==1) {

			Iterator<EdgeOperator> jgl111 = JoinGroupsListLeftOptional.iterator();
			while (jgl111.hasNext()) {
				EdgeOperator z = jgl111.next();

				if (jglenl.toString().contains(z.toString())) {
					jgl111.remove();
				}
			}

//}
		}
	
	
		Iterator<List<EdgeOperator>> jglll1 = JoinGroupsListOptional.iterator();
		while (jglll1.hasNext()) {
			List<EdgeOperator> jglenl = jglll1.next();
//if(jglenl.size()==1) {

			Iterator<EdgeOperator> jgl111 = JoinGroupsListRightOptional.iterator();
			while (jgl111.hasNext()) {
				EdgeOperator z = jgl111.next();

				if (jglenl.toString().contains(z.toString())) {
					jgl111.remove();
				}
			}

//}
		}

	
	
		
		Set<List<EdgeOperator>>JoinGroupsListExclusiveTemp11 = new HashSet<>();
		Set<EdgeOperator> JoinGroupsListExclusiveTemp112 = new HashSet<>();
		
		for(List<EdgeOperator>jgl:JoinGroupsListOptional) {
			if(!JoinGroupsListExclusiveTemp11.contains(jgl))
			JoinGroupsListExclusiveTemp11.add(jgl);
		}
		JoinGroupsListOptional.clear();
		
		JoinGroupsListOptional.addAll(JoinGroupsListExclusiveTemp11);
		
		for(EdgeOperator jgl:JoinGroupsListRightOptional) {
			if(!JoinGroupsListExclusiveTemp112.toString().contains(jgl.toString()))
			JoinGroupsListExclusiveTemp112.add(jgl);
		}
		for(EdgeOperator jgr:JoinGroupsListExclusiveTemp112)
			{ 
			System.out.println("These are the edgeoperator right:"+jgr);
		//	for(int i=0; i<jgr.toString().length(); i++)
		//	{
		//	  int asciiValue = jgr.toString().charAt(i);
		//	  System.out.print(" "+jgr.toString().charAt(i) + "=" + asciiValue);
		//	}
			}  
			
		JoinGroupsListRightOptional.clear();
		for(EdgeOperator jgr:JoinGroupsListExclusiveTemp112)
		if(!JoinGroupsListRightOptional.toString().contains(jgr.toString()))
		JoinGroupsListRightOptional.addAll(JoinGroupsListExclusiveTemp112);

		JoinGroupsListExclusiveTemp112.clear();
		for(EdgeOperator jgl:JoinGroupsListLeftOptional) {
			JoinGroupsListExclusiveTemp112.add(jgl);
		}
		JoinGroupsListLeftOptional.clear();
		
		for(EdgeOperator jgr:JoinGroupsListExclusiveTemp112)
			if(!JoinGroupsListLeftOptional.toString().contains(jgr.toString()))
			JoinGroupsListLeftOptional.addAll(JoinGroupsListExclusiveTemp112);
		
		for(EdgeOperator jgr:JoinGroupsListRightOptional)
		System.out.println("These are the edgeoperator right:"+jgr);
		JoinGroupsListExclusiveTemp112.clear();
			System.out.println("This is the new group time:" + LocalTime.now());
			

	}

	public static void CompleteMinus(ConcurrentHashMap<EdgeOperator, HashSet<Integer>> newFormationM,
			List<EdgeOperator> operators, List<EdgeOperator> operators_BushyTreeOrder) {
		int ll = 0;
		ConcurrentHashMap<EdgeOperator, HashSet<Integer>> newFormation = new ConcurrentHashMap<>();
		JoinGroupsListMinus.clear();
		List<EdgeOperator> operators_BushyTreeLeftOptional = new ArrayList<>();
		List<EdgeOperator> operators_BushyTreeRightOptional = new ArrayList<>();
		newFormationM.clear();

		for (Entry<List<EdgeOperator>, Integer> e4 : joinGroupsMinus1.entries()) {
			for (EdgeOperator e6 : e4.getKey()) {

				if (newFormation.size() == 0 || !newFormation.containsKey(e6)) {
					HashSet<Integer> bb = new HashSet<>();
					bb.add(e4.getValue());
					newFormation.put(e6, bb);

				} else {
					for (Entry<EdgeOperator, HashSet<Integer>> nf : newFormation.entrySet()) {
						if (nf.getKey().equals(e6)) {
							HashSet<Integer> bb = new HashSet<>();
							bb.addAll(nf.getValue());
							bb.add(e4.getValue());
							if (bb.size() > 1)
								newFormation.put(e6, bb);
						}

					}
				}
				// //logger.info("This is the new new new:"+e4.getKey()+"--"+e6);
			}

			JoinGroupsListMinus.add(e4.getKey());
		}

//for(Entry<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator, HashSet<Integer>> nf:newFormation.entrySet())
////logger.info("This is the new new new11:"+newFormation);
		Set<EdgeOperator> URIbased = new HashSet<>();
		Set<EdgeOperator> uris = new HashSet<>();

		for (List<EdgeOperator> nf1 : JoinGroupsListMinus)
			for (EdgeOperator nf : nf1)
				if (nf.getEdge().getV1().getNode().isURI() || nf.getEdge().getV2().getNode().isURI()) {
					URIbased.add(nf);

				}
		int equality = 0;
		for (EdgeOperator uri : URIbased)
			for (EdgeOperator uri1 : URIbased) {

				if ((uri.getEdge().getV1().equals(uri1.getEdge().getV2())
						|| uri.getEdge().getV2().equals(uri1.getEdge().getV1()))
						&& (uri.getEdge().getV1().getNode().isURI() || uri.getEdge().getV2().getNode().isURI())
						&& (uri1.getEdge().getV1().getNode().isURI() || uri1.getEdge().getV2().getNode().isURI())) {
					// System.out.println("This is the focus
					// here:"+Optimizer.getEndpointE(uri.getEdge().getTriple()).toString()+"--"+Optimizer.getEndpointE(uri1.getEdge().getTriple()).toString()+"--"+equality);
					equality = 0;

					for (Endpoint op1 : Optimizer.getEndpointE(uri.getEdge().getTriple())) {
						for (Endpoint op2 : Optimizer.getEndpointE(uri1.getEdge().getTriple()))
							if (op1.equals(op1)) {
								equality++;
								System.out.println("This is the focus here:" + op1 + "--" + op2 + "--" + equality);

							}

						if (equality == 2) {

							uris.add(uri);
							uris.add(uri1);
							System.out.println("This is the focus here2:" + uris + "--" + equality);

							break;
						}
						if (equality == 2)
							break;
					}

				}
			}

		for (Entry<EdgeOperator, HashSet<Integer>> nf : newFormation.entrySet()) {
			if (nf.getValue().size() > 1) {
				newFormationM.put(nf.getKey(), nf.getValue());
			}
		}

//System.out.println("This is Old joinGroup2:"+newFormationM);
//System.out.println("This is Old joinGroup333:"+JoinGroupsListExclusive);

//LinkedHashMap<Integer, List<EdgeOperator>> uuu;
//HashSet<EdgeOperator> ExistingEdges = new HashSet<>(); 

		operators_BushyTreeLeftOptional.clear();

		operators_BushyTreeRightOptional.clear();

		/*
		 * int isDoubled=0; int isTripled=0; int CompletlyNewFormation=0;
		 * List<EdgeOperator> leSafe= new ArrayList<>(); List<EdgeOperator>
		 * leSafeNonConcrete= new ArrayList<>();
		 * 
		 * Iterator<List<EdgeOperator>> jgle = JoinGroupsListExclusive.iterator() ;
		 * while(jgle.hasNext()) {
		 * List<com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator>
		 * jglen = jgle.next(); //CompletlyNewFormation=0; for(Entry<EdgeOperator,
		 * HashSet<Integer>> nf:newFormationM.entrySet()) {
		 * //Iterator<List<EdgeOperator>> le = JoinGroupsListExclusive.iterator() ;
		 * 
		 * // while(le.hasNext()) // { // List<EdgeOperator> le2 = le.next();
		 * Iterator<EdgeOperator> le3 = jglen.iterator(); EdgeOperator le1;
		 * while(le3.hasNext()) { le1= le3.next();
		 * if(le1.getEdge().getTriple().getSubject().isURI()
		 * ||le1.getEdge().getTriple().getSubject().isLiteral()) { isTripled++; //
		 * if(!ConcreteEdge.contains(le1.getEdge().getTriple().getObject()))
		 * 
		 * //ConcreteEdge.add(le1.getEdge().getTriple().getObject()); continue; }
		 * 
		 * if( le1.getEdge().getTriple().getObject().isURI()
		 * ||le1.getEdge().getTriple().getObject().isLiteral()) { isTripled++;
		 * //if(!ConcreteEdge.contains(le1.getEdge().getTriple().getSubject()))
		 * //C/oncreteEdge.add(le1.getEdge().getTriple().getSubject()); continue; }
		 * 
		 * 
		 * /* if(!nf.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nf.getKey().getEdge().getTriple().getSubject().isConcrete()) { for(Node ce:
		 * ConcreteEdge) { if(nf.getKey().getEdge().getTriple().getObject().equals(ce) )
		 * CompletlyNewFormation=1; // //logger.info(""); if(
		 * nf.getKey().getEdge().getTriple().getSubject().equals(ce))
		 * CompletlyNewFormation=1; ////logger.info(""); } }
		 * 
		 * 
		 * if(CompletlyNewFormation==0) { isDoubled++;
		 * 
		 * // //log.info("This is total size of triple group:"+"--"+jglen.size());
		 * if(!leSafeNonConcrete.contains(nf.getKey()))
		 * leSafeNonConcrete.add(nf.getKey()); } CompletlyNewFormation=1;
		 * 
		 * if((nf.getKey().getEdge().getTriple().getSubject().toString().equals(le1.
		 * getEdge().getTriple().getObject().toString()) ||
		 * nf.getKey().getEdge().getTriple().getObject().toString().equals(le1.getEdge()
		 * .getTriple().getSubject().toString())) &&
		 * !nf.getKey().getEdge().toString().equals(le1.getEdge().toString())) { if
		 * ((le1.getEdge().getTriple().getSubject().isConcrete() &&
		 * !le1.getEdge().getTriple().getObject().isConcrete())) //isDoubled++;
		 * isTripled++; if ((le1.getEdge().getTriple().getObject().isConcrete() &&
		 * !le1.getEdge().getTriple().getSubject().isConcrete())) //isDoubled++;
		 * isTripled++; if ((nf.getKey().getEdge().getTriple().getSubject().isConcrete()
		 * && !nf.getKey().getEdge().getTriple().getObject().isConcrete()))
		 * //isDoubled++; isTripled++; if
		 * ((nf.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nf.getKey().getEdge().getTriple().getSubject().isConcrete())) //isDoubled++;
		 * isTripled++; } leSafe.add(le1);
		 * 
		 * } if(isTripled>1) { //isTripled=0; continue; }
		 * 
		 * 
		 * 
		 * if(isTripled==0 ) { for(Entry<EdgeOperator, HashSet<Integer>>
		 * nf1:newFormationM.entrySet()) {
		 * 
		 * if(jglen.contains(nf1.getKey())) jglen.remove(nf1.getKey());
		 * if(!operators_BushyTreeLeft.contains(nf1.getKey()) &&
		 * ((!nf1.getKey().getEdge().getTriple().getSubject().isURI()
		 * ||!nf1.getKey().getEdge().getTriple().getSubject().isLiteral()) ||
		 * (!nf1.getKey().getEdge().getTriple().getObject().isURI() ||
		 * !nf1.getKey().getEdge().getTriple().getObject().isLiteral())) )
		 * {operators_BushyTreeLeft.add(nf1.getKey());
		 * operators_BushyTreeRight.add(nf1.getKey()); }
		 * 
		 * if(jglen.size()==1) {
		 * if(!operators_BushyTreeLeft.toString().contains(jglen.toString())) {
		 * operators_BushyTreeLeft.addAll(jglen);
		 * operators_BushyTreeRight.addAll(jglen);
		 * 
		 * }
		 * 
		 * // jgle.remove(); } }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * // isDoubled=0;
		 * 
		 * } } // }
		 * 
		 * 
		 * 
		 * if(isDoubled>0) { //for(Entry<EdgeOperator, HashSet<Integer>>
		 * nf1:newFormationM.entrySet()) //{ for(EdgeOperator le11:leSafeNonConcrete) {
		 * EdgeOperator a11= le11; for(int i=0;i<JoinGroupsListExclusive.size();i++)
		 * if(!JoinGroupsListExclusive.get(i).toString().contains("http")) {
		 * JoinGroupsListExclusive.get(i).remove(a11);
		 * if(!operators_BushyTreeLeft.contains(le11) ) {
		 * operators_BushyTreeLeft.add(le11); operators_BushyTreeRight.add(le11); } } //
		 * }
		 * 
		 * // } // } //} }
		 * 
		 * 
		 * }
		 * 
		 */

		/*
		 * for(int i=0 ;i<JoinGroupsListExclusive.size();i++) for(int
		 * j=0;j<JoinGroupsListExclusive.get(i).size();j++) for(Node ce:ConcreteEdge)
		 * if(!JoinGroupsListExclusive.get(i).get(j).getEdge().getTriple().getObject().
		 * equals(ce) &&
		 * !JoinGroupsListExclusive.get(i).get(j).getEdge().getTriple().getSubject().
		 * equals(ce)) {
		 * operators_BushyTreeLeft.add(JoinGroupsListExclusive.get(i).get(j));
		 * operators_BushyTreeRight.add(JoinGroupsListExclusive.get(i).get(j));
		 * JoinGroupsListExclusive.get(i).remove(j); }
		 */
		List<EdgeOperator> inclusion = new ArrayList<>();
		Iterator<List<EdgeOperator>> jgIterator = JoinGroupsListMinus.iterator();// joinGroups2.keySet().iterator();
		int ij1 = 0;
		while (jgIterator.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator.next();
			for (Entry<EdgeOperator, HashSet<Integer>> nfm : newFormationM.entrySet()) {

				if (aa.contains(nfm.getKey())) {
					inclusion.add(nfm.getKey());
//	jgIterator.remove();;
					if (!uris.contains(nfm.getKey())) {
						operators_BushyTreeLeftOptional.add(nfm.getKey());
						operators_BushyTreeRightOptional.add(nfm.getKey());
					}
					System.out.println("this is problem11111:" + aa);

//}
//}
//}
				}
			}
		}

		jgIterator = JoinGroupsListMinus.iterator();// joinGroups2.keySet().iterator();

		while (jgIterator.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator.next();
			for (EdgeOperator e : inclusion)
				aa.remove(e);
		}
		List<EdgeOperator> namesList1 = uris.parallelStream().collect(Collectors.toList());

		JoinGroupsListMinus.add(namesList1);
//JoinGroupsListExclusive.remove(inclusion);
		/*
		 * Iterator<List<EdgeOperator>> jgIterator1 =
		 * JoinGroupsListExclusive.iterator(); int ij=0;
		 * 
		 * while(jgIterator1.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
		 * aa=jgIterator1.next(); if(aa.size()==1 ) { for(EdgeOperator aaa:aa) { //
		 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
		 * ()); for(List<EdgeOperator> jge:JoinGroupsListExclusive)
		 * if((aaa.getEdge().getTriple().getObject().isConcrete() ||
		 * aaa.getEdge().getTriple().getSubject().isConcrete()) && jge.size()>1)
		 * for(EdgeOperator jgle:jge) {
		 * if(jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getSubject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getSubject())) { jge.add(aaa); aa.remove(aaa); } ij=1; break; } if(ij==1)
		 * break;
		 * 
		 * if(ij==1) {ij=0; break;}
		 * 
		 * }
		 * 
		 * } }
		 * 
		 * Iterator<List<EdgeOperator>> jgIterator11 = joinGroups2.values().iterator();
		 * int ij11=0;
		 * 
		 * while(jgIterator11.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
		 * aa=jgIterator11.next(); if(aa.size()==1 ) { for(EdgeOperator aaa:aa) { //
		 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
		 * ()); for(Entry<Integer, List<EdgeOperator>> jgel:joinGroups2.entrySet())
		 * if((aaa.getEdge().getTriple().getObject().isConcrete() ||
		 * aaa.getEdge().getTriple().getSubject().isConcrete()) &&
		 * jgel.getValue().size()>1) for(EdgeOperator jgle:jgel.getValue()) {
		 * if(jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getSubject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getSubject().equals(aaa.getEdge().getTriple().
		 * getObject()) ||
		 * jgle.getEdge().getTriple().getObject().equals(aaa.getEdge().getTriple().
		 * getSubject())) { jgel.getValue().add(aaa); aa.remove(aaa); } ij11=1; break; }
		 * if(ij11==1) break;
		 * 
		 * if(ij11==1) {ij11=0; break;}
		 * 
		 * }
		 * 
		 * } }
		 * 
		 *//*
			 * Iterator<List<EdgeOperator>> jgIterator111 =
			 * JoinGroupsListExclusive.iterator(); int ij111=0;
			 * 
			 * while(jgIterator111.hasNext()) { List<EdgeOperator> aa = new ArrayList<>();
			 * aa=jgIterator111.next(); if(aa.size()<=newFormationM.size() && aa.size()>1 )
			 * { for(EdgeOperator aaa:aa) { //
			 * //log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.size
			 * ()); for(Entry<EdgeOperator, HashSet<Integer>> nfm:newFormationM.entrySet())
			 * if(aaa.equals(nfm.getKey()) &&
			 * !aaa.getEdge().getTriple().getSubject().isConcrete()&&
			 * !aaa.getEdge().getTriple().getObject().isConcrete()) ij111++;
			 * if(ij111==aa.size()) if(aa.size()>1 || aa.isEmpty()==false) {
			 * 
			 * // System.out.println("1this is problem:"+aa);
			 * //if(!operators_BushyTreeRight.equals(aa)) {
			 * 
			 * // operators_BushyTreeRight.addAll(aa); //
			 * operators_BushyTreeLeft.addAll(aa); // } // jgIterator111.remove();
			 * //System.out.println("1this is problem11:"+aa);
			 * 
			 * } } } }
			 */

		/*
		 * Iterator<List<EdgeOperator>> obtIterator99; Iterator<List<EdgeOperator>>
		 * jg2Iterator99; List<EdgeOperator> obt99; boolean isString=false;
		 * obtIterator99 = JoinGroupsListExclusive.iterator();
		 * while(obtIterator99.hasNext()) { obt99 = obtIterator99.next(); for(int i=0;
		 * i<obt99.size();i++) {
		 * if(obt99.get(i).getEdge().getTriple().getObject().isConcrete() ||
		 * obt99.get(i).getEdge().getTriple().getSubject().isConcrete()) {isString=true;
		 * break; } } if(isString==true) { isString=false; jg2Iterator99 =
		 * JoinGroupsListExclusive.iterator(); List<EdgeOperator> jg99;
		 * while(jg2Iterator99.hasNext()) { jg99=jg2Iterator99.next();
		 * if(!obt99.equals(jg99))
		 * 
		 * for(EdgeOperator jg991:jg99) { if(obt99.contains(jg991)) jg99.remove(jg991);
		 * } } }
		 * 
		 * }
		 */

		for(List<EdgeOperator> jgl:JoinGroupsListMinus)
			System.out.println("This i the optional problem here:"+jgl);
		Iterator<List<EdgeOperator>> jgIterator3 = joinGroupsMinus1.keySet().iterator();

		while (jgIterator3.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator3.next();
			if (aa.size() == 1) {

				if (!operators_BushyTreeRightOptional.equals(aa)) {

					operators_BushyTreeRightOptional.addAll(aa);
					operators_BushyTreeLeftOptional.addAll(aa);
				}
				jgIterator3.remove();
			}
		}
		for (EdgeOperator uri : uris) {
			operators_BushyTreeRightOptional.remove(uri);
			operators_BushyTreeLeftOptional.remove(uri);

		}
////logger.info("This is the task of eternity:"+joinGroups2);
		Iterator<List<EdgeOperator>> jgIterator1111 = JoinGroupsListMinus.iterator();

		while (jgIterator1111.hasNext()) {
			List<EdgeOperator> aa = new ArrayList<>();
			aa = jgIterator1111.next();
			if (aa.size() == 1) {
				if (!operators_BushyTreeRightOptional.equals(aa)) {
					operators_BushyTreeRightOptional.addAll(aa);
					operators_BushyTreeLeftOptional.addAll(aa);
				}

				jgIterator1111.remove();
			}
		}
		for(List<EdgeOperator> jgl:JoinGroupsListMinus)
			System.out.println("This i the optional problem here111111111111:"+jgl);

/////////////////////////////Seperating SourceStatements from Exclusive Group and forming Left/Right Bushy Tree ////////////////////////

/////////////////////////////Ordering JoinGroupsListExclusive ////////////////////////

////////////////////////////////////////////////////////////////////////////
		/*
		 * Iterator<List<EdgeOperator>> jgIterator6 =
		 * JoinGroupsListExclusive.iterator();
		 * 
		 * List<EdgeOperator> jg = new ArrayList<EdgeOperator>();
		 * while(jgIterator6.hasNext()) {int ij6=0;
		 * 
		 * List<EdgeOperator> aa = new ArrayList<>(); aa=jgIterator6.next();
		 * if(aa.size()>1 ) { for(Entry<EdgeOperator, HashSet<Integer>>
		 * nfm:newFormationM.entrySet()) { int isConcrete=0; for(List<EdgeOperator>
		 * joinG:JoinGroupsListExclusive) { for(EdgeOperator joinG1:joinG) {
		 * if(joinG1.getEdge().getTriple().getObject().equals(nfm.getKey().getEdge().
		 * getTriple().getSubject()) &&
		 * joinG1.getEdge().getTriple().getSubject().isConcrete()) isConcrete=1; } }
		 * 
		 * if(aa.contains(nfm.getKey()) &&
		 * !nfm.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nfm.getKey().getEdge().getTriple().getSubject().isConcrete() &&
		 * isConcrete==0) {ij6++; jg.add(nfm.getKey()); } } if(aa.size()>=ij6 &&ij6>0)
		 * //for(EdgeOperator aaa:jg) {
		 * ////log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.
		 * size()); for(EdgeOperator j:jg) { if(!operators_BushyTreeRight.contains(j)) {
		 * operators_BushyTreeRight.addAll(jg); operators_BushyTreeLeft.addAll(jg); }
		 * aa.remove(j);
		 * 
		 * } //}
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * Iterator<List<EdgeOperator>> jgIterator7 = joinGroups2.values().iterator();
		 * List<EdgeOperator> jg1 = new ArrayList<EdgeOperator>();
		 * 
		 * while(jgIterator7.hasNext()) {int ij7=0; List<EdgeOperator> aa = new
		 * ArrayList<>(); aa=jgIterator7.next(); if(aa.size()>1 ) {
		 * for(Entry<EdgeOperator, HashSet<Integer>> nfm:newFormationM.entrySet()) { int
		 * isConcrete=0; for(List<EdgeOperator> joinG:JoinGroupsListExclusive) {
		 * for(EdgeOperator joinG1:joinG) {
		 * if(joinG1.getEdge().getTriple().getObject().equals(nfm.getKey().getEdge().
		 * getTriple().getSubject()) &&
		 * joinG1.getEdge().getTriple().getSubject().isConcrete()) isConcrete=1; } }
		 * 
		 * if(aa.contains(nfm.getKey()) &&
		 * !nfm.getKey().getEdge().getTriple().getObject().isConcrete() &&
		 * !nfm.getKey().getEdge().getTriple().getSubject().isConcrete() &&
		 * isConcrete==0) {ij7++; jg.add(nfm.getKey());
		 * 
		 * } } if(aa.size()>=ij7 &&ij7>0) //for(EdgeOperator aaa:jg) {
		 * ////log.info("aa.size()<newFormation.size():"+aa.size()+"--"+newFormationM.
		 * size()); for(EdgeOperator j:jg1) { if(!operators_BushyTreeRight.contains(j))
		 * { operators_BushyTreeRight.addAll(jg1); operators_BushyTreeLeft.addAll(jg1);
		 * } aa.remove(j); } //}
		 * 
		 * } }
		 */

		// ForkJoinPool fjp98 = new ForkJoinPool();
		// fjp98.submit(() -> futileTripleOptional());
		// fjp98.shutdown();

		// ForkJoinPool fjp97 = new ForkJoinPool();
		// fjp97.submit(() -> refineTripleOptional(newFormationM,
		// operators_BushyTreeLeftOptional,
		// operators_BushyTreeRightOptional));
		// fjp97.shutdown();

		for (int i = 0; i < JoinGroupsListMinus.size(); i++) {
			if (JoinGroupsListMinus.get(i).size() == 1) {
				operators_BushyTreeRightOptional.addAll(JoinGroupsListMinus.get(i));
				operators_BushyTreeLeftOptional.addAll(JoinGroupsListMinus.get(i));

				JoinGroupsListMinus.remove(i);
			}
		}
		/*
		 * obtIterator=null; jg2Iterator=null; ax=null; obt=null; obtIterator =
		 * joinGroups2.values().iterator(); while(obtIterator.hasNext()) { int
		 * comparedSize=0; obt = obtIterator.next(); jg2Iterator =
		 * joinGroups2.values().iterator(); while(jg2Iterator.hasNext()) {
		 * List<EdgeOperator> jg2=jg2Iterator.next(); size=jg2.size();
		 * if(obt.size()<size) {
		 * 
		 * for(EdgeOperator jg22:jg2) { if(obt.contains(jg22)) comparedSize++; }
		 * ax.add(obt);
		 * //log.info("This is the size for whole1:"+obt.size()+"--"+size);;
		 * 
		 * obtIterator.remove();
		 * 
		 * }
		 * 
		 * //log.info("THis is exclussss1:"+JoinGroupsListExclusive);
		 * 
		 * 
		 * } }
		 */
		////////////////////////////////////////////////////////////////////////////

////logger.info("This is the task of eternity222222222:"+JoinGroupsListExclusive);

////logger.info("This is now operators_BushyTreeRight:"+operators_BushyTreeRight);

////////////////////////////////////////////////////////////////////////////

//logger.info("This is the task of eternity222222222:"+JoinGroupsListExclusive);
		for(List<EdgeOperator> jgl:JoinGroupsListMinus)
			System.out.println("This i the optional problem here2222222222:"+jgl);

		List<EdgeOperator> JoinGroupsListExclusiveTemp1 = new Vector<>();
		for (EdgeOperator obt1 : operators_BushyTreeOrder)
			for (EdgeOperator jg2 : operators_BushyTreeRightOptional)
				if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp1.contains(jg2))
					JoinGroupsListExclusiveTemp1.add(jg2);

		operators_BushyTreeRightOptional.clear();
		operators_BushyTreeLeftOptional.clear();
		Collections.reverse(JoinGroupsListExclusiveTemp1);
		operators_BushyTreeRightOptional.addAll(JoinGroupsListExclusiveTemp1);
		operators_BushyTreeLeftOptional.addAll(JoinGroupsListExclusiveTemp1);
//operators_BushyTreeRight.parallelStream();

/////////////////////////////Ordering JoinGroupsListExclusive ////////////////////////

//for(List<EdgeOperator> jgle1:JoinGroupsListExclusive)
////logger.info("This is now JoinGroupsListExclusive:"+jgle1);

//for(Entry<Integer, List<EdgeOperator>> jgle1:joinGroups2.entrySet())
////logger.info("This is now JoinGroups2:"+jgle1);

////logger.info("This is now the original order:"+operators_BushyTreeOrder);

////logger.info("This is JoinGroupsListExclusive:"+JoinGroupsListExclusive);
////logger.info("This is operators_BushyTreeLeft:"+operators_BushyTreeLeft);
		for(List<EdgeOperator> jgl:JoinGroupsListMinus)
			System.out.println("This i the optional problem here33333333:"+jgl);

		if (operators_BushyTreeLeftOptional.size() > 0) {
			joinGroupsLeftMinus = CreateBushyTreesLeft(operators_BushyTreeLeftOptional, operators,
					operators_BushyTreeOrder);

			for (Entry<EdgeOperator, Integer> e : joinGroupsLeftMinus.entries()) {
				// logger.info("This is the new group of queries123123
				// left:"+e.getKey()+"--"+e.getValue());
				if (!JoinGroupsListLeftMinus.contains(e.getKey()))
					JoinGroupsListLeftMinus.add(e.getKey());
			}

			Iterator<EdgeOperator> jgll = JoinGroupsListLeftMinus.iterator();
			while (jgll.hasNext()) {
				com.fluidops.fedx.trunk.parallel.engine.exec.operator.EdgeOperator jglenl = jgll.next();
//if(jglenl.size()==1) {
				if (operators_BushyTreeRightOptional.toString().contains(jglenl.toString())) {
					operators_BushyTreeRightOptional.remove(jglenl);

				}
//}
			}

			Iterator<List<EdgeOperator>> jgrrr = JoinGroupsListMinus.iterator();
			while (jgrrr.hasNext()) {
				List<EdgeOperator> jglenr = jgrrr.next();
//if(jglenl.size()==1) {

				Iterator<EdgeOperator> jgr111 = operators_BushyTreeRightOptional.iterator();
				while (jgr111.hasNext()) {
					if (jglenr.contains(jgr111.next())) {
						jgr111.remove();
						;

					}
				}
			}
			Iterator<EdgeOperator> jgrrl = JoinGroupsListLeftMinus.iterator();
			while (jgrrl.hasNext()) {
				EdgeOperator jglenrl = jgrrl.next();
//if(jglenl.size()==1) {

				Iterator<EdgeOperator> jgr1112 = operators_BushyTreeRightOptional.iterator();
				while (jgr1112.hasNext()) {
					if (jglenrl.equals(jgr1112.next())) {
						jgr1112.remove();
						;

					}
				}
			}
			
			for(List<EdgeOperator> jgl:JoinGroupsListMinus)
				System.out.println("This i the optional problem here333333333333:"+jgl);

			if (operators_BushyTreeRightOptional != null)
				joinGroupsRightMinus = CreateBushyTreesRight(operators_BushyTreeRightOptional, operators,
						operators_BushyTreeOrder);

			if (joinGroupsRightMinus != null)
				for (Entry<EdgeOperator, Integer> e : joinGroupsRightMinus.entries()) {
					// logger.info("This is the new group of
					// queries123123:"+e.getKey()+"--"+e.getValue());
					if (!JoinGroupsListRightMinus.contains(e.getKey()))
						JoinGroupsListRightMinus.add(e.getKey());
				}
		}
		///// logger.info("This is now right bushy tree:"+joinGroups1);

		// for(int i=0;i<JoinGroupsList.size();i++)
		/*
		 * for(List<EdgeOperator> jglr:JoinGroupsListExclusive) for(EdgeOperator
		 * jglr1:jglr) if(JoinGroupsList.toString().contains(jglr1.toString())) {
		 * for(List<EdgeOperator> jgl:JoinGroupsList)
		 * 
		 * 
		 * }
		 */
		// logger.info("This is left group tree:"+JoinGroupsListLeft);

		// logger.info("This is right group tree:"+JoinGroupsListRight);
		Iterator<List<EdgeOperator>> xyz = JoinGroupsListMinus.iterator();
		while (xyz.hasNext()) {
			List<EdgeOperator> xyz1 = xyz.next();
			if (xyz1.size() == 0)
				xyz.remove();
		}

		//// logger.info("This is the new group list of
		//// JoinGroupsListRight:"+operators_BushyTreeRight);

//		//logger.info("This is the new group list of JoinGroupsListLeft:"+operators_BushyTreeLeft);

		// ExecOrder
		// logger.info("This is now
		// operators_BushyTreeRight:"+operators_BushyTreeRight);
		List<EdgeOperator> JoinGroupsListExclusiveTemp = new Vector<>();
		/*
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) { for(List<EdgeOperator>
		 * jg2:JoinGroupsListExclusive) {
		 * 
		 * for(EdgeOperator jg22:jg2) {
		 * if(jg22.getEdge().equals(obt1.getEdge())&&!JoinGroupsListExclusiveTemp.
		 * contains(jg22) ) JoinGroupsListExclusiveTemp.add(jg22); }
		 * 
		 * } if(!JoinGroupsListExclusiveTempT.contains(JoinGroupsListExclusiveTemp))
		 * JoinGroupsListExclusiveTempT.add(JoinGroupsListExclusiveTemp);
		 * JoinGroupsListExclusiveTemp =new Vector<>();
		 * 
		 * }
		 * 
		 * 
		 * // Map<List<EdgeOperator>, Integer> JoinGroupsListExclusiveTempTV =new
		 * ConcurrentHashMap<>();
		 * 
		 * // for(Entry<List<EdgeOperator>, Integer> jg2:joinGroups2.entrySet()) //
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) //
		 * if(jg2.getKey().get(0).getEdge().equals(obt1.getEdge())&&!
		 * JoinGroupsListExclusiveTempTV.containsKey(jg2.getKey()) ) //
		 * JoinGroupsListExclusiveTempTV.put(jg2.getKey(),jg2.getValue());
		 * 
		 * 
		 * 
		 * 
		 * 
		 * //joinGroups2.clear();
		 * 
		 * // joinGroups2.putAll(JoinGroupsListExclusiveTempTV);
		 * 
		 * for(EdgeOperator obt1:operators_BushyTreeOrder) for(Entry<List<EdgeOperator>,
		 * Integer> jg2:joinGroups2.entrySet()) if(jg2.getKey().size()>0)
		 * if(obt1.toString().equals(jg2.getKey().get(0).toString()))
		 * joinGroups2Temp1.put(jg2.getKey(),jg2.getValue());
		 * 
		 * joinGroups2.clear(); joinGroups2.putAll(joinGroups2Temp1);
		 * 
		 */
		
		
		for(List<EdgeOperator> jgl:JoinGroupsListMinus)
			System.out.println("This i the optional problem here44444444:"+jgl);

		if (JoinGroupsListRightMinus.size() > 0 || !JoinGroupsListRightMinus.isEmpty()) {
			JoinGroupsListExclusiveTemp = new Vector<>();
			for (EdgeOperator jg2 : JoinGroupsListRightMinus) {
				for (EdgeOperator obt1 : operators_BushyTreeOrder) {
					if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp.contains(jg2))
						JoinGroupsListExclusiveTemp.add(jg2);

				}

			}

			JoinGroupsListRightMinus.clear();

			JoinGroupsListRightMinus.addAll(JoinGroupsListExclusiveTemp);

		}
		if (JoinGroupsListLeftMinus.size() > 0 || !JoinGroupsListLeftMinus.isEmpty()) {

			JoinGroupsListExclusiveTemp = new Vector<>();
			for (EdgeOperator obt1 : operators_BushyTreeOrder) {
				for (EdgeOperator jg2 : JoinGroupsListLeftMinus) {
					if (jg2.getEdge().equals(obt1.getEdge()) && !JoinGroupsListExclusiveTemp.contains(jg2))
						JoinGroupsListExclusiveTemp.add(jg2);

				}

			}

			JoinGroupsListLeftMinus.clear();

			JoinGroupsListLeftMinus.addAll(JoinGroupsListExclusiveTemp);
		}
		for(List<EdgeOperator> jgl:JoinGroupsListMinus)
			System.out.println("This i the optional problem here6666666662:"+jgl);
		for(EdgeOperator jgl:JoinGroupsListLeftMinus)
			System.out.println("This i the optional left problem here6666666662:"+jgl);
		for(EdgeOperator jgl:JoinGroupsListRightMinus)
			System.out.println("This i the optional right problem here6666666662:"+jgl);
	}


	void refineTriple(ConcurrentHashMap<EdgeOperator, HashSet<Integer>> newFormationM,
			List<EdgeOperator> operators_BushyTreeLeft, List<EdgeOperator> operators_BushyTreeRight) {
		/// In the same set if triple pattern is not related to one with a URI
		// or has a mutliple source remove them
		// If a group does not have a URI with a single element then remove
		Iterator<List<EdgeOperator>> obt1Iterator1;
		Iterator<List<EdgeOperator>> jg3Iterator1;
		Iterator<EdgeOperator> jg4Iterator;
		List<EdgeOperator> obt11;
		List<EdgeOperator> tobeRemoved = new ArrayList<>();
		int ab1 = 0;
		int size = 0;
		int countConcrete = 0;
		List<Node> obtnew1 = new ArrayList<>();
		obt1Iterator1 = joinGroupsOptional1.keySet().iterator();
		while (obt1Iterator1.hasNext()) {

			obt11 = obt1Iterator1.next();
			jg3Iterator1 = JoinGroupsListExclusive.iterator();
			List<EdgeOperator> jg3;
			for (EdgeOperator bb : obt11) {
				if (bb.getEdge().getTriple().getObject().isConcrete()) {
					ab1++;
					//// logger.info("This is the first:");
					obtnew1.add(bb.getEdge().getTriple().getSubject());
				}

				if (bb.getEdge().getTriple().getSubject().isConcrete()) {
					ab1++;
					obtnew1.add(bb.getEdge().getTriple().getObject());
				}
			}

			if (ab1 > 0) {
				ab1 = 0;
				while (jg3Iterator1.hasNext()) {
					jg3 = jg3Iterator1.next();
					size = jg3.size();
					jg4Iterator = jg3.iterator();

					if (obt11.equals(jg3)) {

						while (jg4Iterator.hasNext())
						// for(EdgeOperator jg22:jg3)
						{

							EdgeOperator jg22 = jg4Iterator.next();
							if (!(obtnew1.contains(jg22.getEdge().getTriple().getSubject())
									|| obtnew1.contains(jg22.getEdge().getTriple().getObject()))) {
								if (!(jg22.getEdge().getTriple().getSubject().isURI()
										|| jg22.getEdge().getTriple().getObject().isURI()
										|| jg22.getEdge().getTriple().getSubject().isLiteral()
										|| jg22.getEdge().getTriple().getObject().isLiteral()))
								// JoinGroupsListExclusive.get(ab).remove(jg22);
								{
									if (newFormationM.containsKey(jg22)) {
										tobeRemoved.add(jg22);
										// obt1.remove(jg22);
										// operators_BushyTreeRight.addAll(jg3);
										// operators_BushyTreeLeft.addAll(jg3);
									}

//				operators_BushyTreeRight
									if (jg3.size() == 1) {
										if (!operators_BushyTreeRight.equals(jg3)) {
											operators_BushyTreeRight.addAll(jg3);
											operators_BushyTreeLeft.addAll(jg3);
										}
										jg3Iterator1.remove();
										break;
									}
								}
							}
						}
						if (!tobeRemoved.isEmpty())
							for (EdgeOperator tbr : tobeRemoved) {
								if (!operators_BushyTreeRight.contains(tbr)) {
									operators_BushyTreeRight.add(tbr);
									operators_BushyTreeLeft.add(tbr);
								}
								jg3.remove(tbr);
							}
						tobeRemoved.clear();
					}
					// ax.add(obt);
					//// logger.info("This is the size for whole:"+obt.size()+"--"+size);;

				}

				//// logger.info("THis is exclussss:"+JoinGroupsListExclusive);

			}

			if (ab1 == 0) {
				ab1 = 0;
				while (jg3Iterator1.hasNext()) {
					jg3 = jg3Iterator1.next();
					size = jg3.size();
					jg4Iterator = jg3.iterator();

					if (obt11.equals(jg3)) {

						while (jg4Iterator.hasNext())
						// for(EdgeOperator jg22:jg3)
						{

							EdgeOperator jg22 = jg4Iterator.next();
							if ((jg22.getEdge().getTriple().getSubject().isConcrete()
									|| jg22.getEdge().getTriple().getObject().isConcrete()
//						||	jg22.getEdge().getTriple().getSubject().isLiteral()|| jg22.getEdge().getTriple().getObject().isLiteral())	
							))
							// JoinGroupsListExclusive.get(ab).remove(jg22);
							{
								countConcrete++;
							}
							if (countConcrete > 0)
								continue;
							if (newFormationM.containsKey(jg22)) {
								tobeRemoved.add(jg22);
							}

//				operators_BushyTreeRight
							if (jg3.size() == 1) {
								if (!operators_BushyTreeRight.equals(jg3)) {
									operators_BushyTreeRight.addAll(jg3);
									operators_BushyTreeLeft.addAll(jg3);
								}
								jg3Iterator1.remove();
								break;
							}
						}

					}
					if (!tobeRemoved.isEmpty())
						for (EdgeOperator tbr : tobeRemoved) {
							if (!operators_BushyTreeRight.contains(tbr)) {
								operators_BushyTreeRight.add(tbr);
								operators_BushyTreeLeft.add(tbr);
							}
							jg3.remove(tbr);
						}
					tobeRemoved.clear();
				}
				// ax.add(obt);
				//// logger.info("This is the size for whole:"+obt.size()+"--"+size);;

				//// logger.info("THis is exclussss:"+JoinGroupsListExclusive);

			}
			countConcrete = 0;
		}

	}

	static void refineTripleOptional(ConcurrentHashMap<EdgeOperator, HashSet<Integer>> newFormationM,
			List<EdgeOperator> operators_BushyTreeLeft, List<EdgeOperator> operators_BushyTreeRight) {
		/// In the same set if triple pattern is not related to one with a URI
		// or has a mutliple source remove them
		// If a group does not have a URI with a single element then remove
		Iterator<List<EdgeOperator>> obt1Iterator1;
		Iterator<List<EdgeOperator>> jg3Iterator1;
		Iterator<EdgeOperator> jg4Iterator;
		List<EdgeOperator> obt11;
		List<EdgeOperator> tobeRemoved = new ArrayList<>();
		int ab1 = 0;
		int size = 0;
		int countConcrete = 0;
		List<Node> obtnew1 = new ArrayList<>();
		obt1Iterator1 = joinGroupsOptional1.keySet().iterator();
		while (obt1Iterator1.hasNext()) {

			obt11 = obt1Iterator1.next();
			jg3Iterator1 = JoinGroupsListOptional.iterator();
			List<EdgeOperator> jg3;
			for (EdgeOperator bb : obt11) {
				if (bb.getEdge().getTriple().getObject().isConcrete()) {
					ab1++;
					//// logger.info("This is the first:");
					obtnew1.add(bb.getEdge().getTriple().getSubject());
				}

				if (bb.getEdge().getTriple().getSubject().isConcrete()) {
					ab1++;
					obtnew1.add(bb.getEdge().getTriple().getObject());
				}
			}

			if (ab1 > 0) {
				ab1 = 0;
				while (jg3Iterator1.hasNext()) {
					jg3 = jg3Iterator1.next();
					size = jg3.size();
					jg4Iterator = jg3.iterator();

					if (obt11.equals(jg3)) {

						while (jg4Iterator.hasNext())
						// for(EdgeOperator jg22:jg3)
						{

							EdgeOperator jg22 = jg4Iterator.next();
							if (!(obtnew1.contains(jg22.getEdge().getTriple().getSubject())
									|| obtnew1.contains(jg22.getEdge().getTriple().getObject()))) {
								if (!(jg22.getEdge().getTriple().getSubject().isURI()
										|| jg22.getEdge().getTriple().getObject().isURI()
										|| jg22.getEdge().getTriple().getSubject().isLiteral()
										|| jg22.getEdge().getTriple().getObject().isLiteral()))
								// JoinGroupsListExclusive.get(ab).remove(jg22);
								{
									if (newFormationM.containsKey(jg22)) {
										tobeRemoved.add(jg22);
										// obt1.remove(jg22);
										// operators_BushyTreeRight.addAll(jg3);
										// operators_BushyTreeLeft.addAll(jg3);
									}

//				operators_BushyTreeRight
									if (jg3.size() == 1) {
										if (!operators_BushyTreeRight.equals(jg3)) {
											operators_BushyTreeRight.addAll(jg3);
											operators_BushyTreeLeft.addAll(jg3);
										}
										jg3Iterator1.remove();
										break;
									}
								}
							}
						}
						if (!tobeRemoved.isEmpty())
							for (EdgeOperator tbr : tobeRemoved) {
								if (!operators_BushyTreeRight.contains(tbr)) {
									operators_BushyTreeRight.add(tbr);
									operators_BushyTreeLeft.add(tbr);
								}
								jg3.remove(tbr);
							}
						tobeRemoved.clear();
					}
					// ax.add(obt);
					//// logger.info("This is the size for whole:"+obt.size()+"--"+size);;

				}

				//// logger.info("THis is exclussss:"+JoinGroupsListExclusive);

			}

			if (ab1 == 0) {
				ab1 = 0;
				while (jg3Iterator1.hasNext()) {
					jg3 = jg3Iterator1.next();
					size = jg3.size();
					jg4Iterator = jg3.iterator();

					if (obt11.equals(jg3)) {

						while (jg4Iterator.hasNext())
						// for(EdgeOperator jg22:jg3)
						{

							EdgeOperator jg22 = jg4Iterator.next();
							if ((jg22.getEdge().getTriple().getSubject().isConcrete()
									|| jg22.getEdge().getTriple().getObject().isConcrete()
//						||	jg22.getEdge().getTriple().getSubject().isLiteral()|| jg22.getEdge().getTriple().getObject().isLiteral())	
							))
							// JoinGroupsListExclusive.get(ab).remove(jg22);
							{
								countConcrete++;
							}
							if (countConcrete > 0)
								continue;
							if (newFormationM.containsKey(jg22)) {
								tobeRemoved.add(jg22);
							}

//				operators_BushyTreeRight
							if (jg3.size() == 1) {
								if (!operators_BushyTreeRight.equals(jg3)) {
									operators_BushyTreeRight.addAll(jg3);
									operators_BushyTreeLeft.addAll(jg3);
								}
								jg3Iterator1.remove();
								break;
							}
						}

					}
					if (!tobeRemoved.isEmpty())
						for (EdgeOperator tbr : tobeRemoved) {
							if (!operators_BushyTreeRight.contains(tbr)) {
								operators_BushyTreeRight.add(tbr);
								operators_BushyTreeLeft.add(tbr);
							}
							jg3.remove(tbr);
						}
					tobeRemoved.clear();
				}
				// ax.add(obt);
				//// logger.info("This is the size for whole:"+obt.size()+"--"+size);;

				//// logger.info("THis is exclussss:"+JoinGroupsListExclusive);

			}
			countConcrete = 0;
		}

	}

	@Override
	protected boolean hasNextBinding() {
		// //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
		// execBGP11!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+results);

		if (results == null) {
			// //logger.info("!!!!!!!!!!!!!!!!!!!!!!!This is here in
			// execBGP12!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!: "+results);
			// logger.info("This is after BGPEvalConstructor");
			// if(completionE<1) {
			try {
				System.out.println("This is result next213:");
				
				execBGP();
				//System.out.println("This is result next:"+results.hasNext());
			//	return results.hasNext();
				

			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
//		Iterable<Binding> newIterable1 = () -> results;
//		long count1 = StreamSupport.stream(newIterable1.spliterator(), false).count();
	//	System.out.println("This is filter values lsat1111111111:" + results.hasNext() );
		// StatementGroupOptimizer2 sgo = new StatementGroupOptimizer2(null);
		// sgo.meetNJoin(null);
		return results.hasNext();

		// }
		// return false;
	}

	@Override
	public void output(IndentedWriter out, SerializationContext sCxt) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void requestCancel() {
		// TODO Auto-generated method stub

	}

	static void pythonCall(int type, int isLarge) {

//File file11 = new File("/mnt/hdd/hammad/hammad/SimpleHello/");
//try {
//	FileUtils.cleanDirectory(file11);
//} catch (IOException e) {
		// TODO Auto-generated catch block
//	e.printStackTrace();
//} 

		System.out.println("Hello Python World blA BKA!");
		GatewayServer.turnLoggingOff();
		GatewayServer server = new GatewayServer();
		server.start();
		System.out.println("Hello Python World blA BKA!111:" + server.getListeningPort() + "--" + server.getPort()
				+ "--" + server.getPythonPort() + "--" + server.getListeners().stream().collect(Collectors.toList())
				+ "--" + server.getPythonAddress() + "--" + server.getAddress());
		String join = null;

		Field f = null;
		try {
			f = server.getClass().getDeclaredField("gateway");
			System.out.println("Hello Python World blA BKA!555:" + f);

		} catch (NoSuchFieldException e2) {
// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} // NoSuchFieldException
		f.setAccessible(true);
		Gateway gateway = new Gateway(null);
		try {
			gateway = (Gateway) f.get(server);
			System.out.println("Hello Python World blA BKA!222:" + gateway.getBindings().values() + "--"
					+ gateway.getEntryPoint());

		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String fakeCommandPart = "f" + Protocol.ENTRY_POINT_OBJECT_ID + ";" + "py4j.examples.IHello";
		System.out.println("Hello Python World blA BKA!222:" + fakeCommandPart + "--" + gateway.getEntryPoint());

		// System.out.println("Hello Python World blA BKA!22222");
		RootClassLoadingStrategy rmmClassLoader = new RootClassLoadingStrategy();
		ReflectionUtil.setClassLoadingStrategy(rmmClassLoader);
		IHello hello = (IHello) Protocol.getPythonProxy(fakeCommandPart, gateway);
		System.out.println("Hello Python World blA BKA!9999:" + leftTable.size() + "--" + rightTable.size());
		try {
			System.out.println("Hello Python World blA BKA!33333");

			if ((!ParaEng.Optional.isEmpty() || !ParaEng.Optional.isEmpty()) ||!ParaEng.Union.isEmpty()) {
				// try {
				// Thread.sleep(500);
				// } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				// }
				if (type == 1)
					join = hello.sayHello(1,isLarge);
				else if (type == 4 || QueryTask.IsLiteral == 1 || QueryTask.IsURLProper == 1)
					// {
					join = hello.sayHello(4,isLarge);
				// }
				// type=-1;}
				else if (type == 0) {
					join = hello.sayHello(0,isLarge);
					QueryTask.IsURLProper = 0;
				}
				
				else	if (type == 2)
					join = hello.sayHello(2,isLarge);
				//else		if (type == 5)
				//	join = hello.sayHello(5,isLarge);
				////else	if (type == 6)
				else
				join = hello.sayHello(0,isLarge);

			} else {
				if (possibleOuter == 5)
					join = hello.sayHello(3,isLarge);
				else if (type == 4 || QueryTask.IsLiteral == 1 || QueryTask.IsURLProper == 1)
					join = hello.sayHello(4,isLarge);
			//	else	if (type == 5)
			//		join = hello.sayHello(5,isLarge);
			//	else	if (type == 6)
			//		join = hello.sayHello(6,isLarge);

				else
					join = hello.sayHello(0,isLarge);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		server.shutdown();
	}

	static synchronized HashMap<String, ResultSet> pythonProcess(List<List<String>> leftTable,
			List<List<String>> rightTable, int type, int isLarge) {
		System.out.println("This is here 11111:");
		System.out.println("This is here 3333333333000000000000:" + leftTable.size() + "--" + rightTable.size());
ForkJoinPool fjp = new ForkJoinPool();
fjp.submit(()->
		{	ArrayList<String[]> leftCsv = new ArrayList<String[]>();
		for (int i = 0; i < leftTable.size(); i++) {
			String[] temp = new String[leftTable.get(0).size()];
			for (int n = 0; n < temp.length; n++) {
				if(i==0 && leftTable.get(0).size()>1 )  {
				if (HeaderReplacement.containsKey(leftTable.get(0).get(n).toString()))
				{		System.out.println("This is the problem of problems:"+HeaderReplacement+"--"+leftTable.get(0).get(n)+"--"+HeaderReplacement.get(leftTable.get(0).get(n)));
					temp[n] = HeaderReplacement.get(leftTable.get(0).get(n));
					//temp[n].set(n, HeaderReplacement.get(leftTable.get(n).toString()));// ).replace(rowsLeft.get(0).get(z),
			
				}else
					temp[n] = leftTable.get(i).get(n);
			// HeaderReplacement.get(headersLeft.get(z).toString()));
			// ).replace(rowsLeft.get(0).get(z),
																// HeaderReplacement.get(headersLeft.get(z).toString()));

				// rowsLeft.get(0).get(z).replace(rowsLeft.get(0).get(z), headersLeft.get(z));

				// table[0][z]=headersLeft.get(z);
				}
				else
				temp[n] = leftTable.get(i).get(n);
			}

			leftCsv.add(temp);
		}

		List<String[]> rightCsv = new ArrayList<String[]>();
		for (int i = 0; i < rightTable.size(); i++) {
			String[] temp = new String[rightTable.get(0).size()];
			for (int n = 0; n < temp.length; n++) {
				if(i==0 && rightTable.get(0).size()>1 ) {
					if (HeaderReplacement.containsKey(rightTable.get(0).get(n).toString()))
					{		System.out.println("This is the problem of problems:"+HeaderReplacement+"--"+rightTable.get(0).get(n)+"--"+HeaderReplacement.get(leftTable.get(0).get(n)));
						temp[n] = HeaderReplacement.get(rightTable.get(0).get(n));
						//temp[n].set(n, HeaderReplacement.get(leftTable.get(n).toString()));// ).replace(rowsLeft.get(0).get(z),
				
					}	
				else
					temp[n] = rightTable.get(0).get(n);}
			// HeaderReplacement.get(headersLeft.get(z).toString()));
			// ).replace(rowsLeft.get(0).get(z),
																// HeaderReplacement.get(headersLeft.get(z).toString()));

				// rowsLeft.get(0).get(z).replace(rowsLeft.get(0).get(z), headersLeft.get(z));

				// table[0][z]=headersLeft.get(z);

				else
					temp[n] = rightTable.get(i).get(n);
			}

			rightCsv.add(temp);
		}
		String left=null,right=null;
	
		File file = new File("/mnt/hdd/hammad/hammad/leftTable.csv");

		if (file.delete()) {
			System.out.println("File deleted successfully");
		}
		File file1 = new File("/mnt/hdd/hammad/hammad/rightTable.csv");

		if (file1.delete()) {
			System.out.println("File deleted successfully1");
		}
		// System.out.print("These are left var4");
		try (CSVWriter writer = new CSVWriter(new FileWriter("/mnt/hdd/hammad/hammad/leftTable.csv", true))) {
			writer.writeAll(leftCsv);
			// writer.close();
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		try (CSVWriter writer1 = new CSVWriter(new FileWriter("/mnt/hdd/hammad/hammad/rightTable.csv", true))) {
			writer1.writeAll(rightCsv);
			// writer1.close();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
//ForkJoinPool fjp = new ForkJoinPool();
//fjp.submit(()->
		System.out.println("This is here 3333333333:" + leftCsv.size()+"--"+rightCsv.size());
	}).join();
fjp.shutdown();
/*
		if((leftCount>1 && rightCount==1 && right.equals("?a")) )
		{
			System.out.println("This is here 88888888888:");

			ForkJoinPool fjp = new ForkJoinPool();
			fjp.submit(() -> pythonCall(6, 0)).join();// );\
			fjp.shutdown();
		}
		else	if( (rightCount>1 && leftCount==1 && left.equals("?a")) )
		{
			System.out.println("This is here 99999999999:");

			ForkJoinPool fjp = new ForkJoinPool();
			fjp.submit(() -> pythonCall(5, 0)).join();// );\
			fjp.shutdown();
		}
		else {
	*/	ForkJoinPool fjp1 = new ForkJoinPool();
		fjp1.submit(() -> pythonCall(type, isLarge)).join();// );\
		fjp1.shutdown();
//		}	
		System.out.println("This is here 444444444444");

//fjp.shutdown();
		System.out.println("This is file initialization:" + LocalTime.now());

		org.apache.jena.query.ResultSet v = ResultSetFactory.load("/mnt/hdd/hammad/hammad/output.csv");

		HashMap<String, ResultSet> vList = new HashMap<>();
		//

//	File file11 = new File("/mnt/hdd/hammad/hammad/output0.csv");
//	File file21 = new File("/mnt/hdd/hammad/hammad/output24.csv");

		if (isLarge == 1) {
			String[] filenames = null;

			File f = new File("/mnt/hdd/hammad/hammad/");

			// This filter will only include files ending with .csv
			FilenameFilter filter = new FilenameFilter() {
				@Override
				public boolean accept(File f, String name) {
					return name.startsWith("pythonoutput");
				}
			};

			// This is how to apply the filter
			filenames = f.list(filter);
			for (String fnames : filenames) {
				// System.out.println("This is final version:"+fnames);
				try {
					fList.add(new File("/mnt/hdd/hammad/hammad/" + fnames));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vList.put(fnames, ResultSetFactory.load("/mnt/hdd/hammad/hammad/" + fnames));
			}

			/*
			 * try (Stream<Path> walk = Files.walk(Paths.get("/mnt/hdd/hammad/hammad/"))) {
			 * 
			 * List<String> result = walk.filter(Files::isRegularFile) .map(x ->
			 * x.toString()).collect(Collectors.toList()); for(String r:result) {
			 * System.out.println("This is final version:"+r);
			 * vList.add(ResultSetFactory.load(r)); // vList1.add(ResultSetFactory.load(r));
			 * }
			 * 
			 * // .forEach(e->vList.add(ResultSetFactory.load(
			 * "/mnt/hdd/hammad/hammad/SimpleHello/"+e)));
			 * 
			 * } catch (IOException e) { e.printStackTrace(); }
			 * 
			 * 
			 * /*try (Stream<Path> walk =
			 * Files.walk(Paths.get("/mnt/hdd/hammad/hammad/SimpleHello/"))) {
			 * 
			 * List<String> result1 = walk.filter(Files::isRegularFile) .map(x ->
			 * x.toString()).collect(Collectors.toList()); List<ResultSet> vList1 = new
			 * ArrayList<>();
			 * 
			 * 
			 * for(String r1:result1) { System.out.println("This is final version1:"+r1);
			 * vList1.add(ResultSetFactory.load(r1)); //
			 * vList1.add(ResultSetFactory.load(r)); } //
			 * .forEach(e->vList.add(ResultSetFactory.load(
			 * "/mnt/hdd/hammad/hammad/SimpleHello/"+e)));
			 * 
			 * //for(ResultSet vl:vList1) // while(vl.hasNext()) //
			 * System.out.println("This is final version1 data:"+vl.next()); // } catch
			 * (IOException e) { // e.printStackTrace(); // }
			 */
		}

		else
			vList.put(null, v);

//	for(ResultSet v1:vList1)
//		while(v1.hasNext())
//		System.out.println("This is final vList:"+v1.nextBinding());
		System.out.println("This is here 555555555555555:" + vList.size()+"--"+LocalTime.now());

		return vList;
	}

	static void resultSetThread(HashMap<String, ResultSet> rSet1,String string) {
		List<Callable<ArrayList<Binding>>> tasks = new ArrayList<Callable<ArrayList<Binding>>>();
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (Entry<String, ResultSet> r : rSet1.entrySet()) {

			Callable<ArrayList<Binding>> c = new Callable<ArrayList<Binding>>() {
				@Override
				public ArrayList<Binding> call() throws Exception {
					System.out.println("This is going in:" + r.getKey());
					ArrayList<Binding> bb = postProcess(r, 0,string);
					;
					System.out.println("This is going out:" + bb.size());

					// System.out.println("This is going out:"+bb);

					// return resultoutput.addAll(postProcess(r,1));
					return bb;

					// return 1;
				}
			};
			tasks.add(c);
		}

		try {
			List<Future<ArrayList<Binding>>> results1 = exec.invokeAll(tasks);
			for (Future<ArrayList<Binding>> r : results1)
				try {
					int j = 0;

					resultoutput.addAll(r.get());
					// for(Binding fr:resultoutput)
					// {System.out.println("These are the bindings in fr:"+fr);
					// if(j==10)
					// break;
					// j++;}
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // finally {

		try {
			exec.awaitTermination(500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exec.shutdownNow();

	}

	public void possibleEdgeOperator(LinkedHashMap<EdgeOperator, List<Binding>> TempBind, LinkedHashMap<EdgeOperator, List<Binding>> tempBind1, Entry<EdgeOperator, List<Binding>> t, List<EdgeOperator> joinGroupsListLeft2) {
		int k = 0;
		int notPossible=0;
		int found=0;
		EdgeOperator futureEdgeOperator = null;
		
		for (Entry<EdgeOperator, List<Binding>> jggl1 : TempBind.entrySet()) {
			if(jggl1.getKey().toString().contains("Bind"))	
				//	if(!Done.contains(eo.getKey()))
				 {
		 			for(Entry<Vertex, LinkedHashSet<Binding>>  sb123:StartBinding123.entrySet()) {
						
					if(!sb123.getKey().equals(jggl1.getKey().getStartVertex())) {
					//	delayedExecution.put(eo1, new ArrayList<>());
						notPossible=1;
							System.out.println("It has no bind vertex yet:"+sb123.getValue().size()+"--"+jggl1);
							futureEdgeOperator=	jggl1.getKey();
						
					   }
					else 
						futureEdgeOperator=	jggl1.getKey();
		 			}
				 }
		}
			EdgeOperator Exec = null;
			if(notPossible==1) {
				
				for (List<EdgeOperator> eo1 : JoinGroupsListExclusive) {
					if(eo1.get(0).toString().contains("Bind"))	
						
					 for(Entry<Vertex, LinkedHashSet<Binding>>  sb1231:StartBinding123.entrySet()) {
							//	if(!Done.contains(eo.getKey()))
							 {
								//	System.out.println("It has no bind vertex check:"+sb1231.getKey()+"--"+eo1.getStartVertex());
										 
								if(sb1231.getKey().toString().equals(eo1.get(0).getStartVertex().toString())) {
									
							 Exec=eo1.get(0);				
							 found=1;	
								}
							}	
				}
					}
			}
					if(notPossible==1 && found==0) {
									
				for (EdgeOperator eo1 : JoinGroupsListLeft) {
					if(eo1.toString().contains("Bind"))	
						
					 for(Entry<Vertex, LinkedHashSet<Binding>>  sb1231:StartBinding123.entrySet()) {
							//	if(!Done.contains(eo.getKey()))
							 {
								//	System.out.println("It has no bind vertex check:"+sb1231.getKey()+"--"+eo1.getStartVertex());
										 
								if(sb1231.getKey().toString().equals(eo1.getStartVertex().toString())) {
			found=1;					
							 Exec=eo1;				
									
								}
							}	
				}
				}
					}
					
					if(notPossible==1  && found==0) {
						
						for (EdgeOperator eo1 : JoinGroupsListRight) {
							if(eo1.toString().contains("Bind"))	
								
							 for(Entry<Vertex, LinkedHashSet<Binding>>  sb1231:StartBinding123.entrySet()) {
									//	if(!Done.contains(eo.getKey()))
									 {
										//	System.out.println("It has no bind vertex check:"+sb1231.getKey()+"--"+eo1.getStartVertex());
												 
										if(sb1231.getKey().toString().equals(eo1.getStartVertex().toString())) {
					found=1;					
									 Exec=eo1;				
											
										}
									}	
						}
						}
							}
				if(notPossible==1) {
				if (Exec.getEdge().getV1().equals(t.getKey().getStartVertex())
						|| Exec.getEdge().getV2().equals(t.getKey().getStartVertex())) {
					for (Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> gst : StartBindingFinal
							.entrySet())
						for (Entry<Edge, Vertex> gst1 : gst.getKey().entries())
							if (Exec.getEdge().equals(gst1.getKey())) {
								TempBind.put(Exec, null);
								tempBind1.remove(Exec);
								System.out.println("This is the new group list of JoinGroupsListLeft000000:" + TempBind);
								
								
								PartitionedExecutions(TempBind,TempBind, "Norm");
								Done.add(Exec);
								
								k = 1;
								break;
							}
				
					
				}

		}
	
			//for (EdgeOperator jggl1 : jggl) {
			else {if (futureEdgeOperator.getEdge().getV1().equals(t.getKey().getStartVertex())
						|| futureEdgeOperator.getEdge().getV2().equals(t.getKey().getStartVertex())) {
					for (Entry<Multimap<Edge, Vertex>, ArrayList<Binding>> gst : StartBindingFinal
							.entrySet())
						for (Entry<Edge, Vertex> gst1 : gst.getKey().entries())
							if (futureEdgeOperator.getEdge().equals(gst1.getKey())) {
								TempBind.put(futureEdgeOperator, null);
								tempBind1.remove(futureEdgeOperator);
								System.out.println("This is the new group list of JoinGroupsListLeft000000:" + TempBind);
								
								
								PartitionedExecutions(TempBind,TempBind, "Norm");
								k = 1;
								break;
							}
				}
				
		}
		

	}
public void adjustEdgeOperator(List<EdgeOperator> TempDone,LinkedHashMap<EdgeOperator,List<Binding>> TempBind,LinkedHashMap<EdgeOperator,List<Binding>> TempBind1, Entry<EdgeOperator, List<Binding>> t, Entry<EdgeOperator, List<Binding>> t11,
		List<EdgeOperator> JoinGroupsListLeft) {

//		System.out.println("This is the new group list of TempDone45:" + TempBind);
	
ForkJoinPool fjp = new ForkJoinPool();
fjp.submit(()->{
	for(Entry<EdgeOperator, List<Binding>> tb:TempBind.entrySet())
	possibleEdgeOperator(TempBind, TempBind1, t,JoinGroupsListLeft);
	}).join();
fjp.shutdown();
//System.out.println("This is the new group list of TempDone123123:" + TempBind);
	for (EdgeOperator td : TempDone)
		if (!TempBind.containsKey(td)) {
			PartitionedExecutions(TempBind,TempBind, "Norm");
			TempBind.put(t11.getKey(), t11.getValue());
			TempBind.put(t.getKey(), t.getValue());
			Iterator<Entry<EdgeOperator, List<Binding>>> tb1 = TempBind1.entrySet().iterator();
			while (tb1.hasNext()) {
				EdgeOperator tt = tb1.next().getKey();
				if (tt.equals(t.getKey()) || tt.equals(t11.getKey()))
					tb1.remove();
			}
		}
	// TempBind1.remove(t.getKey());
	// TempBind1.remove(t11.getKey());

	for (Entry<EdgeOperator, List<Binding>> et : TempBind.entrySet())
		TempDone.add(et.getKey());
}
}
