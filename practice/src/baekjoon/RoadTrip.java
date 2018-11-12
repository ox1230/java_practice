package baekjoon;

import java.util.PriorityQueue;
import java.util.Scanner;
// prim 알고리즘 활용
public class RoadTrip {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int K = sc.nextInt();
		
		for(int i=1; i<=K;i++){
			Trip t = new Trip();
			
			t.input(sc);
			System.out.println("Data Set "+i+":");
			System.out.println(t.solve());
		}
		
	}
	
}
//각 노드별로 연결하는 데 필요한 거리
class VPriorityCheck implements Comparable<VPriorityCheck>{
	int v;  // 각 노드
	int w; // 노드와 연결하면 증가하는 거리

	
	VPriorityCheck(int v, int w){
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(VPriorityCheck arg0) {
		// TODO Auto-generated method stub
		if(this.w < arg0.w) return -1;
		else if(this.w > arg0.w) return 1;
		else return 0;
	}
	
}

class Trip{
	int V;
	int E;
	final int INF=-1;
	
	int[][] G;
	
	
	void input(Scanner sc){  // 그래프 입력
		V = sc.nextInt();
		E = sc.nextInt();
		
		int v1,v2,w;
		
		G = new int[V+1][V+1];
		
		for(int i=0;i<=V;i++)for(int j=0;j<=V;j++){
			G[i][j] = INF;
		}
		for(int i=1;i<=V;i++){
			G[i][i] = 0;
		}
		
		for(int i=0;i<E;i++){
			v1 = sc.nextInt();
			v2 = sc.nextInt();
			w = sc.nextInt();
				
			G[v1][v2] = w;
			G[v2][v1] = w;
		}
	}
	
	int solve(){  
		/*
		 * 1부터 시작해서, 주변 노드들을 차례로 신장 트리에 집어넣는다.
		 * 현재 트리에 속한 노드에 연결된 간선 중 가장 거리가 짧은 간선의 노드를 트리에 삽입한다.
		 * (남은 노드중 연결하는 거리가 가장 짧은 노드부터 연결한다)
		 * V-1개의 노드가 트리에 연결될 떄 까지 계속한다.
		 */
		
		boolean[] visited = new boolean[V+1];  
		PriorityQueue<VPriorityCheck> save = new PriorityQueue<>();
		
		VPriorityCheck temp;
		int now;  //새로 추가된 노드
		
		int sum = 0; // 총 거리의 합
		int cnt = 0; // 추가된 노드의 개수
		
		save.add(new VPriorityCheck(1,0));
		
		while (!save.isEmpty()){
			temp = save.poll();
			
			now = temp.v;
			visited[now] = true;
			sum += temp.w;
			cnt++;
			if(cnt >= V-1) break; // 한 도시 빼고 다 방문했으면 종료
			
			for(int next=1;next<=V;next++){  // 다음에 방문할 도시들 확인
				if(visited[next] == false && G[now][next] != INF){
					save.add(new VPriorityCheck(next,G[now][next]));
				}
			}
		}
		return sum*2; //왕복이므로 2배해준다.
	}
}


