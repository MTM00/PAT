package countingleaves;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	public static ArrayList<ArrayList<Integer>> getInput() {
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		String temp;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(!(temp = bf.readLine()).isEmpty()) {
				ArrayList<Integer> intArray = new ArrayList<Integer>();
				for(String s : temp.split(" ")) {
					intArray.add(Integer.parseInt(s));
				}
				input.add(intArray);
			}
			return input;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] arge) {
		ArrayList<ArrayList<Integer>> input = Solution.getInput();
		ArrayList<Integer>[] mode = new ArrayList[100];
		ArrayList<Integer> child = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		int non_leaf_Number = input.get(0).get(1);
		if(non_leaf_Number > 0) {
			result.add(0);
			for(int i = 1; i <= non_leaf_Number; i++) {
				int number = input.get(i).get(0);
				int childNumber = input.get(i).get(1);
				ArrayList<Integer> t = new ArrayList<Integer>();
				for(int j = 1; j <= childNumber; j++) {
					t.add(input.get(i).get(1+j));
				}
				mode[number] = t;
			}
			child.addAll(mode[1]);
			while(!child.isEmpty()) {
				int count = 0;
				ArrayList<Integer> temp = new ArrayList<Integer>();
				for(Integer i : child) {
					if(mode[i] == null) {
						count++;				
					}
					else {
						for(Integer j : mode[i]) {
							temp.add(j);
						}
					}
				}
				
				result.add(count);
				child = temp;
			}
		} else {
			result.add(1);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < result.size(); i++) {
			sb.append(result.get(i) + " ");
		}
		System.out.println(sb.toString().trim());
	}
}