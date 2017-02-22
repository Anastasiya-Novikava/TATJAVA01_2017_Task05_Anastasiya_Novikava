package com.epam.task5.dao.parser.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.task5.bean.Food;
import com.epam.task5.dao.parser.Parser;
import com.epam.task5.dao.parser.exception.ParserException;
import com.epam.task5.dao.parser.sax.MenuSaxHandler;

public class MenuSaxParser implements Parser{
	private final static String PATH = "menu.xml";
	
	@Override
	public HashMap<String, ArrayList<Food>> parseXML() throws ParserException  {
		HashMap<String, ArrayList<Food>> menu = new HashMap<String, ArrayList<Food>>();

		try{
		XMLReader reader = XMLReaderFactory.createXMLReader();
		MenuSaxHandler handler = new MenuSaxHandler();
		reader.setContentHandler(handler);
		reader.parse(new InputSource(PATH));
		menu = handler.getMenu();
		} catch (SAXException | IOException e) {
            throw new ParserException(e);
        }
		return menu;
	}
}
