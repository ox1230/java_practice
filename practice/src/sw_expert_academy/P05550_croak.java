package sw_expert_academy;

import java.util.ArrayList;
import java.util.Scanner;

public class P05550_croak {
	public static void main(String[] args) {
		/*
		   �Ʒ��� �޼ҵ� ȣ���� ������ ǥ�� �Է�(Ű����) ��� input.txt ���Ϸκ��� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
		   �������� �ۼ��� �ڵ带 �׽�Ʈ �� ��, ���Ǹ� ���ؼ� input.txt�� �Է��� ������ ��,
		   �� �ڵ带 ���α׷��� ó�� �κп� �߰��ϸ� ���� �Է��� ������ �� ǥ�� �Է� ��� ���Ϸκ��� �Է��� �޾ƿ� �� �ֽ��ϴ�.
		   ���� �׽�Ʈ�� ������ ������ �Ʒ� �ּ��� ����� �� �޼ҵ带 ����ϼŵ� �����ϴ�.
		   ��, ä���� ���� �ڵ带 �����Ͻ� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ� ó�� �ϼž� �մϴ�.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   ǥ���Է� System.in ���κ��� ��ĳ�ʸ� ����� �����͸� �о�ɴϴ�.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   ���� ���� �׽�Ʈ ���̽��� �־����Ƿ�, ������ ó���մϴ�.
		 */
		sc.nextLine(); //
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//Frog�� ����ִ� arrayList�� �����. input�� �ް�, �� �۾����� ������ Frog�� Ȯ���Ѵ�. ������ -1, ���

			// ���ڸ� �޴´�.
			String target = sc.nextLine();

			ArrayList<Frog> pond = new ArrayList<>();  // �������� �����Ѵ�.

			//ù��° ���� ó��
			char c = target.charAt(0);
			int cnt = -1;
			if (c != 'c') {
				//�ƹ��͵� ����
			}
			else {
				// ������
				pond.add(new Frog());
				cnt = 1;
				bigLoop:
					for(int i =1; i < target.length(); i++) {
						//�����Ѱ� �ִ��� Ȯ��
						c = target.charAt(i);
						int flag = 0;  // ������ ���� ������ 1����
						for(Frog temp: pond) {
							if(temp.isOK(c)) {
								temp.goNextStep();
								flag++;
								break;
							}
							else {
								//�׳� �Ѿ
							}
						}

						// ���� ������ �Ұ����ϹǷ� ����
						if(flag == 0 && c != 'c') {
							cnt = -1;
							break bigLoop;
						}
						else if(flag == 0 && c == 'c') {
							pond.add(new Frog());
							cnt++;
						}
						else {
							// ������ ���� ����, �������� �Ѿ
						}
					}
			}

			// ��� �������� croak���� ����� ���´��� Ȯ���Ѵ�.
			for (Frog frog : pond) {
				if(!frog.isOK('c')){  // k�� ������ ������
					cnt = -1;
				}
			}
			
			System.out.println("#"+ test_case+ " "+cnt);

		}
	}
}

class Frog{
	char stat = 'c';

	boolean isOK(char c) {
		//c�� ���� ��ġ���� Ȯ���Ѵ�. (��, ���� stat�� c�ε�, r�� �������� true, �ƴϸ� False
		boolean ret = false;
		switch (stat) {
		case 'c': 
			ret = c == 'r'? true: false;
			break;
		case 'r': 
			ret = c == 'o'? true: false;
			break;
		case 'o': 
			ret = c == 'a'? true: false;
			break;
		case 'a': 
			ret = c == 'k'? true: false;
			break;
		case 'k': 
			ret = c == 'c'? true: false;
			break;
		default:
			break;
		}

		return ret;
	}

	void goNextStep() {
		// stat�� ���� �ܰ�� �̵���Ų��. 
		switch (stat) {
		case 'c': 
			stat = 'r';
			break;
		case 'r': 
			stat = 'o';
			break;
		case 'o': 
			stat = 'a';
			break;
		case 'a': 
			stat = 'k';
			break;
		case 'k': 
			stat = 'c';
			break;
		default:
			break;
		}
	}
}

