package com.ssafy.bj;

import java.util.Arrays;
import java.util.Scanner;

class Edge implements Comparable<Edge>{
	int from;
	int to;
	int weight;
	public Edge(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.weight, o.weight);
	}
	
}
public class bj_1647 {
	static int V,E;
	static int parents[];
	static Edge[] arr;
	
	static void makeSet() {
		parents = new int[V+1];
		for(int i=1;i<V+1;i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(x == parents[x]) return x;
		return parents[x] = findSet(parents[x]);
	}
	
	static boolean union(int x, int y) {
		int nx = findSet(x);
		int ny = findSet(y);
		if(nx == ny) return false;
		parents[ny] = nx;
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		V = sc.nextInt();
		E = sc.nextInt();
		makeSet();
		arr = new Edge[E];
		for(int i=0;i<E;i++) {
			arr[i] = new Edge(sc.nextInt(),sc.nextInt(),sc.nextInt());
		}
		Arrays.sort(arr);
		int cnt =0;
		int ans = 0;
		for(Edge e: arr) {
			if(union(e.from,e.to)) {
				cnt++;
				ans+= e.weight;
			}
			if(cnt == V-2) {
				break;
			}
		}
		System.out.println(ans);

	}

}
