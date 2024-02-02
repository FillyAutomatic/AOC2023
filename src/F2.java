import java.io.*;
import java.util.*;
import java.math.*;

public class F2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/f.in"))));

        long ans = 0;
        String tt = "";
        String dd = "";
        ArrayList<Integer> ts = new ArrayList<>();
        ArrayList<Integer> ds = new ArrayList<>();
        String s = sc.nextLine();
        String[] sp = s.split(" ");
        for (String s2 : sp) {
            try {
                ts.add(Integer.parseInt(s2));
                tt += s2;
            } catch (Exception e) {
                continue;
            }
        }
        s = sc.nextLine();
        sp = s.split(" ");
        for (String s2 : sp) {
            try {
                ds.add(Integer.parseInt(s2));
                dd += s2;
            } catch (Exception e) {
                continue;
            }
        }
        long t = Long.parseLong(tt);
        long d = Long.parseLong(dd);
        for (long j = 0; j <= t; ++j) {
            if (j * (t - j) > d)
                ++ans;
        }
        System.out.println(ans);
    }
}