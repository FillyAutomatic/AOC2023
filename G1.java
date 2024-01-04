import java.io.*;
import java.util.*;
import java.math.*;

public class G1 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/g.in"))));
		/**/
		
		int ans = 0;
		HashMap<String, Integer> hm = new HashMap<>();
		while (sc.hasNext()) {
			String hand = sc.next();
			int bid = sc.nextInt();
			hm.put(hand, bid);
		}
		String[] hands = new String[hm.size()];
		int ind = 0;
		for (String s : hm.keySet())
			hands[ind++] = s;
		Arrays.sort(hands, (String a, String b) -> {
			a = a.replaceAll("T","a").replaceAll("J","b").replaceAll("Q","c").replaceAll("K","d").replaceAll("A","e");
			b = b.replaceAll("T","a").replaceAll("J","b").replaceAll("Q","c").replaceAll("K","d").replaceAll("A","e");
			char[] ac = a.toCharArray();
			char[] bc = b.toCharArray();
			Arrays.sort(ac);
			Arrays.sort(bc);
			int at = 0;
			int bt = 0;
			HashSet<Character> acs = new HashSet<>();
			HashSet<Character> bcs = new HashSet<>();
			for (char c : ac)
				acs.add(c);
			for (char c : bc)
				bcs.add(c);
			if (ac[0]==ac[4])
				at = 0;
			else if (ac[0]==ac[3]||ac[1]==ac[4])
				at = 1;
			else if (ac[0]==ac[1]&&ac[3]==ac[4]&&(ac[2]==ac[0]||ac[2]==ac[4]))
				at = 2;
			else if (ac[2]==ac[0]||ac[1]==ac[3]||ac[2]==ac[4])
				at = 3;
			else if (acs.size()==3)
				at = 4;
			else if (acs.size()==4)
				at = 5;
			else
				at = 6;
			if (bc[0]==bc[4])
				bt = 0;
			else if (bc[0]==bc[3]||bc[1]==bc[4])
				bt = 1;
			else if (bc[0]==bc[1]&&bc[3]==bc[4]&&(bc[2]==bc[0]||bc[2]==bc[4]))
				bt = 2;
			else if (bc[2]==bc[0]||bc[1]==bc[3]||bc[2]==bc[4])
				bt = 3;
			else if (bcs.size()==3)
				bt = 4;
			else if (bcs.size()==4)
				bt = 5;
			else
				bt = 6;
			return at==bt?-a.compareTo(b):at-bt;
		});
		for (int i = 0; i < hands.length; ++i)
			ans += (hands.length-i)*hm.get(hands[i]);
		System.out.println(ans);
	}
}