import java.io.*;
import java.util.*;
import java.math.*;

public class N2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/n.in"))));
		/**/
		
		int ans = 0;
		ArrayList<String> lines = new ArrayList<>();
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.isEmpty())
				break;
			lines.add(s);
		}
		int n = lines.size();
		int m = lines.get(0).length();
		char[][] grid = new char[n][m];
		for (int i = 0; i < n; ++i) {
			grid[i] = lines.get(i).toCharArray();
		}
		HashMap<String, Integer> strings = new HashMap<>();
		int count = 0;
		String end = "";
		while (true) {
			String curr = "";
			++count;
			for (int i = 1; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					char c = grid[i][j];
					if (c=='O') {
						grid[i][j] = '.';
						int k = i;
						while (k>0&&grid[k-1][j]=='.')
							--k;
						grid[k][j] = 'O';
					}
				}
			}
			for (int i = 0; i < n; ++i) {
				for (int j = 1; j < m; ++j) {
					char c = grid[i][j];
					if (c=='O') {
						grid[i][j] = '.';
						int k = j;
						while (k>0&&grid[i][k-1]=='.')
							--k;
						grid[i][k] = 'O';
					}
				}
			}
			
			for (int i = n-2; i >= 0; --i) {
				for (int j = 0; j < m; ++j) {
					char c = grid[i][j];
					if (c=='O') {
						grid[i][j] = '.';
						int k = i;
						while (k<n-1&&grid[k+1][j]=='.')
							++k;
						grid[k][j] = 'O';
					}
				}
			}
			for (int i = 0; i < n; ++i) {
				for (int j = m-2; j >= 0; --j) {
					char c = grid[i][j];
					if (c=='O') {
						grid[i][j] = '.';
						int k = j;
						while (k<n-1&&grid[i][k+1]=='.')
							++k;
						grid[i][k] = 'O';
					}
				}
			}
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					curr+=grid[i][j];
				}
				//curr+='\n';
			}
			if (strings.containsKey(curr)) {
				int cycle = count-strings.get(curr);
				int key = strings.size()-cycle+(1000000000-strings.size())%cycle;
				for (String x : strings.keySet()) {
					if (strings.get(x)==key)
						end = x;
				}
				break;
			}
			strings.put(curr,count);
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (end.charAt(i*m+j)=='O')
					ans += n-i;
			}
		}
		System.out.println(ans);
	}
}