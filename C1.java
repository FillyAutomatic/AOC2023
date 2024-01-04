import java.io.*;
import java.util.*;
import java.math.*;

public class C1 {
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
		boolean[][] should = new boolean[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				char c = lines.get(i).charAt(j);
				if (c<'0'||c>'9')
					continue;
				for (int a = i-1; a <= i+1; ++a) {
					for (int b = j-1; b <= j+1; ++b) {
						if (a<0||a>=n||b<0||b>=m)
							continue;
						char c2 = lines.get(a).charAt(b);
						if (c2=='.'||(c2>='0'&&c2<='9'))
							continue;
						should[i][j] = true;
					}
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				char c = lines.get(i).charAt(j);
				if (c<'0'||c>'9')
					continue;
				if (j>0) {
					char prev = lines.get(i).charAt(j-1);
					if (prev>='0'&&prev<='9')
						continue;
				}
				int mul = 0;
				int ta = 0;
				for (int k = j; k < m; ++k) {
					char c2 = lines.get(i).charAt(k);
					if (c2<'0'||c2>'9')
						break;
					ta *= 10;
					ta += c2-'0';
					if (should[i][k])
						mul = 1;
				}
				ans += mul*ta;
			}
		}
		System.out.println(ans);
	}
}