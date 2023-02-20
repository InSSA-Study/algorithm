import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 박은정_B2178 {// 미로탐색
    static int result;
    static int N;
    static int M;
    static int[][] miro; // 미로
    static int[][] way = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                miro[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0); // (0,0) -> (N,M)

        System.out.println(result);
    }

    private static void bfs(int i, int j) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i,j,1});
        while(!queue.isEmpty()){
            int[] loc = queue.poll();
            for (int w = 0; w < 4; w++) {
                int ni = way[w][0] + loc[0];
                int nj = way[w][1] + loc[1];
                int cnt = loc[2] + 1;

                if (ni < 0 || ni >= N || nj < 0 || nj >= M) {
                    continue;
                }
                if (miro[ni][nj] != 1) {
                    continue;
                }
                if(ni==N-1 && nj == M-1){
                    result = cnt;
                    return;
                }
                miro[ni][nj] = cnt; //visited 배열 역할 같이함
                queue.add(new int[]{ni,nj,cnt});
            }
        }
    }

}
