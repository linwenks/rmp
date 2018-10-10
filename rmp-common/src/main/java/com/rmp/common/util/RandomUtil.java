package com.rmp.common.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

	public static String getRandomCode(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<num; i++) {
		    sb.append(ThreadLocalRandom.current().nextInt(10));
		}  
		return sb.toString();
	}
	
	public static int nextInt(int bound) {
		int rand = ThreadLocalRandom.current().nextInt(bound);
		return rand;
	}
	
	public static int nextInt(int min, int max) {
		int rand = ThreadLocalRandom.current().nextInt(max) % (max - min + 1) + min;
		return rand;
    }
	
	public static void main(String[] args) {
		System.out.println(nextInt(2));
	}
}
