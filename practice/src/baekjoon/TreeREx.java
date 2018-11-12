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
//��Ʈ���� ���� �ָ� �ִ� ���(firstNode)�� ã��, �� ��忡�� ���� �ָ��ִ� ��带 ã�´�.
// firstNode������ �Ÿ��� ã�� ���� ������ ���� ���� Node������ ���̸� �̿��Ѵ�.
class TreeR{
	int n;
	
	int[] parents; // parents[i] :i�� �θ���
	int[]  disToRoot;    // ��Ʈ �������� �Ÿ�  
	int firstMax = 0;
	int firstNode;   // ��Ʈ������ �Ÿ��� ���� �� ���
	
	HashSet<Integer> ancestors = new HashSet<>();   // firstNode�� ���� ����
	int[] dis;    // firstNode���� �Ÿ�
	
	// ��Ʈ������ �Ÿ��� �ٷ� �����Ѵ�.
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
		
		//firstNode�� ���� ���� ������ �Ÿ�
		int temp = firstNode; 

		while(temp != 0){   //  1�� ����ϱ� ���ؼ� (1�� parent�� 0���� �Ǿ��ִ�.
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
		// t�� firstNode�� �Ÿ��� ���Ѵ�.
		int temp = t;
	
		while(ancestors.contains(temp) == false){
			temp = parents[temp];
		}
		
		dis[t] = dis[temp]  + (disToRoot[t] - disToRoot[temp]); 
	}
	
	
}