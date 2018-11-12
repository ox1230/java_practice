package baekjoon;


import java.util.HashSet;
import java.util.Scanner;

public class B14889_��ŸƮ�͸�ũ {
	public static void main(String[] args)  {
		StartLink sl = new StartLink();
		
		sl.input();
		System.out.println(sl.solve());
		
		
	} // end of main
} // end of class

class StartLink{
	int N;
	
	int S[][];
	
	HashSet<Integer> start = new HashSet<>();  // start����
	HashSet<Integer> link = new HashSet<>();   // link����
	
	int min = Integer.MAX_VALUE;  // �� ������ ������ �ּڰ�
	
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
		// i: �̹��� ����� ����
		// startS: start���� ����
		// linkS : link���� ����
		
		if(i == N){
			int temp = Math.abs(startS - linkS);
			
			if(temp < min){
//				System.out.println(start.toString());
//				System.out.println(link.toString());
				min = temp;
			}
		}
		else{ // i < N
			
			if(start.size() < N/2){  //size �������� ��
				int tt = startS;
				for (int j : start) {
					tt += S[i][j] + S[j][i];
				}
				start.add(i);
				
				divideTeam(i+1,tt,linkS);
				
				start.remove(i);
			}
			
			if(link.size() < N/2){  //link �������� ��
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