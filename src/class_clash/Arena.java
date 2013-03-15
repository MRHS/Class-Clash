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

	/** Return player closest to the provided Player */
	public Player getClosestPlayer(Player player){

		//Closest distance / player
		int closestDistance = getDistance(player,players.get(0));
		Player closestPlayer = players.get(0);

		//Calculate closest distance
		for(int i = 0; i < players.size(); i++){
			int distance = getDistance(player,players.get(i));
			if(distance < closestDistance){
				closestDistance = distance;
				closestPlayer = players.get(i);
			}
		}

		return closestPlayer;
	}
	
	/** Return if a player is at the specified location */
	public boolean isPlayer(Location location){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getLocation().equals(location))
				return true;
		}
		return false;
	}

	/** Returns the distance between two Player objects */
	public int getDistance(Player player1, Player player2){
		Location p1location = player1.getLocation();
		Location p2location = player2.getLocation();
		int p1x = p1location.getX();
		int p1y = p1location.getY();
		int p2x = p2location.getX();
		int p2y = p2location.getY();

		//Calculate distance
		return (int)Math.sqrt(Math.pow(p1x - p2x, 2) + Math.pow(p1y - p2y, 2));
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
