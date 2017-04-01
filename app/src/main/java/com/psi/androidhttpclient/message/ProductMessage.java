package com.psi.androidhttpclient.message;

public class ProductMessage extends Message {

	private static final long serialVersionUID = 5664398205093745345L;
	
	private String productId;
	
	private String stock;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
	
	
}
