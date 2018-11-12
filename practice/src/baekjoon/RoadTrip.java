package baekjoon;

import java.util.PriorityQueue;
import java.util.Scanner;
// prim �˰��� Ȱ��
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
//�� ��庰�� �����ϴ� �� �ʿ��� �Ÿ�
class VPriorityCheck implements Comparable<VPriorityCheck>{
	int v;  // �� ���
	int w; // ���� �����ϸ� �����ϴ� �Ÿ�

	
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
	
	
	void input(Scanner sc){  // �׷��� �Է�
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
		 * 1���� �����ؼ�, �ֺ� ������ ���ʷ� ���� Ʈ���� ����ִ´�.
		 * ���� Ʈ���� ���� ��忡 ����� ���� �� ���� �Ÿ��� ª�� ������ ��带 Ʈ���� �����Ѵ�.
		 * (���� ����� �����ϴ� �Ÿ��� ���� ª�� ������ �����Ѵ�)
		 * V-1���� ��尡 Ʈ���� ����� �� ���� ����Ѵ�.
		 */
		
		boolean[] visited = new boolean[V+1];  
		PriorityQueue<VPriorityCheck> save = new PriorityQueue<>();
		
		VPriorityCheck temp;
		int now;  //���� �߰��� ���
		
		int sum = 0; // �� �Ÿ��� ��
		int cnt = 0; // �߰��� ����� ����
		
		save.add(new VPriorityCheck(1,0));
		
		while (!save.isEmpty()){
			temp = save.poll();
			
			now = temp.v;
			visited[now] = true;
			sum += temp.w;
			cnt++;
			if(cnt >= V-1) break; // �� ���� ���� �� �湮������ ����
			
			for(int next=1;next<=V;next++){  // ������ �湮�� ���õ� Ȯ��
				if(visited[next] == false && G[now][next] != INF){
					save.add(new VPriorityCheck(next,G[now][next]));
				}
			}
		}
		return sum*2; //�պ��̹Ƿ� 2�����ش�.
	}
}


