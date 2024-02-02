import java.io.*;
import java.util.*;
import java.math.*;

public class P2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/p.in"))));

        int fans = 0;
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
        for (int a = 0; a < n; ++a) {
            for (int b = 0; b < n; ++b) {
                HashSet<Integer> ids = new HashSet<>();
                if (a > 0 && b > 0 && a < n - 1 && b < m - 1)
                    continue;
                if (b == 0)
                    ids.add(0);
                if (a == 0)
                    ids.add(1);
                if (b == m - 1)
                    ids.add(2);
                if (a == n - 1)
                    ids.add(3);
                for (int id : ids) {
                    int ans = 0;
                    boolean[][][] vis = new boolean[4][n][m];
                    ArrayDeque<Integer> q = new ArrayDeque<>();
                    vis[id][a][b] = true;
                    q.add(id * n * m + a * m + b);
                    while (!q.isEmpty()) {
                        int u = q.removeFirst();
                        int ud = u / n / m;
                        int uy = u % (n * m) / m;
                        int ux = u % m;
                        HashSet<Integer> dirs = new HashSet<>();
                        if (ud == 0) {
                            if (lines.get(uy).charAt(ux) == '/')
                                dirs.add(3);
                            else if (lines.get(uy).charAt(ux) == '\\')
                                dirs.add(1);
                            else if (lines.get(uy).charAt(ux) == '|') {
                                dirs.add(3);
                                dirs.add(1);
                            } else
                                dirs.add(0);
                        }
                        if (ud == 2) {
                            if (lines.get(uy).charAt(ux) == '/')
                                dirs.add(1);
                            else if (lines.get(uy).charAt(ux) == '\\')
                                dirs.add(3);
                            else if (lines.get(uy).charAt(ux) == '|') {
                                dirs.add(1);
                                dirs.add(3);
                            } else
                                dirs.add(2);
                        }
                        if (ud == 1) {
                            if (lines.get(uy).charAt(ux) == '/')
                                dirs.add(2);
                            else if (lines.get(uy).charAt(ux) == '\\')
                                dirs.add(0);
                            else if (lines.get(uy).charAt(ux) == '-') {
                                dirs.add(2);
                                dirs.add(0);
                            } else
                                dirs.add(1);
                        }
                        if (ud == 3) {
                            if (lines.get(uy).charAt(ux) == '/')
                                dirs.add(0);
                            else if (lines.get(uy).charAt(ux) == '\\')
                                dirs.add(2);
                            else if (lines.get(uy).charAt(ux) == '-') {
                                dirs.add(0);
                                dirs.add(2);
                            } else
                                dirs.add(3);
                        }
                        for (int vd : dirs) {
                            int vy = uy + dy[vd];
                            int vx = ux + dx[vd];
                            if (vy < 0 || vx < 0 || vy >= n || vx >= m || vis[vd][vy][vx])
                                continue;
                            vis[vd][vy][vx] = true;
                            q.add(vd * n * m + vy * m + vx);
                        }
                    }
                    for (int i = 0; i < n; ++i) {
                        for (int j = 0; j < m; ++j) {
                            int ta = 0;
                            for (int k = 0; k < 4; ++k) {
                                if (vis[k][i][j])
                                    ta = 1;
                            }
//							if (ta==1) {
//								System.out.println(i+" "+j);
//							}
                            ans += ta;
                        }
                    }
                    fans = Math.max(fans, ans);
                }
            }
        }
        System.out.println(fans);
    }
}