package main;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.io.IOException;

import solver.Solver;
import utility.GemGrid;
import utility.MyThread;
import utility.ThreadHandler;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		while(true){
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {}
			
			robot.delay(2000);
			PointerInfo pointerInfo = MouseInfo.getPointerInfo();
			Point location = pointerInfo.getLocation();
			Color color = robot.getPixelColor((int)location.getX(), (int)location.getY());
			System.out.println(color.toString() + " " + location.getX() + " " + location.getY());
			
			
			
			
			
		}
		*/
		
		
		//while(true){
			/*
			Robot robot = null;
			
			try {
				robot = new Robot();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			robot.delay(2000);
			PointerInfo pointerInfo = MouseInfo.getPointerInfo();
			Point point = pointerInfo.getLocation();
			Color color = robot.getPixelColor((int)point.getX(), (int)point.getY());
			System.out.println(color.getRGB() + " " + point.getX() + " " + point.getY());
			
			*/
			
			GemGrid gemGrid  = new GemGrid();
			Solver solver = new Solver(gemGrid);
			MyThread thread = new MyThread(solver);
			ThreadHandler threadHandler = new ThreadHandler(thread, solver);
			threadHandler.start();
	
		
			
			
		//}
		

	}

}
