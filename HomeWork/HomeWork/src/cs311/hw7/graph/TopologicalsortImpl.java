package cs311.hw7.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class TopologicalsortImpl implements IGraph<Boolean, String>{

private Map<String, List<Edge<String>>> adjacencyList;  // [vertices] -> [edge]
	
	private LinkedHashMap<String, Vertex<Boolean>> vertexInfo;
	
	private boolean isDirectedGraph;

	private double weight = 0;

	private static List<cs311.hw7.graph.IGraph.Edge<Integer>> mEdges;
	private List<Edge> treeEdges=new ArrayList<Edge>();

	private String path1;

	private String path2; 

	public TopologicalsortImpl(IGraph graph) {
		super();
		init(graph);
		
	}
	
	private void init(IGraph graph) {
		if (graph == null) return;
		List<Edge<String>> edges = graph.getEdges();
		List<Vertex<Boolean>> vertexs = graph.getVertices();
		adjacencyList = new HashMap<String, List<Edge<String>>>();
		vertexInfo = new LinkedHashMap<String, Vertex<Boolean>>();
		mEdges = new ArrayList<>();
		for(Vertex<Boolean> v :vertexs){
			addVertex(v.getVertexName(), v.getVertexData());
		}
		for(cs311.hw7.graph.IGraph.Edge<String> e:edges){
			addEdge(e.getVertexName1(), e.getVertexName2(), e.getEdgeData());
		}
	
	}
	
	
	
	public TopologicalsortImpl() {
		super();
		adjacencyList = new HashMap<String, List<Edge<String>>>();
		vertexInfo = new LinkedHashMap<String, Vertex<Boolean>>();
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
		addVertex(vertexName, false);
	}
	

	@Override
	public void addVertex(String vertexName, Boolean color) throws cs311.hw7.graph.IGraph.DuplicateVertexException {
		if (vertexInfo.get(vertexName) != null) {
			throw new DuplicateVertexException();
		}
		adjacencyList.put(vertexName, new ArrayList<Edge<String>>());
		vertexInfo.put(vertexName, new Vertex<Boolean>(vertexName, color));
	}

	@Override
	public void addEdge(String vertex1, String vertex2)
			throws cs311.hw7.graph.IGraph.DuplicateEdgeException, cs311.hw7.graph.IGraph.NoSuchVertexException {
		addEdge(vertex1, vertex2,null);
		
	}

	@Override
		public void addEdge(String vertex1, String vertex2, String edgeData)
				throws cs311.hw7.graph.IGraph.DuplicateEdgeException, cs311.hw7.graph.IGraph.NoSuchVertexException {
		List<Edge<String>> list = adjacencyList.get(vertex1);
		if(list == null)
			throw new NoSuchVertexException();
		list.add(new Edge<String>(vertex1, vertex2, edgeData));
			
		}

	@Override
	public Boolean getVertexData(String vertexName) throws cs311.hw7.graph.IGraph.NoSuchVertexException {
		Vertex<Boolean> vertex = vertexInfo.get(vertexName);
		if (vertex == null) {
			throw new NoSuchVertexException();
		}
		return vertex.getVertexData();
	}

	@Override
	public void setVertexData(String vertexName, Boolean vertexData) throws cs311.hw7.graph.IGraph.NoSuchVertexException {
		Vertex<Boolean> v = vertexInfo.remove(vertexName);
		if (v == null) {
			throw new NoSuchVertexException();
		}
		vertexInfo.put(vertexName, new Vertex<Boolean>(vertexName, vertexData));
	}

	@Override
	public String getEdgeData(String vertex1, String vertex2)
			throws cs311.hw7.graph.IGraph.NoSuchVertexException, cs311.hw7.graph.IGraph.NoSuchEdgeException {
		List<Edge<String>> edges = adjacencyList.get(vertex1);
		if (edges == null )
				throw new NoSuchEdgeException();
		for(Edge<String> e :edges){
			if (vertex1.equals(e.getVertexName1())) {
				return e.getEdgeData();
			}
		}
		return null;
	}

	
	@Override
	public void setEdgeData(String vertex1, String vertex2, String edgeData)
			throws cs311.hw7.graph.IGraph.NoSuchVertexException, cs311.hw7.graph.IGraph.NoSuchEdgeException {
		List<Edge<String>> edges = adjacencyList.get(vertex1);
		if (edges == null )
				throw new NoSuchEdgeException();
		
		boolean removed = edges.remove(new Edge<Integer>(vertex1, vertex2, null));
		
		if (removed)
			throw new cs311.hw7.graph.IGraph.NoSuchEdgeException();
		else
			edges.add(new Edge<String>(vertex1, vertex2, edgeData));
		
	}
	

	@Override
	public cs311.hw7.graph.IGraph.Vertex<Boolean> getVertex(String VertexName) {
		
		return vertexInfo.get(VertexName);
	}

	@Override
	public cs311.hw7.graph.IGraph.Edge<String> getEdge(String vertexName1, String vertexName2) {
		for(Entry<String, List<Edge<String>>> entry : adjacencyList.entrySet()){
			if (vertexName1.equals(entry.getKey())) {
				List<Edge<String>> edges = entry.getValue();
				if (edges!=null && edges.size() > 0) {
					for(Edge<String> edge : edges){
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
	public List<cs311.hw7.graph.IGraph.Vertex<Boolean>> getVertices() {
		return new ArrayList<>(vertexInfo.values());
	}

	@Override
	public List<cs311.hw7.graph.IGraph.Edge<String>> getEdges() {
		List<cs311.hw7.graph.IGraph.Edge<String>> list = new ArrayList<>();
		for(Entry<String,List<Edge<String>>> edgeEntry : adjacencyList.entrySet()){
			if (edgeEntry.getValue() != null && edgeEntry.getValue().size() > 0) {
				list.addAll(edgeEntry.getValue());
			}
		}
		return list;
	}

	@Override
	public List<cs311.hw7.graph.IGraph.Vertex<Boolean>> getNeighbors(String vertex) {
		Vertex<Boolean> v = vertexInfo.get(vertex);
		Set entrySet = vertexInfo.entrySet();
		
		//vertexInfo.
		return null;
	}

	public void setWeight(double weight){
		this.weight  = weight;
	}
	
	public List<String> getDFSPath(String v1,String v2) { 
		if(v1 == null || v2 == null) return null;
		checkVertices(v1, v2);

		clearVisited();

		List<String> path = new ArrayList<String>();
		return getDFSPath(v1, v2, path);
	}

	private void clearVisited() {
		LinkedHashMap<String, Vertex<Boolean>> newVertexs = new LinkedHashMap<>();
		for (Vertex info : vertexInfo.values()) {
			newVertexs.put(info.getVertexName(), new Vertex<Boolean>(info.getVertexName(), false));
		}
		vertexInfo = newVertexs;
	}
	

	
	private void checkForNull(Object arg) {
		if (arg == null) {
			throw new NullPointerException("Argument must not be null");
		}
	}

	private void checkVertices(String v1, String v2) {
		checkForNull(v1);
		checkForNull(v2);
		if (!adjacencyList.containsKey(v1)) {
			throw new IllegalArgumentException("Vertex not found in graph: " + v1);
		}
		if (!adjacencyList.containsKey(v2)) {
			throw new IllegalArgumentException("Vertex not found in graph: " + v2);
		}        
	} 
	
	private List<String> getDFSPath(String v1, String v2, List<String> path) {
		
		path.add(v1);
		setVertexData(v1, true);
		if (v1.equals(v2)) {
			return path;
		}
		List<Edge<String>> edges = adjacencyList.get(v1);
		for (Edge<String> e : edges) {
			if (vertexInfo.get(e.getVertexName2()).getVertexData()) {
				continue;
			}

			if (getDFSPath(e.getVertexName2(), v2, path) != null) {
				return path;
			}
		}

		path.remove(path.size() - 1);

		return null;
	}

	public void setTextPath(String path1, String path2) {
		this.path1 = path1;
		this.path2 = path2;
		
	}

}
