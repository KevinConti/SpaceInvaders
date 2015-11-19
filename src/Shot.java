import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;


public class Shot extends MovingScreenObject {

	/**
	 * @param location
	 * @param size
	 * @param i
	 */
	public Shot(Point location, Rectangle size, Image i) {
		super(location, size, i);
	}
	
	/**
	 * To draw a shot, it is rotated by its orientation.
	 * @param g The graphics object.
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		g2.setColor(Color.white);
		Rectangle2D.Double ray =
				new Rectangle2D.Double(location.x, location.y,
						size.getWidth(), size.getHeight());
		AffineTransform at = g2.getTransform();
		at.rotate(Math.toRadians(angle), location.x, location.y);
		g2.setTransform(at);
		g2.fill(ray);
		g2.setTransform(old);
		
	}

}
