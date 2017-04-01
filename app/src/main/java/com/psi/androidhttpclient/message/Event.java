package com.psi.androidhttpclient.message;

public final class Event {
	
	public static final String HEARTBEAT_EVENT = "heartbeat";
	
	public static final String CREATE_ORDER = "create_order";
	//目标不在线
	public static final String TARGET_OFFLINE = "target_offline";
	
	public static final String RECIVE_MESSAGE = "recive_message";
	
	public static final String ACK_MESSAGE = "ack_message";
	//更新商品库存
	public static final String UPDATE_STOCK = "update_stock";
	
}
