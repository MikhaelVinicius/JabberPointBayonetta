package View;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public interface Drawable {
	  void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer);
	}

	