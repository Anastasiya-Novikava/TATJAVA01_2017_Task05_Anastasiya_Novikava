package com.epam.task5.service.util;

public class Validator {
	public static boolean validateName(String parserName){
		if (parserName == null || parserName.isEmpty()) {
			return false;
		}
		return true;
	}
}
