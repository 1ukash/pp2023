package ru.sbstu.icst.hsai.pp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicLong;

public class ConcurrentStuff {
	
	static Map<String,String> map = new HashMap<>();
	static ConcurrentHashMap<String, String> cm = new ConcurrentHashMap<>();
	
	static AtomicLong counter = new AtomicLong(10);
	static ThreadLocal<Integer> tl = new ThreadLocal<>();
	
	
	public static void main(String[] args) {
		
		Map<String,String> syncMap = Collections.synchronizedMap(map);
 		
		tl.get();
		tl.set(1);
		
		
		
	}

}
