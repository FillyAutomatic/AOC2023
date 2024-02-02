import java.io.*;
import java.util.*;
import java.math.*;

public class U1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/u.in"))));

        int ans = 0;
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.isEmpty())
                break;
            lines.add(s);
        }
        int n = lines.size();
        int m = lines.get(0).length();
        int[] dy = { 0, 1, 0, -1 };
        int[] dx = { 1, 0, -1, 0 };
        boolean[][][] dp = new boolean[65][n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (lines.get(i).charAt(j) == 'S')
                    dp[0][i][j] = true;
            }
        }
        for (int z = 1; z <= 64; ++z) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (!dp[z - 1][i][j])
                        continue;
                    for (int zz = 0; zz < 4; ++zz) {
                        int a = i + dy[zz];
                        int b = j + dx[zz];
                        if (a >= 0 && b >= 0 && a < n && b < m && lines.get(a).charAt(b) != '#')
                            dp[z][a][b] = true;
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (dp[64][i][j])
                    ++ans;
            }
        }
        System.out.println(ans);
    }
}