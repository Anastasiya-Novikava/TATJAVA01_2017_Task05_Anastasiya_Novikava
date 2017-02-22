package com.epam.task5.controller.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.epam.task5.bean.Food;
import com.epam.task5.controller.command.Command;
import com.epam.task5.service.MenuService;
import com.epam.task5.service.exception.ServiceException;
import com.epam.task5.service.factory.ServiceFactory;

public class ParserXML implements Command {

	@Override
	public String execute(String parserName) {
		String response = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		MenuService menuService = factory.getMenuService();
		
		StringBuilder builder = new StringBuilder();
		try {
			HashMap<String, ArrayList<Food>> menu = menuService.parseXML(parserName);
			for (Map.Entry<String, ArrayList<Food>> entry : menu.entrySet()) {
				builder.append(createResponse(entry.getKey(), entry));
			}
			response = builder.toString();

		} catch (ServiceException e) {
			response = "Error during parser procedure";
		}

		return response;
	}

	private String createResponse(String type, Map.Entry<String, ArrayList<Food>> foodList) {
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");

        for (Food food: foodList.getValue()) {
            stringBuilder.append("Название: ").append(food.getName());

            HashMap<String, String> description = food.getDescriptionAndPrice();

            for (Map.Entry<String, String> about : description.entrySet()) {		
                stringBuilder.append("; описание: ").append(about.getKey()).append("; цена: ").append(about.getValue());
            }
            stringBuilder.append("; порция: ");
            if (food.getCount() != 0) {
                stringBuilder.append(food.getCount()).append("шт");
            } else if (food.getListWeight().size() != 0) {
                for (int listWeight : food.getListWeight()) {
                    stringBuilder.append(listWeight).append(" ");
                }
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
       
        return stringBuilder.toString();
    }
}
