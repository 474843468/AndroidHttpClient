package com.psi.androidhttpclient.message;

public class AckMessage extends Message {

	private static final long serialVersionUID = -6691115575755100038L;
	private String code;
	private String content;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public static AckMessage buildOfflineACK(){
		AckMessage ack = new AckMessage();
		ack.setCode("20001");
		ack.setContent("目标用户不在线");
		return ack;
	}
	
}
