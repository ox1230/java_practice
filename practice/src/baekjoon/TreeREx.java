package baekjoon;

import java.io.IOException;
import java.util.HashSet;

public class TreeREx {
	public static void main(String[] args) throws IOException{
		TreeR t = new TreeR();
		t.inputAndFindDis();
		System.out.println(t.startSolve());
		System.out.println();
	}
}
//루트에서 가장 멀리 있는 노드(firstNode)를 찾고, 그 노드에서 가장 멀리있는 노드를 찾는다.
// firstNode에서의 거리를 찾을 때는 최초의 공통 조상 Node까지의 길이를 이용한다.
class TreeR{
	int n;
	
	int[] parents; // parents[i] :i의 부모노드
	int[]  disToRoot;    // 루트 노드까지의 거리  
	int firstMax = 0;
	int firstNode;   // 루트까지의 거리가 가장 긴 노드
	
	HashSet<Integer> ancestors = new HashSet<>();   // firstNode의 조상 노드들
	int[] dis;    // firstNode와의 거리
	
	// 루트에서의 거리도 바로 측정한다.
	void inputAndFindDis() throws IOException{
		Reader reader = new Reader();

		n = reader.nextInt();

		int k;
		int v;
		
		parents = new int[n+1];
		disToRoot = new int[n+1];

		
		
		for(int i=1;i<n;i++){
			int index = reader.nextInt();
			k = reader.nextInt();
			v = reader.nextInt();
			
			parents[k] = index;
			
			disToRoot[k] = disToRoot[index] + v;
			
			if(disToRoot[k] > firstMax){
				firstMax = disToRoot[k];
				firstNode = k;
			}
		
		}
	}

	int startSolve(){
		dis = new int[n+1];
		
		//firstNode의 조상 노드들 까지의 거리
		int temp = firstNode; 

		while(temp != 0){   //  1도 계산하기 위해서 (1의 parent는 0으로 되어있다.
 			ancestors.add(temp);
			dis[temp] = disToRoot[firstNode] - disToRoot[temp];
			
			temp = parents[temp];
		}
		
		int max = 0;
		for(int i=1; i<=n;i++){
			findDis(i);
			if(dis[i] > max) max = dis[i];
		}
		
		return max;
	}
	
	void findDis(int t){
		// t와 firstNode의 거리를 구한다.
		int temp = t;
	
		while(ancestors.contains(temp) == false){
			temp = parents[temp];
		}
		
		dis[t] = dis[temp]  + (disToRoot[t] - disToRoot[temp]); 
	}
	
	
}