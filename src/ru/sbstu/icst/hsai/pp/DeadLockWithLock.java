package ru.sbstu.icst.hsai.pp;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockWithLock {
	
	
	static Lock[] locks = new Lock[] {new ReentrantLock(true), new ReentrantLock(true)};
	static int[] ids = new int[] {0, 0};
	volatile int forNonBlockRead;
	
	static class DeadLockGenerator implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				Random r = new Random();
				int idx1 = r.nextInt(2);
				int idx2 = (idx1 + 1) % 2;
				
				while (locks[idx1].tryLock()) {
					try {
						System.out.println(Thread.currentThread().getName() + " acquired lock " + idx1);
						if (locks[idx2].tryLock()) {
							try {
								System.out.println(Thread.currentThread().getName() + " acquired lock " + idx2);
								ids[idx1] += ids[idx2];
							} finally {
								locks[idx2].unlock();
							}
	
						} else {
							System.out.println(Thread.currentThread().getName() + " failed to get lock " + idx2);
						}
					} finally {
						locks[idx1].unlock();
					}
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		new Thread(new DeadLockGenerator()).start();
		new Thread(new DeadLockGenerator()).start();
		
	}

}
