import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class 박은정_B1461 {
    /**
     * 0에 책 위치 정리해야 하는 책의 개수, 한번에 들 수 있는 책의 개수 책의 위치 최소 걸음
     */
    static int res = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 책의 개수
        int M = Integer.parseInt(st.nextToken()); // 한번에 들 수 있는 책의 개수

        List<Integer> left = new LinkedList<>();
        List<Integer> right = new LinkedList<>();

        int max = Integer.MIN_VALUE; //가장 0과 멀리 떨어져 있는 위치

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int item = Integer.parseInt(st.nextToken());
            if (item < 0) {
                left.add(Math.abs(item));
            } else {
                right.add(item);
            }
            max = Math.max(max, Math.abs(item));
        }
        Collections.sort(left, Collections.reverseOrder()); // 거리가 먼 순서대로 정렬
        Collections.sort(right, Collections.reverseOrder());

        for (int i = 0; i < left.size(); i++) { // 0, M , 2M ... 위치
            if (i % M == 0 && left.get(i) == max) {//거리가 가장 먼 item이면,
                res += left.get(i);
            } else if (i % M == 0) {
                res += (left.get(i) * 2);
            }
        }

        for (int i = 0; i < right.size(); i++) {
            if (i % M == 0 && right.get(i) == max) {
                res += right.get(i);
            } else if (i % M == 0) {
                res += (right.get(i) * 2);
            }
        }
        System.out.println(res);

    }
}
