package baekjoon;

import java.io.IOException;

public class B1725_Histogram {
	public static void main(String[] args) throws IOException{
		Histogram h = new Histogram();
		h.input();
		System.out.println(h.startSolve());
	}
}

class Histogram{
	int N;
	int S[];
	int PF[][];  // PF[i] : i의 높이 기준으로 i에서 앞/뒤로 '
	//          몇개의 상자로 사각형을 만들 수 있는지 저장

	void input() throws IOException{
		Reader r = new Reader();

		N = r.nextInt();
		S = new int[N];

		for(int i=0;i<N;i++){
			S[i] = r.nextInt();
		}
	}

	int startSolve(){

		PF = new int[N][2];
		int max;  // 최대 크기

		//a가 0일때

		PF[0][0] = 0;

		for(int i=1;i<N;i++){
			if(S[i] >= S[0]){
				PF[0][1]++;
			}
			else break;   //0번째가 더 크면 종료
		}

		max = S[0] * (1 + PF[0][1]);

		//a 1이상

		for(int a=1;a<N;a++){

			//  pre를 채움
			int p = a-1;

			AllProcess:
			{
				while(p>0){
					if(S[p] == S[a]){  // 같은 높이 만나면  
						PF[a][0] = PF[p][0] + (a-p);
						PF[a][1] = PF[p][1] - (a-p);
						break AllProcess; // F 과정도 break
					}
					else if(S[p] < S[a]){
						break;  //p채우는 것만 break
					}
					else{  // S[p] > S[a]
						
						PF[a][0] += (PF[p][0]+1);
						p = p - PF[p][0];
					}
				}

				// forward를 채움
				for(int f = a+1; f<N; f++){
					if(S[f] <S[a]){
						break;
					}
					else{
						PF[a][1]++;
					}
				}	
			}
			
			int cnt = 1 + PF[a][0] + PF[a][1]; // 포함 개수
			
			int area = cnt * S[a];// 전체 넓이
			
			
			if(max < area){
				max = area;
			}
		}
		return max;
	}



}