import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Ship extends MovingScreenObject {

	public Ship(Point location, Rectangle size, Image i) {
		super(location, size, i);
		angle = 0;
	}

}
