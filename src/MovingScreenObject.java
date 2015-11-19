import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class MovingScreenObject extends ScreenObject {
	
	//Changed these two to protected - Kevin
	protected MyVector vector;
	protected Image myImage;
	
	protected int age;
	protected int maximumAge;

	protected double angle;

	public MovingScreenObject(Point location, Rectangle size, Image i) {
		super(location, size);
		vector = new MyVector(0);
		myImage = i;
		age = 0;
		maximumAge = Integer.MAX_VALUE;
	}
	
	public void move(){
		age++;
		location.x += vector.getChangeX();
		
		if (location.x > Screen.screenWidth) {
			location.x -= Screen.screenWidth;
		}
		if (location.x < 0) {
			location.x += Screen.screenWidth;
		}
	
	}
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the maximumAge
	 */
	public int getMaximumAge() {
		return maximumAge;
	}

	/**
	 * @param maximumAge the maximumAge to set
	 */
	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}
	
	/**
	 * @return the vector
	 */
	public MyVector getVector() {
		return vector;
	}

	/**
	 * @param vector the vector to set
	 */
	public void setVector(MyVector vector) {
		this.vector = vector;
	}

	/**
	 * @return the myImage
	 */
	public Image getMyImage() {
		return myImage;
	}

	/**
	 * @param myImage the myImage to set
	 */
	public void setMyImage(Image myImage) {
		this.myImage = myImage;
	}
	
	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}
	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	@Override
	public void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(myImage, location.x, location.y,
				size.width, size.height,
				null);

	}

}
