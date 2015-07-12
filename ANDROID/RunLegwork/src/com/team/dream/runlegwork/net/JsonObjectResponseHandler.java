package com.team.dream.runlegwork.net;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.conn.HttpHostConnectException;

import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.TextHttpResponseHandler;
import com.team.dream.runlegwork.entity.RespStatus;
import com.team.dream.runlegwork.net.response.OpteratorResponse;
import com.team.dream.runlegwork.utils.JsonSerializer;

public abstract class JsonObjectResponseHandler<T extends OpteratorResponse> extends TextHttpResponseHandler {

	public JsonObjectResponseHandler() {
		super(DEFAULT_CHARSET);
	}

	public JsonObjectResponseHandler(String encoding) {
		super(encoding);
	}

	public void onSuccess(int statusCode, Header[] headers, T t) {
		onSuccess(t);
		onSuccess(headers);
	}

	public void onSuccess(Header[] headers) {
	}

	public void onFailure(int statusCode, Header[] headers, Throwable throwable, T t) {

		if (throwable instanceof HttpHostConnectException) {
			onFailure("The network link is unavailable, please try again later.");
		} else {
			onFailure(throwable.getMessage());
		}
	}

	public abstract void onSuccess(T response);

	public abstract void onFailure(String errMsg);

	@Override
	public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers, String responseString) {

	}

	@Override
	public void onFailure(final int statusCode, final Header[] headers, final byte[] responseBytes, final Throwable throwable) {
		if (responseBytes != null) {
			Runnable parser = new Runnable() {

				@Override
				public void run() {
					try {
						final T t = parseResponse(responseBytes);
						postRunnable(new Runnable() {
							public void run() {
								onFailure(statusCode, headers, throwable, t);
							}
						});
					} catch (final JsonSyntaxException e) {
						postRunnable(new Runnable() {
							public void run() {
								onFailure(statusCode, headers, e, (T) null);
							}
						});
					}

				}
			};
			if (!getUseSynchronousMode()) {
				new Thread(parser).start();
			} else {
				parser.run();
			}
		} else {
			onFailure("Server not return content! Must be return data!");
		}
	}

	@Override
	public void onSuccess(final int statusCode, final Header[] headers, final byte[] responseBytes) {
		if (statusCode != HttpStatus.SC_NO_CONTENT) {

			Runnable parser = new Runnable() {

				@Override
				public void run() {
					try {
						final T jsonRespone = parseResponse(responseBytes);
						postRunnable(new Runnable() {
							public void run() {
								if (jsonRespone != null) {
									if (jsonRespone.getRespStatus().getResult() != RespStatus.CODE_BASE_SUCCESS) {
										onFailure(jsonRespone.getRespStatus().getMsg());
									} else {
										onSuccess(statusCode, headers, jsonRespone);
									}

								} else {
									onFailure(statusCode, headers, new Exception("repsonse is null"), (T) null);
								}
							}
						});
					} catch (final JsonSyntaxException e) {
						postRunnable(new Runnable() {
							public void run() {
								onFailure(statusCode, headers, e, (T) null);
							}
						});
					}
				}
			};

			if (!getUseSynchronousMode()) {
				new Thread(parser).start();
			} else {
				parser.run();
			}
		} else {
			onFailure("Server not return content! Must be return data!");
		}
	}

	protected T parseResponse(byte[] responseBytes) {
		if (null == responseBytes)
			return null;
		T respone = null;
		String jsonString = getResponseString(responseBytes, getCharset());
		if (jsonString != null) {
			jsonString = jsonString.trim();
			if (jsonString.startsWith(UTF8_BOM)) {
				jsonString = jsonString.substring(1);
			}
			if (jsonString.startsWith("{") || jsonString.startsWith("[")) {
				Type paramClassType = getClass().getGenericSuperclass();
				if (!ParameterizedType.class.isAssignableFrom(paramClassType.getClass())) {
					paramClassType = getClass().getSuperclass().getGenericSuperclass();
				}
				Type[] paramTypes = ((ParameterizedType) paramClassType).getActualTypeArguments();
				JsonSerializer jsonSerializer = new JsonSerializer();
				respone = jsonSerializer.deSerialize(jsonString, paramTypes[0]);

			}
		}
		return respone;
	}

}
