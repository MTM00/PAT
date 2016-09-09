package countingleaves;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String firstLine = sc.nextLine().trim();
		String[] firstLine_arr = firstLine.split(" ");
		int N = Integer.parseInt(firstLine_arr[0]);
		if(N == 0) {
			System.out.println(0);
			return;
		}
		int M = Integer.parseInt(firstLine_arr[1]);
		
		String[] line;
		TreeMap<Integer,ArrayList<Integer>> map = new TreeMap<Integer,ArrayList<Integer>>();
		for(int i=0; i<M; i++) {
			line = sc.nextLine().trim().split(" ");
			int num = Integer.parseInt(line[1]);
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for(int j=0; j<num; j++) {
				arr.add(Integer.parseInt(line[j+2]));
			}
			map.put(Integer.parseInt(line[0]), arr);
		}
		
		ArrayList<Integer> child = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		boolean[] mark = new boolean[N];
		for(int i=0; i<N; i++) {
			mark[i] = false;
		}
		child.add(1);
		while(!child.isEmpty()) {
			int count = 0;
			ArrayList<Integer> temp = new ArrayList<Integer>();
			for(int i : child) {
				if(map.containsKey(i)) {
					temp.addAll(map.get(i));
				} else {
					if(!mark[i-1])
					count++;
					mark[i-1] = true;
				}
			}
			child = temp;
			result.add(count);
		}
		
		StringBuilder sb = new StringBuilder();
		for(Integer i : result) {
			sb.append(i + " ");
		}
		System.out.println(sb.toString().trim());
	}
}
