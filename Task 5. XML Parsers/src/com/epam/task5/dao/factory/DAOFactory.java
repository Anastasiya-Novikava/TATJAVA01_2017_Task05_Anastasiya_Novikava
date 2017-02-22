package com.epam.task5.dao.factory;

import com.epam.task5.dao.MenuDAO;
import com.epam.task5.dao.impl.XMLMenuDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

    private final MenuDAO xmlMenuImpl = new XMLMenuDAO();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public MenuDAO getMenuDAO(){
        return xmlMenuImpl;
    }
}
