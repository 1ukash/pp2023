package ru.sbstu.icst.hsai.pp;

import java.util.Date;

public class ParallelCodeRunsHere implements Runnable{

	private SharedObject obj;

	public ParallelCodeRunsHere(SharedObject obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println("My name is " + name);
		
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println(name + " " + new Date());
			obj.inc();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		
		//cleanup
		System.out.println("Thread " + name + " is cleaning up and dying");
	}

}
