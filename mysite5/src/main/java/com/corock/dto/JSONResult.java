package com.corock.dto;

/**
 * {@value #result}  :		"success" | "fail"
 * {@value #message} :		result가 "fail"이면 에러 내용
 * 									"success" 이면 null
 * {@value #data}	 :		result가 "fail" null
 * 									"success" 이면 객체
 */
public class JSONResult {

	private String result;
	private String message;
	private Object data;

	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public static JSONResult success( Object data ) {
		return new JSONResult( "success", null, data );
	}

	public static JSONResult fail( String message ) {
		return new JSONResult( "fail", message, null );
	}

	public String getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

}
