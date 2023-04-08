package model;

import java.util.List;

public interface SlideContainer {

  int getSize();

  String getTitle();

  void setTitle(String title);

  int getSlideNumber();

  void setSlideNumber(int number);

  void prevSlide();

  void nextSlide();

  void clear();

  void append(Slide slide);

  Slide getSlide(int number);

  Slide getCurrentSlide();

  void exit(int n);

  List<Slide> getShowList();

}
