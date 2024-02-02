import java.io.*;
import java.util.*;
import java.math.*;

public class I1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/i.in"))));

        int ans = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] spl = s.split(" ");
            long[][] dp = new long[spl.length + 1][spl.length + 1];
            for (int i = 0; i < spl.length; ++i)
                dp[0][i] = Long.parseLong(spl[i]);
            for (int i = 1; i < spl.length; ++i) {
                for (int j = 1; j < spl.length; ++j) {
                    dp[i][j] = dp[i - 1][j] - dp[i - 1][j - 1];
                }
            }
            for (int i = spl.length - 1; i >= 0; --i) {
                dp[i][spl.length] = dp[i][spl.length - 1] + dp[i + 1][spl.length];
            }
            ans += dp[0][spl.length];
        }
        System.out.println(ans);
    }
}