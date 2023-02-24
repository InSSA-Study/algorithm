package com.ssafy.bj;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class bj_2178 {
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int [][] map = new int[n][m];
		for(int i=0;i<n;i++) {
			String [] str = sc.next().split("");
			for(int j=0;j<m;j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		Queue<int []> q = new ArrayDeque<int[]>();
		q.add(new int[] {0,0});
		
		while(!q.isEmpty()) {
			int [] a = q.poll();
			for(int i =0;i<4;i++) {
				int nx = dx[i]+a[0];
				int ny = dy[i]+a[1];
				
				if(nx<0||ny<0||nx>=n||ny>=m) continue;
				if(map[nx][ny]!=1) continue;
				
				if(nx == n-1 && ny == m-1) {
					System.out.println(map[a[0]][a[1]]+1);
					return;
				}
				map[nx][ny] = map[a[0]][a[1]]+1;
				q.add(new int[] {nx,ny});
			}
			map[a[0]][a[1]] = 0;

		}

	}

}
