import java.io.*;
import java.util.*;
import java.math.*;

public class O2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/o.in"))));
		/**/
		
		long ans = 0;
		ArrayList<ArrayList<String>> lenses = new ArrayList<>();
		HashMap<String, Integer> lengths = new HashMap<>(); 
		for (int i = 0; i < 256; ++i) {
			lenses.add(new ArrayList<>());
		}
		for (String s : sc.nextLine().split(",")) {
			String ss = "";
			int ta = 0;
			for (char c : s.toCharArray()) {
				if (c=='-') {
					lenses.get(ta).remove(ss);
				} else if (c=='=') {
					continue;
				} else if (c>='0'&&c<='9') {
					int len = c-'0';
					if (!lenses.get(ta).contains(ss))
						lenses.get(ta).add(ss);
					lengths.put(ss, len);
				} else {
					ta+=c;
					ta=ta*17%256;
					ss+=c;
				}
			}
		}
		for (int i = 0; i < 256; ++i) {
			for (int j = 0; j < lenses.get(i).size(); ++j) {
				int len = lengths.get(lenses.get(i).get(j));
				ans += (i+1)*(j+1)*len;
			}
		}
		System.out.println(ans);
	}
}