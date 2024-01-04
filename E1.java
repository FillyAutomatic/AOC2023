import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.math.*;

public class E1 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/e.in"))));
		/**/
		
		long ans = 0;
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
		for (int z = 0; z < 7; ++z) {
			sc.nextLine();
			ArrayList<String> lines = new ArrayList<>();
			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				if (s.isEmpty()) {
					break;
				}
				lines.add(s);
			}
			ArrayList<Long> ns = new ArrayList<>();
			for (long seed : seeds) {
				long conv = seed;
				for (String s : lines) {
					long start = Long.parseLong(s.split(" ")[1]);
					long end = start+Long.parseLong(s.split(" ")[2]);
					long offset = Long.parseLong(s.split(" ")[0])-start;
					if (seed>=start&&seed<end)
						conv = seed+offset;
				}
				ns.add(conv);
			}
			seeds = ns;
		}
		ans = seeds.stream().sorted().collect(Collectors.toList()).get(0);
		System.out.println(ans);
	}
}