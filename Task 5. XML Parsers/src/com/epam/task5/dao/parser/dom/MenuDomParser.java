package com.epam.task5.dao.parser.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.SAXException;

import com.epam.task5.bean.Food;
import com.epam.task5.dao.parser.Parser;
import com.epam.task5.dao.parser.exception.ParserException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class MenuDomParser implements Parser {
	private final static String PATH = "menu.xml";
	
	@Override
	public HashMap<String, ArrayList<Food>> parseXML() throws ParserException {
		DOMParser parser = new DOMParser();
		try {
			parser.parse(PATH);
		} catch (SAXException | IOException e) {
			throw new ParserException(e);
		}
		Document document = parser.getDocument();
		
		Element root = document.getDocumentElement();

		HashMap<String, ArrayList<Food>> menu = new HashMap<String, ArrayList<Food>>();
		ArrayList<Food> foodList = new ArrayList<Food>();
		Food food;
		String type = null;

		NodeList typeNodes = root.getElementsByTagName("type");

		for (int i = 0; i < typeNodes.getLength(); i++) {

			foodList = new ArrayList<Food>();
			Element typeElement = (Element) typeNodes.item(i);
			type = typeElement.getAttribute("id");

			NodeList foodNodes = typeElement.getElementsByTagName("food");
			for (int j = 0; j < foodNodes.getLength(); j++) {
				food = new Food();
				Element foodElement = (Element) foodNodes.item(j);
				food.setId(foodElement.getAttribute("id"));
				food.setName(getSingleChild(foodElement, "link").getTextContent().trim());
				food.setName(getSingleChild(foodElement, "name").getTextContent().trim());
				setDescriptionAndPrice(food, foodElement);
				setWeightOrCount(food, foodElement);
				foodList.add(food);
			}
			menu.put(type, foodList);
		}
		return menu;
	}

	private static Element getSingleChild(Element element, String childName) {
		NodeList nlist = element.getElementsByTagName(childName);
		Element child = (Element) nlist.item(0);
		return child;
	}

	private static void setDescriptionAndPrice(Food food, Element foodElement) {
		NodeList descriptionNodes = foodElement.getElementsByTagName("descriptionAndPrice");
		for (int i = 0; i < descriptionNodes.getLength(); i++) {
			Element descriptionElement = (Element) descriptionNodes.item(i);
			String description = getSingleChild(descriptionElement, "description").getTextContent().trim();
			String price = getSingleChild(descriptionElement, "price").getTextContent().trim();
			food.addDescriptionAndPrice(description, price);
		}
	}

	private static void setWeightOrCount(Food food, Element foodElement) {
		Element count = getSingleChild(foodElement, "count");

		if (count != null) {
			int countReal = Integer.parseInt(count.getTextContent().trim());
			food.setCount(countReal);

		} else {
			NodeList weightNodes = foodElement.getElementsByTagName("weight");
			for (int i = 0; i < weightNodes.getLength(); i++) {
				Element weight = (Element) weightNodes.item(i);
				int weightReal = Integer.parseInt(weight.getTextContent().trim());
				food.addWeight(weightReal);

			}
		}
	}
}