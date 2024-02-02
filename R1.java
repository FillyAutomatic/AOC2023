import java.io.*;
import java.util.*;
import java.math.*;

public class R1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/r.in"))));

        int ans = 0;
        int cy = 500;
        int cx = 500;
        int[][] codes = new int[1000][1000];
        codes[cy][cx] = 1;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            char dir = s.charAt(0);
            int dist = Integer.parseInt(s.split(" ")[1]);
            int dy = 0;
            int dx = 0;
            if (dir == 'R')
                dx = 1;
            if (dir == 'L')
                dx = -1;
            if (dir == 'D')
                dy = 1;
            if (dir == 'U')
                dy = -1;
            for (int i = 0; i < dist; ++i) {
                cy += dy;
                cx += dx;
                codes[cy][cx] = 1;
            }
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 1000; ++j) {
                if (i == 0 || i == 999 || j == 0 || j == 999) {
                    if (codes[i][j] == 0) {
                        q.add(i * 1000 + j);
                    }
                }
            }
        }
        while (!q.isEmpty()) {
            int u = q.removeFirst();
            int uy = u / 1000;
            int ux = u % 1000;
            int[] dy = { 0, 1, 0, -1 };
            int[] dx = { 1, 0, -1, 0 };
            for (int z = 0; z < 4; ++z) {
                int vy = uy + dy[z];
                int vx = ux + dx[z];
                if (vy >= 0 && vy < 1000 && vx >= 0 && vx < 1000 && codes[vy][vx] == 0) {
                    codes[vy][vx] = -1;
                    q.add(vy * 1000 + vx);
                }
            }
        }
        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 1000; ++j) {
                if (codes[i][j] >= 0) {
                    // System.out.println(i+" "+j+" "+codes[i][j]);
                    ++ans;
                }
            }
        }
        System.out.println(ans);
    }
}