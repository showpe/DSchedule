package com.beck.dschedule.common.quence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * if current action must wait the prefer finished then extend this class.
 * @author Disjob
 *
 */
public abstract class Action implements Runnable {

	protected Logger logger = LogManager.getLogger(getClass());

	protected ActionQueue queue;
	protected Long createTime;
	
	public Action() {
		createTime = System.currentTimeMillis();
	} 
	
	public Action(ActionQueue queue) {
		this.queue = queue;
		createTime = System.currentTimeMillis();
	}
	
	public void setActionQueue(ActionQueue queue){
		this.queue = queue;
	}
	
	public ActionQueue getActionQueue() {
		return queue;
	}   

	public void run() {
		
		if (queue != null) {
			long start = System.currentTimeMillis();
			try {
				execute();
				long end = System.currentTimeMillis();
				long interval = end - start;
				long leftTime = end - createTime;
				if (interval >= 1000) {
					System.err.println("execute action : " + this.toString() + ", interval : " + interval + ", leftTime : " + leftTime + ", size : " + queue.getQueue().size());
					logger.warn("execute action : " + this.toString() + ", interval : " + interval + ", leftTime : " + leftTime + ", size : " + queue.getQueue().size());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("run action execute exception. action : " + this.toString()+e.getMessage());
			} finally {
				queue.dequeue(this);
			}
		}
	}

	public abstract void execute() throws TaskExecuteException;
	
	public void execeptionCaught(Exception e){
		
	}
}
