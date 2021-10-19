package org.web.base.helper;

public class EnumHelper {

	public enum BOOLEAN {
		Y, N
	}

	public enum DELETE {
		Y, N
	}
	
	public static boolean checkExist(Class<?> e, String arg) {
		Object[] array = e.getEnumConstants();
		for (Object o : array) {
			if (o.toString().equalsIgnoreCase(arg)) {
				return true;
			}
		}
		return false;
	}


}
