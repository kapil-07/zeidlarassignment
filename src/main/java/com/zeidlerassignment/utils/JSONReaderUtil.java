package com.zeidlerassignment.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public enum JSONReaderUtil {
	
	INSTANCE;
	private static final Logger LOGGER = Logger.getLogger(JSONReaderUtil.class); 
	public JSONObject readResourceFile(String filePath) {
		
		LOGGER.info("Reading the json file : "+filePath);
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(filePath);
		StringBuilder resultJson = new StringBuilder();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){ 
			String line;
			while((line = br.readLine()) != null){
				resultJson.append(line).append("\n");
			}
			
			
		} catch (IOException e) {
			LOGGER.error("Error Reading Resource File");
			e.printStackTrace();
		}
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try{
			jsonObject = (JSONObject) jsonParser.parse(resultJson.toString());
		}catch(ParseException e){
			LOGGER.error("JSON file supplied has parsing issues");
			e.printStackTrace();
		}
		return jsonObject;
	}

}
