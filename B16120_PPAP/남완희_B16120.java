import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class bj_16120_PPAP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String [] arr = sc.next().split("");
		LinkedList<String> list = new LinkedList<>(Arrays.asList(arr)); 
		Deque<String> deq = new ArrayDeque<>();
		
		while(list.size()!=0) {
			String str = list.pollFirst();
			if(str.equals("P")) {
				deq.add(str);
			}
			else if(str.equals("A")) {
				if(list.isEmpty()) {
					System.out.println("NP");
					return;
				}
				if(deq.size()>=2 && list.peek().equals("P")) {
					deq.poll();
					list.pollFirst();
				}
				else {
					System.out.println("NP");
					return;
				}
			}
		}
		if(deq.size()==1 && deq.getFirst().equals("P")) {
			System.out.println("PPAP");
		}
		else {
			System.out.println("NP");
		}

	}

}
