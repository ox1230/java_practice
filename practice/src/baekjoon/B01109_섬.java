package baekjoon;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B01109_�� {
	public static void main(String[] args) {
		Island i = new Island();
		i.input();
		i.solve();
		System.out.println(i.makeString());

	} // end of main
} // end of class

class Island{
	int N;
	int M;
	int S[][];

	int cnt;  // ���� ��
	HashSet<Integer>[] naver ; // �̿��ϴ� ���� ����   naver[i]: i���� �̿��ϴ� ����.
	int[] parent;   // parent[i]:  i���� �ѷ��ΰ� �ִ� ��  (������ ���� �� ������ -1-- root��)
	int[] level;    // level : i���� ����

	int copyS[][][]; // �� ���� ����� ����.


	public void input() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		S = new int[N][M];
		// ���� - ���̸� -1, �ٴ��̸� 0

		String temp;
		for(int i=0; i < N ; i++){
			temp = sc.nextLine();

			for(int j= 0; j <M; j++){
				S[i][j] = temp.charAt(j) == 'x'? -1: 0;
			}
		}

		//		for (int i = 0; i < N; i++) {
		//			System.out.println(Arrays.toString(S[i]));
		//		}
	}
	public void solve() {
		// TODO Auto-generated method stub
		// parent�� �ϼ��Ѵ�.

		// �� ���� �����Ѵ�.
		cnt = 0; // 1���� ���ʴ�� ���� �󺧸�

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(S[i][j] == -1){
					cnt++;
					seperate(i,j,cnt);
				}
			}
		}

		//		System.out.println();
		//		for (int i = 0; i < N; i++) {
		//			System.out.println(Arrays.toString(S[i]));
		////		}

		// copyS�� ä���.
		copyS = new int[cnt+1][N][M];

		for (int c = 1; c <= cnt; c++) {
			for (int i = 0; i < N; i++) {
				System.arraycopy(S[i], 0, copyS[c][i], 0, M);
			}
		}
		// �� ���������� �̿� �� + ���� �� �ִ��� ���θ� ã�´�.
		// ���� �� ������ -1�� naver�� �߰�.

		naver = new HashSet[cnt+1];
		for (int i = 0; i <= cnt; i++) {
			naver[i] = new HashSet<Integer>();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(S[i][j] > 0 && !naver[S[i][j]].contains(-1)){   // i,j�� ���̰�  ���� ������ ���� �� �ִٴ� ���� �߰ߵ��� �ʾ�����
					findNaver(i,j,S[i][j],  copyS[S[i][j]]);
				}
			}
		}
		//System.out.println("break");
		parent = new int[cnt+1];
		
		// �� ���� �ѷ����� üũ   -- parent�ϼ��ϱ�.
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int i = 1; i <= cnt; i++) {
			if(naver[i].contains(-1)){
				parent[i] = -1;
				q.add(i);
				naver[i].remove(-1);
			}
		}
		int now;
		while(!q.isEmpty()){
			now = q.poll();
		
			for (int j : naver[now]) {
				if(parent[j] == 0){
					parent[j] = now;
					q.add(j);
					naver[j].remove(now);  // �ߺ�Ž�� ����
				}
			}
		}
		
		return;
	} // end of solve

	private void seperate(int i, int j, int cnt) {
		// TODO Auto-generated method stub

		if(i < 0 || i >= N || j<0 || j>=M){ // ������ ����� ����
			return;
		}
		else if(S[i][j] != -1){  // �ٸ� ���̰ų� �ٴ��̸� ����

			return;
		}
		else{  // �������̸�
			S[i][j] = cnt;
			seperate(i-1,j-1,cnt);
			seperate(i-1,j+1,cnt);
			seperate(i-1,j,cnt); // �Ʒ�
			seperate(i+1,j-1,cnt);
			seperate(i+1,j+1,cnt);
			seperate(i+1,j,cnt);  // ��
			seperate(i,j-1,cnt);// ��
			seperate(i,j+1,cnt); // ��

			return;
		}
	}

	private void findNaver(int i, int j, int me, int[][] map) {
		// TODO Auto-generated method stub
		// me: ���� ���ߴ� ��.

		if(i < 0 || i >= N || j<0 || j>=M){ // ������ ����� -����
			naver[me].add(-1);
		}
		else if(map[i][j] == -1){  // �̼����� �̹� Ȯ���� ���̸� pass
		}
		else if(map[i][j] == me || map[i][j] == 0){
			// �ڱⰡ ���� ���̰ų� �ٴ��̸�
			map[i][j] = -1;

			findNaver(i+1,j  ,me,map);
			findNaver(i-1,j  ,me,map);
			findNaver(i  ,j+1,me,map);
			findNaver(i  ,j-1,me,map);
		}
		else{ // �ٸ� ��B �߽߰�
			// ������ �̿��� ���� �߰�
			naver[S[i][j]].add(me);
			naver[me].add(S[i][j]);
		}
		return;
	}

	public String makeString() {
		// TODO Auto-generated method stub
		// level�� �������� ������ �����.

		if(cnt == 0) return "-1"; // �ƹ��͵� ������ -1���
		
		level = new int[cnt+1];
		
		// �ڱ⸦ parent�� �ϴ� ���� ������ level 1
		
		for (int i = 1; i <= cnt; i++) {
			if(level[i] > 0) continue;  
			
			boolean isRoot = true;
			for (int j = 1; j <= cnt; j++) {
				if(parent[j] == i){
					isRoot = false;
					break;
				}
			}
			if(isRoot) goToParent(i, 1);
			// i���� parent�� Ÿ�� �ö󰡸鼭 level�� �÷��ش�.
			//t¥���� parent�� ���� level2
		}
		
		int nums[] = new int[cnt+1];// �� ������ ���� ����
		
		for (int i = 1; i <= cnt; i++) {
			nums[level[i]] ++;
		}
		
		
		//���
		StringBuffer ret = new StringBuffer();
		
		for (int i = 1; i <= cnt; i++) {
			if(nums[i] == 0) break;
			ret.append(' ');
			ret.append(nums[i]);
		}
		ret.deleteCharAt(0); // �Ǿ��� ���� ����
		
		return  ret.toString();
	}
	
	private void goToParent(int i, int l) {
		// TODO Auto-generated method stub
		if(i == -1) return;
		else{
			if(level[i] < l){   // �� ���� ������ �ڽ��� �ڽ��� ���� ���� ������ ���ϴ� ���̴�.
				level[i] = l;
				goToParent(parent[i], l+1);
			}
		}
	}
}
