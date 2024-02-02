import java.io.*;
import java.util.*;
import java.math.*;

public class V2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/v.in"))));

        int ans = 0;
        TreeMap<Integer, ArrayList<String>> tm = new TreeMap<>();
        ArrayList<Integer> gzs = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] spl = s.split("~");
            int x1 = Integer.parseInt(spl[0].split(",")[0]);
            int y1 = Integer.parseInt(spl[0].split(",")[1]);
            int z1 = Integer.parseInt(spl[0].split(",")[2]);
            int x2 = Integer.parseInt(spl[1].split(",")[0]);
            int y2 = Integer.parseInt(spl[1].split(",")[1]);
            int z2 = Integer.parseInt(spl[1].split(",")[2]);
            if (!tm.containsKey(z1))
                tm.put(z1, new ArrayList<>());
            tm.get(z1).add(s);
        }
        int curr = 0;
        int[][][] locs = new int[1000][10][10];
        for (int key : tm.keySet()) {
            for (String s : tm.get(key)) {
                ++curr;
                String[] spl = s.split("~");
                int x1 = Integer.parseInt(spl[0].split(",")[0]);
                int y1 = Integer.parseInt(spl[0].split(",")[1]);
                int z1 = Integer.parseInt(spl[0].split(",")[2]);
                int x2 = Integer.parseInt(spl[1].split(",")[0]);
                int y2 = Integer.parseInt(spl[1].split(",")[1]);
                int z2 = Integer.parseInt(spl[1].split(",")[2]);
                boolean bot = false;
                while (!bot) {
                    if (z1 == 0) {
                        bot = true;
                        break;
                    }
                    for (int i = y1; i <= y2; ++i) {
                        for (int j = x1; j <= x2; ++j) {
                            if (locs[z1 - 1][i][j] > 0) {
                                bot = true;
                            }
                        }
                    }
                    if (!bot) {
                        --z1;
                        --z2;
                    }
                }
                gzs.add(z1);
                for (int i = z1; i <= z2; ++i) {
                    for (int j = y1; j <= y2; ++j) {
                        for (int k = x1; k <= x2; ++k) {
                            locs[i][j][k] = curr;
                        }
                    }
                }
            }
        }
        for (int tt = 0; tt < gzs.size(); ++tt) {
            curr = 0;
            locs = new int[1000][10][10];
            for (int key : tm.keySet()) {
                for (String s : tm.get(key)) {
                    ++curr;
                    if (curr - 1 == tt)
                        continue;
                    String[] spl = s.split("~");
                    int x1 = Integer.parseInt(spl[0].split(",")[0]);
                    int y1 = Integer.parseInt(spl[0].split(",")[1]);
                    int z1 = Integer.parseInt(spl[0].split(",")[2]);
                    int x2 = Integer.parseInt(spl[1].split(",")[0]);
                    int y2 = Integer.parseInt(spl[1].split(",")[1]);
                    int z2 = Integer.parseInt(spl[1].split(",")[2]);
                    boolean bot = false;
                    while (!bot) {
                        if (z1 == 0) {
                            bot = true;
                            break;
                        }
                        for (int i = y1; i <= y2; ++i) {
                            for (int j = x1; j <= x2; ++j) {
                                if (locs[z1 - 1][i][j] > 0) {
                                    bot = true;
                                }
                            }
                        }
                        if (!bot) {
                            --z1;
                            --z2;
                        }
                    }
                    if (gzs.get(curr - 1) != z1)
                        ++ans;
                    for (int i = z1; i <= z2; ++i) {
                        for (int j = y1; j <= y2; ++j) {
                            for (int k = x1; k <= x2; ++k) {
                                locs[i][j][k] = curr;
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}