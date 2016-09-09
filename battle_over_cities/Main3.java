package battle_over_cities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Reader {
	private BufferedReader reader;
	private StringTokenizer tokenizer;
	
	public Reader(InputStream input) {
		this.reader = new BufferedReader(new InputStreamReader(input));
		this.tokenizer = new StringTokenizer("");
	}
	
	public String next() throws IOException {
		while(!tokenizer.hasMoreTokens()) {
			tokenizer = new StringTokenizer(reader.readLine());
		}
		return tokenizer.nextToken();
	}
	
	public double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
	
	public int nextInt() throws IOException {
		return Integer.parseInt(next());
	}
}
public class Main3 {
	private static int[] mark;
	private static int Dfs(int[][] map, int cities, int checked_c) {
		int count = 0;
		mark = new int[cities+1];
		mark[checked_c] = 2;
		for(int i = 1; i < cities+1; i++) {
			if(mark[i] == 0) {
				Dfs_visit(map,cities,i);
				count++;
			}
		}
		return count-1;
	}
	
	private static void Dfs_visit(int[][] map, int cities, int start) {
		mark[start] = 1;
		for(int i = 1; i < cities+1; i++) {
			if(mark[i] == 0 && map[start][i] < Integer.MAX_VALUE) {
				mark[i] = 1;
				Dfs_visit(map,cities,i);
			}
		}
		mark[start] = 2;
	}

	public static void main(String[] args) throws IOException {
		Reader reader = new Reader(System.in);
		int cities = reader.nextInt();
		int ways = reader.nextInt();
		int checked = reader.nextInt();
		
		int[][] map = new int[cities+1][cities+1];
		for(int i = 0; i < cities+1; i++) {
			for(int j = 0; j < cities+1; j++) {
				map[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i = 0; i < ways; i++) {
			int city1 = reader.nextInt();
			int city2 = reader.nextInt();
			map[city1][city2] = map[city2][city1] = 1;
		}
		
		int[] result = new int[checked];
		for(int i = 0; i < checked; i++) {
			int checked_c = reader.nextInt();
			result[i] = Dfs(map,cities,checked_c);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int res : result) {
			sb.append(res + "\n");
		}
		System.out.println(sb.toString().trim());
	}
}
