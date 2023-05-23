package ru.sbstu.icst.hsai.pp;

import java.util.Random;

public class RendezvousRunner {
	public static void main(String[] args) {
		
		final RendezvousObject obj = new RendezvousObject();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted())
					try {
						Thread.sleep(new Random().nextInt(2000));
						int r = obj.provideInputAndGetResult(10);
						System.out.println("Result is " + r);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(!Thread.currentThread().isInterrupted())
					try {
						Thread.sleep(new Random().nextInt(2000));
						obj.calculateResult();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		}).start();
		
	}
}
