package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CompanyPeople {
	public static void main(String[] args) throws IOException{
		Commute c =new Commute();
		c.inputAndSolve();
		c.printName();
		
	}
}

class Commute{
	int n;
	HashSet<String> inCompany = new HashSet<>();
	
	void inputAndSolve() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		String name;
		

		for(int i=0;i<n;i++){
			st = new StringTokenizer(br.readLine());
			
			name = st.nextToken();
			
			if(st.nextToken().equals("enter")){
				inCompany.add(name);
			}
			else{
				inCompany.remove(name);
			}
		}	
	}
	
	void printName(){
		ArrayList<String> temp = new ArrayList<>();
		temp.addAll(inCompany);
		Collections.sort(temp, Collections.reverseOrder());
		
		for(String a: temp){
			System.out.println(a);
		}
		
		
		
		
	}
	
	
	
}