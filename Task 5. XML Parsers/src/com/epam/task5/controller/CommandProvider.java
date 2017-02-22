package com.epam.task5.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.task5.controller.command.Command;
import com.epam.task5.controller.command.CommandName;
import com.epam.task5.controller.command.impl.ParserXML;
import com.epam.task5.controller.command.impl.WrongRequest;

final class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<>();

	CommandProvider() {
		repository.put(CommandName.PARSE, new ParserXML());
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
	}

	Command getCommand(String name) {
		CommandName commandName = null;
		Command command = null;
		
		commandName = CommandName.valueOf((name.toUpperCase()));
		command = repository.get(commandName);

		return command;
	}
}
