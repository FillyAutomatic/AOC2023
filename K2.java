import java.io.*;
import java.util.*;
import java.math.*;

public class K2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/k.in"))));
		/**/
		
		long ans = 0;
		ArrayList<String> lines = new ArrayList<>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		int n = lines.size();
		int m = lines.get(0).length();
		int[] rct = new int[n];
		int[] cct = new int[m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (lines.get(i).charAt(j)=='#') {
					++rct[i];
					++cct[j];
				}
			}
		}
		int ADD = 1000000;
		int[] rs = new int[n];
		int[] cs = new int[n];
		for (int i = 1; i < n; ++i) {
			rs[i]=rs[i-1]+1;
			if (rct[i-1]==0)
				rs[i]+=ADD-1;
		}
		for (int i = 1; i < m; ++i) {
			cs[i]=cs[i-1]+1;
			if (cct[i-1]==0)
				cs[i]+=ADD-1;
		}
		long nn = rs[n-1]+(rct[n-1]==0?ADD:1);
		long mm = cs[m-1]+(cct[m-1]==0?ADD:1);
		ArrayList<Long> al = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (lines.get(i).charAt(j)=='#') {
					al.add(rs[i]*mm+cs[j]);
				}
			}
		}
		for (int i = 0; i < al.size(); ++i) {
			long iy = al.get(i)/mm;
			long ix = al.get(i)%mm;
			for (int j = i+1; j < al.size(); ++j) {
				long jy = al.get(j)/mm;
				long jx = al.get(j)%mm;
				//System.out.println(iy+" "+ix+" "+jy+" "+jx+" "+ans+" "+(Math.abs(iy-jy)+Math.abs(ix-jx)));
				ans += Math.abs(iy-jy)+Math.abs(ix-jx);
			}
		}
		System.out.println(ans);
	}
}