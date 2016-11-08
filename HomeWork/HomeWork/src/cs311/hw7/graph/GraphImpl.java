package cs311.hw7.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cs311.hw7.graphalgorithms.IWeight;

public  class GraphImpl implements IGraph<String, Integer>,IWeight{
	
	
	public enum Color {  
	    WHITE, GRAY, BLACK  
	}  
	
	private Map<String, List<Edge<Integer>>> adjacencyList;  // [vertices] -> [edge]
	
	private LinkedHashMap<String, Vertex<String>> vertexInfo;
	
	private boolean isDirectedGraph;

	private double weight = 0;

	private static List<cs311.hw7.graph.IGraph.Edge<Integer>> mEdges;
	private List<Edge> treeEdges=new ArrayList<Edge>(); 

	public GraphImpl(IGraph graph) {
		super();
		init(graph);
		
	}
	
	private void init(IGraph graph) {
		if (graph == null) return;
		List<Edge<Integer>> edges = graph.getEdges();
		List<Vertex<String>> vertexs = graph.getVertices();
		adjacencyList = new HashMap<String, List<Edge<Integer>>>();
		vertexInfo = new LinkedHashMap<String, Vertex<String>>();
		mEdges = new ArrayList<>();
		for(Vertex<String> v :vertexs){
			addVertex(v.getVertexName(), v.getVertexData());
		}
		for(cs311.hw7.graph.IGraph.Edge<Integer> e:edges){
			addEdge(e.getVertexName1(), e.getVertexName2(), e.getEdgeData());
		}
		countWeight();
	
	}

	private Set<String> points=new HashSet<String>(); 
	public  void countWeight(){
     
     	 List<Edge<Integer>> edges = getEdges();
     	 Collections.sort(edges,new Comparator<Edge<Integer>>() {

  			@Override
  			public int compare(Edge<Integer> o1, Edge<Integer> o2) {
  				if (o1.getEdgeData()>o2.getEdgeData()) {
  					return 1;
  				}else if(o1.getEdgeData()<o2.getEdgeData()){
  					return -1;
  				}else{
  					return 0;
  				}
  			}
  		});
     	
     	 for(Edge<Integer> edge:edges){
     		 if(isCircle(edge)){
     			 continue;
     		 }else{
     			 
     			 treeEdges.add(edge);
     			 
     		 }
     	 }
     	 printTreeInfo();
	};
	
	public void printTreeInfo(){  
        int totalDistance=0;  
        for(Edge<Integer> edge:treeEdges){  
            totalDistance+=edge.getEdgeData();  
            System.out.println(edge.getVertexName1()+"->"+edge.getVertexName2());  
        }  
        System.out.println("The total weighted distance:"+totalDistance);  
    }  
	 
    private  boolean isCircle(Edge<Integer> edge){  
    	int size= points.size();  
        if(!points.contains(edge.getVertexName1())){  
            size++;  
        }  
        if(!points.contains(edge.getVertexName2())){  
            size++;  
        }  
        if(size==treeEdges.size()+1){  
            return true;  
        }else{  
            points.add(edge.getVertexName1());  
            points.add(edge.getVertexName2());  
            return false;  
        }  
    }  
	
	public GraphImpl() {
		super();
		adjacencyList = new HashMap<String, List<Edge<Integer>>>();
		vertexInfo = new LinkedHashMap<String, Vertex<String>>();
	}

	@Override
	public void setDirectedGraph() {
		this.isDirectedGraph = true;
		
	}

	@Override
	public void setUndirectedGraph() {
		this.isDirectedGraph = false;
		
	}

	@Override
	public boolean isDirectedGraph() {
		return isDirectedGraph;
	}

	@Override
	public void addVertex(String vertexName) throws cs311.hw7.graph.IGraph.DuplicateVertexException {
		addVertex(vertexName, vertexName);
	}
	

	@Override
	public void addVertex(String vertexName, String vertexData) throws cs311.hw7.graph.IGraph.DuplicateVertexException {
		if (vertexInfo.get(vertexName) != null) {
			throw new DuplicateVertexException();
		}
		adjacencyList.put(vertexName, new ArrayList<Edge<Integer>>());
		vertexInfo.put(vertexName, new Vertex<String>(vertexName, vertexData));
	}

	@Override
	public void addEdge(String vertex1, String vertex2)
			throws cs311.hw7.graph.IGraph.DuplicateEdgeException, cs311.hw7.graph.IGraph.NoSuchVertexException {
		addEdge(vertex1, vertex2,null);
		
	}

	
	@Override
	public void addEdge(String vertex1, String vertex2, Integer edgeData)
			throws cs311.hw7.graph.IGraph.DuplicateEdgeException, cs311.hw7.graph.IGraph.NoSuchVertexException {
		List<Edge<Integer>> list = adjacencyList.get(vertex1);
		if(list == null)
			throw new NoSuchVertexException();
		list.add(new Edge<Integer>(vertex1, vertex2, edgeData));
	}

	@Override
	public String getVertexData(String vertexName) throws cs311.hw7.graph.IGraph.NoSuchVertexException {
		Vertex<String> vertex = vertexInfo.get(vertexName);
		if (vertex == null) {
			throw new NoSuchVertexException();
		}
		return vertex.getVertexData();
	}

	@Override
	public void setVertexData(String vertexName, String vertexData) throws cs311.hw7.graph.IGraph.NoSuchVertexException {
		Vertex<String> v = vertexInfo.remove(vertexName);
		if (v == null) {
			throw new NoSuchVertexException();
		}
		vertexInfo.put(vertexName, new Vertex<String>(vertexName, vertexData));
	}

	@Override
	public Integer getEdgeData(String vertex1, String vertex2)
			throws cs311.hw7.graph.IGraph.NoSuchVertexException, cs311.hw7.graph.IGraph.NoSuchEdgeException {
		List<Edge<Integer>> edges = adjacencyList.get(vertex1);
		if (edges == null )
				throw new NoSuchEdgeException();
		for(Edge<Integer> e :edges){
			if (vertex1.equals(e.getVertexName1())) {
				return e.getEdgeData();
			}
		}
		return null;
	}

	
	
	@Override
	public void setEdgeData(String vertex1, String vertex2, Integer edgeData)
			throws cs311.hw7.graph.IGraph.NoSuchVertexException, cs311.hw7.graph.IGraph.NoSuchEdgeException {
		List<Edge<Integer>> edges = adjacencyList.get(vertex1);
		if (edges == null )
				throw new NoSuchEdgeException();
		
		boolean removed = edges.remove(new Edge<Integer>(vertex1, vertex2, null));
		
		if (removed)
			throw new cs311.hw7.graph.IGraph.NoSuchEdgeException();
		else
			edges.add(new Edge<Integer>(vertex1, vertex2, edgeData));
	}

	@Override
	public cs311.hw7.graph.IGraph.Vertex<String> getVertex(String VertexName) {
		
		return vertexInfo.get(VertexName);
	}

	@Override
	public cs311.hw7.graph.IGraph.Edge<Integer> getEdge(String vertexName1, String vertexName2) {
		for(Entry<String, List<Edge<Integer>>> entry : adjacencyList.entrySet()){
			if (vertexName1.equals(entry.getKey())) {
				List<Edge<Integer>> edges = entry.getValue();
				if (edges!=null && edges.size() > 0) {
					for(Edge<Integer> edge : edges){
						if (vertexName1.equals(edge.getVertexName1()) && vertexName2.equals(edge.getVertexName2())) {
							return edge;
						}
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public List<cs311.hw7.graph.IGraph.Vertex<String>> getVertices() {
		return new ArrayList<>(vertexInfo.values());
	}

	@Override
	public List<cs311.hw7.graph.IGraph.Edge<Integer>> getEdges() {
		List<cs311.hw7.graph.IGraph.Edge<Integer>> list = new ArrayList<>();
		for(Entry<String,List<Edge<Integer>>> edgeEntry : adjacencyList.entrySet()){
			if (edgeEntry.getValue() != null && edgeEntry.getValue().size() > 0) {
				list.addAll(edgeEntry.getValue());
			}
		}
		return list;
	}

	@Override
	public List<cs311.hw7.graph.IGraph.Vertex<String>> getNeighbors(String vertex) {
		//TODO 
		Vertex<String> v = vertexInfo.get(vertex);
		Set entrySet = vertexInfo.entrySet();
		
		//vertexInfo.
		return null;
	}

	public void setWeight(double weight){
		this.weight  = weight;
	}
	
	@Override
	public double getWeight() {

		return weight;
	}
	
	
}
