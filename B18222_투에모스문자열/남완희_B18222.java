package com.ssafy.bj;

import java.util.Arrays;
import java.util.Scanner;

public class bj_18222 {
	static long [] arr = new long[64];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long k = sc.nextLong();
		arr[0] = 1l;
		for(int i=1; i<64;i++) {
			arr[i] = (long) Math.pow(2, i);
		}
		System.out.println(method(k));
	}

	public static int method(long x) {
		if(x==1) return 0;
		long num = 0;
		for(int i=0;i<64;i++) {
			if(x>arr[i]) {
				num = arr[i];
				
			}
			else {
				break;
			}
		}
		return 1- method(x-num);
	}
}
