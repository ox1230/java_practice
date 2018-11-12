package codeground;


import java.util.HashSet;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_����ǰſ� {
	static int Answer;

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
		
		int N;
		int[][] S;
		HashSet<Room> touched = new HashSet<>();  // ���� �ݻ��� �ſ��� ����

		
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
		
			
			
			N = sc.nextInt();
			sc.nextLine();
			String temp;
			
			//init & input
			touched.clear();
			S = new int[N][];
			for(int i=0;i<N;i++){
				S[i] = new int[N];
				temp = sc.nextLine();
				for(int j=0; j<N; j++){
					S[i][j] = temp.charAt(j) - '0';
				}
			}
			
			
			//if(test_case == 2) {
				//System.out.println("1");
			//}
			
			int col = 0;
			int row = 0;
			int d = 1;  // ���� ���� 1:���� 2:�ϱ� 3:���� 4:����
			int rType;
			while(col>=0 && row>=0 && col<N && row< N){
				// ���� �ſ��� ���������� �ʴ� ����
				rType = S[row][col];
				if(rType > 0){ // �ſ��� ����
					touched.add(new Room(row,col));
				}
				int[] tt = nextRoom(row,col,d, rType);
				
				row = tt[0];
				col = tt[1];
				d = tt[2];
			}
			
			Answer = touched.size();
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	static int[] nextRoom(int r, int c, int d, int rType){
		 // r: ��, c: ��, d: ���� ����, rType: �ſ��� ���� 
		int[] ret = new int[3];
		ret[0] = r;  // ���� ���� ��
		ret[1] = c; // ���� ���� ��
		ret[2] = d; // ���� ���� ���� ��ġ 
		
		if(rType == 0){  // ���� ���� ����
			switch (d){
			case 1: ret[2] = 1; break;
			case 2: ret[2] = 2; break;
			case 3: ret[2] = 3; break;
			case 4: ret[2] = 4; break;
			}
		}
		else if(rType == 1){
			switch (d){
			case 1: ret[2] = 4; break;
			case 2: ret[2] = 3; break;
			case 3: ret[2] = 2; break;
			case 4: ret[2] = 1; break;
			}
		}
		else{ // rType == 2
			switch (d){
			case 1: ret[2] = 2; break;
			case 2: ret[2] = 1; break;
			case 3: ret[2] = 4; break;
			case 4: ret[2] = 3; break;
			}
		}
		
		switch(ret[2]){  // ���� �� ����
		case 1: ret[1]++; break;
		case 2: ret[0]++; break;
		case 3: ret[1]--; break;
		case 4: ret[0]--; break;
		}
		
		return ret;
	}
	
}
class Room{
	int row;
	int col;
	
	public Room(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	
	
}