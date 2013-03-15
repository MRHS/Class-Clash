package class_clash;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args){
		JFrame frame = new JFrame("Class Clash Test");
		frame.setSize(500,400);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new ExamplePlayer(10,10));
		players.add(new ExamplePlayer(50,10));
		Arena panel = new Arena(500,400,players);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
