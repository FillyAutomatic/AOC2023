import java.io.*;
import java.util.*;
import java.math.*;

public class M2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/m.in"))));

        int ans = 0;
        while (sc.hasNextLine()) {
            ArrayList<String> lines = new ArrayList<>();
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                if (s.isEmpty())
                    break;
                lines.add(s);
            }
            int n = lines.size();
            int m = lines.get(0).length();
            boolean[][] re = new boolean[n][n];
            boolean[][] ce = new boolean[m][m];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    re[i][j] = lines.get(i).equals(lines.get(j));
                }
            }
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < m; ++j) {
                    ce[i][j] = true;
                    for (int k = 0; k < n; ++k) {
                        ce[i][j] &= (lines.get(k).charAt(i) == lines.get(k).charAt(j));
                    }
                }
            }
            HashSet<Integer> vr = new HashSet<>();
            HashSet<Integer> vc = new HashSet<>();
            for (int i = 0; i < n - 1; ++i) {
                int ta = (i + 1) * 100;
                for (int a = 0; i - a >= 0 && i + a + 1 < n; ++a) {
                    if (!re[i - a][i + a + 1])
                        ta = 0;
                }
                // System.out.println(ta);
                if (ta > 0)
                    vr.add(i);
            }
            for (int i = 0; i < m - 1; ++i) {
                int ta = i + 1;
                for (int a = 0; i - a >= 0 && i + a + 1 < m; ++a) {
                    if (!ce[i - a][i + a + 1])
                        ta = 0;
                }
                // System.out.println(ta);
                if (ta > 0)
                    vc.add(i);
            }
            HashSet<Integer> nvr = new HashSet<>();
            HashSet<Integer> nvc = new HashSet<>();
            for (int y = 0; y < n; ++y) {
                for (int x = 0; x < m; ++x) {
                    for (int i = 0; i < n; ++i) {
                        for (int j = 0; j < n; ++j) {
                            re[i][j] = true;
                            for (int k = 0; k < m; ++k) {
                                re[i][j] &= (lines.get(i).charAt(k) == lines.get(j).charAt(k))
                                        ^ ((i == y || j == y) && k == x);
                            }
                        }
                    }
                    for (int i = 0; i < m; ++i) {
                        for (int j = 0; j < m; ++j) {
                            ce[i][j] = true;
                            for (int k = 0; k < n; ++k) {
                                ce[i][j] &= (lines.get(k).charAt(i) == lines.get(k).charAt(j))
                                        ^ ((i == x || j == x) && k == y);
                            }
                        }
                    }
                    for (int i = 0; i < n - 1; ++i) {
                        int ta = (i + 1) * 100;
                        for (int a = 0; i - a >= 0 && i + a + 1 < n; ++a) {
                            if (!re[i - a][i + a + 1])
                                ta = 0;
                        }
                        // System.out.println(ta);
                        if (ta > 0)
                            nvr.add(i);
                    }
                    for (int i = 0; i < m - 1; ++i) {
                        int ta = i + 1;
                        for (int a = 0; i - a >= 0 && i + a + 1 < m; ++a) {
                            if (!ce[i - a][i + a + 1])
                                ta = 0;
                        }
                        // System.out.println(ta);
                        if (ta > 0)
                            nvc.add(i);
                    }
                }
            }
            nvr.removeAll(vr);
            nvc.removeAll(vc);
            for (int i : nvr) {
                ans += (i + 1) * 100;
            }
            for (int i : nvc) {
                ans += i + 1;
            }
        }
        System.out.println(ans);
    }
}