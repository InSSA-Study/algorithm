package _test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2567 {
    static int totalCnt;
    static int MAX_LEN = 100;
    static int[][] box;
    static int blackN; // 검은 스카프의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//검은 스카프의 수

        totalCnt=0;
        blackN = Integer.parseInt(br.readLine());

        box = new int[MAX_LEN+2][MAX_LEN+2]; // 양의 정수로 주어짐 즉, 1,1 부터 100,100까지만 주어짐
        StringTokenizer st;
        for(int n=0;n<blackN;n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for(int i = x; i < Math.min(x+10, MAX_LEN); i++) {
                for(int j= y; j < Math.min(y+10, MAX_LEN); j++) {
                    box[i][j] = 1;
                }
            }
        }

        for(int i=0;i<MAX_LEN+1;i++) {
            for(int j=0;j<MAX_LEN+1;j++) {
                if(box[i][j]==1) {
                    meltBlack(i,j); // 안쪽 둘레에 표시
                }
            }
        }
        //printMatrix();
        System.out.println(totalCnt);

    }

    static int[][] way = new int[][] {{1,0},{-1,0},{0,1},{0,-1}}; //상하좌우 방향

    private static void meltBlack(int i, int j) { /* 필요없는 코드 다 삭제 했더니 아래처럼 남음! -> 구현이 맞음...! */
        int[] now = new int[]{i,j};
        for(int w=0;w<4;w++) {
            int nx = now[0]+way[w][0];
            int ny = now[1]+way[w][1];

            if(nx<0 || nx > MAX_LEN || ny<0 || ny>MAX_LEN) {continue;} //범위 상황봐서 바꿔야 함
            if(box[nx][ny]==0) { //다음으로 갈 곳이 흰색
                totalCnt+=1;
                box[now[0]][now[1]] = 2;
            }
        }
    }

    private static void printMatrix() { // 디버깅용 함수
        for(int i=0;i<100;i++) {
            for(int j=0;j<100;j++) {
                System.out.print(box[i][j]+" ");
            }
            System.out.println();
        }
    }
}