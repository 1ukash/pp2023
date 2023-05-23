package ru.sbstu.icst.hsai.pp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConcModExc {
	
	public static void main(String[] args) {
		
		List<Integer> l = new ArrayList<>();
		
		l.add(1);
		l.add(2);
		
		for (Integer i: l) {
			l.add(3);
		}
		
		List l2 = Collections.synchronizedList(l);
		
	}

}
