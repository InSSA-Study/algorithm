package com.ssafy.bj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class bj_14725 {

	static int N;
	static Map<String, HashMap<String, HashMap>> map = new HashMap<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N =sc.nextInt();
		for(int i=0;i<N;i++) {
			int num = sc.nextInt();
			Map m = map;
			for(int j=0;j<num;j++) {
				String str = sc.next();
				if(!m.containsKey(str)) {
					m.put(str, new HashMap<>());
				}
				m = (Map) m.get(str);
			}
			
		}
		//System.out.println(map);
		print(map,"");

	}
	
	static void print(Map map, String bar) {
		Object[] key =  map.keySet().toArray();
		Arrays.sort(key);
		for(Object k: key) {

			System.out.println(bar+k);
			print((Map) map.get(k),bar+"--");
		}
	}

}

