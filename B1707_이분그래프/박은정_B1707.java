package _week05._0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1707 {
    static int K;
    static List<Integer>[] adjList;
    static int V, E;
    static int[] colored;

    /*
     * 이분그래프 : 정점의 집합을 둘로 분할 , 각 집합에 속한 정점끼리는 서로 인접하지 않도록
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        K = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        for (int test = 0; test < K; test++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); // 정점의 개수
            E = Integer.parseInt(st.nextToken()); // 간선의 개수

            adjList = new List[V + 1]; //인접리스트
            for (int v = 0; v < V + 1; v++) {
                adjList[v] = new ArrayList<>();
            }

            for (int e = 0; e < E; e++) { // E개의 줄에 걸쳐 간선에 대한 정보가 주어짐
                st = new StringTokenizer(br.readLine()); // 인접한 두 정점의 번호
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adjList[u].add(v);
                adjList[v].add(u);
            }

            boolean isBipartite = true;
            int color = 1;
            colored = new int[V + 1];
            for (int v = 0; v < V + 1; v++) {
                if (colored[v]==0 && !bfs(v, color)) {
                    isBipartite = false;
                    break;
                }
            }
            if(isBipartite){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }

    }

    // 서로 인접한 정점이 같은 색 -> 이분 그래프 아님
    private static boolean bfs(int start, int color) {
        Arrays.fill(colored, 0); // 0으로 초기화

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        colored[start] = color; 
        
        while (!queue.isEmpty()) {
            int now = queue.poll(); //color

            for (int next : adjList[now]) { // 현재와 인접한 정점
                if (colored[next] == 0) { // 방문한 적이 없음
                    colored[next] = (-1) * colored[now];
                    queue.offer(next);
                } else { // colored[next]
                    if (colored[next] == colored[now]) { //인접해있는데 같은 색
                        return false;
                    }
                }
            }
        }
        return true;
    }


}
