package br.com.caelum.javaone.mapper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JsonMapper {

	public String toJSON(Object t) {
		return new JsonObject(t).toString();
	}

	public Object fromJSON(String line) {
		return null;
	}

	public List<String> toJson(List<?> list) {
		List<String> lines = new ArrayList<>();
		for (Object value : list) {
			lines.add(new JsonObject(value).toString());
		}
		return lines;
	}

	public void toJson(List<?> list, OutputStream out) throws IOException {
		PrintStream ps = new PrintStream(out);
		ps.print("[ ");

		for (int i = 0; i < list.size(); i++) {

			if (i > 0) {
				ps.print(",");
			}
			
			Object value = list.get(i);
			ps.print(new JsonObject(value));

		}
		ps.print("]");
		ps.close(); 

	}

}
