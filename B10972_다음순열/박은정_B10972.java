package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10972 {
    static int N;
    static int[]findNext; // 5, 4, 3, 2, 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        findNext=new int[N];
        for(int i=0;i<N;i++){
            findNext[i]=Integer.parseInt(st.nextToken());
        }

        nextPermutation(findNext);

    }
    public static void nextPermutation(int[] nums){
        //뒤에서부터 탐색하면서 오름차순이 깨지는 인덱스를 확인(a)
        int a = nums.length-2;
        while(a>=0&&nums[a]>=nums[a+1]){
            a--;
        }
        if(a!=-1){// 5 4 3 2 1 
            //다시 뒤에서부터 탐색하면서 a의 수 보다 큰 첫번째 인덱스를 확인(b)
            int b = nums.length-1;
            while(nums[a]>=nums[b])b--;
            //a와 b를 스왑
            swap(nums,a,b);

            //a+1에서부터 끝까지를 오름차순 정렬
            int start = a+1;
            int end = nums.length-1;
            while(start<end){
                swap(nums,start++,end--);
            }
            for(int n:nums){
                System.out.print(n+" ");
            }
        }else{
            System.out.println(-1);
        }
    }
    public static void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
