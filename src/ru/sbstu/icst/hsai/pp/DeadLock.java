package ru.sbstu.icst.hsai.pp;

import java.util.Random;

public class DeadLock {
	
	
	static Object[] locks = new Object[] {new Object(), new Object()};
	static int[] ids = new int[] {0, 0};
	volatile int forNonBlockRead;
	
	static class DeadLockGenerator implements Runnable {

		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				Random r = new Random();
//				int idx1 = r.nextInt(2);
//				int idx2 = (idx1 + 1) % 2;
				int idx1 = 0;
				int idx2 = 1;
				synchronized (locks[idx1]) {
					System.out.println(Thread.currentThread().getName() + " acquired lock " + idx1);
					synchronized (locks[idx2]) {
						System.out.println(Thread.currentThread().getName() + " acquired lock " + idx2);
						ids[idx1] += ids[idx2];

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
