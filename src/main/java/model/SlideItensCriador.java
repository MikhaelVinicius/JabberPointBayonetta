package model;

import View.BitmapItem;
import View.SlideItem;
import View.TextItem;

public class SlideItensCriador {
  
  public SlideItem createSlideItem(String kind, int level, String text) {
    if (kind.equals("text")) {
      return new TextItem(level, text);
    } else if (kind.equals("image")) {
      return new BitmapItem(level, text);
    } else {
      throw new IllegalArgumentException("Unknown SlideItem kind: " + kind);
    }
  }
  
}
