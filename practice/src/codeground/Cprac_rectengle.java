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
			// ���� �ֻ����� rectangle���� ����
			
			
			LinkedList<Rectangle> removeDirect = new LinkedList<>();
			//���� �Լ� ����
			
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
				
				// temp�� ������ �簢���� ���Ͽ� ����ִ´�. 
				for(Rectangle first: sub){
					int subTest = first.rearrangeSub(temp);

					if(subTest == 0){} // �ƹ��͵� ����
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

	int lx;   // ���� ��
	int ly;  // ���� ���� ��ǥ
	int rx;   // ���� �� ���� ��ǥ
	int ry;    //������ ���� ��ǥ

	LinkedList<Rectangle> directSub = new LinkedList<Rectangle>(); // �ڽ� �ȿ� ���� �ִ�  direct Rectangle
	// direct Rectangle r: r�� this���� ���ϰ�, r�� this���̿��� �Ǵٸ� �簢���� �������� ����  -> r�� ���� �簢���� this���Ե� �������� r�� �����Ѵ�. 
	int cnt = 1; // ���ؿ� ���� sub�� ����(�� ����) 


	public Rectangle(int lx, int ly, int rx, int ry) {
		super();
		this.lx = lx;
		this.ly = ly;
		this.rx = rx;
		this.ry = ry;
	}

	int compareWith(Rectangle other){
		// �ٸ� �������� ��
		// ���� ����: -1 , �ƹ����� �ƴ�: 0  , other�� ����: 1

		if((lx < other.lx && ly < other.ly) && (rx > other.rx && ry > other.ry)){
			return 1;
		}
		else if((lx > other.lx && ly > other.ly) && (rx < other.rx && ry < other.ry)){
			return -1;
		}
		else return 0;																		
	}

	int rearrangeSub(Rectangle other){
		// ���� ���� : -1   .  �ƹ��͵� �ƴ� : 0   // ���ؿ� ���� ���� ������� ��
		int test = compareWith(other);
		if(test < 1){
			return test;
		}
		else{  // test= 0 ���� �۾� ����
			int subTest;
			LinkedList<Rectangle> removeDirect = new LinkedList<Rectangle>();  // direct���� ����� �͵��� ����
			
			boolean newOne = true; // ��� next�� ������ ������ ���� ����־�� �Ѵ�.
			
			for(Rectangle next: directSub){
				subTest = next.rearrangeSub(other);

				if(subTest == 0){} // �ƹ��͵� ����
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
			else if(!removeDirect.isEmpty()){ // other�� direct�� �Ǿ���.  
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