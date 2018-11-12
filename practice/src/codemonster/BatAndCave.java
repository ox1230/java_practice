package codemonster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BatAndCave {
	public static void main(String args[]){
		Cave com = new  Cave();
		File f = new File("input.txt");
		try {
			Scanner sc = new Scanner(f);
			FileWriter fw;
			try {
				fw = new FileWriter("result.txt");
				PrintWriter pw = new PrintWriter(fw);
			
				double C = sc.nextInt();
				for(double i=0;i<C;i++){
					com.input(sc);
					
					double min= com.findOptimalWay() ;
					System.out.println(C);
					min = Math.round(min*100.0) /100.0;
					pw.printf("%.2f\n",min);
				}
				pw.close();
				fw.close();
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}


class Cave{
	int n; // 동굴의 길이
	Posi[][] cave;  //동굴 (위지점,아래지점)배열의 배열
	int startIndex=0;
	
	void input(Scanner sc){
		n = sc.nextInt();
		
		cave = new Posi[n][];
		
		for(int i=0;i<n;i++){
			cave[i] = new Posi[2];
			double x,y1,y2;
					
			x = sc.nextInt();
			y1 = sc.nextInt();
			y2 = sc.nextInt();
			
			cave[i][0] = new Posi(x,y1);
			cave[i][1] = new Posi(x,y2);
		}
	}
	
	Cover setLine(Cover start){
		List<Cover> line = new ArrayList<Cover>();
		int i=0;
				
		line.add(start);
		allLoop:
		while(true){
			if(startIndex == n-1) break;
			i++;
			Cover end =  new Cover(cave[i][0].x, (double)cave[i][0].y, (double)cave[i][1].y);
			if(line.size()>=2){ // 세번째 차례 이후
				Cover test = line.get(i-1);
				boolean isIncrease= true;  //기울기가 양수인 직선?
				int isOk = canLine(line,end,isIncrease);
				int j = line.size()-1;
				
				while (isOk!=0){ // 선이 cover안을 통과하지 않는 동안
					j--;
					if(j==0) break allLoop;
					Cover temp = line.get(j);
					double tempY;
					if (isOk==1){
						tempY =  calculY(start.x,start.low,temp.x,temp.high,end.x);
						if(tempY >= end.low) end.high = tempY;
						else break allLoop;
					}
					else if (isOk==-1){
						tempY =   calculY(end.x,end.high,temp.x,temp.high,start.x);
						if(tempY <= start.high) start.low = tempY;
						else break allLoop;
					}
					
					isOk = canLine(line,end,isIncrease);
				}
				
				isIncrease = false;  //기울기가 음수인 직선
				
				isOk = canLine(line,end,isIncrease);
				j = line.size()-1;
				while (isOk!=0){ // 선이 cover안을 통과하지 않는 동안
					j--;
					if(j==0) break allLoop;
					Cover temp = line.get(j);
					double tempY;
					if (isOk==1){
						tempY =   calculY(end.x,end.high,temp.x,temp.high,start.x);
						if(tempY <= start.low) start.high = tempY;
						else break allLoop;
						
					}
					else if (isOk==-1){
						tempY =  calculY(start.x,start.low,temp.x,temp.high,end.x);
						if(tempY >= end.high) end.low = tempY;
						else break allLoop;
					}
					
					isOk = canLine(line,end,isIncrease);
				}
			}
			line.add(end);
			startIndex = i;
		}
		
		return line.get(line.size()-1);
}
	
	double calculY(double x1, double y1,double x2, double y2, double targetX){
		double tangent = (y2- y1)/ (x2 - x1);
		double target = tangent*(targetX - x2) +y2;
		return target;
	
	}
	
	int canLine(List<Cover> line,Cover end,boolean isIncrease){
		Cover preEnd = line.get(line.size()-1);
		Cover start = line.get(0);
		double target;
		
		if(isIncrease){
			target = calculY(start.x,start.low,end.x,end.high,preEnd.x);
		}
		else{
			target = calculY(start.x,start.high,end.x,end.low,preEnd.x);
		}
		if(target > preEnd.high) return 1;
		else if(target < preEnd.low) return -1;
		else return 0;
	}
	
	List<Cover> setAllLine(){
		Cover start = new Cover(cave[0][0].x, (double)cave[0][0].y, (double)cave[0][1].y);
		List<Cover> ends = new ArrayList<Cover>();
		ends.add(start);
		
		while(startIndex < n-1){
			start = setLine(start);
			ends.add(start);
		}
		
		return ends;
	}
	
	double findOptimalWay(){
		List<Cover> ends = setAllLine();
		Posi[][] forMinDis = new Posi[ends.size()][];
		
		//각 end(의 지점)에서 다음 end(temp)의 각 지점으로의 최소 거리를 구함
		
		forMinDis[0] = new Posi[6];
		int i;
		for(i=0; i<ends.size() ;i++){
			Cover temp,next;

			temp = ends.get(i);
			if(i!=ends.size()-1){
				next = ends.get(i+1);
						
				
				forMinDis[i][0] = new Posi(temp.x,temp.high);
				forMinDis[i][1] = new Posi(temp.x,temp.low);
				
				forMinDis[i+1] = new Posi[6];
				
				if(temp.low < next.high && next.high < temp.high){
					forMinDis[i][2] = new Posi(temp.x,next.high);
					forMinDis[i+1][4] = new Posi(next.x, temp.low);
				}
				if(temp.low < next.low && next.low < temp.high){
					forMinDis[i][2] = new Posi(temp.x,next.low);
					forMinDis[i+1][5] = new Posi(next.x, temp.high);
				}
				if(temp.high < next.high && temp.low < next.low){
					forMinDis[i+1][4] = new Posi(next.x, temp.low);
					forMinDis[i+1][5] = new Posi(next.x, temp.high);
				}
			}
			else{ // i == ends.size()-1일때(마지막에 도착했을때)
				forMinDis[i][0] = new Posi(temp.x,temp.high);
				forMinDis[i][1] = new Posi(temp.x,temp.low);
			}
			
			
			if(i!=0)
				for(int j=0;j<6;j++){  // i=0일떄는 아래의 과정을 거치지 않음
					Posi tempPosi = forMinDis[i][j];
					if(tempPosi==null) continue;
					
					Posi tempPrePosi = forMinDis[i-1][0];
					double tempMinDis = tempPrePosi.disFromZero + tempPosi.lengthTo(tempPrePosi);
					for(int k=1;k<6;k++){
						tempPrePosi = forMinDis[i-1][k];
						if(tempPrePosi==null) continue;
					
						double tempDis = tempPrePosi.disFromZero + tempPosi.lengthTo(tempPrePosi);
						if(tempMinDis > tempDis) tempMinDis = tempDis;
					}
					forMinDis[i][j].disFromZero = tempMinDis;
				}
		}
		
		double minDis = forMinDis[ends.size()-1][0].disFromZero;
		for(int k=0;k<6;k++){
			if(forMinDis[ends.size()-1][k] !=null && minDis >  forMinDis[ends.size()-1][k].disFromZero ) minDis = forMinDis[ends.size()-1][k].disFromZero;
		}
		
		return minDis;
	}
	
}

class Cover{
	double x;
	double low;
	double high;
	
	Cover(){};
	Cover(double x,double l,double h){
		this.x = x;
		this.low = l;
		this.high = h;
	}
}

class Posi{
	double x;
	double y;
	double disFromZero=0;
	
	Posi(){};
	Posi(double x,double y){
		this.x = x;
		this.y = y;
	}
	
	double lengthTo(Posi other){
		double l =0.0;
		
		double dx = Math.pow(this.x- other.x, 2.0);
		double dy = Math.pow(this.y - other.y, 2.0);
		
		l = Math.sqrt(dx+dy);		
		return l;
	}
}