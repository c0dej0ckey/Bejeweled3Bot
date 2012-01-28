package utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

import solver.Solver;


public class ThreadHandler implements ActionListener {
	
	public MyThread thread;
	public Runnable runnable;
	public JFrame frame;
	public JButton startButton;
	private Solver solver;
	
	public ThreadHandler(MyThread t, Solver solver){
		this.thread = t;
		this.solver = solver;
		frame = new JFrame();
		frame.setSize(60, 60);
		frame.setLocation(1450, 465);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startButton = new JButton("Stop");
		startButton.addActionListener(this);
		frame.add(startButton);
		
	}
	
	public void start(){
		
		thread.start();
		
	}

	 

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton){
			if(thread.running == true){
			thread.shutdown();
			startButton.setText("Start");
			}
			else {
				startButton.setText("Stop");
				thread.begin();
				
			}
		}
		if(e.getSource() == frame){
			thread = null;
		}
		
	}

}
