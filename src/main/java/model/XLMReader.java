package model;

import java.io.File;
import java.io.IOException;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XMLReader {
	  
	  protected static final String DEFAULT_API_TO_USE = "dom";
	  protected static final String SHOWTITLE = "showtitle";
	  protected static final String SLIDETITLE = "title";
	  protected static final String SLIDE = "slide";
	  protected static final String ITEM = "item";
	  protected static final String LEVEL = "level";
	  protected static final String KIND = "kind";
	  protected static final String TEXT = "text";
	  protected static final String IMAGE = "image";
	  
	  protected static final String PCE = "Parser Configuration Exception";
	  protected static final String UNKNOWNTYPE = "Unknown Element type";
	  protected static final String NFE = "Number Format Exception";
	  
	  private Presentation createPresentation(Element doc) {
	    Presentation presentation = new Presentation();
	    presentation.setTitle(getTitle(doc, SHOWTITLE));
	    NodeList slides = doc.getElementsByTagName(SLIDE);
	    for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
	      Element xmlSlide = (Element) slides.item(slideNumber);
	      Slide slide = createSlide(xmlSlide);
	      presentation.append(slide);
	    }
	    return presentation;
	  }
	  
	  private Slide createSlide(Element xmlSlide) {
	    Slide slide = new Slide();
	    slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
	    NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
	    for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
	      Element item = (Element) slideItems.item(itemNumber);
	      slide.append(createSlideItem(item));
	    }
	    return slide;
	  }
	  
	  private SlideItem createSlideItem(Element item) {
	    int level = 1;
	    NamedNodeMap attributes = item.getAttributes();
	    String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
	    if (leveltext != null) {
	      try {
	        level = Integer.parseInt(leveltext);
	      } catch (NumberFormatException x) {
	        System.err.println(NFE);
	      }
	    }
	    String type = attributes.getNamedItem(KIND).getTextContent();
	    if (TEXT.equals(type)) {
	      return new TextItem(level, item.getTextContent());
	    } else {
	      if (IMAGE.equals(type)) {
	        return new BitmapItem(level, item.getTextContent());
	      } else {
	        System.err.println(UNKNOWNTYPE);
	        return null;
	      }
	    }
	  }
	  
	  private String getTitle(Element element, String tagName) {
	    NodeList titles = element.getElementsByTagName(tagName);
	    return titles.item(0).getTextContent();
	  }
	  
	  public Presentation readXML(String filename) {
	    try {
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	      Document document = builder.parse(new File(filename));
	      Element doc = document.getDocumentElement();
	      return createPresentation(doc);
	    } catch (IOException iox) {
	      System.err.println(iox.toString());
	      return null;
	    } catch (SAXException sax) {
	      System.err.println(sax.getMessage());
	      return null;
	    } catch (ParserConfigurationException pcx) {
	      System.err.println(PCE);
	      return null;
	    }
	  }
	}
