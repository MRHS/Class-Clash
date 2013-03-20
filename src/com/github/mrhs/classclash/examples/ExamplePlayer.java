package com.github.mrhs.classclash.examples;

import com.github.mrhs.classclash.Arena;
import com.github.mrhs.classclash.Direction;
import com.github.mrhs.classclash.Player;

public class ExamplePlayer extends Player {

    public ExamplePlayer(int x, int y) {
        super(x, y);
    }

    @Override
    protected final void runTurn(Arena arena) {
        setDirection(Direction.SOUTH_EAST);
        arena.movePlayer(this, 1, 1);
    }

}
