import java.io.*;
import java.util.*;
import java.math.*;

public class K1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/k.in"))));

        int ans = 0;
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        int n = lines.size();
        int m = lines.get(0).length();
        int[] rct = new int[n];
        int[] cct = new int[m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (lines.get(i).charAt(j) == '#') {
                    ++rct[i];
                    ++cct[j];
                }
            }
        }
        ArrayList<String> nlines = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            String next = "";
            for (int j = 0; j < m; ++j) {
                next += lines.get(i).charAt(j);
                if (cct[j] == 0)
                    next += lines.get(i).charAt(j);
            }
            nlines.add(next);
            if (rct[i] == 0)
                nlines.add(next);
        }
        lines = nlines;
        n = lines.size();
        m = lines.get(0).length();
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (lines.get(i).charAt(j) == '#') {
                    al.add(i * m + j);
                }
            }
        }
        for (int i = 0; i < al.size(); ++i) {
            int iy = al.get(i) / m;
            int ix = al.get(i) % m;
            for (int j = i + 1; j < al.size(); ++j) {
                int jy = al.get(j) / m;
                int jx = al.get(j) % m;
                ans += Math.abs(iy - jy) + Math.abs(ix - jx);
            }
        }
        System.out.println(ans);
    }
}