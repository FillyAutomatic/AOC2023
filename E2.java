import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.math.*;

public class E2 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/e.in"))));

        long ans = 123456789012345678L;
        ArrayList<Long> seeds = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.isEmpty()) {
                break;
            }
            String[] spl = s.split(" ");
            for (int i = 1; i < spl.length; ++i) {
                seeds.add(Long.parseLong(spl[i]));
            }
        }
        ArrayList<ArrayList<Long>> starts = new ArrayList<>();
        ArrayList<TreeMap<Long, Integer>> smap = new ArrayList<>();
        ArrayList<ArrayList<Long>> ends = new ArrayList<>();
        ArrayList<ArrayList<Long>> offsets = new ArrayList<>();
        for (int z = 0; z < 7; ++z) {
            sc.nextLine();
            starts.add(new ArrayList<>());
            ends.add(new ArrayList<>());
            offsets.add(new ArrayList<>());
            smap.add(new TreeMap<>());
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                if (s.isEmpty()) {
                    break;
                }
                long start = Long.parseLong(s.split(" ")[1]);
                long end = start + Long.parseLong(s.split(" ")[2]);
                long offset = Long.parseLong(s.split(" ")[0]) - start;
                starts.get(z).add(start);
                ends.get(z).add(end);
                offsets.get(z).add(offset);
                smap.get(z).put(start, starts.get(z).size() - 1);
            }
        }
        for (int i = 0; i < seeds.size(); i += 2) {
            System.out.println(seeds.get(i + 1));
            for (long seed = seeds.get(i); seed < seeds.get(i) + seeds.get(i + 1); ++seed) {
                if (seed % 10000000 == 0)
                    System.out.println("yeah");
                long prev = seed;
                long curr = seed;
                for (int z = 0; z < 7; ++z) {
                    prev = curr;
                    if (smap.get(z).floorEntry(prev) == null)
                        continue;
                    int j = smap.get(z).floorEntry(prev).getValue();
                    if (prev >= starts.get(z).get(j) && prev < ends.get(z).get(j)) {
                        curr = prev + offsets.get(z).get(j);
                    }
                }
                ans = Math.min(ans, curr);
            }
        }
        System.out.println(ans);
    }
}