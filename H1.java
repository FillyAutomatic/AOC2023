import java.io.*;
import java.util.*;
import java.math.*;

public class H1 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/h.in"))));
		/**/
		
		int ans = 0;
		String seq = sc.nextLine();
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
		String curr = "AAA";
		while (!curr.equals("ZZZ")) {
			if (seq.charAt(ans%seq.length())=='L')
				curr = l.get(curr);
			else
				curr = r.get(curr);
			++ans;
		}
		System.out.println(ans);
	}
}