import java.io.*;
import java.util.*;
import java.math.*;

public class T2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/t.in"))));
		/**/

		HashMap<String, String[]> hm = new HashMap<>();
		HashMap<String, Character> types = new HashMap<>();
		HashMap<String, Boolean> last = new HashMap<>();
		HashMap<String, Boolean> ff = new HashMap<>();
		HashMap<String, ArrayList<String>> inputs = new HashMap<>();
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			String key = s.split(" ")[0];
			if (s.startsWith("%")||s.startsWith("&"))
				key = key.substring(1);
			hm.put(key, s.split("> ")[1].split(", "));
			last.put(key, false);
			ff.put(key, false);
			inputs.put(key, new ArrayList<>());
			if (s.startsWith("%")||s.startsWith("&"))
				types.put(key, s.charAt(0));
		}
		for (String s : hm.keySet()) {
			for (String s2 : hm.get(s)) {
				if (inputs.containsKey(s2))
					inputs.get(s2).add(s);
			}
		}
		ArrayDeque<String> q = new ArrayDeque<>();
		for (int z = 0; z < 100000000; ++z) {
			if (z%1000000==0)
				System.out.println(z+1);
			for (String s : hm.get("broadcaster"))
				q.add(s+" l");
			while (!q.isEmpty()) {
				String u = q.removeFirst();
				String s = u.split(" ")[0];
				//System.out.println(u+" "+q.size());
				boolean high = u.endsWith("h");
				if (last.get("mp")&&last.get("qt"))
					System.out.println("mpqt "+(z+1));
				if (last.get("qb")&&last.get("ng"))
					System.out.println("qbng "+(z+1));
				// multiply the above two numbers together for the answer
				if (!types.containsKey(s)) {
					if (!high) {
						System.out.println(z+1);
						return;
					}
					continue;
				}
				if (types.get(s)=='%'&&!high) {
					ff.put(s, !ff.get(s));
					last.put(s,ff.get(s));
					for (String s2 : hm.get(s)) {
						//System.out.println(s+" "+s2+" "+ff.get(s));
						q.add(s2+" "+(ff.get(s)?"h":"l"));
					}
				} else if (types.get(s)=='&') {
					boolean send = false;
					for (String inp : inputs.get(s)) {
						send |= !last.get(inp);
					}
					last.put(s,send);
					for (String s2 : hm.get(s)) {
						//System.out.println(s+" "+s2+" "+send);
						q.add(s2+" "+(send?"h":"l"));
					}
				}
			}
		}
	}
}