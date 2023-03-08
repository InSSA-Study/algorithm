package study._0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B2252 {
    static int N, M;
    static List<Integer>[] adjList;
    static int[] inDegree;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 번호 1 ~ N번
        M = Integer.parseInt(st.nextToken()); // 키를 비교한 회수

        adjList = new ArrayList[N + 1];// 1 ~ N번
        for (int n = 0; n < N + 1; n++) {
            adjList[n] = new ArrayList<>();
        }

        inDegree = new int[N + 1];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b); // 방향 있는 . 위상정렬
            inDegree[b]++;
        }
        queue = new ArrayDeque<>();

        // 1. 진입 차수가 0인 노드를 큐에 모두 넣는다.
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) { // 진입 차수가 0
                queue.add(i);
            }

        }
        // 2. 큐에서 진입 차수가 0인 노드를 꺼내어 자신과 인접한 노드의 간선을 제거
        // 인접한 노드의 진입차수를 1 감소
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {        // 4. 큐가 공백 큐 상태가 될때까지 2-3작업을 반복한다.
            int now = queue.poll();
            result.add(now);
            for (int next : adjList[now]) { //자신과 인접한 노드의 간선을 제거
                inDegree[next]--;
                if (inDegree[next] == 0) { // 3. 간선 제거 후, 진입 차수가 0인 노드를 큐에 넣는다.
                    queue.offer(next);
                }
            }
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i) + " ");
        }
    }
}
