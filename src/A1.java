import java.io.*;
import java.util.*;
import java.math.*;

public class A1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/a.in"))));

        int ans = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            int first = -1;
            int last = -1;
            for (char c : s.toCharArray()) {
                if (c >= '0' && c <= '9') {
                    if (first < 0)
                        first = c - '0';
                    last = c - '0';
                }
            }
            ans += first * 10 + last;
        }
        System.out.println(ans);
    }
}