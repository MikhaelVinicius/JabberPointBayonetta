package View;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public abstract class SlideItem implements Drawable, Bounded {

  private int level = 0;

  public SlideItem(int lev) {
    level = lev;
  }

  public SlideItem() {
    this(0);
  }

  public int getLevel() {
    return level;
  }

  
}
