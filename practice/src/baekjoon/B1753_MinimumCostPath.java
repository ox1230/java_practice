package baekjoon;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;


//Dijkstra 알고리즘 활용   1753번 최단경로
public class B1753_MinimumCostPath{
	public static void main(String[] args) throws IOException{
		Graph2 g = new Graph2();
		g.input();
		g.dijkstra();
		g.printIt();
	}
}



class Graph2{
	int V;
	int E;
	int start;

	final int INF= -1;  // 알수 없는 거리
	HashMap<Integer,Integer>[] G; // 그래프   <k,w> : k까지 가는데 걸리는 거리 w
	boolean[] isFinished;    //정점이 이미 최단거리가 나왔는지 체크
	
	int[] dist; //start에서의 최단 거리
	
	@SuppressWarnings("unchecked")
	void input() throws IOException{
		Reader r = new Reader();
	
		
		V = r.nextInt();
		E = r.nextInt();
		
		start = r.nextInt();
		
		G = new HashMap[V+1];  // 1부터 시작하므로 한개 더 생성
		isFinished = new boolean[V+1];
		
		for(int i=0;i<=V;i++){ // graph 초기화
			G[i] = new HashMap<Integer,Integer>();
			G[i].put(i, 0);
		}

		for(int i=0;i<E;i++){
			int u = r.nextInt();
			int v = r.nextInt();
			int w = r.nextInt();
			
			if(G[u].containsKey(v) && G[u].get(v) <w) continue; // 가중치가 가장 작은 간선 한개만 남긴다.
			
			G[u].put(v,w);
		}
	}
	
	void dijkstra(){
		dist = new int[V+1];
		PriorityQueue<VandD> save = new PriorityQueue<VandD>();// 최단거리순으로 정점 저장
		int temp; // 확인할 정점
		
		for(int i=0;i<=V;i++){
			dist[i] = INF;
		}
		dist[start] = 0;
		save.add(new VandD(start,0));
		
		while(!save.isEmpty()){
			temp = save.poll().v;
			
			if(isFinished[temp] == true) continue;  //이미 체크한 정점이므로 
			
			int toTemp = dist[temp];
			isFinished[temp] = true;
			
			for(int i=1;i<=V;i++){
				if(G[temp].containsKey(i)){  // temp에서 i로의 길이 존재하면
					int otherDis = G[temp].get(i) + toTemp;  // temp를 거쳐가는 새로운 길을 확인한다
					
					if(dist[i] != INF && dist[i] <= otherDis){}
					else{
						dist[i] = otherDis;
						save.add(new VandD(i,otherDis));
						
					}
				}
			}
		}
	}
	
	void printIt(){
		for(int i=1;i<=V;i++){
			if(dist[i]!=INF) System.out.println(dist[i]);
			else System.out.println("INF");
		}
	}	
}


