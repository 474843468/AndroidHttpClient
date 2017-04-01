package com.psi.androidhttpclient.message;

import java.io.Serializable;

public class Message implements Serializable {

	
	private static final long serialVersionUID = 7562349126853188083L;
	private String from;
	private String to;
	
	public Message() {
		super();
	}
	
	public Message(String from, String to) {
		super();
		this.from = from;
		this.to = to;
	}
	
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	
}
