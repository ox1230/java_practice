package baekjoon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeParent {
	public static void main(String[] args) throws IOException{
		Parent p = new Parent();
		p.input();
		p.findParent();
		System.out.println(p.printIt());
	}
}

class Parent{
	int N;
	ArrayList<Integer>[] near;  //�� ���� ����Ǿ��ִ� ����
	int[] P;  // �� ����� �θ���
	Queue<Integer> save = new LinkedList<Integer>();
	// �ڽ��� ã�� �θ� ������ �����Ѵ�.

	void input() throws IOException{
		Reader reader = new Reader();

		N = reader.nextInt();

		near = new ArrayList[N+1];
		P = new int[N+1];

		int X,Y;

		for(int i=1; i<=N ;i++){
			near[i] = new ArrayList<Integer>();
		}

		for(int i=0; i<N-1; i++){
			X = reader.nextInt();
			Y = reader.nextInt();


			if(X == 1){
				save.add(Y);
				P[Y] = 1;
			}
			else if(Y == 1){
				save.add(X);
				P[X] = 1;
			}
			else{
				near[X].add(Y);
				near[Y].add(X);
			}
		}

	}

	void findParent(){
		int parent;
		
		while(!save.isEmpty()){
			parent = save.poll();

			for(int node: near[parent]){
				if(P[parent] !=  node){
					P[node] = parent;
					save.add(node);
				}
			}
		}


	}

	String printIt(){

		// ���� return
		StringBuffer ret = new StringBuffer();

		for(int i=2;i<=N;i++){
			ret.append(P[i]+"\n");
		}

		return ret.toString();
	}
}