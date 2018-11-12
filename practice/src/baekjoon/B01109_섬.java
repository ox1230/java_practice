package baekjoon;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B01109_섬 {
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

	int cnt;  // 섬의 수
	HashSet<Integer>[] naver ; // 이웃하는 섬의 집합   naver[i]: i섬의 이웃하는 섬들.
	int[] parent;   // parent[i]:  i섬을 둘러싸고 있는 섬  (밖으로 나갈 수 있으면 -1-- root섬)
	int[] level;    // level : i섬의 레벨

	int copyS[][][]; // 각 섬용 복사된 지도.


	public void input() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		S = new int[N][M];
		// 지도 - 섬이면 -1, 바다이면 0

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
		// parent를 완성한다.

		// 각 섬을 구분한다.
		cnt = 0; // 1부터 차례대로 섬에 라벨링

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

		// copyS를 채운다.
		copyS = new int[cnt+1][N][M];

		for (int c = 1; c <= cnt; c++) {
			for (int i = 0; i < N; i++) {
				System.arraycopy(S[i], 0, copyS[c][i], 0, M);
			}
		}
		// 각 지점에서의 이웃 섬 + 나갈 수 있는지 여부를 찾는다.
		// 나갈 수 있으면 -1을 naver에 추가.

		naver = new HashSet[cnt+1];
		for (int i = 0; i <= cnt; i++) {
			naver[i] = new HashSet<Integer>();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(S[i][j] > 0 && !naver[S[i][j]].contains(-1)){   // i,j가 섬이고  지도 밖으로 나갈 수 있다는 것이 발견되지 않았으면
					findNaver(i,j,S[i][j],  copyS[S[i][j]]);
				}
			}
		}
		//System.out.println("break");
		parent = new int[cnt+1];
		
		// 각 섬의 둘러쌓임 체크   -- parent완성하기.
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
					naver[j].remove(now);  // 중복탐색 제거
				}
			}
		}
		
		return;
	} // end of solve

	private void seperate(int i, int j, int cnt) {
		// TODO Auto-generated method stub

		if(i < 0 || i >= N || j<0 || j>=M){ // 지도를 벗어나면 종료
			return;
		}
		else if(S[i][j] != -1){  // 다른 섬이거나 바다이면 종료

			return;
		}
		else{  // 같은섬이면
			S[i][j] = cnt;
			seperate(i-1,j-1,cnt);
			seperate(i-1,j+1,cnt);
			seperate(i-1,j,cnt); // 아래
			seperate(i+1,j-1,cnt);
			seperate(i+1,j+1,cnt);
			seperate(i+1,j,cnt);  // 위
			seperate(i,j-1,cnt);// 좌
			seperate(i,j+1,cnt); // 우

			return;
		}
	}

	private void findNaver(int i, int j, int me, int[][] map) {
		// TODO Auto-generated method stub
		// me: 원래 속했던 섬.

		if(i < 0 || i >= N || j<0 || j>=M){ // 지도를 벗어나면 -종료
			naver[me].add(-1);
		}
		else if(map[i][j] == -1){  // 이섬에서 이미 확인한 곳이면 pass
		}
		else if(map[i][j] == me || map[i][j] == 0){
			// 자기가 속한 섬이거나 바다이면
			map[i][j] = -1;

			findNaver(i+1,j  ,me,map);
			findNaver(i-1,j  ,me,map);
			findNaver(i  ,j+1,me,map);
			findNaver(i  ,j-1,me,map);
		}
		else{ // 다른 섬B 발견시
			// 각자의 이웃에 각자 추가
			naver[S[i][j]].add(me);
			naver[me].add(S[i][j]);
		}
		return;
	}

	public String makeString() {
		// TODO Auto-generated method stub
		// level을 바탕으로 정답을 만든다.

		if(cnt == 0) return "-1"; // 아무것도 없으면 -1출력
		
		level = new int[cnt+1];
		
		// 자기를 parent로 하는 섬이 없으면 level 1
		
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
			// i에서 parent를 타고 올라가면서 level을 올려준다.
			//t짜리의 parent인 섬은 level2
		}
		
		int nums[] = new int[cnt+1];// 각 레벨별 섬의 개수
		
		for (int i = 1; i <= cnt; i++) {
			nums[level[i]] ++;
		}
		
		
		//출력
		StringBuffer ret = new StringBuffer();
		
		for (int i = 1; i <= cnt; i++) {
			if(nums[i] == 0) break;
			ret.append(' ');
			ret.append(nums[i]);
		}
		ret.deleteCharAt(0); // 맨앞의 공백 제거
		
		return  ret.toString();
	}
	
	private void goToParent(int i, int l) {
		// TODO Auto-generated method stub
		if(i == -1) return;
		else{
			if(level[i] < l){   // 각 섬의 레벨은 자신의 자식중 가장 높은 레벨을 취하는 것이다.
				level[i] = l;
				goToParent(parent[i], l+1);
			}
		}
	}
}
