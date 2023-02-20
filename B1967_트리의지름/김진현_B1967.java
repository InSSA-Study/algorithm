import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] tree;
    static int[] weight;
    static int maxWeight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        tree = new List[n+1];
        weight = new int[n+1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();            
        }
        
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[p].add(c);
            weight[c] = w;
        }
        getMaximumWeight(1);
        System.out.println(maxWeight);
    
    }
    static int getMaximumWeight(int p) {
        List<Integer> children = tree[p];
        int firstMax = 0;
        int secondMax = 0;
        for (int c : children) {
            int w = weight[c] + getMaximumWeight(c);
            if (w > firstMax) {
                secondMax = firstMax;
                firstMax = w;
            } else if (w > secondMax) {
                secondMax = w;
            }
        }
        if (firstMax + secondMax > maxWeight) {
            maxWeight = firstMax + secondMax;
        }
        return firstMax;
    }
}
