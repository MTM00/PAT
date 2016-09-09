package maximum_subsequence_sum;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine().trim());
		if(num == 0) {
			System.out.println("0");
			return;
		}
		String[] input_str = sc.nextLine().trim().split(" ");
		
		int max_start = 0;
		int max_end = 0;
		int max = Integer.MIN_VALUE;
		int current_start = 0;
		int current_end = 0;
		int current_max = 0;
		
		for(int i = 0; i < input_str.length; i++) {
			int temp = current_max + Integer.parseInt(input_str[i]);
			if(temp > max && temp >= 0) {
				
				current_end = i;
				current_max = temp;
				max_start = current_start;
				max_end = current_end;
				max = current_max;
			} else if(temp > 0) {
				current_end = i;
				current_max = temp;
			} else if(temp <= 0) {
				if(i+1<input_str.length) {
					current_start = i+1;
					current_end = i+1;
					current_max = 0;
				}
			}
		}
		if(max < 0) {
			int end = input_str.length-1;
			System.out.println("0 " + input_str[0] + " " + input_str[end]);
		} else {
			System.out.println(max + " " + input_str[max_start] + " " + input_str[max_end]);
		}
	}
}
