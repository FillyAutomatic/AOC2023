import java.io.*;
import java.util.*;
import java.math.*;

public class V1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/v.in"))));

        int ans = 0;
        TreeMap<Integer, ArrayList<String>> tm = new TreeMap<>();
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
        HashSet<Integer> cant = new HashSet<>();
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
                HashSet<Integer> below = new HashSet<>();
                while (!bot) {
                    if (z1 == 0) {
                        bot = true;
                        break;
                    }
                    for (int i = y1; i <= y2; ++i) {
                        for (int j = x1; j <= x2; ++j) {
                            if (locs[z1 - 1][i][j] > 0) {
                                bot = true;
                                below.add(locs[z1 - 1][i][j]);
                            }
                        }
                    }
                    if (!bot) {
                        --z1;
                        --z2;
                    }
                }
                for (int i = z1; i <= z2; ++i) {
                    for (int j = y1; j <= y2; ++j) {
                        for (int k = x1; k <= x2; ++k) {
                            locs[i][j][k] = curr;
                        }
                    }
                }
                if (below.size() == 1) {
                    cant.add(below.iterator().next());
                }
            }
        }
        System.out.println(curr - cant.size());
    }
}