package class_clash;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Player {
	
	//Player life
	private int life = 100;
	
	//Movement speed of the Player
	private int speed = 1;
	
	//Name of the Player
	private String name;
	
	//Coords for Player
	private int x;
	private int y;
	private Location location;
	
	//X and Y Variation
	private int xVar = 0;
	private int yVar = 0;
	
	//Width and height
	private int width = 10;
	private int height = 10;
	
	//Arena
	private Arena arena;
	
	//Number of kills the player has gotten
	private int kills = 0;
	
	public Player(int x, int y, Arena startArena){
		this.x = x;
		this.y = y;
		location = new Location(x,y);
		arena = startArena;
	}
	
	/** Update the player */
	public void update(){
		x += xVar * speed;
		y += yVar * speed;
		location.update(x, y);
		runTurn(arena);
	}
	
	/** Sets the arena that the player is in */
	public void setArena(Arena arena){
		this.arena = arena;
	}
	
	/** Adds a kill */
	public void killConfirmed(){
		kills++;
	}
	
	/** Sets direction that the robot should move */
	public void setDirection(Direction direction){
		xVar = direction.xVar();
		yVar = direction.yVar();
	}
	
	/** Returns the player's current health */
	public int getHealth(){
		return life;
	}
	
	/** Get player's current location */
	public Location getLocation(){
		return location;
	}
	
	public void render(Graphics g){
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}
	
	protected abstract void runTurn(Arena arena);
}
