import java.io.*;
import java.util.*;
import java.math.*;

public class L2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/l.in"))));
		/**/
		
		long ans = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String s = line.split(" ")[0];
			s += "?"+s+"?"+s+"?"+s+"?"+s;
			String unparts = line.split(" ")[1];
			unparts += ","+unparts+","+unparts+","+unparts+","+unparts;
			//System.out.println(s+" "+unparts);
			String[] parts = unparts.split(",");
			int[] pints = new int[parts.length];
			for (int i = 0; i < parts.length; ++i)
				pints[i] = Integer.parseInt(parts[i]);
			int k = pints.length;
			int n = s.length();
			long[][] dp = new long[k+1][n+100];
			dp[0][0] = 1;
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j <= k; ++j) {
					if (s.charAt(i)!='#')
						dp[j][i+1]+=dp[j][i];
					if (j==0)
						continue;
					boolean poss = true;
					for (int a = i; a > i-pints[j-1]; --a) {
						if (a<0) {
							poss = false;
							break;
						}
						if (s.charAt(a)=='.')
							poss = false;
					}
					if (j>1) {
						if (i-pints[j-1]<0) {
							poss = false;
							continue;
						}
						if (s.charAt(i-pints[j-1])=='#')
							poss = false;
						if (poss) {
							dp[j][i+1]+=dp[j-1][i-pints[j-1]];
						}
					} else {
						if (poss) {
							dp[j][i+1]+=dp[j-1][i-pints[j-1]+1];
						}
					}
				}
			}
			System.out.println(dp[k][n]);
			ans += dp[k][n];
		}
		System.out.println(ans);
	}
}