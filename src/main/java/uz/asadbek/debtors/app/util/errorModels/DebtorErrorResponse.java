/******************************************************************************
 * Copyright (c) 2024.  Asadbek Rajabboyev         created 1/11/24, 5:25 PM   *
 ******************************************************************************/

package uz.asadbek.debtors.app.util.errorModels;

public class DebtorErrorResponse {

	private String message;
	private long timestamp;


	public DebtorErrorResponse(String message, long timestamp) {
		this.message = message;
		this.timestamp = timestamp;
	}

	public DebtorErrorResponse() {
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
