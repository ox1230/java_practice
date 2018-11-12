package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DFSnBFS {
	public static void main(String[] args){
		Graph g = new Graph();
		
		g.input();
		g.DFS();
		System.out.println();
		g.BFS();
		
	}
}

class Graph{
	int N;
	int M;
	int start;  // Ž�� ���� ��
	int[][] G;  // graph
	
	
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		N= sc.nextInt();
		M = sc.nextInt();
		start = sc.nextInt();
		
		G = new int[N+1][N+1];
		
		int f,t;
		
		for(int i=0;i<M;i++){
			f = sc.nextInt();
			t = sc.nextInt();
			
			G[f][t] = 1;
			G[t][f] = 1;
		}
		
		sc.close();
	}
	
	void DFS(){
		Stack<Integer> save = new Stack<Integer>();   // ���ư��� �� ��� ����
		boolean[] chk = new boolean[N+1]; // ������ �� üũ
		int v; //���� ���
		int w; //������ ���
		
		
		save.push(start);
		System.out.print(start+" ");
		chk[start] = true;

		while(!save.isEmpty()){
			v = save.pop();
			
			while(true){
				boolean isContinue=false;
				for(w = 1; w<=N; w++){
					if(G[v][w]==1 && chk[w]==false){
						chk[w] = true;
						save.push(v);
						System.out.print(w+" ");
						v = w;
						isContinue = true;
						break;
					}
				}
				
				if(isContinue == false) break;
			}
			
		}
	}
	
	void BFS(){
		Queue<Integer> save = new LinkedList<Integer>();   // ������ �� �� ����
		boolean[] chk = new boolean[N+1];  // �̹� ���� üũ
		int v; // �̹��� �� ��
		int w; // ������ �� ��
		save.add(start);
		chk[start] = true;
		
		while(!save.isEmpty()){
			v = save.poll();
			System.out.print(v+" ");
			
			
			for(w=1; w<=N; w++ ){
				if(G[v][w] != 0 && chk[w]==false){
					chk[w] = true;
					save.add(w);
				}
			}
			
		}
	}
}
