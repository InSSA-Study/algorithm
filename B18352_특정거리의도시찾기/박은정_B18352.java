import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B18352 { // 특정 거리의 도시 찾기
    static List<Integer>[] adjList;
    static int N, M, K, X; // 도시, 도로, 거리정보, 출발 도시의 번호

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/study/_0305/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 개수
        M = Integer.parseInt(st.nextToken()); // 도로의 개수
        K = Integer.parseInt(st.nextToken()); // 거리 정보
        X = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        adjList = new ArrayList[N + 1];
        for (int n = 0; n < N + 1; n++) {
            adjList[n] = new ArrayList<>();
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            // 단방향 , 가중치 없음, 최단거리 => BFS
            adjList[A].add(B);
        }

        // bfs -> K 일때
        boolean isExist = false;
        boolean[] visited = new boolean[N + 1]; // 1 ~ N 도시
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{X, 0});
        visited[X] = true;
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[1] == K) {
                list.add(now[0]);
                isExist = true;
            }
            for (int next : adjList[now[0]]) {
                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                queue.add(new int[]{next, now[1] + 1});
            }
        }
        if (!isExist) {
            System.out.println(-1);
        } else {
            list.sort(Comparator.comparingInt(x -> x));
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }

    }
}
