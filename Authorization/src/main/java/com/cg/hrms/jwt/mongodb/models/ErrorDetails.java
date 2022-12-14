package com.cg.hrms.jwt.mongodb.models;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

public class ErrorDetails {


	public ErrorDetails(LocalDateTime timestamp, HttpStatus status, String reason, String message) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.reason = reason;
		this.message = message;
	}
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String reason;
	private String message;
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMessage() {
		return message;
	}
	public ErrorDetails() {
		super();
	}
	public void setMessage(String message) {
		this.message = message;
	}

}