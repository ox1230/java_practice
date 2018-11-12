package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


//Dijkstra 알고리즘 활용   1753번 최단경로
public class DijkstraExample2 {
	public static void main(String[] args) throws IOException{
		Graph2 g = new Graph2();
		g.input();
		g.dijkstra();
		g.printIt();
	}
}

class Graph2 implements Comparator<Integer>{
	int V;
	int E;
	int start;

	final int INF= -1;  // 알수 없는 거리
	HashMap<Integer,Integer>[] G; // 그래프   <k,w> : k까지 가는데 걸리는 거리 w
	boolean[] isFinished;    //정점이 이미 최단거리가 나왔는지 체크
	
	long[] dist; //start에서의 최단 거리
	
	void input() throws IOException{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
	
		String[] temp = sc.readLine().split(" ");
		V = Integer.parseInt(temp[0]);
		E = Integer.parseInt(temp[1]);
		
		start = Integer.parseInt(sc.readLine());
		
		G = new HashMap[V+1];  // 1부터 시작하므로 한개 더 생성
		isFinished = new boolean[V+1];
		
		for(int i=0;i<=V;i++){ // graph 초기화
			G[i] = new HashMap<Integer,Integer>();
			G[i].put(i, 0);
		}

		for(int i=0;i<E;i++){
			temp = sc.readLine().split(" ");
			int u = Integer.parseInt(temp[0]);
			int v = Integer.parseInt(temp[1]);
			int w = Integer.parseInt(temp[2]);
			
			if(G[u].containsKey(v) && G[u].get(v) <w) continue; // 가중치가 가장 작은 간선 한개만 남긴다.
			
			G[u].put(v,w);
		}
		sc.close();
	}
	
	void dijkstra(){
		dist = new long[V+1];
		PriorityQueue<Integer> save = new PriorityQueue<Integer>(this);// 최단거리순으로 정점 저장
		int temp; // 확인할 정점
		
		for(int i=0;i<=V;i++){
			dist[i] = INF;
		}
		dist[start] = 0;
		save.offer(start);
		
		
		
		while(!save.isEmpty()){
			temp = save.poll();
			
			
			if(isFinished[temp] == true) continue;
			
			long toTemp = dist[temp];
			isFinished[temp] = true;
			
			for(int i=1;i<=V;i++){
				if(G[temp].containsKey(i)){  // temp에서 i로의 길이 존재하면
					long otherDis = toTemp + G[temp].get(i);  // temp를 거쳐가는 새로운 길을 확인한다
					
					if(dist[i] != INF && dist[i] <= otherDis){}
					
					else{
						dist[i] = otherDis;
						save.offer(i);
						
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

	@Override
	public int compare(Integer arg0, Integer arg1) {
		// TODO Auto-generated method stub
		if(dist[arg0] == INF && dist[arg1] == INF) return 0;
		else if(dist[arg0] == INF) return 1;
		else if(dist[arg1] == INF) return -1;


		if(dist[arg0]> dist[arg1])  return 1;
		else if(dist[arg0] < dist[arg1]) return -1;

		return 0;
	}
}
