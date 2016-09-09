package battle_over_cities;

import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
	private static int[] root;
	public static int findRoot(int i) {
		if(root[i] == -1)
			return i;
		int temp = findRoot(root[i]);
		root[i] = temp;
		return temp;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] line1 = sc.nextLine().split(" ");
		int cities = Integer.parseInt(line1[0]);
		int ways = Integer.parseInt(line1[1]);
		int checked = Integer.parseInt(line1[2]);
		
		int[][] way = new int[ways][2];
		for(int i = 0; i < ways; i++) {
			String[] line = sc.nextLine().split(" ");
			way[i][0] = Integer.parseInt(line[0]);
			way[i][1] = Integer.parseInt(line[1]);
		}
		
		root = new int[cities+1];
		
		int[] result = new int[checked];
		String[] lastLine = sc.nextLine().split(" ");
		for(int j = 0; j < lastLine.length; j++) {
			Arrays.fill(root, -1);
			int city = Integer.parseInt(lastLine[j]);
			for(int c = 0; c < ways; c++) {
				if(way[c][0] != city && way[c][1] != city) {
					int r1 = Main2.findRoot(way[c][0]);
					int r2 = Main2.findRoot(way[c][1]);
					if(r1 != r2) {
						root[r1] = r2;
					}
				}
			}
			int count = 0;
			for(int r = 1; r < root.length; r++) {
				if(root[r] == -1)
					count++;
			}
			result[j] = count-2;
		}
		StringBuilder sb = new StringBuilder();
		for(int res : result) {
			sb.append(res + "\n");
		}
		System.out.println(sb.toString().trim());
	}
}
