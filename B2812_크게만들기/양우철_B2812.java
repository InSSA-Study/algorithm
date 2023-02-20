package wc_bj_2812_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String[] sArr = s.split(" ");
		int N = Integer.parseInt(sArr[0]);
		int M = Integer.parseInt(sArr[1]);
		s = br.readLine();
		StringBuilder sb = new StringBuilder();
		Stack<Character> st = new Stack<Character>();
		//String result;
		
		for (int i = 0; i < N; i++) {
			char temp = s.charAt(i);
			while (!st.isEmpty()) {
				if (M == 0 || st.peek() >= temp) {
					st.add(temp);
					break;
				}
				st.pop();
				M--;
			}
			if(st.isEmpty()) st.add(temp);
			if(M == 0) {
				sb.append(s.substring(i+1));
				break;
			}
		}
		while (!st.isEmpty()) {
			if(M > 0) {
				st.pop();
				M--;
				continue;
			}
			sb.insert(0, st.pop());
		}
		//sb.append(result);
		System.out.println(sb);
		br.close();
	}

}
