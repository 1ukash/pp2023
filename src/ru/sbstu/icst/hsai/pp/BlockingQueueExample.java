package ru.sbstu.icst.hsai.pp;

import java.util.Random;

public class BlockingQueueExample {
	
	public static void main(String[] args) {
		final BlockingQueue<Integer> queue = new BlockingQueue<>(10);
		
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted())
					try {
						Thread.sleep(new Random().nextInt(50));
						int val = new Random().nextInt(10);
						System.out.println("Put " + val);
						queue.put(val);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}).start();
		
		for (int i = 0; i< 10; i++)
			new Thread(new Runnable() {
				
				
				@Override
				public void run() {
					while(!Thread.currentThread().isInterrupted())
						try {
							Thread.sleep(new Random().nextInt(1000));
							int val = queue.get();
							
							System.out.println(Thread.currentThread().getName() + " read " + val);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				}
			}).start();
		
	}

}
