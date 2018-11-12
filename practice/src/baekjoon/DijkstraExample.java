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


class Buses implements Comparator<Integer>{  // �켱���� ������ ���� Comparator implement
	int n;
	int m;
	int start;  // �������
	int dest; // ��������


	int[][]  busGraph; // ���ð� ���� ǥ��

	int[] dist; //A������ �ִܰŸ� 
	final int INF_LEN = -1; // �˼����� �Ÿ�(���Ѵ�)

	PriorityQueue<Integer> save;  // A���� �����ִ� �ִ� �Ÿ��� ���õ�


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
			if(i!= start) dist[i] = INF_LEN;  // -1 : �Ÿ��� �� �� ����(���Ѵ�)
		}


		int from; //���� ���� + �Ÿ� Ȯ��
		int transportDist; // start���� from�� ���� ���� �ּ� ���
		int oriDist; // j���� ���� �� �ɸ� ���� �Ÿ�
		int newDist; // from�� ���� j���� ���� ���ο� �Ÿ�

		save = new PriorityQueue<Integer>(this);

		save.add(start);

		while(!save.isEmpty()){
			from = save.poll();

			//if(from == dest) break;  // ��� �� Ȯ��

			transportDist = dist[from];

			for(int j=0;j<=n;j++){

				if(busGraph[from][j] < 0) continue;  // ����Ǿ����� �ʰų� �ڱ��ڽ�
				
				// ����Ǿ�������
				oriDist = dist[j];
				newDist = busGraph[from][j] + transportDist;


				if(oriDist!= INF_LEN && oriDist < newDist){} // ori�� new���� ������ ��ȭX
				else{
					dist[j] = newDist;  // j������ �Ÿ� ��ȸ

					save.remove(j);  // j�� �켱�� ������
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