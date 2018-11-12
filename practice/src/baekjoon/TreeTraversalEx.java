package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TreeTraversalEx {
	public static void main(String[] args){
		BinaryTree S = new BinaryTree();

		S.input();

		S.preOrder(S.head);
		System.out.println();
		S.inOrder(S.head);
		System.out.println();
		S.postOrder(S.head);

	}
}

class DLNode<T>{
	T data;
	DLNode<T> left=null;
	DLNode<T> right=null;

	DLNode(){};
	DLNode(T data, DLNode<T> l,DLNode<T> r){
		this.data = data;
		left = l;
		right = r;
	}
	DLNode(T data){
		this(data,null,null);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DLNode other = (DLNode) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	public T getData(){
		return data;
	}

	public void setData(T data){
		this.data =   data;
	}
}

class BinaryTree{
	DLNode<String> head=null;
	LinkedList<DLNode<String>> orphan = new LinkedList<DLNode<String>>();  // 부모노드가 등장하지 않은 노드
	int N;

	void input(){
		DLNode<String> temp;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		String[][] tempSave = new String[N][];
		String me, left, right;

		for(int i=0;i<N;i++){
			me = sc.next();
			left = sc.next();
			right = sc.next();

			tempSave[i] = new String[]{me,left,right};
		}
		sc.close();

		Queue<DLNode<String>> save = new LinkedList<DLNode<String>>();

		head = new DLNode<String>("A");
		save.add(head);

		while(!save.isEmpty()){
			temp = save.poll();
			if(temp == null) continue;
			
			for(int i=0;i<N;i++){
				if(tempSave[i][0].equals(temp.data)){
					if(!tempSave[i][1].equals(".")) temp.left = new DLNode<String>(tempSave[i][1]);
					if(!tempSave[i][2].equals(".")) temp.right = new DLNode<String>(tempSave[i][2]);
					
					save.add(temp.left);
					save.add(temp.right);
				}
			}
		}
	}

	void preOrder(DLNode<String> temp){
		if(temp == null) return;
		else{
			System.out.print(temp.data);
			preOrder(temp.left);
			preOrder(temp.right);
		}
	}

	void inOrder(DLNode<String> temp){
		if(temp == null) return;
		else{

			inOrder(temp.left);
			System.out.print(temp.data);
			inOrder(temp.right);
		}
	}

	void postOrder(DLNode<String> temp){
		if(temp == null) return;
		else{

			postOrder(temp.left);

			postOrder(temp.right);
			System.out.print(temp.data);
		}
	}

}