import java.io.*;
import java.util.*;
import java.math.*;

public class F1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/f.in"))));

        long ans = 1;
        ArrayList<Integer> ts = new ArrayList<>();
        ArrayList<Integer> ds = new ArrayList<>();
        String s = sc.nextLine();
        String[] sp = s.split(" ");
        for (String s2 : sp) {
            try {
                ts.add(Integer.parseInt(s2));
            } catch (Exception e) {
                continue;
            }
        }
        s = sc.nextLine();
        sp = s.split(" ");
        for (String s2 : sp) {
            try {
                ds.add(Integer.parseInt(s2));
            } catch (Exception e) {
                continue;
            }
        }
        for (int i = 0; i < ts.size(); ++i) {
            int tm = 0;
            int t = ts.get(i);
            int d = ds.get(i);
            for (int j = 0; j <= t; ++j) {
                if (j * (t - j) > d)
                    ++tm;
            }
            ans *= tm;
        }
        System.out.println(ans);
    }
}