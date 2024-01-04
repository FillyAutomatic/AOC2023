import java.io.*;
import java.util.*;
import java.math.*;

public class W2 {
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
		TreeMap<Integer, HashMap<Integer,Integer>> forks = new TreeMap<>();
		forks.put(1, new HashMap<>());
		forks.put(n*m-2, new HashMap<>());
		for (int i = 1; i < n-1; ++i) {
			for (int j = 1; j < m-1; ++j) {
				if (lines.get(i).charAt(j)=='#')
					continue;
				int ct = 0;
				for (int z = 0; z < 4; ++z) {
					if (lines.get(i+dy[z]).charAt(j+dx[z])!='#') {
						++ct;
					}
				}
				if (ct>2) {
					forks.put(i*m+j, new HashMap<>());
				}
			}
		}
		for (int i : forks.keySet()) {
			int[] p = new int[n*m];
			int[] dist = new int[n*m];
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.add(i);
			while (!q.isEmpty()) {
				int u = q.removeFirst();
				int uy = u/m;
				int ux = u%m;
				HashSet<Integer> dirs = new HashSet<>();
				if (ux>0)
					dirs.add(2);
				if (uy>0)
					dirs.add(3);
				if (ux<m-1)
					dirs.add(0);
				if (uy<n-1)
					dirs.add(1);
				for (int z : dirs) {
					int vy = uy+dy[z];
					int vx = ux+dx[z];
					int v = vy*m+vx;
					if (p[u]==v)
						continue;
					p[v]=u;
					if (lines.get(vy).charAt(vx)!='#'&&dist[u]+1>dist[v]) {
						dist[v] = dist[u]+1;
						if (forks.containsKey(v)) {
							forks.get(i).put(v, dist[v]);
						} else {
							q.add(v);
						}
					}
				}
			}
		}
		int fs = forks.size();
		int[] fus = new int[fs];
		int ind = 0;
		for (int x : forks.keySet()) {
			fus[ind++] = x;
		}
		HashMap<Long, Integer> dist = new HashMap<>();
		dist.put(1L,0);
		ArrayDeque<Long> q = new ArrayDeque<>();
		q.add(1L);
		while (!q.isEmpty()) {
			if (q.size()%1000==0)
				System.out.println(q.size());
			long q0 = q.removeFirst();
			long mask = q0%(1L<<48);
			int u = (int)(q0>>48);
			int ud = dist.get(q0);
			if (u==fs-1) {
				ans = Math.max(ans, ud);
			}
			for (int fusv : forks.get(fus[u]).keySet()) {
				int v = -1;
				for (int j = 0; j < fs; ++j) {
					if (fus[j]==fusv)
						v=j;
				}
				if ((mask&(1L<<v))>0)
					continue;
				long vta = ((long)v)<<48;
				vta += mask|(1L<<v);
				if (ud+forks.get(fus[u]).get(fusv)>dist.getOrDefault(vta,0)) {
					dist.put(vta, ud+forks.get(fus[u]).get(fusv));
					q.add(vta);
				}
			}
		}
		System.out.println(ans);
	}
}