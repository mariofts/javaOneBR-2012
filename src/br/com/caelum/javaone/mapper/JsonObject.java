package br.com.caelum.javaone.mapper;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JsonObject {
	
	private SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");

	private Class<?> clazz;

	private List<MethodAndField> getters = new ArrayList<>();

	private final Object obj;

	public JsonObject(Object value) {
		this.obj = value;
		this.clazz = value.getClass();

		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				String fieldName = getFieldName(method);
				getters.add(new MethodAndField(method, fieldName));
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");

		for (int i = 0; i < getters.size(); i++) {

			MethodAndField mf = getters.get(i);

			if (i > 0) {
				sb.append(", ");
			}

			try {
				Object value = mf.getMethod().invoke(obj);

				String fieldName = mf.getFieldName();

				sb.append("\"");
				sb.append(fieldName);
				sb.append("\"");
				sb.append(":");
				sb.append(getStringValueFor(value));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}
		sb.append("}");
		return sb.toString();
	}

	private String getStringValueFor(Object value) {
		if (value instanceof String) {
			return "\"" + value.toString() + "\"";
		} else if (value instanceof Calendar) {
			return "\"" + format.format(((Calendar) value).getTime()) + "\"";
		} else
			return value.toString();
	}

	private String getFieldName(Method method) {
		return method.getName().substring(3, 4).toLowerCase()
				+ method.getName().substring(4);
	}

	private class MethodAndField {

		private Method method;
		private String fieldName;

		public MethodAndField(Method method, String fieldName) {
			this.method = method;
			this.fieldName = fieldName;
		}

		public Method getMethod() {
			return method;
		}

		public String getFieldName() {
			return fieldName;
		}
	}

}
