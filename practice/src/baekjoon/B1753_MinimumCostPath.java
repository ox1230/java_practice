package baekjoon;

import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;


//Dijkstra �˰��� Ȱ��   1753�� �ִܰ��
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

	final int INF= -1;  // �˼� ���� �Ÿ�
	HashMap<Integer,Integer>[] G; // �׷���   <k,w> : k���� ���µ� �ɸ��� �Ÿ� w
	boolean[] isFinished;    //������ �̹� �ִܰŸ��� ���Դ��� üũ
	
	int[] dist; //start������ �ִ� �Ÿ�
	
	@SuppressWarnings("unchecked")
	void input() throws IOException{
		Reader r = new Reader();
	
		
		V = r.nextInt();
		E = r.nextInt();
		
		start = r.nextInt();
		
		G = new HashMap[V+1];  // 1���� �����ϹǷ� �Ѱ� �� ����
		isFinished = new boolean[V+1];
		
		for(int i=0;i<=V;i++){ // graph �ʱ�ȭ
			G[i] = new HashMap<Integer,Integer>();
			G[i].put(i, 0);
		}

		for(int i=0;i<E;i++){
			int u = r.nextInt();
			int v = r.nextInt();
			int w = r.nextInt();
			
			if(G[u].containsKey(v) && G[u].get(v) <w) continue; // ����ġ�� ���� ���� ���� �Ѱ��� �����.
			
			G[u].put(v,w);
		}
	}
	
	void dijkstra(){
		dist = new int[V+1];
		PriorityQueue<VandD> save = new PriorityQueue<VandD>();// �ִܰŸ������� ���� ����
		int temp; // Ȯ���� ����
		
		for(int i=0;i<=V;i++){
			dist[i] = INF;
		}
		dist[start] = 0;
		save.add(new VandD(start,0));
		
		while(!save.isEmpty()){
			temp = save.poll().v;
			
			if(isFinished[temp] == true) continue;  //�̹� üũ�� �����̹Ƿ� 
			
			int toTemp = dist[temp];
			isFinished[temp] = true;
			
			for(int i=1;i<=V;i++){
				if(G[temp].containsKey(i)){  // temp���� i���� ���� �����ϸ�
					int otherDis = G[temp].get(i) + toTemp;  // temp�� ���İ��� ���ο� ���� Ȯ���Ѵ�
					
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


