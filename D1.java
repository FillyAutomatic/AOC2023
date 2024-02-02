import java.io.*;
import java.util.*;
import java.math.*;

public class D1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/d.in"))));

        int ans = 0;
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] spl = s.split(" ");
            HashSet<Integer> win = new HashSet<>();
            boolean div = false;
            int score = 0;
            for (int i = 0; i < spl.length; ++i) {
                if (spl[i].contains("|")) {
                    div = true;
                    continue;
                }
                try {
                    Integer.parseInt(spl[i]);
                } catch (Exception e) {
                    continue;
                }
                if (!div) {
                    win.add(Integer.parseInt(spl[i]));
                    continue;
                }
                if (win.contains(Integer.parseInt(spl[i]))) {
                    score = score == 0 ? 1 : score * 2;
                }
            }
            ans += score;
        }
        System.out.println(ans);
    }
}