package graph;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ArrayGraph<T> implements Graph<T> {
	
	int[][] connections;
	Map<T,Integer> index;
	
	public ArrayGraph(Map<T,Integer> index,int[][] connections) {
		super();
		if(index.size() != connections.length)
			throw new IndexOutOfBoundsException("index map has a different dimension than connections array");
		this.index = index;
		this.connections = connections;
	}
	
	
	@Override
	public void addNode(T name, int[][] connections) {
		
		
		
	}

	@Override
	public void removeNode(T name) {
		
		
		
	}

	@Override
	public boolean isEmpty() {
		return connections.length==0?true:false;
	}

	@Override
	public int[][] calculateDijkstra(T origin) {
		Set<T> S = new LinkedHashSet<T>();
		Set<T> V_S = index.keySet();
		int originIndex = index.get(origin);
		
		int[] D = new int[connections.length];
		int[] P = new int[connections.length];
		
		for (int i = 0; i < D.length; i++) {
			D[i] = connections[originIndex][i];
			P[i] = originIndex;
		}
		
		while(!V_S.isEmpty()) {
			T w = lookMinIndex(V_S,D);
			int indexW = index.get(w);
			V_S.remove(w);
			S.add(w);
			for (T node : V_S) {
				int indexNode = index.get(node);
				if(connections[indexW][indexNode] != Integer.MAX_VALUE) {
					int valAnterior = D[indexNode];
					D[indexNode] = Math.min(D[indexNode], D[indexW] + connections[indexW][indexNode]);
					P[indexNode] = valAnterior!=D[indexNode]?indexW:P[indexNode];
				}
			}
		}
		
		int[][] res = new int[2][connections.length];
		res[0] = D;
		res[1] = P;
		res[0][originIndex] = -1;
		res[1][originIndex] = -1;
		
		return res;
	}


	private T lookMinIndex(Set<T> v_S,int[] D) {
		int indexMin = Integer.MAX_VALUE;
		T nodeMin = null;
		for (T node : v_S) {
			int curMin = D[index.get(node)];
			
			if(curMin < indexMin) {
				indexMin = curMin;
				nodeMin = node;
			}
			else if(nodeMin == null) {
				nodeMin = node;
				indexMin = curMin;
			}
				
		}
		
		return nodeMin;
	}


	@Override
	public int size() {
		return connections.length;
	}

	@Override
	public Set<T> nodesSet() {
		return index.keySet();
	}

	public int[][] connectionsArray() {
		return connections;
	}

}
