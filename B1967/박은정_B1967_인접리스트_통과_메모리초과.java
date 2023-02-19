package study.ongoing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class B1967_인접리스트 {//트리의 지름
    /**
     * 한 점에서 다른 한 점 끝까지 가야 함 -> DFS
     */
    // 인접리스트
    static int N; // 노드의 개수
    static List<int[]>[] adjList; // 인접리스트
    static boolean[] visited;
    static int maxDistance = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/study/ongoing/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1]; // 1 ~ N 까지의 정수
        for (int i = 1; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int money = Integer.parseInt(st.nextToken());
            adjList[parent].add(new int[]{child, money});
            adjList[child].add(new int[]{parent, money});
        }
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            dfs_stack(i);
        }
        System.out.println(maxDistance);
    }
    // 인접리스트 + 재귀 = dfs : 통과
    private static void dfs_recursive(int node, int sum) { 
        visited[node] = true;

        int now = node;
        maxDistance = Math.max(sum, maxDistance);
        for (int[] next : adjList[now]) {
            if (visited[next[0]]) {
                continue;
            }
            visited[next[0]] = true;
            dfs_recursive(next[0], sum + next[1]);
            visited[next[0]] = false;
        }


    }
    // 인접리스트 + stack = dfs : 메모리초과
    private static void dfs_stack(int node) { 

        Stack<int[]> stack = new Stack<>();
        visited[node] = true;
        stack.add(new int[]{node,0});

        while (!stack.isEmpty()){
            int[] now = stack.pop();
            //System.out.println(now[0]);
            maxDistance = Math.max(now[1], maxDistance);

            for (int[] next : adjList[now[0]]) {
                if (visited[next[0]]) {
                    continue;
                }
                visited[next[0]] = true;
                stack.add(new int[]{next[0],now[1]+next[1]});


            }
        }
    }
}
