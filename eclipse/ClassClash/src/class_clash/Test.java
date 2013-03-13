package class_clash;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args){
		JFrame frame = new JFrame("Class Clash Test");
		frame.setSize(500,400);
		frame.add(new ClassClashPanel(500,400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
