package com.epam.task5.view;

import com.epam.task5.controller.Controller;

public class Runner {
	public static void main(String[] args){
		Controller controller = new Controller();
		System.out.println(controller.executeTask("parse sax"));
	}
}
