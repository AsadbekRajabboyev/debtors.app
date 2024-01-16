/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/10/24, 4:56 PM   *
 ******************************************************************************/

package uz.asadbek.debtors.app.util.errorModels;


public class UserResponseError {
	private String message;
	private long timestamp;


	public UserResponseError(String message, long timestamp) {
		this.message = message;
		this.timestamp = timestamp;
	}

	public UserResponseError() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
