package baekjoon;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


public class B02667_단지번호붙이기 {
	public static void main(String[] args) throws IOException{
		Apartment a = new Apartment();
		a.input();
		a.solveAndPrint();
		
		
		
	}
}

class Apartment{
	int N;
	int[][] S;
	LinkedList<Integer> sizes = new LinkedList<>();
	void input() throws IOException{
		Reader r = new Reader();
		
		N = Integer.parseInt(r.nextLine());
		
		S = new int[N][N];
		
		
		
		String temp;
		for(int i=0;i<N;i++){
			temp = r.nextLine();
			for(int j=0;j<N;j++){
				S[i][j] = temp.charAt(j) - '0';
			}
		}
	}
	
	void solveAndPrint(){
		int cnt = 0;
		int size;
		// 한개씩 확인하며 단지 체크
		for(int i=0;i<N;i++)for(int j=0;j<N;j++){
			if(S[i][j] == 1){
				size = dfs(i,j,0);
				sizes.add(size);
				cnt++;
			}
		}
		
		sizes.sort(null);
		
		System.out.println(cnt);
		
		Iterator<Integer> s = sizes.iterator();
		while(s.hasNext()){
			System.out.println(s.next());
		}

	}

	private int dfs(int i, int j, int size) {
		// 단지의 사이즈 return
		size++;
		S[i][j] = 0;
		//상하좌우 확인
		if(i-1 >= 0 && S[i-1][j] == 1) size = dfs(i-1,j, size); 
		if(j-1 >= 0 && S[i][j-1] == 1) size = dfs(i,j-1, size);
		if(i+1 < N && S[i+1][j] == 1) size = dfs(i+1,j, size);
		if(j+1 < N && S[i][j+1] == 1) size = dfs(i,j+1, size);
		
		return size;
	}
	
	
	
	
}