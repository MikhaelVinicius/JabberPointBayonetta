package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Slide;

public class TextItem extends SlideItem implements Drawable, Bounded {
	
	private String text;
	private static final String EMPTY_TEXT = "No Text Given";

	public TextItem(int level, String string) {
	    super(level);
	    text = string;
	}

	public TextItem() {
	    this(0, EMPTY_TEXT);
	}

	public String getText() {
	    return text == null ? "" : text;
	}

	public AttributedString getAttributedString(Style style, float scale) {
	    AttributedString attrStr = new AttributedString(getText());
	    attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
	    return attrStr;
	}

	private List<TextLayout> getLayouts(Graphics g, Style s, float scale) {
	    List<TextLayout> layouts = new ArrayList<TextLayout>();

	    AttributedString attrStr = getAttributedString(s, scale);
	    Graphics2D g2d = (Graphics2D) g;

	    FontRenderContext frc = g2d.getFontRenderContext();
	    LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);

	    float wrappingWidth = (Slide.WIDTH - s.indent) * scale;

	    while (measurer.getPosition() < getText().length()) {
	        TextLayout layout = measurer.nextLayout(wrappingWidth);
	        layouts.add(layout);
	    }

	    return layouts;
	}

	public String toString() {
	    return "TextItem[" + getLevel() + "," + getText() + "]";
	}

	@Override
	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
	    List<TextLayout> layouts = getLayouts(g, style, scale);

	    int xsize = 0, ysize = (int) (style.leading * scale);

	    Iterator<TextLayout> iterator = layouts.iterator();

	    while (iterator.hasNext()) {
	        TextLayout layout = iterator.next();
	        Rectangle2D bounds = layout.getBounds();

	        if (bounds.getWidth() > xsize) {
	            xsize = (int) bounds.getWidth();
	        }

	        if (bounds.getHeight() > 0) {
	            ysize += bounds.getHeight();
	        }
	        ysize += layout.getLeading() + layout.getDescent();
	    }

	    return new Rectangle((int) (style.indent * scale), 0, xsize, ysize);
	}

	@Override
	public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
	    if (text == null || text.length() == 0) {
	        return;
	    }

	    List<TextLayout> layouts = getLayouts(g, style, scale);
	    Point pen = new Point(x + (int) (style.indent * scale), y + (int) (style.leading * scale));

	    Graphics2D g2d = (Graphics2D) g;
	    g2d.setColor(style.color);

	    Iterator<TextLayout> it = layouts.iterator();

	    while (it.hasNext()) {
	        TextLayout layout = it.next();

	        pen.y += layout.getAscent();
	        layout.draw(g2d, pen.x, pen.y);

	        pen.y += layout.getDescent();
	    }
	}
}
