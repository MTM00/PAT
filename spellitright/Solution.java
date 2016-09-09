package spellitright;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	public static String[] NUMBERS = {"zero","one","two","three", "four", "five", "six", "seven", "eight", "nine"};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine().trim();
		int sum = 0;
		for(int i=0; i<line.length(); i++) {
			sum += line.charAt(i)-'0';
		}
		ArrayList<String> result = new ArrayList<String>();
		if(sum == 0) {
			System.out.println("zero");
			return;
		}
		while(sum > 0) {
			result.add(NUMBERS[sum%10]);
			sum = sum/10;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = result.size()-1; i >= 0; i--) {
			sb.append(result.get(i) + " ");
		}
		System.out.println(sb.toString().trim());
	}
}
