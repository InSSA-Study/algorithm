package study.ongoing;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 박은정_B18222 {// 투에모스
    /*
        t_0 = 0
        t_2n = t_n
        t_2n+1 = 1 - t_n
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());

        System.out.println(toEmos(k-1));
    }

    private static int toEmos(long k) {
        if (k == 0) {
            return 0;
        }
        else if(k == 1){
            return 1;
        }
        if (k % 2 == 0) {
            return toEmos(k / 2);
        }
        return 1 - toEmos((k - 1) / 2);
    }

}

public class B18222_다른풀이1 {// 투에모스
    static long[] pow;    // 2의 제곱수들을 저장해 놓는 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());

        pow = new long[64];

        pow[0] = 1;
        for (int i = 1; i < 64; i++) {
            pow[i] = pow[i - 1] * 2;
        }

        System.out.println(toEmos(k));
    }

    private static int toEmos(long k) {
        if (k == 1) {
            return 0;
        }
        for (int i = 0; i < 64; i++) {
          if (pow[i] >= k) {
            return 1 - toEmos(k - pow[i - 1]);
          }
        }
        return 0;
    }

}