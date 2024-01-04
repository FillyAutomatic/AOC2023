import java.io.*;
import java.util.*;
import java.math.*;

public class B1 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/b.in"))));
		/**/
		
		int ans = 0;
		int num = 0;
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			++num;
			int r = 0;
			int g = 0;
			int b = 0;
			String[] words = s.split(" ");
			for (int i = 2; i < words.length; i+=2) {
				if (words[i+1].startsWith("red"))
					r = Math.max(r, Integer.parseInt(words[i]));
				if (words[i+1].startsWith("green"))
					g = Math.max(g, Integer.parseInt(words[i]));
				if (words[i+1].startsWith("blue"))
					b = Math.max(b, Integer.parseInt(words[i]));
			}
			if (r<=12&&g<=13&&b<=14)
				ans+=num;
		}
		System.out.println(ans);
	}
}