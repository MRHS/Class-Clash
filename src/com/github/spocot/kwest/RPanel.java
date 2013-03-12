package com.github.spocot.kwest;

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

public abstract class RPanel extends JPanel implements Runnable{

	//Desired FPS
	protected int fps;
	
	//Ideal sleep time (ms)
	protected long period;

	//Default background color
	protected final Color DBACKGROUND = Color.white;

	//Background color variable
	protected Color pBackground;

	//Default height and width
	protected final int DWIDTH = 500;
	protected final int DHEIGHT = 400;

	//Height and width variables
	protected int pWidth;
	protected int pHeight;

	//Animator thread
	protected Thread animator;

	//Number of frames with a delay of 0ms before the animation thread yields to other running threads
	protected static final int NO_DELAYS_PER_YIELD = 16;

	//Number of frames that can be skipped in any one animation loop
	protected static final int MAX_FRAME_SKIPS = 5;

	//Boolean variable for pausing
	protected volatile boolean isPaused = false;

	//Boolean variables for game termination
	protected volatile boolean running;
	protected volatile boolean gameOver;

	//Variables for off-screen rendering
	protected Graphics g;
	protected Image img = null;
	
	public RPanel(int pWidth, int pHeight){
		fps = 40;
		calculatePeriod(fps);
		this.pWidth = pWidth;
		this.pHeight = pHeight;
		pBackground = DBACKGROUND;
		setBackground(pBackground);
		setPreferredSize(new Dimension(pWidth, pHeight));
		setFocusable(true);
		requestFocus();
		addKeys();
		addMouse();
	}
	
	/** Change the background color */
	public void setColor(Color color){
		pBackground = color;
	}
	
	/** Calculates the period */
	public void calculatePeriod(int FPS){
		period = 1000000000L / FPS;
	}

	/** Wait for panel to be added to frame/applet before starting the game */
	public void addNotify(){
		super.addNotify();
		startGame();
	}

	/** Handles key events */
	protected void addKeys(){
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				keyPressedEvents(e);
			}
			public void keyReleased(KeyEvent e){
				keyReleasedEvents(e);
			}
		});
	}
	
	/** Handles Mouse Events */
	protected void addMouse(){
		addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent arg0) {
				mouseDraggedEvents(arg0);
			}
			public void mouseMoved(MouseEvent arg0) {
				mouseMovedEvents(arg0);
			}
		});
	}
	
	protected abstract void mouseDraggedEvents(MouseEvent e);
	protected abstract void mouseMovedEvents(MouseEvent e);
	
	protected abstract void keyPressedEvents(KeyEvent e);
	protected abstract void keyReleasedEvents(KeyEvent e);

	/** Initialize and start the game thread */
	protected void startGame(){
		if(animator == null || !running){
			animator = new Thread(this);
			animator.start();
		}
	}

	/** Called to pause the game */
	public void pauseGame(){
		isPaused = true;
	}

	/** Called to resume the game */
	public void resumeGame(){
		isPaused = false;
	}

	/** Stops the thread */
	public void stopGame(){
		running = false;
	}

	/** Update, render, sleep */
	public void run() {
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		int noDelays = 0;
		long excess = 0L;

		//Get time before running
		beforeTime = System.nanoTime();

		running = true;
		while(running){

			//Update game state
			gameUpdate();

			//Render to a buffer
			gameRender();

			//Draw buffer to screen
			paintScreen();

			//Get time after updating and rendering
			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (period - timeDiff) - overSleepTime;

			if(sleepTime > 0){
				try{
					Thread.sleep(sleepTime/1000000L);
				}catch(InterruptedException e){}
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			}else{
				excess -= sleepTime;
				overSleepTime = 0L;

				if(++noDelays >= NO_DELAYS_PER_YIELD){
					Thread.yield();
					noDelays = 0;
				}
			}

			beforeTime = System.nanoTime();

			//Update game state without rendering if frame animation is taking too long
			int skips = 0;
			while((excess > period) && (skips < MAX_FRAME_SKIPS)){
				excess -= period;
				gameUpdate();
				skips++;
			}
		}
		System.exit(0);
	}

	/** Draw buffer to screen */
	protected void paintScreen(){
		Graphics graphics;
		try{
			graphics = this.getGraphics();
			if((graphics != null) && (img != null))
				graphics.drawImage(img,0,0,null);
			//Sync the display for consistency across systems
			Toolkit.getDefaultToolkit().sync();
			graphics.dispose();
		}catch(Exception e){
			System.out.println("Graphics context error: " + e);
		}
	}

	/** Update game state(s) */
	protected void gameUpdate(){
		if(!isPaused && !gameOver){
			updateGame();
		}
	}
	
	/** Update the specific game components */
	protected abstract void updateGame();

	/** Draw the current frame to an image buffer */
	protected void gameRender(){
		if(img == null){
			//Create buffer
			img = createImage(pWidth, pHeight);
			if(img == null){
				System.out.println("Buffer is null");
				return;
			}else{
				g = img.getGraphics();
			}
		}

		//Clear the background
		g.setColor(pBackground);
		g.fillRect(0,0,pWidth,pHeight);
		
		//Draw game components
		drawGame(g);
	}
	
	/** Draw the specific game components */
	protected abstract void drawGame(Graphics g);
}
