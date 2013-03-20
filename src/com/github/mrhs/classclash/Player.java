package com.github.mrhs.classclash;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Player {

    // Player life
    private int life = 100;

    // Movement speed of the Player
    private final int speed = 1;

    // Coords for Player
    private int x;
    private int y;

    // X and Y Variation
    private int xVar = 0;
    private int yVar = 0;

    // Width and height
    private final int width = 10;
    private final int height = 10;

    // Arena
    private Arena arena;

    // Number of kills the player has gotten
    private int kills = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Sets the arena that the player is in */
    public void setArena(Arena arena) {
        this.arena = arena;
    }

    /** Adds a kill */
    public void killConfirmed() {
        kills++;
    }

    /** Sets direction that the robot should move */
    public void setDirection(Direction direction) {
        xVar = direction.xVar();
        yVar = direction.yVar();
    }

    /** Returns the player's current health */
    public int getHealth() {
        return life;
    }

    /** Reduces a player's life by a certain amount */
    public void takeDamage(int amount) {
        if (amount > 0) {
            this.life -= amount;
        }
    }

    /** Sets a player's life */
    public void setLife(int life) {
        this.life = life;
    }

    /** Heals a player by a specific amount */
    public void healPlayer(int amount) {
        if (amount > 0) {
            this.life += amount;
        }
    }

    /** Return the number of kills the player has */
    public int getKills() {
        return kills;
    }

    /** Render the player */
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(this.x, this.y, width, height);
    }

    /** Turn to face the target player */
    public void turnTo(Location location) {
        int plyrX = location.getX();
        int plyrY = location.getY();

        boolean toLeft = false;
        boolean toRight = false;
        boolean above = false;
        boolean below = false;

        // Calculate direction from current location
        if (plyrX > x) {
            toRight = true;
        } else if (plyrX < x) {
            toLeft = true;
        }

        if (plyrY > y) {
            above = true;
        } else if (plyrY < y) {
            below = true;
        }

        // Use booleans to get direction
        Direction direction;

        if (toLeft) {
            if (above) {
                direction = Direction.NORTH_WEST;
            } else if (below) {
                direction = Direction.SOUTH_WEST;
            } else {
                direction = Direction.WEST;
            }
        } else if (toRight) {
            if (above) {
                direction = Direction.NORTH_EAST;
            } else if (below) {
                direction = Direction.SOUTH_EAST;
            } else {
                direction = Direction.EAST;
            }
        } else {
            direction = Direction.NEUTRAL;
        }

        // Set the new direction
        setDirection(direction);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Sets the X location of the player.
     * 
     * @param x
     *        The new X location.
     */
    void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the Y location of the player.
     * 
     * @param y
     *        The new Y location.
     */
    void setY(int y) {
        this.y = y;
    }

    protected abstract void runTurn(Arena arena);
}
