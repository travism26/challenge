package challenge;

import java.util.*;

public class mazeHunter {

	public static void println(Object val)
	{
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
		String output = "";
		for (int i = 0; i < list.size(); i++)
		{
			output += list.get(i) + " ";
		}
		return output;
	}

	public static int[][] setMaze(int[][] mazeIn)
	{
		int val = 0;
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

	public ArrayList<Integer> findAllNodes(int[][] vertices)
	{
		ArrayList<Integer> nodes = new ArrayList<>();
		for (int i = 0; i < vertices.length; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				if (!nodes.contains(vertices[i][j]))
				{
					nodes.add(vertices[i][j]);
				} else
				{
					;
				}
			}
		}
		return nodes;
	}

	public HashMap<Integer, ArrayList<Integer>> getShortestPath(int[][] vertex,
			int mazeSize)
	{
		boolean isDone = false;
		HashMap<Integer, ArrayList<Integer>> shortestPath = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> cloud = new ArrayList<Integer>();
		ArrayList<Integer> nodes = new ArrayList<>();
		// init stuff
		shortestPath.put(0, new ArrayList<Integer>());
		shortestPath.get(0).add(0);
		cloud.add(0);
		nodes = findAllNodes(vertex);
		while (isDone == false)
		{
			ArrayList<Integer> path = new ArrayList<Integer>();
			int nextShortest = -1;
			for (int i = 0; i < cloud.size(); i++)
			{
				int node = cloud.get(i);
				ArrayList<Integer> currentPath = new ArrayList<Integer>();
				currentPath.addAll(shortestPath.get(node));
				//println("--------" + node);
				for (int[] vertice : vertex)
				{
					// Arrays.asList(vertice).contains(node) <-- not working for
					// some reason :(
					if ((vertice[0] == node) || (vertice[1] == node))
					{
						//println("Vertex: " + vertice[0] + " " + vertice[1]);
						int toNode = vertice[0] == node ? vertice[1]
								: vertice[0];
						if (!cloud.contains(toNode))
						{
							//println(toNode + " is NOT IN THE CLOUD");
							if (nextShortest == -1)
							{
								// this means it found first vertex. make it
								// shortest node
								nextShortest = toNode;
								currentPath.add(toNode);
								path = currentPath;
								//println("SET SOME STUFF");
							} else if (path.size() > currentPath.size())
							{
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
			if (nodes.size() == cloud.size())
			{
				isDone = true;
			}
		}

		return shortestPath;
	}

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		mazeHunter meh = new mazeHunter();

		int[][] vertex = { { 0, 3 }, { 2, 5 }, { 3, 4 }, { 4, 5 }, { 4, 7 },
				{ 7, 8 }, { 5, 8 } };

		HashMap<Integer, ArrayList<Integer>> shortestPath = new HashMap<Integer, ArrayList<Integer>>();

		shortestPath = meh.getShortestPath(vertex, 0);
		for (Map.Entry<Integer, ArrayList<Integer>> entry : shortestPath
				.entrySet())
		{
			println(entry.getKey() + "| " + listPrint(entry.getValue()));
		}
	}
}
