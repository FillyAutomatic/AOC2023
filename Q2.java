import java.io.*;
import java.util.*;
import java.math.*;

public class Q2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/q.in"))));

        int ans = 1000000000;
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
        int[][][][] dist = new int[4][10][n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int a = 0; a < 4; ++a) {
                    for (int b = 0; b < 10; ++b) {
                        dist[a][b][i][j] = 1000000000;
                    }
                }
            }
        }
        dist[0][0][0][1] = lines.get(0).charAt(1) - '0';
        dist[1][0][1][0] = lines.get(1).charAt(0) - '0';
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(1);
        q.add(1 * 10 * n * m + 1 * m);
        while (!q.isEmpty()) {
            int u = q.removeFirst();
            int ud = u / m / n / 10;
            int unum = (u / m / n) % 10;
            int uy = (u / m) % n;
            int ux = u % m;
            int udist = dist[ud][unum][uy][ux];
            // System.out.println(ud+" "+unum+" "+uy+" "+ux+" "+udist);
            if (uy == n - 1 && ux == m - 1)
                ans = Math.min(ans, udist);

            int vd = ud;
            int vnum = unum + 1;
            int vy = uy + dy[vd];
            int vx = ux + dx[vd];
            if (vnum < 10 && vy >= 0 && vx >= 0 && vy < n && vx < m) {
                int vdist = udist + lines.get(vy).charAt(vx) - '0';
                if (vdist < dist[vd][vnum][vy][vx]) {
                    // System.out.println(vd+" "+vnum+" "+vy+" "+vx);
                    q.add(vd * 10 * n * m + vnum * n * m + vy * m + vx);
                    dist[vd][vnum][vy][vx] = vdist;
                }
            }

            if (vnum >= 4) {
                vd = (ud + 1) % 4;
                vnum = 0;
                vy = uy + dy[vd];
                vx = ux + dx[vd];
                if (vnum < 10 && vy >= 0 && vx >= 0 && vy < n && vx < m) {
                    int vdist = udist + lines.get(vy).charAt(vx) - '0';
                    if (vdist < dist[vd][vnum][vy][vx]) {
                        // System.out.println(vd+" "+vnum+" "+vy+" "+vx);
                        q.add(vd * 10 * n * m + vnum * n * m + vy * m + vx);
                        dist[vd][vnum][vy][vx] = vdist;
                    }
                }

                vd = (ud + 3) % 4;
                vnum = 0;
                vy = uy + dy[vd];
                vx = ux + dx[vd];
                if (vnum < 10 && vy >= 0 && vx >= 0 && vy < n && vx < m) {
                    int vdist = udist + lines.get(vy).charAt(vx) - '0';
                    if (vdist < dist[vd][vnum][vy][vx]) {
                        // System.out.println(vd+" "+vnum+" "+vy+" "+vx);
                        q.add(vd * 10 * n * m + vnum * n * m + vy * m + vx);
                        dist[vd][vnum][vy][vx] = vdist;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}