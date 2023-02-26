import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 박은정_B2636 { // 치즈
    static int N, M;
    static int[][] box;

    static final int EMPTY = 0;
    static final int CHEESE = 1;
    static int[][] ways = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int allCheese;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/study/_0226/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                box[n][m] = Integer.parseInt(st.nextToken());
                if (box[n][m] == CHEESE) {
                    allCheese += 1;
                }
            }
        }

        int currentCheese = allCheese;
        int time = 0;
        while (allCheese != 0) {
            time++;
            currentCheese = allCheese;
            meltCheese();
        }
        System.out.println(time);
        System.out.println(currentCheese);

    }

    static boolean[][] isVisited;

    private static void meltCheese() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        isVisited = new boolean[N][M];
        isVisited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + ways[i][0];
                int ny = cur[1] + ways[i][1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || isVisited[nx][ny]) {
                    continue;
                }

                if (box[nx][ny] == CHEESE) { // 치즈라면,
                    allCheese--;
                    box[nx][ny] = 0; // 녹이기 -> 녹이고, 방문처리하므로, 이번 라운드에 녹은 치즈안쪽에 있는 치즈는 녹지 않는다.
                } else if (box[nx][ny] == EMPTY) { // 비어있다면,
                    queue.offer(new int[]{nx, ny}); //다음 방문할 위치이므로 큐에 넣는다.
                }
                isVisited[nx][ny] = true;

            }
        }
    }
}
