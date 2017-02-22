package com.epam.task5.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.task5.bean.Food;
import com.epam.task5.dao.exception.DAOException;

public interface MenuDAO {
	HashMap<String, ArrayList<Food>> parseXML(String parserName) throws DAOException;
}
