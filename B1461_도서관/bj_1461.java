package com.ssafy.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_1461 {
	static int n;
	static int m;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = 0;
		PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
		PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(Collections.reverseOrder());
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(st1.nextToken());
			if(x <0) pq1.add(x);
			else pq2.add(x);
		}
		
		if(!pq1.isEmpty() && pq2.isEmpty()) {
			method(pq1);
			return;									
		}
		else if(pq1.isEmpty() && !pq2.isEmpty()) {
			method(pq2);
			return;
		}
		
		
		if(Math.abs(pq1.peek()) > Math.abs(pq2.peek())) {
			int cnt = 0;
			while(cnt!=m) {
				if(pq1.isEmpty()) break;
				if(cnt==0) ans+=Math.abs(pq1.poll());
				else pq1.poll();
				cnt++;
			}
		}
		else {
			int cnt = 0;
			while(cnt!=m) {
				if(pq2.isEmpty()) break;
				if(cnt==0) ans+=Math.abs(pq2.poll());
				else pq2.poll();
				cnt++;
			}
		}
		
		int cnt1 = 0;
		while(!pq1.isEmpty()) {
			
			if(cnt1 % m == 0) ans+=Math.abs(pq1.poll())*2;
			else pq1.poll();
			cnt1++;
		}
		
		int cnt2 = 0;
		while(!pq2.isEmpty()) {
			
			if(cnt2 % m == 0) ans+= Math.abs(pq2.poll())*2;
			else pq2.poll();
			cnt2++;
		}
		
		System.out.println(ans);
		

	}
	
	public static void method(PriorityQueue<Integer> p) {
		int cnt = 0;
		while(cnt!=m) {
			if(p.isEmpty()) break;
			if(cnt==0) ans+=Math.abs(p.poll());
			else p.poll();
			cnt++;
		}
		int cnt1 = 0;
		while(!p.isEmpty()) {
			
			if(cnt1 % m == 0) ans+=Math.abs(p.poll())*2;
			else p.poll();
			cnt1++;
		}
		System.out.println(ans);
	}

}