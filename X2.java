import java.io.*;
import java.util.*;
import java.math.*;

public class X2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/x.in"))));
		/**/
		
		long ans = 0;
		ArrayList<Long> xs = new ArrayList<>();
		ArrayList<Long> ys = new ArrayList<>();
		ArrayList<Long> zs = new ArrayList<>();
		ArrayList<Long> vxs = new ArrayList<>();
		ArrayList<Long> vys = new ArrayList<>();
		ArrayList<Long> vzs = new ArrayList<>();
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			xs.add(Long.parseLong(s.substring(0,s.indexOf(','))));
			s = s.substring(s.indexOf(',')+2);
			ys.add(Long.parseLong(s.substring(0,s.indexOf(','))));
			s = s.substring(s.indexOf(',')+2);
			zs.add(Long.parseLong(s.substring(0,s.indexOf(' '))));
			s = s.substring(s.indexOf('@')+2);
			vxs.add(Long.parseLong(s.substring(0,s.indexOf(','))));
			s = s.substring(s.indexOf(',')+2);
			vys.add(Long.parseLong(s.substring(0,s.indexOf(','))));
			s = s.substring(s.indexOf(',')+2);
			vzs.add(Long.parseLong(s));
		}
		int n = xs.size();
		HashSet<Integer> possx = new HashSet<>();
		HashSet<Integer> possy = new HashSet<>();
		HashSet<Integer> possz = new HashSet<>();
		for (int i = 0; i < 1000; ++i) {
			possx.add(i);
			possx.add(-i);
			possy.add(i);
			possy.add(-i);
			possz.add(i);
			possz.add(-i);
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (vxs.get(i)<=vxs.get(j)&&xs.get(i)<xs.get(j)) {
					for (int k = (int)(0+vxs.get(i)); k <= vxs.get(j); ++k) {
						possx.remove(k);
					}
				}
				if (vys.get(i)<=vys.get(j)&&ys.get(i)<ys.get(j)) {
					for (int k = (int)(0+vys.get(i)); k <= vys.get(j); ++k) {
						possy.remove(k);
					}
				}
				if (vzs.get(i)<=vzs.get(j)&&zs.get(i)<zs.get(j)) {
					for (int k = (int)(0+vzs.get(i)); k <= vzs.get(j); ++k) {
						possz.remove(k);
					}
				}
			}
		}
		for (int x = 0; x <= 300; ++x) {
			for (int xm = -1; xm<=1; xm+=2) {
				if (possx.contains(x*xm))
					System.out.println(x*xm);
			}
		}
		// Used Wolfram Alpha to solve the equations
//		        i*a+x=-88*a+212542581053874,
//				i*b+x=184*b+154677220587564,
//				i*c+x=109*c+216869547613134,
//
//				j*a+y=-256*a+357959731032403,
//				j*b+y=74*b+207254130208265,
//				j*c+y=262*c+38208083662943,
//		
//				k*a+z=-240*a+176793474286781,
//				k*b+z=235*b+139183938188421,
//				k*c+z=-66*c+397740686492049,
		// given each of these x's, looking for integer values.
		// Solved for ys given vx, then zs given vx, because of char limit on Alpha.
		int vx = 139;
		int vy = -93;
		int vz = 245;
		long x = 191146615936494L;
		long y = 342596108503183L;
		long z = 131079628110881L;
		ans = x+y+z;
		System.out.println(ans);
	}
}