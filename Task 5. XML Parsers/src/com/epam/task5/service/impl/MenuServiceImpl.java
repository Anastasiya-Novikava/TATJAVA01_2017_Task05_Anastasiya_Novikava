package com.epam.task5.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.epam.task5.bean.Food;
import com.epam.task5.dao.MenuDAO;
import com.epam.task5.dao.exception.DAOException;
import com.epam.task5.dao.factory.DAOFactory;
import com.epam.task5.service.MenuService;
import com.epam.task5.service.exception.ServiceException;
import com.epam.task5.service.util.Validator;

public class MenuServiceImpl implements MenuService {

	@Override
	public HashMap<String, ArrayList<Food>> parseXML(String parserName) throws ServiceException {
		HashMap<String, ArrayList<Food>> menu;
		
		if(!Validator.validateName(parserName)){
			throw new ServiceException("Incorrect name initialization");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			MenuDAO menuDAO = daoObjectFactory.getMenuDAO();
			menu = menuDAO.parseXML(parserName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return menu;
	}
}
