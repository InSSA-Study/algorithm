import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {// N-Queen https://www.acmicpc.net/problem/9663
    static int[] box;
    static int N;
    static int totalCnt = 0;

    /**
     * 퀸은 상하좌우, 대각선 4방향으로 거리 제한 없이 이동 가능
     * 같은 행 두지 않는다.
     **/
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        box = new int[N + 1]; //행은
        nQueen(1);
        System.out.println(totalCnt);
    }

    private static void nQueen(int rowNo) {
        if (rowNo == N + 1) {
            totalCnt++;
            return;
        }
        for (int c = 1; c <= N; c++) {
            if (!isAvailable(rowNo, c)) continue;
            box[rowNo] = c;
            nQueen(rowNo + 1);
        }
    }

    private static boolean isAvailable(int rowNo, int c) {//해당 row에 c값을 넣는 것이 가능한가?
        for (int k = 1; k < rowNo; k++) { // k : 비교대상 queen의 행
            if (box[k] == c || Math.abs(box[k] - c) == rowNo - k) return false;
        }
        return true;
    }
}