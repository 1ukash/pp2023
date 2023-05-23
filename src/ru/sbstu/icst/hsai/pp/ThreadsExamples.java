package ru.sbstu.icst.hsai.pp;

import java.util.Random;

public class ThreadsExamples {
	
	public static void main(String[] args) {
		System.out.println("Hey");
		
		
		SharedObject obj = new SharedObject();
		ParallelCodeRunsHere pcrh = new ParallelCodeRunsHere(obj);
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(pcrh);
			t.start();
		}
		
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(new Random().nextInt(2000));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			System.out.println(obj.getCommonCounter());
		}
		
//		try {
//			Thread.sleep(new Random().nextInt(2000));
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//		t.interrupt();
//		
//		try {
//			t.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}
