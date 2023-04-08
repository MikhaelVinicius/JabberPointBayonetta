package model;

import java.util.ArrayList;
import java.util.List;

import View.SlideViewerComponent;

public class Presentation implements SlideContainer {

  private String title;
  private List<Slide> showList = null;
  private SlideViewerComponent slideViewComponent = null;
  private int currentSlideNumber = 0;

  public Presentation() {
    slideViewComponent = null;
    clear();
  }

  public Presentation(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
    clear();
  }

  @Override
  public int getSize() {
    return showList.size();
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String nt) {
    title = nt;
  }

  public void setShowView(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
  }

  @Override
  public int getSlideNumber() {
    return currentSlideNumber;
  }

  @Override
  public void setSlideNumber(int number) {
    currentSlideNumber = number;
    if (slideViewComponent != null) {
      slideViewComponent.update(this, getCurrentSlide());
    }
  }

  @Override
  public void prevSlide() {
    if (currentSlideNumber > 0) {
      setSlideNumber(currentSlideNumber - 1);
    }
  }

  @Override
  public void nextSlide() {
    if (currentSlideNumber < (showList.size() - 1)) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

  @Override
  public void clear() {
    showList = new ArrayList<Slide>();
    setSlideNumber(-1);
  }

  @Override
  public void append(Slide slide) {
    showList.add(slide);
  }

  @Override
  public Slide getSlide(int number) {
    if (number < 0 || number >= getSize()) {
      return null;
    }
    return (Slide) showList.get(number);
  }

  @Override
  public Slide getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

  @Override
  public void exit(int n) {
    System.exit(n);
  }

  @Override
  public List<Slide> getShowList() {
    return showList;
  }

}

