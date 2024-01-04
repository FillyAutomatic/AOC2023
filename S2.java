import java.io.*;
import java.util.*;
import java.math.*;

public class S2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/s.in"))));
		/**/
		
		long ans = 0;
		HashMap<String,String> wf = new HashMap<>();
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.isEmpty())
				break;
			String key = s.split("\\{")[0];
			s = s.split("\\{")[1].split("\\}")[0];
			wf.put(key, s);
		}
		ans = acc(wf.get("in"),1,4000,1,4000,1,4000,1,4000,wf);
		System.out.println(ans);
	}
	
	public static long acc(String s, int nxa, int nxb, int nma, int nmb, int naa, int nab, int nsa, int nsb, HashMap<String,String> wf) {
		//System.out.println(s+" "+nxa+":"+nxb+" "+nma+":"+nmb+" "+naa+":"+nab+" "+nsa+":"+nsb);
		if (nxa>nxb||nma>nmb||naa>nab||nsa>nsb)
			return 0;
		if (s.equals("R"))
			return 0;
		if (s.equals("A")) {
			long ans = nxb-nxa+1;
			ans *= nmb-nma+1;
			ans *= nab-naa+1;
			ans *= nsb-nsa+1;
			return ans;
		}
		String eq = s.split("\\:")[0];
		s = s.substring(s.indexOf(":")+1);
		String left = s.split(",")[0];
		String right = s.substring(s.indexOf(",")+1);
		char eql = eq.charAt(0);
		int eqr = Integer.parseInt(eq.substring(2));
		
		int lnxa = nxa;
		int lnxb = nxb;
		int lnma = nma;
		int lnmb = nmb;
		int lnaa = naa;
		int lnab = nab;
		int lnsa = nsa;
		int lnsb = nsb;
		int rnxa = nxa;
		int rnxb = nxb;
		int rnma = nma;
		int rnmb = nmb;
		int rnaa = naa;
		int rnab = nab;
		int rnsa = nsa;
		int rnsb = nsb;
		
		if (eq.contains(">")) {
			if (eql=='x') {
				rnxb = eqr;
				lnxa = eqr+1;
			}
			if (eql=='m') {
				rnmb = eqr;
				lnma = eqr+1;
			}
			if (eql=='a') {
				rnab = eqr;
				lnaa = eqr+1;
			}
			if (eql=='s') {
				rnsb = eqr;
				lnsa = eqr+1;
			}
		} else {
			if (eql=='x') {
				lnxb = eqr-1;
				rnxa = eqr;
			}
			if (eql=='m') {
				lnmb = eqr-1;
				rnma = eqr;
			}
			if (eql=='a') {
				lnab = eqr-1;
				rnaa = eqr;
			}
			if (eql=='s') {
				lnsb = eqr-1;
				rnsa = eqr;
			}
		}
		
		long ans = 0;
		if (left.contains(":")||left.equals("A")||left.equals("R"))
			ans += acc(left,lnxa,lnxb,lnma,lnmb,lnaa,lnab,lnsa,lnsb,wf);
		else
			ans += acc(wf.get(left),lnxa,lnxb,lnma,lnmb,lnaa,lnab,lnsa,lnsb,wf);
		if (right.contains(":")||right.equals("A")||right.equals("R"))
			ans += acc(right,rnxa,rnxb,rnma,rnmb,rnaa,rnab,rnsa,rnsb,wf);
		else
			ans += acc(wf.get(right),rnxa,rnxb,rnma,rnmb,rnaa,rnab,rnsa,rnsb,wf);
		return ans;
	}
}