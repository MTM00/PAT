package the_best_rank;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] firstLine = sc.nextLine().split(" ");
		int n_num = Integer.parseInt(firstLine[0]);
		int m_num = Integer.parseInt(firstLine[1]);
		
		HashMap<String,int[]> map = new HashMap<String,int[]>();
		int[] c_arr = new int[101];
		int[] m_arr = new int[101];
		int[] e_arr = new int[101];
		int[] a_arr = new int[101];
		
		for(int i = 0; i < n_num; i++) {
			String[] line = sc.nextLine().split(" ");
			String id = line[0];
			int c = Integer.parseInt(line[1]);
			int m = Integer.parseInt(line[2]);
			int e = Integer.parseInt(line[3]);
			int a = (int) Math.round((c+m+e)/3.0);
			int[] temp = {a,c,m,e};
			map.put(id, temp);
			
			c_arr[c]++;
			m_arr[m]++;
			e_arr[e]++;
			a_arr[a]++;
		}
		
		int c_temp = 0;
		int m_temp = 0;
		int e_temp = 0;
		int a_temp = 0;
		for(int j = c_arr.length-1; j >= 0; j--) {
			if(c_arr[j] != 0) {
				int temp = c_arr[j];
				c_arr[j] = c_temp + 1;
				c_temp += temp;
			}
			if(m_arr[j] != 0) {
				int temp = m_arr[j];
				m_arr[j] = m_temp + 1;
				m_temp += temp;
			}
			if(e_arr[j] != 0) {
				int temp = e_arr[j];
				e_arr[j] = e_temp + 1;
				e_temp += temp;
			}
			if(a_arr[j] != 0) {
				int temp = a_arr[j];
				a_arr[j] = a_temp + 1;
				a_temp += temp;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		String[] s = {"A","C","M","E"};
		for(int k = 0; k < m_num; k++) {
			String[] line = sc.nextLine().split(" ");
			String id = line[0];
			if(map.get(id) == null) {
				sb.append("N/A\n");
				continue;
			}
			int[] cmea = map.get(id);
			int[] cmea_ra = {a_arr[cmea[0]], c_arr[cmea[1]], m_arr[cmea[2]], e_arr[cmea[3]]};
			int rank = Integer.MAX_VALUE;
			String course = "";
			int index = -1;
			for(int p = 0; p < cmea_ra.length; p++) {
				if(cmea_ra[p] < rank) {
					rank = cmea_ra[p];
					course = s[p];
					index = p;
				}
			}
			
			sb.append(rank + " " + course + "\n");
		}
		
		System.out.println(sb.toString().trim());
	}
}
