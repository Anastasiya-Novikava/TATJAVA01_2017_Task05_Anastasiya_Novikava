package com.epam.task5.dao.parser.factory;

import com.epam.task5.dao.exception.DAOException;
import com.epam.task5.dao.parser.Parser;
import com.epam.task5.dao.parser.dom.MenuDomParser;
import com.epam.task5.dao.parser.sax.MenuSaxParser;
import com.epam.task5.dao.parser.stax.MenuStaxParser;

public class ParserFactory {
	private static ParserFactory instance;

    private ParserFactory() {}

    public static ParserFactory getInstance() {
        if (instance == null) {
            instance = new ParserFactory();
        }

        return instance;
    }

    public Parser getParser(String parserName) throws DAOException {
        if (parserName.toLowerCase().equals("sax")) {
            return new MenuSaxParser();
        } else if (parserName.toLowerCase().equals("stax")) {
            return new MenuStaxParser();
        } else if (parserName.toLowerCase().equals("dom")) {
            return new MenuDomParser();
        }
        throw new DAOException("No such parser");
    }
}

