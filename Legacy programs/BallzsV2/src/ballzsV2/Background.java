package ballzsV2;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Background extends Component {
	public Color color;

	public Background(Color color2) {
		color2 = color;
	}
	public void paintComponent(Graphics page) {

		page.setColor(color);
		page.fillRect(0, 0, Settings.width, Settings.height);
	}
}
