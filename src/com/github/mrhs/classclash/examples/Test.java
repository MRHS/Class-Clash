package com.github.mrhs.classclash.examples;

import javax.swing.JFrame;

import com.github.mrhs.classclash.Arena;

public class Test {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public static void main(String[] args) {
        Arena arena = new Arena(WIDTH, HEIGHT);
        arena.addPlayer(new ExamplePlayer(10, 10));

        JFrame frame = new JFrame("ClassClash Test");
        frame.setSize(WIDTH, HEIGHT);
        frame.add(arena);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocus();
    }
}
