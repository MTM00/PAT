package countingleaves;

import java.util.Scanner;

public class Main {
	private int layer;
	private boolean haschild;
	
	
	public Main(int layer, boolean haschild) {
		super();
		this.layer = layer;
		this.haschild = haschild;
	}


	public int getLayer() {
		return layer;
	}


	public void setLayer(int layer) {
		this.layer = layer;
	}


	public boolean isHaschild() {
		return haschild;
	}


	public void setHaschild(boolean haschild) {
		this.haschild = haschild;
	}


	public static void main(String[] args) {
		int n,m;
		Scanner sc = new Scanner(System.in);
		String[] firstline = sc.nextLine().split(" ");
		n = Integer.parseInt(firstline[0]);
		m = Integer.parseInt(firstline[1]);
		Main[] result = new Main[n];
		for(int i = 0; i < n; i++) {
			result[i] = new Main(Integer.MIN_VALUE,false);
		}
		
		for(int j = 0; j < m; j++) {
			String[] str = sc.nextLine().split(" ");
			int node = Integer.parseInt(str[0]);
			result[node].setHaschild(true);
			if(result[node].getLayer() != Integer.MIN_VALUE) {
				for(int i = 2; i < str.length; i++) {
					result[Integer.parseInt(str[i])].setLayer(result[node].getLayer() + 1);
				}
			} else {
				for(int i = 2; i < str.length; i++) {
					if(result[Integer.parseInt(str[i])].getLayer() != Integer.MIN_VALUE) {
						result[node].setLayer(result[i].getLayer() - 1);
						for(int t = 2;t < str.length; t++ ) {
							result[Integer.parseInt(str[t])].setLayer(result[node].getLayer() + 1);
						}
					}
				}
				if(result[node].getLayer() == Integer.MIN_VALUE) {
					result[node].setLayer(0);
					for(int s = 2; s < str.length; s++) {
						result[Integer.parseInt(str[s])].setLayer(1);;
					}
				}
			}
		}
	}
}
