package baekjoon;


import java.util.HashSet;
import java.util.Scanner;

public class B14889_스타트와링크 {
	public static void main(String[] args)  {
		StartLink sl = new StartLink();
		
		sl.input();
		System.out.println(sl.solve());
		
		
	} // end of main
} // end of class

class StartLink{
	int N;
	
	int S[][];
	
	HashSet<Integer> start = new HashSet<>();  // start팀원
	HashSet<Integer> link = new HashSet<>();   // link팀원
	
	int min = Integer.MAX_VALUE;  // 두 팀사이 점수의 최솟값
	
	public void input() {
		// TODO Auto-generated method stub
		Scanner r = new Scanner(System.in);
		
		N = r.nextInt();
		
		S  = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				S[i][j] = r.nextInt();
			}
		}
		
	}

	public int solve() {
		// TODO Auto-generated method stub
		
		divideTeam(0,0,0);
		
		return min;
	}
	
	void divideTeam(int i, int startS, int linkS){
		// i: 이번에 배분할 팀원
		// startS: start팀의 점수
		// linkS : link팀의 점수
		
		if(i == N){
			int temp = Math.abs(startS - linkS);
			
			if(temp < min){
//				System.out.println(start.toString());
//				System.out.println(link.toString());
				min = temp;
			}
		}
		else{ // i < N
			
			if(start.size() < N/2){  //size 팀원으로 때
				int tt = startS;
				for (int j : start) {
					tt += S[i][j] + S[j][i];
				}
				start.add(i);
				
				divideTeam(i+1,tt,linkS);
				
				start.remove(i);
			}
			
			if(link.size() < N/2){  //link 팀원으로 때
				int tt = linkS;
				for (int j : link) {
					tt += S[i][j] + S[j][i];
				}
				link.add(i);
				
				divideTeam(i+1, startS , tt);
				
				link.remove(i);
			}
			
		}
		
	}
	
}