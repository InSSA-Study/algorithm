package study._0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 박은정_B14501_DP { // 퇴사
    /* D*/
    static int N;
    static int[][] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        data = new int[N + 1][2];
        StringTokenizer st;
        for (int n = 1; n <= N; n++) { // 1 ~ N일
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            data[n][0] = T;
            data[n][1] = P;
        }
        int[] dp = new int[N + 2];
        for (int n = 1; n <= N; n++) {
            int next = n + data[n][0];
            if (next <= N + 1) {
                dp[next] = Math.max(dp[n] + data[n][1], dp[next]);
            }
            dp[n + 1] = Math.max(dp[n], dp[n + 1]);
        }
        System.out.println(dp[N + 1]);
    }
}
