package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import View.TextItem;

public class TxtFileReader {

  public static void loadTxtFile(Presentation presentation, String filename) throws IOException {
    try {
      File file = new File(filename);
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;
      Slide slide = new Slide(); // cria um novo slide para adicionar os itens do arquivo TXT
      while ((line = br.readLine()) != null) {
        slide.append(new TextItem(1, line)); // cria um novo TextItem com nível 1 para cada linha do arquivo
      }
      br.close();
      presentation.append(slide); // adiciona o slide à apresentação
    } catch (IOException iox) {
      System.err.println(iox.toString());
    }
  }

}
