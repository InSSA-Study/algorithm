package com.ssafy.bj;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class bj_2210 {
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,-1,1};
	static HashSet set;
	static String [][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		map = new String[5][5];
		set = new HashSet();
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				map[i][j] = sc.next();
			}
		}
		String str = "";
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				dfs(i,j,map[i][j]);
			}
		}
		System.out.println(set.size());
	}
	
	public static void dfs(int x,int y,String str) {

		if(str.length()==6) {
			set.add(str);
			return;
		}
		
		for(int i=0;i<4;i++) {
			int nx = dx[i]+x;
			int ny = dy[i]+y;
			
			if(nx<0 || ny<0 || nx>=5 || ny>=5) continue;
			dfs(nx,ny,str+map[nx][ny]);
		}
	}

}
