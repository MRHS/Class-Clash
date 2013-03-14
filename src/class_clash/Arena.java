package class_clash;

import java.util.ArrayList;

public class Arena {
	
	private ArrayList<Player> players;
	
	public Arena(ArrayList<Player> players){
		this.players = players;
	}
	
	public boolean addPlayer(Player player) {
		return this.players.add(player);
	}
}
