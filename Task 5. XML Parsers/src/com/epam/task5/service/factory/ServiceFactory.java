package com.epam.task5.service.factory;

import com.epam.task5.service.MenuService;
import com.epam.task5.service.impl.MenuServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final MenuService menuService = new MenuServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance;
    }

    public MenuService getMenuService(){
        return menuService;
    }
}