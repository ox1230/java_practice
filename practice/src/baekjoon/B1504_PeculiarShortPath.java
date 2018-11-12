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
	int v;  // �Ÿ��� ����� ����
	int dis;  // �������� ���������� �Ÿ�

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

	HashMap<Integer,Integer> from = new HashMap<>();  // start������ �ִܰŸ�

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
			
			if(graph[f].containsKey(t) && graph[f].get(t) <= c) continue; // f���� t������ ���� ª�� �游 ��� 
			
			graph[f].put(t, c);
			graph[t].put(f, c);
		}

		v1 = r.nextInt();
		v2 = r.nextInt();
	}

	long startSolve(){
		// �Ÿ��� �������� ������ -1
		fillFrom(1, v1 , v2);
		int from1toV1 = from.getOrDefault(v1, -1);
		int from1toV2 = from.getOrDefault(v2, -1);
		
		fillFrom(v1,v2 , N);
		int fromV1toV2 = from.getOrDefault(v2, -1);
		int fromV1toN = from.getOrDefault(N, -1);
		
		fillFrom(v2, N, N);  // fromv1tov2 �� fromv2tov1�� �����Ƿ� N������ �Ÿ��� ���ϸ� �ȴ�.
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

	void fillFrom(int start, int to1, int to2){   // to1�� to2�� ��� �ذ�Ǹ� �����Ѵ�.
		PriorityQueue<VandD> save = new PriorityQueue<>();
		from = new HashMap<Integer,Integer>();
		
		boolean canFinish = false;
		if(to1 == to2) canFinish = true; // to1�� to2�� ������ �ѹ��� �ɸ��� break
		
		from.put(start,0);
		save.add(new VandD(start,0));

		int v , dis;
		while(!save.isEmpty()){
			v = save.peek().v;
			dis = save.poll().dis;

			if(v == to1 || v == to2){
				if(canFinish == true) break;  // �Ѵ� ã��
				else canFinish = true;  // ������ ã���� break.
			}
			if(from.get(v) < dis) continue;

			for(int tempV : graph[v].keySet()){
				int tempDis = dis + graph[v].get(tempV);

				int dd = from.getOrDefault(tempV, -1);
				if( dd == -1){  // tempV������ �Ÿ��� ������
					from.put(tempV, tempDis);
					save.add(new VandD(tempV,tempDis));
				}else if( dd > tempDis){ // tempV������ ���� �Ÿ��� tempDis���� ũ�ٸ�
					from.replace(tempV, tempDis);
					save.add(new VandD(tempV,tempDis));
				}
			}
		}
	}

}