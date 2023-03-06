package com.ssafy.bj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.Scanner;

public class bj_18352 {
	static int N,M,K,X;
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer> list = new ArrayList<>();
	static boolean visited[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		X = sc.nextInt();
		adj = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int i=0;i<N+1;i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			adj[sc.nextInt()].add(sc.nextInt());
		}
		
		bfs();
		if(list.size()==0) {
			System.out.println(-1);
			return;
		}
		Collections.sort(list);
		
		for(int i:list) {
			System.out.println(i);
		}

	}
	static public void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(X);
		visited[X] = true;
		int level = 0;
		while(!q.isEmpty()) {
			level++;
			int size = q.size();
			for(int i=0;i<size;i++) {
				int cur = q.poll();
				for(int s: adj[cur]) {
					if(!visited[s]) {
						visited[s] = true;
						q.add(s);
						if(level == K) {
							list.add(s);
						}
					}
				}
			}
			

		}
	}

}
