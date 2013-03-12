package com.github.spocot.kwest;

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
	
	//ArrayList of all other robots
	private ArrayList<RobotOutline> robots;
	
	public RobotOutline(ArrayList<RobotOutline> robots, int x, int y){
		this.robots = robots;
		this.x = x;
		this.y = y;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public void update(){
		x += xVar * speed;
		y += yVar * speed;
	}
	
	public void render(Graphics g){
		g.setColor(Color.black);
		g.fillOval(x, y, 10, 10);
	}
}
