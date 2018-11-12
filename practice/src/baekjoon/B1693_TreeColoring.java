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
	
	boolean[] chk;  // chk되어 있으면 부모노드
	int colors[]; // i번 노드의 색
	int[] cntColor = new int[5];// 색 1,2,3,4가 몇개씩 쓰는지 체크
	int[] colorOrder = new int[]{1,2,3,4}; // 색 1,2,3,4를 가장 많이 쓴 순서대로 유지
							
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
		
		coloring();  //1을 root로 해서 칠해간다. (leaf node부터)
		
		int cost= 0;
		
		for(int i=1; i<=4; i++){  // 가장 많이 있는 색에 가장 낮은 cost를 준다. 
			cost += cntColor[colorOrder[i-1]]  * i;   // 개수 * cost
		}

		return cost;
	}

	void coloring(){  //주위에 없는 색으로 (DFS탐색)
		// child가 없는 노드는 무조건 색1
		
		Stack<Integer> save = new Stack<>();

		save.add(1);
		chk[1] = true;
		
		int temp;
		while(!save.isEmpty()){
			temp = save.pop();

			// 더 이상 주변이 존재하지 않는 노드
			while(true){
				boolean isFinish =true; 
				// 말단 노드에 도달했으면 true
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
			
			boolean isExist[] = new boolean[5]; /*i번 색이 존재하는가?
													4색 정리에 의해 색 4개면 충분
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

	private void orderChange(int color) {   // 색의 개수가 차이가 나면 바꾼다.
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