package study.ongoing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1967_인접행렬_메모리초과 {//트리의 지름 // 메모리초과
    /**
     * 한 점에서 다른 한 점 끝까지 가야 함 -> DFS
     */

    static int N; // 노드의 개수
    static int[][] adjMatrix; // 인접행렬
    static boolean[] visited;
    static int maxDistance = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/study/ongoing/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjMatrix = new int[N+1][N + 1]; // 1 ~ N 까지의 정수

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            adjMatrix[parent][child]= money;
            adjMatrix[child][parent] = money;
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            dfs_recursive(i,0);
        }
        System.out.println(maxDistance);
    }

    private static void dfs_recursive(int node, int sum) { // 인접행렬 + 재귀 = dfs  : 메모리 초과
        visited[node] = true;

        int now = node;
        maxDistance = Math.max(sum, maxDistance);
        for (int next = 1; next<=N;next++) {
            if (adjMatrix[now][next]==0 || visited[next]) {
                continue;
            }
            visited[next] = true;
            dfs_recursive(next, sum + adjMatrix[now][next]);
            visited[next] = false;
        }


    }
    private static void dfs_stack(int node) { // 인접행렬 + stack = dfs  : 메모리 초과

        Stack<int[]> stack = new Stack<>();
        visited[node] = true;
        stack.add(new int[]{node,0});

        while (!stack.isEmpty()){
            int[] now = stack.pop();
            //System.out.println(now[0]+" "+now[1]);
            maxDistance = Math.max(now[1], maxDistance);

            for (int next = 1; next<=N;next++) {
                if (adjMatrix[now[0]][next]==0 || visited[next]) {
                    continue;
                }
                visited[next] = true;
                stack.add(new int[]{next,now[1]+adjMatrix[now[0]][next]});


            }
        }
    }
}
