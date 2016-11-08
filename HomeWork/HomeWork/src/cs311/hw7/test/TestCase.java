package cs311.hw7.test;

import cs311.hw7.graph.GraphImpl;
import cs311.hw7.graph.IGraph;
import cs311.hw7.graph.TopologicalsortImpl;
import cs311.hw7.graphalgorithms.GraphAlgorithms;

public class TestCase {
	
	public static void main2(String[] args) {
		GraphAlgorithms algorithms = new GraphAlgorithms();
		IGraph graph = new GraphImpl();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		
		graph.addEdge("A","B",1);
		graph.addEdge("A","C",4);
		graph.addEdge("A","F",6);
		graph.addEdge("B","D",8);
		graph.addEdge("B","E",3);
		graph.addEdge("C","F",5);
		graph.addEdge("C","E",9);
		graph.addEdge("D","E",7);
		graph.addEdge("D","F",10);
		graph.addEdge("E","F",2);
		algorithms.Kruscal(graph);
		
	}
	
	public static void main(String[] args) {
		GraphAlgorithms algorithms =  new GraphAlgorithms();
		TopologicalsortImpl g = new TopologicalsortImpl();
		g.addVertex("BOS");
		g.addVertex("LAX");
		g.addVertex("JFK");
		g.addVertex("ORD");
		g.addVertex("MIA");
		g.addVertex("DFW");
		g.addVertex("SFO");
		g.addEdge("MIA", "LAX", "1");
		g.addEdge("MIA", "DFW", "1");
		g.addEdge("JFK", "MIA", "1");
		g.addEdge("JFK", "SFO", "1");
		g.addEdge("JFK", "BOS", "1");
		g.addEdge("ORD", "DFW", "1");
		g.addEdge("DFW", "ORD", "1");
		g.addEdge("DFW", "SFO", "1");
		g.addEdge("DFW", "LAX", "1");
		g.addEdge("DFW", "JFK", "1");
		g.addEdge("LAX", "ORD", "1");
		g.addEdge("BOS", "MIA", "1");
		g.addEdge("BOS", "JFK", "1");
		algorithms.TopologicalSort(g);
		
	}

}
