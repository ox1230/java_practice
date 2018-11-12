package baekjoon;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue; 


public class KevinEx {
	public static void main(String[] args) throws IOException{
		Kevin k = new Kevin();
		k.input();
		System.out.println(k.startSolve());
	}
}


class Kevin{
	private static final int LinkedList = 0;
	int N;
	int M;

	HashMap<Integer,Integer>[] dis;  // dis[a] {b} : a에서 b로 가기위해 거치는 단계의 수



	void input() throws IOException{
		Reader r = new Reader();

		N = r.nextInt();
		M = r.nextInt();

		int t,f;

		dis = new HashMap[N+1];

		for(int i=1;i<=N;i++){
			dis[i] = new HashMap<Integer,Integer>();
			dis[i].put(i,0);
		}

		for(int i=0;i<M;i++){
			t = r.nextInt();
			f = r.nextInt();

			dis[t].put(f,1);
			dis[f].put(t,1);
		}
	}

	int startSolve(){

		for(int i=1;i<=N;i++){
			fillDis(i);
		}

		int min = N*N;
		int person = 1;
		
		for(int i=1;i<=N;i++){
			int sum = 0;
			
			for(int j=1;j<=N;j++){
				sum += dis[i].get(j);
			}
			
			if(sum < min){
				min = sum;
				person = i;
			}
			
		}
		
		return person;
	}


	private void fillDis(int f){   // f 에서 via를 거쳐 to로 가는 거리
		int via;

		Queue<Integer> save = new LinkedList<Integer>();

		save.addAll(dis[f].keySet());

		while(!save.isEmpty()){
			via = save.poll();

			int disVia = dis[f].get(via);
			for(int to: dis[via].keySet()){
				int tempDis = dis[via].get(to) + disVia; 
				if(dis[f].containsKey(to)==false){
					dis[f].put(to, tempDis);
					save.add(to);
				}
				else if(dis[f].get(to) > tempDis){
					dis[f].replace(to,tempDis);
					save.add(to);
				}
				
			}
		}
	}
}

