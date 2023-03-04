import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 박은정_B1647 { // 도시 분할 계획
    // 일단 MST -> 가장 큰 weight을 가지는 간선 제거    
    static List<Edge>[] graph;
    static int V;
    static int E;
    public static void main(String[] args) throws IOException { //List<Edge>[] graph, Edge{to,cost}
        // System.setIn(new FileInputStream("src/_week04/_0228/live/prim_adjList_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수

        // 그래프 선언, 간선 리스트로 표현
        graph = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }

        // 프림 알고리즘 수행
        System.out.println(prim(1));
    }

    public static int prim(int start) {
        boolean[] visit = new boolean[V + 1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        int totalCost = 0;
        int maxWeight = 0;
        while (!pq.isEmpty()) {
            Edge nowE = pq.poll();

            if (visit[nowE.to]) continue;
            visit[nowE.to] = true;
            totalCost += nowE.cost;
            maxWeight = Math.max(maxWeight, nowE.cost);
            for (Edge nextE : graph[nowE.to]) {
                if (!visit[nextE.to]) {
                    pq.add(nextE);
                }
            }
        }
        return totalCost-maxWeight;
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}
