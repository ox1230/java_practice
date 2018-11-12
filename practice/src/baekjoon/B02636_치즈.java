package baekjoon;

import java.io.IOException;

public class B02636_치즈 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Cheese C = new Cheese();
		C.input();
		int[] temp = C.solve();
		System.out.println(temp[0]);
		System.out.println(temp[1]);
	}

}


class Cheese{
	int N;
	int M;
	int plate[][];
	
	void input() throws IOException{
		Reader r = new Reader();
		N = r.nextInt();
		M = r.nextInt();
		
		plate = new int[N][M];
		
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				plate[i][j] = r.nextInt();
			}
		}
		
	}
	
	int[] solve(){
		int cnt = 1;
		int num = 0;
		int prenum = num;
	
		do{
			prenum = num;
			num = dfs(0,0,cnt,0);
			cnt++;
		}while(num > 0);
		cnt-= 2;
		return new int[]{cnt,prenum};
	}
	
	int dfs(int i,int j, int cnt, int ret){
		// 치즈를 처리하고, 처리한 치즈의 수를 return
		
		if(plate[i][j]== 1){// 치즈임
			plate[i][j] = cnt * -1;
			return ret+1;
		}
		else { // 공기임
			plate[i][j] = cnt * -1;
			
			//상하좌우 체크
			if(i+1 <N && plate[i+1][j]> cnt*-1) ret = dfs(i+1,j,cnt,ret);
			if(i-1 >= 0 && plate[i-1][j]> cnt*-1) ret = dfs(i-1,j,cnt,ret);
			if(j+1 <M && plate[i][j+1]> cnt*-1) ret = dfs(i,j+1,cnt,ret);
			if(j-1 >= 0 && plate[i][j-1]> cnt*-1) ret = dfs(i,j-1,cnt,ret);
			return ret;
		}
	}
	
}