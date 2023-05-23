package ru.sbstu.icst.hsai.pp;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuturesExamples {
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		CompletableFuture<Date> f = CompletableFuture.supplyAsync(
														() -> {
															
															try {
																Thread.sleep(3000);
															} catch (InterruptedException e) {
																// TODO Auto-generated catch block
																e.printStackTrace();
															}
															System.out.println(Thread.currentThread().getName());
															
															return new Date();
														}
													  );
		
		f.thenAccept(x -> {
			
			System.out.println(new String(x + " is appended"));
			
		});
		System.out.println("Then accepted");
		Date d = f.get();
		
		System.out.println(d);
		
		
		CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
			return 1;
		}).thenApply((Integer x) -> {
			return x * 2;
		});
		
		int x = f2.get();
		System.out.println(x);
		
	}

}
