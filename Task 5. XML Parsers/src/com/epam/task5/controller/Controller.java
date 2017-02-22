package com.epam.task5.controller;

import com.epam.task5.controller.command.Command;
import com.epam.task5.controller.command.CommandName;

public class Controller {
	private final CommandProvider provider = new CommandProvider();

	private final String DELIMETER = " ";

	public String executeTask(String inputLine) {
		CommandName commandName = null;
		String parserName = null;
		String response = null;

		try {
			commandName = CommandName.valueOf(inputLine.split(DELIMETER)[0].toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			commandName = CommandName.WRONG_REQUEST;
		}

		Command command = provider.getCommand(commandName.name());
		parserName = inputLine.split(DELIMETER)[1];
		response = command.execute(parserName);

		return response;
	}

}
