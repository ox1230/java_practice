package baekjoon;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MinimumSpanningTree {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		Spanning s = new Spanning();
		s.input(sc);
		
		System.out.println(s.solve());
		
	}
}


// prim �˰��� Ȱ��

//�� ��庰�� �����ϴ� �� �ʿ��� �Ÿ�
class PriorityCheck implements Comparable<PriorityCheck>{
	int v;  // �� ���
	long w; // ���� �����ϸ� �����ϴ� �Ÿ�

	
	PriorityCheck(int v, long w){
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(PriorityCheck arg0) {
		// TODO Auto-generated method stub
		if(this.w < arg0.w) return -1;
		else if(this.w > arg0.w) return 1;
		else return 0;
	}

	@Override
	public String toString() {
		return "[v=" + v + ", w=" + w + "]";
	}
	
	
}

class Spanning{
	int V;
	int E;
	final long INF= 10000001;
	
	HashMap<Integer,Long>[] G;
	
	
	void input(Scanner sc){  // �׷��� �Է�
		V = sc.nextInt();
		E = sc.nextInt();
		
		int v1,v2;
		long w;
		
		G = new HashMap[V+1] ;
		
		for(int i=0;i<=V;i++){
			G[i] = new HashMap<Integer,Long>();
		}
		
		for(int i=0;i<E;i++){
			v1 = sc.nextInt();
			v2 = sc.nextInt();
			w = sc.nextLong();
				
			G[v1][v2] = w;
			G[v2][v1] = w;
		}
	}
	
	long solve(){  
		/*
		 * 1���� �����ؼ�, �ֺ� ������ ���ʷ� ���� Ʈ���� ����ִ´�.
		 * ���� Ʈ���� ���� ��忡 ����� ���� �� ���� �Ÿ��� ª�� ������ ��带 Ʈ���� �����Ѵ�.
		 * (���� ����� �����ϴ� �Ÿ��� ���� ª�� ������ �����Ѵ�)
		 * V-1���� ��尡 Ʈ���� ����� �� ���� ����Ѵ�.
		 */
		
		boolean[] visited = new boolean[V+1];  
		PriorityQueue<PriorityCheck> save = new PriorityQueue<>();
		
		PriorityCheck temp;
		int now;  //���� �߰��� ���
		
		long sum = 0; // �� �Ÿ��� ��
		int cnt = 0; // �߰��� ����� ����
		
		save.add(new PriorityCheck(1,0));
		
		while (!save.isEmpty()){
			temp = save.poll();
			
			now = temp.v;
			if(visited[now] == true) continue;
			
			visited[now] = true;
			sum += temp.w;
			cnt++;
			if(cnt >= V) break; //  �� �湮������ ����
			
			for(int next=1;next<=V;next++){  // ������ �湮�� ���õ� Ȯ��
				if(visited[next] == false && G[now][next] != INF){
					save.add(new PriorityCheck(next,G[now][next]));
				}
			}
		}
		return sum;
	}
}

