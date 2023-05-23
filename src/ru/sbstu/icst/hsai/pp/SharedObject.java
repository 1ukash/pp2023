package ru.sbstu.icst.hsai.pp;

public class SharedObject {
	
	private int commonCounter;



	public synchronized int getCommonCounter() {
		return commonCounter;
	}

	public synchronized void setCommonCounter(int commonCounter) {
		this.commonCounter = commonCounter;
	}
	
	public void inc() {
		commonCounter ++;
	}
}
