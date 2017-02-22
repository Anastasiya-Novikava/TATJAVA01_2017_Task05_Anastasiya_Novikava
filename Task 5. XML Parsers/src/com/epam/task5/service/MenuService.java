package com.epam.task5.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.task5.bean.Food;
import com.epam.task5.service.exception.ServiceException;

public interface MenuService {
	HashMap<String, ArrayList<Food>> parseXML(String parserName) throws ServiceException;
}
