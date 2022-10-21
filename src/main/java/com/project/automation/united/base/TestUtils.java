package com.project.automation.united.base;

	public class TestUtils extends BaseTest{
		public static long PAGE_LOAD_TIME = 20;
		public static long EXPLICIT_WAIT_TIME = 20;
		
		public void sleep(int x) {
			try {
				Thread.sleep(x);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


