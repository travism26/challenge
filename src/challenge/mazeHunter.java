package challenge;

import java.lang.reflect.Array;
import java.util.*;

import javax.lang.model.element.NestingKind;
import javax.management.ReflectionException;

public class mazeHunter {
	
	public static void println(Object val){
		System.out.println(val.toString());
	}

	public static void print(int[][] maze)
	{
		for (int i = 0; i < maze.length; i++)
		{
			System.out.println();
			for (int j = 0; j < maze.length; j++)
			{
				System.out.print(maze[i][j]);
			}
		}
	}
	public static String listPrint(ArrayList<Integer> list)
	{
		String output ="";
		for (int i = 0; i < list.size(); i++)
		{
			output += list.get(i)+ " ";
		}
		return output;
	}
	
	public static int[][] setMaze(int[][] mazeIn){
		int val=0;
		int[][] maze = mazeIn;
		for (int i = 0; i < maze.length; i++)
		{
			for (int j = 0; j < maze.length; j++)
			{
				maze[i][j] = val++;
			}
		}
		return maze;
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		
		// int[][] vertex;

		//int size = in.nextInt();
		int[][] vertex = 
			{ 
				{ 0, 3 }, 
				{ 2, 5 }, 
				{ 3, 4 }, 
				{ 4, 5 }, 
				{ 4, 7 },
				{ 7, 8 }, 
				{ 5, 8 } 
			};
		int[][] maze = new int[3][3];
		maze = setMaze(maze);
		int val = 0;
		
		
		print(maze);
		
//		for (int i = 0; i < vertex.length; i++)
//		{
//			System.out.println("Hello");
//		}
		boolean isDone = false;
		HashMap<Integer, ArrayList<Integer>> shortestPath = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> cloud = new ArrayList<Integer>();
		
		//init stuff
		ArrayList<Integer> basePath = new ArrayList<Integer>();
		basePath.add(0);
		shortestPath.put(0, new ArrayList<Integer>());
		shortestPath.get(0).add(0);
		cloud.add(0);
		int counter=0;
		while(isDone == false){
			
			ArrayList<Integer> path = new ArrayList<Integer>();
			int nextShortest= -1;
			for (int i = 0; i < cloud.size(); i++)
			{
				int node = cloud.get(i);
				ArrayList<Integer> currentPath = new ArrayList<Integer>(); 
				currentPath.addAll(shortestPath.get(node));
				println("--------" + node);
				for (int[] vertice : vertex)
				{
					//Arrays.asList(vertice).contains(node) <-- not working for some reason :(
					if((vertice[0] == node) || (vertice[1] == node ))
					{
						println("Vertex: " + vertice[0] + " " + vertice[1]);
						int toNode = vertice[0]==node? vertice[1] : vertice[0];
						//check the cloud
						//for now we just check the second val in vertice 
						if( ! cloud.contains(toNode)){
							println(toNode + " is NOT IN THE CLOUD");
							if(nextShortest == -1){
								//this means it found first vertex. make it shortest node
								nextShortest = toNode;
								currentPath.add(toNode);
								path = currentPath;
								println("SET SOME STUFF");
							} else if(path.size() > currentPath.size()){
								//if the current path is shorter we need to update the 
								//path and the shortest node.
								nextShortest = toNode;
								currentPath.add(toNode);
								path = currentPath;
							}
						}
					}
				}
			}
			
			shortestPath.put(nextShortest, path);
			cloud.add(nextShortest);
			//here is where i want to add my next node to the cloud
			for (Map.Entry<Integer, ArrayList<Integer>> entry: shortestPath.entrySet())
			{
				println(entry.getKey() + "| " + listPrint(entry.getValue()));
			}
			if(counter++ > 4){
				isDone = true;
			}
		}
	}
}
