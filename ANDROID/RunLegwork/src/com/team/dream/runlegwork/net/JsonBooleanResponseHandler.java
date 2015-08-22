package com.team.dream.runlegwork.net;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.conn.HttpHostConnectException;

import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.TextHttpResponseHandler;
import com.team.dream.runlegwork.entity.RespStatus;
import com.team.dream.runlegwork.net.response.OpteratorResponse;
import com.team.dream.runlegwork.utils.JsonSerializer;

public abstract class JsonBooleanResponseHandler extends
		TextHttpResponseHandler {

	public JsonBooleanResponseHandler() {
		super(DEFAULT_CHARSET);
	}

	public JsonBooleanResponseHandler(String encoding) {
		super(encoding);
	}

	public void onSuccess(int statusCode, Header[] headers,
			OpteratorResponse response) {
		onSuccess();
		onSuccess(headers);
	}

	public void onSuccess(Header[] headers) {
	}

	public void onFailure(int statusCode, Header[] headers,
			Throwable throwable, OpteratorResponse errorResponse) {
		if (throwable instanceof HttpHostConnectException) {
//			onFailure("The network link is unavailable, please try again later.");
			onFailure("网络部可用, 请稍后再试.");
		} else {
			onFailure(throwable.getMessage());
		}
	}

	public abstract void onSuccess();

	public abstract void onFailure(String errMsg);

	@Override
	public void onFailure(int statusCode, Header[] headers,
			String responseString, Throwable arg3) {

	}

	@Override
	public void onSuccess(int statusCode, Header[] headers,
			String responseString) {

	}

	@Override
	public void onSuccess(final int statusCode, final Header[] headers,
			final byte[] responseBytes) {
		if (statusCode != HttpStatus.SC_NO_CONTENT) {
			Runnable parser = new Runnable() {
				@Override
				public void run() {
					try {
						final OpteratorResponse jsonResponse = parseResponse(responseBytes);
						postRunnable(new Runnable() {
							public void run() {
								if (jsonResponse != null) {
									if (jsonResponse.getRespStatus()
											.getResult() != RespStatus.CODE_BASE_SUCCESS) {
										onFailure(jsonResponse.getRespStatus()
												.getMsg());
									} else {
										onSuccess(statusCode, headers,
												jsonResponse);
									}
								} else {
//									onFailure(statusCode, headers,
//											new Exception(
//													"Reponse content is null"),
//											(OpteratorResponse) null);
									onFailure(statusCode, headers,
											new Exception(
													"数据请求失败"),
											(OpteratorResponse) null);
								}
							}
						});
					} catch (final JsonSyntaxException e) {
						postRunnable(new Runnable() {
							@Override
							public void run() {
								onFailure(statusCode, headers, e,
										(OpteratorResponse) null);
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
			onFailure("数据请求失败");
//			onFailure("Server not return content! Must be return data!");
		}
	}

	@Override
	public void onFailure(final int statusCode, final Header[] headers,
			final byte[] responseBytes, final Throwable throwable) {
		if (responseBytes != null) {
			Runnable parser = new Runnable() {

				@Override
				public void run() {
					try {
						final OpteratorResponse response = parseResponse(responseBytes);
						postRunnable(new Runnable() {
							public void run() {
								onFailure(statusCode, headers, throwable,
										response);
							}
						});
					} catch (final JsonSyntaxException e) {
						postRunnable(new Runnable() {
							public void run() {
								onFailure(statusCode, headers, e,
										(OpteratorResponse) null);
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
			onFailure(statusCode, headers, throwable, (OpteratorResponse) null);
		}
	}

	protected OpteratorResponse parseResponse(byte[] responseBytes) {
		if (null == responseBytes)
			return null;
		OpteratorResponse response = null;
		String responseString = getResponseString(responseBytes, getCharset());
		if (responseString == null)
			return null;
		responseString = responseString.trim();
		if (responseString.startsWith(UTF8_BOM))
			responseString = responseString.substring(1);
		if (responseString.startsWith("{") || responseString.startsWith("[")) {
			JsonSerializer jsonSerializer = new JsonSerializer();
			response = jsonSerializer.deSerialize(responseString,
					OpteratorResponse.class);
			if (response == null) {
				response = new OpteratorResponse();
				response.setRespStatus((RespStatus) jsonSerializer.deSerialize(
						responseString, RespStatus.class));
			}
		}

		return response;
	}

}
