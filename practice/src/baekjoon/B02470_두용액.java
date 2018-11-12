package baekjoon;

import java.io.IOException;
import java.util.Arrays;

public class B02470_두용액 {
	public static void main(String[] args) throws IOException {
		TwoWater tw = new TwoWater();
		tw.input();
		tw.solve();
		System.out.println(tw.ans[0] + " " + tw.ans[1]);
		
	}
}

class TwoWater{
	int ans[] = new int[2]; // 정답을 담아두는 배열
	int minAbs = Integer.MAX_VALUE;    // 현재까지 0에 가장 가까운 값 
	
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
		
	//  a b c 0 d e f 일때 가능한 경우는 {(abc중 한개),  (def중 한개)}  (b,c) , (d,e) 셋중 하나.
		
		int zeroPoint; // 0이 들어갈 위치
		zeroPoint = -1*Arrays.binarySearch(S, 0) -1;
		
		// (b,c)
		if(zeroPoint-2 >=0){   // a,b가 존재하면
			chkVaildAns(S[zeroPoint-2], S[zeroPoint-1]);
		}
		// (d,e)
		if(zeroPoint+1 < N){  // d,e
			chkVaildAns(S[zeroPoint], S[zeroPoint+1]);
		}
		
		//{(abc중 한개),  (def중 한개)}
		for (int i = 0; i < zeroPoint; i++) {    // (-)인것들만 
			for (int j = zeroPoint; j < N; j++) { // (+)인 것들만
				chkVaildAns(S[i],S[j]);
			}
		}
		
	}

	private void chkVaildAns(int i, int j) {
		// TODO Auto-generated method stub
		// i,j가 정답인지 확인한다.    ( 두 용액의 합이 0에 가장 가까우면 저장한다)
		//  (i < j)여야한다.
		int tempAbs =  Math.abs(i+j);
		
		if( tempAbs < minAbs ){
			minAbs = tempAbs;
			ans[0] = i;
			ans[1] = j;
		}
	}
	
	
	
	
	
}
