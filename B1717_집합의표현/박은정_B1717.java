import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1717 {
    static int[] parents;
    static int[] depth; // depth를 쓰는 이유!

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 0 1 2 .. n 의 집합이 있음
        int m = Integer.parseInt(st.nextToken()); // 입력으로 주어지는 연산의 개수
        parents = new int[n + 1]; // 0 ~ n
        depth = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
            depth[i] = 1;
        }
        for (int i = 0; i < m; i++) { // m개의 줄, 각각의 연산
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (command == 0) { // a가 포함되어 있는 집합과 b가 포함되어 있는 집합을 합친다 : 0 a b
                union(a, b);
            } else { // 두원소가 같은 집합에 포함? find(a)==find(b) : 1 a b
                if (find(a) == find(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }
        System.out.println(sb);
        br.close();
    }

    private static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent == bParent) {
            return;
        }
        if (depth[aParent] < depth[bParent]) { //a의 최상위 부모의 깊이
            parents[aParent] = bParent; // aParent의 root를 bParent로 변경
        } else {
            parents[bParent] = aParent; // bParent의 root를 aParent로 변경
        }
        if(depth[aParent]==depth[bParent]){
            depth[aParent]++; // 만약 높이가 같다면, 합친 후 (aParent + 1) //aParent로 root를 변경했으므로!
        }

    }

    private static int find(int x) {
        if (parents[x] == x) {//루트
            return x;
        }
        return parents[x] = find(parents[x]);
    }


}
