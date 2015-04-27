package challenge;

import java.util.*;

public class mazeHunter {

	public static void println(Object val)
	{
		System.out.println(val.toString());
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
				}
			}
		}
		return nodes;
	}

	// Dijkstra's Algorithm.
	public HashMap<Integer, ArrayList<Integer>> getShortestPath(int[][] vertex,
			int mazeSize)
	{
		boolean isDone = false;
		HashMap<Integer, ArrayList<Integer>> shortestPath = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> cloud = new ArrayList<Integer>();
		ArrayList<Integer> nodes = new ArrayList<>();

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
				for (int[] vertice : vertex)
				{
					// Arrays.asList(vertice).contains(node) <-- not working for
					// some reason :(
					if ((vertice[0] == node) || (vertice[1] == node))
					{
						int toNode = vertice[0] == node ? vertice[1]
								: vertice[0];
						if (!cloud.contains(toNode))
						{
							if (nextShortest == -1)
							{
								nextShortest = toNode;
								currentPath.add(toNode);
								path = currentPath;
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
			if (!(nextShortest == -1))
			{
				shortestPath.put(nextShortest, path);
				cloud.add(nextShortest);
			}

			if (nodes.size() == cloud.size())
			{
				isDone = true;
			}
		}

		return shortestPath;
	}
	
	public int[] parseData(String data)
	{
		String[] parts;
		int results[];
		int index;

		parts = data.split(",");
		results = new int[parts.length];
		for (index = 0; index < parts.length; ++index)
		{
			results[index] = Integer.parseInt(parts[index].trim());
		}

		return results;
	}
	
	public String findSolution(HashMap<Integer, ArrayList<Integer>> shortestPath, int mazeSize){
		String solution = "";
		int exitDoor = mazeSize * mazeSize -1;
		for (Map.Entry<Integer, ArrayList<Integer>> entry : shortestPath
				.entrySet())
		{
			if (entry.getKey()== exitDoor)
			{
				solution=entry.getKey() + " | " + listPrint(entry.getValue());
			}
			//println(entry.getKey() + " | " + listPrint(entry.getValue()));
			
		}
		if(solution.isEmpty()){
			solution = "Unreachable exit :(";
		}
		return solution;
	}

	public static void main(String[] args)
	{

		mazeHunter meh = new mazeHunter();
		Scanner in = new Scanner(System.in);
		ArrayList<String> inputValues = new ArrayList<String>();
		int mazeSize =0;
		String input;
		input = in.nextLine();
		mazeSize = Integer.parseInt(input);
		while (true)
		{
			input = in.nextLine();
			if (input.isEmpty())
			{
				break;
			} else
			{
				inputValues.add(input);
			}
		}
		int[][] vertices = new int[inputValues.size()][2];
		// parse the data
		for (int i = 0; i < vertices.length; i++)
		{
			vertices[i] = meh.parseData(inputValues.get(i));
		}
		
		//used for testing :)
		int[][] vertex = { { 0, 3 }, { 2, 5 }, { 3, 4 }, { 4, 5 }, { 4, 7 },
				{ 7, 8 }, { 5, 8 }, {8,10}, {10,15} };

		HashMap<Integer, ArrayList<Integer>> shortestPath = new HashMap<Integer, ArrayList<Integer>>();

		shortestPath = meh.getShortestPath(vertices, 0);
		String solution = meh.findSolution(shortestPath, mazeSize);
		
		//answer
		println(solution);
	}

	// Methods used for testing

	public static void printInputDate(int[][] input)
	{
		for (int i = 0; i < input.length; i++)
		{
			System.out.println();
			for (int j = 0; j < 2; j++)
			{
				System.out.print(input[i][j] + " ");
			}
		}

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

}
