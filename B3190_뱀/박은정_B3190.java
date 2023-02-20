import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 박은정_B3190 {
    /**
     * 몸길이를 늘려 머리를 다음칸에 위치 이동한 칸에 사과 O -> 사과 없어짐 + 꼬리 움직이지 않음 사과 X -> 꼬리위치 칸 비워줌
     */
    static int N; // 보드의 크기
    static int[][] board; // 사과 1, 뱀 2, 그외 0
    static int K; // 사과 갯수
    static int L; // 뱀의 방향 변환 횟수
    static int[][] way = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //오른쪽-> 아래 -> 왼쪽 -> 위쪽
    static Queue<int[]> bam ;
    // L(왼쪽) : w-1
    // D(오른쪽) : w+1
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/study/ongoing/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드의 크기
        K = Integer.parseInt(br.readLine()); // 사과의 개수

        StringTokenizer st;
        board = new int[N][N];
        for (int k = 0; k < K; k++) {// 사과 위치
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;
            board[i][j] = 1;
        }

        L = Integer.parseInt(br.readLine());

        int bamX = 0, bamY = 0; // 뱀X, 뱀Y
        bam = new ArrayDeque<>();
        bam.offer(new int[]{bamX,bamY});
        board[bamX][bamY] = 2;

        //print();

        boolean endGame = false;
        int time = 0;
        int wIdx = 0;
        for (int l = 0; l < L; l++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // x초후에
            char rotate = st.nextToken().charAt(0); // 해당 방향으로 90도 회전 : L 또는 D
            //System.out.println(x+" "+rotate+">> ");

            while (true) {

                // 다음 뱀 길이 X, 다음 뱀 길이 Y
                int nextBamX = bamX + way[wIdx][0];
                int nextBamY = bamY + way[wIdx][1];
                if (nextBamX < 0 || nextBamX >= N || nextBamY < 0 || nextBamY >= N) { // 벽에 부딪히면 게임이 끝난다.
                    endGame = true;
                    break;
                }
                if (board[nextBamX][nextBamY] == 2) {// 자신의 몸과 부딪히면 게임이 끝난다.
                    endGame = true;
                    break;
                }
                // 이동 ===
                // 머리가 이동한다.
                bam.offer(new int[]{nextBamX,nextBamY});

                // 꼬리 이동 ? ===
                if(board[nextBamX][nextBamY]==0){//이동한 칸에 사과가 없다면
                    int[] tail = bam.poll();
                    board[tail[0]][tail[1]] = 0;
                }
                board[nextBamX][nextBamY] = 2;

                bamX = nextBamX;
                bamY = nextBamY;
                time += 1;
                if (time == x) {
                    wIdx = (rotate == 'L' ? wIdx - 1 + 4 : wIdx + 1) % 4;
                    break;
                }
            }
            if(endGame){
                break;
            }
        }
        if(endGame==false){
            while(true){
                int nextBamX = bamX + way[wIdx][0];
                int nextBamY = bamY + way[wIdx][1];
                if (nextBamX < 0 || nextBamX >= N || nextBamY < 0 || nextBamY >= N) { // 벽에 부딪히면 게임이 끝난다.
                    break;
                }
                if (board[nextBamX][nextBamY] == 2) {// 자신의 몸과 부딪히면 게임이 끝난다.
                    break;
                }
                bamX = nextBamX;
                bamY = nextBamY;
                time+=1;
            }
        }
        System.out.println(time+1);
    }

//    private static void printBam() {
//        int[][] bamArray = bam.toArray(new int[bam.size()][2]);
//        for(int[] t : bamArray){
//            System.out.println(t[0]+" "+t[1]+", \t");
//        }
//        System.out.println();
//    }
//
//    private static void print(){
//        for(int i=0;i<N;i++){
//            for(int j= 0; j < N; j++){
//                System.out.print(board[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//    }
}
