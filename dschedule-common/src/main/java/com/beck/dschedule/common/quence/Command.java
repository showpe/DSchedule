package com.beck.dschedule.common.quence;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * the different from Action class is that disorder so extends this class will improve throughput 
 * @author Disjob
 *
 */
public abstract class Command implements Runnable{

	protected Logger logger = LogManager.getLogger(getClass());

	private long createTime ;
	public Command() {
		
		this.createTime = System.currentTimeMillis();
	} 
	
	public void run() {
		long start = System.currentTimeMillis();
		try {
			this.execute();
			this.executeSuccess();
		} catch (TaskExecuteException e) {
			this.executeException(e.getMessage());
		}
		long end = System.currentTimeMillis();
		long interval = end - start;
		long leftTime = end - createTime;
		
		if (interval >= 1000 || leftTime >= 1100) {
			logger.warn("execute action : " + this.toString() + ", interval : " + interval + ", leftTime : " + leftTime + ", size : ");
		}
	}
	
	public abstract void executeException(String execeptionMsg);
	
	public abstract void execute() throws TaskExecuteException;
	
	public abstract void executeSuccess();
}
