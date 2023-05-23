package ru.sbstu.icst.hsai.pp;

import java.util.LinkedList;
import java.util.List;

public class BadStoryWithMoney {
	
	public static final int GOOD_PRICE = 3, INITIAL_BALANCE = 1000, BUYERS_NUM = 10, GOODS_NUM = 10000;
	
	static abstract class Bag {
		private int goodsNum;
		private int account;
		
		public Bag(int goodsNum, int account) {
			super();
			this.goodsNum = goodsNum;
			this.account = account;
		}

		public int getGoodsNum() {
			return goodsNum;
		}

		public void setGoodsNum(int goodsNum) {
			this.goodsNum = goodsNum;
		}

		public int getAccount() {
			return account;
		}

		public void setAccount(int account) {
			this.account = account;
		}
		
		public void sell() {
			account += GOOD_PRICE;
			goodsNum --;
		}
		
		public void buy() {
			account -= GOOD_PRICE;
			goodsNum ++;
		}
		
	}
	
	static class Store extends Bag {

		public Store() {
			super(GOODS_NUM, 0);
		}


	}
	
	static class Buyer extends Bag implements Runnable {
		
		public Buyer() {
			super(0, INITIAL_BALANCE);
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted() && getAccount() >= GOOD_PRICE) {
				
				boolean passedCheck = false;
				
				synchronized (store) {
					if (store.getGoodsNum() > 0) {
						store.sell();
						passedCheck = true;
					}
				}
				if (passedCheck)
					this.buy();
			}
		}
		
	}
	
	private static Store store = new Store();
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Goods=" + store.getGoodsNum() + ", Money=" + BUYERS_NUM * INITIAL_BALANCE);
		
		List<Thread> threads = new LinkedList<>();
		List<Buyer> buyers = new LinkedList<>();
		
		for (int i = 0; i < BUYERS_NUM; i ++) {
			Buyer b = new Buyer();
			Thread t = new Thread(b);
			threads.add(t);
			buyers.add(b);
			t.start();
		}
		
		for (Thread t: threads) {
			t.join();
		}
		
		//check balance
		int money = store.getAccount();
		int goods = store.getGoodsNum();
		for (Buyer b: buyers) {
			money += b.getAccount();
			goods += b.getGoodsNum();
		}
		
		System.out.println("Goods=" + goods + ", Money=" + money);
		
		
	}

}
