package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String [] args) {
		JFrame window=new JFrame();  //this is just a frame ...components to this frame will be added in gamepanel.

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		window.setTitle("SimpleAdventure");
		
		GamePanel gp=new GamePanel();
		window.add(gp);
		window.pack();
		
		
		window.setLocationRelativeTo(null); // brings the window to the center of the screen
		window.setVisible(true); //must
		
		gp.startGameThread();
		gp.setUp();
		
	
	}
	

}
