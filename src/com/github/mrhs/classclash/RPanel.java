package com.github.mrhs.classclash;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public abstract class RPanel extends JPanel implements Runnable {

    // Desired FPS
    private final int fps;

    // Ideal sleep time (ms)
    private long period;

    // Default background color
    private final Color DBACKGROUND = Color.white;

    // Background color variable
    private Color panelBackground;

    // Default height and width
    private final int DEFAULT_WIDTH = 500;
    private final int DEFAULT_HEIGHT = 400;

    // Height and width variables
    private final int panelWidth;
    private final int panelHeight;

    // Animator thread
    private Thread animator;

    // Number of frames with a delay of 0ms before the animation thread yields
    // to other running threads
    private static final int DELAYS_PER_YIELD = 16;

    // Number of frames that can be skipped in any one animation loop
    private static final int MAX_FRAME_SKIPS = 5;

    // Boolean variable for pausing
    private volatile boolean isPaused = false;

    // Boolean variables for game termination
    private volatile boolean running;
    private volatile boolean gameOver;

    // Variables for off-screen rendering
    private Graphics g;
    private Image img = null;

    public RPanel(int pWidth, int pHeight) {
        fps = 40;
        calculatePeriod(fps);
        this.panelWidth = pWidth;
        this.panelHeight = pHeight;
        this.panelBackground = DBACKGROUND;
        setBackground(this.panelBackground);
        setPreferredSize(new Dimension(pWidth, pHeight));
        setFocusable(true);
        requestFocus();
        addKeys();
        addMouse();
    }

    /** Change the background color */
    public void setColor(Color color) {
        this.panelBackground = color;
    }

    /** Calculates the period */
    public void calculatePeriod(int FPS) {
        this.period = 1000000000L / FPS;
    }

    /** Wait for panel to be added to frame/applet before starting the game */
    @Override
    public void addNotify() {
        super.addNotify();
        startGame();
    }

    /** Handles key events */
    private void addKeys() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyPressedEvents(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyReleasedEvents(e);
            }
        });
    }

    /** Handles Mouse Events */
    private void addMouse() {
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                mouseDraggedEvents(arg0);
            }

            @Override
            public void mouseMoved(MouseEvent arg0) {
                mouseMovedEvents(arg0);
            }
        });
    }

    public abstract void mouseDraggedEvents(MouseEvent e);

    public abstract void mouseMovedEvents(MouseEvent e);

    public abstract void keyPressedEvents(KeyEvent e);

    public abstract void keyReleasedEvents(KeyEvent e);

    /** Initialize and start the game thread */
    private void startGame() {
        if (animator == null || !running) {
            animator = new Thread(this);
            animator.start();
        }
    }

    /** Called to pause the game */
    public void pauseGame() {
        isPaused = true;
    }

    /** Called to resume the game */
    public void resumeGame() {
        isPaused = false;
    }

    /** Stops the thread */
    public void stopGame() {
        running = false;
    }

    /** Update, render, sleep */
    @Override
    public void run() {
        long beforeTime, afterTime, timeDiff, sleepTime;
        long overSleepTime = 0L;
        int noDelays = 0;
        long excess = 0L;

        // Get time before running
        beforeTime = System.nanoTime();

        running = true;
        while (running) {

            // Update game state
            gameUpdate();

            // Render to a buffer
            gameRender();

            // Draw buffer to screen
            paintScreen();

            // Get time after updating and rendering
            afterTime = System.nanoTime();
            timeDiff = afterTime - beforeTime;
            sleepTime = (period - timeDiff) - overSleepTime;

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000L);
                } catch (InterruptedException e) {
                    // TODO: Handle this exception
                }
                overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
            } else {
                excess -= sleepTime;
                overSleepTime = 0L;

                if (++noDelays >= DELAYS_PER_YIELD) {
                    Thread.yield();
                    noDelays = 0;
                }
            }

            beforeTime = System.nanoTime();

            // Update game state without rendering if frame animation is taking
            // too long
            int skips = 0;
            while ((excess > period) && (skips < MAX_FRAME_SKIPS)) {
                excess -= period;
                gameUpdate();
                skips++;
            }
        }
        System.exit(0);
    }

    /** Draw buffer to screen */
    private void paintScreen() {
        Graphics graphics;
        try {
            graphics = this.getGraphics();
            if ((graphics != null) && (img != null)) {
                graphics.drawImage(img, 0, 0, null);
            }
            // Sync the display for consistency across systems
            Toolkit.getDefaultToolkit().sync();
            graphics.dispose();
        } catch (Exception e) {

            System.out.println("Graphics context error: " + e);
        }
    }

    /** Update game state(s) */
    private void gameUpdate() {
        if (!isPaused && !gameOver) {
            updateGame();
        }
    }

    /** Update the specific game components */
    public abstract void updateGame();

    /** Draw the current frame to an image buffer */
    private void gameRender() {
        if (img == null) {
            // Create buffer
            img = createImage(panelWidth, panelHeight);
            if (img == null) {
                System.out.println("Buffer is null");
                return;
            } else {
                g = img.getGraphics();
            }
        }

        // Clear the background
        g.setColor(this.panelBackground);
        g.fillRect(0, 0, panelWidth, panelHeight);

        // Draw game components
        drawGame(g);
    }

    /** Return panel width */
    @Override
    public int getWidth() {
        return panelWidth;
    }

    /** Return panel height */
    @Override
    public int getHeight() {
        return panelHeight;
    }

    /** Draw the specific game components */
    public abstract void drawGame(Graphics g);
}
