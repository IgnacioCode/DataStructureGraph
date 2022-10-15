package graph;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LinkedGraph<T> implements Graph<T> {
	
	private List<List<T>> connections;
	private Map<T,Integer> index;
	private List<T> nodes;
	
	public LinkedGraph() {
		connections = new LinkedList<List<T>>();
		index = new LinkedHashMap<T,Integer>();
		nodes = new LinkedList<T>();
	}
	
	public LinkedGraph(List<List<T>> connections, Map<T, Integer> index,List<T> nodes) {
		super();
		if(index.size() != connections.size() || index.size() != nodes.size() || connections.size() != nodes.size())
			throw new IndexOutOfBoundsException("index map has a different dimension than connections array");
		this.connections = connections;
		this.index = index;
		this.nodes = nodes;
	}
	
	public LinkedGraph(List<List<T>> connections, Map<T, Integer> index) {
		super();
		if(index.size() != connections.size())
			throw new IndexOutOfBoundsException("index map has a different dimension than connections array");
		this.connections = connections;
		this.index = index;
		
		nodes = new LinkedList<T>();
		for (int i = 0; i < connections.size(); i++) {
			T nextNode = getNodeIndexValue(i);
			if(nextNode == null)
				throw new IndexOutOfBoundsException("map index values do not follow increasing order");
			nodes.add(nextNode);
		}
	}

	private T getNodeIndexValue(int i) {
		
		for (T node : index.keySet()) {
			int indRead = index.get(node);
			if(indRead == i)
				return node;
		}
		
		return null;
	}

	@Override
	public void addNode(T name, int[] connections, int[] otherNewCon) {
		
		List<T> newNodeList = new LinkedList<T>();
		
		for (int i = 0; i < connections.length-1; i++) {
			int con = connections[i];
			if(con != Graph.NO_CONNECTION) {
				newNodeList.add(nodes.get(i));
			}
		}
		nodes.add(name);
		index.put(name, index.size());
		this.connections.add(newNodeList);
		
		for (int i = 0; i < otherNewCon.length; i++) {
			int oldCon = otherNewCon[i];
			if(oldCon != Graph.NO_CONNECTION)
				this.connections.get(i).add(name);
		}
		
	}

	@Override
	public void removeNode(T name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		return connections.size()==0?true:false;
	}

	@Override
	public int size() {
		return connections.size();
	}

	@Override
	public Set<T> nodesSet() {
		return index.keySet();
	}

	@Override
	public int[][] calculateDijkstra(T origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] getConnectionArray() {
		
		int dimension = connections.size();
		int[][] arrayConnections = new int[dimension][dimension];
		
		for (int i = 0; i < arrayConnections.length; i++) {
			for (int j = 0; j < arrayConnections.length; j++) {
				arrayConnections[i][j] = Graph.NO_CONNECTION;
			}
		}
		
		for (int i = 0; i < arrayConnections.length; i++) {
			for (T node : connections.get(i)) {
				arrayConnections[i][index.get(node)] = 1;
			}
		}
		
		return arrayConnections;
	}

}
