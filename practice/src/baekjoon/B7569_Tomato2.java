package baekjoon;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class B7569_Tomato2 {
	public static void main(String[] args) throws IOException{
		Tomato2 t = new Tomato2();
		t.input();
		System.out.println(t.startSolve());
	}
}
// 7576 tomato ���� ����
class Tomato2{
	int M;
	int N;
	int H;
	int T[][][];
	final int MAX_NUM = 1000* 1000 + 2;
	
	void input() throws IOException{
		Reader r = new Reader();
		
		M = r.nextInt();
		N = r.nextInt();
		H = r.nextInt();
		
		T = new int[H][N][M];
		
		for(int k=0;k<H;k++)for(int i=0;i<N;i++)for(int j=0;j<M;j++){
			T[k][i][j] = r.nextInt();
			if(T[k][i][j] == 0) T[k][i][j] = MAX_NUM;
		}
	}
	
	int startSolve(){
		Queue<Integer> saveRow = new LinkedList<>();  //���� �丶���� ��
		Queue<Integer> saveCol = new LinkedList<>();  //���� �丶���� ��
		Queue<Integer> saveBox = new LinkedList<>(); // ���� �丶���� ���ڹ�ȣ
		
		for(int k=0;k<H;k++)for(int i=0;i<N;i++)for(int j=0;j<M;j++){
			if(T[k][i][j] == 1){
				saveRow.add(i);
				saveCol.add(j);
				saveBox.add(k);
			}
		}
		
		makeRipe(saveBox,saveRow,saveCol);
		
		int max = 0;
		for(int k=0;k<H;k++)for(int i=0;i<N;i++)for(int j=0;j<M;j++){
			if(max  < T[k][i][j])  max = T[k][i][j]; 
		}
		
		int ret;
		if(max == MAX_NUM) ret = -1;
		else{
			ret = max -1 ;  // ���� ���°� 1���� �����ϹǷ� �ð��� 1�� ���ش�.
		}
		
		return ret;
	}
	
	void makeRipe(Queue<Integer> saveBox, Queue<Integer> saveRow, Queue<Integer> saveCol){
		int tempB, tempR, tempC;
		while(!saveRow.isEmpty()){
			tempR = saveRow.poll();
			tempC = saveCol.poll();
			tempB = saveBox.poll();
			
			int thisNum = T[tempB][tempR][tempC] +1;  
			
			if(tempR+1 <N && T[tempB][tempR+1][tempC] > thisNum){
				T[tempB][tempR+1][tempC] = thisNum;
				
				saveRow.add(tempR +1);
				saveCol.add(tempC);
				saveBox.add(tempB);
			} //��
			if(tempR - 1 >= 0 && T[tempB][tempR-1][tempC] > thisNum){
				T[tempB][tempR - 1][tempC] = thisNum;
				
				saveRow.add(tempR -1);
				saveCol.add(tempC);
				saveBox.add(tempB);
			} // ��
			
			if(tempC +1 <M && T[tempB][tempR][tempC+1] > thisNum){
				T[tempB][tempR][tempC+1] = thisNum;
				
				saveRow.add(tempR);
				saveCol.add(tempC+1);
				saveBox.add(tempB);
			} // ������
			
			if(tempC -1 >= 0 && T[tempB][tempR][tempC-1] > thisNum){
				T[tempB][tempR][tempC - 1] = thisNum;
				
				saveRow.add(tempR);
				saveCol.add(tempC-1);
				saveBox.add(tempB);
			} // ����
			
			if(tempB+1 <H && T[tempB+1][tempR][tempC] > thisNum){
				T[tempB+1][tempR][tempC] = thisNum;
				
				saveRow.add(tempR);
				saveCol.add(tempC);
				saveBox.add(tempB+1);
			} // �Ʒ�
			
			if(tempB -1 >= 0 && T[tempB-1][tempR][tempC] > thisNum){
				T[tempB-1][tempR][tempC] = thisNum;
				
				saveRow.add(tempR);
				saveCol.add(tempC);
				saveBox.add(tempB-1);
			} // ��
			
		}
	}
}