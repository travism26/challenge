package challenge;

import java.util.*;
public class node {


	public static void main(String[] args)
	{
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
		int node = 3;
		for (int[] vertice : vertex)
		{
			System.out.println( vertice[0] + " " + vertice[1]);
			
			if(Arrays.asList(vertex).contains(2))
			{
				System.out.println("Im here");
			}
		}
	}
}
