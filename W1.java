import java.io.*;
import java.util.*;
import java.math.*;

public class W1 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/w.in"))));
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
		int[] dy = {0,1,0,-1};
		int[] dx = {1,0,-1,0};
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(1);
		int[] p = new int[n*m];
		int[] dist = new int[n*m];
		while (!q.isEmpty()) {
			int u = q.removeFirst();
			int uy = u/m;
			int ux = u%m;
			HashSet<Integer> dirs = new HashSet<>();
			if (ux>0&&lines.get(uy).charAt(ux-1)!='>')
				dirs.add(2);
			if (uy>0&&lines.get(uy-1).charAt(ux)!='v')
				dirs.add(3);
			if (ux<m-1&&lines.get(uy).charAt(ux+1)!='<')
				dirs.add(0);
			if (uy<n-1&&lines.get(uy+1).charAt(ux)!='^')
				dirs.add(1);
			for (int z : dirs) {
				int vy = uy+dy[z];
				int vx = ux+dx[z];
				if (p[u]==vy*m+vx)
					continue;
				p[vy*m+vx]=u;
				if (lines.get(vy).charAt(vx)!='#'&&dist[u]+1>dist[vy*m+vx]) {
					dist[vy*m+vx] = dist[u]+1;
					q.add(vy*m+vx);
				}
			}
		}
		System.out.println(dist[n*m-2]);
	}
}