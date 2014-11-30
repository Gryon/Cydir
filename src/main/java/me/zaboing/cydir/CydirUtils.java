package me.zaboing.cydir;

import java.lang.reflect.Field;


public class CydirUtils {

	public static <T> T getPrivateField(Object object, String name, Class<T> outClass) {
		return getPrivateField(object, name, object.getClass(), outClass);
	}
	
	public static <T> T getPrivateField(Object object, String name, Class<?> inClass, Class<T> outClass) {
		try {
			Field field =inClass.getDeclaredField(name);
			field.setAccessible(true);
			return (T)field.get(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
