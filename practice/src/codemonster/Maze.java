package codemonster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import com.sun.javafx.scene.control.skin.VirtualFlow.ArrayLinkedList;

public class Maze {

	public static void main(String[] args) {
		// TODO Auto-generated method study
		int C;
		File f = new File("input.txt");
		try {
			FileWriter rw = new FileWriter("result.txt");
			PrintWriter pw = new PrintWriter(rw);
			Scanner sc;
			try {
				sc = new Scanner(f);
				C = sc.nextInt();
				sc.nextLine();
				
				for(int i=0;i<8;i++){
					System.out.println(i+"번째");
					MazeSet com = new MazeSet();
					
					com.getInput(sc);
					com.findEAndTresure();
					com.findAllOptWay();
						
					com.findAllMaxWay();
					
					int max = com.findMax();
					
					pw.println(max);
	
				}
				sc.close();
				rw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}

class MazeSet{
	char[][] maze;
	int n;
	int m;
	MazePos E;
	int[][] d = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
	List<MazePos> trs=  new ArrayList<MazePos>();  //보물들과 출입구의 집합
	int cnT; //보물의 수+출입구수
	int[][] costOf; //각 출입구,보물에서 다른 출입구,보물까지의 거리
	OptimalWay[][][] onePiece;
	
	class MazePos{
		int row=0;
		int col=0;
		int cost=0;
		int score=0;
		
		MazePos(int r,int col,int cost,int score){
			this.row = r;
			this.col = col;
			this.cost = cost;
			this.score = score;
		}
		
		MazePos(int r,int c){
			this.row =r ; this.col = c;
		}
		
		MazePos(){
		}
		
	}
	class OptimalWay{  //도착지점까지 n개의 보물을 거치는 적의 방법
		
		int netScore=0;  // from 에서  to 까지의 score (from의 score는 계산하지 않음)
		List <MazePos> way = new ArrayList<MazePos>();	
		
		OptimalWay addWay(OptimalWay other){
			OptimalWay ret = new OptimalWay();
			
			ret.netScore = this.netScore + other.netScore;
			for(int i=0;i<way.size();i++)
				ret.way.add(this.way.get(i));
			for(int j=0;j<other.way.size();j++)
				ret.way.add(other.way.get(j));
			
			return ret;
		}
	}
	
	
	
	void getInput(Scanner sc){
			n= sc.nextInt();
			m = sc.nextInt();
			sc.nextLine();
			
			maze = new char[n][];
			
			for(int i=0;i<n;i++){
			
				String temp = sc.nextLine();
				
				maze[i] = temp.toCharArray();
			}
	}
	
	void findEAndTresure(){   //e의 위치 (i,j)와 보물의 최대값을 구함
		for(int i=0;i<n;i++) for(int j=0;j<m;j++){
			char temC = maze[i][j];
			if( Character.isDigit(temC)){
				int k = Character.digit(temC, 10);
				trs.add(new MazePos(i,j,0,k));
			}
			else if(temC=='E'){
				E = new MazePos(i,j,0,0);
				trs.add(0, E);
			}
			
			cnT = trs.size();
			costOf = new int[cnT][cnT];
			onePiece = new OptimalWay[cnT+1][cnT][cnT];
		}
	}
	
	private int findOptWay(MazePos from, MazePos to){
		MazePos now= from;
		Queue<MazePos> save = new LinkedList<MazePos>();
		int minCost = n*m*2;
		char[][] cpyMaze = copyMaze(maze);
		
		save.add(now);
		
		while(! save.isEmpty()){
			now = save.poll();
			
			for(int i=0;i<4;i++){
				MazePos temp = new MazePos(now.row + d[i][0], now.col+ d[i][1] , now.cost+2,now.score); //갈떄 2
		
				if(0>temp.row || temp.row>=n || 0>temp.col || temp.col>=m){
					continue;
				}
				else if(cpyMaze[temp.row][temp.col]=='~' || cpyMaze[temp.row][temp.col]=='*'){
					continue;
				}
				else{
					if(temp.row == to.row && temp.col == to.col){
						minCost = minCost<temp.cost ? minCost: temp.cost;
					}
					else{
						save.add(temp);
						cpyMaze[temp.row][temp.col] = '*';
					}
				}
			}
		}
		return minCost;
	}
	
	void findAllOptWay(){
		for(int i=0;i<cnT;i++) for(int j=0;j<cnT;j++){
			if(i==j) costOf[i][j] = 0;
			else{
				costOf[i][j] = findOptWay( trs.get(i), trs.get(j));
			}
		}
	}
	
	private void findMaxWayByK(int from,int to,int k){
		MazePos fromTrs = trs.get(from);
		MazePos toTrs = trs.get(to);
		
		int maxScore = -200* n*m;
		OptimalWay maxWay1=null,maxWay2=null;
		
		if(k==1){
			onePiece[1][from][to].netScore = toTrs.score - costOf[from][to];
			onePiece[1][from][to].way.add(toTrs);
		}
		
	
		else{
			for(int t=1;t<k;t++){
				
				thisBreak:
				for(int i=1;i<cnT;i++){
					OptimalWay temp1,temp2;
					if(i==from || i==to) continue;
					
					temp1 = onePiece[t][from][i];
					temp2 = onePiece[k-t][i][to];
					
					if(temp1==null || temp2==null) continue;
					
					for(int j=0;j<temp1.way.size();j++){
						if(temp2.way.contains(temp1.way.get(j))) continue thisBreak;
					}
										
					
					
					int tempScore = temp1.netScore + temp2.netScore;
					if(maxScore <= tempScore){
						maxScore = tempScore;
						maxWay1 =temp1;
						maxWay2 = temp2;
					
						
					}
				}
			}
			if(maxWay1!=null) onePiece[k][from][to] = maxWay1.addWay(maxWay2);
			else onePiece[k][from][to] = null;
		}
	}
	
	void findAllMaxWay(){
		for(int from=0;from<cnT;from++)for(int to=0;to<cnT;to++)
			onePiece[1][from][to] = new OptimalWay();
		
		thisChapter:
		for(int k=1;k<cnT;k++)
			for(int to=0;to<cnT;to++)
				for(int from=0;from<cnT;from++){
					if(k==cnT-1 && to>=1) break thisChapter;
					if(from!= to) findMaxWayByK(from,to,k);
					
				}
	}
	
	
	
	int findMax(){
		int max = -2*n*m;
		
		for(int k=1;k<=cnT;k++){
			findMaxWayByK(0,0,k);
			
			OptimalWay temp = onePiece[k][0][0];
			
			for(int i=0;i<temp.way.size();i++) System.out.print(temp.way.get(i).row+ " "+temp.way.get(i).col+"/");
			System.out.println();
			
			if(temp.netScore > max) {
				max = temp.netScore;
			}
		}
		return max;
	}
	char[][] copyMaze(char[][] obj){
		char[][] ret = new char[obj.length][];
		
		for(int i=0; i < obj.length; i++){
			ret[i] = Arrays.copyOf(obj[i], m);
		}
		return ret;
	}
}

