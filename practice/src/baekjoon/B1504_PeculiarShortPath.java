package baekjoon;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;

public class B1504_PeculiarShortPath {

	public static void main(String[] args) throws IOException{
		PSPath p = new PSPath();
		
		p.input();
		System.out.println(p.startSolve());
		System.out.println();
		
	}
}
class VandD implements Comparable<VandD>{
	int v;  // 거리를 계산한 정점
	int dis;  // 현재계산한 정점까지의 거리

	VandD(){}
	VandD(int v, int d){
		this.v = v;
		this.dis = d;
	}
	@Override
	public int compareTo(VandD arg0) {
		// TODO Auto-generated method stub
		if(this.dis <  arg0.dis) return -1;
		else if(this.dis > arg0.dis) return 1;
		return 0;
	}
	@Override
	public String toString() {
		return "[v=" + v + ", dis=" + dis + "]";
	}
	
	
}

class PSPath{
	int N;
	int E;
	int v1, v2;
	HashMap<Integer,Integer>[] graph;

	HashMap<Integer,Integer> from = new HashMap<>();  // start에서의 최단거리

	@SuppressWarnings("unchecked")
	void input() throws IOException{
		Reader r = new Reader();

		N = r.nextInt();
		E = r.nextInt();
		
		graph = new HashMap[N+1];

		for(int i=1;i<=N;i++){
			graph[i] = new HashMap<Integer,Integer>();

			graph[i].put(i,0);
		}

		int f,t,c;
		for(int i=0;i<E;i++){
			f = r.nextInt();
			t = r.nextInt();
			c = r.nextInt();	
			
			if(graph[f].containsKey(t) && graph[f].get(t) <= c) continue; // f에서 t까지의 가장 짧은 길만 취급 
			
			graph[f].put(t, c);
			graph[t].put(f, c);
		}

		v1 = r.nextInt();
		v2 = r.nextInt();
	}

	long startSolve(){
		// 거리가 존재하지 않으면 -1
		fillFrom(1, v1 , v2);
		int from1toV1 = from.getOrDefault(v1, -1);
		int from1toV2 = from.getOrDefault(v2, -1);
		
		fillFrom(v1,v2 , N);
		int fromV1toV2 = from.getOrDefault(v2, -1);
		int fromV1toN = from.getOrDefault(N, -1);
		
		fillFrom(v2, N, N);  // fromv1tov2 와 fromv2tov1은 같으므로 N까지의 거리만 구하면 된다.
		int fromV2toN = from.getOrDefault(N,-1);
		
		long case1;
		if(from1toV1 == -1 || fromV1toV2 == -1 || fromV2toN == -1){
			case1 = -1;
		}
		else{
			case1 = from1toV1 + fromV1toV2 + fromV2toN;
		}
		
		
		long case2;
		if(from1toV2 == -1 || fromV1toV2 == -1 || fromV1toN == -1){
			case2 = -1;
		}
		else{
			case2 = from1toV2 + fromV1toV2 + fromV1toN;
		}
		
		long ret;
		if(case1 == -1 && case2 == -1) ret = -1;
		else if(case1 == -1) ret = case2;
		else if(case2 == -1) ret = case1;
		else ret = case1 < case2 ? case1 : case2;
		
		return ret;
	}

	void fillFrom(int start, int to1, int to2){   // to1과 to2가 모두 해결되면 종료한다.
		PriorityQueue<VandD> save = new PriorityQueue<>();
		from = new HashMap<Integer,Integer>();
		
		boolean canFinish = false;
		if(to1 == to2) canFinish = true; // to1과 to2가 같으면 한번만 걸리면 break
		
		from.put(start,0);
		save.add(new VandD(start,0));

		int v , dis;
		while(!save.isEmpty()){
			v = save.peek().v;
			dis = save.poll().dis;

			if(v == to1 || v == to2){
				if(canFinish == true) break;  // 둘다 찾음
				else canFinish = true;  // 다음에 찾으면 break.
			}
			if(from.get(v) < dis) continue;

			for(int tempV : graph[v].keySet()){
				int tempDis = dis + graph[v].get(tempV);

				int dd = from.getOrDefault(tempV, -1);
				if( dd == -1){  // tempV까지의 거리가 없으면
					from.put(tempV, tempDis);
					save.add(new VandD(tempV,tempDis));
				}else if( dd > tempDis){ // tempV까지의 기존 거리가 tempDis보다 크다면
					from.replace(tempV, tempDis);
					save.add(new VandD(tempV,tempDis));
				}
			}
		}
	}

}