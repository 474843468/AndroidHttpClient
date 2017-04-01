package com.psi.androidhttpclient.message;

public class OrderMessage extends Message {

	private static final long serialVersionUID = 1056029899011954653L;
	
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
