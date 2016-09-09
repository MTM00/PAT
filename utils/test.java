package utils;

import java.io.IOException;

public class test {
	public static void main(String[] args) throws IOException {
		Reader reader = new Reader(System.in);
		String s1 = reader.next();
		String s2 = reader.next();
		System.out.println(s1 + "   " + s2);
	}
}
