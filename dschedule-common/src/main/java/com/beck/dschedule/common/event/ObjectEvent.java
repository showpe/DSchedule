package com.beck.dschedule.common.event;


public class ObjectEvent<V> {

	private V value;
	private int eventType;

	/**
	 * @param value 事件
	 * @param eventType 事件类型
	 */
	public ObjectEvent( V value, int eventType) {
		this.value = value;
		this.eventType = eventType;
	}

	public int getEventType() {
		return eventType;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
}
