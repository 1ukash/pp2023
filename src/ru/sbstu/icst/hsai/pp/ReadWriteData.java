package ru.sbstu.icst.hsai.pp;

import java.util.concurrent.Semaphore;

public class ReadWriteData {
	
	private int currentReadersNum = 0;
	private Semaphore readerSemaphore = new Semaphore(10);
	private Semaphore writerSemaphore = new Semaphore(10);
	private boolean isWriting;
	
	
	int idx = 0;
	
	public int read() throws InterruptedException {
		
		int res = 0;
		boolean canGo = false;
		
		readerSemaphore.acquire();
		currentReadersNum ++;
		readerSemaphore.release();
		
		res = idx;
		
		readerSemaphore.acquire();
		currentReadersNum --;
		readerSemaphore.release();
		
		return res;
	}
	
	public void write(int val) {
		
		//ensure that nobody reads
		
		//writerSemaphore.tr
		
		//ensure that nobody writes
		
		idx += val;
		
	}

}
