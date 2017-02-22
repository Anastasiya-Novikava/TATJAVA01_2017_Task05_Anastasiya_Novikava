package com.epam.task5.dao.parser;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.task5.bean.Food;
import com.epam.task5.dao.parser.exception.ParserException;

public interface Parser {
	 HashMap<String, ArrayList<Food>> parseXML() throws ParserException;
}
