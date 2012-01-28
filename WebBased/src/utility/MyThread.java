package utility;

import java.awt.AWTException;

import solver.Solver;

public class MyThread extends Thread{
	
	private Solver solver;
	public volatile boolean running = true;
	
	public MyThread(Solver solver){
		this.solver = solver;
	}

	@Override
	public void run() {
		while(running){
		Grid grid = new Grid();
		Solver solver = new Solver(grid);
		try {
			solver.generateGrid();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		while(!running){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		run();

	}
	
	public void shutdown(){
		running = false;
	}
	
	public void begin(){
		running = true;
	}

}
