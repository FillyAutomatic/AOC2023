import java.io.*;
import java.util.*;
import java.math.*;

public class L1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/l.in"))));

        int ans = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String s = line.split(" ")[0];
            String[] parts = line.split(" ")[1].split(",");
            int[] pints = new int[parts.length];
            for (int i = 0; i < parts.length; ++i)
                pints[i] = Integer.parseInt(parts[i]);
            int rem = s.length();
            for (int x : pints)
                rem -= x + 1;
            ++rem;
            if (rem < 0)
                continue;
            int slots = rem + pints.length;
            for (int i = 0; i < (1 << slots); ++i) {
                int ct = 0;
                for (int j = 0; j < slots; ++j) {
                    if ((i & (1 << j)) > 0)
                        ++ct;
                }
                if (ct != pints.length)
                    continue;
                String check = "";
                ct = 0;
                for (int j = 0; j < slots; ++j) {
                    if ((i & (1 << j)) > 0) {
                        if (ct > 0)
                            check += '.';
                        for (int k = 0; k < pints[ct]; ++k)
                            check += '#';
                        ++ct;
                    } else {
                        check += '.';
                    }
                }
                int ta = 1;
                for (int j = 0; j < s.length(); ++j) {
                    if (s.charAt(j) == '?' || s.charAt(j) == check.charAt(j))
                        continue;
                    ta = 0;
                }
                ans += ta;
            }
        }
        System.out.println(ans);
    }
}