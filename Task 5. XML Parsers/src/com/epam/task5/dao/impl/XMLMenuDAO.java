package com.epam.task5.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.task5.bean.Food;
import com.epam.task5.dao.MenuDAO;
import com.epam.task5.dao.exception.DAOException;
import com.epam.task5.dao.parser.Parser;
import com.epam.task5.dao.parser.exception.ParserException;
import com.epam.task5.dao.parser.factory.ParserFactory;

public class XMLMenuDAO implements MenuDAO {
	@Override
	public HashMap<String, ArrayList<Food>> parseXML(String parserName) throws DAOException {
		HashMap<String, ArrayList<Food>> menu = new HashMap<String, ArrayList<Food>>();

		try {
			ParserFactory factoryObject = ParserFactory.getInstance();
			Parser parser = factoryObject.getParser(parserName);
			menu = parser.parseXML();
		} catch (ParserException e) {
			throw new DAOException("Exception during parsing", e);
		}
		return menu;
	}

}
