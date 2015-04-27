package challenge;

import java.util.ArrayList;
import java.util.HashMap;

import challenge.mazeHunter;
import static org.junit.Assert.*;

import org.junit.Test;

public class mazeHunterTest {

	/*
	 * test data
	 */
	
	//door open
	public int[][] vertex = { { 0, 3 }, { 2, 5 }, { 3, 4 }, { 4, 5 }, { 4, 7 },
			{ 7, 8 }, { 5, 8 }, {8,10}, {10,15}, {10,13}, {10,14}, {14,24} };
	public int mazeSize = 3;
	mazeHunter maze = new mazeHunter();
	
	public void test()
	{
		fail("Not yet implemented");
	}
	@Test
	public void testShortestPath(){
		HashMap<Integer, ArrayList<Integer>> shortestPath = new HashMap<Integer, ArrayList<Integer>>();

		shortestPath = maze.getShortestPath(vertex, 0);
		String solution = maze.findSolution(shortestPath, mazeSize);
		System.out.println(solution);
		assertEquals("Expect: 8 | 0 3 4 7 8 ", "8 | 0 3 4 7 8 ", solution);
	}
	
	@Test
	public void testCase4(){
		HashMap<Integer, ArrayList<Integer>> shortestPath = new HashMap<Integer, ArrayList<Integer>>();
		mazeSize = 4;
		shortestPath = maze.getShortestPath(vertex, 0);
		String solution = maze.findSolution(shortestPath, mazeSize);
		System.out.println(solution);
		assertEquals("Expect: 8 | 0 3 4 7 8 ", "15 | 0 3 4 7 8 10 15 ", solution);
	}
	
	@Test
	public void testCase5(){
		HashMap<Integer, ArrayList<Integer>> shortestPath = new HashMap<Integer, ArrayList<Integer>>();
		mazeSize = 5;
		shortestPath = maze.getShortestPath(vertex, 0);
		String solution = maze.findSolution(shortestPath, mazeSize);
		System.out.println(solution);
		assertEquals("Expect: 8 | 0 3 4 7 8 ", "24 | 0 3 4 7 8 10 14 24 ", solution);
	}

}
