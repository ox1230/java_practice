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
	int PF[][];  // PF[i] : i�� ���� �������� i���� ��/�ڷ� '
	//          ��� ���ڷ� �簢���� ���� �� �ִ��� ����

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
		int max;  // �ִ� ũ��

		//a�� 0�϶�

		PF[0][0] = 0;

		for(int i=1;i<N;i++){
			if(S[i] >= S[0]){
				PF[0][1]++;
			}
			else break;   //0��°�� �� ũ�� ����
		}

		max = S[0] * (1 + PF[0][1]);

		//a 1�̻�

		for(int a=1;a<N;a++){

			//  pre�� ä��
			int p = a-1;

			AllProcess:
			{
				while(p>0){
					if(S[p] == S[a]){  // ���� ���� ������  
						PF[a][0] = PF[p][0] + (a-p);
						PF[a][1] = PF[p][1] - (a-p);
						break AllProcess; // F ������ break
					}
					else if(S[p] < S[a]){
						break;  //pä��� �͸� break
					}
					else{  // S[p] > S[a]
						
						PF[a][0] += (PF[p][0]+1);
						p = p - PF[p][0];
					}
				}

				// forward�� ä��
				for(int f = a+1; f<N; f++){
					if(S[f] <S[a]){
						break;
					}
					else{
						PF[a][1]++;
					}
				}	
			}
			
			int cnt = 1 + PF[a][0] + PF[a][1]; // ���� ����
			
			int area = cnt * S[a];// ��ü ����
			
			
			if(max < area){
				max = area;
			}
		}
		return max;
	}



}