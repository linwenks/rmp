package com.rmp.common.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private static Gson gson = new GsonBuilder()
//			.setPrettyPrinting()
			.disableHtmlEscaping()
			.create();
	
	private JsonUtil() {
	}

	public static String toJson(Object o) {
		return gson.toJson(o);
	}
	
	public static <T> T fromJson(String jsonStr, Class<T> t) {
		return gson.fromJson(jsonStr, t);
	}
	/*
	public static List<QueryPage> fromJson2(String jsonStr) {
		return gson.fromJson(jsonStr, new TypeToken<List<QueryPage>>() {}.getType());  
	}
	*/
	public static <T> T fromJson(String jsonStr, Type typeOfT) {
		return gson.fromJson(jsonStr, typeOfT);  
	}
}
