package class_clash;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ClassClashPanel extends RPanel{
	
	//Index of robot currently performing their turn
	private int turnIndex = 0;
	
	//Array list of all the robots
	private ArrayList<RobotOutline> robots = new ArrayList<RobotOutline>();
	
	public ClassClashPanel(int pWidth, int pHeight) {
		super(pWidth, pHeight);
		addRobots();
	}
	
	private void addRobots(){
		robots.add(new ExampleRobot(0,0));
		//repeat for all the robots
	}

	@Override
	protected void mouseDraggedEvents(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void mouseMovedEvents(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyPressedEvents(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void keyReleasedEvents(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateGame() {
		robots.get(turnIndex).update();
		
		//Update turn index
		turnIndex++;
		if(turnIndex >= robots.size())
			turnIndex = 0;
	}

	@Override
	protected void drawGame(Graphics g) {
		//Render all of the robots
		for(int i = 0; i < robots.size(); i++){
			robots.get(i).render(g);
		}
	}

}
