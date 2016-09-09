package battle_over_cities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Reader1 {
	private BufferedReader reader;
	private StringTokenizer tokenizer;
	
	public Reader1(InputStream input) {
		reader = new BufferedReader(new InputStreamReader(input));
		tokenizer = new StringTokenizer("");
	}
	
	public String next() throws IOException {
		while(!tokenizer.hasMoreTokens()) {
			tokenizer = new StringTokenizer(reader.readLine());
		}
		return tokenizer.nextToken();
	}
	
	public int nextInt() throws IOException {
		return Integer.parseInt(next());
	}
	
	public double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}
}

public class Main4 {
	private static int findRoot(int i) {
		if(root[i] == -1)
			return i;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int index = i;
		int temp = root[index];
		while(temp != -1) {
			arr.add(index);
			index = temp;
			temp = root[index];
		}
		for(int j : arr) {
			root[j] = index;
		}
		return index;
	}
	
	private static int[] root;
	public static void main(String[] args) throws IOException {
		Reader1 reader = new Reader1(System.in);
		int cities = reader.nextInt();
		int ways = reader.nextInt();
		int checked = reader.nextInt();
		
		int[][] edge = new int[ways][2];
		for(int i = 0; i < ways; i++) {
			int city1 = reader.nextInt();
			int city2 = reader.nextInt();
			edge[i][0] = city1;
			edge[i][1] = city2;
		}
		
		root = new int[cities+1];
		int[] result = new int[checked];
		
		for(int i = 0; i < checked; i++) {
			Arrays.fill(root, -1);
			int checked_c = reader.nextInt();
			for(int j = 0; j < ways; j++) {
				if(edge[j][0] != checked_c && edge[j][1] != checked_c) {
					int r1 = findRoot(edge[j][0]);
					int r2 = findRoot(edge[j][1]);
					if(r1 != r2) {
						root[r1] = r2;
					}
				}
			}
			int count = 0;
			for(int k = 1; k < root.length; k++) {
				if(root[k] == -1)
					count++;
			}
			result[i] = count-2;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int res : result) {
			sb.append(res + "\n");
		}
		System.out.println(sb.toString().trim());
	}
	
}
