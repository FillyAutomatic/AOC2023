import java.io.*;
import java.util.*;
import java.math.*;

public class D2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/d.in"))));
		/**/
		
		long ans = 0;
		HashMap<Integer, Long> extras = new HashMap<>();
		int num = 0;
		while (sc.hasNextLine()) {
			++num;
			long ct = extras.getOrDefault(num, 0L)+1;
			ans += ct;
			String s = sc.nextLine();
			String[] spl = s.split(" ");
			HashSet<Integer> win = new HashSet<>();
			boolean div = false;
			int next = num+1;
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
					extras.put(next, extras.getOrDefault(next, 0L)+ct);
					++next;
				}
			}
		}
		System.out.println(ans);
	}
}