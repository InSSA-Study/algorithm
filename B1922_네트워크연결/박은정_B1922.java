import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] box;
    static int[][] dist; // 최소비용을 저장
    static int[][] way = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int time = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            box = new int[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    box[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            System.out.println("Problem " + time + ": " + dijkstra(0, 0));
            time++;
        }
    }

    private static int dijkstra(int startX, int startY) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[startX][startY] = box[startX][startY]; // 초기 값
        pq.offer(new Node(startX, startY, box[startX][startY])); // 시작 좌표

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            // 사방탐색
            for (int k = 0; k < 4; k++) {
                int nextX = now.x + way[k][0];
                int nextY = now.y + way[k][1];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) { // 범위 검사
                    continue;
                }

                if (dist[nextX][nextY] > dist[now.x][now.y] + box[nextX][nextY]) { // 기존의 가중치보다 작은 경우
                    dist[nextX][nextY] = dist[now.x][now.y] + box[nextX][nextY]; // 가중치를 교환
                    pq.offer(new Node(nextX, nextY, dist[nextX][nextY])); // 큐에 추가
                }
            }
        }
        return dist[N - 1][N - 1];
    }

    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

}
