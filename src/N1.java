import java.io.*;
import java.util.*;
import java.math.*;

public class N1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/n.in"))));

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
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; ++i) {
            grid[i] = lines.get(i).toCharArray();
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                char c = grid[i][j];
                if (c == 'O') {
                    grid[i][j] = '.';
                    int k = i;
                    while (k > 0 && grid[k - 1][j] == '.')
                        --k;
                    grid[k][j] = 'O';
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 'O')
                    ans += n - i;
            }
        }
        System.out.println(ans);
    }
}