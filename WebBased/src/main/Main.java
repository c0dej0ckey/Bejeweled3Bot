package main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.rmi.server.UID;

import solver.Solver;
import utility.Grid;
import utility.MyThread;
import utility.ThreadHandler;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Robot robot = null;
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			
			e.printStackTrace();
		}
		Grid grid = new Grid();
		Solver solver = new Solver(grid);
		
		MyThread t = new MyThread(solver);
		ThreadHandler threadHandler = new ThreadHandler(t, solver);
		threadHandler.start();
		
		

	}
	
	

}
