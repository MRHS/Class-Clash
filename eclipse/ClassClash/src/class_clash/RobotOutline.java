package class_clash;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class RobotOutline {
	
	//Robot life
	private int life = 100;
	
	//Movement speed of the robot
	private int speed = 1;
	
	//Name of the robot
	private String name;
	
	//Coords for robot
	private int x;
	private int y;
	
	//X and Y Variation
	private int xVar = 0;
	private int yVar = 0;
	
	//Width and height
	private int width = 10;
	private int height = 10;
	
	public RobotOutline(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void update(){
		x += xVar * speed;
		y += yVar * speed;
		autonomous();
	}
	
	public void setDirection(Movement direction){
		xVar = direction.xVar();
		yVar = direction.yVar();
	}
	
	public void render(Graphics g){
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	}
	
	protected abstract void autonomous();
}
