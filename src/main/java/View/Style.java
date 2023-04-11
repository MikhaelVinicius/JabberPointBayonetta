package View;

import java.awt.Color;
import java.awt.Font;

public class Style {

  private static Style[] styles;

  private static final String FONTNAME = "Roboto";
  int indent;
  Color color;
  Font font;
  int fontSize;
  int leading;

  public static void createStyles() {
    styles = new Style[5];
    styles[0] = new Style(0, new Color(141, 107, 227), 48, 20); // nível 0
    styles[1] = new Style(20, new Color(155, 89, 182), 40, 10); // nível 1
    styles[2] = new Style(50, new Color(184, 80, 147), 36, 10); // nível 2
    styles[3] = new Style(70, new Color(218, 67, 98), 30, 10); // nivel 3
    styles[4] = new Style(90, new Color(242, 38, 19), 24, 10); // nível 4
  }

  public static Style getStyle(int level) {
    if (level >= styles.length) {
      level = styles.length - 1;
    }

    return styles[level];
  }

  public Style(int indent, Color color, int points, int leading) {
    this.indent = indent;
    this.color = color;
    font = new Font(FONTNAME, Font.PLAIN, fontSize = points);
    this.leading = leading;
  }

  public String toString() {
    return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
  }

  public Font getFont(float scale) {
    return font.deriveFont(fontSize * scale);
  }
}
