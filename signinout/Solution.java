package signinout;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int num = Integer.parseInt(line.trim());
		String unlocked,locked,unlocked_time,locked_time;
		unlocked = "";
		locked = "";
		unlocked_time = "25";
		locked_time = "0";
		for(int i = 1; i <= num; i++) {
			line = sc.nextLine();
			String[] arr = line.trim().split(" ");
			if(unlocked_time.compareTo(arr[1]) > 0) {
				unlocked_time = arr[1];
				unlocked = arr[0];
			}
			if(locked_time.compareTo(arr[2]) < 0) {
				locked_time = arr[2];
				locked = arr[0];
			}
		}
		System.out.println(unlocked + " " + locked);
	}
}
