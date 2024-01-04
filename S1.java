import java.io.*;
import java.util.*;
import java.math.*;

public class S1 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/s.in"))));
		/**/
		
		int ans = 0;
		HashMap<String,String> wf = new HashMap<>();
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.isEmpty())
				break;
			String key = s.split("\\{")[0];
			wf.put(key, s.split("\\{")[1].split("\\}")[0]);
		}
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			s = s.substring(1,s.length()-1);
			String[] split = s.split(",");
			int nx = Integer.parseInt(split[0].substring(2));
			int nm = Integer.parseInt(split[1].substring(2));
			int na = Integer.parseInt(split[2].substring(2));
			int ns = Integer.parseInt(split[3].substring(2));
			if (acc(wf.get("in"),nx,nm,na,ns,wf)) {
				ans+=nx+nm+na+ns;
				//System.out.println(nx);
			}
		}
		System.out.println(ans);
	}
	
	public static boolean acc(String s, int nx, int nm, int na, int ns, HashMap<String,String> wf) {
		String eq = s.split("\\:")[0];
		//System.out.println(s);
		s = s.substring(s.indexOf(":")+1);
		String left = s.split(",")[0];
		String right = s.substring(s.indexOf(",")+1);
		char eql = eq.charAt(0);
		int eqr = Integer.parseInt(eq.substring(2));
		//System.out.println(eql+" "+eqr);
		boolean useLeft = false;
		//System.out.println(eq+" "+ns);
		if (eq.contains(">")) {
			if (eql=='x') {
				useLeft = nx>eqr;
			}
			if (eql=='m') {
				useLeft = nm>eqr;
			}
			if (eql=='a') {
				useLeft = na>eqr;
			}
			if (eql=='s') {
				useLeft = ns>eqr;
			}
		} else {
			if (eql=='x') {
				useLeft = nx<eqr;
			}
			if (eql=='m') {
				useLeft = nm<eqr;
			}
			if (eql=='a') {
				useLeft = na<eqr;
			}
			if (eql=='s') {
				useLeft = ns<eqr;
			}
		}
		//System.out.println(useLeft);
		String next = useLeft?left:right;
		if (next.equals("A"))
			return true;
		if (next.equals("R"))
			return false;
		if (next.contains(":"))
			return acc(next,nx,nm,na,ns,wf);
		return acc(wf.get(next),nx,nm,na,ns,wf);
	}
}