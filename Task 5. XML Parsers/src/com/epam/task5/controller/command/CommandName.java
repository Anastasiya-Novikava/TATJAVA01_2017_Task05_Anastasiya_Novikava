package com.epam.task5.controller.command;

public enum CommandName {

	PARSE("parse"),
    WRONG_REQUEST("wrong_request");
	
	String name;

    CommandName(String name) {
        this.name = name;
    }
}


