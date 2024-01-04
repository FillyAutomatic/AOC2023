import java.io.*;
import java.util.*;
import java.math.*;

public class C2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/c.in"))));
		/**/
		
		int ans = 0;
		ArrayList<String> lines = new ArrayList<>();
		while (sc.hasNextLine()) {
			lines.add(sc.next());
		}
		int n = lines.size();
		int m = lines.get(0).length();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				char c = lines.get(i).charAt(j);
				if (c!='*')
					continue;
				HashSet<Integer> hs = new HashSet<>();
				for (int a = i-1; a <= i+1; ++a) {
					for (int b = j-1; b <= j+1; ++b) {
						if (a<0||a>=n||b<0||b>=m)
							continue;
						char c2 = lines.get(a).charAt(b);
						if (c2<'0'||c2>'9')
							continue;
						int k = b;
						while (k>0) {
							char c3 = lines.get(a).charAt(k-1);
							if (c3<'0'||c3>'9')
								break;
							--k;
						}
						int ta = 0;
						while (k < m) {
							char c3 = lines.get(a).charAt(k);
							if (c3<'0'||c3>'9')
								break;
							ta *= 10;
							ta += c3-'0';
							++k;
						}
						hs.add(ta);
					}
				}
				if (hs.size()==2) {
					int ta = 1;
					for (int x : hs)
						ta *= x;
					ans += ta;
				}
			}
		}
		System.out.println(ans);
	}
}