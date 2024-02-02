import java.io.*;
import java.util.*;
import java.math.*;

public class A2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/a.in"))));

        int ans = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine() + "     ";
            int first = -1;
            int last = -1;
            for (int i = 0; i < s.length() - 5; ++i) {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9') {
                    if (first < 0)
                        first = c - '0';
                    last = c - '0';
                }
                int dig = -1;
                if (s.substring(i).startsWith("one")) {
                    dig = 1;
                }
                if (s.substring(i).startsWith("two")) {
                    dig = 2;
                }
                if (s.substring(i).startsWith("three")) {
                    dig = 3;
                }
                if (s.substring(i).startsWith("four")) {
                    dig = 4;
                }
                if (s.substring(i).startsWith("five")) {
                    dig = 5;
                }
                if (s.substring(i).startsWith("six")) {
                    dig = 6;
                }
                if (s.substring(i).startsWith("seven")) {
                    dig = 7;
                }
                if (s.substring(i).startsWith("eight")) {
                    dig = 8;
                }
                if (s.substring(i).startsWith("nine")) {
                    dig = 9;
                }
                if (dig >= 0) {
                    if (first < 0)
                        first = dig;
                    last = dig;
                }
            }
            ans += first * 10 + last;
        }
        System.out.println(ans);
    }
}