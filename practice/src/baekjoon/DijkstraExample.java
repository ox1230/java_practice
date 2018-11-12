package baekjoon;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DijkstraExample {
	public static void main(String[] args){
		Buses b = new Buses();
		
		b.input();
		b.solve();
		b.print();
		
	}
}


class Buses implements Comparator<Integer>{  // 우선순위 조정을 위해 Comparator implement
	int n;
	int m;
	int start;  // 출발지점
	int dest; // 도착지점


	int[][]  busGraph; // 도시간 연결 표시

	int[] dist; //A에서의 최단거리 
	final int INF_LEN = -1; // 알수없는 거리(무한대)

	PriorityQueue<Integer> save;  // A에서 갈수있는 최단 거리의 도시들


	void input(){
		Scanner sc =new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		
		
		busGraph = new int[n+1][n+1];
		dist = new int[n+1];
		
		for(int i=0;i<=n;i++)for(int j=0;j<=n;j++) busGraph[i][j] = INF_LEN;
		
		
		int from, to , w;
		for(int i=0;i<m;i++){
			from = sc.nextInt();
			to = sc.nextInt();
			w = sc.nextInt();
			
			
			if(busGraph[from][to]== INF_LEN || w <  busGraph[from][to]){
				busGraph[from][to] = w;
			}
		}


		start = sc.nextInt();
		dest = sc.nextInt();

		sc.close();

	}

	int solve(){
		for(int i=0;i<=n;i++){
			if(i!= start) dist[i] = INF_LEN;  // -1 : 거리를 알 수 없음(무한대)
		}


		int from; //에서 시작 + 거리 확정
		int transportDist; // start에서 from을 거쳐 가는 최소 비용
		int oriDist; // j까지 가는 데 걸린 기존 거리
		int newDist; // from을 거쳐 j까지 가는 새로운 거리

		save = new PriorityQueue<Integer>(this);

		save.add(start);

		while(!save.isEmpty()){
			from = save.poll();

			//if(from == dest) break;  // 결과 값 확정

			transportDist = dist[from];

			for(int j=0;j<=n;j++){

				if(busGraph[from][j] < 0) continue;  // 연결되어있지 않거나 자기자신
				
				// 연결되어있으면
				oriDist = dist[j];
				newDist = busGraph[from][j] + transportDist;


				if(oriDist!= INF_LEN && oriDist < newDist){} // ori가 new보다 작으면 변화X
				else{
					dist[j] = newDist;  // j까지의 거리 변회

					save.remove(j);  // j의 우선도 재조정
					save.add(j);
				}
			}
		}

		return dist[dest];
	}
	
	void print(){
		for(int i=1;i<=n;i++){
			if(dist[i] != INF_LEN)System.out.println(dist[i]);
			else System.out.println("INF");
		}
	}
	@Override
	public int compare(Integer arg0, Integer arg1) {
		// TODO Auto-generated method stub
		if(arg0==-1 && arg1 == INF_LEN) return 0;
		else if(arg0 == INF_LEN) return 1;
		else if(arg1 == INF_LEN) return -1;


		if(dist[arg0]> dist[arg1])  return 1;
		else if(dist[arg0] < dist[arg1]) return -1;

		return 0;
	}
}