package polynomials;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String args[]) {
		double[] result = new double[1001];
		String[][] lines = new String[2][1001];
		int count = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			for(int i = 0; i < 2; i++) {
				String line = br.readLine();
				if(line == null || line.isEmpty())
					break;
				lines[i] = line.split(" ");
				
				for(int j = 1; j < lines[i].length - 1; j++) {
					int index = Integer.parseInt(lines[i][j++]);
					if(result[index] == 0) {
						result[index] = Double.parseDouble(lines[i][j]);
						if(result[index] != 0)
							count++;
					} else {
						result[index] += Double.parseDouble(lines[i][j]);
						if(result[index] == 0 && count > 0) {
							count--;
						}
					}
				}

			}
			System.out.print(count);
			for(int i = result.length - 1; i >= 0;i--) {
				if(result[i] != 0) {
					System.out.print(" " + i + " " + String.format("%.1f", result[i]));
				}	
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


