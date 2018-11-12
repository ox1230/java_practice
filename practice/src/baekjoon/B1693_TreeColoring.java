package baekjoon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class B1693_TreeColoring {
	public static void main(String[] args) throws IOException{
		TreeColoring tc = new TreeColoring();
		tc.input();
		System.out.println(tc.startSolve());
	}
}



class TreeColoring{
	int n;

	ArrayList<Integer>[] tree;
	
	boolean[] chk;  // chk�Ǿ� ������ �θ���
	int colors[]; // i�� ����� ��
	int[] cntColor = new int[5];// �� 1,2,3,4�� ��� ������ üũ
	int[] colorOrder = new int[]{1,2,3,4}; // �� 1,2,3,4�� ���� ���� �� ������� ����
							
	@SuppressWarnings("unchecked")
	void input() throws IOException{
		Reader r = new Reader();

		n = r.nextInt();

		tree= new ArrayList[n+1];

		for(int i=1;i<=n;i++){
			tree[i] = new ArrayList<Integer>();
		}

		int a,b;
		for(int i=0;i< n-1 ;i++){
			a = r.nextInt();
			b = r.nextInt();

			tree[a].add(b);
			tree[b].add(a);
		}
	}


	int startSolve(){
		chk = new boolean[n+1];  
		colors = new int[n+1];
		
		coloring();  //1�� root�� �ؼ� ĥ�ذ���. (leaf node����)
		
		int cost= 0;
		
		for(int i=1; i<=4; i++){  // ���� ���� �ִ� ���� ���� ���� cost�� �ش�. 
			cost += cntColor[colorOrder[i-1]]  * i;   // ���� * cost
		}

		return cost;
	}

	void coloring(){  //������ ���� ������ (DFSŽ��)
		// child�� ���� ���� ������ ��1
		
		Stack<Integer> save = new Stack<>();

		save.add(1);
		chk[1] = true;
		
		int temp;
		while(!save.isEmpty()){
			temp = save.pop();

			// �� �̻� �ֺ��� �������� �ʴ� ���
			while(true){
				boolean isFinish =true; 
				// ���� ��忡 ���������� true
				for(int near:tree[temp]){
					if(chk[near] == false){
						save.push(temp);
						temp = near;
						chk[temp] = true;
						isFinish = false;
						break;
					}
				}
				
				if(isFinish == true) break;
			}
			
			boolean isExist[] = new boolean[5]; /*i�� ���� �����ϴ°�?
													4�� ������ ���� �� 4���� ���
												*/
			for(int near: tree[temp]){
				isExist[colors[near]] = true;
			}
			
			for(int i:colorOrder){
				if(isExist[i] == false){
					colors[temp] = i;
					cntColor[i]++;
					orderChange(i);
					break;
				}
			}
		}
	}

	private void orderChange(int color) {   // ���� ������ ���̰� ���� �ٲ۴�.
		// TODO Auto-generated method stub
		if(colorOrder[0] == color) return;
		
		int i =1;
		for(;i<4;i++){
			if(colorOrder[i] == color) break;
		}
		
		if(cntColor[colorOrder[i-1]] < cntColor[color]){
			int temp = colorOrder[i-1];
			colorOrder[i-1] = color;
			colorOrder[i] = temp;
		}
	}
}