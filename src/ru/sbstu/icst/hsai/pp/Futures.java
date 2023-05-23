package ru.sbstu.icst.hsai.pp;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Futures {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		ExecutorService pool = Executors.newFixedThreadPool(1);
		
		Future<Date> d = pool.submit(new Callable<Date>() {

			@Override
			public Date call() throws Exception {
				
				Thread.sleep(1000);
				
				return new Date();
			}
			
			
		});
		
		d.cancel(false);
		
		Date date = d.get(2, TimeUnit.SECONDS);
		System.out.println(date);
		
	}
}
