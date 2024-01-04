import java.io.*;
import java.util.*;
import java.math.*;

public class R2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/r.in"))));
		/**/
		
		long ans = 0;
		int cy = 0;
		int cx = 0;
		TreeSet<Integer> xs = new TreeSet<>();
		TreeSet<Integer> ys = new TreeSet<>();
		xs.add(0);
		ys.add(0);
		ArrayList<String> lines = new ArrayList<>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		for (String s : lines) {
			s = s.split(" ")[2].substring(2,8);
			char dir = s.charAt(5);
			int dist = Integer.parseInt(s.substring(0,5),16);
			int dy = 0;
			int dx = 0;
			if (dir=='0')
				dx=1;
			if (dir=='2')
				dx=-1;
			if (dir=='1')
				dy=1;
			if (dir=='3')
				dy=-1;
			cy+=dy*dist;
			cx+=dx*dist;
			xs.add(cx);
			ys.add(cy);
		}
		int[] xcs = new int[2000];
		int[] ycs = new int[2000];
		int cyi = 1000;
		int cxi = 1000;
		cy = 0;
		cx = 0;
		while (true) {
			ycs[cyi--] = cy;
			ycs[cyi--] = 1234567890;
			if (ys.lower(cy)==null)
				break;
			cy = ys.lower(cy);
		}
		while (true) {
			xcs[cxi--] = cx;
			xcs[cxi--] = 1234567890;
			if (xs.lower(cx)==null)
				break;
			cx = xs.lower(cx);
		}
		cyi = 1000;
		cxi = 1000;
		cy = 0;
		cx = 0;
		while (true) {
			ycs[cyi++] = cy;
			ycs[cyi++] = 1234567890;
			if (ys.higher(cy)==null)
				break;
			cy = ys.higher(cy);
		}
		while (true) {
			xcs[cxi++] = cx;
			xcs[cxi++] = 1234567890;
			if (xs.higher(cx)==null)
				break;
			cx = xs.higher(cx);
		}
		cy = 1000;
		cx = 1000;
		int[][] codes = new int[2000][2000];
		codes[cy][cx] = 1;
		for (String s : lines) {
			s = s.split(" ")[2].substring(2,8);
			char dir = s.charAt(5);
			int dist = Integer.parseInt(s.substring(0,5),16);
			int dy = 0;
			int dx = 0;
			if (dir=='0')
				dx=1;
			if (dir=='2')
				dx=-1;
			if (dir=='1')
				dy=1;
			if (dir=='3')
				dy=-1;
			int tgy = ycs[cy]+dy*dist;
			int tgx = xcs[cx]+dx*dist;
			while (ycs[cy]!=tgy||xcs[cx]!=tgx) {
				cy+=dy;
				cx+=dx;
				codes[cy][cx] = 1;
			}
		}
		ArrayDeque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < 2000; ++i) {
			for (int j = 0; j < 2000; ++j) {
				if (i==0||i==1999||j==0||j==1999) {
					if (codes[i][j] == 0) {
						q.add(i*2000+j);
					}
				}
			}
		}
		while (!q.isEmpty()) {
			int u = q.removeFirst();
			int uy = u/2000;
			int ux = u%2000;
			int[] dy = {0,1,0,-1};
			int[] dx = {1,0,-1,0};
			for (int z = 0; z < 4; ++z) {
				int vy = uy+dy[z];
				int vx = ux+dx[z];
				if (vy>=0&&vy<2000&&vx>=0&&vx<2000&&codes[vy][vx]==0) {
					codes[vy][vx] = -1;
					q.add(vy*2000+vx);
				}
			}
		}
		for (int i = 0; i < 2000; ++i) {
			for (int j = 0; j < 2000; ++j) {
				if (codes[i][j]>=0) {
					long xlen = 1;
					long ylen = 1;
					if (ycs[i]==1234567890) {
						ylen = ycs[i+1]-ycs[i-1]-1;
					}
					if (xcs[j]==1234567890) {
						xlen = xcs[j+1]-xcs[j-1]-1;
					}
					ans += xlen*ylen;
					//System.out.println(i+" "+j+" "+codes[i][j]+" "+ylen+" "+xlen);
				}
			}
		}
		System.out.println(ans);
	}
}