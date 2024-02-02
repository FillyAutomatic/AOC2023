import java.io.*;
import java.util.*;
import java.math.*;

public class X1 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/x.in"))));

        int ans = 0;
        ArrayList<Long> xs = new ArrayList<>();
        ArrayList<Long> ys = new ArrayList<>();
        ArrayList<Long> zs = new ArrayList<>();
        ArrayList<Long> vxs = new ArrayList<>();
        ArrayList<Long> vys = new ArrayList<>();
        ArrayList<Long> vzs = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            xs.add(Long.parseLong(s.substring(0, s.indexOf(','))));
            s = s.substring(s.indexOf(',') + 2);
            ys.add(Long.parseLong(s.substring(0, s.indexOf(','))));
            s = s.substring(s.indexOf(',') + 2);
            zs.add(Long.parseLong(s.substring(0, s.indexOf(' '))));
            s = s.substring(s.indexOf('@') + 2);
            vxs.add(Long.parseLong(s.substring(0, s.indexOf(','))));
            s = s.substring(s.indexOf(',') + 2);
            vys.add(Long.parseLong(s.substring(0, s.indexOf(','))));
            s = s.substring(s.indexOf(',') + 2);
            vzs.add(Long.parseLong(s));
        }
        long testmin = 200000000000000L;
        long testmax = 400000000000000L;
        int n = xs.size();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                double x1 = xs.get(i);
                double y1 = ys.get(i);
                double vx1 = vxs.get(i);
                double vy1 = vys.get(i);
                double x2 = xs.get(j);
                double y2 = ys.get(j);
                double vx2 = vxs.get(j);
                double vy2 = vys.get(j);
                double t1xmin = (testmin - x1) / vx1;
                double t1xmax = (testmax - x1) / vx1;
                double y1xmin = y1 + vy1 * t1xmin;
                double y1xmax = y1 + vy1 * t1xmax;
                double t2xmin = (testmin - x2) / vx2;
                double t2xmax = (testmax - x2) / vx2;
                double y2xmin = y2 + vy2 * t2xmin;
                double y2xmax = y2 + vy2 * t2xmax;
                if (Math.signum(y1xmin - y2xmin) == Math.signum(y1xmax - y2xmax))
                    continue;
                double t1ymin = (testmin - y1) / vy1;
                double t1ymax = (testmax - y1) / vy1;
                double x1ymin = x1 + vx1 * t1ymin;
                double x1ymax = x1 + vx1 * t1ymax;
                double t2ymin = (testmin - y2) / vy2;
                double t2ymax = (testmax - y2) / vy2;
                double x2ymin = x2 + vx2 * t2ymin;
                double x2ymax = x2 + vx2 * t2ymax;
                if (Math.signum(x1ymin - x2ymin) == Math.signum(x1ymax - x2ymax))
                    continue;
                double xmin = testmin;
                double xmax = testmax;
                while (xmin + Math.ulp(xmin) < xmax) {
                    double xmid = (xmin + xmax) / 2;
                    double t1xmid = (xmid - x1) / vx1;
                    double y1xmid = y1 + vy1 * t1xmid;
                    double t2xmid = (xmid - x2) / vx2;
                    double y2xmid = y2 + vy2 * t2xmid;
                    if (Math.signum(y1xmin - y2xmin) == Math.signum(y1xmid - y2xmid)) {
                        xmin = xmid;
                    } else {
                        xmax = xmid;
                    }
                }
                double t1ix = (xmin - x1) / vx1;
                double t2ix = (xmin - x2) / vx2;
                if (t1ix < 0 || t2ix < 0)
                    continue;
                System.out.println(i + " " + j);
                ++ans;
            }
        }
        System.out.println(ans);
    }
}