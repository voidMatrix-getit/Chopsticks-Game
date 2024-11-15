package sahu.chopstick_game;

import java.awt.event.KeyEvent;

public class Practice  {

	
	public void Traverse(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_UP) {
			
			System.out.println("UP");
		}
		
		if(code == KeyEvent.VK_DOWN) {
			
			System.out.println("DOWN");
		}
		
	}
	
}
