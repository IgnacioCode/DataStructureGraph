package graph;

import java.util.Set;

public interface Graph<T>{
	
	public static final int NO_CONNECTION = Integer.MAX_VALUE;
	
	public void addNode(T name,int[] connections, int[] otherNewCon);
	public void removeNode(T name);
	
	public boolean isEmpty();
	public int size();
	
	public Set<T> nodesSet();
	
	public int[][] calculateDijkstra(T origin);
	public int[][] getConnectionArray();
	
	
	
}
