package codemonster;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Areas {
	
	static Case[] cases;
	static int C;
	
	public static void main(){
		File f = new File("input.txt");
		Case temp;
		
		inputData(f);
		for(int i=0;i<C;i++){
			temp = cases[i];
			temp.solve();
		}
		
	}
	
	static void inputData(File f){	
		try {
			Scanner sc = new Scanner(f);
			
			C = sc.nextInt();
			cases = new Case[C];
			
			int n;
			long x1,y1 , x2, y2;
			
			for(int i=0;i<C;i++){
				n = sc.nextInt();
				Case temp = new Case();
				
				for(int j=0;j<n;j++){
					x1 = sc.nextLong();
					y1 = sc.nextLong();
					x2 = sc.nextLong();
					y2 = sc.nextLong();
					
					temp.addToBoxes(new Box(x1,y1,x2,y2));	
				}
				cases[i] = temp;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}

class Case{
	List <Box> boxes;   //boxes안에 start의 x좌표 크기의 오름차순으로 저장됨
	List <Box> sub;    //subBox들을 담아둠
	
	Case(){
		boxes = new LinkedList<Box>();
		sub = new LinkedList<Box>();
	}
	
	void addToBoxes(Box a){
		if(boxes==null){
			boxes.add(a);
		}
		else{
			for(int i=0; i<boxes.size() ;i++ )
				if(a.start.x > boxes.get(i).start.x) {
					boxes.add(i,a);
					return;
				}

		}
	}
	
	void addToSub(Box a){
		if(sub==null){
			sub.add(a);
		}
		else{
			for(int i=0; i<sub.size() ;i++ )
				if(a.start.x > sub.get(i).start.x) {
					sub.add(i,a);
					return;
				}

		}
	}
	
	void solve(int i){
		Box target = boxes.get(i);
		long x1= target.start.x, x2 = target.end.x;
		long y1 = target.start.y, y2 = target.end.y;
		
		if(sub==null){
			sub.add(target);
		}
		else{
			for(int j= sub.size(); j>=0; j--){
				Box temp = sub.get(j);
				
		
			
			}
			
		}	
	}
	
}


class Box{
	Pos start; //왼쪽 아래
	Pos end;		//오른쪽 위
	long area;
	
	Box(long x1,long y1, long x2,long y2){
		start = new Pos(x1,y1);
		end = new Pos(x2,y2);
		area = (x2-x1)* (y2-y1);
	}
	
	Box(Pos s,Pos e){
		start = s;
		end = e;
	}
	
	
}

class Pos{
	long x;
	long y;
	
	Pos(long a,long b){
		x = a;
		y = b;
	}
}