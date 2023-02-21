package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj_1062 {
	static ArrayList<String>  arr =new ArrayList<>(); // a,n,t,c,i 담은 리스트
	static HashSet<String> arr2 = new HashSet<>(); 	  // 배워야 하는 알파벳을 중복없이 나타내기 위한 set
	static int n;
	static int k;
	static String [] a;
	static int answer = 0;
	static ArrayList<String>  arr5 =new ArrayList<>(); // anta , tica 를 제외한 가운데 문자열
	static ArrayList<String> arr3; // 배워야 하는 알파벳을 중복없이 나타내기 위한 list
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr.add("a");
		arr.add("t");
		arr.add("n");
		arr.add("i");
		arr.add("c");
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int ans = 0;
		
		
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			String str2 = "";
			if(str.length()==8) ans++;
			else {
				str = str.substring(4,str.length()-4); // anta, tica 제거한 문자열 
				for(int j =0;j<str.length();j++) {
					if(arr.contains(String.valueOf(str.charAt(j)))) continue;	// 문자열의 각 문자가 a,t,i,c,n 인 경우 continue
					arr2.add(String.valueOf(str.charAt(j))); // 그외의 알파벳인 경우 Set 에 추가하여 중복 제거
					str2 += String.valueOf(str.charAt(j));	 // 빈 문자열에 anta tica를 제외한 가운데 문자열만 담고 arr5 리스트에 저장
				}
				if(str2.length()==0) ans++;	// 가운데 문자열이 a n t i c 로 이루어진 경우 정답 카운트 + 1
				else arr5.add(str2);	// 가운데 부분 문자열 추가
			}
		}
		
		if(k < 5) {				// 배울 수 있느 알파벳이 5개 미만일 경우 a n t i c 못배우므로 0 출력 후 return
			System.out.println(0);
			return;
		}
		
		if (k!=5) a = new String[k-5];	// 배울 수 있는 알파벳이 5개가 넘을 경우 k-5크기의 배열 생성 // a 배열은 배울 수 있는 알파벳을 조합으로 담기 위한 배열
		else if (k==5) { // 배울 수 있는 알파벳이 5개인 경우 
			if(arr2.size() != 0) {			// 배워야 할 새로운 알파벳이 있는 경우( a t i c n )제외 한 다른 알파벳
				System.out.println(ans);
				return;
			}
			else {					// 배울 수 있는 알파벳이 5개인데 모든 문자열이 a t i c n 으로 이루어진 경우
				System.out.println(n);
				return;
			}

		}
		
		arr3 = new ArrayList<>(arr2);
		
		if(arr3.size()<= k-5) {
			System.out.println(n);
			return;
		}

		comb(0,0);
		System.out.println(answer+ans);

	}
	
	public static void comb(int cnt,int start) {
		if(cnt == k-5) {
			ArrayList<String> arr4 =new ArrayList<>(Arrays.asList(a));
			int sum = 0;
			for(int j=0;j<arr5.size();j++) {
				boolean check = true;
				for(int k=0;k<arr5.get(j).length();k++) {
					if(!arr4.contains(String.valueOf(arr5.get(j).charAt(k)))) {
						check =false;
						break;
					}
				}
					if(check) sum++; 
				}
			answer = Math.max(answer, sum);
			return;
			}
			
		for(int i=start;i<arr2.size();i++) {
			a[cnt] = arr3.get(i); 
			comb(cnt+1,i+1);
		}
	}

}
