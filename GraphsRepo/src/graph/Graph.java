package graph;

import java.util.Set;

public interface Graph<T>{
	
	public void addNode(T name,int[][] connections);
	public void removeNode(T name);
	
	public boolean isEmpty();
	public int size();
	
	public Set<T> nodesSet();
	
	public int[][] calculateDijkstra(T origin);
	
	
	
}
