package class_clash;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ClassClashPanel extends RPanel{
	
	//Index of player currently performing their turn
	private int turnIndex = 0;
	
	//Array list of all the players
	private ArrayList<Player> players = new ArrayList<Player>();
	
	//Current arena
	private Arena arena;
	
	public ClassClashPanel(int pWidth, int pHeight, Arena startArena) {
		super(pWidth, pHeight);
		arena = startArena;
		players.add(new ExamplePlayer(10,10));
		players.add(new ExamplePlayer(50,10));
	}
	
	
	public void setPlayers(ArrayList<Player> players){
		this.players = players;
	}
	
	public void addPlayer(Player player){
		players.add(player);
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
