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
}
