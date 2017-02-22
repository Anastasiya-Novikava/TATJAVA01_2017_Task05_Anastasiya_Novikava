package com.epam.task5.dao.parser.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import com.epam.task5.bean.Food;
import com.epam.task5.bean.MenuTagName;
import com.epam.task5.dao.parser.Parser;
import com.epam.task5.dao.parser.exception.ParserException;

public class MenuStaxParser implements Parser {
	private final static String PATH = "menu.xml";

	private Food food;
	private String type;
	private String description;
	private String price;

	private ArrayList<Food> foodList;
	private HashMap<String, ArrayList<Food>> menu = new HashMap<String, ArrayList<Food>>();

	@Override
	public HashMap<String, ArrayList<Food>> parseXML() throws ParserException {
		MenuTagName elementName = null;
		
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream(PATH);
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

			while (reader.hasNext()) {
				int element = reader.next();
				if (element == XMLStreamConstants.START_ELEMENT) {
					elementName = MenuTagName.valueOf(reader.getLocalName().toUpperCase());

					if (elementName == MenuTagName.TYPE) {
						type = reader.getAttributeValue(null, "id");
						foodList = new ArrayList<Food>();
					}

					else if (elementName == MenuTagName.FOOD) {
						food = new Food();
						food.setId(reader.getAttributeValue(null, "id"));
					} 

				} else if (element == XMLStreamConstants.CHARACTERS) {
					String text = reader.getText().trim();
					if (text.isEmpty()) {
						continue;
					}

					if (elementName == MenuTagName.DESCRIPTION) {
						description = text;

					} else if (elementName == MenuTagName.PRICE) {
						price = text;

					} else if (elementName == MenuTagName.LINK) {
						food.setLink(text);

					} else if (elementName == MenuTagName.NAME) {
						food.setName(text);

					} else if (elementName == MenuTagName.COUNT) {
						food.setCount(Integer.parseInt(text));
						
					} else if (elementName == MenuTagName.WEIGHT) {
						food.addWeight(Integer.parseInt(text));
					}

				} else if (element == XMLStreamConstants.END_ELEMENT) {
					elementName = MenuTagName.valueOf(reader.getLocalName().toUpperCase());

					if (elementName == MenuTagName.DESCRIPTIONANDPRICE) {
						food.addDescriptionAndPrice(description, price);

					} else if (elementName == MenuTagName.FOOD) {
						foodList.add(food);
					}

					menu.put(type, foodList);
					
				}
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			throw new ParserException(e);
		}

		return menu;
	}

}