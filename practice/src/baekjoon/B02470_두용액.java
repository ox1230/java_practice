package baekjoon;

import java.io.IOException;
import java.util.Arrays;

public class B02470_�ο�� {
	public static void main(String[] args) throws IOException {
		TwoWater tw = new TwoWater();
		tw.input();
		tw.solve();
		System.out.println(tw.ans[0] + " " + tw.ans[1]);
		
	}
}

class TwoWater{
	int ans[] = new int[2]; // ������ ��Ƶδ� �迭
	int minAbs = Integer.MAX_VALUE;    // ������� 0�� ���� ����� �� 
	
	int N;
	int S[];
	
	
	
	public void input() throws IOException {
		// TODO Auto-generated method stub
		Reader rd = new Reader();
		
		N = rd.nextInt();
		
		S = new int[N];
		
		for (int i = 0; i < S.length; i++) {
			S[i] = rd.nextInt();
		}
	}

	public void solve() {
		// TODO Auto-generated method stub
		
		Arrays.sort(S);
		
	//  a b c 0 d e f �϶� ������ ���� {(abc�� �Ѱ�),  (def�� �Ѱ�)}  (b,c) , (d,e) ���� �ϳ�.
		
		int zeroPoint; // 0�� �� ��ġ
		zeroPoint = -1*Arrays.binarySearch(S, 0) -1;
		
		// (b,c)
		if(zeroPoint-2 >=0){   // a,b�� �����ϸ�
			chkVaildAns(S[zeroPoint-2], S[zeroPoint-1]);
		}
		// (d,e)
		if(zeroPoint+1 < N){  // d,e
			chkVaildAns(S[zeroPoint], S[zeroPoint+1]);
		}
		
		//{(abc�� �Ѱ�),  (def�� �Ѱ�)}
		for (int i = 0; i < zeroPoint; i++) {    // (-)�ΰ͵鸸 
			for (int j = zeroPoint; j < N; j++) { // (+)�� �͵鸸
				chkVaildAns(S[i],S[j]);
			}
		}
		
	}

	private void chkVaildAns(int i, int j) {
		// TODO Auto-generated method stub
		// i,j�� �������� Ȯ���Ѵ�.    ( �� ����� ���� 0�� ���� ������ �����Ѵ�)
		//  (i < j)�����Ѵ�.
		int tempAbs =  Math.abs(i+j);
		
		if( tempAbs < minAbs ){
			minAbs = tempAbs;
			ans[0] = i;
			ans[1] = j;
		}
	}
	
	
	
	
	
}
