package emergency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


class City {
	/*
	 * The NO. of the city
	 */
	private int num;
	
	/*
	 * The number of rescue teams
	 */
	private int teams;
	
	/*
	 * Neighbour
	 */
	private ArrayList<Neighbour> neighbours = new ArrayList<Neighbour>();
	
	/*
	 * The shortest path to other cities
	 */
	private PathMap pathMap;
	
	public PathMap getPathMap() {
		return pathMap;
	}

	public void setPathMap(PathMap pathMap) {
		this.pathMap = pathMap;
	}

	public void setNeighbours(ArrayList<Neighbour> neighbours) {
		this.neighbours = neighbours;
	}

	public City(int num, int teams) {
		this.num = num;
		this.teams = teams;
		
		ArrayList<City> cityList = new ArrayList<City>();
		cityList.add(this);
		Path path = new Path(cityList, 0);
		ArrayList<Path> pathArray = new ArrayList<Path>();
		pathArray.add(path);
		PathMap pathMap = new PathMap(this, pathArray);
		this.setPathMap(pathMap);
	}
	
	public void addNeighbour(Neighbour neighbour) {
		this.neighbours.add(neighbour);
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getTeams() {
		return teams;
	}
	public void setTeams(int teams) {
		this.teams = teams;
	}
	public ArrayList<Neighbour> getNeighbours() {
		return neighbours;
	}
	
}

class PathMap {
	private Map<City, ArrayList<Path>> pathMap = new HashMap<City, ArrayList<Path>>();
	
	public PathMap(City city, ArrayList<Path> pathArray) {
		this.pathMap.put(city, pathArray);
	}
	public void addPath(City current, City goal, Path path) {
		ArrayList<Path> paths = current.getPathMap().pathMap.get(goal);
		if(paths == null || paths.isEmpty() || paths.get(0).distance > path.distance) {
			paths = new ArrayList<Path>();
			paths.add(path);
			current.getPathMap().pathMap.put(goal, paths);
		} else if(paths.get(0).distance == path.distance) {
			paths.add(path);
		}
	}
	
	public boolean isInPathMap(City city) {
		return this.pathMap.get(city) != null;
	}
	
	public int getDistance(City city) {
		if(!isInPathMap(city)) {
			return Integer.MAX_VALUE;
		} else {
			return pathMap.get(city).get(0).distance;
		}
	}
	
	public ArrayList<Path> getPathArray(City city) {
		if(!isInPathMap(city)) {
			return null;
		} else {
			return pathMap.get(city);
		}
	}
}

class Path {
	ArrayList<City> path;
	int distance;
	public Path(ArrayList<City> path, int distance) {
		this.path = path;
		this.distance = distance;
	}
	
	public void addCity(City city, int distance) {
		this.path.add(city);
		this.distance += distance;
	}
	
	public void deleteCity(City city, int distance) {
		path.remove(path.indexOf(city));
		this.distance -= distance;
	}
}
class Neighbour {
	private City city;
	private int distance;
	public Neighbour(City city, int distance) {
		this.setCity(city);
		this.setDistance(distance);
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
}

public class Solution {
	int cityNum;
	int roadNum;
	City current;
	City goal;
	City[] cities;
	boolean hasNewCity = true;
	
	public ArrayList<String[]> getInput() {
		ArrayList<String[]> input = new ArrayList<String[]>();
		String temp;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(!(temp = bf.readLine()).isEmpty()) {
				input.add(temp.split(" "));
			}
			return input;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void setUp(ArrayList<String[]> input) {
		if(input == null) {
			System.out.println("No input!");
			return;
		}
		//获取第一行输入
		int[] firstLine = new int[input.get(0).length];
		for(int i = 0; i < input.get(0).length; i++) {
			firstLine[i] = Integer.parseInt(input.get(0)[i]);
		}
		//判断第一行数据数是否是4
		if(firstLine.length == 4) {
			//判断input行数是否正确
			if(input.size() != firstLine[1] + 2) {
				System.out.println("Error data!");
				return;
			}
			//判断input第二行teams信息数量是否正确
			if(input.get(1).length != firstLine[0]) {
				System.out.println("The number Cities is wrong!");
				return;
			}
			cityNum = firstLine[0];
			roadNum = firstLine[1];
			//根据city数创建city数组
			cities = new City[cityNum];
		} else {
			System.out.println("Error data!");
			return;
		}
		
		//初始化city数组
		for(int i = 0; i < cityNum; i++) {
			cities[i] = new City(i,Integer.parseInt(input.get(1)[i]));
		}
		//获取neighbour信息
		for(int i = 2; i < roadNum + 2; i++) {
			cities[Integer.parseInt(input.get(i)[0])]
					.addNeighbour(
							new Neighbour(
									cities[Integer.parseInt(input.get(i)[1])], Integer.parseInt(input.get(i)[2])));
			cities[Integer.parseInt(input.get(i)[1])]
					.addNeighbour(
							new Neighbour(
									cities[Integer.parseInt(input.get(i)[0])], Integer.parseInt(input.get(i)[2])));
		}
		current = cities[firstLine[2]];
		goal = cities[firstLine[3]];
		ArrayList<City> cityList = new ArrayList<City>();
		cityList.add(current);
		//添加最短路径信息
		buildShortestPaths(cityList, current);
	}
	/*
	 * 获取目标城市最短路径信息
	 */
	private void buildShortestPaths(ArrayList<City> cityList, City current) {

		ArrayList<City> temp = new ArrayList<City>();
		
		hasNewCity = false;
		
		for(City c : cityList) {
			for(Neighbour neighbour : c.getNeighbours()) {
				if(!current.getPathMap().isInPathMap(neighbour.getCity())) {
					hasNewCity = true;	
					temp.add(neighbour.getCity());
				}
				ArrayList<Path> pa = current.getPathMap().getPathArray(c);
				for(int i = 0; i < pa.size(); i++) {
					Path p = pa.get(i);
					ArrayList<City> cityArray = new ArrayList<City>();
					cityArray.addAll(p.path);
					Path path = new Path(cityArray, p.distance);
					path.addCity(neighbour.getCity(), neighbour.getDistance());
					current.getPathMap().addPath(current, neighbour.getCity(), path);
				}
			}
			

		}
		while(hasNewCity) {
			buildShortestPaths(temp, current);
		}
		
	}
	
	public static void main(String[] args) {
		
		int distance = 0;
		int teams = 0;
		Solution solution = new Solution();
		solution.setUp(solution.getInput());
		distance = solution.current.getPathMap().getDistance(solution.goal);
		ArrayList<Path> pathList = solution.current.getPathMap().getPathArray(solution.goal);
		System.out.println("The distance between " + solution.current + " and " + solution.goal + " is..." + distance);
		
		int size = pathList.size();
		if(distance == 0) {
			size = 0;
		}
		System.out.println("We have " + size + " ways!");
		for(Path path: pathList) {
			for(City c: path.path) {
				System.out.print("city" + c.getNum() + "----");
			}
			System.out.println(" distance = " + path.distance);
		}
//		for(City c : solution.cities) {
//			System.out.println(solution.current.getPathMap().getDistance(c));
//		}
		for(Path path: pathList) {
			int sum = 0;
			for(City c: path.path) {
				sum += c.getTeams();
			}
			teams = sum > teams?sum:teams;
		}
		
		System.out.println(size + " " + teams);
	}
}
