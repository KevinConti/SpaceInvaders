import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Screen extends JPanel implements KeyListener {

	public static int screenWidth = 630;
	public static int screenHeight = 500;
	public static ImageIcon titleImg = new ImageIcon("TitleScreen.jpg");
	public static ImageIcon shipImg = new ImageIcon("PlayerShip.png");
	public static ImageIcon shotImg = new ImageIcon("shot.gif");
	public JPanel startButton, gameScreen;
	public JButton button, button2;
	public Font buttonFont;
	public TitleScreen titleScreen;
	public JLabel background;

	private javax.swing.Timer timer;
	private ArrayList<ScreenObject> screenObjects;
	private Ship playerShip;


	public Screen(TitleScreen screen) {
		titleScreen = screen;
		setPreferredSize(new Dimension(screenWidth, screenHeight));
		setBackground(Color.yellow);
		screenObjects = new ArrayList<ScreenObject>();

		background = new JLabel(new ImageIcon("TitleScreen.jpg"));
		add(background);
		Layout();
		//add(startButton);
		setVisible(true);


	}

	private void Layout(){

		buttonFont = new Font("SansSerif", Font.BOLD, 30);
		startButton = new JPanel();
		startButton.setBackground(Color.black);

		//creates start button
		button = new JButton("Start Game!");
		//Sets button properties
		/**
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setForeground(Color.green);
		button.setFont(buttonFont);
		 */
		//adds the action listener to the button
		button.addActionListener(new StartButtonListener());

		//adds the button to the panel
		startButton.add(button);

		//creates quit button
		button2 = new JButton("Quit!");
		//Sets button properties
		/**
		button2.setOpaque(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button2.setForeground(Color.green);
		button2.setFont(buttonFont);
		 */
		//adds the action listener to the button
		button2.addActionListener(new StartButtonListener());

		//adds the button to the panel
		startButton.add(button2);

		//Adds the panel with the two buttons
		add(startButton);
		startButton.setVisible(true);


	}

	public void gameLayout(){

		gameScreen = new JPanel();
		//gameScreen.setBackground(Color.orange);

		// add player's ship to the arraylist
		int x = screenWidth/2;
		int y = screenHeight-75;
		playerShip = new Ship(new Point(x, y),
				new Rectangle(40,20),
				shipImg.getImage());
		//playerShip.setVector(new MyVector(0));
		playerShip.setAngle(0);
		screenObjects.add(playerShip);

		this.addKeyListener(this);
		timer = new javax.swing.Timer(30, new TimerListener());
		timer.start();
		add(gameScreen);

	}

	public void paintComponent(Graphics g) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();

		super.paintComponent(g);

		// draws the objects in the screenObjects arraylist
		for (ScreenObject obj : screenObjects) {
			obj.draw(g);
		}
	}

	public class StartButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			if (event.getActionCommand().equalsIgnoreCase("Start Game!")) {
				remove(background);
				remove(startButton);
				gameLayout();
				//gameScreen.setVisible(true);

			}
			//Closes the entire window
			else if (event.getActionCommand().equalsIgnoreCase("Quit!")) {
				titleScreen.dispose();

			}

		}

	}

	/**
	 * @return the timer
	 */
	public javax.swing.Timer getTimer() {
		return timer;
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(javax.swing.Timer timer) {
		this.timer = timer;
	}

	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			for (int i = 0; i < screenObjects.size(); i++){
				ScreenObject obj = screenObjects.get(i);
				if (obj instanceof MovingScreenObject){
					MovingScreenObject movingObj = (MovingScreenObject) obj;
					if (movingObj.getAge() > movingObj.getMaximumAge()){
						screenObjects.remove(movingObj);
					}
					else{
						movingObj.move();
						for (int j = 0; j < screenObjects.size(); j++){
							ScreenObject obj2 = screenObjects.get(j);
							if (obj2 instanceof MovingScreenObject){
								MovingScreenObject moving2 = (MovingScreenObject) obj2;
								if (movingObj == obj2){
									;//do nothing if they're the same object
								}
							}
						}
					}
				}
				double dx = playerShip.getVector().getChangeX();
				dx *= 0.996;
				if (Math.abs(dx) < 0.2) {
					dx = 0;
				}
				playerShip.getVector().setChangeX(dx);

			}
			repaint();
		}
	}

	//was trying different ways to get the cannon to move
	@Override
	public void keyPressed(KeyEvent event) {

		double dx = 
				5*Math.cos(
						Math.toRadians(
								playerShip.getAngle() - 90));
		int keyCode = event.getKeyCode();
		switch( keyCode ) { 

		case KeyEvent.VK_LEFT:
			Point pos1=playerShip.getLocation();
			if (pos1.x>=0) {
				playerShip.setLocation(new Point(pos1.x-5,pos1.y));
			}
			break;
		case KeyEvent.VK_RIGHT :
			Point pos2=playerShip.getLocation();
			if (pos2.x<=630) {
				playerShip.setLocation(new Point(pos2.x+5,pos2.y));
			}
			break;

		case KeyEvent.VK_SPACE:
			int x = (int) (playerShip.getLocation().x + playerShip.getSize().getWidth()/1.5);
			int y = (int) (playerShip.getLocation().y + playerShip.getSize().getHeight()/2);
			Shot shot = new Shot(new Point(x,y),
					new Rectangle(20,2), shotImg.getImage());
			dx = 
					15*Math.cos(Math.toRadians(playerShip.getAngle()
							- 90));

			shot.setVector(new MyVector(dx));
			shot.setAngle(playerShip.getAngle() - 90);
			shot.setMaximumAge(40);
			screenObjects.add(shot);


			break;

		}
		repaint();


	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}


}
