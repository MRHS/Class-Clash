package class_clash;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Arena extends RPanel{

	//Index of player currently performing their turn
	private int turnIndex = 0;

	//ArrayList containing all of the players
	private ArrayList<Player> players;

	public Arena(int pWidth, int pHeight, ArrayList<Player> players) {
		super(pWidth, pHeight);
	}

	public boolean addPlayer(Player player) {
		return this.players.add(player);
	}
	
	/** Return if a player is at the specified location */
	public boolean isPlayer(Location location){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getLocation().equals(location))
				return true;
		}
		return false;
	}

	/** Returns the distance between two locations */
	public int getDistance(Location location1, Location location2){
		int x1 = location1.getX();
		int y1 = location1.getY();
		int x2 = location2.getX();
		int y2 = location2.getY();

		//Calculate distance
		return (int)Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
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
		players.get(turnIndex).update();

		//Update turn index
		turnIndex++;
		if(turnIndex >= players.size())
			turnIndex = 0;
	}

	@Override
	protected void drawGame(Graphics g) {
		//Render all of the players
		for(int i = 0; i < players.size(); i++){
			players.get(i).render(g);
		}
	}
}
