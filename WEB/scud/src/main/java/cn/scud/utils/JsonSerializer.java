package cn.scud.utils;

import com.google.gson.Gson;

public class JsonSerializer {

	private final Gson gson = new Gson();

	public JsonSerializer() {

	}

	public <T> String serialize(T t) {

		String json = gson.toJson(t);
		return json;
	}

	@SuppressWarnings("unchecked")
	public <T> T deSerialize(String json, Class<?> cls) {
		T t = null;
		if ("" == json || null == json || null == cls) {
			throw new AssertionError("json is null");
		}
		try {
			t = (T) gson.fromJson(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}
}
