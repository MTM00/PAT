package worldcup;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String[] wtl = {"W","T","L"};
		double[] odds = new double[3];
		int[] index = new int[3];
		double TIME = 0.65;
		int yuan = 2;
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 3; i++) {
			String[] line = sc.nextLine().split(" ");
			double odds_temp = 0.0;
			int index_temp = 0;
			for(int j = 0; j < 3; j++) {
				double od = Double.parseDouble(line[j]);
				if(od > odds_temp) {
					odds_temp = od;
					index_temp = j;
				}
			}
			odds[i] = odds_temp;
			index[i] = index_temp;
		}
		
		double result = (odds[0]*odds[1]*odds[2]*TIME-1)*yuan;
		
		System.out.println(wtl[index[0]] + " " + wtl[index[1]] + " " + wtl[index[2]] + " " + String.format("%.2f", result));
	}
}
