package com.github.mrhs.classclash;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Arena extends RPanel {

    // Index of player currently performing their turn
    private int turnIndex = 0;

    // ArrayList containing all of the players
    private final ArrayList<Player> players = new ArrayList<Player>();

    public Arena(int pWidth, int pHeight) {
        super(pWidth, pHeight);
    }

    /**
     * Adds a player to the arena.
     * 
     * @param player
     *        The player to add
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /** Return if a player is at the specified location */
    public boolean isPlayer(int x, int y) {
        for (int i = 0; i < players.size(); i++) {
            if ((players.get(i).getX() == x) && (players.get(i).getY() == y)) {
                return true;
            }
        }
        return false;
    }

    /** Returns the distance between two locations */
    public int getDistance(Location location1, Location location2) {
        int x1 = location1.getX();
        int y1 = location1.getY();
        int x2 = location2.getX();
        int y2 = location2.getY();

        // Calculate distance
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public void mouseDraggedEvents(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMovedEvents(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressedEvents(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleasedEvents(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateGame() {
        players.get(turnIndex).update();

        // Update turn index
        turnIndex++;
        if (turnIndex >= players.size()) {
            turnIndex = 0;
        }
    }

    @Override
    public void drawGame(Graphics g) {
        // Render all of the players
        for (int i = 0; i < players.size(); i++) {
            players.get(i).render(g);
        }
    }
}
