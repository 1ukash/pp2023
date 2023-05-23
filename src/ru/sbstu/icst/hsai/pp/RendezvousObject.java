package ru.sbstu.icst.hsai.pp;

public class RendezvousObject {
	
	private Integer inputObject;
	private Integer resultObject;
	private int counter = 0;
	
	
	public int provideInputAndGetResult(int x) throws InterruptedException {
		this.inputObject = x;
		// notify that x provided
		synchronized (this) {
			this.notify();
		}
		
		
		//wait for calc
		while(resultObject == null) {
			synchronized (this) {
				wait();
			}
		}
		int res =  resultObject;
		resultObject = null;
		inputObject = null;
		return res;
	}
	
	public void calculateResult() throws InterruptedException {
		
		// wait for input
		while (inputObject == null) {
			synchronized (this) {
				wait();
			}
		}
		
		// calc
		resultObject = inputObject * counter;
		counter ++;
		Thread.sleep(1000);
		
		/// notify
		synchronized (this) {
			notify();
		}
		
	}
	
	

}
