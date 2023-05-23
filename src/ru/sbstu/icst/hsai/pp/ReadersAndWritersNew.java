package ru.sbstu.icst.hsai.pp;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadersAndWritersNew {
	
	private static CopyOnWriteArrayList<Integer> sharedList = new CopyOnWriteArrayList<>();
	
	static class ReaderThread implements Runnable {
		

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				for (Integer i: sharedList ) {
					System.out.println(Thread.currentThread().getName() + " read " + i);
				}
			}
		}
		
	}
	
	static class WriterThread implements Runnable {
		

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				Integer num = new Random().nextInt(1000);
				sharedList.add(num);
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
