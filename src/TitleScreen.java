import javax.swing.JFrame;

public class TitleScreen extends JFrame {

	public TitleScreen() {
		setTitle("Space Invaders");
		Screen screen = new Screen(this);
		add(screen);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

		screen.setFocusable(true);
		screen.requestFocusInWindow();
		
		setVisible(true);
		
	}

	public static void main(String[] args) {
		TitleScreen window = new TitleScreen();
		
	}

}
