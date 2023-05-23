package ru.sbstu.icst.hsai.pp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RendezvousObjectNew {
	
	private Integer inputObject;
	private Integer resultObject;
	private int counter = 0;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	public int provideInputAndGetResult(int x) throws InterruptedException  {
		this.inputObject = x;
		// notify that x provided
		
		try {
			lock.lock();
			cond.signal();
		} finally {
			lock.unlock();
		}
		
		//wait for calc
		while(resultObject == null) {
			
			lock.lock();
			try {
				cond.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw e;
			} finally {
				lock.unlock();
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
			try {
				lock.lock();
				cond.await();
			} finally {
				lock.unlock();
			}
		}
		
		// calc
		resultObject = inputObject * counter;
		counter ++;
		Thread.sleep(1000);
		
		/// notify
		try {
			lock.lock();
			cond.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
	

}
