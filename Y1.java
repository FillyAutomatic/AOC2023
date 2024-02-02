import java.io.*;
import java.util.*;
import java.math.*;

public class Y1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/y.in"))));

        int ans = 0;
        HashMap<String, HashSet<String>> g = new HashMap<>();
        ArrayList<String> us = new ArrayList<>();
        ArrayList<String> vs = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String u = s.substring(0, 3);
            s = s.substring(5);
            while (!s.isEmpty()) {
                String v = s.substring(0, 3);
                if (!g.containsKey(u))
                    g.put(u, new HashSet<>());
                if (!g.containsKey(v))
                    g.put(v, new HashSet<>());
                g.get(u).add(v);
                g.get(v).add(u);
                us.add(u);
                vs.add(v);
                s = s.length() == 3 ? "" : s.substring(4);
            }
        }
        Random r = new Random();
        while (ans == 0) {
            HashMap<String, String> which = new HashMap<>();
            HashMap<String, ArrayList<String>> ch = new HashMap<>();
            for (String s : g.keySet()) {
                which.put(s, s);
                ch.put(s, new ArrayList<>());
                ch.get(s).add(s);
            }
            for (int z = 0; z < g.size() - 2; ++z) {
                String u = "";
                String v = "";
                while (u.equals(v)) {
                    int e = r.nextInt(us.size());
                    u = which.get(us.get(e));
                    v = which.get(vs.get(e));
                }
                if (ch.get(v).size() > ch.get(u).size()) {
                    String t = u;
                    u = v;
                    v = t;
                }
                for (String v2 : ch.get(v)) {
                    which.put(v2, u);
                    ch.get(u).add(v2);
                }
                ch.remove(v);
            }
            int edges = 0;
            for (int i = 0; i < us.size(); ++i) {
                if (!which.get(us.get(i)).equals(which.get(vs.get(i))))
                    ++edges;
            }
            if (edges == 3) {
                ans = 1;
                for (String s : ch.keySet()) {
                    ans *= ch.get(s).size();
                }
            }
        }
        System.out.println(ans);
    }
}