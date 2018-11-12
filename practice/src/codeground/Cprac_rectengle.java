package codeground;

import java.util.LinkedList;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_rectengle {
	static int Answer;

	static int K;
	static int N;
	static int M;

	public static void main(String args[]) throws Exception	{
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */		

		/*
		   Make new scanner from standard input System.in, and read data.
		 */
		Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			int lx,ly,rx,ry;

			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();

			LinkedList<Rectangle> sub = new LinkedList<>();
			// 가장 최상위의 rectangle들을 저장
			
			
			LinkedList<Rectangle> removeDirect = new LinkedList<>();
			//밑의 함수 참고
			
			for(int i=0; i<K;i++){
				lx = sc.nextInt();
				ly = sc.nextInt();
				rx = sc.nextInt();
				ry = sc.nextInt();

				Rectangle temp = new Rectangle(lx,ly,rx,ry);
				// init
				removeDirect.clear();
				boolean newOne = true;
				//init
				
				// temp를 기존의 사각형의 산하에 집어넣는다. 
				for(Rectangle first: sub){
					int subTest = first.rearrangeSub(temp);

					if(subTest == 0){} // 아무것도 안함
					else if(subTest >= 1){
						newOne = false;
					} 
					else{  // subTest = -1
						temp.rearrangeSub(first);
						removeDirect.add(first);
						newOne = false;
					}
				}	
				if(newOne == true){
					sub.add(temp);
				}
				else if(!removeDirect.isEmpty()){   
					sub.removeAll(removeDirect);
					sub.add(temp);
				}
			}
			
			int max = 1;
			for(Rectangle first: sub){
				if(max < first.cnt) max =first.cnt;
			}
			Answer = max;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}

class Rectangle{ 

	int lx;   // 왼쪽 밑
	int ly;  // 왼쪽 밑의 좌표
	int rx;   // 오른 쪽 위의 좌표
	int ry;    //오른쪽 위의 좌표

	LinkedList<Rectangle> directSub = new LinkedList<Rectangle>(); // 자신 안에 들어와 있는  direct Rectangle
	// direct Rectangle r: r은 this에게 속하고, r과 this사이에는 또다른 사각형이 존재하지 않음  -> r에 속한 사각형은 this에게도 속하지만 r이 관리한다. 
	int cnt = 1; // 내밑에 속한 sub의 개수(나 포함) 


	public Rectangle(int lx, int ly, int rx, int ry) {
		super();
		this.lx = lx;
		this.ly = ly;
		this.rx = rx;
		this.ry = ry;
	}

	int compareWith(Rectangle other){
		// 다른 도형과의 비교
		// 내가 속함: -1 , 아무관계 아님: 0  , other가 속함: 1

		if((lx < other.lx && ly < other.ly) && (rx > other.rx && ry > other.ry)){
			return 1;
		}
		else if((lx > other.lx && ly > other.ly) && (rx < other.rx && ry < other.ry)){
			return -1;
		}
		else return 0;																		
	}

	int rearrangeSub(Rectangle other){
		// 내가 속함 : -1   .  아무것도 아님 : 0   // 내밑에 속한 가장 긴라인의 수
		int test = compareWith(other);
		if(test < 1){
			return test;
		}
		else{  // test= 0 속함 작업 추진
			int subTest;
			LinkedList<Rectangle> removeDirect = new LinkedList<Rectangle>();  // direct에서 사라질 것들의 모임
			
			boolean newOne = true; // 모든 next에 속하지 않으면 새로 집어넣어야 한다.
			
			for(Rectangle next: directSub){
				subTest = next.rearrangeSub(other);

				if(subTest == 0){} // 아무것도 안함
				else if(subTest >= 1){
					newOne = false;
					if(subTest+1 > cnt) cnt = subTest+1;
				}  
				else{  // subTest = -1
					newOne = false;
					other.rearrangeSub(next);
					removeDirect.add(next);
					
					if(cnt < other.cnt+1) cnt = other.cnt +1;
				}	
			}

			if(newOne == true){
				directSub.add(other);
				if(cnt == 1) cnt++;
			}
			else if(!removeDirect.isEmpty()){ // other는 direct가 되었다.  
				directSub.removeAll(removeDirect);
				directSub.add(other);
			}

			return cnt;
		}
	}

	@Override
	public String toString() {
		return "R[lx=" + lx + ", ly=" + ly + ", rx=" + rx + ", ry=" + ry + ", cnt=" + cnt + "]";
	}
	
	
	
}