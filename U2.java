import java.io.*;
import java.util.*;
import java.math.*;

public class U2 {
	public static void main(String[] args) throws IOException {

		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		/*/
		Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(new FileInputStream("src/u.in"))));
		/**/
		
		long ans = 0;
		ArrayList<String> lines = new ArrayList<>();
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			if (s.isEmpty())
				break;
			lines.add(s);
		}
		int n = lines.size();
		int m = lines.get(0).length();
		boolean[][] wall = new boolean[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				wall[i][j] = lines.get(i).charAt(j)=='#';
			}
		}
		int[] dy = {0,1,0,-1};
		int[] dx = {1,0,-1,0};
		int[][] dist = new int[n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				dist[i][j] = 999999;
				if (lines.get(i).charAt(j)=='S') {
					dist[i][j] = 0;
				}
			}
		}
		for (int z = 1; z <= 1000; ++z) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < m; ++j) {
					if (dist[i][j]==999999)
						continue;
					for (int zz = 0; zz < 4; ++zz) {
						int a = i+dy[zz];
						int b = j+dx[zz];
						if (a>=0&&b>=0&&a<n&&b<m&&!wall[a][b])
							dist[a][b]=Math.min(dist[a][b], dist[i][j]+1);
					}
				}
			}
		}
		int[] cts = new int[2];
		int maxdist = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (dist[i][j]==999999)
					continue;
				maxdist = Math.max(maxdist, dist[i][j]);
				++cts[dist[i][j]%2];
			}
		}
		HashMap<String,Integer> segcts = new HashMap<>();
		HashMap<String,ArrayList<HashMap<Integer,Integer>>> segs = new HashMap<>();
		HashMap<String,Long> species = new HashMap<String,Long>();
		// n==m, start is in the middle, open lines from start
		int STEPS = 26501365;
		int maxwarps = STEPS/n;
		for (int z = 0; z <= maxwarps+1; ++z) {
			int steps = STEPS-z*n;
			if (steps-2*n>=maxdist) {
				for (int i = 0; i <= z; ++i) {
					int j = z-i;
					ans += (i>0?2:1)*(j>0?2:1)*cts[steps&1];
					continue;
				}
			} else {
				for (int i = 0; i <= z; ++i) {
					int j = z-i;
					for (int a = -1; a <= 1; a+=2) {
						for (int b = -1; b <= 1; b+=2) {
							if (i==0&&a<0)
								continue;
							if (j==0&&b<0)
								continue;
							int ii = i*a;
							int jj = j*b;
							String s = "";
							String ss = "";
							for (int aa = -1; aa <= 1; ++aa) {
								for (int bb = -1; bb <= 1; ++bb) {
									int iiaa = ii+aa;
									int jjbb = jj+bb;
									int zz = Math.abs(iiaa)+Math.abs(jjbb);
									int sss = STEPS-zz*n;
									s += sss<0?"X":(sss/n);
								}
								s+="\n";
							}
							for (int aa = -3; aa <= 3; ++aa) {
								for (int bb = -3; bb <= 3; ++bb) {
									int iiaa = ii+aa;
									int jjbb = jj+bb;
									int zz = Math.abs(iiaa)+Math.abs(jjbb);
									int sss = STEPS-zz*n;
									ss += sss<0?"X":(sss/n);
								}
								ss+="\n";
							}
							segcts.put(s, segcts.getOrDefault(s, 0)+1);
							if (!segs.containsKey(s)) {
								segs.put(s, new ArrayList<>());
								for (int k = 0; k < n; ++k)
									segs.get(s).add(new HashMap<>());
							}
							if (z<=maxwarps) {
								species.put(ss,createKey(ii*n+n/2,jj*n+n/2));
								segs.get(s).get(n/2).put(n/2,steps);
								if (steps%2==0)
									++ans;
							}
						}
					}
				}
			}
			if (z%10000==0)
				System.out.println(z);
		}
		System.out.println(species.size());
		for (String ss : species.keySet()) {
			long sq0 = species.get(ss);
			ArrayDeque<Long> q = new ArrayDeque<>();
			q.add(sq0);
			//System.out.println(sq0);
			while (!q.isEmpty()) {
				long q0 = q.removeFirst();
				int uysg = (q0&(1L<<61))==0?1:-1;
				int uxsg = (q0&(1L<<60))==0?1:-1;
				q0&=(1L<<60)-1;
				int uy = (int)(uysg*(q0>>30));
				int ux = (int)(uxsg*(q0&((1L<<30)-1)));
				int uys = Math.floorDiv(uy, n);
				int uxs = Math.floorDiv(ux, n);
				int uyo = uy-uys*n;
				int uxo = ux-uxs*n;
				String us = "";
				for (int aa = -1; aa <= 1; ++aa) {
					for (int bb = -1; bb <= 1; ++bb) {
						int iiaa = uys+aa;
						int jjbb = uxs+bb;
						int zz = Math.abs(iiaa)+Math.abs(jjbb);
						int sss = STEPS-zz*n;
						us += sss<0?"X":(sss/n);
					}
					us+="\n";
				}
				if (!segs.containsKey(us))
					System.out.println(us);
				if (!segs.get(us).get(uyo).containsKey(uxo))
					System.out.println(us+" "+uyo+" "+uxo);
				int uw = segs.get(us).get(uyo).get(uxo);
				if (uw==0)
					continue;
				for (int z = 0; z < 4; ++z) {
					int vy = uy+dy[z];
					int vx = ux+dx[z];
					int vw = uw-1;
					int vys = Math.floorDiv(vy, n);
					int vxs = Math.floorDiv(vx, n);
					int vyo = vy-vys*n;
					int vxo = vx-vxs*n;
					String vs = "";
					for (int aa = -1; aa <= 1; ++aa) {
						for (int bb = -1; bb <= 1; ++bb) {
							int iiaa = vys+aa;
							int jjbb = vxs+bb;
							int zz = Math.abs(iiaa)+Math.abs(jjbb);
							int sss = STEPS-zz*n;
							vs += sss<0?"X":(sss/n);
						}
						vs+="\n";
					}
					if (wall[vyo][vxo]||STEPS-(Math.abs(vys)+Math.abs(vxs)+2)*n>=maxdist)
						continue;
					if (!segs.containsKey(vs))
						System.out.println(vs+vw);
					if (segs.get(vs).get(vyo).getOrDefault(vxo,-1)<vw) {
						q.add(createKey(vy,vx));
						if (vw%2==0&&!segs.get(vs).get(vyo).containsKey(vxo)) {
							//System.out.println(vs+ans+" "+segcts.get(vs));
							ans+=segcts.get(vs);
						}
						segs.get(vs).get(vyo).put(vxo, vw);
					}
				}
			}
		}
		System.out.println(ans);
	}
	
	public static long createKey(int uy, int ux) {
		long key = Math.abs(uy);
		key<<=30;
		key+=Math.abs(ux);
		if (ux<0)
			key+=1L<<60;
		if (uy<0)
			key+=1L<<61;
		return key;
	}
}