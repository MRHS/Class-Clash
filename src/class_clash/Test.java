package class_clash;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Test {
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	
	private static ArrayList<Player> players = new ArrayList<Player>();
	
	public static void main(String[] args){
		addPlayers();
		JFrame frame = new JFrame("ClassClash Test");
		frame.setSize(WIDTH,HEIGHT);
		frame.add(new Arena(WIDTH,HEIGHT,players));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.requestFocus();
	}
	
	private static void addPlayers(){
		players.add(new ExamplePlayer(10,10));
	}
}
