package ru.sbstu.icst.hsai.pp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadersAndWriters {
	
	private static List<Integer> sharedList = new LinkedList<>();
	private static ReadWriteLock rw = new ReentrantReadWriteLock(true);
	
	static class ReaderThread implements Runnable {
		
		private Lock rl = rw.readLock();

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				rl.lock();
				try {
					for (Integer i: sharedList ) {
						System.out.println(Thread.currentThread().getName() + " read " + i);
					}
				} finally {
					rl.unlock();
				}
			}
		}
		
	}
	
	/*
	 * 1,2,3
	 * add 4
	 * 1.2.3.4
	 */
	
	static class WriterThread implements Runnable {
		
		private Lock wl = rw.writeLock();

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				Integer num = new Random().nextInt(1000);
				wl.lock();
				try {
					sharedList.add(num);
				} finally {
					wl.unlock();
				}
				System.out.println("!!!!!!!!!!!!!!!!!!!" + Thread.currentThread().getName() + " wrote " + num );
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 5; i++) {
			new Thread(new ReaderThread()).start();
			new Thread(new WriterThread()).start();
		}
	}

}
