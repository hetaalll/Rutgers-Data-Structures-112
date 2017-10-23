package apps;

import structures.*;
import java.util.ArrayList;

public class MST {

	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {

		
		Vertex[] vertices = graph.vertices;
		boolean[] visitedVertices = new boolean[vertices.length];
		int ctr = 0;
		Vertex.Neighbor neighbors;
		PartialTree.Arc a;
		PartialTreeList L = new PartialTreeList(); //step 1

		for(Vertex v: vertices){ //step 2 
			PartialTree T = new PartialTree(v); 

			
			visitedVertices[ctr] = true;
			
			MinHeap<PartialTree.Arc> P = T.getArcs(); 

			neighbors = v.neighbors;
			while(neighbors != null){
				a = new PartialTree.Arc(v, neighbors.vertex, neighbors.weight); 
				
				P.insert(a);
				
				neighbors = neighbors.next;
			}
			if(visitedVertices[ctr] == true){
				L.append(T);
			}
			ctr++;
		} 

		return L;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {

		ArrayList<PartialTree.Arc> list = new ArrayList<PartialTree.Arc>();
		PartialTree ptx;
		PartialTree.Arc a;
		MinHeap<PartialTree.Arc> pqx;
		MinHeap<PartialTree.Arc> pqy;
		Vertex v1;
		Vertex v2;
		PartialTree pty;
		int PTListSize = ptlist.size();
		while(PTListSize > 1){ 

			ptx = ptlist.remove(); //step 3
			
			pqx = ptx.getArcs();

			do{
				a = pqx.deleteMin();	
				v1 = a.v1;
				v2 = a.v2;

				if(v2.getRoot().equals(v1.getRoot())){
					continue;
				}
				else{
					break;
				}
			}while(true);
			
			list.add(a);
			
			pty = ptlist.removeTreeContaining(v2);
			
			pqy = pty.getArcs();
			 
			ptx.merge(pty);
			
			ptlist.append(ptx);
			PTListSize--;
		}
		return list;
	}
}