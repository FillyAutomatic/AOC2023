import java.io.*;
import java.util.*;
import java.math.*;

public class O1 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/o.in"))));
		/**/
		
		int ans = 0;
		for (String s : sc.nextLine().split(",")) {
			int ta = 0;
			for (char c : s.toCharArray()) {
				ta+=c;
				ta=ta*17%256;
			}
			ans+=ta;
		}
		System.out.println(ans);
	}
}