package elevator;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] input_str = sc.nextLine().trim().split(" ");
		int num = Integer.parseInt(input_str[0]);
		int current = 0;
		int total = 0;
		for(int i = 1; i <= num; i++) {
			int temp = Integer.parseInt(input_str[i]);
			if(temp-current > 0) {
				total += 6 * (temp-current) + 5;
			} else if(temp-current < 0) {
				total += 4 * (current-temp) + 5;
			} else {
				total += 5;
			}
			current = temp;
		}
		System.out.println(total);
	}
}
