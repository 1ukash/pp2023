package ru.sbstu.icst.hsai.pp;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PoolsEtc {
	
	private static ExecutorService pool;

	static {
		pool = Executors.newFixedThreadPool(5);
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		
		List<Future<Integer>> results = new LinkedList<>();
		
		for (int i = 0; i < 100; i++) {
			int x = i;
			Future<Integer> result = pool.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					try {
						Thread.sleep(1000 + new Random().nextInt(2000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread().getName() + " has completed the task " + x);
					return new Random().nextInt(10);
				}
			});
			
			results.add(result);
			
		}
		
		Set<Future<Integer>> finished = new HashSet<>();
		
		while(finished.size() < results.size()) {
			for (Future<Integer> r: results) {
				if (r.isDone() && !finished.contains(r) ) {
					Integer res = r.get();
					System.out.println(r + " completed calculating result " + res);
					
					finished.add(r);
					
				} 
			}
		}
		
		System.out.println("Finished");
		
		
	}

}
