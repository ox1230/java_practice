package baekjoon;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class B7576_Tomato {
	public static void main(String[] args) throws IOException{
		Tomato t = new Tomato();
		t.input();
		System.out.println(t.startSolve());
	}
}

class Tomato{
	int M;
	int N;
	int T[][];
	final int MAX_NUM = 1000* 1000 + 2;
	
	void input() throws IOException{
		Reader r = new Reader();
		
		M = r.nextInt();
		N = r.nextInt();
		
		T = new int[N][M];
		
		for(int i=0;i<N;i++)for(int j=0;j<M;j++){
			T[i][j] = r.nextInt();
			if(T[i][j] == 0) T[i][j] = MAX_NUM;
		}
	}
	
	int startSolve(){
		Queue<Integer> saveRow = new LinkedList<>();  //익은 토마토의 행
		Queue<Integer> saveCol = new LinkedList<>();  //익은 토마토의 열
		
		for(int i=0;i<N;i++)for(int j=0;j<M;j++){
			if(T[i][j] == 1){
				saveRow.add(i);
				saveCol.add(j);
			}
		}
		
		makeRipe(saveRow,saveCol);
		
		int max = 0;
		for(int i=0;i<N;i++)for(int j=0;j<M;j++){
			if(max  < T[i][j])  max = T[i][j]; 
		}
		
		int ret;
		if(max == MAX_NUM) ret = -1;
		else{
			ret = max -1 ;  // 익은 상태가 1부터 시작하므로 시간은 1을 빼준다.
		}
		
		return ret;
	}
	
	void makeRipe(Queue<Integer> saveRow, Queue<Integer> saveCol){
		int tempR, tempC;
		while(!saveRow.isEmpty()){
			tempR = saveRow.poll();
			tempC = saveCol.poll();
			
			int thisNum = T[tempR][tempC] +1;  
			
			if(tempR+1 <N && T[tempR+1][tempC] > thisNum){
				T[tempR+1][tempC] = thisNum;
				
				saveRow.add(tempR +1);
				saveCol.add(tempC);
			}
			if(tempR - 1 >= 0 && T[tempR-1][tempC] > thisNum){
				T[tempR - 1][tempC] = thisNum;
				
				saveRow.add(tempR -1);
				saveCol.add(tempC);
			}
			
			if(tempC +1 <M && T[tempR][tempC+1] > thisNum){
				T[tempR][tempC+1] = thisNum;
				
				saveRow.add(tempR);
				saveCol.add(tempC+1);
			}
			
			if(tempC -1 >= 0 && T[tempR][tempC-1] > thisNum){
				T[tempR][tempC - 1] = thisNum;
				
				saveRow.add(tempR);
				saveCol.add(tempC-1);
			}
		}
	}
}