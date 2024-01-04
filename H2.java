import java.io.*;
import java.util.*;
import java.math.*;

public class H2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/h.in"))));
		/**/
		
		long ans = 0;
		String seq = sc.nextLine();
		seq += seq;
		seq += seq;
		seq += seq;
		seq += seq;
		seq += seq;
		seq += seq;
		seq += seq;
		seq += seq;
		seq += seq;
		seq += seq;
		seq += seq;
		sc.nextLine();
		HashMap<String, String> l = new HashMap<>();
		HashMap<String, String> r = new HashMap<>();
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			String key = s.substring(0,3);
			String ls = s.substring(7,10);
			String rs = s.substring(12,15);
			l.put(key, ls);
			r.put(key, rs);
		}
		HashMap<String, HashSet<Integer>> nums = new HashMap<>();
		HashMap<String, String> fulls = new HashMap<>();
		for (String s : l.keySet()) {
			nums.put(s, new HashSet<>());
			String s2 = s;
			for (int i = 0; i < seq.length(); ++i) {
				s2 = seq.charAt(i)=='L'?l.get(s2):r.get(s2);
				if (s2.endsWith("Z"))
					nums.get(s).add(i);
			}
			fulls.put(s, s2);
		}
		HashSet<String> currs = new HashSet<>();
		for (String s : l.keySet()) {
			if (s.endsWith("A"))
				currs.add(s);
		}
		while (true) {
			HashSet<String> nc = new HashSet<>();
			HashSet<Integer> poss = null;
			int ct = 0;
			for (String curr : currs) {
				if (poss==null) {
					poss = (HashSet<Integer>)nums.get(curr).clone();
				} else {
					poss.retainAll(nums.get(curr));
				}
				++ct;
				if (ct>=5&&!poss.isEmpty())
					System.out.println(ct+" "+ans);
				nc.add(fulls.get(curr));
			}
			if (poss.size()>0) {
				long aa = ans+seq.length();
				for (int x : poss)
					aa = Math.min(aa, ans+x+1);
				System.out.println(aa);
				return;
			}
			currs = nc;
			ans += seq.length();
		}
	}
}