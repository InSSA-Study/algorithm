package com.ssafy.bj;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
/**
 * @param cheese : 현재 맵에서 치즈의 총 개수
 * @param lastcheese : 한 시간 전의 치즈 개수 저장할 변수
 * (0,0) 에서 BFS 진행하여 치즈를 만나면 치즈의 개수를 1개 줄이고 빈칸으로 만든다.
 * 치즈가 0개가 될때까지 반복하며 BFS를 반복실행하는데 이때 방문체크한 배열은 계속 초기화 시켜준다.
 *
 */
public class bj_2636 {
	static int N,M, answer=0, cheese=0,lastcheese;
	static int map[][];
	static int dx [] = {1,-1,0,0};
	static int dy[] = {0,0,1,-1};
	static boolean visited[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==1) cheese++;
			}
		}

		while(cheese!=0) {
			visited= new boolean[N][M];
			lastcheese = cheese;
			bfs();
			answer++;

		}
		System.out.println(answer);
		System.out.println(lastcheese);

	}
	
	public static void bfs() {

		Queue<int []> q = new ArrayDeque<>();
		q.add(new int[] {0,0});
		visited[0][0] = true;
		int [] arr;
		while(!q.isEmpty()) {
			arr = q.poll();
			for(int i=0;i<4;i++) {
				int nx = arr[0]+dx[i];
				int ny = arr[1]+dy[i];
				if(nx<0||ny<0||nx>=N||ny>=M||visited[nx][ny]) continue;
				if(map[nx][ny] == 1) {
					map[nx][ny] = 0;
					
					cheese--;
				}
				else q.add(new int[] {nx,ny});
				visited[nx][ny] = true;
			}
		}
	}

}
