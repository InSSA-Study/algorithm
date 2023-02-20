import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 박은정_B1062 {
    // 어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어의 개수가 최대가 되는가?
    static int N, K;
    static boolean[] alphaVisited;
    static char[] commons = {'a', 'c', 'i', 'n', 't'};
    static String[] words;
    static int maxReadableWordCnt = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 단어 개수
        K = Integer.parseInt(st.nextToken()); // 알파벳중 K개를 가르쳐야 함

        words = new String[N]; // 단어들
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word.substring(4, word.length() - 4);
        }

        // anta tica
        // a c i n t  : 공통적으로 가르쳐야 함
        if (K < 5) { // 공통 알파벳도 가르치지 못함
            System.out.println(0);
        } else {
            // 26-5 개중 K-5개 선택
            alphaVisited = new boolean[26];
            for (char common : commons) {
                alphaVisited[common - 'a'] = true;
            }
            comb(0, 0);
            System.out.println(maxReadableWordCnt);
        }

    }

    private static void comb(int cnt, int start) {
        if (cnt == K - 5) {
            //여기서 단어를 몇개 읽을 수 있는지 판단
            int readableWordCnt = 0;
            for (String word : words) {
                boolean canRead = true;
                for (int i = 0; i < word.length(); i++) {
                    if (alphaVisited[word.charAt(i) - 'a'] == false) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) {
                    readableWordCnt+=1;
                }
            }
            maxReadableWordCnt = Math.max(maxReadableWordCnt, readableWordCnt);
            return;
        }
        for (int i = start; i < 26; i++) {
            if (alphaVisited[i]) {
                continue;
            }
            alphaVisited[i] = true;
            comb(cnt + 1, i + 1);
            alphaVisited[i] = false;
        }
    }
}
