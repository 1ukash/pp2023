package ru.sbstu.icst.hsai.pp;

public class WaitNotify {
	
	static class StateHolder {
		State state;

		public StateHolder(State s) {
			super();
			this.state = s;
		}

		public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}
		
		
	}
	
	static class Waiter extends StateHolder implements Runnable {

		public Waiter(State s) {
			super(s);
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				
				synchronized (getState()) {
					try {
						getState().wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println(Thread.currentThread().getName() + " woke up and read " + getState().counter);
				}
				
				
			}
		}
		
	}
	
	
	static class Notifier extends StateHolder implements Runnable {

		public Notifier(State s) {
			super(s);
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				
				synchronized (getState()) {
					getState().counter++;
					getState().notifyAll();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}			
		}
		
	}
	
	static class State {
		private int counter;
	}
	
	public static void main(String[] args) {
		
		State s = new State();

		for (int i = 0; i < 10; i++) {
			Thread t1 = new Thread(new Waiter(s));
			t1.start();
		}
		Thread t2 = new Thread(new Notifier(s));
		t2.start();
		
	}
	

}
