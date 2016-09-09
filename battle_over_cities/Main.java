package battle_over_cities;

import java.util.Scanner;

public class Main {
	private static int[] mark;
	public static int Dfs(int[][] map, int cities, int checked_city) {
		int count = 0;
		mark = new int[cities+1];
		mark[checked_city] = 2;
		for(int i = 1; i <= cities; i++) {
			if(mark[i] == 0) {
				Dfs_visit(map,cities,i);
				count++;
			}
		}
		return count-1;
	}
	

	private static void Dfs_visit(int[][] map, int cities, int start) {
		mark[start] = 1;
		for(int i = 1; i <= cities; i++) {
			if(map[start][i] < Integer.MAX_VALUE) {
				if(mark[i] == 0) {
					Dfs_visit(map,cities,i);
				}
			}
		}
		mark[start] = 2;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line1 = sc.nextLine().split(" ");
		int cities = Integer.parseInt(line1[0]);
		int highways = Integer.parseInt(line1[1]);
		int checked = Integer.parseInt(line1[2]);
		
		int[][] map = new int[cities+1][cities+1];
		for(int n = 0; n <= cities; n++) {
			for(int m = 0; m <= cities; m++) {
				map[n][m] = Integer.MAX_VALUE;
			}
		}
		
		for(int i = 0; i < highways; i++) {
			String[] line = sc.nextLine().split(" ");
			int city = Integer.parseInt(line[0]);
			for(int j = 1; j < line.length; j++) {
				int city_con = Integer.parseInt(line[j]);
				map[city][city_con] = map[city_con][city] = 1;
			}
		}
		
		String[] lastLine = sc.nextLine().split(" ");
		int[] result = new int[checked];
		for(int i = 0; i < lastLine.length; i++) {
			int checked_city = Integer.parseInt(lastLine[i]);
			result[i] = Main.Dfs(map,cities,checked_city);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int res : result) {
			sb.append(res + "\n");
		}
		System.out.println(sb.toString().trim());
	}
}
