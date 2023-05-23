package ru.sbstu.icst.hsai.pp;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {
	
	private final int capacity;
	private List<T> data = new LinkedList<>();
	
	public BlockingQueue(int capacity) {
		this.capacity = capacity;
	}
	
	public synchronized T  get() throws InterruptedException {
		
		while(data.isEmpty()) {
			wait();
		}

		T elem = null;
		elem =  data.remove(0);
		notify();
		return elem;
		
	}
	
	public synchronized  void put (T obj) throws InterruptedException {
		
		while(data.size() == capacity) {
				wait();
		}
	
		data.add(obj);
		notify();
	}
	

}
