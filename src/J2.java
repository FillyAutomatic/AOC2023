import java.io.*;
import java.util.*;
import java.math.*;

public class J2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/j.in"))));

        int ans = 0;
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        int n = lines.size();
        int m = lines.get(0).length();
        ArrayList<HashSet<Integer>> g = new ArrayList<>();
        for (int i = 0; i < n * m; ++i)
            g.add(new HashSet<>());
        int start = -1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int u = i * m + j;
                char c = lines.get(i).charAt(j);
                if (c == 'S')
                    start = u;
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int u = i * m + j;
                char c = lines.get(i).charAt(j);
                if (j > 0 && (c == '-' || c == 'J' || c == '7')) {
                    g.get(u).add(u - 1);
                    if (u - 1 == start)
                        g.get(u - 1).add(u);
                }
                if (j < m - 1 && (c == '-' || c == 'L' || c == 'F')) {
                    g.get(u).add(u + 1);
                    if (u + 1 == start)
                        g.get(u + 1).add(u);
                }
                if (i > 0 && (c == '|' || c == 'J' || c == 'L')) {
                    g.get(u).add(u - m);
                    if (u - m == start)
                        g.get(u - m).add(u);
                }
                if (i < n - 1 && (c == '|' || c == '7' || c == 'F')) {
                    g.get(u).add(u + m);
                    if (u + m == start)
                        g.get(u + m).add(u);
                }
            }
        }
        boolean[][] vis = new boolean[n][m];
        vis[start / m][start % m] = true;
        int u = g.get(start).iterator().next();
        int pu = start;
        while (u != start) {
            vis[u / m][u % m] = true;
            for (int v : g.get(u)) {
                if (v == pu)
                    continue;
                pu = u;
                u = v;
                break;
            }
        }
        lines.set(42, lines.get(42).replaceAll("S", "F"));
        for (int i = 0; i < n; ++i) {
            int ct = 0;
            char pair = '-';
            for (int j = 0; j < m; ++j) {
                if (!vis[i][j] && ct % 2 == 1) {
                    ++ans;
                    // System.out.println(i+" "+j);
                }
                if (!vis[i][j]) {
                    continue;
                }
                if (lines.get(i).charAt(j) == '|')
                    ++ct;
                if (lines.get(i).charAt(j) == 'L' || lines.get(i).charAt(j) == 'F')
                    pair = lines.get(i).charAt(j);
                if (lines.get(i).charAt(j) == 'J' && pair == 'F')
                    ++ct;
                if (lines.get(i).charAt(j) == '7' && pair == 'L')
                    ++ct;
            }
        }
        System.out.println(ans);
    }
}