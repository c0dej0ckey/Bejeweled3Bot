package utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import solver.Solver;


public class ThreadHandler implements ActionListener, KeyListener {
	
	public MyThread thread;
	public Runnable runnable;
	public JFrame frame;
	public JButton startButton;
	public JPanel panel;
	private Solver solver;
	
	public ThreadHandler(MyThread t, Solver solver){
		this.thread = t;
		this.solver = solver;
		frame = new JFrame();
		frame.setSize(60, 60);
		frame.setLocation(800, 0);
		
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.addKeyListener(this);
		startButton = new JButton("Stop");
		startButton.addActionListener(this);
		panel.add(startButton);
		frame.add(panel);
		
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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char keyTyped = e.getKeyChar();
		if(keyTyped == KeyEvent.VK_SPACE){
			thread.shutdown();
		}
		
	}

}
