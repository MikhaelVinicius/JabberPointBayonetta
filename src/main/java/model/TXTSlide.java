package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TXTSlide extends Slide {

  public TXTSlide(String fileName) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));
    String line;
    StringBuilder content = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      content.append(line).append('\n');
    }
    reader.close();
    setContent(content.toString());
  }

}
