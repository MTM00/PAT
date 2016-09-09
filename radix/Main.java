package radix;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	HashMap<Character,Integer> map;
	public Main() {
		this.map = new HashMap<Character,Integer>();
		for(int i = 0; i <= 9; i++) {
			map.put((char)('0'+i), i);
		}
		for(int j = 0; j <=25; j++) {
			map.put((char)('a'+j), 10+j);
		}
	}
	public int[] transform(char[] c) {
		
		int[] out = new int[c.length];
		for(int n = 0; n < c.length; n++) {
			out[n] = map.get(c[n]);
		}
		return out;
	}
	
	public long parseTen(int[] input, long radix) {
		long temp = 0;
		for(int i = 0; i < input.length; i++) {
			temp = temp*radix + input[i];
		}
		return temp;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] input_str = sc.nextLine().split(" ");
		char[] first_c = input_str[0].toCharArray();
		char[] second_c = input_str[1].toCharArray();
		int tag = Integer.parseInt(input_str[2]);
		long radix = Integer.parseInt(input_str[3]);
		int varRadix = 0;
		if(input_str[0].equals(input_str[1])) {
			System.out.println(radix);
			return;
		}
		
		Main ma = new Main();
		int[] first = ma.transform(first_c);
		int[] second = ma.transform(second_c);
		long first_ten;
		long second_ten;
		if(tag == 1) {
			first_ten = ma.parseTen(first, radix);
			int temp = 2;
			second_ten = ma.parseTen(second, temp);
			while(second_ten <= first_ten && second_ten != 0) {
				if(second_ten == first_ten) {
					varRadix = temp;
				}
				temp++;
				second_ten = ma.parseTen(second, temp);
			}
		} else {
			second_ten = ma.parseTen(second, radix);
			int temp = 2;
			first_ten = ma.parseTen(first, temp);
			while(first_ten <= second_ten && first_ten != 0) {
				if(second_ten == first_ten) {
					varRadix = temp;
				}
				temp++;
				first_ten = ma.parseTen(first, temp);
			}
		}
		if(varRadix == 0) {
			System.out.println("Impossible");
		} else {
			System.out.println(varRadix);
		}
	}
}
