package com.beck.dschedule.common.event;
/**
 * 
 * O: 就是被观察的对象，因为它承载了数据源，提供条件的判断。
 * V: 满足某个条件的固定值
 */
public abstract class BaseCondition<O,V> {
	protected O observiable ;
	protected V value;
	public BaseCondition(O observiable,V v) {
		this.observiable = observiable;
		this.value = v ;
	}
	public abstract boolean isFinished();
	public abstract void handler() ;

	public O getObserviable() {
		final O o = observiable; 
		return o;
	}
	public V getValue() {
		final V v = value;
		return v;
	}
}
