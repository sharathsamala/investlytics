package com.SentimentApi;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class convertTxt {
	

		 public String getOnlyStrings(String s) {
			    Pattern pattern = Pattern.compile("[^a-z A-Z]");
			    Matcher matcher = pattern.matcher(s);
			    String number = matcher.replaceAll("");
			    return number;
			 }
		 

	
}
