package prouct_of_polynomials;

import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] firstLine = sc.nextLine().split(" ");
		String[] secondLine = sc.nextLine().split(" ");
		double[] first = new double[1001];
		double[] second = new double[1001];
		double[] result = new double[2001];
		int number1 = Integer.parseInt(firstLine[0]);
		int number2 = Integer.parseInt(secondLine[0]);
		for(int i = 1; i <= number1; i++) {
			first[Integer.parseInt(firstLine[2*i-1])] = Double.parseDouble(firstLine[2*i]);
		}
		for(int j = 1; j <= number2; j++) {
			second[Integer.parseInt(secondLine[2*j-1])] = Double.parseDouble(secondLine[2*j]);
		}
		
		for(int n = 0; n < first.length; n++) {
			for(int m = 0; m < second.length; m++) {
				result[n+m] += first[n] * second[m];
			}
		}
		
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for(int k = result.length - 1; k >= 0; k--) {
			if(result[k] != 0) {
				sb.append(k + " ");
				sb.append(String.format("%.1f", result[k]) + " ");
				count++;
			}
		}
		sb.insert(0, count + " ");
		System.out.println(sb.toString().trim());
	}
}
