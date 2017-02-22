package com.epam.task5.dao.parser.sax;

import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.task5.bean.Food;
import com.epam.task5.bean.MenuTagName;

public class MenuSaxHandler extends DefaultHandler{
	private HashMap<String, ArrayList<Food>> menu = new HashMap<String, ArrayList<Food>>();
	private String type;
	private ArrayList<Food> foodList = new ArrayList<Food>();
	private Food food;
	private String description;
    private String price;
	private StringBuilder text;
	
	public HashMap<String, ArrayList<Food>> getMenu() {
		return menu;
	}

	public ArrayList<Food> getFoodList() {
		return foodList;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		text = new StringBuilder();
		if (qName.equals("type")) {
			foodList = new ArrayList<Food>();
			type = attributes.getValue("id");
		} else {
			if (qName.equals("food")) {
				food = new Food();
				food.setId(attributes.getValue("id"));
			}
		}
	}
	
	public void characters(char[] buffer, int start, int length) {
		text.append(buffer, start, length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase().replace("-", "_"));

		switch (tagName) {
		case MENU:
			menu.put(type, foodList);
			break;
		case TYPE:
			menu.put(type, foodList);
			break;
		case FOOD:
			foodList.add(food);
			break;
		case LINK:
			food.setLink(text.toString());
			break;
		case NAME:
			food.setName(text.toString());
			break;
		case DESCRIPTIONANDPRICE:
			food.addDescriptionAndPrice(description, price);
			break;
		case DESCRIPTION:
			description = text.toString();
			break;
		case PRICE:
			price = text.toString();
			break;
		case WEIGHT:
			food.addWeight(Integer.parseInt(text.toString()));
			break;
		case COUNT:
			food.setCount(Integer.parseInt(text.toString()));
			break;
		default:
			break;
		}
	}
		
	public void warning(SAXParseException e) {
		System.err.println("WARNING: line" + e.getLineNumber() + ": " + e.getMessage());
	}

	public void error(SAXParseException e) {
		System.err.println("ERROR: line" + e.getLineNumber() + ": " + e.getMessage());
	}

	public void fatalError(SAXParseException e) throws SAXException {
		System.err.println("FATAL: line" + e.getLineNumber() + ": " + e.getMessage());
		throw (e);
	}
	
}
