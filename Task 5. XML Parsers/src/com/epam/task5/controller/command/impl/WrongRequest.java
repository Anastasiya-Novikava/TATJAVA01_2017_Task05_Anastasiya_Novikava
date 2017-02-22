package com.epam.task5.controller.command.impl;

import com.epam.task5.controller.command.Command;

public class WrongRequest implements Command {

	@Override
	public String execute(String parserName) {
		return "Wrong request";
	}

	
}
