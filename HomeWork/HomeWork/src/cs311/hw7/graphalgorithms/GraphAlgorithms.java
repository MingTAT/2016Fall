
package cs311.hw7.graphalgorithms;

import java.util.List;

import cs311.hw7.graph.GraphImpl;
import cs311.hw7.graph.IGraph;
import cs311.hw7.graph.IGraph.Vertex;
import cs311.hw7.graph.TopologicalsortImpl;


public class GraphAlgorithms
{
	
    public static <V, E> List<Vertex<V>> TopologicalSort( IGraph<V, E> graph)
    {
    	
    	TopologicalsortImpl mGraph =new  TopologicalsortImpl(graph);
    	List<String> result = mGraph.getDFSPath("BOS","LAX");
    	System.out.println(result);
        return null;  
       // return null; // Dummy return - replace this.
    }
    
    public static <V, E> List<List<Vertex<V>>> AllTopologicalSort( IGraph<V, E> g )
    {
        return null; // Dummy return - replace this.
    }
    
    
    public static <V, E extends IWeight> IGraph<V, E> Kruscal(IGraph<V, E> graph )
    {
    	IGraph mGraph  = new GraphImpl(graph);    
    	return mGraph;
    }
    
    
   
}