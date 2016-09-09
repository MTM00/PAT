package emergency;

import java.util.Scanner;

public class Main {
	int[][] map;	//临接矩阵
	int n,m,start,end;					//城市数，路径数，起点，重点，
	int[] teams;  						//各城市拥有的队伍数
	int[] dists;						//记录每点权重
	int[] num_team;						//到该点路径总计最大的team数
	boolean[] mark;						//标记该点未进入结点集合S
	int[] count;
	public Main(int[][] map, int n, int m, int start, int end, int[] teams) {
		super();
		this.map = map;
		this.n = n;
		this.m = m;
		this.start = start;
		this.end = end;
		this.teams = teams;
		num_team = new int[n];
		dists = new int[n];
		mark = new boolean[n];
		count = new int[n];
	}

	public void dijkstra() {
		initialize();
		int newCity = start;
		while(newCity >= 0) {
			//设置标志位，表示该城市已进入结点集合S
			mark[newCity] = true;
			//更新该点相邻结点权重
			for(int i = 0; i < n; i++){
				if(dists[i] > dists[newCity] + map[newCity][i] && dists[newCity] + map[newCity][i] > 0) {
					dists[i] = dists[newCity] + map[newCity][i];
					num_team[i] = num_team[newCity] + teams[i];
					count[i] = count[newCity];
				}
				else if(dists[i] == dists[newCity] + map[newCity][i] && dists[i] != Integer.MAX_VALUE) {
					count[i] += count[newCity];
					if(num_team[newCity] + teams[i] > num_team[i]) {
						num_team[i] = num_team[newCity] + teams[i];
					} 
				}
			}
			int temp = Integer.MAX_VALUE;
			newCity = -1;
			for(int j = 0; j < n; j++) {
				if(mark[j] == false && dists[j] < temp) {
					temp = dists[j];
					newCity = j;
				}
			}
		}
	}
	
	private void initialize() {
		for(int i = 0; i < n; i++) {
			dists[i] = Integer.MAX_VALUE;
			num_team[i] = 0;
			mark[i] = false;
			count[i] = 1;
		}
		mark[start] = true;
		dists[start] = 0;
		num_team[start] = teams[start];
	}
	public static void main(String[] args) {
		int[][] map;						//临接矩阵
		int n,m,start,end;					//城市数，路径数，起点，重点，
		int[] teams;  						//各城市拥有的队伍数
		Scanner sc = new Scanner(System.in);
		String[] first_str = sc.nextLine().split(" ");
		n = Integer.parseInt(first_str[0]);
		m = Integer.parseInt(first_str[1]);
		start = Integer.parseInt(first_str[2]);
		end = Integer.parseInt(first_str[3]);
		teams = new int[n];
		map = new int[n][n];
		String[] second = sc.nextLine().split(" ");
		for(int i = 0; i < n; i++) {
			teams[i] = Integer.parseInt(second[i]);
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i = 0; i < m; i++) {
			String[] str = sc.nextLine().split(" ");
			int c1 = Integer.parseInt(str[0]);
			int c2 = Integer.parseInt(str[1]);
			int dist = Integer.parseInt(str[2]);
			map[c1][c2] = map[c2][c1] = dist;
		}
		Main ma = new Main(map,n,m,start,end,teams);
		ma.dijkstra();
		System.out.println(ma.count[end] + " " + ma.num_team[end]);
	}
}
