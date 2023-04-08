package View;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public interface Bounded {
	  Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style);
	}
