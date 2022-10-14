package graph;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ArrayGraph<T> implements Graph<T> {
	
	private int[][] connections;
	private Map<T,Integer> index;
	
	public ArrayGraph(Map<T,Integer> index,int[][] connections) {
		super();
		if(index.size() != connections.length)
			throw new IndexOutOfBoundsException("index map has a different dimension than connections array");
		this.index = index;
		this.connections = connections;
	}
	
	
	@Override
	public void addNode(T name, int[] newCon, int[] otherNewCon) {
		
		if(newCon.length != connections.length+1)
			throw new IndexOutOfBoundsException("newCon array has an unexpected dimension");
		if(otherNewCon.length != connections.length)
			throw new IndexOutOfBoundsException("otherNewCon array has an unexpected dimension");
		
		index.put(name, connections.length);
		int[][] newArray = new int[connections.length+1][connections.length+1];

		for (int i = 0; i < connections.length; i++) {
			for (int j = 0; j < connections[0].length; j++) {
				newArray[i][j] = connections[i][j];
			}
		}
		
		newArray[connections.length] = newCon;
		
		for (int i = 0; i < otherNewCon.length; i++) {
			newArray[i][newArray[0].length-1] = otherNewCon[i];
		}
		
		connections = newArray;
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
		Set<T> V_S = new LinkedHashSet<T>();
		V_S.addAll(index.keySet());
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
	
	@Override
	public int[][] getConnectionArray() {
		return connections;
	}

}
